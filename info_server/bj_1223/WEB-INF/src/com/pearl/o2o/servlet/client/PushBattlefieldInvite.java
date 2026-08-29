package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.Constants.TeamSpaceConstants.FightType;

/**
 * 发送资源争夺战进攻邀请信息
 * 
 * @author leo.zhang
 */
public class PushBattlefieldInvite extends BaseClientServlet {

	private static final long serialVersionUID = 7956047244525381200L;

	static Logger logger = LoggerFactory.getLogger(PushBattlefieldInvite.class
			.getName());

	private String[] paramNames = { "pid", "type", "serverid", "channelid",
			"roomid", "tpid", "roompwd" ,"restype"};

	/**
	 * <ul>
	 * args
	 * <li>pid:发出邀请的玩家</li>
	 * <li>type:邀请方式 {0:指定玩家 ; 1:战队所有在线玩家}</li>
	 * <li>serverid:邀请的服务器ID</li>
	 * <li>channelid:邀请的频道ID</li>
	 * <li>roomid:邀请的房间ID</li>
	 * <li>tpid:接受邀请的玩家，如有以 , 进行分割，没有为""字符串</li>
	 * <li>roompwd:邀请的房间密码，没有为""字符串</li>
	 * <li>restype:0匹配  1挑战</li>
	 * </ul>
	 */
	@Override
	protected String innerService(String... args) {
		String result = "";
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			int serverid = StringUtil.toInt(args[2]);
			int channelid = StringUtil.toInt(args[3]);
			int roomid = StringUtil.toInt(args[4]);
			String roompwd ="";
			int restype = StringUtil.toInt(args[7]);
			// 发出邀请的玩家
			Player inviter = getService.getPlayerById(playerId);
			// 接受邀请的玩家集合
			List<Player> receivers = new ArrayList<Player>();
			if (type == 0) {// 指定邀请人员
				String[] receiverArray = args[5].split(",");
				if(receiverArray.length==0){
					return "";
				}
				for (String receiverID : receiverArray) {
					Player receiver = getService.getPlayerById(StringUtil
							.toInt(receiverID));
					if (receiver != null) {
						receivers.add(receiver);
					}

				}

			} else if (type == 1) {// 邀请所有团员
				List<PlayerTeam> teamMemberList = getService.getTeamByPlayerId(
						playerId).getMemberList();
				for (PlayerTeam playerTeam : teamMemberList) {
					Player receiver = getService.getPlayerById(playerTeam
							.getPlayerId());
					if (getService.isPlayerOnline(receiver)) {// 在线
						receivers.add(receiver);
					}
				}

			} else {
				return Converter
						.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
			}


			if (args[6] != null)
				roompwd = args[6];

			FightType fightType=Constants.TeamSpaceConstants.FightType.getFightByValue(restype);
			for (Player receiver : receivers) {// 向每一玩家发出邀请
				soClient.sendBattleFieldInvite(receiver, inviter, serverid,
						channelid, roomid, roompwd,	fightType);
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
