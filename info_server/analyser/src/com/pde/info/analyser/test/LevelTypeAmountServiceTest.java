package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.LevelTypeAmount;
import com.pde.info.analyser.pojo.LevelTypeAmountStatistic;
import com.pde.info.analyser.service.LevelTypeAmountService;


public class LevelTypeAmountServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	LevelTypeAmountService levelTypeAmountService = (LevelTypeAmountService)context.getBean("levelTypeAmountService");

	@Test
    public void insertTest() {

    	LevelTypeAmount levelTypeAmount = new LevelTypeAmount();
    	levelTypeAmount.setLevelId(75);
    	levelTypeAmount.setType(0);
    	levelTypeAmountService.addLevelTypeAmount(levelTypeAmount);
    }
    
//	@Test
    public void getStatisticTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-05");
    	List<LevelTypeAmountStatistic> list = levelTypeAmountService.getStatistic(param);
//    	System.out.println("list.size=" + list.size());
    	for (LevelTypeAmountStatistic statistic : list) {
    		if (statistic==null) {
    			System.out.println("statistic is null");
    			continue;
    		}
    		System.out.println("date=" + statistic.getStatisticDate());
    	}
    }

}
