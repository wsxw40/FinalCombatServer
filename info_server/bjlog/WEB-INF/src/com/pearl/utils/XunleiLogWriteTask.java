package com.pearl.utils;

import org.apache.log4j.Logger;

public class XunleiLogWriteTask implements Runnable {
	public static Logger logger = Logger.getLogger(XunleiLogWriteTask.class);
	@Override
	public void run() {
		try {
			if(ServiceLocator.bjlogSwitch){
				ServiceLocator.nosqlService.xunleiLogWrite();
			}
		} catch (Exception e) {
			ServiceLocator.exception.error(e);
		}

	}

}
