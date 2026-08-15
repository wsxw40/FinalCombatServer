package com.pearl.o2o.pojo;

import java.io.Serializable;

public class PlayerGPointTotal implements Serializable {

	private static final long serialVersionUID = 3417242366531780313L;
	private String totalGPoint;  // 总存量
	private String totalPlayer;  // 已有c币角色数
	// 以下为持有c币量分布
	private String amount1; // 0-5000
	private String amount2; // 5001-10000
	private String amount3; // 10001-15000
	private String amount4; // 15001-20000
	private String amount5; // 20001-30000
	private String amount6; // 30001-50000
	private String amount7; // 50001-70000
	private String amount8; // 70001-100000
	private String amount9; // 100001-150000
	private String amount10; // 150001-200000
	private String amount11; // 200001-300000
	private String amount12; // more than 300001

	public String getTotalGPoint() {
		return totalGPoint;
	}
	public void setTotalGPoint(String totalGPoint) {
		this.totalGPoint = totalGPoint;
	}
	public String getTotalPlayer() {
		return totalPlayer;
	}
	public void setTotalPlayer(String totalPlayer) {
		this.totalPlayer = totalPlayer;
	}
	public String getAmount1() {
		return amount1;
	}
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}
	public String getAmount2() {
		return amount2;
	}
	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}
	public String getAmount3() {
		return amount3;
	}
	public void setAmount3(String amount3) {
		this.amount3 = amount3;
	}
	public String getAmount4() {
		return amount4;
	}
	public void setAmount4(String amount4) {
		this.amount4 = amount4;
	}
	public String getAmount5() {
		return amount5;
	}
	public void setAmount5(String amount5) {
		this.amount5 = amount5;
	}
	public String getAmount6() {
		return amount6;
	}
	public void setAmount6(String amount6) {
		this.amount6 = amount6;
	}
	public String getAmount7() {
		return amount7;
	}
	public void setAmount7(String amount7) {
		this.amount7 = amount7;
	}
	public String getAmount8() {
		return amount8;
	}
	public void setAmount8(String amount8) {
		this.amount8 = amount8;
	}
	public String getAmount9() {
		return amount9;
	}
	public void setAmount9(String amount9) {
		this.amount9 = amount9;
	}
	public String getAmount10() {
		return amount10;
	}
	public void setAmount10(String amount10) {
		this.amount10 = amount10;
	}
	public String getAmount11() {
		return amount11;
	}
	public void setAmount11(String amount11) {
		this.amount11 = amount11;
	}
	public String getAmount12() {
		return amount12;
	}
	public void setAmount12(String amount12) {
		this.amount12 = amount12;
	}
	
	
}
