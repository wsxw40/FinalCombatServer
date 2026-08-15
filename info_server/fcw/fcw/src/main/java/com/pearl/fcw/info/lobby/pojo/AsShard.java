package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.SYS)
public class AsShard extends BasePojo {

    private static final long serialVersionUID = 7401552271457834190L;

    @Id
    @GeneratedValue
    private int id;
    private int zoneId;
    private int startNodeId;
    private int endNodeId;
    private String dbHost = null;
    private String dbName = null;
    private String dbUsername = null;
    private String dbPassword = null;
    private String isDeleted;
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(int startNodeId) {
        this.startNodeId = startNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(int endNodeId) {
        this.endNodeId = endNodeId;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
