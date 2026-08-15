package com.pearl.fcw.lobby.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.pojo.DelayMessage;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.service.Delayed;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 玩家的一些操作后，可能会触发不可控的二次、三次……循环操作。<br/>
 * 例如购买物品会达成成就，而成就奖励物品又会达成另一个成就……<br/>
 * 利用消息链表滞后间接处理这些不可控的循环操作，可以将注意力集中在核心业务上<br/>
 * 业务代码中勿手动触发delay方法
 */
@Service
public class DelayService implements Delayed {
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private EntityManager entityManager;
    @Resource
    private WPlayerService wPlayerService;
    @Resource
    private ItemService itemService;
    @Resource
	private QuestService questService;
	@Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private SocketClientNew socketClientNew;

    @Override
	public void addMsg(Object msg) {
		entityManager.getMsgList().addLast(msg);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void delay() throws Exception {
		//任何修改的玩家道具数量不能小于0，否则抛出异常。数量为0则逻辑删除？
		for (EntityProxy<WPlayerItem> p : entityManager.readThreadCacheProxySet(WPlayerItem.class, null, true)) {
			if (p.get().getQuantity() < 0) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_ITEM);
			}
			if (p.get().getQuantity() == 0 && !p.get().getIsRemoved()) {
				wPlayerService.remove(p);
			}
		}

		//单独获取当前线程的发出接口，因为不是每条信息都包含接口信息
		String uri = entityManager.getMsgList().stream().filter(p -> {
			if (p instanceof DelayMessage) {
				DelayMessage p1 = (DelayMessage) p;
				if (null != p1.getUri()) {
					return true;
				}
			}
			return false;
		}).map(p -> ((DelayMessage) p).getUri()).findFirst().orElse(null);
		int playerId = 0;

		//循环延迟处理结束的条件：消息数量为0
        while (entityManager.getMsgList().size() > 0) {
			Object obj = entityManager.getMsgList().pollFirst();
			if (obj instanceof DelayMessage) {
				DelayMessage playerDelay = (DelayMessage) obj;
				if (null != playerDelay.getKey() && null == playerDelay.getData()) {//有锁键值但没有填充信息，表示要处理玩家任务
					playerId = Integer.parseInt(playerDelay.getKey());
					//任务触发状态检查
					questService.updateQuestStatusAuto(playerId, uri, entityManager.readThreadCacheProxySet(null, playerId, true));
				} else if (null != playerDelay.getKey() && playerDelay.getData() instanceof Collection) {
					Collection<?> collection = (Collection<?>) playerDelay.getData();
					if (!collection.isEmpty() && collection.stream().findAny().get() instanceof WSysItemPrice) {//使用了多对多交易表
						playerId = Integer.parseInt(playerDelay.getKey());
						//触发和交易表有关系的任务检查
						Collection cs = entityManager.readThreadCacheProxySet(null, playerId, true);
						cs.addAll(collection);
						questService.updateQuestStatusAuto(playerId, uri, cs);
					}
				}
			}

			//            if (obj instanceof ProtoPlayerDelay) {
			//                ProtoPlayerDelay playerDelay = (ProtoPlayerDelay) obj;
			//                int playerId = playerDelay.getPlayerId();
			//                ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
			//                switch (playerDelay.getType()) {
			//                case delayGp:
			//                    //TODO
			//                    //                    socketClientNew.messageUpdatePlayerMoney(player);
			//                    break;
			//                case delayXunleiPoint:
			//					//TODO 如果扣除迅雷点，会触发玩家活动、玩家成长任务和玩家轨迹的数据变化
			//                    if (playerDelay.getUnit() < 0) {
			//
			//                    }
			//                    break;
			//                case delaySysItem:
			//                    int playerItemId = playerDelay.getTargetId();
			//                    ProxyWPlayerItem pwPlayerItem = wPlayerService.findProxyWPlayerItemMap(playerId).get(playerItemId);
			//                    WSysItem wSysItem = wSysItemDao.findEntity(pwPlayerItem.itemId().get());
			//					//TODO 给玩家发送物品可能会影响成就
			//                    if (playerDelay.getUnit() > 0) {
			//						//TODO 有部分物品玩家获得后直接使用
			//                        if (wPlayerService.isItemUseImmediately(wSysItem)) {
			//
			//                        }
			//						//限时装备
			//                        if (WSysConfigService.getSysItemIdLimitTime().contains(wSysItem.getId())) {
			//                            delayLimitTimeItem(playerDelay);
			//                        }
			//                    }
			//					//TODO 使用物品可能会影响成就
			//                    if (playerDelay.getUnit() < 0) {
			//						//TODO 使用大喇叭影响成就
			//                        //ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player,Constants.ACTION.DALABA.getValue(), 1);
			//                        //ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.USE_DLB);
			//						//消耗类型为数量的道具，当持有数量小于等于零后，自动移除
			//                        if (pwPlayerItem.unitType().get() == EUnitType.QUANTITY.getNumber() && pwPlayerItem.quantity().get() <= 0) {
			//                            wPlayerService.remove(pwPlayerItem);
			//                        }
			//                    }
			//
			//					if (pwPlayerItem.unitType().eq((byte) EUnitType.QUANTITY.getNumber()) && pwPlayerItem.quantity().le(0)) {//数量为零自动逻辑删除
			//						wPlayerService.remove(pwPlayerItem);
			//					}
			//                    break;
			//                case delayBuyRecord:
			//					//首次购买月卡有奖励
			//                    Set<Integer> sysItemIdSet = wSysItemDao.findList(null).stream().filter(p -> p.getiBuffId() == EItemIBuffId.CARD_MONTH.getNumber()).map(p -> p.getId()).collect(Collectors.toSet());
			//                    long buyRecord = wPlayerService.findProxyWBuyitemRecordMap(playerId).values().stream().filter(p -> sysItemIdSet.contains(p.itemId().get()) && p.record().get() == 1).count();
			//                    if (buyRecord == 1) {
			//                        for (Integer sysItemId : sysItemIdSet) {
			//                            for (WPayment gift : WSysConfigService.getSysItemFirstBuyGift().get(sysItemId)) {
			//                                itemService.sendPlayerItem(pwPlayer, wSysItemDao.findEntity(gift.getItemId()), gift, false, true, false);
			//								//TODO 邮件通知
			//                                //                    String content = CommonUtil.messageFormatI18N(CommonMsg.ON_CARDS_FIRST_MESSAGE, count);
			//                                //                    ServiceLocator.createService.sendSystemMail(player, CommonUtil.messageFormatI18N("1"), content , sItem.getId(), new Payment(count,1));
			//                            }
			//                        }
			//                    }
			//                    break;
			//                case delayVipLevel:
			//                    if (playerDelay.getUnit() > 0) {
			//						//TODO 送vip专属名片
			//                        //                        SysItem vipCard=ServiceLocator.getService.getVipCard(vipRank);
			//                        //                        if(vipCard!=null){
			//                        //                            PlayerItem vipCardBuff = getService.getPlayerBuff(player.getId(), vipCard.getIBuffId());
			//                        //                            if (vipCardBuff==null || vipCardBuff.getItemId() != vipCard.getId()) {
			//                        //                                sendItem(vipCard, player, new Payment(30, 2),
			//                        //                                        Constants.BOOLEAN_NO, Constants.BOOLEAN_YES,
			//                        //                                        Constants.BOOLEAN_NO);
			//                        //                            }
			//                        //                        }
			//						//TODO 送强化buff
			//                        //                        SysItem vipStrengthItem=ServiceLocator.getService.getVipStrengthBuff(vipRank);
			//                        //
			//                        //                        if (vipStrengthItem != null) {
			//                        //                            PlayerItem playerBuff = getService.getPlayerBuff(player.getId(), vipStrengthItem.getIBuffId());
			//                        //
			//                        //                            if (null == playerBuff|| playerBuff.getItemId() != vipStrengthItem.getId()) {
			//                        //                                int playerItemId = sendItem(vipStrengthItem, player,
			//                        //                                        new Payment(0, 0), Constants.BOOLEAN_NO,
			//                        //                                        Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
			//                        //                                PlayerItem playerItem = getService.getPlayerItemByItemId(player.getId(),
			//                        //                                                vipStrengthItem.getType(), playerItemId);
			//                        //                                useItemById(player, vipStrengthItem.getType(), playerItem, "",0, 0);
			//						//                                // 删道具
			//                        //                                updateItemQuantity(playerItem);
			//                        //                                int playerId = player.getId();
			//                        //                                mcc.delete(CacheUtil.oPlayer(playerId));
			//                        //                                mcc.delete(CacheUtil.sPlayer(playerId));
			//                        //                                ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, vipStrengthItem);
			//                        //                                soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST, null));
			//                        //
			//                        //                            }
			//                        //                        }
			//                    }
			//                    break;
			//                default:
			//                    break;
			//                }
			//            } else if (obj instanceof WMessage) {
			//
			//            }
        }
    }
}
