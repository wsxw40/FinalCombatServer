package com.pearl.o2o.pojo;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class LevelWeapon extends BaseItemPojo implements Serializable{
	
		private static final long serialVersionUID = -1774380518543769733L;
		private int sysLevelId;
		private String 		displayName;
		//not in database
		private int isChange;
		
		public int getIsChange() {
			return isChange;
		}
		public void setIsChange(int isChange) {
			this.isChange = isChange;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	
		public int getSysLevelId() {
			return sysLevelId;
		}
		public void setSysLevelId(int sysLevelId) {
			this.sysLevelId = sysLevelId;
		}
		
	}
	
