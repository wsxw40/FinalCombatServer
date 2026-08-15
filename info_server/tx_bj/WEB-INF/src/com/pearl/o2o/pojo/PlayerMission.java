package com.pearl.o2o.pojo;

import java.util.Date;
import java.util.List;

import com.pearl.o2o.utils.DBRouteUtil;
import com.pearl.o2o.utils.GrowthMissionConstants.CycleMissionIncome;

public class PlayerMission extends BaseMappingBean<PlayerMission> {

	private static final long serialVersionUID = 2044601984128880825L;

	private Integer	playerId;
	private Integer sysMissionId;
	private Integer number;
	private Integer target;
	private Integer status;//0:unfinshed  1:finsh
	private Integer type;
	private SysMission sysMission;
	private Date createTime;
	private int award;
	private String description;
	private transient CycleMissionIncome cmIncome;
	private transient List<AwardItemVo> awardItemVos;
	private transient int vipUpGradeExp;				//vip 能获得的升级vip等级的经验
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	public SysMission getSysMission() {
		return sysMission;
	}
	public void setSysMission(SysMission sysMission) {
		this.sysMission = sysMission;
	}
	/**
	 * @return the playerId
	 */
	public Integer getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the sysMissionId
	 */
	public Integer getSysMissionId() {
		return sysMissionId;
	}
	/**
	 * @param sysMissionId the sysMissionId to set
	 */
	public void setSysMissionId(Integer sysMissionId) {
		this.sysMissionId = sysMissionId;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerMission.class, playerId);
    }
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CycleMissionIncome getCmIncome() {
		return cmIncome;
	}
	public void setCmIncome(CycleMissionIncome cmIncome) {
		this.cmIncome = cmIncome;
	}
	public List<AwardItemVo> getAwardItemVos() {
		return awardItemVos;
	}
	public void setAwardItemVos(List<AwardItemVo> awardItemVos) {
		this.awardItemVos = awardItemVos;
	}
	public int getVipUpGradeExp() {
		return vipUpGradeExp;
	}
	public void setVipUpGradeExp(int vipUpGradeExp) {
		this.vipUpGradeExp = vipUpGradeExp;
	}

}
