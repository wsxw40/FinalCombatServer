package com.pearl.o2o.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ConfigurationUtil;

/**
 * @author wuxiaofei
 *
 */
public class ResetService extends BaseService {
	private static Logger log = LoggerFactory.getLogger(ResetService.class.getName());
	private MessageService messageService;
	public void resetSysItem() throws Exception {
		StringBuffer sb = new StringBuffer();
		String ips = ConfigurationUtil.XUNLEI_INFO_IP;
		if (ips != null) {
			String[] ip = ips.split(",");
			for (String i : ip) {
				String urlStr = "http://" + i + ":8080/bj/gm/ResetSysItem";
				URL url = new URL(urlStr);
				URLConnection connection = url.openConnection();
				InputStreamReader r = new InputStreamReader(connection
						.getInputStream());
				BufferedReader rd = new BufferedReader(r);
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(i + "           ").append(line);
				}
				sb.append("\n");
				rd.close();
			}
		}
		log.warn(sb.toString());
	}
	
	public void resetStrengthAppend() throws Exception {
		StringBuffer sb = new StringBuffer();
		String ips = ConfigurationUtil.XUNLEI_INFO_IP;
		if (ips != null) {
			String[] ip = ips.split(",");
			for (String i : ip) {
				String urlStr = "http://" + i + ":8080/bj/gm/reset?type=0";
				URL url = new URL(urlStr);
				URLConnection connection = url.openConnection();
				InputStreamReader r = new InputStreamReader(connection
						.getInputStream());
				BufferedReader rd = new BufferedReader(r);
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(i + "           ").append(line);
				}
				sb.append("\n");
				rd.close();
			}
		}
		log.warn(sb.toString());
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
