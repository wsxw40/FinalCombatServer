package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.ItemMod;

public class ItemModDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<ItemMod> getItemMod(ItemMod itemMod) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("ItemMod.select", itemMod);
	}	
	
	@SuppressWarnings("unchecked")
	public void create(int playerItemid,int num,String parts,String seqs,String modifiedDesc) throws DataAccessException {
		Map  map=new HashMap();
		map.put("parent_item_id", playerItemid);
		map.put("num", num);
		map.put("child_items", parts);
		map.put("seqs", seqs);
		map.put("modifiedDesc", modifiedDesc);
		this.getSqlMapClientTemplate().insert("ItemMod.insert", map);
	}		
	
	@SuppressWarnings("unchecked")
	public void update(ItemMod itemMod) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ItemMod.update", itemMod);
	}			
	
	@SuppressWarnings("unchecked")
	public void delete(Integer partId) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("ItemMod.delete", partId);
	}	
}
