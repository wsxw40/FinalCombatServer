package com.pde.uweb.web.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.pde.uweb.database.ws.channel.ChannelPojo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/spring-uweb-web.xml",
		"classpath:/META-INF/spring/spring-uweb-database.xml",
		"classpath:/META-INF/spring/spring-uweb-framework.xml" 
		})
@TransactionConfiguration(defaultRollback = false, transactionManager = "dataSourceTransactionManager_ws")
public class TestWebChannelController {

}
