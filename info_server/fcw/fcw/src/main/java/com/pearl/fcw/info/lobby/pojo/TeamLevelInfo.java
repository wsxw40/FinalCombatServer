package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.SYS)
public class TeamLevelInfo extends BasePojo {

	private static final long serialVersionUID = 5782547220246919465L;
	@Id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
