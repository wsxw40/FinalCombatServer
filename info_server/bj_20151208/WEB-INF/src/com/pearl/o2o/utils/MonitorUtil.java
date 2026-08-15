package com.pearl.o2o.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.socket.DefaultSocketHandler;
import com.pearl.o2o.socket.Request;

public class MonitorUtil {
	 private static final Logger log = LoggerFactory.getLogger(MonitorUtil.class.getName());
	public static void logInfostats(){
		List<DefaultSocketHandler> channels = ConfigurationUtil.CHANNEL_HANDLERS.getChannels();
		DefaultSocketHandler handler=null;
		if (channels != null&&channels.size()>0) {
			handler=channels.get(0);
			Request request = generateRequest();
			List<String> proxyInfo = new ArrayList<String>();
			try {
				byte[] result = Constants.EMPTY_BYTE_ARRAY;
				result = ServiceLocator.soClient.send(request, handler);
				proxyInfo = getFromByte(result);
				ThreadPoolExecutor loginPool = (ThreadPoolExecutor) handler.getLoginPool();
				ThreadPoolExecutor serverPool = (ThreadPoolExecutor) handler.getServerPool();
				ThreadPoolExecutor clientPool = (ThreadPoolExecutor) handler.getClientPool(); 
				ThreadPoolExecutor gamePool = (ThreadPoolExecutor) handler.getGamePool(); 
				ThreadPoolExecutor playerPool = (ThreadPoolExecutor) handler.getPlayerPool(); 
				ServiceLocator.infoLog.info(CommonUtil.simpleDateFormat.format(new Date()));
				ServiceLocator.infoLog.info("loginPool: "+loginPool.getActiveCount()+" "+loginPool.getQueue().size());
				ServiceLocator.infoLog.info("serverPool: "+serverPool.getActiveCount()+" "+serverPool.getQueue().size());
				ServiceLocator.infoLog.info("clientPool: "+clientPool.getActiveCount()+" "+clientPool.getQueue().size());
				ServiceLocator.infoLog.info("gamePool: "+gamePool.getActiveCount()+" "+gamePool.getQueue().size());
				ServiceLocator.infoLog.info("playerPool: "+playerPool.getActiveCount()+" "+playerPool.getQueue().size());
				ServiceLocator.infoLog.info("proxyInfo: "+proxyInfo.get(0)+" "+proxyInfo.get(1)+" "+proxyInfo.get(2)+" "+proxyInfo.get(3)+" "+proxyInfo.get(4)+" "+proxyInfo.get(5));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static Request generateRequest(){
		Request request = new Request();
		request.setType((byte)ServerMessage.SM_RequestStatus.ordinal());
		request.setUrl("");
		request.setUserData(Constants.EMPTY_BYTE_ARRAY);
		return request;
	}
	
	private static List<String> getFromByte(byte[] result) throws IOException{ 
		BinaryChannelBuffer in = new BinaryChannelBuffer(result);
		
		int count = in.readInt();
		List<String> list = new ArrayList<String>();
		for (int i=0 ;i<count;i++) {
			String key = in.readString();
			String value = in.readString();
			list.add(value);
		}
		return list;
	}
}
