package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;

@GoSchema(type = Schema.EXT)
public class WPlayerInfo extends DmExtModel {

    private static final long serialVersionUID = 6271509170997923338L;

    private Integer id;

    private Integer playerId;

    private Integer xunleiPoint;

	private String caches;

	private JsonPlayerInfoCaches cachesEntity = new JsonPlayerInfoCaches();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getXunleiPoint() {
        return xunleiPoint;
    }

    public void setXunleiPoint(Integer xunleiPoint) {
        this.xunleiPoint = xunleiPoint;
    }

	public String getCaches() {
		return caches;
	}

	public void setCaches(String caches) {
		this.caches = caches;
	}

	public JsonPlayerInfoCaches getCachesEntity() {
		return cachesEntity;
	}

	public void setCachesEntity(JsonPlayerInfoCaches cachesEntity) {
		this.cachesEntity = cachesEntity;
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
    public Integer getShareAmount() {
        return null;
    }
}