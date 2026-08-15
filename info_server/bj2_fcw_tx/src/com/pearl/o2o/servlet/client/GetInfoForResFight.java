package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;

public class GetInfoForResFight extends BaseClientServlet{
	@Override
	protected String innerService(String... strings) {
		try {
			int playerId = Integer.valueOf(strings[0]);
			Team team = getService.getTeamByPlayerId(playerId);
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
			Player player = getService.getPlayerById(playerId);
			
 		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return super.innerService(strings);
	}
}
