package ru.aryukov.messageSystem;

/**
 * Created by dev on 07.09.17.
 */
public abstract class Message {
    private Address from;
    private Address to;

    protected void setFrom(Address from) {
        this.from = from;
    }

    protected void setTo(Address to) {
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public abstract MessageResponse exec();
}
