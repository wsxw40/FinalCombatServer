package com.pearl.o2o.exception;

import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;



/**
 * 
 * @author WengJie
 *
 */
public class DuplicatePlayerException extends BaseException {	
	private static final long serialVersionUID = -4340128667349518506L;

	public DuplicatePlayerException() {
		super(ExceptionMessage.NAME_EXIST);
	}

	public DuplicatePlayerException(String s) {
		super(s);
	}

	public DuplicatePlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatePlayerException(Throwable cause) {
		super(cause);
	}
}