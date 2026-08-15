package com.pearl.fcw.info.lobby.servlet.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.network.proto.UserLoginRequest;
import com.pearl.fcw.info.core.network.proto.UserLoginResponse;
import com.pearl.fcw.info.lobby.pojo.User;
import com.pearl.fcw.info.lobby.service.UserService;
import com.pearl.fcw.info.lobby.servlet.client.BaseClientServlet;

@Service("c_UserLogin")
public class UserLogin extends BaseClientServlet {

    private static final long serialVersionUID = 3100241133009596702L;

    @Resource
    private UserService userService;

    @Override
    public byte[] innerService(long playerId, byte[] params) throws Exception {

        UserLoginRequest request = getProtoParam(params, new UserLoginRequest());

        System.out.println(request.getUserName() + " Login.");
        
        User user = userService.get(1);
//        User user = userService.create(request.getUserName());
        System.out.println(user.getUserName() + " ****************");
        UserLoginResponse response = new UserLoginResponse();
        response.setId(user.getId());
        return outProtoByByte(response);
    }
}
