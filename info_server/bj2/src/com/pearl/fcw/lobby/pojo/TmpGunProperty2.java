package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;
import java.util.List;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.NEST)
public class TmpGunProperty2 implements Serializable {

    private static final long serialVersionUID = 2897482625267944798L;

	private Number index;
	private Number value;
	private Number value2;
	private Number time;

    public TmpGunProperty2() {
    }

	public TmpGunProperty2(List<Number> list) {
		if (null == list || list.isEmpty()) {
			return;
		}
		if (list.size() > 0) {
			this.index = list.get(0);
		}
		if (list.size() > 1) {
			this.value = list.get(1);
		}
		if (list.size() > 2) {
			this.value2 = list.get(2);
		}
		if (list.size() > 3) {
			this.time = list.get(3);
		}
	}

    public TmpGunProperty2(Number index, Number value, Number value2, Number time) {
        this.index = index;
        this.value = value;
        this.value2 = value2;
        this.time = time;
    }

    public Number getIndex() {
        return index;
    }

    public void setIndex(Number index) {
        this.index = index;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Number getValue2() {
        return value2;
    }

    public void setValue2(Number value2) {
        this.value2 = value2;
    }

    public Number getTime() {
        return time;
    }

    public void setTime(Number time) {
        this.time = time;
    }
}
