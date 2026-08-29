package com.pearl.o2o.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class CompeteBuyOverTask implements Runnable {
	private boolean isManual =false;
	
	public boolean isManual() {
		return isManual;
	}
	public CompeteBuyOverTask() {
	}
	public CompeteBuyOverTask(boolean isManual) {
		this.isManual = isManual;
	}
	@Override
	public void run() {
		ServiceLocator.competeBuylog.info("Start competebuy overtask " + CommonUtil.simpleDateFormat.format(new Date()));
		MemcachedClient mcc = ServiceLocator.updateService.getMcc();
		try {
			Calendar now = Calendar.getInstance();
			GetsResponse<Calendar> response=mcc.gets(Constants.CMPT_BUY_TSK_LOCK_MCC_KEY);
			Calendar lastRunTime  = null;
			if (response == null){//之前没有执行过任务，在缓存内设置初始值
                mcc.set(Constants.CMPT_BUY_TSK_LOCK_MCC_KEY, 0, now);
                response = mcc.gets(Constants.CMPT_BUY_TSK_LOCK_MCC_KEY);
            }else{
            	lastRunTime  = response.getValue();
            }
			
			//TODO change the condition unit for judge to one day,not a week, for test
//			if(!isManual &&(lastRunTime!=null&&lastRunTime.get(Calendar.YEAR)==now.get(Calendar.YEAR) && 
//					lastRunTime.get(Calendar.WEEK_OF_YEAR)==now.get(Calendar.WEEK_OF_YEAR)&&
//					lastRunTime.get(Calendar.DAY_OF_WEEK)==now.get(Calendar.DAY_OF_WEEK))){
			if(!isManual &&(lastRunTime!=null&&lastRunTime.get(Calendar.YEAR)==now.get(Calendar.YEAR) && lastRunTime.get(Calendar.WEEK_OF_YEAR)==now.get(Calendar.WEEK_OF_YEAR))){	
				ServiceLocator.competeBuylog.info("competebuy overtask had run on " + CommonUtil.simpleDateFormat.format(lastRunTime.getTime()) +",skip on " + CommonUtil.simpleDateFormat.format(new Date()));
			}else if("2".equals(ServiceLocator.getService.getCompeteBuyTime(Constants.CMPT_BUY_STT_WEEK_DAY,Constants.CMPT_BUY_STT_TIME_HOUR,Constants.CMPT_BUY_END_WEEK_DAY,Constants.CMPT_BUY_END_TIME_HOUR)[1])){
				ServiceLocator.competeBuylog.info("competebuy overtask can not run while the activity is going on"+",skip on " + CommonUtil.simpleDateFormat.format(new Date()));
			}else{
				
				//get the pay unit (1:黄金卡,2:勋章,3:fc点(目前没有))
				String payUnits=ServiceLocator.getService.getSysConfig().get("compete.currency");
				String[] payUnitArrayStrings=payUnits.split(",");
				for(int i=0;i<payUnitArrayStrings.length;i++){
					if(StringUtil.isAllNumber(payUnitArrayStrings[i])){
						int payUnit=Integer.parseInt(payUnitArrayStrings[i]);
						if(0<payUnit && 4>payUnit){
							switch (payUnit) {
							case 1:  Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=4879;	
								break;
							case 2:  Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=4479;
								break;
							case 3:   Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=-1;
								break;
							default:
								break;
							}
						}
					}
				}	
				if(mcc.cas(Constants.CMPT_BUY_TSK_LOCK_MCC_KEY, 0,now, response.getCas())){
				NoSql nosql = ServiceLocator.nosqlService.getNosql();
				List<String> rarePlayerNames = new ArrayList<String>();
				List<String> brilliantPlayerNames = new ArrayList<String>();
				int index = 0;
				Integer[] validNums = new Integer[3];//有效价
				for(int[] item : Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS){
					String key = NosqlKeyUtil.competeBuyBid(item[0]);
					//倒序获取
					Set<Tuple> playerIds = nosql.revRangeWithScores(key, 0, -1);
					Iterator<Tuple> itrt = playerIds.iterator();
					int count =0;
					SysItem award = ServiceLocator.getService.getSysItemByItemId(item[0]);
					if(award==null){
						ServiceLocator.competeBuylog.error("CompeteBuyOverTask/AwardNull:\tsid" + item[0]);
						continue;
					}
					SysItem costSi = ServiceLocator.getService.getSysItemByItemId(item[1]);
					if(costSi==null){
						ServiceLocator.competeBuylog.error("CompeteBuyOverTask/CostItemNull:\tsid" + item[1]);
						continue;
					}
					int validNum  = item[2];
					if(nosql.zCard(key)>=item[3]){
						validNum = (int) nosql.revRangeWithScores(Constants.COMPETE_BUY_ITEM_KEY_PREX+item[0],item[3]-1,item[3]-1).iterator().next().getScore();
					}
					validNums[index++] = validNum;
					while (itrt.hasNext()) {//遍历所有参与该物品抢拍的玩家
						count++;
						Tuple entry = itrt.next();
						int playerId = StringUtil.toInt(entry.getElement());
						Player player = ServiceLocator.getService.getSimplePlayerById(playerId);
						int myNum = (int) entry.getScore();
						if(player==null){
							ServiceLocator.competeBuylog.error("CompeteBuyOverTask/PlayerNull:\tpid" + playerId + "\tcost:" + myNum);
							continue;
						}
						if(count<=item[3]){//判断是否能够获得该物品
							if(index==1){//稀有，登记玩家名称
								rarePlayerNames.add(player.getName());
							}else if(index==2){//卓越，登记玩家名称
								brilliantPlayerNames.add(player.getName());
							}
							String content = CommonUtil.messageFormatI18N(CommonMsg.COMPETE_BUY_AWARD_EMAIL_SYS,award.getDisplayName(),validNum,costSi.getDisplayName(),award.getDisplayName(),myNum,costSi.getDisplayName(),CommonMsg.COMPETE_BUY_AWARD_GET_MSG);
							ServiceLocator.createService.sendSystemMail(player, CommonMsg.COMPETE_BUY_AWARD_EMAIL_TTL, content, item[0], new Payment(1,1));
							ServiceLocator.competeBuylog.info("CompeteBuyOverTask/sendMail:\tpid:" + playerId + "\titemid:" + item[0] + "\tunit:1\tunittype:1");
						}else{
							String content = CommonUtil.messageFormatI18N(CommonMsg.COMPETE_BUY_AWARD_EMAIL_SYS,award.getDisplayName(),validNum,costSi.getDisplayName(),award.getDisplayName(),myNum,costSi.getDisplayName(),CommonMsg.COMPETE_BUY_AWARD_NOT_GET_MSG);
							ServiceLocator.createService.sendSystemMail(player,  CommonMsg.COMPETE_BUY_AWARD_EMAIL_TTL, content, item[1], new Payment(myNum,1));
							ServiceLocator.competeBuylog.info("CompeteBuyOverTask/sendMail:\tpid:" + playerId + "\titemid:" + item[1] + "\tunit:"+myNum +"\tunittype:1");
						}
					}
					//rename the key for backup
					//********为了便于以后玩家反馈查询，每次执行脚本，发完邮件后不删除redis数据，而是修改key名称用作备份******************
					try {
						nosql.rename(key, Constants.COMPETE_BUY_BACKUP_ITEM_KEY_PREX+item[0]);
					} catch (Exception e) {
						ServiceLocator.competeBuylog.error("CompeteBuyOverTask/renameKey:\t" ,e);
					}
				}
				
				//update bill board,notice all players
				ServiceLocator.soClient.updateBillBoardList(CommonUtil.messageFormatI18N(CommonMsg.CMPT_OVER_NTS_BFR_MSG,(Object[])validNums));
				ServiceLocator.soClient.updateBillBoardList(CommonUtil.messageFormatI18N(CommonMsg.CMPT_OVER_NTS_RAR_MSG, StringUtils.join(rarePlayerNames, ",")));
				ServiceLocator.soClient.updateBillBoardList(CommonUtil.messageFormatI18N(CommonMsg.CMPT_OVER_NTS_BRT_MSG, StringUtils.join(brilliantPlayerNames, ",")));
				ServiceLocator.soClient.updateBillBoardList(CommonMsg.CMPT_OVER_NTS_END_MSG);
				}
			}
		} catch (Exception e) {
			ServiceLocator.competeBuylog.error("CompeteBuyOverTask:\t" ,e);
		}
		ServiceLocator.competeBuylog.info("Competebuy overtask end at " + CommonUtil.simpleDateFormat.format(new Date()));
	}

}
