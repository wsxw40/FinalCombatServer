package com.pearl.o2o.servlet.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTrack;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class PlayerOnline extends BaseServerServlet {
	private static final long serialVersionUID = -186025727266294629L;
	static Logger logger = LoggerFactory.getLogger(PlayerOnline.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			int uid=r.readInt();
			final String userName=r.readString().trim();//openip
			String name = r.readString();
			String ip = r.readString();
			String key=r.readString();//openkey //没屁用
			final String isXunleiVip = "";
			String internetCafe = "";
			int accountType = 0;
			//获取角色信息
			Player player = getService.getPlayerByName(userName,name);
			String errorMsg="";
			int playerId2=0;
			try{
				if(player == null){
					player=createService.createPlayer(userName,name,accountType);
					
					if(ConfigurationUtil.PLAYER_TRACK_TIMESTAMP==0 || ConfigurationUtil.PLAYER_TRACK_TIMESTAMP==(new Date().getMonth()+1)){
						PlayerTrack pt = new PlayerTrack();
						pt.setPlayerId(player.getId());
						pt.setGiftTime("");
						pt.setMedalCount(0);
						pt.setGoldCount(0);
						pt.setFirstCost(0);
						pt.setFirstCostTime("");
						pt.setNewRenewItem("");
						ServiceLocator.getService.getPlayerTrackDao().insertPlayerTrack(pt);
					}
					
				}
				playerId2=player.getId();
			}catch (BaseException e) {
				errorMsg=e.getMessage();
			}
			final int playerId=playerId2;
			//获取角色的战队信息
//			Team team = getService.getTeamByPlayerId(playerId);
			
		 	if(player==null||player.getId()==0){
		 		logger.warn(errorMsg);
				out.write(BinaryUtil.toByta(uid));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(errorMsg));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta((float)0));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(1));   
				out.write(BinaryUtil.toByta((byte)0)); //is_gm
			}else{
				PlayerLocation location=new PlayerLocation(playerId,0,0);
				mcc.set(CacheUtil.oPlayerLocation(playerId), Constants.CACHE_TIMEOUT_DAY, location,Constants.CACHE_TIMEOUT);
				if(!"".equals(isXunleiVip)&&StringUtil.toInt(isXunleiVip)!=0&&player.getIsVip()==0){
					//10.18版本前是 player.setIsVip(2)    貌似没有用过
					//加入新vip系统后改为200
					player.setIsVip(200);
				}
				if(!"".equals(internetCafe)&&StringUtil.toInt(internetCafe)!=0){
					player.setInternetCafe(StringUtil.toInt(internetCafe));
				}else{
					player.setInternetCafe(0);
				}
				updateService.updatePlayerWhileOnline(player, ip);
				getService.getPlayerMelting(playerId, true);
				final boolean isFirstLoginToday = getService.isFirstLogin(playerId, 0);//是否是今天第一次登陆
				final boolean isFirstLoginWeek = getService.isFirstLogin(playerId, 1);//是否是本周第一次登陆
				final Player p = player;
				getService.checkGrowthShot(player);

				//成长任务：新用戶派发1级长任务
//				if(null == player.getTutorial() || player.getTutorial().length() != GrowthMissionConstants.MODULE_NUM){
//					player.setTutorial(GrowthMissionConstants.DEFAULTTUTORIAL);
//				}
				
//				if(!isFirstLoginToday){
//					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue(), playerId, new Date(),0,0,null,null);
//				}
				Runnable onlineTask = new Runnable(){
					@Override
					public void run() {
						try {
							//成长任务：新用戶派发1级长任务
							updateService.initGrowthMission4NewPlayer(p);	
							//新用户派发1级任务，初始化功能状态值
//							SysItem sysItem=getService.getSysItemByItemId(4304);
//							StringBuilder ids = new StringBuilder("");
//							createService.awardToPlayer(p,sysItem, ids);
//							createService.sendSystemMail(p, CommonMsg.GIFT_EMAIL_SYS,CommonUtil.messageFormat(CommonMsg.PRESENT_TO_PLAYER,sysItem.getDisplayName()), ids.toString());
							if(!"".equals(isXunleiVip)&&StringUtil.toInt(isXunleiVip)!=0){
								List<SysItem> selects=new ArrayList<SysItem>();
								for(String id:Constants.DEFAULT_XUNLEI_VIP_GIFT){
									try{
										selects.add(getService.getSysItemByItemId(StringUtil.toInt(id)));
									}catch (Exception e) {
										logger.warn("VIP gift happen error");
									}
								}
								createService.presentToPlayer(selects, p.getName(), playerId, CommonMsg.PRESENT_TO_XUNLEI_VIP);
							}
							if(isFirstLoginToday){
								//活动
								getService.checkAvailablePlayerActivities(playerId);
								updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue(), playerId, null,0,0,null,null);
								updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LEVEL_FIRST_LOGIN.getValue(), playerId, null, 0, 0, null, null);
								//派送任务 由于新用户一级为开放 每日 每周 活动 模块
								//updateService.sendMission(playerId, p.getName(), isFirstLoginToday, isFirstLoginWeek);
								//清除昨日的挽留机制数据
								nosqlService.clearStayData(p);
								
								getService.checkAndCreatePlayerAchievement(playerId);
								//初始化玩家排行榜
								for(String type : Constants.PERSONAL_TOP_TYPE){
									if("kFightNum".equals(type)){
										for(int cid : Constants.CHARACTER_IDS){
											createService.initPlayerFightNumRankInTop(playerId, String.valueOf(cid));
										}
									}else{
										createService.initPlayerCommonRankInTop(playerId, type);
									}
								}
							}
							//时间段内登录活动
							updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue(), playerId, new Date(),0,0,null,null);
							
						} catch (Exception e) {
							logger.warn("error happened int onlineMessageTask",e);
						}
		
					}
				};
				if(!"none".equals(ConfigurationUtil.XUNLEI_LOGIN_URL)){
				Runnable xunleiTask = new Runnable(){
					@Override
					public void run() {
						try {
							if(p.getIsXunlei()!=1&&p.getIsXunlei()<30){
								String serverIP=ConfigurationUtil.XUNLEI_SERVER_IP;
								String urlStr=ConfigurationUtil.XUNLEI_LOGIN_URL;
								String dateStr=""+new Date().getTime();
								String str = MD5Util.md5(userName+serverIP+ dateStr+"dfa0988aajmx*ds1wa");
								urlStr+="account="+userName+"&";
								urlStr+="sid="+serverIP+"&";
								urlStr+="time="+dateStr+"&";
								urlStr+="sign="+str;
								logger.debug(urlStr);
								URL url=new URL(urlStr);
								URLConnection connection=url.openConnection();
								connection.setConnectTimeout(1000);
								connection.setReadTimeout(1000);
								InputStreamReader r = new InputStreamReader(connection.getInputStream());
								BufferedReader rd = new BufferedReader(r);
								StringBuffer sb = new StringBuffer();
								String line;
								while ((line = rd.readLine()) != null) {
									sb.append(line);
								}
								rd.close();
								String result = sb.toString(); 
								logger.debug(result);
								if("result=0".equals(result)){
									p.setIsXunlei(1);
									updateService.updatePlayerInfoOnly(p);
								}else{
									p.setIsXunlei(p.getIsXunlei()+10);
									updateService.updatePlayerInfoOnly(p);
								}
							}
						} catch (Exception e) {
							logger.error("error happened int xunleiTask",e);
						}
					}
				};
				ServiceLocator.asynTakService.execute(xunleiTask);
				}
				ServiceLocator.asynTakService.execute(onlineTask);
				out.write(BinaryUtil.toByta(uid));
				out.write(BinaryUtil.toByta(playerId));
				out.write(BinaryUtil.toByta(player.getName()));
				out.write(BinaryUtil.toByta(player.getRank()));
				out.write(BinaryUtil.toByta(player.getExp()));
				if (player.getProfile() == null) {
					out.write(BinaryUtil.toByta(""));
				}else{
					out.write(BinaryUtil.toByta(player.getProfile()));
				}
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(player.getIsNew().byteValue()));
				out.write(BinaryUtil.toByta(player.getIsVip().byteValue()));
				out.write(BinaryUtil.toByta((byte)player.getInternetCafe()));
				out.write(BinaryUtil.toByta((byte)0));
				if(player.getIcon() == null){
					out.write(BinaryUtil.toByta(Constants.DEFAULT_PLAYER_ICON));
				}else{
					out.write(BinaryUtil.toByta(player.getIcon()));
				}
				out.write(BinaryUtil.toByta((byte)0));
				//top
				out.write(BinaryUtil.toByta(0));
				//fightnum
				out.write(BinaryUtil.toByta(0));
				//win rate
				out.write(BinaryUtil.toByta((float)0.0));
				//team
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(1));
				out.write(BinaryUtil.toByta((byte)0)); //is_gm
				if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
					ServiceLocator.nosqlService.addXunleiLog("1.1"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
							+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER+player.getRank()
							+Constants.XUNLEI_LOG_DELIMITER+CommonUtil.simpleDateFormat.format(new Date()));
					ServiceLocator.nosqlService.addXunleiLog("1.5"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
							+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
							+CommonUtil.simpleDateFormat.format(new Date()));
				}
				{
					infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
							LogUtils.JoinerByTab.join("1.4",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,playerId,player.getName()));
					
					
					infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
							LogUtils.JoinerByTab.join("2.1",playerId,player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,ip,playerId,player.getName(),player.getRank()));
					
					String createMessage=String.format("%s\t%s\t%s\t%s", playerId,player.getName(),player.getUserName(),new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
					transferDataToDc.addLog("bjCreatePlayer", createMessage);
					String loginMessage=String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s", playerId,player.getName(),player.getUserName(),player.getRank(),ip,new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(player.getCreateTime()),new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
					transferDataToDc.addLog("bjLoginIn", loginMessage);
				
				}
			}
		 	
			//登录时，二级密码是否输入状态置为0，表示没有输入过二级密码
		 	mcc.set(CacheUtil.sPlayerSPWIE(playerId),  Constants.CACHE_TIMEOUT_DAY,0,Constants.CACHE_TIMEOUT);	
		 	
			return out.toByteArray();
		}catch (BaseException e) {
			out.write(BinaryUtil.toByta(e.getMessage()));
			return out.toByteArray();
		}
		catch (Exception e) {
			logger.warn("Error in CharacterOnline: ", e);
			throw e;
		}
	}
}
