package com.pde.log.server.handler;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Stopwatch;
import com.pde.info.analyser.persistence.PersistenceData;
import com.pde.log.common.LogMessage.LogMsg;
import com.pde.log.server.util.Constants;
import com.pde.log.server.util.LoggerHelper;

public class LogServerHandler extends SimpleChannelHandler {

	private static final int MsgQueuePollWait = 1000 * 5;// ms
	private static final int SwapSize = 10000 * 1;
	private final ArrayList<LogMsg> LogSwap = new ArrayList<LogMsg>(SwapSize);

	private final LinkedBlockingQueue<LogMsg> msgQueue;
	private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	
	public LogServerHandler(int bufferSize, final long delay) {
		this.msgQueue = new LinkedBlockingQueue<LogMsg>(bufferSize);
		this.service.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				
				dumpAndLog(MsgQueuePollWait);
			}
		}, 100, delay, TimeUnit.MILLISECONDS);
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent event)
			throws Exception {
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent event)
			throws Exception {
		
		LogMsg logMsg = (LogMsg) event.getMessage();
		
		if (!msgQueue.offer(logMsg)) {
		
			LoggerHelper.log(logMsg);

			Constants.logServerLogger.error("LogServerHandler.msgQueue.offer Fail:" + msgQueue.size());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Constants.logServerLogger.error("LogServerHandler.exceptionCaught", e);
		ctx.getChannel().close();
	}

	private final AtomicLong times = new AtomicLong();

	private int dumpAndLog(int msgQueuePollWait) {
		Stopwatch stopwatch = new Stopwatch().start();
		LogMsg poll = null;
		int drainTo = 0;
		try {
			if ((!msgQueue.isEmpty())
					|| (poll = msgQueue.poll(msgQueuePollWait, TimeUnit.MILLISECONDS)) != null) {
				if (null != poll) {
					LoggerHelper.log(poll);
					//insert to db
					PersistenceData persistenceData=(PersistenceData)context.getBean("persistenceData");
					persistenceData.save(poll.getMsg(),poll.getLogger());
				}
				LogSwap.clear();
				drainTo = msgQueue.drainTo(LogSwap, SwapSize);
				if (drainTo > 0) {
					LoggerHelper.logs(LogSwap);
				}
			}
		} catch (Exception e) {
			Constants.logServerLogger.error("dumpAndLog Exception", e);
		}
		int dumpAndLog = (poll != null ? 1 : 0) + drainTo;
		stopwatch.stop();
		if (Constants.logServerLogger.isDebugEnabled()) {
			Constants.logServerLogger.debug(String.format(
					"LogServerHandler.dumpAndLog:\t%d\t%s\t%d", dumpAndLog,
					stopwatch.toString(), times.addAndGet(dumpAndLog)));
		}
		return dumpAndLog;
	}

	public void shutdown() {
		this.service.shutdown();
		int size = this.msgQueue.size();
		int loop = size % SwapSize == 0 ? size % SwapSize : size % SwapSize + 1;
		for (int i = 0; i < loop; i++) {
			dumpAndLog(1);
		}
	}

	
}
