package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.StringUtil;


public class StageClearOpenTimeOut extends BaseClientServlet{
	private static final long serialVersionUID = 5178567466572297017L;
	private static Logger logger = LoggerFactory.getLogger("stage_clear");
	private static final String[] paramNames = {"pid"};

	@Override
	protected String innerService(String... args) {
		
		try{
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			Integer totalOpenChance = 0;
			List<Integer> randomBrandIdList = new ArrayList<Integer>();
			List<Integer> bidList = new ArrayList<Integer>();
			String brdListKey = Constants.STAGE_BRAND_LIST_PREFIX + playerId;//玩家获得的牌子
			String brandOpenChancekey = Constants.STAGE_OPEN_CHANCE_PREFIX + playerId;//玩家获得翻牌次数
			randomBrandIdList = mcc.get(brdListKey);
			if(randomBrandIdList==null||randomBrandIdList.size()<9){
				logger.warn("StageClearOpenTimeOut/GetBrdIdsFail:\t" + playerId + "\t" + randomBrandIdList);
			}
			mcc.delete(brdListKey);
			totalOpenChance = mcc.get(brandOpenChancekey);
			if(totalOpenChance==null){
				logger.warn("StageClearOpenTimeOut/GetChanceFail:\t" + playerId);
			}
			mcc.delete(brandOpenChancekey);
			String brandOpenkey = Constants.STAGE_BRAND_OPEN_PREFIX + playerId;
			bidList = mcc.get(brandOpenkey);
			if(bidList==null){
				logger.warn("StageClearOpenTimeOut/GetOpenListFail:\t" + playerId);
			}
			mcc.delete(brandOpenkey);		
			Date today = new Date();
			int length = bidList.size();
			int randomNum = totalOpenChance - length;
			List<Integer>  tmpList = new ArrayList<Integer>();
			List<Integer>  randomList = new ArrayList<Integer>();
			if(randomNum>0){
				for(int k=0;k<Constants.STAGE_BRAND_OPEN_NUM;k++){
					tmpList.add(k);
				}
				for(Integer bid :bidList){
					tmpList.remove(bid);
				}
				randomList = getService.randomDiffObjsFromList(randomNum,tmpList);
			}
			if(bidList!=null&&bidList.size()>0){
			for(int bidx : bidList){
				OnlineAward brd = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),randomBrandIdList.get(bidx));
				if(brd ==null){
					brd = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),Constants.STAGE_DEFAULT_AWARD_BRAND_ID);
					logger.warn("StageClearOpenTimeOut/BrandNull:\t"  +randomBrandIdList.get(bidx)+"\t" + playerId);
				}
				Payment payment = new Payment();
				payment.setUnit(brd.getUnit());
				payment.setUnitType(brd.getUnitType());
				SysItem si = getService.getSysItemByItemId(brd.getItemId());
				if(si==null){
					si= getService.getSysItemByItemId(Constants.STAGE_DEFAULT_AWARD_SYSITEM_ID);
					logger.error("StageClearOpenTimeOut/SysItemNull:\t"  +brd.getItemId()+" \t" + playerId);
				}
				createService.awardToPlayer(player, si,payment, null,Constants.BOOLEAN_YES);
				nosqlService.updatePlayerOpenBrandsNum(playerId,today);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&brd.getItemId()==GrowthMissionConstants.MEDAL_ID) {
					nosqlService.addXunleiLog("7.4"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 1
							+ Constants.XUNLEI_LOG_DELIMITER + brd.getUnit()
							+ Constants.XUNLEI_LOG_DELIMITER+ 6
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
				logger.info("StageClearOpenTimeOut/Award:\t" + playerId + "\t" +si.getId()+"\t"+si.getDisplayNameCN()+"\t"+brd.getUnit() + "\t" + brd.getUnitType());
				deleteService.deletePlayerItemInMemcached(playerId, si);
				}
			}
			if(randomList!=null&&randomList.size()>0){
				for(int brdidx :randomList){
					OnlineAward brd = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),randomBrandIdList.get(brdidx));
					if(brd ==null){
						brd = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),Constants.STAGE_DEFAULT_AWARD_BRAND_ID);
						logger.warn("StageClearOpenTimeOut/BrandNull:\t"  +randomBrandIdList.get(brdidx));
					}
					Payment payment = new Payment();
					payment.setUnit(brd.getUnit());
					payment.setUnitType(brd.getUnitType());
					SysItem si = getService.getSysItemByItemId(brd.getItemId());
					if(si==null){
						si= getService.getSysItemByItemId(Constants.STAGE_DEFAULT_AWARD_SYSITEM_ID);
						logger.warn("StageClearOpenTimeOut/SysItemNull:\t"  +brd.getItemId()+" \t" + playerId);
					}
					createService.awardToPlayer(player, si,payment, null,Constants.BOOLEAN_YES);
					nosqlService.updatePlayerOpenBrandsNum(playerId,today);
					logger.info("StageClearOpenTimeOut/RandomAward:\t" + playerId + "\t" +si.getId()+"\t"+si.getDisplayNameCN()+"\t"+brd.getUnit() + "\t" + brd.getUnitType());
					deleteService.deletePlayerItemInMemcached(playerId, si);
				}
			}
			List<OnlineAward> allRandomBrandSysItemList = getService.getOnlineAwardsByTypeAndIds(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),randomBrandIdList);
			for(OnlineAward oa : allRandomBrandSysItemList){
				if(oa==null)
					oa = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),Constants.STAGE_DEFAULT_AWARD_BRAND_ID);
				SysItem si = getService.getSysItemByItemId(oa.getItemId());
				if(si==null){
					si= getService.getSysItemByItemId(Constants.STAGE_DEFAULT_AWARD_SYSITEM_ID);
				}
				oa.setSysItem(si);
			}
			String indexs = "";
			for(int i = 0;i<randomList.size();i++){
				if(i<randomList.size()-1)
					indexs += (randomList.get(i)+1) + ",";
				else
					indexs += (randomList.get(i)+1);
			}
			return Converter.brandOpenTimeOut(indexs,allRandomBrandSysItemList);
		}catch (BaseException e){
			logger.warn("StageClearOpenTimeOut/Warn:\t" , e );
			return Converter.error(e.getMessage());
		}
		catch (Exception e){
			logger.error("StageClearOpenTimeOut/Error:\t" , e);
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
