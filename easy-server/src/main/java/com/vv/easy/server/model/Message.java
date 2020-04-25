package com.vv.easy.server.model;

public class Message<T> {
    private T content;

    public Message(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return content != null ? content.equals(message.content) : message.content == null;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
