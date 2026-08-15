package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;



/**
 * @author WengJie
 *
 */
public class FuzzyGetPlayerList extends BaseClientServlet {
	private static final long serialVersionUID = -1225837047769195165L;
	static Logger log = Logger.getLogger(FuzzyGetPlayerList.class.getName());

	public FuzzyGetPlayerList(){
		super();
	}	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			
			String name = req.getParameter("name");
			if (StringUtil.isEmptyString(name)) {
				out.write(Converter.error(ExceptionMessage.EMPTY_STR));
				return ;
			}
			int page = StringUtil.toInt(req.getParameter("page_id"));
			List<Player> players=getService.fuzzyGetPlayerByName(name, page);
			for(Player player:players){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(player.getId()));
				if (location != null) {
					Channel channel = getService.getChannel(location.getChannel());
					if (channel != null) {
						Server server = getService.getServer(channel.getServerId());
						if (server != null) {
							player.setChannel(channel.getName());
							player.setServer(server.getName());
							player.setRoom(location.getRoom());
						}
					}
				}
			}
			String result = Converter.playerPageList((int)Math.ceil(getService.fuzzyCountPlayer(name)/(double)Constants.FRIEND_PAGE_SIZE),
					players);		
			out.write(result);
		}
		catch(Exception e){
			log.error("Error in GetPlayerList: ", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
					
	}
}
