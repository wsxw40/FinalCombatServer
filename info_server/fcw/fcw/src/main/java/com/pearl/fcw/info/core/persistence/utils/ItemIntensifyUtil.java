package com.pearl.fcw.info.core.persistence.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.info.lobby.pojo.SysItem;
import com.pearl.fcw.info.lobby.pojo.TeamItem;
import com.pearl.fcw.info.lobby.utils.Constants;

/**
 * 物品强化工具类
 * 
 * @author zhang.li
 * 
 */
public class ItemIntensifyUtil {
	/**
	 * 一级内可强化的最高等级
	 * 
	 * @param ti
	 * @return
	 * @throws Exception
	 */
	public static int getMaxLevel(int subType) throws Exception {
		if (subType == TEAM_STORAGE_TYPE.DEFENSE_TOWER.getValue()) {
			return TEAM_ATTACK_MAX_UP_LEVEL;
		} else if (subType == TEAM_STORAGE_TYPE.WALL.getValue()) {
			return TEAM_WALL_MAX_LEVEL;
		} else if (subType == TEAM_STORAGE_TYPE.OIL_TANK.getValue()) {
			return TEAM_OIL_TANK_LEVEL;
		} else if (subType == TEAM_STORAGE_TYPE.PERSONAL_TANK.getValue()) {// 个人坦克
			return PERSONAL_ITEM_MAX_LEVEL;
		} else {
			throw new Exception();
		}
	}

	/**
	 * 属性增幅
	 * 
	 * @param ti
	 * @param subType
	 * @param intensifyType
	 * @param curLevel
	 * @return
	 * @throws Exception
	 */
	public static double getIntensifyProperty(int itemLevel, int subType,
			int intensifyType, int curLevel) throws Exception {
		
		int level = curLevel+(itemLevel-1)*getMaxLevel(subType);//物品当前的强化等级
		int maxLevel =0; //物品可强化的最高等级
		switch(subType){
			case 1:;
			case 2:;
			case 3:maxLevel = ITEM_MAX_LEVEL*getMaxLevel(subType);break;
			case 5:maxLevel =  OIL_TANK_MAX_LEVEL*getMaxLevel(subType);break;
			default : maxLevel = ITEM_MAX_LEVEL*getMaxLevel(subType);break;
		}
		double baseValue = 0;								//基础加成值
		if(subType==TEAM_STORAGE_TYPE.OIL_TANK.getValue()){
			baseValue = OIL_TANK_BASE_VALUE;
		}else if(subType==TEAM_STORAGE_TYPE.WALL.getValue()){
			baseValue = WALL_BASE_VALUE;
		}else{
			baseValue =TeamItemPropertyType.getTeamItemPropertyType(
					intensifyType).getBaseValue();
		}
		
		double value = Math.pow(baseValue, (double) (level) / maxLevel);
		BigDecimal b = new BigDecimal(value);
		MathContext mathContext = new MathContext(5);
		return b.round(mathContext).doubleValue();
	}

	/**
	 * 
	 * @param currency
	 * @param ti
	 * @param intensifyType
	 * @param subType
	 * @param curLevel
	 * @return
	 * @throws Exception
	 */
	public static int getIntensifyPrice(int currency, int itemLevel,
			int intensifyType, SysItem si, int curLevel) throws Exception {
		
		return 0;
	}

	/**
	 * 战队空间可配置物品的数量
	 * 
	 * @param placeLevel
	 *            战队空间等级
	 * 
	 * @return
	 */
	public static Map<Integer, Integer> getTeamItemDeploy(int placeLevel) {
		// map[战队空间等级，[teamItemId，数量]]
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();

		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		m.put(5434, 10);
		m.put(5435, 10);
		map.put(1, m);
		Map<Integer, Integer> m2 = new HashMap<Integer, Integer>();
		m2.put(5434, 20);
		m2.put(5435, 20);
		map.put(2, m2);
		// ...
		return map.get(placeLevel);
	}

	final public static int MAX_TEAM_PLACE_LEVEL = 5;
	public static final int TEAM_ATTACK_MAX_UP_LEVEL = 30;// 防御塔最高升级等级
	public static final int TEAM_WALL_MAX_LEVEL = 10;// 城墙最高升级等级
	public static final int TEAM_OIL_TANK_LEVEL = 30;
	public static final int PRE_WEEK_RES_TOP_SIZE = 15;
	public static final int PERSONAL_ITEM_MAX_LEVEL = 10;
	public static final int TEAM_ITEM_SUPPLY_PRICE = 5;// 补级每1%为5的价格
	public static final int TEAM_ITEM_REPAIR_PRICE = 5;// 修理每1%为5的价格
	public static final int DEFAULT_TEAM_TOWER_NUM = 6;// 默认给6个塔
	public static final int DEFAULT_TEAM_OIL_CAN = 1;// 默认给1个油罐
	public static final int ITEM_MAX_LEVEL = 3;// 物品最高等级
	public static final int OIL_TANK_MAX_LEVEL = 1; //油罐只有一个大的等级
	public static final double OIL_TANK_BASE_VALUE = 1.2;
	public static final double WALL_BASE_VALUE = 2.5;
	

	public enum TEAM_STORAGE_TYPE {
		WALL(1), DEFENSE_TOWER(2), // 防御塔2,城墙台阶1
		PERSONAL_TANK(3), // 个人BUFF，和TANK
		OIL_TANK(5), // 油罐
		PERSONAL_SKILL(6);// 可以带入的技能
		private int value;

		private TEAM_STORAGE_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum TEAM_ITEM_MANAGE_TYPE {
		TEAM_PALCE_UP(1), // 升级战队空间
		// DEPLOY(2),//配置
		REPAIR_TEAM_ITEM(3), // 修理
		SUPPLY(4), // 补给

		TEAM_ITEM_UP(5);// 升级//INTENSIFY_TEAM_ITEM(2),//强化
		// SUPPLY_ALL(6),//全部补给
		// REPAIR_ALL(7),//全部修理
		// DELETE_TEAM_ITEM(8);//删除

		private int value;

		private TEAM_ITEM_MANAGE_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 修理，补给参数 每小时低保精制油*24*Ingame系数K*损耗度/战队防御设施总数系数M
	 * 
	 * @param durable
	 * @return
	 */
	public static final double ONE_HOUR_CONSUME = 10000;
	public static final double INGAME_K = 0.1;
	public static final Map<Integer, Integer> TEAM_PLACE_LEVEL_PARAM = new HashMap<Integer, Integer>() {
		private static final long serialVersionUID = -362605340279211849L;
		{
			put(1, 16);
		}
		{
			put(2, 24);
		}
		{
			put(3, 32);
		}
		{
			put(4, 40);
		}
		{
			put(4, 48);
		}
	};

	public enum MathOperator {
		ADD, SUB, MUTIP, DIV;
		public double cal(double a, double b) {
			double result = 0d;
			switch (this) {
			case ADD:
				result = a + b;
				break;
			case SUB:
				result = a - b;
				break;
			case MUTIP:
				result = a * b;
				break;
			case DIV:
				result = a / b;
				break;
			default:
				break;
			}
			return result;
		}
	}

	public enum TeamItemPropertyType {

		POWER(1, 1.50, 5, 4), // 威力
		FIRE_SPEED(2, 1.20, 2, 1), // 射速
		HP(3, 2.00, 4, 3), 
		FIRE_DISTANCE(4, 1.20, 1, 0), // 射程
		ROTATE_SPEED(5, 1.50, 3, 0), // 转速
		MOVE_SPEED(6, 1.50, 0, 2);// 移动速度
		private int type;
		// 最大能力幅度
		private double baseValue;
		private int teamItemPropertyAdd;
		private int personalItemPropertyAdd;

		public int getType() {
			return type;
		}

		public double getBaseValue() {
			return baseValue;
		}

		public int getTeamItemPropertyAdd() {
			return teamItemPropertyAdd;
		}

		public int getPersonalItemPropertyAdd() {
			return personalItemPropertyAdd;
		}

		TeamItemPropertyType(int type, double baseValue,
				int teamItemPropertyAdd, int personalItemPropertyAdd) {
			this.baseValue = baseValue;
			this.type = type;
			this.teamItemPropertyAdd = teamItemPropertyAdd;
			this.personalItemPropertyAdd = personalItemPropertyAdd;
		}

		public static TeamItemPropertyType getTeamItemPropertyType(int type) {
			for (TeamItemPropertyType t : TeamItemPropertyType.values()) {
				if (t.getType() == type) {
					return t;
				}
			}
			return null;
		}
	}

	/**
	 * 获得塔强化价格 各级收费(FC)=round(基础单级费用*能力类别倍率*塔种类倍率*增幅^本级强化等级,0）
	 * 
	 * @param level
	 * @param type
	 * @return
	 */
	public static int getTeamItemIntensifyPriceForAttack(int currency,
			int level, int type, int wId) {
		// cr的价格
		int price = (int) Math.round(36
				* TeamItemPropertyType.getTeamItemPropertyType(type)
						.getTeamItemPropertyAdd() * TOWER_MULTIPLE.get(wId)
				* Math.pow(1.1005, level));
		return price;
	}

	/**
	 * 获得城墙强化价格 各级收费(FC)=round(基础单级费用*增幅^本级强化等级,0）
	 * 
	 * @param currency
	 * @param level
	 * @param type
	 * @return
	 */
	public static int getTeamItemIntensifyPriceForWall(int currency, int level,
			int type) {
		int price = (int) Math.round(168 * Math.pow(1.29555, level));
		return price;
	}

	/**
	 * 获得资源收集器（油罐）强化价格 各级收费(FC)=round(基础单级费用*增幅^本级强化等级,0）
	 * 
	 * @param currency
	 * @param level
	 * @param type
	 * @return
	 */
	public static int getTeamItemIntensifyPriceForOilTank(int currency,
			int level, int type) {
		int price = (int) Math.round(188 * Math.pow(1.29555, level));
		return price;
	}

	/**
	 * 强化个人坦克价格（只能用精石）
	 * 
	 * @param currency
	 * @param level
	 * @param type
	 * @return
	 */
	public static int getPersonalIntensifyPriceForTank(int currency, int level,
			int type) {
		return (int) Math
				.round(25
						* TeamItemPropertyType.getTeamItemPropertyType(type)
								.getPersonalItemPropertyAdd()
						* Math.pow(1.2255, level)) * 8;
	}

	// wId塔种类倍率
	public static Map<Integer, Double> TOWER_MULTIPLE = new HashMap<Integer, Double>() {
		private static final long serialVersionUID = 1L;
		{
			put(Constants.WEAPON_TEAM_ELEMENTARY_TOWER, 1.0);
		}
		{
			put(Constants.WEAPON_TEAM_MIDDLE_TOWER, 1.5);
		}
		{
			put(Constants.WEAPON_TEAM_ADVANCED_TOWER, 2.25);
		}
	};

	/**
	 * 将钱统一转成exp
	 * 
	 * @param currency  货币种类
	 * @param value	货币数量
	 * @return
	 */
	public static int CRAndRESChangeToExp(int currency, int value) {
		if (currency == Constants.CR_PAY) {
			return value;
		} else if (currency == Constants.RES_PAY_TEAM) {
			return value * 4;
		} else if (currency == Constants.RES_PAY) {// 个人
			return value;
		}
		return 0;
	}

	/**
	 * 将exp转成钱
	 * 
	 * @param currency
	 * @param value
	 * @return
	 */
	public static int expChangeToCROrRES(int currency, double curExp) {
		if (currency == Constants.CR_PAY) {
			return (int) Math.round(curExp);
		} else if (currency == Constants.RES_PAY_TEAM) {
			return (int) Math.round(curExp / 4);
		} else if (currency == Constants.RES_PAY) {
			return (int) Math.round(curExp);
		}
		return 0;
	}

	public static class ConditionForPlaceUp {
		private static ConditionForPlaceUp level2;
		private static ConditionForPlaceUp level3;
		private static ConditionForPlaceUp level4;
		private static ConditionForPlaceUp level5;

		public boolean aaequals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ConditionForPlaceUp other = (ConditionForPlaceUp) obj;
			if (defenseIntensifyLevel != other.defenseIntensifyLevel)
				return false;
			if (guardIntensifyLevel != other.guardIntensifyLevel)
				return false;
			if (level != other.level)
				return false;
			if (money != other.money)
				return false;
			if (snipeIntensifyLevel != other.snipeIntensifyLevel)
				return false;
			if (time != other.time)
				return false;
			if (towerNum != other.towerNum)
				return false;
			if (wallNum != other.wallNum)
				return false;
			return true;
		}

		static {
			
			level2 = new ConditionForPlaceUp(2, 8, 1, 45, 0, 0,
					Math.round(1.2d * 24 * 60 * 60), 60000,12000);
			level3 = new ConditionForPlaceUp(3, 10, 2, 60, 20, 0,
					3 * 24 * 60 * 60l, 180000,36000);
			level4 = new ConditionForPlaceUp(4, 13, 2, 75, 40, 0,
					Math.round(7.5d * 24 * 60 * 60), 525000,105000);
			level5 = new ConditionForPlaceUp(5, 16, 3, 90, 60, 30,
					Math.round(18.75d * 24 * 60 * 60), 1500000,300000);
		}
		private int level;
		private int towerNum;// 塔总数
		private int wallNum;// 墙总数
		private int guardIntensifyLevel;// 机枪塔强化等级
		private int snipeIntensifyLevel;// 狙击塔强化等级
		private int defenseIntensifyLevel;// 防御塔强化等级
		private long time;// 升级所要的时间（秒）
		private int money;
		private int fbtsMoney;//完成空间建造的FULL FC的价格

		public ConditionForPlaceUp(int level, int towerNum, int wallNum,
				int guardIntensifyLevel, int snipeIntensifyLevel,
				int defenseIntensifyLevel, long time, int money,int fbtsMoney) {
			this.level = level;
			this.towerNum = towerNum;
			this.wallNum = wallNum;
			this.guardIntensifyLevel = guardIntensifyLevel;
			this.snipeIntensifyLevel = snipeIntensifyLevel;
			this.defenseIntensifyLevel = defenseIntensifyLevel;
			this.time = time;
			this.money = money;
			this.fbtsMoney=fbtsMoney;
		}

		public ConditionForPlaceUp(int level, int towerNum, int wallNum,
				TeamItem guard, TeamItem snipe, TeamItem defense)
				throws NumberFormatException, Exception {
			this.level = level + 1;
			ConditionForPlaceUp c = getConditionForPlaceUp(level);
			this.towerNum = towerNum >= c.getTowerNum() ? c.getTowerNum() : 0;
			this.wallNum = wallNum >= c.getWallNum() ? c.getWallNum() : 0;
			this.guardIntensifyLevel = checkIntensifyLevel(guard, c
					.getGuardIntensifyLevel());
			this.snipeIntensifyLevel = checkIntensifyLevel(snipe, c
					.getSnipeIntensifyLevel());
			this.defenseIntensifyLevel = checkIntensifyLevel(defense, c
					.getDefenseIntensifyLevel());
			this.time = c.getTime();
			this.money = c.getMoney();
		}

		public static int checkIntensifyLevel(TeamItem ti, int level)
				throws NumberFormatException, Exception {
			
			return 0;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getTowerNum() {
			return towerNum;
		}

		public void setTowerNum(int towerNum) {
			this.towerNum = towerNum;
		}

		public int getWallNum() {
			return wallNum;
		}

		public void setWallNum(int wallNum) {
			this.wallNum = wallNum;
		}

		public int getGuardIntensifyLevel() {
			return guardIntensifyLevel;
		}

		public void setGuardIntensifyLevel(int guardIntensifyLevel) {
			this.guardIntensifyLevel = guardIntensifyLevel;
		}

		public int getSnipeIntensifyLevel() {
			return snipeIntensifyLevel;
		}

		public void setSnipeIntensifyLevel(int snipeIntensifyLevel) {
			this.snipeIntensifyLevel = snipeIntensifyLevel;
		}

		public int getDefenseIntensifyLevel() {
			return defenseIntensifyLevel;
		}

		public void setDefenseIntensifyLevel(int defenseIntensifyLevel) {
			this.defenseIntensifyLevel = defenseIntensifyLevel;
		}

		public long getTime() {
			return time;
		}

		public void setTime(long time) {
			this.time = time;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}

		public static ConditionForPlaceUp getConditionForPlaceUp(int curLevel) {
			switch (curLevel) {
			case 1:
				return level2;
			case 2:
				return level3;
			case 3:
				return level4;
			case 4:
				return level5;
			}
			return null;
		}

		public int getFbtsMoney() {
			return fbtsMoney;
		}

		public void setFbtsMoney(int fbtsMoney) {
			this.fbtsMoney = fbtsMoney;
		}
		
		

	}

	/**
	 * 获取资源争夺战中 坦克，塔，墙，油桶 各种属性
	 * <ul>
	 * <ul>
	 * <b>坦克</b>
	 * <li>威力</li>
	 * <li>血量</li>
	 * <li>移动速度</li>
	 * <li>转速</li>
	 * </ul>
	 * 
	 * <ul>
	 * <b>塔</b>
	 * <li>威力</li>
	 * <li>血量</li>
	 * <li>攻击速度</li>
	 * <li>转速</li>
	 * <li>射程</li>
	 * </ul>
	 * 
	 * <ul>
	 * <b>墙，油桶</b>
	 * <li>血量</li>
	 * </ul>
	 * 
	 * </ul>
	 * 
	 * @param sGunProperty
	 * @param teamItemPropertyType
	 * @param mathOperator
	 * @param level
	 * @param sysItem
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public static Map<String, Number> getPropertyBasic(String sGunProperty,
			TeamItemPropertyType teamItemPropertyType,
			MathOperator mathOperator, int level, SysItem sysItem,float baseValue)
			throws NumberFormatException, Exception {
		Map<String, Number> map = new HashMap<String, Number>();
		return map;
	}
}
