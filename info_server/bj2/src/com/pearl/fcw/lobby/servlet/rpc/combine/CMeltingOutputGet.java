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
 * 熔炼奖励抽取
 */
@Service("fcw_c_melting_output_get")
public class CMeltingOutputGet extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = 9059640603942025232L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CMeltingOutputGet fcw_c_melting_output_get;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "index" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_melting_output_get.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
		} catch (ProxyInterruptException e) {
			return e.getMessage();
		} catch (Exception e) {
			logger.error("c_melting_output_get has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int index = Integer.parseInt(args[1]);
		return combineService.previewMeltingAward(playerId, index);
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }
}
