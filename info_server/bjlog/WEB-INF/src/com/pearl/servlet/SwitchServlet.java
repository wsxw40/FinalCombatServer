package com.pearl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.Switch;

public class SwitchServlet extends HttpServlet {
	private static final long serialVersionUID = 6119751670304424903L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		String switchName = req.getParameter("name");
		String action = req.getParameter("action");
		
		List<Switch> switchs = Switch.getSwitchList();
		
		
		if (switchName != null && action != null) {
			Switch zwitch = null;
			for (Switch s : switchs){
				if (s.getName().equals(switchName)) {
					zwitch = s;
					break;	
				}
			}
			
			if (zwitch == null) {
				res.getWriter().write("switch is null");
			}else {
				if ("on".equals(action) ){
					zwitch.open();
				}else if ("off".equals(action)) {
					zwitch.close();
				}else {
					res.getWriter().write("action should be on/off");
				}
			}
		}
		
		for (Switch s : switchs) {
			res.getWriter().write(s.getName() + " " + s.getIsOn() + "\n");
		}
	}
	
}
