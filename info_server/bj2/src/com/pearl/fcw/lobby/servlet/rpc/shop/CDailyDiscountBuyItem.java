package com.pearl.fcw.lobby.servlet.rpc.shop;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.ShopService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 购买每日折扣物品
 */
@Service("fcw_c_daily_discount_buy_item")
public class CDailyDiscountBuyItem extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = 4939785116245693727L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CDailyDiscountBuyItem fcw_c_daily_discount_buy_item;
    @Resource
    private ShopService shopService;

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_daily_discount_buy_item.rpc(strings);
        } catch (Exception e) {
            logger.error("c_daily_discount_buy_item has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    protected String[] paramNames() {
        return new String[] { "pid", "sid", "index" };//玩家ID，购买的系统道具ID，使用的折扣索引
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[0]);
        int sysItemId = Integer.parseInt(args[1]);
        int index = Integer.parseInt(args[2]);
        return shopService.payFromDailyDiscount(playerId, sysItemId, index);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[0];
    }
}
