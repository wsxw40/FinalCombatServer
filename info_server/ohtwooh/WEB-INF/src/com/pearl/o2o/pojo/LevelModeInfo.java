package com.pearl.o2o.pojo;

import java.util.HashSet;
import java.util.Set;

import com.pearl.o2o.utils.StringUtil;

public class LevelModeInfo {
	private Integer id;
	private int type;
	private String name;
	
	private Set<TeamPoints> startPoints = new HashSet<TeamPoints>() ;
	private Set<Vector3> blastPoints = new HashSet<Vector3>();
	private Set<TeamPoints> bannerPoint = new HashSet<TeamPoints>();
	private Set<Vector3> supplyPoints = new HashSet<Vector3>();
	private Set<WeaponPoint>  weaponPoints = new HashSet<WeaponPoint>();
	private Set<Supply> supplies = new HashSet<Supply>();
	
	public LevelModeInfo(){
		
	}
	
	public LevelModeInfo(LevelInfoPojo pojo){
		this.id = pojo.getId();
		this.type = pojo.getType();
		this.name = pojo.getName();
		String[] sArray = null;
		//start points
		if (!StringUtil.isEmptyString(pojo.getStartPoints())) {
			sArray = pojo.getStartPoints().split(";");
			for (String s : sArray) {
				this.startPoints.add(TeamPoints.createFromText(s));
			}
		}
		//blastPoints
		if (!StringUtil.isEmptyString(pojo.getBlastPoints())) {
			sArray = pojo.getBlastPoints().split(";");
			for (String s : sArray) {
				this.blastPoints.add(Vector3.createFromText(s));
			}
		}
		//REFACTOR : change name
		//bannerPoints
		if (!StringUtil.isEmptyString(pojo.getFlagPoints())) {
			sArray = pojo.getFlagPoints().split(";");
			for (String s : sArray) {
				this.bannerPoint.add(TeamPoints.createFromText(s));
			}
		}
		//supplyPoints
		if (!StringUtil.isEmptyString(pojo.getSupplyPoints())) {
			sArray = pojo.getSupplyPoints().split(";");
			for (String s : sArray) {
				this.supplyPoints.add(Vector3.createFromText(s));
			}
		}
		//weaponPoints
		if (!StringUtil.isEmptyString(pojo.getWeaponPoints())) {
			sArray = pojo.getWeaponPoints().split(";");
			for (String s : sArray) {
				this.weaponPoints.add(WeaponPoint.createFromText(s));
			}
		}
		//supplies
		if (!StringUtil.isEmptyString(pojo.getSupplies())) {
			sArray = pojo.getSupplies().split(";");
			for (String s : sArray) {
				this.supplies.add(Supply.createFromText(s));
			}
		}
	}
	
	public LevelInfoPojo toPojo(){
		LevelInfoPojo pojo = new LevelInfoPojo();
		pojo.setId(this.id);
		pojo.setType(this.type);
		pojo.setName(this.name);
		StringBuilder sb = new StringBuilder();
		
		for (TeamPoints v : this.startPoints){
			sb.append(v.toPlainString()).append(";");
		}
		pojo.setStartPoints(sb.toString());
		
		sb  = new StringBuilder();
		for (Vector3 v : this.blastPoints){
			sb.append(v.toPlainString()).append(";");
		}
		pojo.setBlastPoints(sb.toString());
		
		sb = new StringBuilder();
		for (TeamPoints v : this.bannerPoint){
			sb.append(v.toPlainString()).append(";");
		}
		pojo.setFlagPoints(sb.toString());
		
		sb  = new StringBuilder();
		for (Vector3 v : this.supplyPoints){
			sb.append(v.toPlainString()).append(";");
		}
		pojo.setSupplyPoints(sb.toString());
	
		sb  = new StringBuilder();
		for (WeaponPoint wp : this.weaponPoints) {
			sb.append(wp.toPlainString()).append(";");
		}
		pojo.setWeaponPoints(sb.toString());
		
		sb  = new StringBuilder();
		for (Supply sp : this.supplies) {
			sb.append(sp.toPlainString()).append(";");
		}
		pojo.setSupplies(sb.toString());
		
		return pojo;
	}
	
	public static class TeamPoints{
		private int teamId;
		private Transform transform;
		
		public TeamPoints(int teamId, Transform transform) {
			this.teamId = teamId;
			this.transform = transform;
		}
		
		public int getTeamId() {
			return teamId;
		}
		public Transform getTransform() {
			return transform;
		}

		//id,x,y,z,rotate
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(teamId).append(",").append(transform.toPlainString());
			return sb.toString();
		}
		//id,x,y,z,rotate
		public static TeamPoints createFromText(String s){
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			int index = s.indexOf(',');
			String id = s.substring(0, index);
			String transformStr = s.substring(index + 1); 
			return new TeamPoints(Integer.valueOf(id), Transform.createFromText(transformStr));
		}
	}
	
	public static class WeaponPoint{
		private int id;//level weapon id
		private Transform transform;
		
		public WeaponPoint(int id, Transform transform) {
			this.id = id;
			this.transform = transform;
		}

		public int getId() {
			return id;
		}

		public Transform getTransform() {
			return transform;
		}
		
		//id,x,y,z,rotate
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(id).append(",").append(transform.toPlainString());
			return sb.toString();
		}
		//id,x,y,z,rotate
		public static WeaponPoint createFromText(String s){
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			int index = s.indexOf(',');
			String id = s.substring(0, index);
			String transformStr = s.substring(index + 1); 
			return new WeaponPoint(Integer.valueOf(id), Transform.createFromText(transformStr));
		}
	}
	
	public static class Vector3{
		private final float x;
		private final float y;
		private final float z;
		
		public Vector3(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}
		//x,y,z
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(x).append(",").append(y).append(",").append(z);
			return sb.toString();
		}
		//x.y.z
		public static Vector3 createFromText(String s){
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if(sArray.length != 3){
				throw new IllegalArgumentException(s);
			}
			return new Vector3(Float.valueOf(sArray[0]),Float.valueOf(sArray[1]),Float.valueOf(sArray[2]));
		}
		
		public String toString(){
			return "x:" + x + " y:" + y + " z:" + z;
		}
	}
	
	public static class Transform{
		private Vector3 position;
		private float rotate;
		
		public Transform(Vector3 position, float rotate) {
			this.position = position;
			this.rotate = rotate;
		}
		
		public Transform(float x, float y,float z, float rotate){
			this.position = new Vector3(x,y,z);
			this.rotate = rotate;
		}
		
		public Vector3 getPosition() {
			return position;
		}

		public float getRotate() {
			return rotate;
		}
		//x,y,z,rotate
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(position.toPlainString()).append(",").append(rotate);
			return sb.toString();
		}
		//x.y.z,rotate
		public static Transform createFromText(String s){
			if(StringUtil.isEmptyString(s)){
				return null;
			}
			int index = s.lastIndexOf(",");
			String vectorStr = s.substring(0, index);
			String rotate = s.substring(index +1);
			return new Transform(Vector3.createFromText(vectorStr), Float.valueOf(rotate));
		}
		
		public String toString(){
			return position.toString() + " r:" + rotate;
		}
	}
	
	public static class Supply {
		private int type;
		private String name;
		private int team;
		private float appearRation;
		//type = weapon - id
		//type = skill  - skill time
		//type = item   - item number
		private float value;
		//only occured if it's skill 
		private float skillTime;
		//TODO move to other place
		public static final int TYPE_WEAPON = 1;
		
		public Supply(int type, String name, int team, float value, float appearRation, float skillTime) {
			this.type = type;
			this.name = name;
			this.team = team;
			this.value = value;
			this.appearRation = appearRation;
			this.skillTime = skillTime;
		}
		
		public int getType() {
			return type;
		}
		public String getName() {
			return name;
		}
		public int getTeam() {	
			return team;
		}
		public float getValue() {
			return value;
		}
		
		public float getAppearRation() {
			return appearRation;
		}
		
		
		
		public float getSkillTime() {
			return skillTime;
		}

		//type,name,team,value,appearRatio,skillTime
		public String toPlainString(){
			StringBuilder sb = new StringBuilder();
			sb.append(type).append(",").append(name).append(",").append(team)
					.append(",").append(value).append(",").append(appearRation)
					.append(",").append(skillTime);
			return sb.toString();
		}
		//type,name,team,value,appearRatio,skillTime
		public static Supply createFromText(String s){
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length!=6) {
				throw new IllegalArgumentException();
			}
			return new Supply(Integer.valueOf(sArray[0]), sArray[1],
					Integer.valueOf(sArray[2]), Float.valueOf(sArray[3]),
					Float.valueOf(sArray[4]), Float.valueOf(sArray[5]));
		}
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
	

	public Set<Vector3> getBlastPoints() {
		return blastPoints;
	}

	public void setBlastPoints(Set<Vector3> blastPoints) {
		this.blastPoints = blastPoints;
	}

	public Set<WeaponPoint> getWeaponPoints() {
		return weaponPoints;
	}

	public void setWeaponPoints(Set<WeaponPoint> weaponPoints) {
		this.weaponPoints = weaponPoints;
	}

	public Set<Supply> getSupplies() {
		return supplies;
	}

	public void setSupplies(Set<Supply> supplies) {
		this.supplies = supplies;
	}

	public Set<Vector3> getSupplyPoints() {
		return supplyPoints;
	}

	public void setSupplyPoints(Set<Vector3> supplyPoints) {
		this.supplyPoints = supplyPoints;
	}

	public Set<TeamPoints> getStartPoints() {
		return startPoints;
	}

	public void setStartPoints(Set<TeamPoints> startPoints) {
		this.startPoints = startPoints;
	}

	public Set<TeamPoints> getBannerPoint() {
		return bannerPoint;
	}

	public void setBannerPoint(Set<TeamPoints> bannerPoint) {
		this.bannerPoint = bannerPoint;
	}
	
	public boolean hasRelatedWeapon(){
		for(Supply supply : this.supplies){  
			if (supply.getType() == Supply.TYPE_WEAPON) {
				return true;
			}
		}
		return this.getWeaponPoints().size() >0;
	}
}
