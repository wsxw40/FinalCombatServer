package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.gm.pojo.WSysRobot;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysRobotService;
import com.pearl.fcw.proto.enums.EGameType;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.pojo.BiochemicalCharacter;
import com.pearl.o2o.pojo.BossPojo;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class GetPlayer extends BaseServerServlet {
	private static final long serialVersionUID = -6638020940416102777L;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private WSysRobotService wSysRobotService;

	//if check the storage before entering game the performance is good
	//if enter game directly,must get storage info and must get five charater's pack
	@Override
	protected byte[] innerService(BinaryReader r) throws IOException, Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			int uId = r.readInt();
			int playerId = r.readInt();
			int robotId = r.readInt();//FCW 页游机器人
			int levelId = r.readInt();
			int characterId = r.readByte();
			int isKnifeBox = r.readByte();
			boolean isKnife = isKnifeBox == 1 ? true : false;
			if (characterId < 0 || characterId > 10 || playerId == 0 || playerId < 0 || levelId == 0 || levelId < 0) {
				throw new IllegalArgumentException("wrong character while get character");
			}
			//Write Player Info
			Player player = null;
			if (robotId > 0) {//FCW 页游机器人
				player = getRobot(playerId);
			} else {
				player = getService.getPlayerById(playerId);
			}

			out.write(BinaryUtil.toByta(uId));
			out.write(BinaryUtil.toByta(player.getName()));

			byte remainVoternum;
			if (getService.isFirstGameToday(playerId)) {
				boolean isVip = player.getIsVip() >= 1;
				remainVoternum = nosqlService.initPlayerRemainVoterNum(playerId, isVip);
			} else {
				remainVoternum = nosqlService.getPlayerRemainVoterNum(playerId);
			}
			List<PlayerItem> reliveCoinList = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
			int remainReliveCoinNum = 0;
			for (PlayerItem pi : reliveCoinList) {
				if (pi.getQuantity() != 0) {
					remainReliveCoinNum += pi.getQuantity();
				}
			}
			//剩余投票数
			out.write(BinaryUtil.toByta(remainVoternum));
			//剩余复活币数量
			out.write(BinaryUtil.toByta(remainReliveCoinNum));
			//玩家总战斗力
			out.write(BinaryUtil.toByta(player.getMaxFightNum()));
			//
			int mateRank = getService.getQW_MateRank(playerId);
			//FCW start
			mateRank++;//这里不知道为什么从0开始算了。。。
			out.write(BinaryUtil.toByta(mateRank));
			out.write(BinaryUtil.toByta(player.getPvalue()));
			//zlm2016-08-01-匹配-开始 输出p值
			out.write(BinaryUtil.toByta(player.getMvalueToRatio()));
			//zlm2016-08-01-匹配-结束
			//FCW end
			//
			out.write(BinaryUtil.toByta(player.getCharacterSize().byteValue()));
			//buff list
			List<PlayerItem> buffList = player.getBuffList();

			int buffListSize = buffList.size();

			//新手保护：30级以下增加一攻击，一防御两个隐藏buff

			final int player_rank = player.getRank();

			//			if(player_rank<31&&player_rank>0){
			buffListSize += 2;
			//			}

			out.write(BinaryUtil.toByta(buffListSize));
			for (PlayerItem pi : buffList) {
				SysItem sysItem = getService.getSysItemDao().getSystemItemById(pi.getItemId());
				float iValue = (Float.parseFloat(sysItem.getIValue()));
				out.write(BinaryUtil.toByta(sysItem.getIId().byteValue()));
				out.write(BinaryUtil.toByta(sysItem.getIBuffId().byteValue()));
				out.write(BinaryUtil.toByta(iValue));
			}
			float value = 0;
			if (player_rank < 31 && player_rank > 0) {
				double versionValue = Math.pow(Constants.HIDE_BUFF_FOR_NEWER[player_rank - 1], Double.parseDouble(ServiceLocator.getService.getSysConfig().get("newer.buff.version")) / 100);
				value = (float) (Math.pow(versionValue, 0.5) - 1) * 100;
			}
			out.write(BinaryUtil.toByta((byte) 1));
			out.write(BinaryUtil.toByta((byte) 81));
			out.write(BinaryUtil.toByta(value));

			out.write(BinaryUtil.toByta((byte) 1));
			out.write(BinaryUtil.toByta((byte) 82));
			out.write(BinaryUtil.toByta(value));

			//Write characters
			LevelModeInfo level = getService.getLevelModeInfoIncludeTeamLevel(levelId);
			if (level == null || level.getType() == EGameType.NEWTRAIN.getNumber()) {//新手关
				List<Character> characterList = new ArrayList<Character>();
				List<Integer> characterIds = new ArrayList<Integer>();
				characterIds = getService.getCharacterIdFromPlayerPack(playerId);
				if (characterIds.size() != 0) {
					List<Integer> sysCharacterIdList = WSysConfigService.getSysLevelNewTrainSysCharacterId();
					for (int i = 0; i < sysCharacterIdList.size(); i++) {
						Integer sysCharacterId = sysCharacterIdList.get(i);
						List<PlayerItem> costumeList = getService.getDefaultPackList(playerId, Constants.PACK_TYPE_C, sysCharacterId);
						List<PlayerItem> weaponList = getService.getDefaultPackList(playerId, Constants.PACK_TYPE_W, sysCharacterId);
						SysCharacter sysCharacter = getService.getSysCharacterById(sysCharacterId);
						Character character = new Character();
						character.setPlayerId(playerId);
						character.setSysCharacter(sysCharacter);
						character.setWeaponList(weaponList);
						character.setCostumeList(costumeList);
						character.putOnCostume();
						characterList.add(character);
					}
					out.write(BinaryUtil.toByta(characterList.size()));
					getService.checkPlayerDataWhenEnterGame(player, characterList, false);
					for (Character ch : characterList) {
						ch.putOnCostume();
						ch.writeByte(out, level.getType());
					}
				}
			} else if (level.getType() == Constants.GAMETYPE.BIOCHEMICAL.getValue()) {
				List<Character> characterList = getService.getCharacterListForGame(playerId, characterId, false, false);
				getService.checkPlayerDataWhenEnterGame(player, characterList, false);
				List<BiochemicalCharacter> biochemicalCharacters = getService.getBiochemicalCharacterList(playerId);

				out.write(BinaryUtil.toByta(characterList.size() + biochemicalCharacters.size()));
				for (Character ch : characterList) {
					ch.putOnCostume();
					ch.writeByte(out, level.getType());
				}
				for (BiochemicalCharacter ch : biochemicalCharacters) {
					ch.putOnCostume();
					ch.writeByte(out, level.getType(), playerId);
				}
			} else if (level.getType() == Constants.GAMETYPE.TEAMEDIT.getValue()) {
				out.write(BinaryUtil.toByta(1));

				SysCharacter sysCharacter = getService.getSysCharacterById(7);
				Character character = new Character();
				List<PlayerItem> costumeList = getService.getDefaultPackList(playerId, Constants.PACK_TYPE_C, 7);
				List<PlayerItem> weaponList = new ArrayList<PlayerItem>();

				PlayerItem putItem = new PlayerItem();
				putItem.setItemId(Constants.SYSTEM_PUT_WEAPON_FOR_ZYZDZ); //只带一把武器进游戏
				putItem.setId(0);
				putItem.setPlayerItemUnitType(1);
				weaponList.add(putItem);

				character.setPlayerId(playerId);
				character.setSysCharacter(sysCharacter);
				character.setWeaponList(weaponList);
				character.setCostumeList(costumeList);
				character.putOnCostume();
				character.writeByte(out, level.getType());

				PlayerTeam playerTeam = getService.getPlayerTeamByPlayerId(playerId);
				List<TeamItem> availableTT = getService.getAvailableTeamItems(playerTeam.getTeamId());
				Team team = getService.getSimpleTeamById(playerTeam.getTeamId());
				//TODO: mock data
				//				TeamItem mock1=new TeamItem();
				//				mock1.setItemId(5434);
				//				mock1.setQuantity(50);
				//				TeamItem mock2=new TeamItem();
				//				mock2.setItemId(5435);
				//				mock2.setQuantity(50);
				//				TeamItem mock3=new TeamItem();
				//				mock3.setItemId(5453);
				//				mock3.setQuantity(50);
				//				availableTT.add(mock1);
				//				availableTT.add(mock2);
				//				availableTT.add(mock3);
				//
				Constants.TEAMSPACELEVELCONSTANTS tsls = Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel());
				out.write(BinaryUtil.toByta(availableTT.size()));		//type size (but now is total count)
				for (TeamItem tt : availableTT) {
					if (tt != null) {
						SysItem si = getService.getSysItemByItemId(tt.getItemId());
						if (si != null) {
							//	out.write(BinaryUtil.toByta(tt.getId())); //
							out.write(BinaryUtil.toByta(si.getSubType() == 2 ? 1 : 0));					 //type  0-2客户端用于区别墙还是塔
							out.write(BinaryUtil.toByta(si.getSubType() - 1));			//subtype
							out.write(BinaryUtil.toByta(si.getId()));  //sysItem id
							out.write(BinaryUtil.toByta(tt.getShowQuantity()));  //count
							out.write(BinaryUtil.toByta(si.getTeamOccupyLength()));
							out.write(BinaryUtil.toByta(si.getTeamOccupyWidth()));
							out.write(BinaryUtil.toByta(si.getName()));
						}
					}

				}
				out.write(BinaryUtil.toByta(tsls.getMaxtakeInWall()));	//能摆放最大墙数
				out.write(BinaryUtil.toByta(tsls.getMaxtakeInTowerTower()));	//能摆放最大塔数

			} else {
				List<Character> characterList = getService.getCharacterListForGame(playerId, characterId, isKnife, false);
				getService.checkPlayerDataWhenEnterGame(player, characterList, isKnife);
				boolean flag = false; //是否带坦克
				if (level.getType() == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
					characterList = getService.getCharacterListForGame(playerId, characterId, isKnife, true);
					List<PlayerItem> tankItems = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ZYZDZ_TYPE, Constants.SPECIAL_ITEM_IIDS.TANK.getValue());
					if (tankItems != null && tankItems.size() > 0) {
						PlayerItem tankItem = tankItems.get(0);
						int tankCharacterId = tankItem.getSysItem().getWAccuracyTime().intValue();
						if ((tankCharacterId >= Constants.TEAM_ZYZDZ_TANK1_CHARACTER) && (tankCharacterId <= Constants.TEAM_ZYZDZ_TANK3_CHARACTER)) {
							BossPojo hero = getService.getBossPojo(tankCharacterId, 5442, 5387, tankItem);
							flag = true;
							out.write(BinaryUtil.toByta(characterList.size() + 1));
							for (Character ch : characterList) {
								ch.putOnCostume();
								ch.writeByte(out, level.getType());
							}
							hero.putOnCostume();
							hero.writeByte(out);
						}
					}
				}

				if (!flag) {
					out.write(BinaryUtil.toByta(characterList.size()));
					for (Character ch : characterList) {
						ch.putOnCostume();
						ch.writeByte(out, level.getType());
					}
				}
			}

			if (level.getType() == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
				//个人
				int[] iids = { Constants.SPECIAL_ITEM_IIDS.BLOOD_BOTTLE.getValue(), Constants.SPECIAL_ITEM_IIDS.BULLET_BOTTLE.getValue(), Constants.SPECIAL_ITEM_IIDS.TANK.getValue(),
						Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT.getValue(), Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_DEF.getValue(), Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_MOVE.getValue(),
						Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT_SP.getValue() };
				HashMap<Integer, List<PlayerItem>> sendItemMap = getService.getZYZDZPlayerItems(playerId, iids);

				out.write(BinaryUtil.toByta((short) sendItemMap.size()));
				Iterator<Integer> iterator = sendItemMap.keySet().iterator();
				while (iterator.hasNext()) {
					int key = iterator.next();
					List<PlayerItem> list = sendItemMap.get(key);
					out.write(BinaryUtil.toByta(((short) (list.get(0).getQuantityForZYZDZPersonal()))));
					list.get(0).writeResourceWarItem(out);
				}
			} else {
				out.write(BinaryUtil.toByta(0));
			}

			return out.toByteArray();
		} catch (Exception e) {
			log.warn("Error in GetPlayer: ", e);
			throw e;
		}
	}

	@Override
	protected String getLockKey(BinaryChannelBuffer in) throws Exception {
		in.readInt();
		Integer playerId = in.readInt();
		int robotId = in.readInt();//FCW 页游机器人
		if (robotId <= 0) {
			return CommonUtil.getLockKey(playerId);
		}
		return null;
	}

	private Player getRobot(final int playerId) throws Exception {
		Random random = new Random();
		List<WSysRobot> wRobotList = wSysRobotService.findList(null);
		String targetName = wRobotList.get(random.nextInt(wRobotList.size())).getName();
		//获取玩家可用的系统角色,随机角色作为机器人角色
		List<Character> characterList = getService.getSysCharacterListAll().stream().filter(p -> {
			return "Y".equals(p.getIsDefault()) && 1 == p.getIsEnable();
		}).map(p -> {
			try {
				return getService.getCharacterByCharacterId(playerId, p.getId());
			} catch (Exception e) {
			}
			return null;
		}).collect(Collectors.toList());
		//characterList.removeIf(null);
		int targetSysCharacterId = characterList.get(random.nextInt(characterList.size())).getSysCharacter().getId();
		//查找该玩家的最强角色战斗力，作为机器人角色战斗力判断的依据
		int baseFightNum = characterList.stream().map(p -> {
			try {
				return p.getFightNum();
			} catch (Exception e) {
				return 0;
			}
		}).max((p1, p2) -> p1 - p2).orElse(0);
		//大致推算机器人角色战斗力判断的依据在redis排行榜的位置索引（redis和info是异步操作，拿到的索引可能是脏数据）
		String key = NosqlKeyUtil.fightNumInRealTopByType(targetSysCharacterId + "");
		long count = nosqlService.getNosql().zCard(key);
		if (count == 1) {//排行榜中只有一个玩家，则使用该玩家作为机器人的依据
			Player targetPlayer = getService.getPlayerById(playerId);
			targetPlayer.setName(targetName);// 机器人名字替换
			targetPlayer.setSelectedCharacters(targetSysCharacterId + "");//机器人只使用一个角色
			return targetPlayer;
		}
		long index = count - nosqlService.getNosql().zCount(key, 0, -baseFightNum);
		//获取机器人参考的玩家
		int targetPlayerId = 0;
		int minFightNum = baseFightNum, maxFightNum = baseFightNum;
		while (true) {
			minFightNum -= 100;
			if (maxFightNum < baseFightNum + 1000) {
				maxFightNum += 100;
			}
			long count1 = nosqlService.getNosql().zCount(key, -maxFightNum, -baseFightNum + 1);
			long count2 = nosqlService.getNosql().zCount(key, -minFightNum, -baseFightNum);
			if (count1 + count2 > 0) {
				List<Integer> targetPlayerIds = nosqlService.getNosql().zRange(key, (int) (index - count1), (int) (index + count2)).stream().map(Integer::parseInt).collect(Collectors.toList());
				targetPlayerId = targetPlayerIds.get(random.nextInt(targetPlayerIds.size()));
				break;
			}
		}
		Player targetPlayer = getService.getPlayerById(targetPlayerId);
		targetPlayer.setName(targetName);// 机器人名字替换
		targetPlayer.setSelectedCharacters(targetSysCharacterId + "");//机器人只使用一个角色
		return targetPlayer;
	}

}
