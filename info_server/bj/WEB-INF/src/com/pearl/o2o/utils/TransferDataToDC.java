package com.pearl.o2o.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.client.InforLogger;
import com.pde.log.common.LogMessage.Level;
import com.pde.log.common.LogMessage.LogMsg;

public class TransferDataToDC {
	static Logger logger = LoggerFactory.getLogger(TransferDataToDC.class.getName());
	
	private InforLogger dcLogger;
	
	
	public InforLogger getDcLogger() {
		return dcLogger;
	}


	public void setDcLogger(InforLogger dcLogger) {
		this.dcLogger = dcLogger;
	}


	public void addLog(String logName, String logContent) {
		if(Constants.SWITCH_ANALYSER_IS_ON==1){
			LogMsg logMsg = LogMsg.newBuilder().setTime(System.currentTimeMillis())
			.setLevel(Level.INFO_INT).setLogger(logName)
			.setThread(Thread.currentThread().getName()).setMsg(logContent)
			.build();
	
			logger.info("Begin to transfer data to analyser system!");
			dcLogger.log(logMsg);
			
		}
		
	}
}
