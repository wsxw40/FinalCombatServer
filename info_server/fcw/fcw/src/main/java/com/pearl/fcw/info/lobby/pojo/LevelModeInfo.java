package com.pearl.fcw.info.lobby.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.pearl.fcw.info.lobby.utils.Constants;
import com.pearl.fcw.info.lobby.utils.StringUtil;

public class LevelModeInfo {
	private Integer id;
	private int type;
	private String name;
	private int isSelf;
	private String displayNameCN;
	private String displayName;
	private String description;
	private String zombieInfo;
	private Float levelHorizon;
	private Float targetSpeed;
	private int bloodOffset;
	private int isRushGold;
	private int isMoneyReward;
	private int isVip;
	private int isGm;
	private VehicleInfo vehicleInfo;

	private Set<TeamPoints> startPoints = new HashSet<TeamPoints>();
	private Set<AABBPoints> blastPoints = new LinkedHashSet<AABBPoints>();
	private Set<BannerPoints> bannerPoint = new HashSet<BannerPoints>();
	private List<BossItem> bossItems = new ArrayList<BossItem>();
	private Set<Vector3> rushGlodPoints = new HashSet<Vector3>();
	private Set<WeaponPoint> weaponPoints = new HashSet<WeaponPoint>();
	private Set<Supply> supplies = new HashSet<Supply>();
	private List<VehicleLinePoint> vehicleLine1Points = new ArrayList<VehicleLinePoint>();
	private List<VehicleLinePoint> vehicleLine2Points = new ArrayList<VehicleLinePoint>();
	private List<BossLinePoint> bossLinePoint = new ArrayList<BossLinePoint>();

	public LevelModeInfo() {

	}

	public int getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(int isSelf) {
		this.isSelf = isSelf;
	}

	public LevelModeInfo(SysLevel pojo) {
		this.id = pojo.getId();
		this.type = pojo.getType();
		this.name = pojo.getName();
		this.displayName = pojo.getDisplayName();
		this.isSelf = pojo.getIsSelf();
		this.zombieInfo = pojo.getZombieInfo();
		this.levelHorizon = pojo.getLevelHorizon();
		this.targetSpeed = pojo.getTargetSpeed();
		this.bloodOffset = pojo.getBloodOffset();
		this.isRushGold = pojo.getIsRushGold();
		this.isMoneyReward = pojo.getIsMoneyReward();
		this.isVip = pojo.getIsVip();
		this.isGm = pojo.getIsGm();
		String[] sArray = null;
		// start points
		if (!StringUtil.isEmptyString(pojo.getVehicleInfo())) {
			this.vehicleInfo = VehicleInfo
					.createFromText(pojo.getVehicleInfo());
		}
		// start points
		if (!StringUtil.isEmptyString(pojo.getStartPoints())) {
			sArray = pojo.getStartPoints().split(";");
			for (String s : sArray) {
				this.startPoints.add(TeamPoints.createFromText(s));
			}
		}
		// blastPoints
		if (!StringUtil.isEmptyString(pojo.getBlastPoints())) {
			sArray = pojo.getBlastPoints().split(";");
			for (String s : sArray) {
				this.blastPoints.add(AABBPoints.createFromText(s));
			}
		}
		// REFACTOR : change name
		// bannerPoints
		if (!StringUtil.isEmptyString(pojo.getFlagPoints())) {
			sArray = pojo.getFlagPoints().split(";");
			for (String s : sArray) {
				this.bannerPoint.add(BannerPoints.createFromText(s));
			}
		}
		// bossItems
		if (!StringUtil.isEmptyString(pojo.getBossItems())) {
			sArray = pojo.getBossItems().split(";");
			for (String s : sArray) {
				this.bossItems.add(BossItem.createFromText(s));
			}
		}
		// weaponPoints
		if (!StringUtil.isEmptyString(pojo.getWeaponPoints())) {
			sArray = pojo.getWeaponPoints().split(";");
			for (String s : sArray) {
				this.weaponPoints.add(WeaponPoint.createFromText(s));
			}
		}
		// supplies
		if (!StringUtil.isEmptyString(pojo.getSupplies())) {
			sArray = pojo.getSupplies().split(";");
			for (String s : sArray) {
				this.supplies.add(Supply.createFromText(s));
			}
		}
		// rushGlodPoints
		if (!StringUtil.isEmptyString(pojo.getRushGlodPoint())) {
			sArray = pojo.getRushGlodPoint().split(";");
			for (String s : sArray) {
				this.rushGlodPoints.add(Vector3.createFromText(s));
			}
		}
		// vehicle line
		if (!StringUtil.isEmptyString(pojo.getLineInfo())
				&& (type == Constants.GAMETYPE.DELIVER.getValue() || type == Constants.GAMETYPE.NEWTRAIN
						.getValue())) {
			sArray = pojo.getLineInfo().split(";");
			for (String s : sArray) {
				if (s.indexOf(",") != -1) {
					String[] tArray = s.split(",");
					if (tArray.length != 9) {
						throw new IllegalArgumentException();
					}
					if ("1".equals(tArray[0])) {
						vehicleLine1Points.add(VehicleLinePoint
								.createFromText(s));
					} else if ("2".equals(tArray[0])) {
						vehicleLine2Points.add(VehicleLinePoint
								.createFromText(s));
					}
				}
			}
			Collections.sort(vehicleLine1Points,
					new Comparator<VehicleLinePoint>() {

						@Override
						public int compare(VehicleLinePoint o1,
								VehicleLinePoint o2) {

							return ((Integer) o1.getIndex())
									.compareTo((Integer) o2.getIndex());
						}
					});
			Collections.sort(vehicleLine2Points,
					new Comparator<VehicleLinePoint>() {

						@Override
						public int compare(VehicleLinePoint o1,
								VehicleLinePoint o2) {

							return ((Integer) o1.getIndex())
									.compareTo((Integer) o2.getIndex());
						}
					});
		}
		if (!StringUtil.isEmptyString(pojo.getLineInfo())
				&& type == Constants.GAMETYPE.BOSS2.getValue()) {
			sArray = pojo.getLineInfo().split(";");
			for (String s : sArray) {
				this.bossLinePoint.add(BossLinePoint.createFromText(s));
			}
		}
	}

	public static class TeamPoints {
		private int teamId;
		private Transform point;

		public TeamPoints(int teamId, Transform point) {
			this.teamId = teamId;
			this.point = point;
		}

		public int getTeamId() {
			return teamId;
		}

		public Transform getPoint() {
			return point;
		}

		public void setPoint(Transform point) {
			this.point = point;
		}

		public void setTeamId(int teamId) {
			this.teamId = teamId;
		}

		// id,x,y,z,rotate
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(teamId).append(",").append(point.toPlainString());
			return sb.toString();
		}

		// id,x,y,z,rotate
		public static TeamPoints createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			int index = s.indexOf(',');
			String id = s.substring(0, index);
			String pointStr = s.substring(index + 1);
			return new TeamPoints(Integer.valueOf(id),
					Transform.createFromText(pointStr));
		}
	}

	public static class BannerPoints {
		private int teamId;
		private AABBPoints point;

		public BannerPoints(int teamId, AABBPoints point) {
			this.teamId = teamId;
			this.point = point;
		}

		public int getTeamId() {
			return teamId;
		}

		public AABBPoints getPoint() {
			return point;
		}

		public void setPoint(AABBPoints point) {
			this.point = point;
		}

		public void setTeamId(int teamId) {
			this.teamId = teamId;
		}

		// id,x,y,z,rotate,x1,y1,z1
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(teamId).append(",").append(point.toPlainString());
			return sb.toString();
		}

		// id,x,y,z,rotate,x1,y1,z1
		public static BannerPoints createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			int index = s.indexOf(',');
			String id = s.substring(0, index);
			String pointStr = s.substring(index + 1);
			return new BannerPoints(Integer.valueOf(id),
					AABBPoints.createFromText(pointStr));
		}
	}

	public static class WeaponPoint {
		private int id;// level weapon id
		private Transform transform;
		private int type;// for special game type

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public WeaponPoint(int id, Transform transform, int type) {
			this.id = id;
			this.transform = transform;
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public Transform getTransform() {
			return transform;
		}

		// id,x,y,z,rotate,type
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(id).append(",").append(transform.toPlainString())
					.append(",").append(type);
			return sb.toString();
		}

		// id,x,y,z,rotate,type
		public static WeaponPoint createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] index = s.split(",");
			String id = index[0];
			String transformStr = index[1] + "," + index[2] + "," + index[3]
					+ "," + index[4];
			String type = index[5];
			return new WeaponPoint(Integer.valueOf(id),
					Transform.createFromText(transformStr),
					Integer.valueOf(type));
		}
	}

	public static class Vector3 {
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

		// x,y,z
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(x).append(",").append(y).append(",").append(z);
			return sb.toString();
		}

		// x.y.z
		public static Vector3 createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 3) {
				throw new IllegalArgumentException(s);
			}
			return new Vector3(Float.valueOf(sArray[0]),
					Float.valueOf(sArray[1]), Float.valueOf(sArray[2]));
		}

		public String toString() {
			return "x:" + x + " y:" + y + " z:" + z;
		}
	}

	// blast point
	public static class AABBPoints {
		private Transform transform;
		private Vector3 point;

		public AABBPoints(Transform transform, Vector3 point) {
			this.transform = transform;
			this.point = point;
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

		// x,y,z,rotate,x1,y1,z1
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(transform.toPlainString()).append(",")
					.append(point.toPlainString());
			return sb.toString();
		}

		// x,y,z,rotate,x1,y1,z1
		public static AABBPoints createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 7) {
				throw new IllegalArgumentException();
			}
			String transStr = sArray[0] + "," + sArray[1] + "," + sArray[2]
					+ "," + sArray[3];
			String pointStr = sArray[4] + "," + sArray[5] + "," + sArray[6];
			return new AABBPoints(Transform.createFromText(transStr),
					Vector3.createFromText(pointStr));
		}
	}

	public static class Transform {
		private Vector3 position;
		private float rotate;

		public Transform(Vector3 position, float rotate) {
			this.position = position;
			this.rotate = rotate;
		}

		public Transform(float x, float y, float z, float rotate) {
			this.position = new Vector3(x, y, z);
			this.rotate = rotate;
		}

		public Vector3 getPosition() {
			return position;
		}

		public float getRotate() {
			return rotate;
		}

		// x,y,z,rotate
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(position.toPlainString()).append(",").append(rotate);
			return sb.toString();
		}

		// x.y.z,rotate
		public static Transform createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			int index = s.lastIndexOf(",");
			String vectorStr = s.substring(0, index);
			String rotate = s.substring(index + 1);
			return new Transform(Vector3.createFromText(vectorStr),
					Float.valueOf(rotate));
		}

		public String toString() {
			return position.toString() + " r:" + rotate;
		}
	}

	public static class BossItem {
		private int itemId;
		private float paramA;
		private float paramB;
		private int num;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public BossItem(int itemId, float paramA, float paramB, int num) {
			this.itemId = itemId;
			this.paramA = paramA;
			this.paramB = paramB;
			this.num = num;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public float getParamA() {
			return paramA;
		}

		public void setParamA(float paramA) {
			this.paramA = paramA;
		}

		public float getParamB() {
			return paramB;
		}

		public void setParamB(float paramB) {
			this.paramB = paramB;
		}

		// type,name,team,value,appearRatio,skillTime
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(itemId).append(",").append(paramA).append(",")
					.append(",").append(paramB).append(",").append(num);
			return sb.toString();
		}

		// type,name,team,value,appearRatio,skillTime
		public static BossItem createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 4) {
				throw new IllegalArgumentException();
			}
			return new BossItem(Integer.valueOf(sArray[0]),
					Float.valueOf(sArray[1]), Float.valueOf(sArray[2]),
					Integer.valueOf(sArray[3]));
		}
	}

	public static class Supply {
		private Vector3 vector;
		private int type;
		private String name;
		private float appearRation;
		// type = weapon - id
		// type = skill - skill time
		// type = item - item number
		private Integer value;
		// only occured if it's skill
		private float skillTime;

		// TODO move to other place

		public Supply(Vector3 vector, int type, String name, int value,
				float appearRation, float skillTime) {
			this.vector = vector;
			this.type = type;
			this.name = name;
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

		public int getValue() {
			return value;
		}

		public Vector3 getVector() {
			return vector;
		}

		public float getAppearRation() {
			return appearRation;
		}

		public float getSkillTime() {
			return skillTime;
		}

		// type,name,team,value,appearRatio,skillTime
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(vector.toPlainString()).append(type).append(",")
					.append(name).append(",").append(",").append(value)
					.append(",").append(appearRation).append(",")
					.append(skillTime);
			return sb.toString();
		}

		// type,name,team,value,appearRatio,skillTime
		public static Supply createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 8) {
				throw new IllegalArgumentException();
			}
			Vector3 v = new Vector3(Float.parseFloat(sArray[0]),
					Float.parseFloat(sArray[1]), Float.parseFloat(sArray[2]));
			return new Supply(v, Integer.valueOf(sArray[3]), sArray[4],
					Integer.valueOf(sArray[5]), Float.valueOf(sArray[6]),
					Float.valueOf(sArray[7]));
		}
	}

	public static class VehicleLinePoint {
		private final int lineId;
		private final int index;
		private Vector3 vector;
		private Vector3 vector2;
		private int isSlope = 0;

		public int getIsSlope() {
			return isSlope;
		}

		public VehicleLinePoint(int lineId, int index, Vector3 vector,
				Vector3 vector2, int isSlope) {
			this.lineId = lineId;
			this.index = index;
			this.vector = vector;
			this.vector2 = vector2;
			this.isSlope = isSlope;
		}

		public int getLineId() {
			return lineId;
		}

		public int getIndex() {
			return index;
		}

		public Vector3 getVecter() {
			return vector;
		}

		public Vector3 getVecter2() {
			return vector2;
		}

		// lineid,index,x,y,z,x,y,z
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(lineId).append(",").append(index).append(",")
					.append(vector.toPlainString()).append(",")
					.append(vector2.toPlainString()).append(",")
					.append(isSlope);
			return sb.toString();
		}

		// lineid,index,x,y,z,x,y,z,isslope
		public static VehicleLinePoint createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 9) {
				throw new IllegalArgumentException(s);
			}
			Vector3 v = new Vector3(Float.valueOf(sArray[2]),
					Float.valueOf(sArray[3]), Float.valueOf(sArray[4]));
			Vector3 v2 = new Vector3(Float.valueOf(sArray[5]),
					Float.valueOf(sArray[6]), Float.valueOf(sArray[7]));
			return new VehicleLinePoint(Integer.valueOf(sArray[0]),
					Integer.valueOf(sArray[1]), v, v2,
					Integer.valueOf(sArray[8]));
		}
	}

	public static class BossLinePoint {
		private final int lineId;
		private final int index;
		private Vector3 vector;
		private Vector3 vector2;
		private int isSlope = 0;

		public int getIsSlope() {
			return isSlope;
		}

		public BossLinePoint(int lineId, int index, Vector3 vector,
				Vector3 vector2, int isSlope) {
			this.lineId = lineId;
			this.index = index;
			this.vector = vector;
			this.vector2 = vector2;
			this.isSlope = isSlope;
		}

		public int getLineId() {
			return lineId;
		}

		public int getIndex() {
			return index;
		}

		public Vector3 getVecter() {
			return vector;
		}

		public Vector3 getVecter2() {
			return vector2;
		}

		// lineid,index,x,y,z,round,y,moveId,isslope
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(lineId).append(",").append(index).append(",")
					.append(vector.toPlainString()).append(",")
					.append(vector2.toPlainString()).append(",")
					.append(isSlope);
			return sb.toString();
		}

		// lineid,index,x,y,z,round,y,moveId,isslope
		public static BossLinePoint createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 9) {
				throw new IllegalArgumentException(s);
			}
			Vector3 v = new Vector3(Float.valueOf(sArray[2]),
					Float.valueOf(sArray[3]), Float.valueOf(sArray[4]));
			Vector3 v2 = new Vector3(Float.valueOf(sArray[5]),
					Float.valueOf(sArray[6]), Float.valueOf(sArray[7]));
			return new BossLinePoint(Integer.valueOf(sArray[0]),
					Integer.valueOf(sArray[1]), v, v2,
					Integer.valueOf(sArray[8]));
		}
	}

	public static class VehicleInfo {
		private final int addBlood;
		private Vector3 vector;

		public VehicleInfo(int addBlood, Vector3 vector) {
			this.addBlood = addBlood;
			this.vector = vector;
		}

		public Vector3 getVector() {
			return vector;
		}

		public void setVector(Vector3 vector) {
			this.vector = vector;
		}

		public int getAddBlood() {
			return addBlood;
		}

		// addBlood,x,y,z
		public String toPlainString() {
			StringBuilder sb = new StringBuilder();
			sb.append(addBlood).append(",").append(vector.toPlainString());
			return sb.toString();
		}

		// lineid,index,x,y,z,x,y,z
		public static VehicleInfo createFromText(String s) {
			if (StringUtil.isEmptyString(s)) {
				return null;
			}
			String[] sArray = s.split(",");
			if (sArray.length != 4) {
				throw new IllegalArgumentException(s);
			}
			Vector3 v = new Vector3(Float.valueOf(sArray[1]),
					Float.valueOf(sArray[2]), Float.valueOf(sArray[3]));
			return new VehicleInfo(Integer.valueOf(sArray[0]), v);
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

	public Set<AABBPoints> getBlastPoints() {
		return blastPoints;
	}

	public void setBlastPoints(Set<AABBPoints> blastPoints) {
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

	public List<BossItem> getBossItems() {
		return bossItems;
	}

	public void setBossItems(List<BossItem> bossItems) {
		this.bossItems = bossItems;
	}

	public Set<TeamPoints> getStartPoints() {
		return startPoints;
	}

	public void setStartPoints(Set<TeamPoints> startPoints) {
		this.startPoints = startPoints;
	}

	public Set<BannerPoints> getBannerPoint() {
		return bannerPoint;
	}

	public void setBannerPoint(Set<BannerPoints> bannerPoint) {
		this.bannerPoint = bannerPoint;
	}

	public boolean hasRelatedWeapon() {

		return this.getWeaponPoints().size() > 0;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getZombieInfo() {
		return zombieInfo;
	}

	public void setZombieInfo(String zombieInfo) {
		this.zombieInfo = zombieInfo;
	}

	public Float getLevelHorizon() {
		return levelHorizon;
	}

	public void setLevelHorizon(Float levelHorizon) {
		this.levelHorizon = levelHorizon;
	}

	public List<VehicleLinePoint> getVehicleLine1Points() {
		return vehicleLine1Points;
	}

	public void setVehicleLine1Points(List<VehicleLinePoint> vehicleLine1Points) {
		this.vehicleLine1Points = vehicleLine1Points;
	}

	public List<VehicleLinePoint> getVehicleLine2Points() {
		return vehicleLine2Points;
	}

	public void setVehicleLine2Points(List<VehicleLinePoint> vehicleLine2Points) {
		this.vehicleLine2Points = vehicleLine2Points;
	}

	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public Float getTargetSpeed() {
		return targetSpeed;
	}

	public void setTargetSpeed(Float targetSpeed) {
		this.targetSpeed = targetSpeed;
	}

	public int getBloodOffset() {
		return bloodOffset;
	}

	public void setBloodOffset(int bloodOffset) {
		this.bloodOffset = bloodOffset;
	}

	public int getIsRushGold() {
		return isRushGold;
	}

	public void setIsRushGold(int isRushGold) {
		this.isRushGold = isRushGold;
	}

	public int getIsMoneyReward() {
		return isMoneyReward;
	}

	public void setIsMoneyReward(int isMoneyReward) {
		this.isMoneyReward = isMoneyReward;
	}

	public Set<Vector3> getRushGlodPoints() {
		return rushGlodPoints;
	}

	public void setRushGlodPoints(Set<Vector3> rushGlodPoints) {
		this.rushGlodPoints = rushGlodPoints;
	}

	public int getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public List<BossLinePoint> getBossLinePoint() {
		return bossLinePoint;
	}

	public void setBossLinePoint(List<BossLinePoint> bossLinePoint) {
		this.bossLinePoint = bossLinePoint;
	}

	public String getDisplayNameCN() {
		return displayNameCN;
	}

	public void setDisplayNameCN(String displayNameCN) {
		this.displayNameCN = displayNameCN;
	}

	public int getIsGm() {
		return isGm;
	}

	public void setIsGm(int isGm) {
		this.isGm = isGm;
	}
}
