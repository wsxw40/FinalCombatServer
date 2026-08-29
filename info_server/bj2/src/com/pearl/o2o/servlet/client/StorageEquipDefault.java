package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 玩家装备全部更换为默认道具
 */
public class StorageEquipDefault extends BaseClientServlet {

	private static final long serialVersionUID = -9030583522569063480L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysItemService wSysItemService;
	private String[] paramNames = { "userId", "playerId", "sysCharacterId" };

	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[1]);
			int sysCharacterId = StringUtil.toInt(args[2]);
			int i = 1;

			List<PlayerItem> playerItemList = new ArrayList<>();
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.WEAPON.getNumber(), sysCharacterId, EItemSubType.WEAPON_MAIN.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.WEAPON.getNumber(), sysCharacterId, EItemSubType.WEAPON_SUB.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.WEAPON.getNumber(), sysCharacterId, EItemSubType.WEAPON_CLOSE.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.WEAPON.getNumber(), sysCharacterId, EItemSubType.WEAPON_SPECIAL.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.COSTUME.getNumber(), sysCharacterId, EItemSubType.CHARACTER_SUIT.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.PART.getNumber(), sysCharacterId, EItemSubType.PART_ADORNMENT.getNumber() % ItemSubType.SEP));
			playerItemList.addAll(getService.getPlayerItem_s1(playerId, EItemType.PART.getNumber(), sysCharacterId, EItemSubType.PART_HAT.getNumber() % ItemSubType.SEP));
			Map<Integer, WSysItem> sysItemMap = wSysItemService.findMap(null);
			for (PlayerPack playerPack : getService.getPlayerPackList(playerId).stream().filter(p -> p.getSysCharacterId() == sysCharacterId).collect(Collectors.toSet())) {
				int playerItemId = playerPack.getPlayerItemId();
				PlayerItem playerItem = playerItemList.stream().filter(p -> p.getId() == playerItemId).findFirst().orElse(null);
				if (null == playerItem || "N".equals(playerItem.getIsDefault())) {//该栏位不是默认道具，换成默认道具
					playerItem = playerItemList.stream().filter(p -> {
						if(!"Y".equals(p.getIsDefault())){
							return false;
						}
						WSysItem wSysItem = sysItemMap.get(p.getItemId());
						if (null == wSysItem) {
							return false;
						}
						if ("W".equals(playerPack.getType()) && wSysItem.getType() != EItemType.WEAPON.getNumber()) {
							return false;
						}
						if ("C".equals(playerPack.getType()) && wSysItem.getType() != EItemType.COSTUME.getNumber() && wSysItem.getType() != EItemType.PART.getNumber()) {
							return false;
						}
						boolean b = wSysItem.getNumberParamMap().get(EItemNumberParam.seq.toString()).intValue() == playerPack.getSeq();
						return b;
					}).findFirst().orElse(null);
					if (null == playerItem) {
						continue;
					}
					WSysItem wSysItem = sysItemMap.get(playerItem.getItemId());
					if ("W".equals(playerPack.getType())) {
						updateService.updateWeaponPackEquipment(playerId, (int) wSysItem.getType(), playerItem.getId(), sysCharacterId);
					} else if ("C".equals(playerPack.getType())) {
						updateService.updateCostumePackEquipment(playerId, (int) wSysItem.getType(), playerItem.getId(), sysCharacterId);
					}
				}
			}
			Player player = getService.getSimplePlayerById(playerId);
			soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			return Converter.createPackEquipment(null);
		} catch (Exception e) {
			logger.error("c_storage_equip_default has error : ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
