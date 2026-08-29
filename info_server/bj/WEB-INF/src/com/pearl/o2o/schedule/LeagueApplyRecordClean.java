package com.pearl.o2o.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.LeagueUtil;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛比赛名单跟报名的记录清理
 * @author zhaolianming
 */
public class LeagueApplyRecordClean implements Runnable {
	public static Logger log = LoggerFactory.getLogger(LeagueApplyRecordClean.class);
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();

	@Override
	public void run() {
		try {
			LeagueUtil.leagueRecordClean();
		} catch (Exception e) {
			log.warn("Error in " + LeagueApplyRecordClean.class.getName() + ": ", e);
			e.printStackTrace();
		}
	}
}
