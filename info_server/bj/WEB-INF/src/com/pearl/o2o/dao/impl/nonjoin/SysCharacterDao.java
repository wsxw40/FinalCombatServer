package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.utils.IdComparatorClass;

public class SysCharacterDao extends BaseMappingDao{
	private static final int DEFAULTID = 1;
	
	@SuppressWarnings("unchecked")
	public Map<Integer, SysCharacter> getAllSysCharacterMap() {
		return queryMappingBeanMapByRelatedId(SysCharacter.class,DEFAULTID);
	}	
	
	@SuppressWarnings("unchecked")
	public List<SysCharacter> getAllSysCharacterList() {
		Map<Integer, SysCharacter> characters=getAllSysCharacterMap();
		List<SysCharacter> returnList=new ArrayList<SysCharacter>(characters.values());
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}
	
	public SysCharacter getSysCharacterById(int id){
		Map<Integer, SysCharacter> characters=getAllSysCharacterMap();
		return characters.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysCharacter> getSysCharacterList() {
		Map<Integer, SysCharacter> characters=getAllSysCharacterMap();
		List<SysCharacter> returnList=new ArrayList<SysCharacter>(characters.values());
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}
	
	public SysCharacter createSysCharacter(SysCharacter character) throws Exception{
		int id= insertObjIntoDBAndCache(character, DEFAULTID);
		character.setId(id);
		return character;
	}
	
	
	public void deleteSysCharacter(SysCharacter character) throws Exception{
		deleteObjFromDBAndCache(character, DEFAULTID);
	}
	
	public void updateCharacter(SysCharacter character) throws Exception{
		updateMappingBeanInCache(character, DEFAULTID);
	}
}
