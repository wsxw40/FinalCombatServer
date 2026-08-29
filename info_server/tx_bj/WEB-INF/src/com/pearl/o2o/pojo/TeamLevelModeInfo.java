package com.pearl.o2o.pojo;

import java.util.HashSet;
import java.util.Set;

import com.pearl.o2o.utils.StringUtil;
/**
 * 战队资源战
 * @author chenyifan
 *
 */
public class TeamLevelModeInfo extends LevelModeInfo{
	private Integer id;
	private int type;
	private String name;
	private String displayName;
	private int refLevelId;
	private int teamId;
	private long createTime;
	private long lastUpdateTime;
	private int lastUpdatePlayer;
	private long lastBeginUpdateTime;
	private String isEditable;

	private TeamLevelInfo teamLevelInfo;
	private Set<ConfigPoint> configPoints=new HashSet<ConfigPoint>();

	public TeamLevelModeInfo(){
		
	}
	
	public TeamLevelModeInfo(TeamLevelInfo teamLevelInfo){
		this.teamLevelInfo = teamLevelInfo;
		this.id=teamLevelInfo.getId();
		this.name=teamLevelInfo.getName();
		this.displayName=teamLevelInfo.getDisplayName();
		this.type=teamLevelInfo.getType();
		this.refLevelId=teamLevelInfo.getRefLevelId();
		this.teamId=teamLevelInfo.getTeamId();
		this.createTime=teamLevelInfo.getCreateTime();
		this.lastUpdatePlayer=teamLevelInfo.getLastUpdatePlayer();
		this.lastUpdateTime=teamLevelInfo.getLastUpdateTime();
		this.lastBeginUpdateTime=teamLevelInfo.getLastBeginUpdateTime();
		this.isEditable=teamLevelInfo.getIsEditable();
		
		if(teamLevelInfo.getConfigPoints()!=null && teamLevelInfo.getConfigPoints().contains(";")){
			String[] allConfigPointsString=teamLevelInfo.getConfigPoints().split(";");
			for(String s: allConfigPointsString){
				this.configPoints.add(ConfigPoint.createFromText(s));
			}
		}
	}
	

	public static class ConfigPoint{
		private int itemId;
		private Transform transform;
		private Vector3 point;
		public ConfigPoint(int itemId,Transform transform,Vector3 point) {
			
			this.itemId=itemId;
			this.transform=transform;
			this.point=point;
		}
		public Transform getTransform() {
			return transform;
		}
		public void setTransform(Transform transform) {
			this.transform = transform;
		}
		
		public Vector3 getPoint() {
			return point;
		}
		public void setPoint(Vector3 point) {
			this.point = point;
		}
	
		public int getItemId() {
			return itemId;
		}
		public void setItemId(int itemId) {
			this.itemId = itemId;
		}
		//x,y,z,rotate,x1,y1,z1
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(itemId).append(",").append(transform.toPlainString()).append(",").append(point.toPlainString());
			return sb.toString();
		}
		//x,y,z,rotate,x1,y1,z1
		public static ConfigPoint createFromText(String s){
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length!=8) {
				throw new IllegalArgumentException();
			}
			String itemId=sArray[0];
			String transStr=sArray[1]+","+sArray[2]+","+sArray[3]+","+sArray[4];
			String pointStr=sArray[5]+","+sArray[6]+","+sArray[7];
			return new ConfigPoint(Integer.valueOf(itemId),Transform.createFromText(transStr),Vector3.createFromText(pointStr));
		}
	}

	
	public TeamLevelInfo getTeamLevelInfo(String config) {
		teamLevelInfo.setConfigPoints(config);
		return teamLevelInfo;
	}

	public void setTeamLevelInfo(TeamLevelInfo teamLevelInfo) {
		this.teamLevelInfo = teamLevelInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getLastUpdatePlayer() {
		return lastUpdatePlayer;
	}

	public void setLastUpdatePlayer(int lastUpdatePlayer) {
		this.lastUpdatePlayer = lastUpdatePlayer;
	}

	public Set<ConfigPoint> getConfigPoints() {
		return configPoints;
	}

	public void setConfigPoints(Set<ConfigPoint> configPoints) {
		this.configPoints = configPoints;
	}

	public long getLastBeginUpdateTime() {
		return lastBeginUpdateTime;
	}

	public void setLastBeginUpdateTime(long lastBeginUpdateTime) {
		this.lastBeginUpdateTime = lastBeginUpdateTime;
	}

	public String getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}

	
}
