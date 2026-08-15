package com.pearl.o2o.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.qq.pojo.Quser;

/**
 * 测试版功能，获得openid和appid
 * @param
 * @author OuYangGuang
 */
public class QqSelectServlet extends HttpServlet {
    static final long serialVersionUID = 0L;
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
        	//http://localhost:8080/webaa/cindex?openid=ewqeqwc
            //http://localhost:8080/webaa/cindex?openid=q&appid=1
            Map<String,Object> map = request.getParameterMap();
            String str="";
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                String key = entry.getKey();
                str+= key;
                str+= " "+request.getParameter(key)+"\n";
            }
            //解析保存成qq_user
            Quser quser = new Quser();
            quser.setOpenid(request.getParameter("openid"));
            quser.setOpenkey(request.getParameter("openkey"));
            quser.setPfkey(request.getParameter("pfkey"));
			//存入
			
            response.getWriter().write(str);
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
