package com.pearl.fcw.lobby.servlet.rpc.team;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.WTeamService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 读取战队地图
 */
@Service("fcw_c_edit_map")
public class CEditMap extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = 2665740323325737842L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CEditMap fcw_c_edit_map;
    @Resource
    private WTeamService wTeamService;

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_edit_map.rpc(strings);
        } catch (Exception e) {
            logger.error("c_edit_map has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    protected String[] paramNames() {
        return new String[] { "pid" };
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[0]);
        return wTeamService.getTeamMap(playerId);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[0];
    }
}
