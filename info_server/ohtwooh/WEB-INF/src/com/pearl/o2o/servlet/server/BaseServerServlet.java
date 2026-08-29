package com.pearl.o2o.servlet.server;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.socket.SocketClient;



public class BaseServerServlet extends HttpServlet {

	private static final long serialVersionUID = 8593037293384703852L;


	public BaseServerServlet(){
		
	}	
	 
	
	@Override    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	 
	    final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	    mcc				= (MemcachedClient) context.getBean("memcachedClient");
	    getService 		= (GetService) context.getBean("getService" );
	    createService 	= (CreateService) context.getBean("createService" );  
	    deleteService	= (DeleteService) context.getBean("deleteService");
	    updateService   = (UpdateService) context.getBean("updateService");
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
		res.setCharacterEncoding("GBK");
		super.service(req, res);
		
	}
	
	protected GetService getService;
	protected CreateService createService;
	protected DeleteService deleteService;
	protected UpdateService updateService;
	protected MemcachedClient 		mcc;
	protected SocketClient soClient = SocketClient.getInstance();
	
	public void setGetService(GetService getService) {
		this.getService = getService;
	}	
	
	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}		
	
	public void setDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}
	
	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}


	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}
	
	
}
