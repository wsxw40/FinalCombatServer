package com.pearl.fcw.lobby.servlet.server.player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.gm.service.WSysLevelService;
import com.pearl.fcw.lobby.pojo.WCharacterData;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBasePoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBlastPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBoss;
import com.pearl.fcw.lobby.pojo.json.JsonLevelItemPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelSupplyPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicle;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicleLine;
import com.pearl.fcw.lobby.service.CharacterService;
import com.pearl.fcw.proto.enums.EGameType;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 战斗获取地图
 */
@Service("fcw_s_get_level_info")
public class SGetLevelInfo extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = -5864638924910120574L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private SGetLevelInfo fcw_s_get_level_info;
	@Resource
	private WSysLevelService WSysLevelService;
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private CharacterService characterService;

	@Override
	protected byte[] innerService(BinaryReader in) throws IOException, Exception {
		try {
			return fcw_s_get_level_info.server(in);
		} catch (Exception e) {
			logger.error("fcw_s_get_level_info has error : ", e);
		}
		return Servletable.super.server(in);
	}

	@Override
	public byte[] server(BinaryReader in) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		//=====客户端输入=====
		int sysLevelId = in.readInt();//系统地图ID
		int roomId = in.readInt();//房间ID
		int playerId = in.readInt();//当前玩家ID
		int serverId = in.readInt();
		int channelId = in.readInt();
		int isTeam = in.readByte();//是否战队战
		//		Set<Integer> bossCharacterIdSet = new HashSet<Integer>();
		int tmpCount = in.readInt();//临时计数的变量
		//获取房间内两队玩家的ID
		List<Integer> team0PlayerIdList1 = new ArrayList<>();
		List<Integer> team1PlayerIdList2 = new ArrayList<>();
		for (int i = 0; i < tmpCount; i++) {
			team0PlayerIdList1.add(in.readInt());
		}
		tmpCount = in.readInt();
		for (int i = 0; i < tmpCount; i++) {
			team1PlayerIdList2.add(in.readInt());
		}
		int roomCreatePlayerId = in.readInt();//创建房间的玩家ID
		int gameStartSecond = in.readInt();//对战开始时间，s？
		int gameType = in.readByte();//系统地图类型?
		int sysCharacterId = in.readByte();//当前玩家系统角色ID?

		//=====对客户端输出=====
		WSysLevel wSysLevel = WSysLevelService.findEntity(sysLevelId);
		//系统地图不存在
		if (null == wSysLevel) {
			out.write(BinaryUtil.toByta(0));
			return out.toByteArray();
		}
		out.write(BinaryUtil.toByta(wSysLevel.getId()));//系统地图ID
		out.write(BinaryUtil.toByta(wSysLevel.getType()));//系统地图类型//FIXME 匹配模式地图类型需要修改？
		out.write(BinaryUtil.toByta(wSysLevel.getName()));//地图名
		out.write(BinaryUtil.toByta(wSysLevel.getIsSelf()));//是否自带武器
		out.write(BinaryUtil.toByta(wSysLevel.getLevelHorizon()));//地图视野？
		out.write(BinaryUtil.toByta(wSysLevel.getTargetSpeed()));//地图移动速度
		//推车战局中车的位置信息？
		JsonLevelVehicle vehicleInfo = wSysLevel.getVehicleInfoEntity();
		if (vehicleInfo.getAddBlood() != 0) {
			out.write(BinaryUtil.toBytas(vehicleInfo.getAddBlood(), vehicleInfo.getX(), vehicleInfo.getY(), vehicleInfo.getZ()));
		} else {
			out.write(BinaryUtil.toByta(0));
		}
		//出生点
		List<JsonLevelBasePoint> startPointList = wSysLevel.getStartPointList();
		out.write(BinaryUtil.toByta(startPointList.size()));
		for (JsonLevelBasePoint p : startPointList) {
			out.write(BinaryUtil.toBytas(p.getTeamId(), p.getX(), p.getY(), p.getZ(), p.getRotate()));
		}
		//爆炸点
		if (wSysLevel.getType() == EGameType.TEAMEDIT.getNumber() || wSysLevel.getType() == EGameType.TEAMZYZDZ.getNumber()) {//资源争夺战及其编辑模式下不显示爆炸点
			out.write(BinaryUtil.toByta(0));
		} else {
			List<JsonLevelBlastPoint> blastPointList = wSysLevel.getBlastPointList();
			out.write(BinaryUtil.toByta(blastPointList.size()));
			for (JsonLevelBlastPoint p : blastPointList) {
				out.write(BinaryUtil.toBytas(p.getX(), p.getY(), p.getZ(), p.getRotate(), p.getX1(), p.getY1(), p.getZ1()));
			}
		}
		//载具信息
		switch (EGameType.forNumber(wSysLevel.getType())) {
		case DELIVER:
		case NEWTRAIN:
		case BOSS2:
			List<JsonLevelVehicleLine> vehicleLineList = wSysLevel.getVehicleLineInfoList();
			out.write(BinaryUtil.toByta(vehicleLineList.size()));
			for (JsonLevelVehicleLine p : vehicleLineList) {
				out.write(BinaryUtil.toBytas(p.getLineId(), p.getIndex(), p.getX(), p.getY(), p.getZ(), p.getX1(), p.getY1(), p.getZ1(), p.getIsSlope()));
			}
			break;
		default:
			out.write(BinaryUtil.toByta(0));
			break;
		}
		//旗帜点
		List<JsonLevelBasePoint> flagPointList = wSysLevel.getFlagPointList();
		out.write(BinaryUtil.toByta(flagPointList.size()));
		for (JsonLevelBasePoint p : flagPointList) {
			out.write(BinaryUtil.toBytas(p.getTeamId(), p.getX(), p.getY(), p.getZ(), p.getRotate()));
		}
		//道具点
		List<JsonLevelItemPoint> weaponList = wSysLevel.getWeaponPointList().stream().filter(p -> p.getGameType() == gameType).collect(Collectors.toList());//只需要游戏类型对应的道具
		out.write(BinaryUtil.toByta(weaponList.size()));
		for (JsonLevelItemPoint p : weaponList) {
			out.write(BinaryUtil.toBytas(p.getSysItemId(), p.getX(), p.getY(), p.getZ()));
		}
		//补给点
		List<JsonLevelSupplyPoint> supplyList = wSysLevel.getSupplyList();
		out.write(BinaryUtil.toByta(supplyList.size()));
		for (JsonLevelSupplyPoint p : supplyList) {
			out.write(BinaryUtil.toBytas(p.getX(), p.getY(), p.getZ(), p.getType(), p.getName(), p.getValue(), p.getRandom(), p.getSkillTime()));
		}
		out.write(BinaryUtil.toByta(wSysLevel.getBloodOffset()));//角色血量翻倍系数
		//金币掉落点
		out.write(BinaryUtil.toByta(wSysLevel.getIsRushGold()));//是否掉落金币
		List<JsonLevelBasePoint> rushGoldPointList = wSysLevel.getRushGoldPointList();
		out.write(BinaryUtil.toByta(supplyList.size()));
		for (JsonLevelBasePoint p : rushGoldPointList) {
			out.write(BinaryUtil.toBytas(p.getX(), p.getY(), p.getZ()));
		}
		out.write(BinaryUtil.toByta(wSysLevel.getIsMoneyReward()));//货币奖励？
		//Boss(以下的逻辑分支其实全可以合并到BOSS2，原编写者为了节省几个字节犯蠢，具体看XXX标记处)
		switch (EGameType.forNumber(wSysLevel.getType())) {
		case BOSS2:
			out.write(BinaryUtil.toByta(wSysLevel.getBossList().size()));
			for (JsonLevelBoss boss : wSysLevel.getBossList()) {
				WCharacterData wCharacterData = new WCharacterData();
				wCharacterData.setPlayerId(0);
				wCharacterData.setCharacterId(boss.getSysCharacterId());
				List<WPlayerItem> wPlayerItemList = wSysItemService.findList(null).stream().filter(p -> {
					return boss.getEquipList().contains(p.getId());
				}).map(p -> {
					WPlayerItem wPlayerItem = new WPlayerItem();
					wPlayerItem.setPlayerId(0);
					wPlayerItem.setId(0);
					wPlayerItem.setItemId(p.getId());
					return wPlayerItem;
				}).collect(Collectors.toList());
				if (boss.getBossCount() > 1) {//XXX 省每种boss的数量
					out.write(BinaryUtil.toByta(boss.getBossCount()));
				}
				characterService.writeCharacterOutputStream(out, wCharacterData, wPlayerItemList);
				if (!boss.getDropList().isEmpty()) {//XXX 省掉落数量和掉落
					out.write(BinaryUtil.toByta(boss.getDropList().size()));
					for (JsonLevelBoss.Drop drop : boss.getDropList()) {
						out.write(BinaryUtil.toBytas(drop.getwPayment().getItemId(), drop.getFactor1(), drop.getFactor2(), drop.getwPayment().getUnit()));
					}
				}
			}
			break;
		default:
			//XXX 省boss种类数量
			for (JsonLevelBoss boss : wSysLevel.getBossList()) {
				WCharacterData wCharacterData = new WCharacterData();
				wCharacterData.setPlayerId(0);
				wCharacterData.setCharacterId(boss.getSysCharacterId());
				List<WPlayerItem> wPlayerItemList = wSysItemService.findList(null).stream().filter(p -> {
					return boss.getEquipList().contains(p.getId());
				}).map(p -> {
					WPlayerItem wPlayerItem = new WPlayerItem();
					wPlayerItem.setPlayerId(0);
					wPlayerItem.setId(0);
					wPlayerItem.setItemId(p.getId());
					return wPlayerItem;
				}).collect(Collectors.toList());
				if (boss.getBossCount() > 1) {//XXX 省每种boss的数量
					out.write(BinaryUtil.toByta(boss.getBossCount()));
				}
				characterService.writeCharacterOutputStream(out, wCharacterData, wPlayerItemList);
				if (!boss.getDropList().isEmpty()) {//XXX 省掉落数量和掉落
					out.write(BinaryUtil.toByta(boss.getDropList().size()));
					for (JsonLevelBoss.Drop drop : boss.getDropList()) {
						out.write(BinaryUtil.toBytas(drop.getwPayment().getItemId(), drop.getFactor1(), drop.getFactor2(), drop.getwPayment().getUnit()));
					}
				}
			}
			break;
		}
		out.write(BinaryUtil.toByta((byte) 0));//?
		out.write(BinaryUtil.toByta(0));//这里有一段和战队有关系的数据，页游无战队，写0
		//死亡复活奖励道具
		switch (EGameType.forNumber(wSysLevel.getType())) {
		case BOSS2:
		case HITBOSS2:
		case TEAMZYZDZ:
			for(WSysItem wSysItem:wSysItemService.findList(null).stream().filter(p -> p.getType() == EItemType.ITEM.getNumber() && p.getiId() == EItemIId.BUFF_REVIVE.getNumber()).collect(Collectors.toSet())){
				out.write(BinaryUtil.toBytas(wSysItem.getwFireTime(), wSysItem.getwChangeInTime(), wSysItem.getwAmmoCount(), Integer.parseInt(wSysItem.getiValue()), wSysItem.getName(),
						wSysItem.getDescription(), Math.round(wSysItem.getwReloadTime())));
			}
			break;
		default:
			out.write(BinaryUtil.toByta(0));
			break;
		}

		return out.toByteArray();
	}
}
