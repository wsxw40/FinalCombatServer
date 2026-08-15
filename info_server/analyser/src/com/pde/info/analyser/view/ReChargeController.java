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

import com.pde.info.analyser.pojo.ReChargeInfo;
import com.pde.info.analyser.pojo.ReChargeStatistic;
import com.pde.info.analyser.service.ReChargeService;


@Controller
public class ReChargeController {

	private ReChargeService reChargeService;


	
	@RequestMapping("listReCharge")
	public String listReCharge(HttpServletRequest req) {
		return "listReCharge";
	}
	
	@RequestMapping("listRechargeReport")
	public String listReChargeReport(HttpServletRequest req) {
		return "listRechargeReport";
	}
	
	@RequestMapping(value="ajaxGetReChargeList", method=RequestMethod.GET)
	public @ResponseBody List<ReChargeStatistic> ajaxGetReChargeList(@RequestParam String startDate, @RequestParam String endDate) {
		
		try {
			if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate) ) {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		List<ReChargeStatistic> list = reChargeService.getStatistic(param);
		
		return list;
	}

//	@RequestMapping(value="addReChagrgeInfo", method=RequestMethod.GET)
//	public void addReChagrgeInfo(@RequestParam String playerUserName, @RequestParam String playerName, @RequestParam int playerId,@RequestParam int playerRank,
//			@RequestParam int rmb, @RequestParam int isNewer) {
//		ReChargeInfo reChargeInfo = new ReChargeInfo();
//		
//		reChargeInfo.setPlayerUserName(playerUserName);
//		reChargeInfo.setPlayerName(playerName);
//		reChargeInfo.setPlayerId(playerId);
//		reChargeInfo.setPlayerRank(playerRank);
//		reChargeInfo.setRmb(rmb);
//		reChargeInfo.setIsNewer(isNewer==0 ? true: false);
//    	
//		reChargeService.addReChargeInfo(reChargeInfo);
//    	
//	}

	public void setReChargeService(ReChargeService reChargeService) {
		this.reChargeService = reChargeService;
	}


	
}


