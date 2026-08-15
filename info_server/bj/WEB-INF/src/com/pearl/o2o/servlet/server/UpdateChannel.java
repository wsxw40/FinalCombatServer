package com.pearl.o2o.servlet.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;


public class UpdateChannel extends BaseServerServlet {
	private static final long serialVersionUID = -1804268538576026176L;
	static Logger log = LoggerFactory.getLogger(UpdateChannel.class.getName());

	public UpdateChannel() {
	}
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		/*try{
			int size=r.readInt();
			for(int i=0;i<size;i++){
				int channelId=r.readInt();
				int count=r.readInt();
				int max=r.readInt();
				updateService.updateChannel(channelId, count,max);
			}
			return Constants.EMPTY_BYTE_ARRAY;
		}catch(Exception e){
			log.error("Error in UpdateLocation: " + e);
			throw e;
		}*/
		return null;
	};
	
}
