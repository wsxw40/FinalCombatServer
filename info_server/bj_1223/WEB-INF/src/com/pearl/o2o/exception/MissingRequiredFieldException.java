package com.pearl.o2o.exception;



/**
 * 
 * @author WengJie
 *
 */
public class MissingRequiredFieldException extends BaseException {	
	private static final long serialVersionUID = -6314040534788042725L;

	public MissingRequiredFieldException() {
		super();
	}

	public MissingRequiredFieldException(String s) {
		super(s);
	}

	public MissingRequiredFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingRequiredFieldException(Throwable cause) {
		super(cause);
	}
}