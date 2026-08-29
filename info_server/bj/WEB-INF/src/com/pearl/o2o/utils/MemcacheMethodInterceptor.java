package com.pearl.o2o.utils;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MemcacheMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object result = mi.proceed();
		Method m = mi.getMethod();
		//just catch mechod "get(String)" of memcache
		if (ConfigurationUtil.SWITCH_PERFORMANCE_MONITOR.getIsOn() && "get".equals(m.getName())
				 && m.getParameterTypes()[0].getName().equals(String.class.getName())){
			StackTraceElement stack[] = (new Throwable()).getStackTrace();
			
			String servletName =  "";
			
			for (StackTraceElement e : stack) {
				//int index = e.getClassName().indexOf("com.pearl.o2o.servlet.");
				if (e.getClassName().startsWith("com.pearl.o2o.servlet.")){
					servletName = e.getClassName();
					break;
				}
			}
			int hit = 0,miss =0 ;
			if (result == null) {
				miss = 1;
			}else {
				hit =1;
			}
			ConfigurationUtil.performanceDatas.addMemcacheInvokeRecord(servletName, hit, miss);
		}
		return result;
	}
}
