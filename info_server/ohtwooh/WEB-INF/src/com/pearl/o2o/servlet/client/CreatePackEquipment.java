package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class CreatePackEquipment extends BaseClientServlet {
	private static final long serialVersionUID = -6515353905004343636L;
	static Logger log = Logger.getLogger(CreatePackEquipment.class.getName());
	
	public CreatePackEquipment() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
	
		PrintWriter out=res.getWriter();
		String result;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int playerItemId=StringUtil.toInt(req.getParameter("pid"));
			int packId=StringUtil.toInt(req.getParameter("packid"));
			int type=StringUtil.toInt(req.getParameter("t"));
			int seq=StringUtil.toInt(req.getParameter("seq"));
			if(Constants.DEFAULT_WEAPON_TYPE == type){
				updateService.updateWeaponPackEquipment(userId, playerId, type, playerItemId, packId);
			}else if(Constants.DEFAULT_COSTUME_TYPE == type){
				updateService.updateCostumePackEquipment(userId, playerId, type, playerItemId, packId,seq);
			}
			
			result=Converter.createPackEquipment(null);
			
			out.write(result);
		}
		catch (NotEquipException nee) {
			result=Converter.createPackEquipment(nee.getMessage());
			out.write(result);
		}
		catch (Exception e) {
			log.error("Exception in CreatePackEquipment",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
	}
}
