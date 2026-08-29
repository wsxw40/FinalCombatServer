package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.pojo.DelayMessage;
import com.pearl.fcw.core.service.Delayed;
import com.pearl.fcw.gm.dao.WPaymentDao;
import com.pearl.fcw.gm.dao.WSysConfigDao;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.dao.WSysOnlineAwardDao;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.pojo.WSysOnlineAward;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerTeam;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeam;
import com.pearl.fcw.proto.enums.EItemIBuffId;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EOnlineAwardType;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.ETeamJob;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.RandomUnit;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.DataTooLongException;
import com.pearl.o2o.exception.IllegalCharacterException;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;

/**
 * 因道具的使用业务逻辑复杂，从wPlayerService中分离出来的功能块
 */
@Service
public class ItemService {
    @Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private WPaymentDao wPaymentDao;
    @Resource
    private WSysOnlineAwardDao wSysOnlineAwardDao;
    @Resource
    private WSysConfigDao wSysConfigDao;
    @Resource
    private WPlayerService wPlayerService;
    @Resource
    private WTeamService wTeamService;
    @Resource
	private Delayed delayed;
	@Resource
    private SocketClientNew socketClientNew;

    /**
	 * 向玩家发送物品。未触发新增WMessage，不扣除任何种类的游戏币<br/>
	 * @param pwPlayer
	 * @param wSysItem
	 * @param wPayment
	 *            用于确定发送物品的数量或者时效。为null时，数量或者时效由payment表确定。wPayment内unityType属性如果违背SysItem的数量或者时效规则，
	 *            将重新设置为符合SysItem的数量或者时效规则
	 * @param isGift 是否作为（玩家A）礼物赠送给对象玩家（玩家B）
	 * @param isBind 是否将物品绑定在对象玩家身上（该玩家不可将该赠送的物品转赠给其他玩家）
	 * @param isDefault 是否作为默认物品
	 * @throws Exception
	 */
    public ProxyWPlayerItem sendPlayerItem(final ProxyWPlayer pwPlayer, final WSysItem wSysItem, WPayment wPayment, boolean isGift, boolean isBind, boolean isDefault) throws Exception {
		//部分特殊物品必须绑定玩家(限时物品和新手升级包)
        if (WSysConfigService.getSysItemIdLimitTime().contains(wSysItem.getId())) {
            isBind = true;
        }
        if (wSysItem.getiId() == EItemIId.GIFT_BAG_UPDATE_FOR_FRESHMAN.getNumber()) {
            isBind = true;
        }
		//如果没有确定物品的发送数量和发送类型，将由Payment表来决定
        if (null == wPayment) {
			//先后以游戏币、迅雷点、代金券三种支付类型来决定发送数量和物品消耗类型
            wPayment = wPaymentDao.findList(null).stream().filter(p -> p.getItemId() == wSysItem.getId() && p.getPayType() == EPayType.PAY_GP.getNumber() && p.getIsShow() > 0)
                    .min((p1, p2) -> p1.getCost() - p2.getCost()).orElse(null);
            if (null == wPayment) {
				wPayment = wPaymentDao.findList(null).stream().filter(p -> p.getItemId() == wSysItem.getId() && p.getPayType() == EPayType.PAY_FC_POINT.getNumber() && p.getIsShow() > 0)
                        .min((p1, p2) -> p1.getCost() - p2.getCost()).orElse(null);
            }
            if (null == wPayment) {
                wPayment = wPaymentDao.findList(null).stream().filter(p -> p.getItemId() == wSysItem.getId() && p.getPayType() == EPayType.PAY_VOUCHER.getNumber() && p.getIsShow() > 0)
                        .min((p1, p2) -> p1.getCost() - p2.getCost()).orElse(null);
            }
			//以上三种支付类型都无法找到对应的发送数量和物品消耗类型，固定发送数量1，物品消耗类型为永久
            if (null == wPayment) {
                wPayment = new WPayment();
                wPayment.setUnit(1);
                wPayment.setUnitType(EUnitType.FOREVER.getNumber());
            }
        }

		//确定该玩家物品是否可进行数量堆叠
        boolean isPile = false;
		if (!isGift) {//发送的系统物品不当做礼物的前提下，物品类型为素材、开放类，或者道具类中的消耗类型，该玩家物品数量可堆叠
            switch (EItemType.forNumber(wSysItem.getType())) {
            case MATERIAL:
            case OPEN:
                isPile = true;
                break;
            case ITEM:
                switch (EItemSubType.forNumber(EItemType.ITEM.getNumber() * ItemSubType.SEP + wSysItem.getSubType())) {
                case ITEM_CONSUME:
                    isPile = true;
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
		} else {//发送的系统物品当做礼物的前提下，物品类型为资源争夺战，物品子类型为个人消耗或者个人技能，该玩家物品数量可堆叠
            switch (EItemType.forNumber(wSysItem.getType())) {
            case BATTLE_FOR_RESOURCE:
                switch (EItemSubType.forNumber(EItemType.ITEM.getNumber() * ItemSubType.SEP + wSysItem.getSubType())) {
                case BFR_PERSONAL_CONSUME:
                case BFR_PERSONAL_SKILL:
                    isPile = true;
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
        }

		//根据是否可堆叠，确定PlayerItem数据是更新或者新增
        Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(pwPlayer.id().get());
        ProxyWPlayerItem pwPlayerItem = null;
		if (!isPile) {//玩家物品数量不可堆叠
			if (wPayment.getUnitType() == EUnitType.TIME.getNumber()) {//玩家存在该道具，续费；不存在该道具，新增
                pwPlayerItem = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == wSysItem.getId()).findFirst().orElse(null);
                if (null == pwPlayerItem) {
                    pwPlayerItem = wPlayerService.addProxyWPlayerItem(pwPlayer.id().get(), wSysItem.getId(), wPayment.getUnit(), wPayment.getUnitType(), isGift, isBind, isDefault);
                } else {
                    pwPlayerItem.expireTime().increase(wPayment.getUnit() * 1000 * 60 * 60 * 24);
                }
			} else {//新增道具
                pwPlayerItem = wPlayerService.addProxyWPlayerItem(pwPlayer.id().get(), wSysItem.getId(), wPayment.getUnit(), wPayment.getUnitType(), isGift, isBind, isDefault);
            }
		} else {//玩家物品数量可堆叠
            List<ProxyWPlayerItem> pwPlayerItemList = pwPlayerItemMap.values().stream().filter(p -> p.itemId().get() == wSysItem.getId()).sorted((p1, p2) -> p2.quantity().get() - p1.quantity().get())
                    .collect(Collectors.toList());
			//系统物品类型为资源争夺战类，玩家物品数量堆叠上限Integer.max，否则为系统设置
            int maxStackSize = wSysItem.getType() == EItemType.BATTLE_FOR_RESOURCE.getNumber() ? Integer.MAX_VALUE : WSysConfigService.getPlayerItemMaxStackSize();
			if (pwPlayerItemList.isEmpty()) {//玩家未持有该系统物品
                List<Integer> stackList = wPlayerService.getPlayerItemStackList(0, wPayment.getUnit(), maxStackSize);
                for (int i : stackList) {
                    pwPlayerItem = wPlayerService.addProxyWPlayerItem(pwPlayer.id().get(), wSysItem.getId(), i, wPayment.getUnitType(), isGift, isBind, isDefault);
                }
			} else {//玩家持有该系统物品
                long totalCount = pwPlayerItemList.stream().map(p -> (long) p.quantity().get()).reduce(0L, (p, sum) -> p + sum, (sum1, sum2) -> sum1 + sum2);
                List<Integer> stackList = wPlayerService.getPlayerItemStackList(totalCount, wPayment.getUnit(), maxStackSize);
                for (int i = 0; i < stackList.size(); i++) {
                    if (i < pwPlayerItemList.size()) {
                        pwPlayerItem = pwPlayerItemList.get(i);
                        pwPlayerItem.quantity().set(stackList.get(i));
					} else {//计算得到的堆叠数超过原有堆叠数，需新增数据
                        pwPlayerItem = wPlayerService.addProxyWPlayerItem(pwPlayer.id().get(), wSysItem.getId(), stackList.get(i), wPayment.getUnitType(), isGift, isBind, isDefault);
                    }
                }
            }
        }

		//发送的物品不是礼物，且系统物品类型不是资源争夺战，而且该系统物品iBuffId不属于指定iBuffId类型；则向玩家推送消息
        boolean isPushMsg = true;
        if (!isGift && EItemType.BATTLE_FOR_RESOURCE.getNumber() != wSysItem.getType()) {
            switch (EItemIBuffId.forNumber(wSysItem.getiBuffId())) {
            case UNLOCK_UPDATE_LEVEL:
            case INCREASE_UPDATE_SUCCESS_PROBABILITY:
            case INCREASE_ATTACK:
            case INCREASE_MAX_HP:
            case INCREASE_C_COIN_INCOME:
            case INCREASE_EXP_INCOME:
            case INCREASE_AMMO_INCOME:
            case INCREASE_HP_DRUG_INCOME:
            case REDUCE_SELF_INJURE_BY_ROCKET_SOLDIER:
            case SHORTEN_COOLING_TIME_BY_MACHINE_GUN_SOLDIER:
            case SHORTEN_COOLING_TIME_BY_SNIPE_SOLDIER:
            case SHORTEN_COOLING_TIME_BY_ASSAULT_SOLDIER:
            case INCREASE_MAX_AMMO_BY_FLAME_SOLDIER:
            case SHORTEN_COOLING_TIME_BY_MEDICAL_SOLDIER:
                isPushMsg = false;
                break;
            default:
                break;
            }
        }
        if (isPushMsg) {
            socketClientNew.puchCMDtoClient(pwPlayer.name().get(), CommonUtil.messageFormat(CommonMsg.HIGHLIGHT_STORAGE, wSysItem.getType(), wSysItem.getSubType()));
        }

        return pwPlayerItem;
    }


	/**
	 * 从多对多交易表执行支付或者获取<br/>
	 * 无广播和邮件提示<br/>
	 * 玩家持有数量不足以支付时会抛出异常<br/>
	 * 获取交易目标后，非道具玩家持有上限Integer.MAX，道具持有上限看sysConfig表stack相关
	 * @param playerId
	 * @param wSysItemPrices
	 * @throws Exception
	 */
	public void earnOrPayItemPrice(int playerId, Collection<WSysItemPrice> wSysItemPrices) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		for (WSysItemPrice wSysItemPrice : wSysItemPrices) {
			if (pwPlayer.rank().lt(wSysItemPrice.getLevel()) || pwPlayer.isVip().lt(wSysItemPrice.getVipLevel())) {//等级限制
				continue;
			}
			if (wSysItemPrice.getIsTarget() > 0) {//作为多对多的交易目标
				earnItemPrice(wSysItemPrice, pwPlayer, pwPlayerInfo, pwPlayerItemMap);
			} else if (wSysItemPrice.getPayGroup() > 0) {//作为多对多的支付对象
				payItemPrice(wSysItemPrice, pwPlayer, pwPlayerInfo, pwPlayerItemMap);
			} else {//一对一交易，既是交易目标也是支付对象(交易目标必定是道具，支付对象必定不是道具)
				payItemPrice(wSysItemPrice, pwPlayer, pwPlayerInfo, pwPlayerItemMap);
				WSysItemPrice wSysItemPrice2 = (WSysItemPrice) wSysItemPrice.clone();
				wSysItemPrice2.setPayType(EPayType.PAY_ITEM.getNumber());
				earnItemPrice(wSysItemPrice2, pwPlayer, pwPlayerInfo, pwPlayerItemMap);
			}
		}
		//走多对多交易表要增加延迟处理信息
		DelayMessage msg = new DelayMessage();
		msg.setKey(playerId + "");
		msg.setData(wSysItemPrices);
		delayed.addMsg(msg);
	}

	/**
	 * 支付<br/>
	 * 该方法仅用于支援earnOrPayItemPrice方法
	 * @param wSysItemPrice
	 * @param pwPlayer
	 * @param pwPlayerInfo
	 * @param pwPlayerItemMap
	 * @throws Exception
	 */
	private void payItemPrice(WSysItemPrice wSysItemPrice, ProxyWPlayer pwPlayer, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap) throws Exception {
		switch (EPayType.forNumber(wSysItemPrice.getPayType())) {
		case PAY_GP://C币
			if (pwPlayer.gPoint().lt(wSysItemPrice.getCost())) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
			}
			if (wSysItemPrice.getCost() <= 0) {
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " cost is error .");
			}
			pwPlayer.gPoint().increase(-wSysItemPrice.getCost());
			break;
		case PAY_FC_POINT://FC点
			if (pwPlayerInfo.xunleiPoint().lt(wSysItemPrice.getCost())) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_CR);
			}
			if (wSysItemPrice.getCost() <= 0) {
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " cost is error .");
			}
			pwPlayerInfo.xunleiPoint().increase(-wSysItemPrice.getCost());
			break;
		case PAY_ITEM://道具
			if (wSysItemPrice.getUnitType() != EUnitType.QUANTITY.getNumber()) {//作为支付对象却不是以数量为单位，抛出异常
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " unitType is error .");
			}
			if (wSysItemPrice.getUnit() <= 0) {
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " unit is error .");
			}
			List<ProxyWPlayerItem> pwPlayerItemList = pwPlayerItemMap.values().stream().filter(p -> {//找到对应sysItemId的玩家道具，注意按照数量升序排列
						return p.itemId().eq(wSysItemPrice.getSysItemId());
					}).sorted((p1, p2) -> p1.quantity().get() - p2.quantity().get()).collect(Collectors.toList());
			int count = pwPlayerItemList.stream().map(p -> p.quantity().get()).reduce((p1, p2) -> p1 + p2).orElse(0);
			if (count < wSysItemPrice.getUnit()) {//玩家道具数量不足
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_ITEM);
			}
			int quantity = wSysItemPrice.getUnit();
			for (ProxyWPlayerItem p : pwPlayerItemList) {//按照已堆叠道具的数量从少往多扣除
				if (quantity == 0) {
					break;
				}
				if (p.quantity().lt(quantity)) {
					p.quantity().increase(-p.quantity().get());
					quantity -= p.quantity().get();
				} else {
					p.quantity().increase(-quantity);
					quantity = 0;
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 获取<br/>
	 * 该方法仅用于支援earnOrPayItemPrice方法
	 * @param wSysItemPrice
	 * @param pwPlayer
	 * @param pwPlayerInfo
	 * @param pwPlayerItemMap
	 * @throws Exception
	 */
	private void earnItemPrice(WSysItemPrice wSysItemPrice, ProxyWPlayer pwPlayer, ProxyWPlayerInfo pwPlayerInfo, Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap) throws Exception {
		switch (EPayType.forNumber(wSysItemPrice.getPayType())) {
		case PAY_GP://C币
			if (wSysItemPrice.getCost() <= 0) {
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " cost is error .");
			}
			if (0L + pwPlayer.gPoint().get() + wSysItemPrice.getCost() > Integer.MAX_VALUE) {
				pwPlayer.gPoint().set(Integer.MAX_VALUE);
			} else {
				pwPlayer.gPoint().increase(wSysItemPrice.getCost());
			}
			break;
		case PAY_FC_POINT://FC点
			if (wSysItemPrice.getCost() <= 0) {
				throw new Exception("sysItemPrice.id=" + wSysItemPrice.getId() + " cost is error .");
			}
			if (0L + pwPlayerInfo.xunleiPoint().get() + wSysItemPrice.getCost() > Integer.MAX_VALUE) {
				pwPlayerInfo.xunleiPoint().set(Integer.MAX_VALUE);
			} else {
				pwPlayerInfo.xunleiPoint().increase(wSysItemPrice.getCost());
			}
			break;
		case PAY_ITEM://道具
			WSysItem wSysItem = wSysItemDao.findEntity(wSysItemPrice.getSysItemId());
			if (wSysItem.getiId() == EItemIId.ITEM_GAME_COIN.getNumber()) {//游戏币道具有些特殊，不获得道具，而是直接加货币
				WSysItemPrice wSysItemPrice2 = (WSysItemPrice) wSysItemPrice.clone();
				wSysItemPrice2.setPayType(EPayType.PAY_GP.getNumber());
				wSysItemPrice2.setCost(wSysItemPrice.getUnit() * Integer.parseInt(wSysItem.getiValue()));
				earnItemPrice(wSysItemPrice2, pwPlayer, pwPlayerInfo, pwPlayerItemMap);
			} else {
				//TODO
				//sendPlayerItem(pwPlayer, wSysItem, wPayment, isGift, isBind, isDefault);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 单纯减少玩家道具的数量，数量不足将抛出异常。有延迟处理信息，可能会逻辑删除玩家道具<br/>
	 * 如果有多个堆叠的相同sysItemId道具，按照堆叠数量由少到多依次扣除数量
	 * @param playerId
	 * @param sysItemId
	 * @param reduceQuantity 正数
	 * @throws Exception
	 */
	public void reducePlayerItem(int playerId, int sysItemId, int reduceQuantity) throws Exception {
		if (reduceQuantity <= 0) {
			return;
		}
		List<ProxyWPlayerItem> pwPlayerItemList = wPlayerService.findProxyWPlayerItemMap(playerId).values().stream().filter(p -> {//找到对应sysItemId的玩家道具，注意按照数量升序排列
			return p.itemId().get() == sysItemId;
		}).sorted((p1, p2) -> p1.quantity().get() - p2.quantity().get()).collect(Collectors.toList());
		int count = pwPlayerItemList.stream().map(p -> p.quantity().get()).reduce((p1, p2) -> p1 + p2).orElse(0);
		if (count < reduceQuantity) {//玩家道具数量不足
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_ITEM);
		}
		for (ProxyWPlayerItem p : pwPlayerItemList) {//按照已堆叠道具的数量从少往多扣除
			if (reduceQuantity == 0) {
				break;
			}
			if (p.quantity().get() < reduceQuantity) {
				p.quantity().increase(p.quantity().get());
				reduceQuantity -= p.quantity().get();
			} else {
				p.quantity().increase(-reduceQuantity);
				reduceQuantity = 0;
			}
		}
	}

    /**
	 * 玩家使用道具，有附带效果。单纯的减少道具数量不使用该方法
	 * @param pwPlayer
	 * @param pwPlayerItem
	 * @param serverId
	 * @param channelId
	 * @param msg
	 * @throws Exception
	 */
	public void usePlayerItem(final ProxyWPlayer pwPlayer, final ProxyWPlayerItem pwPlayerItem, int serverId, int channelId, String msg) throws Exception {
        if (!(pwPlayerItem.quantity().get() <= 0)) {
            throw new NullPointerException(ExceptionMessage.NOT_PLAYER_ITEM);
        }
        WSysItem wSysItem = wSysItemDao.findEntity(pwPlayerItem.itemId().get());
        Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(pwPlayer.id().get());
        int iValue = 0;
        try {
            iValue = Integer.parseInt(wSysItem.getiValue());
        } catch (Exception e) {
        }
        Date now = new Date();
        switch (EItemIId.forNumber(wSysItem.getiId())) {
		//buff道具
        case BUFF:
			//从PlayerBuff查找之前启用buff的playerItem
			int playerItemId = wPlayerService.findProxyWPlayerBuffMap(pwPlayer.id().get()).values().stream().filter(p -> (int) p.buffId().get() == wSysItem.getiBuffId())
                    .map(p -> p.playerItemId().get()).findFirst().orElse(0);
            ProxyWPlayerItem pwPlayerItemInBuff = pwPlayerItemMap.get(playerItemId);
			//以下种类的Buff不能在Buff未过期时叠加使用
            if (wSysItem.getiBuffId() == EItemIBuffId.GIFT_BAG_DAILY.getNumber() || wSysItem.getiBuffId() == EItemIBuffId.GIFT_BAG_NWE_YEAR_DAILY.getNumber()) {
                if (null != pwPlayerItemInBuff && DateUtil.after(now, pwPlayerItemInBuff.expireTime().get(), Calendar.MILLISECOND, 1)) {
                    throw new BaseException(ExceptionMessage.ILLEGAL_REQUEST);
                }
            }
            wPlayerService.updateProxyWPlayerBuff(pwPlayer, pwPlayerItem, EItemIBuffId.forNumber(wSysItem.getiBuffId()));
			//原物品持有时间未过期，将原来持有该buff的playerItem的剩余时间叠加到新的playerItem上。原有的playerItem移除
            if (null != pwPlayerItemInBuff && DateUtil.after(now, pwPlayerItemInBuff.expireTime().get(), Calendar.MILLISECOND, 1)) {
                long millisecond = pwPlayerItemInBuff.expireTime().getTime() - now.getTime();
                pwPlayerItem.expireTime().increase((int) millisecond);
                wPlayerService.remove(pwPlayerItemInBuff);
                return;
            }
			//现物品持有时间过期，移除物品并发提示邮件
            String title = "";
            String content = "";
            if (DateUtil.after(pwPlayerItem.expireTime().get(), now, Calendar.MILLISECOND, 1)) {
                wPlayerService.remove(pwPlayerItem);
                switch (EItemIBuffId.forNumber(wSysItem.getiBuffId())) {
                case CARD_MONTH:
                    title = CommonMsg.ON_CARDS_FAILURE_MESSAGE_TITLE;
                    content = CommonMsg.ON_CARDS_FAILURE_MESSAGE_CON;
                    break;
                case GIFT_BAG_DAILY:
                    title = CommonMsg.EG_FAILURE_MESSAGE_TITLE;
                    content = CommonMsg.EG_FAILURE_MESSAGE_CON;
                    break;
                case GIFT_BAG_NWE_YEAR_DAILY:
                    title = CommonMsg.NWE_YEAR_EG_FAILURE_MESSAGE_TITLE;
                    content = CommonMsg.NWE_YEAR_EG_FAILURE_MESSAGE_CON;
                    break;
                default:
                    break;
                }
                content = CommonUtil.messageFormatI18N(content, DateUtil.formatToFlag(now, "yyyy/MM/dd"));
				//FIXME 发邮件
                return;
            }
			//使用物品并发出提示邮件
            title = "";
            content = "";
            switch (EItemIBuffId.forNumber(wSysItem.getiBuffId())) {
            case CARD_MONTH:
                WPayment wPayment = new WPayment();
                wPayment.setUnit(1);
                wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
				wPayment.setPayType(EPayType.PAY_FC_POINT.getNumber());
                wPayment.setCost(iValue);
                wPlayerService.earn(pwPlayer, wPayment);
                title = CommonMsg.ON_CARDS_SUCCESS_MESSAGE_TITLE;
                content = CommonUtil.messageFormatI18N(CommonMsg.ON_CARDS_SUCCESS_MESSAGE_CON, wPayment.getCost(), (pwPlayerItem.expireTime().getTime() - now.getTime()) / 1000 / 60 / 60 / 24);
                break;
            case GIFT_BAG_DAILY:
                for (WPayment m : WSysConfigService.getSysItemUseGift().getItemForSysItemId().get(wSysItem.getId())) {
                    sendPlayerItem(pwPlayer, wSysItem, m, false, false, false);
                }
                title = CommonMsg.EG_SUCCESS_MESSAGE_TITLE;
                content = CommonUtil.messageFormatI18N(CommonMsg.EG_SUCCESS_MESSAGE_CON, wSysItem.getDisplayName(), (pwPlayerItem.expireTime().getTime() - now.getTime()) / 1000 / 60 / 60 / 24);
                break;
            case GIFT_BAG_NWE_YEAR_DAILY:
                for (WPayment m : WSysConfigService.getSysItemUseGift().getItemForSysItemId().get(wSysItem.getId())) {
                    sendPlayerItem(pwPlayer, wSysItem, m, false, false, false);
                }
                title = CommonMsg.NWE_YEAR_EG_SUCCESS_MESSAGE_TITLE;
                content = CommonUtil.messageFormatI18N(CommonMsg.NWE_YEAR_EG_SUCCESS_MESSAGE_CON, wSysItem.getDisplayName(), (pwPlayerItem.expireTime().getTime() - now.getTime()) / 1000 / 60 / 60
                        / 24);
                break;
            default:
                break;
            }
			//部分道具使用后推送消息
            switch (EItemIBuffId.forNumber(wSysItem.getiBuffId())) {
            case HIDE_1:
            case HIDE_2:
            case HIDE_3:
            case HIDE_4:
            case HIDE_5:
                break;
            default:
                socketClientNew.puchCMDtoClient(pwPlayer.name().get(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST));
                break;
            }
			//FIXME buff道具不扣数量？
            break;
		//大小喇叭
        case TRUMPET:
        case TYPHON:
            if (StringUtil.isEmpty(msg)) {
                throw new BaseException(ExceptionMessage.EMPTY_STR);
            }
            if (msg.length() > 64) {
                throw new DataTooLongException(ExceptionMessage.TOO_LONG);
            }
			//FIXME KeywordFilterUtil获取bandword方式不合理
            msg = KeywordFilterUtil.filter(com.pearl.o2o.utils.StringUtil.escapeIndex(msg));
            if (channelId != 0 && wSysItem.getiId() == EItemIId.TYPHON.getNumber()) {
                socketClientNew.messageDLB(msg, pwPlayer.name().get());
            } else if (channelId != 0 && wSysItem.getiId() == EItemIId.TRUMPET.getNumber()) {
                socketClientNew.messageXLB(serverId, msg, pwPlayer.name().get());
            } else {
                throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
            }
            break;
		//玩家改名
        case CHANGE_APPEARANCE:
            if (com.pearl.o2o.utils.StringUtil.filter(msg)) {
                throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
            }
			//FIXME KeywordFilterUtil获取bandword方式不合理
            if (!KeywordFilterUtil.isLegalInput(msg)) {
                throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
            }
            if (StringUtil.isEmpty(msg)) {
                throw new BaseException(ExceptionMessage.NAME_CANT_NULL);
            } else if (msg.length() > Integer.parseInt(wSysConfigDao.findMap(null).get("playername.maxlength").toString())) {
                throw new BaseException(ExceptionMessage.TOO_LONG_CREATE_PLAYER);
            } else if (msg.length() < Integer.parseInt(wSysConfigDao.findMap(null).get("playername.minlength").toString())) {
                throw new BaseException(ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
            }
            pwPlayer.name().set(msg);
            break;
		//战绩清零卡
        case CARD_CLEAR_BATTLE_RECORD:
            pwPlayer.generalWin().set(0);
            pwPlayer.generalLose().set(0);
			//TODO 更新玩家战绩排行榜
            //            ServiceLocator.updateService.updatePlayerTop(player);
            //            soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
            break;
        case CARD_CLEAR_DEAD:
            break;
		//逃跑清零卡
        case CARD_CLEAR_ESCAPE:
            pwPlayer.runAway().set(0);
            pwPlayer.generalTotal().set(0);
			//TODO 更新玩家战绩排行榜
            //            soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
            break;
		//战队清零卡
        case CARD_CLEAR_TEAM:
            ProxyWTeam pwTeam = wTeamService.findProxyWTeamByPlayerId(pwPlayer.id().get());
            if (null == pwTeam) {
                throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
            }
            pwTeam.gameWin().set(0);
            pwTeam.gameTotal().set(0);
			//TODO 更新战队战绩排行榜
            //            nosqlService.getNosql().zRem(Constants.TEAMTOP_KEY_PREFIX+ Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue(), String.valueOf(pt.getTeamId()));
            break;
		//游戏币道具
        case ITEM_GAME_COIN:
            WPayment wPayment = new WPayment();
            wPayment.setUnit(1);
            wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
            wPayment.setCost(iValue);
            wPlayerService.earn(pwPlayer, wPayment);
            //TODO
			//nosqlService.updateStayData(player, money);// 今日表现
            //            nosqlService.publishEvent(new PayGpEvent(money, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
            break;
		//扩充战队
        case EXPAND_TEAM:
            ProxyWPlayerTeam pwPlayerTeam = wTeamService.findProxyWPlayerTeamMap(pwPlayer.id().get()).values().stream().filter(p -> "Y".equals(p.approved().get())).findFirst().orElse(null);
            if (null == pwPlayerTeam) {
                throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
            }
			if (pwPlayerTeam.job().get() != ETeamJob.TEAM_CAPTAIN.getNumber()) {//只有队长能扩充战队
                throw new BaseException(ExceptionMessage.TEAM_CAPTAIN_RIGHT);
            }
            pwTeam = wTeamService.findProxyWTeam(pwPlayerTeam.teamId().get());
			pwTeam.size().increase(iValue);
			if (pwTeam.size().get() > WSysConfigService.getTeamMaxMemberCount()) {//战队成员上限
                throw new BaseException(ExceptionMessage.TEAM_OVER_MAX_SIZE);
            }
            break;
        //VIP
        case VIP:
			if (pwPlayer.vipToExpire().get() == -1) {//已经是永久VIP，道具无效
                throw new BaseException(ExceptionMessage.ALREADY_VIP);
            }
            if (pwPlayerItem.unitType().get() == EUnitType.FOREVER.getNumber()) {
                pwPlayer.vipToExpire().set(-1L);
            } else if (pwPlayerItem.unitType().get() == EUnitType.TIME.getNumber()) {
				if (pwPlayer.isVip().get() == 0) {//根据VIP经验重设VIP等级，等级至少为1
                    int vipLevel = WSysConfigService.getExpLevel().get("vip").values().stream().filter(p -> p <= pwPlayer.vipExperience().get()).max((p1, p2) -> p1 - p2).orElse(1);
                    pwPlayer.isVip().set(vipLevel);
                }
                pwPlayer.vipToExpire().set(now.getTime());
                pwPlayer.vipToExpire().increase(pwPlayerItem.leftSeconds().get() * 1000L);
            }
            socketClientNew.puchCMDtoClient(pwPlayer.name().get(), CommonUtil.messageFormat(CommonMsg.BECOME_VIP));
            break;
		//战队更名卡
        case CARD_CHANGE_TEAM_NAME:
            pwPlayerTeam = wTeamService.findProxyWPlayerTeamMap(pwPlayer.id().get()).values().stream().filter(p -> "Y".equals(p.approved().get())).findFirst().orElse(null);
            if (null == pwPlayerTeam) {
                throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
            }
			if (pwPlayerTeam.job().get() != ETeamJob.TEAM_CAPTAIN.getNumber()) {//只有队长能改名
                throw new BaseException(ExceptionMessage.TEAM_CAPTAIN_RIGHT);
            }
			if (StringUtil.isEmpty(msg)) {//名称不可为空
                throw new BaseException(ExceptionMessage.EMPTY_STR);
            }
			if (!KeywordFilterUtil.isLegalInput(msg) || com.pearl.o2o.utils.StringUtil.filter(msg)) {//敏感字 FIXME KeywordFilterUtil引用bandword不合理
                throw new BaseException(ExceptionMessage.ILLEGAL_CHARACTER);
            }
			if (msg.length() > Integer.parseInt(wSysConfigDao.findMap(null).get("teamname.maxlength").toString())) {//名称过长
                throw new BaseException(ExceptionMessage.TOO_LONG);
            }
			if (null != wTeamService.findProxyWTeamByName(msg)) {//已有同名战队
                throw new BaseException(ExceptionMessage.TEAM_EXIST);
            }
            pwTeam = wTeamService.findProxyWTeam(pwPlayerTeam.teamId().get());
            pwTeam.name().set(msg);
			//TODO 推送和邮件通知
            //            soClient.updateCharacterInfo(p, newTeamName, p.getRank());
            //            messageService.sendSystemMail(getService.getPlayerById(pt.getPlayerId()), CommonMsg.TEAM_RENAME_MAIL_SUBJECT, CommonUtil.messageFormatI18N(CommonMsg.TEAM_RENAME_MAIL_CONTENT, new Object[] { oldTeamName, newTeamName }));
            break;
		//包装盒内物品全送
        case GIFT_BOX_LEVEL:
        case GIFT_BAG_UPDATE_FOR_FRESHMAN:
        case GIFT_BAG_COMEBACK_FOR_VETERAN:
        case GIFT_BOX_PER_TOW_DAYS:
        case GIFT_BOX_UPDATE_RANK:
        case GIFT_BOX_FRIEND:
        case GIFT_BAG_SHOP:
			//使用冷却期
            int second = WSysConfigService.getSysItemUseDelay().getOrDefault(wSysItem.getId(), 0);
            if (!DateUtil.after(now, pwPlayerItem.validTime().get(), Calendar.SECOND, second)) {
                throw new BaseException(ExceptionMessage.NOT_LEVEL_TIME);
            }
            for (WPayment gift : WSysConfigService.getSysItemUseGift().getItemForSysItemId().getOrDefault(wSysItem.getId(), new ArrayList<>())) {
                if (null != gift.getPayType()) {
                    wPlayerService.earn(pwPlayer, gift);
                } else {
                    sendPlayerItem(pwPlayer, wSysItemDao.findEntity(gift.getItemId()), gift, false, true, false);
                }
            }
            for (WPayment gift : WSysConfigService.getSysItemUseGift().getItemForIId().getOrDefault(wSysItem.getiId(), new ArrayList<>())) {
                if (null != gift.getPayType()) {
                    wPlayerService.earn(pwPlayer, gift);
                } else {
                    sendPlayerItem(pwPlayer, wSysItemDao.findEntity(gift.getItemId()), gift, false, true, false);
                }
            }
            break;
		//包装盒内物品全送，但是由于历史遗留问题，走SysOnlineAward路线
        case GIFT_BOX_FOR_SUMMER:
            List<WSysOnlineAward> wSysOnlineAwardList = wSysOnlineAwardDao.findList(null).stream().filter(p -> {
                return p.getType() == EOnlineAwardType.ONE_TO_MANY_BOX.getNumber() && p.getLevel() == Integer.parseInt(wSysItem.getiValue());
            }).collect(Collectors.toList());
            for (WSysOnlineAward wSysOnlineAward : wSysOnlineAwardList) {
                wPayment = new WPayment();
                wPayment.setUnit(wSysOnlineAward.getUnit());
                wPayment.setUnitType(wSysOnlineAward.getUnitType());
                sendPlayerItem(pwPlayer, wSysItemDao.findEntity(wSysOnlineAward.getSysItemId()), wPayment, false, true, false);
            }
            break;
		//包装盒内物品送一种
        case GIFT_CHECK_BOX:
        case GIFT_CHECK_BOX_LIMIT_TIME:
            List<WPayment> giftList = WSysConfigService.getSysItemUseGift().getItemForSysItemId().getOrDefault(wSysItem.getId(), new ArrayList<>());
            wPayment = RandomUnit.getRandomObjectByWeight(giftList);
            if (null != wPayment.getPayType()) {
                wPlayerService.earn(pwPlayer, wPayment);
            } else {
                sendPlayerItem(pwPlayer, wSysItemDao.findEntity(wPayment.getItemId()), wPayment, false, true, false);
            }
            break;
		//包装盒内物品送一种，但是由于历史遗留问题，走SysOnlineAward路线
        case BOX_VIP:
        case GIFT_BOX_SIGNIN:
        case BOX_CHRISMAS_JUMP:
            wSysOnlineAwardList = wSysOnlineAwardDao.findList(null).stream().filter(p -> {
                int onlineAwardType = 0;
                switch (EItemIId.forNumber(wSysItem.getiId())) {
                case BOX_VIP:
                    onlineAwardType = EOnlineAwardType.MYSTIC_BAG.getNumber();
                    break;
                case GIFT_BOX_SIGNIN:
                    onlineAwardType = EOnlineAwardType.DAILY_CHECK.getNumber();
                    break;
                case BOX_CHRISMAS_JUMP:
                    onlineAwardType = EOnlineAwardType.AGRAVITYBOXITEM.getNumber();
                    break;
                default:
                    break;
                }
                return p.getType() == onlineAwardType && p.getLevel() == Integer.parseInt(wSysItem.getiValue());
            }).collect(Collectors.toList());
            WSysOnlineAward wSysOnlineAward = RandomUnit.getRandomObjectByWeight(wSysOnlineAwardList);
            if (null == wSysOnlineAward) {
                throw new Exception("sysItemId" + wSysItem.getId() + " has no corresponding sysOnlineAward .");
            }
            wPayment = new WPayment();
            wPayment.setUnit(wSysOnlineAward.getUnit());
            wPayment.setUnitType(wSysOnlineAward.getUnitType());
            sendPlayerItem(pwPlayer, wSysItemDao.findEntity(wSysOnlineAward.getSysItemId()), wPayment, false, true, false);
            //TODO
            //            soClient.puchCMDtoClient(player.getName(), Converter.useItemAwards(cvtList));
            break;
		//将玩家各个有效角色正在装备的、槽位号为1的默认武器或者装备的等级提升到指定等级
        case WEAL_STONE:
            wPlayerService.findProxyWPlayerPackMap(pwPlayer.id().get()).values().forEach(p -> {
                ProxyWPlayerItem tmpPwPlayerItem = pwPlayerItemMap.get(p.playerItemId().get());
                if (p.seq().get() == 1 && "Y".equals(tmpPwPlayerItem.isDefault().get()) && tmpPwPlayerItem.level().get() < Integer.parseInt(wSysItem.getiValue())) {
                    tmpPwPlayerItem.level().set(Integer.parseInt(wSysItem.getiValue()));
                }
            });
            break;
		//玩家经验
        case VIP_EXP_STONE:
			pwPlayer.exp().increase(iValue);
            break;
		//战队经验
        case SUPER_ENERGY_STONE_1:
        case SUPER_ENERGY_STONE_2:
        case SUPER_ENERGY_STONE_3:
        case SUPER_ENERGY_STONE_4:
        case SUPER_ENERGY_STONE_5:
        case SUPER_ENERGY_STONE_6:
        case SUPER_ENERGY_STONE_7:
        case SUPER_ENERGY_STONE_8:
        case SUPER_ENERGY_STONE_9:
        case SUPER_ENERGY_STONE_10:
            pwTeam = wTeamService.findProxyWTeamByPlayerId(pwPlayer.id().get());
            if (null == pwTeam) {
                throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
            }
			pwTeam.exp().increase(iValue);
            break;
        default:
            break;
        }

		//减少道具数量并记录延迟信息
        if (pwPlayerItem.unitType().get() == EUnitType.QUANTITY.getNumber()) {
			pwPlayerItem.quantity().increase(-1);
        }
    }

}
