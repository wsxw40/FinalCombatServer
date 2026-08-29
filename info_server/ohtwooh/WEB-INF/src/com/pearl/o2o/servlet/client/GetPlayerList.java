package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;



/**
 * @author WengJie
 *
 */
public class GetPlayerList extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = Logger.getLogger(GetPlayerList.class.getName());

	public GetPlayerList(){
		super();
	}	
	
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			String strKey=CacheUtil.sPlayerList(userId);
			String result = (String) mcc.get(strKey);
			
			if(result == null){
				List<Player> players = getService.getPlayerByUserId(userId);
				for(Player player:players){
					List<PlayerItem> cosT = getService.getCostumePackList(player.getId(), 1, 0);
					List<PlayerItem> cosP = getService.getCostumePackList(player.getId(), 1, 1);
					player.putOnCostume(0, cosT);
					player.putOnCostume(1, cosP);
				}
				result = Converter.playerList(players);
				mcc.set(strKey, Constants.CACHE_ITEM_TIMEOUT, result);
			}			
				
			out.write(result);
		}			
		catch (Exception e) {
			log.error("Exception in GetPlayerList: ", e);
			if (e instanceof MemcachedException
					|| e instanceof TimeoutException) {
				out.write(Converter.error("Memcached Not Started"));
			}
			else{
				out.write(Converter.error("系统出现异常错误，请联系GM"));				
			}
		}
	}
}
