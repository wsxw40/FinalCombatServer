package com.pearl.o2o.service.flexservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class FlexQwInfo {
	private static Logger logger = LoggerFactory.getLogger(FlexQwInfo.class);
	public List<String> getUserList() throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
		Set<Tuple> zrangeWithScores = nosql.zrangeWithScores(QW_KEY_TOP, 0, -1);
		List<String> list = new ArrayList<String>();
		list.add(CommonUtil.simpleDateFormat.format(new Date())+" 排行榜共"+zrangeWithScores.size()+"条");
		int i_int=1;
		for (Tuple tuple : zrangeWithScores) {
			list.add(i_int+"  "+tuple.getElement()+"  "+-tuple.getScore());
			i_int++;
		}
		return list;
	}
	
	public String close_account(String date1,String date2){
		String dateFormat = CommonUtil.dateFormat.format(new Date());
		String dateFormatDate = CommonUtil.dateFormatDate.format(new Date());
		if (dateFormat.equals(date1)&&dateFormatDate.equals(date2)) {
			try {
					NoSql nosql = ServiceLocator.nosqlService.getNosql();
					GetService getService=ServiceLocator.getService;
					CreateService createService = ServiceLocator.createService;
					int size = nosql.keys("qw:*").size();
					if (size<=0) {
						return "数据已经为空";
					}
					Payment payment = new Payment(1,1);
					String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
					String item_6278="<C278^2^<SN6278^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6283="<C278^2^<SN6283^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6284="<C278^2^<SN6284^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6285="<C278^2^<SN6285^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6286="<C278^2^<SN6286^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					
					Set<Tuple> tuples = nosql.zrangeWithScores(QW_KEY_TOP, 0, 9);//1-10
					for (Tuple tuple : tuples) {
						Integer playerId = Integer.valueOf(tuple.getElement());
						Player player = getService.getPlayerById(playerId);
						//发送东西
						//<C278^2^<SN6283^0>^2016-01-19 11>
						createService.sendSystemMail(player, "<C762^0>", item_6286 , 6286, payment);
						nosql.delete(Constants.QW_SUM_KEY_PREFIX + playerId);// 删除对应用户总
					}
					nosql.delete(QW_KEY_TOP);//删除总排行榜
					Set<String> keysS = nosql.keys(Constants.QW_SUM_KEY_PREFIX + "*");
					for (String str : keysS) {
						Integer playerId = Integer.valueOf(str.split(":")[2]);
						Integer scoce = Integer.valueOf(nosql.get(str).split("\\|")[0]);//积分
						Player player = getService.getPlayerById(Integer.valueOf(playerId));
						if (scoce>=1&&scoce<=599) {
							createService.sendSystemMail(player, "<C762^0>", item_6278 , 6278, new Payment(30,2));
						}else if (scoce>=600&&scoce<=1199) {
							createService.sendSystemMail(player, "<C762^0>", item_6283 , 6283, payment);
						}else if (scoce>=1200&&scoce<=1799) {
							createService.sendSystemMail(player, "<C762^0>", item_6284 , 6284, payment);
						}else if (scoce>=1800) {
							createService.sendSystemMail(player, "<C762^0>", item_6285 , 6285, payment);
						}
						//发送
						nosql.delete(Constants.QW_SUM_KEY_PREFIX + playerId);// 删除对应用户总		
					}
					Set<String> keysD = nosql.keys(Constants.QW_DAY_KEY_PREFIX+"*");
					for (String str : keysD) {
						nosql.delete(str);// 删除对应用户总		
					}
					logger.info(CommonUtil.simpleDateFormat.format(new Date())+"send succeed");
					return "发送物品成功  清除"+size+"条数据";
			} catch (Exception e) {
				logger.error(CommonUtil.simpleDateFormat.format(new Date())+"send error");
				return "发送物品失败";
			}
		} else {
			return "输入错误";
		}
	}
}
