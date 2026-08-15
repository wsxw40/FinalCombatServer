/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 
 */
public class s_player_online extends SM_ResponseBinaryRPC {
	private int uid;
	private int playerId;
	private String playerName;
	private int playerRank;
	private int playerExp;
	private String playerProfile;
	private byte playerIsNew;
	private byte playerIsVip;
	private byte playerInternetCafe;
	private String playerIcon;
	private String exceptionMsg;

	public s_player_online(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		this.uid = buffer.readInt();
		this.playerId = buffer.readInt();
		this.playerName = readStringByLengthField(buffer);
		this.playerRank = buffer.readInt();
		this.playerExp = buffer.readInt();
		this.playerProfile = readStringByLengthField(buffer);
		readStringByLengthField(buffer);
		this.playerIsNew = buffer.readByte();
		this.playerIsVip = buffer.readByte();
		this.playerInternetCafe = buffer.readByte();
		buffer.readByte();
		this.playerIcon = readStringByLengthField(buffer);
//		buffer.readByte();
//		buffer.readInt();
//		buffer.readInt();
//		buffer.readFloat();
//		readStringByLengthField(buffer);
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_player_online [uid=" + uid + ", playerId=" + playerId + ", playerName=" + playerName + ", playerRank=" + playerRank + ", playerExp=" + playerExp
				+ ", playerProfile=" + playerProfile + ", playerIsNew=" + playerIsNew + ", playerIsVip=" + playerIsVip + ", playerInternetCafe=" + playerInternetCafe
				+ ", playerIcon=" + playerIcon + ", exceptionMsg=" + exceptionMsg + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", userData=" + Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength + "]";
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(int playerRank) {
		this.playerRank = playerRank;
	}

	public int getPlayerExp() {
		return playerExp;
	}

	public void setPlayerExp(int playerExp) {
		this.playerExp = playerExp;
	}

	public String getPlayerProfile() {
		return playerProfile;
	}

	public void setPlayerProfile(String playerProfile) {
		this.playerProfile = playerProfile;
	}

	public byte getPlayerIsNew() {
		return playerIsNew;
	}

	public void setPlayerIsNew(byte playerIsNew) {
		this.playerIsNew = playerIsNew;
	}

	public byte getPlayerIsVip() {
		return playerIsVip;
	}

	public void setPlayerIsVip(byte playerIsVip) {
		this.playerIsVip = playerIsVip;
	}

	public byte getPlayerInternetCafe() {
		return playerInternetCafe;
	}

	public void setPlayerInternetCafe(byte playerInternetCafe) {
		this.playerInternetCafe = playerInternetCafe;
	}

	public String getPlayerIcon() {
		return playerIcon;
	}

	public void setPlayerIcon(String playerIcon) {
		this.playerIcon = playerIcon;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
