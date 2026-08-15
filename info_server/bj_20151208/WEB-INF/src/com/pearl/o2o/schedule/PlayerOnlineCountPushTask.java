package com.pearl.o2o.schedule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.CharacterOnlineInfo;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class PlayerOnlineCountPushTask implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(PlayerOnlineCountPushTask.class);
	@Override
	public void run() {
		try {
			//FIXME for test
//			CharacterOnlineInfo cOnlineInfo;
//			try {
//				cOnlineInfo = ServiceLocator.soClient.getCharacterOnlineInfo();
//			} catch (Exception ee) {
//				logger.error("exception happen while getCharacterOnlineInfo",ee);
//				cOnlineInfo = null;
//			}
//			int online=0;
//			StringBuilder sb = new StringBuilder("b|");
//			sb.append(cOnlineInfo.getOnlineCharacter()+"|");
//			if (cOnlineInfo != null) {
//				for (Map.Entry<Integer, Map<Integer,Integer>> serverEntry : cOnlineInfo.getChannelClientCount().entrySet()) {
//					for (Map.Entry<Integer, Integer> channelEntry : serverEntry.getValue().entrySet()) {
//						online+=channelEntry.getValue();
//						sb.append(serverEntry.getKey()).append("/").append(channelEntry.getKey()).append("/").append(channelEntry.getValue()).append(";");
//					}
//				}
//			}
			StringBuffer sbURL = new StringBuffer();
			sbURL.append("http://");
			sbURL.append(ConfigurationUtil.FC_ONLINE_URL);
			sbURL.append("zoneid=");
			sbURL.append(ConfigurationUtil.XUNLEI_SERVER_IP);
			sbURL.append("&datetime=");
			sbURL.append(System.currentTimeMillis());
			sbURL.append("&num=");
//			sbURL.append(online);
			//FIXME for test
			Date now=new Date();
			sbURL.append((int)(Math.random()*10)+now.getHours()*100);
			sbURL.append("&db=fc");
			URL url=new URL(sbURL.toString());
			URLConnection connection=url.openConnection();
			connection.setConnectTimeout(1000);
			connection.setReadTimeout(1000);
			InputStreamReader r = new InputStreamReader(connection.getInputStream());
			BufferedReader rd = new BufferedReader(r);
			StringBuffer sb1 = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb1.append(line);
			}
			rd.close();
			String result = sb1.toString(); 
			if(!"{statusCode:1}".equals(result)){
				logger.error("push online count:"+sbURL.toString()+"   "+result);
				
			}else{
				logger.info(sbURL.toString()+"   "+result);
			}
			
		} catch (Exception e) {
			logger.error("PlayerOnlineCountPushTask/Error:\t", e);
		}

	}

}
