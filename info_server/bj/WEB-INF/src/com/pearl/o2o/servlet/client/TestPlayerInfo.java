package com.pearl.o2o.servlet.client;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.PlayerBuffDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerItemDao;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class TestPlayerInfo extends BaseClientServlet {
	private static final long serialVersionUID = 5117201445216191162L;
	static Logger log = LoggerFactory.getLogger(TestPlayerInfo.class.getName());
	private static int increment = 0;

	private static final String[] paramNames = { "pid", "count" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]) + increment;
			int count = Integer.parseInt(args[1]);
			for (int i = 0; i < count; i++) {

				increment++;
				System.out.println(playerId);
				Player p = getService.getPlayerById(playerId);
				getService.getPlayerDao().updatePlayerInCache(p);
				mcc.delete(CacheUtil.oPlayer(p.getId()));
				mcc.delete(CacheUtil.sPlayer(p.getId()));

				PlayerItemDao playerItemDao = getService.getPlayerItemDao();
				List<PlayerItem> itemList = playerItemDao.getPlayerItemList(playerId);
				updateService.updatePlayerItem(itemList.get(new Random().nextInt(itemList.size())));

				PlayerBuffDao playerBuffDao = getService.getPlayerBuffDao();
				List<PlayerBuff> buffList = playerBuffDao.getPlayerBuffListByPlayerId(playerId);

				for (PlayerBuff playerBuff : buffList) {
					playerBuffDao.updatePlayerBuff(playerBuff);
				}
				List<PlayerPack> packList = getService.getPlayerPackList(playerId);
				for (PlayerPack playerpack : packList) {
					getService.getPlayerPackDao().update(playerpack);
				}
			}
			return Converter.warn(ExceptionMessage.ERROR_MESSAGE_ALL);
		} catch (Exception e) {
			log.warn("Exception in GetPlayerItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]) + increment);
	}

}
