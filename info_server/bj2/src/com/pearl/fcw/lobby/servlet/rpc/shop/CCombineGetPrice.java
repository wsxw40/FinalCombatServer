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
 * 合成时查看合成零件的价格
 */
@Service("fcw_c_combine_get_price")
public class CCombineGetPrice extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -2009984089642021917L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CCombineGetPrice fcw_c_combine_get_price;
    @Resource
    private ShopService shopService;

    @Override
    protected String[] paramNames() {
        return new String[] { "pid", "type" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_combine_get_price.rpc(strings);
        } catch (Exception e) {
            logger.error("c_combine_get_price has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int type = Integer.parseInt(args[1]);
        return shopService.getCombinePrice(type);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
