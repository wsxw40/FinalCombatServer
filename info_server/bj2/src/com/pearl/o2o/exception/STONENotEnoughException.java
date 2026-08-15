package com.pearl.o2o.exception;

public class STONENotEnoughException extends NotBuyEquipmentException {
	private static final long serialVersionUID = 1237774647284498436L;

	public STONENotEnoughException() {
		super();
	}

	public STONENotEnoughException(String s) {
		super(s);
	}

	public STONENotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public STONENotEnoughException(Throwable cause) {
		super(cause);
	}
}
