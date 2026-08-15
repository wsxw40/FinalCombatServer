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

import com.pde.info.analyser.pojo.CharacterKDStatistic;
import com.pde.info.analyser.service.CharacterKDService;
import com.pde.info.analyser.service.SysLevelService;

@Controller
public class CharacterKDController {

	private CharacterKDService characterKDService;
	private SysLevelService sysLevelService;

	@RequestMapping("listCharacterKD")
	public String listCharacterKD(HttpServletRequest req) {
		
		List<String> levels = sysLevelService.getAllMapNames();
		req.setAttribute("levels", levels);
		return "listCharacterKD";
	}

	/**
	 * get characters using data
	 * 
	 * @param displayName map_name
	 * @param type  game_model
	 * 
	 * e.g. http://localhost:9090/analyser/ajaxGetCharacterKD.do?startDate=2013-01-01&endDate=2013-06-10&type=0
	 */
	@RequestMapping(value="ajaxGetCharacterKD", method=RequestMethod.GET)
	public @ResponseBody List<CharacterKDStatistic> ajaxGetCharacter(
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
			CharacterKDStatistic statistic = new CharacterKDStatistic();
			statistic.setCharacterAKill(0);
			statistic.setCharacterBKill(0);
			statistic.setCharacterCKill(0);
			statistic.setCharacterDKill(0);
			statistic.setCharacterEKill(0);
			statistic.setCharacterFKill(0);
			statistic.setCharacterGKill(0);

			statistic.setCharacterADead(0);
			statistic.setCharacterBDead(0);
			statistic.setCharacterCDead(0);
			statistic.setCharacterDDead(0);
			statistic.setCharacterEDead(0);
			statistic.setCharacterFDead(0);
			statistic.setCharacterGDead(0);
			
			List<CharacterKDStatistic> list = new ArrayList<CharacterKDStatistic>();
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
		
		List<CharacterKDStatistic> list = characterKDService.getStatistic(param);
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
	
	/**
	 * http://localhost:9090/analyser/addCharacterKD.do?characterId=3&levelId=28&kill=3&dead=0
	 */
//	@RequestMapping(value="addCharacterKD", method=RequestMethod.GET)
//	public void addCharacterKD(@RequestParam int characterId, @RequestParam int levelId, @RequestParam int kill, @RequestParam int dead) {
//		
//		CharacterKD characterKD = new CharacterKD();
//		characterKD.setCharacterId(characterId);
//		characterKD.setLevelId(levelId);
//		characterKD.setCharacterKill(characterId);
//		characterKD.setCharacterDead(characterId);
//    	characterKDService.addCharacterKD(characterKD);
//	}
	
	public void setCharacterKDService(CharacterKDService characterKDService) {
		this.characterKDService = characterKDService;
	}
	public void setSysLevelService(SysLevelService sysLevelService) {
		this.sysLevelService = sysLevelService;
	}

}
