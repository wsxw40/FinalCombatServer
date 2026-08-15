package com.pearl.fcw.gm.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.proto.enums.ENoticeType;

@GoSchema(type = Schema.SYS)
public class WSysNotice extends DmModel {

	private static final long serialVersionUID = 4291145747950897489L;

	private Integer id;

	private Integer type = ENoticeType.NOTICE_ONCE.getNumber();

    private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer noticeTime;

	private String isDeleted = "N";
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

    @Override
	public Integer getId() {
        return id;
    }

    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Integer noticeTime) {
        this.noticeTime = noticeTime;
    }

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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

	@Override
	public boolean getIsRemoved() {
		return "Y".equals(isDeleted);
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDeleted = isRemoved ? "Y" : "N";
	}
}