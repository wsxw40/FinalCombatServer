/**
 * 
 */
package com.pearl.o2o.pojo;

/**
 * @author lifengyang
 *
 */
public class MeltingReslut {
	private int result;
	private int mt;
	private PlayerMelting playerMelting;
	private MeltingAward meltingAward;
	private int rate;
	private int price;
	
	public MeltingReslut() {
		super();
	}
	public MeltingReslut(int result) {
		super();
		this.result = result;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public int getMt() {
		return mt;
	}
	public void setMt(int mt) {
		this.mt = mt;
	}
	public PlayerMelting getPlayerMelting() {
		return playerMelting;
	}
	public void setPlayerMelting(PlayerMelting playerMelting) {
		this.playerMelting = playerMelting;
	}
	public MeltingAward getMeltingAward() {
		return meltingAward;
	}
	public void setMeltingAward(MeltingAward meltingAward) {
		this.meltingAward = meltingAward;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
