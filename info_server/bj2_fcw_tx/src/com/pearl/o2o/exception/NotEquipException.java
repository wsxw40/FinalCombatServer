package com.pearl.o2o.exception;

public class NotEquipException extends BaseException {

	private static final long serialVersionUID = -1239146676425824149L;

	public NotEquipException() {
		super("您不能装备此物品");
	}

	public NotEquipException(String s) {
		super(s);
	}

	public NotEquipException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEquipException(Throwable cause) {
		super(cause);
	}

}
