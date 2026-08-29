/**
 * 
 */
package com.pearl.o2o.pojo;

import com.google.common.base.Preconditions;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MeltingConstants;

/**
 * @author lifengyang
 *
 */
public class PlayerMelting  extends BaseMappingBean<PlayerMelting> {
	private static final long serialVersionUID = 2704482943842748175L;
	private int level;
	private int exp;
	
	private double remaind;
	
	private int num;
	private int recovery;
	
	private long lastInit;

	private long startTime;
	private long grandTotalTime;
	
	private transient int upgrade;
	
	public PlayerMelting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayerMelting(int playerId) {
		this.id = playerId;
		this.level = 1;
		this.exp = 0;
		this.recovery = 0;
		initMeltingEnergy();
	}

	public void initMeltingEnergy() {
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

	private long getRecoveryTime(){
		return MeltingConstants.GetRecoveryTime.apply(this.recovery) * OneMinute;
	}
	public PlayerMelting calibrate() {
		if (!CacheUtil.isToday(this.lastInit)) {
			this.initMeltingEnergy();
		} else if (this.num < MeltingConstants.MeltingEnergyNum && this.recovery > 0 ) {
			while(getResumeTime() >= getRecoveryTime() && ++this.num < MeltingConstants.MeltingEnergyNum){
				this.startTime = this.startTime - this.grandTotalTime + getRecoveryTime();
				this.grandTotalTime = 0;
				this.recovery++;
			}
		}
		return this;
	}

	public long getResumeTime() {
		return System.currentTimeMillis() - this.startTime + this.grandTotalTime;
	}
	
	public long getRemaindTime(){
		if(this.recovery>0){
			return MeltingConstants.GetRecoveryTime.apply(this.recovery) * OneMinute - getResumeTime();
		}
		return 0;
	}

	/**
	 * 客户端 采用 s 计时 会差生 0-1000ms的误差
	 * @return
	 */
	public long getRemaindSecond(){
		return getNum() == MeltingConstants.MeltingEnergyNum ? 0 :getRemaindTime()/1000+1;
	}
	
	public PlayerMelting consume(int vipLevel) {
		Preconditions.checkState(this.num>0, ExceptionMessage.MELTING_NOT_ENOUGH);
		if (this.num == MeltingConstants.MeltingEnergyNum) {
			this.recovery++;
			this.startTime = System.currentTimeMillis();
			this.grandTotalTime = 0;
		}
		// 4级以上vip（含）不消耗熔能
		if (vipLevel<4) {
			this.num--;
		}
		return this;
	}

	public PlayerMelting playerOnline() {
		this.startTime = System.currentTimeMillis();
		return this;
	}

	public PlayerMelting playerOffline() {
		this.grandTotalTime += System.currentTimeMillis() - this.startTime ;
		this.startTime = System.currentTimeMillis();
		return this;
	}
	
	
	public int getTotalExp(){
		return 10000*(1<<(this.level-1));
	}
	
	public int getSlotNum(){
		return level+1;
	}
	
	public PlayerMelting addExp(int add){
		this.exp+=add;
		for(;exp>=getTotalExp();){
			if(level>=8){
				exp=getTotalExp();
				level=8;
				return this;
			}else{
				exp-=getTotalExp();
				level++;
				this.upgrade = this.level;
			}
		}
		return this;
	}
	
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public double getRemaind() {
		return remaind;
	}
	public int getRemaindInt() {
		return (int)getRemaind();
	}
	public void setRemaind(double remaind) {
		this.remaind = remaind;
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
		return "PlayerMelting [level=" + level + ", exp=" + exp + ", remaind=" + remaind + ", num=" + num + ", recovery=" + recovery + ", lastInit=" + lastInit + ", startTime="
				+ startTime + ", grandTotalTime=" + grandTotalTime + "]";
	}

	public int getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	} 

}
