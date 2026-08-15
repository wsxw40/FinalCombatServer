package com.pearl.o2o.dao.impl.nonjoin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DBRouteUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class PlayerItemDao extends BaseMappingDao{	
	public Map<Integer, PlayerItem> getPlayerItemMap(int playerId) throws DataAccessException{
		return queryMappingBeanMapByRelatedId(PlayerItem.class, playerId);
	}	
	public List<PlayerItem> getPlayerItemList(int playerId) throws DataAccessException{
		return new ArrayList<PlayerItem>(getPlayerItemMap(playerId).values());
	}
	
	public PlayerItem getPlayerItemById(final Integer id){
		return queryMappingBeanById(PlayerItem.class,id);
	}
	
	public PlayerItem getPlayerItemForMessage(int playerId,int playerItemId) throws DataAccessException{
		Map<Integer,PlayerItem> playerItemMap = getPlayerItemMap(playerId);
		for(Map.Entry<Integer, PlayerItem> entry: playerItemMap.entrySet()) {  
			PlayerItem pi=playerItemMap.get(entry.getKey());
			try{
				SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
				if(pi!=null&&Constants.BOOLEAN_YES.equals(pi.getIsGift())&&pi.getId()==playerItemId){
					pi.initWithoutPart(si);
					return pi;
				}
			}catch (Exception e) {
				logger.warn("happen in getPlayerItemForMessage",e);
			}
		}
		
		return null;
	}
	
	
	public int createPlayItem(Integer playerId, SysItem sysItem, int unit,int unitType,String isGift,String isBind,String isDefault)throws Exception{
		PlayerItem playerItem=new PlayerItem();
		playerItem.setPlayerId(playerId);
		playerItem.setItemId(sysItem.getId());
		playerItem.setModifiedDesc(sysItem.getModifiedDesc());
		if(unitType==2){			//类型时间计算
			playerItem.setValidDate(new Date());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_YEAR, unit);
			playerItem.setExpireDate(c.getTime()); 
			playerItem.setTimeLeft(24*60*unit);
			//时间计算的物品记录剩余秒数
			int seconds = unit * 24 * 60 * 60;
			playerItem.setLeftSeconds(seconds);
		}else if(unitType==1){		//类型个数计算
			playerItem.setQuantity(unit);
			playerItem.setValidDate(new Date());
			playerItem.setExpireDate(new Date());
		}else {
			playerItem.setValidDate(new Date());
			playerItem.setExpireDate(new Date());
		}
		
		playerItem.setPlayerItemCurrency(1);
		playerItem.setPlayerItemUnitType(unitType);
		playerItem.setIsBind(isBind);
		playerItem.setIsDeleted(Constants.BOOLEAN_NO);
		playerItem.setIsGift(isGift);
		playerItem.setIsDefault(isDefault);
		playerItem.setGunProperty1(sysItem.getGunProperty1());
		if(sysItem.getIsStrengthen() == Constants.NUM_ONE){//zlm2015-5-7 --是否可以强化  0不可强化 1可强化
			playerItem.setSysItem(sysItem);
			playerItem.setLevel(sysItem.getStrengthLevel());
			if (sysItem.getStrengthLevel() > 0) {
				for (int i = 1; i <= playerItem.getMaxHoleNum(); i++) {
					playerItem.openHole();
				}
			}
		} else {	//不可强化 可以带默认的插槽属性
			//zlm2015-5-7-限时装备-开始-------------------------------------
			if (sysItem.getProvisional_item_flag()) {
				playerItem.setSysItem(sysItem);
				playerItem.setLevel(sysItem.getStrengthLevel());
			}
			//zlm2015-5-7-限时装备-结束-------------------------------------
			playerItem.setGunProperty2(sysItem.getGunProperty2());
			playerItem.setGunProperty3(sysItem.getGunProperty3());
			playerItem.setGunProperty4(sysItem.getGunProperty4());
			playerItem.setGunProperty5(sysItem.getGunProperty5());
			playerItem.setGunProperty6(sysItem.getGunProperty6());
			playerItem.setGunProperty7(sysItem.getGunProperty7());
		}
		playerItem.setCBloodAdd(sysItem.getCBloodAdd());
		playerItem.setCResistanceFire(sysItem.getCResistanceFire());
		playerItem.setCResistanceBlast(sysItem.getCResistanceBlast());
		playerItem.setCResistanceBullet(sysItem.getCResistanceBullet());
		playerItem.setCResistanceKnife(sysItem.getCResistanceKnife());
		return insertObjIntoDBAndCache(playerItem,playerItem.getPlayerId());
	}
	public int createPlayItemForPersonalPlace(Integer playerId, SysItem sysItem, int unit,int unitType,String isGift,String isBind,String isDefault,Date lastCreateDate)throws Exception{
		PlayerItem playerItem=new PlayerItem();
		playerItem.setPlayerId(playerId);
		playerItem.setItemId(sysItem.getId());
		playerItem.setModifiedDesc(sysItem.getModifiedDesc());
		if(unitType==2){
			playerItem.setValidDate(new Date());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_YEAR, unit);
			playerItem.setExpireDate(c.getTime()); 
			playerItem.setTimeLeft(24*60*unit);
			//时间计算的物品记录剩余秒数
			int seconds = unit * 24 * 60 * 60;
			playerItem.setLeftSeconds(seconds);
		}else if(unitType==1){
			playerItem.setQuantity(unit);
			playerItem.setValidDate(lastCreateDate);
			playerItem.setExpireDate(new Date());
		}else {
			playerItem.setValidDate(new Date());
			playerItem.setExpireDate(new Date());
		}
		
		playerItem.setPlayerItemCurrency(1);
		playerItem.setPlayerItemUnitType(unitType);
		playerItem.setIsBind(isBind);
		playerItem.setIsDeleted(Constants.BOOLEAN_NO);
		playerItem.setIsGift(isGift);
		playerItem.setIsDefault(isDefault);
		playerItem.setGunProperty1(sysItem.getGunProperty1());
	
		playerItem.setSysItem(sysItem);
		playerItem.setLevel(sysItem.getLevel());

		playerItem.setGunProperty2(sysItem.getGunProperty2());
		playerItem.setGunProperty3(sysItem.getGunProperty3());
		playerItem.setGunProperty4(sysItem.getGunProperty4());
		playerItem.setGunProperty5(sysItem.getGunProperty5());
		playerItem.setGunProperty6(sysItem.getGunProperty6());
		playerItem.setGunProperty7(sysItem.getGunProperty7());
		int result= insertObjIntoDBAndCache(playerItem,playerItem.getPlayerId());
		return result;
	}
	
	public int createPlayItem(PlayerItem playerItem)throws Exception{
		return insertObjIntoDBAndCache(playerItem,playerItem.getPlayerId());
	}
	
	public void softDeletePlayerItem(PlayerItem pi)throws Exception{
		deleteObjFromDBAndCache(pi,pi.getPlayerId());
	}
	public void updatePlayerItem(PlayerItem pi)throws Exception{
		pi.setSysItem(null);
		pi.setBuff(0);
		pi.setPack(0);
		updateMappingBeanInCache(pi, pi.getPlayerId());
	}
	public void updatePlayerItemPersonal(PlayerItem pi) throws Exception{
		pi.setBuff(0);
		pi.setPack(0);
		updateMappingBeanInCache(pi, pi.getPlayerId());
	}
	public void updatePlayerItemWhileAttachMessage(int playerId,PlayerItem pi)throws Exception{
		pi.setSysItem(null);
		pi.setBuff(0);
		pi.setPack(0);
		updateMappingBeanInCache(pi,playerId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getDeletedPlayerItem(Integer playerId,Integer id,Integer sysitemId,Integer level ,Integer useType ) throws Exception{
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		if(null != playerId){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("fid", playerId);
			map.put("id", id);
			map.put("sysitemId", sysitemId);
			map.put("level", level);
			map.put("useType", useType);
			map.put("tableSuffix", DBRouteUtil.getTableSuffix(PlayerItem.class, playerId));
			retList = (List<PlayerItem>)this.getSqlMapClientTemplate().queryForList("PlayerItem.selectDeleted",map);
		}
		return retList;
	}
	public PlayerItem getPlayerItemByIdForPersonal(int playerId,int id){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("tableSuffix", DBRouteUtil.getTableSuffix(PlayerItem.class, playerId));
		return (PlayerItem)getSqlMapClientTemplate().queryForObject("PlayerItem.selectForPersonal", map);
	}
	public void revDeletedPlayerItem(Integer playerId,List<?> itemIdList) throws SQLException{
		if(null != playerId){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("tableSuffix", DBRouteUtil.getTableSuffix(PlayerItem.class, playerId));
			map.put("idsStr", StringUtils.join(itemIdList, ","));
			getSqlMapClientTemplate().update("PlayerItem.revByIds",map);
			
		}
	}
}
