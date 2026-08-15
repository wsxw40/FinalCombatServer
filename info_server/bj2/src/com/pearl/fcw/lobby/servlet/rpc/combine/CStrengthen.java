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
 * 强化道具
 */
@Service("fcw_c_strengthen")
public class CStrengthen extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -8497125590139345601L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CStrengthen fcw_c_strengthen;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "playerItemId", "strengthenItemId", "safeItemId", "stableItemId" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_strengthen.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
			logger.error("c_strengthen has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int targetPlayerItemId = Integer.parseInt(args[1]);
		int partPlayerItemId = Integer.parseInt(args[2]);
		int safePlayerItemId = Integer.parseInt(args[3]);
		int stablePlayerItemId = Integer.parseInt(args[4]);
		return combineService.enhance(playerId, targetPlayerItemId, partPlayerItemId, safePlayerItemId, stablePlayerItemId);
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }

}
