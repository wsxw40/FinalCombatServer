package com.pearl.o2o.exception;



/**
 * 
 * @author WengJie
 *
 */
public class EmptyInputException extends BaseException {	
	private static final long serialVersionUID = -960535220178671161L;

	public EmptyInputException() {
		super("请填写名称");
	}

	public EmptyInputException(String s) {
		super(s);
	}

	public EmptyInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyInputException(Throwable cause) {
		super(cause);
	}
}