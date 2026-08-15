package com.pearl.o2o.exception;

import com.pearl.o2o.utils.ExceptionMessage;

public class ReceiverNotExistException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ReceiverNotExistException(){
		super(ExceptionMessage.RECEUVER_NOT_EXIST);
	}
}
