package com.pearl.fcw.info.core.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;

public class Request {
	private byte 	type;
	private int  	rpcId;
	private String 	url;
	private byte[]  userData;
	private HashMap<String, String> paramMap;
	private byte[]  paramArray;
	private int queueId;

	/**
	 * @return the type
	 */
	public byte getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}
	/**
	 * @return the rpcId
	 */
	public int getRpcId() {
		return rpcId;
	}
	/**
	 * @param rpcId the rpcId to set
	 */
	public void setRpcId(int rpcId) {
		this.rpcId = rpcId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the userData
	 */
	public byte[] getUserData() {
		return userData;
	}
	/**
	 * @param userData the userData to set
	 */
	public void setUserData(byte[] userData) {
		this.userData = userData;
	}
	/**
	 * @return the paramMap
	 */
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(HashMap<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	/**
	 * @return the paramArray
	 */
	public byte[] getParamArray() {
		return paramArray;
	}
	/**
	 * @param paramArray the paramArray to set
	 */
	public void setParamArray(byte[] paramArray) {
		this.paramArray = paramArray;
	}

	public  byte[] generateByte() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(BinaryUtil.toByta((byte)getType()));
		out.write(BinaryUtil.toByta((int)0));
		out.write(BinaryUtil.toByta((int)0));
		out.write(BinaryUtil.toByta(getRpcId()));
		out.write(BinaryUtil.toByta(getUrl()));
		out.write(BinaryUtil.toByta(getUserData().length));
		out.write(getUserData());
		return out.toByteArray();
	}
    public int getQueueId() {
        return queueId;
    }
    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
	@Override
	public String toString() {
		return "Type:"+type + " RpcId:" + rpcId+ " Url:"+url +" Params:"+(paramMap!=null?paramMap.toString():null);
	}
}
