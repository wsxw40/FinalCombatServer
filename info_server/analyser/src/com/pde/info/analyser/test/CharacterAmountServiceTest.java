package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.CharacterAmount;
import com.pde.info.analyser.pojo.CharacterAmountStatistic;
import com.pde.info.analyser.service.CharacterAmountService;


public class CharacterAmountServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	CharacterAmountService characterAmountService = (CharacterAmountService)context.getBean("characterAmountService");

//	@Test
    public void insertTest() {
    	
    	CharacterAmount characterAmount = new CharacterAmount();
    	characterAmount.setCharacterId(111);
    	characterAmount.setLevelId(3);
    	characterAmount.setUsingNumber(50);
    	characterAmountService.addCharacterAmount(characterAmount);
    }
    
    @Test
    public void getStatisticTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("levelId", "28,30,51,74,78,109,112,116,117,129,130,136,142,156,159,171");
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-05");
    	List<CharacterAmountStatistic> list = characterAmountService.getStatistic(param);
//    	System.out.println("list.size=" + list.size());
    	for (CharacterAmountStatistic statistic : list) {
    		if (statistic==null) {
    			System.out.println("statistic is null");
    			continue;
    		}
    		System.out.println("date=" + statistic.getStatisticDate() + 
    				" && characterA = "+statistic.getCharacterA() + 
    				" && characterB = "+statistic.getCharacterB() + 
    				" && characterC = "+statistic.getCharacterC() + 
    				" && characterD = "+statistic.getCharacterD() + 
    				" && characterE = "+statistic.getCharacterE() + 
    				" && characterF = "+statistic.getCharacterF() + 
    				" && characterG = "+statistic.getCharacterG());
    	}
    }

}
