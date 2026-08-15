package com.pearl.o2o.pojo;

import java.util.Date;

import com.pearl.o2o.utils.DateUtil;



public class TeamRecord extends BaseMappingBean<TeamRecord> implements Comparable<Object>{
	

	/**
	 * eclipse 自动生成的
	 */
	private static final long serialVersionUID = 6827126149553543466L;
	
	private int teamId;
	private int bTeamId;
	private int score;
	private int  bScore;
	private int levelId;
	private int result;
	private long createTime;
	private String bTeamLeader;
	
	
	public String getBTeamLeader() {
		return bTeamLeader;
	}
	public void setBTeamLeader(String bTeamLeader) {
		this.bTeamLeader = bTeamLeader;
	}
	//附加属性
	private transient String bTeamName;
	
	private transient String levelName;
	private transient int levelType;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getBTeamId() {
		return bTeamId;
	}
	public void setBTeamId(int bTeamId) {
		this.bTeamId = bTeamId;
	}
	public String getStringScore() {
		return score +":"+bScore;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public  int getScore(){
		return score;
	}
	public int getBScore() {
		return bScore;
	}
	public void setBScore(int bScore) {
		this.bScore = bScore;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getBTeamName() {
		return bTeamName;
	}
	public void setBTeamName(String bTeamName) {
		this.bTeamName = bTeamName;
	}
	public int getLevelType() {
		return levelType;
	}
	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreateTimeStr(){
		return DateUtil.getDf().format(new Date(createTime));
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Override
	public int compareTo(Object o) {
		TeamRecord t=(TeamRecord)o;
		return createTime>t.createTime?1:0;
	}
	
	
	
}
