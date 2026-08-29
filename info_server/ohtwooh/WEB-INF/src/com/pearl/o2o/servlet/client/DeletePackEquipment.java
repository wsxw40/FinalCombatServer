package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DeletePackEquipment extends BaseClientServlet {
	static Logger log = Logger.getLogger(DeletePackEquipment.class.getName());
	private static final long serialVersionUID = -3467750958211447191L;

	public DeletePackEquipment() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out=res.getWriter();
		String result;
		int itemType=0;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int packId=StringUtil.toInt(req.getParameter("packid"));
			int type=StringUtil.toInt(req.getParameter("t"));
			int seq=StringUtil.toInt(req.getParameter("seq"));
			if(type==1&&seq==3){
				throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
			}else{
			
				deleteService.deletePackEquip(userId,playerId,packId,type,seq);
			}
			
			result=Converter.deletePackEquipment(null);
			out.write(result);
		}catch (NotEquipException e) {
			result=Converter.deletePackEquipment(e.getMessage());
			out.write(result);
		}
		catch (Exception nee) {
			log.error("exception in DeletePackEquipment servlet",nee);
			result=Converter.deletePackEquipment("系统出现异常错误，请联系GM");
			out.write(result);
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
}
