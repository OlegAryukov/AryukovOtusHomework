package ru.aryukov.cashHandler;

import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;

import java.util.List;

/**
 * Created by oaryukov on 28.06.2017.
 */
public abstract class CashHandler {
    private CashHandler next;
    private int cashNominal;
    private int level;

    public CashHandler(int cashNominal, int level) {
        this.cashNominal = cashNominal;
        this.level = level;
    }

    public void handle(List<BaseCashHolder> cashHolderList, Request request){
    }


    public CashHandler getNext() {
        return next;
    }

    public void setNext(CashHandler next) {
        this.next = next;
    }

    public int getCashNominal() {
        return cashNominal;
    }

    public int getLevel() {
        return level;
    }

    public void getCashFromCashHolder(List<BaseCashHolder> cashHolderList, Request request) {
        int count = request.getSum() / getCashNominal();
        int countInHolder = cashHolderList.get(getLevel()).getCount();
        int tmpSum = request.getSum();
        request.setSum(tmpSum - count * getCashNominal());
        cashHolderList.get(getLevel()).setCount(countInHolder - count);
    }

    public void putCashToCashHolder(List<BaseCashHolder> cashHolderList, Request request, CashType cashType){
        int count = request.getCashIn().get(cashType);
        int countInHolder = cashHolderList.get(getLevel()).getCount();
        int tmpSum = request.getSum();
        request.setSum(tmpSum - count * getCashNominal());
        cashHolderList.get(getLevel()).setCount(countInHolder + count);
    }
}
