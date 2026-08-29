/**
 * 
 */
package com.pearl.o2o.protocal.cm.requestBinaryRpc;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;
import com.pearl.o2o.protocal.pojo.SingleCharacterInfo;
import com.pearl.o2o.protocal.pojo.StageClearPlayerInfo;

/**
 * @author lifengyang
 * 
 */
public class s_stage_clear extends CM_RequestBinaryRPC {
	protected int isClear;
	private int rid;
	private int serverId;
	private int channelId;
	private int hostId;
	private int type;
	private int gameStart;
	private int gameEnd;
	private int levelId;
	private int winner;
	private int terroristScore;
	private int policeScore;
	private int mvpId;
	private int firstKill;
	private int firstDead;
	private int itemType;
	private StageClearPlayerInfo[] playerInfoListTeam;

	private final ChannelBuffer fillPlayerInfoListTeam(ChannelBuffer message) {
		message.writeInt(playerInfoListTeam.length);
		for (StageClearPlayerInfo playerInfo : playerInfoListTeam) {
			message.writeInt(playerInfo.getId());
			fillStringByLengthField(message, playerInfo.getName());
			message.writeByte(playerInfo.getSide());
			message.writeInt(playerInfo.getOnlineMinutes());
			message.writeInt(playerInfo.getOfflineMinutes());
			message.writeByte(playerInfo.getDecrNum());
			message.writeInt(playerInfo.getStart());
			message.writeInt(playerInfo.getEnd());
			message.writeInt(playerInfo.getScore());
			message.writeShort(playerInfo.getKill());
			message.writeShort(playerInfo.getDead());
			message.writeShort(playerInfo.getControlNum());
			message.writeShort(playerInfo.getRevengeNum());
			message.writeShort(playerInfo.getAssistNum());
			message.writeShort(playerInfo.getKnifeKill());
			message.writeInt(playerInfo.getMaxHeadshotNum());
			message.writeInt(playerInfo.getMaxHeadshotCharacter());
			message.writeInt(playerInfo.getMaxKillNum());
			message.writeInt(playerInfo.getMaxKillNumCharacter());
			message.writeInt(playerInfo.getMaxHealthNum());
			message.writeInt(playerInfo.getMaxHealthNumCharacter());
			message.writeInt(playerInfo.getMaxDamageNum());
			message.writeInt(playerInfo.getMaxDamageNumCharacter());
			message.writeInt(playerInfo.getMaxLiveTime());
			message.writeInt(playerInfo.getMaxLiveTimeCharacter());
			message.writeInt(playerInfo.getBulletNum());
			message.writeInt(playerInfo.getBottleHpNum());
			message.writeInt(playerInfo.getCharacterInfos().length);
			for (SingleCharacterInfo singleCharacterInfo : playerInfo.getCharacterInfos()) {
				message.writeInt(singleCharacterInfo.getCharacterId());
				message.writeShort(singleCharacterInfo.getKill());
				message.writeShort(singleCharacterInfo.getDead());
				message.writeShort(singleCharacterInfo.getControlNum());
				message.writeShort(singleCharacterInfo.getRevengeNum());
				message.writeShort(singleCharacterInfo.getAssistNum());
				message.writeShort(singleCharacterInfo.getGrenadeKill());
				message.writeShort(singleCharacterInfo.getKnifeKill());
				message.writeShort(singleCharacterInfo.getUsedCount());
				message.writeShort(singleCharacterInfo.getHeadshot());
				message.writeShort(singleCharacterInfo.getMaxDamage());
				message.writeShort(singleCharacterInfo.getBoostKill());
				message.writeShort(singleCharacterInfo.getSustainKill());
				message.writeShort(singleCharacterInfo.getHealthNum());
			}
			message.writeInt(playerInfo.getKillCharacters().length);
			for (int k = 0; k < playerInfo.getKillCharacters().length; k++) {
				message.writeInt(k);
				message.writeInt(playerInfo.getKillCharacters()[k]);
			}
		}
		return message;
	}

	@Override
	protected final void fillParam(ChannelBuffer message) {
		message.writeInt(rid);
		message.writeInt(serverId);
		message.writeInt(channelId);
		message.writeInt(hostId);
		message.writeByte(type);

		message.writeInt(gameStart);
		message.writeInt(gameEnd);
		message.writeInt(levelId);
		message.writeByte(winner);
		message.writeInt(terroristScore);
		message.writeInt(policeScore);
		message.writeInt(mvpId);
		message.writeInt(firstKill);
		message.writeInt(firstDead);
		message.writeInt(itemType);
		fillPlayerInfoListTeam(message);
	}

	private void mergePlayerInfoListTeam(ChannelBuffer buffer) {
		int playerArrayLength = buffer.readInt();
		playerInfoListTeam = new StageClearPlayerInfo[playerArrayLength];
		for (int i = 0; i < playerArrayLength; i++) {
			StageClearPlayerInfo playerInfo = new StageClearPlayerInfo();
			playerInfoListTeam[i] = playerInfo;

			int id = buffer.readInt();
			playerInfo.setId(id);

			String characterName = readStringByLengthField(buffer);
			playerInfo.setName(characterName);

			byte side = buffer.readByte();
			playerInfo.setSide(side);

			int onlineMinutes = buffer.readInt();
			playerInfo.setOnlineMinutes(onlineMinutes);

			int offlineMinutes = buffer.readInt();
			playerInfo.setOfflineMinutes(offlineMinutes);
			byte decrNum = buffer.readByte();
			playerInfo.setDecrNum(decrNum);
			int start = buffer.readInt();
			playerInfo.setStart(start);

			int end = buffer.readInt();
			playerInfo.setEnd(end);

			int score = buffer.readInt();
			playerInfo.setScore(score);

			short kill = buffer.readShort();
			playerInfo.setKill(kill);

			short dead = buffer.readShort();
			playerInfo.setDead(dead);

			short controlNum = buffer.readShort();
			playerInfo.setControlNum(controlNum);

			short revengeNum = buffer.readShort();
			playerInfo.setRevengeNum(revengeNum);

			short assistNum = buffer.readShort();
			playerInfo.setAssistNum(assistNum);

			short knifeKill = buffer.readShort();
			playerInfo.setKnifeKill(knifeKill);

			int maxHeadshotNum = buffer.readInt();
			playerInfo.setMaxHeadshotNum(maxHeadshotNum);

			int maxHeadshotCharacter = buffer.readInt();
			playerInfo.setMaxHeadshotCharacter(maxHeadshotCharacter);

			int maxKillNum = buffer.readInt();
			playerInfo.setMaxKillNum(maxKillNum);

			int maxKillNumCharacter = buffer.readInt();
			playerInfo.setMaxKillNumCharacter(maxKillNumCharacter);

			int maxHealthNum = buffer.readInt();
			playerInfo.setMaxHealthNum(maxHealthNum);

			int maxHealthNumCharacter = buffer.readInt();
			playerInfo.setMaxHealthNumCharacter(maxHealthNumCharacter);

			int maxDamageNum = buffer.readInt();
			playerInfo.setMaxDamageNum(maxDamageNum);

			int maxDamageNumCharacter = buffer.readInt();
			playerInfo.setMaxDamageNumCharacter(maxDamageNumCharacter);

			int maxLiveTime = buffer.readInt();
			playerInfo.setMaxLiveTime(maxLiveTime);

			int maxLiveTimeCharacter = buffer.readInt();
			playerInfo.setMaxLiveTimeCharacter(maxLiveTimeCharacter);

			short bulletNum = buffer.readShort();
			playerInfo.setBulletNum(bulletNum);

			short bottleHpNum = buffer.readShort();
			playerInfo.setBottleHpNum(bottleHpNum);

			int characterDatasize = buffer.readInt();

			SingleCharacterInfo[] singleCharacterInfos = new SingleCharacterInfo[characterDatasize];
			playerInfo.setCharacterInfos(singleCharacterInfos);

			for (int j = 0; j < characterDatasize; j++) {
				int characterId = buffer.readInt();
				int characterKill = buffer.readShort();
				int characterDead = buffer.readShort();
				int characterControlNum = buffer.readShort();
				int characterRevengeNum = buffer.readShort();
				int characterAssistNum = buffer.readShort();
				int characterGrenadeKill = buffer.readShort();
				int characterKnifeKill = buffer.readShort();
				int characterUsed = buffer.readShort();
				int characterHeadshot = buffer.readShort();
				int characterMaxDamage = buffer.readShort();
				int characterBoostKill = buffer.readShort();
				int characterSustainKill = buffer.readShort();
				int characterHealthNum = buffer.readInt();
				singleCharacterInfos[i] = new SingleCharacterInfo(characterId, characterKill, characterDead, characterControlNum, characterRevengeNum, characterAssistNum,
						characterKnifeKill, characterUsed, characterGrenadeKill, characterHeadshot, characterMaxDamage, characterBoostKill, characterSustainKill,
						characterHealthNum);
			}

			int killSize = buffer.readInt();

			int[] killCharacters = new int[killSize];
			playerInfo.setKillCharacters(killCharacters);

			for (int k = 0; k < killSize; k++) {
				int characterId = buffer.readInt();
				int num = buffer.readInt();
				killCharacters[characterId] = num;
			}
		}
	}

	@Override
	protected final void mergeParam(ChannelBuffer buffer) {
		rid = buffer.readInt();
		serverId = buffer.readInt();
		channelId = buffer.readInt();
		hostId = buffer.readInt();
		type = buffer.readByte();
		gameStart = buffer.readInt();
		gameEnd = buffer.readInt();
		levelId = buffer.readInt();
		winner = buffer.readByte();
		terroristScore = buffer.readInt();
		policeScore = buffer.readInt();
		mvpId = buffer.readInt();
		firstKill = buffer.readInt();
		firstDead = buffer.readInt();
		itemType = buffer.readByte();
		mergePlayerInfoListTeam(buffer);
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

}
