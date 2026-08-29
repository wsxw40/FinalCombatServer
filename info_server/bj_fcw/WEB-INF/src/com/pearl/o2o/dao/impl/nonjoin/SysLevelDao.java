package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelWeapon;

public class SysLevelDao extends BaseMappingDao {
	private static final int DEFAULTID = 1;
	private Map<Integer, LevelInfo> getAllLevelsMap() {
		return queryMappingBeanMapByRelatedId(LevelInfo.class, DEFAULTID);
	}
	public List<LevelInfo> getAllLevelsList(){
		Map<Integer, LevelInfo> map=getAllLevelsMap();
		List<LevelInfo> levelList=new ArrayList<LevelInfo>(map.values());
		Collections.sort(levelList,new Comparator<LevelInfo>(){

			@Override
			public int compare(LevelInfo o1, LevelInfo o2) {
				return ((Integer)o1.getIndex()).compareTo((Integer)o2.getIndex());
			}
			
		});
		return levelList;
	}
	public List<LevelInfo> getLevelsListByType(int type){
		Map<Integer, LevelInfo> map=getAllLevelsMap();
		List<LevelInfo> levelList=new ArrayList<LevelInfo>();
		for(Map.Entry<Integer, LevelInfo> entry: map.entrySet()) {  
			LevelInfo li=map.get(entry.getKey());
			if(li.getType()==type){
				levelList.add(li);
			}
		}
		if(levelList.size()!=0){
			Collections.sort(levelList);
		}
		return levelList;
	}
	public LevelInfo getLevelInfoById(int id){
		Map<Integer, LevelInfo> map=getAllLevelsMap();
		return map.get(id);
	}
	
	public void insertLevelInfo(LevelInfo pojo) throws Exception{
		insertObjIntoDBAndCache(pojo, DEFAULTID);
	}
	public  void updateLevel(LevelInfo pojo) throws Exception{
		updateMappingBeanInCache(pojo, DEFAULTID);
		updateObjInDB(pojo);
	}
	public  void deleteLevel(LevelInfo pojo) throws Exception{
		deleteObjFromDBAndCache(pojo, DEFAULTID);
	}
	
	
	
	public void insertLevelWeapon(LevelWeapon pojo){
		this.getSqlMapClientTemplate().insert("LevelWeapon.insert", pojo);
	}
	public  void updateLevelWeapon(LevelWeapon infoPojo){
		this.getSqlMapClientTemplate().update("LevelWeapon.update",infoPojo);
	}
	public  void deleteLevelWeapon(int id){
		this.getSqlMapClientTemplate().delete("LevelWeapon.delete",id);
	}
	public  LevelWeapon getLevelWeaponById(int id){
		return (LevelWeapon) this.getSqlMapClientTemplate().queryForObject("LevelWeapon.selectById", id);
	}
	@SuppressWarnings("unchecked")
	public  List<LevelWeapon> getLevelWeaponByLevelId(int id){
		return  (List<LevelWeapon>) this.getSqlMapClientTemplate().queryForList("LevelWeapon.selectByLevelId", id);
	}
	@SuppressWarnings("unchecked")
	public  List<LevelWeapon> getLevelWeapons(){
		return  (List<LevelWeapon>) this.getSqlMapClientTemplate().queryForList("LevelWeapon.select");
	}
}
