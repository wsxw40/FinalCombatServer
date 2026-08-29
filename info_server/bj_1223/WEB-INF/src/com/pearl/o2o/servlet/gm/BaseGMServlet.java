package com.pearl.o2o.servlet.gm;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.BeanFactory;

import com.pde.log.client.InforLogger;
import com.pearl.o2o.dao.impl.nonjoin.InterfaceRecordDao;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.MessageService;
import com.pearl.o2o.service.NosqlService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TransferDataToDC;

public class BaseGMServlet extends HttpServlet {
	private static final long serialVersionUID = -5579552513208224076L;	
	protected InforLogger infoLogger;
	
	//log to analyser
	protected TransferDataToDC transferDataToDc;

	public TransferDataToDC getTransferDataToDc() {
		return transferDataToDc;
	}
	public void setTransferDataToDc(TransferDataToDC transferDataToDc) {
		this.transferDataToDc = transferDataToDc;
	}
	public InforLogger getInfoLogger() {
		return infoLogger;
	}
	public void setInfoLogger(InforLogger infoLogger) {
		this.infoLogger = infoLogger;
	}
	public BaseGMServlet(){
		
	}	
	@Override    
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    final BeanFactory context = ConfigurationUtil.beanFactory;
	    mcc				= (MemcachedClient) context.getBean("memcachedClient");
	    getService 		= (GetService) context.getBean("getService" );
	    createService 	= (CreateService) context.getBean("createService" );
	    updateService 	= (UpdateService) context.getBean("updateService" );  	    
	    deleteService	= (DeleteService) context.getBean("deleteService");	  
	    nosqlService 	= (NosqlService) context.getBean("nosqlService");
	    messageService  = (MessageService) context.getBean("messageService");
	    soClient		= (SocketClientNew) context.getBean("socketClientNew");
	    dao 			= (InterfaceRecordDao) context.getBean("interfaceRecordDao");
	    infoLogger		= (InforLogger) context.getBean("infoLogger");
	    transferDataToDc= (TransferDataToDC) context.getBean("transferDataToDc");
	}
	
	protected InterfaceRecordDao 	dao;
	protected MemcachedClient 		mcc;
	protected GetService 			getService;
	protected CreateService 		createService;
	protected UpdateService 		updateService;
	protected DeleteService 		deleteService;
	protected NosqlService			nosqlService;	
	protected SocketClientNew 		soClient;
	protected MessageService	    messageService;
	protected String 				key = "xlyouxi@pde";
	
	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}

	public void setGetService(GetService getService) {
		this.getService = getService;
	}

	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}

	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

	public void setDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}

	
	public void setNosqlService(NosqlService nosqlService) {
		this.nosqlService = nosqlService;
	}
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public SocketClientNew getSoClient() {
		return soClient;
	}
	public void setSoClient(SocketClientNew soClient) {
		this.soClient = soClient;
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String lockKey = getLockKey(req);
		if(null != lockKey){
			try {
				boolean isLocked = ServiceLocator.ml.tryLock(lockKey, 2000); // 可以以PLAYER ID为锁的KEY，或自己能方便标识公共资源的KEY，该方法不会抛出任何异常
				if (isLocked) { // 加锁了便需要解锁
					String innerService = innerService(req,res);
					res.setContentType("text/html");
					PrintWriter out = res.getWriter();
					out.write(innerService);//其他错误
					out.flush();
					out.close();
				} else {// 拿锁失败
					ServiceLocator.fileLog.warn("lock fail key=" + lockKey);
				}
			} finally {
				ServiceLocator.ml.unlock(lockKey);// 无论如何要在这里解锁
			}
		}else{
			String innerService = innerService(req,res);
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.write(innerService);//其他错误
			out.flush();
			out.close();
		}
	}	
	
	protected String innerService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "";
	}
	
	protected String getLockKey(HttpServletRequest request) {
		return null;
	}
}
