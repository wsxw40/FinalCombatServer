package com.pearl.fcw.lobby.servlet.rpc.shop;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.ShopService;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 一般商城批量购买道具
 */
@Service("fcw_c_shop_req_buy_list")
public class CShopReqBuyList extends BaseClientServlet implements Servletable {
    private static final long serialVersionUID = 3680450877065468945L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CShopReqBuyList fcw_c_shop_req_buy_list;
    @Resource
    private ShopService shopService;
    @Resource
    private ProtoConverter protoConverter;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "cid", "packid", "list" };
    }

    @Override
    protected String innerService(String... args) {
        try {
            return fcw_c_shop_req_buy_list.rpc(args);
        } catch (Exception e) {
            logger.error("c_shop_req_buy_list has error : " + getLockedKey(args), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        Map<Integer, Integer> sysItemIdAndPaymentIdMap = Stream.of(args[4].split(";")).map(p -> {
            return Stream.of(p.split(",")).collect(Collectors.toList());
        }).collect(Collectors.toMap(p -> Integer.parseInt(p.get(0)), p -> Integer.parseInt(p.get(2))));
        return shopService.payInShop(playerId, sysItemIdAndPaymentIdMap);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }
}
