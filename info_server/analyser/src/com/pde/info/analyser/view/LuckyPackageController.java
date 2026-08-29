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

import com.pde.info.analyser.pojo.LuckyPackageStatistic;
import com.pde.info.analyser.service.LuckyPackageService;
import com.pde.info.analyser.service.PaymentService;

@Controller
public class LuckyPackageController {

	private LuckyPackageService luckyPackageService;
	private PaymentService paymentService;
	
	@RequestMapping("listLuckyPackage")
	public String listLuckyPackage(HttpServletRequest req) {
		req.setAttribute("unitPrices", paymentService.getCardsUnitPrice());
		return "listLuckyPackage";
	}
	
	@RequestMapping(value="ajaxGetLuckyPackage", method=RequestMethod.GET)
	public @ResponseBody List<LuckyPackageStatistic> ajaxGetLuckyPackage(@RequestParam String startDate, @RequestParam String endDate) {

		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			return null;
		}
		Map<String,String> param = new HashMap<String, String>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return luckyPackageService.getStatistic(param);
	}
	
//	@RequestMapping(value="ajaxGetLuckyPackage", method=RequestMethod.GET)
//	public @ResponseBody String ajaxExportLuckyPackage(@RequestParam String startDate, @RequestParam String endDate) {
//
//		List<LuckyPackageStatistic> list = this.ajaxGetLuckyPackage(startDate, endDate);
//		if (CollectionUtils.isEmpty(list))
//			return "";
//		
//		for (LuckyPackageStatistic statistic : list) {
//			
//		}
//		return "";
//	}
//	
//	
//	public void export() throws Exception {
//		String str[][] = {{"xx","123中文"}, {"yy好","en456"}};
//		File file = new File("xx.xls");
//		WritableWorkbook workbook = Workbook.createWorkbook(file);
//		WritableSheet sheet = workbook.createSheet("sheet1", 0);
//		
//		Label label = null;
//		for (int i=0; i<str.length; i++) {
//			for (int j=0; j<str[i].length; j++) {
//				label = new Label(j, i, str[i][j]);
//				sheet.addCell(label);
//			}
//		}
//		workbook.write();
//		workbook.close();
//	}

	public void setLuckyPackageService(LuckyPackageService luckyPackageService) {
		this.luckyPackageService = luckyPackageService;
	}
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
}
