package com.pearl.manager.dao.impl.nonjoin;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.ChargeLog;
import com.pearl.manager.pojo.PayLog;
import com.pearl.manager.utils.DateUtil;


public class PayLogDao  extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<PayLog> getpayloListByPlayerId(int playerId){
		Map<String,Integer> paramMap=new HashMap<String, Integer>();
		paramMap.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("PayLog.selectByPlayerId",paramMap);
	}
	public void createPayLog(PayLog payLog){
		this.getSqlMapClientTemplate().insert("PayLog.insert",payLog);
	}
	@SuppressWarnings("unchecked")
	public List<PayLog> getPayLog(String year,String month,Integer rmbStart,Integer rmbEnd) throws ParseException{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuilder start=new StringBuilder();
		StringBuilder end=new StringBuilder();
		Calendar c=Calendar.getInstance();
		if(year==null){
			year=String.valueOf(c.get(Calendar.YEAR));
		}
		if(month==null){
			month=String.valueOf(c.get(Calendar.MONTH));
		}
		start.append(year);
		start.append("-");
		if(Integer.parseInt(month)<10){
			start.append("0"+month);
		}else{
			start.append(month);
		}
		start.append("-00 00:00:00");
		end.append(year);
		end.append("-");
		if(Integer.parseInt(month)+1<10){
			end.append("0"+(Integer.parseInt(month)+1));
		}else{
			end.append((Integer.parseInt(month)+1));
		}
		end.append("-01 00:00:00");
		paramMap.put("start",DateUtil.df3.parse(start.toString()));
		paramMap.put("end", DateUtil.df3.parse(end.toString()));
		paramMap.put("rmbStart",rmbStart);
		paramMap.put("rmbEnd", rmbEnd);
		return this.getSqlMapClientTemplate().queryForList("PayLog.selectPayLogByMonth",paramMap);
	}
	@SuppressWarnings("unchecked")
	public List<PayLog> getPayLogByPlayerIds(String playerIds){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("playerIds", playerIds);
		return this.getSqlMapClientTemplate().queryForList("PayLog.selectPayLogByPlayerIds",paramMap);
	}
	@SuppressWarnings("unchecked")
	public List<PayLog> getPayLogByPlayerIds(List<Integer> ids){
		Map<String,Integer[]> paramMap=new HashMap<String,Integer[]>();
		Integer[] idArr = {};
		if(ids==null||ids.size()==0){
			idArr=new Integer[1];
			idArr[0]=0;
		}else{
			ids.toArray();
			idArr =  ids.toArray(idArr);
		}
		paramMap.put("ids",idArr);
		return this.getSqlMapClientTemplate().queryForList("PayLog.selectPayLogByPlayerIds",paramMap);
	}
	@SuppressWarnings("unchecked")
	public List<PayLog> getAllPaylogs(){
		List<PayLog> list=null;
		try{
			 list=this.getSqlMapClientTemplate().queryForList("PayLog.select");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
}
