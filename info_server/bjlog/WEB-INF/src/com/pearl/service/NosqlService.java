package com.pearl.service;


import java.util.Date;
import java.util.List;

import com.pearl.nosql.NoSql;
import com.pearl.utils.Constants;
import com.pearl.utils.ServiceLocator;



public class NosqlService {
	protected NoSql nosql;
	public void xunleiLogWrite() throws Exception{
		ServiceLocator.exception.error("start log write....... "+ServiceLocator.simpleDateFormat.format(new Date()));
		List<String> xunleiLogs=nosql.getQueue(Constants.XUNLEI_LOG_WRITE);
		nosql.delete(Constants.XUNLEI_LOG_WRITE);
		for(String str:xunleiLogs){
			ServiceLocator.xunleiLog.info(str);
		}
		ServiceLocator.exception.error("ending log write.......had "+xunleiLogs.size()+" logs " 
					+ServiceLocator.simpleDateFormat.format(new Date()));
		
	}
	
	public NoSql getNosql() {
		return nosql;
	}

	public void setNosql(NoSql nosql) {
		this.nosql = nosql;
	}
}
