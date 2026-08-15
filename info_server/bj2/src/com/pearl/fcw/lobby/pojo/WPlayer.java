package com.pearl.fcw.lobby.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.MAIN)
public class WPlayer extends DmModel {

    private static final long serialVersionUID = 226028300191794778L;

    private Integer id;

    private String userName;

    private String name;

    private Integer gPoint;

    private Integer credit;

    private Integer voucher;

    private Integer exp;

    private Integer rank;

    private Byte characterSize;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Date lastLogoutTime;

    private Integer generalScore;

    private Integer generalKill;

    private Integer generalDead;

    private Integer generalHeadShot;

    private Integer generalKnifeKill;

    private Integer generalControl;

    private Integer generalRevenge;

    private Integer generalWin;

    private Integer generalTotal;

    private Integer teamScore;

    private Integer teamKill;

    private Integer teamDead;

    private Integer teamHeadShot;

    private Integer teamKnifeKill;

    private Integer teamGrenadeKill;

    private Integer teamHitPoint;

    private Integer teamWin;

    private Integer teamTotal;

    private Integer funGpTotal;

    private Integer funCrTotal;

    private Integer funGrenadeKill;

    private Integer funDropKill;

    private Integer funWeaponMod;

    private Integer funDead;

    private String tutorial;

    private String profile;

    private String icon;

    private String selectedCharacters;

    private String achievement;

    private String isDeleted;

    private Integer teamId;

    private Integer generalGrenadeKill;

    private Integer isNew;

    private Integer isVip;

    private Integer vipExperience;

    private Long vipToExpire;

    private String title;

    private Integer gold;

    private Integer silver;

    private Integer bronze;

    private Integer total;

    private Integer generalAssist;

    private Integer weekScore;

    private Integer weekKill;

    private Integer weekAssist;

    private Integer weekDead;

    private Integer weekWin;

    private Integer weekTotal;

    private String blackWhite;

    private Integer runAway;

    private String characters;

    private Date createTime;

    private Integer generalLose;

    private Byte isXunlei;

    private Integer generalHealthNum;

    private Boolean isCheck;

    private Integer maxFightNum;

    private Byte accountType;

    private Integer isChest;

    private String secondPassword;

    private Byte hasSecondPassword;

    private Date applyResetSpwTime;

    private Integer remainsTeamExp;

    private Integer lastTeamExp;

    private Date lastTeamExpExpireTime;

    private Integer lastExitTeamId;

    private Integer unusableResource;

    private Integer usableResource;

    private Date pResourceBeginTime;

    private String vipExpGiftLevel;

    private String matchWin;

    private Map<String, ParamObject<Integer>> tutorialMap = new HashMap<>();

	private Map<String, ParamObject<Number>> numberParamMap = new HashMap<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getgPoint() {
        return gPoint;
    }

    public void setgPoint(Integer gPoint) {
        this.gPoint = gPoint;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getVoucher() {
        return voucher;
    }

    public void setVoucher(Integer voucher) {
        this.voucher = voucher;
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

    public Byte getCharacterSize() {
        return characterSize;
    }

    public void setCharacterSize(Byte characterSize) {
        this.characterSize = characterSize;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public Integer getGeneralScore() {
        return generalScore;
    }

    public void setGeneralScore(Integer generalScore) {
        this.generalScore = generalScore;
    }

    public Integer getGeneralKill() {
        return generalKill;
    }

    public void setGeneralKill(Integer generalKill) {
        this.generalKill = generalKill;
    }

    public Integer getGeneralDead() {
        return generalDead;
    }

    public void setGeneralDead(Integer generalDead) {
        this.generalDead = generalDead;
    }

    public Integer getGeneralHeadShot() {
        return generalHeadShot;
    }

    public void setGeneralHeadShot(Integer generalHeadShot) {
        this.generalHeadShot = generalHeadShot;
    }

    public Integer getGeneralKnifeKill() {
        return generalKnifeKill;
    }

    public void setGeneralKnifeKill(Integer generalKnifeKill) {
        this.generalKnifeKill = generalKnifeKill;
    }

    public Integer getGeneralControl() {
        return generalControl;
    }

    public void setGeneralControl(Integer generalControl) {
        this.generalControl = generalControl;
    }

    public Integer getGeneralRevenge() {
        return generalRevenge;
    }

    public void setGeneralRevenge(Integer generalRevenge) {
        this.generalRevenge = generalRevenge;
    }

    public Integer getGeneralWin() {
        return generalWin;
    }

    public void setGeneralWin(Integer generalWin) {
        this.generalWin = generalWin;
    }

    public Integer getGeneralTotal() {
        return generalTotal;
    }

    public void setGeneralTotal(Integer generalTotal) {
        this.generalTotal = generalTotal;
    }

    public Integer getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    public Integer getTeamKill() {
        return teamKill;
    }

    public void setTeamKill(Integer teamKill) {
        this.teamKill = teamKill;
    }

    public Integer getTeamDead() {
        return teamDead;
    }

    public void setTeamDead(Integer teamDead) {
        this.teamDead = teamDead;
    }

    public Integer getTeamHeadShot() {
        return teamHeadShot;
    }

    public void setTeamHeadShot(Integer teamHeadShot) {
        this.teamHeadShot = teamHeadShot;
    }

    public Integer getTeamKnifeKill() {
        return teamKnifeKill;
    }

    public void setTeamKnifeKill(Integer teamKnifeKill) {
        this.teamKnifeKill = teamKnifeKill;
    }

    public Integer getTeamGrenadeKill() {
        return teamGrenadeKill;
    }

    public void setTeamGrenadeKill(Integer teamGrenadeKill) {
        this.teamGrenadeKill = teamGrenadeKill;
    }

    public Integer getTeamHitPoint() {
        return teamHitPoint;
    }

    public void setTeamHitPoint(Integer teamHitPoint) {
        this.teamHitPoint = teamHitPoint;
    }

    public Integer getTeamWin() {
        return teamWin;
    }

    public void setTeamWin(Integer teamWin) {
        this.teamWin = teamWin;
    }

    public Integer getTeamTotal() {
        return teamTotal;
    }

    public void setTeamTotal(Integer teamTotal) {
        this.teamTotal = teamTotal;
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

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial == null ? null : tutorial.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getSelectedCharacters() {
        return selectedCharacters;
    }

    public void setSelectedCharacters(String selectedCharacters) {
        this.selectedCharacters = selectedCharacters == null ? null : selectedCharacters.trim();
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement == null ? null : achievement.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGeneralGrenadeKill() {
        return generalGrenadeKill;
    }

    public void setGeneralGrenadeKill(Integer generalGrenadeKill) {
        this.generalGrenadeKill = generalGrenadeKill;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getVipExperience() {
        return vipExperience;
    }

    public void setVipExperience(Integer vipExperience) {
        this.vipExperience = vipExperience;
    }

    public Long getVipToExpire() {
        return vipToExpire;
    }

    public void setVipToExpire(Long vipToExpire) {
        this.vipToExpire = vipToExpire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Integer getGeneralAssist() {
        return generalAssist;
    }

    public void setGeneralAssist(Integer generalAssist) {
        this.generalAssist = generalAssist;
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

    public String getBlackWhite() {
        return blackWhite;
    }

    public void setBlackWhite(String blackWhite) {
        this.blackWhite = blackWhite == null ? null : blackWhite.trim();
    }

    public Integer getRunAway() {
        return runAway;
    }

    public void setRunAway(Integer runAway) {
        this.runAway = runAway;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters == null ? null : characters.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGeneralLose() {
        return generalLose;
    }

    public void setGeneralLose(Integer generalLose) {
        this.generalLose = generalLose;
    }

    public Byte getIsXunlei() {
        return isXunlei;
    }

    public void setIsXunlei(Byte isXunlei) {
        this.isXunlei = isXunlei;
    }

    public Integer getGeneralHealthNum() {
        return generalHealthNum;
    }

    public void setGeneralHealthNum(Integer generalHealthNum) {
        this.generalHealthNum = generalHealthNum;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getMaxFightNum() {
        return maxFightNum;
    }

    public void setMaxFightNum(Integer maxFightNum) {
        this.maxFightNum = maxFightNum;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public Integer getIsChest() {
        return isChest;
    }

    public void setIsChest(Integer isChest) {
        this.isChest = isChest;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword == null ? null : secondPassword.trim();
    }

    public Byte getHasSecondPassword() {
        return hasSecondPassword;
    }

    public void setHasSecondPassword(Byte hasSecondPassword) {
        this.hasSecondPassword = hasSecondPassword;
    }

    public Date getApplyResetSpwTime() {
        return applyResetSpwTime;
    }

    public void setApplyResetSpwTime(Date applyResetSpwTime) {
        this.applyResetSpwTime = applyResetSpwTime;
    }

    public Integer getRemainsTeamExp() {
        return remainsTeamExp;
    }

    public void setRemainsTeamExp(Integer remainsTeamExp) {
        this.remainsTeamExp = remainsTeamExp;
    }

    public Integer getLastTeamExp() {
        return lastTeamExp;
    }

    public void setLastTeamExp(Integer lastTeamExp) {
        this.lastTeamExp = lastTeamExp;
    }

    public Date getLastTeamExpExpireTime() {
        return lastTeamExpExpireTime;
    }

    public void setLastTeamExpExpireTime(Date lastTeamExpExpireTime) {
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

    public void setUnusableResource(Integer unusableResource) {
        this.unusableResource = unusableResource;
    }

    public Integer getUsableResource() {
        return usableResource;
    }

    public void setUsableResource(Integer usableResource) {
        this.usableResource = usableResource;
    }

    public Date getpResourceBeginTime() {
        return pResourceBeginTime;
    }

    public void setpResourceBeginTime(Date pResourceBeginTime) {
        this.pResourceBeginTime = pResourceBeginTime;
    }

    public String getVipExpGiftLevel() {
        return vipExpGiftLevel;
    }

    public void setVipExpGiftLevel(String vipExpGiftLevel) {
        this.vipExpGiftLevel = vipExpGiftLevel == null ? null : vipExpGiftLevel.trim();
    }

    public String getMatchWin() {
        return matchWin;
    }

    public void setMatchWin(String matchWin) {
        this.matchWin = matchWin == null ? null : matchWin.trim();
    }


    public Map<String, ParamObject<Integer>> getTutorialMap() {
        return tutorialMap;
    }

    public void setTutorialMap(Map<String, ParamObject<Integer>> tutorialMap) {
        this.tutorialMap = tutorialMap;
    }

	public Map<String, ParamObject<Number>> getNumberParamMap() {
		return numberParamMap;
	}

	public void setNumberParamMap(Map<String, ParamObject<Number>> numberParamMap) {
		this.numberParamMap = numberParamMap;
	}

	@Override
    public boolean getIsRemoved() {
        return "Y".equals(isDeleted);
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        isDeleted = isRemoved ? "Y" : "N";
    }
}