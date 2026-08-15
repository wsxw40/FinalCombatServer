package com.pearl.o2o.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class LeagueGameRecordImpl {
    private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
  //"lagrs:temaid", "日期|temabid|战场type" "100|10"
    public static Map<String, LeagueGameRecord> getGameRecords(int teamId) throws Exception {
        String key = Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + teamId;
        //总记录
        Map<String, String> lgrs = noSql.hgetAll(key);
        //放解析后的总记录
        Map<String, LeagueGameRecord> hashMap = new HashMap<String, LeagueGameRecord>();
        
        for (Entry<String, String> lgr : lgrs.entrySet()) {
            String key_now = lgr.getKey();
            String value_now = lgr.getValue();
            LeagueGameRecord lgr_now = hashMap.get(key_now);
            
            String[] keyArray = key_now.split("\\|");
            String time = keyArray[0];
            int teambid = Integer.valueOf(keyArray[1]);
            int gameType = Integer.valueOf(keyArray[2]);
            
            String[] valueArray = value_now.split("\\|");
            int myTeamScoce = Integer.valueOf(valueArray[0]);
            int teambScoce = Integer.valueOf(valueArray[1]);
            
            if (lgr_now == null) {
                LeagueGameRecord lGameRecord = new LeagueGameRecord();
                lGameRecord.setTime(time);//比赛时间日期
                lGameRecord.setTeambId(teambid);////对方战队id
                Team team = ServiceLocator.getService.getTeam(teambid);
                lGameRecord.setTeambName(team.getName());////对方战队id
                lGameRecord.getLgrSingleS().add(new LeagueGameRecordSingle(gameType,myTeamScoce,teambScoce));
                hashMap.put(time+"|"+teambid, lGameRecord);
            } else {
                lgr_now.getLgrSingleS().add(new LeagueGameRecordSingle(gameType,myTeamScoce,teambScoce));
            }
        }
        return hashMap;
    }
    /**
     * 更新战斗记录
     * @param myTeamId
     * @param teambId
     * @param gameType
     * @param myScore
     * @param scoreb
     * @throws Exception
     */
    public static void  updataGameRecords(int myTeamId,int teambId,int gameType,int myScore,int scoreb) throws Exception {
        String key_1 = Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + myTeamId;
        String format = DateFormatUtil.getYMDSf().format(new Date());
        String key_2= format+"|"+teambId+"|"+gameType;
        noSql.hashSet(key_1, key_2,myScore+"|"+scoreb);
    }
}