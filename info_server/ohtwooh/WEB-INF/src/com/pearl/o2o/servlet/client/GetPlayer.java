package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.Converter;






public class GetPlayer extends BaseClientServlet {
	private static final long serialVersionUID = 8121117755228356762L;
	static Logger log = Logger.getLogger(GetPlayer.class.getName());	
	
	public GetPlayer(){
		super();
	}	 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{					
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			String key = CacheUtil.sPlayer(playerId);
			String result = (String) mcc.get(key);
			if(result == null){
				Player player = getService.getPlayerById(playerId);
				result = Converter.player(player);
				mcc.set(key, Constants.CACHE_ITEM_TIMEOUT, result);
			}
			out.write(result);			
		}
		catch (Exception e) {
			log.error("Exception in GetPlayer: ", e);
			if (e instanceof MemcachedException
					|| e instanceof TimeoutException) {
				out.write(Converter.error("Memcached Not Started"));
			}
			else{
				out.write(Converter.error("系统出现异常错误，请联系GM"));				
			}
		}	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);		
	}
}

