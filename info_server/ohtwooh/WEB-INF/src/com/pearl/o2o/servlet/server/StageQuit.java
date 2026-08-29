package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.Receiver;

@SuppressWarnings("serial")
public class StageQuit extends BaseServerServlet{
	private static int STAGECLEAR_EXPR = 600;
	private static Logger logger = Logger.getLogger(StageQuit.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		InputStream is = req.getInputStream();
		
		byte[] buffer = new byte[64];
		Receiver r = new Receiver(is, buffer);
		
		GeneralStageClearInfo stageClearInfo = new GeneralStageClearInfo();
		int cid = r.readInt();//indicate which player has just quit

		int rid =  r.readInt();
		stageClearInfo.setRid(rid);
		
		int type = r.readByte();
		stageClearInfo.setType(type);
		int winner = r.readByte();
		stageClearInfo.setWinner(winner);
		int terrorist_score = r.readInt();
		stageClearInfo.setTerrorist_score(terrorist_score);
		int	police_score = r.readInt();
		stageClearInfo.setPolice_score(police_score);
		
		String 	ace = r.readString();
		stageClearInfo.setAce(ace);
		String 	mvp = r.readString();
		stageClearInfo.setMvp(mvp);
		String	last_shot = r.readString();
		stageClearInfo.setLast_shot(last_shot);
		
		//player list
		int playerArrayLength = r.readInt();
		List<StageClearPlayerInfo> playerInfoList = new ArrayList<StageClearPlayerInfo>();
		stageClearInfo.setStageClearPlayerInfo(playerInfoList);
		
		for (int i=0; i<playerArrayLength; i++) {
			StageClearPlayerInfo playerInfo = new StageClearPlayerInfo();
			
			int id = r.readInt();
			playerInfo.setId(id);
			
			String characterName = r.readString();
			playerInfo.setName(characterName);
			
			byte side = r.readByte();
			playerInfo.setSide(side);
			
			int onlineMinutes = r.readInt();
			playerInfo.setOnlineMinutes(onlineMinutes);
			
			int offlineMinutes = r.readInt();
			playerInfo.setOfflineMinutes(offlineMinutes);
			
			int score = r.readInt();
			playerInfo.setScore(score);
			
			int hitPoint = r.readInt();
			playerInfo.setHit_point(hitPoint);
			
			short kill = r.readShort();
			playerInfo.setKill(kill);
			short dead = r.readShort();
			playerInfo.setDead(dead);
			
			short head_shot = r.readShort();
			playerInfo.setHead_shot(head_shot);
			
			short grenade_kill = r.readShort();
			playerInfo.setGrenade_kill(grenade_kill);
			short knife_kill = r.readShort();
			playerInfo.setKnife_kill(knife_kill);
			short fun_drop_kill = r.readShort();
			playerInfo.setFun_drop_kill(fun_drop_kill);
			
			short bio_infect = r.readShort();
			playerInfo.setBio_infect(bio_infect);
			
			short bio_infected = r.readShort();
			playerInfo.setBio_infected(bio_infected);
			
			short bio_as_zombie_win = r.readShort();
			playerInfo.setBio_as_zombie_win(bio_as_zombie_win);
			short bio_as_soldier_win = r.readShort();
			playerInfo.setBio_as_soldier_win(bio_as_soldier_win);
			short bio_level = r.readShort();
			playerInfo.setBio_level(bio_level);
			short bio_max_infect = r.readShort();
			playerInfo.setBio_max_infect(bio_max_infect);
			
			short flag_capture = r.readShort();
			playerInfo.setFlag_capture(flag_capture);
			short flag_return = r.readShort();
			playerInfo.setFlag_return(flag_return);
			short flag_succeed = r.readShort();
			playerInfo.setFlag_succeed(flag_succeed);
			
			short blast_plant = r.readShort();
			playerInfo.setBlast_plant(blast_plant);
			short blast_disable = r.readShort();
			playerInfo.setBlast_disable(blast_disable);
			short blast_succeed = r.readShort();
			playerInfo.setBlast_succeed(blast_succeed);
			
			short fun_grenade_kill = r.readShort();
			playerInfo.setFun_grenade_kill(fun_grenade_kill);
			
			playerInfoList.add(playerInfo);
		}
		try{
			StageClearPlayerInfo toUpdate = null;
			//update player
			//meanwhile, some information of player will be set to playerInfo
			List<StageClearPlayerInfo> updateList = new ArrayList<StageClearPlayerInfo>();
			for (StageClearPlayerInfo info : stageClearInfo.getStageClearPlayerInfo()) {
				if (cid == info.getId()) {// find the current player to update
					toUpdate = info;
				}
			}
			updateList.add(toUpdate);//only update this player
			updateService.updatePlayerStageClear(updateList, type, winner);
			String result = Converter.stageClear(stageClearInfo);
			String key = CacheUtil.sStageQuit(stageClearInfo.getRid(),cid);
			logger.debug(result);
			mcc.set(key,STAGECLEAR_EXPR, result);
		}catch (Exception e){
			logger.error("error happend when processing the stage clear. Exception is " + e);
		}
	}
}
