package com.pearl.o2o.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Constants;

public class PlayerPackDao extends SqlMapClientDaoSupport {
	public void create(Integer userId,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		
		param.put("packNum", Constants.MAX_WEAPON_PACK_SIZE);
		param.put("seqNum", Constants.SEQ_NUM);
		param.put("cPackNum", Constants.DEFAULT_COSTUME_PACK_SIZE);
		param.put("cSeqNum", Constants.DEFAULT_COSTUME_PACK_SEQ);
		param.put("playerId", playerId);
		param.put("userId", userId);
		this.getSqlMapClientTemplate().insert("PlayerPack.insert", param);
	}
	
	
	public void createWeaponPack(int uid, int cid, final int size, int packNo, final Map<Integer,Integer> defaultWeapon, Date expiredTime){
		final Map param = new HashMap();
		param.put("uid", uid);
		param.put("cid", cid);
		param.put("packNo", packNo);
		param.put("expireTime", expiredTime);
		
		SqlMapClientCallback callback = new SqlMapClientCallback() {
	        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	            executor.startBatch();
	            for (int i=1;i<=size;i++) {
	            	param.put("seq", i);
	            	Integer itemId = defaultWeapon.get(i);
	            	param.put("itemId", itemId);
	                executor.update("PlayerPack.createWeaponPack", param);
	            }
	            executor.executeBatch();
	            return null;
	        }
	    };
	    this.getSqlMapClientTemplate().execute(callback);
	}
	
	public void updatePackExpriedTime(int uid, int cid, int packNo, Date expireTime){
		Map param=new HashMap();
		param.put("playerId", cid);
		param.put("userId", uid);
		param.put("packId", packNo);
		param.put("expireTime",expireTime );
		
		this.getSqlMapClientTemplate().update("PlayerPack.updateWeaponPackExpiredTime", param);
	}
	
	public Map<Integer,List<PlayerItem>> getAllWeaponPacks(int cid){
		Map param=new HashMap();
		param.put("playerId", cid);
		param.put("packType", Constants.PACK_TYPE_W);
		List<PlayerItem> allWeapons =  this.getSqlMapClientTemplate().queryForList("PlayerPack.select",param);
		
		Map<Integer,List<PlayerItem>> result = new HashMap<Integer,List<PlayerItem>>();
		for (int i=1;i<=Constants.MAX_WEAPON_PACK_SIZE;i++) {
			result.put(i, new ArrayList<PlayerItem>());
		}
		
		for (PlayerItem playerItem : allWeapons) {
			if (!playerItem.getPack().equals(0)) {//if weapon is in pack
				List<PlayerItem> pack = result.get(playerItem.getPack());
				if (pack != null) {
					pack.add(playerItem);
				}
			}
		}
		return result;
	}
	
	
	public int clearExpiredWeaponPack(int cid,int packId){
		final Map param = new HashMap();
		param.put("playerId", cid);
		param.put("packId", packId);
		return this.getSqlMapClientTemplate().delete("PlayerPack.clearExpiredWeaponPack", param);
	}
	
	public int deleteWeaponPack(int uid, int cid, int packNo){
		final Map param = new HashMap();
		param.put("uid", uid);
		param.put("cid", cid);
		param.put("packNo", packNo);
		
		return this.getSqlMapClientTemplate().delete("PlayerPack.deleteWeaponPack", param);
	}
	
	public List<PlayerItem> getPlayerPackByPackId(Integer userId,Integer playerId,Integer packId)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("packId", packId);
		return this.getSqlMapClientTemplate().queryForList("PlayerPack.select", map);
	}
	public List<PlayerItem> getPlayerPackByPackId(Integer playerId,Integer packId,String packType)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("packType", packType);
		map.put("packId", packId);
		return this.getSqlMapClientTemplate().queryForList("PlayerPack.select", map);
	}

	public List<PlayerItem> getPlayerPackByPackId(int playerId, int packId,
			String packType, int side) {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("packType", packType);
		map.put("packId", packId);
		map.put("side", side);
		return this.getSqlMapClientTemplate().queryForList("PlayerPack.select", map);
	}
	public void createWeaponPackEquipment(Integer userId,Integer playerId,Integer playerItemId,Integer packId,Integer seq)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("playerItemId", playerItemId);
		map.put("seq", seq);
		map.put("packId", packId);
		map.put("type", Constants.PACK_TYPE_W);
		this.getSqlMapClientTemplate().update("PlayerPack.updatePackEquipment", map);
	}
	public void createCostumePackEquipment(Integer userId,Integer playerId,Integer playerItemId,Integer packId,Integer seq,Integer side)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("playerItemId", playerItemId);
		map.put("seq", seq);
		map.put("packId", packId);
		map.put("type", Constants.PACK_TYPE_C);
		map.put("side", side);
		this.getSqlMapClientTemplate().update("PlayerPack.updatePackEquipment", map);
	}
	public void deletePackEquipment(Integer userId,Integer playerId,Integer packId,int type,int seq)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		if(type==Constants.DEFAULT_WEAPON_TYPE){
			map.put("type", Constants.PACK_TYPE_W);
			map.put("packId", packId);
		}else{
			map.put("type", Constants.PACK_TYPE_C);
			map.put("side", packId);
		}
		map.put("seq", seq);
		this.getSqlMapClientTemplate().update("PlayerPack.delete", map);
	}

	public void deletePackAll(Integer userId,Integer playerId,Integer packId,int type)throws DataAccessException {
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		if(type==Constants.DEFAULT_WEAPON_TYPE){
			map.put("type", Constants.PACK_TYPE_W);
			map.put("packId", packId);
		}else{
			map.put("type", Constants.PACK_TYPE_C);
			map.put("side", packId);
		}
		this.getSqlMapClientTemplate().update("PlayerPack.delete", map);
	}

}
