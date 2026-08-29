package com.pearl.o2o.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.CharacterOnlineInfo;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Constants.TeamSpaceConstants.FightType;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class SocketClientNew {
	private static Logger logger = LoggerFactory.getLogger(SocketClientNew.class);
	
	public  void sendWithNoReply(byte[] output, DefaultSocketHandler handler) {
		try {
			handler.getResQueue().put(output);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}
	
	public  void broadSendWithNoReply(byte[] output){
		for (DefaultSocketHandler handler : ConfigurationUtil.CHANNEL_HANDLERS.getChannels()) {
			sendWithNoReply(output,handler);
		}
	}
	
	public  byte[] send(Request request, DefaultSocketHandler handler) throws IOException, TimeoutException{
		//generate rpcId
		int rpcId = handler.generateId();
		request.setRpcId(rpcId);
		ConcurrentHashMap<Integer, Task<byte[]>> clientBinaryResponseQueue = handler.getClientBinaryResponseQueue();
		byte[] output = request.generateByte();
		Task<byte[]> task = new Task<byte[]>();
		// rpcId will be unique
		clientBinaryResponseQueue.put(rpcId, task);
		try {
			sendWithNoReply(output,handler);
			logger.debug("finsih invoking send request to channel, rpcId:" + rpcId);
			// wait for response, will be time out
			byte[] response = task.await(ConfigurationUtil.TIMEOUT_CLIENT);
			return response;  
		} finally{
			clientBinaryResponseQueue.remove(rpcId);
		}
	}
	
	public  void chat(String receiver, String to, String name, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_SendChat.ordinal());
		out.write(BinaryUtil.toByta(receiver));
		out.write(BinaryUtil.toByta(to));
		out.write(BinaryUtil.toByta(name));
		out.write(BinaryUtil.toByta(msg));	
		
		broadSendWithNoReply(out.toByteArray());
	}
	
	
	public  void proxyBroadCast(String to, String name, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_BroadcastProxyChat.ordinal());
		out.write(BinaryUtil.toByta(to));
		out.write(BinaryUtil.toByta(name));
		out.write(BinaryUtil.toByta(msg));
		
		broadSendWithNoReply(out.toByteArray());
	}
	
	
	public  void serverBroadCast(int serverId, String to, String name, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_BroadcastServerChat.ordinal());
		out.write(BinaryUtil.toByta(serverId));
		out.write(BinaryUtil.toByta(to));
		out.write(BinaryUtil.toByta(name));
		out.write(BinaryUtil.toByta(msg));
		
		broadSendWithNoReply(out.toByteArray());
	}
	
	
	public  void channelBroadCast(int serverId, int channelId, String to, String name, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		out.write((byte)ServerMessage.SM_BroadcastChannelChat.ordinal());
		out.write(BinaryUtil.toByta(serverId));
		out.write(BinaryUtil.toByta(channelId));
		out.write(BinaryUtil.toByta(to));
		out.write(BinaryUtil.toByta(name));
		out.write(BinaryUtil.toByta(msg));
		
		broadSendWithNoReply(out.toByteArray());
	}
	
	public  void puchCMDtoClient(String receiver, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_NotifyClient.ordinal());
		out.write(BinaryUtil.toByta(receiver));
		out.write(BinaryUtil.toByta(msg));
		broadSendWithNoReply(out.toByteArray());
	}
	
	public  void sendAllServer(Server server, String to, String name, String msg) throws Exception{
		List<Channel> channels = ServiceLocator.getService.getChannelsList(server.getId());
		for (Channel channel : channels) {
			channelBroadCast(server.getId(), channel.getId(), to, name, msg);
		}
	}
	
	
	public  void messageXLB(int serverId, String msg, String name) throws IOException{
		serverBroadCast(serverId,Constants.MSG_XLB, name, msg);
	}
	
	public  void messageDLB(String msg, String name) throws Exception{
		proxyBroadCast(Constants.MSG_DLB, name, msg );
	}
	
	public  void messageInfo(String receiver, String msg) throws IOException{
		chat(receiver, Constants.MSG_INFO,"", msg);
	}
	public  void groupInfo(String receiver, String msg) throws IOException{
		chat(receiver, Constants.MSG_GROUP,"", msg);
	}
	public  void messageOnline(String receiver, String msg) throws IOException{
		chat(receiver, Constants.MSG_ONLINE,"", msg);
	}
	
	public  void messageCMD(String receiver, String msg) throws IOException{
		puchCMDtoClient(receiver,msg);
	}
	
	//push to all client in the server
	public  void messageCMD(String msg) throws IOException{
		puchCMDtoClient("",msg);
	}
	
	public void messageUpdatePlayerMoney(Player player) throws Exception{
		PlayerInfo playerInfo = ServiceLocator.getService.getPlayerInfoById(player.getId());
		messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_MONEY, new Object[]{player.getGPoint().toString(),""+playerInfo.getXunleiPoint(),player.getVoucher().toString(),""+player.getDBUsableResource(),""+player.getDBUnusableResource()}));
	}
	
	public void sendAchievementCompleted(List<PlayerAchievement> list,int playerId, String playerName) throws IOException{
		int fcm_time=ServiceLocator.nosqlService.getFCMTime(playerId);
//		logger.error("fcm_time"+ fcm_time);
		String cmdStr = Converter.completedAchievementList(list,fcm_time);
//		logger.debug("update achievement completed="+cmdStr);
		puchCMDtoClient(playerName, cmdStr);
	}
	
	
	/**
	 * 发送资源争夺战邀请
	 * @throws IOException 
	 */
	public void sendBattleFieldInvite(Player receiver, Player inviter,int serverid,int channelid,int roomid,String roompwd,FightType fightType,int player_Res_ratio_status) throws IOException{
		String message=Converter.getBattleFiledInvitation(receiver, inviter, serverid, channelid, roomid, roompwd,fightType,player_Res_ratio_status);
		puchCMDtoClient(receiver.getName(), message);
	}
	
	/**
	 * 发送战场信息给进攻方队员
	 * 
	 * @param sPlayerList
	 * @param defenceTeamId
	 * @param canRobRes
	 * @throws Exception
	 */
	public void sendBattleFieldInfoToAttactMembers(String sPNames,
			int defenceTeamId, String sCanRobRes, String config,FightType fightType,GetService getService)
			throws Exception {
		Team defenceTeam = getService.getTeamById(defenceTeamId);
		int canRobRes = StringUtil.toInt(sCanRobRes);
		if (defenceTeam != null) {
			String[] sPnameArray = sPNames.split("-");
			if (sPnameArray.length > 0) {
				for (String sPName : sPnameArray) {
						Player receiver = getService.getPlayerByName(sPName);
						if (receiver != null) {
							sendBattleFieldInfoToAttactMember(
									receiver, defenceTeam, canRobRes, config,fightType);
					}
				}
			}
		}
	}	
	
	/**
	 * 推送给进攻方战斗成员 防守方的 资源争夺战地图信息
	 * @throws IOException 
	 */
	private void sendBattleFieldInfoToAttactMember(Player receiver,Team defenceTeam,int canRobRes,String config,FightType fightType) throws IOException{
		String message=Converter.getBattleFiledInfoToAttactMember(receiver, defenceTeam, canRobRes,config,fightType);
		puchCMDtoClient(receiver.getName(), message);
	}	
	
	/**
	 * 发送资源争夺战防御邀请
	 * @throws IOException 
	 */
	public void sendBattleFieldDefenseInvite(Team attackTeam,Player receiver, Team defenseTeam,int serverid,int channelid,int roomid,String roompwd,FightType fightType,String config ,int canRobRes,int teamSpaceLevel,int atknum) throws IOException{
		String message=Converter.getBattleFiledDefenseInvitation(attackTeam,receiver, defenseTeam, serverid, channelid, roomid, roompwd,fightType,config,canRobRes,teamSpaceLevel,atknum);
		puchCMDtoClient(receiver.getName(), message);
	}	
	
	public void kickPlayer(int playerId,String type)throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_KickPlayer.ordinal());
		out.write(BinaryUtil.toByta(playerId));
		out.write(BinaryUtil.toByta(type));
		broadSendWithNoReply(out.toByteArray());
	}
	public void updateBillBoardList(String str) throws TimeoutException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		out.write((byte)ServerMessage.SM_UpdateBillBoardList.ordinal());
		out.write(BinaryUtil.toByta(str));
		broadSendWithNoReply(out.toByteArray());
	}
	/**
	 * this method shall be called everytime the gp/cr/level/team/exp was changed 
	 * @param player
	 * @param newTeamName
	 * @param newLevel
	 * @throws Exception 
	 * @throws TimeoutException 
	 * @throws IOExceptions
	 */
	public void updateCharacterInfo(Player player, String newTeamName, int newLevel) throws TimeoutException, Exception{
		boolean isPlayerCheckToday =ServiceLocator.nosqlService.isPlayerCheck(player.getId(),  CommonUtil.dateFormatDate.format(new Date()));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		out.write((byte)ServerMessage.SM_UpdateCharacterInfo.ordinal());
		out.write(BinaryUtil.toByta(player.getName()));
		
		out.write(BinaryUtil.toByta(player.getId()));
		out.write(BinaryUtil.toByta(newLevel));
		out.write(BinaryUtil.toByta(player.getExp()));
		out.write(BinaryUtil.toByta(player.getIsVip().byteValue()));
		List<PlayerItem> buffList=player.getBuffList();
		int flag=0;
//		logger.fatal("updateCharacterInfo buffList.size()="+buffList.size()+" player="+player.getId());
		if(buffList!=null){
			for(PlayerItem pi:buffList){
				try{
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(Constants.DEFAULT_ITEM_TYPE == si.getType() && pi.getId()!=0&&si.getIId()==1&&si.getIBuffId()==Constants.DEFAULT_CARD_BUFF_ID){
						flag=StringUtil.toInt(si.getIValue());
						break;
					}
				}catch (Exception e) {
					logger.warn("happen in updateCharacterInfo",e);
				}
			}
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
		
		int  rankNumInTop = ServiceLocator.getService.getPlayerFightNumRankInTop(player.getId(), String.valueOf(Constants.CHARACTER_IDS[0]));
//		String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(player.getId(), "kCommonTop");
//		int  rankNumInTop =StringUtil.toInt( ServiceLocator.nosqlService.getNosql().get(rankKey));
//		String[] selfRankValues =ServiceLocator.nosqlService.getNosql().get(rankKey)==null?null:ServiceLocator.nosqlService.getNosql().get(rankKey).split(":");
//		int  rankNumInTop =selfRankValues==null?0:StringUtil.toInt( selfRankValues[0]);
		//top
		out.write(BinaryUtil.toByta(rankNumInTop));
		//fightnum
		out.write(BinaryUtil.toByta(player.getMaxFightNum()));
		//win rate
		float winRate=0;
		if(player.getGWin()!=0 || player.getGLose()!=0){
			winRate=CommonUtil.toFourFloat((float)player.getGWin()/(player.getGWin()+player.getGLose()));
		}
		out.write(BinaryUtil.toByta(winRate));
	
		//team
		if (newTeamName == null) {
			newTeamName = "";
		}
		Team team=ServiceLocator.getService.getTeamByPlayerId(player.getId());
		out.write(BinaryUtil.toByta(team==null?0:team.getId()));
		out.write(BinaryUtil.toByta(team==null?"":team.getName()));
		out.write(BinaryUtil.toByta(team==null?1:team.getLevel()));
		//zlm2015-10-9-匹配-开始  输出p值
		out.write(BinaryUtil.toByta(CommonUtil.get_Pvalue(player)));
		//zlm2015-10-9-匹配-结束
		broadSendWithNoReply(out.toByteArray());
	}
	
	public CharacterOnlineInfo getCharacterOnlineInfo() throws IOException, TimeoutException{
		//generate request
		Request request = new Request();
		request.setType((byte)ServerMessage.SM_RequestOnlineInfo.ordinal());
		request.setUrl("");
		request.setUserData(Constants.EMPTY_BYTE_ARRAY);
		//send 
		//pick a random one
		DefaultSocketHandler handler = ConfigurationUtil.CHANNEL_HANDLERS.getChannels().get(0);
		byte[] result = send(request, handler);
		//wrap the result
		BinaryChannelBuffer in = new BinaryChannelBuffer(result);
		
		int activeConnections = in.readInt();
		int online_character = in.readInt();
		
		int channelId = -1;
		int serverId = -1;
		int client_count = -1;
		
		
		CharacterOnlineInfo clientInfo  = new CharacterOnlineInfo();
		clientInfo.setActiveConnections(activeConnections);
		clientInfo.setOnlineCharacter(online_character);
		
		
		while(channelId !=0) {
			serverId = in.readInt();
			channelId = in.readInt();
			client_count = in.readInt();
			if (channelId !=0) {
				clientInfo.addCount(serverId, channelId, client_count);
			}
		}
		
		return clientInfo;
	}
	public void refreashKeyWords(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_RequestKeywords.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	public void refreashServerList(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_RequestServerList.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	public void refreashLevelList(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_RequestLevelList.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	public void refreashGMNotice(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_RequestNotice.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	
	public void refreashUnSpeakerList(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_RequestBlackList.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	
	public void refreashSysConfig(){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_SysConfig.ordinal());
		broadSendWithNoReply(out.toByteArray());
	}
	public void pushExpiredPackList(String playerName,List<PlayerItem> list) throws IOException{
	
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_ExpiredPack.ordinal());
		out.write(BinaryUtil.toByta(playerName));
		out.write(BinaryUtil.toByta(list.size()));
		for(PlayerItem pi:list){
			try {
				SysItem si = ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
				out.write(BinaryUtil.toByta(si.getDisplayName()));
			} catch (Exception e) {
				out.write(BinaryUtil.toByta(""));
				logger.warn("happen in pushExpiredPackList",e);
			}
		}
		broadSendWithNoReply(out.toByteArray());
	}
	
	public void refreashPlayerExpAndLevel(Player player) throws  Exception{
		puchCMDtoClient(player.getName(), CommonUtil
				.messageFormat(CommonMsg.REFRESH_EXP,
						player.getExp(), String.valueOf(player.getRank())));		//推送一个命令给客户端，当前表示 刷新经验和等级
	}

}
