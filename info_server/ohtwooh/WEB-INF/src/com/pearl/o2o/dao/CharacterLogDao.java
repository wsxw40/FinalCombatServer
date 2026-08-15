package com.pearl.o2o.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterLog;

public class CharacterLogDao extends SqlMapClientDaoSupport{
	@SuppressWarnings("unchecked")
	public List<Character> getCharacterLogList(Integer characterId) throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("CharacterLog.select",characterId);
	}
	public int getCharacterLogVersion(Integer characterId) throws DataAccessException{
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CharacterLog.selectVersion",characterId);
	}
	public void createCharacterLog(CharacterLog characterLog) throws DataAccessException{
		 this.getSqlMapClientTemplate().insert("CharacterLog.insert",characterLog);
	}
}
