package com.pearl.o2o.service.flexservice;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BannedWord;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.GmGroup;
import com.pearl.o2o.pojo.GmPrivilege;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.StrengthenAppend;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysBioCharacter;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysCharacterLog;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;
import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.PasswordUtil;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;



public class FlexCreateService extends CreateService{
	static Logger logger = LoggerFactory.getLogger("FlexCreateService");
	
	public void presentToPlayer(GmUser gmUser, List<SysItem> selects,String  userName,int playerId,int unit,int unitType)throws Exception {
		final Player player=getService.getPlayerById(playerId);
		StringBuilder names = new StringBuilder();
		StringBuilder ids = new StringBuilder();
		StringBuilder units = new StringBuilder();
		StringBuilder unitTypes = new StringBuilder();
		
		String content=CommonMsg.PRESENT_TO_PLAYER;
		for(SysItem item:selects){
			SysItem si = getService.getSysItemByItemId(item.getId());
			if(si.getType() == Constants.DEFAULT_PACKAGE_TYPE){//大礼包
				super.packageToPlayer(player, si, new Payment(unit,unitType), ids, Constants.BOOLEAN_NO);
				ids.append(si.getId()).append(",");
			} else {
				Payment payment=new Payment();
				payment.setUnit(unit);
				payment.setUnitType(unitType);
				//super.awardToPlayer(player, si, payment, ids, Constants.BOOLEAN_YES);
				sendItem(si, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				ids.append(si.getId()).append(",");
			}
			units.append(unit).append(",");
			unitTypes.append(unitType).append(",");
			names.append(si.getDisplayName()).append(",");
//			if(unitType==0)
//				names.append(si.getDisplayName()).append("x").append("永久").append("、");
//			else if(unitType==1)
//				names.append(si.getDisplayName()).append("x").append(unit).append("个").append("、");
//			else if(unitType==2)
//				names.append(si.getDisplayName()).append("x").append(unit).append("天").append("、");
		}
		
//		logger.info("GM("+gmUser.getUserName()+") send gift to player="+playerId +" itemId=" + ids.toString() + " units="+units);
		Object[] obj = {CommonUtil.cutLastWord(names.toString()), CommonUtil.dateFormat.format(new Date())}; 
		content = CommonUtil.messageFormatI18N(content, obj);
//		ServiceLocator.createService.sendSystemMail(player, CommonMsg.GIFT_EMAIL_SYS, content, ids.toString());
		ServiceLocator.createService.sendSystemMail(player, CommonMsg.GIFT_EMAIL_SYS, content, ids.toString(),units.toString(),unitTypes.toString());
	}
	
	public void presentToPlayers(GmUser gmUser, List<SysItem> selects,List<PlayerVO> players,int unit,int unitType)throws Exception {
		for(PlayerVO p:players){
			presentToPlayer(gmUser, selects, p.getUserName(), p.getId(),unit,unitType);
		}
	}	
	
	
	public void createSysConfig(GmUser gmUser, String key,String value)throws Exception{
		SysConfiguration sc=new SysConfiguration();
		sc.setKey(key);
		sc.setValue(value);
		systemDao.createSysConfig(sc);
		mcc.delete(CacheUtil.oSysConfigMap());
	}	
	
	
	//===============================================================================	
	//								  Character Services
	//===============================================================================

	public void createSysCharacter(GmUser gmUser, SysCharacter sysCharacter)throws Exception{
		mcc.delete(CacheUtil.oSysCharacterList());
		sysCharacter=sysCharacterDao.createSysCharacter(sysCharacter);
		SysCharacterLog characterLog=new SysCharacterLog();
		characterLog.setSysCharacter(sysCharacter);
		characterLog.setLogVersion(0);
		sysCharacterLogDao.createSysCharacterLog(characterLog);
	}
	public void createSysBioCharacter(GmUser gmUser, SysBioCharacter sysBioCharacter)throws Exception{
		mcc.delete(CacheUtil.oSysBioCharacterList());
		sysBioCharacterDao.createSysCharacter(sysBioCharacter);
	}
	
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================	
	public void createServer(GmUser gmUser, Server server)throws Exception{
		mcc.delete(CacheUtil.oServerList());
		serverDao.createServer(server);
		soClient.refreashServerList();
	}
	//===============================================================================	
	//								  Channel Services
	//===============================================================================	
	public void createChannel(GmUser gmUser, Channel channel)throws Exception{
		mcc.delete(CacheUtil.oChannelList(channel.getServerId()));
		serverDao.createChannel(channel);
		soClient.refreashServerList();
	}	
	//===============================================================================	
	//								  Level  Services
	//===============================================================================
	public void createLevelInfo(GmUser gmUser, LevelInfo info)throws Exception{
		try{
			sysLevelDao.insertLevelInfo(info);
			mcc.delete(CacheUtil.oLevelList());
			soClient.refreashLevelList();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void createLevelWeapon(GmUser gmUser, LevelWeapon levelWeapon)throws Exception{
		levelWeapon.setId(0);
		sysLevelDao.insertLevelWeapon(levelWeapon);
	}	
	
	
	//===============================================================================	
	//								  System Item Services
	//===============================================================================

	public void createSysItem(GmUser gmUser, SysItem sysItem)throws Exception{
		try{
//			String[] characterids=sysItem.getCharacterIds();
//			for(String id:characterids){
//				mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//				mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//			}
			
			if(sysItem.getType().equals(Constants.DEFAULT_PACKAGE_TYPE)){//大礼包
				String items=sysItem.getItems();
				if(items!=null && !items.equals("")){
//					String item[]=items.split(",");
//					for(int i=0;i<item.length;i++){
//						sysItemDao.createSysPackage(id, StringUtil.toInt(item[i]));
//					}
					//to do create sys_package
				}else{
					throw new Exception(ExceptionMessage.NOT_PACKAGE_ITEM);
				}
			} else {
				sysItem.setItems("");
			}
			
			int id=sysItemDao.createSysItem(sysItem);
			SysItemLog sysItemLog =new SysItemLog();
			sysItemLog.setId(null);
			sysItem.setId(id);
			sysItemLog.setSysItem(sysItem);
			sysItemLog.setLogVersion(0);
			sysItemLogDao.createSysItemLog(sysItemLog);
			soClient.messageCMD(CommonUtil.messageFormat(CommonMsg.REFRESH_SHOP,null));
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public void createPaymentList(GmUser gmUser, List<Payment> paymentList) throws Exception{
		if(paymentList!=null&paymentList.size()>0){
			for(Payment payment:paymentList){
				createPayment(gmUser, payment);
			}
		}
	}
	public int createPayment(GmUser gmUser, Payment payment) throws Exception{
		SysItem sysItem=sysItemDao.getSystemItemById(payment.getItemId());
//		String[] characterids=sysItem.getCharacterIds();
//		for(String id:characterids){
//			mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//			mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//		}
		int id = paymentDao.insertPayment(payment);
		mcc.delete(CacheUtil.oPaymentList(payment.getItemId()));
		ServiceLocator.getService.joinPayment(sysItem);
		return id;
	}
	
	//===============================================================================	
	//								  System Item Log Services
	//===============================================================================
	
	public void createSysItemLog(SysItemLog sysItemLog)throws Exception{
		sysItemLogDao.createSysItemLog(sysItemLog);
	}
	//===============================================================================	
	//								  System Notice
	//===============================================================================
	public int createSysNotice(GmUser gmUser, SysNotice sysNotice)throws Exception{
		//FIXME
		if(sysNotice.getContent().length()>60){
			return 1;
		}
		else {
			mcc.delete(CacheUtil.oSysNoticeList());
			//sysNotice.setContent(KeywordFilterUtil.filter(sysNotice.getContent()));
			sysNoticeDao.create(sysNotice);
			soClient.refreashGMNotice();
			return 0;
		}
	}	
	//===============================================================================	
	//								 GM notice
	//===============================================================================
	public void createGMNoticeNow(GmUser gmUser, int serverId, int type,String msg)throws Exception{
		switch (type) {
		case 1:
			soClient.serverBroadCast(serverId,Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, msg);
			break;
		case 2:
			soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, msg);
			break;
		case 3:
				
			break;
		default:
			break;
		}
	}
	//===============================================================================	
	//								 BlackIP Services
	//===============================================================================
	public void createBlackIP(GmUser gmUser, BlackIP blackIP)throws Exception{
		mcc.delete(CacheUtil.oBlackIpList());
		blackIP.setCreateTime((int)(new Date().getTime()/1000L));
		blackIPDao.createBlackIP(blackIP);
	}
	
	//===============================================================================	
	//								  Banned Word
	//===============================================================================
	public int createBannedWord(GmUser gmUser, BannedWord bw) throws Exception{
		int newId = ServiceLocator.createService.getBannedWordDao().createBannedWord(bw);
		mcc.delete(CacheUtil.oBannedWords());
		soClient.refreashKeyWords();
		KeywordFilterUtil.loadKeyWords();
		return newId;
	}
	//===============================================================================	
	//								  System Message
	//===============================================================================
	public void createSystemMessage(GmUser gmUser, List<PlayerVO> players, Message message) throws Exception{
		for(PlayerVO p : players){
			Player receiver = getService.getSimplePlayerById(p.getId());
			message.setContent(message.getContent().replace("\r", "\\n"));
			ServiceLocator.createService.sendSystemMail(receiver,KeywordFilterUtil.filter(StringUtil.escapeIndex(message.getSubject()))
					,KeywordFilterUtil.filter(StringUtil.escapeIndex(message.getContent())), null);
		}
	}
	
	public void kickPlayer(GmUser gu, int playerId) throws Exception{
		soClient.kickPlayer(playerId, Constants.KICK_PLAYER_TYPE.GM_KICK.getValue());
	}
	
	public GmUser createGmUser(GmUser gu, GmUser gmUser){
		String password = gmUser.getPassword();
		gmUser.setPassword(PasswordUtil.encrypt(password));
		return gmUserDao.create(gmUser);
	}	
	
	public GmGroup createGmGroup(GmUser gmUser, GmGroup group) throws Exception{
		int groupId = gmGroupDao.createGmGroup(group);
		return gmGroupDao.getGmGroupById(groupId);
	}
	
	public void createGmUserGroup(GmUser gmUser, int userId, int groupId) throws Exception{
		gmUserGroupDao.create(userId, groupId);
	}
	

	public void createGmGroupPrivilege(GmUser gmUser, int groupId, List<GmPrivilege> list) throws Exception{
		gmGroupPrivilegeDao.delete(groupId);
		for (GmPrivilege p : list){
			gmGroupPrivilegeDao.create(groupId, p.getId());
		}		
	}
	
	public void createActivity(GmUser gmUser, SysActivity activity) throws Exception{
		if(activity.getPath() == null || activity.getPath().equals("")){
			activity.setPath("lb_task_H_1");
		}
		if(activity.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
			if(activity.getTargetNum() < 5){
				activity.setTargetNum(5);
			}
		}
		activity.setDescription(activity.getName());
		sysActivityDao.insert(activity);
		if(activity.getAction()==Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue() || activity.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
			soClient.refreashSysConfig();
		}
		String key=CacheUtil.oCurrentActivityMap();
		mcc.delete(key);
		mcc.delete(key+"A");
		mcc.delete(key+"B");
	}
	
	public int createSysChest(GmUser gmUser, SysChest sysChest) throws Exception{
		try {
			SysChest newChest = sysChestDao.insert(sysChest);
			getService.flushLuckyPackagePageCache();
			return newChest.getType();
		} catch (Exception e) {
			ServiceLocator.fileLog.warn(e.getMessage(), e);
			throw e;
		}
	}
	
	public OnlineAward createOnlineAward(GmUser gmUser,OnlineAward award)throws Exception{
		OnlineAward oa = onlineAwardDao.createOnlineAward(award);
		ServiceLocator.deleteService.deleteOnlineAwardsByTypeInMemcache(oa.getType());
		return oa;
	}
	
	public void createStrengthenAppend(GmUser gmUser,StrengthenAppend stre)throws Exception{
		strengthenAppendDao.createStreApp(stre);
		getService.initStrengthAppend();
	}
}

