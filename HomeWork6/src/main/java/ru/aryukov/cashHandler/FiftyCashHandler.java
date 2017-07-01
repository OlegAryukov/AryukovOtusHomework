package ru.aryukov.cashHandler;

import org.apache.log4j.Logger;
import ru.aryukov.cashType.BaseCashHolder;
import ru.aryukov.cashType.CashType;
import ru.aryukov.request.Request;

import java.util.List;

/**
 * Created by oaryukov on 29.06.2017.
 */
public class FiftyCashHandler extends CashHandler {

    private static final Logger LOG = Logger.getLogger(FiftyCashHandler.class);
    public FiftyCashHandler(int cashNominal, int level) {
        super(cashNominal, level);
    }

    @Override
    public void handle(List<BaseCashHolder> cashHolderList, Request request) {
        if(request.getAction().getType().equals("GET")){
            if(request.getSum() >= 50){
                getCashFromCashHolder(cashHolderList, request);
                LOG.info("обработан хэндлером " + this.getClass().getName());
                if(request.getSum() > 0){
                    if(getNext() != null){
                        getNext().handle(cashHolderList, request);
                    }
                }
            }
        }else if(request.getAction().getType().equals("PUT")){
            if (request.getCashIn().containsKey(CashType.FIFTY)){
                putCashToCashHolder(cashHolderList, request, CashType.FIFTY);
                LOG.info("обработан хэндлером " + this.getClass().getName());
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
