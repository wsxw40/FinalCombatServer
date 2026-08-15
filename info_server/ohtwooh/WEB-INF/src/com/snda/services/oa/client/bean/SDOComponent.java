package com.snda.services.oa.client.bean;

import java.util.concurrent.TimeoutException;

import com.snda.services.oa.client.callback.ItemAccountLockCallbackHandler;
import com.snda.services.oa.client.callback.ItemAccountUnlockCallbackHandler;
import com.snda.services.oa.client.callback.SDOItemAccountLockNotify;
import com.snda.services.oa.client.callback.SDOItemAccountUnlockNotify;
import com.snda.services.oa.client.callback.UserInfoAuthCallbackHandler;
import com.snda.services.oa.client.callback.UserInfoAuthenNotify;
import com.snda.services.oa.client.callback.UserLoginNotify;


/**
 * This component encapsulate all logical on communication layer 
 * 
 * @author Timon Zhang
 *
 */
public interface SDOComponent {
	
	public  UserLoginNotify synUserAuthen(String token, String endpointIp) throws TimeoutException, Exception;

	public  SDOItemAccountLockNotify sendAccountLockRequest(SDOItemOrder order) throws TimeoutException, Exception;

	public  UserInfoAuthenNotify sendUserInfoAuthenRequest(PlayerInfo playerInfo) throws TimeoutException, Exception;
	
	public 	UserInfoAuthenNotify sendUserInfoAuthenRequest(PlayerInfo playerInfo,SDOItemOrder.PayType payType) throws TimeoutException, Exception;
	
	public  boolean asynSendAccountUnlockRequest(SDOItemOrder order);

	public  boolean asynSendAcountRollbackRequest(SDOItemOrder order);

	public SDOItemAccountUnlockNotify sendAccountUnlockRequest(SDOItemOrder order) throws TimeoutException, Exception;
	
	public SDOItemAccountUnlockNotify sendAccountRollbackRequest(SDOItemOrder order) throws TimeoutException, Exception;
	
	public int getBalance(PlayerInfo playerInfo, SDOItemOrder.PayType payType) throws Exception;
	
	
	public 	void registAccountLockHandler(ItemAccountLockCallbackHandler handler);
	
	public 	void registAccountUnlockHandler(ItemAccountUnlockCallbackHandler handler);
	
	public 	void registUserInfoAuthenHandler(UserInfoAuthCallbackHandler handler);
	

	public  String generateUniqueId();
}