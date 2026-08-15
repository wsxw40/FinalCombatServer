package com.pearl.fcw.gm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.dao.WMessageDao;
import com.pearl.fcw.lobby.pojo.WMessage;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWMessage;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.dao.impl.nonjoin.PlayerDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 邮件
 */
@Service
public class WMessageService extends DmServiceImpl<WMessage, Integer> {
    @Resource
    private EntityManager entityManager;
    @Resource
    private WMessageDao wMessageDao;
    @Resource
    private PlayerDao playerDao;
    @Resource
    private SysItemDao sysItemDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wMessageDao;
    }

    public void addAndSend(WMessage wMessage, String receiverIds, GmUser gmUser) throws Exception {
		//检查附件
        if (StringUtil.isEmpty(wMessage.getSysItems())) {
            return;
        }
        List<Integer> sysItemIdList = Stream.of(wMessage.getSysItems().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int unit = 0;
        int unitType = EUnitType.FOREVER.getNumber();
        try {
            unit = Integer.parseInt(wMessage.getItemUnits());
            unitType = Integer.parseInt(wMessage.getItemUnittypes());
        } catch (Exception e) {
            return;
        }
		//物品消耗类型非永久的前提下，物品数量或者持有时间不能小于1
        if (EUnitType.FOREVER.getNumber() != unitType && unit < 1) {
            return;
        }
		//物品消耗类型永久的前提下，物品数量或固定为1
        if (EUnitType.FOREVER.getNumber() == unitType) {
            unit = 1;
        }
		//排除逻辑上不存在的系统物品
        sysItemIdList.removeIf(id -> {
			SysItem sysItem = sysItemDao.getSystemItemById(id);//验证发送的系统物品存在
                if (null == sysItem) {
                    return true;
                }
                return false;
            });
		//附件填充
        Map<SysItem, Payment> attachmentMap = new HashMap<>();
        for (Integer id : sysItemIdList) {
            Payment payment = new Payment(unit, unitType);
            attachmentMap.put(sysItemDao.getSystemItemById(id), payment);
        }
		//检查接收玩家
        List<Player> receiverList = Stream.of(receiverIds.split(",")).map(Integer::parseInt).map(playerDao::getPlayerById).collect(Collectors.toList());
        receiverList.removeIf(p -> null == p);
        if (receiverList.isEmpty()) {
            return;
        }
        Date now = new Date();
		//添加物品
        for (Entry<SysItem, Payment> kv : attachmentMap.entrySet()) {
            SysItem sysItem = kv.getKey();
            Payment payment = kv.getValue();
			if (sysItem.getType() == EItemType.PACKAGE.getNumber() && !StringUtil.isEmpty(sysItem.getItems())) {//大礼包
                for (Player p : receiverList) {
                    ServiceLocator.createService.packageToPlayer(p, sysItem, payment, new StringBuilder(), "N");
                }
                /*for (int sysItemId : Stream.of(sysItem.getItems().split(",")).map(Integer::parseInt).collect(Collectors.toList())) {
				    SysItem sysItemInPackage = sysItemDao.getSystemItemById(sysItemId);
				    if (sysItemInPackage.getType() == ItemType.ITEM.getValue() && sysItemInPackage.getIId() == ItemIId.ITEM_GAME_COIN.getValue()) {//大礼包内含游戏币
				        //                                    player.setGPoint(player.getGPoint() + money);
				        //                                    nosqlService.updateStayData(player, money);// 今日表现
				        //                                    playerDao.updatePlayerInCache(player);
				        //                                    mcc.delete(CacheUtil.oPlayer(player.getId()));
				        //                                    mcc.delete(CacheUtil.sPlayer(player.getId()));
				        //                                    soClient.messageUpdatePlayerMoney(player);
				        //                                    nosqlService.publishEvent(new PayGpEvent(money, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				    } else if (sysItemInPackage.getType() == Constants.DEFAULT_ITEM_TYPE && sysItemInPackage.getIId() == ItemIId.UNCONSUME_BULLET_NEXT_TIME.getValue()) {// 大礼包内含解锁角色道具

				    } else {

				    }
				}*/
            } else {
                for (Player p : receiverList) {
                    //                    ServiceLocator.getService.getplayer
                    ServiceLocator.createService.sendItem(sysItem, p, payment, "N", "Y", "N");
                }
            }
        }
		//修改邮件信息
        wMessage.setSysItems(sysItemIdList.stream().map(Object::toString).collect(Collectors.joining(",")));
		wMessage.setItemUnits(sysItemIdList.stream().map(p -> {
			if (StringUtil.isEmpty(wMessage.getItemUnits()) || "0".equals(wMessage.getItemUnits().trim())) {
				return "1";
			}
			return wMessage.getItemUnits();
		}).collect(Collectors.joining(",")));
        wMessage.setItemUnittypes(sysItemIdList.stream().map(p -> wMessage.getItemUnittypes()).collect(Collectors.joining(",")));
		//发送邮件
        String subject = StringUtil.isEmpty(wMessage.getSubject()) ? CommonMsg.GIFT_EMAIL_SYS : wMessage.getSubject();
        String content = wMessage.getContent();
        if (StringUtil.isEmpty(content)) {
            content = CommonUtil.messageFormatI18N(CommonMsg.PRESENT_TO_PLAYER, String.join(",", attachmentMap.keySet().stream().map(SysItem::getDisplayName).collect(Collectors.toList())),
					DateUtil.formatToFlag(now, "yyyy-MM-dd"));
        }
        //        subject = com.pearl.o2o.utils.StringUtil.stringToAscii(subject);
        //        content = com.pearl.o2o.utils.StringUtil.stringToAscii(content);
        for (Player receiver : receiverList) {
            ServiceLocator.createService.sendSystemMail(receiver, subject, content, wMessage.getSysItems(), wMessage.getItemUnits(), wMessage.getItemUnittypes());
        }
    }

    /**
	 * GM逻辑删除
	 * @param WMessage
	 * @param gmUser
	 * @throws Exception
	 */
    public void removeByGm(WMessage WMessage, GmUser gmUser) throws Exception {

    }

    private ProxyWMessage add(int senderId, int receiverId, String subject, String content) throws Exception {
        WMessage m = new WMessage();
        m.setSenderId(senderId);
        m.setReceiverId(receiverId);
        m.setOpen("N");
        m.setIsDeleted("N");
        m.setCreatedTime(new Date());
        m.setSubject(subject);
        m.setContent(content);
        return entityManager.add(ProxyWMessage::new, m);
    }

}
