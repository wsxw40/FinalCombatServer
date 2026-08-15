package com.pearl.o2o.servlet.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class CharacterOnline extends BaseServerServlet {
	private static final long serialVersionUID = -186025727266294629L;
	static Logger LOG = LoggerFactory.getLogger(CharacterOnline.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int uid=0;
		try{
		
			uid=r.readInt();
 			final String userName=r.readString().trim();
			String ip = r.readString();
			String version = r.readString();
			String isXunleiVip=r.readString();
			String internetCafe=r.readString();
			r.readShort(); //accountType
			
//			LOG.error("internetCafe:"+internetCafe);
			if (StringUtil.isEmptyString(userName) || StringUtil.isEmptyString(ip) || StringUtil.isEmptyString(version) ) {
				throw new IllegalArgumentException("parameter error");
			}
			if(ConfigurationUtil.logConfig==1&&userName.indexOf("xldcf")==-1){
				LOG.error("xunleiID incorrent xunleiID="+userName);
			}
			//验证版本信息
			String[] clientVersions = getService.getClientVersion();
			if (clientVersions == null) {
				LOG.warn("key[client.version] is empty !!!");
				throw new Exception("key[client.version] is empty");
			}
			boolean versionCorrect = false;
			for (String v : clientVersions) {
				if (v.equals(version) || v.trim().equals("*")){
					versionCorrect = true;
					break;
				}
			}
			if (!versionCorrect) {
				throw new BaseException (CommonUtil.messageFormatI18N(ExceptionMessage.CLIENT_VERSION_ERROR, (Object[])clientVersions));
			}
			
			//验证ip是否被禁
			List<BlackIP> retBlackIPList = getService.getByIP(ip.trim());
			if(retBlackIPList!=null && retBlackIPList.size()>0){
				for(BlackIP tempIP : retBlackIPList){
					if(tempIP.getIsBanned().equals("Y")){
						throw new BaseException (ExceptionMessage.BANED_ERROR);
					}
				}
			}
			
			
			//获取角色信息
			final Integer playerId = getService.getPlayerIdByUserId(userName);
			if(playerId == null){
				out.write(BinaryUtil.toByta(uid));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
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
			}else 
//				if(mcc.get(CacheUtil.oPlayerLocation(playerId))!=null){
//				out.write(BinaryUtil.toByta(uid));
//				out.write(BinaryUtil.toByta(-2));
//				out.write(BinaryUtil.toByta(playerId));
//			}else
			{
				final Player player=getService.getPlayerByIdWithTeam(playerId);
				
				//成长任务：老用户检查一下，没有派发的派发成长任务，切开放相应功能
				if(null == player.getTutorial()){
					player.setTutorial(Constants.TUTORIAL_DEFAULT);
					updateService.sendMission(player, false, false);
				}
				getService.checkGrowthShot(player);

				//TODO ugly code. version update. add default to player
				if(player.getIsCheck()==0){
					for(int i:Constants.defalutItems){
						createService.getPlayerItemDao().createPlayItem(player.getId(), 
								getService.getSysItemByItemId(i), 1, 1, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					}
					for(int i=0;i<Constants.weaponPack.length;i++){
						if(i!=3){
							SysItem si=getService.getSysItemByItemId(Constants.weaponPack[i][3]);
							int playerItemId =createService.getPlayerItemDao().createPlayItem(player.getId(), 
									si, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
							deleteService.deletePlayerItemInMemcached(playerId, si);
							updateService.updateWeaponPackEquipment( playerId, Constants.DEFAULT_WEAPON_TYPE, playerItemId, i+1);
							
						}
					}
					player.setIsCheck(1);
					ServiceLocator.fileLog.warn("create new version item to player. playerId"+player.getId());
				}
				
				PlayerMelting pm=getService.getPlayerMelting(playerId, true);//熔能时间初始化
				//check 工程兵
				if(player.getCharacters().indexOf("7")==-1){
					player.setCharacters(Constants.DEFAULT_CHARACTERS);
				}
				getService.checkDataWhileLogin(player);
				final boolean isFirstLoginToday = getService.isFirstLogin(playerId, 0);//是否是今天第一次登陆
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_1);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_2);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_3);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_4);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_5);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_6);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_7);
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_8);
				
				//登录时，二级密码是否输入状态置为0，表示没有输入过二级密码
				mcc.set(CacheUtil.sPlayerSPWIE(playerId),  Constants.CACHE_TIMEOUT_DAY,0,Constants.CACHE_TIMEOUT);	

				if(isFirstLoginToday){//check player once one day
//					getService.checkDataWhileLogin(player);
						
						SysItem si_4479 = getService.getSysItemByItemId(4479);//勋章
						SysItem si_125 = getService.getSysItemByItemId(125);//强化部件
						SysItem si_126 = getService.getSysItemByItemId(126);//安定芯片
						SysItem si_127 = getService.getSysItemByItemId(127);//增幅能源
						createService.awardToPlayer(player,si_4479,new Payment(300,1),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
						createService.awardToPlayer(player,si_125,new Payment(200,1),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
						createService.awardToPlayer(player,si_126,new Payment(20,1),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
						createService.awardToPlayer(player,si_127,new Payment(20,1),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
										
					for(String type : Constants.PERSONAL_TOP_TYPE){
						if("kFightNum".equals(type)){
							for(int cid : Constants.CHARACTER_IDS){
								createService.initPlayerFightNumRankInTop(playerId, String.valueOf(cid));
							}
						}else{
							createService.initPlayerCommonRankInTop(playerId, type);
						}
					}
					
					//如果玩家是当天第一次登陆则要更新玩家的所猜数字
					Calendar day = new GregorianCalendar(); 
					String dateStr = CommonUtil.dateFormatDate.format(day.getTime());
					boolean isInit = nosqlService.isInitPlayerNum(playerId,dateStr);
					if(!isInit){
						day.add(GregorianCalendar.DAY_OF_MONTH, 1);
						nosqlService.updatePlayerGuessNum(playerId, dateStr, CommonUtil.dateFormatDate.format(day.getTime()));
						nosqlService.setIsInitPlayerNum(playerId,dateStr);
					}
					updateService.updateOnlineAward(playerId,true);
					
					//第一次登陆当天对战队的贡献值清零
					PlayerTeam playerTeam= getService.getPlayerTeamDao().getByPlayerId(playerId);
					if(playerTeam!=null){
						playerTeam.setTeamFightExp(0);
						playerTeam.setPersonalFightExp(0);
						getService.getPlayerTeamDao().updatePlayerTeam(playerTeam);
					}
					//第一次登陆熔炼残余值清零
					if(pm!=null){
						pm.setRemaind(0);
						updateService.updatePlayerMelting(pm);
					}
					
					PlayerInfo playerInfo=null;
					try {
						playerInfo = getService.getPlayerInfoById(playerId);
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						if(playerInfo==null){
							LOG.error("find no playerInfo for player "+playerId+",now try to create it at "+DateUtil.getDf3().format(new Date()));
							createService.createPlayerInfo(playerId);
						}
					}
					
					//每天第一次登陆 ，赠送月卡福利
					updateService.onCardsWelfare(player);
					
					//每天第一次登陆，赠送 经验 给玩家
					//createService.characterOnlineAddExp(player);
					
					//每天第一次登陆，天天礼赠送
					updateService.everydayGiftWelfare(player);
				}
				final boolean isFirstLoginWeek = getService.isFirstLogin(playerId, 1);//是否是本周第一次登陆
				final boolean isFirstLoginMonth = getService.isFirstLogin(playerId, 2);//是否本月第一次登陆
				final boolean isPlayerCheckToday = nosqlService.isPlayerCheck(playerId,  CommonUtil.dateFormatDate.format(new Date()));//是否已经签到
				
				if (isFirstLoginWeek) {
					SysItem si_5933 = getService.getSysItemByItemId(5933);//勋章
					SysItem si_5935 = getService.getSysItemByItemId(5935);//强化部件
					createService.awardToPlayer(player,si_5933,new Payment(1,0),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
					createService.awardToPlayer(player,si_5935,new Payment(1,0),null,Constants.BOOLEAN_YES);//new Payment(1 1个,0 0永久)
				}				
								
				//初始化玩家本月签到列表
				if(isFirstLoginMonth){
					if(!nosqlService.isInitDailyCheckDays(playerId, Calendar.getInstance()))
					nosqlService.initPlayerCheckList(playerId);
				}
				//验证是否黑名单
				if(Constants.BLACK_LIST_FLAG.equals(player.getBlackWhite())){
					throw new BaseException (ExceptionMessage.BLACK_ERROR);
				}
//				Team team = getService.getTeamByPlayerId(playerId);
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
				//FIXME for test 
//				if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&ConfigurationUtil.XUNLEI_SERVER_IP.equals("11211")){
//					
//					Random rd=new Random();
//					if(rd.nextInt(10)<9){
//						int rmb=rd.nextInt(1000);
//						infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
//								LogUtils.JoinerByTab.join("4.1",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,rmb,"currency",
//										player.getName(),player.getRank(),playerId,CommonUtil.isToday(player.getCreateTime())?1:0));
//						PlayerInfo pi=getService.getPlayerInfoById(player.getId());
//						pi.setXunleiPoint(pi.getXunleiPoint()+rmb);
//						updateService.getPlayerInfoDao().update(pi);
//					}
//				}
				updateService.updatePlayerWhileOnline(player, ip);
				final Player p=player;
//				if(!isFirstLoginToday){
//					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue(), playerId, new Date(),0,0,null,null);
//				}
				Runnable onlineTask = new Runnable(){
					@Override
					public void run() {
					try{
						//派送任务
						if(isFirstLoginToday){
							//每日 每周 任务
							updateService.sendMission(player , isFirstLoginToday, isFirstLoginWeek);
							//活动
							getService.checkAvailablePlayerActivities(playerId);
							updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue(), playerId, null, 0, 0, null, null);
							updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LEVEL_FIRST_LOGIN.getValue(), playerId, null, 0, 0, null, null);

							//清除昨日的挽留机制数据
							nosqlService.clearStayData(player);
							
							getService.checkAndCreatePlayerAchievement(playerId);
						}
						//成长任务：老用户检查一下，没有派发的派发成长任务，切开放相应功能
						updateService.initGrowthMission4OldPlayer(player);	
						//时间段内登录活动
						updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue(), playerId, new Date(),0,0,null,null);
						
						//send online message to friend
						List<Friend> list=getService.getFriendList(playerId, Constants.FRIEND);
						String onlineMsg = CommonUtil.messageFormatI18N(CommonMsg.FRIEND_ONLINE, new Object[]{player.getName(),player.getName()});
							for(Friend friend:list){
								if(mcc.get(CacheUtil.oPlayerLocation(friend.getFriendId()),Constants.CACHE_TIMEOUT)!=null){
									soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));
									soClient.messageOnline(friend.getName(),onlineMsg);
								}
							}
						}catch (Exception e){
							LOG.warn("Error happend during send online message to friends. exception is " + e + " cid:" + playerId,e);
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
								LOG.debug(urlStr);
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
								LOG.debug(result);
								if("result=0".equals(result)){
									p.setIsXunlei(1);
									updateService.updatePlayerInfoOnly(p);
								}else{
									p.setIsXunlei(p.getIsXunlei()+10);
									updateService.updatePlayerInfoOnly(p);
								}
							}
						} catch (Exception e) {
							LOG.error("error happened int xunleiTask",e);
							p.setIsXunlei(p.getIsXunlei()+10);
							try {
								updateService.updatePlayerInfoOnly(p);
							} catch (Exception e1) {
								LOG.warn("error happened int xunleiTask updatePlayerInfoOnly",e);
							}
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
				out.write(BinaryUtil.toByta(player.getIsNew().byteValue()));
				out.write(BinaryUtil.toByta(player.getIsVip().byteValue()));
				out.write(BinaryUtil.toByta((byte)player.getInternetCafe()));
				List<PlayerItem> buffList=player.getBuffList();
				int flag=0;
				for(PlayerItem pi:buffList){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
					if(Constants.DEFAULT_ITEM_TYPE == si.getType() && pi.getId()!=0&&si.getIId()==1&&si.getIBuffId()==Constants.DEFAULT_CARD_BUFF_ID){
						flag=StringUtil.toInt(si.getIValue());
					}
//TODO CharacterSize					
//					if(pi.getId()!=0&&si.getIId()==1&&si.getIBuffId()==Constants.DEFAULT_RELEASE_SIZE_ID){
//						player.setCharacterSize(Constants.MAX_CHARACTER_SIZE);
//					}
				}
				
				out.write(BinaryUtil.toByta((byte)flag));
				if(player.getIcon() == null){
					out.write(BinaryUtil.toByta(Constants.DEFAULT_PLAYER_ICON));
				}else{
					out.write(BinaryUtil.toByta(player.getIcon()));
				}
				if(isPlayerCheckToday){
					out.write(BinaryUtil.toByta((byte)1));
				}else{
					out.write(BinaryUtil.toByta((byte)0));
				}
//				String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(playerId, "kCommonTop");
//				int  rankNumInTop =StringUtil.toInt( nosqlService.getNosql().get(rankKey));
//				String[] selfRankValues =nosqlService.getNosql().get(rankKey)==null?null:nosqlService.getNosql().get(rankKey).split(":");
//				int  rankNumInTop =selfRankValues==null?0:StringUtil.toInt( selfRankValues[0]);
				int  rankNumInTop = getService.getPlayerFightNumRankInTop(playerId, "0");
				//top
				out.write(BinaryUtil.toByta(rankNumInTop));
				//fightnum
				out.write(BinaryUtil.toByta(player.getMaxFightNum()));
				//win rate
				float winRate=0;
				if(player.getGWin()!=0 || player.getGLose()!=0){
					winRate=CommonUtil.toTwoFloat((float)player.getGWin()/(player.getGWin()+player.getGLose()));
				}
				out.write(BinaryUtil.toByta(winRate));
				//team
				Team team=getService.getTeamByPlayerId(playerId);
				out.write(BinaryUtil.toByta(team==null?0:team.getId()));
				out.write(BinaryUtil.toByta(team==null?"":team.getName()));
				out.write(BinaryUtil.toByta(team==null?1:team.getLevel()));
				out.write(BinaryUtil.toByta(ConfigurationUtil.GM_USER_IDS.contains(String.valueOf(player.getId()))?1:0));
				//zlm2015-10-9-匹配-开始 输出p值
				String qw_s = nosqlService.getNosql().get(Constants.QW_SUM_KEY_PREFIX+ playerId);
				int k_value=0;
				if (qw_s==null) {
					k_value=CommonUtil.get_Pvalue(player);
				} else {
					int scoce =Integer.valueOf(qw_s.split("\\|")[0]);
					if(scoce>=1800)
						k_value=120;
				}
				out.write(BinaryUtil.toByta(k_value));   
				//zlm2015-10-9-匹配-结束		
				if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
					ServiceLocator.nosqlService.addXunleiLog("1.1"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
							+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER+player.getRank()
							+Constants.XUNLEI_LOG_DELIMITER+CommonUtil.simpleDateFormat.format(new Date()));
				}
				{
					infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
							LogUtils.JoinerByTab.join("2.1",playerId,player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,ip,playerId,player.getName(),player.getRank()));
				
					String message=String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s", playerId,player.getName(),player.getUserName(),player.getRank(),ip, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(player.getCreateTime()),new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
					transferDataToDc.addLog("bjLoginIn", message);
				
				}
	
			}
			
			return out.toByteArray();
		}catch (BaseException e) {
			out.write(BinaryUtil.toByta(uid));
			out.write(BinaryUtil.toByta(-1));
			out.write(BinaryUtil.toByta(e.getMessage()));
			return out.toByteArray();
		}
		catch (Exception e) {
			LOG.warn("Error in CharacterOnline: ", e);
			throw e;
		}
	}
	@Override
	protected String getLockKey(BinaryChannelBuffer in) throws Exception {
		in.readInt();
		String name=in.readString().trim();
		Integer playerId = getService.getPlayerIdByUserId(name);
		if(playerId==null){
			return null;
		}else{
			return CommonUtil.getLockKey(playerId);
		}
		
	}
}
