package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WTeam;

public class ProxyWTeam extends EntityProxy<WTeam> {
	private IntegerProxy id;
	private PropertyProxy<java.lang.String> name;
	private PropertyProxy<java.lang.String> declaration;
	private PropertyProxy<java.lang.String> description;
	private PropertyProxy<java.lang.String> board;
	private PropertyProxy<java.lang.String> logo;
	private IntegerProxy size;
	private IntegerProxy kill;
	private IntegerProxy headShot;
	private IntegerProxy gameWin;
	private IntegerProxy gameTotal;
	private IntegerProxy challengeWin;
	private IntegerProxy challengeTotal;
	private DateProxy<java.util.Date> createdTime;
	private PropertyProxy<java.lang.String> deleted;
	private IntegerProxy score;
	private IntegerProxy hitScore;
	private PropertyProxy<java.lang.String> province;
	private PropertyProxy<java.lang.String> city;
	private IntegerProxy rank;
	private IntegerProxy level;
	private IntegerProxy exp;
	private IntegerProxy recoreRankingFormer;
	private IntegerProxy recoreRankingCurr;
	private IntegerProxy fightRankingFormer;
	private IntegerProxy fightRankingCurr;
	private IntegerProxy preweekResAmount;
	private IntegerProxy teamSpaceLevel;
	private IntegerProxy unusableResource;
	private IntegerProxy usableResource;
	private IntegerProxy teamSpaceActive;
	private DateProxy<java.util.Date> tResLastAddTime;
	private DateProxy<java.util.Date> tResourceBeginTime;
	private IntegerProxy lastTeamPlaceLevelUpTime;
	private IntegerProxy predayResAmount;
	private IntegerProxy curWeekRobCount;
	private DateProxy<java.util.Date> curWeekRobUpdateTime;

	public IntegerProxy id() {
		return id;
	}

	public PropertyProxy<java.lang.String> name() {
		return name;
	}

	public PropertyProxy<java.lang.String> declaration() {
		return declaration;
	}

	public PropertyProxy<java.lang.String> description() {
		return description;
	}

	public PropertyProxy<java.lang.String> board() {
		return board;
	}

	public PropertyProxy<java.lang.String> logo() {
		return logo;
	}

	public IntegerProxy size() {
		return size;
	}

	public IntegerProxy kill() {
		return kill;
	}

	public IntegerProxy headShot() {
		return headShot;
	}

	public IntegerProxy gameWin() {
		return gameWin;
	}

	public IntegerProxy gameTotal() {
		return gameTotal;
	}

	public IntegerProxy challengeWin() {
		return challengeWin;
	}

	public IntegerProxy challengeTotal() {
		return challengeTotal;
	}

	public DateProxy<java.util.Date> createdTime() {
		return createdTime;
	}

	public PropertyProxy<java.lang.String> deleted() {
		return deleted;
	}

	public IntegerProxy score() {
		return score;
	}

	public IntegerProxy hitScore() {
		return hitScore;
	}

	public PropertyProxy<java.lang.String> province() {
		return province;
	}

	public PropertyProxy<java.lang.String> city() {
		return city;
	}

	public IntegerProxy rank() {
		return rank;
	}

	public IntegerProxy level() {
		return level;
	}

	public IntegerProxy exp() {
		return exp;
	}

	public IntegerProxy recoreRankingFormer() {
		return recoreRankingFormer;
	}

	public IntegerProxy recoreRankingCurr() {
		return recoreRankingCurr;
	}

	public IntegerProxy fightRankingFormer() {
		return fightRankingFormer;
	}

	public IntegerProxy fightRankingCurr() {
		return fightRankingCurr;
	}

	public IntegerProxy preweekResAmount() {
		return preweekResAmount;
	}

	public IntegerProxy teamSpaceLevel() {
		return teamSpaceLevel;
	}

	public IntegerProxy unusableResource() {
		return unusableResource;
	}

	public IntegerProxy usableResource() {
		return usableResource;
	}

	public IntegerProxy teamSpaceActive() {
		return teamSpaceActive;
	}

	public DateProxy<java.util.Date> tResLastAddTime() {
		return tResLastAddTime;
	}

	public DateProxy<java.util.Date> tResourceBeginTime() {
		return tResourceBeginTime;
	}

	public IntegerProxy lastTeamPlaceLevelUpTime() {
		return lastTeamPlaceLevelUpTime;
	}

	public IntegerProxy predayResAmount() {
		return predayResAmount;
	}

	public IntegerProxy curWeekRobCount() {
		return curWeekRobCount;
	}

	public DateProxy<java.util.Date> curWeekRobUpdateTime() {
		return curWeekRobUpdateTime;
	}

	@Override
	public void initFields() {
		WTeam instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		name = new  PropertyProxy<java.lang.String> ("name", this, instance::getName, instance::setName);
		declaration = new  PropertyProxy<java.lang.String> ("declaration", this, instance::getDeclaration, instance::setDeclaration);
		description = new  PropertyProxy<java.lang.String> ("description", this, instance::getDescription, instance::setDescription);
		board = new  PropertyProxy<java.lang.String> ("board", this, instance::getBoard, instance::setBoard);
		logo = new  PropertyProxy<java.lang.String> ("logo", this, instance::getLogo, instance::setLogo);
		size = new  IntegerProxy ("size", this, instance::getSize, instance::setSize);
		kill = new  IntegerProxy ("kill", this, instance::getKill, instance::setKill);
		headShot = new  IntegerProxy ("headShot", this, instance::getHeadShot, instance::setHeadShot);
		gameWin = new  IntegerProxy ("gameWin", this, instance::getGameWin, instance::setGameWin);
		gameTotal = new  IntegerProxy ("gameTotal", this, instance::getGameTotal, instance::setGameTotal);
		challengeWin = new  IntegerProxy ("challengeWin", this, instance::getChallengeWin, instance::setChallengeWin);
		challengeTotal = new  IntegerProxy ("challengeTotal", this, instance::getChallengeTotal, instance::setChallengeTotal);
		createdTime = new  DateProxy<java.util.Date> ("createdTime", this, instance::getCreatedTime, instance::setCreatedTime);
		deleted = new  PropertyProxy<java.lang.String> ("deleted", this, instance::getDeleted, instance::setDeleted);
		score = new  IntegerProxy ("score", this, instance::getScore, instance::setScore);
		hitScore = new  IntegerProxy ("hitScore", this, instance::getHitScore, instance::setHitScore);
		province = new  PropertyProxy<java.lang.String> ("province", this, instance::getProvince, instance::setProvince);
		city = new  PropertyProxy<java.lang.String> ("city", this, instance::getCity, instance::setCity);
		rank = new  IntegerProxy ("rank", this, instance::getRank, instance::setRank);
		level = new  IntegerProxy ("level", this, instance::getLevel, instance::setLevel);
		exp = new  IntegerProxy ("exp", this, instance::getExp, instance::setExp);
		recoreRankingFormer = new  IntegerProxy ("recoreRankingFormer", this, instance::getRecoreRankingFormer, instance::setRecoreRankingFormer);
		recoreRankingCurr = new  IntegerProxy ("recoreRankingCurr", this, instance::getRecoreRankingCurr, instance::setRecoreRankingCurr);
		fightRankingFormer = new  IntegerProxy ("fightRankingFormer", this, instance::getFightRankingFormer, instance::setFightRankingFormer);
		fightRankingCurr = new  IntegerProxy ("fightRankingCurr", this, instance::getFightRankingCurr, instance::setFightRankingCurr);
		preweekResAmount = new  IntegerProxy ("preweekResAmount", this, instance::getPreweekResAmount, instance::setPreweekResAmount);
		teamSpaceLevel = new  IntegerProxy ("teamSpaceLevel", this, instance::getTeamSpaceLevel, instance::setTeamSpaceLevel);
		unusableResource = new  IntegerProxy ("unusableResource", this, instance::getUnusableResource, instance::setUnusableResource);
		usableResource = new  IntegerProxy ("usableResource", this, instance::getUsableResource, instance::setUsableResource);
		teamSpaceActive = new  IntegerProxy ("teamSpaceActive", this, instance::getTeamSpaceActive, instance::setTeamSpaceActive);
		tResLastAddTime = new  DateProxy<java.util.Date> ("tResLastAddTime", this, instance::gettResLastAddTime, instance::settResLastAddTime);
		tResourceBeginTime = new  DateProxy<java.util.Date> ("tResourceBeginTime", this, instance::gettResourceBeginTime, instance::settResourceBeginTime);
		lastTeamPlaceLevelUpTime = new  IntegerProxy ("lastTeamPlaceLevelUpTime", this, instance::getLastTeamPlaceLevelUpTime, instance::setLastTeamPlaceLevelUpTime);
		predayResAmount = new  IntegerProxy ("predayResAmount", this, instance::getPredayResAmount, instance::setPredayResAmount);
		curWeekRobCount = new  IntegerProxy ("curWeekRobCount", this, instance::getCurWeekRobCount, instance::setCurWeekRobCount);
		curWeekRobUpdateTime = new  DateProxy<java.util.Date> ("curWeekRobUpdateTime", this, instance::getCurWeekRobUpdateTime, instance::setCurWeekRobUpdateTime);
	}

	public ProxyWTeam(WTeam  instance) {
		super(instance);
	}

	public ProxyWTeam(Object element, Proxy<?> owner, Supplier<WTeam> getter,  Consumer<WTeam> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWTeam(Object element, Proxy<?> owner, WTeam instance) {
		super(element, owner, instance);
	}

}