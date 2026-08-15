package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.socket.DefaultSocketHandler;
import com.pearl.o2o.socket.Request;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.PerformanceDatas.InterfaceEntry;
import com.pearl.o2o.utils.StringUtil;

public class MonitorServlet extends BaseServerServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String channelId = req.getParameter("channelId");
		if (!StringUtil.isEmptyString(channelId)) {
			DefaultSocketHandler handler = ConfigurationUtil.CHANNEL_HANDLERS.getChannelById(Integer.valueOf(channelId));
			if (handler == null) {
				res.getWriter().write("no proxy connected");
			}
			
			Request request = generateRequest();
			//proxy info
			List<String[]> proxyInfo = new ArrayList<String[]>();
			try {
				byte[] result = Constants.EMPTY_BYTE_ARRAY;
				result = soClient.send(request, handler);
				proxyInfo = getFromByte(result);
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			//
			res.getWriter().write(Converter.monitor(handler,  proxyInfo));
		}else{// list all channel
			List<DefaultSocketHandler> channels = ConfigurationUtil.CHANNEL_HANDLERS.getChannels();
			
			String action = req.getParameter("action");
			
			List<InterfaceEntry> datas = ConfigurationUtil.performanceDatas.getDatas();
			if (!StringUtil.isEmptyString(action) && "flush".equals(action)) {
				ConfigurationUtil.performanceDatas.flush();
			}
			res.getWriter().write(Converter.monitorList(datas, channels));
		}
	}
	
	
	private Request generateRequest(){
		Request request = new Request();
		request.setType((byte)ServerMessage.SM_RequestStatus.ordinal());
		request.setUrl("");
		request.setUserData(Constants.EMPTY_BYTE_ARRAY);
		return request;
	}
	
	private List<String[]> getFromByte(byte[] result) throws IOException{ 
		BinaryChannelBuffer in = new BinaryChannelBuffer(result);
		int count = in.readInt();
		List<String[]> list = new ArrayList<String[]>();
		for (int i=0 ;i<count;i++) {
			String key = in.readString();
			String value = in.readString();
			String str=key + "\t   \t" + value;
			String[] strs=str.split("\t");
			list.add(strs);
		}
		return list;
	}
}
