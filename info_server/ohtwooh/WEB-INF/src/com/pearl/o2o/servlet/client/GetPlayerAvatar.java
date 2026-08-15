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
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.Converter;

public class GetPlayerAvatar extends BaseClientServlet {
	private static final long serialVersionUID = 6628659867111484527L;
	static Logger log = Logger.getLogger(GetPlayerAvatar.class.getName());	
	
	public GetPlayerAvatar(){
		super();
	}	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{					
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			String cName = req.getParameter("name");
			Player player = null;
			if (!StringUtil.isEmptyString(cName)){
				player = getService.getPlayerByName(cName);
				if (player == null) {
					out.write(Converter.error(ExceptionMessage.PALERY_NOT_EXIST));
					return ;
				}
			}else{
				player = getService.getPlayerById(playerId);
			}
			List<PlayerItem> cosT = getService.getCostumePackList(playerId, 1, 0);
			List<PlayerItem> cosP = getService.getCostumePackList(playerId, 1, 1);
			List<PlayerItem> pack = getService.getWeaponPackList(playerId, 1);
			
			player.putOnCostume(0, cosT);
			player.putOnCostume(1, cosP);
			
			PlayerItem weapon = null;
			for(PlayerItem pi : pack){
				if(pi!=null && pi.getId()!=null&&pi.getParts()!=null&&pi.getParts().size()!=0){
					weapon = pi;
					
//					weapon.putOnPart();
					break;
				}
			}
			String result = Converter.playerAvatar(player, weapon);			
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

