/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 完成
 */
public class s_character_online extends SM_ResponseBinaryRPC {
	public s_character_online(int userDataLength) {
		super(userDataLength);
	}

	private int uid;
	private int playerId;
	private String playerName;
	private int playerRank;
	private int playerExp;
	private String playerProfile;
	private byte playerIsNew;
	private byte playerIsVip;
	private byte playerInternetCafe;
	private byte flag;
	private String playerIcon;
	private byte isPlayerCheckToday;
	private int rankNumInTop;
	private int playerMaxFightNum;
	private float winRate;
	private String playerTeam;
	private String exceptionMsg;

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		this.uid = buffer.readInt();
		this.playerId = buffer.readInt();
		switch (playerId) {
		case -1: {
			this.exceptionMsg = readStringByLengthField(buffer);
			break;
		}
		case 0: {
			this.playerName = readStringByLengthField(buffer);
			this.playerRank = buffer.readInt();
			this.playerExp = buffer.readInt();
			this.playerProfile = readStringByLengthField(buffer);
			this.playerIsNew = buffer.readByte();
			this.playerIsVip = buffer.readByte();
			this.playerInternetCafe = buffer.readByte();
			this.playerIcon = readStringByLengthField(buffer);
			break;
		}
		default: {
			this.playerName = readStringByLengthField(buffer);
			this.playerRank = buffer.readInt();
			this.playerExp = buffer.readInt();
			this.playerProfile = readStringByLengthField(buffer);
			this.playerIsNew = buffer.readByte();
			this.playerIsVip = buffer.readByte();
			this.playerInternetCafe = buffer.readByte();
			this.flag = buffer.readByte();
			this.playerIcon = readStringByLengthField(buffer);
			this.isPlayerCheckToday = buffer.readByte();
			this.rankNumInTop = buffer.readInt();
			this.playerMaxFightNum = buffer.readInt();
			this.winRate = Float.intBitsToFloat(buffer.readInt());
			this.playerTeam = readStringByLengthField(buffer);
			break;
		}
		}

	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_character_online [uid=" + uid + ", playerId=" + playerId + ", playerName=" + playerName + ", playerRank=" + playerRank + ", playerExp=" + playerExp
				+ ", playerProfile=" + playerProfile + ", playerIsNew=" + playerIsNew + ", playerIsVip=" + playerIsVip + ", playerInternetCafe=" + playerInternetCafe + ", flag="
				+ flag + ", playerIcon=" + playerIcon + ", isPlayerCheckToday=" + isPlayerCheckToday + ", rankNumInTop=" + rankNumInTop + ", playerMaxFightNum="
				+ playerMaxFightNum + ", winRate=" + winRate + ", playerTeam=" + playerTeam + ", exceptionMsg=" + exceptionMsg + ", queueId=" + queueId + ", queueCurrentSize="
				+ queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
