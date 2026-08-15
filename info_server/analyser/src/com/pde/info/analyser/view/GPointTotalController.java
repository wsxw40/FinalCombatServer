package com.pde.info.analyser.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pde.info.analyser.pojo.GPointTotalStatistic;
import com.pde.info.analyser.service.GPointTotalService;

@Controller
public class GPointTotalController {

	GPointTotalService gPointTotalService;
	
	@RequestMapping("listGPointTotal")
	public String listGPointTotal(HttpServletRequest req) {
		return "listGPointTotal";
	}

//	@RequestMapping(value="addGPointTotal", method=RequestMethod.GET)
//	public void addGPointTotal(@RequestParam String gpointJson) {
//		JSONObject json = JSONObject.parseObject(gpointJson);
//		if (json==null || json.isEmpty())
//			return;
//
//		GPointTotalStatistic gPonitTotal = new GPointTotalStatistic();
//		gPonitTotal.setTotalGPoint(json.getIntValue("totalGPoint"));
//		gPonitTotal.setTotalPlayer(json.getIntValue("totalPlayer"));
//		gPonitTotal.setAmount1(json.getIntValue("amount1"));
//		gPonitTotal.setAmount2(json.getIntValue("amount2"));
//		gPonitTotal.setAmount3(json.getIntValue("amount3"));
//		gPonitTotal.setAmount4(json.getIntValue("amount4"));
//		gPonitTotal.setAmount5(json.getIntValue("amount5"));
//		gPonitTotal.setAmount6(json.getIntValue("amount6"));
//		gPonitTotal.setAmount7(json.getIntValue("amount7"));
//		gPonitTotal.setAmount8(json.getIntValue("amount8"));
//		gPonitTotal.setAmount9(json.getIntValue("amount9"));
//		gPonitTotal.setAmount10(json.getIntValue("amount10"));
//		gPonitTotal.setAmount11(json.getIntValue("amount11"));
//		gPonitTotal.setAmount12(json.getIntValue("amount12"));
//		
//		gPointTotalService.addGPointTotal(gPonitTotal);
//	}
	
	@RequestMapping(value="ajaxGetGPointTotal", method=RequestMethod.GET)
	public @ResponseBody List<GPointTotalStatistic> ajaxGetGPointTotal( @RequestParam String startDate, @RequestParam String endDate) {
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return gPointTotalService.getStatistic(param);
	}
	
	public void setGPointTotalService(GPointTotalService gPointTotalService) {
		this.gPointTotalService = gPointTotalService;
	}
}
