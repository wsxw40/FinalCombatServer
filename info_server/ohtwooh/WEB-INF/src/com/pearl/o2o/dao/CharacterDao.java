package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Character;

public class CharacterDao extends SqlMapClientDaoSupport{
	
	@SuppressWarnings("unchecked")
	public List<Character> getCharacterList(String isDefault) throws DataAccessException{
		Map map=new HashMap();
		map.put("isDefault", isDefault);
		return this.getSqlMapClientTemplate().queryForList("Character.select",map);
	}
	@SuppressWarnings("unchecked")
	public List<Character> getCharacterList() throws DataAccessException{
		
		return this.getSqlMapClientTemplate().queryForList("Character.select");
	}
	public Character createCharacter(Character character) throws DataAccessException{
	
		int id= (Integer)this.getSqlMapClientTemplate().insert("Character.insert",character);
		character.setId(id);
		return character;
	}
	public void deleteCharacter(Integer id) throws DataAccessException{
		this.getSqlMapClientTemplate().update("Character.delete",id);
	}
	public void updateCharacter(Character character) throws DataAccessException{
		this.getSqlMapClientTemplate().update("Character.update",character);
	}
}
