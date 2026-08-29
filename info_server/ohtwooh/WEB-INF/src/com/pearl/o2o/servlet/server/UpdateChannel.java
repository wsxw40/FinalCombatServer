package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Receiver;


public class UpdateChannel extends BaseServerServlet {
	private static final long serialVersionUID = -1804268538576026176L;
	static Logger log = Logger.getLogger(UpdateChannel.class.getName());

	public UpdateChannel() {
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		InputStream in = req.getInputStream();
		byte[] buffer = new byte[32];
		try{
			String objKey =  Constants.GET_SERVER_LIST;
			Receiver r = new Receiver(in, buffer);
			int size=r.readInt();
			for(int i=0;i<size;i++){
				int channelId=r.readInt();
				int count=r.readInt();
				int max=r.readInt();
				updateService.updateChannel(channelId, count,max);
			}
		}catch(Exception e){
			log.error("Error in UpdateLocation: " + e);
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		
	}

}
