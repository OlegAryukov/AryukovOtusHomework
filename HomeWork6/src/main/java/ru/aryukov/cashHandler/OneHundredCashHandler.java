package ru.aryukov.cashHandler;

import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;

import java.util.List;

/**
 * Created by oaryukov on 29.06.2017.
 */
public class OneHundredCashHandler extends CashHandler {
    public OneHundredCashHandler(int cashNominal, int level) {
        super(cashNominal, level);
    }

    @Override
    public void handle(List<BaseCashHolder> cashHolderList, Request request) {
        if(request.getAction().getType().equals("GET")){
            if(request.getSum() >= 100){
                getCashFromCashHolder(cashHolderList, request);
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            }
        }else if(request.getAction().getType().equals("PUT")){
            if (request.getCashIn().containsKey(CashType.ONE_HUNDRED)){
                putCashToCashHolder(cashHolderList, request, CashType.ONE_HUNDRED);
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            } else {
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            }
        }
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

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void getCashFromCashHolder(List<BaseCashHolder> cashHolderList, Request request) {
        super.getCashFromCashHolder(cashHolderList, request);
    }
}
