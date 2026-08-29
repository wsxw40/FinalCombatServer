package com.pearl.fcw.info.lobby.pojo;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.config.annotation.CountPerGroup;
import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;
import com.pearl.fcw.info.core.persistence.utils.ItemIntensifyUtil;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;
import com.pearl.fcw.info.lobby.utils.Constants;

@Entity
@Schema(GoSchema.EXT)
@CountPerGroup(GoCountPerGroup.LARGE)
public class PlayerItem implements PlayerStorage,BaseShardingEntity
{
	private static final long serialVersionUID = -8219382432859480599L;

	@Id
	@GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 private Integer userId;
	 private int playerId;
	 private Integer itemId;
	@Transient private Date validDate;
	@Transient private Date expireDate;
	@Transient private String isBind;
	@Transient private String isDefault;
	@Transient private String isDeleted;
	@Transient private String isGift;
	// join column
	@Transient private Integer pack = 0;
	@Transient private Integer buff = 0;
	@Transient private Integer seq;

	@Transient private Float durable = 100f;
	@Transient private int durableInt = 100;
	@Transient private long createTime;
	@Transient private Integer playerItemCurrency;// playerItem's currency
	@Transient private Integer playerItemUnitType;
	@Transient private Integer playerItemUnit1;// 如果是基于时间的，用于计算过期日期

	@Transient private Integer quantity = 1;

	@Transient private String modifiedDesc;
	@Transient private Float cResistanceFire = 0F;
	@Transient private Float cResistanceBlast = 0F;
	@Transient private Float cResistanceBullet = 0F;
	@Transient private Float cResistanceKnife = 0F;
	@Transient private Float cBloodAdd = 0F;

	@Transient private int fightNum = 0;

	@Transient private List<PlayerItem> parts;
	@Transient private List<PlayerItem> packages;

	// join table sys_package
	@Transient private List<SysItem> gifts;

	// Not Stored in Database
	@Transient private float recoil;// 后坐力
	@Transient private float accuracy;// 精度
	@Transient private float shootSpeed;
	@Transient private float weight;
	@Transient private float damange;
	@Transient private float stopPpower;
	@Transient private int repairCost;
	@Transient private float range;
	@Transient private Integer direction[];
	@Transient private String prices[];
	@Transient private String pSuits[];
	@Transient private String resource[];
	@Transient private ArrayList<String> resources;
	@Transient private Integer timeLeft;
	@Transient private GunProperty gunProperty = new GunProperty();
	@Transient private String gunProperty1 = "";
	@Transient private String gunProperty2 = "";
	@Transient private String gunProperty3 = "";
	@Transient private String gunProperty4 = "";
	@Transient private String gunProperty5 = "";
	@Transient private String gunProperty6 = "";
	@Transient private String gunProperty7 = "";

	@Transient private String gunProperty8 = ""; // 工程兵属性字段

	@Transient private Integer gst_level; // 精英合成等级（武器星级）
	@Transient private Integer gst_level_exp; // 精英合成经验

	@Transient private int damage;
	@Transient private float fireTime;

	// Not Stored in Database
	@Transient private List<Integer> gunPropertyList1 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList2 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList3 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList4 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList5 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList6 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList7 = new ArrayList<Integer>();

	// Join table ITEM_MOD
	@Transient private Integer parentItemId;

	@Transient private String itemName;

	@Transient private String itemDisplayName;

	@Transient private SysItem sysItem;

	@Transient private int type;

	@Transient private int leftSeconds = 0;

	@Transient private int holeNum = 0;// 物品已经开放的孔数=开放未打孔+已经打孔的

	@Transient private int materialNeed = 0;
	@Transient private int gpNeed = 0;

	@Transient private float nextDamange = 0;

	@Transient private float nextShootSpeed = 0;

	@Transient private int nextFightNum = 0;

	@Transient private float nextCResistanceFire = 0;
	@Transient private float nextCResistanceBlast = 0;
	@Transient private float nextCResistanceBullet = 0;
	@Transient private float nextCResistanceKnife = 0;
	@Transient private float nextBloodAdd = 0;
	@Transient private int nextHoleNum = 0;
	@Transient private int nextColor = 0;
	@Transient private int baseItemFightNum;// 基础战斗力
	@Transient private int strengthenItemFightNum; // 强化后增加的战斗力
	@Transient private String meltingItemStr;

	// zlm2015-5-7-限时装备-开始
	@Transient private int provisional_item_day = 60001;// 续费时间 60001为默认返回指，表示不受续费限制
	@Transient private Boolean provisional_item_flag;// 是否是限时装备 false不是限时装备 true是限时装备

	public int getProvisional_item_day() {
		return provisional_item_day;
	}

	public void setProvisional_item_day(int provisional_item_day) {
		this.provisional_item_day = provisional_item_day;
	}

	public boolean getProvisional_item_flag() {
		if (this.provisional_item_flag == null) {
			provisional_item_flag = CommonUtil.provisional_item_scope_flag(this
					.getItemId());
		}
		return this.provisional_item_flag;
	}

	// zlm2015-5-7-限时装备-结束

	public float getNextDamange() {
		return nextDamange;
	}

	public void setNextDamange(int nextDamange) {
		this.nextDamange = nextDamange;
	}

	public float getNextShootSpeed() {
		return nextShootSpeed;
	}

	public void setNextShootSpeed(float nextShootSpeed) {
		this.nextShootSpeed = nextShootSpeed;
	}

	public void setNextFightNum(int nextStar) {
		this.nextFightNum = nextStar;
	}

	public float getNextCResistanceFire() {
		return nextCResistanceFire;
	}

	public void setNextCResistanceFire(float nextCResistanceFire) {
		this.nextCResistanceFire = nextCResistanceFire;
	}

	public float getNextCResistanceBlast() {
		return nextCResistanceBlast;
	}

	public void setNextCResistanceBlast(float nextCResistanceBlast) {
		this.nextCResistanceBlast = nextCResistanceBlast;
	}

	public float getNextCResistanceBullet() {
		return nextCResistanceBullet;
	}

	public void setNextCResistanceBullet(float nextCResistanceBullet) {
		this.nextCResistanceBullet = nextCResistanceBullet;
	}

	public float getNextCResistanceKnife() {
		return nextCResistanceKnife;
	}

	public void setNextCResistanceKnife(float nextCResistanceKnife) {
		this.nextCResistanceKnife = nextCResistanceKnife;
	}

	public int getNextHoleNum() {
		this.nextHoleNum = getMaxHoleNum0((this.level + 1));
		return this.nextHoleNum;
	}

	public void setNextHoleNum(int nextHoleNum) {
		this.nextHoleNum = nextHoleNum;
	}

	public int getNextColor() {
		return nextColor;
	}

	public void setNextColor(int nextColor) {
		this.nextColor = nextColor;
	}

	public int getHoleNum() {
		return holeNum;
	}

	public void setHoleNum(int holeNum) {
		this.holeNum = holeNum;
	}

	/**
	 * @return the sysItem
	 */
	public SysItem getSysItem() {
		return sysItem;
	}

	/**
	 * @param sysItem
	 *            the sysItem to set
	 */
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}

	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDateStr = new SimpleDateFormat("yyyy.MM.dd HH:mm")
				.format(validDate);
		this.validDate = validDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public long getExpireSecondsLeft() {
		if (this.expireDate != null) {
			return (this.expireDate.getTime() - System.currentTimeMillis()) / 1000l;
		} else {
			return -1;
		}
	}

	public int getExpireMinutesLeft() {
		if (this.expireDate != null) {
			return (int) ((this.expireDate.getTime() - System
					.currentTimeMillis()) / 1000 / 60);
		} else {
			return -1;
		}
	}

	public void setExpireDate(Date expireDate) {
		this.expireDateStr = new SimpleDateFormat("yyyy.MM.dd HH:mm")
				.format(expireDate);
		this.expireDate = expireDate;
	}

	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public Integer getPack() {
		return pack;
	}

	public void setPack(Integer pack) {
		this.pack = pack;
	}

	public Integer getBuff() {
		return buff;
	}

	public void setBuff(Integer buff) {
		this.buff = buff;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Float getDurable() {
		return durable;
	}

	public void setDurable(Float durable) {
		this.durable = durable;
	}

	public Integer getPlayerItemCurrency() {
		return playerItemCurrency;
	}

	public void setPlayerItemCurrency(Integer playerItemCurrency) {
		this.playerItemCurrency = playerItemCurrency;
	}

	public Integer getPlayerItemUnitType() {
		return playerItemUnitType;
	}

	public void setPlayerItemUnitType(Integer playerItemUnitType) {
		this.playerItemUnitType = playerItemUnitType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getModifiedDesc() {
		return modifiedDesc;
	}

	public void setModifiedDesc(String modifiedDesc) {
		this.modifiedDesc = modifiedDesc;
	}

	public List<PlayerItem> getParts() {
		return parts;
	}

	public void setParts(List<PlayerItem> parts) {
		this.parts = parts;
	}

	public List<PlayerItem> getPackages() {
		return packages;
	}

	public void setPackages(List<PlayerItem> packages) {
		this.packages = packages;
	}

	public List<SysItem> getGifts() {
		return gifts;
	}

	public void setGifts(List<SysItem> gifts) {
		this.gifts = gifts;
	}

	public float getRecoil() {
		return recoil;
	}

	public void setRecoil(float recoil) {
		this.recoil = recoil;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getShootSpeed() {
		return shootSpeed;
	}

	public void setShootSpeed(float shootSpeed) {
		this.shootSpeed = shootSpeed;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getDamange() {
		return damange;
	}

	public void setDamange(float damange) {
		this.damange = damange;
	}

	public float getStopPpower() {
		return stopPpower;
	}

	public void setStopPpower(float stopPpower) {
		this.stopPpower = stopPpower;
	}

	public int getRepairCost() {
		return repairCost;
	}

	public void setRepairCost(int repairCost) {
		this.repairCost = repairCost;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public Integer[] getDirection() {
		return direction;
	}

	public void setDirection(Integer[] direction) {
		this.direction = direction;
	}

	public String[] getPrices() {
		return prices;
	}

	public void setPrices(String[] prices) {
		this.prices = prices;
	}

	public String[] getPSuits() {
		return pSuits;
	}

	public void setPSuits(String[] suits) {
		pSuits = suits;
	}

	public String[] getResource() {
		return resource;
	}

	public void setResource(String[] resource) {
		this.resource = resource;
	}

	public ArrayList<String> getResources() {
		return resources;
	}

	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
	}

	public Integer getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(Integer timeLeft) {
		this.timeLeft = timeLeft;
	}

	public Integer getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getExpireDateStr() {
		return new SimpleDateFormat("yyyy.MM.dd HH:mm").format(this.expireDate);
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public Integer getPlayerItemUnit1() {
		return playerItemUnit1;
	}

	public void setPlayerItemUnit1(Integer playerItemUnit1) {
		this.playerItemUnit1 = playerItemUnit1;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public GunProperty getGunProperty() {
		return gunProperty;
	}

	public void setGunProperty(GunProperty gunProperty) {
		this.gunProperty = gunProperty;
	}

	public List<Integer> getGunPropertyList1() {
		return gunPropertyList1;
	}

	public void setGunPropertyList1(List<Integer> gunPropertyList1) {
		this.gunPropertyList1 = gunPropertyList1;
	}

	public List<Integer> getGunPropertyList2() {
		return gunPropertyList2;
	}

	public void setGunPropertyList2(List<Integer> gunPropertyList2) {
		this.gunPropertyList2 = gunPropertyList2;
	}

	public List<Integer> getGunPropertyList3() {
		return gunPropertyList3;
	}

	public void setGunPropertyList3(List<Integer> gunPropertyList3) {
		this.gunPropertyList3 = gunPropertyList3;
	}

	public List<Integer> getGunPropertyList4() {
		return gunPropertyList4;
	}

	public void setGunPropertyList4(List<Integer> gunPropertyList4) {
		this.gunPropertyList4 = gunPropertyList4;
	}

	public List<Integer> getGunPropertyList5() {
		return gunPropertyList5;
	}

	public void setGunPropertyList5(List<Integer> gunPropertyList5) {
		this.gunPropertyList5 = gunPropertyList5;
	}

	public String getGunProperty1() {
		return gunProperty1;
	}

	public void setGunProperty1(String gunProperty1) {
		this.gunProperty1 = gunProperty1;
	}

	public String getGunProperty2() {
		return gunProperty2;
	}

	public void setGunProperty2(String gunProperty2) {
		this.gunProperty2 = gunProperty2;
	}

	public String getGunProperty3() {
		return gunProperty3;
	}

	public void setGunProperty3(String gunProperty3) {
		this.gunProperty3 = gunProperty3;
	}

	public String getGunProperty4() {
		return gunProperty4;
	}

	public void setGunProperty4(String gunProperty4) {
		this.gunProperty4 = gunProperty4;
	}

	public String getGunProperty5() {
		return gunProperty5;
	}

	public void setGunProperty5(String gunProperty5) {
		this.gunProperty5 = gunProperty5;
	}

	public int getDurableInt() {
		return durableInt;
	}

	public void setDurableInt(int durableInt) {
		this.durableInt = durableInt;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getItemDisplayName() {
		return itemDisplayName;
	}

	public void setItemDisplayName(String itemDisplayName) {
		this.itemDisplayName = itemDisplayName;
	}

	public int getMaterialNeed() {
		return materialNeed;
	}

	public void setMaterialNeed(int materialNeed) {
		this.materialNeed = materialNeed;
	}

	// for GM
	@Transient private String validDateStr;
	@Transient private String expireDateStr;

	public String getValidDateStr() {
		return new SimpleDateFormat("yyyy.MM.dd HH:mm").format(this.validDate);
	}

	public void setValidDateStr(String validDateStr) {
		this.validDateStr = validDateStr;
	}

	public void setExpireDateStr(String expireDateStr) {
		this.expireDateStr = expireDateStr;
	}

	public int getLeftSeconds() {
		return leftSeconds;
	}

	public void setLeftSeconds(int leftSeconds) {
		this.leftSeconds = leftSeconds;
	}

	private void writeProperty(OutputStream out, SysItem sysItem,
			int... suitArgus) throws Exception {
		if (suitArgus.length != 2) {
			out.write(BinaryUtil.toByta((byte) 7));// 总共预留了7个孔
		} else { // 要传 套装 属性
			SysSuit suit = ConfigurationUtil.SUITMAP.get(suitArgus[0]);
			if (suit != null) {
				HashSet<SysSuit.Property> exProperties = suit
						.getSpecSet(suitArgus[1]);
				if (exProperties != null) {
					out.write(BinaryUtil.toByta((byte) (7 + exProperties.size())));
					Iterator<SysSuit.Property> iterator = exProperties
							.iterator();
					while (iterator.hasNext()) {
						SysSuit.Property suitProperty = iterator.next();
						out.write(BinaryUtil.toByta((byte) 1));
						out.write(BinaryUtil.toByta((short) suitProperty
								.getProId()));
						out.write(BinaryUtil.toByta((short) suitProperty
								.getValue1()));
						out.write(BinaryUtil.toByta((short) suitProperty
								.getValue2()));
						out.write(BinaryUtil.toByta((short) suitProperty
								.getTime()));
					}
				} else {
					out.write(BinaryUtil.toByta((byte) 7));// 总共预留了7个孔
				}
			} else {
				out.write(BinaryUtil.toByta((byte) 7));// 总共预留了7个孔
			}
		}

		if (gunPropertyList1.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList1.size() / 4)));
			for (int num : gunPropertyList1) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList2 != null && gunPropertyList2.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList2.size() / 4)));
			for (int num : gunPropertyList2) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList3 != null && gunPropertyList3.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList3.size() / 4)));
			for (int num : gunPropertyList3) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList4 != null && gunPropertyList4.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList4.size() / 4)));
			for (int num : gunPropertyList4) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList5 != null && gunPropertyList5.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList5.size() / 4)));
			for (int num : gunPropertyList5) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList6 != null && gunPropertyList6.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList6.size() / 4)));
			for (int num : gunPropertyList6) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
		if (gunPropertyList7 != null && gunPropertyList7.size() == 0) {
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta((byte) (gunPropertyList7.size() / 4)));
			for (int num : gunPropertyList7) {
				out.write(BinaryUtil.toByta((short) num));
			}
		}
	}

	/**
	 * 将源武器属性的值转换为对应的工程兵武器的属性值
	 * 
	 * @param index
	 *            源武器属性ID，大部分时间无用
	 * @param mainProperties
	 *            源武器属性值列表(pro1Min,pro1Max,pro2Min,pro2Max,time1Min,time1Max)
	 * @param propertyValue
	 *            源武器属性值(由于工程兵属性只有一个属性值，该参数仅取源武器属性中第一个非时间属性的属性值，若无则取时间属性值)
	 * @param engineerProperties
	 *            工程兵武器属性值列表(pro1Min,pro1Max,pro2Min,pro2Max,time1Min,time1Max)
	 * @return 工程兵武器对应属性值
	 */
	private int getConvertPropertyValue(int index, int[] mainProperties,
			int propertyValue, int[] engineerProperties) {
		float min = mainProperties[0];
		float max = mainProperties[1];
		float engMin = engineerProperties[0];
		float engMax = engineerProperties[1];

		if (min == 0 && max == 0) { // 如果第一属性为0，向下找到第一个非0的属性作为转换对象
			for (int i = 2; i < mainProperties.length; i += 2) {
				if (mainProperties[i] != 0 && mainProperties[i + 1] != 0) {
					min = mainProperties[i];
					max = mainProperties[i + 1];
					break;
				}
			}
		}

		if (min == max) {
			if (min == 0) {
				return 0;
			}
			return Math.round(engMax);
		}

		if (index == 31) { // hardcode,该属性属性值1为↓，对应工程兵属性值为↑，故逆向对应
			return Math.round(engMax - (engMax - engMin)
					* ((propertyValue - min) / (max - min)));
		}

		// 此处没有判断工程兵属性数组是由于其属性都是单属性值且在首位
		return Math.round(engMin + (engMax - engMin)
				* ((propertyValue - min) / (max - min)));
	}

	public void calculateTimeLeft() {
		this.durableInt = Math.round(this.durable);
		this.createTime = (long) (validDate.getTime() / 1000);
		long milliSecond = (this.getExpireDate().getTime() - new Date()
				.getTime());
		this.timeLeft = (int) (milliSecond / 60000L);
		if (this.timeLeft < 0) {
			this.timeLeft = 0;
		}
		if (this.isBind.equals(Constants.BOOLEAN_NO)) {
			this.timeLeft = this.leftSeconds / 60;
		}
	}

	public Float getCResistanceFire() {
		return cResistanceFire;
	}

	public void setCResistanceFire(Float resistanceFire) {
		cResistanceFire = resistanceFire;
	}

	public Float getCResistanceBlast() {
		return cResistanceBlast;
	}

	public void setCResistanceBlast(Float resistanceBlast) {
		cResistanceBlast = resistanceBlast;
	}

	public Float getCResistanceBullet() {
		return cResistanceBullet;
	}

	public void setCResistanceBullet(Float resistanceBullet) {
		cResistanceBullet = resistanceBullet;
	}

	public Float getCResistanceKnife() {
		return cResistanceKnife;
	}

	public void setCResistanceKnife(Float resistanceKnife) {
		cResistanceKnife = resistanceKnife;
	}

	public String getGunProperty6() {
		return gunProperty6;
	}

	public void setGunProperty6(String gunProperty6) {
		this.gunProperty6 = gunProperty6;
	}

	public List<Integer> getGunPropertyList6() {
		return gunPropertyList6;
	}

	public void setGunPropertyList6(List<Integer> gunPropertyList6) {
		this.gunPropertyList6 = gunPropertyList6;
	}

	public String getGunProperty7() {
		return gunProperty7;
	}

	public void setGunProperty7(String gunProperty7) {
		this.gunProperty7 = gunProperty7;
	}

	public List<Integer> getGunPropertyList7() {
		return gunPropertyList7;
	}

	public void setGunPropertyList7(List<Integer> gunPropertyList7) {
		this.gunPropertyList7 = gunPropertyList7;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getFireTime() {
		return fireTime;
	}

	public void setFireTime(float fireTime) {
		this.fireTime = fireTime;
	}

	/*
	 * +--------------------------------------------------------------------------
	 * ---------------------------------------------------+
	 * |------------------------------------------------>for combine system |
	 * +--
	 * ------------------------------------------------------------------------
	 * ---------------------------------------------------+
	 */
	private void resetGunPropertys(String value) {
		this.gunProperty2 = this.gunProperty3 = this.gunProperty4 = this.gunProperty5 = this.gunProperty6 = this.gunProperty7 = value;
	}

	public int getMaxHoleNum() {
		return getMaxHoleNum0(this.level);
	}

	public int getMaxHoleNum0(int level) {
		if (this.sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE) {
			if (level > 15) {
				return 6;
			}
			switch (level) {
			case 15:
				return 6;
			case 14:
				return 5;
			case 12:
			case 13:
				return 4;
			case 10:
			case 11:
				return 3;
			case 7:
			case 8:
			case 9:
				return 2;
			case 5:
			case 6:
			case 4:
				return 1;
			default:
				return 0;
			}
		} else if (this.sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE
				|| this.sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
			if (level > 15) {
				return 3;
			}
			switch (level) {
			case 15:
				return 3;
			case 13:
			case 14:
			case 12:
				return 2;
			case 11:
			case 10:
			case 9:
			case 8:
			case 7:
				return 1;
			default:
				return 0;
			}
		}
		return 0;
	}

	public int getSlottedHoleNum() {
		int num = 0;
		if (this.gunProperty2 != null
				&& gunProperty2.length() > Constants.NUM_ZERO
				&& gunProperty2.charAt(0) == '1') {
			num++;
		}
		if (this.gunProperty3 != null
				&& gunProperty3.length() > Constants.NUM_ZERO
				&& gunProperty3.charAt(0) == '1') {
			num++;
		}
		if (this.gunProperty4 != null
				&& gunProperty4.length() > Constants.NUM_ZERO
				&& gunProperty4.charAt(0) == '1') {
			num++;
		}
		if (this.gunProperty5 != null
				&& gunProperty5.length() > Constants.NUM_ZERO
				&& gunProperty5.charAt(0) == '1') {
			num++;
		}
		if (this.gunProperty6 != null
				&& gunProperty6.length() > Constants.NUM_ZERO
				&& gunProperty6.charAt(0) == '1') {
			num++;
		}
		if (this.gunProperty7 != null
				&& gunProperty7.length() > Constants.NUM_ZERO
				&& gunProperty7.charAt(0) == '1') {
			num++;
		}
		return num;
	}

	public String getGunPropertyByHoleIndex(int index) {
		switch (index) {
		case 1:
			return this.gunProperty2;
		case 2:
			return this.gunProperty3;
		case 3:
			return this.gunProperty4;
		case 4:
			return this.gunProperty5;
		case 5:
			return this.gunProperty6;
		case 6:
			return this.gunProperty7;
		}
		return "";
	}

	public void setGunPropertyByHoleIndex(int index, String pro) {
		switch (index) {
		case 1:
			this.gunProperty2 = pro;
			break;
		case 2:
			this.gunProperty3 = pro;
			break;
		case 3:
			this.gunProperty4 = pro;
			break;
		case 4:
			this.gunProperty5 = pro;
			break;
		case 5:
			this.gunProperty6 = pro;
			break;
		case 6:
			this.gunProperty7 = pro;
			break;
		}
	}

	public double getSlotRate() {
		switch (getSlottedHoleNum()) {
		case 0:
			return sysItem.getType() == 1 ? 1 : 0.5;
		case 1:
			return sysItem.getType() == 1 ? 0.5 : 0.2;
		case 2:
			return sysItem.getType() == 1 ? 0.25 : 0.1;
		case 3:
			return sysItem.getType() == 1 ? 0.2 : 0;
		case 4:
		case 5:
			return sysItem.getType() == 1 ? 0.1 : 0;
		}
		return 0;
	}

	public boolean isLevelChangeLevel() {
		if ((this.sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE || this.sysItem
				.getType() == Constants.DEFAULT_PART_TYPE)
				&& (this.level == 7 || this.level == 12 || this.level == 15)) {
			return true;
		} else if (this.sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE
				&& (this.level == 4 || this.level == 7 || this.level == 10
						|| this.level == 12 || this.level == 14 || this.level == 15)) {
			return true;
		}
		return false;
	}

	public boolean isRepeatProperty(String property) {
		for (int i = 1; i <= 6; i++) {
			String[] proDetail = getGunPropertyByHoleIndex(i).split(
					Constants.SEMICOLON);
			if (proDetail.length > 1) {
				if (proDetail[2].split(Constants.COMMA)[0].equals(property
						.split(Constants.COMMA)[0])) {
					return true;
				}
			}
		}
		return false;
	}

	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	/*
	 * +--------------------------------------------------------------------------
	 * ---------------------------------------------------+
	 * |------------------------------------------------>for combine system end
	 * |
	 * +------------------------------------------------------------------------
	 * -----------------------------------------------------+
	 */

	public Float getCBloodAdd() {
		return cBloodAdd;
	}

	public void setCBloodAdd(Float bloodAdd) {
		cBloodAdd = bloodAdd;
	}

	public float getNextBloodAdd() {
		return nextBloodAdd;
	}

	public void setNextBloodAdd(float nextBloodAdd) {
		this.nextBloodAdd = nextBloodAdd;
	}

	public int getGpNeed() {
		return gpNeed;
	}

	public void setGpNeed(int gpNeed) {
		this.gpNeed = gpNeed;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return sysItem.getType();
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获取基础战斗力
	 * 
	 * @return
	 */
	public int getBaseItemFightNum() {
		if (this.sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE) {
			if (this.sysItem.getSubType() == Constants.NUM_ONE) {// 主武器
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[0][0] * Math
						.pow(Constants.FIGHT_PARAM[0][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[0][2])));
			} else if (this.sysItem.getSubType() == Constants.NUM_TWO) {// 副武器
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[5][0] * Math
						.pow(Constants.FIGHT_PARAM[5][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[5][2])));
			} else if (this.sysItem.getSubType() == Constants.NUM_THREE) {// 近身器
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[1][0] * Math
						.pow(Constants.FIGHT_PARAM[1][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[1][2])));
			}
		} else if (this.sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
			baseItemFightNum = (int) (Constants.FIGHT_PARAM[2][0] * ((Math
					.pow(Constants.FIGHT_PARAM[2][1],
							((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[2][2]))) - 1));
		} else if (this.sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
			if (this.sysItem.getSubType() == Constants.NUM_ONE) {// 帽子
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[3][0] * ((Math
						.pow(Constants.FIGHT_PARAM[3][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[3][2]))) - 1));
			} else if (this.sysItem.getSubType() == Constants.NUM_TWO) {// 配饰
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[4][0] * (Math
						.pow(Constants.FIGHT_PARAM[4][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[4][2])) - 1));
			}
		}
		return baseItemFightNum;
	}

	public String getMeltingItemStr() {
		return meltingItemStr;
	}

	public void setMeltingItemStr(String meltingItemStr) {
		this.meltingItemStr = meltingItemStr;
	}

	public String getIntensifyInfo(int type) {
		String property = "";
		switch (type) {
		case 1:
			property = gunProperty1;
			break;
		case 2:
			property = gunProperty2;
			break;
		case 3:
			property = gunProperty3;
			break;
		case 6:
			property = gunProperty6;
			break;
		}
		return property;
	}

	public void setIntensifyInfo(int type, String property) {
		switch (type) {
		case 1:
			gunProperty1 = property;
			break;
		case 2:
			gunProperty2 = property;
			break;
		case 3:
			gunProperty3 = property;
			break;
		case 6:
			gunProperty6 = property;
		}
	}

	public boolean isUpgrade() throws NumberFormatException, Exception {
		if (gunProperty1 == null || !gunProperty1.contains(",")
				|| gunProperty2 == null || !gunProperty2.contains(",")
				|| gunProperty3 == null || !gunProperty3.contains(",")
				|| gunProperty6 == null || !gunProperty6.contains(",")) {
			return false;
		}

		String[] p1 = gunProperty1.split(",");
		String[] p2 = gunProperty2.split(",");
		String[] p3 = gunProperty3.split(",");
		String[] p4 = gunProperty6.split(",");
		if (ItemIntensifyUtil.getMaxLevel(sysItem.getSubType()) == Integer
				.valueOf(p1[0])
				&& ItemIntensifyUtil.getMaxLevel(sysItem.getSubType()) == Integer
						.valueOf(p2[0])
				&& ItemIntensifyUtil.getMaxLevel(sysItem.getSubType()) == Integer
						.valueOf(p3[0])
				&& ItemIntensifyUtil.getMaxLevel(sysItem.getSubType()) == Integer
						.valueOf(p4[0])) {
			return true;
		} else {
			return false;
		}
	}

	public Integer getGst_level() {
		// 精英合成 最高5级
		return gst_level == null ? 0
				: gst_level >= Constants.MAX_GST_LEVEL ? Constants.MAX_GST_LEVEL
						: gst_level;
	}

	/**
	 * 精英合成系统，增加经验
	 * 
	 * @param rareLevel
	 *            武器个数及其稀有度 every_day_experience 是否每日加成 mulCount 同品质加成次数
	 * 
	 * */
	public void addGst_Exp(boolean every_day_experience, final int mulCount,
			int[] rareLevel) throws Exception {
		// int quality_level = Constants.QUALITY_LEVEL(rareLevel);

		// 当前经验
		int cExp = getGst_level_exp();
		// 下级经验
		int nExp = (int) Constants.getGst_Level_Exp(getGst_level(),
				getSysItem().getRareLevel());
		// 该武器增加经验
		int aExp = 0;
		int count = 2;
		for (int lExp : rareLevel) {
			// 同品质武器加成
			aExp += count++ <= mulCount ? Constants.getGst_Level_Exp(lExp) * 5
					: Constants.getGst_Level_Exp(lExp);
		}

		// 每日一次
		aExp += every_day_experience ? Constants.EVERY_DAY_EXPERIENCE[Constants
				.getQualityLevel(getSysItem().getRareLevel()) - 1] : 0;

		// System.out.println("此次物品总经验："+(cExp + aExp));
		// System.out.println("下级所需经验(到达"+(getGst_level()+1)+"级需要经验)："+ nExp);
		// System.out.println("物品当前经验：" + cExp);
		// System.out.println("此次增加经验：" + aExp);

		if (getGst_level() < Constants.MAX_GST_LEVEL) {
			long tExp = nExp; // 下级经验
			int i = getGst_level(); // 当前等级

			while ((cExp + aExp) >= tExp && i < Constants.MAX_GST_LEVEL) // 连续升级;
			{
				// i = getGst_level();
				// tExp = nExp;
				i += 1;
				nExp = (int) Constants.getGst_Level_Exp(i, getSysItem()
						.getRareLevel());
				tExp += nExp;
				// System.out.println("下一级所需经验("+(i+1)+"):"+nExp+"("+ tExp
				// +") 当前可用经验：" + (cExp + aExp));
			}

			tExp -= nExp;
			// --i;

			if (getGst_level() <= 0)
				this.gst_level = 0;
			if (getGst_level_exp() <= 0)
				this.gst_level_exp = 0;

			// System.out.println("停留等级："+i +" 所需经验："+tExp);
			// System.out.println("溢出经验："+(cExp + aExp - tExp));

			if (i > getGst_level()) {
				this.gst_level = i > Constants.MAX_GST_LEVEL ? Constants.MAX_GST_LEVEL
						: i; // 升级
				// System.out.println(this.gst_level);
				this.gst_level_exp = (i > Constants.MAX_GST_LEVEL ? (int) Constants
						.getGst_Level_Exp(getGst_level() - 1, getSysItem()
								.getRareLevel()) : Integer.parseInt(String
						.valueOf(((cExp + aExp) - tExp)))); // 溢出经验
				// System.out.println("最终经验："+this.gst_level_exp);
			} else {
				this.gst_level_exp += aExp;
			}
		}
		// ServiceLocator.updateService.updatePlayerItem(this); FIXME
	}

	public int getGst_level_exp() {
		return this.gst_level_exp != null ? this.gst_level_exp : 0;
	}

	@Override
	public long getShardingKey() {
		return id;//playerId
	}

	@Override
	public int getItemType() {
		return sysItem.getType();
	}

	@Override
	public boolean getIsStorage() {
		return level>0;
	}

	@Override
	public int getUnitType() {
		return playerItemUnitType;
	}

	@Override
	public int getUnit() {
		return quantity;
	}

	@Override
	public boolean getIsEquipped() {
		return pack>0;
	}

	@Override
	public void setIsStorage(boolean isStorage) {
		//TODO IsStorage
	}

}
