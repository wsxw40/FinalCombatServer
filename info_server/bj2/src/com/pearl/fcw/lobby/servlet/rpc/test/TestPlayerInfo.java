package com.pearl.fcw.lobby.servlet.rpc.test;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

@Service("c_test_player_info")
public class TestPlayerInfo extends BaseClientServlet implements Servletable {
	private static final long serialVersionUID = 3680450877065468945L;
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final String[] paramNames = { "uid", "pid", "count" };
	@Resource
	private TestPlayerInfo c_test_player_info;
	@Resource
	private WPlayerService wPlayerService;

	@Override
	protected String innerService(String... args) {
		try {
			c_test_player_info.rpc(args);
			return Converter.warn(ExceptionMessage.ERROR_MESSAGE_ALL);
		} catch (Exception e) {
			log.warn("c_test_player_info errors : ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[1]);
		int count = Integer.parseInt(args[2]);
		for (int i = 0; i < count; i++) {
			ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
			pwPlayer.gPoint().set(pwPlayer.gPoint().get());

			wPlayerService.findProxyWPlayerItemMap(playerId).values().stream().findAny().ifPresent(p -> {
				p.isDefault().set(p.isDefault().get());
			});
			wPlayerService.findProxyWPlayerBuffMap(playerId).values().stream().findAny().ifPresent(p -> {
				p.playerItemId().set(p.playerItemId().get());
			});
			wPlayerService.findProxyWPlayerPackMap(playerId).values().stream().findAny().ifPresent(p -> {
				p.packId().set(p.packId().get());
			});
		}
		return "end test";
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}

	@Override
	public String getLockedKey(String... args) {
		return args[1];
	}

}
