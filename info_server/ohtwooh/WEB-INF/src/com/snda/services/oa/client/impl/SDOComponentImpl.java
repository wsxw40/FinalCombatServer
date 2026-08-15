package com.snda.services.oa.client.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.StringUtil;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOComponent;
import com.snda.services.oa.client.bean.SDOConstants;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.callback.ItemAccountLockCallbackHandler;
import com.snda.services.oa.client.callback.ItemAccountUnlockCallbackHandler;
import com.snda.services.oa.client.callback.SDOItemAccountLockNotify;
import com.snda.services.oa.client.callback.SDOItemAccountUnlockNotify;
import com.snda.services.oa.client.callback.UserInfoAuthCallbackHandler;
import com.snda.services.oa.client.callback.UserInfoAuthenNotify;
import com.snda.services.oa.client.callback.UserLoginCallbackHandler;
import com.snda.services.oa.client.callback.UserLoginNotify;

/**
 * Ensure this instance will be only one in all application
 * 
 * @author Timon Zhang
 */
public class SDOComponentImpl implements ItemAccountLockCallbackHandler, ItemAccountUnlockCallbackHandler, UserInfoAuthCallbackHandler, UserLoginCallbackHandler, SDOComponent {
	private final static Logger logger = Logger.getLogger(SDOComponentImpl.class);
	//retry times if local send fail
	private	int retry = 3;
	//interval between each time retry
	private int resend_interval = 300;
	// wait time out
	private int timeout = 2000;
	//the path of "sdoa4server.ini"
	private final String configFile; 
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	//this counter was only used when sending a user authen request to snda. use it as unique id to matche the request and response
	private static final AtomicLong counter = new AtomicLong(0);
	//the JNI class provided by SNDA
	private final ISDOBillingHandler billingHandler = new  ISDOBillingHandler();
	private final ISDOUserAuthenHandler authenHandler = new ISDOUserAuthenHandler();
	// call-back handler
	private final List<ItemAccountUnlockCallbackHandler> accountUnlockHandlers = new ArrayList<ItemAccountUnlockCallbackHandler>();
	private final List<ItemAccountLockCallbackHandler> accountlockHandlers = new ArrayList<ItemAccountLockCallbackHandler>();
	private final List<UserInfoAuthCallbackHandler> userInfoAuthHandlers = new ArrayList<UserInfoAuthCallbackHandler>();
	private final List<UserLoginCallbackHandler> userLoginHandlers = new ArrayList<UserLoginCallbackHandler>();
	
	// must use concurrentHashMap
	// key : orderId value : task
	private  final ConcurrentHashMap<String, Task<SDOItemAccountLockNotify>> lockQueue = new ConcurrentHashMap<String,Task<SDOItemAccountLockNotify>>();
	private  final ConcurrentHashMap<String, Task<UserInfoAuthenNotify>> userInfoQueue = new ConcurrentHashMap<String,Task<UserInfoAuthenNotify>>();
	private  final ConcurrentHashMap<Long, Task<UserLoginNotify>> userLoginQueue = new ConcurrentHashMap<Long,Task<UserLoginNotify>>();
	private  final ConcurrentHashMap<String, Task<SDOItemAccountUnlockNotify>> unlockQueue = new ConcurrentHashMap<String,Task<SDOItemAccountUnlockNotify>>();
	
	public SDOComponentImpl(){
		this(ConfigurationUtil.SDO_CONFIG_PATH);
	}
	
	
	public SDOComponentImpl(String configFile){
		this.configFile = configFile;
		try{
			logger.info("load config file " + configFile);
			init();
		}catch (Throwable e){
			logger.fatal("sdo component initialize failed " + e.getMessage());
			cleanup();
		}
	}
	
	/**
	 * ************************************* life-cycle *********************************************
	 */
	
	private void init(){
		if (configFile == null ) {
			throw new RuntimeException("config file is null!");
		}
		
		int nRet = billingHandler.Initialize(configFile, billingHandler);
		logger.debug("init billing, result code" + nRet);
		if(nRet != 0) {
			throw new RuntimeException("billing init failed, errorCode:" + nRet);
		}
		billingHandler.sdoComponent = this;
		
		nRet = authenHandler.Initialize(configFile, authenHandler);
		logger.debug("init authen, result code" + nRet);
		if(nRet != 0) {
			throw new RuntimeException("authen init failed, errorCode:" + nRet);
		}
		authenHandler.sdoComponent = this;
		
		Runtime.getRuntime().addShutdownHook( new Thread(new Runnable(){
				@Override
				public void run() {
					cleanup();
				};
		}));
		
		registAccountLockHandler(this);
		registAccountUnlockHandler(this);
		registUserInfoAuthenHandler(this);
		registUserLoginHandler(this);
		
		try {//billingHandler & authenHandler needs time to initialize
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		logger.info("SDO component init successfully");
	}

	public void cleanup(){
		logger.info("sdo component uninitialize ");

		if (billingHandler != null){
			billingHandler.UnInitialize();
		}
		
		if (authenHandler != null){
			authenHandler.UnInitialize();
		}
		logger.info("sdo component uninitialize successfully");
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.cleanup();
		super.finalize();
	}
	
	/**
	 * *************************************** receive *******************************************************/
	
	public void registAccountLockHandler(ItemAccountLockCallbackHandler handler){
		synchronized(accountlockHandlers){
			accountlockHandlers.add(handler);
		}
	}
	
	public void registAccountUnlockHandler(ItemAccountUnlockCallbackHandler handler){
		synchronized(accountUnlockHandlers){
			accountUnlockHandlers.add(handler);
		}
	}
	
	public void registUserInfoAuthenHandler(UserInfoAuthCallbackHandler handler){
		synchronized(userInfoAuthHandlers){
			userInfoAuthHandlers.add(handler);
		}
	}
	
	public void registUserLoginHandler(UserLoginCallbackHandler handler){
		synchronized(userLoginHandlers){
			userLoginHandlers.add(handler);
		}
	}
	
	@Override
	public void onAccountLockNotify(SDOItemAccountLockNotify notifyRequest) {
		logger.info("receive account lock response. contextId:" + notifyRequest.getContextId());
		Task<SDOItemAccountLockNotify> task =  lockQueue.get(notifyRequest.getOrderId());
		// re-send or time out will make task become null
		if (task != null){
			task.singnal(notifyRequest);
		}
	}
	
	@Override
	public void onAccountUnlockNotify(SDOItemAccountUnlockNotify notifyRequest) {
		logger.info("receive account unlock response. contextId:" + notifyRequest.getContextId());
		Task<SDOItemAccountUnlockNotify> task =  unlockQueue.get(notifyRequest.getOrderId());
		// re-send or time out will make task become null
		if (task != null){
			task.singnal(notifyRequest);
		}
	}
	
	
	@Override
	public void onUserInfoAuthenNotify(UserInfoAuthenNotify notifyRequest) {
		logger.info("receive user info authen response. uid:" + notifyRequest.getUserId());
		Task<UserInfoAuthenNotify> task =  userInfoQueue.get(notifyRequest.getOrderId());
		// re-send or time out will make task become null
		if (task != null){
			task.singnal(notifyRequest);
		} 
	}
	
	@Override
	public void onUserLoginNotify(UserLoginNotify notifyRequest) {
		logger.info("receive user login  response. Id:" + notifyRequest.getUniqueId());
		Task<UserLoginNotify> task =  userLoginQueue.get(Long.valueOf(notifyRequest.getUniqueId()));
		// re-send or time out will make task become null
		if (task != null){
			task.singnal(notifyRequest);
		}
	}
	
	public void OnSndaUserAuthenCallback(long lResMsg){
		ISDOMessage message = new ISDOMessage(lResMsg,ISDOMessage.MsgType.USERAUTHEN_RESPONSE);
		final UserLoginNotify notify = new UserLoginNotify(message);
		for (final UserLoginCallbackHandler handler : userLoginHandlers) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					handler.onUserLoginNotify(notify);
				}
			}).start();
		}
	}
	
	/**
	 * The entrance of all jni callback.
	 * The method will be called by ISDOBillingHandler and ISDOUserAuthenHandler, then it dispatch the response to all registed handler
	 * 
	 * @param nMsgType
	 * @param lResMsg
	 */
	protected void onBillingCallBack(int nMsgType, long lResMsg){
		ISDOMessage.MsgType msgType = ISDOMessage.MsgType.getMsgTypeByCode(nMsgType);
		if (msgType == null) {
			logger.error("receive un recognize msgType " + nMsgType + "not exists :");
			throw new RuntimeException("unknow msgType :" + msgType);
		}
		ISDOMessage message = new ISDOMessage(lResMsg,msgType);
		logger.debug("receive billing message from SDO, msgType:" + Integer.toHexString(nMsgType)  +  " id:" + message.getValue("orderId"));
		
		// as the message will be uninitialize after this method, so we should
		// copy the value from it before end of this method
		switch (msgType) {
			case UNLOCK_RESPONSE: 
					{
						final SDOItemAccountUnlockNotify request = new SDOItemAccountUnlockNotify(message);
						for (final ItemAccountUnlockCallbackHandler handler : accountUnlockHandlers) {
							new Thread(new Runnable(){
								@Override
								public void run() {
									handler.onAccountUnlockNotify(request);
								}
							}).start();
						}
					}
					break;
			
			case LOCK_RESPONSE:
			{
				final SDOItemAccountLockNotify request = new SDOItemAccountLockNotify(message);
				for (final ItemAccountLockCallbackHandler handler : accountlockHandlers) {
					new Thread(new Runnable(){
						@Override
						public void run() {
							handler.onAccountLockNotify(request);
						}
					}).start();
				}
			}
			break;
			
			case USERINFOAUTHEN_RESPONSE:
			{
					final UserInfoAuthenNotify request = new UserInfoAuthenNotify(message);
					for (final UserInfoAuthCallbackHandler handler : userInfoAuthHandlers) {
						new Thread(new Runnable(){
							@Override
							public void run() {
								handler.onUserInfoAuthenNotify(request);
							}
						}).start();
					}
			}
			break;
			
			default: 
			{	
					logger.error("no handler for " + msgType.name());
					throw new RuntimeException("no handler found for " + msgType.name());
			}
		}
	}
	
	
	/** ************************************ send ******************************************** */
	
	/**
	 * base send method, will generate orderId and sessionId if not set. It
	 * will also override the callTime field
	 * 
	 * @param msg
	 * @return
	 */
	private int baseAsynSend(ISDOMessage msg){
		// check if orderId and session Id has been set
		if (StringUtil.isEmptyString(msg.getValue("orderId"))) {
			String tempId = generateUniqueId();
			msg.setValue("orderId", tempId);
			msg.setValue("sessionId", tempId);
		}
		msg.setValue("callTime", new SimpleDateFormat(dateFormat).format(new Date()));
		logger.debug("billingHandler send message. Id:" + msg.getValue("orderId"));
		int result = billingHandler.SendRequest(msg.getMsgType().getCode(), msg.getLAddress());
		return result;
	}
	
	/**
	 * use base send, but will retry if send fail
	 * 
	 * @param msg
	 * @return
	 */
	private int baseAsynSendAndRetry(ISDOMessage msg){
			int result = baseAsynSend(msg);
			
			int retry = this.retry;
			while(needResend(result,msg) && retry >0 ){
				try {
					Thread.sleep(resend_interval);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				logger.debug("re-send message id: "  + msg.getValue("orderId"));
				result = baseAsynSend(msg);
				retry -- ;
			}
		return result;
	}
	
	
	/**
	 * after call "asyn-send", block the send thread, and put the id of
	 * message(orderId) into a concurrentHashMap. in call-back handler, notify
	 * the send thread via the same orderId. 
	 * Message Id shall be unique
	 * 
	 * @param msgQueue
	 *            must use concurrent container
	 * @param isRetry
	 *            re-send message if first time send fail
	 * @return T 
	 * 			  the response
	 */
	@SuppressWarnings("unchecked")
	private <T> T  synSend(ISDOMessage msg, ConcurrentHashMap<String,Task<T>> msgQueue, boolean isRetry) throws TimeoutException{
		Task<T> task = new Task<T>();
		String orderId = generateUniqueId();
		// override orderId, keep every message use unique orderId. this
		// orderId can be used to mapping the send thread and response
		msg.setValue("orderId", orderId);
		msg.setValue("sessionId", orderId);
		// orderId will be unique
		msgQueue.put(orderId, task);
		try {
			int sendResult = isRetry ? baseAsynSendAndRetry(msg): baseAsynSend(msg) ;
			if (sendResult <0){ // result <0 -> send fail
				logger.warn("send billing message fail. ErrorCode " + sendResult);
				return null;
			} 
			// wait for response, will be time out
			T result = task.await();
			return result;  
		} finally{
			msgQueue.remove(orderId);
		}
	}
	
	/**
	 * judge whether needs re-send
	 * @param result
	 * @param msg
	 * @return
	 */
	private boolean needResend(int result, ISDOMessage msg){
		if (result == -401005 || result == -401006 || result == -401008 || result == -402001 || result ==-401003 || result == -402005 ) {
			return true;
		}
		return false;
	}
	
	@Override
	public UserLoginNotify synUserAuthen(String token, String endpointIp) throws TimeoutException{
		Task<UserLoginNotify> task = new Task<UserLoginNotify>();
		//REFACTOR : avoid overflow
		long uniqueId = generateLongTypeUniqueId();
		userLoginQueue.put(uniqueId, task);
		try {
			//logger.debug("sdo sessionId " + token);
			int sendResult = authenHandler.ASyncGetUserInfo(token, endpointIp, uniqueId) ;
			if (sendResult < 0){ // <0
				logger.warn("send user authen request fail. ErrorCode " + sendResult);
				return null;
			} 
			// wait for response, will be time out
			UserLoginNotify result = task.await();
			return result;  
		} finally{
			userLoginQueue.remove(uniqueId);
		}
	}
		
	
	@Override
	public SDOItemAccountLockNotify sendAccountLockRequest(SDOItemOrder order) throws Exception{
		ISDOMessage msg = null;
		try{
			msg = generateAccountLockRequestMessage(order);
			logger.info("send lock request. contexId:" + msg.getValue("contextId") );
			return synSend(msg,this.lockQueue, false);
		}finally{
			if (msg != null){
				msg.unInitialize();
			}
		}
	}
	
	@Override
	public UserInfoAuthenNotify sendUserInfoAuthenRequest(PlayerInfo playerInfo, SDOItemOrder.PayType payType) throws Exception{
		ISDOMessage msg = null;
		try{
			msg = generateUserInfoAuthRequestMessage(playerInfo,payType);
			logger.info("send userInfoAuthen  request. uid:" + msg.getValue("userId"));
			return synSend(msg,this.userInfoQueue,false);
		}finally{
			if (msg != null){
				msg.unInitialize();
			}
		}
	}

	@Override
	public UserInfoAuthenNotify sendUserInfoAuthenRequest(PlayerInfo playerInfo) throws Exception{
		return sendUserInfoAuthenRequest(playerInfo,null);
	}
	
	@Override
	public int getBalance(PlayerInfo playerInfo, SDOItemOrder.PayType payType) throws Exception{
		UserInfoAuthenNotify resp = sendUserInfoAuthenRequest(playerInfo);
		return resp.getBalance(payType.getCode());
	}
	
	@Override
	public boolean asynSendAccountUnlockRequest(SDOItemOrder order){
		ISDOMessage message = null;
		try {
			message = generateAccountUnlockRequestMessage(order,1);
			logger.info("send account unlock request. contextId:" + message.getValue("contextId") );
			return baseAsynSendAndRetry(message) >= 0;
		} catch (Exception e) {
			logger.error(" account unlock request message error");
			return false;
		}finally{
			message.unInitialize();
		}
	}
	
	@Override
	public SDOItemAccountUnlockNotify sendAccountUnlockRequest(SDOItemOrder order) throws Exception{
		ISDOMessage msg = null;
		try{
			 msg = generateAccountUnlockRequestMessage(order,1);
			logger.info("send account unlock  request. contextId:" + msg.getValue("contextId") );
			return synSend(msg,this.unlockQueue,true);
		}finally{
			if (msg != null){
				msg.unInitialize();
			}
		}
	}
	@Override
	public SDOItemAccountUnlockNotify sendAccountRollbackRequest(SDOItemOrder order) throws Exception{
		ISDOMessage msg = null;
		try{
			 msg = generateAccountUnlockRequestMessage(order,2);
			logger.info("send account rollback request. contextId:" + msg.getValue("contextId") );
			return synSend(msg,this.unlockQueue,true);
		}finally{
			if (msg != null){
				msg.unInitialize();
			}
		}
	}
	
	
	@Override
	public boolean asynSendAcountRollbackRequest(SDOItemOrder order){
		ISDOMessage message = null;
		try {
			message = generateAccountUnlockRequestMessage(order,2);
			logger.info("send account rollback request. contextId:" + message.getValue("contextId") );
			return  baseAsynSendAndRetry(message) >=0;
		} catch (Exception e) {
			logger.error("account rollback request message error, id :" + order.getOrderId());
			return false;
		}finally{
			message.unInitialize();
		}
	}
	
	/** ******************************generate***************************************************** */ 
	
	private long generateLongTypeUniqueId(){
		return counter.getAndIncrement();
	}
	
	public  String generateUniqueId(){
		return billingHandler.GetUniqueId();
	}
	
	private ISDOMessage generateAccountLockRequestMessage(SDOItemOrder order) throws Exception{
		ISDOMessage msg = new ISDOMessage(ISDOMessage.MsgType.LOCK_REQUEST);
		try{
			msg.setValue("paytype", String.valueOf(order.getPayType().getCode()));
			// 17 means item deal
			msg.setValue("appType", "17");
			// 2 online
			msg.setValue("status", "2");
			msg.setValue("coupleType", "2");
			msg.setValue("fee", "0");
			msg.setValue("lockPeriod", "0");
			// user info
			msg.setValue("uidType", SDOConstants.UIDTYPE);
			msg.setValue("userId", order.getPlayerInfo().getSdoUid());
			msg.setValue("contextId", order.getOrderId());
			msg.setValue("appIdPlayer",SDOConstants.APPID);
			msg.setValue("areaIdPlayer", String.valueOf(order.getPlayerInfo().getAreaIdPlayer()));
			msg.setValue("groupIdPlayer", String.valueOf(order.getPlayerInfo().getGroupIdPlayer()));
			// 1：pc 2：手机
			msg.setValue("endpointType", "1");
			msg.setValue("endpointIp", order.getPlayerInfo().getEndpointIp());
			// 1：PC
			msg.setValue("platformType", "1");
			// 1，1表示 获得全部货币类型余额（松耦合）
			msg.setValue("indication", "1");
			int itemNums = order.getItemsPurchaseInfo().getItemNums();
			if (itemNums > 5){
				throw new Exception("itemNum can not bigger than 5");
			}
			msg.setValue("itemNum", String.valueOf(itemNums));
			// goodsInfo
			// TODO
			int count = 0;
			for (ItemsPurchaseInfo.ItemPurchaseEntry itemInfo : order.getItemsPurchaseInfo().getItemsInfo()){
				SysItem item = itemInfo.getItem();
				msg.setValue("goodsinfo["+count+"].id", String.valueOf(item.getId()));
				msg.setValue("goodsinfo["+count+"].number", String.valueOf(itemInfo.getQuantity()));
				msg.setValue("goodsinfo["+count+"].amount", String.valueOf(itemInfo.getAmount()));
				
				msg.setValue("goodsinfo["+count+"].appId", SDOConstants.APPID);
				msg.setValue("goodsinfo["+count+"].areaId", String.valueOf(order.getPlayerInfo().getAreaIdPlayer()));
				msg.setValue("goodsinfo["+count+"].groupId", String.valueOf(order.getPlayerInfo().getGroupIdPlayer()));
				msg.setValue("goodsinfo["+count+"].discount", "0");
				count ++;
			}
			return msg;
		}
		catch (Exception e){
			logger.warn("fail to create mssage for order:" + order.getOrderId() + " Exception is " + e);
			msg.unInitialize();
			throw e;
		}
	}
	
	/**
	 * 
	 * @param order
	 * @param confirm
	 *            1：确定 2：取消 3：强制扣费（不关心缓存、环形文件
	 * @return
	 * @throws Exception 
	 */
	private ISDOMessage generateAccountUnlockRequestMessage(SDOItemOrder order,int confirm) throws Exception {
		ISDOMessage msg = new ISDOMessage(ISDOMessage.MsgType.UNLOCK_REQUEST);
		try {
			msg.setValue("paytype", String.valueOf(order.getPayType().getCode()));
			msg.setValue("amount", String.valueOf(order.getTotalAmount()));
			msg.setValue("appType", "17");
			msg.setValue("status", "2");
			msg.setValue("coupleType", "2");
			msg.setValue("confirm", String.valueOf(confirm));
			msg.setValue("responsable", "1");
			
			msg.setValue("uidType", SDOConstants.UIDTYPE);
			msg.setValue("userId", String.valueOf(order.getPlayerInfo().getSdoUid()));
			msg.setValue("contextId", order.getOrderId());
			
			msg.setValue("appIdPlayer", SDOConstants.APPID);
			msg.setValue("areaIdPlayer", String.valueOf(order.getPlayerInfo().getAreaIdPlayer()));
			msg.setValue("groupIdPlayer", String.valueOf(order.getPlayerInfo().getGroupIdPlayer()));
			
			msg.setValue("endpointType", "1");
			msg.setValue("endpointIp", order.getPlayerInfo().getEndpointIp());
			msg.setValue("platformType", "1");
			msg.setValue("indication", "1");
			// TODO
			int itemNums = order.getItemsPurchaseInfo().getItemNums();
			if (itemNums > 5){
				throw new Exception("itemNum can not bigger than 5");
			}
			msg.setValue("itemNum", String.valueOf(itemNums));
			// goodsInfo
			int count = 0;
			for (ItemsPurchaseInfo.ItemPurchaseEntry itemInfo : order.getItemsPurchaseInfo().getItemsInfo()){
				String seq = "[" + count + "]";
				SysItem item = itemInfo.getItem();
				msg.setValue("goodsinfo"+seq+".id", String.valueOf(item.getId()));
				msg.setValue("goodsinfo"+seq+".number", String.valueOf(itemInfo.getQuantity()));
				msg.setValue("goodsinfo"+seq+".amount", String.valueOf(itemInfo.getAmount()));
				
				msg.setValue("goodsinfo"+seq+".appId",SDOConstants.APPID);
				msg.setValue("goodsinfo"+seq+".areaId", String.valueOf(order.getPlayerInfo().getAreaIdPlayer()));
				msg.setValue("goodsinfo"+seq+".groupId", String.valueOf(order.getPlayerInfo().getGroupIdPlayer()));
				msg.setValue("goodsinfo"+seq+".discount", "0");
				count ++;
			}
			return msg;
		} catch (Exception e) {
			logger.warn("fail to create mssage for order:" + order.getOrderId() + " Exception is " + e);
			msg.unInitialize();
			throw e;
		}
	}
	
	private ISDOMessage generateUserInfoAuthRequestMessage(PlayerInfo playerinfo, SDOItemOrder.PayType payType) throws Exception {
		ISDOMessage msg = new ISDOMessage(ISDOMessage.MsgType.USERINFOAUTHEN_REQUEST);
		try{
			//1：盛大用户 2：其他游戏平台用户
			msg.setValue("userType", "1");
			msg.setValue("uidType", SDOConstants.UIDTYPE);
			msg.setValue("userId", playerinfo.getSdoUid());
			
			msg.setValue("appIdPlayer", SDOConstants.APPID);
			msg.setValue("areaIdPlayer", String.valueOf(playerinfo.getAreaIdPlayer()));
			msg.setValue("groupIdPlayer", String.valueOf(playerinfo.getGroupIdPlayer()));
			
			msg.setValue("endpointIp", playerinfo.getEndpointIp());
			msg.setValue("platformType", "1");
			if (payType!=null){
				msg.setValue("indication", "2");
				msg.setValue("payType", String.valueOf(payType.getCode()));
			}else {
				msg.setValue("indication", "1");
				msg.setValue("payType", "0");
			}
			return msg;
		}
		catch(Exception e){
			logger.warn("fail to create mssage for User:" + playerinfo.getSdoUid() + " Exception is " + e);
			msg.unInitialize();
			throw e;
		}
	}
	
	
	public class Task<T>{ 
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		private T result;
		
		public T await() throws TimeoutException{
			lock.lock();
			try{
				// check if get result before starting wait
				if (result != null){
					return result;
				}
				boolean waitResult = condition.await(timeout,TimeUnit.MILLISECONDS);
				if (!waitResult && result == null) {// time out
					throw new TimeoutException();
				}
				return result;
			} catch (InterruptedException e) {
				return result;
			}finally{
				lock.unlock();
			}
		}
		
		public void singnal(T result){
			lock.lock();
			try{
				this.result = result;
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public T getResult() {
			return result;
		}
	}

	/** **************************** getter/setter************************ */
	
	  public String getConfigFile() {
		return configFile;
	}

	public List<ItemAccountUnlockCallbackHandler> getAccountUnlockHandlers() {
		return accountUnlockHandlers;
	}

	public List<ItemAccountLockCallbackHandler> getAccountlockHandlers() {
		return accountlockHandlers;
	}

	public List<UserInfoAuthCallbackHandler> getUserInfoAuthHandlers() {
		return userInfoAuthHandlers;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setResend_interval(int resend_interval) {
		this.resend_interval = resend_interval;
	}
}