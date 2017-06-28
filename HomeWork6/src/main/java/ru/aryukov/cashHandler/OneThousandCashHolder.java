package ru.aryukov.cashHandler;

import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;

import java.util.List;

/**
 * Created by oaryukov on 28.06.2017.
 */
public class OneThousandCashHolder extends CashHandler {
    public OneThousandCashHolder(int cashNominal) {
        super(cashNominal);
    }

    @Override
    public void handle(List<BaseCashHolder> cashHolderList, Request request) {
        if(request.getAction().getType().equals("GET")){
            if(request.getSum() >= 1000){
                getCashFromHolder(cashHolderList, request);
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            }
        }else if(request.getAction().getType().equals("PUT")){
            if(cashHolderList.get(1).getCount() + request.getCashIn().get(CashType.ONE_THOUSAND) <= cashHolderList.get(1).getMaxHolderSize()){
                cashHolderList.get(1).putCash(request.getCashIn().get(CashType.ONE_THOUSAND));
                int tmpSum = request.getSum();
                request.setSum(tmpSum - getCashNominal()*request.getCashIn().get(CashType.ONE_THOUSAND));
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            } else {
                System.out.println("Сейчас банкомат не может принять купюры номиналом " + CashType.ONE_THOUSAND.getNominal());
                throw new RuntimeException();
            }
        }
    }

    private void getCashFromHolder(List<BaseCashHolder> cashHolderList, Request request) {
        int count = request.getSum() / getCashNominal();
        int countInHolder = cashHolderList.get(1).getCount();
        int tmpSum = request.getSum();
        request.setSum(tmpSum - count * getCashNominal());
        cashHolderList.get(1).setCount(countInHolder - count);
    }

    @Override
    public CashHandler getNext() {
        return super.getNext();
    }

    @Override
    public void setNext(CashHandler next) {
        super.setNext(next);
    }

    @Override
    public int getCashNominal() {
        return super.getCashNominal();
    }
}
