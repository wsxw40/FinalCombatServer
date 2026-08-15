package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/*
 * get puzzle award list
 */
public class GetPinTuAwards extends BaseClientServlet {
	private static final long serialVersionUID = 2020399083343188532L;
	private static Logger log = LoggerFactory.getLogger(GetPinTuAwards.class.getName());
	private static final String[] paramNames = {"page","type"};
	private static final int PAGE_SIZE = 7;
	@Override
	protected String innerService(String... args) {
		try {
			int page = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			List<OnlineAward> pinTuAwards = getService.getSortedOnlineAwardByType(Constants.ONLINE_AWARD_TYPES.MISTIC_PINTU.getValue(),new Comparator<OnlineAward>(){
				@Override
				public int compare(OnlineAward o1, OnlineAward o2) {
					return o1.getSysItem().getCharacterId().compareTo(o2.getSysItem().getCharacterId());
				}},type);
			int totalSize = pinTuAwards.size();
			int pageCount = totalSize%PAGE_SIZE == 0 ?totalSize/PAGE_SIZE:(totalSize/PAGE_SIZE)+1;
			if(page<0||page>=pageCount){
				page = 0;
			}
			int fromIndex = page*PAGE_SIZE;
			int toIndex = page == pageCount-1?totalSize:(page+1)*PAGE_SIZE;
			List<OnlineAward> retList = new ArrayList<OnlineAward>();
			for(OnlineAward oa : pinTuAwards.subList(fromIndex, toIndex)){
				oa.setSysItem(getService.getSysItemByItemId(oa.getItemId()));
				retList.add(oa);
			}
			return Converter.pinTuAwards(retList,pageCount,page);
		} catch (Exception e) {
			log.warn("Error in GetPinTuAwards: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
	
}
