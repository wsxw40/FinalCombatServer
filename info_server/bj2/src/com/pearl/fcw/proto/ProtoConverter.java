package com.pearl.fcw.proto;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.pojo.WSysQuest;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.gm.service.WSysAchievementService;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemPriceService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.gm.service.WSysQuestService;
import com.pearl.fcw.gm.service.WSysStrengthenAppendService;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyItemGunProperty;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerAchievement;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerMelting;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerQuest;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWSysItemGunProperty;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeam;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeamLevel;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.proto.enums.ECharacterDataNumberParam;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemIBuffId;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemMType;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemPriceType;
import com.pearl.fcw.proto.enums.EItemRareType;
import com.pearl.fcw.proto.enums.EItemShoppingType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EItemWId;
import com.pearl.fcw.proto.enums.EMeltingResult;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.ERepeatType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.proto.model.ProtoCharacterData;
import com.pearl.fcw.proto.model.ProtoDiscount;
import com.pearl.fcw.proto.model.ProtoExpireItem;
import com.pearl.fcw.proto.model.ProtoKv;
import com.pearl.fcw.proto.model.ProtoList;
import com.pearl.fcw.proto.model.ProtoPayment;
import com.pearl.fcw.proto.model.ProtoPlayerAchievement;
import com.pearl.fcw.proto.model.ProtoPlayerItem;
import com.pearl.fcw.proto.model.ProtoPlayerItemCombineDetail;
import com.pearl.fcw.proto.model.ProtoPlayerItemCommon;
import com.pearl.fcw.proto.model.ProtoPlayerItemMeltingItem;
import com.pearl.fcw.proto.model.ProtoPlayerItemMeltingResult;
import com.pearl.fcw.proto.model.ProtoPlayerItemNextLevelDetail;
import com.pearl.fcw.proto.model.ProtoPlayerItemPerformance;
import com.pearl.fcw.proto.model.ProtoPlayerItemSuitDetail;
import com.pearl.fcw.proto.model.ProtoPlayerMelting;
import com.pearl.fcw.proto.model.ProtoPlayerQuest;
import com.pearl.fcw.proto.model.ProtoSysAchievement;
import com.pearl.fcw.proto.model.ProtoSysItem;
import com.pearl.fcw.proto.model.ProtoSysItemCommon;
import com.pearl.fcw.proto.model.ProtoSysItemGunProperty;
import com.pearl.fcw.proto.model.ProtoSysItemPerformance;
import com.pearl.fcw.proto.model.ProtoSysItemPrice;
import com.pearl.fcw.proto.model.ProtoSysItemResource;
import com.pearl.fcw.proto.model.ProtoSysQuest;
import com.pearl.fcw.proto.model.ProtoUndurableItem;
import com.pearl.fcw.proto.rpc.ResponseCharacterGet;
import com.pearl.fcw.proto.rpc.ResponseCombineGetPrice;
import com.pearl.fcw.proto.rpc.ResponseConvertPer;
import com.pearl.fcw.proto.rpc.ResponseDailyDiscountItem;
import com.pearl.fcw.proto.rpc.ResponseEditMap;
import com.pearl.fcw.proto.rpc.ResponseFinishGuide;
import com.pearl.fcw.proto.rpc.ResponseGuideList;
import com.pearl.fcw.proto.rpc.ResponseInsert;
import com.pearl.fcw.proto.rpc.ResponseItemExpiredNotify;
import com.pearl.fcw.proto.rpc.ResponseMeltingInfoGet;
import com.pearl.fcw.proto.rpc.ResponseMeltingInput;
import com.pearl.fcw.proto.rpc.ResponseOnlineAward;
import com.pearl.fcw.proto.rpc.ResponseRemove;
import com.pearl.fcw.proto.rpc.ResponseShopExchangeList;
import com.pearl.fcw.proto.rpc.ResponseShopList;
import com.pearl.fcw.proto.rpc.ResponseShopReqBuy;
import com.pearl.fcw.proto.rpc.ResponseSloting;
import com.pearl.fcw.proto.rpc.ResponseStorageList;
import com.pearl.fcw.proto.rpc.ResponseStrengthItemList;
import com.pearl.fcw.proto.rpc.ResponseStrengthen;
import com.pearl.fcw.proto.rpc.ResponseTwoToOne;
import com.pearl.fcw.proto.server.ResponsePlayerOnline;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.SysSuit;
import com.pearl.o2o.pojo.SysSuit.Property;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;

/**
 * 将数据库数据转为proto数据<br/>
 */
public class ProtoConverter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysAchievementService wSysAchievementService;
	@Resource
	private WSysStrengthenAppendService wSysStrengthenAppendService;
	@Resource
	private WSysCharacterService wSysCharacterService;
	@Resource
	private WSysQuestService wSysQuestService;
	@Resource
	private WSysItemPriceService wSysItemPriceService;
	@Resource
	private WPaymentService wPaymentService;
	@Resource
	private WPlayerService wPlayerService;

	public ResponseEditMap responseEditMap(ProxyWTeam pwTeam, ProxyWTeamLevel pwTeamLevel) {
		ResponseEditMap.Builder builder = ResponseEditMap.newBuilder();
		builder.setName(pwTeam.name().get());
		builder.setTsl(pwTeam.teamSpaceLevel().get());
		//把首位分隔符前的sysItemId换成sysItemName
		String configs = pwTeamLevel
				.configPointsList()
				.fetch()
				.stream()
				.map(cp -> {
					WSysItem wSysItem = null;
					try {
						wSysItem = wSysItemService.findEntity(cp.sysItemId().get());
						if (null != wSysItem) {
							return String.join(",", wSysItem.getName(), cp.r1().get().toString(), cp.r2().get().toString(), cp.r3().get().toString(), cp.r4().get().toString(),
									cp.x().get().toString(), cp.y().get().toString(), cp.z().get().toString());
						}
					} catch (Exception e) {
						logger.warn("responseEditMap has error in : " + cp, e);
					}
					return String.join(",", cp.sysItemId().get().toString(), cp.r1().get().toString(), cp.r2().get().toString(), cp.r3().get().toString(), cp.r4().get().toString(), cp.x().get()
							.toString(), cp.y().get().toString(), cp.z().get().toString());

				}).collect(Collectors.joining(";"));
		builder.setConfigs(configs);
		return builder.build();
	}

	/**
	 * @param itemAndRatio 道具和折扣率
	 * @param itemAndFlag 道具和是否已使用折扣
	 * @return
	 * @throws Exception
	 */
	public ResponseDailyDiscountItem responseDailyDiscountItem(Map<WSysItem, List<Float>> itemAndRatio, Map<WSysItem, List<Boolean>> itemAndFlag) throws Exception {
		ResponseDailyDiscountItem.Builder builder = ResponseDailyDiscountItem.newBuilder();
		for (WSysItem wSysItem : itemAndRatio.keySet()) {
			builder.addTheItem(protoDiscount(wSysItem, itemAndRatio.get(wSysItem), itemAndFlag.get(wSysItem), false));
		}
		return builder.build();
	}

	public ResponseShopList responseShopList(ProxyWPlayer pwPlayer, List<WSysItem> wSysItemList, int page, int pages) throws Exception {
		ResponseShopList.Builder builder = ResponseShopList.newBuilder();
		builder.setPage(page);
		builder.setPages(pages);
		builder.setPlayerRank(pwPlayer.rank().get());
		for (WSysItem wSysItem : wSysItemList) {
			builder.addItems(protoSysItem(wSysItem));
		}
		return builder.build();
	}

	public ResponseShopReqBuy responseShopReqBuy(ProxyWPlayerItem pwPlayerItem, String error, String successMsg, int result) throws Exception {
		ResponseShopReqBuy.Builder builder = ResponseShopReqBuy.newBuilder();
		builder.setResult(null == pwPlayerItem ? result : pwPlayerItem.id().get());
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setBuildTime(wSysItem.getTimeForCreate() * 60 * 1000L);
		builder.setError(error);
		builder.setSuccessMsg(successMsg);
		return builder.build();
	}

	public ResponseCombineGetPrice responseCombineGetPrice(Map<WSysItem, Integer> map) throws Exception {
		ResponseCombineGetPrice.Builder builder = ResponseCombineGetPrice.newBuilder();
		for (Entry<WSysItem, Integer> kv : map.entrySet()) {
			builder.addItems(protoSysItem(kv.getKey()));
			builder.addPrices(kv.getValue());
		}
		return builder.build();
	}

	public ResponseStorageList responseStorageList(ProxyWPlayer pwPlayer, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap,
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap, int page, int pages) throws Exception {
		ResponseStorageList.Builder builder = ResponseStorageList.newBuilder();
		builder.setPage(page);
		builder.setPages(pages);
		builder.setPlayerRank(pwPlayer.rank().get());
		builder.setCurrenttime(System.currentTimeMillis() / 1000);
		builder.setMaxDuration(WSysConfigService.getPlayerItemDuration().getMaxDuration());
		for (ProxyWPlayerItem p : pwPlayerItemMap.values()) {
			builder.addItems(protoPlayerItem(p, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		}
		return builder.build();
	}

	public ResponseItemExpiredNotify responseItemExpiredNotify(Collection<ProxyWPlayerItem> expireItems, Collection<ProxyWPlayerItem> undurableItems) throws Exception {
		ResponseItemExpiredNotify.Builder builder = ResponseItemExpiredNotify.newBuilder();
		for (ProxyWPlayerItem p : expireItems) {
			builder.addList(protoExpireItem(p));
		}
		for (ProxyWPlayerItem p : undurableItems) {
			builder.addList1(protoUndurableItem(p));
		}
		return builder.build();
	}

	/**
	 * @param pwPlayer
	 * @param pwCharacterData
	 * @param pwPlayerInfo
	 * @param pwPlayerPackMap
	 * @param pwPlayerItemMap
	 * @param matchRank 排行榜的段位
	 * @return
	 * @throws Exception
	 */
	public ResponseCharacterGet responseCharacterGet(ProxyWPlayer pwPlayer, ProxyWCharacterData pwCharacterData, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap, int matchRank) throws Exception {
		ResponseCharacterGet.Builder builder = ResponseCharacterGet.newBuilder();
		builder.setEnable(1);
		builder.setName(pwPlayer.name().get());
		builder.setFightnum(pwCharacterData.numberParamMap().get(ECharacterDataNumberParam.characterFightNum.toString()).value().get().intValue());
		//TODO
		builder.setMatchRank(0);
		builder.setItems(protoCharacterData(pwCharacterData, pwPlayerInfo, pwPlayerPackMap, pwPlayerItemMap, pwPlayerBuffMap));
		return builder.build();
	}

	public ResponsePlayerOnline responsePlayerOnline(int userId, ProxyWPlayer pwPlayer, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap,
			Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap, Map<Serializable, ProxyWCharacterData> pwCharacterDataMap) {
		ResponsePlayerOnline.Builder builder = ResponsePlayerOnline.newBuilder();
		builder.setUid(userId);
		builder.setPlayerId(pwPlayer.id().get());
		builder.setName(pwPlayer.name().get());
		builder.setRank(pwPlayer.rank().get());
		builder.setExp(pwPlayer.exp().get());
		builder.setProfile(pwPlayer.profile().isNull() ? "" : pwPlayer.profile().get());
		builder.setIsNew(pwPlayer.isNew().get());
		builder.setIsVip(pwPlayer.isVip().get());
		builder.setInternetCafe(pwPlayerInfo.cachesEntity().get().getInternetCafe());
		int sysItemIValueInBuff = pwPlayerBuffMap.values().stream().map(pwPlayerBuff -> {
			try {
				int sysItemId = pwPlayerItemMap.get(pwPlayerBuff.playerItemId()).itemId().get();
				WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
				if (wSysItem.getType() == EItemType.ITEM.getNumber() && wSysItem.getiId() == EItemIId.BUFF.getNumber() && wSysItem.getiBuffId() == EItemIBuffId.PERSONAL_CARD.getNumber()) {
					return Integer.parseInt(wSysItem.getiValue());
				}
			} catch (Exception e) {
				logger.warn("", e);
			}
			return 0;
		}).max((p1, p2) -> p1 - p2).orElse(0);
		builder.setSysItemIValueInBuff(sysItemIValueInBuff);
		builder.setIcon(pwPlayer.icon().isNull() ? Constants.DEFAULT_PLAYER_ICON : pwPlayer.icon().get());
		builder.setIsCheckToday(0);//TODO
		builder.setTop(0);//TODO
		int fightNum = pwCharacterDataMap.values().stream().map(p -> p.numberParamMap().get(ECharacterDataNumberParam.characterFightNum.toString()).get().getValue().intValue())
				.max((p1, p2) -> p1 - p2).orElse(0);
		builder.setFightNum(fightNum);
		builder.setWinRate(0);//TODO
		builder.setTeamId(0);//TODO
		builder.setTeamName("");//TODO
		builder.setTeamLevel(1);//TODO
		builder.setIsGm(WSysConfigService.getGmPlayerId().contains(pwPlayer.id().get()) ? 1 : 0);
		return builder.build();
	}

	public ResponseGuideList responseGuideList(List<ProxyWPlayerAchievement> pwPlayerAchievementList) throws Exception {
		ResponseGuideList.Builder builder = ResponseGuideList.newBuilder();
		for (int i = 0; i < pwPlayerAchievementList.size(); i++) {
			ProxyWPlayerAchievement pwPlayerAchievement = pwPlayerAchievementList.get(i);
			builder.addPlayerAchievement(ProtoPlayerAchievement(pwPlayerAchievement));
			//从系统成就中查找下一个应完成的成就
			int sysAchievementId = 0;
			LinkedList<Integer> sysAchievementIdList = new LinkedList<>();
			sysAchievementIdList.add(sysAchievementId);
			while (sysAchievementIdList.size() < pwPlayerAchievement.level().get() + 1) {
				sysAchievementId = wSysAchievementService.findList(null).stream().filter(p -> {
					return pwPlayerAchievement.group().eq(p.getGroup()) && Integer.parseInt(p.getParent()) == sysAchievementIdList.peekLast();
				}).map(p -> p.getId()).findFirst().orElse(0);
				sysAchievementIdList.add(sysAchievementId);
			}
			WSysAchievement wSysAchievement = wSysAchievementService.findEntity(sysAchievementId);
			if (null == wSysAchievement) {
				builder.addNextSysAchievement(ProtoSysAchievement.getDefaultInstance());
			} else {
				builder.addNextSysAchievement(protoSysAchievement(wSysAchievement));
			}
		}
		return builder.build();
	}

	public ResponseFinishGuide responseFinishGuide(ProxyWPlayerAchievement pwPlayerAchievement, WSysAchievement nextWSysAchievement) {
		ResponseFinishGuide.Builder builder = ResponseFinishGuide.newBuilder();
		if (null != pwPlayerAchievement) {
			builder.setPlayerAchievement(ProtoPlayerAchievement(pwPlayerAchievement));
			if (null != nextWSysAchievement) {
				builder.setNextSysAchievement(protoSysAchievement(nextWSysAchievement));
			}
		}
		return builder.build();
	}

	public ResponseStrengthItemList responseStrengthItemList(ProxyWPlayer pwPlayer, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap,
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap, int page, int pages) throws Exception {
		ResponseStrengthItemList.Builder builder = ResponseStrengthItemList.newBuilder();
		builder.setPage(page);
		builder.setPages(pages);
		builder.setPlayerRank(pwPlayer.rank().get());
		for (ProxyWPlayerItem pwPlayerItem : pwPlayerItemMap.values()) {
			builder.addItems(protoPlayerItem(pwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		}
		for (int rareType : WSysConfigService.getSysItemRareTypeRareLevel().keySet()) {
			if (rareType == EItemRareType.RARE_0.getNumber()) {//普通稀有度忽略
				continue;
			}
			List<Integer> list = new ArrayList<>();
			int rareLevel = WSysConfigService.getSysItemRareTypeRareLevel().get(rareType);
			for (int gstLevel = 0; gstLevel < WSysConfigService.getPlayerItemMaxGstLevel(); gstLevel++) {
				int exp = wPlayerService.getNextGstLevelExp(gstLevel, rareLevel);
				list.add(exp);
			}
			builder.addGstExp(protoListInt32(list));
			builder.addEverydayGstExpInCombine(WSysConfigService.getSysItemRareTypeGstExpInCombine().get(rareType));
			builder.addEverydayGpointInCombine(WSysConfigService.getSysItemRareTypeGpointInCombine().get(rareType));
		}
		List<Long> everydayGstExpInCombineUseTimeList = pwPlayerInfo.cachesEntity().get().getRareTypeAndBuyGstExpTime().values().stream().collect(Collectors.toList());
		int len = WSysConfigService.getSysItemRareTypeRareLevel().keySet().size() - everydayGstExpInCombineUseTimeList.size();
		for (int i = 1; i < len; i++) {//普通稀有度忽略
			everydayGstExpInCombineUseTimeList.add(0L);
		}
		Date now = new Date();
		for (long millsec : everydayGstExpInCombineUseTimeList) {
			builder.addEverydayGstExpInCombineUseFlag(DateUtil.isSameDay(now, Date.from(Instant.ofEpochMilli(millsec))) ? 0 : 1);
		}
		return builder.build();
	}

	public ResponseStrengthen responseStrengthen(int result, ProxyWPlayerItem targetPwPlayerItem, ProxyWPlayerItem partPwPlayerItem, ProxyWPlayerItem safePwPlayerItem,
			ProxyWPlayerItem stablePwPlayerItem, List<WSysItem> failWardSysItemList, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseStrengthen.Builder builder = ResponseStrengthen.newBuilder();
		builder.setResult(result);
		builder.addItems(protoPlayerItem(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.addItems2(protoPlayerItem(partPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.addItems2(protoPlayerItem(safePwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.addItems2(protoPlayerItem(stablePwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		for (WSysItem p : failWardSysItemList) {
			builder.addFailAwards(protoSysItem(p));

		}
		return builder.build();
	}

	public ResponseSloting responseSloting(int result, ProxyWPlayerItem targetPwPlayerItem, ProxyWPlayerItem partPwPlayerItem, ProxyWPlayerInfo pwPlayerInfo,
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseSloting.Builder builder = ResponseSloting.newBuilder();
		builder.setResult(result);
		builder.setItem(protoPlayerItem(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.setSloterItem(protoPlayerItem(partPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		return builder.build();
	}

	public ResponseInsert responseInsert(int result, ProxyWPlayerItem targetPwPlayerItem, ProxyWPlayerItem partPwPlayerItem, ProxyWPlayerInfo pwPlayerInfo,
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseInsert.Builder builder = ResponseInsert.newBuilder();
		builder.setResult(result);
		builder.setItem(protoPlayerItem(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.setSloterItem(protoPlayerItem(partPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		return builder.build();
	}

	public ResponseRemove responseRemove(int result, ProxyWPlayerItem targetPwPlayerItem, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseRemove.Builder builder = ResponseRemove.newBuilder();
		builder.setResult(result);
		builder.setItem(protoPlayerItem(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		return builder.build();
	}

	public ResponseTwoToOne responseTwoToOne(ProxyWPlayerItem targetPwPlayerItem, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseTwoToOne.Builder builder = ResponseTwoToOne.newBuilder();
		builder.setMainItem(protoPlayerItem(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		return builder.build();
	}

	/**
	 * @param rate1 转换物1的成功率
	 * @param rate2 转换物2的成功率
	 * @param pwPlayerItem1
	 * @param pwPlayerItem2
	 * @param pwPlayerInfo
	 * @param pwPlayerPackMap
	 * @param pwPlayerBuffMap
	 * @return
	 * @throws Exception
	 */
	public ResponseConvertPer responseConvertPer(float rate1, float rate2, ProxyWPlayerItem pwPlayerItem1, ProxyWPlayerItem pwPlayerItem2, ProxyWPlayerInfo pwPlayerInfo,
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ResponseConvertPer.Builder builder = ResponseConvertPer.newBuilder();
		builder.setRateFrom(1 - (int) (rate1 * 100));
		builder.setRateTo(1 - (int) (rate2 * 100));
		builder.setRate((int) (rate1 + rate2 - rate1 * rate2));
		builder.setFrom(protoPlayerItem(pwPlayerItem1, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		builder.setFrom(protoPlayerItem(pwPlayerItem1, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
		return builder.build();
	}

	public ResponseMeltingInfoGet responseMeltingInfoGet(ProxyWPlayerMelting pwPlayerMelting, ProxyWPlayerInfo pwPlayerInfo) {
		ResponseMeltingInfoGet.Builder builder = ResponseMeltingInfoGet.newBuilder();
		builder.setPlayerMelting(protoPlayerMelting(pwPlayerMelting, pwPlayerInfo, 0));
		return builder.build();
	}

	public ResponseMeltingInput responseMeltingInput(EMeltingResult result) {
		ResponseMeltingInput.Builder builder = ResponseMeltingInput.newBuilder();
		builder.setResult(result);
		return builder.build();
	}

	/**
	 * @param pwPlayerMelting
	 * @param pwPlayerInfo
	 * @param award 奖励的系统道具和数量
	 * @param previewCost 预览熔炼产出的花费
	 * @return
	 */
	public ResponseMeltingInput responseMeltingInput(ProxyWPlayerMelting pwPlayerMelting, ProxyWPlayerInfo pwPlayerInfo, Map<WSysItem, Integer> award, int previewCost) {
		ResponseMeltingInput.Builder builder = ResponseMeltingInput.newBuilder();
		builder.setResult(EMeltingResult.MELTING_SUCCESS);
		if (null != pwPlayerMelting) {
			builder.setPlayerMelting(protoPlayerMelting(pwPlayerMelting, pwPlayerInfo, previewCost));
		}
		//TODO 蓝图产出暂时不写
		return builder.build();
	}

	/**
	 * @param page
	 * @param pages
	 * @param wSysItemList
	 * @param sysItemIdAndQuantity k:玩家持有的系统道具ID，v:对应该系统道具的数量
	 * @return
	 * @throws Exception
	 */
	public ResponseShopExchangeList responseShopExchangeList(int page, int pages, List<WSysItem> wSysItemList, Map<Integer, Integer> sysItemIdAndQuantity) throws Exception {
		ResponseShopExchangeList.Builder builder = ResponseShopExchangeList.newBuilder();
		builder.setPage(page);
		builder.setPages(pages);
		//保留端游勋章数量的单独统计
		Map<Integer, WSysItem> map = wSysItemService.findMap(null);
		int count = sysItemIdAndQuantity.entrySet().stream().filter(kv -> {
			try {
				return map.get(kv.getKey()).getiId() == EItemIId.MEDAL.getNumber();
			} catch (Exception e) {
				return false;
			}
		}).map(kv -> kv.getValue()).reduce((p1, p2) -> p1 + p2).orElse(0);
		builder.setChipNum(count);
		//保留端游复活币数量的单独统计
		count = sysItemIdAndQuantity.entrySet().stream().filter(kv -> {
			return map.get(kv.getKey()).getiId() == EItemIId.REVIVE_COIN.getNumber();
		}).map(kv -> kv.getValue()).reduce((p1, p2) -> p1 + p2).orElse(0);
		builder.setReviceCoinNum(count);
		for (WSysItem wSysItem : wSysItemList) {
			builder.addSysItem(protoSysItem(wSysItem, EItemPriceType.EXCHANGE));
		}
		for (int k : sysItemIdAndQuantity.keySet()) {
			builder.addSysItemAndQuantity(protoKv(k, sysItemIdAndQuantity.get(k)));
		}
		return builder.build();
	}

	public ProtoList protoListInt32(List<Integer> intValueList) {
		//用常规的newBuilder方法填充数据后build会抛异常，原因不明
		ProtoList p = ProtoList.getDefaultInstance();
		ProtoList.Builder b = p.toBuilder();
		b.addAllI(intValueList);
		p = b.build();
		return p;
	}

	public ProtoKv protoKv(int k, int v) {
		ProtoKv.Builder builder = ProtoKv.newBuilder();
		builder.setK(k);
		builder.setV(v);
		return builder.build();
	}

	public ProtoExpireItem protoExpireItem(ProxyWPlayerItem pwPlayerItem) throws Exception {
		ProtoExpireItem.Builder builder = ProtoExpireItem.newBuilder();
		builder.setDisplayName(wSysItemService.findEntity(pwPlayerItem.itemId().get()).getDisplayName());
		//        long second = System.currentTimeMillis() - pwPlayerItem.expireTime().getTime();
		builder.setExpireSecondsLeft(0);
		return builder.build();
	}

	public ProtoUndurableItem protoUndurableItem(ProxyWPlayerItem pwPlayerItem) throws Exception {
		ProtoUndurableItem.Builder builder = ProtoUndurableItem.newBuilder();
		builder.setDisplayName(wSysItemService.findEntity(pwPlayerItem.itemId().get()).getDisplayName());
		builder.setDurable(pwPlayerItem.durable().get());
		return builder.build();
	}

	/**
	 * @param wSysItem
	 * @param ratios 折扣率
	 * @param flags 是否已使用对应的折扣率
	 * @param isSysItemSimple sysItem字段是否只记录id
	 * @return
	 * @throws Exception
	 */
	public ProtoDiscount protoDiscount(WSysItem wSysItem, List<Float> ratios, List<Boolean> flags, boolean isSysItemSimple) throws Exception {
		ProtoDiscount.Builder builder = ProtoDiscount.newBuilder();
		if (isSysItemSimple) {
			ProtoSysItem.Builder b = ProtoSysItem.newBuilder();
			b.setSid(wSysItem.getId());
			builder.setSysItem(b.build());
		} else {
			builder.setSysItem(protoSysItem(wSysItem));
		}
		builder.addAllRatio(ratios);
		builder.addAllFlag(flags.stream().map(p -> p ? 1 : 0).collect(Collectors.toList()));
		Date now = new Date();
		builder.setTime(now.getTime());
		return builder.build();
	}

	/**
	 * @param wSysItem
	 * @param quantity 显示用的数量
	 * @return
	 * @throws Exception
	 */
	public ProtoSysItem protoSysItem(WSysItem wSysItem, int quantity) throws Exception {
		ProtoSysItem.Builder builder = protoSysItem(wSysItem).toBuilder();
		builder.setQuantity(quantity);
		return builder.build();
	}

	/**
	 * @param wSysItem
	 * @param itemPriceType 交易类型
	 * @return
	 * @throws Exception
	 */
	public ProtoSysItem protoSysItem(WSysItem wSysItem, EItemPriceType itemPriceType) throws Exception {
		ProtoSysItem.Builder builder = protoSysItem(wSysItem).toBuilder();
		List<WSysItemPrice> wSysItemPriceList = wSysItemPriceService.findList(null).stream().filter(p -> {
			//不符合交易类型
				if (!p.getMultiTypeMap().containsKey(itemPriceType.getNumber())) {
					return false;
				}
				//一对一交易中作为目标
				if (p.getIsTarget() == 0 && p.getPayGroup() == 0) {
					return p.getSysItemId().equals(wSysItem.getId());
				}
				//多对多中作为目标
				if (p.getIsTarget() > 0) {
					return p.getSysItemId().equals(wSysItem.getId());
				}
				return false;
			}).collect(Collectors.toList());
		for (WSysItemPrice p : wSysItemPriceList) {
			builder.addPrice(protoSysItemPrice(p));
			if (p.getPayGroup() == 0) {//一对一交易中数据只有一条，跳过后面的逻辑
				continue;
			}
			//可能有多对多的交易价格未获取，必须根据payGroup找到它们
			List<WSysItemPrice> priceList = wSysItemPriceService.findList(null).stream().filter(p1 -> p1.getPayGroup() == p.getPayGroup() && p1.getSysItemId() != p.getSysItemId())
					.collect(Collectors.toList());
			if (priceList.stream().filter(a -> a.getIsTarget() > 0).map(a -> a.getSysItemId()).distinct().count() == 1) {//交易目标只有一个的交易组根据交易目标的unit做分组排序
				priceList.stream().filter(a -> a.getIsTarget() > 0).sorted((a1, a2) -> a1.getUnit() - a2.getUnit()).forEach(a -> {
					int group = a.getPayGroup();
					priceList.stream().filter(b -> b.getPayGroup() == group).sorted((b1, b2) -> b2.getIsTarget() - b1.getIsTarget()).forEach(b -> {
						builder.addPrice(protoSysItemPrice(b));
					});
				});
			} else {
				priceList.forEach(a -> {
					builder.addPrice(protoSysItemPrice(a));
				});
			}
		}
		return builder.build();
	}

	public ProtoSysItem protoSysItem(WSysItem wSysItem) throws Exception {
		ProtoSysItem.Builder builder = ProtoSysItem.newBuilder();
		builder.setSid(wSysItem.getId());
		builder.setDisplay("<SN" + wSysItem.getId() + "^0>");
		builder.setName(wSysItem.getName());
		builder.setDescription("<SD" + wSysItem.getId() + "^0>");
		builder.setModifiedDesc(wSysItem.getModifiedDesc());
		String timelimit = "<C440^0>";
		List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getItemId() == wSysItem.getId() && p.getIsShow() != 0)
				.sorted((p1, p2) -> p1.getPayType() - p2.getPayType()).collect(Collectors.toList());
		if (!wPaymentList.isEmpty()) {
			switch (EUnitType.forNumber(wPaymentList.get(0).getUnitType())) {
			case QUANTITY:
				timelimit = "<C438^1^" + wPaymentList.get(0).getUnit() + ">";
				break;
			case TIME:
				timelimit = "<C439^1^" + wPaymentList.get(0).getUnit() + ">";
			default:
				break;
			}
		}
		builder.setTimelimit(timelimit);
		builder.addAllCharacters(wSysItem.getCharacterIdList());
		builder.setColorValue((int) wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.color.toString(), 0));
		builder.setTypeValue(wSysItem.getType());
		builder.setSendperson(wSysItem.getIsHot());
		builder.setWidValue(wSysItem.getwId());
		builder.setIidValue(wSysItem.getiId());
		builder.setCommon(protoSysItemCommon(wSysItem));
		builder.setPerformance(protoSysItemPerformance(wSysItem));
		for (int i = 2; i <= 5; i++) {//FIXME 原接口就是输出2到5，原因不明
			builder.addGunproperty(protoSysItemGunPropertyList(wSysItem, i));
		}
		for (int sysItemId : wSysItem.getItemsList()) {
			String displayName = "";
			WSysItem m = wSysItemService.findEntity(sysItemId);
			if (null != m) {
				displayName = m.getDisplayName();
			}
			builder.addPackageStr(displayName);
		}
		for (WPayment wPayment : wPaymentList) {
			switch (EPayType.forNumber(wPayment.getPayType())) {
			case PAY_GP:
				builder.addGpprices(protoPayment(wPayment));
				break;
			case PAY_FC_POINT:
				builder.addCrprices(protoPayment(wPayment));
				break;
			case PAY_VOUCHER:
				builder.addVoucherprices(protoPayment(wPayment));
				break;
			case PAY_PERSONAL_RES:
				builder.addResprices(protoPayment(wPayment));
				break;
			case PAY_TEAM_RES:
				builder.addResteamprices(protoPayment(wPayment));
				break;
			default:
				break;
			}
		}
		builder.setResource(protoSysItemResource(wSysItem));
		return builder.build();
	}

	public ProtoSysItemCommon protoSysItemCommon(WSysItem wSysItem) {
		Date now = new Date();
		ProtoSysItemCommon.Builder builder = ProtoSysItemCommon.newBuilder();
		builder.setLevel(wSysItem.getLevel());
		builder.setModifiedDesc(null == wSysItem.getModifiedDesc() ? "" : wSysItem.getModifiedDesc());
		builder.addAllCharacters(wSysItem.getCharacterIdList());
		builder.setSubtype(wSysItem.getSubType());
		builder.setWidValue(wSysItem.getwId());
		builder.setSeq((int) wSysItem.getNumberParamMap().get(EItemNumberParam.seq.toString()));
		builder.setIsVip(wSysItem.getIsVip());
		builder.setIsNew(wSysItem.getIsNew());
		builder.setIsHot(wSysItem.getIsHot());
		builder.setIsWeb(wSysItem.getIsWeb());
		builder.setStar((int) wSysItem.getNumberParamMap().get(EItemNumberParam.fightNum.toString()));
		builder.setStrength(wSysItem.getIsStrengthen() != 0 ? wSysItem.getStrengthLevel() : -1);
		builder.setCResistanceFire(wSysItem.getcResistanceFire());
		builder.setCResistanceBlast(wSysItem.getcResistanceBlast());
		builder.setCResistanceBullet(wSysItem.getcResistanceBullet());
		builder.setCResistanceKnife(wSysItem.getcResistanceKnife());
		builder.setCBloodAdd(wSysItem.getcBloodAdd());
		builder.setRareLevel(wSysItem.getRareLevel());
		builder.setIsOpenQuality(wSysItem.getwId() == EItemWId.WID_JQT_GUN.getNumber() ? 0 : 1);
		builder.setTypeValue(wSysItem.getType());
		builder.setMTypeValue(wSysItem.getmType());
		boolean b = wSysItem.getShoppingType() == EItemShoppingType.FLASH_SALE.getNumber() && DateUtil.after(now, wSysItem.getShoppingStartTime(), Calendar.MILLISECOND, 1)
				&& DateUtil.before(now, wSysItem.getShoppingEndTime(), Calendar.MILLISECOND, 1);
		builder.setIsFlashSale(b ? 1 : 0);
		return builder.build();
	}

	public ProtoSysItemPerformance protoSysItemPerformance(WSysItem wSysItem) throws Exception {
		ProtoSysItemPerformance.Builder builder = ProtoSysItemPerformance.newBuilder();
		builder.setDamage(wSysItem.getNumberParamMap().get(EItemNumberParam.damage.toString()).floatValue());
		builder.setSpeed(wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeed.toString()).floatValue());
		builder.setAmmos(wSysItem.getwAmmoOneClip());
		builder.setAmmoCount(wSysItem.getwAmmoCount());
		builder.setDamageAdd(wSysItem.getNumberParamMap().get(EItemNumberParam.damageNext.toString()).floatValue() - builder.getDamage());
		builder.setSpeedAdd(wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeedNext.toString()).floatValue() - builder.getSpeed());
		return builder.build();
	}

	/**
	 * @param wSysItem
	 * @param key @WSysItem.numberParamMap.key
	 * @return
	 */
	public ProtoSysItemGunProperty protoSysItemGunPropertyList(WSysItem wSysItem, int key) {
		ProtoSysItemGunProperty.Builder builder = ProtoSysItemGunProperty.newBuilder();
		builder.setColorValue((int) wSysItem.getNumberParamMap().get(EItemNumberParam.color.toString()));
		builder.setBasePropertyStr("");//FIXME 原始接口这里似乎没有输出内容
		return builder.build();
	}

	public ProtoSysItemResource protoSysItemResource(WSysItem wSysItem) {
		ProtoSysItemResource.Builder builder = ProtoSysItemResource.newBuilder();
		switch (EItemType.forNumber(wSysItem.getType())) {//武器用可变资源，非武器用固定资源；武器用wid，角色和配饰用cid，其余默认0
		case WEAPON:
			builder.setType(wSysItem.getwId());
			for (List<String> strList : wSysItem.getResourceChangeableList()) {
				for (String str : strList) {
					if (!StringUtil.isEmpty(str)) {
						builder.addRes(wSysItem.getName() + "/" + str);
					}
				}
			}
			for (String str : wSysItem.getResourceStableList()) {
				if (!StringUtil.isEmpty(str)) {
					builder.addRes(wSysItem.getName() + "/" + str);
				}
			}
			break;
		case COSTUME:
		case PART:
			builder.setType(wSysItem.getcId());
		default:
			for (String str : wSysItem.getResourceStableList()) {
				builder.addRes(str);
			}
			break;
		}
		return builder.build();
	}

	public ProtoPlayerItem protoPlayerItem(ProxyWPlayerItem pwPlayerItem, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ProtoPlayerItem.Builder builder = ProtoPlayerItem.newBuilder();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setPlayeritemid(pwPlayerItem.id().get());
		builder.setSid(pwPlayerItem.itemId().get());
		builder.setIsBind(pwPlayerItem.isBind().get());
		builder.setIid(wSysItem.getiId());
		builder.setWid(wSysItem.getwId());
		builder.setDisplay(wSysItem.getDisplayName());
		builder.setName(wSysItem.getName());
		builder.setCreatetime(pwPlayerItem.validTime().getTime() / 1000);
		builder.setUnitTypeValue(pwPlayerItem.unitType().get());
		builder.setModifiedDesc(pwPlayerItem.modifiedDesc().get());
		for (int id : wSysItem.getCharacterIdList()) {
			builder.addCharacters(id);
		}
		builder.setDescription(wSysItem.getDescription());
		int packId = pwPlayerPackMap.values().stream().filter(p -> p.playerItemId().get() == pwPlayerItem.id().get()).findFirst().map(p -> p.packId().get()).orElse((short) 0);
		builder.setPackId(packId);
		builder.setRepairCost(pwPlayerItem.numberParamMap().get(EItemNumberParam.repaireCost.toString()).value().get().intValue());
		builder.setBuff(pwPlayerBuffMap.values().stream().filter(p -> p.playerItemId().eq(pwPlayerItem.id().get())).findFirst().map(p -> p.buffId().get()).orElse((short) 0));
		builder.setIsDefault("Y".equals(pwPlayerItem.isDefault().get()) ? 0 : 1);
		builder.setMType(wSysItem.getmType());
		builder.setMValue(wSysItem.getmValue());
		Integer provisionalItemDay = pwPlayerInfo.cachesEntity().get().getPlayerItemIdAndRemainDaysInLimitTime().get(pwPlayerItem.id().get());
		builder.setProvisionalItemDay(null == provisionalItemDay ? 60001 : provisionalItemDay);
		int provisionalItemFlag = WSysConfigService.getSysItemIdLimitTime().contains(wSysItem.getId()) ? 1 : 0;
		builder.setProvisionalItemFlag(provisionalItemFlag);
		builder.setCommon(protoPlayerItemCommon(pwPlayerItem));
		builder.setPerformance(protoPlayerItemPerformance(pwPlayerItem));
		builder.setColorValue(pwPlayerItem.numberParamMap().get(EItemNumberParam.color.toString()).value().get().intValue());
		for (int i = 2; i <= 7; i++) {//FIXME 原接口就是输出2到7，对应系统角色ID1-6
			builder.addGunproperty(protoSysItemGunPropertyList(wSysItem, i));
		}
		for (int i = 2; i <= 7; i++) {//FIXME 原接口就是输出2到7，对应系统角色ID1-6
			builder.addCombineDetail(protoPlayerItemCombineDetail(pwPlayerItem, i));
		}
		if (wSysItem.getNeedTeamPlaceLevel() > 99) {//FIXME 这里的99应可被策划配置
			SysSuit sysSuit = ConfigurationUtil.SUITMAP.get(wSysItem.getNeedTeamPlaceLevel());
			builder.setSuitId(sysSuit.getSuitId());
			builder.setSuitDetail(protoPlayerItemSuitDetail(sysSuit));
		} else {
			builder.setSuitDetail(ProtoPlayerItemSuitDetail.getDefaultInstance());
		}
		List<WPayment> wPaymentList = wPaymentService.findList(null).stream().filter(p -> p.getItemId().equals(wSysItem.getId()) && p.getIsShow() != 0)
				.sorted((p1, p2) -> p1.getPayType() - p2.getPayType()).collect(Collectors.toList());
		for (WPayment wPayment : wPaymentList) {
			switch (EPayType.forNumber(wPayment.getPayType())) {
			case PAY_GP:
				builder.addGpprices(protoPayment(wPayment));
				break;
			case PAY_FC_POINT:
				builder.addCrprices(protoPayment(wPayment));
				break;
			case PAY_VOUCHER:
				builder.addVoucherprices(protoPayment(wPayment));
				break;
			default:
				break;
			}
		}
		for (int sysItemId : wSysItem.getItemsList()) {
			String displayName = "";
			WSysItem m = wSysItemService.findEntity(sysItemId);
			if (null != m) {
				displayName = m.getDisplayName();
			}
			builder.addPackageStr(displayName);
		}
		builder.setResource(protoSysItemResource(wSysItem));
		builder.setGstLevel(pwPlayerItem.gstLevel().get());
		builder.setGstExp(pwPlayerItem.gstLevelExp().get());
		//根据稀有度类型判断玩家是否在当天是否已购买获得星级经验
		int rareType = WSysConfigService.getSysItemRareTypeRareLevel().entrySet().stream().filter(kv -> kv.getValue() <= wSysItem.getRareLevel()).map(kv -> kv.getKey()).max((p1, p2) -> p1 - p2).get();
		Instant flagTime = Instant.ofEpochMilli(0);
		try {
			flagTime = Instant.ofEpochMilli(pwPlayerInfo.cachesEntity().get().getRareTypeAndBuyGstExpTime().getOrDefault(rareType, 0L));
		} catch (Exception e) {
		}
		Date now = new Date();
		if (!DateUtil.isSameDay(now, Date.from(flagTime))) {//当天玩家没有获得过该稀有度类型的购买经验
			builder.setGstExpAdd(WSysConfigService.getSysItemRareTypeGstExpInCombine().get(rareType));
		}
		if (wSysItem.getIsStrengthen() == 0) {
			builder.setBaseItemFightNum(wSysItem.getNumberParamMap().get(EItemNumberParam.fightNum.toString()).intValue());
		} else {
			builder.setBaseItemFightNum(pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNumBase.toString()).value().get().intValue());
			builder.setStrengthenItemFightNum(pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNumAdd.toString()).value().get().intValue());
		}
		builder.setNextLevelDetail(protoPlayerItemNextLevelDetail(pwPlayerItem));
		if (wSysItem.getmType() == EItemMType.M_MELTING_FORMULA.getNumber()) {
			for (int sysItemId : wSysItem.getResourceStableList().stream().map(Integer::parseInt).collect(Collectors.toSet())) {
				WSysItem meltingWSysItem = wSysItemService.findEntity(sysItemId);
				builder.addMeltingItem(protoPlayerItemMeltingItem(meltingWSysItem));
			}
			List<List<Integer>> list = wSysItem.getResourceChangeableList().stream().map(p -> {
				return p.stream().map(Integer::parseInt).collect(Collectors.toList());
			}).collect(Collectors.toList());
			for (List<Integer> list1 : list) {
				WSysItem meltingResult = wSysItemService.findEntity(list1.get(0));
				builder.addMeltingResult(protoPlayerItemMeltingResult(meltingResult, list1.get(1), EUnitType.forNumber(list1.get(2))));
			}
		}
		return builder.build();
	}

	public ProtoPlayerItemCommon protoPlayerItemCommon(ProxyWPlayerItem pwPlayerItem) throws Exception {
		ProtoPlayerItemCommon.Builder builder = ProtoPlayerItemCommon.newBuilder();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setIsAsset(wSysItem.getIsAsset());
		builder.setIsOpenQuality(wSysItem.getwId() == EItemWId.WID_JQT_GUN.getNumber() ? 0 : 1);
		builder.setLevel(wSysItem.getLevel());
		builder.setMaterialNeed(pwPlayerItem.numberParamMap().get(EItemNumberParam.materialNeed.toString()).value().get().intValue());
		builder.setGpNeed(pwPlayerItem.numberParamMap().get(EItemNumberParam.gpNeed.toString()).value().get().intValue());
		builder.setTypeValue(wSysItem.getType());
		builder.setSubtype(wSysItem.getSubType());
		builder.setWidValue(wSysItem.getwId());
		builder.setDurable(pwPlayerItem.durable().get() < 0 ? 0 : pwPlayerItem.durable().get());
		builder.setQuantity(pwPlayerItem.quantity().get());
		int minutesLeft = (int) ((pwPlayerItem.expireTime().getTime() - System.currentTimeMillis()) / 1000 / 60);
		minutesLeft = minutesLeft < 0 ? 0 : minutesLeft;
		builder.setMinutesLeft(minutesLeft);
		builder.setSeq(wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.seq.toString(), 0).intValue());
		builder.setIsVip(wSysItem.getIsVip());
		builder.setIsNew(wSysItem.getIsNew());
		builder.setStar((int) wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.fightNum.toString(), 0));
		builder.setStrength(wSysItem.getIsStrengthen() > 0 ? wSysItem.getStrengthLevel() : -1);
		int holeCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.holeCount.toString()).value().get().intValue();
		int slotCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.slotCount.toString()).value().get().intValue();
		builder.setHoleNum(holeCount + slotCount);
		builder.setCResistanceFire(wSysItem.getcResistanceFire());
		builder.setCResistanceBlast(wSysItem.getcResistanceBlast());
		builder.setCResistanceBullet(wSysItem.getcResistanceBullet());
		builder.setCResistanceKnife(wSysItem.getcResistanceKnife());
		builder.setCBloodAdd(wSysItem.getcBloodAdd());
		builder.setCResistanceFireAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceFire.toString()).value().get().floatValue());
		builder.setCResistanceBlastAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceBlast.toString()).value().get().floatValue());
		builder.setCResistanceBulletAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceBullet.toString()).value().get().floatValue());
		builder.setCResistanceKnifeAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceKnife.toString()).value().get().floatValue());
		builder.setCBloodAddAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cBloodAdd.toString()).value().get().floatValue());
		builder.setRareLevel(null == wSysItem.getRareLevel() ? 0 : wSysItem.getRareLevel());
		builder.setEvaluateRmb(wSysItem.getEvaluateRmb());
		builder.setNeedTeamPlaceLevel(null == wSysItem.getNeedTeamPlaceLevel() ? 0 : wSysItem.getNeedTeamPlaceLevel());
		return builder.build();
	}

	public ProtoPlayerItemPerformance protoPlayerItemPerformance(ProxyWPlayerItem pwPlayerItem) throws Exception {
		ProtoPlayerItemPerformance.Builder builder = ProtoPlayerItemPerformance.newBuilder();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setDamage(wSysItem.getNumberParamMap().get(EItemNumberParam.damage.toString()).floatValue());
		builder.setSpeed(wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeed.toString()).floatValue());
		builder.setAmmos(wSysItem.getwAmmoOneClip());
		builder.setAmmoCount(wSysItem.getwAmmoCount());
		builder.setDamageAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.damage.toString()).get().getValue().floatValue()
				- wSysItem.getNumberParamMap().get(EItemNumberParam.damage.toString()).floatValue());
		builder.setSpeedAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.shootSpeed.toString()).get().getValue().floatValue()
				- wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeed.toString()).floatValue());
		return builder.build();
	}

	/**
	 * @param pwPlayerItem
	 * @param gunPropertyIndex gunPropertyN字段名称中在gunProperty后的数字
	 * @return
	 * @throws Exception
	 */
	public ProtoPlayerItemCombineDetail protoPlayerItemCombineDetail(ProxyWPlayerItem pwPlayerItem, int gunPropertyIndex) throws Exception {
		ProtoPlayerItemCombineDetail.Builder builder = ProtoPlayerItemCombineDetail.newBuilder();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setIndex(gunPropertyIndex - 1);
		builder.setOpen(wSysItem.getIsStrengthen() > 0 ? 0 : 1);
		ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(gunPropertyIndex + "");
		if (null != pItemGunProperty) {
			builder.setState(pItemGunProperty.open().get());
			if (!pItemGunProperty.gunPropertyList().isEmpty()) {
				ProxyWSysItemGunProperty pwSysItemGunProperty = pItemGunProperty.gunPropertyList().get(pItemGunProperty.gunPropertyList().size() - 1);
				builder.setLevel(pwSysItemGunProperty.value().get());
				//FIXME CommonUtil.getPropertyStr取国际化文字的方式要弃用
				builder.setDesc(CommonUtil.getPropertyStr(pwSysItemGunProperty.index().get(), pwSysItemGunProperty.value().get(), pwSysItemGunProperty.value2().get(), pwSysItemGunProperty.time()
						.get()));
			}
		}
		return builder.build();
	}

	public ProtoPlayerItemSuitDetail protoPlayerItemSuitDetail(SysSuit sysSuit) {
		ProtoPlayerItemSuitDetail.Builder builder = ProtoPlayerItemSuitDetail.newBuilder();
		for (Property p : sysSuit.getAllSpec4Pros()) {
			builder.addDes4(p.getDesc());
		}
		for (Property p : sysSuit.getAllSpec6Pros()) {
			builder.addDes6(p.getDesc());
		}
		return builder.build();
	}

	public ProtoPlayerItemNextLevelDetail protoPlayerItemNextLevelDetail(ProxyWPlayerItem pwPlayerItem) throws Exception {
		ProtoPlayerItemNextLevelDetail.Builder builder = ProtoPlayerItemNextLevelDetail.newBuilder();
		WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
		builder.setDamage(wSysItem.getNumberParamMap().get(EItemNumberParam.damage.toString()).floatValue());
		float tmpFloat = pwPlayerItem.numberParamMap().get(EItemNumberParam.damageNext.toString()).get().getValue().floatValue()
				- wSysItem.getNumberParamMap().get(EItemNumberParam.damage.toString()).floatValue();
		builder.setDamageAdd(tmpFloat);
		builder.setSpeed(wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeed.toString()).floatValue());
		tmpFloat = pwPlayerItem.numberParamMap().get(EItemNumberParam.shootSpeedNext.toString()).get().getValue().floatValue()
				- wSysItem.getNumberParamMap().get(EItemNumberParam.shootSpeed.toString()).floatValue();
		builder.setSpeedAdd(tmpFloat);
		builder.setDurable(pwPlayerItem.durable().get() < 0 ? 0 : pwPlayerItem.durable().get());
		tmpFloat = pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNumBase.toString()).get().getValue().floatValue()
				+ pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNumAdd.toString()).get().getValue().floatValue();
		builder.setStar(wSysItem.getIsStrengthen() > 0 ? (int) tmpFloat : (int) wSysItem.getNumberParamMap().get(EItemNumberParam.fightNum.toString()).floatValue());
		builder.setLevel(WSysConfigService.getSysItemEnhanceLevel().keySet().stream().max((p1, p2) -> p1 - p2).orElse(0));
		builder.setCResistanceFire(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceFireNext.toString()).get().getValue().floatValue());
		builder.setCResistanceBlast(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceBlastNext.toString()).get().getValue().floatValue());
		builder.setCResistanceBullet(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceBulletNext.toString()).get().getValue().floatValue());
		builder.setCResistanceKnife(pwPlayerItem.numberParamMap().get(EItemNumberParam.cResistanceKnifeNext.toString()).get().getValue().floatValue());
		builder.setCBloodAddAdd(pwPlayerItem.numberParamMap().get(EItemNumberParam.cBloodAddNext.toString()).get().getValue().floatValue());
		int maxHoleCount = 0;
		if (null != WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) wSysItem.getType())) {//只有武器，衣服，配饰可以开孔
			maxHoleCount = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) wSysItem.getType()).entrySet().stream()
					.filter(kv -> kv.getKey() <= pwPlayerItem.level().get() + 1).map(kv -> kv.getValue()).max((p1, p2) -> p1 - p2).orElse(0);
		}
		builder.setHolesNum(maxHoleCount);
		int color = WSysConfigService.getPlayerItemLevelColor().entrySet().stream().filter(kv -> kv.getKey() <= pwPlayerItem.level().get() + 1).map(kv -> kv.getValue()).max((p1, p2) -> p1 - p2)
				.orElse(EItemColor.TRANSPARENT.getNumber());
		builder.setColor(color);
		return builder.build();
	}

	public ProtoPlayerItemMeltingItem protoPlayerItemMeltingItem(WSysItem wSysItem) {
		ProtoPlayerItemMeltingItem.Builder builder = ProtoPlayerItemMeltingItem.newBuilder();
		builder.setId(wSysItem.getId());
		builder.setName(wSysItem.getName());
		builder.setDisplayName(wSysItem.getDisplayName());
		return builder.build();
	}

	public ProtoPlayerItemMeltingResult protoPlayerItemMeltingResult(WSysItem wSysItem, int unit, EUnitType unitType) {
		ProtoPlayerItemMeltingResult.Builder builder = ProtoPlayerItemMeltingResult.newBuilder();
		builder.setId(wSysItem.getId());
		builder.setName(wSysItem.getName());
		builder.setDisplayName(wSysItem.getDisplayName());
		builder.setUnit(unit);
		builder.setUnitType(unitType);
		return builder.build();
	}

	public ProtoCharacterData protoCharacterData(ProxyWCharacterData pwCharacterData, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap,
			Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap, Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap) throws Exception {
		ProtoCharacterData.Builder builder = ProtoCharacterData.newBuilder();
		WSysCharacter wSysCharacter = wSysCharacterService.findEntity(pwCharacterData.characterId().get());
		builder.setResourcename(wSysCharacter.getResourceName());
		builder.setName(wSysCharacter.getName());
		for (String str : wSysCharacter.getResourcePList()) {
			builder.addAvatar(str);
		}
		Map<Integer, Integer> map = new HashMap<>();//统计正在装备的道具的suitId
		for (ProxyWPlayerPack pwPlayerPack : pwPlayerPackMap.values()) {
			ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.get(pwPlayerPack.playerItemId().get());
			WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
			int tmpSuitId = wSysItem.getNeedTeamPlaceLevel();
			map.put(tmpSuitId, map.getOrDefault(tmpSuitId, 0) + 1);
		}
		//套装编号，从playerPack对应该角色已装备的服装类道具的sysItem.needTeamPlaceLevel(>99必须条件)获得，且该角色的所有已装备服装的sysItem.needTeamPlaceLevel必须相同
		int suitId = map.entrySet().stream().max((p1, p2) -> p1.getValue() - p2.getValue()).map(p -> p.getKey()).orElse(0);
		if (suitId > 99) {
			builder.setPutSuitId(suitId);
			builder.setSuitCount(map.get(suitId));
		}
		for (ProxyWPlayerPack pwPlayerPack : pwPlayerPackMap.values()) {
			if ("W".equals(pwPlayerPack.type().get())) {
				ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.get(pwPlayerPack.playerItemId().get());
				builder.addWeapons(protoPlayerItem(pwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap));
			}
		}
		return builder.build();
	}

	public ProtoPayment protoPayment(WPayment wPayment) {
		ProtoPayment.Builder builder = ProtoPayment.newBuilder();
		builder.setId(null == wPayment.getId() ? 0 : wPayment.getId());
		builder.setUnittypeValue(wPayment.getUnitType());
		builder.setUnit(wPayment.getUnit());
		builder.setCost(wPayment.getCost());
		return builder.build();
	}

	public ProtoSysAchievement protoSysAchievement(WSysAchievement wSysAchievement) {
		ProtoSysAchievement.Builder builder = ProtoSysAchievement.newBuilder();
		builder.setId(wSysAchievement.getId());
		builder.setName(wSysAchievement.getName());
		builder.setDescription(wSysAchievement.getDescription());
		builder.setTitle(wSysAchievement.getTitle());
		builder.setTypeValue(wSysAchievement.getType());
		builder.setNumber(wSysAchievement.getNumber());
		builder.setColorValue(wSysAchievement.getColor());
		builder.setSysCharacterId(wSysAchievement.getCharacterId());
		builder.setActionValue(wSysAchievement.getAction());
		builder.setStatTypeValue(wSysAchievement.getStatType());
		for (WPayment wPayment : wSysAchievement.getGiftList()) {
			builder.addGift(protoPayment(wPayment));
		}
		builder.setParent(Integer.parseInt(wSysAchievement.getParent()));
		builder.setGroup(wSysAchievement.getGroup());
		builder.setBackUp(wSysAchievement.getBackUp());
		return builder.build();
	}

	public ProtoSysItemPrice protoSysItemPrice(WSysItemPrice wSysItemPrice) {
		ProtoSysItemPrice.Builder builder = ProtoSysItemPrice.newBuilder();
		builder.setId(null == wSysItemPrice.getId() ? 0 : wSysItemPrice.getId());
		builder.setSysItemId(null == wSysItemPrice.getSysItemId() ? 0 : wSysItemPrice.getSysItemId());
		builder.setPayTypeValue(null == wSysItemPrice.getPayType() ? 0 : wSysItemPrice.getPayType());
		builder.setUnitTypeValue(null == wSysItemPrice.getUnitType() ? 0 : wSysItemPrice.getUnitType());
		builder.setCost(null == wSysItemPrice.getCost() ? 0 : wSysItemPrice.getCost());
		builder.setUnit(null == wSysItemPrice.getUnit() ? 0 : wSysItemPrice.getUnit());
		builder.setIsTarget(null == wSysItemPrice.getIsTarget() ? 0 : wSysItemPrice.getIsTarget());
		builder.setPayGroup(null == wSysItemPrice.getPayGroup() ? 0 : wSysItemPrice.getPayGroup());
		if (builder.getSysItemId() > 0) {
			try {
				WSysItem wSysItem = wSysItemService.findEntity(builder.getSysItemId());
				builder.setItemDisplayName("<SN" + builder.getSysItemId() + "^0>");
				builder.setItemName(wSysItem.getName());
			} catch (Exception e) {
				logger.warn("", e);
			}
		}
		return builder.build();
	}

	public ProtoPlayerAchievement ProtoPlayerAchievement(ProxyWPlayerAchievement pwPlayerAchievement) {
		ProtoPlayerAchievement.Builder builder = ProtoPlayerAchievement.newBuilder();
		builder.setId(pwPlayerAchievement.id().get());
		builder.setSysCharacterId(pwPlayerAchievement.get().getSysCharacterId());
		builder.setStatusValue(pwPlayerAchievement.status().get());
		builder.setGroup(pwPlayerAchievement.group().get());
		builder.setLevel(pwPlayerAchievement.level().get());
		pwPlayerAchievement.sysAchievementIdsList().forEach(p -> {
			builder.addSysAchievementId(p.get().getValue().intValue());
		});
		builder.setNumber(pwPlayerAchievement.number().get());
		builder.setBackUp(pwPlayerAchievement.backUp().isNull() ? "" : pwPlayerAchievement.backUp().get());
		return builder.build();
	}

	public ProtoSysQuest protoSysQuest(WSysQuest wSysQuest) {
		ProtoSysQuest.Builder builder = ProtoSysQuest.newBuilder();
		builder.setId(wSysQuest.getId());
		builder.setName(null == wSysQuest.getNameI18n() ? "" : wSysQuest.getNameI18n());
		builder.setTitle(null == wSysQuest.getTitleI18n() ? "" : wSysQuest.getTitleI18n());
		builder.setDesc(null == wSysQuest.getDescI18n() ? "" : wSysQuest.getDescI18n());
		builder.setIcon(null == wSysQuest.getIcon() ? "" : wSysQuest.getIcon());
		builder.setResource(null == wSysQuest.getResource() ? "" : wSysQuest.getResource());
		builder.setUiTypeValue(wSysQuest.getUiType());
		builder.setUiActionValue(wSysQuest.getUiAction());
		builder.setUiGroup(wSysQuest.getUiGroup());
		builder.setUiIndex(wSysQuest.getUiIndex());
		builder.setNumber(wSysQuest.getNumber());
		builder.setCompleteCount(wSysQuest.getCompleteCount());
		builder.setRepeatTypeValue(wSysQuest.getRepeatType());
		builder.setRepeatParam(wSysQuest.getRepeatParam());
		if (null != wSysQuest.getStartTime()) {
			String timeStr = "";
			switch (ERepeatType.forNumber(wSysQuest.getRepeatType())) {
			case EVERY_DAY:
			case EVERY_WEEK:
			case EVERY_MONTH:
			case DAY_OF_WEEK:
			case DAY_OF_MONTH:
				timeStr = DateUtil.formatToFlag(wSysQuest.getStartTime(), "hh:mm");
				break;
			case DESIGNATED_DAY:
				timeStr = DateUtil.formatToFlag(wSysQuest.getStartTime(), "yyyy-MM-dd hh:mm");
				break;
			default:
				break;
			}
			builder.setStartTime(timeStr);
		}
		if (null != wSysQuest.getEndTime()) {
			String timeStr = "";
			switch (ERepeatType.forNumber(wSysQuest.getRepeatType())) {
			case EVERY_DAY:
			case EVERY_WEEK:
			case EVERY_MONTH:
			case DAY_OF_WEEK:
			case DAY_OF_MONTH:
				timeStr = DateUtil.formatToFlag(wSysQuest.getEndTime(), "hh:mm");
				break;
			case DESIGNATED_DAY:
				timeStr = DateUtil.formatToFlag(wSysQuest.getEndTime(), "yyyy-MM-dd hh:mm");
				break;
			default:
				break;
			}
			builder.setStartTime(timeStr);
		}
		builder.setColorValue(wSysQuest.getColor());
		builder.setSysCharacterId(wSysQuest.getSysCharacterId());
		builder.setParentId(wSysQuest.getParentId());
		builder.setDifficulty(wSysQuest.getDifficulty());
		wSysQuest.getItemList().forEach(p -> builder.addItem(protoSysItemPrice(p)));
		wSysQuest.getVipItemList().forEach(p -> builder.addVipItem(protoSysItemPrice(p)));
		return builder.build();
	}

	public ProtoPlayerQuest protoPlayerQuest(ProxyWPlayerQuest pwPlayerQuest) {
		ProtoPlayerQuest.Builder builder = ProtoPlayerQuest.newBuilder();
		builder.setId(pwPlayerQuest.id().get());
		builder.setSysQuestId(pwPlayerQuest.sysQuestId().get());
		builder.setNumber(pwPlayerQuest.number().get());
		builder.setCompleteCount(pwPlayerQuest.completeCount().get());
		builder.setStatusValue(pwPlayerQuest.status().get());
		return builder.build();
	}

	/**
	 * @param pwPlayerMelting
	 * @param pwPlayerInfo
	 * @param previewCost 预览熔炼产出的花费
	 * @return
	 */
	public ProtoPlayerMelting protoPlayerMelting(ProxyWPlayerMelting pwPlayerMelting, ProxyWPlayerInfo pwPlayerInfo, int previewCost) {
		ProtoPlayerMelting.Builder builder = ProtoPlayerMelting.newBuilder();
		builder.setMeltingLevel(pwPlayerMelting.level().get());
		builder.setSlotNum(pwPlayerMelting.level().get() + 1);
		builder.setExp(pwPlayerMelting.exp().get());
		builder.setTotalExp(WSysConfigService.getPlayerItemCombineMelting().getLevelAndExp().getOrDefault(pwPlayerMelting.level().get() - 1, 0));
		builder.setMeltingEnergy(pwPlayerMelting.num().get());
		if (WSysConfigService.getPlayerItemCombineMelting().getDefalutMeltingEnergyNum() == pwPlayerMelting.num().get()) {
			builder.setRemaind(0);
		} else {
			int seconds = 0;
			if (pwPlayerMelting.recovery().get() > 0) {
				seconds = (int) (3 * 60 * 1000 - (System.currentTimeMillis() - pwPlayerMelting.startTime().get() + pwPlayerMelting.grandTotalTime().get())) / 1000;
			}
			builder.setRemaind(seconds);
		}
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		if (DateUtil.isSameDay(System.currentTimeMillis(), jsonCaches.getLastMeltingTime())) {
			builder.setRecycleRatio(100);
			if (jsonCaches.getMeltingCount() > WSysConfigService.getPlayerItemCombineMelting().getMeltingCountThreshold()) {
				builder.setRecycleRatio((int) (Math.pow(0.7, jsonCaches.getMeltingCount() / 10.0) * 100 + 0.49));
			}
		}
		builder.setPrice(previewCost);
		builder.setResidual(pwPlayerMelting.remaind().get().intValue());
		return builder.build();
	}

	/**
	 * 在线奖励
	 * @param pwPlayerQuests 玩家任务以及到任务完成的剩余秒数
	 * @param awardSysQuestIdList
	 * @param loginTime
	 * @return
	 * @throws Exception
	 */
	public ResponseOnlineAward ResponseOnlineAward(Map<ProxyWPlayerQuest, Long> pwPlayerQuests, List<Integer> awardSysQuestIdList, Date loginTime) throws Exception {
		ResponseOnlineAward.Builder builder = ResponseOnlineAward.newBuilder();
		for (ProxyWPlayerQuest pwPlayerQuest : pwPlayerQuests.keySet()) {
			WSysQuest wSysQuest = wSysQuestService.findEntity(pwPlayerQuest.sysQuestId().get());
			ProtoPlayerQuest.Builder b1 = protoPlayerQuest(pwPlayerQuest).toBuilder();
			b1.setNumber(pwPlayerQuests.getOrDefault(pwPlayerQuest, 0L));//调整计数
			ProtoSysQuest.Builder b2 = protoSysQuest(wSysQuest).toBuilder();
			Date date = DateUtil.add(loginTime, Calendar.SECOND, pwPlayerQuests.getOrDefault(pwPlayerQuest, 0L).intValue());
			b2.setDesc(MessageFormat.format(b2.getDesc(), DateUtil.formatToFlag(date, "HH:mm")));//调整文字描述
			builder.addPlayerQuest(b1.build());
			builder.addSysQuest(b2.build());
		}
		awardSysQuestIdList.forEach(p -> builder.addAwardSysQuestId(p));
		return builder.build();
	}
}
