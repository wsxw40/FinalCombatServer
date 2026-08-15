package com.pearl.o2o.service.onbuy;



public interface PlayerBuyWay {
	public static final int SUCCESS = 0;
	public static final int FAIL = 1;
	public static final int NOT_ENOUGH = 2;
	public int buy() throws Exception;
}
