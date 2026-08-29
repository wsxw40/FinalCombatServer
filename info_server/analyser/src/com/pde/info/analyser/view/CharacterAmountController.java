package com.pde.info.analyser.view;

import java.util.ArrayList;
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

import com.pde.info.analyser.pojo.CharacterAmount;
import com.pde.info.analyser.pojo.CharacterAmountStatistic;
import com.pde.info.analyser.service.CharacterAmountService;
import com.pde.info.analyser.service.SysLevelService;

@Controller
public class CharacterAmountController{

	private CharacterAmountService characterAmountService;
	private SysLevelService sysLevelService;

	@RequestMapping("listCharacterAmount")
	public String listCharacterAmount(HttpServletRequest req) {
		
		List<String> levels = sysLevelService.getAllMapNames();
		req.setAttribute("levels", levels);
		return "listCharacterAmount";
	}

	/**
	 * get characters using data
	 * 
	 * @param displayName map_name
	 * @param type  game_model
	 * 
	 * e.g. http://localhost:9090/analyser/ajaxGetCharacterAmount.do?startDate=2013-01-01&endDate=2013-06-10&type=0
	 */
	@RequestMapping(value="ajaxGetCharacterAmount", method=RequestMethod.GET)
	public @ResponseBody List<CharacterAmountStatistic> ajaxGetCharacter(
			@RequestParam String startDate, 
			@RequestParam String endDate,
			@RequestParam(required=false) String type, 
			@RequestParam(required=false) String displayName) {

//		System.out.println("param startDate="+startDate+" && endDate="+endDate+" && packagelevel="+packageLevel);

		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			return null;
		}
		
		// get level_id at first
		Map<String,String> param = new HashMap<String, String>();
		param.put("type", type);
		param.put("displayName", displayName);
//		System.out.println("type="+type+" && displayName="+displayName);
		List<String>  levelIdList = sysLevelService.getLevelId(param);
		if (levelIdList.isEmpty()) {
			CharacterAmountStatistic statistic = new CharacterAmountStatistic();
			statistic.setCharacterA(0);
			statistic.setCharacterB(0);
			statistic.setCharacterC(0);
			statistic.setCharacterD(0);
			statistic.setCharacterE(0);
			statistic.setCharacterF(0);
			statistic.setCharacterG(0);
			
			List<CharacterAmountStatistic> list = new ArrayList<CharacterAmountStatistic>();
			list.add(statistic);
			return list;
		}
		
		StringBuilder sb = new StringBuilder(32);
		for (String id : levelIdList) {
    		sb.append(id).append(",");
    	}
    	String levelId = sb.toString();
    	levelId = levelId.substring(0, levelId.length()-1);
		param.put("levelId", levelId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		List<CharacterAmountStatistic> list = characterAmountService.getStatistic(param);
//		for (CharacterAmountStatistic statistic : list) {
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
	
	/**
	 * http://localhost:9090/analyser/addCharacterAmount.do?characterId=3&levelId=2&usingNumber=5
	 */
//	@RequestMapping(value="addCharacterAmount", method=RequestMethod.GET)
//	public void addCharacterAmount(@RequestParam int characterId, @RequestParam int levelId, @RequestParam int usingNumber) {
//		
//		CharacterAmount characterAmount = new CharacterAmount();
//    	characterAmount.setCharacterId(characterId);
//    	characterAmount.setLevelId(levelId);
//    	characterAmount.setUsingNumber(usingNumber);
//    	characterAmountService.addCharacterAmount(characterAmount);
//	}
	
	public void setCharacterAmountService(CharacterAmountService characterAmountService) {
		this.characterAmountService = characterAmountService;
	}
	public void setSysLevelService(SysLevelService sysLevelService) {
		this.sysLevelService = sysLevelService;
	}


}
