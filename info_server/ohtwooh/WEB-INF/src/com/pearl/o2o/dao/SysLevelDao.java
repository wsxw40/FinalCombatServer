package com.pearl.o2o.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.LevelInfoPojo;
import com.pearl.o2o.pojo.LevelWeapon;

public class SysLevelDao extends SqlMapClientDaoSupport {

	public void insertLevelInfo(LevelInfoPojo pojo){
		this.getSqlMapClientTemplate().insert("LevelInfo.insert", pojo);
	}
	public void insertLevelWeapon(LevelWeapon pojo){
		this.getSqlMapClientTemplate().insert("LevelWeapon.insert", pojo);
	}
	
	public LevelInfoPojo getLevelInfoById(int id){
		return (LevelInfoPojo) this.getSqlMapClientTemplate().queryForObject("LevelInfo.selectById", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<LevelInfoPojo> getAllLevels(){
		return this.getSqlMapClientTemplate().queryForList("LevelInfo.selectAll");
	}
	@SuppressWarnings("unchecked")
	public  void updateLevel(LevelInfoPojo infoPojo){
		this.getSqlMapClientTemplate().update("LevelInfo.update",infoPojo);
	}
	@SuppressWarnings("unchecked")
	public  void deleteLevel(int id){
		this.getSqlMapClientTemplate().delete("LevelInfo.delete",id);
	}
	
	@SuppressWarnings("unchecked")
	public  void updateLevelWeapon(LevelWeapon infoPojo){
		this.getSqlMapClientTemplate().update("LevelWeapon.update",infoPojo);
	}
	@SuppressWarnings("unchecked")
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
