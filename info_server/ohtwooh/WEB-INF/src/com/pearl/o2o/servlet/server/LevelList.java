package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.LevelInfoPojo;
import com.pearl.o2o.utils.BinaryOut;

public class LevelList extends BaseServerServlet{
	private static final long serialVersionUID = 8114130304798189701L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream os = res.getOutputStream();
		BinaryOut bOut = new  BinaryOut(os);
		try{
			List<LevelInfoPojo> pojos = getService.getLevelList();
			bOut.writeInt(pojos.size());
			for(LevelInfoPojo pojo : pojos){
				bOut.writeInt(pojo.getId());
				bOut.writeString(pojo.getName());
				bOut.writeByte(pojo.getType());
			}
			bOut.flush();
		}catch (Exception e) {
			os.write("load level list fail".getBytes("GBK"));
		}
	}
}
