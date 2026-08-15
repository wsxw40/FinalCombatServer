package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.KeywordFilterUtil;



public class ProxyConfig extends BaseServerServlet {
	private static final long serialVersionUID = -3139142051652850979L;
	static Logger log = Logger.getLogger(ProxyConfig.class.getName());
	
	public ProxyConfig(){
		super();
	}			
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = res.getOutputStream();
		try{
			List<String> keywords = KeywordFilterUtil.getKeywordsList();
			Iterator<String> iterator = keywords.iterator(); 
				
			out.write(BinaryUtil.toByta(keywords.size()));
			while(iterator.hasNext()){
				out.write(BinaryUtil.toByta((String)iterator.next()));			
			}
		}catch (Exception e) {
			log.error("error happened in ProxyConfig");
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}	
}
