package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.UpdateService;
import com.snda.services.oa.client.bean.SDOComponent;

public class BaseClientServlet extends HttpServlet {

	private static final long serialVersionUID = -5579552513208224076L;	


	public BaseClientServlet(){
		
	}	
	
	@Override    
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	    mcc				= (MemcachedClient) context.getBean("memcachedClient");
	    getService 		= (GetService) context.getBean("getService" );
	    createService 	= (CreateService) context.getBean("createService" );
	    updateService 	= (UpdateService) context.getBean("updateService" );  	    
	    deleteService	= (DeleteService) context.getBean("deleteService");	  
	    sdoComponent	= (SDOComponent) context.getBean("sdoComponent");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("GBK");
	}		
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("GBK");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//res.setCharacterEncoding("GBK");
		//req.setCharacterEncoding("GBK");
		super.service(req, res);
	}
	protected MemcachedClient 		mcc;
	protected GetService 			getService;
	protected CreateService 		createService;
	protected UpdateService 		updateService;
	protected DeleteService 		deleteService;
	protected SDOComponent 			sdoComponent;
	
	public final String getEndpointIp(HttpServletRequest req){
		String xff = req.getHeader("x-forwarded-for");
		//X-Forwarded-For: client1, proxy1, proxy2...
		//see http://en.wikipedia.org/wiki/X-Forwarded-For
		if (xff != null) {
			int index = xff.indexOf(",");
			if (index >0){
				return xff.substring(0, index);
			}else {
				return xff;
			}
		}
		return null;
	}
}
