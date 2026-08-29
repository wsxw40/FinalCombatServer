package com.pde.uweb.ma.service.impl;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pde.uweb.ma.dto.LoginRequestDto;
import com.pde.uweb.ma.dto.LoginResultDto;
import com.pde.uweb.ma.dto.RegisterRequestDto;
import com.pde.uweb.ma.dto.RegisterResultDto;
import com.pde.uweb.ma.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-uweb-biz-ma.xml",
		"classpath:/META-INF/spring/spring-uweb-database.xml",
		"classpath:/META-INF/spring/spring-uweb-framework.xml" })
@TransactionConfiguration(defaultRollback = false, transactionManager = "dataSourceTransactionManager_ma")
public class TestMemberServiceImpl {
	@Resource(name = "memberService_ma")
	private MemberService memberService;
	
//	@Test
	public void testDoCreateMemberRnTx() throws Exception {
		
		RegisterRequestDto requestDto = new RegisterRequestDto();
		requestDto.setUserName("xxxx");
		requestDto.setPassword("yyyy");
		requestDto.setIdName("中文");
		requestDto.setIdNumber("123456789");
		RegisterResultDto result = memberService.doCreateMemberRnTx(requestDto);
		System.out.println("result = "+result.isRegisterSuccess());
		System.out.println("error = "+result.getRegisterError());
	}
	
//	@Test
	public void testDoLogin() throws Exception {
		
		LoginRequestDto requestDto = new LoginRequestDto();
		requestDto.setUserName("xxxx");
		requestDto.setPassword("yyyys");
		LoginResultDto result = memberService.doLogin(requestDto);
		System.out.println("result = "+result.isLoginSuccess());
		System.out.println("error = "+result.getLoginError());
	}
}
