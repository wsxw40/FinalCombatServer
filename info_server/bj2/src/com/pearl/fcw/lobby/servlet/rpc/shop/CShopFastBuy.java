package com.pearl.fcw.lobby.servlet.rpc.shop;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.ShopService;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.rpc.ResponseShopReqBuy;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 快速购买道具
 */
@Service("fcw_c_shop_fast_buy")
public class CShopFastBuy extends BaseClientServlet implements Servletable {
    private static final long serialVersionUID = 3680450877065468945L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CShopFastBuy fcw_c_shop_fast_buy;
    @Resource
    private ShopService shopService;
    @Resource
    private ProtoConverter protoConverter;

    @Override
    protected String[] paramNames() {
        return new String[] { "pid", "sid", "num" };
    }

    @Override
    protected String innerService(String... args) {
        try {
            return fcw_c_shop_fast_buy.rpc(args);
        } catch (BaseException e) {
            try {
                if (e.getMessage().equals(ExceptionMessage.NOT_ENOUGH_GP)) {
                    ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, e.getMessage(), e.getMessage(), -1);
					return Smarty4jConverter.proto2Lua(proto);
                } else if (e.getMessage().equals(ExceptionMessage.NOT_ENOUGH_CR_RENEW)) {
                    ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, e.getMessage(), e.getMessage(), -7);
					return Smarty4jConverter.proto2Lua(proto);
                } else if (e.getMessage().equals(ExceptionMessage.NOT_ENOUGH_V_RENEW)) {

                } else {
                    return e.getMessage();
                }
            } catch (Exception e1) {
                logger.error("c_shop_req_buy has error : " + getLockedKey(args), e1);
            }
        } catch (NotBuyEquipmentException e) {
            try {
                ResponseShopReqBuy proto = protoConverter.responseShopReqBuy(null, e.getMessage(), e.getMessage(), 0);
				return Smarty4jConverter.proto2Lua(proto);
            } catch (Exception e1) {
                logger.error("c_shop_req_buy has error : " + getLockedKey(args), e1);
            }
        } catch (Exception e) {
            logger.error("c_shop_req_buy has error : " + getLockedKey(args), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int sysItemId = Integer.parseInt(args[2]);
        int unit = Integer.parseInt(args[3]);
        return shopService.payFast(playerId, sysItemId, unit);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[0];
    }
}
