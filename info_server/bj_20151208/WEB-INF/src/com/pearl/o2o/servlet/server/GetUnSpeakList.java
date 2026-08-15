package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.UnSpeaker;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

public class GetUnSpeakList extends BaseServerServlet {

	private static final long serialVersionUID = 5089293981299897027L;
	static Logger log = LoggerFactory.getLogger(GetSysNotice.class.getName());
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			List<UnSpeaker> unSpeakerList=getService.getAllUnSpeakerList();
			out.write(BinaryUtil.toByta(unSpeakerList.size()));
			for(UnSpeaker temp : unSpeakerList){
				out.write(BinaryUtil.toByta(temp.getPlayerId()));
			}
			return out.toByteArray();
		}catch (Exception e) {
			log.warn("Error in GetUnSpeakList: ", e);
			throw e;
		}
	}
}

