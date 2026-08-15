package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.proto.rpc.ResponseItemExpiredNotify;
import com.pearl.fcw.proto.rpc.ResponseStorageList;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.LobbyCompareUtil;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 玩家仓库和背包
 */
@Service
public class StorageService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysCharacterService wSysCharacterService;
	@Resource
	private WPaymentService wPaymentService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ItemService ItemService;
	@Resource
	private ProtoConverter protoConverter;
	@Resource
	private SocketClientNew socketClientNew;

	/**
	 * 获取玩家仓库
	 * @param playerId
	 * @param sysCharacterId
	 * @param itemType
	 * @param itemSubType EItemSubType%1000
	 * @param page 页码大于等于1
	 * @return
	 * @throws Exception
	 */
	public String getPlayerItemList(int playerId, int sysCharacterId, int itemType, int itemSubType, int page) throws Exception {
		//获取每页数据量
		int pageSize = WSysConfigService.getUiPage().getItemTypeAndPageSizeInShop().entrySet().stream().filter(kv -> kv.getKey() <= itemType).max((p1, p2) -> p1.getKey() - p2.getKey()).get()
				.getValue();
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		//过滤玩家道具
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		List<ProxyWPlayerItem> pwPlayerItemList = pwPlayerItemMap.values().stream().filter(pwPlayerItem -> {
			WSysItem wSysItem = wSysItemMap.get(pwPlayerItem.itemId().get());
			boolean b = wSysItem.getCharacterIdList().contains(sysCharacterId) && wSysItem.getType() == itemType;
			if (itemSubType > 0) {
				b = b && wSysItem.getSubType() == itemSubType;
			}
			return b;
		}).sorted(new LobbyCompareUtil(wSysItemMap).PROXY_W_PLAYER_ITEM_COMPARATOR).skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
		int pages = pwPlayerItemList.size() / pageSize;
		pages += pwPlayerItemList.size() % pageSize > 0 ? 1 : 0;
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseStorageList proto = protoConverter.responseStorageList(pwPlayer, pwPlayerInfo, pwPlayerItemMap, pwPlayerPackMap, pwPlayerBuffMap, page, pages);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 玩家将指定道具装备到某个角色上
	 * @param playerId
	 * @param sysCharacterId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void equip(int playerId, int sysCharacterId, int playerItemId) throws Exception {
		WSysCharacter wSysCharacter = wSysCharacterService.findEntity(sysCharacterId);
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).get(playerItemId);
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		//检查物品是否可装备
		if (wSysItem.getType() != EItemType.WEAPON.getNumber() && wSysItem.getType() != EItemType.COSTUME.getNumber() && wSysItem.getType() != EItemType.PART.getNumber()) {
			throw new Exception("itemType " + wSysItem.getType() + " can not be equiped at sysItemId " + wSysItem.getId());
		}
		int seq = wSysItem.getNumberParamMap().get(EItemNumberParam.seq.toString()).intValue();
		if (seq <= 0) {
			throw new Exception("seq " + seq + " can not be equiped at sysItemId " + wSysItem.getId());
		}
		//修改玩家仓库数据
		wPlayerService.updateProxyWPlayerPack(pwPlayer, pwPlayerItem, wSysCharacter, 1);
		if (pwPlayerItem.isBind().eq("N")) {//未绑定的玩家物品要绑定（装备过后的物品不可再转赠）
			pwPlayerItem.isBind().set("Y");
			Date now = new Date();
			pwPlayerItem.validTime().set(now);
			pwPlayerItem.expireTime().set(DateUtil.add(now, Calendar.SECOND, pwPlayerItem.leftSeconds().get()));
		}
	}

	/**
	 * 玩家卸载自己的道具
	 * @param playerId
	 * @param sysCharacterId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void upEquip(int playerId, int sysCharacterId, String packType, int seq) throws Exception {
		ProxyWPlayerPack pwPlayerPack = wPlayerService.findProxyWPlayerPackMap(playerId).values().stream().filter(p -> {
			return p.sysCharacterId().get() == sysCharacterId && packType.equals(p.type().toString()) && seq == p.seq().get();
		}).findFirst().orElse(null);
		if (null == pwPlayerPack) {
			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
		}
		WSysCharacter wSysCharacter = wSysCharacterService.findEntity(sysCharacterId);
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.get(pwPlayerPack.playerItemId().get());
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		//部分道具不可卸载
		if ("Y".equals(pwPlayerItem.isDefault().get())) {
			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
		}
		if (wSysItem.getType() == EItemType.WEAPON.getNumber() && 1 <= seq && seq <= 3) {
			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
		} else if (wSysItem.getType() == EItemType.COSTUME.getNumber()) {
			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
		}
		//在玩家背包中找到对应seq和类型的默认道具，替换玩家原有的装备
		ProxyWPlayerItem pwPlayerItem1 = pwPlayerItemMap.values().stream().filter(p -> {
			try {
				if ("Y".equals(p.isDefault()) && seq == wSysItemService.findEntity(p.itemId().get()).getNumberParamMap().get(EItemNumberParam.seq.toString()).intValue()) {
					return true;
				}
			} catch (Exception e) {
				logger.warn("upEquip findPlayerItem error : ", e);
			}
			return false;
		}).findAny().orElse(null);
		if (null == pwPlayerItem1) {
			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
		}
		wPlayerService.updateProxyWPlayerPack(pwPlayer, pwPlayerItem1, wSysCharacter, 1);
	}

	/**
	 * 玩家移除自己的物品
	 * @param playerId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void remove(int playerId, int playerItemId) throws Exception {
		ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).values().stream().filter(p -> p.id().get() == playerItemId).findFirst().get();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		//部分物品不能移除
		if (wSysItem.getType() == EItemType.OPEN.getNumber()) {
			switch (EItemIId.forNumber(wSysItem.getiId())) {
			case GIFT_BOX_LEVEL:
			case GIFT_BAG_UPDATE_FOR_FRESHMAN:
			case GIFT_BAG_COMEBACK_FOR_VETERAN:
				throw new BaseException(ExceptionMessage.NOT_DELETE_LEVEL_PACKAGE);
			default:
				break;
			}
		}
		wPlayerService.remove(pwPlayerItem);
	}

	/**
	 * 玩家修理自己的物品
	 * @param playerId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void fix(int playerId, int playerItemId) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).values().stream().filter(p -> p.id().get() == playerItemId).findFirst().get();
		WPayment wPayment = new WPayment();
		wPayment.setUnit(1);
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setCost(pwPlayerItem.numberParamMap().get(EItemNumberParam.repaireCost.toString()).get().getValue().intValue());
		wPlayerService.pay(pwPlayer, wPayment);
		pwPlayerItem.durable().set(WSysConfigService.getPlayerItemDuration().getMaxDuration());
	}

	/**
	 * 玩家道具续费
	 * @param playerId
	 * @param playerItemId
	 * @param paymentId
	 * @throws Exception
	 */
	public void renew(int playerId, int playerItemId, int paymentId) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).values().stream().filter(p -> p.id().get() == playerItemId).findFirst().get();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		WPayment wPayment = wPaymentService.findEntity(paymentId);
		wPlayerService.pay(pwPlayer, wPayment);
		ItemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
	}

	/**
	 * 玩家使用道具
	 * @param playerId
	 * @param playerItemId
	 * @param serverId
	 * @param channelId
	 * @param msg
	 * @throws Exception
	 */
	public void useItem(int playerId, int playerItemId, int serverId, int channelId, String msg) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).get(playerItemId);
		ItemService.usePlayerItem(pwPlayer, pwPlayerItem, serverId, channelId, msg);
	}

	/**
	 * 获取玩家已失效的道具（过期或者耐久度低于警戒值）
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public String getExpiredItems(int playerId) throws Exception {
		Date now = new Date();
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		//已绑定的过期道具
		Set<ProxyWPlayerItem> set1 = pwPlayerItemMap.values().stream().filter(pwPlayerItem -> {
			return "Y".equals(pwPlayerItem.isBind().get()) && DateUtil.after(now, pwPlayerItem.expireTime().get(), Calendar.MILLISECOND, 1);
		}).collect(Collectors.toSet());
		//消耗类型为永久且耐久度低于警戒值
		Set<ProxyWPlayerItem> set2 = pwPlayerItemMap.values().stream().filter(pwPlayerItem -> {
			return pwPlayerItem.durable().get() <= WSysConfigService.getPlayerItemDuration().getMaxDuration() * WSysConfigService.getPlayerItemDuration().getAlertDurationRatio();
		}).collect(Collectors.toSet());
		ResponseItemExpiredNotify proto = protoConverter.responseItemExpiredNotify(set1, set2);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 整理玩家背包（重新调整可堆叠道具的堆叠状况）
	 * @param playerId
	 * @throws Exception
	 */
	public void arrangeStorage(int playerId) throws Exception {
		Map<Integer, List<ProxyWPlayerItem>> map = new HashMap<>();
		wPlayerService.findProxyWPlayerItemMap(playerId).values().forEach(pwPlayerItem -> {
			if (!map.containsKey(pwPlayerItem.itemId().get())) {
				map.put(pwPlayerItem.itemId().get(), new ArrayList<>());
			}
		});
		for (Entry<Integer, List<ProxyWPlayerItem>> kv : map.entrySet()) {
			int sysItemId = kv.getKey();
			int totalQuantity = kv.getValue().stream().map(pwPlayerItem -> pwPlayerItem.quantity().get()).reduce((a, b) -> a + b).get();
			WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
			//系统物品类型为资源争夺战类，玩家物品数量堆叠上限Integer.max，否则为系统设置
			int maxStackSize = wSysItem.getType() == EItemType.BATTLE_FOR_RESOURCE.getNumber() ? Integer.MAX_VALUE : WSysConfigService.getPlayerItemMaxStackSize();
			for (int i = 0; i < kv.getValue().size(); i++) {
				//算出当前堆应有的数量
				int size = totalQuantity - i * maxStackSize;
				size = size > maxStackSize ? maxStackSize : size;
				//重新堆叠
				if (size > 0) {
					if (!kv.getValue().get(i).quantity().eq(size)) {
						kv.getValue().get(i).quantity().set(size);
					}
				} else {//堆叠数量小于等于0的逻辑删除数据
					wPlayerService.remove(kv.getValue().get(i));
				}
			}
		}
	}
}
