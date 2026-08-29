
package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.DuplicateAllyException;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * @author Timon
 */
public class TeamAllyOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamAllyOp.class.getName());
	private static final String[] paramNames = {"tid","allyid","action"};
	
	protected String innerService(String... args) {
		try{
			int teamId = Integer.valueOf(args[0]);
			int allyId = Integer.valueOf(args[1]);
			String action =args[2];
			
			if (teamId == allyId) {
				return Converter.error(ExceptionMessage.TEAM_CANNOT_ADD_SELFALLY);
			}
			
			if ("add".equals(action)) {
				updateService.addAlly(teamId, allyId);
			}else if ("delete".equals(action)) {
				updateService.removeAlly(teamId, allyId);
			}else{
				throw new Exception("unknown action " + action);
			}
			return "error=nil";
		}
		catch(DuplicateAllyException de){
			return Converter.error(ExceptionMessage.TEAM_HAD_ADDED);
		}
		catch (Exception e) {
			logger.warn("team update op fail:" + e.getMessage(),e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}