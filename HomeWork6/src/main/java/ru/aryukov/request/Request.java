package ru.aryukov.request;

import ru.aryukov.cashType.CashType;

import java.util.HashMap;

/**
 * Created by oaryukov on 28.06.2017.
 */
public class Request {
    private RequestAction action;
    private int sum;
    private HashMap<CashType, Integer> cashIn;

    public Request(RequestAction action, int sum) {
        this.action = action;
        this.sum = sum;
    }

    public Request(RequestAction action, int sum, HashMap<CashType, Integer> cashIn) {
        this.action = action;
        this.sum = sum;
        this.cashIn = cashIn;
    }

    public RequestAction getAction() {
        return action;
    }

    public int getSum() {
        return sum;
    }
    public void setSum(int sum){
        this.sum = sum;
    }

    public HashMap<CashType, Integer> getCashIn() {
        return cashIn;
    }
}
