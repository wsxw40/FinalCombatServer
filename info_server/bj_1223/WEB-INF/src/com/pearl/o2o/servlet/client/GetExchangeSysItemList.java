package com.pearl.o2o.servlet.client;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.sort.SysItemComparator;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetExchangeSysItemList extends BaseClientServlet {

	private static final long serialVersionUID = -8847865216405794375L;
	private static Logger log = LoggerFactory.getLogger(GetExchangeSysItemList.class.getName());
	private static final String[] paramNames = { "pid", "page" };
	
	protected String innerService(String... args) {
		int playerId = StringUtil.toInt(args[0]);
		int page = StringUtil.toInt(args[1]);
		try{
			Player player = getService.getSimplePlayerById(playerId);
			List<SysItem> list=getService.getExchangeSysItemList();
			Collections.sort(list,new SysItemComparator());
			int chipNum=getService.getMedolNumByPlayerId(playerId);
			int pageSize=Constants.DEFAULT_A_PAGE_SIZE;
			int pages = CommonUtil.getListPages(list, pageSize);
			if(pages!=0 && page>pages){
				log.warn("Error in page bigger than pages");
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			int endIndex = page * pageSize;
			int startIndex = (page - 1) * pageSize;
			endIndex=endIndex > list.size()?list.size():endIndex;
			list = list.subList(startIndex, endIndex);
			if(pages==0){
				pages=1;
			}
			return Converter.exchangeSysItemList(page, pages, list, player.getRank(),chipNum);
		}catch(Exception e){
			log.warn("Error in GetExchangeSysItem: ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
