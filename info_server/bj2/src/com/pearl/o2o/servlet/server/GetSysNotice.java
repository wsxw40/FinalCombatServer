package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

public class GetSysNotice extends BaseServerServlet {

	private static final long serialVersionUID = -4450590859947913662L;
	static Logger log = LoggerFactory.getLogger(GetSysNotice.class.getName());
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			List<SysNotice> sysNoticeList=getService.getSysNotice();
			out.write(BinaryUtil.toByta(sysNoticeList.size()));
			for(SysNotice sysN : sysNoticeList){
				out.write(BinaryUtil.toByta(sysN.getId()));
				out.write(BinaryUtil.toByta(sysN.getType().byteValue()));
				out.write(BinaryUtil.toByta(sysN.getContent()));
				if(sysN.getType()==2){
					out.write(BinaryUtil.toByta(sysN.getStartTime()));
					out.write(BinaryUtil.toByta(sysN.getEndTime()));
					out.write(BinaryUtil.toByta(sysN.getNoticeTime()));
				} else if(sysN.getType()==1){
					out.write(BinaryUtil.toByta(sysN.getStartTime()));
				}
			}
			return out.toByteArray();
		}catch (Exception e) {
			log.warn("Error in GetServerList: ", e);
			throw e;
		}
	}
}
