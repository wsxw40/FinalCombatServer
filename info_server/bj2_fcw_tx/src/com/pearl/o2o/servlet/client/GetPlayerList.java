package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;




/**
 * @author WengJie
 *
 */
public class GetPlayerList extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(GetPlayerList.class.getName());
	private static final String[] paramNames = {"uid"};
	
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
//			String strKey=CacheUtil.sPlayerList(userId);
			String result = null;
////				(String) mcc.get(strKey);
//			
//			if(result == null){
//				
//				String objKey=CacheUtil.oCharacter();
//				List<SysCharacter> characters = mcc.get(objKey);
//				if(characters == null){
//					characters = getService.getCharacterList(Constants.BOOLEAN_YES);
//				}
//				
//				List<Player> players = getService.getPlayerByUserId(userId);
//				if(players!=null&&players.size()!=0){
//					List<Player> tempplayers=new ArrayList<Player>();
//					for(int j=0;j<players.size();j++){
//						tempplayers.add(new Player());
//					}
//					Collections.copy(tempplayers,players);
//					Collections.sort(tempplayers,new Comparator<Player>() {
//						@Override
//						public int compare(Player o1, Player o2) {
//							return ((Integer)o2.getLastLoginTime()).compareTo((Integer)o1.getLastLoginTime());
//						} 
//					});
//					//get the last player avatar info
//					Player player = tempplayers.get(0);
//					List<PlayerItem> cosT = getService.getCostumePackList(player.getId(), 1, 0);
//					List<PlayerItem> cosP = getService.getCostumePackList(player.getId(), 1, 1);
////					player.putOnCostume(0, cosT);
////					player.putOnCostume(1, cosP);
//					List<PlayerItem> pack = getService.getWeaponPackList(player.getId(), 1);
//					for(PlayerItem pi : pack){
//						if(pi!=null && pi.getId()!=0){
//							player.setPi(pi);
//							break;
//						}
//					}
//					//replace the last login player to players
//					for(int i=0;i<players.size();i++){
//						Player pi=players.get(i);
//						if(pi.getId()== player.getId()){
//							players.set(i, player);
//						}
//					}
//					result = Converter.playerList(players,characters.get(0).getCost());
//					mcc.setWithNoReply(strKey, Constants.CACHE_ITEM_TIMEOUT, result);
//				}else{
//					result = Converter.playerList(new ArrayList<Player>(),characters.get(0).getCost());
//				}
//			}			
				
			return result;
		}	
//		catch (BaseException e) {
//			return Converter.error(e.getMessage());	
//		}
		catch (Exception e) {
			log.warn("Exception in GetPlayerList: ", e);
			if (e instanceof MemcachedException
					|| e instanceof TimeoutException) {
				
				return Converter.error("Memcached Not Started");
			}
			else{
				return Converter.error(ExceptionMessage.ERROR_MESSAGE);				
			}
		}
	}


	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
