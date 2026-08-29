package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class Player implements Serializable {
	private static final long serialVersionUID = -3491449820340834800L;
	
	public void writeByte(OutputStream out, Integer side) throws Exception{
		out.write(BinaryUtil.toByta(id));				//Player ID
		out.write(BinaryUtil.toByta(Constants.GENDER_MALE.equals(gender)?(byte)0:(byte)1));
		out.write(BinaryUtil.toByta(name));				//Player Name
		out.write(BinaryUtil.toByta(team==null?"":team));				//Team Name
		out.write(BinaryUtil.toByta(laborUnion==null?"":laborUnion));	//Labor Union

		out.write(BinaryUtil.toByta(runSpeed));			//Run Speed
		out.write(BinaryUtil.toByta(walkSpeed));		//Walk Speed
		out.write(BinaryUtil.toByta(crouchSpeed));		//Crouch Speed
		out.write(BinaryUtil.toByta(accelSpeed));		//accel Speed
		out.write(BinaryUtil.toByta(jumpSpeed));		//Jump Velocity
		out.write(BinaryUtil.toByta(throwSpeed));		//Throw Velocity
		

		//Resources
		String[] costume = (side==0?costumeT:costumeP);
		int size = 0;
		for (String cos : costume){
			if(cos!=null && !"".equals(cos)){
				size ++;
			}
		}
		
		out.write(BinaryUtil.toByta(size));
		for(String cos : costume){
			if(cos!=null && !"".equals(cos)){			
				out.write(BinaryUtil.toByta(cos));
			}
		}		
	}	
	
	public void putOnCostume(int side, List<PlayerItem> costume){
		String resource = (side==0?resourceT:resourceP);
		
		String[] resources = resource.split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
		String[] list= new String[Constants.AVATAR_CHANGE_SIZE];
		System.arraycopy(resources, 0, resOutput, 0, resources.length);
		for(PlayerItem cos : costume){
			if(cos.getResourceChangeable() != null){
				if(cos.getType()==Constants.DEFAULT_COSTUME_TYPE&&cos.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
					resOutput = new String[Constants.AVATAR_CHANGE_SIZE];
					resOutput = cos.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
					resOutput[2] = resources[2];
					list= new String[1];
					list[0] = cos.getDisplayName();
				}else if(cos.getCId()>0&&cos.getCId()<=8){
					resOutput[cos.getCId()-1] = cos.getResourceStable();
					list[cos.getCId()-1]=cos.getDisplayName();
				}
			}
		}

		if(side == 0){
			costumeT = resOutput;
			costume0 = resources;
		}
		else{
			costumeP = resOutput;
			costume1 = resources;
		}
	}
	
	
	private Integer 	id;
	private Integer 	userId;
	private	Integer		sysCharacterId;
	private String		gender;
	private String		name;
	private Integer		teamId 			= 0;
	private Integer		laborUnionId 	= 0;
	private	Integer		exp 			= 0;
	private	Integer		rank 			= 1;
	private Integer		wPackSize 		= 2;
	private Integer		cPackSize		= 1;
	private	Integer		gPoint 			= 0;
	private	Integer		credit 			= 0;
	private String		deleted;
	private Integer 	lastGameSide;
	private Integer 	lastLoginTime;
	private String	 	lastLoginIp;
	private Integer 	flowerTotal=0;
	private Integer 	flowerToday=0;
	private Date 		flowerLastTime;
	
	//General
	private Integer		gScore = 0;
	private Integer		gKill= 0;
	private Integer		gDead= 0;
	private Integer		gHeadShot= 0;
	private Integer		gGrenadeKill= 0;
	private Integer		gKnifeKill= 0;
	private Integer		gHitPoint= 0;
	private Integer		gWin= 0;
	private Integer		gTotal= 0;
	
	//Team
	private Integer		tScore= 0;
	private Integer		tKill= 0;
	private Integer		tDead= 0;
	private Integer		tHeadShot= 0;
	private Integer		tGrenadeKill= 0;
	private Integer		tKnifeKill= 0;
	private Integer		tHitPoint= 0;
	private Integer		tWin= 0;
	private Integer		tTotal= 0;
	
	//Bio
	private Integer		bioScore= 0;
	private Integer		bioInfect= 0;
	private Integer		bioKill= 0;
	
	//Flag
	private Integer		flagScore= 0;
	private Integer		flagSucceed= 0;
	private Integer		flagReturn= 0;
	private Integer		flagWin= 0;
	private Integer		flagTotal= 0;
	
	//Blast
	private Integer		blastScore= 0;
	private Integer		blastSucceed= 0;
	private Integer		blastDisable= 0;
	private Integer		blastWin= 0;
	private Integer		blastTotal= 0;
	
	//Fun
	private Integer		funGpTotal= 0;
	private Integer		funCrTotal= 0;
	private Integer		funGrenadeKill= 0;
	private Integer		funDropKill= 0;
	private Integer		funWeaponMod= 0;
	private Integer		funDead= 0;
	
	private	String		resourceP;
	private String		resourceT;	
	private List<PlayerItem> buffList;
	
	//Join from table SYS_CHARACTER
	private Float		runSpeed;
	private Float		walkSpeed;
	private	Float		crouchSpeed;
	private Float 		accelSpeed;
	private Float		jumpSpeed;
	private Float 		throwSpeed;

	
	//Join from table GROUP
	private String		team;
	
	//Join from table UNION
	private String		laborUnion;
	
	
	//Not Stored in Database
	private String[]	costumeP;
	private String[]	costumeT;
	private String[]	costume0;
	private String[]	costume1;
	//Location
	private int room=0;
	private String channel;
	private String server;
	
	private Integer isChange;
	private Integer		gRank;
	private Integer		gVictoryRate;
	private Integer		tRank;
	private Integer		tVictoryRate;
	private Integer		flagRank;
	private Integer		flagVictoryRate;
	private Integer		bioRank;
	private Integer		blastRank;
	private Integer		blastVictoryRate;
	private Integer		funGpRank;
	private	Integer		funCrRank;
	private Integer		funGrenadeKillRank;
	private Integer		funDropKillRank;
	private Integer		funWeaponModRank;
	private Integer 	funDeadRank;
	
	public Integer getGRank() {
		return gRank;
	}

	public void setGRank(Integer rank) {
		gRank = rank;
	}

	public Integer getGVictoryRate() {
		return gVictoryRate;
	}

	public void setGVictoryRate(Integer victoryRate) {
		gVictoryRate = victoryRate;
	}

	public Integer getTRank() {
		return tRank;
	}

	public void setTRank(Integer rank) {
		tRank = rank;
	}

	public Integer getTVictoryRate() {
		return tVictoryRate;
	}

	public void setTVictoryRate(Integer victoryRate) {
		tVictoryRate = victoryRate;
	}

	public Integer getFlagRank() {
		return flagRank;
	}

	public void setFlagRank(Integer flagRank) {
		this.flagRank = flagRank;
	}

	public Integer getFlagVictoryRate() {
		return flagVictoryRate;
	}

	public void setFlagVictoryRate(Integer flagVictoryRate) {
		this.flagVictoryRate = flagVictoryRate;
	}

	public Integer getBioRank() {
		return bioRank;
	}

	public void setBioRank(Integer bioRank) {
		this.bioRank = bioRank;
	}

	public Integer getBlastRank() {
		return blastRank;
	}

	public void setBlastRank(Integer blastRank) {
		this.blastRank = blastRank;
	}

	public Integer getBlastVictoryRate() {
		return blastVictoryRate;
	}

	public void setBlastVictoryRate(Integer blastVictoryRate) {
		this.blastVictoryRate = blastVictoryRate;
	}

	public Integer getFunGpRank() {
		return funGpRank;
	}

	public void setFunGpRank(Integer funGpRank) {
		this.funGpRank = funGpRank;
	}

	public Integer getFunCrRank() {
		return funCrRank;
	}

	public void setFunCrRank(Integer funCrRank) {
		this.funCrRank = funCrRank;
	}

	public Integer getFunGrenadeKillRank() {
		return funGrenadeKillRank;
	}

	public void setFunGrenadeKillRank(Integer funGrenadeKillRank) {
		this.funGrenadeKillRank = funGrenadeKillRank;
	}

	public Integer getFunDropKillRank() {
		return funDropKillRank;
	}

	public void setFunDropKillRank(Integer funDropKillRank) {
		this.funDropKillRank = funDropKillRank;
	}

	public Integer getFunWeaponModRank() {
		return funWeaponModRank;
	}

	public void setFunWeaponModRank(Integer funWeaponModRank) {
		this.funWeaponModRank = funWeaponModRank;
	}

	public Integer getFunDeadRank() {
		return funDeadRank;
	}

	public void setFunDeadRank(Integer funDeadRank) {
		this.funDeadRank = funDeadRank;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the sysCharacterId
	 */
	public Integer getSysCharacterId() {
		return sysCharacterId;
	}

	/**
	 * @param sysCharacterId the sysCharacterId to set
	 */
	public void setSysCharacterId(Integer sysCharacterId) {
		this.sysCharacterId = sysCharacterId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the teamId
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the laborUnionId
	 */
	public Integer getLaborUnionId() {
		return laborUnionId;
	}

	/**
	 * @param laborUnionId the laborUnionId to set
	 */
	public void setLaborUnionId(Integer laborUnionId) {
		this.laborUnionId = laborUnionId;
	}

	/**
	 * @return the exp
	 */
	public Integer getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the wPackSize
	 */
	public Integer getWPackSize() {
		return wPackSize;
	}

	/**
	 * @param packSize the wPackSize to set
	 */
	public void setWPackSize(Integer packSize) {
		wPackSize = packSize;
	}

	/**
	 * @return the cPackSize
	 */
	public Integer getCPackSize() {
		return cPackSize;
	}

	/**
	 * @param packSize the cPackSize to set
	 */
	public void setCPackSize(Integer packSize) {
		cPackSize = packSize;
	}

	/**
	 * @return the gPoint
	 */
	public Integer getGPoint() {
		return gPoint;
	}

	/**
	 * @param point the gPoint to set
	 */
	public void setGPoint(Integer point) {
		gPoint = point;
	}

	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the resourceP
	 */
	public String getResourceP() {
		return resourceP;
	}

	/**
	 * @param resourceP the resourceP to set
	 */
	public void setResourceP(String resourceP) {
		this.resourceP = resourceP;
	}

	/**
	 * @return the resourceT
	 */
	public String getResourceT() {
		return resourceT;
	}

	/**
	 * @param resourceT the resourceT to set
	 */
	public void setResourceT(String resourceT) {
		this.resourceT = resourceT;
	}

	/**
	 * @return the runSpeed
	 */
	public Float getRunSpeed() {
		return runSpeed;
	}

	/**
	 * @param runSpeed the runSpeed to set
	 */
	public void setRunSpeed(Float runSpeed) {
		this.runSpeed = runSpeed;
	}

	/**
	 * @return the walkSpeed
	 */
	public Float getWalkSpeed() {
		return walkSpeed;
	}

	/**
	 * @param walkSpeed the walkSpeed to set
	 */
	public void setWalkSpeed(Float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	/**
	 * @return the crouchSpeed
	 */
	public Float getCrouchSpeed() {
		return crouchSpeed;
	}

	/**
	 * @param crouchSpeed the crouchSpeed to set
	 */
	public void setCrouchSpeed(Float crouchSpeed) {
		this.crouchSpeed = crouchSpeed;
	}

	/**
	 * @return the jumpSpeed
	 */
	public Float getJumpSpeed() {
		return jumpSpeed;
	}

	/**
	 * @param jumpSpeed the jumpSpeed to set
	 */
	public void setJumpSpeed(Float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	/**
	 * @return the throwSpeed
	 */
	public Float getThrowSpeed() {
		return throwSpeed;
	}

	/**
	 * @param throwSpeed the throwSpeed to set
	 */
	public void setThrowSpeed(Float throwSpeed) {
		this.throwSpeed = throwSpeed;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the laborUnion
	 */
	public String getLaborUnion() {
		return laborUnion;
	}

	/**
	 * @param laborUnion the laborUnion to set
	 */
	public void setLaborUnion(String laborUnion) {
		this.laborUnion = laborUnion;
	}

	/**
	 * @return the costumeP
	 */
	public String[] getCostumeP() {
		return costumeP;
	}

	/**
	 * @param costumeP the costumeP to set
	 */
	public void setCostumeP(String[] costumeP) {
		this.costumeP = costumeP;
	}

	/**
	 * @return the costumeT
	 */
	public String[] getCostumeT() {
		return costumeT;
	}

	/**
	 * @param costumeT the costumeT to set
	 */
	public void setCostumeT(String[] costumeT) {
		this.costumeT = costumeT;
	}

	public String[] getCostume0() {
		return costume0;
	}

	public void setCostume0(String[] costume0) {
		this.costume0 = costume0;
	}

	public String[] getCostume1() {
		return costume1;
	}

	public void setCostume1(String[] costume1) {
		this.costume1 = costume1;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getLastGameSide() {
		return lastGameSide;
	}

	public void setLastGameSide(Integer lastGameSide) {
		this.lastGameSide = lastGameSide;
	}

	public Integer getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Integer lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Float getAccelSpeed() {
		return accelSpeed;
	}

	public void setAccelSpeed(Float accelSpeed) {
		this.accelSpeed = accelSpeed;
	}

	public Integer getFlowerTotal() {
		return flowerTotal;
	}

	public void setFlowerTotal(Integer flowerTotal) {
		this.flowerTotal = flowerTotal;
	}

	public Integer getFlowerToday() {
		return flowerToday;
	}

	public void setFlowerToday(Integer flowerToday) {
		this.flowerToday = flowerToday;
	}

	
	public Date getFlowerLastTime() {
		return flowerLastTime;
	}

	public void setFlowerLastTime(Date flowerLastTime) {
		this.flowerLastTime = flowerLastTime;
	}

	public List<PlayerItem> getBuffList() {
		return buffList;
	}

	public void setBuffList(List<PlayerItem> buffList) {
		this.buffList = buffList;
	}

	public Integer getGScore() {
		return gScore;
	}

	public void setGScore(Integer score) {
		gScore = score;
	}

	public Integer getGKill() {
		return gKill;
	}

	public void setGKill(Integer kill) {
		gKill = kill;
	}

	public Integer getGDead() {
		return gDead;
	}

	public void setGDead(Integer dead) {
		gDead = dead;
	}

	public Integer getGHeadShot() {
		return gHeadShot;
	}

	public void setGHeadShot(Integer headShot) {
		gHeadShot = headShot;
	}

	public Integer getGGrenadeKill() {
		return gGrenadeKill;
	}

	public void setGGrenadeKill(Integer grenadeKill) {
		gGrenadeKill = grenadeKill;
	}

	public Integer getGKnifeKill() {
		return gKnifeKill;
	}

	public void setGKnifeKill(Integer knifeKill) {
		gKnifeKill = knifeKill;
	}

	public Integer getGHitPoint() {
		return gHitPoint;
	}

	public void setGHitPoint(Integer hitPoint) {
		gHitPoint = hitPoint;
	}

	public Integer getGWin() {
		return gWin;
	}

	public void setGWin(Integer win) {
		gWin = win;
	}

	public Integer getGTotal() {
		return gTotal;
	}

	public void setGTotal(Integer total) {
		gTotal = total;
	}

	public Integer getTScore() {
		return tScore;
	}

	public void setTScore(Integer score) {
		tScore = score;
	}

	public Integer getTKill() {
		return tKill;
	}

	public void setTKill(Integer kill) {
		tKill = kill;
	}

	public Integer getTDead() {
		return tDead;
	}

	public void setTDead(Integer dead) {
		tDead = dead;
	}

	public Integer getTHeadShot() {
		return tHeadShot;
	}

	public void setTHeadShot(Integer headShot) {
		tHeadShot = headShot;
	}

	public Integer getTGrenadeKill() {
		return tGrenadeKill;
	}

	public void setTGrenadeKill(Integer grenadeKill) {
		tGrenadeKill = grenadeKill;
	}

	public Integer getTKnifeKill() {
		return tKnifeKill;
	}

	public void setTKnifeKill(Integer knifeKill) {
		tKnifeKill = knifeKill;
	}

	public Integer getTHitPoint() {
		return tHitPoint;
	}

	public void setTHitPoint(Integer hitPoint) {
		tHitPoint = hitPoint;
	}

	public Integer getTWin() {
		return tWin;
	}

	public void setTWin(Integer win) {
		tWin = win;
	}

	public Integer getTTotal() {
		return tTotal;
	}

	public void setTTotal(Integer total) {
		tTotal = total;
	}

	public Integer getBioScore() {
		return bioScore;
	}

	public void setBioScore(Integer bioScore) {
		this.bioScore = bioScore;
	}

	public Integer getBioInfect() {
		return bioInfect;
	}

	public void setBioInfect(Integer bioInfect) {
		this.bioInfect = bioInfect;
	}

	public Integer getBioKill() {
		return bioKill;
	}

	public void setBioKill(Integer bioKill) {
		this.bioKill = bioKill;
	}

	public Integer getFlagScore() {
		return flagScore;
	}

	public void setFlagScore(Integer flagScore) {
		this.flagScore = flagScore;
	}

	public Integer getFlagSucceed() {
		return flagSucceed;
	}

	public void setFlagSucceed(Integer flagSucceed) {
		this.flagSucceed = flagSucceed;
	}

	public Integer getFlagReturn() {
		return flagReturn;
	}

	public void setFlagReturn(Integer flagReturn) {
		this.flagReturn = flagReturn;
	}

	public Integer getFlagWin() {
		return flagWin;
	}

	public void setFlagWin(Integer flagWin) {
		this.flagWin = flagWin;
	}

	public Integer getFlagTotal() {
		return flagTotal;
	}

	public void setFlagTotal(Integer flagTotal) {
		this.flagTotal = flagTotal;
	}

	public Integer getBlastScore() {
		return blastScore;
	}

	public void setBlastScore(Integer blastScore) {
		this.blastScore = blastScore;
	}

	public Integer getBlastSucceed() {
		return blastSucceed;
	}

	public void setBlastSucceed(Integer blastSucceed) {
		this.blastSucceed = blastSucceed;
	}

	public Integer getBlastDisable() {
		return blastDisable;
	}

	public void setBlastDisable(Integer blastDisable) {
		this.blastDisable = blastDisable;
	}

	public Integer getBlastWin() {
		return blastWin;
	}

	public void setBlastWin(Integer blastWin) {
		this.blastWin = blastWin;
	}

	public Integer getBlastTotal() {
		return blastTotal;
	}

	public void setBlastTotal(Integer blastTotal) {
		this.blastTotal = blastTotal;
	}

	public Integer getFunGpTotal() {
		return funGpTotal;
	}

	public void setFunGpTotal(Integer funGpTotal) {
		this.funGpTotal = funGpTotal;
	}

	public Integer getFunCrTotal() {
		return funCrTotal;
	}

	public void setFunCrTotal(Integer funCrTotal) {
		this.funCrTotal = funCrTotal;
	}

	public Integer getFunGrenadeKill() {
		return funGrenadeKill;
	}

	public void setFunGrenadeKill(Integer funGrenadeKill) {
		this.funGrenadeKill = funGrenadeKill;
	}

	public Integer getFunDropKill() {
		return funDropKill;
	}

	public void setFunDropKill(Integer funDropKill) {
		this.funDropKill = funDropKill;
	}

	public Integer getFunWeaponMod() {
		return funWeaponMod;
	}

	public void setFunWeaponMod(Integer funWeaponMod) {
		this.funWeaponMod = funWeaponMod;
	}

	public Integer getFunDead() {
		return funDead;
	}

	public void setFunDead(Integer funDead) {
		this.funDead = funDead;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	

}
