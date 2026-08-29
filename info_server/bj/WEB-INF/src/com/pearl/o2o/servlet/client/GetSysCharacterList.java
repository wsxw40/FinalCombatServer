package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;


public class GetSysCharacterList extends BaseClientServlet {
	private static final long serialVersionUID = -4108330286376393442L;
	private static Logger LOG = LoggerFactory.getLogger(GetSysCharacterList.class.getName());	
	private static final String[] paramNames = {"pid"};
	@Override
	protected String innerService(String... strings) {
		try {
			List<SysCharacter> sysCharaterList = getService.getDefaultSysCharacterList();
			String result = Converter.sysCharacterList(sysCharaterList);			
			return result;
		} catch (Exception e) {
			LOG.warn("Error in GetSysCharacterList: ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
