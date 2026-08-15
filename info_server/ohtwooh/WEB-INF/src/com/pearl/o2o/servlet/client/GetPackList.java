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
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPackList extends BaseClientServlet {
	static Logger log = Logger.getLogger(GetPackList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result;
		try{
			//int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int packId=StringUtil.toInt(req.getParameter("id"));
			int type=StringUtil.toInt(req.getParameter("type"));
			String cName = req.getParameter("name");
			
			if (!StringUtil.isEmptyString(cName) ){
				Player p = getService.getPlayerByName(cName);
				if (p == null){
					out.write(Converter.error(ExceptionMessage.PALERY_NOT_EXIST));
					return ;
				}
				playerId = p.getId();
			}
			
			
			
			String strKey;
			//Player player=getService.getPlayerById(playerId);
			if(type==Constants.DEFAULT_WEAPON_TYPE){
				 strKey=CacheUtil.sWeaponPack(playerId, packId);
			}else{
				 strKey=CacheUtil.sCostumePack(playerId, Constants.NUM_ONE,packId);
			}
			List<PlayerItem> packList=new ArrayList<PlayerItem>();
			result= mcc.get(strKey);
			if(result==null){
				if(type==Constants.DEFAULT_WEAPON_TYPE){
					packList=getService.getWeaponPackList(playerId,packId);
				}else{
					packList=getService.getCostumePackList(playerId, Constants.NUM_ONE, packId);
				}
				if (packList != null) {
					for(PlayerItem playerItem:packList){
						if(playerItem.getId()!=null&&playerItem.getUnitType()==Constants.DEFAULT_TIMEBASE_TYPE){
							playerItem.initResource();
						}
					}
				}
				result=Converter.playerPackList(packList);
				mcc.set(strKey, Constants.CACHE_ITEM_TIMEOUT, result);
			}
			out.write(result);
		}
		catch (Exception e) {
			log.error("Exception happen in GetPackList",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}

}
