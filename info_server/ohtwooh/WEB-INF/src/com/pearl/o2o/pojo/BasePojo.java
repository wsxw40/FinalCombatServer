package com.pearl.o2o.pojo;

import java.io.Serializable;

public class BasePojo implements Serializable {
	private static final long serialVersionUID = 5214162456426179355L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
