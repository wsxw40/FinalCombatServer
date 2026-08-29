package com.pearl.fcw.lobby.servlet.rpc.secondPassword;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.WSecondPasswordService;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 设置或验证二级密码
 */
@Service("c_setcheck_second_password1")
public class CSetCheckSecondPassword extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = 2665740323325737842L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String[] paramNames  = { "pid","password","type"};
    @Resource
    private CSetCheckSecondPassword c_setcheck_second_password1;
    @Resource
    private WSecondPasswordService wSecondPasswordService;
    @Override
    protected String innerService(String... strings) {
        try {
            return c_setcheck_second_password1.rpc(strings);
        } catch (Exception e) {
            logger.error("c_setcheck_second_password has error : ", e);
        }
        return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = StringUtil.toInt(args[0]) ;
        String password = args[1];
        int type=StringUtil.toInt(args[2]);  //0:设置密码;1:验证密码
        return wSecondPasswordService.setOrCheckSecondPassword(playerId,password,type);
    }
    @Override
    protected String[] paramNames() {
        return paramNames;
    }
    @Override
    public String getLockedKey(String... args) {
        return args[0];
    }
}
