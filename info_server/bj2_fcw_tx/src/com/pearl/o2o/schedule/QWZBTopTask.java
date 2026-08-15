package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class QWZBTopTask implements Runnable{
	private NoSql nosql;
	public static Logger log = LoggerFactory.getLogger(QWZBTopTask.class);
	@Override
	public void run() {
		try{
			String tokenKey = Constants.QWZB_TOP_MCC_KEY;
			boolean isGet = ServiceLocator.ml.tryLockWithNoDelay(tokenKey);//锁 //会有多台服务器同时运行，所以上锁
			if (isGet) {//抢到锁后
				try {
					Date now =new Date();
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					c.set(Calendar.HOUR_OF_DAY, 2);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
				//	c.set(Calendar.DAY_OF_WEEK, 2);
					Date oNowTime=c.getTime();
					String nowTime = sdf.format(oNowTime);
					//先判断redis里面上次进行，不登陆积分减少的操作的时间
					nosql = ServiceLocator.nosqlService.getNosql();
					String flag_Time = nosql.get("qwzb_flag");
					if(nowTime.equals(flag_Time)){
						log.info(CommonUtil.simpleDateFormat.format(new Date())+ "QWZBTopTask today already executeing :"+flag_Time);
						return;
					}
					nosql.set("qwzb_flag", nowTime);
					//nosql.zRemRangeByScore(Constants.QW_TOP_KEY_PREFIX, -10, 10);//不支持
					Set<Tuple> tuples = nosql.zrangeWithScores(Constants.QW_TOP_KEY_PREFIX, 0, -1);
					int index=0;
					for (Tuple tuple : tuples) {
						int score=-(int)tuple.getScore();
						index++;
						if (index<=10&&score>Constants.QW_RATIO[10]) {
							reduceScore(tuple.getElement(), score, 7, 0.95,1);
							continue;
						}
						if(score<=Constants.QW_RATIO[8]&&score>Constants.QW_RATIO[5])//1799 1200
							reduceScore(tuple.getElement(), score, 7, 0.97,2);
						if (score > Constants.QW_RATIO[8])//1800
							reduceScore(tuple.getElement(), score, 7, 0.96,2);
					}
					log.info(CommonUtil.simpleDateFormat.format(new Date())	+ "QWZBTopTask run win:");
				} finally {
					ServiceLocator.ml.unlock(tokenKey);//释放
				}
			}	
		}catch (Exception e) {
			log.error(CommonUtil.simpleDateFormat.format(new Date())+ "QWZBTopTask error", e);
		}
	}
	private void reduceScore(String playerId, int score,int period,double ratio,int start) throws Exception{
		Map<String, String> hgetAll = nosql.hgetAll(Constants.QW_DAY_KEY_PREFIX+ playerId);//获得玩家单天数据
		if (hgetAll==null) return;
		List<String> slist = new ArrayList<String>();
		for (String str : hgetAll.keySet()) {
			slist.add(str);
		}
		if (slist.size()==0) return;
		Collections.sort(slist);//排序
		String oldTimeStr=slist.get(slist.size()-1);
		oldTimeStr=oldTimeStr.substring(oldTimeStr.lastIndexOf(":") +1,oldTimeStr.length());
		try {
			Date oldTime = DateFormatUtil.getYMDSf().parse(oldTimeStr);// 解析
			long lTime=System.currentTimeMillis()-oldTime.getTime();
			int intb=(int) (lTime/(3600*24*1000))-1;
			if (intb/period>=start&&intb%period==0) {
				int score_1=(int)(score*ratio);
				//然后存总磅跟个人
				nosql.zAdd(Constants.QW_TOP_KEY_PREFIX, -score_1, String.valueOf(playerId));//加入
				String str = nosql.hashGet(Constants.QW_SUM_KEY_PREFIX, playerId+"");
				nosql.set(Constants.QW_SUM_KEY_PREFIX+ playerId, score_1+str.substring(str.indexOf("|"),str.length()));// 第一次
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					ServiceLocator.nosqlService.addXunleiLog("20.3"
						+ Constants.XUNLEI_LOG_DELIMITER + playerId//玩家帐号ID
						+ Constants.XUNLEI_LOG_DELIMITER + score//玩家本局积分
						+ Constants.XUNLEI_LOG_DELIMITER + score_1//玩家本局积分
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())//结算时间
						);
					}
			} 
		} catch (ParseException e) {
			return;
		}
	}
}
