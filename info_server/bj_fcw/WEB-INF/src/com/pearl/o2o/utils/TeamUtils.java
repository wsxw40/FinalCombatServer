package com.pearl.o2o.utils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;

public class TeamUtils{
	public static <R> R execute(String key,Callable<R> execute) throws MemcachedException, TimeoutException, Exception{
		boolean isLocked = false;
		if(key == null){
			return execute.call();
		} else {
			try {
				isLocked = ServiceLocator.ml.tryLock(key, 2000); // 可以以PLAYER ID为锁的KEY，或自己能方便标识公共资源的KEY，该方法不会抛出任何异常
				if (isLocked) { // 加锁了便需要解锁
					return execute.call();
				} else {// 拿锁失败
					ServiceLocator.fileLog.warn("lock team info fail. key=" + key);
					throw new Exception(ExceptionMessage.ERROR_MESSAGE_RETRY); // 在指定时间内拿不到锁，根据业务逻辑做相应处理
				}
			} finally {
				ServiceLocator.ml.unlock(key);// 无论如何要在这里解锁
			}
		}
	}
	/**
	 * 更新战队排名 战斗力信息
	 * 先判断 是否需要更新，如需先获得锁再去reUpdate
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public static Team updateTeamInfo(final int teamId) throws Exception {
		Team team = ServiceLocator.getService.getTeamById(teamId);
		CommonUtil.checkNull(team, ExceptionMessage.NOT_FIND_TEAM);
		int recoreRanking = ServiceLocator.getService.getRecoreRanking(teamId);
		int fightRanking = ServiceLocator.getService.getFightRanking(team.getId());
		int teamFight = ServiceLocator.getService.getTeamFight(team);
		
		if (team.getRecoreRankingCurr() != recoreRanking || team.getFightRankingCurr() != fightRanking || team.getFight() != teamFight) {
			return execute(CommonUtil.getTeamLockKey(team.getId()), 
				new Callable<Team>(){
					@Override
					public Team call() throws Exception {
						Team team = ServiceLocator.getService.getTeamById(teamId);
						int recoreRanking = ServiceLocator.getService.getRecoreRanking(teamId);
						int fightRanking = ServiceLocator.getService.getFightRanking(team.getId());
						int teamFight = ServiceLocator.getService.getTeamFight(team);
						if (team.getRecoreRankingCurr() != recoreRanking || team.getFightRankingCurr() != fightRanking || team.getFight() != teamFight) {
							team.setRecoreRankingFormer(team.getRecoreRankingCurr());
							team.setRecoreRankingCurr(recoreRanking);
							team.setFightRankingFormer(team.getFightRankingCurr());
							team.setFightRankingCurr(fightRanking);
							team.setFight(teamFight);
							ServiceLocator.updateService.updateTeamInfo(team);
						}
						return team;
					}
				}
			);
		}else {
			return team;
		}
	}
	public static Team updateTeamSize(final int teamId,final int size) throws Exception {
			return execute(CommonUtil.getTeamLockKey(teamId), 
				new Callable<Team>(){
					@Override
					public Team call() throws Exception {
						Team team = ServiceLocator.getService.getTeam(teamId);
						int num=0;
						num=size>=Constants.TEAME_MAX_SIZE?Constants.TEAME_MAX_SIZE:size;
						team.setSize(num);
						ServiceLocator.updateService.updateTeamInfo(team);
						return team;
					}
				}
			);
	}
	public static Team updateTeamName(final int teamId,final String name) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					team.setName(name);
					ServiceLocator.updateService.updateTeamInfo(team);
					return team;
				}
			}
		);
}
	public static Team updateTeamMemberNum(final int teamId,final int Num) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					team.setNumber(team.getNumber()+Num);
					ServiceLocator.updateService.updateTeamInfo(team);
					ServiceLocator.updateService.updateTeamTop(team,Constants.TEAM_TOP_TYPE.HOT.getValue());
					return team;
				}
			}
		);
}
	public static Team updateTeamExp(final int teamId,final Team tm) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					int levelBefore = team.getLevel();
					team.setExp(tm.getExp());
					team.setLevel(tm.getLevel());
					team.setSize(tm.getSize());
					ServiceLocator.updateService.updateTeamInfo(team);
					if(tm.getLevel()>levelBefore){//更新战队等级排行
						ServiceLocator.updateService.updateTeamTop(team, Constants.TEAM_TOP_TYPE.RANK.getValue());
					}
					return team;
				}
			}
		);
	}
	public static Team updateTeamExp(final int teamId,final int exp) throws Exception {//战队经验变化时，等级有可能会发生变化，等级变化时，战队最大人数会变化
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					int levelBefore = team.getLevel();
					team.addExp(exp,team);
					ServiceLocator.updateService.updateTeamInfo(team);
					if(team.getLevel()>levelBefore){//更新战队等级排行
						ServiceLocator.updateService.updateTeamTop(team, Constants.TEAM_TOP_TYPE.RANK.getValue());
					}
					return team;
				}
			}
		);
	}
	public static Team updateTeamBaseInfo(final int teamId,final Team team) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					team.setLogo(team.getLogo());
					team.setRank(team.getRank());
					team.setDescription(team.getDescription());
					ServiceLocator.updateService.updateTeamInfo(team);
					return team;
				}
			}
		);
	}
	public static Team updateTeamWinTotal(final int teamId,final Team tm) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					team.setGameWin(tm.getGameWin());
					team.setGameTotal(tm.getGameTotal());
					ServiceLocator.updateService.updateTeamInfo(team);
					ServiceLocator.updateService.updateTeamTop(team, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
					return team;
				}
			}
		);
	}
	public static Team updateTeamFightResTop(final int teamId,final Team tm) throws  Exception{
		return execute(CommonUtil.getTeamLockKey(teamId), new Callable<Team>(){
			@Override
			public Team call() throws Exception {
				Team team = ServiceLocator.getService.getTeam(teamId);
				team.setPreweekResAmount(tm.getPreweekResAmount());
				ServiceLocator.updateService.updateTeamInfo(team);
				ServiceLocator.updateService.updateTeamTop(team, Constants.TEAM_TOP_TYPE.RESOURCE.getValue());
				return team;
			}
		});
	}
	public static Team updateTeamWinTotal(final int teamId,final int isWin) throws Exception {
		return execute(CommonUtil.getTeamLockKey(teamId), 
			new Callable<Team>(){
				@Override
				public Team call() throws Exception {
					Team team = ServiceLocator.getService.getTeam(teamId);
					team.setGameWin(team.getGameWin()+isWin);
					team.setGameTotal(team.getGameTotal()+1);
					ServiceLocator.updateService.updateTeamInfo(team);
					ServiceLocator.updateService.updateTeamTop(team, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
					return team;
				}
			}
		);
	}
	
	/**
	 * 像每一个在线的战队成员发送更新CMD
	 * @param teamId
	 * @param num 
	 * @throws Exception 
	 */
	public static void pushCMDToOnlineTeamPlayer(final int teamId,int num ) throws Exception{
		List<PlayerTeam>  list= ServiceLocator.getService.getPlayerTeamByTeamIdSimple(teamId);
		for(Iterator<PlayerTeam> it=list.iterator();it.hasNext();){
			PlayerTeam pt= it.next();
			  PlayerLocation location=ServiceLocator.mcc.get(CacheUtil.oPlayerLocation(pt.getPlayerId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					ServiceLocator.soClient.puchCMDtoClient(ServiceLocator.getService.getSimplePlayerById(pt.getPlayerId()).getName(), Converter.needRefreshProclamation(num));
				}
		}
	}
	
}
