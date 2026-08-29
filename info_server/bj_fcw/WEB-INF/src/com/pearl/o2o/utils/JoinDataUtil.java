package com.pearl.o2o.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.ItemMod;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;

public class JoinDataUtil {	
	public static Map<Integer, Player> joinPlayerTeam(Map<Integer, Player> playerMap, 
						List<PlayerTeam> playerTeamList, Map<Integer, Team> teamMap) throws Exception{
		Player 	player;
		
		for(PlayerTeam pt : playerTeamList){
			player = playerMap.get(pt.getPlayerId());
			
			if(player != null){
				player.setPlayerTeam(teamMap.get(pt.getTeamId()));
			}
		}

		return playerMap;
	}
	
	@SuppressWarnings("unused")
	public static List<PlayerItem> joinPlayerItemPack(Map<Integer, PlayerItem> playerItemMap,List<PlayerPack> playerPacks)throws Exception{
		for(PlayerPack pp:playerPacks){
			if(pp.getPlayerItemId()!=null){
				PlayerItem pi = playerItemMap.get(pp.getPlayerItemId());
				if (pi != null){//PLAY PACK中可能含有已删除的PLAYERITEM,此时无法在playerItemMap中找到
					pi.setPack(pp.getPackId());
					pi.setSeq(pp.getSeq());
				}
			}
		}
		List<PlayerItem> returnlist=new ArrayList<PlayerItem>();
		for(Map.Entry<Integer, PlayerItem> entry: playerItemMap.entrySet()) { 
			PlayerItem pi =entry.getValue();
			SysItem sysItem = ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
			if(pi.getPlayerItemUnitType() == 2 && pi.getIsBind().equals(Constants.BOOLEAN_NO) && (sysItem.getType() == 1 || sysItem.getType() == 2 || sysItem.getType() == 3)){
				returnlist.add(pi);
			} else if (pi != null && pi.getPlayerItemUnitType() == 2 && (sysItem.getType() == 1 || sysItem.getType() == 2 || sysItem.getType() == 3) && new Date().getTime() > pi.getExpireDate().getTime() && pi.getIsBind().equals(Constants.BOOLEAN_YES)) {
				Date date = pi.getExpireDate();
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(date.getTime());
				c.add(Calendar.DAY_OF_YEAR, Constants.AUTO_DESTORY);
				if (c.getTimeInMillis() > new Date().getTime() || pi.getPack() != 0) {
					returnlist.add(pi);
				} else {
					ServiceLocator.fileLog.warn("sysitem delete playerItem playerID=" + pi.getPlayerId() + " itemId=" + pi.getItemId() + " " + pi.getItemName() + "expired more than 7 days");
					pi.setIsDeleted(Constants.BOOLEAN_YES);
					ServiceLocator.createService.getPlayerItemDao().softDeletePlayerItem(pi);
				}

			} else if (pi != null) {
				returnlist.add(pi);
			} else {
				ServiceLocator.exceptionLog.warn("nullpoint happen in joinPlayerItemPack");
			}
		}
		return returnlist;
	}
	
	public static List<PlayerItem> joinPlayerItemBuff(Map<Integer, PlayerItem> playerItemMap,List<PlayerBuff> PlayerBuffs)throws Exception{
		for(PlayerBuff pb:PlayerBuffs){
			if(pb.getPlayerItemId()!=null){
				PlayerItem pi = playerItemMap.get(pb.getPlayerItemId());
				if(null != pi){
					pi.setBuff(pb.getBuffId());
				}
			}
		}
		return new ArrayList<PlayerItem>(playerItemMap.values());
	}
	public static List<PlayerItem> joinPartItemMod(Map<Integer, PlayerItem> playerItemMap,List<ItemMod> itemMods)throws Exception{
		for(ItemMod im:itemMods){
			playerItemMap.get(im.getChildItemId()).setParentItemId(im.getParentItemId());
		}
		return new ArrayList<PlayerItem>(playerItemMap.values());
	}
}