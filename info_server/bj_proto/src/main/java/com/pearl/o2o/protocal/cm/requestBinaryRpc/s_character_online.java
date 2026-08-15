/**
 * 
 */
package com.pearl.o2o.protocal.cm.requestBinaryRpc;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;

/**
 * @author lifengyang
 * 
 */
public class s_character_online extends CM_RequestBinaryRPC {

	private int uid;
	private String userName;
	private String ip;
	private String version;
	private String isXunleiVip;
	private String internetCafe;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(uid);
		fillStringByLengthField(message, userName);
		fillStringByLengthField(message, ip);
		fillStringByLengthField(message, version);
		fillStringByLengthField(message, isXunleiVip);
		fillStringByLengthField(message, internetCafe);
	}

	@Override
	protected void mergeParam(ChannelBuffer r) {
		uid = r.readInt();
		userName = readStringByLengthField(r).trim();
		ip = readStringByLengthField(r).trim();
		version = readStringByLengthField(r).trim();
		isXunleiVip = readStringByLengthField(r).trim();
		internetCafe = readStringByLengthField(r).trim();
	}

	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIsXunleiVip() {
		return isXunleiVip;
	}

	public void setIsXunleiVip(String isXunleiVip) {
		this.isXunleiVip = isXunleiVip;
	}

	public String getInternetCafe() {
		return internetCafe;
	}

	public void setInternetCafe(String internetCafe) {
		this.internetCafe = internetCafe;
	}

}
