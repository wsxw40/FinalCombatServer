package com.pearl.o2o.service.flexservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.QwPlayerDay;
import com.pearl.o2o.pojo.QwPlayerSum;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class FlexQwInfo {
	private static Logger logger = LoggerFactory.getLogger(FlexQwInfo.class);
	public List<String> getUserList() throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
		Set<Tuple> zrangeWithScores = nosql.zrangeWithScores(QW_KEY_TOP, 0, -1);
		List<String> list = new ArrayList<String>();
		list.add(CommonUtil.simpleDateFormat.format(new Date())+" 共有"+	nosql.hashlen(Constants.QW_SUM_KEY_PREFIX)+"玩家");
		list.add(CommonUtil.simpleDateFormat.format(new Date())+" 排行榜共"+zrangeWithScores.size()+"条");
		int i_int=1;
		for (Tuple tuple : zrangeWithScores) {
			list.add(i_int+"  "+tuple.getElement()+"  "+-tuple.getScore());
			i_int++;
		}
		return list;
	}
	/**
	 * 查询发送删除记录
	 * @return
	 * @throws Exception
	 */
	public List<String> getSendOrDelTime() throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
		List<String> list = new ArrayList<String>();
		Map<String, String> keys = nosql.hgetAll("qw:sendOrDel:");
		list.add(DateFormatUtil.getYMDHMSSf().format(new Date())+"刷新时间");
		for (Entry<String, String> kv : keys.entrySet()) {
			list.add(kv.getKey()+"  "+kv.getValue());
		}
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}
	/**
	 * 根据id获得玩家的数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public List<String> getPlayerIdByQwInfo(Integer playerId) throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		Player player = ServiceLocator.getService.getPlayerById(playerId);
		List<String> list = new ArrayList<String>();
		if(player!=null){
			String QW_KEY_DAY = Constants.QW_DAY_KEY_PREFIX+ playerId+":"+"*";//单
			
			Map<String, String> keys = nosql.hgetAll(Constants.QW_DAY_KEY_PREFIX+ playerId);
			String sunStr = nosql.hashGet(Constants.QW_SUM_KEY_PREFIX, playerId+"");
			if (sunStr!=null) {
				list.add(Constants.QW_SUM_KEY_PREFIX+ playerId+"  "+sunStr);
			}
			if (keys != null) {
				for (Entry<String, String> kv : keys.entrySet()) {
					list.add(kv.getKey()+"  "+kv.getValue());
				}
			}
			Collections.sort(list);
			Collections.reverse(list);
		}else {
			list.add("该id不存在");
		}
		return list;
	}
	/**
	 * 根据id删除
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public String deletePalyerIdByQwInfo(Integer playerId) throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		Player player = ServiceLocator.getService.getPlayerById(playerId);
		if(player!=null){
			String format = DateFormatUtil.getYMDSf().format(new Date());
			
			int rank = (int)nosql.zRank(Constants.QW_TOP_KEY_PREFIX, String.valueOf(playerId));//获得元素位置 -1为不存在，从0开始
			if (rank!=-1) {
				nosql.zRem(Constants.QW_TOP_KEY_PREFIX, String.valueOf(playerId));//排名清空
			}
			nosql.hashSet(Constants.QW_SUM_KEY_PREFIX, playerId+"", QwPlayerSum.QW_SUM_DEFAULT_DATA);// 对用户总进行重置
			nosql.hashSet(Constants.QW_DAY_KEY_PREFIX+ playerId, format, QwPlayerDay.QW_DAY_DEFAULT_DATA);// 对用户今天的单天数据进行重置
			return "对该用户总进行重置";
		}else {
			return "该id不存在";
		}
	}
	/**
	 * 删除数据
	 * @param date1
	 * @param date2
	 * @return
	 */
	public String close_account(String date1,String date2){ 
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String dateFormat = CommonUtil.dateFormat.format(new Date());
		String dateFormatDate = CommonUtil.dateFormatDate.format(new Date());
		String str_1="";
		if (dateFormat.equals(date1)&&dateFormatDate.equals(date2)) {
			try {
					int size=(int)nosql.hashlen(Constants.QW_SUM_KEY_PREFIX);
					if (size<=0) 
						return "数据已经为空";
					Map<String, String> hgetAll = nosql.hgetAll(Constants.QW_SUM_KEY_PREFIX);//获得玩家总的list
					for (Entry<String, String> str : hgetAll.entrySet()) {
						Integer playerId = Integer.valueOf(str.getKey());
						nosql.delete(Constants.QW_DAY_KEY_PREFIX+playerId);//删除用户单日
					}
					nosql.delete(Constants.QW_SUM_KEY_PREFIX);// 删除对应用户总
					nosql.delete(Constants.QW_TOP_KEY_PREFIX);// 删除总排行榜
					
					logger.info(CommonUtil.simpleDateFormat.format(new Date())+"del succeed");
					str_1 =  "删除成功  清除"+size+"位玩家数据";
			} catch (Exception e) {
				logger.error(CommonUtil.simpleDateFormat.format(new Date())+"del error");
				str_1 = "删除失败";
			}
			try {
				nosql.hashSet("qw:sendOrDel:", DateFormatUtil.getYMDHMSSf().format(new Date()), str_1);
			} catch (Exception e) {
				e.printStackTrace();
			}// 保持删除
			return str_1;
		} else {
			return "验证码输入错误";
		}
	}
	/**
	 * 发送物品
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public String sendSysItem(String date1,String date2) {
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String dateFormat = CommonUtil.dateFormat.format(new Date());
		String dateFormatDate = CommonUtil.dateFormatDate.format(new Date());
		if (dateFormat.equals(date1)&&dateFormatDate.equals(date2)) {
			String str_1="";
			int[] counts={0,0,0,0,0}; 
			try {
					GetService getService=ServiceLocator.getService;
					CreateService createService = ServiceLocator.createService;
					int size=(int)nosql.hashlen(Constants.QW_SUM_KEY_PREFIX);
					if (size<=0) 
						return "数据已经为空";

					//发送东西 <C278^2^<SN6283^0>^2016-01-19 11>
					Payment payment = new Payment(1,1);
					String item_6314="<C278^2^<SN6314^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6283="<C278^2^<SN6283^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6284="<C278^2^<SN6284^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6285="<C278^2^<SN6285^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					String item_6286="<C278^2^<SN6286^0>^"+CommonUtil.dateFormat.format(new Date())+">" ;
					//用于记录已经获得物品的人
					List<String> playerIds = new ArrayList<String>();
					//获得前10的人
					Set<Tuple> tuples = nosql.zrangeWithScores(Constants.QW_TOP_KEY_PREFIX, 0, 9);//1-10
					Integer rankNum=0;
					for (Tuple tuple : tuples) {
						rankNum++;
						Integer playerId = Integer.valueOf(tuple.getElement());
						Player player = getService.getPlayerById(playerId);
						QwPlayerSum pSum = getService.getQW_player_Sum(playerId);//根据id获得玩家总数据
						if(pSum.getScoce()>=Constants.QW_RATIO[10]){//判断积分是否到达 
							playerIds.add(playerId.toString());
							if (player==null) continue;
							createService.sendSystemMail(player, "<C762^0>", item_6286 , 6286, payment);
							log_1(player,pSum.toString(),6286,rankNum);//记录
							counts[4]++;
						}
					}
					//获得玩家总的list
					Map<String, String> keys = nosql.hgetAll(Constants.QW_SUM_KEY_PREFIX);
					//移除已经获得物品的玩家
					for (String string : playerIds) {
						keys.remove(string);
					}
					//懒的写成数组了，等下次吧
					for (Entry<String, String> str : keys.entrySet()) {
						Integer playerId = Integer.valueOf(str.getKey());
						String sumStr = str.getValue();//玩家总
						Integer scoce = Integer.valueOf(str.getValue().split("\\|")[0]);//积分
						Player player = getService.getPlayerById(playerId);
						if (player==null) 
							continue;
						if (scoce<=Constants.QW_RATIO[2]) {//599
							createService.sendSystemMail(player, "<C762^0>", item_6314 , 6314, payment);
							log_1(player,sumStr,6314,0);
							counts[0]++;
						}else if (scoce<=Constants.QW_RATIO[5]) {//1199
							createService.sendSystemMail(player, "<C762^0>", item_6283 , 6283, payment);
							log_1(player,sumStr,6283,0);
							counts[1]++;
						}else if (scoce<=Constants.QW_RATIO[8]) {//1799
							createService.sendSystemMail(player, "<C762^0>", item_6284 , 6284, payment);
							log_1(player,sumStr,6284,0);
							counts[2]++;
						}else if (scoce>Constants.QW_RATIO[8]) {//1800
							createService.sendSystemMail(player, "<C762^0>", item_6285 , 6285, payment);
							log_1(player,sumStr,6285,0);
							counts[3]++;
						}
					}
					logger.info(CommonUtil.simpleDateFormat.format(new Date())+"send succeed "+counts[0]+"|"+counts[1]+"|"+counts[2]+"|"+counts[3]+"|"+counts[4]);
					str_1="发送物品成功 "+counts[0]+"|"+counts[1]+"|"+counts[2]+"|"+counts[3]+"|"+counts[4];
			} catch (Exception e) {
				logger.error(CommonUtil.simpleDateFormat.format(new Date())+"send error");
				str_1="发送物品失败";
			}
			try {
				nosql.hashSet("qw:sendOrDel:", DateFormatUtil.getYMDHMSSf().format(new Date()), str_1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return str_1;
		} else {
			return "验证码输入错误";
		}
	}
	/**
	 * 日志记录
	 * @param player
	 * @param sum
	 * @param sysItemId
	 * @param rankNum
	 */
	private static void log_1(Player player,String sum,int sysItemId,Integer rankNum) {
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			ServiceLocator.nosqlService.addXunleiLog("20.2"
				+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
				+ Constants.XUNLEI_LOG_DELIMITER + player.getId()
				+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
				+ Constants.XUNLEI_LOG_DELIMITER + sysItemId//玩家拿到的物品
				+ Constants.XUNLEI_LOG_DELIMITER + rankNum//玩家排名只记录前10的，
				+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())//结算时间
				+ Constants.XUNLEI_LOG_DELIMITER + "^"+sum+"^"//玩家总战绩表
				);
			}
	}
}
