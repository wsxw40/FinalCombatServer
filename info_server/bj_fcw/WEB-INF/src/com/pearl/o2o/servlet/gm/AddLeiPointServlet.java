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

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.dao.impl.nonjoin.XunleiOrderLogDao;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class AddLeiPointServlet extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;
	static Logger logger = LoggerFactory.getLogger(AddLeiPointServlet.class.getName());
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
		
		StringBuffer checkSuccess=new StringBuffer("check+ " +orderId+"|" +userId+"|" +timestamp+"|" +rmb+"|" +amount+"|");
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try {
				time = simpleDateFormat.parse(timestamp);
				Date now = new Date();
				if ((now.getTime() - time.getTime()) > 60000) {// 超过一分钟
					ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:0");
					return "fail:0" ;// 时间戳过期
				}
			} catch (ParseException e) {
				logger.error("ParseException happened in AddLeiPointServlet", e);
				ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:4");
				return "fail:4" ;
			}
			if (rmb > 99999) {
				logger.error("ParseException happened in AddLeiPointServlet RMB >99999");
				ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:4");
				return "fail:4" ;
			}
			String encodeStr = MD5Util.md5(timestamp + userId + orderId + rmb + amount + key);
			if (!encodeStr.equals(sign)) {// 验证串错误
				ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:1");
				return "fail:1" ;
			}
			Integer playerId = getService.getPlayerIdByUserId(userId);
			if (playerId == null) {
				ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:2");
				return "fail:2" ;// 用户不存在
			}

			int size = ServiceLocator.getService.duplicateXunleiOrder(orderId, userId);
			if (size > 0) {
				ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:3");
				return"fail:3" ;// 订单重复
			}
			XunleiOrderLog xunleiPayLog = new XunleiOrderLog();
			xunleiPayLog.setOrderId(orderId);
			xunleiPayLog.setUserId(userId);
			xunleiPayLog.setPlayerId(playerId);
			xunleiPayLog.setRmb(rmb);
			xunleiPayLog.setAmount(amount);
			xunleiPayLog.setDiscount(amount / rmb);
			xunleiPayLog.setCreateTime(new Date());
			xunleiPayLog.setType(XunleiOrderLogDao.type_recharge);
			Player player = createService.createAddXunleiOrderLog(xunleiPayLog, playerId,checkSuccess);
			
			// 成长任务33：第一次充值
			updateService.updatePlayerGrowthMission(player, amount,GrowthMissionType.FIRST_RECHARGE);
			updateService.updatePlayerGrowthMission(player,amount, GrowthMissionType.RECHARGE158 );
			updateService.updatePlayerGrowthMission(player,amount, GrowthMissionType.RECHARGE188);
			updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.CHARGE_FC.getValue(),playerId, null, rmb, 0,null,null);
			ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|ok:" + orderId);
			if (!ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
				infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
						LogUtils.JoinerByTab.join("4.1",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,rmb,"currency",
								player.getName(),player.getRank(),playerId,CommonUtil.isToday(player.getCreateTime())?1:0));
				
				String message = String.format(
						"%s\t%s\t%s\t%s\t%s\t%s", player.getUserName(), rmb, player.getName(),player.getRank(),playerId,CommonUtil.isToday(player.getCreateTime())?1:0);
				// 记到analyser server
				transferDataToDc.addLog("bjReCharge", message);					
			}
			return "ok:" + orderId ;

		} catch (Exception e) {
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			logger.error("Exception happened in AddLeiPointServlet from ip " + ip, e);
			ServiceLocator.rechargeLog.info(orderId + "|" + userId + "|" + rmb + "|" + timestamp + "|fail:4");
			return "fail:4";
		}finally{
			ServiceLocator.rechargeLog.info(checkSuccess.toString());
		}
	}
	@Override
	protected String getLockKey(HttpServletRequest request) {
		try {
			String userId = request.getParameter("userId");
			Integer playerId = getService.getPlayerIdByUserId(userId);
			return CommonUtil.getLockKey(playerId);
		} catch (Exception e) {
			return null;
		}
	}
}
