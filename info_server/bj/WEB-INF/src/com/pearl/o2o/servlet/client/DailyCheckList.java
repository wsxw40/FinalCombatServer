package com.pearl.o2o.servlet.client;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DailyCheckList extends BaseClientServlet {

	private static final long serialVersionUID = 6755165691495403628L;
	private static Logger log = LoggerFactory.getLogger("dailycheck");
	private static final String[] paramNames = { "pid" };
	private static String dailyRandomKey = Constants.DAILY_RANDOM_NUM_MCC_KEY;

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			
			
			Player player =  getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			Calendar date = Calendar.getInstance();
			String dateStr = CommonUtil.dateFormatDate.format(date.getTime());
			boolean isInit = nosqlService.isInitPlayerNum(playerId,dateStr);
			
			Calendar gDate = Calendar.getInstance(); 
			gDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
			String tomDateStr = CommonUtil.dateFormatDate.format(gDate.getTime());
			if(!isInit){
				nosqlService.updatePlayerGuessNum(playerId, dateStr, tomDateStr);
				nosqlService.setIsInitPlayerNum(playerId,dateStr);
			}
			boolean isInitCheckDays = nosqlService.isInitDailyCheckDays(playerId, date);
			if(!isInitCheckDays)
				nosqlService.initPlayerCheckList(playerId);
			List<String> playerCheckList = nosqlService.getPlayerCheckList(playerId);
			List<SysItem> checkItemList = nosqlService.getCheckItemList(playerId);

			int todayNum = nosqlService.getDailyRandomNum(dateStr);
			if(todayNum==-1){
			todayNum = createService.dailyRandomNumCreate(todayNum, dailyRandomKey);
			}
//			int myNum = nosqlService.getPlayerNum(playerId, 1,dateStr);
			
			int vipLevel = player.getIsVip();
			
			// 获得玩家已猜过的数字
			String playerNums = vipLevel>0 ? nosqlService.getPlayerNums(playerId, 1, dateStr) : String.valueOf(nosqlService.getPlayerNum(playerId, 1,dateStr));
			// 看是否猜中
			boolean isNeedAward = false;
			String[] playerNumArray = playerNums.split(",");
			for (int i=0; i<playerNumArray.length; i++) {
				if (Integer.parseInt(playerNumArray[i])==todayNum) {
					isNeedAward = true;
					break;
				}
			}
			
//			int tomorrow = nosqlService.getPlayerNum(playerId, 2,tomDateStr);
			String tomorrows = vipLevel>0 ? nosqlService.getPlayerNums(playerId, 2,tomDateStr) : String.valueOf(nosqlService.getPlayerNum(playerId, 2,tomDateStr));
			
			int isGift = nosqlService.isDailyGift(playerId, dateStr);
			int isShowAward = nosqlService.getIsDailyGusAward(playerId, dateStr);
			if(isShowAward==0&&isNeedAward){
				List<OnlineAward> dailyAwards = getService.getDailyCheckGifts(Constants.DAILY_CHECK_AWARD_LEVEL.DAILY_GUESS.getValue());
				for(OnlineAward oa : dailyAwards){
					SysItem si = getService.getSysItemByItemId(oa.getItemId());
					Payment pm = new Payment();
					if(si!=null){
						pm.setUnit(oa.getUnit());
						pm.setUnitType(oa.getUnitType());
						createService.awardToPlayer(player, si, pm, null, Constants.BOOLEAN_YES);
//						log.info("Award to player:" + playerId + " sysItem:" +si.getId()+" when guessing daily num right");
						log.info("DailyCheckList/Award:\t" + playerId + "\t" +si.getId());
					}else{
						log.warn("DailyCheckList/SysItemNull:\t"  +oa.getItemId());
						throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
					}
				}
				nosqlService.dailyGusAward(playerId, dateStr);
			}
			
			int vipCheckTimes = Constants.VIP_DAILY_CHECK_COUNT[vipLevel>0?vipLevel:0]; // vip会员允许免费补签和预签的次数
			
			
			// 已预签和已补签次数合
			Calendar today = Calendar.getInstance();
			String yearMonth = String.valueOf(today.get(Calendar.YEAR)) + today.get(Calendar.MONTH);
			int checkedTimes = nosqlService.getVipPlayerCheckTimes(playerId,0,yearMonth) + nosqlService.getVipPlayerCheckTimes(playerId,1,yearMonth);
			int checkOver = nosqlService.getPlayerCheckTimes(playerId,0,yearMonth) + nosqlService.getPlayerCheckTimes(playerId,1,yearMonth);	//非Vip补预签次数
			int freeCheckTimes = 0;
			if (vipLevel>0) {
				freeCheckTimes = vipCheckTimes > checkedTimes ? (vipCheckTimes-checkedTimes) : 0;
			}
			
			int checkOverVip = checkOver - vipCheckTimes;//Vip 补预签剩余次数
			
//			System.out.println("DailyCheckList  checkedTimes="+checkedTimes);
			return Converter.dailyCheckList(dateStr, date.get(Calendar.DAY_OF_WEEK)-Constants.NUM_ONE,playerCheckList,checkItemList ,todayNum ,playerNums,tomorrows,isGift,isShowAward,freeCheckTimes,checkOverVip);
		} catch (BaseException e) {
			log.warn("DailyCheckList/Warn:\t", e);
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("DailyCheckList/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
