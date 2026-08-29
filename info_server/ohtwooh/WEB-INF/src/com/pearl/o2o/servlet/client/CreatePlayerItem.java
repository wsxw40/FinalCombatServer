package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class CreatePlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = 6356406975633293330L;
	static Logger log = Logger.getLogger(CreatePlayerItem.class.getName());
	public CreatePlayerItem() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		
		PrintWriter out=res.getWriter();
		String result;
		int newGP=0;
		int newCR=0;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int sysItemId=StringUtil.toInt(req.getParameter("sid"));
			int type=StringUtil.toInt(req.getParameter("t"));
			int subType=StringUtil.toInt(req.getParameter("st"));
			int costId=StringUtil.toInt(req.getParameter("costid"));
			Map<String, Object> map=createService.createPlayerItem(userId, playerId, type, subType, sysItemId,costId,Constants.BOOLEAN_NO);
			result=Converter.createPlayerItem(Constants.NUM_ONE,(Integer)map.get("gp"),(Integer)map.get("cr"),(Integer)map.get("id"),null);
			out.write(result);
		}
		catch (NotBuyEquipmentException nbe) {
			log.warn("Exception in CreatePlayerItem",nbe);
			result=Converter.createPlayerItem(Constants.NUM_ZERO,newGP,newCR,Constants.NUM_ZERO,nbe.getMessage());
			out.write(result);
		}
		catch (Exception e) {
			log.error("Exception in CreatePlayerItem",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
	}
}
