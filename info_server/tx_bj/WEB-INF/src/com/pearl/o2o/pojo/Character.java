package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Constants.GAMETYPE;
import com.pearl.o2o.utils.ServiceLocator;

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

	private transient boolean hasSuitNow=false; 
	private int suitNum=0; //1表示4 件套 ; 3表示 6件套 
	private int suitId=0;
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
		
		
		hasSuitNow=checkHasSuitNow();
		double totalBloodAdd = 0;
		for(int i=0;i<costumeList.size();i++){
			PlayerItem pi=costumeList.get(i);
			if(pi!=null){
				SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//				si.init();
				if(i==0){
					if(hasSuitNow){
						if(suitNum==3){
							pi.writeCostume(out,si,sysCharacter,new int[]{suitId,4});
						}else if(suitNum>0&& suitNum<3){
							pi.writeCostume(out,si,sysCharacter,new int[]{suitId,2});
						}
					}else{
						pi.writeCostume(out,si,sysCharacter);
					}
				}else{
					pi.writeCostume(out,si,sysCharacter);
				}
				totalBloodAdd+=pi.calculateGstLevelDefenceNum(pi.getSysItem());
			}else{
				out.write(BinaryUtil.toByta(0));
			}
		}
		totalBloodAdd=totalBloodAdd>1.0?(1 + (totalBloodAdd/600.0)):1.0;
		out.write(BinaryUtil.toByta((float)totalBloodAdd));  //升星提升后的总血量
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
			for(int i=0;i<weaponList.size();i++){
				PlayerItem pi=weaponList.get(i);
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
		}else if(gameType==GAMETYPE.AGRAVITY.getValue()){
			out.write(BinaryUtil.toByta(4));//pack size
			for(int i=0;i<weaponList.size();i++){
				PlayerItem pi=weaponList.get(i);
				if(pi!=null){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(pi!=null&&si!=null&&si.getType()==Constants.DEFAULT_WEAPON_TYPE&&(si.getSubType()==Constants.NUM_TWO||si.getSubType()==Constants.NUM_THREE)){
						pi.writeWeapon(out, si);
					}else{ 
						out.write(BinaryUtil.toByta(0));
					}
				}
				else{
					out.write(BinaryUtil.toByta(0));
				}
			}
		}else if(gameType==GAMETYPE.RISK_ISLET.getValue()){
			out.write(BinaryUtil.toByta(1));//pack size
			for(PlayerItem pi:weaponList){
				if(pi!=null){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(pi!=null&&si!=null&&si.getType()==Constants.DEFAULT_WEAPON_TYPE&&si.getSubType()==Constants.NUM_ONE){
						pi.writeWeapon(out, si);
					}
				}
			}
		}else{
			List<PlayerItem> list=weaponList;
			if(weaponList.size()>4){
				out.write(BinaryUtil.toByta(4));//pack size
				list=weaponList.subList(0, 3);
			}else{
				out.write(BinaryUtil.toByta(weaponList.size()));//pack size
			}
			for(int i=0;i<list.size();i++){
				PlayerItem pi=list.get(i);
				if(pi!=null){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(i==0){
						if(hasSuitNow){
							if(suitNum==3){
								pi.writeWeapon(out,si,new int[]{suitId,3});
							}else if(suitNum>0&& suitNum<3){
								pi.writeWeapon(out,si,new int[]{suitId,1});
							}
						}else{
							pi.writeWeapon(out,si);
						}
					}else{
						pi.writeWeapon(out,si);
					}
				}else{
					out.write(BinaryUtil.toByta(0));
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

	public boolean isHasSuitNow() {
		return hasSuitNow;
	}

	public void setHasSuitNow(boolean hasSuitNow) {
		this.hasSuitNow = hasSuitNow;
	}
	
	final public int getSuitNum() {
		return suitNum;
	}

	public final int getSuitId() {
		return suitId;
	}

	public boolean checkHasSuitNow(){
		int cflag=-1;
		HashSet<Integer> hashSet=new HashSet<Integer>();
		//仅仅为了显示套装属性
		for(PlayerItem pi: costumeList){
			SysItem si=pi.getSysItem();
			if(si.getNeedTeamPlaceLevel()>99){
				si.setBelongSuit(ConfigurationUtil.SUITMAP.get(si.getNeedTeamPlaceLevel()));
			}
		}
		for(PlayerItem pi: weaponList){
			SysItem wsi=pi.getSysItem();
			if(wsi.getNeedTeamPlaceLevel()>99){
				wsi.setBelongSuit(ConfigurationUtil.SUITMAP.get(wsi.getNeedTeamPlaceLevel()));
			}
		}
		
		if((weaponList.size()>0) && costumeList.size()==3){
			cflag=costumeList.get(0).getSysItem().getNeedTeamPlaceLevel();
			// 信任之前的操作。没有检测cid 以确保是衣服、翅膀、帽子三类
			if((cflag == costumeList.get(1).getSysItem().getNeedTeamPlaceLevel()) 
					&& ( costumeList.get(1).getSysItem().getNeedTeamPlaceLevel()== costumeList.get(2).getSysItem().getNeedTeamPlaceLevel())
					&& cflag>99){
				
				for(int i=0;i<weaponList.size();i++){
					if(weaponList.get(i).getSysItem().getNeedTeamPlaceLevel()==cflag){
						hashSet.add(weaponList.get(i).getSysItem().getSubType());
					}
				}
				suitId=cflag;
				return checkWeaponForSuit(hashSet);
				
			}else{
				return false;
			}
		}
		return false;
	}
	
	private boolean checkWeaponForSuit(HashSet<Integer> weaponListStauts){
		
		if(weaponListStauts.size()>=1){
			if(weaponListStauts.contains(1)){
				suitNum=weaponListStauts.size();
				return true;
			}
			return false;
			
		}else{
			return false;
		}
	}
}
