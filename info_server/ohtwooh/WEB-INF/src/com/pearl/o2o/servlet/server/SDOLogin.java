package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.User;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.snda.services.oa.client.callback.UserLoginNotify;

@SuppressWarnings("serial")
public class SDOLogin extends BaseClientServlet{
	static Logger logger = Logger.getLogger(SDOLogin.class);
	private static String SUCCESS = "0";
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = null;
		try{
			out = res.getOutputStream();
			
			String token = req.getParameter("pass");
			String endPointIp = req.getParameter("ip");
			String version = req.getParameter("version").trim();

			if (StringUtil.isEmptyString(token) || StringUtil.isEmptyString(endPointIp) || StringUtil.isEmptyString(version) ) {
				throw new IllegalArgumentException("parameter error");
			}

			//check client version firstly
			String clientVersion = getService.getClientVersion();
			if (clientVersion == null) {
				logger.error("key[client.version] is empty !!!");
				throw new Exception("key[client.version] is empty");
			}
			if (!version.equals(clientVersion)) {
				throw new Exception (CommonUtil.messageFormat(ExceptionMessage.CLIENT_VERSION_ERROR, clientVersion));
			}
			
			UserLoginNotify  authenResponse = sdoComponent.synUserAuthen(token, endPointIp);
			
			logger.debug("after send authen request to sdo");
			
			if(authenResponse.getResult().equals(SUCCESS)){
				logger.debug("sdo login success");
				
				String sdoUid = authenResponse.getSndaID();
				User user = getService.getUser(sdoUid);
				
				if ( user == null){//if user not exist
					user = createService.createUser(sdoUid, "");
				}
				
				out.write(BinaryUtil.toByta(user.getId()));
				out.write(BinaryUtil.toByta(sdoUid));
				out.write(BinaryUtil.toByta(""));
				
			}else {
				logger.info("fail to login, result code from sdo is " + authenResponse.getResult());
				throw new Exception("登录失败");
			}
		}catch(Exception e){
			logger.error("Fail during Login: ", e);
			out.write(BinaryUtil.toByta((int)0));
			out.write(BinaryUtil.toByta(""));
			out.write(BinaryUtil.toByta(e.getMessage()));
		}
	}
}
