package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.LuckyPackage;
import com.pde.info.analyser.pojo.LuckyPackageStatistic;
import com.pde.info.analyser.pojo.Payment;
import com.pde.info.analyser.service.LuckyPackageService;
import com.pde.info.analyser.service.PaymentService;


public class LuckyPackageServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    LuckyPackageService luckyPackageService = (LuckyPackageService)context.getBean("luckyPackageService");
    PaymentService paymentService = (PaymentService)context.getBean("paymentService");
    
    public void insertUserTest() {
    	
    	LuckyPackage luckyPackage = new LuckyPackage();
    	luckyPackage.setPackageLevel(3);
    	luckyPackage.setPayType(1);
    	luckyPackage.setPlayerId(10);
    	luckyPackage.setSystemId(2);
    	luckyPackage.setSystemNumber(3);
    	luckyPackage.setUseType(1);
    	luckyPackageService.addLuckyPackage(luckyPackage);
    }
    
    
    public void getStatisticTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("packageLevel", "2");
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-01");
    	List<LuckyPackageStatistic> list = luckyPackageService.getStatistic(param);
    	for (LuckyPackageStatistic statistic : list) {
    		System.out.println("date=" + statistic.getStatisticDate() + " && userNumber = " + statistic.getUserNumberCopper() + " && usingNumber="+statistic.getUserNumberGold());
    	}
    }
    
    @Test
    public void getCardsUnitPrice() {
    	List<Payment> list = paymentService.getCardsUnitPrice();
    	for (Payment payment : list) {
    		System.out.println(payment.getName() + " = " + payment.getCost());
    	}
    }

}
