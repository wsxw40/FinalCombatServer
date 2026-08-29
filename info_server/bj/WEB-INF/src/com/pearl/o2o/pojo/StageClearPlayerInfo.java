package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.pojo.GeneralStageClearInfo.StageClearBox;


public class StageClearPlayerInfo implements Comparable<StageClearPlayerInfo>{
	private int id;//playerId
	private String name;
	private int side;
	private int onlineMinutes;
	private int offlineMinutes;
	private int isWiner;
	private int isVip;
	private int internetCafe=0;
	private String headGif;
	//below value will be set in server side
	private int currExp;
	private int currRank;
	private int currGp;
	private int currVipExp;
	
	private int rank;//currented rank
	private int exp;  // exp increment
	private int gp;// gp increment
	private int vipExp;
	
	private int passLevel;//Boss2 通过boss数
	
	private int score;
	private int kill;			
	private int dead;	
	private int health;
	private int controlNum;
	private int revengeNum;
	private int assistNum;
	private int knifeKill;	
	private int maxHeadshotNum;
	private int maxHeadshotCharacter;
	private int maxKillNum;
	private int maxKillNumCharacter;
	private int maxHealthNum;
	private int maxHealthNumCharacter;
	private int maxDamageNum;//最大伤害量
	private int maxDamageNumCharacter;
	private int maxLiveTime;
	private int maxLiveTimeCharacter;
	private int bottleHpNum;//吃血包
	private int bulletNum;//吃弹药包
	private int useTotal=0;
	
	private Date start;
	private Date end;
	private int isTeamAdd;
	private int isFirstKill;
	private int isFirstDead;
	private int[] killCharacter=new int[10];
	private List<SingleCharacterInfo> characterInfoList=new ArrayList<SingleCharacterInfo>();
	private List<StageClearBox>  stageClearBoxList;
	private List<PlayerAchievement>  paList;
	private List<PlayerItem> buffList;
	private List<OnlineAward> boss2Awards;//Boss2 奖励
	private List<OnlineAward> brands;//牌子
	private int  playerTeamExp ;//玩家当次战役获得的战队经验
	private int  playerContribution;//玩家档次战役对战队的贡献值
	private int costReliveCoin;//消耗的复活币数量
	private GetExps getExps;
	private GetGps getGps;
	private GetChances getChances;
	
	private int currRobRes;	//当前资源数
	private int earnRobRes;	//获取资源数
	private int captainAddRes=0;	//队长加成,默认0
//	private int winnerAddRes;	//战队 胜利 加成
	private List<PlayerItem> zyzdzCostItem=new ArrayList<PlayerItem>(); //资源争夺战 道具使用情况
	private int gainMvalue;
	
	private List<OnlineAward> items=new ArrayList<OnlineAward>();//匹配额外的物品跟数目
	public List<OnlineAward> getItems() {
		return items;
	}
	public void setItems(List<OnlineAward> items) {
		this.items = items;
	}
	
	public int getPlayerTeamExp() {
		return playerTeamExp;
	}
	public void setPlayerTeamExp(int playerTeamExp) {
		this.playerTeamExp = playerTeamExp;
	}
	public int getPlayerContribution() {
		return playerContribution;
	}
	public void setPlayerContribution(int playerContribution) {
		this.playerContribution = playerContribution;
	}
	public class GetExps{
		private int gameGet;//游戏获得
		private int vipGet;//VIp获得
		private int netBarGet;//网吧获得
		private int activityGet;//活动获得
		private int itemAdd;//道具加成
		private int teamAdd;//战队buff加成
		
		
		public GetExps(int gameGet, int vipGet, int netBarGet, int activityGet,
				int itemAdd, int teamAdd) {
			super();
			this.gameGet = gameGet;
			this.vipGet = vipGet;
			this.netBarGet = netBarGet;
			this.activityGet = activityGet;
			this.itemAdd = itemAdd;
			this.teamAdd = teamAdd;
		}
		public int getGameGet() {
			return gameGet;
		}
		public void setGameGet(int gameGet) {
			this.gameGet = gameGet;
		}
		public int getVipGet() {
			return vipGet;
		}
		public void setVipGet(int vipGet) {
			this.vipGet = vipGet;
		}
		public int getNetBarGet() {
			return netBarGet;
		}
		public void setNetBarGet(int netBarGet) {
			this.netBarGet = netBarGet;
		}
		public int getActivityGet() {
			return activityGet;
		}
		public void setActivityGet(int activityGet) {
			this.activityGet = activityGet;
		}
		public int getItemAdd() {
			return itemAdd;
		}
		public void setItemAdd(int itemAdd) {
			this.itemAdd = itemAdd;
		}
		public int getTeamAdd() {
			return teamAdd;
		}
		public void setTeamAdd(int teamAdd) {
			this.teamAdd = teamAdd;
		}
		
	}
	public class GetGps{
		private int gameGet;
		private int vipGet;
		private int netBarGet;
		private int activityGet;
		private int itemAdd;
		private int teamAdd;
		
		
		public GetGps(int gameGet, int vipGet, int netBarGet, int activityGet,
				int itemAdd, int teamAdd) {
			super();
			this.gameGet = gameGet;
			this.vipGet = vipGet;
			this.netBarGet = netBarGet;
			this.activityGet = activityGet;
			this.itemAdd = itemAdd;
			this.teamAdd = teamAdd;
		}
		public int getGameGet() {
			return gameGet;
		}
		public void setGameGet(int gameGet) {
			this.gameGet = gameGet;
		}
		public int getVipGet() {
			return vipGet;
		}
		public void setVipGet(int vipGet) {
			this.vipGet = vipGet;
		}
		public int getNetBarGet() {
			return netBarGet;
		}
		public void setNetBarGet(int netBarGet) {
			this.netBarGet = netBarGet;
		}
		public int getActivityGet() {
			return activityGet;
		}
		public void setActivityGet(int activityGet) {
			this.activityGet = activityGet;
		}
		public int getItemAdd() {
			return itemAdd;
		}
		public void setItemAdd(int itemAdd) {
			this.itemAdd = itemAdd;
		}
		public int getTeamAdd() {
			return teamAdd;
		}
		public void setTeamAdd(int teamAdd) {
			this.teamAdd = teamAdd;
		}
		
	}
	public class GetChances{
		private int totalGet;
		private int gameGet;
		private int vipGet;
		private int netBarGet;
		private int activityGet;
		
		public GetChances(int gameGet, int vipGet, int netBarGet,
				int activityGet) {
			super();
			this.gameGet = gameGet;
			this.vipGet = vipGet;
			this.netBarGet = netBarGet;
			this.activityGet = activityGet;
		}
		public int getGameGet() {
			return gameGet;
		}
		public void setGameGet(int gameGet) {
			this.gameGet = gameGet;
		}
		public int getVipGet() {
			return vipGet;
		}
		public void setVipGet(int vipGet) {
			this.vipGet = vipGet;
		}
		public int getNetBarGet() {
			return netBarGet;
		}
		public void setNetBarGet(int netBarGet) {
			this.netBarGet = netBarGet;
		}
		public int getActivityGet() {
			return activityGet;
		}
		public void setActivityGet(int activityGet) {
			this.activityGet = activityGet;
		}
		public int getTotalGet() {
			totalGet = gameGet+ vipGet+ netBarGet+activityGet;
			return totalGet;
		}
		public void setTotalGet(int totalGet) {
			this.totalGet = totalGet;
		}
		
		
	}
	public class SingleCharacterInfo{
		int characterId;
		int kill;
		int dead;
		int controlNum;
		int revengeNum;
		int assistNum;
		int grenadeKill;
		int knifeKill;
		int usedCount;
		int headshot;
		int maxDamage;
		int boostKill;
		int sustainKill;
		int healthNum;
		public SingleCharacterInfo(int characterId,int kill,int dead,int controlNum,int revengeNum,int assistNum,int knifeKill,
				int used,int grenadeKill,int headshot,int maxDamage,int boostKill,int sustainKill,int healthNum) {
			this.characterId=characterId;
			this.kill=kill;
			this.dead=dead;
			this.controlNum=controlNum;
			this.revengeNum=revengeNum;
			this.assistNum=assistNum;
			this.knifeKill=knifeKill;
			this.usedCount=used;
			this.grenadeKill=grenadeKill;
			this.headshot=headshot;
			this.maxDamage=maxDamage;
			this.boostKill=boostKill;
			this.sustainKill=sustainKill;
			this.healthNum=healthNum;
		}
		public int getCharacterId() {
			return characterId;
		}
		public void setCharacterId(int characterId) {
			this.characterId = characterId;
		}
		public int getKill() {
			return kill;
		}
		public void setKill(int kill) {
			this.kill = kill;
		}
		public int getDead() {
			return dead;
		}
		public void setDead(int dead) {
			this.dead = dead;
		}
		public int getControlNum() {
			return controlNum;
		}
		public void setControlNum(int controlNum) {
			this.controlNum = controlNum;
		}
		public int getRevengeNum() {
			return revengeNum;
		}
		public void setRevengeNum(int revengeNum) {
			this.revengeNum = revengeNum;
		}
		public int getAssistNum() {
			return assistNum;
		}
		public void setAssistNum(int assistNum) {
			this.assistNum = assistNum;
		}
		public int getKnifeKill() {
			return knifeKill;
		}
		public void setKnifeKill(int knifeKill) {
			this.knifeKill = knifeKill;
		}
		public int getUsedCount() {
			return usedCount;
		}
		public void setUsedCount(int used) {
			this.usedCount = used;
		}
		public int getGrenadeKill() {
			return grenadeKill;
		}
		public void setGrenadeKill(int grenadeKill) {
			this.grenadeKill = grenadeKill;
		}
		public int getHeadshot() {
			return headshot;
		}
		public void setHeadshot(int headshot) {
			this.headshot = headshot;
		}
		public int getMaxDamage() {
			return maxDamage;
		}
		public void setMaxDamage(int maxDamage) {
			this.maxDamage = maxDamage;
		}
		public int getBoostKill() {
			return boostKill;
		}
		public void setBoostKill(int boostKill) {
			this.boostKill = boostKill;
		}
		public int getSustainKill() {
			return sustainKill;
		}
		public void setSustainKill(int sustainKill) {
			this.sustainKill = sustainKill;
		}
		public int getHealthNum() {
			return healthNum;
		}
		public void setHealthNum(int healthNum) {
			this.healthNum = healthNum;
		}
	}
	//this flag indicate whether this playerStageInfo needs be persist to db
	//eg. in stagequit, only the player who quit needs be persisted; and all observers will never be persisted
	private boolean needUpdate = false;
	
	public void addSingleCharacterInfo(int characterId,int kill,int dead,int controlNum,int revengeNum,int assistNum,
			int knifeKill,int used,int grenadeKill,int headshot,int maxDamage,int boostKill,int sustainKill,int healthNum){
		SingleCharacterInfo sInfo=new SingleCharacterInfo(characterId,kill,dead,controlNum,revengeNum,assistNum,
				knifeKill,used,grenadeKill,headshot,maxDamage,boostKill,sustainKill,healthNum);
		characterInfoList.add(sInfo);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	
	public int getOnlineMinutes() {
		return onlineMinutes;
	}
	public void setOnlineMinutes(int onlineMinutes) {
		this.onlineMinutes = onlineMinutes;
	}
	public int getOfflineMinutes() {
		return offlineMinutes;
	}
	public void setOfflineMinutes(int offlineMinutes) {
		this.offlineMinutes = offlineMinutes;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCurrExp() {
		return currExp;
	}
	public void setCurrExp(int currExp) {
		this.currExp = currExp;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getGp() {
		return gp;
	}
	public void setGp(int gp) {
		this.gp = gp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public boolean isNeedUpdate() {
		return needUpdate;
	}
	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}
	
	
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	
	public int getControlNum() {
		return controlNum;
	}
	public void setControlNum(int controlNum) {
		this.controlNum = controlNum;
	}
	public int getRevengeNum() {
		return revengeNum;
	}
	public void setRevengeNum(int revengeNum) {
		this.revengeNum = revengeNum;
	}
	public int getAssistNum() {
		return assistNum;
	}
	public void setAssistNum(int assistNum) {
		this.assistNum = assistNum;
	}
	public int getKnifeKill() {
		return knifeKill;
	}
	public void setKnifeKill(int knifeKill) {
		this.knifeKill = knifeKill;
	}
	public int getMaxHeadshotNum() {
		return maxHeadshotNum;
	}
	public void setMaxHeadshotNum(int maxHeadshotNum) {
		this.maxHeadshotNum = maxHeadshotNum;
	}
	public int getMaxHeadshotCharacter() {
		return maxHeadshotCharacter;
	}
	public void setMaxHeadshotCharacter(int maxHeadshotCharacter) {
		this.maxHeadshotCharacter = maxHeadshotCharacter;
	}
	public int getMaxKillNum() {
		return maxKillNum;
	}
	public void setMaxKillNum(int maxKillNum) {
		this.maxKillNum = maxKillNum;
	}
	public int getMaxKillNumCharacter() {
		return maxKillNumCharacter;
	}
	public void setMaxKillNumCharacter(int maxKillNumCharacter) {
		this.maxKillNumCharacter = maxKillNumCharacter;
	}
	public int getMaxHealthNum() {
		return maxHealthNum;
	}
	public void setMaxHealthNum(int maxHealthNum) {
		this.maxHealthNum = maxHealthNum;
	}
	public int getMaxHealthNumCharacter() {
		return maxHealthNumCharacter;
	}
	public void setMaxHealthNumCharacter(int maxHealthNumCharacter) {
		this.maxHealthNumCharacter = maxHealthNumCharacter;
	}
	public int getMaxDamageNum() {
		return maxDamageNum;
	}
	public void setMaxDamageNum(int maxDamageNum) {
		this.maxDamageNum = maxDamageNum;
	}
	public int getMaxDamageNumCharacter() {
		return maxDamageNumCharacter;
	}
	public void setMaxDamageNumCharacter(int maxDamageNumCharacter) {
		this.maxDamageNumCharacter = maxDamageNumCharacter;
	}
	public int getMaxLiveTime() {
		return maxLiveTime;
	}
	public void setMaxLiveTime(int maxLiveTime) {
		this.maxLiveTime = maxLiveTime;
	}
	public int getMaxLiveTimeCharacter() {
		return maxLiveTimeCharacter;
	}
	public void setMaxLiveTimeCharacter(int maxLiveTimeCharacter) {
		this.maxLiveTimeCharacter = maxLiveTimeCharacter;
	}
	public List<SingleCharacterInfo> getCharacterInfoList() {
		return characterInfoList;
	}
	public void setCharacterInfoList(List<SingleCharacterInfo> characterInfoList) {
		this.characterInfoList = characterInfoList;
	}
	public int getCurrRank() {
		return currRank;
	}
	public void setCurrRank(int currRank) {
		this.currRank = currRank;
	}
	public int getCurrGp() {
		return currGp;
	}
	public void setCurrGp(int currGp) {
		this.currGp = currGp;
	}
	public int getIsWiner() {
		return isWiner;
	}
	public void setIsWiner(int isWiner) {
		this.isWiner = isWiner;
	}
	public int getUseTotal() {
		return useTotal;
	}
	public void setUseTotal(int useTotal) {
		this.useTotal = useTotal;
	}
	public List<StageClearBox> getStageClearBoxList() {
		return stageClearBoxList;
	}
	public void setStageClearBoxList(List<StageClearBox> stageClearBoxList) {
		this.stageClearBoxList = stageClearBoxList;
	}
	public int[] getKillCharacter() {
		return killCharacter;
	}
	public void setKillCharacter(int[] killCharacter) {
		this.killCharacter = killCharacter;
	}
	public int getBottleHpNum() {
		return bottleHpNum;
	}
	public void setBottleHpNum(int bottleHpNum) {
		this.bottleHpNum = bottleHpNum;
	}
	public int getBulletNum() {
		return bulletNum;
	}
	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}
	public int getIsFirstKill() {
		return isFirstKill;
	}
	public void setIsFirstKill(int isFirstKill) {
		this.isFirstKill = isFirstKill;
	}
	public int getIsFirstDead() {
		return isFirstDead;
	}
	public void setIsFirstDead(int isFirstDead) {
		this.isFirstDead = isFirstDead;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}
	public List<PlayerAchievement> getPaList() {
		return paList;
	}
	public void setPaList(List<PlayerAchievement> paList) {
		this.paList = paList;
	}
	public String getHeadGif() {
		return headGif;
	}
	public void setHeadGif(String headGif) {
		this.headGif = headGif;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public List<PlayerItem> getBuffList() {
		return buffList;
	}
	public void setBuffList(List<PlayerItem> buffList) {
		this.buffList = buffList;
	}
	public int getInternetCafe() {
		return internetCafe;
	}
	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public List<OnlineAward> getBoss2Awards() {
		return boss2Awards;
	}
	public void setBoss2Awards(List<OnlineAward> boss2Awards) {
		this.boss2Awards = boss2Awards;
	}
	public List<OnlineAward> getBrands() {
		return brands;
	}
	public void setBrands(List<OnlineAward> brands) {
		this.brands = brands;
	}
	
	
	
	public GetExps getGetExps() {
		return getExps;
	}
	public void setGetExps(GetExps getExps) {
		this.getExps = getExps;
	}
	public GetGps getGetGps() {
		return getGps;
	}
	public void setGetGps(GetGps getGps) {
		this.getGps = getGps;
	}
	public GetChances getGetChances() {
		return getChances;
	}
	public void setGetChances(GetChances getChances) {
		this.getChances = getChances;
	}
	
	public int getPassLevel() {
		return passLevel;
	}
	public void setPassLevel(int passLevel) {
		this.passLevel = passLevel;
	}
	
	public int getCostReliveCoin() {
		return costReliveCoin;
	}
	public void setCostReliveCoin(int costReliveCoin) {
		this.costReliveCoin = costReliveCoin;
	}
	public int getBoss2AwardsNum(){
		if(this.boss2Awards!=null){
			return this.boss2Awards.size();
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StageClearPlayerInfo o) {
		if(this.score==o.getScore()){
			return ((Integer)o.getKill()).compareTo(this.kill);
		}else{
			return ((Integer)o.getScore()).compareTo(this.score);
		}
	}
	public int getIsTeamAdd() {
		return isTeamAdd;
	}
	public void setIsTeamAdd(int isTeamAdd) {
		this.isTeamAdd = isTeamAdd;
	}
	public int getCurrVipExp() {
		return currVipExp;
	}
	public void setCurrVipExp(int currVipExp) {
		this.currVipExp = currVipExp;
	}
	public int getVipExp() {
		return vipExp;
	}
	public void setVipExp(int vipExp) {
		this.vipExp = vipExp;
	}
	public int getCurrRobRes() {
		return currRobRes;
	}
	public void setCurrRobRes(int currRobRes) {
		this.currRobRes = currRobRes;
	}
	public int getEarnRobRes() {
		return earnRobRes;
	}
	public void setEarnRobRes(int earnRobRes) {
		this.earnRobRes = earnRobRes;
	}
	
	public List<PlayerItem> getZyzdzCostItem() {
		return zyzdzCostItem;
	}
	public void setZyzdzCostItem(List<PlayerItem> zyzdzCostItem) {
		this.zyzdzCostItem = zyzdzCostItem;
	}
	public int getCaptainAddRes() {
		return captainAddRes;
	}
	public void setCaptainAddRes(int captainAddRes) {
		this.captainAddRes = captainAddRes;
	}
    public int getGainMvalue() {
        return gainMvalue;
    }
    public void setGainMvalue(int gainMvalue) {
        this.gainMvalue = gainMvalue;
    }
	
	
}

