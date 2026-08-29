package com.pearl.o2o.utils;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.Team;

public class ResRecord {
	private Team attackTeam;
	private Team defenceTeam;
	private BattleFieldRobDaily battleFieldRobDaily;
	private boolean isAttacker;
	public Team getAttackTeam() {
		return attackTeam;
	}
	public void setAttackTeam(Team attackTeam) {
		this.attackTeam = attackTeam;
	}
	public Team getDefenceTeam() {
		return defenceTeam;
	}
	public void setDefenceTeam(Team defenceTeam) {
		this.defenceTeam = defenceTeam;
	}
	public BattleFieldRobDaily getBattleFieldRobDaily() {
		return battleFieldRobDaily;
	}
	public void setBattleFieldRobDaily(BattleFieldRobDaily battleFieldRobDaily) {
		this.battleFieldRobDaily = battleFieldRobDaily;
	}
	public boolean getIsAttacker() {
		return isAttacker;
	}
	public void setIsAttacker(boolean isAttacker) {
		this.isAttacker = isAttacker;
	}
	
	public String getBattleTime(){
		String result="";
		if(this.battleFieldRobDaily!=null){
			result=DateFormatUtil.getYMDHMSf().format(this.battleFieldRobDaily.getRobDate());
		}
		return result;
	}
	

}
