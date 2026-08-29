package com.pde.uweb.database.cdkey.gamecdkeychannel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyChannelDao,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public class AbstractGameCdKeyChannelPojo {

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private long gameCdKeyTypeId;
	
		private int enable;
    @JsonIgnore
    private Date createDate;
    @JsonIgnore
    private Date lastModifyDate;

    @JsonIgnore
    private long version;
	
	
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	
		public long getGameCdKeyTypeId() {
			return gameCdKeyTypeId;
		}
		public void setGameCdKeyTypeId(long gameCdKeyTypeId) {
			this.gameCdKeyTypeId = gameCdKeyTypeId;
		}
	
		public int getEnable() {
			return enable;
		}
		public void setEnable(int enable) {
			this.enable = enable;
		}
	
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
	
		public Date getLastModifyDate() {
			return lastModifyDate;
		}
		public void setLastModifyDate(Date lastModifyDate) {
			this.lastModifyDate = lastModifyDate;
		}
	
		public long getVersion() {
			return version;
		}
		public void setVersion(long version) {
			this.version = version;
		}

    @Override
    public String toString() {
        return "AbstractGameCdKeyChannelPojo{" +
                "id=" + id +
                ", gameCdKeyTypeId=" + gameCdKeyTypeId +
                ", enable=" + enable +
                ", createDate=" + createDate +
                ", lastModifyDate=" + lastModifyDate +
                ", version=" + version +
                '}';
    }
}