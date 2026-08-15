package com.pearl.o2o.dao.impl.nonjoin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.BannedWord;
import com.pearl.o2o.utils.Constants;

public class BannedWordDao extends BaseMappingDao{
	
	@SuppressWarnings("unchecked")
	public List<BannedWord> getAllBannedWrodListIncludeDeleted(){
		return (List<BannedWord>)this.getSqlMapClientTemplate().queryForList("BannedWord.select");
	}
	
	public List<BannedWord> getBannedWordList(){
		List<BannedWord> result = new ArrayList<BannedWord>();
		for (BannedWord bw : getAllBannedWrodListIncludeDeleted()) {
			if (Constants.BOOLEAN_NO.equals(bw.getIsDeleted())){
				result.add(bw);
			}
		}
		return result;
	}
	
	public void deleteAllBannedWords(){
		this.getSqlMapClientTemplate().delete("BannedWord.deleteAll");
	}
	
	public void updateBannedWord(BannedWord bannedWord) throws Exception{
		this.getSqlMapClientTemplate().update("BannedWord.update", bannedWord);
	}
	
	public int  createBannedWord(BannedWord bannedWord) throws Exception{
		return (Integer)this.getSqlMapClientTemplate().insert("BannedWord.insert", bannedWord);
	}
	
	public void createBannedWrods(final List<String> list)throws Exception{
		SqlMapClientCallback callback = new SqlMapClientCallback() {
	        @SuppressWarnings({ "unchecked", "rawtypes" })
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
	            executor.startBatch();
	            for (String ss : list) {
	            	HashMap param = new HashMap();
	        		param.put("bannedWord", ss);				
	        		param.put("isDeleted", "N");
	        		param.put("includeInWord", "N");
	                executor.insert("BannedWord.insertBatch", param);
	            }
	            executor.executeBatch();
	            return null;
	        }
	    };
	this.getSqlMapClientTemplate().execute(callback);
	}
	
	@SuppressWarnings("unchecked")
	public List<BannedWord>  getBannedWrodsByWord(String word) throws Exception{
		return (List<BannedWord>)this.getSqlMapClientTemplate().queryForList("BannedWord.fuzzyQueryByWord", word);
	}
}
