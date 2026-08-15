package com.pearl.o2o.exception;

public class CRNotEnoughException extends RuntimeException {

	private static final long serialVersionUID = 2580861960661826208L;

	public CRNotEnoughException() {
		super();
	}

	public CRNotEnoughException(String s) {
		super(s);
	}

	public CRNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public CRNotEnoughException(Throwable cause) {
		super(cause);
	}
}
