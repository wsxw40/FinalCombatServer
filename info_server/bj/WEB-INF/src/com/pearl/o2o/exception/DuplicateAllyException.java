package com.pearl.o2o.exception;



/**
 * 
 * @author Timon
 *
 */
public class DuplicateAllyException extends BaseException {	
	private static final long serialVersionUID = -4340128667349518506L;

	public DuplicateAllyException() {
		super("此战队已经是您的同盟了");
	}

	public DuplicateAllyException(String s) {
		super(s);
	}

	public DuplicateAllyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateAllyException(Throwable cause) {
		super(cause);
	}
}