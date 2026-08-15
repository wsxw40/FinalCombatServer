package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemPriceService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EItemIBuffId;
import com.pearl.fcw.proto.enums.EItemPriceType;
import com.pearl.fcw.proto.enums.EItemShoppingType;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EPayStatus;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.proto.rpc.ResponseCombineGetPrice;
import com.pearl.fcw.proto.rpc.ResponseDailyDiscountItem;
import com.pearl.fcw.proto.rpc.ResponseShopExchangeList;
import com.pearl.fcw.proto.rpc.ResponseShopReqBuy;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.LobbyCompareUtil;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 商店和交易相关
 */
@Service
public class ShopService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysCharacterService wSysCharacterService;
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WPaymentService wPaymentService;
	@Resource
	private WSysItemPriceService wSysItemPriceService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ItemService itemService;
	@Resource
	private SecondPasswordService secondPasswordService;
	@Resource
	private ProtoConverter protoConverter;

	/**
	 * 玩家获取每日折扣（系统道具ID，折扣率，是否已使用折扣）
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public String getDailyDiscount(int playerId) throws Exception {
		Map<Integer, List<Float>> discountMap = WSysConfigService.getSysItemDailyDiscount();
		Map<WSysItem, List<Float>> itemAndRatio = new HashMap<>();//道具和折扣率
		Map<WSysItem, List<Boolean>> itemAndFlag = new HashMap<>();//道具和是否已使用折扣
		for (Integer sysItemId : discountMap.keySet()) {
			WSysItem wSysItem = null;
			try {
				wSysItem = wSysItemService.findEntity(sysItemId);
			} catch (Exception e) {
				logger.warn("discount sysItemId " + sysItemId + " is not exits.");
				continue;
			}
			if (null == wSysItem || wSysItem.getIsRemoved()) {
				logger.warn("discount sysItemId " + sysItemId + " is not exits.");
				continue;
			}
			//从远程缓存获取指定系统道具的玩家的折扣使用标记
			List<Float> ratios = discountMap.get(sysItemId);
			//从playerInfo的proto字段中获取玩家的折扣使用状态
			ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
			JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
			//            ProtoDiscount protoDiscount = pwPlayerInfo.protoEntity().get().getDiscount();
			List<Boolean> flags = new ArrayList<Boolean>();
			if (!DateUtil.isSameDay(jsonCaches.getLastUseDiscountTime(), System.currentTimeMillis())) {//玩家上次折扣使用时间不是今天，或者扣使用记录不对，根据折扣率创建使用记录
				flags = ratios.stream().map(p -> {
					if (jsonCaches.getDiscountAndUseFlag().containsKey(p)) {
						return jsonCaches.getDiscountAndUseFlag().get(p) == 0 ? false : true;
					}
					return false;
				}).collect(Collectors.toList());
			} else {
				flags = ratios.stream().map(p -> false).collect(Collectors.toList());
			}

			itemAndRatio.put(wSysItem, ratios);
			itemAndFlag.put(wSysItem, flags);
		}

		ResponseDailyDiscountItem proto = protoConverter.responseDailyDiscountItem(itemAndRatio, itemAndFlag);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 玩家购买折扣道具
	 * @param playerId
	 * @param sysItemId
	 * @param index 使用该道具多种折扣率的索引
	 * @return
	 * @throws Exception
	 */
	public String payFromDailyDiscount(int playerId, int sysItemId, int index) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
		//系统道具有VIP等级的前提下，玩家VIP等级必须大于系统道具的VIP
		if (wSysItem.getIsVip() > 0 && pwPlayer.isVip().get() < wSysItem.getIsVip()) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
		}
		//从系统配置获取系统道具的折扣率
		List<Float> ratios = WSysConfigService.getSysItemDailyDiscount().getOrDefault(sysItemId, new ArrayList<>());
		//从远程缓存获取玩家对指定道具的折扣使用标记
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		//        ProtoDiscount protoDiscount = pwPlayerInfo.protoEntity().get().getDiscount();
		List<Boolean> flags = new ArrayList<>();
		if (!DateUtil.isSameDay(jsonCaches.getLastUseDiscountTime(), System.currentTimeMillis())) {//玩家上次折扣使用时间不是今天，或者扣使用记录不对，根据折扣率创建使用记录
			flags = ratios.stream().map(p -> {
				if (jsonCaches.getDiscountAndUseFlag().containsKey(p)) {
					return jsonCaches.getDiscountAndUseFlag().get(p) == 0 ? false : true;
				}
				return false;
			}).collect(Collectors.toList());
		} else {
			flags = ratios.stream().map(p -> false).collect(Collectors.toList());
		}
		if (index > ratios.size() - 1) {
			throw new Exception("discount index must be less than discount ratio count.");
		}
		if (flags.get(index)) {//玩家已使用指定的折扣
			throw new Exception("playerId " + playerId + " has use this discount index " + index + " at sysItemId " + sysItemId);
		}
		//扣除玩家货币
		WPayment wPayment = wPaymentService.findList(null).stream().filter(p -> p.getItemId() == sysItemId).sorted((p1, p2) -> p1.getPayType() - p2.getPayType()).findFirst().get();
		int cost = (int) (wPayment.getCost() * ratios.get(index));
		wPayment.setCost(cost / 10 * 10);//实际花费货币去掉个位数
		try {
			wPlayerService.pay(pwPlayer, wPayment);
		} catch (BaseException e) {
			return "result = " + EPayStatus.PAY_NOT_ENOUGH.getNumber();
		} catch (Exception e) {
			throw e;
		}
		//给玩家发送物品
		wPayment = new WPayment();
		wPayment.setUnit(1);
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		itemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
		//TODO 发送邮件
		//购买记录
		wPlayerService.updateProxyWBuyitemRecord(pwPlayer, wSysItem, wPayment);
		//记录折扣标记
		//        protoDiscount = protoDiscount.toBuilder().setFlag(index, 1).build();
		//        pwPlayerInfo.protoEntity().get().toBuilder().setDiscount(protoDiscount);
		jsonCaches.getDiscountAndUseFlag().put(ratios.get(index), 1);
		pwPlayerInfo.cachesEntity().set(jsonCaches);

		return "result = " + EPayStatus.PAY_SUCCESS.getNumber();
	}

	/**
	 * 玩家在普通商城内购买道具
	 * @param playerId
	 * @param sysItemId
	 * @param wPaymentId 玩家指定的购买方式
	 * @param sysCharacterId 玩家购买道具给的系统角色ID，只在装备道具时有用
	 * @param packId 玩家指定的背包ID（大于0表示立刻装备该物品）
	 * @return
	 * @throws Exception
	 */
	public String payInShop(int playerId, int sysItemId, int wPaymentId, int sysCharacterId, int packId) throws Exception {
		//二级密码
		if (!secondPasswordService.hasSecondPassword(playerId)) {
			return Smarty4jConverter.error(CommonMsg.INPUT_SECOND_PASSWORD);
		}
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
		WPayment wPayment = wPaymentService.findEntity(wPaymentId);
		List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getItemId() == sysItemId && p.getIsShow() > 0).collect(Collectors.toList());
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		//道具未上架或者价格未配置
		if (null == wSysItem || wSysItem.getShoppingType() == EItemShoppingType.UNSALE.getNumber() || wPaymentList.isEmpty()) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		}
		//限时销售已过期
		Date now = new Date();
		if (wSysItem.getShoppingType() == EItemShoppingType.FLASH_SALE.getNumber()) {
			if (DateUtil.after(now, wSysItem.getShoppingStartTime(), Calendar.MILLISECOND, 1) && DateUtil.before(now, wSysItem.getShoppingEndTime(), Calendar.MILLISECOND, 1)) {

			} else {
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			}
		}
		//资源争夺战物品检查
		if (wSysItem.getType() == EItemType.BATTLE_FOR_RESOURCE.getNumber()) {
			if (wSysItem.getSubType() == EItemSubType.BFR_PERSONAL_CONSUME.getNumber() % ItemSubType.SEP) {//资源争夺战的个人消耗需要验证购买冷却时间
				long lastValidTime = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == sysItemId && p.quantity().get() > 0).map(p -> p.validTime().getTime()).findFirst().orElse(0L);
				if (System.currentTimeMillis() - lastValidTime < wSysItem.getTimeForCreate() * 60 * 1000) {
					throw new BaseException(ExceptionMessage.BUY_ITEM_CD);
				}
			} else {//资源争夺战的其余物品不可购买
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			}
		} else {//非资源争夺战物品有购买等级限制
			if (pwPlayer.rank().get() < wSysItem.getLevel()) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_MATCH_LEVEL_BUY);
			}
		}
		//VIP道具购买限制
		if (wSysItem.getIsVip() >= 1 && pwPlayer.isVip().get() < wSysItem.getIsVip()) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
		}
		//网咖专购道具
		if (wSysItem.getIsWeb() > 0 && pwPlayerInfo.cachesEntity().get().getInternetCafe() == 0) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_WEB_BUY);
		}
		//月卡续费检查
		if (wSysItem.getiBuffId() == EItemIBuffId.CARD_MONTH.getNumber()) {
			ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == wSysItem.getId()).findFirst().orElse(null);
			//超期时间在60天后，不可再续费
			if (null != pwPlayerItem && DateUtil.after(pwPlayerItem.expireTime().get(), new Date(), Calendar.DAY_OF_YEAR, WSysConfigService.getLimitTimeThreshouldMap().get("maxDays").intValue())) {
				throw new BaseException(ExceptionMessage.NOT_RENEW);
			}
		}
		//扣除玩家货币
		wPlayerService.pay(pwPlayer, wPayment);
		//发送道具
		ProxyWPlayerItem pwPlayerItem = itemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
		//购买记录
		wPlayerService.updateProxyWBuyitemRecord(pwPlayer, wSysItem, wPayment);
		//装备道具
		if (packId > 0 && wSysItem.getType() == EItemType.WEAPON.getNumber()) {
			wPlayerService.updateProxyWPlayerPack(pwPlayer, pwPlayerItem, wSysCharacterService.findEntity(sysCharacterId), packId);
		}

		ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(pwPlayerItem, "", "", 1);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 玩家在普通商城内批量购买道具
	 * @param playerId
	 * @param sysItemIdAndPaymentIdMap 系统道具ID和支付方式ID的集合
	 * @return
	 * @throws Exception
	 */
	public String payInShop(int playerId, Map<Integer, Integer> sysItemIdAndPaymentIdMap) throws Exception {
		//二级密码
		if (!secondPasswordService.hasSecondPassword(playerId)) {
			return Smarty4jConverter.error(CommonMsg.INPUT_SECOND_PASSWORD);
		}
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		//检查总体扣费
		int costGp = 0;
		int costXunleiPoint = 0;
		for (Integer paymentId : sysItemIdAndPaymentIdMap.values()) {
			WPayment wPayment = wPaymentService.findEntity(paymentId);
			if (wPayment.getPayType() == EPayType.PAY_GP.getNumber()) {
				costGp += wPayment.getUnit() * wPayment.getCost();
			} else if (wPayment.getPayType() == EPayType.PAY_FC_POINT.getNumber()) {
				costXunleiPoint += wPayment.getUnit() * wPayment.getCost();
			}
		}
		if (pwPlayer.gPoint().get() < costGp && pwPlayerInfo.xunleiPoint().get() < costXunleiPoint) {
			ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, "", "", -3);
			return Smarty4jConverter.proto2Lua(proto);
		} else if (pwPlayerInfo.xunleiPoint().get() < costXunleiPoint) {
			ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, "", "", -2);
			return Smarty4jConverter.proto2Lua(proto);
		} else if (pwPlayer.gPoint().get() < costGp) {
			ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, "", "", -1);
			return Smarty4jConverter.proto2Lua(proto);
		}

		for (Entry<Integer, Integer> kv : sysItemIdAndPaymentIdMap.entrySet()) {
			int sysItemId = kv.getKey();
			int paymentId = kv.getValue();
			WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
			WPayment wPayment = wPaymentService.findEntity(paymentId);
			List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getItemId() == sysItemId && p.getIsShow() > 0).collect(Collectors.toList());
			Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);

			//道具未上架或者价格未配置
			if (null == wSysItem || wSysItem.getShoppingType() == EItemShoppingType.UNSALE.getNumber() || wPaymentList.isEmpty()) {
				throw new NotBuyEquipmentException(ExceptionMessage.BUY_FAIL);
			}
			//限时销售已过期
			Date now = new Date();
			if (wSysItem.getShoppingType() == EItemShoppingType.FLASH_SALE.getNumber()) {
				if (DateUtil.after(now, wSysItem.getShoppingStartTime(), Calendar.MILLISECOND, 1) && DateUtil.before(now, wSysItem.getShoppingEndTime(), Calendar.MILLISECOND, 1)) {

				} else {
					throw new BaseException(ExceptionMessage.BUY_FAIL);
				}
			}
			//资源争夺战物品检查
			if (wSysItem.getType() == EItemType.BATTLE_FOR_RESOURCE.getNumber()) {
				if (wSysItem.getSubType() == EItemSubType.BFR_PERSONAL_CONSUME.getNumber() % ItemSubType.SEP) {//资源争夺战的个人消耗需要验证购买冷却时间
					long lastValidTime = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == sysItemId && p.quantity().get() > 0).map(p -> p.validTime().getTime()).findFirst()
							.orElse(0L);
					if (System.currentTimeMillis() - lastValidTime < wSysItem.getTimeForCreate() * 60 * 1000) {
						throw new BaseException(ExceptionMessage.BUY_ITEM_CD);
					}
				} else {//资源争夺战的其余物品不可购买
					throw new BaseException(ExceptionMessage.BUY_FAIL);
				}
			} else {//非资源争夺战物品有购买等级限制
				if (pwPlayer.rank().get() < wSysItem.getLevel()) {
					throw new NotBuyEquipmentException(ExceptionMessage.NOT_MATCH_LEVEL_BUY);
				}
			}
			//VIP道具购买限制
			if (wSysItem.getIsVip() >= 1 && pwPlayer.isVip().get() < wSysItem.getIsVip()) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
			}
			//网咖专购道具
			if (wSysItem.getIsWeb() > 0 && pwPlayerInfo.cachesEntity().get().getInternetCafe() == 0) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_WEB_BUY);
			}
			//月卡续费检查
			if (wSysItem.getiBuffId() == EItemIBuffId.CARD_MONTH.getNumber()) {
				ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == wSysItem.getId()).findFirst().orElse(null);
				//超期时间在60天后，不可再续费
				if (null != pwPlayerItem && DateUtil.after(pwPlayerItem.expireTime().get(), new Date(), Calendar.DAY_OF_YEAR, WSysConfigService.getLimitTimeThreshouldMap().get("maxDays").intValue())) {
					throw new BaseException(ExceptionMessage.NOT_RENEW);
				}
			}
			//扣除玩家货币
			wPlayerService.pay(pwPlayer, wPayment);
			//发送道具
			itemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
			//购买记录
			wPlayerService.updateProxyWBuyitemRecord(pwPlayer, wSysItem, wPayment);
		}
		ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, "", "", 0);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 玩家快速购买道具。比普通商城购买减少道具检查，但是只能先后使用迅雷点和游戏币快速购买，并可以自定义购买数量
	 * @param playerId
	 * @param sysItemId
	 * @param unit 玩家购买数量
	 * @return
	 * @throws Exception
	 */
	public String payFast(int playerId, int sysItemId, int unit) throws Exception {
		//		//二级密码
		//        if (!secondPasswordService.hasSecondPassword(playerId)) {
		//            return Smarty4jConverter.error(CommonMsg.INPUT_SECOND_PASSWORD);
		//        }
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
		List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getItemId() == sysItemId && p.getIsShow() > 0).collect(Collectors.toList());
		//只能先后使用FC点和游戏币快速购买
		WPayment wPayment = wPaymentList.stream().filter(p -> p.getPayType() == EPayType.PAY_FC_POINT.getNumber() && p.getUnitType() == EUnitType.QUANTITY.getNumber()).findFirst().orElse(null);
		if (null == wPayment) {
			wPayment = wPaymentList.stream().filter(p -> p.getPayType() == EPayType.PAY_GP.getNumber() && p.getUnitType() == EUnitType.QUANTITY.getNumber()).findFirst().orElse(null);
		}
		if (null == wPayment) {
			throw new BaseException(ExceptionMessage.NO_SINGLE_PRICE);
		}
		wPayment.setUnit(unit);
		//扣除玩家货币
		wPlayerService.pay(pwPlayer, wPayment);
		//发送道具
		ProxyWPlayerItem pwPlayerItem = itemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
		//购买记录
		wPlayerService.updateProxyWBuyitemRecord(pwPlayer, wSysItem, wPayment);

		ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(pwPlayerItem, "", "", 1);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 玩家获取商店物品展示列表
	 * @param playerId
	 * @param itemType @EItemType
	 * @param itemSubType @EItemSubType
	 * @param sysCharacterId
	 * @param page 页码，大于等于1
	 * @param payType @EPayType
	 * @return
	 * @throws Exception
	 */
	public String getShopItems(int playerId, int itemType, int itemSubType, int sysCharacterId, int page, int payType) throws Exception {
		//获取每页数据量
		int pageSize = WSysConfigService.getUiPage().getItemTypeAndPageSizeInShop().entrySet().stream().filter(kv -> kv.getKey() <= itemType).max((p1, p2) -> p1.getKey() - p2.getKey()).get()
				.getValue();
		//过滤系统道具（按热度倒序排列）和道具对应的交易条目
		List<WSysItem> wSysItemList = wSysItemService.findList(null).stream().filter(wSysItem -> {
			if (wSysItem.getIsRemoved()) {//排除逻辑删除的道具
					return false;
				}
				if (EPayType.PAY_NONE.getNumber() != payType) {//在客户端指定支付类型的前提下，排除无指定支付类型的道具
					try {
						if (wPaymentService.findList(null).stream().filter(wPayment -> !wPayment.getIsRemoved() && wPayment.getItemId() == wSysItem.getId()).count() <= 0) {
							return false;
						}
					} catch (Exception e) {
						logger.warn("wPaymentService.findList(null) at getShopItems has error : ", e);
					}
				}
				switch (EItemType.forNumber(itemType)) {
				case WEAPON:
				case COSTUME:
				case PART:
					if (!wSysItem.getCharacterIdList().contains(sysCharacterId)) {//武器，角色，配置类型的物品要包含对应的系统角色
						return false;
					}
				default:
					break;
				}
				return wSysItem.getSubType() == itemSubType;
			}).sorted((p1, p2) -> p2.getIsHot() - p1.getIsHot()).collect(Collectors.toList());
		//分页
		List<WSysItem> list = wSysItemList.stream().sorted(LobbyCompareUtil.W_SYS_ITEM_COMPARATOR).skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
		int pages = wSysItemList.size() / pageSize;
		pages += wSysItemList.size() % pageSize > 0 ? 1 : 0;
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		return Smarty4jConverter.proto2Lua(protoConverter.responseShopList(pwPlayer, list, page, pages));
	}

	/**
	 * 查看合成用的零件的价格（只看FC点）
	 * @param type 客户端输入
	 * @return
	 * @throws Exception
	 */
	public String getCombinePrice(int type) throws Exception {
		List<Integer> sysItemIdList = WSysConfigService.getSysItemEnhancePart().get(type);
		List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getPayType() == EPayType.PAY_FC_POINT.getNumber() && p.getIsShow() > 0).collect(Collectors.toList());
		Map<WSysItem, Integer> map = new HashMap<>();
		if (null != sysItemIdList) {
			map = wSysItemService.findList(null).stream().filter(p -> sysItemIdList.contains(p.getId())).collect(Collectors.toMap(wSysItem -> wSysItem, wSysItem -> {
				return wPaymentList.stream().filter(wPayment -> wPayment.getItemId() == wSysItem.getId()).findFirst().map(p -> p.getCost()).orElse(0);
			}));
		}
		ResponseCombineGetPrice proto = protoConverter.responseCombineGetPrice(map);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 获取免费兑换的物品列表
	 * @param playerId
	 * @param page 页码
	 * @param isBlueprint 是否蓝图兑换
	 * @param itemType 兑换对象的父类型
	 * @param itemSubType 兑换对象的子类型
	 * @return
	 * @throws Exception
	 */
	public String getExchangeList(int playerId, int page, boolean isBlueprint, int itemType, int itemSubType) throws Exception {
		int pageSize = WSysConfigService.getUiPage().getPageSizeInExchange();
		Player player = ServiceLocator.getService.getPlayerById(playerId);//FIXME 为保证逻辑正确，临时使用端游缓存的玩家数据
		List<WSysItemPrice> wSysItemPriceList = wSysItemPriceService.findList(null);
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		List<WSysItem> targetWSysItemList = wSysItemMap.values().stream().filter(wSysItem -> {//获取有效的玩家兑换的目标数据
					if (wSysItem.getIsExchange() <= 0) {//用于免费兑换界面的道具
						return false;
					}
					if (itemType != EItemType.ITEMTYPE_NONE.getNumber() && wSysItem.getType() != itemType) {//查询的道具类型可能有限制
						return false;
					} else {
						if (itemSubType != EItemSubType.ITEMSUBTYPE_NONE.getNumber() && wSysItem.getSubType() != itemSubType) {//查询的道具子类型可能有限制
							return false;
						}
					}
					List<Integer> groupList = wSysItemPriceList.stream().filter(wSysItemPrice -> {//获得指定道具作为多对多交易目标的分组
								if (!wSysItemPrice.getMultiTypeMap().containsKey(EItemPriceType.EXCHANGE.getNumber())) {//不可用于免费兑换的UI界面
									return false;
								}
								if (wSysItemPrice.getPayGroup() == 0) {//免费兑换必定是多对多交易
									return false;
								}
								if (wSysItemPrice.getIsTarget() == 0 || wSysItemPrice.getPayType() != EPayType.PAY_ITEM.getNumber() || !wSysItemPrice.getSysItemId().equals(wSysItem.getId())) {//在多对多交易中必须作为交易目标
									return false;
								}
								return wSysItemPrice.getLevel() <= player.getRank() && wSysItemPrice.getVipLevel() <= player.getIsVip();//玩家能看到的数据有等级限制
							}).map(p -> p.getPayGroup()).distinct().collect(Collectors.toList());
					List<Integer> blueprintGroupList = wSysItemPriceList.stream().filter(wSysItemPrice -> {//获得蓝图作为多对多非交易目标的分组
								if (!groupList.contains(wSysItemPrice.getPayGroup())) {//必须在指定分组内
									return false;
								}
								if (wSysItemPrice.getIsTarget() != 0) {//不能作为多对多交易目标
									return false;
								}
								WSysItem tmpWSysItem = wSysItemMap.get(wSysItemPrice.getSysItemId());
								if (null == tmpWSysItem || tmpWSysItem.getType() * ItemSubType.SEP + tmpWSysItem.getSubType() != EItemSubType.MATERIAL_BLUE_PRINT.getNumber()) {//不是蓝图
									return false;
								}
								return wSysItemPrice.getLevel() <= player.getRank() && wSysItemPrice.getVipLevel() <= player.getIsVip();//玩家能看到的数据有等级限制
							}).map(p -> p.getPayGroup()).distinct().collect(Collectors.toList());
					if (!isBlueprint) {//免费兑换-非蓝图
						return groupList.size() > blueprintGroupList.size();
					} else {//免费兑换-蓝图
						return blueprintGroupList.size() > 0;
					}
				}).sorted(LobbyCompareUtil.W_SYS_ITEM_COMPARATOR).collect(Collectors.toList());
		int pages = targetWSysItemList.size() / pageSize + (targetWSysItemList.size() % pageSize == 0 ? 0 : 1);//总页数
		pages = pages <= 0 ? 1 : pages;
		page = page > pages ? pages : page <= 1 ? 1 : page;
		targetWSysItemList = targetWSysItemList.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
		//检查玩家当前持有道具以及数量
		Map<Integer, Integer> map = new HashMap<>();
		ServiceLocator.getService.getPlayerItemDao().getPlayerItemMap(playerId).values().forEach(playerItem -> {
			if (wSysItemMap.containsKey(playerItem.getItemId())) {
				if (!map.containsKey(playerItem.getItemId())) {
					map.put(playerItem.getItemId(), 0);
				}
				map.put(playerItem.getItemId(), map.get(playerItem.getItemId()) + playerItem.getQuantity());
			}
		});

		ResponseShopExchangeList proto = protoConverter.responseShopExchangeList(page, pages, targetWSysItemList, map);
		String str = Smarty4jConverter.proto2Lua(proto);
		return str;
	}
}
