package com.pearl.fcw.lobby.servlet.rpc.combine;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.pojo.proxy.ProxyInterruptException;
import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.CombineService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 转换
 */
@Service("fcw_c_convert")
public class CConvert extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -8628673467907898043L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CConvert fcw_c_convert;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "fromItemId", "toItemId", "islose" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_convert.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
		} catch (ProxyInterruptException e) {
			return e.getMessage();
		} catch (Exception e) {
			logger.error("c_convert has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int playerItemId1 = Integer.parseInt(args[1]);
		int playerItemId2 = Integer.parseInt(args[2]);
		boolean isHoldLevel = Integer.parseInt(args[3]) == 1 ? true : false;
		return combineService.convert(playerId, playerItemId1, playerItemId2, isHoldLevel);
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }

}
