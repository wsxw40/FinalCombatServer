package com.pearl.fcw.info.lobby.exception;

import com.pearl.fcw.info.lobby.utils.ExceptionMessage;



/**
 * 
 * @author WengJie
 *
 */
public class DataTooLongException extends BaseException {	
	private static final long serialVersionUID = -4340128667349518506L;
	public String data;
	public DataTooLongException() {
		super(ExceptionMessage.TOO_LONG);
	}

	public DataTooLongException(String s) {
		super(s);
	}

	public DataTooLongException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataTooLongException(Throwable cause) {
		super(cause);
	}
}