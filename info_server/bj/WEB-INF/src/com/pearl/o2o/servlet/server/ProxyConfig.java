package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

public class ProxyConfig extends BaseServerServlet {
	private static final long serialVersionUID = -3139142051652850979L;
	static Logger log = LoggerFactory.getLogger(ProxyConfig.class.getName());
	
	
	protected  byte[] innerService(BinaryReader in) throws IOException, Exception{
		try{
			List<String> keywords = getService.getBannedWordsStrList();
			Iterator<String> iterator = keywords.iterator(); 
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(BinaryUtil.toByta(keywords.size()));
			while(iterator.hasNext()){
				out.write(BinaryUtil.toByta((String)iterator.next()));			
			}
			return out.toByteArray();
		}catch (Exception e) {
			log.warn("error happened in ProxyConfig");
			throw e;
		}
	}
	
}
