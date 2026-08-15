package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetAvatarPackList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = Logger.getLogger(GetAvatarPackList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result="";
		
		int pages;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int packId=StringUtil.toInt(req.getParameter("pack"));
			int seq=StringUtil.toInt(req.getParameter("seq"));
			int page=StringUtil.toInt(req.getParameter("page"));
			List<PlayerItem> returnList=getService.getItemPackList(userId, playerId, Constants.DEFAULT_COSTUME_TYPE, packId, seq);
			if(returnList.size()%Constants.DEFAULT_PAGE_SIZE==0){
				pages=returnList.size()/Constants.DEFAULT_PAGE_SIZE;
			}else{
				pages=returnList.size()/Constants.DEFAULT_PAGE_SIZE+1;
			}
			int fromIndex=(page-1)*Constants.DEFAULT_PAGE_SIZE;
			int toIndex=(page)*Constants.DEFAULT_PAGE_SIZE;
			List<PlayerItem> list=returnList.subList(fromIndex, toIndex>returnList.size()?returnList.size():toIndex);
			Player player=mcc.get(CacheUtil.oPlayer(playerId));
			if(player==null){
				player=getService.getPlayerById(playerId);
			}
			result=Converter.avatarPackList(page, pages, list,player.getRank());
			out.write(result);
		}
		catch(Exception e){
			log.error("Error in GetItemPackList: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
			
	}

}
