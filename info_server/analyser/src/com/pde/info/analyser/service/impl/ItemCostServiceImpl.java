package com.pde.info.analyser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.ItemCostDao;
import com.pde.info.analyser.pojo.ItemCostInfo;
import com.pde.info.analyser.pojo.ItemCostStatistic;
import com.pde.info.analyser.service.ItemCostService;

public class ItemCostServiceImpl implements ItemCostService{
	private ItemCostDao itemCostDao;

	public void setItemCostDao(ItemCostDao itemCostDao) {
		this.itemCostDao = itemCostDao;
	}

	@Override
	public void addItemCostInfo(ItemCostInfo itemCostInfo) {
		// TODO Auto-generated method stub

		itemCostDao.addItemCostInfo(itemCostInfo);

	}

	@Override
	public List<ItemCostStatistic> getStatistic(Map<String, String> param) {
		HashMap<String, String> dbParamMap=new HashMap<String, String>();
		dbParamMap.put("startDate", param.get("startDate"));
		dbParamMap.put("endDate", param.get("endDate"));
		
		List<ItemCostStatistic> dblist=itemCostDao.getStatistic(dbParamMap);
		
				
		String itemNameString=param.get("itemName");
		int itemId=param.get("itemId").length()>0 ? Integer.valueOf(param.get("itemId")) :0;
		int itemType=Integer.valueOf(param.get("itemType"));
		int payType=Integer.valueOf(param.get("payType"));
		
		List<ItemCostStatistic> list=new ArrayList<ItemCostStatistic>();
		for(ItemCostStatistic itemCostStatistic: dblist){
			boolean flag=true;
			if(itemNameString!=null && itemNameString.length()>0){
				if(!itemCostStatistic.getItemName().contains(itemNameString)){
					flag=false;
				}
			}
			if(itemId>0){
				if(itemCostStatistic.getItemId()!=itemId){
					flag=false;
				}
			}
			if(itemCostStatistic.getItemType()!=itemType){
				flag=false;
			}
			if(itemCostStatistic.getPayType()!=payType){
				flag=false;
			}
			if(flag){
				list.add(itemCostStatistic);
			}
		}
		
		return list;
	}

	@Override
	public List<ItemCostStatistic> getStatisticSum(Map<String, String> param) {
		List<ItemCostStatistic> list=itemCostDao.getStatistic(param);
		//this method just ignore the statisticDate 
		for(ItemCostStatistic itemCostStatistic: list){
			itemCostStatistic.setStatisticDate(param.get("startDate"));
		}

//		List<ItemCostStatistic> sumList=new ArrayList<ItemCostStatistic>();
//		while(list.size()>0){
//			ItemCostStatistic itemCostStatistic=list.get(0);
//			int sumPeople=itemCostStatistic.getPeopleAmount();
//			int sumMoney=itemCostStatistic.getMoneyAmount();
//			int sumNumber=itemCostStatistic.getNumberAmount();
//			
//			for(int i=1;i<list.size();i++){
//				if(itemCostStatistic.getItemId()==list.get(i).getItemId()){
//					sumPeople+=list.get(i).getPeopleAmount();
//					sumMoney+=list.get(i).getMoneyAmount();
//					sumNumber+=list.get(i).getNumberAmount();
//					list.remove(list.get(i));
//					i--;
//				}
//			}
//			
//			itemCostStatistic.setPeopleAmount(sumPeople);
//			itemCostStatistic.setMoneyAmount(sumMoney);
//			itemCostStatistic.setNumberAmount(sumNumber);
//			sumList.add(itemCostStatistic);
//			list.remove(0);
//		}
//		
		return list;
	}
	
	
}
