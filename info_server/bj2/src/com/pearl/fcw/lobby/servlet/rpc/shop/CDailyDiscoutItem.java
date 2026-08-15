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
 * 查看每日折扣物品
 */
@Service("fcw_c_daily_discount_item")
public class CDailyDiscoutItem extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -5827934245624414605L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CDailyDiscoutItem fcw_c_daily_discount_item;
    @Resource
    private ShopService shopService;

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_daily_discount_item.rpc(strings);
        } catch (Exception e) {
            logger.error("c_daily_discount_item has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    protected String[] paramNames() {
        return new String[] { "pid" };
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[0]);
        return shopService.getDailyDiscount(playerId);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[0];
    }

}
