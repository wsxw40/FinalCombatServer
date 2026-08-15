package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBasePoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBlastPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBoss;
import com.pearl.fcw.lobby.pojo.json.JsonLevelItemPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelSupplyPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicle;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicleLine;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysLevel表的特殊字段描述
 */
public class WSysLevelColumnDescriptor extends ColumnDescriptor<WSysLevel> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public WSysLevel get(WSysLevel m) {
		if (null == m) {
			return m;
		}
		//推车粗略信息？
		if (!StringUtil.isEmpty(m.getVehicleInfo())) {
			List<Float> list = Stream.of(m.getVehicleInfo().split(",")).map(Float::parseFloat).collect(Collectors.toList());
			JsonLevelVehicle json = new JsonLevelVehicle();
			json.setAddBlood(list.get(0).intValue());
			json.setX(list.get(1));
			json.setY(list.get(2));
			json.setZ(list.get(3));
			m.setVehicleInfoEntity(json);
		}
		//载具模型
		if (!StringUtil.isEmpty(m.getVehicleLineInfo())) {
			List<JsonLevelVehicleLine> list = Stream.of(m.getVehicleLineInfo().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelVehicleLine json = new JsonLevelVehicleLine();
				try {
					json.setLineId(tmpList.get(0).intValue());
					json.setIndex(tmpList.get(1).intValue());
					json.setX(tmpList.get(2));
					json.setY(tmpList.get(3));
					json.setZ(tmpList.get(4));
					json.setX1(tmpList.get(5));
					json.setY1(tmpList.get(6));
					json.setZ1(tmpList.get(7));
					json.setIsSlope(tmpList.get(8).intValue());
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).sorted((p1, p2) -> {
				int p = p1.getLineId() - p2.getLineId();
				if (p != 0) {
					return p;
				}
				return p1.getIndex() - p2.getIndex();
			}).collect(Collectors.toList());
			m.getVehicleLineInfoList().clear();
			m.getVehicleLineInfoList().addAll(list);
		}
		//出生点
		if (!StringUtil.isEmpty(m.getStartPoints())) {
			List<JsonLevelBasePoint> list = Stream.of(m.getStartPoints().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelBasePoint json = new JsonLevelBasePoint();
				try {
					json.setTeamId(tmpList.get(0).intValue());
					json.setX(tmpList.get(1));
					json.setY(tmpList.get(2));
					json.setZ(tmpList.get(3));
					json.setRotate(tmpList.get(4));
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getStartPointList().clear();
			m.getStartPointList().addAll(list);
		}
		//旗帜点
		if (!StringUtil.isEmpty(m.getFlagPoints())) {
			List<JsonLevelBasePoint> list = Stream.of(m.getFlagPoints().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelBasePoint json = new JsonLevelBasePoint();
				try {
					json.setTeamId(tmpList.get(0).intValue());
					json.setX(tmpList.get(1));
					json.setY(tmpList.get(2));
					json.setZ(tmpList.get(3));
					json.setRotate(tmpList.get(4));
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getFlagPointList().clear();
			m.getFlagPointList().addAll(list);
		}
		//金币掉落点
		if (!StringUtil.isEmpty(m.getRushGoldPoint())) {
			List<JsonLevelBasePoint> list = Stream.of(m.getRushGoldPoint().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelBasePoint json = new JsonLevelBasePoint();
				try {
					json.setX(tmpList.get(0));
					json.setY(tmpList.get(1));
					json.setZ(tmpList.get(2));
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getRushGoldPointList().clear();
			m.getRushGoldPointList().addAll(list);
		}
		//爆炸点
		if (!StringUtil.isEmpty(m.getBlastPoints())) {
			List<JsonLevelBlastPoint> list = Stream.of(m.getBlastPoints().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelBlastPoint json = new JsonLevelBlastPoint();
				try {
					json.setX(tmpList.get(0));
					json.setY(tmpList.get(1));
					json.setZ(tmpList.get(2));
					if (tmpList.size() > 3) {
						json.setRotate(tmpList.get(3));
						json.setX1(tmpList.get(4));
						json.setY1(tmpList.get(5));
						json.setZ1(tmpList.get(6));
					}
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getBlastPointList().clear();
			m.getBlastPointList().addAll(list);
		}
		//道具点
		if (!StringUtil.isEmpty(m.getWeaponPoints())) {
			List<JsonLevelItemPoint> list = Stream.of(m.getWeaponPoints().split(";")).map(p -> {
				List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
				JsonLevelItemPoint json = new JsonLevelItemPoint();
				try {
					json.setSysItemId(tmpList.get(0).intValue());
					json.setX(tmpList.get(1));
					json.setY(tmpList.get(2));
					json.setZ(tmpList.get(3));
					json.setGameType(tmpList.get(4).intValue());
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getWeaponPointList().clear();
			m.getWeaponPointList().addAll(list);
		}
		//补给点
		if (!StringUtil.isEmpty(m.getSupplies())) {
			List<JsonLevelSupplyPoint> list = Stream.of(m.getSupplies().split(";")).map(p -> {
				List<String> tmpList = Stream.of(p.split(",")).collect(Collectors.toList());
				JsonLevelSupplyPoint json = new JsonLevelSupplyPoint();
				try {
					json.setX(Float.parseFloat(tmpList.get(0)));
					json.setY(Float.parseFloat(tmpList.get(1)));
					json.setZ(Float.parseFloat(tmpList.get(2)));
					json.setType(Integer.parseInt(tmpList.get(3)));
					json.setName(tmpList.get(4));
					json.setValue(Integer.parseInt(tmpList.get(5)));
					json.setRandom(Float.parseFloat(tmpList.get(6)));
					json.setSkillTime(Float.parseFloat(tmpList.get(7)));
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			m.getSupplyList().clear();
			m.getSupplyList().addAll(list);
		}
		//boss及其装备以及掉落
		if (!StringUtil.isEmpty(m.getBoss())) {
			List<JsonLevelBoss> list = Stream.of(m.getBoss().split(";")).map(p -> {
				List<String> tmpList = Stream.of(p.split(",")).collect(Collectors.toList());
				JsonLevelBoss json = new JsonLevelBoss();
				try {
					json.setSysCharacterId(Integer.parseInt(tmpList.get(0)));
					json.setBossCount(Integer.parseInt(tmpList.get(1)));
					json.setEquipList(Stream.of(tmpList.get(2).split("\\|")).map(Integer::parseInt).collect(Collectors.toList()));
				} catch (Exception e) {
					logger.warn("sysLevel id = " + m.getId(), e);
				}
				return json;
			}).collect(Collectors.toList());
			if (!StringUtil.isEmpty(m.getBossDrop())) {
				Stream.of(m.getBossDrop().split(";")).forEach(p -> {
					List<String> tmpList = Stream.of(p.split(",")).collect(Collectors.toList());
					int sysCharacterId = Integer.parseInt(tmpList.get(0));
					JsonLevelBoss json = list.stream().filter(a -> a.getSysCharacterId() == sysCharacterId).findFirst().orElse(null);
					if (null != json) {
						json.setEquipList(Stream.of(tmpList.get(1).split("\\|")).map(Integer::parseInt).collect(Collectors.toList()));
					}
				});
			}
			m.getBossList().clear();
			m.getBossList().addAll(list);
		}

		return m;
	}

	@Override
	public void set(WSysLevel m) {
	}

}
