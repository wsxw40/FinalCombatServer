package com.pearl.o2o.exception;

public class NotFindPlayerException extends BaseException {


	private static final long serialVersionUID = 6041243746314527178L;
	public NotFindPlayerException() {
		super("您查找的玩家不存在");
	}

	public NotFindPlayerException(String s) {
		super(s);
	}

	public NotFindPlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFindPlayerException(Throwable cause) {
		super(cause);
	}
}
