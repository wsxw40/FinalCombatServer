package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Constants.TeamSpaceConstants.FightType;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 发送资源争夺战防守邀请信息
 * 
 * @author leo.zhang
 */
public class PushBattlefieldDefenseInvite extends BaseClientServlet {

	private static final long serialVersionUID = -7811972952568740757L;

	static Logger logger = LoggerFactory
			.getLogger(PushBattlefieldDefenseInvite.class.getName());

	private String[] paramNames = { "atid", "dtid", "serverid", "channelid",
			"roomid", "roompwd" ,"restype","config","canRobRes","atknum"};

	/**
	 * <ul>
	 * args
	 * <li>atid:进攻方的ID</li>
	 * <li>dtid:防守方的ID</li>
	 * <li>serverid:邀请的服务器ID</li>
	 * <li>channelid:邀请的频道ID</li>
	 * <li>roomid:邀请的房间ID</li>
	 * <li>roompwd:邀请的房间密码，没有为""字符串</li>
	 * <li>restype:0匹配  1挑战</li>
	 * <li>config:地图信息</li>
	 * <li>canRobRes:当前战斗被抢的总数</li>
	 * <li>atknum:进攻方人数</li>
	 * </ul>
	 */
	@Override
	protected String innerService(String... args) {
		String result = "";
		try {
			int attackTeamID = StringUtil.toInt(args[0]);
			int defanseTeamID = StringUtil.toInt(args[1]);
			int serverid = StringUtil.toInt(args[2]);
			int channelid = StringUtil.toInt(args[3]);
			int roomid = StringUtil.toInt(args[4]);
			String roompwd = "";
			if (args[5] != null)
				roompwd = args[5];
			int restype= StringUtil.toInt(args[6]);
			String config= args[7];			
			int canRobRes=StringUtil.toInt(args[8]);
			int atknum=StringUtil.toInt(args[9]);
			Team attackTeam = getService.getTeamById(attackTeamID);
			Team defenseTeam = getService.getTeamById(defanseTeamID);
			// 接受邀请的玩家集合
			List<Player> receivers = new ArrayList<Player>();
			if (defenseTeam != null) {// 邀请所有在线团员
				List<PlayerTeam> teamMemberList = defenseTeam.getMemberList();
				if (teamMemberList != null) {
					for (PlayerTeam playerTeam : teamMemberList) {
						Player receiver = getService.getPlayerById(playerTeam
								.getPlayerId());
						if (getService.isPlayerOnline(receiver)) {// 在线
							receivers.add(receiver);
						}
					}
				}

			} else {
				return Converter
						.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
			}

			FightType fightType=Constants.TeamSpaceConstants.FightType.getFightByValue(restype);
			if (attackTeam != null) {
				for (Player receiver : receivers) {// 向每一玩家发出邀请
					soClient.sendBattleFieldDefenseInvite(attackTeam,receiver,
							defenseTeam, serverid, channelid, roomid, roompwd,fightType,config,canRobRes,defenseTeam.getTeamSpaceLevel(),atknum);
				}
			}

			return result;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
