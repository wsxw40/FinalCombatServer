package com.pearl.manager.service.flexservice;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.pearl.manager.pojo.BaseDAUAbout;
import com.pearl.manager.pojo.BaseEveryDayCharge;
import com.pearl.manager.pojo.BaseFirstCharge;
import com.pearl.manager.pojo.ChargeLog;
import com.pearl.manager.pojo.DAUAboutVo;
import com.pearl.manager.pojo.ChargeMonth;
import com.pearl.manager.pojo.ChargeMonthMoney;
import com.pearl.manager.pojo.ChargeMonthMoneyCount;
import com.pearl.manager.pojo.GmPrivilege;
import com.pearl.manager.pojo.GmUser;
import com.pearl.manager.pojo.PayLog;
import com.pearl.manager.pojo.PayType;
import com.pearl.manager.service.GetService;
import com.pearl.manager.utils.Constants;
import com.pearl.manager.utils.PasswordUtil;


public class FlexGetService extends GetService{
	public List<GmPrivilege> getGmPrivilegesByUserId(int userId){
		List<GmPrivilege> list = gmPrivilegeDao.getGmPrivilegesByUserId(userId);
		return list;
	}
	public List<GmUser> getLoginGmUser(String gmUserName, String passWord){
		List<GmUser> list = gmUserDao.getLoginGmUser(gmUserName, PasswordUtil.encrypt(passWord));
		return list;
	}
	
	public List<BaseFirstCharge> getPlayerFirstCharge(int param ,Date start, Date end){
		if(start!=null&&end!=null&&end.before(start)){
			end=null;
		}
		param=1;
		List<ChargeLog> list = chargeLogDao.getPlayerFirstPay(start, end);
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		
		Multiset<Integer> multiset=HashMultiset.create();
		int totalRmb=0;
		for(ChargeLog o:list){
			int rmb=o.getRmb();
			totalRmb+=rmb;
			int index=rmb;
			multiset.add(index);
		}
		for(Integer rmb:multiset){
			map.put(rmb, multiset.count(rmb));
		}
		List<BaseFirstCharge> returnList=new ArrayList<BaseFirstCharge>();
		int totalTime=0;
		for(Iterator<Integer> it=map.keySet().iterator();it.hasNext();){
			Integer rmb=it.next();
			totalTime+=map.get(rmb);
			BaseFirstCharge bv=new BaseFirstCharge(rmb,map.get(rmb),rmb*map.get(rmb));
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<BaseFirstCharge>(){

			@Override
			public int compare(BaseFirstCharge o1, BaseFirstCharge o2) {
				return ((Double)o1.getSingleRmb()).compareTo((Double)o2.getSingleRmb());
			}});
		return returnList;
	}
	public List<BaseFirstCharge> getPlayerFirstCharge2(int param ,Date start, Date end){
		if(start!=null&&end!=null&&end.before(start)){
			end=null;
		}
		param=1;
		List<ChargeLog> list = chargeLogDao.getPlayerFirstPay(start, end);
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		
		Multiset<Integer> multiset=HashMultiset.create();
		int totalRmb=0;
		for(ChargeLog o:list){
			int rmb=o.getRmb();
			totalRmb+=rmb;
			int index=rmb;
			multiset.add(index);
		}
		for(Integer rmb:multiset){
			map.put(rmb, multiset.count(rmb));
		}
		List<BaseFirstCharge> returnList=new ArrayList<BaseFirstCharge>();
		int totalTime=0;
		for(Iterator<Integer> it=map.keySet().iterator();it.hasNext();){
			Integer rmb=it.next();
			totalTime+=map.get(rmb);
			BaseFirstCharge bv=new BaseFirstCharge(rmb,map.get(rmb),rmb*map.get(rmb));
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<BaseFirstCharge>(){

			@Override
			public int compare(BaseFirstCharge o1, BaseFirstCharge o2) {
				return ((Double)o1.getSingleRmb()).compareTo((Double)o2.getSingleRmb());
			}});
		return returnList;
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException 
	 */
	public List<ChargeMonth> getMonthChargeCount(String year, String month) throws ParseException{
		List<ChargeLog> list = chargeLogDao.getChargeLogByMonth(year, month);
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		Map<String, Integer> mapCount=new HashMap<String, Integer>();
		for(ChargeLog o:list){
		
			if(map.containsKey(o.getUserId())){
				Integer count=map.get(o.getUserId());
				map.remove(o.getUserId());
				map.put(o.getUserId(), count+1);
			}else{
				map.put(o.getUserId(), 1);
			}
		}
		
		for(Iterator<String> it=map.keySet().iterator();it.hasNext();){
			String userId= it.next();
			Integer count=map.get(userId);//该用户充值多少次
			if(count==null){
				count=0;
			}
			if(count==1){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_1);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_1);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_1, sum+1);
			}else if(count>=2&&count<=3){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_2);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_2);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_2, sum+1);
			}else if(count>=4&&count<=7){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_3);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_3);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_3, sum+1);
			}else if(count>=8&&count<=15){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_4);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_4);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_4, sum+1);
			}else if(count>=16&&count<=31){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_5);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_5);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_5, sum+1);
			}else if(count>=32){
				Integer sum=mapCount.get(Constants.MONTH_CHARGE_COUNT_LEVEL_1);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_COUNT_LEVEL_6);
				mapCount.put(Constants.MONTH_CHARGE_COUNT_LEVEL_6, count+1);
			}

		}
		List<ChargeMonth> returnList=new ArrayList<ChargeMonth>();
		int totalTime=0;
		for(Iterator<String> it=mapCount.keySet().iterator();it.hasNext();){
			String x=it.next();
			totalTime+=mapCount.get(x);
			ChargeMonth bv=new ChargeMonth(x, mapCount.get(x), totalTime);
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<ChargeMonth>(){

			@Override
			public int compare(ChargeMonth o1, ChargeMonth o2) {
				
				return ((Integer)Integer.parseInt(o2.getChargeCount().split("-")[0])).compareTo((Integer)Integer.parseInt(o1.getChargeCount().split("-")[0]));
			}});
		return returnList;
	}
	
	
	/**
	 * 不同充值区间的充值总额占所有充值金额的比重
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException 
	 */
	public List<ChargeMonthMoney> getMonthChargeMoney(String year, String month) throws ParseException{
		List<ChargeLog> list = chargeLogDao.getChargeLogByMonth(year, month);
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		Map<String, Integer> mapCount=new HashMap<String, Integer>();
		for(ChargeLog o:list){
			if(map.containsKey(o.getUserId())){
				Integer money=map.get(o.getUserId());
				map.remove(o.getUserId());
				map.put(o.getUserId(), money+o.getRmb());
			}else{
				map.put(o.getUserId(), o.getRmb());
			}
		}
		
		for(Iterator<Entry<String, Integer>> it=map.entrySet().iterator();it.hasNext();){
			Entry<String, Integer> e= it.next();
			int money=e.getValue();
			if(money<=1){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_1);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_1);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_1, sum+money);
			}else if((money>=2)&&(money<=10)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_2);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_2);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_2, sum+money);
			}else if((money>=11)&&(money<=100)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_3);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_3);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_3, sum+money);
			}else if((money>=101)&&(money<=1000)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_4);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_4);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_4, sum+money);
			}else if((money>=1001)&&(money<=10000)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_5);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_5);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_5, sum+money);
			}else if(money>=10001&&money<=100000){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_6);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_6);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_6, sum+money);
			}
			
		}

		List<ChargeMonthMoney> returnList=new ArrayList<ChargeMonthMoney>();
		int c=0;
		for(Iterator<String> it=mapCount.keySet().iterator();it.hasNext();){
			String x=it.next();
			c+=mapCount.get(x);
			ChargeMonthMoney bv=new ChargeMonthMoney(x, mapCount.get(x), c);
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<ChargeMonthMoney>(){

			@Override
			public int compare(ChargeMonthMoney o1, ChargeMonthMoney o2) {
				
				return ((Integer)Integer.parseInt(o2.getChargeMoney().split("-")[0])).compareTo((Integer)Integer.parseInt(o1.getChargeMoney().split("-")[0]));
			}});
		return returnList;
	}
	
	
	
	/**
	 * 每月充值金额在不同区间的人数统计
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException 
	 */
	public List<ChargeMonthMoneyCount> getMonthChargeMoneyAmount(String year, String month) throws ParseException{
		List<ChargeLog> list = chargeLogDao.getChargeLogByMonth(year, month);
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		Map<String, Integer> mapCount=new HashMap<String, Integer>();
		for(ChargeLog o:list){
			if(map.containsKey(o.getUserId())){
				Integer money=map.get(o.getUserId());
				map.remove(o.getUserId());
				map.put(o.getUserId(), money+o.getRmb());
			}else{
				map.put(o.getUserId(), o.getRmb());
			}
		}
		
		for(Iterator<Entry<String, Integer>> it=map.entrySet().iterator();it.hasNext();){
			Entry<String, Integer> e= it.next();
			int num=e.getValue();
			if(num<=1){
				Integer sumNum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_1);
				if(sumNum==null){
					sumNum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_1);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_1, sumNum+1);
			}else if((num>=2)&&(num<=10)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_2);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_2);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_2, sum+1);
			}else if((num>=11)&&(num<=100)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_3);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_3);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_3, sum+1);
			}else if((num>=101)&&(num<=1000)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_4);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_4);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_4, sum+1);
			}else if((num>=1001)&&(num<=10000)){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_5);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_5);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_5, sum+1);
			}else if(num>=10001&&num<=100000){
				Integer sum= mapCount.get(Constants.MONTH_CHARGE_MONEY_LEVEL_6);
				if(sum==null){
					sum=0;
				}
				mapCount.remove(Constants.MONTH_CHARGE_MONEY_LEVEL_6);
				mapCount.put(Constants.MONTH_CHARGE_MONEY_LEVEL_6, sum+1);
			}
			
		}

		List<ChargeMonthMoneyCount> returnList=new ArrayList<ChargeMonthMoneyCount>();
		int c=0;
		for(Iterator<String> it=mapCount.keySet().iterator();it.hasNext();){
			String x=it.next();
			c+=mapCount.get(x);
			ChargeMonthMoneyCount bv=new ChargeMonthMoneyCount(x, mapCount.get(x), c);
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<ChargeMonthMoneyCount>(){

			@Override
			public int compare(ChargeMonthMoneyCount o1, ChargeMonthMoneyCount o2) {
				
				return ((Integer)Integer.parseInt(o2.getChargeMoney().split("-")[0])).compareTo((Integer)Integer.parseInt(o1.getChargeMoney().split("-")[0]));
			}});
		return returnList;
	}

	public List<BaseEveryDayCharge> getPlayerEveryDayCharge(int type ,Date start){
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(start);
		cDay.add(Calendar.DAY_OF_MONTH, 1);
		List<ChargeLog> list = chargeLogDao.getPlayerPayByDay(type,start,cDay.getTime());
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		
		Multiset<Integer> multiset=HashMultiset.create();
		int totalRmb=0;
		for(ChargeLog o:list){
			int rmb=o.getRmb();
			totalRmb+=rmb;
			int index=rmb;
			multiset.add(index);
		}
		for(Integer rmb:multiset){
			map.put(rmb, multiset.count(rmb));
		}
		List<BaseEveryDayCharge> returnList=new ArrayList<BaseEveryDayCharge>();
		int totalTime=0;
		for(Iterator<Integer> it=map.keySet().iterator();it.hasNext();){
			Integer rmb=it.next();
			totalTime+=map.get(rmb);
			BaseEveryDayCharge bv=new BaseEveryDayCharge(rmb,map.get(rmb),rmb*map.get(rmb));
			returnList.add(bv);
		}
		Collections.sort(returnList, new Comparator<BaseEveryDayCharge>(){

			@Override
			public int compare(BaseEveryDayCharge o1, BaseEveryDayCharge o2) {
				return ((Double)o1.getSingleRmb()).compareTo((Double)o2.getSingleRmb());
			}});
		return returnList;
	}
	public List<BaseDAUAbout> getDAUAboutList(Date start) throws SQLException{
		return dauDao.getDauList(start);
	}
	public List<DAUAboutVo> getDAUAboutListByPlayer(Date start) throws Exception{
		List<BaseDAUAbout> l=getDAUAboutList(start);
		List<DAUAboutVo> returnList=new ArrayList<DAUAboutVo>();
		Multiset<Integer> multiset=HashMultiset.create();
		DAUAboutVo vo=null;
		for(BaseDAUAbout bd:l){
			if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL6.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL6.getValue());
			}else if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL5.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL5.getValue());
			}else if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL4.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL4.getValue());
			}else if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL3.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL3.getValue());
			}else if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL2.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL2.getValue());
			}else if(bd.getTotalRmb()>=Constants.DAULEVEL.LEVEL1.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL1.getValue());
			}else if(bd.getTotalRmb()==Constants.DAULEVEL.LEVEL0.getValue()){
				multiset.add(Constants.DAULEVEL.LEVEL0.getValue());
			}
		}
		for(Integer level:multiset.elementSet()){
			vo=new DAUAboutVo(level,multiset.count(level),Constants.DAULEVEL.getTypeByValue(level).getDes());
			returnList.add(vo);
		}
		return returnList;
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel1(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_BOTTOM, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel2(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_2_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel3(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_2_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_3_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel4(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month,Constants.MONTH_CHARGE_MEONEY_LEVEL_3_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_4_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel5(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_4_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevel6(String year, String month) throws ParseException{
		return getArticlePayTypes(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP*100);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney1(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_BOTTOM, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney2(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_1_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_2_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney3(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_2_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_3_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney4(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month,Constants.MONTH_CHARGE_MEONEY_LEVEL_3_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_4_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney5(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_4_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP);
		
	}
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayLogLevelMoney6(String year, String month) throws ParseException{
		return getPayArticleTypesMoney(year, month, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP, Constants.MONTH_CHARGE_MEONEY_LEVEL_5_TOP*100);
		
	}
	/**
	 * 获取某年某月玩家消费消费物品(次数)分布
	 * @param year 年份 如  2012
	 * @param month 月份 如 2
	 * @param bottom  最小值(包含)
	 * @param top     最大值(不包含)
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getArticlePayTypes(String year, String month,int bottom ,int top) throws ParseException{
		Map<String,Integer> mapCount=null;
		List<PayType> returnList=new ArrayList<PayType>();
		List<PayLog> list=payLogDao.getPayLogByPlayerIds(this.getLevelPlayerIds(year, month, bottom, top));
		mapCount=this.statisticsPayTypes(list);
		for(Iterator<Entry<String, Integer>> it=mapCount.entrySet().iterator();it.hasNext();){
				Entry<String, Integer> e=it.next();
				PayType pt=new PayType(e.getKey(), e.getValue(), 0);
				returnList.add(pt);
		}
		return returnList;
	}
	
	/**
	 * 获取某年某月(充值在bottom--top之间的)玩家消费消费物品(金额)分布
	 * @param year 年份 如  2012
	 * @param month 月份 如 2
	 * @param bottom  最小值(包含)
	 * @param top     最大值(不包含)
	 * @return
	 * @throws ParseException
	 */
	public List<PayType> getPayArticleTypesMoney(String year, String month,int bottom ,int top) throws ParseException{
		Map<String,Integer> mapCount=null;
		List<PayType> returnList=new ArrayList<PayType>();
		List<PayLog> list=payLogDao.getPayLogByPlayerIds(this.getLevelPlayerIds(year, month, bottom, top));
		mapCount=this.statisticsPayTypesMoney(list);
		for(Iterator<Entry<String, Integer>> it=mapCount.entrySet().iterator();it.hasNext();){
			Entry<String, Integer> e=it.next();
			PayType pt=new PayType(e.getKey(), e.getValue(), 0);
			returnList.add(pt);
		}
			
		return returnList;
	}
	/**
	 * 获取某年某月充值在bottom--top之间的玩家id
	 * @param year 年份 如  2012
	 * @param month 月份 如 2
	 * @param bottom  最小值(包含)
	 * @param top     最大值(不包含)
	 * @return
	 * @throws ParseException
	 */
	public List<Integer> getLevelPlayerIds(String year, String month,int bottom ,int top) throws ParseException{
		List<ChargeLog> ls = chargeLogDao.getChargeLogByMonth(year, month);
		List<Integer> ids=new ArrayList<Integer>();
		Map<Integer, Integer> m=new HashMap<Integer, Integer>();
		for(ChargeLog o:ls){
			if(m.containsKey(o.getPlayerId())){
				Integer money=m.get(o.getPlayerId());
				m.remove(o.getPlayerId());
				m.put(o.getPlayerId(), money+o.getRmb());
			}else{
				m.put(o.getPlayerId(), o.getRmb());
			}
		}
		for(Iterator<Entry<Integer, Integer>> it=m.entrySet().iterator();it.hasNext();){
			Entry<Integer,Integer> e=it.next();
			if(e.getValue()> bottom&&e.getValue()<top){
			     ids.add(e.getKey());
		    }
		}
		return ids;
	}
	/**
	 * 统计购买某类物品的数量总额
	 * @param map
	 * @param level
	 * @return
	 */
	public Map<String,Integer> andAcount(Map<String,Integer> map,String level){
		Integer sum=map.get(level);
		if(sum==null){
		   sum=0;	
		}else{
			map.remove(level);
		}
		map.put(level,sum+1);
		return map;
	}

	/**
	 * 玩家首次购买的物品(金额)分布
	 * @return
	 */
	public List<PayType> getFirstPayMoney(){
		List<PayLog> list=payLogDao.getAllPaylogs();//查询所有购物日志
		List<PayLog> ls=getSortedPayLog(list);//玩家首次购物
		Map<String,Integer> mapCount=statisticsPayTypesMoney(ls);
		
		List<PayType> returnList=new ArrayList<PayType>();
		for(Iterator<Entry<String, Integer>> it=mapCount.entrySet().iterator();it.hasNext();){
			Entry<String, Integer> e=it.next();
			PayType pt=new PayType(e.getKey(), e.getValue(), 0);
			returnList.add(pt);
		}
		return returnList;
	}
	/**
	 * 玩家首次购买的物品(次数)分布
	 * @return
	 */
	public List<PayType> getFirstPayCount(){
		List<PayLog> list=payLogDao.getAllPaylogs();//查询所有购物日志
		List<PayLog> ls=getSortedPayLog(list);//玩家首次购物
		Map<String,Integer> mapCount=statisticsPayTypes(ls);
		List<PayType> returnList=new ArrayList<PayType>();
		for(Iterator<Entry<String, Integer>> it=mapCount.entrySet().iterator();it.hasNext();){
			Entry<String, Integer> e=it.next();
			PayType pt=new PayType(e.getKey(), e.getValue(), 0);
			returnList.add(pt);
		}
		return returnList;
	}
	/**
	 * 排序，筛选出玩家的第一次购物日志
	 * @return
	 */
	public List<PayLog> getSortedPayLog(List<PayLog> list){
		List<PayLog> ls=new ArrayList<PayLog>();//玩家首次购物
		Collections.sort(list,new Comparator<PayLog>(){
			@Override
			public int compare(PayLog o1, PayLog o2) {
				return ((Integer)o2.getId()).compareTo(o1.getId());
			}
			
		});
		for(PayLog pl:list){
			Map<Integer,PayLog> map=new HashMap<Integer,PayLog>();
			if(!map.containsKey(pl.getPlayerId())){
				map.put(pl.getPlayerId(),pl );
				ls.add(pl);//将首次购物的玩家加入首次购物
			}
		}
		return ls;
	}
	/**
	 * 统计玩家购买物品(次数)分布
	 * @param list 消费日志
	 * @return
	 */
	public Map<String,Integer> statisticsPayTypes(List<PayLog> list){
		Map<String,Integer> mapCount=new HashMap<String,Integer>();
		for(PayLog pl:list){
			if(pl.getItemId()==125){//打造核心类
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_1);
			}else if(pl.getItemId()==126||pl.getItemId()==127||pl.getItemId()==4505){//打造辅助类
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_2);
			}else if(pl.getItemId()==4877||pl.getItemId()==4878||pl.getItemId()==4879){//彩盒
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_3);
			}else if(pl.getItemId()==4485){//密码卡
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_4);
			}else if(pl.getItemId()==4305){//道具-vip
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_5);
			}else if(pl.getItemType()==4){//道具-其它
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_6);
			}else if(pl.getItemId()==4918||pl.getId()==4925){//靶场
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_7);
			}else if(pl.getItemId()==5012||pl.getItemId()==5013||pl.getItemId()==5014){//战队
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_8);
			}else if(pl.getItemId()==4889||pl.getItemId()==4890||pl.getItemId()==4891||pl.getItemId()==4892){//熔炼
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_9);
			}else if(pl.getItemType()==1||pl.getItemType()==2||pl.getItemType()==3){//装备
				mapCount=this.andAcount(mapCount, Constants.PAY_TYPE_10);
			}
		}
		return mapCount;
	}
	/**
	 * 统计玩家购买物品(金额)分布
	 * @param list 消费日志
	 * @return
	 */
	public Map<String,Integer> statisticsPayTypesMoney(List<PayLog> list){
		Map<String,Integer> mapCount=new HashMap<String,Integer>();
		for(PayLog pl:list){
			if(pl.getItemId()==125){//打造核心类
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_1,pl);
			}else if(pl.getItemId()==126||pl.getItemId()==127||pl.getItemId()==4505){//打造辅助类
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_2,pl);
			}else if(pl.getItemId()==4877||pl.getItemId()==4878||pl.getItemId()==4879){//彩盒
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_3,pl);
			}else if(pl.getItemId()==4485){//密码卡
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_4,pl);
			}else if(pl.getItemId()==4305){//道具-vip
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_5,pl);
			}else if(pl.getItemType()==4){//道具-其它
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_6,pl);
			}else if(pl.getItemId()==4918||pl.getItemId()==4925){//靶场
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_7,pl);
			}else if(pl.getItemId()==5012||pl.getItemId()==5013||pl.getItemId()==5014){//战队
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_8,pl);
			}else if(pl.getItemId()==4889||pl.getItemId()==4890||pl.getItemId()==4891||pl.getItemId()==4892){//熔炼
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_9,pl);
			}else if(pl.getItemType()==1||pl.getItemType()==2||pl.getItemType()==3){//装备
				mapCount=this.andMoney(mapCount, Constants.PAY_TYPE_10,pl);
			}
		}
		return mapCount;
	}
	/**
	 * 统计购买某类物品的数量总额
	 * @param map
	 * @param level (某类物品)
	 * @return
	 */
	public Map<String,Integer> andMoney(Map<String,Integer> map,String level,PayLog pl){
		Integer sum=map.get(level);
		if(sum==null){
		   sum=0;	
		}else{
			map.remove(level);
		}
		map.put(level,((Integer)(sum+pl.getPayAmount()/100)));
		return map;
	}
}
