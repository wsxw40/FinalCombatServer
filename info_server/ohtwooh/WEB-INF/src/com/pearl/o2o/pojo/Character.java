package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Date;

import com.pearl.o2o.utils.Constants;

public class Character implements Serializable {


	private static final long serialVersionUID = -5016463533351202384L;
	private Integer id;
	private Float runSpeed;
	private Float walkSpeed;
	private Float crouchSpeed;
	private Float accelSpeed=8f;
	private Float jumpSpeed;
	private Float throwSpeed;
	private String resourceP;
	private String resourceT;
	private String gender;
	private String isDefault;
	private Integer cost=100;
	//not exist in database
	private int isChange;
	private String[]	costumeP;
	private String[]	costumeT;
	//log
	private int logVersion;
	private Date logTime;
	private int logId;
	
	public void putOnCostume(){
		String[] resourcesP = resourceP.split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] resourcesT = resourceT.split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] resOutputP = new String[Constants.AVATAR_CHANGE_SIZE];
		String[] resOutputT = new String[Constants.AVATAR_CHANGE_SIZE];
		System.arraycopy(resourcesP, 0, resOutputP, 0, resourcesP.length);
		System.arraycopy(resourcesT, 0, resOutputT, 0, resourcesT.length);
		this.costumeP=resOutputP;
		this.costumeT=resOutputT;
	}
	
	public Float getRunSpeed() {
		return runSpeed;
	}
	public void setRunSpeed(Float runSpeed) {
		this.runSpeed = runSpeed;
	}
	public Float getWalkSpeed() {
		return walkSpeed;
	}
	public void setWalkSpeed(Float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	public Float getCrouchSpeed() {
		return crouchSpeed;
	}
	public void setCrouchSpeed(Float crouchSpeed) {
		this.crouchSpeed = crouchSpeed;
	}
	public Float getJumpSpeed() {
		return jumpSpeed;
	}
	public void setJumpSpeed(Float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}
	public Float getThrowSpeed() {
		return throwSpeed;
	}
	public void setThrowSpeed(Float throwSpeed) {
		this.throwSpeed = throwSpeed;
	}
	public String getResourceP() {
		return resourceP;
	}
	public void setResourceP(String resourceP) {
		this.resourceP = resourceP;
	}
	public String getResourceT() {
		return resourceT;
	}
	public void setResourceT(String resourceT) {
		this.resourceT = resourceT;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String[] getCostumeP() {
		return costumeP;
	}
	public void setCostumeP(String[] costumeP) {
		this.costumeP = costumeP;
	}
	public String[] getCostumeT() {
		return costumeT;
	}
	public void setCostumeT(String[] costumeT) {
		this.costumeT = costumeT;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public Float getAccelSpeed() {
		return accelSpeed;
	}

	public void setAccelSpeed(Float accelSpeed) {
		this.accelSpeed = accelSpeed;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
