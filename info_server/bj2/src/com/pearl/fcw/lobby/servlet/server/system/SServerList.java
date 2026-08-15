package com.pearl.fcw.lobby.servlet.server.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WChannel;
import com.pearl.fcw.gm.pojo.WServer;
import com.pearl.fcw.gm.service.WServerService;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 代理服务器获取数据库的服务器以及渠道数据
 */
@Service("fcw_s_server_list")
public class SServerList extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = 6100289131114393079L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private SServerList fcw_s_server_list;
	@Resource
	private WServerService wServerService;

	@Override
	protected byte[] innerService(BinaryReader in) throws IOException, Exception {
		try {
			return fcw_s_server_list.server(in);
		} catch (Exception e) {
			logger.error("fcw_s_server_list has error : ", e);
		}
		return Servletable.super.server(in);
	}

	@Override
	public byte[] server(BinaryReader reader) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<WServer> wSysLevelList = wServerService.findList(null);
		out.write(BinaryUtil.toByta(wSysLevelList.size()));
		for (WServer wServer : wSysLevelList) {
			List<WChannel> wChannelList = wServerService.findChannelList(null);
			out.write(BinaryUtil.toByta(wChannelList.size()));
			int maxOnline = 0;//server的最大在线数量
			for (WChannel wChannel : wChannelList) {
				maxOnline += wChannel.getMaxOnline();
			}

			//服务器内容
			out.write(BinaryUtil.toBytas(wServer.getId(), wServer.getName(), wServer.getMinLevel(), wServer.getMaxLevel(), maxOnline, wServer.getIsNew().byteValue(), wServer.getMinFightnum(),
					wServer.getGameType()));
			//渠道内容
			for (WChannel wChannel : wChannelList) {
				out.write(BinaryUtil.toBytas(wChannel.getChannelId(), wChannel.getChannelId(), wChannel.getName(), wChannel.getMinLevel(), wChannel.getMaxLevel(), wChannel.getMinTeamLevel(),
						wChannel.getMaxTeamLevel(), wChannel.getMaxOnline(), wChannel.getIsTcp().byteValue()));
			}
		}
		return out.toByteArray();
	}
}
