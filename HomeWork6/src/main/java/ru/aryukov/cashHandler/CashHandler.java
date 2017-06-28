package ru.aryukov.cashHandler;

import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.request.Request;

import java.util.List;

/**
 * Created by oaryukov on 28.06.2017.
 */
public abstract class CashHandler {
    private CashHandler next;
    private int cashNominal;

    public CashHandler(int cashNominal) {
        this.cashNominal = cashNominal;
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
}
