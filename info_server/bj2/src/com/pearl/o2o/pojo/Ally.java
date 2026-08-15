package com.pearl.o2o.pojo;


public class Ally extends BaseMappingBean<Ally> {
	private static final long serialVersionUID = -3969298110541355027L;
	
	private int teamId;
	private int allyId;
	
	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}
	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	/**
	 * @return the allyId
	 */
	public int getAllyId() {
		return allyId;
	}
	/**
	 * @param allyId the allyId to set
	 */
	public void setAllyId(int allyId) {
		this.allyId = allyId;
	}
}
