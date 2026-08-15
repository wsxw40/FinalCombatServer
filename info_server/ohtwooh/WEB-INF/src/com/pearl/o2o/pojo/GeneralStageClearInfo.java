package com.pearl.o2o.pojo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class GeneralStageClearInfo {
	private int rid;
	private int type;
	private int winner;
	private int terrorist_score;
	private int police_score;
	private String	ace;			
	private String	mvp;
	
	private String	last_shot;
	
	private List<StageClearPlayerInfo> stageClearPlayerInfo;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getTerrorist_score() {
		return terrorist_score;
	}
	public void setTerrorist_score(int terrorist_score) {
		this.terrorist_score = terrorist_score;
	}
	public int getPolice_score() {
		return police_score;
	}
	public void setPolice_score(int police_score) {
		this.police_score = police_score;
	}
	public String getAce() {
		return ace;
	}
	public void setAce(String ace) {
		this.ace = ace;
	}
	public String getMvp() {
		return mvp;
	}
	public void setMvp(String mvp) {
		this.mvp = mvp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLast_shot() {
		return last_shot;
	}
	public void setLast_shot(String last_shot) {
		this.last_shot = last_shot;
	}
	
	public List<StageClearPlayerInfo> getStageClearPlayerInfo() {
		return stageClearPlayerInfo;
	}
	public void setStageClearPlayerInfo(
			List<StageClearPlayerInfo> stageClearPlayerInfo) {
		this.stageClearPlayerInfo = stageClearPlayerInfo;
	}			
	
	public String getBlast_succeed_king_name(){
		StageClearPlayerInfo stageClearPlayerInfo = getBlast_succeed_king();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getBlast_succeed() <= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	
	public StageClearPlayerInfo getBlast_succeed_king(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getBlast_succeed()).compareTo(Integer.valueOf(o2.getBlast_succeed()));
			}
		}));
	}
	
	public String getBlast_disable_king_name(){
		StageClearPlayerInfo stageClearPlayerInfo = getBlast_disable_king();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getBlast_disable() <= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	public StageClearPlayerInfo getBlast_disable_king(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getBlast_disable()).compareTo(Integer.valueOf(o2.getBlast_disable()));
			}
		}));
	}
	

	public String getFlag_succeed_king_name(){
		StageClearPlayerInfo stageClearPlayerInfo = getFlag_succeed_king();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getFlag_succeed() <= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	
	public StageClearPlayerInfo getFlag_succeed_king(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getFlag_succeed()).compareTo(Integer.valueOf(o2.getFlag_succeed()));
			}
		}));
	}
	
	
	public String getFlag_return_king_name(){
		StageClearPlayerInfo stageClearPlayerInfo =  getFlag_return_king();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getFlag_return() <= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	
	public StageClearPlayerInfo getFlag_return_king(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getFlag_return()).compareTo(Integer.valueOf(o2.getFlag_return()));
			}
		}));
	}
	
	public String getBio_zombie_lead_name(){
		StageClearPlayerInfo stageClearPlayerInfo =  getBio_zombie_lead();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getBio_infect() <= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	
	public StageClearPlayerInfo getBio_zombie_lead(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getBio_infect()).compareTo(Integer.valueOf(o2.getBio_infect()));
			}
		}));
	}
	
	public String getBio_hunter_name(){
		StageClearPlayerInfo stageClearPlayerInfo =  getBio_hunter();
		if (stageClearPlayerInfo == null ||stageClearPlayerInfo.getKill()<= 0) {
			return "";
		}else{
			return stageClearPlayerInfo.getName();
		}
	}
	
	
	public StageClearPlayerInfo getBio_hunter(){
		return  (Collections.max(stageClearPlayerInfo, new Comparator<StageClearPlayerInfo>(){
			@Override
			public int compare(StageClearPlayerInfo o1, StageClearPlayerInfo o2) {
				return Integer.valueOf(o1.getKill()).compareTo(Integer.valueOf(o2.getKill()));
			}
		}));
	}
	
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
}
