package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.StringUtil;

public class GetChannelList extends BaseClientServlet {
	private static final long serialVersionUID = -8900434171788568386L;
	static Logger log = Logger.getLogger(GetChannelList.class.getName());

	public GetChannelList() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();		
		String serverId = req.getParameter("sid");
		// to do get the channel list by serverid
		String result = "";
		
		try {
			if (serverId != null) {
				int server=StringUtil.toInt(serverId);
				result = getService.getChannelList(server);
			}else{
			
			}
		} catch (NumberFormatException e) {
			result = "1000";// return NumberFormatException erro code
		}
		catch(Exception e){
			log.error("Error in GetChannelList: " + e);
		}			
		out.write(result);

		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}
