package com.pearl.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.manager.utils.Constants;

public class Player extends BaseMappingBean<Player> {
	private static final long serialVersionUID = -3491449820340834800L;
	private String 		userName;
	private String		name;
	private Integer		teamId 			= 0;
	private	Integer		exp 			= 0;
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
	
	
	
	private long 		vipToExpire;
	
	private int 		leftMinites;
	
	private String blackWhite = "0";
	private int internetCafe=0;

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
		this.selectedCharacters = selectedCharacters;
//			CommonUtil.cutLastWord(selectedCharacters);
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
			this.isVip = 1;
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

	public Integer getMaxFightNum() {
		return maxFightNum;
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
	

}
