package com.pearl.fcw.info.core.network;

import java.util.function.Supplier;

public class MessageException extends RuntimeException implements Supplier<MessageException> {

    private static final long serialVersionUID = -7939208019646921049L;

    public MessageException(String message) {
        super(message);
    }

    @Override
    public MessageException get() {
        return this;
    }
}