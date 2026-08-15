package com.pearl.fcw.info.lobby.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.lobby.utils.Constants;

@Entity
@Schema(GoSchema.SYS)
public class SysItem extends Weapon {
	private static final long serialVersionUID = -3349969573843307372L;

	@Id
	private int id;
	private String displayName;
	private int rareLevel;
	private int fightNum;
	private int isStrengthen;
	@Transient private int figthNumOutput = 0;
	private int type;
	private int subType;
	private int strengthLevel;
	private String characterId;

	// Not Stored in Database
	@Transient private List<Integer> gunPropertyList1 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList2 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList3 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList4 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList5 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList6 = new ArrayList<Integer>();
	@Transient private List<Integer> gunPropertyList7 = new ArrayList<Integer>();
	@Transient private List<Integer> characterList = new ArrayList<Integer>();
	@Transient private List<Payment> gpPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> crPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> voucherPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> medalPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> resPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> resDisPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> resTeamPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> resDisTeamPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> reviveCoinList = new ArrayList<Payment>();

	// 包括隐藏的payment
	@Transient private List<Payment> allGpPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allCrPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allVoucherPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allResPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allResDisPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allResTeamPricesList = new ArrayList<Payment>();
	@Transient private List<Payment> allResDisTeamPricesList = new ArrayList<Payment>();

	private String iValue;

	private int iId;

	private int iBuffId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getI18nName() {
		return "<SN" + this.id + "^0>";
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getRareLevel() {
		return rareLevel;
	}

	public void setRareLevel(int rareLevel) {
		this.rareLevel = rareLevel;
	}

	public int getFightNum() {
		return fightNum;
	}

	public int getDefaultPropertySize() {
		return (this.gunPropertyList2.size() / 4 + this.gunPropertyList3.size()
				/ 4 + this.gunPropertyList4.size() / 4
				+ this.gunPropertyList5.size() / 4
				+ this.gunPropertyList6.size() / 4 + this.gunPropertyList7
				.size() / 4);
	}

	private void initFightNum() {
		if (this.fightNum >= 0) {
			figthNumOutput = this.fightNum;
			return;
		}
		int lowPropertyNum = 0;
		int middlePropertyNum = 0;
		int highPropertyNum = 0;
		if (this.getIsStrengthen() == Constants.NUM_ZERO) {
			highPropertyNum = getDefaultPropertySize();
		}
		if (this.type == Constants.DEFAULT_WEAPON_TYPE) {
			if (this.subType == Constants.NUM_ONE) {// 主武器
				double multiplier3 = (1 + lowPropertyNum
						/ Constants.FIGHT_PARAM[0][4] + middlePropertyNum
						/ Constants.FIGHT_PARAM[0][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[0][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[0][0]
						* Math.pow(
								Constants.FIGHT_PARAM[0][1],
								this.strengthLevel
										+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[0][2])
										- Constants.FIGHT_PARAM[0][3]) * multiplier3);
			} else if (this.subType == Constants.NUM_TWO) {// 副武器
				double multiplier3 = (1 + lowPropertyNum
						/ Constants.FIGHT_PARAM[5][4] + middlePropertyNum
						/ Constants.FIGHT_PARAM[5][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[5][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[5][0]
						* Math.pow(
								Constants.FIGHT_PARAM[5][1],
								this.strengthLevel
										+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[5][2])
										- Constants.FIGHT_PARAM[5][3]) * multiplier3);
			} else if (this.subType == Constants.NUM_THREE) {// 近身器
				double multiplier3 = (1 + lowPropertyNum
						/ Constants.FIGHT_PARAM[1][4] + middlePropertyNum
						/ Constants.FIGHT_PARAM[1][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[1][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[1][0]
						* Math.pow(
								Constants.FIGHT_PARAM[1][1],
								this.strengthLevel
										+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[1][2])
										- Constants.FIGHT_PARAM[1][3]) * multiplier3);
			}
		} else if (this.type == Constants.DEFAULT_COSTUME_TYPE) {// 服装
			double multiplier3 = (1 + lowPropertyNum
					/ Constants.FIGHT_PARAM[2][4] + middlePropertyNum
					/ Constants.FIGHT_PARAM[2][5] + highPropertyNum
					/ Constants.FIGHT_PARAM[2][6]);
			figthNumOutput = (int) (Constants.FIGHT_PARAM[2][0] * ((Math
					.pow(Constants.FIGHT_PARAM[2][1],
							this.strengthLevel
									+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[2][2])
									- Constants.FIGHT_PARAM[2][3]) * multiplier3) - 1));
		} else if (this.type == Constants.DEFAULT_PART_TYPE) {
			if (this.subType == Constants.NUM_ONE) {// 帽子
				double multiplier3 = (1 + lowPropertyNum
						/ Constants.FIGHT_PARAM[3][4] + middlePropertyNum
						/ Constants.FIGHT_PARAM[3][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[3][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[3][0] * ((Math
						.pow(Constants.FIGHT_PARAM[3][1],
								this.strengthLevel
										+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[3][2])
										- Constants.FIGHT_PARAM[3][3]) * multiplier3) - 1));
			} else if (this.subType == Constants.NUM_TWO) {// 配饰
				double multiplier3 = (1 + lowPropertyNum
						/ Constants.FIGHT_PARAM[4][4] + middlePropertyNum
						/ Constants.FIGHT_PARAM[4][5] + highPropertyNum
						/ Constants.FIGHT_PARAM[4][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[4][0] * ((Math
						.pow(Constants.FIGHT_PARAM[4][1],
								this.strengthLevel
										+ ((this.rareLevel - 1) / Constants.FIGHT_PARAM[4][2])
										- Constants.FIGHT_PARAM[4][3]) * multiplier3) - 1));
			}
		}
		if (figthNumOutput < 0) {
			figthNumOutput = 0;
		}
	}

	public int getFigthNumOutput() {
		if (figthNumOutput == 0) {
			initFightNum();
		}
		return figthNumOutput;
	}

	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

	public int getIsStrengthen() {
		return isStrengthen;
	}

	public void setIsStrengthen(int isStrengthen) {
		this.isStrengthen = isStrengthen;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getStrengthLevel() {
		return strengthLevel;
	}

	public void setStrengthLevel(int strengthLevel) {
		this.strengthLevel = strengthLevel;
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

	public List<Integer> getGunPropertyList6() {
		return gunPropertyList6;
	}

	public void setGunPropertyList6(List<Integer> gunPropertyList6) {
		this.gunPropertyList6 = gunPropertyList6;
	}

	public List<Integer> getGunPropertyList7() {
		return gunPropertyList7;
	}

	public void setGunPropertyList7(List<Integer> gunPropertyList7) {
		this.gunPropertyList7 = gunPropertyList7;
	}

	public List<Integer> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(List<Integer> characterList) {
		this.characterList = characterList;
	}

	public List<Payment> getGpPricesList() {
		return gpPricesList;
	}

	public void setGpPricesList(List<Payment> gpPricesList) {
		this.gpPricesList = gpPricesList;
	}

	public List<Payment> getCrPricesList() {
		return crPricesList;
	}

	public void setCrPricesList(List<Payment> crPricesList) {
		this.crPricesList = crPricesList;
	}

	public List<Payment> getVoucherPricesList() {
		return voucherPricesList;
	}

	public void setVoucherPricesList(List<Payment> voucherPricesList) {
		this.voucherPricesList = voucherPricesList;
	}

	public List<Payment> getMedalPricesList() {
		return medalPricesList;
	}

	public void setMedalPricesList(List<Payment> medalPricesList) {
		this.medalPricesList = medalPricesList;
	}

	public List<Payment> getResPricesList() {
		return resPricesList;
	}

	public void setResPricesList(List<Payment> resPricesList) {
		this.resPricesList = resPricesList;
	}

	public List<Payment> getResDisPricesList() {
		return resDisPricesList;
	}

	public void setResDisPricesList(List<Payment> resDisPricesList) {
		this.resDisPricesList = resDisPricesList;
	}

	public List<Payment> getResTeamPricesList() {
		return resTeamPricesList;
	}

	public void setResTeamPricesList(List<Payment> resTeamPricesList) {
		this.resTeamPricesList = resTeamPricesList;
	}

	public List<Payment> getResDisTeamPricesList() {
		return resDisTeamPricesList;
	}

	public void setResDisTeamPricesList(List<Payment> resDisTeamPricesList) {
		this.resDisTeamPricesList = resDisTeamPricesList;
	}

	public List<Payment> getReviveCoinList() {
		return reviveCoinList;
	}

	public void setReviveCoinList(List<Payment> reviveCoinList) {
		this.reviveCoinList = reviveCoinList;
	}

	public List<Payment> getAllGpPricesList() {
		return allGpPricesList;
	}

	public void setAllGpPricesList(List<Payment> allGpPricesList) {
		this.allGpPricesList = allGpPricesList;
	}

	public List<Payment> getAllCrPricesList() {
		return allCrPricesList;
	}

	public void setAllCrPricesList(List<Payment> allCrPricesList) {
		this.allCrPricesList = allCrPricesList;
	}

	public List<Payment> getAllVoucherPricesList() {
		return allVoucherPricesList;
	}

	public void setAllVoucherPricesList(List<Payment> allVoucherPricesList) {
		this.allVoucherPricesList = allVoucherPricesList;
	}

	public List<Payment> getAllResPricesList() {
		return allResPricesList;
	}

	public void setAllResPricesList(List<Payment> allResPricesList) {
		this.allResPricesList = allResPricesList;
	}

	public List<Payment> getAllResDisPricesList() {
		return allResDisPricesList;
	}

	public void setAllResDisPricesList(List<Payment> allResDisPricesList) {
		this.allResDisPricesList = allResDisPricesList;
	}

	public List<Payment> getAllResTeamPricesList() {
		return allResTeamPricesList;
	}

	public void setAllResTeamPricesList(List<Payment> allResTeamPricesList) {
		this.allResTeamPricesList = allResTeamPricesList;
	}

	public List<Payment> getAllResDisTeamPricesList() {
		return allResDisTeamPricesList;
	}

	public void setAllResDisTeamPricesList(List<Payment> allResDisTeamPricesList) {
		this.allResDisTeamPricesList = allResDisTeamPricesList;
	}

	public void setFigthNumOutput(int figthNumOutput) {
		this.figthNumOutput = figthNumOutput;
	}

	public String[] getCharacterIds() {
		String[] array = characterId.split(Constants.DELIMITER_RESOURCE_STABLE);
		return array;
	}

	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}

	public String getIValue() {
		return iValue;
	}

	public void setIValue(String iValue) {
		this.iValue = iValue;
	}

	public int getIId() {
		return iId;
	}

	public void setIId(int iId) {
		this.iId = iId;
	}

	public int getIBuffId() {
		return iBuffId;
	}

	public void setIBuffId(int iBuffId) {
		this.iBuffId = iBuffId;
	}

}
