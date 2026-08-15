/**
 * 
 */
package com.pde.uweb.ma.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pde.uweb.partner.dto.PartnerRegistRequestDto;
import com.pde.uweb.partner.service.PartnerUserService;

/**
 * @author Huanggang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-uweb-biz-partner.xml",
		"classpath:/META-INF/spring/spring-uweb-database.xml",
		"classpath:/META-INF/spring/spring-uweb-framework.xml" })
@TransactionConfiguration(defaultRollback = false, transactionManager = "dataSourceTransactionManager_ma")
public class PartnerUserServiceTest {
	
	
	@Resource(name = "partnerUserService_biz")
	private PartnerUserService partnerUserService ;
	
	
	@Test
	public void testCreateUser(){
		
		for(int i=1;i<100;i++){
			PartnerRegistRequestDto requestDto=new PartnerRegistRequestDto();
			requestDto.setEm("test"+i+"@163.com");
			requestDto.setGameAccount("test"+i);
			requestDto.setPartnerId("partner_id_1");
			requestDto.setPassword(md5("123456"));
			this.partnerUserService .doCreateMember(requestDto);
			System.out.println("test"+i+""+",123456");
		}
		
	
		
	}
	
	
	
	
	
	private static MessageDigest md5;

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String md5(String str, String charset) {
		StringBuilder sb = new StringBuilder();
		try {
			byte[] bb = md5.digest(str.getBytes(charset));
			for (byte b : bb) {
				sb.append(hexDigits[b >>> 4 & 0xf]);
				sb.append(hexDigits[b & 0xf]);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static String md5(String str) {
		return md5(str, "UTF-8").toLowerCase();
	}

//	public static void main(String[] args) {
//		String encodeStr = md5("111111");
//		System.out.println(encodeStr);
//	}
	
	
	
	
	
	
	
  
}
