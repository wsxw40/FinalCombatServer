package com.pearl.fcw.info.lobby.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.lobby.utils.CacheUtil;
import com.pearl.fcw.info.lobby.utils.DateUtil;

@Entity
@Schema(GoSchema.MAIN)
public class PlayerTeam extends BasePojo {

	private static final long serialVersionUID = -3237212642385023223L;
	@Id
	@GeneratedValue
	private int id;
	private Integer playerId;
	@Transient private Player player;

	private Integer teamId;
	private String nickName;
	private Integer rank;
	private Integer isVip;
	private int internetCafe = 0;
	private Integer exp;
	private Integer job;
	private Integer killNum;
	private Integer deadNum;
	private long createTime;
	private String approved;
	private Integer card;

	private transient int online = 0;
	private Integer score;
	private Integer assist;

	private int contribution;// 贡献度
	private int times;// 战队战次数
	private int point;// 战队战分数
	private long lastBurnt;
	private int teamFightExp;// 玩家当天通过战队战获得的战队经验
	private int personalFightExp;// 玩家当天通过普通战获得的战队经验
	private transient int fight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeamFightExp() {
		return teamFightExp;
	}

	public void setTeamFightExp(int teamFightExp) {
		this.teamFightExp = teamFightExp;
	}

	public int getPersonalFightExp() {
		return personalFightExp;
	}

	public void setPersonalFightExp(int personalFightExp) {
		this.personalFightExp = personalFightExp;
	}

	public String getCreatDate() {
		return DateUtil.format(new Date(createTime * 1000));
	}

	public String getCreatDay() {
		return DateUtil.format2(new Date(createTime * 1000));
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	public Integer getAssist() {
		return assist;
	}

	public void setAssist(Integer assist) {
		this.assist = assist;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getJob() {
		return job;
	}

	public void setJob(Integer job) {
		this.job = job;
	}

	public Integer getKillNum() {
		return killNum;
	}

	public void setKillNum(Integer killNum) {
		this.killNum = killNum;
	}

	public Integer getDeadNum() {
		return deadNum;
	}

	public void setDeadNum(Integer deadNum) {
		this.deadNum = deadNum;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getCard() {
		return card;
	}

	public void setCard(Integer card) {
		this.card = card;
	}

	public int getInternetCafe() {
		return internetCafe;
	}

	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}

	public int getContribution() {
		return contribution;
	}

	public void setContribution(int contribution) {
		this.contribution = contribution;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public long getLastBurnt() {
		return lastBurnt;
	}

	public void setLastBurnt(long lastBurnt) {
		this.lastBurnt = lastBurnt;
	}

	public int getIsNew() {
		return CacheUtil.isToday(createTime * 1000) ? 1 : 0;
	}
}
