package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.pde.log.client.InforLogger;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.MessageService;
import com.pearl.o2o.service.NosqlService;
import com.pearl.o2o.service.ResetService;
import com.pearl.o2o.service.TeamService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.socket.Response;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.SecondPasswordStatus;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TransferDataToDC;

public class BaseClientServlet extends HttpServlet {
	private static final long serialVersionUID = -5579552513208224076L;
	protected InforLogger infoLogger;

	//log to analyser
	protected TransferDataToDC transferDataToDc;

	public TransferDataToDC getTransferDataToDc() {
		return transferDataToDc;
	}

	public void setTransferDataToDc(TransferDataToDC transferDataToDc) {
		this.transferDataToDc = transferDataToDc;
	}

	private static Logger logger = LoggerFactory.getLogger(SocketClientNew.class);

	public BaseClientServlet() {

	}

	//ne
	protected String innerService(String... strings) {
		return "";
	};

	protected String[] paramNames() {
		return new String[0];
	};

	protected String getLockKey(String[] paramNames) {
		return null;
	};

	public Cache<String, String> viewLocalCache = CacheBuilder.newBuilder().build();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String[] paramValues = new String[paramNames().length];
		for (int i = 0; i < paramValues.length; i++) {
			paramValues[i] = req.getParameter(paramNames()[i]);
		}
		String result = innerService(paramValues);
		res.getWriter().write(result);
	}

	public String service(String... paramNames) {
		return innerService(paramNames);
	}

	public List<Response> execute(Map<String, String> params) throws MemcachedException, TimeoutException, Exception {
		String[] paramValues = new String[paramNames().length];
		//获取参数时，增加获取防沉迷时间的逻辑
		int playerId = -1;
		int time = -1;

		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			String parmString = iterator.next();
			if (parmString.equals("fcm_online_time")) {
				//确保是数字
				String parmValue = params.get(parmString);
				if (parmValue.matches("^[-]?\\d+$")) {
					time = StringUtil.toInt(params.get(parmString));
				}

			}
			if (parmString.equals("pid")) {
				playerId = StringUtil.toInt(params.get(parmString));
			}
		}

		//		logger.error("get the fcm_time for"+playerId+"with time "+time);
		if (playerId != -1 && time >= -1) {
			if (time == -1) {//客户端未拿到防沉迷时间时，当作玩家防沉迷时间为0
				nosqlService.updateFCMTime(playerId, 0);
			}
			nosqlService.updateFCMTime(playerId, time);
		}

		for (int i = 0; i < paramValues.length; i++) {
			paramValues[i] = params.get(paramNames()[i]);
		}

		boolean isLocked = false;
		String key = getLockKey(paramValues);
		String result = "";

		if (key == null) {
			result = innerService(paramValues);
		} else {
			try {
				isLocked = ServiceLocator.ml.tryLock(key, 2000); // 可以以PLAYER ID为锁的KEY，或自己能方便标识公共资源的KEY，该方法不会抛出任何异常
				if (isLocked) { // 加锁了便需要解锁
					result = innerService(paramValues);
				} else {// 拿锁失败
					throw new Exception("BaseClientServlet lock fail key=" + key + ". " + StringUtil.printfString(paramValues)); // 在指定时间内拿不到锁，根据业务逻辑做相应处理
				}
			} finally {
				ServiceLocator.ml.unlock(key);// 无论如何要在这里解锁
			}
		}
		List<Response> responses = new ArrayList<Response>();
		responses.add(Response.text(result));
		return responses;
	}

	public static <R> R execute(String key, Callable<R> execute) throws MemcachedException, TimeoutException, Exception {
		boolean isLocked = false;
		if (key == null) {
			return execute.call();
		} else {
			try {
				isLocked = ServiceLocator.ml.tryLock(key, 2000); // 可以以PLAYER ID为锁的KEY，或自己能方便标识公共资源的KEY，该方法不会抛出任何异常
				if (isLocked) { // 加锁了便需要解锁
					return execute.call();
				} else {// 拿锁失败
					ServiceLocator.fileLog.warn("lock fail key=" + key);
					throw new Exception(key); // 在指定时间内拿不到锁，根据业务逻辑做相应处理
				}
			} finally {
				ServiceLocator.ml.unlock(key);// 无论如何要在这里解锁
			}
		}
	}

	//检测用户是否输入并通过二级密码验证
	protected boolean checkEnterSPW(int playerId) throws Exception {
		//判断二级密码模块是否启用
		String clientSwitch = ServiceLocator.getService.getSysConfig().get("client.switch");
		if (clientSwitch.length() < 3) {
			return true;
		}
		boolean needCheck = (clientSwitch.charAt(2)) == '1' ? true : false;
		if (needCheck) {
			Player player = getService.getPlayerById(playerId);
			Integer hasSecondPassword = player.getHasSecondPassword();
			if (hasSecondPassword == null) {
				hasSecondPassword = 0;
			}
			if (hasSecondPassword != SecondPasswordStatus.UNSET) {
				Object resultObject = mcc.get(CacheUtil.sPlayerSPWIE(playerId));
				if (resultObject != null && (resultObject instanceof Integer)) {
					int hadEnter = (Integer) resultObject;

					if (hadEnter == 0) {
						return false;
					}
					return true;
				} else {
					mcc.set(CacheUtil.sPlayerSPWIE(playerId), Constants.CACHE_TIMEOUT_DAY, 0, Constants.CACHE_TIMEOUT);
					return false;
				}
			}
			return true;
		}

		return true;
	}

	/**
	 * 验证是否启动了资源争夺战
	 */
	protected boolean checkZYZDZOpen() throws Exception {
		String clientSwitch = ServiceLocator.getService.getSysConfig().get("client.switch");
		if (clientSwitch.length() < 4) {
			return false;
		}
		boolean isOpen = (clientSwitch.charAt(3)) == '1' ? true : false;
		return isOpen;
	}

	/**
	 * 验证是否启动了资源争夺战 挑战
	 */
	protected boolean checkZYZDZChallengeOpen() throws Exception {
		String clientSwitch = ServiceLocator.getService.getSysConfig().get("client.switch");
		if (clientSwitch.length() < 5) {
			return false;
		}
		boolean isOpen = (clientSwitch.charAt(4)) == '1' ? true : false;

		if (isOpen) {
			isOpen = getService.isChallengeZYZDZOn();
		}
		return isOpen;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		final BeanFactory context = ConfigurationUtil.beanFactory;
		mcc = (MemcachedClient) context.getBean("memcachedClient");
		getService = (GetService) context.getBean("getService");
		createService = (CreateService) context.getBean("createService");
		updateService = (UpdateService) context.getBean("updateService");
		deleteService = (DeleteService) context.getBean("deleteService");
		nosqlService = (NosqlService) context.getBean("nosqlService");
		messageService = (MessageService) context.getBean("messageService");
		soClient = (SocketClientNew) context.getBean("socketClientNew");
		resetService = (ResetService) context.getBean("resetService");
	}

	protected MemcachedClient mcc;
	protected GetService getService;
	protected CreateService createService;
	protected UpdateService updateService;
	protected DeleteService deleteService;
	protected NosqlService nosqlService;
	protected ResetService resetService;
	protected SocketClientNew soClient;
	protected MessageService messageService;
	protected TeamService teamService;

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}

	public void setGetService(GetService getService) {
		this.getService = getService;
	}

	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}

	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

	public void setDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}

	public void setNosqlService(NosqlService nosqlService) {
		this.nosqlService = nosqlService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public SocketClientNew getSoClient() {
		return soClient;
	}

	public void setSoClient(SocketClientNew soClient) {
		this.soClient = soClient;
	}

	public void setInfoLogger(InforLogger infoLogger) {
		this.infoLogger = infoLogger;
	}

	public InforLogger getInfoLogger() {
		return infoLogger;
	}

}
