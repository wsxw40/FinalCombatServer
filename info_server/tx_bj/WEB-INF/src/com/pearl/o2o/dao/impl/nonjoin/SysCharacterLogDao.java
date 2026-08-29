package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysCharacterLog;

public class SysCharacterLogDao extends SqlMapClientDaoSupport{
	@SuppressWarnings("unchecked")
	public List<SysCharacter> getSysCharacterLogList(Integer characterId) throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("SysCharacterLog.select",characterId);
	}
	public int getSysCharacterLogVersion(Integer characterId) throws DataAccessException{
		return (Integer)this.getSqlMapClientTemplate().queryForObject("SysCharacterLog.selectVersion",characterId);
	}
	public void createSysCharacterLog(SysCharacterLog characterLog) throws DataAccessException{
		 this.getSqlMapClientTemplate().insert("SysCharacterLog.insert",characterLog);
	}
}
