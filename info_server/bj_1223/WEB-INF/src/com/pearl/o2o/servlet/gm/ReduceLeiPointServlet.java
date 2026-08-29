package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.XunleiOrderLogDao;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class ReduceLeiPointServlet extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;
	static Logger logger = LoggerFactory.getLogger(ReduceLeiPointServlet.class.getName());

	@Override
	protected String innerService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");

		String orderId = req.getParameter("orderId");
		String userId = req.getParameter("userId");
		String timestamp = req.getParameter("time");
		int amount = StringUtil.toInt(req.getParameter("amount"));
		String sign = req.getParameter("sign");
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try {
				time = simpleDateFormat.parse(timestamp);
				Date now = new Date();
				if ((now.getTime() - time.getTime()) > 60000) {// 超过一分钟
					ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:0");
					return "fail:0";// 时间戳过期
				}
			} catch (ParseException e) {
				logger.error("ParseException happened in AddLeiPointServlet", e);
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:4");
				return "fail:4";
			}
			
			String encodeStr = MD5Util.md5(timestamp + userId + orderId + amount + key);
			if (!encodeStr.equals(sign)) {// 验证串错误
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:1");
				return "fail:1";
			}
			Integer playerId = getService.getPlayerIdByUserId(userId);
			if (playerId == null) {
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:2");
				return "fail:2";// 用户不存在
			}

			int size = ServiceLocator.getService.duplicateXunleiOrder(orderId, userId);
			if (size > 0) {
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:3");
				return "fail:3";// 重复订单扣除
			}
			PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
			if(playerInfo.getXunleiPoint() >= amount){
				XunleiOrderLog xunleiPayLog = new XunleiOrderLog();
				xunleiPayLog.setOrderId(orderId);
				xunleiPayLog.setUserId(userId);
				xunleiPayLog.setPlayerId(playerId);
				xunleiPayLog.setAmount(amount);
				xunleiPayLog.setCreateTime(new Date());
				xunleiPayLog.setType(XunleiOrderLogDao.type_chargeback);

				createService.createReduceXunleiOrderLog(xunleiPayLog, playerId,playerInfo);
				
				final PayLog payLog = new PayLog();
				payLog.setUserId(userId);
				payLog.setPlayerId(playerId);
				payLog.setItemId(0);
				payLog.setItemName("网上商城扣款");
				payLog.setItemType(0);
				payLog.setPayType(2);
				payLog.setPayAmount(amount);
				payLog.setCreateTime(new Date());
				payLog.setLeftMoney(playerInfo.getXunleiPoint());
				payLog.setPayUse(4);
				final Runnable writePayLog = new Runnable() {
					@Override
					public void run() {
						createService.createPayLog(payLog);

					}
				};
				ServiceLocator.asynTakService.execute(writePayLog);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					Player player=getService.getPlayerById(playerId);
					nosqlService.addXunleiLog("10.1"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 2
							+ Constants.XUNLEI_LOG_DELIMITER + amount
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}	
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|ok:" + playerInfo.getXunleiPoint());
				return "ok:" + playerInfo.getXunleiPoint();
			}else{
				ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:5");
				return "fail:5";// 余额不足
			}
		} catch (Exception e) {
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			logger.error("Exception happened in AddLeiPointServlet from ip " + ip, e);
			ServiceLocator.chargebackLog.info(orderId + "|" + userId + "|" + timestamp + "|fail:3");
			return "fail:4" ;//其他错误
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
