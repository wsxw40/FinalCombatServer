package com.pearl.o2o.servlet.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TOPTeam;

/**
 * 显示资源争夺战主界面
 * 
 * @author leo.zhang
 */
public class GetFightResInfo extends BaseClientServlet {
	private static final long serialVersionUID = 3809672433515940145L;
	static Logger logger = LoggerFactory.getLogger(GetFightResInfo.class
			.getName());
	private String[] paramNames = { "pid" };

	@Override
	protected String innerService(String... args) {
		String result = "";
		try {
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getSimplePlayerById(playerId);
			Team team = null;
			if (player.getTeamId() != 0) {
				team = getService.getTeamById(player.getTeamId());
			}
			if (team != null) {
				if (team.getTeamSpaceActive() == Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE) {// 非活跃队伍
					team
							.setTeamSpaceActive(Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE);
					updateService.updateTeamInfo(team);
				}

				// 上周排名
				List<Team> preZYZDZRank = getService.getTeamTopForPreRes();
				long resourceRank=getService.getTeamZyzdzRank(team.getId());
				result = new BattleFieldView(team, player, playerId,
						preZYZDZRank,resourceRank).toResultBattleFieldView();
			}
			
			//获得个人收益系数跟战队收益系数
			return result;

		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	/**
	 * client显示资源类
	 * </p>
	 * 由
	 * <li> BattleField 本战队战场信息 </li>
	 * <li> Player 玩家信息 </li>
	 * <li> TopBattleFields 冠军战场信息集合 </li>
	 * 构成
	 */
	private class BattleFieldView {
		Team team;
		Player player;
		int playerId;
		long resourceRank;
		// 上周资源争夺战排名
		List<Team> preZYZDZRank = new ArrayList<Team>();

		/**
		 * 
		 * @param locabattleField
		 * @param player
		 */
		public BattleFieldView(Team team, Player player, int playerId,List<Team> preZYZDZRank,long resourceRank) {
			this.team = team;
			this.player = player;
			this.playerId = playerId;
			this.preZYZDZRank = preZYZDZRank;
			this.resourceRank=resourceRank;

		}

		public String toResultBattleFieldView() throws Exception {
			boolean isChallengeZYZDZOn = checkZYZDZChallengeOpen();// 开关
			Context ctx = new Context();

			/** 设置自己战场信息 */
			ctx = setLocalBattleFieldContext(team, ctx,resourceRank);

			/** 设置资源争夺战是否可以进行挑战 */
			ctx.set("challenge", isChallengeZYZDZOn);

			/** 设置战场中玩家信息 */
			ctx = setBattlefieldPlayerContext(ctx, player, playerId, team
					.getTeamSpaceLevel());

			/** 设置排名信息 */
			ctx = setRanks(ctx, preZYZDZRank, isChallengeZYZDZOn,team);

			/** 设置转换商店信息 */
			ctx = setSHOP(ctx);

			/** 设置系统提示的一些信息 */
			ctx = setSysInfos(ctx);
			
			/** 队伍收益系数状态  1，为正常  2，为收益0.33 3.为收益零 */
			ctx.set("Res_ratio_status", getRes_ratio_status());
			//-------------2015年3月24号10点 赵连明-----------------------------
			/** 个人收益系数状态  1，为正常  2，为收益0.33 3.为收益零 */
			ctx.set("player_Res_ratio_status", getPlayer_Res_ratio_status());
			
			
			return Converter.GetBattlefieldView(ctx);
		}
		/**
		 * 获得收益状态  1，为正常  2，为收益0.5 3.为收益零 
		 * @return
		 * @throws TimeoutException
		 * @throws InterruptedException
		 * @throws MemcachedException
		 */
		public int getRes_ratio_status() throws TimeoutException,
				InterruptedException, MemcachedException {
			int Res_ratio_status=1;
			String attTeam_Res_ratio_key=Constants.ATT_TEAM_RES_RATIO_STATUS+team.getId();
			Object object = mcc.get(attTeam_Res_ratio_key);
			if (object!=null) {
				Res_ratio_status=(Integer)object;
			}
			return Res_ratio_status;
		}
		/**
		 * 获得收益状态  1，为正常  2，为收益0.5 3.为收益零 
		 * @return
		 * @throws TimeoutException
		 * @throws InterruptedException
		 * @throws MemcachedException
		 */
		public int getPlayer_Res_ratio_status() throws TimeoutException,
				InterruptedException, MemcachedException {
			int player_Res_ratio_status=1;
			String player_Res_ratio_status_key=Constants.PLAYER_RES_RATIO_STATUS+playerId;
			Object object = mcc.get(player_Res_ratio_status_key);
			if (object!=null) {
				player_Res_ratio_status=(Integer)object;
			}
			return player_Res_ratio_status;
		}
	}

	/**
	 * 设置战场中系统提示的东西，如资源争夺战时间等
	 * 
	 * @param ctx
	 * @return
	 */
	private Context setSysInfos(Context ctx) {
		/**资源争夺战开战时间*/
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date start=getService.getZYZDZChallengeTime(0).getTime();
		Date end=getService.getZYZDZChallengeTime(1).getTime();
		ctx.set("challengeSDate", sf.format(start).substring(5,16));//月-日 时:分
		ctx.set("challengeEDate", sf.format(end).substring(5,16));//月-日 时:分
		
		return ctx;
	}

	/**
	 * 设置资源争夺战排名
	 * 
	 * @param ctx
	 * @param preZYZDZRank
	 * @param curZYZDZRank
	 * @param attackTeam 挑战队伍
	 * @return
	 * @throws Exception
	 */
	private Context setRanks(Context ctx, List<Team> preZYZDZRank, boolean isChallengeZYZDZOn,Team attackTeam)
			throws Exception {
		// 设置上周排名
		List<TOPTeam> topTeams = createService.createTopTeams(preZYZDZRank,
				isChallengeZYZDZOn,attackTeam);
		ctx.set("preZYZDZRankList", topTeams);
		return ctx;
	}

	/**
	 * 设置资源争夺战排名
	 * 
	 * @param ctx
	 * @param preZYZDZRank
	 * @param curZYZDZRank
	 * @return
	 * @throws Exception
	 */
	private Context setSHOP(Context ctx) throws Exception {
		// 设置上周排名
		ctx.set("SHOP_PersonalTransform",
				Constants.TeamSpaceConstants.TransformSHOP.PersonalTransform);
		ctx.set("SHOP_TeamTransofrm",
				Constants.TeamSpaceConstants.TransformSHOP.TeamTransofrm);
		ctx.set("SHOP_TeamBuy",
				Constants.TeamSpaceConstants.TransformSHOP.TeamBuy);
		return ctx;
	}

	/**
	 * 获得资源争夺战玩家资源列表
	 * 
	 * @return
	 */
	private Context setBattlefieldPlayerContext(Context ctx, Player player,
			int playerId, int teamSpaceLevel) {
		Constants.TEAMSPACELEVELCONSTANTS tslc = Constants.TEAMSPACELEVELCONSTANTS
				.getTeamSpaceLevelConstants(teamSpaceLevel);

		/** 资源转换率，可能由公式而来 */
		int p_TransitionResource = Constants.TeamSpaceConstants.TRANSITION_RESOURCE_PLAYER_BASIC;
		/** 转换率加成，可能由公式而来 */
		int p_AddTransition = tslc.getpOrgResConvertNum();
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(teamSpaceLevel);
		/** 不可用资源点 */
		int p_UnusableResource = playerRes.get(Player.ORG_RES);

		/** 不可用资源点上限，可能由公式而来 */
		int p_UnusableResourceMAX = tslc.getpMaxOrgRes();
		
		/** 可用资源点 */
		int p_UsableResource = playerRes.get(Player.RES);
		/** 可用资源点上限，可能由公式而来 */
		int p_UsableResourceMAX = tslc.getpMaxRes();

		ctx.set("p_Id", playerId);
		ctx.set("p_TransitionResource", p_TransitionResource);
		ctx.set("p_AddTransition", p_AddTransition);
		ctx.set("p_UnusableResource", p_UnusableResource);
		ctx.set("p_UnusableResourceMAX", p_UnusableResourceMAX);
		ctx.set("p_UsableResource", p_UsableResource);
		ctx.set("p_UsableResourceMAX", p_UsableResourceMAX);
		return ctx;

	}

	/**
	 * 定义本地battlefield
	 * 
	 * @return
	 */
	public Context setLocalBattleFieldContext(Team team, Context ctx,
			long resourceRank) {
		Constants.TEAMSPACELEVELCONSTANTS tslc = Constants.TEAMSPACELEVELCONSTANTS
				.getTeamSpaceLevelConstants(team.getTeamSpaceLevel());
		HashMap<String, Integer> teamRes = team.getLatestTeamRes();
		ctx.set("t_logo", team.getLogo());
		ctx.set("t_teamName", team.getName());
		ctx.set("t_teamLeader", team.getLeaderName());
		ctx.set("t_teamLevel", team.getLevel());
		ctx.set("t_exp", team.getExp());
		ctx.set("t_totalExp", team.getTotalExp());
		ctx.set("t_teamFightNum", team.getFight());
		ctx.set("t_recoreRankingCurr", team.getRecoreRankingCurr());
		ctx.set("t_resourceRank", resourceRank);
		ctx.set("t_teamSpaceLevel", team.getTeamSpaceLevel());
		ctx.set("t_transitionResource",
				Constants.TeamSpaceConstants.TRANSITION_RESOURCE_TEAM_BASIC);
		ctx.set("t_addTransition", tslc.gettOrgResConvertNum());
		ctx.set("t_unusableResource", teamRes.get(Team.ORG_RES));
		ctx.set("t_unusableResourceMAX", Constants.TEAMSPACELEVELCONSTANTS
				.getTeamSpaceLevelConstants(team.getTeamSpaceLevel())
				.gettMaxOrgRes());
		ctx.set("t_usableResource", teamRes.get(Team.RES));
		ctx.set("t_usableResourceMAX", Constants.TEAMSPACELEVELCONSTANTS
				.getTeamSpaceLevelConstants(team.getTeamSpaceLevel())
				.gettMaxRes());
		ctx.set("t_teamMemberAcount", team.getMemberCount());
		ctx.set("t_teamSpaceActive", team.getTeamSpaceActive());
		return ctx;
	}


	/**
	 * 计算护盾剩余时间
	 * <li>2013-11-06</li>
	 * <li>记录：暂时放弃护盾</li>
	 * 
	 * @param team
	 * @return
	 */
	@Deprecated
	private long calShieldRemainTime(Team team) {
		long shieldRemainTime = 0;
		// todo
		// Date shieldStartTime=team.getShieldStartTime();
		Date shieldStartTime = new Date();
		// todo
		// long shieldLastTime=team.getShieldLastTime();
		long shieldLastTime = 1000 * 60 * 60;
		if (shieldStartTime != null && shieldLastTime != 0) {
			Date now = new Date();
			long shieldUsedTime = now.getTime() - shieldStartTime.getTime();// 已使用护盾的时间
			shieldRemainTime = shieldLastTime - shieldUsedTime;// 护盾剩余时间
			shieldRemainTime = shieldRemainTime < 0 ? 0 : shieldRemainTime;// 矫正负数
			shieldRemainTime = shieldRemainTime / 1000;// 将毫秒转化为秒
		}
		return shieldRemainTime;
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
