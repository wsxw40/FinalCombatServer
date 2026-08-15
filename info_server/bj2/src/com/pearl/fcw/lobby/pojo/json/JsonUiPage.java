package com.pearl.fcw.lobby.pojo.json;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端数据分页设置
 */
public class JsonUiPage {
	private Map<Integer, Integer> itemTypeAndPageSizeInCombine = new HashMap<>();//合成界面物品类型决定每页数量
	private Map<Integer, Integer> itemTypeAndPageSizeInShop = new HashMap<>();//商店界面物品类型决定每页数量
	private int pageSizeInExchange = 28;//免费兑换界面每页数量

	public Map<Integer, Integer> getItemTypeAndPageSizeInCombine() {
		return itemTypeAndPageSizeInCombine;
	}

	public void setItemTypeAndPageSizeInCombine(Map<Integer, Integer> itemTypeAndPageSizeInCombine) {
		this.itemTypeAndPageSizeInCombine = itemTypeAndPageSizeInCombine;
	}

	public Map<Integer, Integer> getItemTypeAndPageSizeInShop() {
		return itemTypeAndPageSizeInShop;
	}

	public void setItemTypeAndPageSizeInShop(Map<Integer, Integer> itemTypeAndPageSizeInShop) {
		this.itemTypeAndPageSizeInShop = itemTypeAndPageSizeInShop;
	}

	public int getPageSizeInExchange() {
		return pageSizeInExchange;
	}

	public void setPageSizeInExchange(int pageSizeInExchange) {
		this.pageSizeInExchange = pageSizeInExchange;
	}
}
