package com.pearl.o2o.filter;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.aop.MethodBeforeAdvice;

public class GmWriteInterceptor implements  MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		boolean b = Modifier.isPublic(method.getModifiers());
		//只对类中的public方法进行拦截
		if(b == true){
//			GmUser gmUser = (GmUser)args[0]; 
//			ServiceLocator.flexGetService.checkCanWrite(gmUser.getGroupIds(),"不能执行此操作");
		}
	}
}
