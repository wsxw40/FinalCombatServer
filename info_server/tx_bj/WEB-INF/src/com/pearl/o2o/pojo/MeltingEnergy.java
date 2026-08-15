/**
 * 
 */
package com.pearl.o2o.pojo;

import java.util.Iterator;

import com.google.common.base.Preconditions;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * @author lifengyang
 * 
 */
@Deprecated
public class MeltingEnergy {
	private int playerId;
	private int num;
	private int recovery;
	private long lastInit;

	private long startTime;
	private long grandTotalTime;

	public MeltingEnergy(int playerId) {
		this.playerId = playerId;
		init();
	}

	public MeltingEnergy(int playerId, int num, int recovery, long lastInit, long startTime, long grandTotalTime) {
		this.playerId = playerId;
		this.num = num;
		this.recovery = recovery;
		this.lastInit = lastInit;
		this.startTime = startTime;
		this.grandTotalTime = grandTotalTime;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRecovery() {
		return recovery;
	}

	public void setRecovery(int recovery) {
		this.recovery = recovery;
	}

	public long getLastInit() {
		return lastInit;
	}

	public void setLastInit(long lastInit) {
		this.lastInit = lastInit;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getGrandTotalTime() {
		return grandTotalTime;
	}

	public void setGrandTotalTime(long grandTotalTime) {
		this.grandTotalTime = grandTotalTime;
	}

	@Override
	public String toString() {
		return "MeltingEnergy [playerId=" + playerId + ", num=" + num + ", recovery=" + recovery + ", lastInit=" + lastInit + ", startTime=" + startTime + ", grandTotalTime="
				+ grandTotalTime + "]";
	}

	public static MeltingEnergy mergeFromString(int playerId, String value) {
		Preconditions.checkNotNull(value);
		try {
			Iterator<String> iterator = CacheUtil.splitterByColon.split(value).iterator();
			int num = Integer.parseInt(iterator.next());
			int recovery = Integer.parseInt(iterator.next());
			long lastInit = Long.parseLong(iterator.next());
			long startTime = Long.parseLong(iterator.next());
			long grandTotalTime = Long.parseLong(iterator.next());
			return new MeltingEnergy(playerId, num, recovery, lastInit, startTime, grandTotalTime);
		} catch (Exception e) {
			ServiceLocator.meltingLog.error(LogUtils.JoinerByColon.join("MeltingEnergy","mergeFromString",playerId,value),e);
		}
		return null;
	}

	public String transformCacheValue() {
		return CacheUtil.JoinerByColon.join(this.getNum(), this.getRecovery(), this.getLastInit(), this.getStartTime(), this.getGrandTotalTime());

	}

	public void init() {
		this.num = MeltingConstants.DefalutMeltingEnergyNum;
		this.recovery = 0;
		this.startTime = 0;
		this.grandTotalTime = 0;
		this.lastInit = System.currentTimeMillis();
		if (this.num < MeltingConstants.MeltingEnergyNum) {
			this.recovery++;
			this.startTime = System.currentTimeMillis();
		}
	}

	private static long OneMinute = 1000 * 60L;

	public MeltingEnergy calibrate() {
		if (!CacheUtil.isToday(this.lastInit)) {
			this.init();
		} else if (this.num < MeltingConstants.MeltingEnergyNum 
				&& this.recovery > 0 
				&& getResumeTime() >= MeltingConstants.RecoveryTime[this.recovery - 1] * OneMinute
				&& ++this.num < MeltingConstants.MeltingEnergyNum) {
			this.recovery++;
			this.startTime = this.startTime - this.grandTotalTime + MeltingConstants.RecoveryTime[this.recovery - 2] * OneMinute;
			this.grandTotalTime = 0;
		}
		return this;
	}

	public long getResumeTime() {
		return System.currentTimeMillis() - this.startTime + this.grandTotalTime;
	}
	
	public long getRemaindTime(){
		if(this.recovery>0){
			return MeltingConstants.RecoveryTime[this.recovery - 1] * OneMinute - getResumeTime();
		}
		return 0;
	}

	public MeltingEnergy consume() {
		Preconditions.checkState(this.num>0, ExceptionMessage.MELTING_NOT_ENOUGH);
		if (this.num == MeltingConstants.MeltingEnergyNum) {
			this.recovery++;
			this.startTime = System.currentTimeMillis();
			this.grandTotalTime = 0;
		}
		this.num--;
		return this;
	}

	public MeltingEnergy playerOnline() {
		this.startTime = System.currentTimeMillis();
		return this;
	}

	public MeltingEnergy playerOffline() {
		this.grandTotalTime += System.currentTimeMillis() - this.startTime ;
		this.startTime = System.currentTimeMillis();
		return this;
	}
}
