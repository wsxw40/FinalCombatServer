package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.GunProperty.Property;
import com.pearl.o2o.pojo.GunProperty.StrProperty;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DBRouteUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.NumberUtil;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.MathOperator;

public class PlayerItem extends BaseMappingBean<PlayerItem> {
	private static final long serialVersionUID = -1774380518543769733L;
	private static final DateFormat DF = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	private Integer userId;
	private Integer playerId;
	private Integer itemId;
	private Date validDate;
	private Date expireDate;
	private String isBind;
	private String isDefault;
	private String isDeleted;
	private String isGift;
	// join column
	private Integer pack = 0;
	private Integer buff = 0;
	private Integer seq;

	private Float durable = 100f;
	private int durableInt = 100;
	private long createTime;
	private Integer playerItemCurrency;// playerItem's currency
	private Integer playerItemUnitType;
	private Integer playerItemUnit1;// 如果是基于时间的，用于计算过期日期

	private Integer quantity = 1;

	private String modifiedDesc;
	private Float cResistanceFire = 0F;
	private Float cResistanceBlast = 0F;
	private Float cResistanceBullet = 0F;
	private Float cResistanceKnife = 0F;
	private Float cBloodAdd = 0F;

	private int fightNum = 0;

	private List<PlayerItem> parts;
	private List<PlayerItem> packages;

	// join table sys_package
	private List<SysItem> gifts;

	// Not Stored in Database
	private float recoil;// 后坐力
	private float accuracy;// 精度
	private float shootSpeed;
	private float weight;
	private float damange;
	private float stopPpower;
	private int repairCost;
	private float range;
	private Integer direction[];
	private String prices[];
	private String pSuits[];
	private String resource[];
	private ArrayList<String> resources;
	private Integer timeLeft;
	private GunProperty gunProperty = new GunProperty();
	private String gunProperty1 = "";
	private String gunProperty2 = "";
	private String gunProperty3 = "";
	private String gunProperty4 = "";
	private String gunProperty5 = "";
	private String gunProperty6 = "";
	private String gunProperty7 = "";

	private String gunProperty8 = ""; // 工程兵属性字段
	
	private Integer gst_level;			//精英合成等级（武器星级）
	private Integer gst_level_exp;		//精英合成经验

	private int damage;
	private float fireTime;

	// Not Stored in Database
	private List<Integer> gunPropertyList1 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList2 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList3 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList4 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList5 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList6 = new ArrayList<Integer>();
	private List<Integer> gunPropertyList7 = new ArrayList<Integer>();


	// Join table ITEM_MOD
	private Integer parentItemId;

	private String itemName;

	private String itemDisplayName;

	private SysItem sysItem;
	private int type;
	private int leftSeconds = 0;

	private int holeNum = 0;// 物品已经开放的孔数=开放未打孔+已经打孔的

	private int materialNeed = 0;
	private int gpNeed = 0;

	private float nextDamange = 0;

	private float nextShootSpeed = 0;

	private int nextFightNum = 0;

	private float nextCResistanceFire = 0;
	private float nextCResistanceBlast = 0;
	private float nextCResistanceBullet = 0;
	private float nextCResistanceKnife = 0;
	private float nextBloodAdd = 0;
	private int nextHoleNum = 0;
	private int nextColor = 0;
	private transient int baseItemFightNum;// 基础战斗力
	private transient int strengthenItemFightNum; // 强化后增加的战斗力
	private String meltingItemStr;
	
	//zlm2015-5-7-限时装备-开始
	private int provisional_item_day=60001;//续费时间  60001为默认返回指，表示不受续费限制
	private Boolean provisional_item_flag;//是否是限时装备 false不是限时装备 true是限时装备
	
	public int getProvisional_item_day() {
		return provisional_item_day;
	}
	public void setProvisional_item_day(int provisional_item_day) {
		this.provisional_item_day = provisional_item_day;
	}
	public boolean getProvisional_item_flag() {
		if (this.provisional_item_flag ==null) {
			provisional_item_flag=CommonUtil.provisional_item_scope_flag(this.getItemId());
		}
		return this.provisional_item_flag;
	}
	//zlm2015-5-7-限时装备-结束
	
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

	public int getNextFightNum() {
		if (this.sysItem != null) {
			if (sysItem.getIsStrengthen() == Constants.NUM_ZERO) {
				nextFightNum = sysItem.getFigthNumOutput();
			} else {
				int lowPropertyNum = 0;
				int middlePropertyNum = 0;
				int highPropertyNum = 0;
				// if(sysItem.getIsStrengthen() == Constants.NUM_ONE){
				for (StrProperty pro : this.gunProperty.getStrPropertyList()) {
					if (pro.getLevel() == 1) {
						lowPropertyNum++;
					} else if (pro.getLevel() == 2) {
						middlePropertyNum++;
					} else if (pro.getLevel() == 3) {
						highPropertyNum++;
					}
				}
				// } else {
				// highPropertyNum = sysItem.getDefaultPropertySize();
				// }
				int calculateLevel = (1 + this.level) > Constants.MAX_STRENGTH_LEVEL ? Constants.MAX_STRENGTH_LEVEL
						: (1 + this.level);
				if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE) {
					if (sysItem.getSubType() == Constants.NUM_ONE) {// 主武器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[0][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[0][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[0][6]) * Double.parseDouble(String.valueOf(calculateGstLevelFightNum(sysItem)));
						nextFightNum = (int) (Constants.FIGHT_PARAM[0][0]
								* Math
										.pow(
												Constants.FIGHT_PARAM[0][1],
												calculateLevel
														+ ((sysItem
																.getRareLevel() - 1) / Constants.FIGHT_PARAM[0][2])
														- Constants.FIGHT_PARAM[0][3]) * multiplier3);
					} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 副武器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[5][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[5][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[5][6]);
						nextFightNum = (int) (Constants.FIGHT_PARAM[5][0]
								* Math
										.pow(
												Constants.FIGHT_PARAM[5][1],
												calculateLevel
														+ ((sysItem
																.getRareLevel() - 1) / Constants.FIGHT_PARAM[5][2])
														- Constants.FIGHT_PARAM[5][3]) * multiplier3);
					} else if (sysItem.getSubType() == Constants.NUM_THREE) {// 近身器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[1][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[1][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[1][6]);
						nextFightNum = (int) (Constants.FIGHT_PARAM[1][0]
								* Math
										.pow(
												Constants.FIGHT_PARAM[1][1],
												calculateLevel
														+ ((sysItem
																.getRareLevel() - 1) / Constants.FIGHT_PARAM[1][2])
														- Constants.FIGHT_PARAM[1][3]) * multiplier3);
					}
				} else if (sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
					double multiplier3 = (1 + lowPropertyNum
							/ Constants.FIGHT_PARAM[2][4] + middlePropertyNum
							/ Constants.FIGHT_PARAM[2][5] + highPropertyNum
							/ Constants.FIGHT_PARAM[2][6]);
					nextFightNum = (int) (Constants.FIGHT_PARAM[2][0] * ((Math
							.pow(
									Constants.FIGHT_PARAM[2][1],
									calculateLevel
											+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[2][2])
											- Constants.FIGHT_PARAM[2][3]) * multiplier3) * getStartTotalBloodAdd() - 1));
				} else if (sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
					if (sysItem.getSubType() == Constants.NUM_ONE) {// 帽子
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[3][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[3][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[3][6]);
						nextFightNum = (int) (Constants.FIGHT_PARAM[3][0] * ((Math
								.pow(
										Constants.FIGHT_PARAM[3][1],
										calculateLevel
												+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[3][2])
												- Constants.FIGHT_PARAM[3][3]) * multiplier3) * getStartTotalBloodAdd() - 1));
					} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 配饰
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[4][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[4][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[4][6]);
						nextFightNum = (int) (Constants.FIGHT_PARAM[4][0] * ((Math
								.pow(
										Constants.FIGHT_PARAM[4][1],
										calculateLevel
												+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[4][2])
												- Constants.FIGHT_PARAM[4][3]) * multiplier3) * getStartTotalBloodAdd() - 1));
					}
				}
			}
		}
		if (this.level >= Constants.MAX_STRENGTH_LEVEL) {
			this.nextFightNum = this.fightNum;
		}
		return nextFightNum;

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

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDateStr = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(validDate);
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
		this.expireDateStr = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(expireDate);
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

	public Integer getSeq() {
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

	public Integer getQuantity() {
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

	@Override
	public String getTableSuffix() {
		return DBRouteUtil.getTableSuffix(PlayerItem.class, playerId);
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
	private String validDateStr;
	private String expireDateStr;

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

	private void writeProperty(OutputStream out, SysItem sysItem,int... suitArgus)
			throws Exception {
		if(suitArgus.length!=2){
			out.write(BinaryUtil.toByta((byte) 7));// 总共预留了7个孔
		}else{ //要传 套装 属性
			SysSuit suit=ConfigurationUtil.SUITMAP.get(suitArgus[0]);
			if(suit!=null){
				HashSet<SysSuit.Property> exProperties=suit.getSpecSet(suitArgus[1]);
				if(exProperties!=null){
					out.write(BinaryUtil.toByta((byte) (7+exProperties.size())));
					Iterator<SysSuit.Property> iterator=exProperties.iterator();
					while(iterator.hasNext()){
						SysSuit.Property suitProperty=iterator.next();
						out.write(BinaryUtil.toByta((byte) 1));
						out.write(BinaryUtil.toByta((short)suitProperty.getProId()));
						out.write(BinaryUtil.toByta((short)suitProperty.getValue1()));
						out.write(BinaryUtil.toByta((short)suitProperty.getValue2()));
						out.write(BinaryUtil.toByta((short)suitProperty.getTime()));
					}
				}else{
					out.write(BinaryUtil.toByta((byte) 7));// 总共预留了7个孔
				}
			}else{
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

	private void writeWeaponBaseInfo(OutputStream out, SysItem sysItem,int... suitArgus)
			throws Exception {
		out.write(BinaryUtil.toByta(itemId));
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(sysItem.getDisplayName())); // Weapon
																// Display Name
		out.write(BinaryUtil.toByta(sysItem.getName())); // Weapon Name
		out.write(BinaryUtil.toByta((byte) gunProperty.getColor()));
		// if(sysItem.getIsStrengthen()==0){//if level bigger 100,non strengthen
		// out.write(BinaryUtil.toByta((byte)100));
		// }else{
		out.write(BinaryUtil.toByta((byte) this.level));
		out.write(BinaryUtil.toByta((byte)(int)getGst_level()));		//星级等级
		// }
		// durable or expire
		if (this.playerItemUnitType == 2) {
			if (!this.expireDate.after(new Date())) {
				out.write(BinaryUtil.toByta((byte) 0));
				for (String id : sysItem.getCharacterIds()) {
					ServiceLocator.getService.getMcc().delete(
							CacheUtil.oCostumePack(playerId, 1, StringUtil
									.toInt(id)));
					ServiceLocator.getService.getMcc().delete(
							CacheUtil.oWeaponPack(playerId, 1, StringUtil
									.toInt(id)));
				}
				ServiceLocator.deleteService.deletePlayerItemInMemcached(
						playerId, sysItem);
				ServiceLocator.getService.getMcc().delete(
						CacheUtil.oCharacterList(playerId));
			} else {
				out.write(BinaryUtil.toByta(this.durable.byteValue()));
			}
		} else if (this.durable <= 0) {
			out.write(BinaryUtil.toByta((byte) 0));
			ServiceLocator.getService.getMcc().delete(
					CacheUtil.oCharacterList(playerId));
			for (String id : sysItem.getCharacterIds()) {
				ServiceLocator.getService.getMcc().delete(
						CacheUtil.oCostumePack(playerId, 1, StringUtil
								.toInt(id)));
				ServiceLocator.getService.getMcc()
						.delete(
								CacheUtil.oWeaponPack(playerId, 1, StringUtil
										.toInt(id)));
			}
			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId,
					sysItem);
		} else {
			out.write(BinaryUtil.toByta(this.durable.byteValue()));
		}

		writeProperty(out, sysItem,suitArgus);
		if (this.resource != null) {
			// Resources
			out.write(BinaryUtil.toByta(this.resource.length));
			for (String res : resource) {
				out.write(BinaryUtil.toByta(res));
			}
		} else {
			// Resources stable
			String[] resStable = sysItem.getResourceStable().split(
					Constants.DELIMITER_RESOURCE_STABLE);
			// Resources changeable
			String[] resChangeable = sysItem.getResourceChangeable().split(
					Constants.DELIMITER_RESOURCE_CHANGEABLE);
			List<String> resourceList = new ArrayList<String>();
			if (resChangeable != null) {
				List<String> tmpList = new ArrayList<String>();
				for (int i = 0; i < resStable.length; i++) {
					if (!"".equals(resStable[i])) {
						tmpList.add(sysItem.getName() + "/" + resStable[i]);
					}
				}
				for (int i = 0; i < resChangeable.length; i++) {
					if (!"".equals(resChangeable[i])
							&& resChangeable[i] != null) {
						String[] changebles = resChangeable[i]
								.split(Constants.DELIMITER_RESOURCE_STABLE);
						for (int j = 0; j < changebles.length; j++) {// resChangeable有两个资源
							tmpList
									.add(sysItem.getName() + "/"
											+ changebles[j]);
						}
					}
				}
				resourceList = tmpList;

			} else {
				for (int i = 0; i < resStable.length; i++) {
					String str = resStable[i];
					if (str != null && !"".equals(str)) {
						resourceList.add(resStable[i]);
					}
				}
			}
			out.write(BinaryUtil.toByta(resourceList.size()));
			for (String res : resourceList) {
				out.write(BinaryUtil.toByta(res));
			}
		}

	}

	private void writeCostumeBaseInfo(OutputStream out, SysItem sysItem,
			SysCharacter sysCharacter,int... suitArgus) throws Exception {
		out.write(BinaryUtil.toByta(itemId));
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(sysItem.getDisplayName())); // Weapon
																// Display Name
		out.write(BinaryUtil.toByta(sysItem.getName())); // Weapon Name
		out.write(BinaryUtil.toByta((byte) gunProperty.getColor()));
		out.write(BinaryUtil.toByta((byte) this.level));
		out.write(BinaryUtil.toByta((byte)(int)getGst_level()));		//星级等级
		// durable or expire
		if (this.playerItemUnitType == 2) {
			if (!this.expireDate.after(new Date())) {
				out.write(BinaryUtil.toByta((byte) 0));
				for (String id : sysItem.getCharacterIds()) {
					ServiceLocator.getService.getMcc().delete(
							CacheUtil.oCostumePack(playerId, 1, StringUtil
									.toInt(id)));
					ServiceLocator.getService.getMcc().delete(
							CacheUtil.oWeaponPack(playerId, 1, StringUtil
									.toInt(id)));
				}
				ServiceLocator.deleteService.deletePlayerItemInMemcached(
						playerId, sysItem);
				ServiceLocator.getService.getMcc().delete(
						CacheUtil.oCharacterList(playerId));
			} else {
				out.write(BinaryUtil.toByta(this.durable.byteValue()));
			}
		} else if (this.durable <= 0) {
			out.write(BinaryUtil.toByta((byte) 0));
			ServiceLocator.getService.getMcc().delete(
					CacheUtil.oCharacterList(playerId));
			for (String id : sysItem.getCharacterIds()) {
				ServiceLocator.getService.getMcc().delete(
						CacheUtil.oCostumePack(playerId, 1, StringUtil
								.toInt(id)));
				ServiceLocator.getService.getMcc()
						.delete(
								CacheUtil.oWeaponPack(playerId, 1, StringUtil
										.toInt(id)));
			}
			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId,
					sysItem);
		} else {
			out.write(BinaryUtil.toByta(this.durable.byteValue()));
		}

		writeProperty(out, sysItem,suitArgus);
		String[] costume = sysCharacter.getCostume();
		int size = 0;
		for (String cos : costume) {
			if (cos != null && !"".equals(cos)) {
				size++;
			}
		}
		out.write(BinaryUtil.toByta(size));
		for (String cos : costume) {
			if (cos != null && !"".equals(cos)) {
				out.write(BinaryUtil.toByta(cos));
			}
		}
	}

	public void writeWeapon(OutputStream out, SysItem sysItem,int... suitArgus) throws Exception {
		writeWeaponBaseInfo(out, sysItem,suitArgus);
		out.write(BinaryUtil.toByta(sysItem.getWId() == null ? 0 : sysItem
				.getWId())); // Weapon ID
		if (sysItem.getWId() != null && sysItem.getWId() > 0) {// base info
			out.write(BinaryUtil.toByta(sysItem.getWChangeInTime())); // Change
																		// In
																		// Time
			out.write(BinaryUtil.toByta(sysItem.getWMoveSpeedOffset())); // Move
																			// Speed
																			// Offset
			out.write(BinaryUtil.toByta(sysItem.getWCrossOffset())); // Cross
																		// Offset
			out.write(BinaryUtil.toByta(sysItem.getWCrossLengthBase())); // wCrossLengthBase
			out.write(BinaryUtil.toByta(sysItem.getWCrossLengthFactor())); // wCrossLengthFactor
			out.write(BinaryUtil.toByta(sysItem.getWCrossDistanceBase())); // wCrossDistanceBase
			out.write(BinaryUtil.toByta(sysItem.getWCrossDistanceFactor())); // wCrossDistanceFactor
			out.write(BinaryUtil.toByta(sysItem.getWHitSpeed())); // wHitSpeed
			out.write(BinaryUtil.toByta(sysItem.getWHitAcceleration())); // wHitAcceleration
			out.write(BinaryUtil.toByta(sysItem.getWHitDistance())); // wHitDistance
			out.write(BinaryUtil.toByta(sysItem.getWCritRate()));
			out.write(BinaryUtil.toByta(sysItem.getWCritAvailable()));
			out.write(BinaryUtil.toByta(sysItem.getWDamageModifer()));

			out.write(BinaryUtil.toByta(sysItem.getWTimeToIdle())); // Time
			// To
			// 武器强化等级 int
			// out.write(BinaryUtil.toByta(getLevel()));
			// 武器战斗力 int
			out.write(BinaryUtil.toByta(getFightNum()));
			// 武器颜色 int
			// out.write(BinaryUtil.toByta(getGunProperty().getColor()));
			calculateParam(sysItem);
			//System.out.println(this.sysItem.getDisplayNameCN()+":"+this.wDamage);
			// 武器攻击力 float
			out.write(BinaryUtil.toByta(getDamange()));
			// 武器攻击速度 float
			out.write(BinaryUtil.toByta(getShootSpeed()));

			if (sysItem.getWId() < Constants.WEAPON_KNIFE_ID ||Constants.WEAPON_ZYZDZ_EDIT_DEFAULT==sysItem.getWId()) {
				// Gun Info
				out.write(BinaryUtil.toByta(sysItem.getWAccuracyDivisor())); // Accuracy
																				// Divisor
				out.write(BinaryUtil.toByta(sysItem.getWAccuracyOffset())); // Accuracy
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWMaxInaccuracy())); // Max
																			// Inaccuracy
				out.write(BinaryUtil.toByta(sysItem.getWPenetration())); // Penetration
				out.write(BinaryUtil.toByta(this.wDamage)); // Damage
				out.write(BinaryUtil.toByta(sysItem.getWRangeModifier())); // Range
																			// Modifier
				out.write(BinaryUtil.toByta(sysItem.getWRangeStart())); // wRangeStart
				out.write(BinaryUtil.toByta(sysItem.getWRangeEnd())); // wRangeEnd
				out.write(BinaryUtil.toByta(this.wFireTime)); // Fire Time
				out.write(BinaryUtil.toByta(sysItem.getWReloadTime())); // Reload
																		// Time
				out.write(BinaryUtil.toByta(sysItem.getWAmmoOneClip())); // Ammo
																			// One
																			// Clip
				out.write(BinaryUtil.toByta(sysItem.getWAmmoCount())); // Ammo
																		// Count
				out
						.write(BinaryUtil.toByta(sysItem.getWAutoFire()
								.byteValue())); // Auto Fire
				// out.write(BinaryUtil.toByta(sysItem.getWTimeToIdle()));
				// //Time To Idle
				out.write(BinaryUtil.toByta(sysItem.getWNormalOffset())); // Normal
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWNormalFactor())); // Normal
																			// Factor
				out.write(BinaryUtil.toByta(sysItem.getWOnairOffset())); // Onair
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWOnairFactor())); // Onair
																			// Factor
				out.write(BinaryUtil.toByta(sysItem.getWMoveOffset())); // Move
																		// Offset
				out.write(BinaryUtil.toByta(sysItem.getWMoveFactor())); // Move
																		// Factor

				if (sysItem.getWSightInfo() != null
						&& !"0".equals(sysItem.getWSightInfo())) {
					String[] sightInfo = sysItem.getWSightInfo().split(";");
					out.write(BinaryUtil.toByta(sightInfo.length));
					// SightInfo
					for (int i = 0; i < sightInfo.length; i++) {
						String[] info = sightInfo[i].split(",");
						if (info.length == 5) {
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[0]))); // level;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[1]))); // mouse_sensitivity;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[2]))); // move_speed_offset;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[3]))); // move_speed_offset;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[4]))); // move_speed_offset;
						} else {
							throw new Exception(
									ExceptionMessage.W_SIGHT_INFO_ERROR);
						}
					}
				} else {
					out.write(BinaryUtil.toByta(0));
				}

				if (sysItem.getWId() == Constants.WEAPON_PISTOL_ID
						|| sysItem.getWId() == Constants.WEAPON_DOUBLE_PISTOL_ID) {// pistol
					out.write(BinaryUtil.toByta(sysItem.getWUpModifier())); // wUpModifier
					out.write(BinaryUtil.toByta(sysItem.getWAccuracyTime())); // wAccuracyTime
					out.write(BinaryUtil.toByta(sysItem
							.getWAccuracyTimeModefier())); // wAccuracyTimeModefier
					out.write(BinaryUtil.toByta(sysItem.getWMaxAccuracy())); // wMaxAccuracy
					out.write(BinaryUtil.toByta(sysItem.getWMinAccuracy())); // wMinAccuracy

					out.write(BinaryUtil.toByta(sysItem.getWNormalUpBase())); // Up
																				// Base
					out.write(BinaryUtil
							.toByta(sysItem.getWNormalLateralBase())); // Lateral
																		// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWNormalUpModifier())); // Up Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWNormalLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpMax())); // Up
																				// Max
					out
							.write(BinaryUtil.toByta(sysItem
									.getWNormalLateralMax())); // Lateral Max
					out.write(BinaryUtil.toByta(sysItem.getWNormalDirChange())); // Dir
																					// Change
					// Move
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpBase())); // Up
																			// Base
					out.write(BinaryUtil.toByta(sysItem.getWMoveLateralBase())); // Lateral
																					// Base
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpModifier())); // Up
																				// Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWMoveLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpMax())); // Up
																			// Max
					out.write(BinaryUtil.toByta(sysItem.getWMoveLateralMax())); // Lateral
																				// Max
					out.write(BinaryUtil.toByta(sysItem.getWMoveDirChange())); // Dir
																				// Change
					// Onair
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpBase())); // Up
																				// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWOnairLateralBase())); // Lateral Base
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpModifier())); // Up
																					// Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWOnairLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpMax())); // Up
																			// Max
					out.write(BinaryUtil.toByta(sysItem.getWOnairLateralMax())); // Lateral
																					// Max
					out.write(BinaryUtil.toByta(sysItem.getWOnairDirChange())); // Dir
																				// Change
					// Crouch
					out.write(BinaryUtil.toByta(sysItem.getWCrouchUpBase())); // Up
																				// Base
					out.write(BinaryUtil
							.toByta(sysItem.getWCrouchLateralBase())); // Lateral
																		// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWCrouchUpModifier())); // Up Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWCrouchLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWCrouchUpMax())); // Up
																				// Max
					out
							.write(BinaryUtil.toByta(sysItem
									.getWCrouchLateralMax())); // Lateral Max
					out.write(BinaryUtil.toByta(sysItem.getWCrouchDirChange())); // Dir
																					// Change
				} else if (sysItem.getWId() == Constants.WEAPON_SNIPER_GUN_ID) {
					out.write(BinaryUtil
							.toByta(sysItem.getWSightNormalOffset()));
					out
							.write(BinaryUtil.toByta(sysItem
									.getWSightOnairOffset()));
					out.write(BinaryUtil.toByta(sysItem.getWSightMoveOffset()));
					out.write(BinaryUtil.toByta(sysItem.getWReadyTime()));

				} else if (sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID) {// 机枪塔
					out.write(BinaryUtil.toByta(sysItem.getWMaxDistance()));
					out.write(BinaryUtil.toByta(sysItem.getWMaxaliveTime()));
					out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
					out.write(BinaryUtil.toByta(sysItem.getWShowSpeed()));
					out.write(BinaryUtil.toByta(sysItem.getWMaxAccuracy()));
					out.write(BinaryUtil.toByta(sysItem.getWAccuracyTime()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialRange()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialLasttime()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialHurt()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialLasthurt()));
					out.write(BinaryUtil.toByta(sysItem.getWUpModifier()));
				
				}
				else if (sysItem.getWId() == Constants.WEAPON_SHOT_GUN_ID
						|| sysItem.getWId() == Constants.WEAPON_MACHINE_GUN_ID) {// 散弹枪
					// 机枪
					out.write(BinaryUtil.toByta(sysItem
									.getWShootBulletCount()));
					out.write(BinaryUtil.toByta(sysItem.getWSpread()));
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpBase())); // Up
					// Base
					out.write(BinaryUtil.toByta(sysItem
									.getWNormalUpModifier())); // Up
					// Modifier
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpMax())); // Up
					// Max
					if (sysItem.getWId() == Constants.WEAPON_MACHINE_GUN_ID) {// 机枪
						out
								.write(BinaryUtil.toByta(sysItem
										.getWFireMaxSpeed()));
						out.write(BinaryUtil.toByta(sysItem
								.getWFireStartSpeed()));
						out.write(BinaryUtil.toByta(sysItem
								.getWFireAceleration()));
						out.write(BinaryUtil.toByta(sysItem
								.getWFireResistance()));
						out.write(BinaryUtil.toByta(sysItem.getWReadyTime()));
						// ================luncher==================
						// int ammo_type;
						out.write(BinaryUtil.toByta(sysItem.getWAmmoType()));
						// float ammo_charge_time_max
						out.write(BinaryUtil.toByta(sysItem.getWMaxDistance()));
						// float fly_speed[3];
						out.write(BinaryUtil.toByta(sysItem.getWFlySpeed())); // fly_speed[0]
						out.write(BinaryUtil.toByta(sysItem.getWUpModifier())); // fly_speed[1]
						out
								.write(BinaryUtil.toByta(sysItem
										.getWAccuracyTime())); // fly_speed[2]
						// float fly_speed_multiple[3];
						out.write(BinaryUtil.toByta(sysItem
								.getWAccuracyTimeModefier())); // fly_speed_multiple[0]
						out.write(BinaryUtil.toByta(sysItem.getWMaxAccuracy())); // fly_speed_multiple[1]
						out.write(BinaryUtil.toByta(sysItem.getWMinAccuracy())); // fly_speed_multiple[2]

						// ================ammo======================
						out
								.write(BinaryUtil.toByta(sysItem
										.getWMaxaliveTime()));
						out.write(sysItem.getWGravity());
						out.write(BinaryUtil.toByta(sysItem.getWHurt()));
						out.write(BinaryUtil.toByta(sysItem.getWAmmopartKey()));
						out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
						out.write(BinaryUtil.toByta(sysItem
								.getWDmgModifyTimerMin()));
						out.write(BinaryUtil.toByta(sysItem
								.getWDmgModifyTimerMax()));
						out
								.write(BinaryUtil.toByta(sysItem
										.getWDmgModifyMin()));
						out
								.write(BinaryUtil.toByta(sysItem
										.getWDmgModifyMax()));
						out.write(BinaryUtil
								.toByta(sysItem.getWCapsuleHeight()));
						out.write(BinaryUtil
								.toByta(sysItem.getWCapsuleRadius()));
					}
				} else if (sysItem.getWId() == Constants.WEAPON_SUBMACHINE_ID
						|| sysItem.getWId() == Constants.WEAPON_RIFFLE_ID) {// 冲锋
																			// 枪,自动步枪
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpBase())); // Up
																				// Base
					out.write(BinaryUtil
							.toByta(sysItem.getWNormalLateralBase())); // Lateral
																		// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWNormalUpModifier())); // Up Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWNormalLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpMax())); // Up
																				// Max
					out
							.write(BinaryUtil.toByta(sysItem
									.getWNormalLateralMax())); // Lateral Max
					out.write(BinaryUtil.toByta(sysItem.getWNormalDirChange())); // Dir
																					// Change
					// Move
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpBase())); // Up
																			// Base
					out.write(BinaryUtil.toByta(sysItem.getWMoveLateralBase())); // Lateral
																					// Base
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpModifier())); // Up
																				// Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWMoveLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWMoveUpMax())); // Up
																			// Max
					out.write(BinaryUtil.toByta(sysItem.getWMoveLateralMax())); // Lateral
																				// Max
					out.write(BinaryUtil.toByta(sysItem.getWMoveDirChange())); // Dir
																				// Change
					// Onair
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpBase())); // Up
																				// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWOnairLateralBase())); // Lateral Base
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpModifier())); // Up
																					// Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWOnairLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWOnairUpMax())); // Up
																			// Max
					out.write(BinaryUtil.toByta(sysItem.getWOnairLateralMax())); // Lateral
																					// Max
					out.write(BinaryUtil.toByta(sysItem.getWOnairDirChange())); // Dir
																				// Change
					// Crouch
					out.write(BinaryUtil.toByta(sysItem.getWCrouchUpBase())); // Up
																				// Base
					out.write(BinaryUtil
							.toByta(sysItem.getWCrouchLateralBase())); // Lateral
																		// Base
					out
							.write(BinaryUtil.toByta(sysItem
									.getWCrouchUpModifier())); // Up Modifier
					out.write(BinaryUtil.toByta(sysItem
							.getWCrouchLateralModifier())); // Lateral Modifier
					out.write(BinaryUtil.toByta(sysItem.getWCrouchUpMax())); // Up
																				// Max
					out
							.write(BinaryUtil.toByta(sysItem
									.getWCrouchLateralMax())); // Lateral Max
					out.write(BinaryUtil.toByta(sysItem.getWCrouchDirChange())); // Dir
																					// Change

				} else if (sysItem.getWId() == Constants.WEAPON_CURE_GUN_ID) {// 治愈类
					out.write(BinaryUtil.toByta(sysItem.getWMaxDistance()));
					out.write(BinaryUtil.toByta(sysItem.getWAddBlood()));
				} else if (sysItem.getWId() == Constants.WEAPON_MEDITNEEDLE_GUN_ID
						|| sysItem.getWId() == Constants.WEAPON_ROCKET_LUNCHER_ID
						|| sysItem.getWId() == Constants.WEAPON_BOW_ID
						|| sysItem.getWId() == Constants.WEAPON_SIGNAL_GUN_ID
						|| sysItem.getWId() == Constants.WEAPON_ARROW_ID) {// 投射类
					out.write(BinaryUtil.toByta(sysItem.getWAmmoType()));
					out.write(BinaryUtil.toByta(sysItem.getWFlySpeed()));
					out.write(BinaryUtil.toByta(sysItem.getWSpread()));
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpBase()));
					out
							.write(BinaryUtil.toByta(sysItem
									.getWNormalUpModifier()));
					out.write(BinaryUtil.toByta(sysItem.getWNormalUpMax()));
					out.write(BinaryUtil.toByta(sysItem.getWMaxaliveTime()));
					out.write(BinaryUtil.toByta((byte) sysItem.getWGravity()));
					out.write(BinaryUtil.toByta(sysItem.getWHurt()));
					out.write(BinaryUtil.toByta(sysItem.getWAmmopartKey()));
					out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
					out.write(BinaryUtil.toByta(sysItem.getWThrowOutTime()));

					out.write(BinaryUtil
							.toByta(sysItem.getWDmgModifyTimerMin()));
					out.write(BinaryUtil
							.toByta(sysItem.getWDmgModifyTimerMax()));
					out.write(BinaryUtil.toByta(sysItem.getWDmgModifyMin()));
					out.write(BinaryUtil.toByta(sysItem.getWDmgModifyMax()));
					out.write(BinaryUtil.toByta(sysItem.getWCapsuleHeight()));
					out.write(BinaryUtil.toByta(sysItem.getWCapsuleRadius()));
					// System.out.println(sysItem.getDisplayNameCN()+"
					// :"+sysItem.getWStabTime()+"
					// "+sysItem.getWStabLightTime()+"
					// "+sysItem.getWStabDistance()+"
					// "+sysItem.getWStabLightDistance()
					// +" "+sysItem.getWStabWidth()+"
					// "+sysItem.getWBackFactor()+" "+sysItem.getWStabHurt());
					out.write(BinaryUtil.toByta(sysItem.getWStabTime()));
					out.write(BinaryUtil.toByta(sysItem.getWStabLightTime()));
					out.write(BinaryUtil.toByta(sysItem.getWStabDistance()));
					out.write(BinaryUtil
							.toByta(sysItem.getWStabLightDistance()));
					out.write(BinaryUtil.toByta(sysItem.getWStabWidth()));
					out.write(BinaryUtil.toByta(sysItem.getWBackFactor()));
					out.write(BinaryUtil.toByta(sysItem.getWStabHurt()));
					out.write(BinaryUtil.toByta(sysItem.getWStabLightHurt()));
					out.write(BinaryUtil.toByta(sysItem.getWBackBoostPlus()));

				} else if (sysItem.getWId() == Constants.WEAPON_FLAME_GUN_ID) {// 喷火枪
				// out.write(BinaryUtil.toByta(sysItem.getWLastHurt()));
				// out.write(BinaryUtil.toByta(sysItem.getWLastTime()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialDistance()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialRange()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialLasttime()));
					// out.write(BinaryUtil.toByta(sysItem.getWSpecialHurt()));
					// out.write(BinaryUtil.toByta(sysItem.getWSpecialLasthurt()));
					out.write(BinaryUtil.toByta(sysItem.getWParticlenum()));
					out.write(BinaryUtil.toByta(sysItem.getWShowSpeed()));
					out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
				}else if(sysItem.getWId()==Constants.WEAPON_ZYZDZ_EDIT_DEFAULT){
					
					
					out.write(BinaryUtil.toByta(sysItem.getWMaxDistance()));
					out.write(BinaryUtil.toByta(sysItem.getWMaxaliveTime()));
					out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
					out.write(BinaryUtil.toByta(sysItem.getWShowSpeed()));
					out.write(BinaryUtil.toByta(sysItem.getWMaxAccuracy()));
					out.write(BinaryUtil.toByta(sysItem.getWAccuracyTime()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialRange()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialLasttime()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialHurt()));
					out.write(BinaryUtil.toByta(sysItem.getWSpecialLasthurt()));
					out.write(BinaryUtil.toByta(sysItem.getWUpModifier()));
		
				}

			} else if (sysItem.getWId() == Constants.WEAPON_KNIFE_ID) {// 刀
				out.write(BinaryUtil.toByta(this.wStabTime)); // Stab Time
				out.write(BinaryUtil.toByta(sysItem.getWStabLightTime())); // Stab
																			// Light
																			// Time

				out.write(BinaryUtil.toByta(sysItem.getWStabDistance()));
				out.write(BinaryUtil.toByta(sysItem.getWStabLightDistance()));
				out.write(BinaryUtil.toByta(sysItem.getWStabWidth()));
				out.write(BinaryUtil.toByta(sysItem.getWBackFactor()));
				out.write(BinaryUtil.toByta((float) this.wStabHurt)); // Stab
																		// Hurt
				out.write(BinaryUtil.toByta((float) this.wStabHurt)); // Stab
																		// Light
																		// Hurt
				out.write(BinaryUtil.toByta(sysItem.getWBackBoostPlus()));
			} else if (sysItem.getWId() == Constants.WEAPON_WAR_DRUM
					|| sysItem.getWId() == Constants.WEAPON_MILK) {
				// Gun Info
				out.write(BinaryUtil.toByta(sysItem.getWAccuracyDivisor())); // Accuracy
																				// Divisor
				out.write(BinaryUtil.toByta(sysItem.getWAccuracyOffset())); // Accuracy
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWMaxInaccuracy())); // Max
																			// Inaccuracy
				out.write(BinaryUtil.toByta(sysItem.getWPenetration())); // Penetration
				out.write(BinaryUtil.toByta(sysItem.getWDamage())); // Damage
				out.write(BinaryUtil.toByta(sysItem.getWRangeModifier())); // Range
																			// Modifier
				out.write(BinaryUtil.toByta(sysItem.getWRangeStart())); // wRangeStart
				out.write(BinaryUtil.toByta(sysItem.getWRangeEnd())); // wRangeEnd
				out.write(BinaryUtil.toByta(this.wFireTime)); // Fire Time
				out.write(BinaryUtil.toByta(sysItem.getWReloadTime())); // Reload
																		// Time
				out.write(BinaryUtil.toByta(sysItem.getWAmmoOneClip())); // Ammo
																			// One
																			// Clip
				out.write(BinaryUtil.toByta(sysItem.getWAmmoCount())); // Ammo
																		// Count
				out.write(BinaryUtil.toByta(sysItem.getWAutoFire()
								.byteValue())); // Auto Fire
				// out.write(BinaryUtil.toByta(sysItem.getWTimeToIdle()));
				// //Time To Idle
				out.write(BinaryUtil.toByta(sysItem.getWNormalOffset())); // Normal
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWNormalFactor())); // Normal
																			// Factor
				out.write(BinaryUtil.toByta(sysItem.getWOnairOffset())); // Onair
																			// Offset
				out.write(BinaryUtil.toByta(sysItem.getWOnairFactor())); // Onair
																			// Factor
				out.write(BinaryUtil.toByta(sysItem.getWMoveOffset())); // Move
																		// Offset
				out.write(BinaryUtil.toByta(sysItem.getWMoveFactor())); // Move
																		// Factor
				if (sysItem.getWSightInfo() != null
						&& !"0".equals(sysItem.getWSightInfo())) {
					String[] sightInfo = sysItem.getWSightInfo().split(";");
					out.write(BinaryUtil.toByta(sightInfo.length));
					// SightInfo
					for (int i = 0; i < sightInfo.length; i++) {
						String[] info = sightInfo[i].split(",");
						if (info.length == 4) {
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[0]))); // level;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[1]))); // mouse_sensitivity;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[2]))); // move_speed_offset;
							out.write(BinaryUtil.toByta(Float
									.parseFloat(info[3]))); // move_speed_offset;
						} else {
							throw new Exception(
									ExceptionMessage.W_SIGHT_INFO_ERROR);
						}
					}
				} else {
					out.write(BinaryUtil.toByta(0));
				}
				out.write(BinaryUtil.toByta(sysItem.getWHurtRange()));
			} else if (sysItem.getWId() >= Constants.WEAPON_THROW_ID
					&& sysItem.getWId() <= Constants.WEAPON_SMOKE_ID) {// 雷
				out.write(BinaryUtil.toByta(sysItem.getWExplodeTime())); // Explode
																			// Time
				out.write(BinaryUtil.toByta(sysItem.getWReadyTime())); // Ready
																		// Time
				out.write(BinaryUtil.toByta(sysItem.getWThrowOutTime())); // Throw
																			// Out
																			// Time
				if (sysItem.getWId() == Constants.WEAPON_GRENADE_ID) {// 手雷
					out.write(BinaryUtil.toByta(sysItem.getWHurtRange())); // Hurt
																			// Range
					out.write(BinaryUtil.toByta(sysItem.getWHurt())); // Hurt
				}
				if (sysItem.getWId() == Constants.WEAPON_FLASH_ID) {// 闪光
					out.write(BinaryUtil.toByta(sysItem.getWFlashRangeStart()));
					out.write(BinaryUtil.toByta(sysItem.getWFlashRangeEnd()));
					out.write(BinaryUtil.toByta(sysItem.getWTimeMax()));
					out.write(BinaryUtil.toByta(sysItem.getWTimeFade()));
					out.write(BinaryUtil.toByta(sysItem.getWFlashBackFactor()));
				}
				if (sysItem.getWId() == Constants.WEAPON_SMOKE_ID) {
					out.write(BinaryUtil.toByta(sysItem.getWTime()));
				}
			}
		}
	}

	public void writeCostume(OutputStream out, SysItem sysItem,
			SysCharacter sysCharacter,int... suitArgus) throws Exception {
		writeCostumeBaseInfo(out, sysItem, sysCharacter, suitArgus);
		if (this.sysItem == null) {
			this.sysItem = ServiceLocator.getService
					.getSysItemByItemId(this.itemId);
		}
		out.write(BinaryUtil.toByta((this.cResistanceFire + this.sysItem
				.getCResistanceFire())));
		out.write(BinaryUtil.toByta((this.cResistanceBlast + this.sysItem
				.getCResistanceBlast())));
		out.write(BinaryUtil.toByta((this.cResistanceBullet + this.sysItem
				.getCResistanceBullet())));
		out.write(BinaryUtil.toByta((this.cResistanceKnife + this.sysItem
				.getCResistanceKnife())));
		out.write(BinaryUtil.toByta((this.cBloodAdd + this.sysItem
				.getCBloodAdd())));
	}

	/**
	 * method to initial item without part item
	 */
	public void initWithoutPart(SysItem sysItem) {
		String[] resStable = sysItem.getResourceStable().split(
				Constants.DELIMITER_RESOURCE_STABLE);
		String[] resChangeable = sysItem.getResourceChangeable().split(
				Constants.DELIMITER_RESOURCE_CHANGEABLE);
		playerItemInit(resStable, resChangeable, sysItem);
		itemName = sysItem.getName();
		itemDisplayName = sysItem.getDisplayNameCN();
	}

	private void playerItemInit(String[] resStable, String[] resChangeable,
			SysItem sysItem) {
		initGunProperty(sysItem);
		initResource(resStable, resChangeable, sysItem);
		initPrice();
		calculateTimeLeft();
		if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE
				|| sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE
				|| sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
			calculateParam(sysItem);
		}
	}

	public void initGunProperty(SysItem sysItem) {
		int color = 0;
		List<Property> propertyList = new ArrayList<Property>();
		List<StrProperty> strPropertyList = new ArrayList<StrProperty>();
		if (this.gunProperty1 != null && !this.gunProperty1.isEmpty()) {
			gunPropertyList1 = new ArrayList<Integer>();
			String[] gunPropertys = gunProperty1
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					gunPropertyList1.add(index);
					gunPropertyList1.add(value);
					gunPropertyList1.add(value2);
					gunPropertyList1.add(time);
				}
			}
		}
		StrProperty strProperty2 = new StrProperty();
		strProperty2.setIndex(1);
		gunPropertyList2 = new ArrayList<Integer>();
		if (this.gunProperty2 != null && !this.gunProperty2.isEmpty()) {
			String pro2 = this.gunProperty2;
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				strProperty2.setOpen(Constants.NUM_ONE);
				String[] splitString = gunProperty2
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty2.setState(Constants.NUM_ONE);
				}
				if (gunProperty2.length() > 4) {
					pro2 = splitString[2];
					strProperty2.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro2
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					color = 4;
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					//是工程兵武器（即哨兵机枪类）就将其属性转为工程兵特有属性 ，   同时属性为手动镶嵌宝石。
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty2,gunPropertyList2,sysItem);
					}else{
						gunPropertyList2.add(index);
						gunPropertyList2.add(value);
						gunPropertyList2.add(value2);
						gunPropertyList2.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty2.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
				}
			}
		}
		strPropertyList.add(strProperty2);

		StrProperty strProperty3 = new StrProperty();
		strProperty3.setIndex(2);
		gunPropertyList3 = new ArrayList<Integer>();
		if (this.gunProperty3 != null && !this.gunProperty3.isEmpty()) {
			String pro3 = this.gunProperty3;
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				strProperty3.setOpen(Constants.NUM_ONE);
				String[] splitString = gunProperty3
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty3.setState(Constants.NUM_ONE);
				}
				if (gunProperty3.length() > 4) {
					pro3 = splitString[2];
					strProperty3.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro3
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					color = 3;
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty3,gunPropertyList3,sysItem);
					}else{
						gunPropertyList3.add(index);
						gunPropertyList3.add(value);
						gunPropertyList3.add(value2);
						gunPropertyList3.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty3.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
				}
			}
		}
		strPropertyList.add(strProperty3);

		StrProperty strProperty4 = new StrProperty();
		strProperty4.setIndex(3);
		gunPropertyList4 = new ArrayList<Integer>();
		if (this.gunProperty4 != null && !this.gunProperty4.isEmpty()) {
			String pro4 = this.gunProperty4;
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				strProperty4.setOpen(Constants.NUM_ONE);
				String[] splitString = gunProperty4
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty4.setState(Constants.NUM_ONE);
				}
				if (gunProperty4.length() > 4) {
					pro4 = splitString[2];
					strProperty4.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro4
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					color = 2;
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty4,gunPropertyList4,sysItem);
					}else{
						gunPropertyList4.add(index);
						gunPropertyList4.add(value);
						gunPropertyList4.add(value2);
						gunPropertyList4.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty4.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
				}
			}
		}
		strPropertyList.add(strProperty4);

		StrProperty strProperty5 = new StrProperty();
		strProperty5.setIndex(4);
		gunPropertyList5 = new ArrayList<Integer>();
		if (this.gunProperty5 != null && !this.gunProperty5.isEmpty()) {
			String pro5 = this.gunProperty5;
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				strProperty5.setOpen(Constants.NUM_ONE);
				String[] splitString = gunProperty5
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty5.setState(Constants.NUM_ONE);
				}
				if (gunProperty5.length() > 4) {
					pro5 = splitString[2];
					strProperty5.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro5
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					color = 5;
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty5,gunPropertyList5,sysItem);
					}else{
						gunPropertyList5.add(index);
						gunPropertyList5.add(value);
						gunPropertyList5.add(value2);
						gunPropertyList5.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty5.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
				}
			}
		}
		strPropertyList.add(strProperty5);

		StrProperty strProperty6 = new StrProperty();
		strProperty6.setIndex(5);
		gunPropertyList6 = new ArrayList<Integer>();
		if (this.gunProperty6 != null && !this.gunProperty6.isEmpty()) {
			String pro6 = this.gunProperty6;
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				strProperty6.setOpen(Constants.NUM_ONE);
				String[] splitString = gunProperty6
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty6.setState(Constants.NUM_ONE);
				}
				if (gunProperty6.length() > 4) {
					pro6 = splitString[2];
					strProperty6.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro6
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty6,gunPropertyList6,sysItem);
					}else{
						gunPropertyList6.add(index);
						gunPropertyList6.add(value);
						gunPropertyList6.add(value2);
						gunPropertyList6.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty6.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
				}
			}
		}
		strPropertyList.add(strProperty6);

		StrProperty strProperty7 = new StrProperty();
		strProperty7.setIndex(6);
		gunPropertyList7 = new ArrayList<Integer>();
		if (this.gunProperty7 != null && !this.gunProperty7.isEmpty()) {
			String pro7 = this.gunProperty7;
			strProperty7.setOpen(Constants.NUM_ONE);
			if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
				String[] splitString = gunProperty7
						.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (splitString[0].equals("1")) {
					strProperty7.setState(Constants.NUM_ONE);
				}
				if (gunProperty7.length() > 4) {
					pro7 = splitString[2];
					strProperty7.setLevel(Integer.parseInt(splitString[1]));
				}
			}
			String[] gunPropertys = pro7
					.split(Constants.DELIMITER_RESOURCE_STABLE);
			if (gunPropertys.length != 0 && gunPropertys.length % 4 == 0) {
				for (int i = 0; i < gunPropertys.length; i++) {
					Integer index = StringUtil.toInt(gunPropertys[i]);
					Integer value = StringUtil.toInt(gunPropertys[++i]);
					Integer value2 = StringUtil.toInt(gunPropertys[++i]);
					Integer time = StringUtil.toInt(gunPropertys[++i]);
					Property p = new Property(index, value, value2, time, 1);
					if(sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID && sysItem.getIsStrengthen() == Constants.NUM_ONE){
						p=convertCommonToEngineer(p,strProperty7,gunPropertyList7,sysItem);
					}else{
						gunPropertyList7.add(index);
						gunPropertyList7.add(value);
						gunPropertyList7.add(value2);
						gunPropertyList7.add(time);
						if (sysItem.getIsStrengthen() == Constants.NUM_ONE) {
							strProperty7.setDesc(CommonUtil.getPropertyStr(index,
									value, value2, time));
							
							
						}
					}
					propertyList.add(p);
					
					
				}
			}
		}
		strPropertyList.add(strProperty7);

		// if(Constants.NUM_ONE == sysItem.getIsStrengthen()){
		holeNum = getOpenedUnSlottedHoleNum() + getSlottedHoleNum();
		if (level >= 4 && level < 7) {
			color = GunProperty.GREEND;
		} else if (level >= 7 && level < 10) {
			color = GunProperty.BLUE;
		} else if (level >= 10 && level < 13) {
			color = GunProperty.PURPLE;
		} else if (level >= 13 && level <= 15) {
			color = GunProperty.ORANGE;
		} else if (level > 15){
			color = GunProperty.COLORFUL;
		}
		
		// else if(level == 14){
		// color = GunProperty.UNKNOWN;
		// } else if(level == 15){
		// color = GunProperty.GOLD;
		// }
		int itemType = sysItem.getType() - 1;
		if (sysItem.getType() == Constants.DEFAULT_PART_TYPE
				&& sysItem.getSubType() == 2) {
			itemType++;
		}
		this.materialNeed = (int) ServiceLocator.getService
				.getStrengthenProperty(sysItem, this.level, 2);
		this.gpNeed = (int) ServiceLocator.getService.getStrengthenProperty(
				sysItem, this.level, 3);
		int nextLevel = this.level + 1;
		if (nextLevel >= 4 && nextLevel < 7) {
			nextColor = GunProperty.GREEND;
		} else if (nextLevel >= 7 && nextLevel < 10) {
			nextColor = GunProperty.BLUE;
		} else if (nextLevel >= 10 && nextLevel < 13) {
			nextColor = GunProperty.PURPLE;
		} else if (nextLevel >= 13 && nextLevel <= 15) {
			nextColor = GunProperty.ORANGE;
		}
		// else if(nextLevel == 14){
		// nextColor = GunProperty.UNKNOWN;
		// } else if(nextLevel >= 15){
		// nextColor = GunProperty.GOLD;
		// }

		// }

		//是工程兵武器（即哨兵机枪类）就将其属性转为工程兵特有属性
//		if (sysItem.getWId() == Constants.WEAPON_JQT_GUN_ID) {
//			List<Property> engineerPropertyList = new ArrayList<Property>();
//
//			int[] isHoleHasDiamon=new int[propertyList.size()];
//			for (int i = 0; i < propertyList.size(); i++) {
//				Property property = propertyList.get(i);
//				StrProperty strProperty=strPropertyList.get(i);
//				//确保strproperty与该property相对应
//				for(int j=i;j<strPropertyList.size();j++){
//					if(strPropertyList.get(j).getLevel()>0){
//						strProperty=strPropertyList.get(j);
//						break;
//					}
//				}
//				int engineerPropertyIndex = CombineProperty.propertyConvertMap.get(property.getIndex());
//			
//				int value = getConvertPropertyValue(property.getIndex(),CombineProperty.getSpecificProperty(0, property.getIndex(),strProperty.getLevel()),
//						property.getValue(), 
//						CombineProperty.getSpecificProperty(0,engineerPropertyIndex, strProperty.getLevel()));
//				Property engineerProperty = new Property(engineerPropertyIndex,
//						value, 0, 0, 1); // 工程兵属性只有一个属性值
//
//				
//				
//				switch(i){
//					case 0:
//						if(gunProperty2.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList2.clear();
//						gunPropertyList2.add(engineerPropertyIndex);
//						gunPropertyList2.add(value);
//						gunPropertyList2.add(0);
//						gunPropertyList2.add(0);
//						break;
//					case 1: 
//						if(gunProperty3.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList3.clear();
//						gunPropertyList3.add(engineerPropertyIndex);
//						gunPropertyList3.add(value);
//						gunPropertyList3.add(0);
//						gunPropertyList3.add(0);
//						break;
//					case 2: 
//						if(gunProperty4.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList4.clear();
//						gunPropertyList4.add(engineerPropertyIndex);
//						gunPropertyList4.add(value);
//						gunPropertyList4.add(0);
//						gunPropertyList4.add(0);
//						break;
//					case 3: 
//						if(gunProperty5.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList5.clear();
//						gunPropertyList5.add(engineerPropertyIndex);
//						gunPropertyList5.add(value);
//						gunPropertyList5.add(0);
//						gunPropertyList5.add(0);
//						break;
//					case 4: 
//						if(gunProperty6.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList6.clear();
//						gunPropertyList6.add(engineerPropertyIndex);
//						gunPropertyList6.add(value);
//						gunPropertyList6.add(0);
//						gunPropertyList6.add(0);
//						break;
//					case 5: 
//						if(gunProperty7.equals(CombineProperty.SLOTTED)){
//							isHoleHasDiamon[i]=1;
//							System.out.println("&&&"+i);
//						}
//						gunPropertyList7.clear();
//						gunPropertyList7.add(engineerPropertyIndex);
//						gunPropertyList7.add(value);
//						gunPropertyList7.add(0);
//						gunPropertyList7.add(0);
//						break;
//					default:
//						break;
//				}
//				strPropertyList.get(i).setDesc(
//						CommonUtil.getPropertyStr(engineerPropertyIndex, value,
//								0, 1));
//				System.out.println(strPropertyList.get(i).getDesc()+"*********"+strPropertyList.get(i).getState());
//				if(isHoleHasDiamon[i]!=1){
//					
//					engineerPropertyList.add(engineerProperty);
//				}
//				
//			}
//
//			propertyList = engineerPropertyList;
//		}

		Collections.sort(propertyList, new Comparator<Property>() {
			@Override
			public int compare(Property o1, Property o2) {
				return Integer.valueOf(o2.getColor()).compareTo(
						Integer.valueOf(o1.getColor()));
			}
		});
		gunProperty.setPropertyList(propertyList);
		gunProperty.setStrPropertyList(strPropertyList);
		gunProperty.setColor(color);
	}
	
	private Property convertCommonToEngineer(Property property,StrProperty strProperty, List<Integer> gunPropertyList,SysItem sysItem){
		int engineerPropertyIndex = CombineProperty.propertyConvertMap.get(property.getIndex());
		
		//属性34，是取时间值作为属性值
		int originalPropertyValue= property.getValue()==0 ? property.getTime() : property.getValue();
		
		int value = getConvertPropertyValue(property.getIndex(),CombineProperty.getSpecificProperty(0, property.getIndex(),strProperty.getLevel()),
				originalPropertyValue, 
				CombineProperty.getSpecificProperty(4,engineerPropertyIndex, strProperty.getLevel()));
	
		Property engineerProperty = new Property(engineerPropertyIndex,
				value, 0, 0, 1); // 工程兵属性只有一个属性值
		
		
		
		gunPropertyList.add(engineerPropertyIndex);
		gunPropertyList.add(value);
		gunPropertyList.add(0);
		gunPropertyList.add(0);
		
		if (sysItem.getIsStrengthen() == Constants.NUM_ONE){
			strProperty.setDesc(
				CommonUtil.getPropertyStr(engineerPropertyIndex, value,
						0, 1));
		}
		return engineerProperty;
	}
	
	/**
	 * 将源武器属性的值转换为对应的工程兵武器的属性值 
	 * @param index 源武器属性ID，大部分时间无用
	 * @param mainProperties 源武器属性值列表(pro1Min,pro1Max,pro2Min,pro2Max,time1Min,time1Max)
	 * @param propertyValue 源武器属性值(由于工程兵属性只有一个属性值，该参数仅取源武器属性中第一个非时间属性的属性值，若无则取时间属性值)
	 * @param engineerProperties 工程兵武器属性值列表(pro1Min,pro1Max,pro2Min,pro2Max,time1Min,time1Max)
	 * @return 工程兵武器对应属性值
	 */
	private int getConvertPropertyValue(int index,int[] mainProperties,
			int propertyValue, int[] engineerProperties) {
		float min = mainProperties[0];
		float max = mainProperties[1];
		float engMin = engineerProperties[0];
		float engMax = engineerProperties[1];

		if(min==0&&max==0){   //如果第一属性为0，向下找到第一个非0的属性作为转换对象
			for(int i=2;i<mainProperties.length;i+=2){
				if(mainProperties[i]!=0 && mainProperties[i+1]!=0){					
					min=mainProperties[i];
					max=mainProperties[i+1];
					break;
				}
			}
		}
		
		if(min==max){
			if(min==0){
				return 0;
			}
			return Math.round(engMax);
		}
		
		if(index==31){  // hardcode,该属性属性值1为↓，对应工程兵属性值为↑，故逆向对应
			return Math.round(engMax - (engMax - engMin)
			* ((propertyValue - min) / (max - min)));
		}
	
		//此处没有判断工程兵属性数组是由于其属性都是单属性值且在首位
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

	/**
	 * method to calculate the weapon params
	 */
	public double damageOffset = 1;
	public double shootSpeedOffset = 1;
	double nextDamangeOffset = 1;
	double nextShootSpeedOffset = 1;

	int wDamage = 0;
	int wStabHurt = 0;
	float wFireTime = 0;
	float wStabTime = 0;

	public void calculateParam(SysItem sysItem) {
		damageOffset = shootSpeedOffset = nextDamangeOffset = nextShootSpeedOffset = 1;
		// strengthen add
		if (this.level > 0) {
			damageOffset = 1 + ServiceLocator.getService.getStrengthenProperty(
					sysItem, level - 1, 0);
			shootSpeedOffset = 1 + ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 1);
		}
		if (this.level >= 0) {
			nextDamangeOffset = 1 + ServiceLocator.getService
					.getStrengthenProperty(sysItem, level, 0);
			nextShootSpeedOffset = 1 + ServiceLocator.getService
					.getStrengthenProperty(sysItem, level, 1);
		}
		// damage = (wDamage+50)/2
		if (sysItem.getSubType() != 3) {
			if (sysItem.getWDamage() == 0) {
				this.damange = 0;// weapon
			} else {
				this.wDamage = (int) (sysItem.getWDamage() * damageOffset);
				this.damange = (float) ((this.wDamage + 50) / 2);
				this.nextDamange = (this.level >= Constants.MAX_STRENGTH_LEVEL ? this.damange
						: (float) ((sysItem.getWDamage() * nextDamangeOffset + 50) / 2));
			}
		} else {
			if (sysItem.getWStabHurt() == 0) {
				this.damange = 0;// weapon
			} else {
				this.wStabHurt = (int) (sysItem.getWStabHurt() * damageOffset);
				this.damange = (float) ((this.wStabHurt + 50) / 2);
				this.nextDamange = (this.level >= Constants.MAX_STRENGTH_LEVEL ? this.damange
						: (float) ((sysItem.getWStabHurt() * nextDamangeOffset + 50) / 2));
			}
		}
		this.damange = NumberUtil.numberFormat(damange);
		
		float gstDamange = Float.parseFloat(String.valueOf(calculateGstLevelFightNum(sysItem)));
		//星级武器带来的提升
		this.damange *= gstDamange;
		this.nextDamange *= gstDamange;
		this.wDamage *= gstDamange;
		// shoot (1/firetime)*10
		if (sysItem.getSubType() != 3) {
			if (sysItem.getWFireTime() == 0) {
				this.shootSpeed = 0;
			} else {
				this.shootSpeed = (float) ((1 / sysItem.getWFireTime() * shootSpeedOffset) * 10);
				wFireTime = sysItem.getWFireTime() / (float) shootSpeedOffset;
				this.nextShootSpeed = (this.level >= 15 ? this.shootSpeed
						: (float) ((1 / sysItem.getWFireTime() * nextShootSpeedOffset) * 10));
			}
		} else {
			if (sysItem.getWStabTime() == 0) {
				this.shootSpeed = 0;
			} else {
				this.shootSpeed = (float) (((1 / sysItem.getWStabTime()) * shootSpeedOffset) * 10);
				wStabTime = sysItem.getWStabTime() / (float) shootSpeedOffset;
				this.nextShootSpeed = (this.level >= 15 ? this.shootSpeed
						: (float) (((1 / sysItem.getWStabTime()) * nextShootSpeedOffset) * 10));
			}
		}
		float k = NumberUtil.numberFormat(shootSpeed);
		this.shootSpeed = k;
		this.nextShootSpeed = NumberUtil.numberFormat(nextShootSpeed);
		if (sysItem.getIsStrengthen() != 0 || sysItem.getProvisional_item_flag()) {
			this.cResistanceFire = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceFire()));
			this.cResistanceBlast = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceBlast()));
			this.cResistanceBullet = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceBullet()));
			this.cResistanceKnife = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceKnife()));
			this.cBloodAdd = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 1)) * 100 * (1 + 0.01 * sysItem
					.getCBloodAdd()));
			if (this.level >= Constants.MAX_STRENGTH_LEVEL) {
				this.nextCResistanceFire = this.cResistanceFire;
				this.nextCResistanceBlast = this.cResistanceBlast;
				this.nextCResistanceBullet = this.cResistanceBullet;
				this.nextCResistanceKnife = this.cResistanceKnife;
				this.nextBloodAdd = this.cBloodAdd;
			} else {
				this.nextCResistanceFire = (float) ((ServiceLocator.getService
						.getStrengthenProperty(sysItem, level, 0)) * 100 * (1 + 0.01 * sysItem
						.getCResistanceFire()));
				this.nextCResistanceBlast = (float) ((ServiceLocator.getService
						.getStrengthenProperty(sysItem, level, 0)) * 100 * (1 + 0.01 * sysItem
						.getCResistanceBlast()));
				this.nextCResistanceBullet = (float) ((ServiceLocator.getService
						.getStrengthenProperty(sysItem, level, 0)) * 100 * (1 + 0.01 * sysItem
						.getCResistanceBullet()));
				this.nextCResistanceKnife = (float) ((ServiceLocator.getService
						.getStrengthenProperty(sysItem, level, 0)) * 100 * (1 + 0.01 * sysItem
						.getCResistanceKnife()));
				this.nextBloodAdd = (float) ((ServiceLocator.getService
						.getStrengthenProperty(sysItem, level, 1)) * 100 * (1 + 0.01 * sysItem
						.getCBloodAdd()));
			}

		} else if ("Y".equals(this.isDefault) && this.level > 0) {
			this.cResistanceFire = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceFire()));
			this.cResistanceBlast = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceBlast()));
			this.cResistanceBullet = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceBullet()));
			this.cResistanceKnife = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 0)) * 100 * (1 + 0.01 * sysItem
					.getCResistanceKnife()));
			this.cBloodAdd = (float) ((ServiceLocator.getService
					.getStrengthenProperty(sysItem, level - 1, 1)) * 100 * (1 + 0.01 * sysItem
					.getCBloodAdd()));
		}

		if (this.durable < 100) {
			this.repairCost = Math.round((100 - this.durable)
					* (sysItem.getWFixPrice()));
		} else {
			this.repairCost = 0;
		}
	}
	
	/**
	 * 获得星级带来的武器提升
	 * 
	 *	@param sysItem
	 *	@author OuYangGuang
	 *	@date 20140821
	 *	@return -星级带来的百分比
	 * */
	public double calculateGstLevelFightNum(SysItem sysItem){
		/*
		 * 武器合成效果目前只主武器 
		 * 该方法分别读取两次，依次顺序第一次计算武器战斗力，第二次计算武器伤害，计算时无视其余的计算
		 * 计算公式 主武器加成（包括强化、嵌入等等） * （Constants.getQualityPercent() * 星等级提升值/百分比）  —or—  [主武器加成 * 星际带来的提升百分比]
		 * @ＤＡ　20140801 OuYangGuang 
		 */ 
		if(this.getGst_level()>0){
			if(sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE && sysItem.getSubType() == Constants.NUM_ONE)
			{
				//星级提升带来的伤害
				double startHurt = 1.0 + Constants.getQualityPercent(sysItem.getRareLevel(),getGst_level(),sysItem.getType())  / 100.0;
				return startHurt; 		//星级带来的百分比
			}
		}
		return 1;
	}
	
	
	/**
	 * 获得星级带来的防具提升
	 * 
	 *	@param sysItem
	 *	@author OuYangGuang
	 *	@date 20141223
	 *	@return -星级带来的百分比
	 * */
	public double calculateGstLevelDefenceNum(SysItem sysItem){
		/*
		 * 权值 ：衣服3、翅膀2、帽子1
		 * 总防具加血百分比=[ n（衣服） * 权值（衣服） + n（翅膀） * 权值（翅膀） + n（帽子） * 权值（帽子） ] / 600		
		 */
		if(this.getGst_level()>0){
			if(sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE 
					|| sysItem.getType() == Constants.DEFAULT_PART_TYPE )
			{
				int defenceWeight = 0;	//权值
				if(sysItem.getType()==Constants.DEFAULT_COSTUME_TYPE){ //服饰
					defenceWeight=Constants.NUM_THREE;
				}else if (sysItem.getType()==Constants.DEFAULT_PART_TYPE){//翅膀and帽子
					defenceWeight=sysItem.getSubType()==Constants.NUM_ONE?Constants.NUM_ONE:sysItem.getSubType()==Constants.NUM_TWO?Constants.NUM_TWO:0;
				}
				
				//星级带来的提升
				double startHurt = Constants.getQualityPercent(sysItem.getRareLevel(),getGst_level(),sysItem.getType()) * defenceWeight;
				return startHurt; 		
			}
		}
		return 0;
	}
	/***
	 * 获得单防具星级带来的血量提升百分比
	 * 
	 *	@param -
	 *	@author OuYangGuang
	 *	@date 20141223
	 *	@return -星级带来的百分比
	 * */
	public double getStartTotalBloodAdd(){
		double startTotalBloodAdd=calculateGstLevelDefenceNum(this.sysItem);
		return startTotalBloodAdd>0 ? (startTotalBloodAdd / 600 + 1) : 1;
	}
	
	public void initPrice() {
		// init prices
		this.prices = new String[5];
		String payType = Constants.GP_PAY_STRING;
		String forever = "-1";
		if (Constants.CR_PAY == playerItemCurrency) {
			payType = Constants.CR_PAY_STRING;
		}
	}

	/**
	 * @param resStable
	 * @param resChangeable
	 *            method init resource with params init resource,resources
	 */
	private void initResource(String[] resStable, String[] resChangeable,
			SysItem sysItem) {
		ArrayList<String> resourceList = new ArrayList<String>();
		if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE
				&& resChangeable != null) {
			List<String> tmpList = new ArrayList<String>();
			for (int i = 0; i < resStable.length; i++) {
				if (!"".equals(resStable[i])) {
					tmpList.add(sysItem.getName() + "/" + resStable[i]);
				}
			}
			resourceList.clear();
			for (int i = 0; i < resChangeable.length; i++) {
				if (!"".equals(resChangeable[i]) && resChangeable[i] != null) {
					String[] changebles = resChangeable[i]
							.split(Constants.DELIMITER_RESOURCE_STABLE);
					for (int j = 0; j < changebles.length; j++) {// resChangeable有两个资源
						resourceList.add(changebles[j]);
						tmpList.add(sysItem.getName() + "/" + changebles[j]);
					}
				}
			}
			resource = new String[tmpList.size()];
			tmpList.toArray(resource);
			this.resources = resourceList;
		} else {
			for (int i = 0; i < resStable.length; i++) {
				String str = resStable[i];
				if (str != null && !"".equals(str)) {
					resourceList.add(resStable[i]);
				}
			}
			this.resources = resourceList;
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
	 * +-----------------------------------------------------------------------------------------------------------------------------+
	 * |------------------------------------------------>for combine system |
	 * +-----------------------------------------------------------------------------------------------------------------------------+
	 */
	private void resetGunPropertys(String value) {
		this.gunProperty2 = this.gunProperty3 = this.gunProperty4 = this.gunProperty5 = this.gunProperty6 = this.gunProperty7 = value;
	}

	public void takeOffAllDiamonds() {
		resetGunPropertys(CombineProperty.SLOTTED);
	}

	public void closeAllHoles() {
		resetGunPropertys(CombineProperty.UNOPEN);
	}

	public void takeOffDiamond(int index) {
		switch (index) {
		case 1:
			this.gunProperty2 = CombineProperty.SLOTTED;
			break;
		case 2:
			this.gunProperty3 = CombineProperty.SLOTTED;
			break;
		case 3:
			this.gunProperty4 = CombineProperty.SLOTTED;
			break;
		case 4:
			this.gunProperty5 = CombineProperty.SLOTTED;
			break;
		case 5:
			this.gunProperty6 = CombineProperty.SLOTTED;
			break;
		case 6:
			this.gunProperty7 = CombineProperty.SLOTTED;
			break;
		}
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

	public int getOpenedUnSlottedHoleNum() {
		int openButUnSlotted = 0;
		if (this.gunProperty2 != null
				&& gunProperty2.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		if (this.gunProperty3 != null
				&& gunProperty3.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		if (this.gunProperty4 != null
				&& gunProperty4.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		if (this.gunProperty5 != null
				&& gunProperty5.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		if (this.gunProperty6 != null
				&& gunProperty6.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		if (this.gunProperty7 != null
				&& gunProperty7.equals(CombineProperty.OPENED)) {
			openButUnSlotted++;
		}
		return openButUnSlotted;
	}

	public void openHole() {
		if ((getOpenedUnSlottedHoleNum() + getSlottedHoleNum()) < getMaxHoleNum()) {
			if (gunProperty2.equals(CombineProperty.UNOPEN)) {
				gunProperty2 = CombineProperty.OPENED;
			} else if (gunProperty3.equals(CombineProperty.UNOPEN)) {
				gunProperty3 = CombineProperty.OPENED;
			} else if (gunProperty4.equals(CombineProperty.UNOPEN)) {
				gunProperty4 = CombineProperty.OPENED;
			} else if (gunProperty5.equals(CombineProperty.UNOPEN)) {
				gunProperty5 = CombineProperty.OPENED;
			} else if (gunProperty6.equals(CombineProperty.UNOPEN)) {
				gunProperty6 = CombineProperty.OPENED;
			} else if (gunProperty7.equals(CombineProperty.UNOPEN)) {
				gunProperty7 = CombineProperty.OPENED;
			}
		}
	}

	public void closeHole() {
		if (getOpenedUnSlottedHoleNum() > 0) {
			if (gunProperty7.equals(CombineProperty.OPENED)) {
				gunProperty7 = CombineProperty.UNOPEN;
			} else if (gunProperty6.equals(CombineProperty.OPENED)) {
				gunProperty6 = CombineProperty.UNOPEN;
			} else if (gunProperty5.equals(CombineProperty.OPENED)) {
				gunProperty5 = CombineProperty.UNOPEN;
			} else if (gunProperty4.equals(CombineProperty.OPENED)) {
				gunProperty4 = CombineProperty.UNOPEN;
			} else if (gunProperty3.equals(CombineProperty.OPENED)) {
				gunProperty3 = CombineProperty.UNOPEN;
			} else if (gunProperty2.equals(CombineProperty.OPENED)) {
				gunProperty2 = CombineProperty.UNOPEN;
			}
		} else if (getSlottedHoleNum() > 0) {
			if (gunProperty7.equals(CombineProperty.SLOTTED)) {
				gunProperty7 = CombineProperty.UNOPEN;
			} else if (gunProperty6.equals(CombineProperty.SLOTTED)) {
				gunProperty6 = CombineProperty.UNOPEN;
			} else if (gunProperty5.equals(CombineProperty.SLOTTED)) {
				gunProperty5 = CombineProperty.UNOPEN;
			} else if (gunProperty4.equals(CombineProperty.SLOTTED)) {
				gunProperty4 = CombineProperty.UNOPEN;
			} else if (gunProperty3.equals(CombineProperty.SLOTTED)) {
				gunProperty3 = CombineProperty.UNOPEN;
			} else if (gunProperty2.equals(CombineProperty.SLOTTED)) {
				gunProperty2 = CombineProperty.UNOPEN;
			} else if (gunProperty7.length() > Constants.NUM_TWO) {
				gunProperty7 = CombineProperty.UNOPEN;
			} else if (gunProperty6.length() > Constants.NUM_TWO) {
				gunProperty6 = CombineProperty.UNOPEN;
			} else if (gunProperty5.length() > Constants.NUM_TWO) {
				gunProperty5 = CombineProperty.UNOPEN;
			} else if (gunProperty4.length() > Constants.NUM_TWO) {
				gunProperty4 = CombineProperty.UNOPEN;
			} else if (gunProperty3.length() > Constants.NUM_TWO) {
				gunProperty3 = CombineProperty.UNOPEN;
			} else if (gunProperty2.length() > Constants.NUM_TWO) {
				gunProperty2 = CombineProperty.UNOPEN;
			}
		}
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

	public int getFightNum() {
		if (this.sysItem != null) {
			
			if (sysItem.getIsStrengthen() == Constants.NUM_ZERO
					&& ( Constants.BOOLEAN_NO.equals(this.isDefault) || this.sysItem.getWId()==Constants.WEAPON_GRENADE_ID )) {
				fightNum = sysItem.getFigthNumOutput();
			} else {
				int lowPropertyNum = 0;
				int middlePropertyNum = 0;
				int highPropertyNum = 0;
				// if(sysItem.getIsStrengthen() == Constants.NUM_ONE){
				for (StrProperty pro : this.gunProperty.getStrPropertyList()) {
					if (pro.getLevel() == 1) {
						lowPropertyNum++;
					} else if (pro.getLevel() == 2) {
						middlePropertyNum++;
					} else if (pro.getLevel() == 3) {
						highPropertyNum++;
					}
				}
				// } else {
				// highPropertyNum = sysItem.getDefaultPropertySize();
				// }
				if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE) {
					if (sysItem.getSubType() == Constants.NUM_ONE) {// 主武器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[0][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[0][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[0][6]) * Double.parseDouble(String.valueOf(calculateGstLevelFightNum(sysItem)));
						fightNum = (int) (Constants.FIGHT_PARAM[0][0]
								* Math.pow(
										Constants.FIGHT_PARAM[0][1],
												this.level 
												+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[0][2])
												- Constants.FIGHT_PARAM[0][3]) * multiplier3);
						
					} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 副武器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[5][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[5][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[5][6]);
						fightNum = (int) (Constants.FIGHT_PARAM[5][0]
								* Math
										.pow(
												Constants.FIGHT_PARAM[5][1],
												this.level
														+ ((sysItem
																.getRareLevel() - 1) / Constants.FIGHT_PARAM[5][2])
														- Constants.FIGHT_PARAM[5][3]) * multiplier3);
					} else if (sysItem.getSubType() == Constants.NUM_THREE) {// 近身器
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[1][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[1][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[1][6]);
						fightNum = (int) (Constants.FIGHT_PARAM[1][0]
								* Math
										.pow(
												Constants.FIGHT_PARAM[1][1],
												this.level
														+ ((sysItem
																.getRareLevel() - 1) / Constants.FIGHT_PARAM[1][2])
														- Constants.FIGHT_PARAM[1][3]) * multiplier3);
					}
				} else if (sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
					double multiplier3 = (1 + lowPropertyNum
							/ Constants.FIGHT_PARAM[2][4] + middlePropertyNum
							/ Constants.FIGHT_PARAM[2][5] + highPropertyNum
							/ Constants.FIGHT_PARAM[2][6]);
					fightNum = (int) (Constants.FIGHT_PARAM[2][0] * ((Math
							.pow(
									Constants.FIGHT_PARAM[2][1],
									this.level
											+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[2][2])
											- Constants.FIGHT_PARAM[2][3]) * multiplier3)  * getStartTotalBloodAdd() - 1));
				} else if (sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
					if (sysItem.getSubType() == Constants.NUM_ONE) {// 帽子
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[3][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[3][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[3][6]);
						fightNum = (int) (Constants.FIGHT_PARAM[3][0] * ((Math
								.pow(
										Constants.FIGHT_PARAM[3][1],
										this.level
												+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[3][2])
												- Constants.FIGHT_PARAM[3][3]) * multiplier3) * getStartTotalBloodAdd() - 1));
					} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 配饰
						double multiplier3 = (1 + lowPropertyNum
								/ Constants.FIGHT_PARAM[4][4]
								+ middlePropertyNum
								/ Constants.FIGHT_PARAM[4][5] + highPropertyNum
								/ Constants.FIGHT_PARAM[4][6]);
						fightNum = (int) (Constants.FIGHT_PARAM[4][0] * ((Math
								.pow(
										Constants.FIGHT_PARAM[4][1],
										this.level
												+ ((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[4][2])
												- Constants.FIGHT_PARAM[4][3]) * multiplier3) * getStartTotalBloodAdd() - 1));
					}
				}
			}			
		}
		
		if (fightNum < 0) {
			fightNum = 0;
		}
		return fightNum;
	}

	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	/*
	 * +-----------------------------------------------------------------------------------------------------------------------------+
	 * |------------------------------------------------>for combine system end |
	 * +-----------------------------------------------------------------------------------------------------------------------------+
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
						.pow(
								Constants.FIGHT_PARAM[0][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[0][2])));
			} else if (this.sysItem.getSubType() == Constants.NUM_TWO) {// 副武器
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[5][0] * Math
						.pow(
								Constants.FIGHT_PARAM[5][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[5][2])));
			} else if (this.sysItem.getSubType() == Constants.NUM_THREE) {// 近身器
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[1][0] * Math
						.pow(
								Constants.FIGHT_PARAM[1][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[1][2])));
			}
		} else if (this.sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
			baseItemFightNum = (int) (Constants.FIGHT_PARAM[2][0] * ((Math
					.pow(
							Constants.FIGHT_PARAM[2][1],
							((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[2][2]))) - 1));
		} else if (this.sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
			if (this.sysItem.getSubType() == Constants.NUM_ONE) {// 帽子
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[3][0] * ((Math
						.pow(
								Constants.FIGHT_PARAM[3][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[3][2]))) - 1));
			} else if (this.sysItem.getSubType() == Constants.NUM_TWO) {// 配饰
				baseItemFightNum = (int) (Constants.FIGHT_PARAM[4][0] * (Math
						.pow(
								Constants.FIGHT_PARAM[4][1],
								((sysItem.getRareLevel() - 1) / Constants.FIGHT_PARAM[4][2])) - 1));
			}
		}
		return baseItemFightNum;
	}

	/**
	 * 获取强化带来的战斗力
	 * 
	 * @return
	 */
	public int getStrengthenItemFightNum() {
		int lowPropertyNum = 0;
		int middlePropertyNum = 0;
		int highPropertyNum = 0;
		for (StrProperty pro : this.gunProperty.getStrPropertyList()) {
			if (pro.getLevel() == 1) {
				lowPropertyNum++;
			} else if (pro.getLevel() == 2) {
				middlePropertyNum++;
			} else if (pro.getLevel() == 3) {
				highPropertyNum++;
			}
		}
		// int baseItemFightNum=0;//基础战斗力
		// int strengthenItemFightNum=0;//强化带来的战斗力
		double b = 0;
		double a = 0;
		if (this.sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE) {
			if (this.sysItem.getSubType() == Constants.NUM_ONE) {// 主武器
				a = Math.pow(Constants.FIGHT_PARAM[0][1], this.level) * Double.parseDouble(String.valueOf(calculateGstLevelFightNum(sysItem))) - 1;
				b = (lowPropertyNum / Constants.FIGHT_PARAM[0][4]
						+ middlePropertyNum / Constants.FIGHT_PARAM[0][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[0][6]);
				// baseItemFightNum = (int)(Constants.FIGHT_PARAM[0][0] *
				// Math.pow(Constants.FIGHT_PARAM[0][1],((sysItem.getRareLevel()
				// - 1)/Constants.FIGHT_PARAM[0][2])));
			} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 副武器
				a = Math.pow(Constants.FIGHT_PARAM[5][1], (this.level)) - 1;
				b = (lowPropertyNum / Constants.FIGHT_PARAM[5][4]
						+ middlePropertyNum / Constants.FIGHT_PARAM[5][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[5][6]);
				// baseItemFightNum = (int)(Constants.FIGHT_PARAM[5][0] *
				// Math.pow(Constants.FIGHT_PARAM[5][1],((sysItem.getRareLevel()
				// - 1)/Constants.FIGHT_PARAM[5][2])));
			} else if (sysItem.getSubType() == Constants.NUM_THREE) {// 近身器
				a = Math.pow(Constants.FIGHT_PARAM[1][1], (this.level)) - 1;
				b = (lowPropertyNum / Constants.FIGHT_PARAM[1][4]
						+ middlePropertyNum / Constants.FIGHT_PARAM[1][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[1][6]);
				// baseItemFightNum = (int)(Constants.FIGHT_PARAM[1][0] *
				// Math.pow(Constants.FIGHT_PARAM[1][1],((sysItem.getRareLevel()
				// - 1)/Constants.FIGHT_PARAM[1][2])));
			}
		} else if (sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
			a = Math.pow(Constants.FIGHT_PARAM[2][1], this.level) - 1;
			b = (lowPropertyNum / Constants.FIGHT_PARAM[2][4]
					+ middlePropertyNum / Constants.FIGHT_PARAM[2][5] + highPropertyNum
					/ Constants.FIGHT_PARAM[2][6]);
			// baseItemFightNum = (int)(Constants.FIGHT_PARAM[2][0] *
			// ((Math.pow(Constants.FIGHT_PARAM[2][1], ((sysItem.getRareLevel()
			// - 1)/Constants.FIGHT_PARAM[2][2]))) - 1));
		} else if (sysItem.getType() == Constants.DEFAULT_PART_TYPE) {
			if (sysItem.getSubType() == Constants.NUM_ONE) {// 帽子
				a = Math.pow(Constants.FIGHT_PARAM[3][1], this.level) - 1;
				b = (lowPropertyNum / Constants.FIGHT_PARAM[3][4]
						+ middlePropertyNum / Constants.FIGHT_PARAM[3][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[3][6]);
				// baseItemFightNum = (int)(Constants.FIGHT_PARAM[3][0] *
				// ((Math.pow(Constants.FIGHT_PARAM[3][1],((sysItem.getRareLevel()
				// - 1)/Constants.FIGHT_PARAM[3][2]) )) - 1));
			} else if (sysItem.getSubType() == Constants.NUM_TWO) {// 配饰
				a = Math.pow(Constants.FIGHT_PARAM[4][1], this.level) - 1;
				b = (lowPropertyNum / Constants.FIGHT_PARAM[4][4]
						+ middlePropertyNum / Constants.FIGHT_PARAM[4][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[4][6]);
				// baseItemFightNum = (int)(Constants.FIGHT_PARAM[4][0] *
				// (Math.pow(Constants.FIGHT_PARAM[4][1],((sysItem.getRareLevel()
				// - 1)/Constants.FIGHT_PARAM[4][2])) - 1));
			}
		}
		if ((a + b) != 0) {
			strengthenItemFightNum = (int) ((a / (a + b)) * (this.getFightNum() - baseItemFightNum));// 强化增加的战斗力
		}
		return strengthenItemFightNum;
	}

	public String getMeltingItemStr() {
		return meltingItemStr;
	}

	public void setMeltingItemStr(String meltingItemStr) {
		this.meltingItemStr = meltingItemStr;
	}
	
	public void writeResourceWarItem(OutputStream out) throws Exception {
//		out.write(BinaryUtil.toByta(this.getId()));
//		out.write(BinaryUtil.toByta((this.getQuantity()).shortValue()));
		
		sysItem=this.getSysItem();
		out.write(BinaryUtil.toByta(sysItem.getId()));
		out.write(BinaryUtil.toByta(sysItem.getSubType()));
		out.write(BinaryUtil.toByta(sysItem.getIId()));
		out.write(BinaryUtil.toByta(sysItem.getWUpModifier()));
		out.write(BinaryUtil.toByta(sysItem.getWAccuracyTime()));
		out.write(BinaryUtil.toByta(getProperty1().get("value")==null ? sysItem.getWAccuracyTimeModefier():getProperty1().get("value").floatValue()));
		out.write(BinaryUtil.toByta(getProperty2().get("value")==null ?sysItem.getWMaxAccuracy():getProperty2().get("value").floatValue()));
		out.write(BinaryUtil.toByta(getProperty6().get("value")==null ?sysItem.getWMinAccuracy():getProperty6().get("value").floatValue()));
		out.write(BinaryUtil.toByta(getProperty3().get("value")==null ?sysItem.getWHitAcceleration():getProperty3().get("value").floatValue()));
		out.write(BinaryUtil.toByta(sysItem.getWHitDistance()));
		out.write(BinaryUtil.toByta(sysItem.getName()));
		
	}
	
	
	public long getBuyCD(){
		long buyCD=0l;
		if(this.sysItem==null){
			return 0l;
		}else{
			if(this.sysItem.getType()==Constants.DEFAULT_ZYZDZ_TYPE&&this.sysItem.getSubType()==3&&sysItem.getTimeForCreate()!=0){
				//这里的有效时间就是上次更新时间
				Date dLastCreateTime=this.validDate;

				long past = System.currentTimeMillis()-dLastCreateTime.getTime() ;
				past=past<0?0:past;
				buyCD = sysItem.getTimeForCreateMsec()-past;
				buyCD = buyCD < 0 ? 0 : buyCD;
			}
		}
		return buyCD;
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

	public Map<String, Number> getProperty1() throws NumberFormatException,
			Exception {
		// 威力
		return ItemIntensifyUtil.getPropertyBasic(gunProperty1, ItemIntensifyUtil.TeamItemPropertyType.POWER, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWAccuracyTimeModefier());
	}

	public Map<String, Number> getProperty2() throws NumberFormatException,
			Exception {
		//射速
		return ItemIntensifyUtil.getPropertyBasic(gunProperty2, ItemIntensifyUtil.TeamItemPropertyType.FIRE_SPEED, MathOperator.DIV,this.getLevel(),sysItem,sysItem.getWMaxAccuracy());
	}

	public Map<String, Number> getProperty3() throws NumberFormatException,
			Exception {
		//hp
		return ItemIntensifyUtil.getPropertyBasic(gunProperty3, ItemIntensifyUtil.TeamItemPropertyType.HP, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWHitAcceleration());
	}

	public Map<String, Number> getProperty6() throws NumberFormatException,
			Exception {
		//坦克移动速度
		return ItemIntensifyUtil.getPropertyBasic(gunProperty6, ItemIntensifyUtil.TeamItemPropertyType.MOVE_SPEED, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWMinAccuracy());
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
	/**
	 * 只是在资源争夺战  个人物品拿个数时使用
	 * 因为playerItem本来是不可为0的，而个人坦克等个数是可以为0，
	 * 所以默认给了一个坦克给玩家而又要为0个
	 * 
	 * @return
	 */
	public int getQuantityForZYZDZPersonal(){
		if(this.getBuyCD()>0){
			if(quantity<2){
				setQuantity(2);
			}
			return quantity - 2;
		}
		if(quantity<1){
			setQuantity(1);
		}
		return quantity - 1;
	}
	public boolean isUpgrade() throws NumberFormatException, Exception {
		if(gunProperty1==null || !gunProperty1.contains(",")||
           gunProperty2==null || !gunProperty2.contains(",")||
		   gunProperty3==null || !gunProperty3.contains(",")||
		   gunProperty6==null || !gunProperty6.contains(",")){
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
		//精英合成 最高5级
		return gst_level == null?0:gst_level >= Constants.MAX_GST_LEVEL ? Constants.MAX_GST_LEVEL : gst_level;
	}

	/**
	 * 精英合成系统，增加经验
	 * 
	 * @param rareLevel 武器个数及其稀有度 every_day_experience 是否每日加成 mulCount 同品质加成次数
	 * 
	 * */
	public void addGst_Exp(boolean every_day_experience,final int mulCount,int[] rareLevel)throws Exception {
		//int quality_level = Constants.QUALITY_LEVEL(rareLevel);
		
		//当前经验
		int cExp = getGst_level_exp();
		//下级经验
		int nExp = (int)Constants.getGst_Level_Exp(getGst_level(), getSysItem().getRareLevel());
		//该武器增加经验
		int aExp = 0;
		int count = 2;
		for(int lExp : rareLevel)
		{
			//同品质武器加成
			aExp += count++<=mulCount?Constants.getGst_Level_Exp(lExp)*5:Constants.getGst_Level_Exp(lExp);
		}
		
		//每日一次
		aExp += every_day_experience?Constants.EVERY_DAY_EXPERIENCE[Constants.getQualityLevel(getSysItem().getRareLevel())-1]:0;
		
		
//		System.out.println("此次物品总经验："+(cExp + aExp));
//		System.out.println("下级所需经验(到达"+(getGst_level()+1)+"级需要经验)："+ nExp);
//		System.out.println("物品当前经验：" + cExp);
//		System.out.println("此次增加经验：" + aExp);
		
		if(getGst_level() < Constants.MAX_GST_LEVEL)
		{
			long tExp=nExp;					//下级经验
			int i = getGst_level();			//当前等级
			
			while((cExp + aExp) >= tExp && i < Constants.MAX_GST_LEVEL)	//连续升级;
			{
					//i = getGst_level();
					//tExp = nExp;
					i += 1;
					nExp=(int)Constants.getGst_Level_Exp(i, getSysItem().getRareLevel());
					tExp += nExp;
//					System.out.println("下一级所需经验("+(i+1)+"):"+nExp+"("+ tExp +") 当前可用经验：" + (cExp + aExp));
			}
			
			tExp -= nExp;
			//--i;
			
			if(getGst_level()<=0)
				this.gst_level=0;
			if(getGst_level_exp()<=0)
				this.gst_level_exp = 0;
			
//			System.out.println("停留等级："+i +" 所需经验："+tExp);
//			System.out.println("溢出经验："+(cExp + aExp - tExp));
			
			if(i > getGst_level()){
				this.gst_level = i>Constants.MAX_GST_LEVEL?Constants.MAX_GST_LEVEL:i;	//升级
//				System.out.println(this.gst_level);
				this.gst_level_exp = (i>Constants.MAX_GST_LEVEL?(int)Constants.getGst_Level_Exp(getGst_level()-1, getSysItem().getRareLevel()):Integer.parseInt(String.valueOf(((cExp + aExp) - tExp))));	//溢出经验
//				System.out.println("最终经验："+this.gst_level_exp);
			}else{
				this.gst_level_exp += aExp;
			}
		}
		ServiceLocator.updateService.updatePlayerItem(this);
	}
	
	public int getGst_level_exp (){
		return this.gst_level_exp!=null?this.gst_level_exp:0;
	}
}

