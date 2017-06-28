package ru.aryukov;

import ru.aryukov.cashHandler.CashHandler;
import ru.aryukov.cashHandler.FiveThousandCashHandler;
import ru.aryukov.cashHandler.OneThousandCashHolder;
import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;
import ru.aryukov.request.RequestAction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by oaryukov on 27.06.2017.
 */
public class CashMachine {
    private static List<BaseCashHolder> cashHolderList = new ArrayList<>();
    private static CashHandler cashHandler;

    public CashMachine() {
        init();
    }

    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();
        cashMachine.printCash();
        System.out.println("Сумма в банкомате: " + cashMachine.getTotalSum() + " рублей");

        cashMachine.getCash(27000);

        cashMachine.printCash();
        System.out.println("Сумма в банкомате: " + cashMachine.getTotalSum() + " рублей");

    }
    private void init(){
        cashHolderList.add(new BaseCashHolder(CashType.FIVE_THOUSAND, 200));
        cashHolderList.add(new BaseCashHolder(CashType.ONE_THOUSAND, 200));
        cashHolderList.add(new BaseCashHolder(CashType.FIVE_HUNDRED, 200));
        cashHolderList.add(new BaseCashHolder(CashType.ONE_HUNDRED, 200));
        cashHolderList.add(new BaseCashHolder(CashType.FIFTY, 200));

        FiveThousandCashHandler fiveThousandCashHandler = new FiveThousandCashHandler(CashType.FIVE_THOUSAND.getNominal());
        fiveThousandCashHandler.setNext(new OneThousandCashHolder(CashType.ONE_THOUSAND.getNominal()));
        cashHandler = fiveThousandCashHandler;
    }

    public void getCash(int sum){
        if(isPossible(sum)){
            Request request = new Request(RequestAction.GET, sum);
            cashHandler.handle(cashHolderList, request);
        }
    }
    private boolean isPossible(int sum){
        boolean result = false;
        if(sum < getTotalSum() && (sum % 50 == 0)){
            int tmpSum = sum;
            for (BaseCashHolder holder:cashHolderList) {
                int count = tmpSum / holder.getType().getNominal();
                if(count >= 1 && count <= holder.getCount()){
                    tmpSum -=(count * holder.getType().getNominal());
                }
                if(tmpSum == 0){
                    result = true;
                    break;
                }
            }
        } else {
            System.out.println("Запрашиваемой суммы нет в банкомате или сумма не кратна 50");
        }
        return result;
    }

    public int getTotalSum(){
        Stream<BaseCashHolder> cashHolderStream = cashHolderList.stream();
        return cashHolderStream.mapToInt(p->p.getCashHolderSum()).reduce((p1,p2)->p1 + p2).getAsInt();
    }

    public void printCash(){
        Stream<BaseCashHolder> cashHolderStream = cashHolderList.stream();
        cashHolderStream.forEach(p-> System.out.println("Купюр номиналом " + p.getType().getNominal() + " сейчас " + p.getCount()));
    }

}
