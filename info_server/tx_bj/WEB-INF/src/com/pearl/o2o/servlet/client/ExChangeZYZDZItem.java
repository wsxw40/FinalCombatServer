package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.client.InforLogger;
import com.pde.log.client.LogClient;
import com.pde.log.common.LogMessage.Level;
import com.pde.log.common.LogMessage.LogMsg;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.BuyItemRecord;
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

public class ExChangeZYZDZItem extends BaseClientServlet {

	private static final long serialVersionUID = -7348426354782892668L;
	static Logger log = LoggerFactory.getLogger(ExChangeZYZDZItem.class.getName());
	private String[] paramNames = {"pid", "sid","costid"};

	@Override
	protected String innerService(String... args) {
		for(int i=0;i<args.length;i++){
			if(!args[i].matches("^\\d+$")){	
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		final int playerId = StringUtil.toInt(args[0]);
		final int sysItemId = StringUtil.toInt(args[1]);
		final int costId = StringUtil.toInt(args[2]);
		//add the check str for log
		StringBuffer sbBuffer=new StringBuffer(playerId+"|"+sysItemId+"|"+costId+"-");
		
		try {
		
			if (costId == 0) {
				throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			}
			Player player = getService.getPlayerById(playerId);
			final SysItem sysItem = getService.getSysItemByItemId(sysItemId);

			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			//base 价格
			Payment payment=getService.getPaymentById(sysItemId,costId).clone();
			if(payment==null){
				throw new BaseException(ExceptionMessage.EXCHANGE_FAIL);
			}
			BuyItemRecord buyItemRecord=getService.getBuyItemRecord(playerId, sysItemId);
			if(buyItemRecord==null){
				buyItemRecord=createService.createBuyItemRecord(playerId, sysItemId, costId, payment.getPayType(), payment.getCost());
			}
			
			int record=buyItemRecord.getRecord();
			if(record>=0){// 根据当日购买次数 计算当前价格
				int price=CommonUtil.getCostForBuyRecord(record,payment.getCost(),sysItem.getIId());
				payment.setCost(price);
			}
			
			int playerItemId = createService.buyPlayerItem(sbBuffer,player.getUserName(), player, sysItem, payment, Constants.BOOLEAN_NO, 0);
			if (playerItemId == -1) {
				throw new BaseException(ExceptionMessage.EXCHANGE_FAIL);
			}
			deleteService.deletePlayerItemInMemcached(playerId, sysItem);
			
			buyItemRecord.setRecord(++record);
			updateService.updateBuyItemRecord(buyItemRecord);
			
			return Converter.createPlayerItem(playerItemId, null);
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
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
