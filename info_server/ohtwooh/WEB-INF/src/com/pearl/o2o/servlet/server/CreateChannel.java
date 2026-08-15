package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Constants;

public class CreateChannel extends BaseServerServlet {
	private static final long serialVersionUID = 3659242973977606881L;
	static Logger log = Logger.getLogger(CreateChannel.class.getName());

	public CreateChannel() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = res.getOutputStream();		

		try {
			String actionType = req.getParameter("action");		
			int channelId = StringUtil.toInt(req.getParameter("channel"));
			int serverId  = StringUtil.toInt(req.getParameter("sid"));

			if (actionType != null && Constants.CHANNEL_ACTION_ADD.equals(actionType)) {
				createService.createChannel(channelId, serverId);
			} else if (actionType != null && Constants.CHANNEL_ACTION_DELETE.equals(actionType)) {				
				if(serverId != 0){
					deleteService.deleteChannel(channelId, serverId);
				}
				else{
					deleteService.deleteChannel(channelId);					
				}
			}
		}
		catch(Exception e){
			log.error("Error in CreateChannel: ", e);
			out.write(BinaryUtil.toByta((int)1));
			return;
		}
		out.write(BinaryUtil.toByta((int)0));
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}
