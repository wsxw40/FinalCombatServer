package com.pearl.o2o.exception;



/**
 * 
 * @author WengJie
 *
 */
public class GmException extends Exception {
	private static final long serialVersionUID = -3990773360472662866L;
	public static final String data="Illegal GM operation: ";
	public GmException() {
		super();
	}

	public GmException(String s) {
		super(data+""+s);
	}

	public GmException(String message, Throwable cause) {
		super(message, cause);
	}

	public GmException(Throwable cause) {
		super(cause);
	}
}