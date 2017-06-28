package ru.aryukov.request;

/**
 * Created by oaryukov on 28.06.2017.
 */
public enum RequestAction {
    GET("GET"),
    PUT("PUT");

    private String action;

    RequestAction(String action) {
        this.action = action;
    }

    public String getType(){
        return this.action;
    }
}
