package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.pojo.CharacterKD;
import com.pde.info.analyser.pojo.CharacterKDStatistic;
import com.pde.info.analyser.service.CharacterKDService;


public class CharacterKDServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	CharacterKDService characterKDService = (CharacterKDService)context.getBean("characterKDService");

//	@Test
    public void insertTest() {

    	CharacterKD characterKD = new CharacterKD();
    	characterKD.setCharacterId(2);
    	characterKD.setCharacterKill(10);
    	characterKD.setCharacterDead(5);
    	characterKD.setLevelId(28);
    	characterKDService.addCharacterKD(characterKD);
    }
    
	@Test
    public void getStatisticTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("levelId", "28,30,51,74,78,109,112,116,117,129,130,136,142,156,159,171");
    	param.put("startDate", "2013-01-01");
    	param.put("endDate", "2013-07-05");
    	List<CharacterKDStatistic> list = characterKDService.getStatistic(param);
//    	System.out.println("list.size=" + list.size());
    	for (CharacterKDStatistic statistic : list) {
    		if (statistic==null) {
    			System.out.println("statistic is null");
    			continue;
    		}
    		System.out.println("date=" + statistic.getStatisticDate() + 
    				" && character a kill = "+statistic.getCharacterAKill()+
    				" && character b kill = "+statistic.getCharacterBKill()+
    				" && character c kill = "+statistic.getCharacterCKill()+
    				" && character d kill = "+statistic.getCharacterDKill()+
    				" && character e kill = "+statistic.getCharacterEKill()+
    				" && character f kill = "+statistic.getCharacterFKill()+
    				" && character g kill = "+statistic.getCharacterGKill()+
    				" && character a dead = "+statistic.getCharacterADead()+
    				" && character b dead = "+statistic.getCharacterBDead()+
    				" && character c dead = "+statistic.getCharacterCDead()+
    				" && character d dead = "+statistic.getCharacterDDead()+
    				" && character e dead = "+statistic.getCharacterEDead()+
    				" && character f dead = "+statistic.getCharacterFDead()+
    				" && character g dead = "+statistic.getCharacterGDead()
    		);
    	}
    }

}
