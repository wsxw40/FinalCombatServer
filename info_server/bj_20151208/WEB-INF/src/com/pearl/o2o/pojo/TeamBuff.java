package com.pearl.o2o.pojo;

import com.pearl.o2o.utils.DBRouteUtil;



public class TeamBuff extends BaseMappingBean<TeamBuff> {

	/**
	 * eclipse 自动生成的
	 */
	private static final long serialVersionUID = 2775695183507984776L;

	private Integer teamId;
	private Boolean isEnable;
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(TeamBuff.class, id);
    }
	
}
