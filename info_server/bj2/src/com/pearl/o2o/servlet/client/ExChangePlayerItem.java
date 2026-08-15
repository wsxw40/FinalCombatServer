package com.pearl.o2o.servlet.client;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.service.WSysItemPriceService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class ExChangePlayerItem extends BaseClientServlet {

	private static final long serialVersionUID = -7348426354782892668L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String[] paramNames = { "pid", "sid", "costid" };
	@Resource
	private WSysItemPriceService wSysItemPriceService;

	/**
	 * 免费兑换
	 * 勋章、复活币
	 */
	@Override
	protected String innerService(String... args) {
		return rpc(args);
		//		for (int i = 0; i < args.length; i++) {
		//			if (!args[i].matches("^\\d+$")) {
		//				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//			}
		//		}
		//		final int playerId = StringUtil.toInt(args[0]);
		//		final int sysItemId = StringUtil.toInt(args[1]);
		//		final int costId = StringUtil.toInt(args[2]);
		//		//add the check str for log
		//		StringBuffer sbBuffer = new StringBuffer(playerId + "|" + sysItemId + "|" + costId + "-");
		//
		//		try {
		//
		//			if (costId == 0) {
		//				throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		//			}
		//			Player player = getService.getPlayerById(playerId);
		//			final SysItem sysItem = getService.getSysItemByItemId(sysItemId);
		//
		//			//zlm2015-8-4-start
		//			//免费兑换 IS_SHOW 1 范围内
		//			if (getService.getExchangeSysItemList().indexOf(sysItem) == -1)
		//				throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		//			//zlm2015-8-4-end
		//
		//			//检测是否输过二级密码
		//			//			if(!checkEnterSPW(playerId)){
		//			//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
		//			//			}
		//			int playerItemId = createService.buyPlayerItem(sbBuffer, player.getUserName(), player, sysItem, costId, Constants.BOOLEAN_NO, 0);
		//			if (playerItemId == -1) {
		//				throw new BaseException(ExceptionMessage.EXCHANGE_FAIL);
		//			}
		//			deleteService.deletePlayerItemInMemcached(playerId, sysItem);
		//
		//			Payment payment = null;
		//			try {
		//				payment = getService.getPaymentById(sysItemId, costId);
		//			} catch (Exception e) {
		//				// TODO Auto-generated catch block
		//				logger.warn("Exception in get payment with costId " + costId + " when log to dc!", e);
		//			}
		//			//zlm2015-8-4-start
		//			////免费兑换 范围内
		//			if (payment != null && payment.getIsShow() == 0)
		//				throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		//			//zlm2015-8-4-end
		//			try {
		//				if (payment != null && (payment.getPayType() == Constants.NUM_FOUR || payment.getPayType() == Constants.REVIVE_COIN_PAY)) { //确保是勋章支付|复活币支付
		//
		//					String message = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s", playerId, payment.getId(), payment.getItemId(), sysItem.getDisplayName(), payment.getCost(), payment.getUnit(),
		//							payment.getUnitType());
		//					// 记到analyser server
		//					transferDataToDc.addLog("bjExchange", message);
		//				}
		//
		//			} catch (Exception e) {
		//				// TODO Auto-generated catch block
		//				logger.warn("Exception happened when sending log message to dc!", e);
		//			}
		//			String msg = "";
		//			if (sysItem.getIId() == 8) {	//该处为，C币直接发放进账户的提示，用于免费兑换界面的C币兑换 20140911 OuYangGuang
		//				playerItemId = -8;
		//				msg = CommonUtil.messageFormatI18N(CommonMsg.GBSUCCESSMSG, (payment.getUnit() * Integer.parseInt(sysItem.getIValue())));
		//			}
		//
		//			return Converter.createPlayerItem(playerItemId, msg.length() > 0 ? msg : null);
		//		} catch (NotBuyEquipmentException nbe) {
		//			logger.debug("Exception in CreatePlayerItem" + nbe.getMessage());
		//			return Converter.createPlayerItem(Constants.NUM_ZERO, nbe.getMessage());
		//		} catch (Exception e) {
		//			logger.warn("Exception in CreatePlayerItem", e);
		//			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//		} finally {
		//			infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, LogUtils.JoinerByTab.join("0.2", CommonUtil.simpleDateFormat.format(new Date()), sbBuffer.toString()));
		//		}
	}

	private String rpc(String... args) {
		try {
			int playerId = Integer.parseInt(args[0]);
			int sysItemPriceId = Integer.parseInt(args[2]);
			Player player = getService.getPlayerById(playerId);
			//找到多对多交易的分组
			int payGroup = wSysItemPriceService.findList(null).stream().filter(p -> {
				return p.getId() == sysItemPriceId && p.getLevel() <= player.getRank() && p.getVipLevel() <= player.getIsVip();
			}).map(p -> p.getPayGroup()).findFirst().orElse(0);
			if (payGroup == 0) {
				throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			}
			//找到指定分组的交易数据
			List<WSysItemPrice> wSysItemPriceList = wSysItemPriceService.findList(null).stream().filter(p -> {
				return p.getPayGroup() == payGroup && p.getLevel() <= player.getRank() && p.getVipLevel() <= player.getIsVip();
			}).collect(Collectors.toList());
			//扣除玩家非交易目标的数据
			updateService.reduceCurrencyOrItem(playerId, wSysItemPriceList);
			// 给玩家增加交易目标的数据
			updateService.earnCurrenyOrItem(playerId, wSysItemPriceList, false, true, false);//注意商店购买的道具未绑定，但是免费兑换的道具绑定
			//FIXME 成长任务：使用勋章购买强化部件，旧缓存
			if (wSysItemPriceList.stream().filter(p -> p.getIsTarget().equals(1) && p.getSysItemId().equals(125)).count() > 0) {
				if (wSysItemPriceList.stream().filter(p -> p.getIsTarget().equals(0) && p.getSysItemId().equals(4479)).count() > 0) {
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.BUY_STRENGTH_BY_MEDAL);
				}
			}

			return Converter.createPlayerItem(1, null);
		} catch (BaseException e1) {
			logger.error("c_shop_req_exchange has error : ", e1);
			return Smarty4jConverter.error(e1.getMessage());
		} catch (Exception e) {
			logger.error("c_shop_req_exchange has error : ", e);
		}
		return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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
