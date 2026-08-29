package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.CharacterData;

public class CharacterDataDao extends BaseMappingDao {
	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public Map<Integer,CharacterData> getCharacterDataById(final Integer playerId) {
		return  queryMappingBeanMapByRelatedId(CharacterData.class, playerId);
	}
	
	//----------------------------------------------------------------------------------------------------------
	
		
	@SuppressWarnings("unchecked")
	public List<CharacterData> getCharacterDataByPlayerId(Integer playerId) throws DataAccessException {
		return new ArrayList<CharacterData>(getCharacterDataById(playerId).values());
	}		
	@SuppressWarnings("unchecked")
	public CharacterData getCharacterDataByPlayerId(int playerId,int characterId) throws DataAccessException {
		List<CharacterData> list=getCharacterDataByPlayerId(playerId);
		for(CharacterData cd :list){
			if(cd.getCharacterId()==characterId){
				return cd;
			}
		}
		return null;
	}
	
	/********************************** create  & delete **************************************************/
	
	public CharacterData create(CharacterData characterData) throws Exception {
		insertObjIntoDBAndCache(characterData);
		return characterData;
	}
//	public void initCharacterData(int playerId) throws Exception {
//		HashMap param = new HashMap();
//		param.put("playerId", playerId);
//		 this.getSqlMapClientTemplate().insert("CharacterData.createCharacterDate", param);;
//	}
	public void delete(CharacterData c ) throws Exception {
		deleteObjFromDBAndCache(c,c.getId());
	}
	
	/********************************** update  ***************************************************/
	
	public void updateCharacterDataInCache(final CharacterData c,int playerId) throws Exception{
		updateMappingBeanInCache(c, playerId);
	}
}
