package com.pearl.o2o.servlet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.SysMissionDao;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.ServiceLocator;
public class ResetSysItem extends HttpServlet {
	private static final long serialVersionUID = -3393897598667208451L;
	static Logger log = LoggerFactory.getLogger(ResetSysItem.class.getName());
	@SuppressWarnings("static-access")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		try {
			String ips=req.getParameter("info");
			StringBuffer sb = new StringBuffer();
			if(ips!=null){
				String[] ip=ips.split(",");
				for(String i:ip){
						String urlStr="http://"+i+":8080/bj/gm/ResetSysItem";
						URL url=new URL(urlStr);
						URLConnection connection=url.openConnection();
						InputStreamReader r = new InputStreamReader(connection.getInputStream());
						BufferedReader rd = new BufferedReader(r);
						String line;
						while ((line = rd.readLine()) != null) {
							sb.append(i+"           ").append(line);
						}
						sb.append("\n");
						rd.close();
				}
			}
			Map<Integer, SysItem> allSysitem=new HashMap<Integer, SysItem>();
			ServiceLocator.getService.getSysItemDao().setAllSysitem(allSysitem);
//			SysAchievementDao.joinAchievementGift();//重置成就奖品
			SysMissionDao.joinMissionGift();//重置任务奖品
			res.getWriter().write(sb+" reset sysitem success");
		} catch (Exception e) {
			ServiceLocator.nosqlLogger.warn("run syncache db task happened error",e);
		}
	}
}