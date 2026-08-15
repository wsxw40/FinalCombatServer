package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueGame;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.LeagueTeamMateImpl;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.schedule.LeagueGameNotice;
import com.pearl.o2o.servlet.client.LeagueApply;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.LeagueUtil;
import com.pearl.o2o.utils.ServiceLocator;
/**
 * 联赛数据
 * @author zhaolianming
 *
 */
public class LeagueGameInfo extends BaseServerServlet {

	private static final long serialVersionUID = 5089293981299897027L;
	static Logger log = LoggerFactory.getLogger(LeagueGameInfo.class.getName());
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
	    System.out.println("LeagueGameInfo");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            //按类型分组的随机配对
            NoSql nosql = ServiceLocator.nosqlService.getNosql();
        
            nosql.delete(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX);
            nosql.delete(Constants.LEAGUE_GAME_TIME_KEY_PREFIX);
            
            String nosql_key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
            Set<Tuple> zrangeWithScores = nosql.zrangeWithScores(nosql_key, 0, -1);
            List<LeagueGame>[] LeagueGameList_group = new ArrayList[5];
            for (int i = 0; i < LeagueGameList_group.length; i++) {
                LeagueGameList_group[i] = new ArrayList<LeagueGame>();
            }
            //按类型分组type_group
            for (Tuple tuple : zrangeWithScores) {
                Integer teamId = Integer.valueOf(tuple.getElement());
                //返回该战队下所有参赛玩家的数据,按游戏类型
                Map<Integer, ArrayList<Integer>> membersMap = LeagueMemberImpl.getMembersMap(teamId);
                Team team = ServiceLocator.getService.getTeamById(teamId);
                String teamName = "未知";
                int level=0;
                if (team != null) {
                    teamName = team.getName();
                    level = team.getLevel();
                }
                for (int i = 0; i < LeagueGameList_group.length; i++) {
                    ArrayList<Integer> arrayList = membersMap.get(i);
                    if (arrayList == null)
                        continue;
                    LeagueGame leagueGame = new LeagueGame();
                    leagueGame.setTeamId(teamId);
                    leagueGame.setGameType(LeagueUtil.leagueGameTypeTogameType(i));
                    leagueGame.setLevel(level);
                    leagueGame.setTeamName(teamName);
                    leagueGame.setPlayerIds(arrayList);
                    LeagueGameList_group[i].add(leagueGame);
                }
            }
            //排序
            //如果是奇数者随即选一个直接轮空
            Random random = new Random();
            ArrayList<LeagueGame> lGameTotal = new ArrayList<LeagueGame>();
            for (int i = 0; i < LeagueGameList_group.length; i++) {
                if (LeagueGameList_group[i].size() % 2 == 1) {
                    //获得轮空  
                    LeagueGame remove = LeagueGameList_group[i].remove(random.nextInt(LeagueGameList_group[i].size()));
                    // TODO 直接获胜
                    
                }
                //随即配对
                lGameTotal.addAll(randoMate(LeagueGameList_group[i]));
            }
            //输出
            int lApplyStartTime = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_APPLY_START);
            int lApplyEndTime = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_APPLY_END);
            int lGameStartTime = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_GAME_START);
            out.write(BinaryUtil.toByta(lApplyEndTime));//下次一请求，只有这个影响请求时间
            out.write(BinaryUtil.toByta(lGameStartTime));//距离开始时间,单位秒
            
            if (lGameStartTime<=lApplyEndTime) {//距离开始时间小于下次一请求，这样只触发一次
                //公告系统
                LeagueGameNotice lgNotice = new LeagueGameNotice(lGameStartTime);
                ServiceLocator.scheduledExecutorService.schedule(lgNotice,lgNotice.scheduledTime(),TimeUnit.SECONDS);
            }
            
            out.write(BinaryUtil.toByta(6));//总条数目
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(0)));//游戏类型地图 case 4://高级战场（歼灭）
            out.write(BinaryUtil.toByta(2));//地图张数
            out.write(BinaryUtil.toByta(68));//地图id
            out.write(BinaryUtil.toByta(76));//地图id
            
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(1)));//游戏类型地图 case 2://中级战场（推车）
            out.write(BinaryUtil.toByta(1));//地图张数
            out.write(BinaryUtil.toByta(80));//地图id
            
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(2)));//游戏类型地图 case 0://低级战场（团队竞技）
            out.write(BinaryUtil.toByta(1));//地图张数
            out.write(BinaryUtil.toByta(74));//地图id
            
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(3)));//游戏类型地图 case 1://低级战场（占点）
            out.write(BinaryUtil.toByta(1));//地图张数
            out.write(BinaryUtil.toByta(43));//地图id
            
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(4)));//游戏类型地图 case 6://低级战场（刀战）
            out.write(BinaryUtil.toByta(1));//地图张数
            out.write(BinaryUtil.toByta(55));//地图id
            
            out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(5)));//游戏类型地图 case 17://特殊战场（跳跳乐）
            out.write(BinaryUtil.toByta(1));//地图张数
            out.write(BinaryUtil.toByta(221));//地图id
            
            out.write(BinaryUtil.toByta(lGameTotal.size()));//总条数目
            //配对id 战队id 战队等级 游戏类型
            for (LeagueGame leagueGame : lGameTotal) {
                out.write(BinaryUtil.toByta(leagueGame.getMateId()));//配对id
                out.write(BinaryUtil.toByta(leagueGame.getTeamId()));//战队id
                out.write(BinaryUtil.toByta(leagueGame.getLevel()));//战队等级
                out.write(BinaryUtil.toByta(leagueGame.getGameType()));//游戏类型
                //out.write(BinaryUtil.toByta(leagueGame.getTeamName()));//战队名字
                out.write(BinaryUtil.toByta(leagueGame.getPlayerIds().size()));//玩家数目
                for (int i = 0; i < leagueGame.getPlayerIds().size() && i < 6; i++) {
                    out.write(BinaryUtil.toByta(leagueGame.getPlayerIds().get(i)));//玩家id
                }
            }
            return out.toByteArray();
        }catch (Exception e) {
            log.warn("Error in LeagueGameInfo: ", e);
            throw e;
        }
	}
	
	/**
     * 随即配对
     * @param list
     * @return
     */
    private ArrayList<LeagueGame> randoMate(List<LeagueGame> lGames) {
        ArrayList<LeagueGame> randoMateList = new ArrayList<LeagueGame>();
        Random random = new Random();
        for (int i = 0; i < lGames.size(); i++) {
            LeagueGame lGame_1 = lGames.remove(random.nextInt(lGames.size()));
            LeagueGame lGame_2 = lGames.remove(random.nextInt(lGames.size()));
            lGame_1.setMateId(i * 10 + lGame_1.getGameType());
            lGame_2.setMateId(i * 10 + lGame_2.getGameType());
            randoMateList.add(lGame_1);
            randoMateList.add(lGame_2);
            LeagueTeamMateImpl.add(lGame_1.getTeamId(), lGame_2.getTeamId());
            LeagueTeamMateImpl.add(lGame_2.getTeamId(), lGame_1.getTeamId());
        }
        return randoMateList;
    }
   
}

