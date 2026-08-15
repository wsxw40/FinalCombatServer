package com.snda.services.oa.client.test;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOComponent;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.bean.SDOItemOrder.PayType;
import com.snda.services.oa.client.callback.ItemAccountLockCallbackHandler;
import com.snda.services.oa.client.callback.ItemAccountUnlockCallbackHandler;
import com.snda.services.oa.client.callback.SDOItemAccountLockNotify;
import com.snda.services.oa.client.callback.SDOItemAccountUnlockNotify;
import com.snda.services.oa.client.callback.UserInfoAuthCallbackHandler;
import com.snda.services.oa.client.callback.UserInfoAuthenNotify;
import com.snda.services.oa.client.callback.UserLoginNotify;
import com.snda.services.oa.client.impl.ISDOMessage;

public class SDOComponentMock implements SDOComponent {

	@Override
	public boolean asynSendAccountUnlockRequest(SDOItemOrder order) {
		return true;
	}

	@Override
	public boolean asynSendAcountRollbackRequest(SDOItemOrder order) {
		return true;
	}

	@Override
	public String generateUniqueId() {
		return String.valueOf(new Random().nextInt(1000));
	}

	@Override
	public int getBalance(PlayerInfo playerInfo, PayType payType)
			throws Exception {
		return 0;
	}

	@Override
	public void registAccountLockHandler(ItemAccountLockCallbackHandler handler) {
	}

	@Override
	public void registAccountUnlockHandler(
			ItemAccountUnlockCallbackHandler handler) {
	}

	@Override
	public void registUserInfoAuthenHandler(UserInfoAuthCallbackHandler handler) {
	}

	@Override
	public SDOItemAccountLockNotify sendAccountLockRequest(SDOItemOrder order)
			throws TimeoutException, Exception {
		ISDOMessage mockMesage = new ISDOMessageMock();
		mockMesage.setValue("result", "0");
		mockMesage.setValue("balance", "100");
		return new SDOItemAccountLockNotify(mockMesage);
	}

	@Override
	public SDOItemAccountUnlockNotify sendAccountRollbackRequest(
			SDOItemOrder order) throws TimeoutException, Exception {
		ISDOMessage mockMesage = new ISDOMessageMock();
		mockMesage.setValue("result", "0");
		mockMesage.setValue("balance", "100");
		return new SDOItemAccountUnlockNotify(mockMesage);
	}

	@Override
	public SDOItemAccountUnlockNotify sendAccountUnlockRequest(
			SDOItemOrder order) throws TimeoutException, Exception {
		ISDOMessage mockMesage = new ISDOMessageMock();
		mockMesage.setValue("result", "0");
		mockMesage.setValue("balance", "100");
		return new SDOItemAccountUnlockNotify(mockMesage);
	}

	@Override
	public UserInfoAuthenNotify sendUserInfoAuthenRequest(PlayerInfo playerInfo)
			throws TimeoutException, Exception {
		ISDOMessage mockMesage = new ISDOMessageMock();
		mockMesage.setValue("result", "0");
		mockMesage.setValue("balance", "100");
		return new UserInfoAuthenNotify(mockMesage);
	}

	@Override
	public UserInfoAuthenNotify sendUserInfoAuthenRequest(
			PlayerInfo playerInfo, PayType payType) throws TimeoutException,
			Exception {
		ISDOMessage mockMesage = new ISDOMessageMock();
		mockMesage.setValue("result", "0");
		mockMesage.setValue("balance", "100");
		return new UserInfoAuthenNotify(mockMesage);
	}

	@Override
	public UserLoginNotify synUserAuthen(String token, String endpointIp)
			throws TimeoutException, Exception {
		ISDOMessage mockMessage = new ISDOMessageMock();
		mockMessage.setValue("result", "0");
		mockMessage.setValue("sndaID", "12345678");
		
		return new UserLoginNotify(mockMessage);
	}
}
