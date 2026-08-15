package com.pearl.fcw.lobby.pojo.columnDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.dao.WSysStrengthenAppendDao;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysStrengthenAppend;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyItemGunProperty;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemRareType;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EItemWId;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.fcw.utils.StringUtil;

/**
 * PlayerItem表的特殊字段描述
 */
public class WPlayerItemColumnDescriptor extends ColumnDescriptor<WPlayerItem> {

	@SuppressWarnings("unchecked")
	@Override
	public WPlayerItem get(WPlayerItem m) {
		if (null == m) {
			return m;
		}
		//FIXME gunPropertyN将废弃，以下为过渡期代码 start
		if (StringUtil.isEmpty(m.getGunProperty())) {
			Map<String, String> map = new HashMap<>();
			map.put("1", m.getGunProperty1());
			map.put("2", m.getGunProperty2());
			map.put("3", m.getGunProperty3());
			map.put("4", m.getGunProperty4());
			map.put("5", m.getGunProperty5());
			map.put("6", m.getGunProperty6());
			map.put("7", m.getGunProperty7());
			map.put("8", m.getGunProperty8());
			m.setGunProperty(JsonUtil.writeAsString(map));
		}
		//FIXME gunPropertyN将废弃，以下为过渡期代码 end
		try {
			Map<String, String> map = JsonUtil.readValue(m.getGunProperty(), Map.class);
			Map<String, ItemGunProperty> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> kv.getKey(), kv -> {
				return new ItemGunProperty(kv.getValue());
			}));
			m.getGunPropertyMap().putAll(mapAdd);
		} catch (Exception e) {
		}
		return m;
	}

	@Override
	public void set(WPlayerItem m) {
		Map<String, String> map = m.getGunPropertyMap().entrySet().stream().collect(Collectors.toMap(kv -> kv.getKey(), kv -> {
			return kv.getValue().toString();
		}));
		m.setGunProperty(JsonUtil.writeAsString(map));
	}

	/**
	 * 从SysStrengthenAppend表获取指定等级的强化属性
	 * @param wSysItem
	 * @param f
	 * @param playerItemLevel 玩家道具的强化级别
	 * @return 默认返回0
	 * @throws Exception
	 */
	public static Number getEnhanceProperty(WSysItem wSysItem, Function<WSysStrengthenAppend, Number> f, int playerItemLevel) throws Exception {
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
			return p.getLevel() == playerItemLevel && p.getType() == type;
		}).findFirst().map(f).orElse(0);
	}

	/**
	 * 刷新代理实例的numberParam属性
	 * @param pwPlayerItem
	 * @throws Exception
	 */
	public static void refreshNumberParamMap(ProxyWPlayerItem pwPlayerItem) throws Exception {
		WSysItemDao wSysItemDao = (WSysItemDao) CacheDao.getModelAndDaoMap().get(WSysItem.class);
		WSysItem wSysItem = wSysItemDao.findEntity(pwPlayerItem.itemId().get());

		//道具可强化的最大等级
		int maxEnhanceLevel = WSysConfigService.getSysItemEnhanceLevel().keySet().stream().max((p1, p2) -> p1 - p2).orElse(0);
		//维修花费
		if (pwPlayerItem.durable().get() < WSysConfigService.getPlayerItemDuration().getMaxDuration()) {
			int repairCost = Math.round((WSysConfigService.getPlayerItemDuration().getMaxDuration() - pwPlayerItem.durable().get()) * (wSysItem.getwFixPrice()));
			pwPlayerItem.numberParamMap().put(EItemNumberParam.repaireCost.toString(), new ParamObject<>(repairCost));
		} else {
			pwPlayerItem.numberParamMap().put(EItemNumberParam.repaireCost.toString(), new ParamObject<>(0));
		}
		//道具的星级造成属性提升的参数
		int rareType = WSysConfigService.getSysItemRareTypeRareLevel().entrySet().stream().filter(kv -> {
			return kv.getValue() <= wSysItem.getRareLevel();
		}).map(kv -> kv.getKey()).max((p1, p2) -> p1 - p2).orElse(EItemRareType.RARE_0.getNumber());
		int itemType = wSysItem.getType();
		double gstFactor = 1;//主要影响武器
		double gstFactorCostume = 1;//主要影响服装
		if (pwPlayerItem.gstLevel().get() > 0) {
			switch (EItemSubType.forNumber(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType())) {
			case WEAPON_MAIN:
				gstFactor = WSysConfigService.getPlayerItemGstFactor().get(rareType).get(pwPlayerItem.gstLevel().get()).getOrDefault(itemType, 0F);
				gstFactor /= 100.0;
				gstFactor += 1.0;
				break;
			case CHARACTER_SUIT:
			case PART_HAT:
			case PART_ADORNMENT:
				gstFactorCostume = WSysConfigService.getPlayerItemGstFactor().get(rareType).get(pwPlayerItem.gstLevel().get()).getOrDefault(itemType, 0F);
				gstFactorCostume *= WSysConfigService.getPlayerItemGstFactorItemSubType().getOrDefault(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType(), 0F);//道具子类型对星级造成的属性提升可能有影响
				gstFactorCostume /= 600.0;
				gstFactorCostume += 1.0;
				break;
			default:
				break;
			}
		}
		//提升攻击属性
		double damageOffset = 1 + getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue();
		double shootSpeedOffset = 1 + getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty2, pwPlayerItem.level().get()).doubleValue();
		double damangeOffsetNext = 1 + getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get() + 1).doubleValue();
		double shootSpeedOffsetNext = 1 + getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty2, pwPlayerItem.level().get() + 1).doubleValue();
		double damage = 0;
		double damageNext = 0;
		if (wSysItem.getType() == EItemType.WEAPON.getNumber()) {
			if (wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP != EItemSubType.WEAPON_CLOSE.getNumber()) {//远程武器
				if (wSysItem.getwDamage() != 0) {
					damage = (wSysItem.getwDamage() * damageOffset + 50) / 2;
					damageNext = damage;
					if (pwPlayerItem.level().lt(maxEnhanceLevel)) {//如果还能升级，则武器威力采用公式计算，否则维持上一级的威力
						damageNext = (wSysItem.getwDamage() * damangeOffsetNext + 50) / 2;
					}
				}
			} else {//近战武器
				if (wSysItem.getwStabHurt() != 0) {
					damage = (wSysItem.getwStabHurt() * damageOffset + 50) / 2;
					damageNext = damage;
					if (pwPlayerItem.level().get() < maxEnhanceLevel) {//如果还能升级，则武器威力采用公式计算，否则维持上一级的威力
						damageNext = (wSysItem.getwStabHurt() * damangeOffsetNext + 50) / 2;
					}
				}
			}
		}
		damage *= gstFactor;
		damageNext *= gstFactor;
		double shootSpeed = 0;
		double shootSpeedNext = 0;
		if (wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP != EItemSubType.WEAPON_CLOSE.getNumber()) {//远程武器
			if (wSysItem.getwFireTime() != 0) {
				shootSpeed = 10.0 / wSysItem.getwFireTime() * shootSpeedOffset;
				shootSpeedNext = shootSpeed;
				if (pwPlayerItem.level().lt(maxEnhanceLevel)) {
					shootSpeedNext = 10.0 / wSysItem.getwFireTime() * shootSpeedOffsetNext;
				}
			}
		} else if (wSysItem.getType() == EItemType.WEAPON.getNumber()) {//近战武器
			if (wSysItem.getwStabTime() != 0) {
				shootSpeed = 10.0 / wSysItem.getwStabTime() * shootSpeedOffset;
				shootSpeedNext = shootSpeed;
				if (pwPlayerItem.level().get() < maxEnhanceLevel) {
					shootSpeedNext = 10.0 / wSysItem.getwStabTime() * shootSpeedOffsetNext;
				}
			}
		}
		pwPlayerItem.numberParamMap().put(EItemNumberParam.damage.toString(), new ParamObject<>(damage));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.damageNext.toString(), new ParamObject<>(damageNext));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.shootSpeed.toString(), new ParamObject<>(shootSpeed));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.shootSpeedNext.toString(), new ParamObject<>(shootSpeedNext));
		//强化防御属性
		double cResistanceFire = 0;
		double cResistanceBlast = 0;
		double cResistanceBullet = 0;
		double cResistanceKnife = 0;
		double cBloodAdd = 0;
		double cResistanceFireNext = 0;
		double cResistanceBlastNext = 0;
		double cResistanceBulletNext = 0;
		double cResistanceKnifeNext = 0;
		double cBloodAddNext = 0;
		if (wSysItem.getIsStrengthen() > 0) {//可强化道具
			cResistanceFire = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceFire());
			cResistanceBlast = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceBlast());
			cResistanceBullet = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceBullet());
			cResistanceKnife = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceKnife());
			cBloodAdd = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty2, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcBloodAdd());
			cResistanceFireNext = cResistanceFire;
			cResistanceBlastNext = cResistanceBlast;
			cResistanceBulletNext = cResistanceBullet;
			cResistanceKnifeNext = cResistanceKnife;
			cBloodAddNext = cBloodAdd;
			if (pwPlayerItem.level().get() < maxEnhanceLevel) {//可强化道具仍可升级
				cResistanceFireNext = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get() + 1).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceFire());
				cResistanceBlastNext = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get() + 1).doubleValue() * 100
						* (1 + 0.01 * wSysItem.getcResistanceBlast());
				cResistanceBulletNext = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get() + 1).doubleValue() * 100
						* (1 + 0.01 * wSysItem.getcResistanceBullet());
				cResistanceKnifeNext = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get() + 1).doubleValue() * 100
						* (1 + 0.01 * wSysItem.getcResistanceKnife());
				cBloodAddNext = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty2, pwPlayerItem.level().get() + 1).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcBloodAdd());
			}
			cBloodAdd *= gstFactorCostume;
			cBloodAddNext *= gstFactorCostume;
		} else if ("Y".equals(pwPlayerItem.isDefault().get()) && pwPlayerItem.level().get() > 0) {//默认道具并且道具等级大于0
			cResistanceFire = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceFire());
			cResistanceBlast = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceBlast());
			cResistanceBullet = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceBullet());
			cResistanceKnife = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty1, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcResistanceKnife());
			cBloodAdd = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getProperty2, pwPlayerItem.level().get()).doubleValue() * 100 * (1 + 0.01 * wSysItem.getcBloodAdd());
			cResistanceFireNext = cResistanceFire;
			cResistanceBlastNext = cResistanceBlast;
			cResistanceBulletNext = cResistanceBullet;
			cResistanceKnifeNext = cResistanceKnife;
			cBloodAdd *= gstFactorCostume;
			cBloodAddNext = cBloodAdd;
		}
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceFire.toString(), new ParamObject<>(cResistanceFire));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceBlast.toString(), new ParamObject<>(cResistanceBlast));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceBullet.toString(), new ParamObject<>(cResistanceBullet));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceKnife.toString(), new ParamObject<>(cResistanceKnife));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cBloodAdd.toString(), new ParamObject<>(cBloodAdd));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceFireNext.toString(), new ParamObject<>(cResistanceFireNext));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceBlastNext.toString(), new ParamObject<>(cResistanceBlastNext));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceBulletNext.toString(), new ParamObject<>(cResistanceBulletNext));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cResistanceKnifeNext.toString(), new ParamObject<>(cResistanceKnifeNext));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.cBloodAddNext.toString(), new ParamObject<>(cBloodAddNext));
		//升级需要材料,货币
		int materialNeed = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getStreNum, pwPlayerItem.level().get() + 1).intValue();
		int gpNeed = getEnhanceProperty(wSysItem, WSysStrengthenAppend::getStreGr, pwPlayerItem.level().get() + 1).intValue();
		pwPlayerItem.numberParamMap().put(EItemNumberParam.materialNeed.toString(), new ParamObject<>(materialNeed));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.gpNeed.toString(), new ParamObject<>(gpNeed));
		//开孔与镶嵌
		int maxHoleCount = 0;
		int holeCount = 0;
		int slotCount = 0;
		Map<Integer, Integer> map = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().getOrDefault(wSysItem.getType(), new HashMap<>());
		int holeLevel = map.keySet().stream().filter(p -> p <= pwPlayerItem.level().get()).max((p1, p2) -> p1 - p2).orElse(-1);
		maxHoleCount = map.getOrDefault(holeLevel, 0);
		for (int i = 2; i < 8; i++) {
			ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
			if (null != pItemGunProperty && !pItemGunProperty.open().isNull()) {
				holeCount += pItemGunProperty.open().eq(0) ? 1 : 0;
				slotCount += pItemGunProperty.open().eq(1) ? 1 : 0;
			}
		}
		pwPlayerItem.numberParamMap().put(EItemNumberParam.maxHoleCount.toString(), new ParamObject<>(maxHoleCount));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.holeCount.toString(), new ParamObject<>(holeCount));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.slotCount.toString(), new ParamObject<>(slotCount));

		//fightNum，战斗力。必须在gunProperty和星级影响系数计算完成后才能执行该步骤
		double fightNum = 0;
		int mValue1 = 0;
		int mValue2 = 0;
		int mValue3 = 0;
		for (int i = 2; i < 8; i++) {
			ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
			if (null != pItemGunProperty && !pItemGunProperty.mValue().isNull()) {
				mValue1 += pItemGunProperty.mValue().eq(1) ? 1 : 0;
				mValue2 += pItemGunProperty.mValue().eq(2) ? 1 : 0;
				mValue3 += pItemGunProperty.mValue().eq(3) ? 1 : 0;
			}
		}
		if (wSysItem.getIsStrengthen() == 0 && ("N".equals(pwPlayerItem.isDefault().get()) || wSysItem.getwId() == EItemWId.WID_THROW.getNumber())) {
			//不可强化并且(默认道具或者手雷)，使用系统道具的fightNum
			fightNum = wSysItem.getNumberParamMap().get(EItemNumberParam.fightNum.toString()).doubleValue();
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNum.toString(), new ParamObject<>(fightNum));
		} else {
			Map<Integer, List<Float>> fightParamMap = WSysConfigService.getSysItemFightParam();
			List<Float> paramList = fightParamMap.get(wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP);
			if (null != paramList) {
				double tmp = 1.0 + mValue1 / paramList.get(4) + mValue2 / paramList.get(5) + mValue3 / paramList.get(6) * gstFactor;
				switch (EItemType.forNumber(wSysItem.getType())) {//服装类型的道具公式不同
				case COSTUME:
				case PART:
					fightNum = Math.pow(paramList.get(1), pwPlayerItem.level().get() + ((wSysItem.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
					fightNum = fightNum * gstFactorCostume - 1;
					fightNum *= paramList.get(0);
					break;
				default:
					fightNum = Math.pow(paramList.get(1), pwPlayerItem.level().get() + ((wSysItem.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
					fightNum *= paramList.get(0);
					break;
				}
			}
			fightNum = fightNum < 0 ? 0 : fightNum;
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNum.toString(), new ParamObject<>((int) fightNum));
		}
		if (wSysItem.getIsStrengthen() > 0) {//可强化则不使用系统道具的值
			double fightNumBase = 0.0;
			double fightNumAdd = 0.0;
			Map<Integer, List<Float>> fightParamMap = WSysConfigService.getSysItemFightParam();
			List<Float> paramList = fightParamMap.get(wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP);
			if (null != paramList) {
				fightNumBase = paramList.get(0) * Math.pow(paramList.get(1), (wSysItem.getRareLevel() - 1) / paramList.get(2) - paramList.get(3));
				double a = 0;
				double b = 0;
				a = Math.pow(paramList.get(1), pwPlayerItem.level().get()) * gstFactor - 1;
				b = mValue1 / paramList.get(4) + mValue2 / paramList.get(5) + mValue3 / paramList.get(6);
				if ((a + b) != 0) {
					fightNumAdd = (int) (a / (a + b) * (wSysItem.getNumberParamMap().get(EItemNumberParam.fightNum.toString()).intValue() - fightNumBase));// 强化增加的战斗力
				}
				double tmp = 1.0 + mValue1 / paramList.get(4) + mValue2 / paramList.get(5) + mValue3 / paramList.get(6) * gstFactor;
				fightNum = paramList.get(0) * Math.pow(paramList.get(1), pwPlayerItem.level().get() + ((wSysItem.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
			}
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumBase.toString(), new ParamObject<>((int) fightNumBase));
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumAdd.toString(), new ParamObject<>((int) fightNumAdd));
		} else {
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumBase.toString(), new ParamObject<>(fightNum));
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumAdd.toString(), new ParamObject<>(0));
		}
		if (wSysItem.getIsStrengthen() > 0 && pwPlayerItem.level().get() < maxEnhanceLevel) {
			double fightNumNext = fightNum;
			Map<Integer, List<Float>> fightParamMap = WSysConfigService.getSysItemFightParam();
			List<Float> paramList = fightParamMap.get(wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP);
			if (null != paramList) {
				double tmp = 1.0 + mValue1 / paramList.get(4) + mValue2 / paramList.get(5) + mValue3 / paramList.get(6) * gstFactor;
				switch (EItemType.forNumber(wSysItem.getType())) {//服装类型的道具公式不同
				case COSTUME:
				case PART:
					fightNumNext = Math.pow(paramList.get(1), pwPlayerItem.level().get() + 1 + ((wSysItem.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
					fightNumNext = fightNum * gstFactorCostume - 1;
					fightNumNext *= paramList.get(0);
					break;
				default:
					fightNumNext = Math.pow(paramList.get(1), pwPlayerItem.level().get() + 1 + ((wSysItem.getRareLevel() - 1) / paramList.get(2)) - paramList.get(3)) * tmp;
					fightNumNext *= paramList.get(0);
					break;
				}
			}
			fightNumNext = fightNumNext < 0 ? 0 : fightNumNext;
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumNext.toString(), new ParamObject<>(fightNumNext));
		} else {
			pwPlayerItem.numberParamMap().put(EItemNumberParam.fightNumNext.toString(), new ParamObject<>(fightNum));
		}
		pwPlayerItem.numberParamMap().put(EItemNumberParam.gstFactorCostume.toString(), new ParamObject<>(gstFactorCostume));

		//强化等级影响道具颜色
		EItemColor itemColor = EItemColor.forNumber(WSysConfigService.getPlayerItemLevelColor().entrySet().stream().filter(kv -> kv.getKey() <= pwPlayerItem.level().get())
				.max((kv1, kv2) -> kv1.getKey() - kv2.getKey()).map(kv -> kv.getValue()).orElse(EItemColor.TRANSPARENT.getNumber()));
		pwPlayerItem.numberParamMap().put(EItemNumberParam.color.toString(), new ParamObject<>(itemColor.getNumber()));
	}
}
