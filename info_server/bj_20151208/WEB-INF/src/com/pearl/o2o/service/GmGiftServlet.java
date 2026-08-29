package com.pearl.o2o.service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 测试版功能，发送物品
 * 
 * @param
 * @author OuYangGuang
 * */
public class GmGiftServlet extends BaseServerServlet {
	static final long serialVersionUID = 0L;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
			String action = request.getParameter("action");
			if(!"userLoginSuccess".equals(((HttpServletRequest)request).getSession().getAttribute("namePwd"))){
				response.getWriter().write("503");
				return;
			}
			if("1".equals(action)){
				sendItem((HttpServletRequest)request, (HttpServletResponse)response);
			}else if("2".equals(action)){
				likeItem((HttpServletRequest)request, (HttpServletResponse)response);
			}else{
				dataGet((HttpServletRequest)request, (HttpServletResponse)response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	 * 可用可强化的所有物品
	 * 
	 * @param
	 * @author OuYangGuang
	 * */
	public void dataGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Writer out = response.getWriter();
		JsonObject jo = new JsonObject();
		String actionStr 			= request.getParameter("actionR");
		int action=null!=actionStr&&!"".equals(actionStr)?Integer.parseInt(actionStr):0;
		int type=1;
		int subType=1;
		
		switch(action){
			case 2:
				subType=2;
			break;
			case 3:
				subType=3;
			break;
			case 4:
				type=2;
				subType=1;
			break;
			case 5:
				type=3;
				subType=1;
			break;
			case 6:
				type=3;
				subType=2;
			break;
		}
		
		try {
			java.util.List<SysItem> sysItems = getService.getSysItemList(type, subType);
			for(SysItem sysItem : sysItems){
				if(sysItem.getIsStrengthen()==1 && !"undefined".equals(sysItem.getDisplayNameCN()) && sysItem.getDisplayNameCN().indexOf("测试版")==-1 && sysItem.getStrengthLevel()<=0)
				{
					jo.addProperty(sysItem.getId()+"", sysItem.getDisplayNameCN());
				}
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		if(jo.toString().length()>0){
			//System.out.println("{"+sb.toString().substring(1)+"}");
			out.write(jo.toString());
		}else{
			//System.out.println("Null data~");
			out.write("{}");
		}
	}

	/**
	 * 发送物品
	 * 
	 * @param 
	 * @author OuYangGuang
	 * */
	public void sendItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String playerName		=request.getParameter("playerName");
		String itemLevel 		=request.getParameter("itemLevel");	//, :
		Player player = getService.getPlayerByName(playerName);
		JsonObject jo = new JsonObject();
		if(null==playerName||"".equals(playerName)||null==itemLevel||"".equals(itemLevel)||player==null)
		{
			jo.addProperty("msg", "玩家不存在，或武器需要勾选!");
			response.getWriter().write(jo.toString());
			return;
		}
		itemLevel=itemLevel.substring(1);
		String[] itemLevelArr 	= itemLevel.split(",");
		try {
			
			for(String itemIdLevel:itemLevelArr){
				String[] item = itemIdLevel.split(":");
				//System.out.println(item[0]+":"+item[1]);
				int id = Integer.parseInt(item[0]);
				int level=0;
				SysItem sysitem=getService.getSysItemByItemId(id);
				int pItemId=createService.sendItem(sysitem,player,sysitem.getDefaultPayment(),Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				if(item[1]!=null && item[1].length()>0)
				{
					level=Integer.parseInt(item[1]);
					PlayerItem pi = getService.getPlayerItemById(player.getId(),pItemId);
					pi.setLevel(level); 
					for(int i=0;i<=pi.getLevel();i++){
						if (pi.isLevelChangeLevel()) {
							pi.openHole();
						}
					}
					// set is bind
					if (pi.getIsBind().equals(Constants.BOOLEAN_NO)) {
						pi.setIsBind(Constants.BOOLEAN_YES);
					}
					getService.playerItemDao.updatePlayerItem(pi);
					ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(), sysitem);
				}  
				
			}
			
			jo.addProperty("msg", "赠送成功，请注意查收!");
			
			response.getWriter().write(jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 名称查找物品,多个物品以,号分割
	 * 
	 * @param
	 * @author OuYangGuang
	 * */
	public void likeItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Writer out = response.getWriter();
		
		java.util.List<SysItem> sysItems = getService.getSysItemDao().getAllSystemItem();
		String serachStr = request.getParameter("serachStr");
		JsonObject jo = new JsonObject();
		serachStr=serachStr.trim();
		if(serachStr!=null && serachStr.length()>0 ){
			String[] serachStrs=null;
			if(serachStr.indexOf(",")>-1){
				serachStrs = serachStr.split(",");
			}else if(serachStr.indexOf("，")>-1){
				serachStrs = serachStr.split("，");
			}else{
				serachStrs=new String[]{serachStr};
			}
			if(serachStrs!=null && serachStrs.length>0){
				java.util.List<String> keyWords = new ArrayList<String>();
				keyWords.addAll(Arrays.asList(serachStrs));
				for(String kw : keyWords){
					if(kw.matches("^\\d+$")){
						SysItem value = getService.getSysItemDao().getAllSysItemMap().get(Integer.parseInt(kw));
						if(value==null)continue;
						jo.addProperty(value.getId()+"", value.getDisplayNameCN());
						if(keyWords.size()>1)
						{
							keyWords.remove(kw);
						}else{ 
							keyWords.clear(); 
							break;
						} 
					}
				}
				for(SysItem sysItem : sysItems){
//					if(keyWords.contains(String.valueOf(sysItem.getId())) || keyWords.contains(sysItem.getDisplayNameCN()!=null?sysItem.getDisplayNameCN():"")){
					if(keyWords.contains(sysItem.getDisplayNameCN()!=null?sysItem.getDisplayNameCN():"")){
						jo.addProperty(sysItem.getId()+"", sysItem.getDisplayNameCN());
					}
//					else{
//						for(String kw : keyWords){
//							if(sysItem.getDisplayNameCN().indexOf(kw)>-1){
//								jo.addProperty(sysItem.getId()+"", sysItem.getDisplayNameCN());
//							}
//						}
//					}
				}
			}
		}else{
			for(SysItem sysItem : sysItems){
				if(sysItem.getDisplayNameCN().indexOf(serachStr)>-1)
				{
					jo.addProperty(sysItem.getId()+"", sysItem.getDisplayNameCN());
				}
			}
	}
		
		if(jo.toString().length()>0){
			out.write(jo.toString());
		}
	}
	
}
