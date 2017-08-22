package ru.aryukov;

import ru.aryukov.cashHandler.*;
import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;
import ru.aryukov.request.RequestAction;

import java.util.ArrayList;
import java.util.HashMap;
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

        cashMachine.getCash(16650);
        System.out.println("После снятия");
        cashMachine.printCash();
        System.out.println("Сумма в банкомате: " + cashMachine.getTotalSum() + " рублей");

        cashMachine.putCash(370050);
        System.out.println("После внесения");
        cashMachine.printCash();
        System.out.println("Сумма в банкомате: " + cashMachine.getTotalSum() + " рублей");

    }

    private void init(){
        cashHolderList.add(new BaseCashHolder(CashType.FIVE_THOUSAND, 200));
        cashHolderList.add(new BaseCashHolder(CashType.ONE_THOUSAND, 200));
        cashHolderList.add(new BaseCashHolder(CashType.FIVE_HUNDRED, 200));
        cashHolderList.add(new BaseCashHolder(CashType.ONE_HUNDRED, 200));
        cashHolderList.add(new BaseCashHolder(CashType.FIFTY, 200));

        FiveThousandCashHandler fiveThousandCashHandler = new FiveThousandCashHandler(CashType.FIVE_THOUSAND.getNominal(),
                CashType.FIVE_THOUSAND.getLevel());
        OneThousandCashHandler oneThousandCashHandler = new OneThousandCashHandler(CashType.ONE_THOUSAND.getNominal(),
                CashType.ONE_THOUSAND.getLevel());
        FiveHundredCashHandler fiveHundredCashHandler = new FiveHundredCashHandler(CashType.FIVE_HUNDRED.getNominal(),
                CashType.FIVE_HUNDRED.getLevel());
        OneHundredCashHandler oneHundredCashHandler = new OneHundredCashHandler(CashType.ONE_HUNDRED.getNominal(),
                CashType.ONE_HUNDRED.getLevel());
        FiftyCashHandler fiftyCashHandler = new FiftyCashHandler(CashType.FIFTY.getNominal(),
                CashType.FIFTY.getLevel());

        fiveThousandCashHandler.setNext(oneThousandCashHandler);
        oneThousandCashHandler.setNext(fiveHundredCashHandler);
        fiveHundredCashHandler.setNext(oneHundredCashHandler);
        oneHundredCashHandler.setNext(fiftyCashHandler);

        cashHandler = fiveThousandCashHandler;
    }

    /**
     * Получение наличных
     * @param sum сумма
     */
    public void getCash(int sum){
        if(isPossibleGet(sum)){
            Request request = new Request(RequestAction.GET, sum);
            cashHandler.handle(cashHolderList, request);
        }
    }

    /**
     * Внесение наличных
     * @param sum сумма
     */
    public void putCash(int sum){
        if(sum % 50 == 0){
            Request request = new Request(RequestAction.PUT, sum, prepearForPut(sum));
            cashHandler.handle(cashHolderList, request);
        }else {
            System.out.println("Вносимая сумма не кратна 50");
        }
    }

    private boolean isPossibleGet(int sum){
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

    private HashMap<CashType, Integer> prepearForPut(int sum){
        HashMap<CashType, Integer> result = new HashMap<>();
        int tmpSum = sum;
        for (BaseCashHolder holder:cashHolderList) {
            int count = tmpSum / holder.getType().getNominal();
            if(count > 0 && count <= (holder.getMaxHolderSize() - holder.getCount())){
                tmpSum -=(count * holder.getType().getNominal());
                result.put(holder.getType(),count);
            }else {
                System.out.println("Сейчас банкомат не может принять купюру номиналом " + holder.getType().getNominal());
            }
            if(tmpSum == 0){
                break;
            }
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
