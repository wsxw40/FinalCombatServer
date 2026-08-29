package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.utils.Constants;

public class GeneralStageClearInfo {
	private int rid;
	private int serverId;
	private int channelId;
	private int hostId;
	private int type;
	private Date gameStart;
	private Date gameEnd;
	private int levelId;
	private int isTeam;
	private int winner;
	private float damagePercent; // 资源争夺战中防守方 大本营损坏度
	private float totalRes; //资源争夺战中 本场比赛能抢夺的最大资源数
	private int terroristScore;
	private int policeScore;
	private int mvpCharacterId;
	private int mvpId;
	private Player player;
	private String mvpWeapon;
	private int firstKill;
	private int firstDead;
	private int onlineActivityOffset;
	private List<StageClearPlayerInfo>[] stageClearPlayerInfo;
	private List<StageClearPlayerInfo> stageClearPlayerInfoAll=new ArrayList<StageClearPlayerInfo>();//only for stage quit
	private List<StageClearPlayerInfo> stageClearPlayerInfoTeam0=new ArrayList<StageClearPlayerInfo>();
	private List<StageClearPlayerInfo> stageClearPlayerInfoTeam1=new ArrayList<StageClearPlayerInfo>();
	private List<StageClearPlayerInfo> stageClearPlayerInfoTeam2=new ArrayList<StageClearPlayerInfo>();
	private Set<RoomItem> roomItems = new HashSet<RoomItem>();
	private int teamaId;
	private int teambId;
	private int winnerRes;//胜者的资源
	private int loserRes;//输者的资源
	private List<PlayerItem> teamaCostItems;
	private List<PlayerItem> teambCostItems;
	private boolean isClear;//true: is stage clear. false : is stage quit
	
	private int isMatch;//匹配 //0为正常，6为普通匹配
	public int getIsMatch() {
		return isMatch;
	}
	public void setIsMatch(int isMatch) {
		this.isMatch = isMatch;
	}
	
	private List<SysItem> items=new ArrayList<SysItem>();//匹配要发送的物品列表，要超包了没办法才这样
	public List<SysItem> getItems() {
		return items;
	}
	public void setItems(List<SysItem> items) {
		this.items = items;
	}
	
	/** if is chanllenge/boxing book, value is the userId/teamId, and str is the name of opponent */
	public class StageClearBox{
		int type;//4 vip 3 gold 2 silver 1 bronze
		int num;
		SysItem sysItem;
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public SysItem getSysItem() {
			return sysItem;
		}
		public void setSysItem(SysItem sysItem) {
			this.sysItem = sysItem;
		}
	} 
	public class RoomItem{
		int type;
		int value;
		String target;
		String user;
		
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		public String getTarget() {
			return target;
		}
		public void setTarget(String target) {
			this.target = target;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		
		@Override
		public String toString() {
		    return ReflectionToStringBuilder.toString(this);
		}
	}
	
	public void addRoomItem(int type, int value, String target, String user){
		RoomItem ri = new RoomItem();
		ri.setType(type);
		ri.setValue(value);
		ri.setTarget(target);
		ri.setUser(user);
		roomItems.add(ri);
	}
	
	public boolean isClear() {
		return isClear;
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
	public Set<RoomItem> getRoomItems() {
		return roomItems;
	}
	public void setRoomItems(Set<RoomItem> roomItems) {
		this.roomItems = roomItems;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getTerroristScore() {
		return terroristScore;
	}

	public void setTerroristScore(int terroristScore) {
		this.terroristScore = terroristScore;
	}

	public int getPoliceScore() {
		return policeScore;
	}

	public void setPoliceScore(int policeScore) {
		this.policeScore = policeScore;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public RoomItem  getItemByType(int type){
		for (RoomItem ri : this.roomItems) {
			if (type == ri.getType() ) {
				return ri;
			}
		}
		return null;
	}
	
//	public StageClearPlayerInfo getHost(){
//		for (StageClearPlayerInfo playerInfo : stageClearPlayerInfo) {
//			if (playerInfo.getId() == hostId ){
//				return playerInfo;
//			}
//		}
//		return null;
//	}
	
//	public int getHostGameResult(){
//		StageClearPlayerInfo host = getHost();
//		if (host !=null) {
//			return getGameResultByPlayerId(host.getId());
//		}
//		return Constants.GAMERESULT_LOSE;
//	}
	
	/**
	 * -1: lose 0: draw 1:win
	 * @param playerId
	 * @return
	 */
//	public int getGameResultByPlayerId(int playerId){
//		if (winner == -1) {
//			return Constants.GAMERESULT_DRAW;
//		}
//		
//		for (StageClearPlayerInfo playerInfo : stageClearPlayerInfo) {
//			if (playerInfo.getId() == playerId) {
//				if (playerInfo.getSide() == winner) {
//					return Constants.GAMERESULT_WIN;
//				}else{
//					return Constants.GAMERESULT_LOSE;
//				}
//			}
//		}
//		return Constants.GAMERESULT_LOSE;
//	}
	
	/**
	 * -1: lose 0: draw 1:win
	 * @param playerId
	 * @return
	 */
	public int getGameResultByTeam(TeamScoreIncrement tsr){
		if (winner == -1) {
			return Constants.GAMERESULT_DRAW;
		}
		
		if (tsr.getSide() == winner) {
			return Constants.GAMERESULT_WIN;
		}else{
			return Constants.GAMERESULT_LOSE;
		}
	}
	
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getMvpWeapon() {
		return mvpWeapon;
	}

	public void setMvpWeapon(String mvpWeapon) {
		this.mvpWeapon = mvpWeapon;
	}

	public Date getGameStart() {
		return gameStart;
	}

	public void setGameStart(Date gameStart) {
		this.gameStart = gameStart;
	}

	public Date getGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(Date gameEnd) {
		this.gameEnd = gameEnd;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMvpCharacterId() {
		return mvpCharacterId;
	}

	public void setMvpCharacterId(int mvpCharacterId) {
		this.mvpCharacterId = mvpCharacterId;
	}

	public int getFirstKill() {
		return firstKill;
	}

	public void setFirstKill(int firstKill) {
		this.firstKill = firstKill;
	}

	public int getFirstDead() {
		return firstDead;
	}

	public void setFirstDead(int firstDead) {
		this.firstDead = firstDead;
	}

	public int getMvpId() {
		return mvpId;
	}

	public void setMvpId(int mvpId) {
		this.mvpId = mvpId;
	}

	public int getOnlineActivityOffset() {
		return onlineActivityOffset;
	}

	public void setOnlineActivityOffset(int onlineActivityOffset) {
		this.onlineActivityOffset = onlineActivityOffset;
	}

	public List<StageClearPlayerInfo> getStageClearPlayerInfoTeam0() {
		return stageClearPlayerInfoTeam0;
	}

	public void setStageClearPlayerInfoTeam0(
			List<StageClearPlayerInfo> stageClearPlayerInfoTeam0) {
		this.stageClearPlayerInfoTeam0 = stageClearPlayerInfoTeam0;
	}

	public List<StageClearPlayerInfo> getStageClearPlayerInfoTeam1() {
		return stageClearPlayerInfoTeam1;
	}

	public void setStageClearPlayerInfoTeam1(
			List<StageClearPlayerInfo> stageClearPlayerInfoTeam1) {
		this.stageClearPlayerInfoTeam1 = stageClearPlayerInfoTeam1;
	}

	public List<StageClearPlayerInfo> getStageClearPlayerInfoTeam2() {
		return stageClearPlayerInfoTeam2;
	}

	public void setStageClearPlayerInfoTeam2(
			List<StageClearPlayerInfo> stageClearPlayerInfoTeam2) {
		this.stageClearPlayerInfoTeam2 = stageClearPlayerInfoTeam2;
	}

	@SuppressWarnings("unchecked")
	public List<StageClearPlayerInfo>[] getStageClearPlayerInfo() {
		stageClearPlayerInfo=new ArrayList[3];
		stageClearPlayerInfo[0]=stageClearPlayerInfoTeam0;
		stageClearPlayerInfo[1]=stageClearPlayerInfoTeam1;
		stageClearPlayerInfo[2]=stageClearPlayerInfoTeam2;
		return stageClearPlayerInfo;
	}

	public List<StageClearPlayerInfo> getStageClearPlayerInfoAll() {
		return stageClearPlayerInfoAll;
	}

	public void setStageClearPlayerInfoAll(
			List<StageClearPlayerInfo> stageClearPlayerInfoAll) {
		this.stageClearPlayerInfoAll = stageClearPlayerInfoAll;
	}

	public int getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(int isTeam) {
		this.isTeam = isTeam;
	}

	public int getTeamaId() {
		return teamaId;
	}

	public void setTeamaId(int teamaId) {
		this.teamaId = teamaId;
	}

	public int getTeambId() {
		return teambId;
	}

	public void setTeambId(int teambId) {
		this.teambId = teambId;
	}

	public float getDamagePercent() {
		return damagePercent;
	}

	public void setDamagePercent(float damagePercent) {
		this.damagePercent = damagePercent;
	}

	public float getTotalRes() {
		return totalRes;
	}

	public void setTotalRes(float totalRes) {
		this.totalRes = totalRes;
	}

	public int getWinnerRes() {
		return winnerRes;
	}

	public void setWinnerRes(int winnerRes) {
		this.winnerRes = winnerRes;
	}

	public int getLoserRes() {
		return loserRes;
	}

	public void setLoserRes(int loserRes) {
		this.loserRes = loserRes;
	}

	public List<PlayerItem> getTeamaCostItems() {
		return teamaCostItems;
	}

	public void setTeamaCostItems(List<PlayerItem> teamaCostItems) {
		this.teamaCostItems = teamaCostItems;
	}

	public List<PlayerItem> getTeambCostItems() {
		return teambCostItems;
	}

	public void setTeambCostItems(List<PlayerItem> teambCostItems) {
		this.teambCostItems = teambCostItems;
	}
	
}
