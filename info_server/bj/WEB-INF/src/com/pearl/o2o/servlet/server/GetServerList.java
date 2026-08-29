package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;


public class GetServerList extends BaseServerServlet {
	private static final long serialVersionUID = -7772792623360674823L;
	static Logger log = LoggerFactory.getLogger(GetServerList.class.getName());	
	
	public GetServerList() {
		super();
	}
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			List<Server> serverList=getService.getServersList();
			out.write(BinaryUtil.toByta(serverList.size()));
			if(serverList!=null){
				for(Server s:serverList){
					List<Channel> channelList=getService.getChannelsList(s.getId());
					int max=0;
					for(Channel c:channelList){
						max+=c.getMaxOnline();
					}
					s.setMaxOnline(max);
				}
				for(Server s:serverList){
					List<Channel> channelList=getService.getChannelsList(s.getId());
					out.write(BinaryUtil.toByta(s.getId()));
					out.write(BinaryUtil.toByta(s.getName()));
					out.write(BinaryUtil.toByta(s.getMin()));
					out.write(BinaryUtil.toByta(s.getMax()));
//					out.write(BinaryUtil.toByta(s.getMaxTeam()));
//					out.write(BinaryUtil.toByta(s.getMinTeam()));
					out.write(BinaryUtil.toByta(s.getMaxOnline()));
					out.write(BinaryUtil.toByta(s.getIsNew().byteValue()));
					out.write(BinaryUtil.toByta(s.getMinFightNum()));
					out.write(BinaryUtil.toByta(s.getGameType()));
					//zlm2015-10-9-匹配-服务器-开始 
					out.write(BinaryUtil.toByta(s.getServerType()));
					//zlm2015-10-9-匹配-服务器-结束
					out.write(BinaryUtil.toByta(channelList.size()));
					for(Channel c:channelList){
						out.write(BinaryUtil.toByta(c.getChannelId()));
						out.write(BinaryUtil.toByta(c.getName()));
						out.write(BinaryUtil.toByta(c.getMin()));
						out.write(BinaryUtil.toByta(c.getMax()));
						out.write(BinaryUtil.toByta(c.getMinTeam()));
						out.write(BinaryUtil.toByta(c.getMaxTeam()));
						out.write(BinaryUtil.toByta(c.getMaxOnline()));
						out.write(BinaryUtil.toByta(c.getIsTcp().byteValue()));
					}
				}
			}
			return out.toByteArray();
		}catch (Exception e) {
			log.warn("Error in GetServerList: ", e);
			throw e;
		}
	}
	
}
