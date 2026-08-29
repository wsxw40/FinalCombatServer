package com.pearl.o2o.pojo;


public class TeamLevelInfo extends BaseMappingBean<TeamLevelInfo> implements Comparable<TeamLevelInfo>{
	
	private static final long serialVersionUID = 5782547220246919465L;
	
	private int type;
	private String name;
	private String displayName;
	private int refLevelId;			
	private int teamId;
	private Integer createTime;
	private Integer lastUpdateTime;
	private int lastUpdatePlayer;
	
	private String configPoints;
	
	private Integer lastBeginUpdateTime;
	private String isEditable;
	private String isDeleted;
	
	private String formatedConfig;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getRefLevelId() {
		return refLevelId;
	}
	public void setRefLevelId(int refLevelId) {
		this.refLevelId = refLevelId;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	public void setLastUpdatePlayer(int lastUpdatePlayer) {
		this.lastUpdatePlayer = lastUpdatePlayer;
	}
	public String getConfigPoints() {
		return configPoints;
	}
	public void setConfigPoints(String configPoints) {
		this.configPoints = configPoints;
	}
	
	public Integer getCreateTime() {
		return createTime;
	}
	public Integer getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Integer lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getLastBeginUpdateTime() {
		return lastBeginUpdateTime;
	}
	public void setLastBeginUpdateTime(Integer lastBeginUpdateTime) {
		this.lastBeginUpdateTime = lastBeginUpdateTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public String getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}
	
	public int getLastUpdatePlayer() {
		return lastUpdatePlayer;
	}
	
	
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public void setConfigPoints(String[] configs){
		StringBuilder sBuilder=new StringBuilder();
		if(configs.length>0){
			for(int i=0;i<configs.length-1;i++ ){
				sBuilder.append(configs[i]).append(";");
			}
			sBuilder.append(configs[configs.length-1]).append(";");
		}

		this.configPoints=sBuilder.toString();
	}
	
	public String getFormatedConfig() {
		return formatedConfig;
	}
	public void setFormatedConfig(String formatedConfig) {
		this.formatedConfig = formatedConfig;
	}
	@Override
	public int compareTo(TeamLevelInfo o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static final String[] DEFAULT_NEW_TEAM_CONFIG={"5435,0.0,0.7071067,0.0,0.70710677,46.5,8.7510735E-8,13.5;5435,0.0,0.7071067,0.0,0.70710677,31.5,7.63803E-8,11.5;5435,0.0,0.7071067,0.0,0.70710677,21.5,7.62939E-8,10.5;5435,0.0,0.0,0.0,1.0,31.5,7.629391E-8,4.5;5435,0.0,0.7071067,0.0,0.70710677,15.5,3.5460684E-7,16.5;5435,0.0,0.0,0.0,1.0,47.5,-0.0023809706,30.5;",
		"5435,0.0,0.7071067,0.0,0.70710677,47.5,8.938397E-8,13.5;5435,0.0,0.7071067,0.0,0.70710677,27.5,7.62939E-8,10.5;5435,0.0,0.7071067,0.0,0.70710677,18.5,1.4344133E-7,-9.5;5435,0.0,0.7071067,0.0,0.70710677,14.5,7.62939E-8,7.5;5435,0.0,0.7071067,0.0,0.70710677,16.5,3.8882737E-7,15.5;5435,0.0,0.99999994,0.0,-4.371139E-8,31.5,7.62939E-8,-1.5;",
		"5435,0.0,0.0,0.0,1.0,31.5,7.629849E-8,7.5;5435,0.0,0.7071067,0.0,0.70710677,22.5,0.159383,4.5;5435,0.0,0.7071067,0.0,0.70710677,43.5,7.932199E-8,13.5;5435,0.0,0.7071067,0.0,-0.70710677,27.5,0.159383,0.5;5435,0.0,0.7071067,0.0,0.70710677,38.5,-0.0037698313,13.5;5435,0.0,0.7071067,0.0,0.70710677,23.5,0.15938301,-4.5;"};
}
