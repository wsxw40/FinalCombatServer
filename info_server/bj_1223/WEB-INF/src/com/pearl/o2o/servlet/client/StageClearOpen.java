package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class StageClearOpen extends BaseClientServlet{
	private static final long serialVersionUID = -994878388308443785L;
	private static Logger logger = LoggerFactory.getLogger("stage_clear");
	private static final String[] paramNames = {"pid","index"};

	@Override
	protected String innerService(String... args) {
		try{
			int playerId=Integer.valueOf(args[0]);
			int index=Integer.valueOf(args[1]);
			if(index<1||index>Constants.STAGE_BRAND_OPEN_NUM){
				logger.warn("StageClearOpen/" + Constants.RPC_PARAM_ERROR_LOG + ":\tindex="+index);
				return Converter.error(ExceptionMessage.PARAM_ERROR_MSG);
			}
			Integer totalOpenChance = 0;
			List<Integer> randomBrandIdList = new ArrayList<Integer>();
			List<Integer>  bidList = new ArrayList<Integer>();
			String brandOpenkey = Constants.STAGE_BRAND_OPEN_PREFIX + playerId;//玩家翻牌index 
			String brandOpenChancekey = Constants.STAGE_OPEN_CHANCE_PREFIX + playerId;//玩家获得翻牌次数
			String brdListKey = Constants.STAGE_BRAND_LIST_PREFIX + playerId;//玩家获得的牌子
			randomBrandIdList = mcc.get(brdListKey);
			if(randomBrandIdList==null||randomBrandIdList.size()<9){
				logger.warn("StageClearOpen/GetBrdIdsFail:\t" + playerId + "\t" + randomBrandIdList);
			}
			totalOpenChance = mcc.get(brandOpenChancekey);
			if(totalOpenChance==null){
				logger.warn("StageClearOpen/GetChanceFail:\t" + playerId);
			}
			bidList = mcc.get(brandOpenkey);
			if(bidList==null){
				bidList = new ArrayList<Integer>();
			}		
			if(bidList.size()<totalOpenChance){ 
				bidList.add(index-1);
				mcc.set(brandOpenkey, Constants.BRAND_CACHE_ITEM_TIMEOUT, bidList,Constants.CACHE_TIMEOUT);
				OnlineAward brand = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),randomBrandIdList.get(index-1));
				if(brand ==null){
					brand = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),Constants.STAGE_DEFAULT_AWARD_BRAND_ID);
					logger.warn("StageClearOpen/BrandNull:" +"\t" +randomBrandIdList.get(index-1)+"\t" + playerId);
				}
				logger.debug("StageClearOpen/BrandIndex:" +"\t"+playerId + "\t" + index +"\t" + brand.getId());
				SysItem si = getService.getSysItemByItemId(brand.getItemId());
				if(si==null){
					si = getService.getSysItemByItemId(Constants.STAGE_DEFAULT_AWARD_SYSITEM_ID);
					logger.warn("StageClearOpen/SysItemNull:"  +brand.getItemId());
				}
				brand.setSysItem(si);
				String result = Converter.brandOpen(index,brand);
				return result;
			}
			return Converter.brandOpen(Constants.NO_OPEN_BRAND_CHANCE_FLAG,null);
		}catch (BaseException e){
			logger.warn("StageClearOpen/Warn:\t" , e );
			return Converter.warn(e.getMessage());
		}catch (Exception e){
			logger.error("StageClearOpen/Error:\t" , e );
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}
