package com.pearl.o2o.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


public class StageClearPlayerInfo {
	private int id;
	private String name;
	private int side;
	private int onlineMinutes;
	private int offlineMinutes;
	
	private int score;
	private int kill;			
	private int dead;			
	private int head_shot;		
	private int grenade_kill;	
	private int knife_kill;		
	private int hit_point;
	
	private int bio_infect;	
	private int bio_infected;
	private int bio_zombie_win;	
	private int bio_soldier_win;	
	private int bio_as_zombie_win;	
	private int bio_as_soldier_win;	
	private int bio_level;		
	private int bio_max_infect;
	
	private int flag_capture;		
	private int flag_return;		
	private int flag_succeed;		
	
	private int blast_plant;		
	private int blast_disable;		
	private int blast_succeed;		
	
	private int drop_kill;
	private int fun_grenade_kill;	
	private int fun_drop_kill;    	  
	//below value will be set in server side
	private int curr_exp;
	private int exp;  // exp increment
	private int rank;//current rank
	private int gp;// gp increment
	
	public int getBio_infected() {
		return bio_infected;
	}
	public void setBio_infected(int bio_infected) {
		this.bio_infected = bio_infected;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getHead_shot() {
		return head_shot;
	}
	public void setHead_shot(int head_shot) {
		this.head_shot = head_shot;
	}
	public int getGrenade_kill() {
		return grenade_kill;
	}
	public void setGrenade_kill(int grenade_kill) {
		this.grenade_kill = grenade_kill;
	}
	public int getKnife_kill() {
		return knife_kill;
	}
	public void setKnife_kill(int knife_kill) {
		this.knife_kill = knife_kill;
	}
	
	
	
	public int getOnlineMinutes() {
		return onlineMinutes;
	}
	public void setOnlineMinutes(int onlineMinutes) {
		this.onlineMinutes = onlineMinutes;
	}
	public int getOfflineMinutes() {
		return offlineMinutes;
	}
	public void setOfflineMinutes(int offlineMinutes) {
		this.offlineMinutes = offlineMinutes;
	}
	public int getHit_point() {
		return hit_point;
	}
	public void setHit_point(int hit_point) {
		this.hit_point = hit_point;
	}
	public int getBio_infect() {
		return bio_infect;
	}
	public void setBio_infect(int bio_infect) {
		this.bio_infect = bio_infect;
	}
	public int getBio_zombie_win() {
		return bio_zombie_win;
	}
	public void setBio_zombie_win(int bio_zombie_win) {
		this.bio_zombie_win = bio_zombie_win;
	}
	public int getBio_soldier_win() {
		return bio_soldier_win;
	}
	public void setBio_soldier_win(int bio_soldier_win) {
		this.bio_soldier_win = bio_soldier_win;
	}
	public int getBio_as_zombie_win() {
		return bio_as_zombie_win;
	}
	public void setBio_as_zombie_win(int bio_as_zombie_win) {
		this.bio_as_zombie_win = bio_as_zombie_win;
	}
	public int getBio_as_soldier_win() {
		return bio_as_soldier_win;
	}
	public void setBio_as_soldier_win(int bio_as_soldier_win) {
		this.bio_as_soldier_win = bio_as_soldier_win;
	}
	public int getBio_level() {
		return bio_level;
	}
	public void setBio_level(int bio_level) {
		this.bio_level = bio_level;
	}
	public int getFlag_capture() {
		return flag_capture;
	}
	public void setFlag_capture(int flag_capture) {
		this.flag_capture = flag_capture;
	}
	public int getFlag_return() {
		return flag_return;
	}
	public void setFlag_return(int flag_return) {
		this.flag_return = flag_return;
	}
	public int getFlag_succeed() {
		return flag_succeed;
	}
	public void setFlag_succeed(int flag_succeed) {
		this.flag_succeed = flag_succeed;
	}
	public int getBlast_plant() {
		return blast_plant;
	}
	public void setBlast_plant(int blast_plant) {
		this.blast_plant = blast_plant;
	}
	public int getBlast_disable() {
		return blast_disable;
	}
	public void setBlast_disable(int blast_disable) {
		this.blast_disable = blast_disable;
	}
	public int getBlast_succeed() {
		return blast_succeed;
	}
	public void setBlast_succeed(int blast_succeed) {
		this.blast_succeed = blast_succeed;
	}
	public int getFun_grenade_kill() {
		return fun_grenade_kill;
	}
	public void setFun_grenade_kill(int fun_grenade_kill) {
		this.fun_grenade_kill = fun_grenade_kill;
	}
	public int getFun_drop_kill() {
		return fun_drop_kill;
	}
	public void setFun_drop_kill(int fun_drop_kill) {
		this.fun_drop_kill = fun_drop_kill;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getCurr_exp() {
		return curr_exp;
	}
	public void setCurr_exp(int curr_exp) {
		this.curr_exp = curr_exp;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getBio_max_infect() {
		return bio_max_infect;
	}
	public void setBio_max_infect(int bio_max_infect) {
		this.bio_max_infect = bio_max_infect;
	}
	
	
	
	public int getDrop_kill() {
		return drop_kill;
	}
	public void setDrop_kill(int drop_kill) {
		this.drop_kill = drop_kill;
	}
	public int getGp() {
		return gp;
	}
	public void setGp(int gp) {
		this.gp = gp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	
}
