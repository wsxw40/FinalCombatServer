package com.pearl.fcw.lobby.servlet.rpc.combine;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.CombineService;
import com.pearl.fcw.proto.enums.EItemCombineType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 查看合成相关道具
 */
@Service("fcw_c_strength_item_list")
public class CStrengthItemList extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -8497125590139345601L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private CStrengthItemList fcw_c_strength_item_list;
    @Resource
	private CombineService combineService;

    @Override
    protected String[] paramNames() {
		return new String[] { "uid", "pid", "t", "cid", "p", "st", "ct" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
			return fcw_c_strength_item_list.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
			logger.error("c_strength_item_list has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
		int itemType = Integer.parseInt(args[2]);
		int sysCharacterId = Integer.parseInt(args[3]);
		int page = Integer.parseInt(args[4]);
		int itemSubType = 0;
		int combineType = 0;
		try {
			itemSubType = Integer.parseInt(args[5]);
			combineType = Integer.parseInt(args[6]);
		} catch (Exception e) {
		}
		return combineService.getCombienItems(playerId, EItemCombineType.forNumber(combineType), EItemType.forNumber(itemType), sysCharacterId, page);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
