package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.TeamTechnology;

/**
 * 处理资源争夺战匹配（取消，开打，战斗结束）
 * 
 * @author leo.zhang
 */
public class PublicTest extends BaseClientServlet {
	private static final long serialVersionUID = -3928363495923777848L;

	static Logger logger = LoggerFactory.getLogger(PublicTest.class
			.getName());

	/**
	 * lpid:挑战队伍队长ID tid:被挑战队伍的ID
	 */
	private String[] paramNames = { "a"};

	@Override
	protected String innerService(String... args) {

		try {
			TeamTechnology a=getService.getTeamTechnologyByCurNode(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
