package com.pearl.fcw.lobby.pojo.columnDescriptor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.proto.enums.ECharacterDataNumberParam;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemSubType;

/**
 * CharacterData表的特殊字段描述
 */
public class WCharacterDataColumnDescriptor extends ColumnDescriptor<WCharacterData> {
	//    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WCharacterData get(WCharacterData m) {
        return m;
    }

    @Override
    public void set(WCharacterData m) {
    }

    /**
	 * 玩家角色更换装备时会导致一些不记录在数据库但是保留在缓存的中间数值变化。该方法用于刷新缓存的中间值
	 * @param pwCharacterData
	 * @param pwPlayerPacks 完整且排除逻辑不合理的玩家背包数据
	 * @param pwPlayerItemMap 完整且排除逻辑不合理的玩家物品数据
	 * @throws Exception
	 */
    public static void refreshNumberParamMap(ProxyWCharacterData pwCharacterData, Collection<ProxyWPlayerPack> pwPlayerPacks, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap)
            throws Exception {
        WSysItemDao wSysItemDao = (WSysItemDao) CacheDao.getModelAndDaoMap().get(WSysItem.class);
        Map<Integer, WSysItem> wSysItemMap = wSysItemDao.findMap(null);
        double fightNum = 0;
		double specialSiFightNum = 0;//特殊武器会将普通武器的战斗力之和做加成
		//武器战斗力累加
        Set<ProxyWPlayerItem> weaponSet = pwPlayerPacks.stream()
				.filter(pwPlayerPack -> "W".equalsIgnoreCase(pwPlayerPack.type().get()) && pwPlayerPack.sysCharacterId().get() == pwCharacterData.characterId().get())
                .map(pwPlayerPack -> pwPlayerItemMap.get(pwPlayerPack.playerItemId().get())).collect(Collectors.toSet());
        for (ProxyWPlayerItem pwPlayerItem : weaponSet) {
            WSysItem wSysItem = wSysItemMap.get(pwPlayerItem.itemId().get());
			if (wSysItem.getSubType() + wSysItem.getType() * ItemSubType.SEP == EItemSubType.WEAPON_SPECIAL.getNumber()) {//特殊武器
                specialSiFightNum = pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNum.toString()).get().getValue().doubleValue();
			} else {//普通武器
				fightNum += pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNum.toString()).get().getValue().floatValue()
                        * WSysConfigService.getCharacterFightNumItemSubType().get(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType());
            }
        }
        if (specialSiFightNum > 0) {
            fightNum = fightNum * (1 + specialSiFightNum * WSysConfigService.getCharacterFightNumItemSubType().get(EItemSubType.WEAPON_SPECIAL.getNumber()).doubleValue());
        }
		//套装和饰品战斗力累加
        Set<ProxyWPlayerItem> costumeSet = pwPlayerPacks.stream()
				.filter(pwPlayerPack -> "C".equalsIgnoreCase(pwPlayerPack.type().get()) && pwPlayerPack.sysCharacterId().get() == pwCharacterData.characterId().get())
                .map(pwPlayerPack -> pwPlayerItemMap.get(pwPlayerPack.playerItemId().get())).collect(Collectors.toSet());
        for (ProxyWPlayerItem pwPlayerItem : costumeSet) {
            WSysItem wSysItem = wSysItemMap.get(pwPlayerItem.itemId().get());
            fightNum += fightNum * pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNum.toString()).get().getValue().doubleValue()
                    * WSysConfigService.getCharacterFightNumItemSubType().get(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType());
        }
		pwCharacterData.numberParamMap().put(ECharacterDataNumberParam.characterFightNum.toString(), new ParamObject<>(fightNum));
    }
}
