package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.SysBioCharacter;

public class SysBioCharacterDao extends BaseMappingDao {
	private static final int DEFAULTID = 1;

	public Map<Integer, SysBioCharacter> getAllSysBioCharacterMap() {
		return queryMappingBeanMapByRelatedId(SysBioCharacter.class, DEFAULTID);
	}

	public List<SysBioCharacter> getAllSysBioCharacterList() {
		Map<Integer, SysBioCharacter> characters = getAllSysBioCharacterMap();
		List<SysBioCharacter> returnList = new ArrayList<SysBioCharacter>(characters.values());
		return returnList;
	}

	public SysBioCharacter getSysCharacterById(int id) {
		return getAllSysBioCharacterMap().get(id);
	}

	public List<SysBioCharacter> getSysCharacterList() {
		Map<Integer, SysBioCharacter> characters = getAllSysBioCharacterMap();
		List<SysBioCharacter> returnList = new ArrayList<SysBioCharacter>(characters.values());
		return returnList;
	}

	public void createSysCharacter(SysBioCharacter character) throws Exception {
		insertObjIntoDBAndCache(character, DEFAULTID);
	}

	public void deleteSysBioCharacter(SysBioCharacter character) throws Exception {
		deleteObjFromDBAndCache(character, DEFAULTID);
	}

	public void updateBioCharacter(SysBioCharacter character) throws Exception {
		updateMappingBeanInCache(character, DEFAULTID);
		updateObjInDB(character);
	}
}
