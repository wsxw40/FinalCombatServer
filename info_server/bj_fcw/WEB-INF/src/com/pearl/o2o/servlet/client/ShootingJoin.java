package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class ShootingJoin extends BaseClientServlet {
	
	private static final long serialVersionUID = -7556870074674515144L;
	private Logger logger = LoggerFactory.getLogger("shooting");
	private static final String[] paramNames = {"pid","type"};
	@Override
	protected String innerService(String... strings) {
		try {
			int playerId = StringUtil.toInt(strings[0]);
			String type = strings[1];
			//每次进入靶场或者切换靶场，清空已缓存无效数据
			String shootMccKey = Constants.SHOOTING_MCC_KEY_PREFX + playerId;
			mcc.delete(shootMccKey);
			String rdmGiftsKey = Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId;
			mcc.delete(rdmGiftsKey);
			int ammoNum = 0;
			int boutNum = 1;
			int dartleNum = 0;
			int lookNum = 0;
			int needDartleNum = Constants.DARTLE_LEVEL_NUMS[0];
			List<SysItem> shootingLooks = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.SHOOTING_LOOK.getValue(), Constants.DEFAULT_ITEM_TYPE);
			SysItem shootingLook = null;
			for(SysItem si : shootingLooks){
				if(type.equals(si.getIValue())){
					shootingLook = si;
					break;
				}
			}
			lookNum = getService.getPlayerItemsTotalQuantity(playerId, Constants.DEFAULT_ITEM_TYPE, shootingLook.getId());
			shootingLook.setUnit(lookNum);
			List<SysItem> shootingAmmos = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.SHOOTING_AMMO.getValue(), Constants.DEFAULT_ITEM_TYPE);
			for(SysItem si : shootingAmmos){
				if(type.equals(si.getIValue())){
					ammoNum = getService.getPlayerItemsTotalQuantity(playerId, Constants.DEFAULT_ITEM_TYPE, si.getId());
					break;
				}
			}
			List<OnlineAward>  goldGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),4);
			for(OnlineAward goa : goldGifts){//过滤稀有度小于60的奖励
				if(getService.getSysItemByItemId(goa.getItemId()).getRareLevel()<60){
					goa.setWeight(0);
				}
			}
			OnlineAward goldGift = getService.randomOnlineAwardFromList(1, goldGifts).get(0);
			SysItem si = getService.getSysItemByItemId(goldGift.getItemId());
			if(si!=null){
				goldGift.setSysItem(si);
			}
			List<OnlineAward> silverGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),3);
			for(OnlineAward soa : silverGifts){//过滤稀有度小于40的奖励
				if(getService.getSysItemByItemId(soa.getItemId()).getRareLevel()<40){
					soa.setWeight(0);
				}
			}
			OnlineAward silverGift = getService.randomOnlineAwardFromList(1, silverGifts).get(0);
			si = getService.getSysItemByItemId(silverGift.getItemId());
			if(si!=null){
				silverGift.setSysItem(si.clone());
			}
			//靶场超值奖励
			List<OnlineAward> commonGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(), 0);
			
			List<OnlineAward> randomCommonGifts = getService.randomObjsFromList(1, commonGifts);
			for(OnlineAward oa : randomCommonGifts){
				si = getService.getSysItemByItemId(oa.getItemId());
				if(si!=null){
					oa.setSysItem(si);
				}
			}
			int dartleAwardType = "0".equals(type)?Constants.ONLINE_AWARD_TYPES.SILVER_DARTLE_AWARD.getValue():Constants.ONLINE_AWARD_TYPES.GOLD_DARTLE_AWARD.getValue();
			List<OnlineAward> dartleGifts = getService.getOnlineAwardByTypeAndLevel(dartleAwardType, 0);
			OnlineAward dartleGift = null;
			if(dartleGifts!=null&&dartleGifts.size()>0){
				dartleGift = dartleGifts.get(0);
				si = getService.getSysItemByItemId(dartleGift.getItemId());
				if(si!=null){
					dartleGift.setSysItem(si.clone());
				}
			}
			List<String> dartleTopList = new ArrayList<String>();
			String dartleTopRedisKey = Constants.DARTLE_TOP_REDIS_KEY;
			NoSql nosql = nosqlService.getNosql();
			Set<String> rankSet= nosql.zRange(dartleTopRedisKey, 0, Constants.DARTLE_TOP_NUM);
			for(String s :rankSet){
				Player p= getService.getPlayerById(Integer.parseInt(s));
				if(p!=null){
					String newStr = CommonUtil.generateDartleTopRetStr(dartleTopRedisKey,p);
					dartleTopList.add(newStr);
				}else{
					logger.warn("ShootTop/PlayerNull:\t" + s);
					nosql.zRem(dartleTopRedisKey,s);
				}
				
			}
			
			List<TmpPlayerItem> dartleAwards = nosqlService.getDartleAwardsTopList();
			return Converter.shootingJoin(boutNum, dartleNum, ammoNum,needDartleNum, shootingLook ,goldGift, silverGift, randomCommonGifts,dartleGift, dartleTopList,dartleAwards);
		} catch (Exception e) {
			logger.error("ShootingJoin/Error:\t" , e);
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
