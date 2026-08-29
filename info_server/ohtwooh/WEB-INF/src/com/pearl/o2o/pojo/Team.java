package com.pearl.o2o.pojo;

import java.util.Date;
import java.util.List;

public class Team extends BasePojo {

	private static final long serialVersionUID = 727386169361377369L;
	private Integer id;
	private String name;
	private String declaration;
	private String description;
	private String board;
	private String logo;
	private Integer size;
	private Integer kill;
	private Integer headShot;
	private Integer gameWin;
	private Integer gameTotal;
	private Integer challengeWin;
	private Integer challengeTotal;
	private Date createTime;
	
	private String gameRatio;
	private String challengeRatio;
	private List<Team> teamList;
	private List<PlayerTeam> memberList;
	
	public void calculateParam(){
		if(gameTotal==0){
			gameRatio="0%";
		}else{
		gameRatio=(gameWin*100.0/gameTotal)+"%";
		}
		if(challengeTotal==0){
			challengeRatio="0%";
		}else{
			challengeRatio=(challengeWin*100.0/challengeTotal)+"%";
		}
		if(teamList!=null&&teamList.size()>0){
			for(Team t:teamList){
				t.calculateParam();
			}
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getKill() {
		return kill;
	}
	public void setKill(Integer kill) {
		this.kill = kill;
	}
	public Integer getHeadShot() {
		return headShot;
	}
	public void setHeadShot(Integer headShot) {
		this.headShot = headShot;
	}
	public Integer getGameWin() {
		return gameWin;
	}
	public void setGameWin(Integer gameWin) {
		this.gameWin = gameWin;
	}
	public Integer getGameTotal() {
		return gameTotal;
	}
	public void setGameTotal(Integer gameTotal) {
		this.gameTotal = gameTotal;
	}
	public Integer getChallengeWin() {
		return challengeWin;
	}
	public void setChallengeWin(Integer challengeWin) {
		this.challengeWin = challengeWin;
	}
	public Integer getChallengeTotal() {
		return challengeTotal;
	}
	public void setChallengeTotal(Integer challengeTotal) {
		this.challengeTotal = challengeTotal;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Team> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
	public List<PlayerTeam> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<PlayerTeam> memberList) {
		this.memberList = memberList;
	}
	public String getGameRatio() {
		return gameRatio;
	}
	public void setGameRatio(String gameRatio) {
		this.gameRatio = gameRatio;
	}
	public String getChallengeRatio() {
		return challengeRatio;
	}
	public void setChallengeRatio(String challengeRatio) {
		this.challengeRatio = challengeRatio;
	}
}
