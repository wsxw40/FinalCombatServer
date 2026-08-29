package com.pearl.o2o.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


public class TeamScoreIncrement {
	private int side;
	private int teamId;
	private String name;
	private String logo;
	private int kill;
	private int dead;
	private int headShot;
	private int knife;
	private int gameWin;
	private int grenade_kill;
	private int challengeWin;
	private int challengeTotal;
	private int created_time;
	private int score;
	private int hitScore;
	//if team was dismissed during stage clear, this field will be set to null and the 'logo' and 'teamId' will be set default value
	private Team relatedTeam;
	
	//private List<Player> attendees = new ArrayList<Player>(); 
	private StringBuilder attendeesStr = new StringBuilder();
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getHeadShot() {
		return headShot;
	}
	public void setHeadShot(int headShot) {
		this.headShot = headShot;
	}
	public int getGameWin() {
		return gameWin;
	}
	public void setGameWin(int gameWin) {
		this.gameWin = gameWin;
	}
	public int getChallengeTotal() {
		return challengeTotal;
	}
	public void setChallengeTotal(int challengeTotal) {
		this.challengeTotal = challengeTotal;
	}
	public int getCreated_time() {
		return created_time;
	}
	public void setCreated_time(int created_time) {
		this.created_time = created_time;
	}
	public int getChallengeWin() {
		return challengeWin;
	}
	public void setChallengeWin(int challengeWin) {
		this.challengeWin = challengeWin;
	}
	
	public void addAttendee(String name){
		attendeesStr.append(name).append(",");
	}
	
	public String getAttendeeNameStr(){
		return attendeesStr.toString();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHitScore() {
		return hitScore;
	}
	public void setHitScore(int hitScore) {
		this.hitScore = hitScore;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getKnife() {
		return knife;
	}
	public void setKnife(int knife) {
		this.knife = knife;
	}
	public int getGrenade_kill() {
		return grenade_kill;
	}
	public void setGrenade_kill(int grenade_kill) {
		this.grenade_kill = grenade_kill;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public Team getRelatedTeam() {
		return relatedTeam;
	}
	public void setRelatedTeam(Team relatedTeam) {
		this.relatedTeam = relatedTeam;
	}
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	
}
