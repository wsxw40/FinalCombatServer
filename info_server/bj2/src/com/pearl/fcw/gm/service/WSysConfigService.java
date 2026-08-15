package com.pearl.fcw.gm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysConfigDao;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysConfig;
import com.pearl.fcw.lobby.pojo.json.JsonCombineConvert;
import com.pearl.fcw.lobby.pojo.json.JsonCombineInsert;
import com.pearl.fcw.lobby.pojo.json.JsonCombineMelting;
import com.pearl.fcw.lobby.pojo.json.JsonItemDuration;
import com.pearl.fcw.lobby.pojo.json.JsonItemPackage;
import com.pearl.fcw.lobby.pojo.json.JsonNewerBuff;
import com.pearl.fcw.lobby.pojo.json.JsonUiPage;
import com.pearl.fcw.utils.JsonUtil;

/**
 * 系统设置
 */
@Service
public class WSysConfigService extends DmServiceImpl<WSysConfig, String> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysConfigDao wSysConfigDao;

	private static Map<String, Number> limitTimeThreshouldMap = new HashMap<>();
	private static int playerItemMaxStackSize = 300;
	private static Integer playerItemMaxGstLevel = 5;
	private static Map<Integer, Integer> playerItemLevelColor = new HashMap<>();
	//	private static Map<Integer, Map<Integer, Integer>> playerItemTypeLevelHoleMap = new HashMap<>();
	private static Map<Integer, Map<Integer, Float>> playerItemSlotRate = new HashMap<>();
	private static Map<Integer, Map<Integer, Map<Integer, Float>>> playerItemGstFactor = new HashMap<>();
	private static Map<Integer, Float> playerItemGstFactorItemSubType = new HashMap<>();
	private static List<Integer> sysItemIdLimitTime = new ArrayList<>();
	private static Map<Integer, List<Float>> sysItemDailyDiscount = new HashMap<>();
	private static Map<Integer, List<Float>> sysItemFightParam = new HashMap<>();
	private static Map<Integer, List<WPayment>> sysItemFirstBuyGift = new HashMap<>();
	private static Map<Integer, Integer> sysItemUseDelay = new HashMap<>();
	private static Map<Integer, List<Integer>> sysItemEnhancePart = new HashMap<>();
	private static Map<Integer, Map<String, Integer>> sysItemEnhanceLevel = new HashMap<>();
	private static Map<Integer, Integer> sysItemRareTypeRareLevel = new HashMap<>();
	private static Map<Integer, Integer> sysItemRareTypeGstExpInCombine = new HashMap<>();
	private static Map<Integer, Integer> sysItemRareTypeGpointInCombine = new HashMap<>();
	//	private static Map<Integer, Integer> shopItemTypePageSize = new HashMap<>();
	//private static Map<Integer, Integer> combineItemTypePageSize = new HashMap<>();
	private static List<Integer> combineFailAwardSysItemId = new ArrayList<>();
	private static List<List<Number>> competeBox = new ArrayList<>();
	private static float jigsawProperty = 0.165F;
	private static int teamMaxMemberCount = 100;
	private static Map<String, Map<Integer, Integer>> expLevel = new HashMap<>();
	private static Map<Integer, Map<Integer, Integer>> expIncrease = new HashMap<>();
	private static Map<Integer, Float> characterFightNumItemSubType = new HashMap<>();
	private static List<Integer> gmPlayerId = new ArrayList<>();
	private static Map<Integer, Integer> serverIdBrandCount = new HashMap<>();

	private static String clientSwitch = "";
	private static List<Integer> sysLevelNewTrainSysCharacterId = new ArrayList<>();

	private static JsonUiPage uiPage = new JsonUiPage();
	private static JsonItemPackage sysItemUseGift = new JsonItemPackage();
	private static JsonItemDuration playerItemDuration = new JsonItemDuration();
	private static JsonCombineConvert playerItemCombineConvert = new JsonCombineConvert();
	private static JsonCombineInsert playerItemCombineInsert = new JsonCombineInsert();
	private static JsonCombineMelting playerItemCombineMelting = new JsonCombineMelting();
	private static JsonNewerBuff newerBuff = new JsonNewerBuff();

	@PostConstruct
	public void init() {
		this.genDao = wSysConfigDao;
		try {
			initConstants();
		} catch (Exception e) {
			logger.error(getClass() + " init error : ", e);
		}
	}

	/**
	 * 系统常量初始化
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void initConstants() throws Exception {
		WSysConfig m = null;
		Map<String, WSysConfig> configs = findMap(null);
		//客户端UI的功能开关
		m = configs.get("client.switch");
		if (null != m) {
			clientSwitch = null == m.getValue() ? "" : m.getValue();
		}
		//玩家道具的开槽成功率
		m = configs.get("playerItem.slotRate");
		if (null != m) {
			Map<String, Map<String, Object>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Map<Integer, Float>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
				return Integer.parseInt(kv.getKey());
			}, kv -> {
				Map<Integer, Float> map1 = kv.getValue().entrySet().stream().collect(Collectors.toMap(kv1 -> {
					return Integer.parseInt(kv1.getKey());
				}, kv1 -> {
					return Float.parseFloat(kv1.getValue().toString());
				}));
				return map1;
			}));
			playerItemSlotRate.clear();
			playerItemSlotRate.putAll(mapAdd);
		}
		//限时的系统物品ID
		m = configs.get("sysItem.id.limitTime");
		if (null != m) {
			List<Integer> list = JsonUtil.readValue(m.getValue(), List.class);
			sysItemIdLimitTime.clear();
			sysItemIdLimitTime.addAll(list);
		}
		//限时道具的时间阈值
		m = configs.get("sysItem.limitTime.threshould");
		if (null != m) {
			Map<String, Number> map = JsonUtil.readValue(m.getValue(), Map.class);
			limitTimeThreshouldMap.clear();
			limitTimeThreshouldMap.putAll(map);
		}
		//一般玩家物品的堆叠数量上限
		m = configs.get("playerItem.maxStackSize");
		if (null != m) {
			playerItemMaxStackSize = Integer.parseInt(m.getValue());
		}
		//玩家道具的星级
		m = configs.get("playerItem.maxGstLevel");
		if (null != m) {
			playerItemMaxGstLevel = Integer.parseInt(m.getValue());
		}
		//每日折扣的系统物品ID
		m = configs.get("sysItem.daily.discount");
		if (null != m) {
			Map<String, List<Object>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, List<Float>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
				return Integer.parseInt(kv.getKey());
			}, kv -> {
				return kv.getValue().stream().map(p -> Float.parseFloat(p.toString())).collect(Collectors.toList());
			}));
			sysItemDailyDiscount.clear();
			sysItemDailyDiscount.putAll(mapAdd);
		}
		//玩家物品.强化等级.颜色关系
		m = configs.get("playerItem.level.color");
		if (null != m) {
			Map<String, Object> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
				return Integer.parseInt(kv.getKey());
			}, kv -> {
				return Integer.parseInt(kv.getValue().toString());
			}));
			playerItemLevelColor.clear();
			playerItemLevelColor.putAll(mapAdd);
		}
		//系统道具伤害和防御值参数
		m = configs.get("sysItem.fightParam");
		if (null != m) {
			Map<String, List<Object>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, List<Float>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
				return Integer.parseInt(kv.getKey());
			}, kv -> {
				return kv.getValue().stream().map(p -> Float.parseFloat(p.toString())).collect(Collectors.toList());
			}));
			sysItemFightParam.clear();
			sysItemFightParam.putAll(mapAdd);
		}
		//		//商城中系统道具类型和的每页显示数量的关系
		//		m = configs.get("shop.itemType.pageSize");
		//		if (null != m) {
		//			Map<String, Object> map = JsonUtil.readValue(m.getValue(), Map.class);
		//			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
		//				return Integer.parseInt(kv.getKey());
		//			}, kv -> {
		//				return Integer.parseInt(kv.getValue().toString());
		//			}));
		//			shopItemTypePageSize.clear();
		//			shopItemTypePageSize.putAll(mapAdd);
		//		}
		//		//合成界面的道具类型和每页显示数量的关系
		//		m = configs.get("combine.itemType.pageSize");
		//		if (null != m) {
		//			Map<String, Object> map = JsonUtil.readValue(m.getValue(), Map.class);
		//			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
		//				return Integer.parseInt(kv.getKey());
		//			}, kv -> {
		//				return Integer.parseInt(kv.getValue().toString());
		//			}));
		//			combineItemTypePageSize.clear();
		//			combineItemTypePageSize.putAll(mapAdd);
		//		}
		//合成失败后返回的系统道具ID
		m = configs.get("combine.failAward.sysItemId");
		if (null != m) {
			List<Integer> list = JsonUtil.readValue(m.getValue(), List.class);
			combineFailAwardSysItemId.clear();
			combineFailAwardSysItemId.addAll(list);
		}
		//首次购买某种道具的赠品
		m = configs.get("sysItem.firstBuy.gift");
		if (null != m) {
			Map<String, List<WPayment>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, List<WPayment>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> {
				return Integer.parseInt(kv.getKey());
			}, kv -> {
				return kv.getValue();
			}));
			sysItemFirstBuyGift.clear();
			sysItemFirstBuyGift.putAll(mapAdd);
		}
		//使用道具后获得的赠品
		m = configs.get("sysItem.use.gift");
		if (null != m) {
			sysItemUseGift = JsonUtil.readValue(m.getValue(), JsonItemPackage.class);
		}
		//某些物品要获得一段时间后才能使用(秒)
		m = configs.get("sysItem.use.delay");
		if (null != m) {
			Map<String, Integer> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemUseDelay.clear();
			sysItemUseDelay.putAll(mapAdd);
		}
		//用于合成的强化零件
		m = configs.get("sysItem.enhancePart");
		if (null != m) {
			Map<String, List<Integer>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, List<Integer>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemEnhancePart.clear();
			sysItemEnhancePart.putAll(mapAdd);
		}
		//系统物品强化等级限制
		m = configs.get("sysItem.enhanceLevel");
		if (null != m) {
			Map<String, Map<String, Integer>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Map<String, Integer>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemEnhanceLevel.clear();
			sysItemEnhanceLevel.putAll(mapAdd);
		}
		//道具根据稀有度类型、星级以及道具类型获取属性增强的系数
		m = configs.get("playerItem.gstFactor");
		if (null != m) {
			Map<String, Map<String, Map<String, Number>>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Map<Integer, Map<Integer, Float>>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> {
				Map<Integer, Map<Integer, Float>> map1 = kv.getValue().entrySet().stream().collect(Collectors.toMap(kv1 -> Integer.parseInt(kv1.getKey()), kv1 -> {
					Map<Integer, Float> map2 = kv1.getValue().entrySet().stream().collect(Collectors.toMap(kv2 -> Integer.parseInt(kv2.getKey()), kv2 -> {
						float value = kv2.getValue().floatValue();
						return value;
					}));
					return map2;
				}));
				return map1;
			}));
			playerItemGstFactor.clear();
			playerItemGstFactor.putAll(mapAdd);
		}
		//和道具星级导致的属性增加相关的道具子类型系数
		m = configs.get("playerItem.gstFactor.itemSubType");
		if (null != m) {
			Map<String, Number> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Float> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> {
				return kv.getValue().floatValue();
			}));
			playerItemGstFactorItemSubType.clear();
			playerItemGstFactorItemSubType.putAll(mapAdd);
		}
		//道具稀有度和rareLevel字段的关系
		m = configs.get("sysItem.rareType.rareLevel");
		if (null != m) {
			Map<String, Integer> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemRareTypeRareLevel.clear();
			sysItemRareTypeRareLevel.putAll(mapAdd);
		}
		//道具合成时根据稀有度类型每日免费获得的星级经验
		m = configs.get("sysItem.rareType.gstExpInCombine");
		if (null != m) {
			Map<String, Integer> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemRareTypeGstExpInCombine.clear();
			sysItemRareTypeGstExpInCombine.putAll(mapAdd);
		}
		//道具合成时根据稀有度类型获取花费
		m = configs.get("sysItem.rareType.gPointInCombine");
		if (null != m) {
			Map<String, Integer> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			sysItemRareTypeGpointInCombine.clear();
			sysItemRareTypeGpointInCombine.putAll(mapAdd);
		}
		//TODO 限时竞购箱子[[@WSysItem.id,@WSysItem.id,@EUnitType,@WPayment.unit],[]...]
		//玩家获得拼图的概率
		m = configs.get("jigsaw.property");
		if (null != m) {
			jigsawProperty = Float.parseFloat(m.getValue());
		}
		//战队最大成员数量
		m = configs.get("team.maxMemberCount");
		if (null != m) {
			teamMaxMemberCount = Integer.parseInt(m.getValue());
		}
		//VIP等级和经验的关系
		m = configs.get("exp.level");
		if (null != m) {
			Map<String, Map<String, Integer>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<String, Map<Integer, Integer>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> kv.getKey(), kv -> {
				Map<Integer, Integer> tmp = kv.getValue().entrySet().stream().collect(Collectors.toMap(kv1 -> Integer.parseInt(kv1.getKey()), kv1 -> kv1.getValue()));
				return tmp;
			}));
			expLevel.clear();
			expLevel.putAll(mapAdd);
		}
		//经验增长方式
		m = configs.get("exp.increase");
		if (null != m) {
			Map<String, Map<String, Integer>> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Map<Integer, Integer>> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> {
				Map<Integer, Integer> tmp = kv.getValue().entrySet().stream().collect(Collectors.toMap(kv1 -> Integer.parseInt(kv1.getKey()), kv1 -> kv1.getValue()));
				return tmp;
			}));
			expIncrease.clear();
			expIncrease.putAll(mapAdd);
		}
		//玩家角色战斗力乘数和道具类型子类型的关系
		m = configs.get("character.fightNum.itemSubType");
		if (null != m) {
			Map<String, Number> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Float> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue().floatValue()));
			characterFightNumItemSubType.clear();
			characterFightNumItemSubType.putAll(mapAdd);
		}
		//GM玩家ID
		m = configs.get("gm.playerId");
		if (null != m) {
			List<Integer> list = JsonUtil.readValue(m.getValue(), List.class);
			gmPlayerId.clear();
			gmPlayerId.addAll(list);
		}
		//过关结算翻牌的地图类型和出牌数量的关系
		m = configs.get("sysLevel.type.brandCount");
		if (null != m) {
			Map<String, Integer> map = JsonUtil.readValue(m.getValue(), Map.class);
			Map<Integer, Integer> mapAdd = map.entrySet().stream().collect(Collectors.toMap(kv -> Integer.parseInt(kv.getKey()), kv -> kv.getValue()));
			serverIdBrandCount.clear();
			serverIdBrandCount.putAll(mapAdd);
		}

		//地图-新手关限定角色ID
		m = configs.get("sysLevel.newTrain.sysCharacterId");
		if (null != m) {
			List<Integer> list = JsonUtil.readValue(m.getValue(), List.class);
			sysLevelNewTrainSysCharacterId.clear();
			sysLevelNewTrainSysCharacterId.addAll(list);
		}
		//UI分页设置
		m = configs.get("uiPage");
		if (null != m) {
			uiPage = JsonUtil.readValue(m.getValue(), JsonUiPage.class);
		}
		//道具耐久配置
		m = configs.get("playerItem.duration");
		if (null != m) {
			playerItemDuration = JsonUtil.readValue(m.getValue(), JsonItemDuration.class);
		}
		//合成-转换配置
		m = configs.get("playerItem.combine.convert");
		if (null != m) {
			playerItemCombineConvert = JsonUtil.readValue(m.getValue(), JsonCombineConvert.class);
		}
		//合成-镶嵌配置
		m = configs.get("playerItem.combine.insert");
		if (null != m) {
			playerItemCombineInsert = JsonUtil.readValue(m.getValue(), JsonCombineInsert.class);
		}
		//合成-熔炼配置
		m = configs.get("playerItem.combine.melting");
		if (null != m) {
			playerItemCombineMelting = JsonUtil.readValue(m.getValue(), JsonCombineMelting.class);
		}
		//新手保护Buff参数
		m = configs.get("newer.buff");
		if (null != m) {
			newerBuff = JsonUtil.readValue(m.getValue(), JsonNewerBuff.class);
		}
	}


	/**
	 * 客户端UI的功能开关
	 * @return
	 */
	public static String getClientSwitch() {
		return clientSwitch;
	}

	/**
	 * 限时的系统物品ID
	 * <@WsysItem.id>
	 */
	public static List<Integer> getSysItemIdLimitTime() {
		return sysItemIdLimitTime;
	}

	/**
	 * 一般玩家物品的堆叠数量上限
	 */
	public static int getPlayerItemMaxStackSize() {
		return playerItemMaxStackSize;
	}

	/**
	 * 每日折扣的系统物品ID
	 * <@WsysItem.id,<多种折扣率>>
	 */
	public static Map<Integer, List<Float>> getSysItemDailyDiscount() {
		return sysItemDailyDiscount;
	}

	/**
	 * 玩家物品.强化等级.颜色关系
	 * <@WPlayerItem.level,@EItemColor>
	 */
	public static Map<Integer, Integer> getPlayerItemLevelColor() {
		return playerItemLevelColor;
	}

	/**
	 * 系统道具战斗力参数<br/> {@EItemSubType,{Float}
	 */
	public static Map<Integer, List<Float>> getSysItemFightParam() {
		return sysItemFightParam;
	}

	//	/**
	//	 * 商城中系统道具类型和的每页显示数量的关系
	//	 * <@EItemType,size>
	//	 */
	//	public static Map<Integer, Integer> getShopItemTypePageSize() {
	//		return shopItemTypePageSize;
	//	}

	/**
	 * 限时道具的时间阈值
	 */
	public static Map<String, Number> getLimitTimeThreshouldMap() {
		return limitTimeThreshouldMap;
	}

	/**
	 * 限时竞购箱子[[@WSysItem.id,@WSysItem.id,@EUnitType,@WPayment.unit],[]...]
	 */
	public static List<List<Number>> getCompeteBox() {
		return competeBox;
	}

	/**
	 * 首次购买某种道具的赠品
	 * <@WsysItem.id,List<@WPayment>>
	 * @return
	 */
	public static Map<Integer, List<WPayment>> getSysItemFirstBuyGift() {
		return sysItemFirstBuyGift;
	}

	/**
	 * 用于合成的强化零件
	 * <Integer,<@WSysItem.id>>
	 */
	public static Map<Integer, List<Integer>> getSysItemEnhancePart() {
		return sysItemEnhancePart;
	}

	/**
	 * 系统物品强化等级限制<br/>
	 * {enhanceLevel,{"player"|"vip",level}}
	 */
	public static Map<Integer, Map<String, Integer>> getSysItemEnhanceLevel() {
		return sysItemEnhanceLevel;
	}

	/**
	 * 使用道具后获得赠品
	 * @return
	 */
	public static JsonItemPackage getSysItemUseGift() {
		return sysItemUseGift;
	}

	/**
	 * 玩家获得拼图的概率
	 * @return
	 */
	public static float getJigsawProperty() {
		return jigsawProperty;
	}

	/**
	 * 战队最大成员数量
	 * @return
	 */
	public static int getTeamMaxMemberCount() {
		return teamMaxMemberCount;
	}

	/**
	 * 等级和经验的关系<br/>
	 * {"player"|"vip",{level,exp}}
	 * @return
	 */
	public static Map<String, Map<Integer, Integer>> getExpLevel() {
		return expLevel;
	}

	/**
	 * 某些物品要获得一段时间后才能使用(秒)<br/>
	 * {sysItemId,second}
	 * @return
	 */
	public static Map<Integer, Integer> getSysItemUseDelay() {
		return sysItemUseDelay;
	}

	/**
	 * 经验增长方式<br/> {@EExpIncreaseType,{该方式的当天已使用次数,增长经验}
	 * @return
	 */
	public static Map<Integer, Map<Integer, Integer>> getExpIncrease() {
		return expIncrease;
	}

	/**
	 * 玩家角色战斗力乘数和道具类型子类型的关系<br/> {@EItemSubType,factor}
	 * @return
	 */
	public static Map<Integer, Float> getCharacterFightNumItemSubType() {
		return characterFightNumItemSubType;
	}

	/**
	 * GM玩家ID
	 * @return
	 */
	public static List<Integer> getGmPlayerId() {
		return gmPlayerId;
	}

	/**
	 * 道具稀有度和rareLevel字段的关系<br/> {@EItemRareType,sysItem.rareLevel}
	 * @return
	 */
	public static Map<Integer, Integer> getSysItemRareTypeRareLevel() {
		return sysItemRareTypeRareLevel;
	}

	/**
	 * 玩家道具的最大星级
	 * @return
	 */
	public static Integer getPlayerItemMaxGstLevel() {
		return playerItemMaxGstLevel;
	}

	/**
	 * 道具合成时根据稀有度类型每日免费获得的星级经验
	 * @return
	 */
	public static Map<Integer, Integer> getSysItemRareTypeGstExpInCombine() {
		return sysItemRareTypeGstExpInCombine;
	}

	/**
	 * 道具合成时根据稀有度类型获取花费
	 * @return
	 */
	public static Map<Integer, Integer> getSysItemRareTypeGpointInCombine() {
		return sysItemRareTypeGpointInCombine;
	}

	//	/**
	//	 * 合成界面的道具类型和每页显示数量的关系
	//	 * @return
	//	 */
	//	public static Map<Integer, Integer> getCombineItemTypePageSize() {
	//		return combineItemTypePageSize;
	//	}

	/**
	 * 合成失败后返回的系统道具ID
	 * @return
	 */
	public static List<Integer> getCombineFailAwardSysItemId() {
		return combineFailAwardSysItemId;
	}

	/**
	 * 玩家道具的开槽成功率{@EItemType,{slotCount,rate}
	 * @return
	 */
	public static Map<Integer, Map<Integer, Float>> getPlayerItemSlotRate() {
		return playerItemSlotRate;
	}

	/**
	 * server表ID和出牌数量的关系
	 * @return {serverId,brandCount}
	 */
	public static Map<Integer, Integer> getServerIdBrandCount() {
		return serverIdBrandCount;
	}

	/**
	 * 道具根据稀有度类型、星级以及道具类型获取属性增强的系数<br/>
	 * @return {@EItemRareType,{playerItem.gstLevel,{@EItemType,参数}
	 */
	public static Map<Integer, Map<Integer, Map<Integer, Float>>> getPlayerItemGstFactor() {
		return playerItemGstFactor;
	}

	/**
	 * 和道具星级导致的属性增加相关的道具子类型系数<br/>{@EItemSubType,factor}
	 * @return
	 */
	public static Map<Integer, Float> getPlayerItemGstFactorItemSubType() {
		return playerItemGstFactorItemSubType;
	}

	/**
	 * 道具耐久配置
	 * @return
	 */
	public static JsonItemDuration getPlayerItemDuration() {
		return playerItemDuration;
	}

	/**
	 * 合成-转换配置
	 * @return
	 */
	public static JsonCombineConvert getPlayerItemCombineConvert() {
		return playerItemCombineConvert;
	}

	/**
	 * 合成-镶嵌配置
	 * @return
	 */
	public static JsonCombineInsert getPlayerItemCombineInsert() {
		return playerItemCombineInsert;
	}

	/**
	 * 合成-熔炼配置
	 * @return
	 */
	public static JsonCombineMelting getPlayerItemCombineMelting() {
		return playerItemCombineMelting;
	}

	/**
	 * 新手保护Buff参数
	 * @return
	 */
	public static JsonNewerBuff getNewerBuff() {
		return newerBuff;
	}

	/**
	 * 地图-新手关限定角色ID
	 * @return
	 */
	public static List<Integer> getSysLevelNewTrainSysCharacterId() {
		return sysLevelNewTrainSysCharacterId;
	}

	/**
	 * UI分页设置
	 * @return
	 */
	public static JsonUiPage getUiPage() {
		return uiPage;
	}

}
