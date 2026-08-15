package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.StringUtil;

/**
 * 快速购买接口
 * @author wuxiaofei
 *
 */
public class CombineFastBuyItems extends BaseClientServlet {
	private static final long serialVersionUID = 6356406975633293330L;
	static Logger log = LoggerFactory.getLogger(CombineFastBuyItems.class.getName());
	private String[] paramNames = { "pid", "sid", "num" };

	/* (non-Javadoc)
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String innerService(String... args) {
		for(int i=0;i<args.length;i++){		
			if(!args[i].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		int playerId = StringUtil.toInt(args[0]);
		int sysItemId = StringUtil.toInt(args[1]);
		int num = StringUtil.toInt(args[2]);
		
		StringBuffer sbBuffer=new StringBuffer(playerId+"|"+sysItemId+"|"+num+"-");
		
		try {
			Player player = getService.getPlayerById(playerId);
			SysItem sysItem = getService.getSysItemByItemId(sysItemId);
			
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			Payment payment = new Payment();
			payment.setUnitType(Constants.NUM_ONE);
			payment.setUnit(num);
			Payment onePrice = null;
			if(null != sysItem.getAllCrPricesList() && !sysItem.getAllCrPricesList().isEmpty()){
				onePrice = sysItem.getAllCrPricesList().get(0);
			}else if(null != sysItem.getAllGpPricesList() && !sysItem.getAllGpPricesList().isEmpty()){
				onePrice = sysItem.getAllGpPricesList().get(0);
			}	
			if(onePrice == null){
				throw new BaseException(ExceptionMessage.NO_SINGLE_PRICE);
			}
			payment.setCost(onePrice.getCost());

			int piId = createService.buyPlayerItemByNewPayment(sbBuffer,sysItem, player, payment);
			deleteService.deletePlayerItemInMemcached(playerId, sysItem);
			PlayerItem item = null;
			if(piId > 0){
				item = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_MATERIAL_TYPE, piId);
			}
			return Converter.combineFastBuy(item, piId);
		} catch (BaseException be) {
			log.debug("Exception in CombineFastBuyItems"+be.getMessage());
			return Converter.createPlayerItem(Constants.NUM_ZERO, be.getMessage());
		} catch (Exception e) {
			log.warn("Exception in CombineFastBuyItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}finally{
			infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
					LogUtils.JoinerByTab.join("0.3",CommonUtil.simpleDateFormat.format(new Date()),sbBuffer.toString()));
		
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
