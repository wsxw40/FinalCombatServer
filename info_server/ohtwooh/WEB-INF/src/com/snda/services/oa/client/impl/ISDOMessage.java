package com.snda.services.oa.client.impl;


/**
 * A class warp the ISDOMsgHandler to manage the life-cycle
 * @author Timon Zhang
 */
public class ISDOMessage {
	private ISDOMsgHandler msg ;
	//memory address, ISDOMsg is a JNI object
	private final long lAddress;
	private final MsgType msgType;
	
	public enum MsgType{
		LOCK_REQUEST(0x4),LOCK_RESPONSE(0X80000004),
		UNLOCK_REQUEST(0X5),UNLOCK_RESPONSE(0X80000005),
		USERINFOAUTHEN_REQUEST(0x3),USERINFOAUTHEN_RESPONSE(0X80000003),
		//self definition
		USERAUTHEN_REQUEST(999),USERAUTHEN_RESPONSE(0X80000999);
		
		private int code;
		private MsgType(int code){
			this.code = code;
		}
		public int getCode(){
			return code;
		}
		
		public static MsgType getMsgTypeByCode(int code){
			for (MsgType msgType : MsgType.values()){
				if (msgType.getCode() == code){
					return msgType;
				}
			}
			return null;
		}
	}
	//REFACTOR : for test 
	protected ISDOMessage(){
		lAddress = -1;
		//just set a random test value
		msgType = MsgType.LOCK_REQUEST;
	}
	
	
	protected ISDOMessage(MsgType msgType){
		msg = new ISDOMsgHandler();
		this.lAddress =  msg.Initialize();
		if(lAddress == 0) {
			System.out.print("msg init failed");
			throw new RuntimeException("msg init failed");
		}
		this.msgType = msgType;
	}
	
	protected ISDOMessage(long lAddress, MsgType msgType){
		msg = new ISDOMsgHandler();
		this.lAddress =  lAddress;
		this.msgType = msgType;
	}
	
	public void setValue(String key, String value){
		if(value == null){
			msg.SetValue(getLAddress(), key, "");
		}else{
			msg.SetValue(lAddress, key, value);
		}
	}
	
	public String getValue(String key){
		return msg.GetValue(lAddress, key);
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public long getLAddress(){
		return lAddress;
	}
	
	public void unInitialize(){
		msg.UnInitialize(lAddress);
	}
}
