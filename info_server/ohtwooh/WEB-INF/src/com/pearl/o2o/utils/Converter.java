package com.pearl.o2o.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.lilystudio.smarty4j.Context;


import com.pearl.o2o.manager.SmartyManager;
import com.pearl.o2o.pojo.*;
import com.pearl.o2o.pojo.Character;

public class Converter {
	private static final Logger log = Logger.getLogger(Converter.class);
	
	public static String error(String msg){
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("msg", msg);					
			return sm.getEncodedBody("Error.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;			
	}
	
	public static String createPlayer(Integer id,String erroMsg){
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("id", id);	
			ctx.set("erroMsg", erroMsg);	
			return sm.getEncodedBody("CreatePlayer.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;		
	}	
	
	public static String player(Player player){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("player", player);					
			return sm.getEncodedBody("Player.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerInfo(Player player){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("player", player);					
			return sm.getEncodedBody("PlayerInfo.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	
	public static String playerAvatar(Player player, PlayerItem playerItem){		
		String value = "";
		String[] cosT = player.getResourceT().split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] cosP = player.getResourceP().split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] wpStable = new String[1];
		ArrayList<String> wpChange = null;
		
		if(playerItem != null){
			wpStable = playerItem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
			wpChange = playerItem.getResources();
		} 
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("player", player);
			ctx.set("playerItem", playerItem);		
			ctx.set("cosT", player.getCostumeT());
			ctx.set("cosP", player.getCostumeP());
			ctx.set("cos0", player.getCostume0());
			ctx.set("cos1", player.getCostume1());
			ctx.set("wpStable", wpStable);
			ctx.set("wpChange", wpChange);			
			return sm.getEncodedBody("PlayerAvatar.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}		
	
	
	public static String playerList(List<Player> players){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("players", players);					
			return sm.getEncodedBody("PlayerList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerPageList(Integer pages, List<Player> players){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();	
			ctx.set("pages", pages);
			ctx.set("players", players);					
			return sm.getEncodedBody("PlayerPageList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	public static String friendList(List<Friend> friends){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("friends", friends);					
			return sm.getEncodedBody("FriendList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	
	public static String channelList(List<Channel> channel){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("channelList", channel);					
			return sm.getEncodedBody("ChannelList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String serverList(List<Server> servers){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("serverList", servers);					
			return sm.getEncodedBody("ServerList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String rankList(List<Rank> ranks){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();				
			ctx.set("rankList", ranks);					
			return sm.getEncodedBody("RankList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItemList(int page,int pages,List<SysItem> list, int newGP, int newCR,int rank){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);					
			ctx.set("gp", newGP);
			ctx.set("cr", newCR);
			ctx.set("rank", rank);
			return sm.getEncodedBody("SysItemList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItemTooltip(SysItem sysItem,String prices[], List<SysItem> packages){		
		String value = "";
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("sysItem", sysItem);
			ctx.set("prices", prices);
			ctx.set("packages", packages);
			value= sm.getEncodedBody("SysItemTooltip.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItem(SysItem sysItem){		
		String value = "";
		String[] wpStable = new String[1];
		String[] wpChange = new String[1];
		
		if(sysItem != null){
			wpStable = sysItem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
			wpChange = sysItem.getResourceChangeable().split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		} 
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("sysItem", sysItem);
		
			ctx.set("wpStable", wpStable);
			ctx.set("wpChange", wpChange);		
			return sm.getEncodedBody("SysItem.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerItemList(int page,int pages,List<PlayerItem> list,int rank){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("rank", rank);
			return sm.getEncodedBody("PlayerItemList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerPartList(int page,int pages,List<PlayerItem> list){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);					
			return sm.getEncodedBody("PlayerPartList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String weaponPackList(int page,int pages,List<PlayerItem> list,int rank){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("rank", rank);
			return sm.getEncodedBody("WeaponPackList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String avatarPackList(int page,int pages,List<PlayerItem> list,int rank){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("rank", rank);
			return sm.getEncodedBody("AvatarPackList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerItem(PlayerItem playerItem){		
		String value = "";
		String[] wpStable = new String[12];
		ArrayList<String> wpChange =new ArrayList<String>();
		if(playerItem != null){
			wpStable = playerItem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
			wpChange = playerItem.getResources();
		}
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("playerItem", playerItem);
			ctx.set("wpStable", wpStable);
			ctx.set("wpChange", wpChange);
			return sm.getEncodedBody("PlayerItem.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerPackList(List<PlayerItem> list){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("PlayerPack.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createPackEquipment(String erroMsg){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CreatePackEquipment.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String deletePackEquipment(String erroMsg){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("DeletePackEquip.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createPlayerItem(int permit,int newGP,int newCR,int id,  String erroMsg){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("permit", permit);
			ctx.set("playerItemId", id);
			ctx.set("gp", newGP);
			ctx.set("cr", newCR);
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CreatePlayerItem.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createMessage(int permit, String erroMsg, int newGP, int newCR){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("permit", permit);
			ctx.set("erroMsg", erroMsg);
			ctx.set("gp", newGP);
			ctx.set("cr", newCR);
			return sm.getEncodedBody("CreateMessage.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String createMessage(int permit, String erroMsg){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("permit", permit);
			ctx.set("erroMsg", erroMsg);
			ctx.set("gp", 0);
			ctx.set("cr", 0);
			return sm.getEncodedBody("CreateMessage.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		return value;
	}
	
	
	
	public static String messageList(List<Message> list){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("messageList", list);
			return sm.getEncodedBody("MessageList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String message(Message message){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("message", message);
			return sm.getEncodedBody("Message.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String characters(List<Character> characterlist){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("characterList", characterlist);
			return sm.getEncodedBody("CharacterList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String previewItemMode(PlayerItem playerItem){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("theItem", playerItem);
			return sm.getEncodedBody("PreviewItemMode.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String commonFeedback(String erroMsg){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CommonFeedback.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String commonTeamError(String erroMsg,String data){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			ctx.set("data", data);
			return sm.getEncodedBody("CommonTeamError.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String creatTeam(String nameStr,String declarationStr,String descriptionStr,String boardStr){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("nameStr", nameStr);
			ctx.set("declarationStr", declarationStr);
			ctx.set("descriptionStr", descriptionStr);
			ctx.set("boardStr", boardStr);
			return sm.getEncodedBody("CreateTeam.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String team(Team team){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("team", team);
			if (team != null){
				ctx.set("createTime", team.getCreateTime().getTime()/1000);
				ctx.set("currentMembers", team.getMemberList().size());
			}
			return sm.getEncodedBody("Team.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String myTeam(Team team, PlayerTeam pt){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			
			ctx.set("team", team);
			ctx.set("member", pt);
			if (team != null){
				ctx.set("createTime", team.getCreateTime().getTime()/1000);
				ctx.set("currentMembers", team.getMemberList().size());
			}
			return sm.getEncodedBody("MyTeam.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String teamList(List<Team> teamList,int page,int pages){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("teamList", teamList);
			ctx.set("page", page);
			ctx.set("pages", pages);
			return sm.getEncodedBody("TeamList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String teamMember(Team team){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("team", team);
			return sm.getEncodedBody("TeamMember.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		return value;
	}
	public static String teamHistory(List<TeamHistory> teamHistoryList){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("teamHistoryList", teamHistoryList);
			return sm.getEncodedBody("TeamHistory.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamAlly(Team team){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("team", team);
			return sm.getEncodedBody("TeamAlly.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamRequestList(List<PlayerTeam> list){		
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("TeamRequestList.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		return value;
	}
	
	
	
	public static String stageClear(GeneralStageClearInfo stageClear){
		String value = "";
		
		try{			
			SmartyManager sm = new SmartyManager();
			Context ctx		 = new Context();
			ctx.set("stageClear", stageClear);
			return sm.getEncodedBody("StageClear.st",  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}
	
}
