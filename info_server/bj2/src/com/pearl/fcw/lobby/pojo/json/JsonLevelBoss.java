package com.pearl.fcw.lobby.pojo.json;

import java.util.ArrayList;
import java.util.List;

import com.pearl.fcw.gm.pojo.WPayment;

/**
 * Boss配置
 */
public class JsonLevelBoss {
	private int sysCharacterId;
	private int bossCount = 1;//
	private List<Integer> equipList = new ArrayList<>();//装备的系统道具ID
	private List<Drop> dropList = new ArrayList<>();//掉落

	public int getSysCharacterId() {
		return sysCharacterId;
	}

	public void setSysCharacterId(int sysCharacterId) {
		this.sysCharacterId = sysCharacterId;
	}

	public int getBossCount() {
		return bossCount;
	}

	public void setBossCount(int bossCount) {
		this.bossCount = bossCount;
	}

	public List<Integer> getEquipList() {
		return equipList;
	}

	public void setEquipList(List<Integer> equipList) {
		this.equipList = equipList;
	}

	public List<Drop> getDropList() {
		return dropList;
	}

	public void setDropList(List<Drop> dropList) {
		this.dropList = dropList;
	}

	public class Drop {
		private WPayment wPayment;
		private float factor1;//影响掉落的参数
		private float factor2;//影响掉落的参数

		public WPayment getwPayment() {
			return wPayment;
		}

		public void setwPayment(WPayment wPayment) {
			this.wPayment = wPayment;
		}

		public float getFactor1() {
			return factor1;
		}

		public void setFactor1(float factor1) {
			this.factor1 = factor1;
		}

		public float getFactor2() {
			return factor2;
		}

		public void setFactor2(float factor2) {
			this.factor2 = factor2;
		}
	}
}
