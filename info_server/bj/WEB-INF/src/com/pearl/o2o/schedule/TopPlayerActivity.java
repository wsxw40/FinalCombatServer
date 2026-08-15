package com.pearl.o2o.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class TopPlayerActivity implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(TopPlayerActivity.class);

	@Override
	public void run() {
		String key = NosqlKeyUtil.topByType("kWeek");
		int start = 0;
		int end = 0;
		try {
			List<SysActivity> activityList = ServiceLocator.getService.getAvailableActivities();
			for (SysActivity sa : activityList) {
				if (Constants.ACTION_ACTIVITY.TOP_PLAYER_ACTIVITY.getValue() == sa.getAction()&&sa.getEndTime().after(new Date())) {
					start = sa.getValue() - 1;
					end = sa.getTargetNum() - 1;
					String title = CommonMsg.GIFT_EMAIL_SYS;
					String content = CommonUtil.messageFormatI18N(CommonMsg.FINISH_ACTIVITY, sa.getName());
					List<String> rankEntry = ServiceLocator.nosqlService.getNosql().lrange(key, start, end);
					for (String info : rankEntry) {
						int id = Integer.parseInt(info.split(",")[11]);
						Player player = ServiceLocator.getService.getPlayerById(id);
						ServiceLocator.createService.awardActivity(player, sa.getSysItem(), null, sa);
						logger.debug("name=" + player.getName() + " id=" + player.getId() + " get topWeek activity price.");
						ServiceLocator.createService.sendSystemMail(player, title, content, sa.getSysItem().getId() + "",sa.getUnit()+"",sa.getUnitType()+"");
//						List<PlayerActivity> paList = ServiceLocator.getService.getPlayerActivityList(player.getId());
//						for (PlayerActivity pa : paList) {
//							if (sa.getId() == pa.getSysActivityId()) {
//								pa.setStatus(1);
//								pa.setAward(1);
//								ServiceLocator.updateService.updatePlayerActivity(pa);
//								ServiceLocator.getService.getMcc().delete(CacheUtil.oPlayerActivityList(player.getId()));
//							}
//						}
					}
				}
			}
		} catch (Exception e) {
			logger.warn("error in send activity award to top players",e);
		}
	}

}
