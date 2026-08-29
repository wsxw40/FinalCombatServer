package com.pearl.fcw.lobby.servlet.rpc.storage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.StorageService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 整理玩家道具的堆叠
 */
@Service("fcw_c_sort_material")
public class CSortMaterial extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = 4131590916191271483L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CSortMaterial fcw_c_sort_material;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "type" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_sort_material.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
			logger.error("c_sort_material has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		storageService.arrangeStorage(playerId);
		return "result = 1";
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }

}
