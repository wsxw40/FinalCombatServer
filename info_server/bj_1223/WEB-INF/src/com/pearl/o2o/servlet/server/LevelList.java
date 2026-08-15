package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

public class LevelList extends BaseServerServlet{
	private static final long serialVersionUID = 8114130304798189701L;
	
	
	protected  byte[] innerService(BinaryReader in) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			List<LevelInfo> pojos = getService.getLevelListUseCache();
			out.write(BinaryUtil.toByta(pojos.size()));
			for(LevelInfo pojo : pojos){
				out.write(BinaryUtil.toByta(pojo.getId()));
				out.write(BinaryUtil.toByta(pojo.getName()));
				out.write(BinaryUtil.toByta((byte)pojo.getType()));
				out.write(BinaryUtil.toByta(pojo.getDisplayName()));
				out.write(BinaryUtil.toByta(pojo.getDescription()));
				out.write(BinaryUtil.toByta((byte)pojo.getIsVip()));
				out.write(BinaryUtil.toByta((byte)pojo.getIsNew()));
				out.write(BinaryUtil.toByta(pojo.getNum4Team()));
			}
			return out.toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
			out.write(BinaryUtil.toByta("load level list fail"));
			return out.toByteArray();
		}
	}

}
