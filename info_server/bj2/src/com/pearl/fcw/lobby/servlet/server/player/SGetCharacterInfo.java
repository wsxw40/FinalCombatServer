package com.pearl.fcw.lobby.servlet.server.player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.gm.service.WSysLevelService;
import com.pearl.fcw.lobby.pojo.WCharacterData;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WCharacterDataColumnDescriptor;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WPlayerColumnDescriptor;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WPlayerItemColumnDescriptor;
import com.pearl.fcw.lobby.pojo.json.JsonNewerBuff;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.lobby.service.CharacterService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.proto.enums.EGameType;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EPlayerNumberParam;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 战斗获取获取玩家
 */
@Service("fcw_s_get_character_info")
public class SGetCharacterInfo extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = -672989462852689666L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private SGetCharacterInfo fcw_s_get_character_info;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private CharacterService characterService;
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysLevelService wSysLevelService;

	@Override
	protected byte[] innerService(BinaryReader in) throws IOException, Exception {
		try {
			return fcw_s_get_character_info.server(in);
		} catch (Exception e) {
			logger.error("fcw_s_get_character_info has error : ", e);
		}
		return Servletable.super.server(in);
	}

	@Override
	public byte[] server(BinaryReader in) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int uId = in.readInt();
		int playerId = in.readInt();
		int sysLevelId = in.readInt();
		int sysCharacterId = in.readByte();
		boolean isKnife = in.readByte() == 1 ? true : false;//是否强制刀战

		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		WSysLevel wSysLevel = wSysLevelService.findEntity(sysLevelId);
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWCharacterData> pwCharacterDataMap = wPlayerService.findProxyWCharacterDataMap(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		//FIXME 刷新一次不存在数据库内但是在缓存中的数据，保证进游戏后的数据正确性(为了效率可能不需要刷新)
		for (ProxyWPlayerItem p : pwPlayerItemMap.values()) {
			WPlayerItemColumnDescriptor.refreshNumberParamMap(p);
		}
		for (ProxyWCharacterData p : pwCharacterDataMap.values()) {
			WCharacterDataColumnDescriptor.refreshNumberParamMap(p, pwPlayerPackMap.values(), pwPlayerItemMap);
		}
		WPlayerColumnDescriptor.refreshNumberParamMap(pwPlayer, pwCharacterDataMap.values());
		int reviveCoinQuantity = pwPlayerItemMap.values().stream().filter(p -> {
			WSysItem wSysItem = wSysItemMap.get(p.itemId().get());
			return wSysItem.getType() == EItemType.ITEM.getNumber() && wSysItem.getiId() == EItemIId.REVIVE_COIN.getNumber();
		}).map(p -> p.quantity().get()).reduce((a, b) -> a + b).orElse(0);
		int fightNum = pwPlayer.numberParamMap().get(EPlayerNumberParam.playerFightNum.toString()).get().getValue().intValue();
		JsonPlayerInfoCaches jsonCache = pwPlayerInfo.cachesEntity().get();

		//=====玩家信息=====
		out.write(BinaryUtil.toBytas(uId, pwPlayer.name().get()));
		out.write(BinaryUtil.toByta(jsonCache.getVoterCountInGame()));//可投票数
		out.write(BinaryUtil.toByta(reviveCoinQuantity));//复活币数量
		out.write(BinaryUtil.toByta(fightNum));//玩家战斗力
		out.write(BinaryUtil.toByta(pwCharacterDataMap.values().size()));//FIXME 玩家角色数量（参战角色数量？）
		//buff。增加保护新手的buff
		JsonNewerBuff jsonNewerBuff = WSysConfigService.getNewerBuff();
		double newerIBuffValue = jsonNewerBuff.getPlayerLevelAndFactor().entrySet().stream().filter(kv -> kv.getKey() <= pwPlayer.rank().get()).max((kv1, kv2) -> kv1.getKey() - kv2.getKey())
				.map(kv -> kv.getValue()).orElse(1F);
		newerIBuffValue = Math.pow(newerIBuffValue, jsonNewerBuff.getIndex());
		newerIBuffValue = 100 * (Math.pow(newerIBuffValue, 0.5) - 1);
		out.write(BinaryUtil.toByta(pwPlayerBuffMap.values().size() + jsonNewerBuff.getiBuffIdList().size()));
		for (ProxyWPlayerBuff pwPlayerBuff : pwPlayerBuffMap.values()) {
			ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.get(pwPlayerBuff.playerItemId().get());
			WSysItem wSysItem = wSysItemMap.get(pwPlayerItem.itemId());
			out.write(BinaryUtil.toBytas(wSysItem.getiId().byteValue(), wSysItem.getiBuffId().byteValue(), Float.parseFloat(wSysItem.getiValue())));
		}
		for (int iBuffId : WSysConfigService.getNewerBuff().getiBuffIdList()) {
			out.write(BinaryUtil.toBytas(EItemIId.BUFF.getNumber(), iBuffId, (float) newerIBuffValue));
		}

		//=====角色信息=====
		if (null == wSysLevel || wSysLevel.getType() == EGameType.NEWTRAIN.getNumber()) {//新手关限定角色并使用默认装备
			Collection<Integer> sysCharacterIds = WSysConfigService.getSysLevelNewTrainSysCharacterId();//玩家可选角色
			out.write(BinaryUtil.toByta(sysCharacterIds.size()));
			for (int sysCharacterId1 : sysCharacterIds) {
				WCharacterData wCharacterData = pwCharacterDataMap.values().stream().filter(p -> p.characterId().get() == sysCharacterId1).findFirst().get().get();
				Map<Integer, WPlayerItem> equipMap = new HashMap<>();//Key:type*1000+subType，利用key的唯一性保证一个槽位号上只有一个装备
				pwPlayerItemMap.values().forEach(p -> {
					if (!p.isDefault().equals("Y")) {
						return;
					}
					WSysItem wSysItem = wSysItemMap.get(p.itemId().get());
					equipMap.put(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType(), p.get());
				});
				characterService.writeCharacterOutputStream(out, wCharacterData, equipMap.values());
			}
		} else if (null != wSysLevel && wSysLevel.getType() == EGameType.BIOCHEMICAL.getNumber()) {//FIXME 页游目前无该种类地图

		} else if (null != wSysLevel && wSysLevel.getType() == EGameType.TEAMEDIT.getNumber()) {//FIXME 页游目前无该种类地图

		} else if (null != wSysLevel) {
			Collection<Integer> sysCharacterIds = Stream.of(pwPlayer.selectedCharacters().get().split(",")).map(Integer::parseInt).collect(Collectors.toSet());//玩家可选角色
			out.write(BinaryUtil.toByta(sysCharacterIds.size()));
			for (int sysCharacterId1 : sysCharacterIds) {
				WCharacterData wCharacterData = pwCharacterDataMap.values().stream().filter(p -> p.characterId().get() == sysCharacterId1).findFirst().get().get();
				Set<WPlayerItem> equips = pwPlayerPackMap.values().stream().filter(p -> p.sysCharacterId().get() == sysCharacterId1).map(p -> {
					return pwPlayerItemMap.get(p.get()).get();
				}).collect(Collectors.toSet());
				characterService.writeCharacterOutputStream(out, wCharacterData, equips);
			}
		}

		if (wSysLevel.getType() == EGameType.TEAMZYZDZ.getNumber()) {//资源争夺战的争夺道具
		//			int[] iids = { Constants.SPECIAL_ITEM_IIDS.BLOOD_BOTTLE.getValue(), Constants.SPECIAL_ITEM_IIDS.BULLET_BOTTLE.getValue(), Constants.SPECIAL_ITEM_IIDS.TANK.getValue(),
		//					Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT.getValue(), Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_DEF.getValue(), Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_MOVE.getValue(),
		//					Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT_SP.getValue() };
		//			HashMap<Integer, List<PlayerItem>> sendItemMap = getService.getZYZDZPlayerItems(playerId, iids);
		//
		//			out.write(BinaryUtil.toByta((short) sendItemMap.size()));
		//			Iterator<Integer> iterator = sendItemMap.keySet().iterator();
		//			while (iterator.hasNext()) {
		//				int key = iterator.next();
		//				List<PlayerItem> list = sendItemMap.get(key);
		//				out.write(BinaryUtil.toByta(((short) (list.get(0).getQuantityForZYZDZPersonal()))));
		//				list.get(0).writeResourceWarItem(out);
		//			}
		} else {
			out.write(BinaryUtil.toByta(0));
		}

		return out.toByteArray();
	}

	@Override
	public String getLockedKey(BinaryReader reader) {
		try {
			reader.readInt();
			int playerId = reader.readInt();
			return playerId + "";
		} catch (IOException e) {
			return null;
		}
	}

}
