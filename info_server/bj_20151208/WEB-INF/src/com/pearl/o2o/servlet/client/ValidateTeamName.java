package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

public class ValidateTeamName extends BaseClientServlet {
	private static final long serialVersionUID = 5138527124601272755L;
	private static Logger logger=LoggerFactory.getLogger(ValidateTeamName.class.getName());
	private static final String[] paramNames = {"name","description"};
	
	protected String innerService(String... args) {
		try{
			//String name = StringUtil.encoding(req.getParameter("name"));
			String name = args[0];
			String description = args[1];
			
			boolean isError = false;
			String nameStr="";
			String descriptionStr="";
			if (StringUtil.isEmptyString(name)) {
				isError=true;
				nameStr += ExceptionMessage.TEAM_NAME_EMPTY +"\\n";
			}
			if(!KeywordFilterUtil.isLegalInput(name)||StringUtil.filter(name)){
				isError=true;
				nameStr+=ExceptionMessage.TEAM_NAME_INVALID+"\\n";
			}
			int count=getService.fuzzyCountTeam(name);
			if(count>0){
				isError=true;
				nameStr+=ExceptionMessage.TEAM_EXIST+"\\n";
			}
			//FIXME
			if (name != null && name.length() > StringUtil.toInt(getService.getSysConfig().get("teamname.maxlength"))) {
				isError=true;
				nameStr+=ExceptionMessage.TOO_LONG+"\\n";
			}
			
			if (StringUtil.isEmptyString(description)) {
				isError=true;
				descriptionStr += ExceptionMessage.EMPTY_STR+"\\n";
			}
			if(!KeywordFilterUtil.isLegalInput(description)){
				isError=true;
				descriptionStr+=ExceptionMessage.TEAM_DESCRIPTION_INVALID+"\\n";
			}
			//FIXME
			if (description != null && description.length() > 280) {
				isError=true;
				descriptionStr+=ExceptionMessage.TOO_LONG+"\\n";
			}
			if(isError){
				if(!"".equals(nameStr)){
					return Converter.error(nameStr.substring(0, nameStr.length()-2));
				}
				if(!"".equals(descriptionStr)){
					return Converter.error(descriptionStr.substring(0, descriptionStr.length()-2));
				}
				return Converter.warn2(null,null);
			}
			else{
				return Converter.warn2(null,null);
			}
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
