package com.pearl.fcw.info.gm.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器
 */
public class AuthInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 5906092201649167286L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }
}
