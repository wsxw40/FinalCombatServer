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
 *  更改二级密码
 */
@Service("c_reset_second_password1")
public class CResetSecondPassword extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = 2665740323325737842L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String[] paramNames  = { "pid","oldpassword","newpassword"};
    @Resource
    private CResetSecondPassword c_reset_second_password1;
    @Resource
    private WSecondPasswordService wSecondPasswordService;
    @Override
    protected String innerService(String... strings) {
        try {
            return c_reset_second_password1.rpc(strings);
        } catch (Exception e) {
            logger.error("c_reset_second_password1 has error : ", e);
        }
        return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }
    @Override
    public String rpc(String... args) throws Exception {
        if(!args[0].matches("^\\d+$")&&!args[1].matches("^[A-Za-z0-9]+$")
                &&!args[2].matches("^[A-Za-z0-9]+$")){      
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
        int playerId = StringUtil.toInt(args[0]) ;
        String oldPassword = args[1];
        String newPassword =args[2];
        
        return wSecondPasswordService.resetSecondPassword(playerId,oldPassword,newPassword);
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
