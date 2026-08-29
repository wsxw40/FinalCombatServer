package com.pearl.fcw.lobby.servlet.rpc.shop;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.service.ShopService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 一般商城
 */
@Service("fcw_c_shop_list")
public class CShopList extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -2009984089642021917L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CShopList fcw_c_shop_list;
    @Resource
    private ShopService shopService;
    @Resource
    private WSysItemService wSysItemService;
    @Resource
    private WPaymentService wPaymentService;
    @Resource
    private WPlayerService wPlayerService;

    @Override
    protected String[] paramNames() {
        //用户id(无用),玩家id,@EItemType,系统角色id,当前页码(大于等于1),@EItemSubType,@EPayType
        return new String[] { "uid", "pid", "t", "cid", "p", "st", "pt" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_shop_list.rpc(strings);
        } catch (Exception e) {
            logger.error("c_shop_list has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int itemType = Integer.parseInt(args[2]);
        int sysCharacterId = Integer.parseInt(args[3]);
        int page = Integer.parseInt(args[4]);
        page = page < 1 ? 1 : page;
        int itemSubType = 0;
        try {
            itemSubType = Integer.parseInt(args[5]);
        } catch (Exception e) {
        }
        int payType = 0;
        try {
            payType = Integer.parseInt(args[6]);
        } catch (Exception e) {
        }
        return shopService.getShopItems(playerId, itemType, itemSubType, sysCharacterId, page, payType);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
