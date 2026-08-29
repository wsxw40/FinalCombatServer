package com.pearl.fcw.lobby.servlet.rpc.combine;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.CombineService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 开槽
 */
@Service("fcw_c_sloting")
public class CSloting extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -6956966989807509736L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CSloting fcw_c_sloting;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "playerItemId", "index", "sloterItemId" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_sloting.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
			logger.error("c_sloting has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int targetPlayerItemId = Integer.parseInt(args[1]);
		int slotId = Integer.parseInt(args[2]);
		int partPlayerItemId = Integer.parseInt(args[3]);
		return combineService.slot(playerId, targetPlayerItemId, partPlayerItemId, slotId);
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }

}
