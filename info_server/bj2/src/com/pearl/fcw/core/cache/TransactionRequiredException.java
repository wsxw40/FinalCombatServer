package com.pearl.fcw.core.cache;

public class TransactionRequiredException extends RuntimeException {

    private static final long serialVersionUID = 6499106637562882420L;

    public TransactionRequiredException() {
        super();
    }

    public TransactionRequiredException(String message) {
        super(message);
    }

}
