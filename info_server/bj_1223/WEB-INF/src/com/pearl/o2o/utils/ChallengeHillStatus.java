package com.pearl.o2o.utils;

import java.io.Serializable;

/**
 * 记录挑战赛信息
 * 
 * @author zhang.li
 * 
 */
public class ChallengeHillStatus implements Serializable {
	private static final long serialVersionUID = 7935974875532173462L;

	public ChallengeHillStatus(int rank) {
		int stones = Constants.TeamSpaceConstants.getdefHillStoneByRank(rank);
		setStones(stones);
	}

	/** 剩余黑曜石 */
	private int stones;

	/** 可以被抢的石头 */
	private int canBeRob;

	public int getStones() {
		return stones;
	}

	public void setStones(int stones) {
		this.stones = stones;
		this.canBeRob=new Double(0.05*stones).intValue();
	}

	public int getCanBeRob() {
		return canBeRob;
	}
	
	public static ChallengeHillStatus createDefaultByRank(int rank){
		return new ChallengeHillStatus(rank);
	}
}
