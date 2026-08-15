package com.pde.info.analyser.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pde.info.analyser.pojo.LevelAmountStatistic;
import com.pde.info.analyser.service.LevelAmountService;

@Controller
public class LevelAmountController {

	private LevelAmountService levelAmountService;
	
	@RequestMapping("listLevelAmount")
	public String listLevelAmount(HttpServletRequest req) {
		return "listLevelAmount";
	}

	/**
	 * e.g. http://localhost:9090/analyser/ajaxGetLevelAmount.do?startDate=2013-01-01&endDate=2013-06-10
	 */
	@RequestMapping(value="ajaxGetLevelAmount", method=RequestMethod.GET)
	public @ResponseBody List<LevelAmountStatistic> ajaxGetCharacter(@RequestParam String startDate, @RequestParam String endDate) {

//		System.out.println("param startDate="+startDate+" && endDate="+endDate+" && packagelevel="+packageLevel);
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			return null;
		}
		
		// get level_id at first
		Map<String,String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		List<LevelAmountStatistic> list = levelAmountService.getStatistic(param);
//		for (CharacterKDStatistic statistic : list) {
//    		if (statistic==null) {
//    			System.out.println("statistic is null");
//    			continue;
//    		}
//    		System.out.println("date=" + statistic.getStatisticDate() + 
//    				" && characterA = "+statistic.getCharacterA() + 
//    				" && characterB = "+statistic.getCharacterB() + 
//    				" && characterC = "+statistic.getCharacterC() + 
//    				" && characterD = "+statistic.getCharacterD() + 
//    				" && characterE = "+statistic.getCharacterE() + 
//    				" && characterF = "+statistic.getCharacterF() + 
//    				" && characterG = "+statistic.getCharacterG());
//    	}
		return list;
	}
	
	public void setLevelAmountService(LevelAmountService levelAmountService) {
		this.levelAmountService = levelAmountService;
	}
}
