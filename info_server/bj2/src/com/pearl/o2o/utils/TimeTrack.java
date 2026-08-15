/**
 * 
 */
package com.pearl.o2o.utils;

import org.slf4j.Logger;

/**
 * @author lifengyang
 * 
 */
public class TimeTrack {
	private long lastTime;
	private long time;
	private Logger log;

	public TimeTrack(Logger log) {
		this.lastTime = System.currentTimeMillis();
		this.log = log;
	}

	public TimeTrack() {
		this.lastTime = System.currentTimeMillis();
	}

	public void debug(String prefix) {
		if (log.isDebugEnabled()) {
			time = System.currentTimeMillis() - lastTime;
			log.debug(String.format("%s %s ms", prefix, System.currentTimeMillis() - lastTime));
			lastTime = System.currentTimeMillis();
		}
	}

	public void stdout(String prefix) {
		time = System.currentTimeMillis() - lastTime;
		System.out.printf("%s %s ms\n", prefix, System.currentTimeMillis() - lastTime);
		lastTime = System.currentTimeMillis();
	}

	public long getTime() {
		return time;
	}
}
