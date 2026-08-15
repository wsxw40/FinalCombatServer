package com.pearl.o2o.pojo;

public class FightRecord {
	private Integer mateId;
	private Integer gameType;
	private String myScore;
	private String mateScore;
	private String time;

	public Integer getMateId() {
		return mateId;
	}

	public void setMateId(Integer mateId) {
		this.mateId = mateId;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public String getMyScore() {
		return myScore;
	}

	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}

	public String getMateScore() {
		return mateScore;
	}

	public void setMateScore(String mateScore) {
		this.mateScore = mateScore;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
