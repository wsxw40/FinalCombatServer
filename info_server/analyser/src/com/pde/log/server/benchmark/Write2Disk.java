/**
 * 
 */
package com.pde.log.server.benchmark;

import java.util.ArrayList;

import com.google.common.base.Stopwatch;
import com.pde.log.common.LogMessage.Level;
import com.pde.log.common.LogMessage.LogMsg;
import com.pde.log.server.util.LoggerHelper;

/**
 * @author lifengyang
 *
 */
public class Write2Disk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 10000 * 100;
		LogMsg logMsg = LogMsg.newBuilder().setTime(System.currentTimeMillis()).setLevel(Level.INFO_INT).setLogger("infolog0").setThread(Thread.currentThread().getName())
				.setMsg("1.3|xldcf0000382965     |玫瑰大公爵|1|求软妹子啊！！求软妹子啊！！求软妹子啊！！求软妹子啊求软妹子|4016|大喇叭|2012-07-15 03:38:22").build();
		ArrayList<LogMsg> arrayList = new ArrayList<LogMsg>(count);
		for (int i =0;i<count;i++) {
			arrayList.add(i, logMsg);
		}
		Stopwatch stopwatch = new Stopwatch();
		for (int i = 0; i < 100; i++) {
			stopwatch.reset().start();
			for (LogMsg msg : arrayList) {
				LoggerHelper.log(msg);
			}
			String time1 = stopwatch.stop().toString();
			
			stopwatch.reset().start();
			LoggerHelper.logs(arrayList);
			String time2 = stopwatch.stop().toString();

			System.out.printf("%-10s%-10s\n", time1, time2);
		}
	}

}
