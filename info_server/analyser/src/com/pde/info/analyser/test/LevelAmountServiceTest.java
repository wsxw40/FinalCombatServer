package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.LevelAmountStatistic;
import com.pde.info.analyser.service.LevelAmountService;


public class LevelAmountServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	LevelAmountService levelAmountService = (LevelAmountService)context.getBean("levelAmountService");

    
	@Test
    public void getStatisticTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-05");
    	List<LevelAmountStatistic> list = levelAmountService.getStatistic(param);
//    	System.out.println("list.size=" + list.size());
    	for (LevelAmountStatistic statistic : list) {
    		if (statistic==null) {
    			System.out.println("statistic is null");
    			continue;
    		}
    		System.out.println("date=" + statistic.getStatisticDate()+" && statistic.getLevel1()="+statistic.getLevel1());
    	}
    }

}
