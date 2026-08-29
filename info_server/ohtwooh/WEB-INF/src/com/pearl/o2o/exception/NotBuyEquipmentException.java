package com.pearl.o2o.exception;

public class NotBuyEquipmentException extends BaseException {

	private static final long serialVersionUID = 2580861960661826208L;

	public NotBuyEquipmentException() {
		super();
	}

	public NotBuyEquipmentException(String s) {
		super(s);
	}

	public NotBuyEquipmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotBuyEquipmentException(Throwable cause) {
		super(cause);
	}
}
