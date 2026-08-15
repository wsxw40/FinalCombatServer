package com.pearl.o2o.servlet.gm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.utils.ServiceLocator;

/**
 * 子GM操作，该Class只做指向操作
 * 
 * @author OuYangGuang
 * */
public class IndexPointerServlet extends HttpServlet {
	static final long serialVersionUID=1L; 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String namePwd = (String)request.getSession().getAttribute("namePwd");
		if("userLoginSuccess".equals(namePwd)){
			if(null!=action && !"".equals(action)){
				request.getRequestDispatcher("/WEB-INF/html/"+action+".html").forward(request, response);
			}else{
				request.getRequestDispatcher("/WEB-INF/html/index.html").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("/WEB-INF/html/login.html").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("Username");
		String pwd = request.getParameter("Password");
		String namePwd = (String)request.getSession().getAttribute("namePwd");
		if("userLoginSuccess".equals(namePwd)){
		}else if(ServiceLocator.flexGetService.getLoginGmUser(name,pwd).size()>0){
			request.getSession().setAttribute("namePwd", "userLoginSuccess");
		}else{
			request.setCharacterEncoding("UTF8");
			response.setCharacterEncoding("UTF8");
			response.getWriter().write("<script>alert('Username or Password error~');window.history.back(-1)</script>");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/html/index.html").forward(request, response);
	}
	
	
}
