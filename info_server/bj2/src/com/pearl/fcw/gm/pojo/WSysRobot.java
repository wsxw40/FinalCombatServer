package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.RandomWeight;

@GoSchema(type = Schema.SYS)
public class WSysRobot extends DmModel implements RandomWeight{

	private static final long serialVersionUID = 133456543108634402L;

	private Integer id;

    private String name;

    @Override
	public Integer getId() {
        return id;
    }

    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}