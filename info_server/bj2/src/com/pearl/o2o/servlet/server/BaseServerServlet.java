package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.beans.factory.BeanFactory;

import com.pde.log.client.InforLogger;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.MessageService;
import com.pearl.o2o.service.NosqlService;
import com.pearl.o2o.service.ResetService;
import com.pearl.o2o.service.TeamService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.socket.Response;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Receiver;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TransferDataToDC;

public class BaseServerServlet extends HttpServlet {
	private static final long serialVersionUID = 8593037293384703852L;
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
	public BaseServerServlet(){

	}
	protected  String getLockKey(BinaryChannelBuffer in)throws Exception{return null;};
	protected  byte[] innerService(BinaryReader in) throws IOException, Exception{return null;};
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		try{
			byte[] buffer = new byte[64];
			BinaryReader r = new Receiver(req.getInputStream(), buffer);
			byte[] out = innerService(r);
			res.getOutputStream().write(out);
		}catch(Exception e){
			res.sendError(500);
		}
	}


	public List<Response> execute(BinaryChannelBuffer in) throws MemcachedException, TimeoutException, Exception{
		BinaryChannelBuffer inClone = in.clone();

		boolean isLocked = false;

		String key = getLockKey(inClone);
		byte[] result = null;

		if(key == null){
			result = innerService(in);
		}else{
			isLocked = ServiceLocator.ml.tryLock(key, 2000); // 可以以PLAYER ID为锁的KEY，或自己能方便标识公共资源的KEY，该方法不会抛出任何异常
			if (isLocked) { // 加锁了便需要解锁
				try {
					result = innerService(in);
				} finally {
					ServiceLocator.ml.unlock(key);// 无论如何要在这里解锁
				}
			} else {//拿锁失败
				throw new Exception("BaseServerServlet lock fail key=" + key); // 在指定时间内拿不到锁，根据业务逻辑做相应处理
			}
		}


		List<Response> responses = new ArrayList<Response>();
		//if (out.length !=0){
			responses.add(Response.binary(result));
		//}
		return responses;
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		context = ConfigurationUtil.beanFactory;
	    mcc				= (MemcachedClient) context.getBean("memcachedClient");
	    getService 		= (GetService) context.getBean("getService" );
	    createService 	= (CreateService) context.getBean("createService" );
	    deleteService	= (DeleteService) context.getBean("deleteService");
	    updateService   = (UpdateService) context.getBean("updateService");
	    messageService 	= (MessageService) context.getBean("messageService");
	    nosqlService 	= (NosqlService) context.getBean("nosqlService");
	    soClient		= (SocketClientNew) context.getBean("socketClientNew");
	    resetService	= (ResetService) context.getBean("resetService");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
	}


	protected BeanFactory context;
	protected GetService getService;
	protected CreateService createService;
	protected DeleteService deleteService;
	protected UpdateService updateService;
	protected MemcachedClient 		mcc;
	protected SocketClientNew soClient ;
	protected MessageService 	messageService;
	protected NosqlService			nosqlService;
	protected ResetService 			resetService;
	protected  TeamService 		teamService;
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

	public BeanFactory getContext() {
		return context;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public NosqlService getNosqlService() {
		return nosqlService;
	}

	public void setNosqlService(NosqlService nosqlService) {
		this.nosqlService = nosqlService;
	}

	public SocketClientNew getSoClient() {
		return soClient;
	}

	public TeamService getTeamService() {
		return teamService;
	}
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	public void setSoClient(SocketClientNew soClient) {
		this.soClient = soClient;
	}


}
