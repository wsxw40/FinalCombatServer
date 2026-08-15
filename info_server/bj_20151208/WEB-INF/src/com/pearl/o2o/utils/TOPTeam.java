package com.pearl.o2o.utils;

import com.pearl.o2o.pojo.Team;

public class TOPTeam {
	private Team team;
	private boolean ableBeChallenge = false;// 默认不可被挑战
	private long leftTime;
	private ChallengeHillStatus challengeHillStatus;
	private int rank;
	private int challengeCost;//

	public TOPTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean getAbleBeChallenge() {
		return ableBeChallenge;
	}

	public void setAbleBeChallenge(boolean ableBeChallenge) {
		this.ableBeChallenge = ableBeChallenge;
	}

	public long getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}

	public ChallengeHillStatus getChallengeHillStatus() {
		return challengeHillStatus;
	}

	public void setChallengeHillStatus(ChallengeHillStatus challengeHillStatus) {
		this.challengeHillStatus = challengeHillStatus;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getChallengeCost() {
		return challengeCost;
	}

	public void setChallengeCost(int challengeCost) {
		this.challengeCost = challengeCost;
	}

	public int getLeaderMaxReturn() {
		return Constants.TeamSpaceConstants.getLeaderReturn(challengeCost, 1);
	}

	public int getMemberMaxDisReturn() {
		return Constants.TeamSpaceConstants.getMemberDisReturn(challengeCost, 1);
	}

	
}
