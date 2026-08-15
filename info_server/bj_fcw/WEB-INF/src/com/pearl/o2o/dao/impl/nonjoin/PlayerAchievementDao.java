package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerAchievement0;
import com.pearl.o2o.pojo.PlayerAchievement1;
import com.pearl.o2o.pojo.PlayerAchievement2;
import com.pearl.o2o.pojo.PlayerAchievement3;
import com.pearl.o2o.pojo.PlayerAchievement4;
import com.pearl.o2o.pojo.PlayerAchievement5;
import com.pearl.o2o.pojo.PlayerAchievement6;
import com.pearl.o2o.pojo.PlayerAchievement7;
import com.pearl.o2o.pojo.SysAchievementBase;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class PlayerAchievementDao extends BaseMappingDao{
	
	public List<PlayerAchievement> getPlayerAchievementList(int playerId) throws Exception{
		Map<Integer, PlayerAchievement0> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement0.class, playerId);
		Map<Integer, PlayerAchievement1> paogeMap = queryMappingBeanMapByRelatedId(PlayerAchievement1.class, playerId);
		Map<Integer, PlayerAchievement2> huojianMap = queryMappingBeanMapByRelatedId(PlayerAchievement2.class, playerId);
		Map<Integer, PlayerAchievement3> jiqiangMap = queryMappingBeanMapByRelatedId(PlayerAchievement3.class, playerId);
		Map<Integer, PlayerAchievement4> jujiMap = queryMappingBeanMapByRelatedId(PlayerAchievement4.class, playerId);
		Map<Integer, PlayerAchievement5> huoyanMap = queryMappingBeanMapByRelatedId(PlayerAchievement5.class, playerId);
		Map<Integer, PlayerAchievement6> yiliaoMap = queryMappingBeanMapByRelatedId(PlayerAchievement6.class, playerId);
		Map<Integer, PlayerAchievement7> gongchengMap = queryMappingBeanMapByRelatedId(PlayerAchievement7.class, playerId);//TODO 新成就
		List<PlayerAchievement> resultList = splitObject(baseMap);
		resultList.addAll(splitObject(paogeMap));
		resultList.addAll(splitObject(huojianMap));
		resultList.addAll(splitObject(jiqiangMap));
		resultList.addAll(splitObject(jujiMap));
		resultList.addAll(splitObject(huoyanMap));
		resultList.addAll(splitObject(yiliaoMap));
		resultList.addAll(splitObject(gongchengMap));//TODO 新成就
		return resultList;
	}
	
	public void createPlayerAchievement(int playerId, SysAchievementBase eachBase) throws Exception {
		if (eachBase.getType() == 1) {
			PlayerAchievement0 pa0 = new PlayerAchievement0();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 1) {
			PlayerAchievement1 pa0 = new PlayerAchievement1();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 2) {
			PlayerAchievement2 pa0 = new PlayerAchievement2();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 3) {
			PlayerAchievement3 pa0 = new PlayerAchievement3();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 4) {
			PlayerAchievement4 pa0 = new PlayerAchievement4();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 5) {
			PlayerAchievement5 pa0 = new PlayerAchievement5();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 6) {
			PlayerAchievement6 pa0 = new PlayerAchievement6();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 7) {//TODO 新成就
			PlayerAchievement7 pa0 = new PlayerAchievement7();
			pa0.setLevel(1);
			pa0.setNumber(0);
			pa0.setPlayerId(playerId);
			pa0.setStatus(0);
			pa0.setSysAchievementIds(eachBase.getIds());
			pa0.setGroup(eachBase.getGroup());
			pa0.setTotalLevel(eachBase.getTotalLevel());
			this.insertObjIntoDBAndCache(pa0, playerId);
		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 8) {

		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 9) {

		} else if (eachBase.getType() == 3 && eachBase.getCharacterId() == 10) {

		}
	}
	
	public void updatePlayerAchievement(PlayerAchievement pa) throws Exception{
		
		int group = pa.getAchievement().getGroup();
		int type = pa.getAchievement().getType();
		if(1 == type){
			Map<Integer, PlayerAchievement0> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement0.class, pa.getPlayerId());
			for(PlayerAchievement0 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		} else if(3 == type && pa.getAchievement().getCharacterId() == 1){
			Map<Integer, PlayerAchievement1> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement1.class, pa.getPlayerId());
			for(PlayerAchievement1 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		} else if(3 == type && pa.getAchievement().getCharacterId() == 2){
			Map<Integer, PlayerAchievement2> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement2.class, pa.getPlayerId());
			for(PlayerAchievement2 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		else if(3 == type && pa.getAchievement().getCharacterId() == 3){
			Map<Integer, PlayerAchievement3> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement3.class, pa.getPlayerId());
			for(PlayerAchievement3 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						int num = pa.getNumber();
						pa0.setNumber(num);
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		else if(3 == type && pa.getAchievement().getCharacterId() == 4){
			Map<Integer, PlayerAchievement4> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement4.class, pa.getPlayerId());
			for(PlayerAchievement4 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		else if(3 == type && pa.getAchievement().getCharacterId() == 5){
			Map<Integer, PlayerAchievement5> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement5.class, pa.getPlayerId());
			for(PlayerAchievement5 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		else if(3 == type && pa.getAchievement().getCharacterId() == 6){
			Map<Integer, PlayerAchievement6> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement6.class, pa.getPlayerId());
			for(PlayerAchievement6 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		//TODO 新成就
		else if(3 == type && pa.getAchievement().getCharacterId() == 7){
			Map<Integer, PlayerAchievement7> baseMap = queryMappingBeanMapByRelatedId(PlayerAchievement7.class, pa.getPlayerId());
			for(PlayerAchievement7 pa0 : baseMap.values()){
				if(pa0.getGroup() == group){
					if(pa.getStatus() == 1){
						pa0.setNumber(0);
						int totalLevel = pa0.getSysAchievementIds().split(",").length;
						if(totalLevel > pa0.getLevel()){
							pa0.setLevel(pa0.getLevel() + 1);
						} else if(totalLevel <= pa0.getLevel()) {
							pa0.setStatus(1);
						}
						
					} else {
						pa0.setNumber(pa.getNumber());
					}
					this.updateMappingBeanInCache(pa0, pa.getPlayerId());
					break;
				}
			}
		}
		
	}
	
	public static List<PlayerAchievement> splitObject(Map<Integer, ? extends BaseMappingBean<?>> baseMap) throws Exception {
		List<PlayerAchievement> result = new ArrayList<PlayerAchievement>();
		for (BaseMappingBean<?> obj : baseMap.values()) {
			if (obj instanceof PlayerAchievement0) {
				PlayerAchievement0 base = (PlayerAchievement0) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){//如果这套成就完成，将所有的playerachievement状态设置为1
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			} else if(obj instanceof PlayerAchievement1) {
				PlayerAchievement1 base = (PlayerAchievement1) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			} else if(obj instanceof PlayerAchievement2) {
				PlayerAchievement2 base = (PlayerAchievement2) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			}else if(obj instanceof PlayerAchievement3) {
				PlayerAchievement3 base = (PlayerAchievement3) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			} else if(obj instanceof PlayerAchievement4) {
				PlayerAchievement4 base = (PlayerAchievement4) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							//tempPa.setId(base.getPlayerId() + getPlayerAchievementId(sa));
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.stageClearLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			}else if(obj instanceof PlayerAchievement5) {
				PlayerAchievement5 base = (PlayerAchievement5) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.stageClearLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			}else if(obj instanceof PlayerAchievement6){

				PlayerAchievement6 base = (PlayerAchievement6) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			}else if(obj instanceof PlayerAchievement7){//TODO 新成就

				PlayerAchievement7 base = (PlayerAchievement7) obj;
				int i = 1;
				for (String saIdStr : base.getSysAchievementIds().split(",")) {
					try {
						int sysAchievementId = 0;
						try {
							sysAchievementId = Integer.parseInt(saIdStr);
						} catch(NumberFormatException e){
							ServiceLocator.exceptionLog.warn("splitObject() format sysAchievementId error sysAchievementId = " + sysAchievementId, e);
						}
//						SysAchievement sa = achievementMap.get(saId);
						if (0 < sysAchievementId) {
							PlayerAchievement tempPa = new PlayerAchievement();
//							tempPa.setAchievement(sa);
							tempPa.setAchievementId(sysAchievementId);
							tempPa.setPlayerId(base.getPlayerId());
							if(base.getStatus() == Constants.NUM_ONE){
								tempPa.setStatus(Constants.NUM_ONE);
								tempPa.setVisible(Constants.NUM_ONE);
//								tempPa.setNumber(sa.getNumber());
							} else {
								int level = base.getLevel();
								if (i < level) {
									tempPa.setStatus(Constants.NUM_ONE);
								} else {
									tempPa.setStatus(Constants.NUM_ZERO);
								}
								if(i < level){
//									tempPa.setNumber(sa.getNumber());
								} else if(i == level){
									tempPa.setNumber(base.getNumber());
								} else if(i > level){
									tempPa.setNumber(Constants.NUM_ZERO);
								}
								if (i <= level) {
									tempPa.setVisible(Constants.NUM_ONE);
								} else {
									tempPa.setVisible(Constants.NUM_ZERO);
								}
							}
							result.add(tempPa);
						}
					} catch (NumberFormatException e) {
						i++;
						ServiceLocator.fileLog.warn("sysItemId format error when splitObject!");
					}
					i++;
				}
			}
		}
		return result;
	}
}
