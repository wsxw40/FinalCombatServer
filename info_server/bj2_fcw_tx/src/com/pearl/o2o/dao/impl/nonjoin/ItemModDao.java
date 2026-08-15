package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.ItemMod;

public class ItemModDao extends  BaseMappingDao {
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#getItemMod(com.pearl.o2o.pojo.ItemMod)
	 */
	@SuppressWarnings("unchecked")
	public List<ItemMod> getItemModListById(int playerId) throws DataAccessException {
		Map params=new HashMap<String, Object>();
		params.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("ItemMod.select", params);
	}
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#getItemMod(com.pearl.o2o.pojo.ItemMod)
	 */
	@SuppressWarnings("unchecked")
	public List<ItemMod> getItemMod(ItemMod itemMod) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("ItemMod.select", itemMod);
	}	
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#create(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
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
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#update(com.pearl.o2o.pojo.ItemMod)
	 */
	@SuppressWarnings("unchecked")
	public void update(ItemMod itemMod) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ItemMod.update", itemMod);
	}			
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#delete(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public void delete(Integer weaponId) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("ItemMod.deleteWeapon", weaponId);
	}
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IItemModDao#deletePart(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public void deletePart(Integer partId) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("ItemMod.deletePart", partId);
	}
}
