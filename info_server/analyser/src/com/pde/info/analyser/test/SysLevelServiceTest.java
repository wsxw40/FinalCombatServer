package com.pde.info.analyser.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pde.info.analyser.service.SysLevelService;

public class SysLevelServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    SysLevelService sysLevelService = (SysLevelService)context.getBean("sysLevelService");
    
//    @Test
    public void getAllMapNamesTest() {

    	List<String> list = sysLevelService.getAllMapNames();
    	
    }
    
    @Test
    public void getLevelIdTest() {

    	Map<String, String> param = new HashMap<String, String>();
    	param.put("type", "0");
    	param.put("displayName", "风车小镇");

    	StringBuilder sb = new StringBuilder();
    	List<String> list = sysLevelService.getLevelId(param);
    	for (String id : list) {
    		sb.append(id).append(",");
    	}
    	String ids = sb.toString();
    	ids = ids.substring(0, ids.length()-1);
    	System.out.println("levelId=" + ids);
    }

}
