package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class FuzzyGetTeam extends BaseClientServlet {
	private static final long serialVersionUID = -6936779568193904765L;
	static Logger log= LoggerFactory.getLogger(FuzzyGetTeam.class.getName());
	private String[] paramNames={"key","p","default","province","city"};
	@Override
	protected String innerService(String... args) {
		try{
			//TODO pagnator
			String name = args[0];
			int page = StringUtil.toInt(args[1]);
			int defaultSearch = -1;
			if(!StringUtil.isEmptyString(args[2])){
				defaultSearch = Integer.valueOf(args[2]);//system will recommend some team at first time browse the team search page
			}
			
			List<Team> teamList = new ArrayList<Team>();
			if (defaultSearch == 1) {
				if(page <= 0){
					page = 1;
				}
				teamList = getService.getDefaultRecommendTeam();
				int pages = CommonUtil.getListPages(teamList, Constants.TEAM_PAGE_SIZE);
				if(pages == 0){
					pages = 1;
				}
				if(page > pages){
					page = pages;
				} else if(page < 1){
					page = 1;
				}
				int fromIndex = (page -1) * Constants.TEAM_PAGE_SIZE;
				int toIndex = Math.min(teamList.size(), page * Constants.TEAM_PAGE_SIZE);
				List<Team> resultList = teamList.subList(fromIndex, toIndex);
				return Converter.teamList(resultList, page, pages);
			}else{
				String province = args[3];
				String city = args[4];
				if(!StringUtil.isEmptyString(args[0])){
					name = StringUtil.escapeIndex(name);
				}
				if(StringUtil.isEmptyString(name)&&StringUtil.isEmptyString(province)&&StringUtil.isEmptyString(city)){
					throw new BaseException(ExceptionMessage.EMPTY_STR);
				}
				teamList=getService.getTeamsByNameProvinceCity(name, province, city);
				                                                 
				List<Team> list = new ArrayList<Team>();
				int pages = CommonUtil.getListPages(teamList,Constants.TEAM_PAGE_SIZE);
				int endIndex = page * Constants.TEAM_PAGE_SIZE;
				int startIndex = (page - 1) * Constants.TEAM_PAGE_SIZE;
				list=teamList.subList(startIndex, endIndex > teamList.size()?teamList.size():endIndex);
				
				if(list!=null&&list.size()!=0){
					for(Team team:list){
						getService.setTeamMember(team);
					}
				}
				
				if(pages == 0){
					pages = 1;
				}
				return Converter.teamList(list, page, pages);
			}
		}
		catch(BaseException e){
			log.debug("Error in FuzzyGetTeam: "+e.getMessage());
			return Converter.error(e.getMessage());
		}
		catch(Exception e){
			log.warn("Error in FuzzyGetTeam: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
