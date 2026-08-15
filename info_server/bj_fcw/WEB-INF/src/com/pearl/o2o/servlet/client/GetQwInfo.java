package com.pearl.o2o.servlet.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
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
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String format = sf.format(new Date());
			String QW_KEY_DAY = Constants.QW_DAY_KEY_PREFIX+ playerId+":"+format;//用户单日 积分跟段位是累计的  别的都为当天的独立数据
			String QW_KEY_SUM = Constants.QW_SUM_KEY_PREFIX+ playerId;//用户总
			String QW_KEY_TOP = Constants.QW_TOP_KEY_PREFIX;//排行榜
			//每有打过
			//积分0|杀人1|死亡2|助攻3|胜利4|失败5|段位6|排名7|k值8|总杀人数目9  	0|0|0|0|0|0|-1|-1|-1|-1  redis
			//积分0|杀人1|死亡2|助攻3|胜利4|失败5|段位6|排名7|k值8|9名字|10等级|11vip等级 			0|0|0|0|0|0|-1|-1|12|-1|-1|-1  redis
			//如果是第一次，那就是要获得总杀人，积分，段位，排名
			//用于枪王总排行榜
			//获得数据
			
			String result =null;
			if (type==0) {//个人当天数据
				//K=-1，表示当天数据没写入
				//积分==0，表示没达、打满十盘，定位赛
				//rpc.safecall("get_qw_info",{pid = 10638000,t = 0},function(data) end)
				String qw_d = nosql.get(QW_KEY_DAY);
				qw_d = qw_d==null? "0|0|0|0|0|0|-1|-1|-1|-1":qw_d;
				int[] ints_d=StringUtil.strsToInts(qw_d,"\\|");
				if(ints_d[8]==-1){
					String qw_s = nosql.get(QW_KEY_SUM);
					qw_s = qw_s==null? "0|0|0|0|0|0|-1|-1|12":qw_s;
					int[] ints_s=StringUtil.strsToInts(qw_s,9,"\\|");
					ints_d[0]=ints_s[0];
					ints_d[6]=ints_s[6];
					ints_d[7]=ints_s[7];
					ints_d[8]=ints_s[8];
					ints_d[9]=ints_s[1];
					nosql.set(QW_KEY_DAY, StringUtil.intsJoint(ints_d, "|"));
				}
				result=Converter.getQwInfo(type,ints_d,null,null,null,null);
			}
			if (type==1) {//个人2周记录
				int PAGE_SIZE=12;//分页大小
				//rpc.safecall("get_qw_info",{pid = 10638000,t = 1,page =1},function(data) end)
				Set<String> keys = nosql.keys(Constants.QW_DAY_KEY_PREFIX+ playerId+":"+"*");
				if (keys!=null) {
					List<String> slist = new ArrayList<String>();
					for (String str : keys) {
						slist.add(str);
					}
					Collections.sort(slist);
					Collections.reverse(slist);
					while (slist.size()>PAGE_SIZE*2) {
						nosql.delete(slist.remove(PAGE_SIZE*2));
					}
					
					List<String[]> list = new ArrayList<String[]>();
					
					int total =slist.size();
					int pageNum=(total+PAGE_SIZE-1)/PAGE_SIZE;//分页总数目
					page=page<1?1:page;
					page=page>pageNum?pageNum:page;
					int start=(page-1)*PAGE_SIZE;
					int end=page*PAGE_SIZE-1;

					for (int i = start; i <= end; i++) {
						if (i>=slist.size())
							break;
						String day = nosql.get(slist.get(i));
						day = day==null? "0|0|0|0|0|0|-1|-1|-1|-1":day;
						String str1 = day+"|"+slist.get(i).split(":")[3];
						list.add(str1.split("\\|"));
					}
				
					result=Converter.getQwInfo(type,null,list,page,pageNum,null);
				}else {
					List<String[]> list = new ArrayList<String[]>();
					String day= "0|0|0|0|0|0|-1|-1|-1|-1";
					String str1 = day+"|"+format;
					list.add(str1.split("\\|"));
					result=Converter.getQwInfo(type,null,list,1,1,null);
				}
				//nosql.set("qw:d:10638000:2016-01-06","0|0|0|0|0|0|-1|-1|-1|-1")
				//nosql.delete("qw:d:10638000:2016-01-06");
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
				
				System.out.println(total+" "+page+" "+pageNum+" "+start+" "+end);
				List<String[]> list = new ArrayList<String[]>();
				Set<Tuple> tuples = nosql.zrangeWithScores(QW_KEY_TOP, start, end);
				for (Tuple tuple : tuples) {
					site_2++;
					String pId = tuple.getElement();
					String day = nosql.get(Constants.QW_SUM_KEY_PREFIX+pId);
					String[] strs = day.split("\\|");
					strs[7]=String.valueOf(site_2);
					list.add(strs);
				}
				result=Converter.getQwInfo(type,null,null,page,pageNum,list);
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
