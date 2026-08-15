package com.pearl.fcw.gm.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.RandomWeight;

@GoSchema(type = Schema.SYS)
public class WSysItemGunProperty extends DmModel implements RandomWeight {

	private static final long serialVersionUID = -716428539260823590L;

	private Integer id;

    private Integer index;

    private Integer value;

    private Integer value2;

    private Integer time;

    private String isDeleted;

	private String multiType;

	private String descI18n;

	private String remark;

	private Map<Integer, List<Integer>> multiTypeMap = new HashMap<>();

    @Override
	public Integer getId() {
        return id;
    }

    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

	public String getMultiType() {
		return multiType;
	}

	public void setMultiType(String multiType) {
		this.multiType = multiType;
	}

	public Map<Integer, List<Integer>> getMultiTypeMap() {
		return multiTypeMap;
	}

	public void setMultiTypeMap(Map<Integer, List<Integer>> multiTypeMap) {
		this.multiTypeMap = multiTypeMap;
	}

	public String getDescI18n() {
		return descI18n;
	}

	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public boolean getIsRemoved() {
		return "N".equals(isDeleted);
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDeleted = isRemoved ? "Y" : "N";
	}

}