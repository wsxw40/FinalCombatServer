package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.*;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetItemModePreview extends BaseClientServlet {
	private static final long serialVersionUID = 5707425177857498182L;
	static Logger log = Logger.getLogger(CreateItemMod.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result = "";
		try {
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int weaponId = StringUtil.toInt(req.getParameter("pid"));
			String array = req.getParameter("array");
			String part[] = null;
			if (array != null && !"".equals(array.trim())) {
				part = array.split(",");
			}
			
			PlayerItem item = getService.getPlayerItemByItemId(userId,
					playerId, Constants.DEFAULT_WEAPON_TYPE, weaponId);
			SysItem sysItem=getService.getSysItemByItemId(userId,playerId,item.getType(), item.getSubType(), item.getItemId());
			if (part != null && part.length != 0) {
				List<SysItem> partList = new ArrayList<SysItem>();
				for (int i = 0; i < part.length; i++) {
					String str = part[i + 1];
					PlayerItem playerItem = getService.getPlayerItemByItemId(userId, playerId, Constants.DEFAULT_PART_TYPE,
							StringUtil.toInt(str));
					
					if (playerItem != null) {
						SysItem syspart=getService.getSysItemByItemId(userId,playerId,playerItem.getType(), playerItem.getSubType(), playerItem.getItemId());
						partList.add(syspart);
					}
					i++;
				}
				if (partList.size() > 0) {
					sysItem.setParts(partList);
					sysItem.putOnPart();
					item.setResource(sysItem.getResource());
					item.setDamange(sysItem.getDamange());
					item.setStopPpower(sysItem.getStopPpower());
					item.setRecoil(sysItem.getRecoil());
					item.setAccuracy(sysItem.getAccuracy());
					item.setShootSpeed(sysItem.getShootSpeed());
					item.setWAmmoOneClip(sysItem.getWAmmoOneClip());
					item.setWAmmoCount(sysItem.getWAmmoCount());
				}
			} else {
				item.setResource(sysItem.getResource());
				item.setDamange(sysItem.getDamange());
				item.setStopPpower(sysItem.getStopPpower());
				item.setRecoil(sysItem.getRecoil());
				item.setAccuracy(sysItem.getAccuracy());
				item.setShootSpeed(sysItem.getShootSpeed());
				item.setWAmmoOneClip(sysItem.getWAmmoOneClip());
				item.setWAmmoCount(sysItem.getWAmmoCount());
			}
			result = Converter.previewItemMode(item);
			out.write(result);

		} catch (Exception e) {
			log.error("Exception in GetItemModePreview",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}
