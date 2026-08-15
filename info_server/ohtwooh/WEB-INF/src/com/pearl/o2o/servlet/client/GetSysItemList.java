package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.User;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOItemOrder;

public class GetSysItemList extends BaseClientServlet {
	private static final long serialVersionUID = -4205634889709446795L;
	static Logger log = Logger.getLogger(GetSysItemList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();

		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int type = StringUtil.toInt(req.getParameter("t"));
			int subType = StringUtil.toInt(req.getParameter("st"));
			int page = StringUtil.toInt(req.getParameter("p"));
			int id = StringUtil.toInt(req.getParameter("iid"));
//			String filter = req.getParameter("f");	
			String gender = req.getParameter("gender").trim();	
			if(type!=Constants.DEFAULT_COSTUME_TYPE){
				gender=Constants.GENDER_BOTH;
			}
			int  filter=StringUtil.toInt(req.getParameter("filter"));
			Player player = getService.getPlayerById(playerId);
			
			int newGP = player.getGPoint();
			int newCR = 0; 
			try{
				newCR = getService.getCR(userId);
			}catch(Exception e){
				newCR = -1;
			}
			
			List<SysItem> sysItemList = new ArrayList<SysItem>();
			String result="";
			int pages = 0;
			ArrayList<SysItem> array[] = null;	
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || subType < Constants.NUM_ZERO || subType > Constants.SUB_TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct.");
			}
			
			List<SysItem> list = new ArrayList<SysItem>();
			int endIndex = page * Constants.DEFAULT_PAGE_SIZE - 1;
			int startIndex = (page - 1) * Constants.DEFAULT_PAGE_SIZE;
			
			if (type == Constants.ITEM_TYPE_PART && filter == Constants.NUM_ONE) {
				if (subType == 0) {
					list = getService.getSysItemListByType(userId, playerId,type, gender,filter);
					for (int i = 0; i < list.size(); i++) {
						if (i >= startIndex && i <= endIndex) {
							sysItemList.add(list.get(i));
						}
					}
					pages = CommonUtil.getListPages(list,Constants.DEFAULT_PAGE_SIZE);
					result = Converter.sysItemList(page, pages, sysItemList, newGP, newCR,player.getRank());
				} else {
					array = mcc.get(CacheUtil.oShopPart(playerId,subType));
					if (array == null) {
						array = getService.getSysItem(userId, playerId, type,subType, gender,filter);
						
					}
					if (array != null) {
						pages = array.length;
					}
					if (pages >= page) {
						sysItemList = array[page - 1];
					}
					result = Converter.sysItemList(page, pages, sysItemList, newGP, newCR,player.getRank());
				}

			}
			else{
				if(subType==0){
					list = getService.getSysItemListByType(userId, playerId,type, gender,filter);
					for (int i = 0; i < list.size(); i++) {
						if (i >= startIndex && i <= endIndex) {
							sysItemList.add(list.get(i));
						}
					}
					pages = CommonUtil.getListPages(list,Constants.DEFAULT_PAGE_SIZE);
					result = Converter.sysItemList(page, pages, sysItemList,newGP,newCR,player.getRank());
				} else {
					// get data array from memory or database
					array = mcc.get(CacheUtil.oShop(type, subType, gender));
					if (array == null) {
						array = getService.getSysItem(userId,playerId,type, subType, gender,filter);
					}
					if (array != null) {
						pages = array.length;
					}
					if (pages >= page) {
						sysItemList = array[page - 1];
					}
					result = Converter.sysItemList(page, pages, sysItemList, newGP, newCR,player.getRank());
				}
			}
			out.write(result);
		}
		catch(Exception e){
			log.error("Error in GetSysItem: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
