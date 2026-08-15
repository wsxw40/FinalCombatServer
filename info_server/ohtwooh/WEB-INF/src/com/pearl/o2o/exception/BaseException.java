package com.pearl.o2o.exception;



/**
 * 
 * @author WengJie
 *
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = -3990773360472662866L;
	public String data;
	public BaseException() {
		super();
	}

	public BaseException(String s) {
		super(s);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}