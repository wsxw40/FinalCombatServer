package com.pearl.o2o.exception;



/**
 * 
 * @author WengJie
 *
 */
public class ServerNotFoundException extends BaseException {	
	private static final long serialVersionUID = 2608285110210596409L;

	public ServerNotFoundException() {
		super("Server not found in database");
	}

	public ServerNotFoundException(String s) {
		super(s);
	}

	public ServerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerNotFoundException(Throwable cause) {
		super(cause);
	}
}