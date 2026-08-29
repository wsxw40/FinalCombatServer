package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class BiochemicalCharacter implements Serializable{
	private static final long serialVersionUID = -1151010122922501743L;
	private SysCharacter sysCharacter;
	private List<SysItem> weaponList;
	private List<SysItem> costumeList;
	private String headResource;
	private String description="生化体";
	private int isOpen=0;
	private int fightNum=0;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BiochemicalCharacter() {
	}

	public BiochemicalCharacter(SysCharacter sysCharacter, List<SysItem> weaponList, List<SysItem> costumeList,String headResource) {
		this.sysCharacter = sysCharacter;
		this.weaponList = weaponList;
		this.costumeList = costumeList;
		this.headResource = headResource;
	}

	public String getHeadResource() {
		return headResource;
	}

	public void setHeadResource(String headResource) {
		this.headResource = headResource;
	}

	public void writeByte(OutputStream out,int gameType,int playerId) throws Exception{
		out.write(BinaryUtil.toByta(playerId));	
		out.write(BinaryUtil.toByta(sysCharacter.getId()));				//Character ID
		out.write(BinaryUtil.toByta(this.getName()));				//Player Name
		out.write(BinaryUtil.toByta(sysCharacter.getResourceName()));
		out.write(BinaryUtil.toByta(headResource));
		
		out.write(BinaryUtil.toByta(sysCharacter.getMaxHp()));
		out.write(BinaryUtil.toByta(sysCharacter.getExHp()));
		out.write(BinaryUtil.toByta(sysCharacter.getIsFired()));
		
		out.write(BinaryUtil.toByta(sysCharacter.getRunSpeed()));			//Run Speed
		out.write(BinaryUtil.toByta(sysCharacter.getWalkSpeed()));		//Walk Speed
		out.write(BinaryUtil.toByta(sysCharacter.getCrouchSpeed()));		//Crouch Speed
		out.write(BinaryUtil.toByta(sysCharacter.getAccelSpeed()));		//accel Speed
		out.write(BinaryUtil.toByta(sysCharacter.getJumpSpeed()));		//Jump Velocity
		out.write(BinaryUtil.toByta(sysCharacter.getThrowSpeed()));		//Throw Velocity
		
		out.write(BinaryUtil.toByta(sysCharacter.getControllerHeight()));	
		out.write(BinaryUtil.toByta(sysCharacter.getControllerRadius()));	
		out.write(BinaryUtil.toByta(sysCharacter.getControllerCrouchHeight()));	
		out.write(BinaryUtil.toByta((byte)sysCharacter.getIsEnable()));	
		out.write(BinaryUtil.toByta((byte)sysCharacter.getScoreOffset()));	
		//角色战斗力 int
		out.write(BinaryUtil.toByta((int)999999));
		out.write(BinaryUtil.toByta(costumeList.size()));//pack size
//		System.err.println(costumeList.size());
		for(SysItem si:costumeList){
			si.writeCostume(out, sysCharacter);
		}
		
		out.write(BinaryUtil.toByta(1));//pack size
		out.write(1);//pack id
		out.write(BinaryUtil.toByta(weaponList.size()));//pack size
		for(SysItem si:weaponList){
			si.writeWeapon(out);
		}
	}	
	
	public void putOnCostume(){
		String resource = sysCharacter.getCostumeResource();
		String[] costumes = resource.split(Constants.DELIMITER_RESOURCE_STABLE);
		sysCharacter.setCostume(costumes);
		
		String[] resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
		System.arraycopy(costumes, 0, resOutput, 0, costumes.length);
		for(SysItem cos : costumeList){
			if(cos!= null){
				if(cos.getType()==Constants.DEFAULT_COSTUME_TYPE){
					this.setHeadResource(cos.getName());
				}
				if(cos.getType()==Constants.DEFAULT_COSTUME_TYPE&&cos.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
					resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
					resOutput = cos.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
					resOutput[2] = costumes[2];
				}else if(cos.getCId()>0&&cos.getCId()<=8){
					resOutput[cos.getCId()-1] = cos.getResourceStable();
				}
			}
		}
		sysCharacter.setCostume(resOutput);
	}

	public SysCharacter getSysCharacter() {
		return sysCharacter;
	}

	public void setSysCharacter(SysCharacter sysCharacter) {
		this.sysCharacter = sysCharacter;
	}

	
	public List<SysItem> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<SysItem> weaponList) {
		this.weaponList = weaponList;
	}

	public List<SysItem> getCostumeList() {
		return costumeList;
	}

	public void setCostumeList(List<SysItem> costumeList) {
		this.costumeList = costumeList;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFightNum() throws Exception {
		if(this.fightNum==0){
			initFightNum();
		}
		return this.fightNum;
		
	}
	
	public int initFightNum() throws Exception{
		double fightNum=0;
		int specialSiFightNum = 0;
		for(SysItem sysItem : weaponList){
			//普通武器
			if(sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE){
				if(sysItem.getSubType() == Constants.NUM_ONE){//主武器
					fightNum += sysItem.getFightNum()*Constants.FIGHT_PARAM1[0];
				} else if(sysItem.getSubType() == Constants.NUM_TWO){//副武器
					fightNum += sysItem.getFightNum()*Constants.FIGHT_PARAM1[1];
				} else if(sysItem.getSubType() == Constants.NUM_THREE){//近身器
					fightNum += sysItem.getFightNum()*Constants.FIGHT_PARAM1[2];
				}else if(sysItem.getSubType() == Constants.NUM_FOUR){
					specialSiFightNum = sysItem.getFightNum();
				}
			}
		}
		//特殊武器
		if(specialSiFightNum!=0){
			fightNum = fightNum*(1+specialSiFightNum*Constants.FIGHT_PARAM1[3]);
		}
		//服装，配饰
		for(SysItem sysItem : costumeList){
			if(sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE){//服装
				fightNum = fightNum*(1+sysItem.getFightNum()*Constants.FIGHT_PARAM1[4]);
			} else if(sysItem.getType() == Constants.DEFAULT_PART_TYPE){
				if(sysItem.getSubType() == Constants.NUM_ONE){//帽子
					fightNum = fightNum*(1+sysItem.getFightNum()*Constants.FIGHT_PARAM1[5]);
				} else if(sysItem.getSubType() == Constants.NUM_TWO){//配饰
					fightNum = fightNum*(1+sysItem.getFightNum()*Constants.FIGHT_PARAM1[6]);
				}
			}
		}
		this.setFightNum((int)fightNum);
		return (int)fightNum;
	}

	public void setFightNum(int fightNum) {
		
		this.fightNum = fightNum;
	}
	
	
}
