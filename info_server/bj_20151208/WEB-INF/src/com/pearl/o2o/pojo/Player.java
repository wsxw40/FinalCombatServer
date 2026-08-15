package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class Player extends BaseMappingBean<Player> {
	private static final long serialVersionUID = -3491449820340834800L;
	private String 		userName;
	private String		name;
	private Integer		teamId 			= 0;
	private	Integer		exp 			= 0;
	private	Integer		accountType 	= 0;//0 迅雷 1 游客 2 PPS 3 YY
	private	Integer		rank 			= 1;
	private Integer		characterSize 	= 3;
	private	Integer		gPoint 			= 0;
//	private	Integer		credit 			= 0;
	private Integer     voucher			= 0; 
	private String		deleted;
	private String 		profile;
	private String 		characters;
	private Date 		createTime;
	//second
	private Integer 	lastLoginTime;//秒
	private Integer 	lastLogoutTime;//秒
	private String	 	lastLoginIp;
	private String 		tutorial;
	private int isCheck;
	//General
	private Integer		gScore = 0;
	private Integer		gKill= 0;
	private Integer		gDead= 0;
	private Integer		gHeadShot= 0;
	private Integer		gKnifeKill= 0;
	private Integer		gWin= 0;
	private Integer		gLose= 0;
	private Integer		gTotal= 0;
	private Integer		gAssist= 0;
	private Integer		gHealth= 0;
	private Integer		runAway= 0;
	//Team
	private Integer		tScore= 0;
	private Integer		tKill= 0;
	private Integer		tDead= 0;
	private Integer		tHeadShot= 0;
	private Integer		tGrenadeKill= 0;
	private Integer		tKnifeKill= 0;
	
	private Integer		gControl= 0;
	private Integer		gRevenge= 0;
	private Integer		tHitPoint= 0;
	private Integer		tWin= 0;
	private Integer		tTotal= 0;
	

	//Fun
	private Integer		funGpTotal= 0;
	private Integer		funCrTotal= 0;
	private Integer		funGrenadeKill= 0;
	private Integer		funDropKill= 0;
	private Integer		funWeaponMod= 0;
	private Integer		funDead= 0;
	private int 		funGun;
	
	private List<PlayerItem> buffList;
	
	//Join from table GROUP
	private String		team;
	private int 		job;
	private String 		tLogo;
	
	
	private String 		icon = "01_r";
	private String 		selectedCharacters;
	private String 		achievement = "";
	
	
	private int online=0;
	
	private Integer 	isChange;
	
	private Integer		gRank;
	private Integer		tRank;
	private Integer		tdRank;

	private Integer		funGpRank;
	private	Integer		funCrRank;
	private Integer		funGrenadeKillRank;
	private Integer		funDropKillRank;
	private Integer		funWeaponModRank;
	private Integer 	funDeadRank;
	private Integer     isNew;
	private Integer 	top;//rownum
	private Integer 	funCount;//fun count
	
	private Integer     isVip;
	private Integer     isXunlei;
	private String     title = "";
	private Integer     gold =0;
	private Integer     silver = 0;
	private Integer     bronze = 0;
	private Integer     total = 0;
	
	private Integer     weekScore=0;
	private Integer     weekKill=0;
	private Integer     weekAssist=0;
	private Integer     weekDead=0;
	private Integer     weekWin=0;
	private Integer     weekTotal=0;
	private Integer     maxFightNum=0;
	
	private float winRate;
	private float runAwayRate;
	
	private Team		playerTeam;
	//vip 
	private long 		vipToExpire;
	private int 		vipExp;
	private int         vipRemainsTeamExp; //REMAINS_TEAM_EXP
	
	private int 		leftMinites;
	
	private String blackWhite = "0";
	private int internetCafe=0;

	//是否新手
	private Integer isChest=0;   //默认为0，是新手
	private Integer hasSecondPassword=0; //是否设置了2级密码，默认0：未设置
	private String secondPassword;
	private Integer applyResetTime;    //申请清除密码的时间
	
	private Integer lastTeamExp=0;         //上次退出战队时战队给贡献
	private Integer lastTeamExpExpireTime;	///上次退出战队的战队贡献保留时间
	private Integer lastExitTeamId=0;		//上次退出的战队ID
	
	/**玩家持有的不可用资源点数*/
	private Integer unusableResource=0;
	
	/**玩家持有的可用资源点数*/
	private Integer usableResource=0;
	
	private int pResourceBeginTime;   //个人资源转化起始时间

	private Integer vipExpGiftLevel;	//VIP 经验对应礼品级别
	
	private HashMap<String, Integer> resMap=new HashMap<String, Integer>();
	
	public String getBlackWhite() {
		return blackWhite;
	}

	public void setBlackWhite(String blackWhite) {
		this.blackWhite = blackWhite;
	}

	public Integer getLastLogoutTime() {
		return lastLogoutTime;
	}

	public void setLastLogoutTime(Integer lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}

	public String getTLogo() {
		return tLogo;
	}

	public void settLogo(String tLogo) {
		this.tLogo = tLogo;
	}

	public int getFunGun() {
		return funGun;
	}

	public void setFunGun(int funGun) {
		this.funGun = funGun;
	}
	
	//TODO remove hard code
	public float getExpIncrement() {

		if (this.getBuffList() != null) {
			for (PlayerItem pt : this.getBuffList()) {
				try {
					SysItem si = ServiceLocator.getService.getSysItemByItemId(pt.getItemId());
					if (Constants.DEFAULT_ITEM_TYPE == si.getType() && 1 == si.getIId() && 1 == si.getIBuffId()) {
						return Float.parseFloat(si.getIValue()) / 100F;
					}
				} catch (Exception e) {
					ServiceLocator.exceptionLog.warn("error happen in getExpIncrement", e);
				}
			}
		}
		return 0;
	}
	//TODO remove hard code
	public float getGpIncrement(){
		if (this.getBuffList()!=null){
			for (PlayerItem pt : this.getBuffList()) {
				try{
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pt.getItemId());
					if (Constants.DEFAULT_ITEM_TYPE == si.getType() && 1 == si.getIId() && 2 == si.getIBuffId()){
						return Float.parseFloat(si.getIValue())/100F;
					}
				}catch (Exception e) {
					ServiceLocator.exceptionLog.warn("error happen in getGpIncrement",e);
				}
			}
		}
		return 0;
	}
	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the playerTeam
	 */
	public Team getPlayerTeam() {
		return playerTeam;
	}

	/**
	 * @param playerTeam the playerTeam to set
	 */
	public void setPlayerTeam(Team playerTeam) {
		this.playerTeam = playerTeam;
	}

	/**
	 * @param logo the tLogo to set
	 */
	public void setTLogo(String logo) {
		tLogo = logo;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCharacterSize() {
		return characterSize;
	}

	public void setCharacterSize(Integer characterSize) {
		this.characterSize = characterSize;
	}

	public Integer getGPoint() {
		return gPoint;
	}

	public void setGPoint(Integer point) {
		gPoint = point;
	}

//	public Integer getCredit() {
//		return credit;
//	}
//
//	public void setCredit(Integer credit) {
//		this.credit = credit;
//	}

	public Integer getVoucher() {
		return voucher;
	}

	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Integer lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

	public Integer getGScore() {
		return gScore;
	}

	public void setGScore(Integer score) {
		gScore = score;
	}

	public Integer getGKill() {
		return gKill;
	}

	public void setGKill(Integer kill) {
		gKill = kill;
	}

	public Integer getGDead() {
		return gDead;
	}

	public void setGDead(Integer dead) {
		gDead = dead;
	}

	public Integer getGHeadShot() {
		return gHeadShot;
	}

	public void setGHeadShot(Integer headShot) {
		gHeadShot = headShot;
	}

	public Integer getGKnifeKill() {
		return gKnifeKill;
	}

	public void setGKnifeKill(Integer knifeKill) {
		gKnifeKill = knifeKill;
	}


	public Integer getGWin() {
		return gWin;
	}

	public void setGWin(Integer win) {
		gWin = win;
	}

	public Integer getGTotal() {
		return gTotal;
	}

	public void setGTotal(Integer total) {
		gTotal = total;
	}

	public Integer getTScore() {
		return tScore;
	}

	public void setTScore(Integer score) {
		tScore = score;
	}

	public Integer getTKill() {
		return tKill;
	}

	public void setTKill(Integer kill) {
		tKill = kill;
	}

	public Integer getTDead() {
		return tDead;
	}

	public void setTDead(Integer dead) {
		tDead = dead;
	}

	public Integer getTHeadShot() {
		return tHeadShot;
	}

	public void setTHeadShot(Integer headShot) {
		tHeadShot = headShot;
	}

	public Integer getTGrenadeKill() {
		return tGrenadeKill;
	}

	public void setTGrenadeKill(Integer grenadeKill) {
		tGrenadeKill = grenadeKill;
	}

	public Integer getTKnifeKill() {
		return tKnifeKill;
	}

	public void setTKnifeKill(Integer knifeKill) {
		tKnifeKill = knifeKill;
	}

	public Integer getTHitPoint() {
		return tHitPoint;
	}

	public void setTHitPoint(Integer hitPoint) {
		tHitPoint = hitPoint;
	}

	public Integer getTWin() {
		return tWin;
	}

	public void setTWin(Integer win) {
		tWin = win;
	}

	public Integer getTTotal() {
		return tTotal;
	}

	public void setTTotal(Integer total) {
		tTotal = total;
	}

	public Integer getFunGpTotal() {
		return funGpTotal;
	}

	public void setFunGpTotal(Integer funGpTotal) {
		this.funGpTotal = funGpTotal;
	}

	public Integer getFunCrTotal() {
		return funCrTotal;
	}

	public void setFunCrTotal(Integer funCrTotal) {
		this.funCrTotal = funCrTotal;
	}

	public Integer getFunGrenadeKill() {
		return funGrenadeKill;
	}

	public void setFunGrenadeKill(Integer funGrenadeKill) {
		this.funGrenadeKill = funGrenadeKill;
	}

	public Integer getFunDropKill() {
		return funDropKill;
	}

	public void setFunDropKill(Integer funDropKill) {
		this.funDropKill = funDropKill;
	}

	public Integer getFunWeaponMod() {
		return funWeaponMod;
	}

	public void setFunWeaponMod(Integer funWeaponMod) {
		this.funWeaponMod = funWeaponMod;
	}

	public Integer getFunDead() {
		return funDead;
	}

	public void setFunDead(Integer funDead) {
		this.funDead = funDead;
	}

	public List<PlayerItem> getBuffList() {
		return buffList;
	}

	public void setBuffList(List<PlayerItem> buffList) {
		this.buffList = buffList;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getGRank() {
		return gRank;
	}

	public void setGRank(Integer rank) {
		gRank = rank;
	}

	public Integer getTRank() {
		return tRank;
	}

	public void setTRank(Integer rank) {
		tRank = rank;
	}

	public Integer getTdRank() {
		return tdRank;
	}

	public void setTdRank(Integer tdRank) {
		this.tdRank = tdRank;
	}

	public Integer getFunGpRank() {
		return funGpRank;
	}

	public void setFunGpRank(Integer funGpRank) {
		this.funGpRank = funGpRank;
	}

	public Integer getFunCrRank() {
		return funCrRank;
	}

	public void setFunCrRank(Integer funCrRank) {
		this.funCrRank = funCrRank;
	}

	public Integer getFunGrenadeKillRank() {
		return funGrenadeKillRank;
	}

	public void setFunGrenadeKillRank(Integer funGrenadeKillRank) {
		this.funGrenadeKillRank = funGrenadeKillRank;
	}

	public Integer getFunDropKillRank() {
		return funDropKillRank;
	}

	public void setFunDropKillRank(Integer funDropKillRank) {
		this.funDropKillRank = funDropKillRank;
	}

	public Integer getFunWeaponModRank() {
		return funWeaponModRank;
	}

	public void setFunWeaponModRank(Integer funWeaponModRank) {
		this.funWeaponModRank = funWeaponModRank;
	}

	public Integer getFunDeadRank() {
		return funDeadRank;
	}

	public void setFunDeadRank(Integer funDeadRank) {
		this.funDeadRank = funDeadRank;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getFunCount() {
		return funCount;
	}

	public void setFunCount(Integer funCount) {
		this.funCount = funCount;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedCharacters() {
		return selectedCharacters;
	}
	public String[] getSelectedCharacterIds() {
		String[] ids=selectedCharacters.split(Constants.COMMA);
		List<String> list=new ArrayList<String>();
		
		for(int i=0;i<characterSize;i++){
			if(i<ids.length){
				list.add(ids[i]);
			}
		}
		String[] returnIds=new String[list.size()];
		for(int i=0;i<list.size();i++){
			returnIds[i]=list.get(i);
		}
		return returnIds;
	}
	public void setSelectedCharacters() {
		String selectedCharacters="";
		for(String str:getSelectedCharacterIds()){
			selectedCharacters+=str+Constants.COMMA;
		}
		this.selectedCharacters = CommonUtil.cutLastWord(selectedCharacters);
	}
	public void setSelectedCharacters(String selectedCharacters) {
		
		this.selectedCharacters = selectedCharacters;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public Integer getIsVip() {
		if(this.vipToExpire > System.currentTimeMillis() || this.vipToExpire == -1){
			this.isVip = checkVipLevel();
		} else {
			this.isVip = 0;
		}
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getSilver() {
		return silver;
	}

	public void setSilver(Integer silver) {
		this.silver = silver;
	}

	public Integer getBronze() {
		return bronze;
	}

	public void setBronze(Integer bronze) {
		this.bronze = bronze;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public float getWinRate() {
		if((gWin + gLose) == 0){
			return 0;
		}
		return (float)((gWin*1000/(gWin + gLose))/1000.0);
	}

	public void setWinRate(float winRate) {
		this.winRate = winRate;
	}

	public Integer getGAssist() {
		return gAssist;
	}

	public void setGAssist(Integer assist) {
		gAssist = assist;
	}

	public Integer getWeekScore() {
		return weekScore;
	}

	public void setWeekScore(Integer weekScore) {
		this.weekScore = weekScore;
	}

	public Integer getWeekKill() {
		return weekKill;
	}

	public void setWeekKill(Integer weekKill) {
		this.weekKill = weekKill;
	}

	public Integer getWeekAssist() {
		return weekAssist;
	}

	public void setWeekAssist(Integer weekAssist) {
		this.weekAssist = weekAssist;
	}

	public Integer getWeekDead() {
		return weekDead;
	}

	public void setWeekDead(Integer weekDead) {
		this.weekDead = weekDead;
	}

	public Integer getWeekWin() {
		return weekWin;
	}

	public void setWeekWin(Integer weekWin) {
		this.weekWin = weekWin;
	}

	public Integer getWeekTotal() {
		return weekTotal;
	}

	public void setWeekTotal(Integer weekTotal) {
		this.weekTotal = weekTotal;
	}

	public Integer getGControl() {
		return gControl;
	}

	public void setGControl(Integer control) {
		gControl = control;
	}

	public Integer getGRevenge() {
		return gRevenge;
	}

	public void setGRevenge(Integer revenge) {
		gRevenge = revenge;
	}

	public Integer getRunAway() {
		return runAway;
	}

	public void setRunAway(Integer runAway) {
		this.runAway = runAway;
	}

	public float getRunAwayRate() {
		if((int)gTotal == 0){
			return 0;
		}
		return (float)((runAway*1000/gTotal)/1000.0);
	}

	public void setRunAwayRate(float runAwayRate) {
		this.runAwayRate = runAwayRate;
	}

	public String getCharacters() {
		return characters;
	}
	public String[] getCharactersIds() {
		return characters.split(Constants.COMMA);
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getGLose() {
		return gLose;
	}

	public void setGLose(Integer lose) {
		gLose = lose;
	}

	public Integer getIsXunlei() {
		return isXunlei;
	}

	public void setIsXunlei(Integer isXunlei) {
		this.isXunlei = isXunlei;
	}

	public int getInternetCafe() {
		return internetCafe;
	}

	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}

	public Integer getGHealth() {
		return gHealth;
	}

	public void setGHealth(Integer health) {
		gHealth = health;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getMaxFightNum() throws Exception {
		if(maxFightNum==0){//如果没有获得战斗力，则重新计算
			initMaxFightNum();
		}
		return maxFightNum;
	}
	private void initMaxFightNum() throws Exception{
		List<Character> characterList= ServiceLocator.getService.getCharacterListById(id);
		double fightNum = 0;
		for(int i=0;i<characterList.size();i++){
			fightNum += (characterList.get(i).getFightNum())*Math.pow(Constants.FUNDUS_NUM, i);
		}
		this.maxFightNum = (int)fightNum;
		ServiceLocator.updateService.updatePlayerInfo(this);//更新玩家综合战斗力
	}
	public void setMaxFightNum(Integer maxFightNum) {
		this.maxFightNum = maxFightNum;
	}

	public long getVipToExpire() {
		return vipToExpire;
	}

	public void setVipToExpire(long vipToExpire) {
		this.vipToExpire = vipToExpire;
	}

	public int getLeftMinites() {
		if(vipToExpire == -1)
		{
			return -1;
		}
		else 
		{
			long leftMiliSeconds = (vipToExpire - System.currentTimeMillis());
			return leftMiliSeconds <= 0 ? 0 : (int)(leftMiliSeconds/60000);
		}
	}

	public void setLeftMinites(int leftMinites) {
		this.leftMinites = leftMinites;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getIsChest() {
		return isChest;
	}

	public void setIsChest(Integer isChest) {
		this.isChest = isChest;
	}

	public Integer getHasSecondPassword() {
		return hasSecondPassword;
	}

	public void setHasSecondPassword(Integer hasSecondPassword) {
		this.hasSecondPassword = hasSecondPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	public Integer getApplyResetTime() {
		return applyResetTime;
	}

	public void setApplyResetTime(Integer applyResetTime) {
		this.applyResetTime = applyResetTime;
	}
	
	public int getVipExp() {
		return vipExp;
	}

	public void setVipExp(int vipExp) {
		this.vipExp = vipExp;
	}
	
	public int getVipRemainsTeamExp() {
		return vipRemainsTeamExp;
	}

	public void setVipRemainsTeamExp(int vipRemainsTeamExp) {
		this.vipRemainsTeamExp = vipRemainsTeamExp;
	}

	
	public Integer getLastTeamExp() {
		return lastTeamExp;
	}

	public void setLastTeamExp(Integer lastTeamExp) {
		this.lastTeamExp = lastTeamExp;
	}

	public Integer getLastTeamExpExpireTime() {
		return lastTeamExpExpireTime;
	}

	public void setLastTeamExpExpireTime(Integer lastTeamExpExpireTime) {
		this.lastTeamExpExpireTime = lastTeamExpExpireTime;
	}

	public Integer getLastExitTeamId() {
		return lastExitTeamId;
	}

	public void setLastExitTeamId(Integer lastExitTeamId) {
		this.lastExitTeamId = lastExitTeamId;
	}

	
	
	public Integer getUnusableResource() {
		return unusableResource;
	}
	
	
	public Integer getDBUnusableResource() {
		return unusableResource;
	}	

	public void setUnusableResource(Integer unusableResource) {
		if(unusableResource<0){
			unusableResource=0;
		}
		this.unusableResource = unusableResource;
	}

	public HashMap<String, Integer> getLatestPlayerRes(int teamSpaceLevel){
		caculatePlayerRes(teamSpaceLevel);
		resMap.put(ORG_RES,getUnusableResource());
		resMap.put(RES, getUsableResource());
		return resMap;
	}
	
	//必须要及时更新pResourceBeginTime
	private void caculatePlayerRes(int teamSpaceLevel){
		Constants.TEAMSPACELEVELCONSTANTS baseTeamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(0);
		Constants.TEAMSPACELEVELCONSTANTS teamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(teamSpaceLevel);
		
		final int nowSeconds=(int)(System.currentTimeMillis()/1000);
		final int preSeconds=this.getpResourceBeginTime();
		
		int newUsableRes=this.getUsableResource();
		int totalSeconds=nowSeconds-preSeconds;
		if(totalSeconds>=Constants.TeamSpaceConstants.PERSON_ORG_RES_CONVERT_INTERVAL){
			int tempIncreaseNum=totalSeconds/Constants.TeamSpaceConstants.PERSON_ORG_RES_CONVERT_INTERVAL
					*(teamSLC.getpOrgResConvertNum()+baseTeamSLC.getpOrgResConvertNum())*Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE;
			int	remainSeconds=totalSeconds% Constants.TeamSpaceConstants.PERSON_ORG_RES_CONVERT_INTERVAL;
			
			int finalIncreaseNum=0;
			int canConvertRes=this.getUnusableResource()-Constants.TeamSpaceConstants.PERSON_BASE_NON_CONVERT_RES;
			if(canConvertRes>0){
				finalIncreaseNum=tempIncreaseNum > canConvertRes ? canConvertRes : tempIncreaseNum;
			}
			
			int finalNewUsableRes=newUsableRes+finalIncreaseNum/Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE;
			if(finalNewUsableRes<0 || finalNewUsableRes > teamSLC.getpMaxRes()){
				finalNewUsableRes=teamSLC.getpMaxRes();
			}
			this.setUsableResource(finalNewUsableRes);
			this.setUnusableResource(this.getUnusableResource()-finalIncreaseNum);
			this.setpResourceBeginTime(nowSeconds-remainSeconds);
		}
	}
	
	
	public Integer getUsableResource() {	
		return usableResource;
	}
	
	public Integer getDBUsableResource() {	
		return usableResource;
	}	
	
	public void setUsableResource(Integer usableResource) {
		usableResource= usableResource<0? 0 : usableResource;
		this.usableResource = usableResource;
	}

	public int getpResourceBeginTime() {
		return pResourceBeginTime;
	}

	public void setpResourceBeginTime(int pResourceBeginTime) {
		this.pResourceBeginTime = pResourceBeginTime;
	}

	//used just for vip player
	private int checkVipLevel(){
		int exp=this.getVipExp();
		for(int i=1;i<Constants.VIP_LEVEL_EXP.length;i++){
			if(exp<Constants.VIP_LEVEL_EXP[i]){
				return i;
			}
		}
		return Constants.VIP_LEVEL_EXP.length;
		
	}	// 续费时的vip level
	public int getRenewVipLevel(){
		if(getIsVip()==0 && getVipExp()>0){
			return checkVipLevel();
		}
		return 0;
	}
	
	public final static String ORG_RES="org_res";
	public final static String RES="res";

	//VIP经验兑换档次
	public Integer getVipExpGiftLevel() {
		if(vipExpGiftLevel==null){
			this.vipExpGiftLevel=-1;
		}
		return vipExpGiftLevel;
	}

	public void setVipExpGiftLevel(Integer vipExpGiftLevel) {
		this.vipExpGiftLevel=vipExpGiftLevel;
	}
}
