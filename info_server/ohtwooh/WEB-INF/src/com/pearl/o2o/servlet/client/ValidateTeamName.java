package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

public class ValidateTeamName extends BaseClientServlet {

	private static final long serialVersionUID = 5138527124601272755L;
	static Logger logger=Logger.getLogger(GetTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			//String name = StringUtil.encoding(req.getParameter("name"));
			String name = req.getParameter("name");
			boolean isError=false;
			String nameStr="";
			
			if (StringUtil.isEmptyString(name)) {
				isError=true;
				nameStr += ExceptionMessage.EMPTY_STR;
			}
			
			if(!KeywordFilterUtil.isLegalInput(name)){
				isError=true;
				nameStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
			}
			int count=getService.fuzzyCountTeam(name);
			if(count>0){
				isError=true;
				nameStr+=ExceptionMessage.TEAM_EXIST+",";
			}
			if (name != null && name.getBytes("GBK").length > 10) {
				isError=true;
				nameStr+=ExceptionMessage.TOO_LONG+",";
			}
			if(!"".equals(nameStr)){
				out.write(Converter.commonFeedback(CommonUtil.cutLastWord(nameStr)));
			}else{
				out.write(Converter.commonFeedback(null));
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("系统出现异常错误，请联系GM"));
		}
	}

}
