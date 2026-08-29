package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerListDefault extends BaseClientServlet {


	private static final long serialVersionUID = -8836051040356139681L;
	static Logger logger=Logger.getLogger(GetPlayerListDefault.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doPost(req, res);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			String objKey=CacheUtil.oCharacter();
			List<Character> characters = mcc.get(objKey);
			if(characters == null){
				characters = getService.getCharacterList(Constants.BOOLEAN_YES);
			}
			out.write(Converter.characters(characters));
		}			
		catch (Exception e) {
			logger.error("error happened in GetPlayerListDefault",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
}
