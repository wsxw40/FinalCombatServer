package com.pde.info.analyser.persistence;

import com.pde.info.analyser.pojo.CharacterAmount;
import com.pde.info.analyser.pojo.CharacterKD;
import com.pde.info.analyser.pojo.GPointTotalStatistic;
import com.pde.info.analyser.pojo.ItemCostInfo;
import com.pde.info.analyser.pojo.LevelTypeAmount;
import com.pde.info.analyser.pojo.LuckyPackage;
import com.pde.info.analyser.pojo.MedalExchange;
import com.pde.info.analyser.pojo.ReChargeInfo;
import com.pde.info.analyser.service.CharacterAmountService;
import com.pde.info.analyser.service.CharacterKDService;
import com.pde.info.analyser.service.GPointTotalService;
import com.pde.info.analyser.service.ItemCostService;
import com.pde.info.analyser.service.LevelTypeAmountService;
import com.pde.info.analyser.service.LuckyPackageService;
import com.pde.info.analyser.service.MedalExchangeService;
import com.pde.info.analyser.service.ReChargeService;

public class PersistenceData {
	
	private MedalExchangeService medalExchangeService;
	private ItemCostService itemCostService;
	private ReChargeService reChargeService;
	private LuckyPackageService luckyPackageService;
	private GPointTotalService gPointTotalService;
	private CharacterAmountService characterAmountService;
	private CharacterKDService characterKDService;
	private LevelTypeAmountService levelTypeAmountService;
	
	public void save(String msg, String logName){
		String[] params=msg.split("\t");
		
		if(logName.equals("bjExchange")){
			saveMedalExchangeInfo(params);
		}else if(logName.equals("bjReCharge")){
			saveRechageInfo(params);
		}else if(logName.equals("bjItemCost")){
			saveItemCostInfo(params);
		}else if(logName.equals("bjLuckPackage")){
			saveLuckPackageInfo(params);
		}else if(logName.equals("bjGPointTotal")){
			saveGpTotalInfo(params);
		}else if(logName.equals("bjStageClear_ChAmount")){
			saveCharacterAmount(params);
		}else if(logName.equals("bjStageClear_ChKD")){
			saveCharacterKD(params);
		}else if(logName.equals("bjStageClear_LTAmount")){
			saveLevelTypeAmount(params);
		}
		
    
	}

	private void saveMedalExchangeInfo(String[] params){
	
		MedalExchange medalCost = new MedalExchange();
    	
    	medalCost.setPlayerId(Integer.valueOf(params[0]));
    	
    	medalCost.setPaymentId(Integer.valueOf(params[1]));
    	medalCost.setItemId(Integer.valueOf(params[2]));
    	medalCost.setItemName(params[3]);
    	medalCost.setCost(Integer.valueOf(params[4]));
    	medalCost.setPaymentUnit(Integer.valueOf(params[5]));
    	medalCost.setPaymentUnitType(Integer.valueOf(params[6]));
   
       	medalExchangeService.addExchangeInfo(medalCost);
	}
	
	private void saveRechageInfo(String[] params){
		
		ReChargeInfo reChargeInfo = new ReChargeInfo();
		
		reChargeInfo.setPlayerUserName(params[0]);
		reChargeInfo.setRmb(Integer.valueOf(params[1]));
		reChargeInfo.setPlayerName(params[2]);
		reChargeInfo.setPlayerRank(Integer.valueOf(params[3]));
		reChargeInfo.setPlayerId(Integer.valueOf(params[4]));		
		reChargeInfo.setIsNewer(Integer.valueOf(params[5]));
    	
		reChargeService.addReChargeInfo(reChargeInfo);
	}
	private void saveItemCostInfo(String[] params){
		
		ItemCostInfo itemCostInfo=new ItemCostInfo();
		itemCostInfo.setPlayerId(Integer.valueOf(params[0]));
		itemCostInfo.setCost(Integer.valueOf(params[2]));
		itemCostInfo.setPayType(Integer.valueOf(params[3]));
		itemCostInfo.setItemName(params[4]);
		itemCostInfo.setItemId(Integer.valueOf(params[5]));
		itemCostInfo.setItemType(Integer.valueOf(params[6]));
		itemCostInfo.setPayUnitType(Integer.valueOf(params[7]));
		itemCostInfo.setPayUnit(Integer.valueOf(params[8]));
		itemCostInfo.setPayUse(Integer.valueOf(params[9]));

		itemCostService.addItemCostInfo(itemCostInfo);
	}
	
	private void saveLuckPackageInfo(String[] params){
		LuckyPackage luckyPackage = new LuckyPackage();
		
		luckyPackage.setPlayerId(Integer.valueOf(params[0]));
    	luckyPackage.setPackageLevel(Integer.valueOf(params[1]));
    	luckyPackage.setPayType(Integer.valueOf(params[2]));
        luckyPackage.setSystemId(Integer.valueOf(params[3]));
    	luckyPackage.setSystemNumber(Integer.valueOf(params[4]));
    	luckyPackage.setUseType(Integer.valueOf(params[5]));
    	luckyPackage.setChipPrice(Integer.valueOf(params[6]));
    	luckyPackageService.addLuckyPackage(luckyPackage);
	}
	
	private void saveGpTotalInfo(String[] params){
		GPointTotalStatistic gPonitTotal = new GPointTotalStatistic();
		gPonitTotal.setTotalGPoint(Integer.valueOf(params[0]));
		gPonitTotal.setTotalPlayer(Integer.valueOf(params[1]));
		gPonitTotal.setAmount1(Integer.valueOf(params[2]));
		gPonitTotal.setAmount2(Integer.valueOf(params[3]));
		gPonitTotal.setAmount3(Integer.valueOf(params[4]));
		gPonitTotal.setAmount4(Integer.valueOf(params[5]));
		gPonitTotal.setAmount5(Integer.valueOf(params[6]));
		gPonitTotal.setAmount6(Integer.valueOf(params[7]));
		gPonitTotal.setAmount7(Integer.valueOf(params[8]));
		gPonitTotal.setAmount8(Integer.valueOf(params[9]));
		gPonitTotal.setAmount9(Integer.valueOf(params[10]));
		gPonitTotal.setAmount10(Integer.valueOf(params[11]));
		gPonitTotal.setAmount11(Integer.valueOf(params[12]));
		gPonitTotal.setAmount12(Integer.valueOf(params[13]));
		
		gPointTotalService.addGPointTotal(gPonitTotal);
	}
	
	private void saveCharacterAmount(String[] params){
		CharacterAmount characterAmount = new CharacterAmount();
    	characterAmount.setCharacterId(Integer.valueOf(params[0]));
    	characterAmount.setLevelId(Integer.valueOf(params[1]));
    	characterAmount.setUsingNumber(Integer.valueOf(params[2]));
    	characterAmountService.addCharacterAmount(characterAmount);
	}
	private void saveCharacterKD(String[] params){
		CharacterKD characterKD = new CharacterKD();
		characterKD.setCharacterId(Integer.valueOf(params[0]));
		characterKD.setLevelId(Integer.valueOf(params[1]));
		characterKD.setCharacterKill(Integer.valueOf(params[2]));
		characterKD.setCharacterDead(Integer.valueOf(params[3]));
    	characterKDService.addCharacterKD(characterKD);
	}
	private void saveLevelTypeAmount(String[] params){
		LevelTypeAmount levelTypeAmount = new LevelTypeAmount();
		levelTypeAmount.setLevelId(Integer.valueOf(params[0]));
		levelTypeAmount.setType(Integer.valueOf(params[1]));
		levelTypeAmountService.addLevelTypeAmount(levelTypeAmount);
	}
	
	public void setMedalExchangeService(MedalExchangeService medalExchangeService) {
		this.medalExchangeService = medalExchangeService;
	}

	public void setItemCostService(ItemCostService itemCostService) {
		this.itemCostService = itemCostService;
	}

	public void setReChargeService(ReChargeService reChargeService) {
		this.reChargeService = reChargeService;
	}

	public void setLuckyPackageService(LuckyPackageService luckyPackageService) {
		this.luckyPackageService = luckyPackageService;
	}

	public void setgPointTotalService(GPointTotalService gPointTotalService) {
		this.gPointTotalService = gPointTotalService;
	}

	public void setCharacterAmountService(
			CharacterAmountService characterAmountService) {
		this.characterAmountService = characterAmountService;
	}

	public void setCharacterKDService(CharacterKDService characterKDService) {
		this.characterKDService = characterKDService;
	}

	public void setLevelTypeAmountService(
			LevelTypeAmountService levelTypeAmountService) {
		this.levelTypeAmountService = levelTypeAmountService;
	}
	
}
