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

import com.pde.info.analyser.pojo.MedalExchange;
import com.pde.info.analyser.pojo.MedalExchangeStatistic;
import com.pde.info.analyser.service.MedalExchangeService;

@Controller
public class MedalExchangeController{

	private MedalExchangeService medalExchangeService;


	
	@RequestMapping("listMedalExchange")
	public String listMedalExchange(HttpServletRequest req) {
		return "listMedalExchange";
	}
	
	@RequestMapping(value="ajaxGetMedalExchangeList", method=RequestMethod.GET)
	public @ResponseBody List<MedalExchangeStatistic> ajaxGetMedalExchangeList(@RequestParam String startDate, @RequestParam String endDate) {
		
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
		
		List<MedalExchangeStatistic> list = medalExchangeService.getStatistic(param);
		
		return list;
	}
	
//	@RequestMapping(value="addExchangeInfo", method=RequestMethod.GET)
//	public void addExchangeInfo(@RequestParam int playerId, @RequestParam int paymentId, @RequestParam int itemId,@RequestParam String itemName, @RequestParam int cost, @RequestParam int paymentUnit, @RequestParam int paymentUnitType) {
//		
//    	
//	}
	public void setMedalExchangeService(MedalExchangeService medalExchangeService) {
		this.medalExchangeService = medalExchangeService;
	}

}



