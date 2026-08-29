package com.pearl.fcw.info.core.network;

import io.netty.handler.timeout.TimeoutException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.info.Bootstrap;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.pojo.PlayerItem;
import com.pearl.fcw.info.lobby.pojo.SysItem;
import com.pearl.fcw.info.lobby.pojo.Team;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.Constants;
import com.pearl.fcw.info.lobby.utils.ServiceLocator;
import com.pearl.fcw.info.lobby.utils.StringUtil;

@Service
public class SocketClientNew {
	private static Logger logger = LoggerFactory.getLogger(SocketClientNew.class);

	
	private Server server = Bootstrap.fcwServer;
	/**
	 * this method shall be called everytime the gp/cr/level/team/exp was changed 
	 * @param player
	 * @param newTeamName
	 * @param newLevel
	 * @throws Exception 
	 * @throws TimeoutException 
	 * @throws IOExceptions
	 */
	public void updateCharacterInfo(Player player, String newTeamName, int newLevel) throws TimeoutException, Exception{
		boolean isPlayerCheckToday =ServiceLocator.nosqlService.isPlayerCheck(player.getId(),  CommonUtil.dateFormatDate.format(new Date()));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	
		out.write((byte)ServerMessage.SM_UpdateCharacterInfo.ordinal());
		out.write(BinaryUtil.toByta(player.getName()));
		
		out.write(BinaryUtil.toByta(player.getId()));
		out.write(BinaryUtil.toByta(newLevel));
		out.write(BinaryUtil.toByta(player.getExp()));
		out.write(BinaryUtil.toByta(player.getIsVip().byteValue()));
		List<PlayerItem> buffList=player.getBuffList();
		int flag=0;
//		logger.fatal("updateCharacterInfo buffList.size()="+buffList.size()+" player="+player.getId());
		if(buffList!=null){
			for(PlayerItem pi:buffList){
				try{
					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					si.init();
					if(Constants.DEFAULT_ITEM_TYPE == si.getType() && pi.getId()!=0&&si.getIId()==1&&si.getIBuffId()==Constants.DEFAULT_CARD_BUFF_ID){
						flag=StringUtil.toInt(si.getIValue());
						break;
					}
				}catch (Exception e) {
					logger.warn("happen in updateCharacterInfo",e);
				}
			}
		}
		out.write(BinaryUtil.toByta((byte)flag));
		if(player.getIcon() == null){
			out.write(BinaryUtil.toByta(Constants.DEFAULT_PLAYER_ICON));
		}else{
			out.write(BinaryUtil.toByta(player.getIcon()));
		}
		if(isPlayerCheckToday){
			out.write(BinaryUtil.toByta((byte)1));
		}else{
			out.write(BinaryUtil.toByta((byte)0));
		}
		
		int  rankNumInTop = ServiceLocator.getService.getPlayerFightNumRankInTop(player.getId(), String.valueOf(Constants.CHARACTER_IDS[0]));
//		String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(player.getId(), "kCommonTop");
//		int  rankNumInTop =StringUtil.toInt( ServiceLocator.nosqlService.getNosql().get(rankKey));
//		String[] selfRankValues =ServiceLocator.nosqlService.getNosql().get(rankKey)==null?null:ServiceLocator.nosqlService.getNosql().get(rankKey).split(":");
//		int  rankNumInTop =selfRankValues==null?0:StringUtil.toInt( selfRankValues[0]);
		//top
		out.write(BinaryUtil.toByta(rankNumInTop));
		//fightnum
		out.write(BinaryUtil.toByta(player.getMaxFightNum()));
		//win rate
		float winRate=0;
		if(player.getGWin()!=0 || player.getGLose()!=0){
			winRate=CommonUtil.toFourFloat((float)player.getGWin()/(player.getGWin()+player.getGLose()));
		}
		out.write(BinaryUtil.toByta(winRate));
	
		//team
		if (newTeamName == null) {
			newTeamName = "";
		}
		Team team=null;//ServiceLocator.getService.getTeamByPlayerId(player.getId()); FIXME
		out.write(BinaryUtil.toByta(team==null?0:team.getId()));
		out.write(BinaryUtil.toByta(team==null?"":team.getName()));
		out.write(BinaryUtil.toByta(team==null?1:team.getLevel()));
		server.broadcast(out.toByteArray());
	}
	
	
	public  void puchCMDtoClient(String receiver, String msg) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write((byte)ServerMessage.SM_NotifyClient.ordinal());
		out.write(BinaryUtil.toByta(receiver));
		out.write(BinaryUtil.toByta(msg));
		server.broadcast(out.toByteArray());
	}
}
