/**
 * 
 */
package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class CreateTeam extends BaseClientServlet {

	private static final long serialVersionUID = -3352098501534070554L;
	static Logger log=LoggerFactory.getLogger(CreateTeam.class.getName());
	private String[] paramNames={"uid","pid","name","description","logo","province","city","rank","playerItemId"};
	@Override
	protected String innerService(String... args) {
		try{
			//int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			String name = args[2];
			String description = args[3];
			String logo = args[4];
			String province = args[5];
			String city = args[6];
			String rank = args[7];
			String playerItemIdStr = args[8];
			
			boolean isError=false;
			String nameStr="";
			String descriptionStr="";
			
			if (StringUtil.isEmptyString(name)) {
				isError=true;
				nameStr += ExceptionMessage.TEAM_NAME_EMPTY+",";
			}
			
			if(!KeywordFilterUtil.isLegalInput(name)||StringUtil.filter(name)){
				isError=true;
				nameStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
				
			}
			int count=getService.fuzzyCountTeam(name);
			if(count>0){
				isError=true;
				nameStr+=ExceptionMessage.TEAM_EXIST+",";
			}
			//FIXME
			if (name != null && name.length() > StringUtil.toInt(getService.getSysConfig().get("teamname.maxlength"))) {
				isError=true;
				nameStr+=ExceptionMessage.TOO_LONG+",";
			}
//			if (StringUtil.isEmptyString(description)) {
//				isError=true;
//				descriptionStr += ExceptionMessage.EMPTY_TEAM_DESCRIPTION+",";
//			}
			if(!KeywordFilterUtil.isLegalInput(description)){
				isError=true;
				descriptionStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
			}
			if (description != null && description.length() > 140) {
				isError=true;
				descriptionStr+=ExceptionMessage.TOO_LONG+",";
			}
			if(null == rank || "".equals(rank)){
				try{
					Integer.parseInt(rank);
				} catch(Exception e){
					return Converter.commonFeedback(ExceptionMessage.JOIN_TEAM_LEVEL_ERROR);
				}
				return Converter.commonFeedback(ExceptionMessage.JOIN_TEAM_LEVEL_EMPTY);
			}
			if(!isError){
				int playerItemId = -1;
				if(null != playerItemIdStr && !"".equals(playerItemIdStr) && !"0".equals(playerItemIdStr)){
					playerItemId = StringUtil.toInt(playerItemIdStr);
				}
				createService.createTeamByItem(playerId, name, description,logo,province,city,rank,playerItemId);
			}
			return Converter.creatTeam(CommonUtil.cutLastWord(nameStr), CommonUtil.cutLastWord(descriptionStr));
		}
		catch (BaseException e) {
			log.debug(e.getMessage());
			return Converter.commonFeedback(e.getMessage());
		}
		catch (Exception e) {
			log.warn(e.getMessage(),e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
