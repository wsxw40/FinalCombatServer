package com.pearl.o2o.service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.service.DataOhteServlet.DataFactory;
import com.pearl.o2o.servlet.gm.BaseGMServlet;
import com.pearl.o2o.utils.ServiceLocator;

public class LevelOhteServlet extends BaseGMServlet  {
	static final long serialVersionUID=1L;
	
	/**
	 * */
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Writer out = response.getWriter();
		DataFactory<LevelInfo> j = (new DataOhteServlet()).new DataFactory<LevelInfo>();
		
		String xyzPoint		= request.getParameter("xyzpoint");
		String id 			= request.getParameter("tableName");
		String type 		= request.getParameter("type");
		String name 		= request.getParameter("name");
		String value 		= request.getParameter("value");
		String random 		= request.getParameter("random");
		String skillTime 	= request.getParameter("skillTime");
		String type1		= request.getParameter("type1");
		String zy			= request.getParameter("zy");
		try {
			if(!"userLoginSuccess".equals(request.getSession().getAttribute("namePwd"))){
				response.getWriter().write("503");
				return;
			}
			List<LevelInfo> list=new ArrayList<LevelInfo>();
			
			if((type1==null || type1.length()<=0) && (null!=id&&!"".equals(id)))
			{
				list.add(ServiceLocator.getService.getLevelInfo(Integer.parseInt(id)));
				out.write(j.conver(list));
				return;
			}else if(null!=xyzPoint&&!"".equals(xyzPoint)){
				StringBuffer ws=new StringBuffer();
				String[] ps = xyzPoint.split("\n"); 
				for(String xyzArray : ps){
					if(xyzArray!=null && xyzArray.length()>=6){
						String zStr = xyzArray.substring(xyzArray.indexOf(",")+1);
						String zStr2 = zStr.substring(0,zStr.indexOf(","));
						
						float z = Float.parseFloat(zStr2);
						float zyd = Float.parseFloat(zy);
						ws.append(xyzArray.substring(0,xyzArray.indexOf(",")+1).trim() + String.valueOf(z+zyd) + xyzArray.substring(xyzArray.lastIndexOf(",")).trim());
						ws.append(",");
						ws.append(type);
						ws.append(",");
						ws.append(name);
						ws.append(",");
						ws.append(value);
						ws.append(",");
						ws.append(random);
						ws.append(",");
						ws.append(skillTime);
						ws.append(";");
						ServiceLocator.fileLog.info(xyzArray);
					}
				}
				
				if(type1!=null && type1.equals("2")){
					LevelInfo level = ServiceLocator.getService.getLevelInfo(Integer.parseInt(id));
					level.setSupplies(level.getSupplies() + ws.toString());
					ServiceLocator.getService.sysLevelDao.updateLevel(level);
				}else{
					LevelInfo level = ServiceLocator.getService.getLevelInfo(Integer.parseInt(id));
					level.setSupplies(ws.toString());
					ServiceLocator.getService.sysLevelDao.updateLevel(level);
				}
				JsonObject jo=new JsonObject();
				jo.addProperty("msg", "更新成功,请手动再次打开该页面以确认!");
				jo.addProperty("con", ws.toString());
				out.write(jo.toString());
			}else
			{
				list = ServiceLocator.getService.getLevelListUseCache();
				Collections.sort(list,new Comparator<LevelInfo>(){
					@Override
					public int compare(LevelInfo o1, LevelInfo o2) {
						return o1.getId()>o2.getId()?1:o2.getId()>o1.getId()?-1:0;
					}
				});
				out.write(j.conver(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}
}
