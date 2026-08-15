package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import com.pearl.o2o.pojo.UnSpeaker;

public class UnSpeakerDao extends BaseMappingDao {
	@SuppressWarnings("unchecked")
	public List<UnSpeaker> getAllUnSpeaker(){
		List<UnSpeaker> list = (List<UnSpeaker>) this.getSqlMapClientTemplate().queryForList("UnSpeaker.select");
		return list;
	}
	
	public void deleteUnSpeakerByPlayerId(int playerId){
		this.getSqlMapClientTemplate().delete("UnSpeaker.delete", playerId);
	}
	
	public int insertUnSpeaker(int playerId){
		UnSpeaker unSpeaker = new UnSpeaker();
		unSpeaker.setPlayerId(playerId);
		return (Integer)this.getSqlMapClientTemplate().insert("UnSpeaker.insert", unSpeaker);
	}
}
