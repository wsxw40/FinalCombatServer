package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author Timon
 */
public class GetTeamRequestList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger = LoggerFactory.getLogger(GetTeamRequestList.class.getName());
	private static final String[] paramNames = { "tid", "page" };
	private static final int PageSize = 10;

	protected String innerService(String... args) {
		try {
			int teamId = StringUtil.toInt(args[0]);
			int page = StringUtil.toInt(args[1]);

			List<PlayerTeam> list = getService.getUnapprovedMemberOverMaxNumber(teamId);

			// 分页
			int pages =list.size() % PageSize == 0 ? list.size() / PageSize: (list.size() / PageSize)+1;
			
			if(pages>0){
				if (page<0||page>pages) {
					page = 1;
				}
				
				int endIndex = page * PageSize;
				int startIndex = (page - 1) * PageSize;
				list = list.subList(startIndex, endIndex > list.size() ? list.size() : endIndex);
				
			}else{
				pages = 1;
			}
			return Converter.teamRequestList(list, page, pages);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
