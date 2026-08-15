package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysStrengthenAppend extends DmModel {

    private static final long serialVersionUID = -2519783319116850933L;

    private Integer id;

    private Integer level;

    private Integer type;

    private Float property1;

    private Float property2;

    private Integer streNum;

    private Integer streGr;

    private Float winRate;

    private Float falseKeepRate;

    private Float falseFallRate;

    private Float falseRuinRate;

    private Float holeWinRate;

    private Float switchFallRate;

	private Float property1Db;

	private Float property2Db;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getProperty1() {
        return property1;
    }

    public void setProperty1(Float property1) {
        this.property1 = property1;
    }

    public Float getProperty2() {
        return property2;
    }

    public void setProperty2(Float property2) {
        this.property2 = property2;
    }

    public Integer getStreNum() {
        return streNum;
    }

    public void setStreNum(Integer streNum) {
        this.streNum = streNum;
    }

    public Integer getStreGr() {
        return streGr;
    }

    public void setStreGr(Integer streGr) {
        this.streGr = streGr;
    }

    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }

    public Float getFalseKeepRate() {
        return falseKeepRate;
    }

    public void setFalseKeepRate(Float falseKeepRate) {
        this.falseKeepRate = falseKeepRate;
    }

    public Float getFalseFallRate() {
        return falseFallRate;
    }

    public void setFalseFallRate(Float falseFallRate) {
        this.falseFallRate = falseFallRate;
    }

    public Float getFalseRuinRate() {
        return falseRuinRate;
    }

    public void setFalseRuinRate(Float falseRuinRate) {
        this.falseRuinRate = falseRuinRate;
    }

    public Float getHoleWinRate() {
        return holeWinRate;
    }

    public void setHoleWinRate(Float holeWinRate) {
        this.holeWinRate = holeWinRate;
    }

    public Float getSwitchFallRate() {
        return switchFallRate;
    }

    public void setSwitchFallRate(Float switchFallRate) {
        this.switchFallRate = switchFallRate;
    }

	public Float getProperty1Db() {
		return property1Db;
	}

	public void setProperty1Db(Float property1Db) {
		this.property1Db = property1Db;
	}

	public Float getProperty2Db() {
		return property2Db;
	}

	public void setProperty2Db(Float property2Db) {
		this.property2Db = property2Db;
	}
}