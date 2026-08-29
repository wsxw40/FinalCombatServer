package com.pearl.o2o.pojo;

public class Server extends BaseMappingBean<Server> {
	private static final long serialVersionUID = -3194044116691123511L;

	private String name;
	private Integer online = 0;
	private Integer maxOnline = 0;
	private Integer max = 1000;
	private Integer min = 1;
	private Integer maxTeam = 10;
	private Integer minTeam = 1;
	private int isNew;
	//private Map<Integer,Channel> channelMap;
	private int isChange;
	private int minFightNum;//鏈�皬缁煎悎鎴樻枟鍔涢檺鍒�
	private String gameType;
	//zlm2015-10-9-匹配-服务器-开始
	private int serverType;//服务器类型0默认 1匹配
	private String picture;

	public int getServerType() {
		return serverType;
	}

	public void setServerType(int serverType) {
		this.serverType = serverType;
	}

	//zlm2015-10-9-匹配-服务器-结束
	//zlm2016-06-07-匹配-服务器-索引-备注说明—开始
	private int index;//服务器排序索引
	private String remark;//服务器备注说明

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//zlm2016-06-07-匹配-服务器-索引-备注说明—结束
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public int getIsChange() {
		return isChange;
	}

	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMaxOnline() {
		return maxOnline;
	}

	public void setMaxOnline(Integer maxOnline) {
		this.maxOnline = maxOnline;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public int getMinFightNum() {
		return minFightNum;
	}

	public void setMinFightNum(int minFightNum) {
		this.minFightNum = minFightNum;
	}

	public Integer getMaxTeam() {
		return maxTeam;
	}

	public void setMaxTeam(Integer maxTeam) {
		this.maxTeam = maxTeam;
	}

	public Integer getMinTeam() {
		return minTeam;
	}

	public void setMinTeam(Integer minTeam) {
		this.minTeam = minTeam;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
