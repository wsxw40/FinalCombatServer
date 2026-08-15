package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerQuest extends DmExtModel {

	private static final long serialVersionUID = 6870795005830821062L;

	private Integer id;

	private Integer playerId;

    private Integer sysQuestId;

    private Integer status;

	private Long number;

	private Integer completeCount;

	private String remark;

	private Date createTime;

    private Date updateTime;

    private String isDeleted;

    @Override
	public Integer getId() {
        return id;
    }

    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysQuestId() {
        return sysQuestId;
    }

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public void setSysQuestId(Integer sysQuestId) {
        this.sysQuestId = sysQuestId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getNumber() {
        return number;
    }

	public void setNumber(Long number) {
        this.number = number;
    }

	public Integer getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Date getCreateTime() {
        return createTime;
    }

    @Override
	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
	public Date getUpdateTime() {
        return updateTime;
    }

    @Override
	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

	@Override
	public Integer getShareId() {
		return playerId;
	}

	@Override
	public void setShareId(Integer shareId) {
		playerId = shareId;
	}

	@Override
	public boolean getIsRemoved() {
		return "Y".equals(isDeleted);
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDeleted = isRemoved ? "Y" : "N";
	}
}