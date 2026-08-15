package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerInfo extends BaseClientServlet {
	private static final long serialVersionUID = 6385243821827353337L;
	static Logger log = Logger.getLogger(GetPlayerInfo.class.getName());	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{					
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			String cName = req.getParameter("name");
			Player player = null;
			if (!StringUtil.isEmptyString(cName)) {
				player = getService.getPlayerByName(cName);
				if (player == null) {
					out.write(Converter.error(ExceptionMessage.PALERY_NOT_EXIST));
				}
			}else{
				player = getService.getPlayerRankById(playerId);
			}
			out.write(Converter.playerInfo(player));			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
