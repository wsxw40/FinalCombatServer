package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.ByteProxy;
import com.pearl.fcw.core.pojo.proxy.DateProxy;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.LongProxy;
import com.pearl.fcw.core.pojo.proxy.MapProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WPlayer;

public class ProxyWPlayer extends EntityProxy<WPlayer> {
	private IntegerProxy id;
	private PropertyProxy<java.lang.String> userName;
	private PropertyProxy<java.lang.String> name;
	private IntegerProxy gPoint;
	private IntegerProxy credit;
	private IntegerProxy voucher;
	private IntegerProxy exp;
	private IntegerProxy rank;
	private ByteProxy characterSize;
	private DateProxy<java.util.Date> lastLoginTime;
	private PropertyProxy<java.lang.String> lastLoginIp;
	private DateProxy<java.util.Date> lastLogoutTime;
	private IntegerProxy generalScore;
	private IntegerProxy generalKill;
	private IntegerProxy generalDead;
	private IntegerProxy generalHeadShot;
	private IntegerProxy generalKnifeKill;
	private IntegerProxy generalControl;
	private IntegerProxy generalRevenge;
	private IntegerProxy generalWin;
	private IntegerProxy generalTotal;
	private IntegerProxy teamScore;
	private IntegerProxy teamKill;
	private IntegerProxy teamDead;
	private IntegerProxy teamHeadShot;
	private IntegerProxy teamKnifeKill;
	private IntegerProxy teamGrenadeKill;
	private IntegerProxy teamHitPoint;
	private IntegerProxy teamWin;
	private IntegerProxy teamTotal;
	private IntegerProxy funGpTotal;
	private IntegerProxy funCrTotal;
	private IntegerProxy funGrenadeKill;
	private IntegerProxy funDropKill;
	private IntegerProxy funWeaponMod;
	private IntegerProxy funDead;
	private PropertyProxy<java.lang.String> tutorial;
	private PropertyProxy<java.lang.String> profile;
	private PropertyProxy<java.lang.String> icon;
	private PropertyProxy<java.lang.String> selectedCharacters;
	private PropertyProxy<java.lang.String> achievement;
	private PropertyProxy<java.lang.String> isDeleted;
	private IntegerProxy teamId;
	private IntegerProxy generalGrenadeKill;
	private IntegerProxy isNew;
	private IntegerProxy isVip;
	private IntegerProxy vipExperience;
	private LongProxy vipToExpire;
	private PropertyProxy<java.lang.String> title;
	private IntegerProxy gold;
	private IntegerProxy silver;
	private IntegerProxy bronze;
	private IntegerProxy total;
	private IntegerProxy generalAssist;
	private IntegerProxy weekScore;
	private IntegerProxy weekKill;
	private IntegerProxy weekAssist;
	private IntegerProxy weekDead;
	private IntegerProxy weekWin;
	private IntegerProxy weekTotal;
	private PropertyProxy<java.lang.String> blackWhite;
	private IntegerProxy runAway;
	private PropertyProxy<java.lang.String> characters;
	private DateProxy<java.util.Date> createTime;
	private IntegerProxy generalLose;
	private ByteProxy isXunlei;
	private IntegerProxy generalHealthNum;
	private PropertyProxy<java.lang.Boolean> isCheck;
	private IntegerProxy maxFightNum;
	private ByteProxy accountType;
	private IntegerProxy isChest;
	private PropertyProxy<java.lang.String> secondPassword;
	private ByteProxy hasSecondPassword;
	private DateProxy<java.util.Date> applyResetSpwTime;
	private IntegerProxy remainsTeamExp;
	private IntegerProxy lastTeamExp;
	private DateProxy<java.util.Date> lastTeamExpExpireTime;
	private IntegerProxy lastExitTeamId;
	private IntegerProxy unusableResource;
	private IntegerProxy usableResource;
	private DateProxy<java.util.Date> pResourceBeginTime;
	private PropertyProxy<java.lang.String> vipExpGiftLevel;
	private PropertyProxy<java.lang.String> matchWin;
    private MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>> tutorialMap;
	private MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>> numberParamMap;

	public IntegerProxy id() {
		return id;
	}

	public PropertyProxy<java.lang.String> userName() {
		return userName;
	}

	public PropertyProxy<java.lang.String> name() {
		return name;
	}

	public IntegerProxy gPoint() {
		return gPoint;
	}

	public IntegerProxy credit() {
		return credit;
	}

	public IntegerProxy voucher() {
		return voucher;
	}

	public IntegerProxy exp() {
		return exp;
	}

	public IntegerProxy rank() {
		return rank;
	}

	public ByteProxy characterSize() {
		return characterSize;
	}

	public DateProxy<java.util.Date> lastLoginTime() {
		return lastLoginTime;
	}

	public PropertyProxy<java.lang.String> lastLoginIp() {
		return lastLoginIp;
	}

	public DateProxy<java.util.Date> lastLogoutTime() {
		return lastLogoutTime;
	}

	public IntegerProxy generalScore() {
		return generalScore;
	}

	public IntegerProxy generalKill() {
		return generalKill;
	}

	public IntegerProxy generalDead() {
		return generalDead;
	}

	public IntegerProxy generalHeadShot() {
		return generalHeadShot;
	}

	public IntegerProxy generalKnifeKill() {
		return generalKnifeKill;
	}

	public IntegerProxy generalControl() {
		return generalControl;
	}

	public IntegerProxy generalRevenge() {
		return generalRevenge;
	}

	public IntegerProxy generalWin() {
		return generalWin;
	}

	public IntegerProxy generalTotal() {
		return generalTotal;
	}

	public IntegerProxy teamScore() {
		return teamScore;
	}

	public IntegerProxy teamKill() {
		return teamKill;
	}

	public IntegerProxy teamDead() {
		return teamDead;
	}

	public IntegerProxy teamHeadShot() {
		return teamHeadShot;
	}

	public IntegerProxy teamKnifeKill() {
		return teamKnifeKill;
	}

	public IntegerProxy teamGrenadeKill() {
		return teamGrenadeKill;
	}

	public IntegerProxy teamHitPoint() {
		return teamHitPoint;
	}

	public IntegerProxy teamWin() {
		return teamWin;
	}

	public IntegerProxy teamTotal() {
		return teamTotal;
	}

	public IntegerProxy funGpTotal() {
		return funGpTotal;
	}

	public IntegerProxy funCrTotal() {
		return funCrTotal;
	}

	public IntegerProxy funGrenadeKill() {
		return funGrenadeKill;
	}

	public IntegerProxy funDropKill() {
		return funDropKill;
	}

	public IntegerProxy funWeaponMod() {
		return funWeaponMod;
	}

	public IntegerProxy funDead() {
		return funDead;
	}

	public PropertyProxy<java.lang.String> tutorial() {
		return tutorial;
	}

	public PropertyProxy<java.lang.String> profile() {
		return profile;
	}

	public PropertyProxy<java.lang.String> icon() {
		return icon;
	}

	public PropertyProxy<java.lang.String> selectedCharacters() {
		return selectedCharacters;
	}

	public PropertyProxy<java.lang.String> achievement() {
		return achievement;
	}

	public PropertyProxy<java.lang.String> isDeleted() {
		return isDeleted;
	}

	public IntegerProxy teamId() {
		return teamId;
	}

	public IntegerProxy generalGrenadeKill() {
		return generalGrenadeKill;
	}

	public IntegerProxy isNew() {
		return isNew;
	}

	public IntegerProxy isVip() {
		return isVip;
	}

	public IntegerProxy vipExperience() {
		return vipExperience;
	}

	public LongProxy vipToExpire() {
		return vipToExpire;
	}

	public PropertyProxy<java.lang.String> title() {
		return title;
	}

	public IntegerProxy gold() {
		return gold;
	}

	public IntegerProxy silver() {
		return silver;
	}

	public IntegerProxy bronze() {
		return bronze;
	}

	public IntegerProxy total() {
		return total;
	}

	public IntegerProxy generalAssist() {
		return generalAssist;
	}

	public IntegerProxy weekScore() {
		return weekScore;
	}

	public IntegerProxy weekKill() {
		return weekKill;
	}

	public IntegerProxy weekAssist() {
		return weekAssist;
	}

	public IntegerProxy weekDead() {
		return weekDead;
	}

	public IntegerProxy weekWin() {
		return weekWin;
	}

	public IntegerProxy weekTotal() {
		return weekTotal;
	}

	public PropertyProxy<java.lang.String> blackWhite() {
		return blackWhite;
	}

	public IntegerProxy runAway() {
		return runAway;
	}

	public PropertyProxy<java.lang.String> characters() {
		return characters;
	}

	public DateProxy<java.util.Date> createTime() {
		return createTime;
	}

	public IntegerProxy generalLose() {
		return generalLose;
	}

	public ByteProxy isXunlei() {
		return isXunlei;
	}

	public IntegerProxy generalHealthNum() {
		return generalHealthNum;
	}

	public PropertyProxy<java.lang.Boolean> isCheck() {
		return isCheck;
	}

	public IntegerProxy maxFightNum() {
		return maxFightNum;
	}

	public ByteProxy accountType() {
		return accountType;
	}

	public IntegerProxy isChest() {
		return isChest;
	}

	public PropertyProxy<java.lang.String> secondPassword() {
		return secondPassword;
	}

	public ByteProxy hasSecondPassword() {
		return hasSecondPassword;
	}

	public DateProxy<java.util.Date> applyResetSpwTime() {
		return applyResetSpwTime;
	}

	public IntegerProxy remainsTeamExp() {
		return remainsTeamExp;
	}

	public IntegerProxy lastTeamExp() {
		return lastTeamExp;
	}

	public DateProxy<java.util.Date> lastTeamExpExpireTime() {
		return lastTeamExpExpireTime;
	}

	public IntegerProxy lastExitTeamId() {
		return lastExitTeamId;
	}

	public IntegerProxy unusableResource() {
		return unusableResource;
	}

	public IntegerProxy usableResource() {
		return usableResource;
	}

	public DateProxy<java.util.Date> pResourceBeginTime() {
		return pResourceBeginTime;
	}

	public PropertyProxy<java.lang.String> vipExpGiftLevel() {
		return vipExpGiftLevel;
	}

	public PropertyProxy<java.lang.String> matchWin() {
		return matchWin;
	}

    public MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>> tutorialMap() {
		return tutorialMap;
	}

	public MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>> numberParamMap() {
		return numberParamMap;
	}

	@Override
	public void initFields() {
		WPlayer instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		userName = new  PropertyProxy<java.lang.String> ("userName", this, instance::getUserName, instance::setUserName);
		name = new  PropertyProxy<java.lang.String> ("name", this, instance::getName, instance::setName);
		gPoint = new  IntegerProxy ("gPoint", this, instance::getgPoint, instance::setgPoint);
		credit = new  IntegerProxy ("credit", this, instance::getCredit, instance::setCredit);
		voucher = new  IntegerProxy ("voucher", this, instance::getVoucher, instance::setVoucher);
		exp = new  IntegerProxy ("exp", this, instance::getExp, instance::setExp);
		rank = new  IntegerProxy ("rank", this, instance::getRank, instance::setRank);
		characterSize = new  ByteProxy ("characterSize", this, instance::getCharacterSize, instance::setCharacterSize);
		lastLoginTime = new  DateProxy<java.util.Date> ("lastLoginTime", this, instance::getLastLoginTime, instance::setLastLoginTime);
		lastLoginIp = new  PropertyProxy<java.lang.String> ("lastLoginIp", this, instance::getLastLoginIp, instance::setLastLoginIp);
		lastLogoutTime = new  DateProxy<java.util.Date> ("lastLogoutTime", this, instance::getLastLogoutTime, instance::setLastLogoutTime);
		generalScore = new  IntegerProxy ("generalScore", this, instance::getGeneralScore, instance::setGeneralScore);
		generalKill = new  IntegerProxy ("generalKill", this, instance::getGeneralKill, instance::setGeneralKill);
		generalDead = new  IntegerProxy ("generalDead", this, instance::getGeneralDead, instance::setGeneralDead);
		generalHeadShot = new  IntegerProxy ("generalHeadShot", this, instance::getGeneralHeadShot, instance::setGeneralHeadShot);
		generalKnifeKill = new  IntegerProxy ("generalKnifeKill", this, instance::getGeneralKnifeKill, instance::setGeneralKnifeKill);
		generalControl = new  IntegerProxy ("generalControl", this, instance::getGeneralControl, instance::setGeneralControl);
		generalRevenge = new  IntegerProxy ("generalRevenge", this, instance::getGeneralRevenge, instance::setGeneralRevenge);
		generalWin = new  IntegerProxy ("generalWin", this, instance::getGeneralWin, instance::setGeneralWin);
		generalTotal = new  IntegerProxy ("generalTotal", this, instance::getGeneralTotal, instance::setGeneralTotal);
		teamScore = new  IntegerProxy ("teamScore", this, instance::getTeamScore, instance::setTeamScore);
		teamKill = new  IntegerProxy ("teamKill", this, instance::getTeamKill, instance::setTeamKill);
		teamDead = new  IntegerProxy ("teamDead", this, instance::getTeamDead, instance::setTeamDead);
		teamHeadShot = new  IntegerProxy ("teamHeadShot", this, instance::getTeamHeadShot, instance::setTeamHeadShot);
		teamKnifeKill = new  IntegerProxy ("teamKnifeKill", this, instance::getTeamKnifeKill, instance::setTeamKnifeKill);
		teamGrenadeKill = new  IntegerProxy ("teamGrenadeKill", this, instance::getTeamGrenadeKill, instance::setTeamGrenadeKill);
		teamHitPoint = new  IntegerProxy ("teamHitPoint", this, instance::getTeamHitPoint, instance::setTeamHitPoint);
		teamWin = new  IntegerProxy ("teamWin", this, instance::getTeamWin, instance::setTeamWin);
		teamTotal = new  IntegerProxy ("teamTotal", this, instance::getTeamTotal, instance::setTeamTotal);
		funGpTotal = new  IntegerProxy ("funGpTotal", this, instance::getFunGpTotal, instance::setFunGpTotal);
		funCrTotal = new  IntegerProxy ("funCrTotal", this, instance::getFunCrTotal, instance::setFunCrTotal);
		funGrenadeKill = new  IntegerProxy ("funGrenadeKill", this, instance::getFunGrenadeKill, instance::setFunGrenadeKill);
		funDropKill = new  IntegerProxy ("funDropKill", this, instance::getFunDropKill, instance::setFunDropKill);
		funWeaponMod = new  IntegerProxy ("funWeaponMod", this, instance::getFunWeaponMod, instance::setFunWeaponMod);
		funDead = new  IntegerProxy ("funDead", this, instance::getFunDead, instance::setFunDead);
		tutorial = new  PropertyProxy<java.lang.String> ("tutorial", this, instance::getTutorial, instance::setTutorial);
		profile = new  PropertyProxy<java.lang.String> ("profile", this, instance::getProfile, instance::setProfile);
		icon = new  PropertyProxy<java.lang.String> ("icon", this, instance::getIcon, instance::setIcon);
		selectedCharacters = new  PropertyProxy<java.lang.String> ("selectedCharacters", this, instance::getSelectedCharacters, instance::setSelectedCharacters);
		achievement = new  PropertyProxy<java.lang.String> ("achievement", this, instance::getAchievement, instance::setAchievement);
		isDeleted = new  PropertyProxy<java.lang.String> ("isDeleted", this, instance::getIsDeleted, instance::setIsDeleted);
		teamId = new  IntegerProxy ("teamId", this, instance::getTeamId, instance::setTeamId);
		generalGrenadeKill = new  IntegerProxy ("generalGrenadeKill", this, instance::getGeneralGrenadeKill, instance::setGeneralGrenadeKill);
		isNew = new  IntegerProxy ("isNew", this, instance::getIsNew, instance::setIsNew);
		isVip = new  IntegerProxy ("isVip", this, instance::getIsVip, instance::setIsVip);
		vipExperience = new  IntegerProxy ("vipExperience", this, instance::getVipExperience, instance::setVipExperience);
		vipToExpire = new  LongProxy ("vipToExpire", this, instance::getVipToExpire, instance::setVipToExpire);
		title = new  PropertyProxy<java.lang.String> ("title", this, instance::getTitle, instance::setTitle);
		gold = new  IntegerProxy ("gold", this, instance::getGold, instance::setGold);
		silver = new  IntegerProxy ("silver", this, instance::getSilver, instance::setSilver);
		bronze = new  IntegerProxy ("bronze", this, instance::getBronze, instance::setBronze);
		total = new  IntegerProxy ("total", this, instance::getTotal, instance::setTotal);
		generalAssist = new  IntegerProxy ("generalAssist", this, instance::getGeneralAssist, instance::setGeneralAssist);
		weekScore = new  IntegerProxy ("weekScore", this, instance::getWeekScore, instance::setWeekScore);
		weekKill = new  IntegerProxy ("weekKill", this, instance::getWeekKill, instance::setWeekKill);
		weekAssist = new  IntegerProxy ("weekAssist", this, instance::getWeekAssist, instance::setWeekAssist);
		weekDead = new  IntegerProxy ("weekDead", this, instance::getWeekDead, instance::setWeekDead);
		weekWin = new  IntegerProxy ("weekWin", this, instance::getWeekWin, instance::setWeekWin);
		weekTotal = new  IntegerProxy ("weekTotal", this, instance::getWeekTotal, instance::setWeekTotal);
		blackWhite = new  PropertyProxy<java.lang.String> ("blackWhite", this, instance::getBlackWhite, instance::setBlackWhite);
		runAway = new  IntegerProxy ("runAway", this, instance::getRunAway, instance::setRunAway);
		characters = new  PropertyProxy<java.lang.String> ("characters", this, instance::getCharacters, instance::setCharacters);
		createTime = new  DateProxy<java.util.Date> ("createTime", this, instance::getCreateTime, instance::setCreateTime);
		generalLose = new  IntegerProxy ("generalLose", this, instance::getGeneralLose, instance::setGeneralLose);
		isXunlei = new  ByteProxy ("isXunlei", this, instance::getIsXunlei, instance::setIsXunlei);
		generalHealthNum = new  IntegerProxy ("generalHealthNum", this, instance::getGeneralHealthNum, instance::setGeneralHealthNum);
		isCheck = new  PropertyProxy<java.lang.Boolean> ("isCheck", this, instance::getIsCheck, instance::setIsCheck);
		maxFightNum = new  IntegerProxy ("maxFightNum", this, instance::getMaxFightNum, instance::setMaxFightNum);
		accountType = new  ByteProxy ("accountType", this, instance::getAccountType, instance::setAccountType);
		isChest = new  IntegerProxy ("isChest", this, instance::getIsChest, instance::setIsChest);
		secondPassword = new  PropertyProxy<java.lang.String> ("secondPassword", this, instance::getSecondPassword, instance::setSecondPassword);
		hasSecondPassword = new  ByteProxy ("hasSecondPassword", this, instance::getHasSecondPassword, instance::setHasSecondPassword);
		applyResetSpwTime = new  DateProxy<java.util.Date> ("applyResetSpwTime", this, instance::getApplyResetSpwTime, instance::setApplyResetSpwTime);
		remainsTeamExp = new  IntegerProxy ("remainsTeamExp", this, instance::getRemainsTeamExp, instance::setRemainsTeamExp);
		lastTeamExp = new  IntegerProxy ("lastTeamExp", this, instance::getLastTeamExp, instance::setLastTeamExp);
		lastTeamExpExpireTime = new  DateProxy<java.util.Date> ("lastTeamExpExpireTime", this, instance::getLastTeamExpExpireTime, instance::setLastTeamExpExpireTime);
		lastExitTeamId = new  IntegerProxy ("lastExitTeamId", this, instance::getLastExitTeamId, instance::setLastExitTeamId);
		unusableResource = new  IntegerProxy ("unusableResource", this, instance::getUnusableResource, instance::setUnusableResource);
		usableResource = new  IntegerProxy ("usableResource", this, instance::getUsableResource, instance::setUsableResource);
		pResourceBeginTime = new  DateProxy<java.util.Date> ("pResourceBeginTime", this, instance::getpResourceBeginTime, instance::setpResourceBeginTime);
		vipExpGiftLevel = new  PropertyProxy<java.lang.String> ("vipExpGiftLevel", this, instance::getVipExpGiftLevel, instance::setVipExpGiftLevel);
		matchWin = new  PropertyProxy<java.lang.String> ("matchWin", this, instance::getMatchWin, instance::setMatchWin);
        tutorialMap = new MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>>("tutorialMap", this, instance::getTutorialMap,
                instance::setTutorialMap, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>::new);
		numberParamMap = new MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>>("numberParamMap", this,
				instance::getNumberParamMap, instance::setNumberParamMap, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>::new);
	}

	public ProxyWPlayer(WPlayer  instance) {
		super(instance);
	}

	public ProxyWPlayer(Object element, Proxy<?> owner, Supplier<WPlayer> getter,  Consumer<WPlayer> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayer(Object element, Proxy<?> owner, WPlayer instance) {
		super(element, owner, instance);
	}

}