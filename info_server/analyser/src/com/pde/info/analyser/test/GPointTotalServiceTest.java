package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.GPointTotalStatistic;
import com.pde.info.analyser.service.GPointTotalService;


public class GPointTotalServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	GPointTotalService gPointTotalService = (GPointTotalService)context.getBean("gPointTotalService");

//	@Test
    public void insertTest() {
    	
    	GPointTotalStatistic gPonitTotal = new GPointTotalStatistic();
    	gPonitTotal.setAmount1(1);
    	gPonitTotal.setAmount2(2);
    	gPonitTotal.setAmount3(3);
    	gPonitTotal.setAmount4(4);
    	gPonitTotal.setAmount5(5);
    	gPonitTotal.setAmount6(6);
    	gPonitTotal.setAmount7(7);
    	gPonitTotal.setAmount8(8);
    	gPonitTotal.setAmount9(9);
    	gPonitTotal.setAmount10(10);
    	gPonitTotal.setAmount11(11);
    	gPonitTotal.setAmount12(12);
    	gPonitTotal.setTotalGPoint(100);
    	gPonitTotal.setTotalPlayer(200);
    	gPointTotalService.addGPointTotal(gPonitTotal);
    }
    
    @Test
    public void getStatisticTest() {
    	System.out.println("xxxx");
    	Map<String, String> param = new HashMap<String, String>();
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-05");
    	List<GPointTotalStatistic> list = gPointTotalService.getStatistic(param);
    	System.out.println("list.size()="+list.size());
    	for (GPointTotalStatistic statistic : list) {
    		if (statistic==null) {
    			System.out.println("statistic is null");
    			continue;
    		}
    		System.out.println("date=" + statistic.getStatisticDate() + 
    				" && amount1 = "+statistic.getAmount1() + 
    				" && amount1 = "+statistic.getAmount2() + 
    				" && total gponit = "+statistic.getTotalGPoint() + 
    				" && total player = "+statistic.getTotalPlayer());
    	}
    }

}
