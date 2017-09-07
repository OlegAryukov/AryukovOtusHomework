package ru.aryukov.messageSystem;

/**
 * Created by dev on 07.09.17.
 */
public class MessageResponse<T> {
    private T value;

    public MessageResponse(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "value=" + value +
                '}';
    }
}
