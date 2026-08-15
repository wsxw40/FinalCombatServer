package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;


public class CreateGroup extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067328215L;
	private static Logger log = LoggerFactory.getLogger(CreateGroup.class.getName());
	private static final String[] paramNames = {"uid","pid","name"};
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			String name = args[2];
			
			boolean isError=false;
			String nameStr="";
			
			if (StringUtil.isEmptyString(name)) {
				isError=true;
				nameStr += ExceptionMessage.EMPTY_STR+",";
			}
			
			if(!KeywordFilterUtil.isLegalInput(name)||StringUtil.filter(name)){
				isError=true;
				nameStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
				
			}
			int count=getService.fuzzyCountGroup(name);
			if(count>0){
				isError=true;
				nameStr+=ExceptionMessage.GROUP_EXIST+",";
			}
			//FIXME
			if (name != null && name.length() > 6) {
				isError=true;
				nameStr+=ExceptionMessage.TOO_LONG+",";
			}
			
			if(!isError){
				updateService.updateGroup(playerId, playerId, Constants.GROUP, name);
			}
			

			return Converter.error(CommonUtil.cutLastWord(nameStr));
		}
		catch (BaseException e) {
			log.debug("Error in CreateGroup: "+e.getMessage());
			return Converter.warn(e.getMessage());
		}
		catch(Exception e){
			log.warn("Error in CreateGroup: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
