package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.QwPlayerDay;
import com.pearl.o2o.pojo.QwPlayerSum;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetQwInfo extends BaseClientServlet {
	private static final long serialVersionUID = 5117201445216191162L;
	static Logger log = LoggerFactory.getLogger(GetQwInfo.class.getName());
	private static final String[] paramNames = { "pid", "t", "page"};

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);//0个人当天数据 1战绩表 2排行榜
			int page = StringUtil.toInt(args[2]);//0个人当天数据 1战绩表 2排行榜
			NoSql nosql = nosqlService.getNosql();
			String format = DateFormatUtil.getYMDSf().format(new Date());
			String QW_KEY_DAY = Constants.QW_DAY_KEY_PREFIX+ playerId;//用户单日 
			String QW_KEY_SUM = Constants.QW_SUM_KEY_PREFIX+ playerId;//用户总
			String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
			//每有打过
			//如果是第一次，那就是要获得总杀人，积分，段位，排名
			//用于枪王总排行榜
			//获得数据
			String result =null;
			if (type==0) {//个人当天数据
				//rpc.safecall("get_qw_info",{pid = 10638000,t = 0},function(data) end)
				//K_VALUE=-1，表示当天数据没写入
				//积分==0，表示没达、打满十盘，定位赛
				//int[] ints_d=getService.getQW_player_Day(playerId);
				
				QwPlayerSum pSum = getService.getQW_player_Sum(playerId);//获得
				getService.QWPS_fresh__MateRank(pSum,playerId);//获得最新段位
				int rank = (int)nosql.zRank(QW_KEY_TOP, String.valueOf(playerId));//获得元素位置 -1为不存在，从0开始
				pSum.setRankNum(rank==-1 ? -1:++rank);//最新排名
				int[] ints_d = StringUtil.strsToInts(pSum.toString(),9, "\\|");
				//
				Integer rate=0;
				int rankNum = pSum.getRankNum();
				if (rankNum>=12) {
					rate=100;
				}else{
					int scoce = pSum.getScoce();
					for (int i = 0; i < Constants.QW_RATIO.length; i++) {
						if(scoce<=Constants.QW_RATIO[i]){
							if (i==0) {
								rate=(scoce*100/Constants.QW_RATIO[i]);
							}else {
								rate=((scoce-Constants.QW_RATIO[i-1])*100/(Constants.QW_RATIO[i]-Constants.QW_RATIO[i-1]));
							}
							break;
						}
					}
				}
				//
				result=Converter.getQwInfo(type,ints_d,null,null,null,null,rate);
			}
			if (type==1) {//个人2周记录
				int PAGE_SIZE=12;//分页大小
				//rpc.safecall("get_qw_info",{pid = 10638000,t = 1,page =1},function(data) end)
				log.warn(new Date()+QW_KEY_DAY);
				Map<String, String> hgetAll = nosql.hgetAll(QW_KEY_DAY);//获得玩家单天数据
				if (hgetAll!=null&&hgetAll.size()!=0) {
					List<String> keys = new ArrayList<String>();
					for (String str : hgetAll.keySet()) {
						keys.add(str);
					}
					Collections.sort(keys);
					Collections.reverse(keys);
					//删除对应超出的kv
					if(keys.size()>PAGE_SIZE*2){
						List<String> removeStrs = keys.subList(PAGE_SIZE*2, keys.size());
						for (String removeStr : removeStrs) {
							hgetAll.remove(removeStr);
						}
					}
					List<String[]> list = new ArrayList<String[]>();
					//=======分页开始
					int total =keys.size();
					int pageNum=(total+PAGE_SIZE-1)/PAGE_SIZE;//分页总数目
					page=page<1?1:page;
					page=page>pageNum?pageNum:page;
					int start=(page-1)*PAGE_SIZE;
					int end=page*PAGE_SIZE-1;
					//=======分页结束
					for (int i = start; i <= end; i++) {
						if (i>=keys.size())
							break;
						String day = hgetAll.get(keys.get(i));
						if(day.split("\\|").length == 10){
							day =day+"|0";
						}
						day = day==null? QwPlayerDay.QW_DAY_DEFAULT_DATA:day;
						String str1 = day+"|"+keys.get(i);
						list.add(str1.split("\\|"));
					}
					result=Converter.getQwInfo(type,null,list,page,pageNum,null,null);
				}else {
					List<String[]> list = new ArrayList<String[]>();
					String str1 ="0|0|0|0|0|0|-1|-1|12|0|0|"+format;
					list.add(str1.split("\\|"));
					result=Converter.getQwInfo(type,null,list,1,1,null,null);
				}
			}
			if (type==2) {//排行榜 分页从1开始
				//rpc.safecall("get_qw_info",{pid = 10638000,t = 2,page =1},function(data) end)	
				int PAGE_SIZE=10;//分页大小
				int total = (int)nosql.zCard(QW_KEY_TOP);//总数目
				int pageNum=(total+PAGE_SIZE-1)/PAGE_SIZE;//分页总数目
				page=page<1?1:page;
				page=page>pageNum?pageNum:page;
				int start=(page-1)*PAGE_SIZE;
				int end=page*PAGE_SIZE-1;
				int site_2=start;
				
				List<String[]> list = new ArrayList<String[]>();
				Set<Tuple> tuples = nosql.zrangeWithScores(QW_KEY_TOP, start, end);
				
				if (page==1) {
					for (Tuple tuple : tuples) {
						site_2++;
						QwPlayerSum pSum = getService.getQW_player_Sum(Integer.valueOf(tuple.getElement()));
						getService.QWPS_fresh__MateRank(pSum,Integer.valueOf(tuple.getElement()));
						pSum.setRankNum(site_2);
						list.add(pSum.toString().split("\\|"));
					}
				}else {
					for (Tuple tuple : tuples) {
						site_2++;
						QwPlayerSum pSum = getService.getQW_player_Sum(Integer.valueOf(tuple.getElement()));
						pSum.setRankNum(site_2);
						list.add(pSum.toString().split("\\|"));
					}
				}
				result=Converter.getQwInfo(type,null,null,page,pageNum,list,null);
			}
			return result;
		} catch (Exception e) {
			log.warn("Exception in GetPlayerItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
