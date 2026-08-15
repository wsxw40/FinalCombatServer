package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class BossPojo implements Serializable{
	private static final long serialVersionUID = -6018126882675914121L;
	private int playerId;
	private SysCharacter sysCharacter;
	private List<SysItem> weaponList;
	private List<SysItem> costumeList;
	public void writeByte(OutputStream out) throws Exception{
		out.write(BinaryUtil.toByta(playerId));	
		out.write(BinaryUtil.toByta(sysCharacter.getId()));				//Character ID
		out.write(BinaryUtil.toByta(sysCharacter.getName()));
		out.write(BinaryUtil.toByta(sysCharacter.getResourceName()));
		
		if(sysCharacter.getId()>=40&&sysCharacter.getId()<=45){//BIOCHEMICAL2 生化2
			out.write(BinaryUtil.toByta(sysCharacter.getResourceName()));
		}else if(sysCharacter.getId()>121){
			out.write(BinaryUtil.toByta(sysCharacter.getResourceName()));
		}else{
			out.write(BinaryUtil.toByta("boss"));
		}
		
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
		for(SysItem pi:costumeList){
			if(pi!=null){
//				SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getId());
//				si.init();
				pi.writeCostume(out,sysCharacter);
			}else{
				out.write(BinaryUtil.toByta(0));
			}
		}
		
		out.write(BinaryUtil.toByta(1));//pack size
		out.write(1);//pack id
		out.write(BinaryUtil.toByta(weaponList.size()));//pack size
		for(SysItem pi:weaponList){
			if(pi!=null){
//				SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getId());
//						si.init();
				pi.writeWeapon(out);
			}else{
				out.write(BinaryUtil.toByta(0));
			}
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

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
}
