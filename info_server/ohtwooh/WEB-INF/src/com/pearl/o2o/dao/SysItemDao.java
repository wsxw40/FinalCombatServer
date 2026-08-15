package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;

public class SysItemDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemList(int type, int subType,String gender) {
		Map map=new HashMap();
		map.put("type", type);
		map.put("subType", subType);
		map.put("isDeleted", Constants.BOOLEAN_NO);
		if(gender.equals(Constants.GENDER_FEMALE)){
			map.put("gender", Constants.GENDER_MALE);
		}else if(gender.equals(Constants.GENDER_MALE)){
			map.put("gender", Constants.GENDER_FEMALE);
		}
		return this.getSqlMapClientTemplate().queryForList("SysItem.select",map);
	}
	//get filter part
	@SuppressWarnings("unchecked")
	public List<SysItem> getSuitableParts(int userId,int playerId, int type, int subType) {
		Map map=new HashMap();
		map.put("type", type);
		map.put("subType", subType);
		map.put("userId", userId);
		map.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("SysItem.selectSuitableParts",map);
	}
	//for gm service
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemList(int type, int subType) {
		Map map=new HashMap();
		map.put("type", type);
		map.put("subType", subType);
		return this.getSqlMapClientTemplate().queryForList("SysItem.select",map);
	}
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemList(int type) {
		Map map=new HashMap();
		map.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("SysItem.select",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysItem> getAllPackgeItem(Integer packageId) {
		return this.getSqlMapClientTemplate().queryForList("SysItem.selectSysPackage",packageId);
	}
	
	@SuppressWarnings("unchecked")
	public SysItem getSystemItemById(Integer itemId) {
		return (SysItem)this.getSqlMapClientTemplate().queryForObject("SysItem.selectSysItemById",itemId);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysItem> getAllSystemItem() {
		return (List<SysItem>) this.getSqlMapClientTemplate().queryForList("SysItem.select");
	}
	
	@SuppressWarnings("unchecked")	
	public SysItem getWeaponByPartName(String Name) {
		return (SysItem)this.getSqlMapClientTemplate().queryForObject("SysItem.selectSysItemByName",Name);
	}
	
	@SuppressWarnings("unchecked")
	public Integer countSystemItemSuitable(Integer id, Integer type, Integer subtype) {
		HashMap param = new HashMap();
		param.put("id", id);
		param.put("type", type);
		param.put("subtype", subtype);		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("SysItem.countSysItemSuitable",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemSuitable(Integer id, Integer start, Integer step, Integer type, Integer subtype) {
		HashMap param = new HashMap();
		param.put("id", id);
		param.put("start", start);
		param.put("step", step);
		param.put("type", type);
		param.put("subtype", subtype);		
		return this.getSqlMapClientTemplate().queryForList("SysItem.selectSysItemSuitable",param);
	}
	@SuppressWarnings("unchecked")
	public Integer createSysItem(SysItem sysItem){
		sysItem.setId(null);
		return (Integer)this.getSqlMapClientTemplate().insert("SysItem.insert", sysItem);
	}
	@SuppressWarnings("unchecked")
	public Integer createSysPackage(Integer packageId,Integer itemId){
		HashMap param = new HashMap();
		param.put("packageId", packageId);
		param.put("itemId", itemId);
		return (Integer)this.getSqlMapClientTemplate().insert("SysItem.insertPackage", param);
	}
	@SuppressWarnings("unchecked")
	public void deleteSysItem(SysItem sysItem){
		HashMap param = new HashMap();
		param.put("id", sysItem.getId());
		param.put("isDeleted", sysItem.getIsDeleted());
		this.getSqlMapClientTemplate().delete("SysItem.updateDeleted", param);
	}
	@SuppressWarnings("unchecked")
	public void updateSysItem(SysItem sysItem){
		this.getSqlMapClientTemplate().update("SysItem.update", sysItem);
	}
}
