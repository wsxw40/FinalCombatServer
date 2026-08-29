package com.pearl.fcw.servlet.user;

import org.junit.Test;

import com.pearl.fcw.BaseTest;
import com.pearl.fcw.info.core.network.proto.UserLoginRequest;
import com.pearl.fcw.info.core.network.proto.UserLoginResponse;

public class UserLoginTest extends BaseTest {

    @Test
    public void normal() {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName("tomt1");
        send(0, "c_UserLogin", request, new UserLoginResponse());
    }

}
