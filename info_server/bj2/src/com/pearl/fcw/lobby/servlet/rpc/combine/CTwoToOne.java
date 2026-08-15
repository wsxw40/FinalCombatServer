package com.pearl.fcw.lobby.servlet.rpc.combine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 * 升星
 */
@Service("fcw_c_two_to_one")
public class CTwoToOne extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = 7734490070755619908L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CTwoToOne fcw_c_two_to_one;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "pid", "mainItemId", "toItemId", "isFree" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_two_to_one.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
			logger.error("c_two_to_one has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int targetPlayerItemId = Integer.parseInt(args[1]);
		List<Integer> partPlayerItemIdList=Stream.of(args[2].split(":")).map(Integer::parseInt).collect(Collectors.toList());
		boolean isBuyGstExp = Integer.parseInt(args[3]) == 1 ? true : false;
		return combineService.gst(playerId, targetPlayerItemId, partPlayerItemIdList, isBuyGstExp);
    }

    @Override
    public String getLockedKey(String... args) {
		return args[0];
    }

}
