package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.XunleiOrderLogDao;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class PayTobeVipServlet extends BaseGMServlet{
	private static final long serialVersionUID = 8640457144125422815L;
	static Logger logger = LoggerFactory.getLogger("paytobevip");
	@Override
	protected String innerService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");

		String orderId = req.getParameter("orderId");
		String userId = req.getParameter("userId");
		String timestamp = req.getParameter("time");
		int rmb = StringUtil.toInt(req.getParameter("rmb"));
		int amount = StringUtil.toInt(req.getParameter("amount"));
		String sign = req.getParameter("sign");
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try {
				time = simpleDateFormat.parse(timestamp);
				Date now = new Date();
				if ((now.getTime() - time.getTime()) > 60000) {// 超过一分钟
					logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|fail:0");
					return "fail:0" ;// 时间戳过期
				}
			} catch (ParseException e) {
				logger.error("ParseException happened in PayTobeVipServlet", e);
				return "fail:4" ;
			}
			if (rmb > 99999) {
				logger.error("ParseException happened in PayTobeVipServlet RMB >99999");
				return "fail:4" ;
			}
			String encodeStr = MD5Util.md5(timestamp + userId + + amount + key);
			if (!encodeStr.equals(sign)) {// 验证串错误
				logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|fail:1");
				return "fail:1" ;
			}
			Integer playerId = getService.getPlayerIdByUserId(userId);
			if (playerId == null) {
				logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|fail:2");
				return "fail:2" ;// 用户不存在
			}
			int size = ServiceLocator.getService.duplicateXunleiOrder(orderId, userId);
			if (size > 0) {
				logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|fail:5");
				return"fail:5" ;// 订单重复
			}
			Player player = getService.getPlayerById(playerId);
			if(player.getVipToExpire() == -1){//玩家已经是永久VIP
				logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|fail:3");
				return "fail:3";
			}
			SysItem si = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.VIP.getValue(), Constants.DEFAULT_ITEM_TYPE).get(0);
			Payment payment = new Payment();
			payment.setUnit(amount);
			payment.setUnitType(Constants.DEFAULT_TIMEBASE_TYPE);
			createService.awardToPlayer(player, si, payment, null, "Y");
//			Calendar c = Calendar.getInstance();
//			int secondsAdd = amount*24*3600;
//			if(player.getIsVip()==1){
//				c.setTimeInMillis(player.getVipToExpire());
//				c.add(Calendar.SECOND, secondsAdd);
//				player.setVipToExpire(c.getTimeInMillis());
//			} else {
//				player.setIsVip(1);
//				c.add(Calendar.SECOND, secondsAdd);
//				player.setVipToExpire(c.getTimeInMillis());
//			}
			XunleiOrderLog xunleiPayLog = new XunleiOrderLog();
			xunleiPayLog.setOrderId(orderId);
			xunleiPayLog.setUserId(userId);
			xunleiPayLog.setPlayerId(playerId);
			xunleiPayLog.setRmb(rmb);
			xunleiPayLog.setAmount(amount);
			xunleiPayLog.setDiscount(1);
			xunleiPayLog.setCreateTime(new Date());
			xunleiPayLog.setType(XunleiOrderLogDao.type_paytobevip);
			createService.createXunleiOrderLog(xunleiPayLog);
			updateService.updatePlayerInfo(player);
			
			final PayLog payLog = new PayLog();
			payLog.setUserId(userId);
			payLog.setPlayerId(playerId);
			payLog.setItemId(0);
			payLog.setItemName("网上VIP消费");
			payLog.setItemType(0);
			payLog.setPayType(9);
			payLog.setPayAmount(rmb);
			payLog.setCreateTime(new Date());
			payLog.setLeftMoney(0);
			payLog.setPayUse(5);
			final Runnable writePayLog = new Runnable() {
				@Override
				public void run() {
					createService.createPayLog(payLog);

				}
			};
			ServiceLocator.asynTakService.execute(writePayLog);
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("10.2"
						+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + 2
						+ Constants.XUNLEI_LOG_DELIMITER + rmb
						+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
						);
			}	
			logger.info(orderId + "|" + userId + "|" + rmb +  "|" + amount + "|" + timestamp +"|ok:" + orderId);
			return "ok:" + amount ;

		} catch (Exception e) {
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			logger.error("Exception happened in PayTobeVipServlet from ip " + ip, e);
			return "fail:4";
		}
	}
	@Override
	protected String getLockKey(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");
			Integer playerId = getService.getPlayerIdByUserId(userId);
			return playerId != null ? playerId +"" :null;
		} catch (Exception e) {
			return null;
		}
	}
}
