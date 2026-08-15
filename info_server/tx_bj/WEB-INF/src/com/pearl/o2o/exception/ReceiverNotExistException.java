package com.pearl.o2o.exception;

import com.pearl.o2o.utils.ExceptionMessage;

public class ReceiverNotExistException extends BaseException {
	
	public ReceiverNotExistException(){
		super(ExceptionMessage.RECEUVER_NOT_EXIST);
	}
}
