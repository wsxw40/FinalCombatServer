package com.pearl.fcw.info.lobby.servlet.server;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.service.GetService;
import com.pearl.fcw.info.lobby.service.PlayerService;
import com.pearl.fcw.info.lobby.servlet.BaseServlet;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;
import com.pearl.fcw.info.lobby.utils.Constants;
import com.pearl.fcw.info.lobby.utils.ServiceLocator;

@Service("s_end_new")
public class SEndNewServlet extends BaseServlet {
	private static final long serialVersionUID = 7553992516814453244L;
	static Logger logger = LoggerFactory.getLogger(SEndNewServlet.class
			.getName());

	@Resource
	private GetService getService;

	@Resource
	private PlayerService playerService;

	@Override
	public byte[] execute(BinaryChannelBuffer r) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			int playerId = r.readInt();
			int entertTime = r.readInt();
			int quitTime = r.readInt();
			int characterId = r.readInt();
			int isClear = r.readInt();
			Player player = playerService.get(playerId);
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				ServiceLocator.nosqlService.addXunleiLog("1.7"
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ 1
						+ Constants.XUNLEI_LOG_DELIMITER
						+ CommonUtil.simpleDateFormat.format(new Date(
								entertTime * 1000l)));

				if (isClear != 0) {
					ServiceLocator.nosqlService.addXunleiLog("1.7"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER
							+ 2
							+ Constants.XUNLEI_LOG_DELIMITER
							+ CommonUtil.simpleDateFormat.format(new Date(
									quitTime * 1000l)));
				} else {
					ServiceLocator.nosqlService.addXunleiLog("1.7"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER
							+ 3
							+ Constants.XUNLEI_LOG_DELIMITER
							+ CommonUtil.simpleDateFormat.format(new Date(
									quitTime * 1000l)));
				}
				ServiceLocator.nosqlService.addXunleiLog("1.8"
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ characterId
						+ Constants.XUNLEI_LOG_DELIMITER
						+ CommonUtil.simpleDateFormat.format(new Date(
								entertTime * 1000l)));
			}
			if (player.getIsNew() != 1 && isClear != 0) {
				player.setIsNew(1);
				// FIXME
				// updateService.updatePlayerInfo(player);
				// updateService.updatePlayerAchievementNotInStageClear(player,
				// Constants.ACTION.NEWPLAYER.getValue(), 1);
				// //成长任务：完成训练关
				// updateService.updatePlayerGrowthMission(player,GrowthMissionType.FINISH_NEW_PLAYER);
			}
			return out.toByteArray();
		} catch (Exception e) {
			logger.warn("Error in GetPlayer: ", e);
			throw e;
		}
	}
}
