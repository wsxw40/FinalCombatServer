package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class ExChangeVIPExpItem extends BaseClientServlet {

	private static final long serialVersionUID = -7348426354782892668L;
	static Logger log = LoggerFactory.getLogger(ExChangeVIPExpItem.class.getName());
	private String[] paramNames = {"pid"};

	@Override
	protected String innerService(String... args) {
		for(int i=0;i<args.length;i++){
			if(!args[i].matches("^\\d+$")){	
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		final int playerId = StringUtil.toInt(args[0]);
		//add the check str for log
		StringBuffer sbBuffer=new StringBuffer(playerId+"-");
		String msg="";
		try {
			Player player = getService.getPlayerById(playerId);
			int cItem = player.getVipExpGiftLevel();
			int nextItem = ServiceLocator.updateService.getVipExpToItem(player.getVipExp(),cItem);
			if(nextItem<0)
			{
				//throw new BaseException(ExceptionMessage.NOT_ENOUGH_VIP_EXP);
				msg = ExceptionMessage.NOT_ENOUGH_VIP_EXP;
				return Converter.createPlayerItem(-2 , msg);
			}
			
			final SysItem sysItem = getService.getSysItemByItemId(Constants.VIP_EXP_ITEMS[nextItem][1]);
			
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}

			Payment payment=null;
			try {
				payment = getService.getPaymemtListById(sysItem.getId()).get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.warn("Exception in get payment with costId "+payment.getId()+" when log to dc!",e);
			} 
			int playerItemId = -1;
			try {
				if(payment!=null && (payment.getPayType()==Constants.VIP_EXP_PAY)){ //确保是VIP经验支付
					String message = String.format(
									"%s\t%s\t%s\t%s\t%s\t%s\t%s", playerId, payment.getId(), payment.getItemId(),sysItem.getDisplayName(),payment.getCost(),payment.getUnit(),payment.getUnitType());
					// 记到analyser server
					transferDataToDc.addLog("bjExchange", message);		
				}
				playerItemId = createService.sendItem(sysItem, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
			} catch (Exception e) {
						// TODO Auto-generated catch block
				log.warn("Exception happened when sending log message to dc!",e);
			}
			
			if (playerItemId == -1) {
				throw new BaseException(ExceptionMessage.EXCHANGE_FAIL);
			}
			player.setVipExpGiftLevel(nextItem);
			
			ServiceLocator.updateService.updatePlayerInfo(player);
			deleteService.deletePlayerItemInMemcached(playerId, sysItem);
			msg = CommonUtil.messageFormatI18N(CommonMsg.VIP_SUCCESS_MESSAGE_CON, (payment.getUnit()*Integer.parseInt(sysItem.getIValue())),sysItem.getDisplayName());
			
			if(playerItemId>0)
				playerItemId=-8;
//			else
//				Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			
			return Converter.createPlayerItem(playerItemId, msg);
		} catch (NotBuyEquipmentException nbe) {
			log.debug("Exception in CreatePlayerItem"+nbe.getMessage());
			return Converter.createPlayerItem(Constants.NUM_ZERO, nbe.getMessage());
		} catch (Exception e) {
			log.warn("Exception in CreatePlayerItem", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}finally{
			infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
					LogUtils.JoinerByTab.join("0.2",CommonUtil.simpleDateFormat.format(new Date()),sbBuffer.toString()));
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
