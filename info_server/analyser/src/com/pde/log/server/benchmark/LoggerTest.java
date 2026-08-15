/**
 * 
 */
package com.pde.log.server.benchmark;

import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pde.log.common.LogMessage.LogMsg;
import com.pde.log.server.util.LoggerHelper;

/**
 * @author lifengyang
 *
 */
public class LoggerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// server端往 infolog0.log 文件写日志信息，log的具体路径(E:\test\logserver\)，配置在 /logback.xml
		LogMsg logMsg = LogMsg
				.newBuilder()
				.setTime(System.currentTimeMillis())
				.setLevel(Level.INFO_INT)
				.setLogger("infolog0")
				.setThread(Thread.currentThread().getName())
				.setMsg("1.3|xldcf0000382965     |玫瑰大公爵|1|求软妹子啊！！求软妹子啊！！求软妹子啊！！求软妹子啊求软妹子|4016|大喇叭|2012-07-15 03:38:22")
				.build();

		LoggerFactory.getLogger("infolog1").info("abc");
		LoggerHelper.log(logMsg);
	}

}
