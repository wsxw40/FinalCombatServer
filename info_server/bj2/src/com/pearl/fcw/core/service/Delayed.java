package com.pearl.fcw.core.service;

public interface Delayed {
    void delay() throws Exception;

	void addMsg(Object msg);
}
