package com.pearl.fcw.info.gm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ @Result(name = "login", location = "/login.html"), @Result(name = "home", location = "/index.jsp") })
@Namespace("")
public class LoginAction extends BaseAction {

    private static final long serialVersionUID = -5597039804270262766L;

    @Action("")
    public String go() {
        return "login";
    }

    @Action("/")
    public String go1() {
        return "login";
    }

    @Action(value = "login")
    public String index() {
        return "login";
    }

    @Action(value = "/home")
    public String home() {

        return "home";
    }

    @Action("/sublogin")
    public String login() throws Exception {
        result.put("ru", request().getContextPath() + "/home");
        out(result);
        return null;
    }

    @Action(value = "/auto")
    public String autoLogin() throws Exception {
        return null;
    }

    @Action(value = "/logout")
    public String logout() {
        return null;
    }

}
