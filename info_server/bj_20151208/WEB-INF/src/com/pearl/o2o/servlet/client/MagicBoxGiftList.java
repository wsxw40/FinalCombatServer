package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class MagicBoxGiftList extends BaseClientServlet {

	private static final long serialVersionUID = 6754145758742488159L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = {"page"};
	
	protected String innerService(String... args) {
		try {
			int isLastPage = 0;
			int page = StringUtil.toInt(args[0]);
			if (page < Constants.NUM_ONE) {
				log.error("MagicBoxGiftList/"+Constants.RPC_PARAM_ERROR_LOG + ":\tpage="+page);
				throw new BaseException(ExceptionMessage.PARAM_ERROR_MSG);
			}
			List<OnlineAward> magicGiftList = getService.getSortedOnlineAwardByType(Constants.ONLINE_AWARD_TYPES.PASSWARD_BOX.getValue(),new ComparatorUtil.OnlineAwardComparatorClass());
			int pages = CommonUtil.getListPages(magicGiftList, Constants.MAGIC_BOX_GIFT_PAGESIZE);
			if(page>pages)
				page=1;
			if(page==pages)
				isLastPage = 1;
			int fromIndex = (page - 1) * Constants.MAGIC_BOX_GIFT_PAGESIZE;
			int toIndex = (page) * Constants.MAGIC_BOX_GIFT_PAGESIZE;
			magicGiftList = magicGiftList.subList(fromIndex, toIndex > magicGiftList.size() ? magicGiftList.size() : toIndex);
			return Converter.magicBoxGiftList(page,isLastPage,magicGiftList);
		}catch (BaseException e) {
			log.warn("MagicBoxGiftList/Warn:\t", e);
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("MagicBoxGiftList/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
