package com.snda.services.oa.client.bean;

import java.util.Set;

public class PurchaseResult {
	private int balance;
	private Set<Integer> ids;
	
	public PurchaseResult(int balance, Set<Integer> ids) {
		this.balance = balance;
		this.ids = ids;
	}
	public int getBalance() {
		return balance;
	}
	public Set<Integer> getIds() {
		return ids;
	}
	
}
