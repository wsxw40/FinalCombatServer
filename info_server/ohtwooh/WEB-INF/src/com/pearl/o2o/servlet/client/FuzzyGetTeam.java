package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class FuzzyGetTeam extends BaseClientServlet {

	
	private static final long serialVersionUID = -6936779568193904765L;
	static Logger log= Logger.getLogger(FuzzyGetTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			
			String name = req.getParameter("key");
			int page = StringUtil.toInt(req.getParameter("p"));
			if("".equals(name)){
				throw new BaseException(ExceptionMessage.EMPTY_STR);
			}
			List<Team> teamList=getService.getTeamsByName(name);
			List<Team> list = new ArrayList<Team>();
			int pages = CommonUtil.getListPages(teamList,
					Constants.TEAM_PAGE_SIZE);
			int endIndex = page * Constants.TEAM_PAGE_SIZE;
			int startIndex = (page - 1) * Constants.TEAM_PAGE_SIZE;
			list=teamList.subList(startIndex, endIndex>teamList.size()?teamList.size():endIndex);
			if(list!=null&&list.size()!=0){
				for(Team t:list){
					t.calculateParam();
				}
			}
			String result = Converter.teamList(list, page, pages);	
			out.write(result);
		}
		catch(BaseException e){
			log.error("Error in GetPlayerList: ", e);
			out.write(Converter.error(e.getMessage()));
		}
		catch(Exception e){
			log.error("Error in GetPlayerList: ", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}

}
