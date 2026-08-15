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

import com.pde.info.analyser.pojo.ItemCostInfo;
import com.pde.info.analyser.pojo.ItemCostStatistic;
import com.pde.info.analyser.service.ItemCostService;

@Controller
public class ItemCostController{
	private ItemCostService itemCostService;

	private static HashMap<String , Integer> weaponTypeHashMap=new HashMap<String, Integer>();
	
	static{
		weaponTypeHashMap.put("weapon", 1);
		weaponTypeHashMap.put("clothes", 2);
		weaponTypeHashMap.put("accessory", 3);
		weaponTypeHashMap.put("item", 4);
		weaponTypeHashMap.put("material", 5);
	}
	
	@RequestMapping("listItemCost")
	public String listItemCost(HttpServletRequest req) {
	
		return "listItemCost";
	}

	public void setItemCostService(ItemCostService itemCostService) {
		this.itemCostService = itemCostService;
	}
	
	@RequestMapping(value="ajaxGetItemCostList", method=RequestMethod.GET)
	public @ResponseBody List<ItemCostStatistic> ajaxGetItemCostList(@RequestParam String startDate, @RequestParam String endDate) {
		
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
		
		List<ItemCostStatistic> list = itemCostService.getStatisticSum(param);

		return combineStatistics(list);
	}
	
	@RequestMapping(value="ajaxGetItemCostListByVar", method=RequestMethod.GET)
	public @ResponseBody List<ItemCostStatistic> ajaxGetItemCostListByVar(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String itemName,@RequestParam String itemId,@RequestParam String itemType,@RequestParam String payType) {
	
		if(itemId!=null&&itemId.length()>0&&!itemId.matches("^\\d+$")){
			return null;
		}
		
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
		param.put("itemName", itemName);
		param.put("itemId", itemId);
		param.put("itemType", String.valueOf(weaponTypeHashMap.get(itemType)));
		param.put("payType", payType.equals("rmb")? "2" :"1");
		
		List<ItemCostStatistic> list = itemCostService.getStatistic(param);
		
		
		return combineStatistics(list);
	}
	
	
//	@RequestMapping(value="addItemCostInfo", method=RequestMethod.GET)
//	public void addItemCostInfo(@RequestParam int playerId,  @RequestParam int cost,@RequestParam int payType, @RequestParam String itemName,
//			 @RequestParam int paymentUnit, @RequestParam int paymentUnitType,@RequestParam int itemId,@ RequestParam int itemType) {
//		
//	}

	private List<ItemCostStatistic> combineStatistics(List<ItemCostStatistic> oriCostStatistics){
		ArrayList<ItemCostStatistic> combinedList=new ArrayList<ItemCostStatistic>();
	
		ItemCostStatistic firstStatistic=fillItemCostStatistic(oriCostStatistics.get(0),null);
		combinedList.add(firstStatistic);
		
	
		for(int i=1;i<oriCostStatistics.size();i++){
			boolean flag=false;
			ItemCostStatistic oriStatistic=oriCostStatistics.get(i);
			for(int j=combinedList.size()-1;j>=0;j--){
				ItemCostStatistic combineStatistic=combinedList.get(j);
				if(oriStatistic.getItemId()==combineStatistic.getItemId() &&
						oriStatistic.getStatisticDate().equals(combineStatistic.getStatisticDate())){
					if(oriStatistic.getPayUse()!=combineStatistic.getPayUse()){
						combineStatistic = fillItemCostStatistic(combineStatistic,oriStatistic);
						sumVarInStatistic(combineStatistic);
						
						flag=true;
						combinedList.set(j, combineStatistic);
						break;
					}
				}
					
			}
			if(!flag){
				
				fillItemCostStatistic(oriStatistic,null);
				combinedList.add(oriStatistic);
				
			}
			
		}
	
		return combinedList;
	}
	
	private ItemCostStatistic fillItemCostStatistic(ItemCostStatistic targetStatistic,ItemCostStatistic sourceStatistic){
		ItemCostStatistic statistic=sourceStatistic;
		if(targetStatistic==null){
			return null;
		}
		
		if (sourceStatistic==null&&targetStatistic!=null){
			statistic=targetStatistic;
		}
		int oriMoney=statistic.getMoneyAmount();
		int oriPeople=statistic.getPeopleAmount();
		int oriNumber=statistic.getNumberAmount();
		
		switch (statistic.getPayUse()) {
		case 1:
			targetStatistic.setBuyMoneyAmount(oriMoney);
			targetStatistic.setBuyNumberAmount(oriNumber);
			targetStatistic.setBuyPeopleAmount(oriPeople);
			break;
		case 2:
			targetStatistic.setRenewMoneyAmount(oriMoney);
			targetStatistic.setRenewNumberAmount(oriNumber);
			targetStatistic.setRenewPeopleAmount(oriPeople);
			break;
		case 3:
			targetStatistic.setGiftMoneyAmount(oriMoney);
			targetStatistic.setGiftNumberAmount(oriNumber);
			targetStatistic.setGiftPeopleAmount(oriPeople);
			break;
		default:
			break;
		}
		
		return targetStatistic;
	}

	private void sumVarInStatistic(ItemCostStatistic itemCostStatistic){
		int sumMoney=itemCostStatistic.getBuyMoneyAmount();
		int sumNumber=itemCostStatistic.getBuyNumberAmount();
		int sumPeople=itemCostStatistic.getBuyPeopleAmount();
		
		sumMoney+=itemCostStatistic.getRenewMoneyAmount();
		sumNumber+=itemCostStatistic.getRenewNumberAmount();
		sumPeople+=itemCostStatistic.getRenewPeopleAmount();
		
		sumMoney+=itemCostStatistic.getGiftMoneyAmount();
		sumNumber+=itemCostStatistic.getGiftNumberAmount();
		sumPeople+=itemCostStatistic.getGiftPeopleAmount();
		
		itemCostStatistic.setMoneyAmount(sumMoney);
		itemCostStatistic.setNumberAmount(sumNumber);
		itemCostStatistic.setPeopleAmount(sumPeople);
	}
}
