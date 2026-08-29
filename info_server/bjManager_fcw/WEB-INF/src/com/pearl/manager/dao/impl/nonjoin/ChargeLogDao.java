package com.pearl.manager.dao.impl.nonjoin;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.ChargeLog;
import com.pearl.manager.utils.DateUtil;

public class ChargeLogDao extends SqlMapClientDaoSupport{
	
	public static final int type_recharge = 1;
	public static final int type_chargeback = 2;
	public static final int type_paytobevip = 3;
	@SuppressWarnings("unchecked")
	public List<ChargeLog> getPlayerFirstPay(Date start,Date end){
		Map<String,Date> map=new HashMap<String, Date>();
		if(start!=null){
			map.put("start", start);
		}
		if(end!=null&&start!=end){
			map.put("end", end);
		}
		return this.getSqlMapClientTemplate().queryForList("ChargeLog.getFirstCharge", map);
	}
	@SuppressWarnings("unchecked")
	public List<ChargeLog> getChargeLogByMonth(String year,String month) throws ParseException{
		Map<String,Date> map=new HashMap<String, Date>();
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
		map.put("start",DateUtil.df3.parse(start.toString()));
		map.put("end", DateUtil.df3.parse(end.toString()));
		List<ChargeLog>list=this.getSqlMapClientTemplate().queryForList("ChargeLog.getMonthCharge",map);
		return  list;
	}
	@SuppressWarnings("unchecked")
	public List<ChargeLog> getPlayerPayByDay(int type,Date start,Date end){
		Map<String,Date> map=new HashMap<String, Date>();
		if(start!=null){
			map.put("start", start);
		}
		if(end!=null&&start!=end){
			map.put("end", end);
		}
		String sql = "";
		if(type==1){
			sql ="ChargeLog.getChargeByDayOnUsr";
		}else{
			sql ="ChargeLog.getChargeByDayOnRmb";
		}
		return this.getSqlMapClientTemplate().queryForList(sql, map);
	}
	
}
