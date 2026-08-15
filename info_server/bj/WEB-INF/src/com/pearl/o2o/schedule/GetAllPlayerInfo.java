package com.pearl.o2o.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class GetAllPlayerInfo implements Runnable {

	public static Logger logger = LoggerFactory.getLogger(GetAllPlayerInfo.class);
	@Override
	public void run() {

		try{
			ServiceLocator.nosqlLogger.debug("Start get all player "+CommonUtil.simpleDateFormat.format(new Date()));
			List<Player> playerList = ServiceLocator.getService.getPlayerAll();
			for(Player p:playerList){
				ServiceLocator.getService.getPlayerById(p.getId());
			}
			while(true){
				ServiceLocator.nosqlLogger.debug("Start get random player "+CommonUtil.simpleDateFormat.format(new Date()));
				for(int i = 0;i<Constants.PLAYER_RANDOM_SIZE;i++){
					int index = (int)(Math.random()*playerList.size());
					ServiceLocator.getService.getPlayerById(playerList.get(index).getId());
				}
				Thread.sleep(Constants.PLAYER_RANDOM_TIME);
			}
			
			
		}catch (Exception e){

		}finally{

		}
	}
}
