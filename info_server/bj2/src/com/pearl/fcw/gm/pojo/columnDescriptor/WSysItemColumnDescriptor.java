package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.dao.WSysStrengthenAppendDao;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysStrengthenAppend;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.json.JsonItemSight;
import com.pearl.fcw.proto.enums.EItemCId;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EItemWId;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysItem表的特殊字段描述
 */
public class WSysItemColumnDescriptor extends ColumnDescriptor<WSysItem> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public WSysItem get(WSysItem m) {
		if (null == m) {
			return m;
		}

		try {
			//瞄准信息
			if (!StringUtil.isEmpty(m.getwSightInfo())) {
				List<JsonItemSight> list = Stream.of(m.getwSightInfo().split(";")).map(p -> {
					JsonItemSight json = new JsonItemSight();
					try {
						List<Float> tmpList = Stream.of(p.split(",")).map(Float::parseFloat).collect(Collectors.toList());
						json.setFov(tmpList.get(0));
						if (tmpList.size() > 1) {
							json.setMouseSensitivity(tmpList.get(1));
							json.setMoveSpeedAdd(tmpList.get(2));
							json.setRecoilReduceFactor(tmpList.get(3));
							json.setShootSpeedReduceFactor(tmpList.get(4));
						}
					} catch (Exception e) {
						logger.warn("sysItem id = " + m.getId(), e);
					}
					return json;
				}).collect(Collectors.toList());
				list.removeIf(p -> p.getFov() == 0);
				m.getwSightInfoList().clear();
				m.getwSightInfoList().addAll(list);
			}
			//items
			if (!StringUtil.isEmpty(m.getItems())) {
				if (!m.getItems().contains(";")) {//FIXME 商城礼包的分割规则不同，这里先排除
					m.getItemsList().addAll(Stream.of(m.getItems().split(",")).map(Integer::parseInt).collect(Collectors.toList()));
				}
			}
			//gunProperty
			EItemColor itemColor = EItemColor.TRANSPARENT;
			try {
				Map<String, String> map = JsonUtil.readValue(m.getGunProperty(), Map.class);
				Map<String, ItemGunProperty> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> kv.getKey(), kv -> {
					return new ItemGunProperty(kv.getValue());
				}));
				m.getGunPropertyMap().putAll(mapAdd);
			} catch (Exception e) {
			}
			if (m.getGunPropertyMap().getOrDefault("2", new ItemGunProperty()).getGunPropertyList().size() > 0) {
				itemColor = EItemColor.GREEND;
			}
			if (m.getGunPropertyMap().getOrDefault("3", new ItemGunProperty()).getGunPropertyList().size() > 0) {
				itemColor = EItemColor.BLUE;
			}
			if (m.getGunPropertyMap().getOrDefault("4", new ItemGunProperty()).getGunPropertyList().size() > 0) {
				itemColor = EItemColor.PURPLE;
			}
			if (m.getGunPropertyMap().getOrDefault("5", new ItemGunProperty()).getGunPropertyList().size() > 0) {
				itemColor = EItemColor.ORANGE;
			}
			//characterId
			if (!StringUtil.isEmpty(m.getCharacterId())) {
				m.getCharacterIdList().addAll(Stream.of(m.getCharacterId().split(",")).map(Integer::parseInt).collect(Collectors.toList()));
			}
			//numberParamMap//seq系统道具在玩家背包中的槽位号
			int seq = 0;
			switch (EItemType.forNumber(m.getType())) {
			case WEAPON:
				switch (EItemWId.forNumber(m.getwId())) {
				case WID_SUBMACHINE:
				case WID_MACHINE_GUN:
				case WID_JQT_GUN:
				case WID_RIFFLE:
				case WID_SNIPER_GUN:
				case WID_MINI_GUN:
				case WID_MEDITNEEDLE_GUN:
				case WID_FLAME_GUN:
				case WID_ROCKET_LUNCHER:
				case WID_BOW:
				case WID_AMMO_GRENADE:
				case WID_ZYZDZ_EDIT_DEFAULT:
					seq = 1;
					break;
				case WID_PISTOL:
				case WID_SHOT_GUN:
				case WID_CURE_GUN:
				case WID_SIGNAL_GUN:
					seq = 2;
					break;
				case WID_KNIFE:
					seq = 3;
					break;
				case WID_THROW:
				case WID_FLASH:
				case WID_SMOKE:
				case WID_WAR_DRUM:
				case WID_MILK:
				case WID_GLASS:
					seq = 4;
					break;
				default:
					break;
				}
				break;
			case COSTUME:
			case PART:
				switch (EItemCId.forNumber(m.getcId())) {
				case CID_1:
					seq = 1;
					break;
				case CID_2:
					seq = 2;
					break;
				case CID_3:
					seq = 3;
					break;
				default:
					break;
				}
			default:
				break;
			}
			m.getNumberParamMap().put(EItemNumberParam.seq.toString(), seq);
			//numberParamMap//fightNum系统道具战斗力。当fightNum字段不为null且不小于0时，返回值为fightNum字段；否则根据sysConfig表配置重新计算
			double fightNum = 0;
			if (null != m.getFightNum() && m.getFightNum() >= 0) {
				fightNum = m.getFightNum();
			}
			int mValue1 = 0;
			int mValue2 = 0;
			int mValue3 = 0;
			if (m.getIsStrengthen() == 0) {
				for (int i = 2; i < 8; i++) {
					ItemGunProperty itemGunProperty = m.getGunPropertyMap().get(i + "");
					if (null != itemGunProperty) {
						mValue3 += itemGunProperty.getGunPropertyList().size();
					}
				}
			}
			Map<Integer, List<Float>> fightParamMap = WSysConfigService.getSysItemFightParam();
			List<Float> paramList = fightParamMap.get(m.getSubType() + m.getType() * ItemSubType.SEP);
			if (null != paramList) {
				double tmp = 1.0 + mValue1 / paramList.get(4) + mValue2 / paramList.get(5) + mValue3 / paramList.get(6);
				fightNum = paramList.get(0) * Math.pow(paramList.get(1), m.getStrengthLevel() + ((m.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
			}
			fightNum = fightNum < 0 ? 0 : fightNum;
			m.getNumberParamMap().put(EItemNumberParam.fightNum.toString(), (int) fightNum);
			//numberParamMap//damage系统道具威力
			double damage = 0.0;
			if (m.getSubType() + m.getType() * ItemSubType.SEP != EItemSubType.WEAPON_CLOSE.getNumber()) {//非近战武器
				damage = m.getwDamage() == 0 ? 0 : (m.getwDamage() + 50) / 2;
			} else {
				damage = m.getwStabHurt() == 0 ? 0 : (m.getwStabHurt() + 50) / 2;
			}
			m.getNumberParamMap().put(EItemNumberParam.damage.toString(), damage);
			//numberParamMap//shootSpeed系统道具射速
			double shootSpeed = 0.0;
			if (m.getSubType() + m.getType() * ItemSubType.SEP != EItemSubType.WEAPON_CLOSE.getNumber()) {//非近战武器
				shootSpeed = m.getwFireTime() == 0 ? 0 : 10.0 / m.getwFireTime();
			} else {//近战武器
				shootSpeed = m.getwStabTime() == 0 ? 0 : 10.0 / m.getwStabTime();
			}
			m.getNumberParamMap().put(EItemNumberParam.shootSpeed.toString(), shootSpeed);
			//numberParamMap//color系统道具颜色
			if (m.getIsStrengthen() != 0) {//可强化物品的颜色有变化
				int level = WSysConfigService.getPlayerItemLevelColor().keySet().stream().filter(p -> p <= m.getStrengthLevel()).max((p1, p2) -> p1 - p2).orElse(0);
				if (level > 0) {
					itemColor = EItemColor.forNumber(WSysConfigService.getPlayerItemLevelColor().get(level));
				}
			}
			m.getNumberParamMap().put(EItemNumberParam.color.toString(), itemColor.getNumber());
			//resourceStable
			if (!StringUtil.isEmpty(m.getResourceStable())) {
				String splitStr = ",";
				if (m.getResourceStable().contains(":")) {//蓝图分隔符
					splitStr = ":";
				}
				m.getResourceStableList().addAll(Stream.of(m.getResourceStable().split(splitStr)).collect(Collectors.toList()));
			}
			//resourceChangeable
			if (!StringUtil.isEmpty(m.getResourceChangeable())) {
				if (m.getResourceChangeable().contains("_")) {//蓝图分隔符
					List<List<String>> tmpList = Stream.of(m.getResourceChangeable().split("_")).map(p -> {
						return Arrays.asList(p);
					}).collect(Collectors.toList());
					m.getResourceChangeableList().addAll(tmpList);
				} else {
					List<List<String>> tmpList = Stream.of(m.getResourceChangeable().split(";")).map(p -> {
						return Stream.of(p.split(",")).collect(Collectors.toList());
					}).collect(Collectors.toList());
					m.getResourceChangeableList().addAll(tmpList);
				}
			}
			//下一级威力
			double damageNext = damage * (1 + getEnhanceProperty(m, WSysStrengthenAppend::getProperty1).doubleValue());
			m.getNumberParamMap().put(EItemNumberParam.damageNext.toString(), damageNext);
			//下一级射速增加
			double shootSpeedNext = shootSpeed * (1 + getEnhanceProperty(m, WSysStrengthenAppend::getProperty2).doubleValue());
			m.getNumberParamMap().put(EItemNumberParam.shootSpeedNext.toString(), shootSpeedNext);
		} catch (Exception e) {
			logger.warn("sysItem id = " + m.getId(), e);
		}
		return m;
	}

	@Override
	public void set(WSysItem m) {
	}

	/**
	 * 从SysStrengthenAppend表取系统道具当前等级需要的强化值，找不到匹配内容返回0
	 * @param wSysItem
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private Number getEnhanceProperty(WSysItem wSysItem, Function<WSysStrengthenAppend, Number> f) throws Exception {
		WSysStrengthenAppendDao wSysStrengthenAppendDao = (WSysStrengthenAppendDao) CacheDao.getModelAndDaoMap().get(WSysStrengthenAppend.class);
		return wSysStrengthenAppendDao.findList(null).stream().filter(p -> {
			int type = -1;
			switch (EItemSubType.forNumber(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType())) {
			case WEAPON_MAIN:
			case WEAPON_SUB:
			case WEAPON_CLOSE:
				type = 1;
				break;
			case CHARACTER_SUIT:
				type = 2;
				break;
			case PART_HAT:
				type = 3;
				break;
			case PART_ADORNMENT:
				type = 4;
			default:
				break;
			}
			return p.getLevel() == wSysItem.getLevel() && p.getType() == type;
		}).findFirst().map(f).orElse(0);
	}
}
