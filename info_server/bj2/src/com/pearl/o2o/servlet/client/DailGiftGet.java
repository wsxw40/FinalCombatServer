package com.pearl.o2o.servlet.client;

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
import com.pearl.o2o.utils.QuietBounds;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class DailGiftGet extends BaseClientServlet{
	
	private static final long serialVersionUID = 2028735459339006028L;
	private static Logger log = LoggerFactory.getLogger("dailycheck");
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			Date date = new Date();
			String dateStr = CommonUtil.dateFormatDate.format(date);
			String msg = null;
			int isGift = nosqlService.isDailyGift(playerId, dateStr);
			
			List<OnlineAward> dailyAwards = getService.getDailyCheckGifts(Constants.DAILY_CHECK_AWARD_LEVEL.DAILY_GIFT.getValue());
			QuietBounds quietBounds=null;
			if(isGift==1){
				return Converter.dailyGift(ExceptionMessage.DAILY_GIFT_ALREADY_MSG, dailyAwards,isGift,quietBounds);
			}
			if(dailyAwards==null||dailyAwards.size()==0){
				return Converter.dailyGift(ExceptionMessage.DAILY_GIFT_FAILED_MSG, dailyAwards,isGift,quietBounds);
			}
			
			Payment pm = new Payment();
			for(OnlineAward oa : dailyAwards){
				SysItem si = getService.getSysItemByItemId(oa.getItemId());
				if(si!=null){
					oa.setSysItem(si);
					//根据vip等级 获得额外奖励
					int itemNum=oa.getUnit()+(int)Math.ceil(player.getIsVip()/3.0);
					//oa是从mcc中拿出来，不需要clone
					oa.setUnit(itemNum);
					pm.setUnit(itemNum);
					pm.setUnitType(oa.getUnitType());
					createService.awardToPlayer(player, si, pm, null, Constants.BOOLEAN_YES);
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&oa.getItemId()==GrowthMissionConstants.MEDAL_ID) {
						nosqlService.addXunleiLog("7.4"
								+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + 1
								+ Constants.XUNLEI_LOG_DELIMITER + oa.getUnit()
								+ Constants.XUNLEI_LOG_DELIMITER + getService.getMedolNumByPlayerId(playerId)
								+ Constants.XUNLEI_LOG_DELIMITER+ 7
								+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
								);
					}
					log.info("DailGiftGet/Award:\t" + playerId + "\t" +si.getId());
					}else{
					log.warn("DailGiftGet/SysItemNull"  +oa.getItemId());	
					throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
					}
				}
			nosqlService.dailyGift(playerId, dateStr);
			//vip 获得升级经验
			if(player.getIsVip()>0){		
				ServiceLocator.updateService.updateVipUpExp(Constants.VIP_EARN_EXP_METHODS.DAILYCHECK.getValue(), playerId);
			}
			
			//发放不进行提示的奖励
			quietBounds=createBoundsDailyQuietly(playerId);
			
			return Converter.dailyGift(msg, dailyAwards,isGift,quietBounds);
			
		} catch (BaseException e) {
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			log.error("DailGiftGet/Error:\t" , e);
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
	
	
	/**
	 * 发放不进行提示的奖励
	 * @param playerId
	 * @throws Exception
	 */
	private QuietBounds createBoundsDailyQuietly(int playerId) throws Exception{
		//发放每日奖励
		return createService.createBoundsDaily(playerId);
	}
}
