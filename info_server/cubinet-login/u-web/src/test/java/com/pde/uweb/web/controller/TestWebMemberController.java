package com.pde.uweb.web.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pde.uweb.ma.dto.RegisterRequestDto;
import com.pde.uweb.ma.dto.RegisterResultDto;
import com.pde.uweb.ma.service.MemberService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-uweb-web.xml",
		"classpath:/META-INF/spring/spring-uweb-biz-ma.xml",
		"classpath:/META-INF/spring/spring-uweb-database.xml",
		"classpath:/META-INF/spring/spring-uweb-framework.xml" 
		})
@TransactionConfiguration(defaultRollback = false, transactionManager = "dataSourceTransactionManager_ma")
public class TestWebMemberController {

	@Resource(name = "memberService_ma")
	private MemberService memberService;
	
	@Test
	public void testDoRegister() throws Exception {
		
		RegisterRequestDto requestDto = new RegisterRequestDto();
		requestDto.setUserName("aaa");
		requestDto.setPassword("bb");
		requestDto.setIdName("中文");
		requestDto.setIdNumber("123456789");
		RegisterResultDto result = memberService.doCreateMemberRnTx(requestDto);
		System.out.println("result = "+result.isRegisterSuccess());
		System.out.println("error = "+result.getRegisterError());
	}
}
