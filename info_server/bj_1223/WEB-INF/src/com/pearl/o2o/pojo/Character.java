package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.Constants.GAMETYPE;

public class Character extends BaseMappingBean<Character> {
	private static final long serialVersionUID = -1151010122922501743L;
	private int playerId;
	private SysCharacter sysCharacter;
	private List<PlayerItem> weaponList;
	private List<PlayerItem> costumeList;
	private String headResource;
	private String description="完成“赢得500场游戏”成就可解锁职业";
	private int isOpen=0;
	private int fightNum=0;
	
	//just for bioCharacter: to show the character state in characterList
	//0:locked 1:unlock 2:using
	private int stateInCharList;
	//biobuff left time
	private int leftSeconds; 
	
	public Character() {
	}

	public Character(int playerId, SysCharacter sysCharacter, List<PlayerItem> weaponList, List<PlayerItem> costumeList,String headResource) {
		this.playerId = playerId;
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

	public void writeByte(OutputStream out,int gameType) throws Exception{
		out.write(BinaryUtil.toByta(playerId));	
		out.write(BinaryUtil.toByta(sysCharacter.getId()));				//Character ID
		out.write(BinaryUtil.toByta(sysCharacter.getName()));				//Player Name
		out.write(BinaryUtil.toByta(sysCharacter.getResourceName()));
		out.write(BinaryUtil.toByta(headResource)); //show the head icon in game left
		
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
		out.write(BinaryUtil.toByta(getFightNum()));
		out.write(BinaryUtil.toByta(3));//pack size
		
		for(PlayerItem pi:costumeList){
			if(pi!=null){
				SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//				si.init();
				pi.writeCostume(out,si,sysCharacter);
			}else{
				out.write(BinaryUtil.toByta(0));
			}
		}
		
		out.write(BinaryUtil.toByta(1));//pack size
		out.write(1);//pack id
		if(gameType==GAMETYPE.KNIFE.getValue()){
			out.write(BinaryUtil.toByta(1));//pack size
			for(PlayerItem pi:weaponList){
				if(pi!=null){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(pi!=null&&si!=null&&si.getType()==Constants.DEFAULT_WEAPON_TYPE&&si.getSubType()==3){
						pi.writeWeapon(out, si);
					}
				}
			}
		}else if(gameType==GAMETYPE.STREETBOY.getValue()){
			out.write(BinaryUtil.toByta(4));//pack size
			for(PlayerItem pi:weaponList){
				if(pi!=null){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(pi!=null&&si!=null&&si.getType()==Constants.DEFAULT_WEAPON_TYPE&&(si.getSubType()==3||si.getSubType()==1)){
						pi.writeWeapon(out, si);
					}else{
						out.write(BinaryUtil.toByta(0));
					}
				}
				else{
					out.write(BinaryUtil.toByta(0));
				}
			}
		}else{
			if(weaponList.size()>4){
				out.write(BinaryUtil.toByta(4));//pack size
				List<PlayerItem> list=weaponList.subList(0, 3);
				for(PlayerItem pi:list){
					if(pi!=null){
						SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//						si.init();
						pi.writeWeapon(out, si);
					}else{
						out.write(BinaryUtil.toByta(0));
					}
				}
			}else{
				out.write(BinaryUtil.toByta(weaponList.size()));//pack size
				for(PlayerItem pi:weaponList){
					if(pi!=null){
						SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//						si.init();
						pi.writeWeapon(out, si);
					}else{
						out.write(BinaryUtil.toByta(0));
					}
				}
			}
		}
	}	
	
	public void putOnCostume(){
		String resource = sysCharacter.getCostumeResource();
		String[] costumes = resource.split(Constants.DELIMITER_RESOURCE_STABLE);
		sysCharacter.setCostume(costumes);
		
		String[] resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
		System.arraycopy(costumes, 0, resOutput, 0, costumes.length);
		for(PlayerItem cos : costumeList){
			try{
				SysItem sysItem =ServiceLocator.getService.getSysItemByItemId(cos.getItemId());
				if(cos!= null){
					if(sysItem.getType()==Constants.DEFAULT_COSTUME_TYPE){
						this.setHeadResource(sysItem.getName());
					}
					if(sysItem.getType()==Constants.DEFAULT_COSTUME_TYPE&&sysItem.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
						resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
						resOutput = sysItem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
						resOutput[2] = costumes[2];
					}else  if(sysItem.getCId()>0&&sysItem.getCId()<=8){
						resOutput[sysItem.getCId()-1] = sysItem.getResourceStable();
					}
				}
			}catch (Exception e) {
				ServiceLocator.exceptionLog.warn("error happen in putOnCostume id="+cos.getId()+" playerId="+cos.getPlayerId(),e);
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

	
	public List<PlayerItem> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<PlayerItem> weaponList) {
		this.weaponList = weaponList;
	}

	public List<PlayerItem> getCostumeList() {
		return costumeList;
	}

	public void setCostumeList(List<PlayerItem> costumeList) {
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
		for(PlayerItem w : weaponList){
			if(w==null)
				continue;
			SysItem sysItem = w.getSysItem();
			if(sysItem==null){
				sysItem = ServiceLocator.getService.getSysItemByItemId(w.getItemId());
				w.setSysItem(sysItem);
				}
			//普通武器
			if(sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE){
				if(sysItem.getSubType() == Constants.NUM_ONE){//主武器
					fightNum += w.getFightNum()*Constants.FIGHT_PARAM1[0];
				} else if(sysItem.getSubType() == Constants.NUM_TWO){//副武器
					fightNum += w.getFightNum()*Constants.FIGHT_PARAM1[1];
				} else if(sysItem.getSubType() == Constants.NUM_THREE){//近身器
					fightNum += w.getFightNum()*Constants.FIGHT_PARAM1[2];
				}else if(sysItem.getSubType() == Constants.NUM_FOUR){
					specialSiFightNum = w.getFightNum();
				}
			}
		}
		//特殊武器
		if(specialSiFightNum!=0){
			fightNum = fightNum*(1+specialSiFightNum*Constants.FIGHT_PARAM1[3]);
		}
		//服装，配饰
		for(PlayerItem c : costumeList){
			if(c==null)
				continue;
			SysItem sysItem = c.getSysItem();
			if(sysItem==null){
				sysItem = ServiceLocator.getService.getSysItemByItemId(c.getItemId());
				c.setSysItem(sysItem);
			}
			if(sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE){//服装
				fightNum = fightNum*(1+c.getFightNum()*Constants.FIGHT_PARAM1[4]);
			} else if(sysItem.getType() == Constants.DEFAULT_PART_TYPE){
				if(sysItem.getSubType() == Constants.NUM_ONE){//帽子
					fightNum = fightNum*(1+c.getFightNum()*Constants.FIGHT_PARAM1[5]);
				} else if(sysItem.getSubType() == Constants.NUM_TWO){//配饰
					fightNum = fightNum*(1+c.getFightNum()*Constants.FIGHT_PARAM1[6]);
				}
			}
		}
		this.setFightNum((int)fightNum);
		return (int)fightNum;
	}

	public void setFightNum(int fightNum) {
		
		this.fightNum = fightNum;
	}
	public int getStateInCharList() {
		return stateInCharList;
	}
	public void setStateInCharList(int stateInCharList) {
		this.stateInCharList = stateInCharList;
	}
	public int getLeftSeconds() {
		return leftSeconds;
	}
	public void setLeftSeconds(int leftSeconds) {
		this.leftSeconds = leftSeconds;
	}
	
}
