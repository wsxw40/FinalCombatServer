package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.User;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.BinaryUtil;



public class Login extends BaseServerServlet {
	private static final long serialVersionUID = 5509102483642154403L;
	static Logger log = Logger.getLogger(Login.class.getName());
	
	public Login(){
		super();
	}			
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = res.getOutputStream();
		User user = null;
			
		try{
			//String userName = StringUtil.encoding(req.getParameter("name"));
			String userName = req.getParameter("name");
			String password	= req.getParameter("pass");		
			String version = req.getParameter("version");
			
/*			if (version == null) {
				throw new Exception ("version is null");
			}*/
			
			
			user = getService.getUser(userName);			
			if(user == null){
				user = createService.createUser(userName, "");
			}
			out.write(BinaryUtil.toByta(user.getId()));
			out.write(BinaryUtil.toByta(userName));
			out.write(BinaryUtil.toByta(""));
		}
		catch(Exception e){
			log.error("Error in Login: ", e);
			out.write(BinaryUtil.toByta((int)0));
			out.write(BinaryUtil.toByta(""));
			out.write(BinaryUtil.toByta(e.getMessage()));
		}
	}
}
