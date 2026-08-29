package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.utils.Constants;

public class PlayerPackDao extends BaseMappingDao {
	static Logger logger = LoggerFactory.getLogger(PlayerPackDao.class.getName());
	public Map<Integer, PlayerPack> getPlayerPackMap(Integer playerId)throws DataAccessException {
		//找出所有playerpack里面的记录
		return queryMappingBeanMapByRelatedId(PlayerPack.class, playerId);
	}	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PlayerPack> getPlayerPackList(Integer playerId)throws DataAccessException {
		List<PlayerPack> list = new ArrayList(getPlayerPackMap(playerId).values());
//		if(list.size()!=49){
//			logger.warn("getPlayerPackListById playerpackdao size="+list.size()+",playerId="+playerId);
//		}
		return list;
	}		
	public int getPlayerPackSize(Integer playerId)throws DataAccessException {
		Set<Integer> set=new HashSet<Integer>();
		for(PlayerPack pp:getPlayerPackList(playerId)){
			if(pp.getPackId()!=0){
				set.add(pp.getPackId());
			}
		}
		return set.size();
	}
	public List<Integer> getCharacterIdFromPlayerPack(Integer playerId){
		List<PlayerPack> playerPackList = getPlayerPackList(playerId);
		List<Integer> resultList = new ArrayList<Integer>();
		Set<Integer> tempSet=new HashSet<Integer>();
		for(PlayerPack pp:playerPackList){
			tempSet.add(pp.getSysCharacterId());
		}
		if(tempSet.size()!=0){
			resultList=new ArrayList<Integer>(tempSet);
		}
		Collections.sort(resultList, new Comparator<Integer>(){

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		});
		return resultList;
	}
	
//constum pack
	public List<PlayerPack> getCostumePackByPackId(int playerId,int characterId) {
		List<PlayerPack> playerPackList = getPlayerPackList(playerId);
		List<PlayerPack> resultList = new ArrayList<PlayerPack>();
		
		for(PlayerPack pp : playerPackList){
			if(pp.getPlayerId()==playerId&&pp.getSysCharacterId()==characterId&&pp.getPackId() == Constants.NUM_ONE && pp.getType().equals(Constants.PACK_TYPE_C)){
				resultList.add(pp);
			}
		}
//		if(resultList.size()!=3){
//		//TODO delete it
//			try {
//				throw new Exception();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			logger.warn("getCostumePackByPackId playerpackdao size="+resultList.size()+" playerId="+playerId+" characterId="+characterId);
//		}
		Collections.sort(resultList,new Comparator<PlayerPack>() {
			@Override
			public int compare(PlayerPack o1, PlayerPack o2) {
				return ((Integer)o1.getSeq()).compareTo((Integer)o2.getSeq());
			}  
		});
		return resultList;
	}
//weapon pack	
	public List<PlayerPack> getWeaponPackByPackId(int playerId,int characterId) {
		List<PlayerPack> playerPackList = getPlayerPackList(playerId);
		List<PlayerPack> resultList = new ArrayList<PlayerPack>();
		
		for(PlayerPack pp : playerPackList){
			if(pp.getPlayerId()==playerId&&pp.getSysCharacterId()==characterId&&pp.getPackId() == Constants.NUM_ONE &&pp.getType().equals(Constants.PACK_TYPE_W)){
				resultList.add(pp);
			}
		}
//		if(resultList.size()!=4){
//			logger.warn("getWeaponPackByPackId playerpackdao size="+resultList.size()+" playerId="+playerId+" characterId="+characterId);
//		}
		Collections.sort(resultList,new Comparator<PlayerPack>() {
			@Override
			public int compare(PlayerPack o1, PlayerPack o2) {
				return ((Integer)o1.getSeq()).compareTo((Integer)o2.getSeq());
			}  
		});
		return resultList;
	}
	
	
//	@SuppressWarnings("unchecked")
//	public void create(Integer playerId) throws DataAccessException {
//		HashMap param = new HashMap();
//		param.put("packNum", Constants.MAX_CHARACTER_SIZE);
//		param.put("seqNum", Constants.DEFAULT_SEQ_SIZE);
//		param.put("playerId", playerId);
//		this.getSqlMapClientTemplate().insert("PlayerPack.createWeaponPlayerPack", param);
//		this.getSqlMapClientTemplate().insert("PlayerPack.createCostumePlayerPack", param);
//		this.getSqlMapClientTemplate().insert("PlayerPack.initWeaponPack", param);
//		this.getSqlMapClientTemplate().insert("PlayerPack.initCostumePack", param);
//	}
	
	public int create(PlayerPack pack) throws Exception{
		return insertObjIntoDBAndCache(pack, pack.getPlayerId());
	}
	public void update(PlayerPack pack) throws Exception{
		updateMappingBeanInCache(pack, pack.getPlayerId());
	}
	
	
}
