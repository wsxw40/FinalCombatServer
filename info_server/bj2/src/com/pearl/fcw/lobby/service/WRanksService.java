package com.pearl.fcw.lobby.service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.pojo.WTeam;

/**
 * 排行榜
 * @author zhaolianming
 */
public class WRanksService extends DmServiceImpl<WTeam, Integer> {
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    //    @Resource
    //    private EntityManager entitymanager;
    //    @Resource
    //    private WPlayerService wPlayerService;
    //    @Resource
    //    private WCharacterDataService wCharacterDataService;
    //    @Resource
    //    private   RedisClient redisClient;
    //    /**
    //     * 获得玩家战斗力排行
    //     * @param playerId
    //     * @param cid
    //     * @param self
    //     * @param page
    //     * @return
    //     */
    //    public String getPlayerFightNumTop(int playerId, String cid, int self, int page) {
    //        try{
    //            int pageSize=Constants.TOP_PAGE_SIZE;
    //            //Player player = getService.getPlayerById(playerId);
    //            ProxyWPlayer pwPlayer = wPlayerService.findWPlayerById(playerId);
    //            
    //            CommonUtil.checkNull(pwPlayer, ExceptionMessage.NO_HAVE_CHARACTER);
    //            String rKey = NosqlKeyUtil.fightNumInRealTopByType(cid);
    //            int rankNumInTop = wCharacterDataService.getPlayerFightNumRankInTop(playerId, cid);
    //            int realRank = (int)redisClient.zRank(rKey, String.valueOf(playerId)) + 1;
    //            if(realRank==0){
    //                joinFightNumInRealTop(cid,pwPlayer);
    //            }
    //            long pages = 0;
    //            long total = (int)redisClient.zCard(rKey);
    //            if(total>Constants.CURRENT_RANK_NUM){
    //                total = Constants.CURRENT_RANK_NUM;
    //            }
    //            if(total%pageSize==0){
    //                pages=(int)(total/pageSize);
    //            }else{
    //                pages=(int)(total/pageSize)+1;
    //            }
    //            if(pages==0){
    //                pages=1;
    //            }
    //            if(page>pages){
    //                page =1;
    //            }
    //            int start = (page-1)*pageSize;
    //            int rowNum = 0;
    //            //locate self 
    //            if(self==1){
    //                rowNum = rankNumInTop;
    //                if(rowNum==0){
    //                    start=0;
    //                    page=1;
    //                }else if(rowNum%pageSize==0){
    //                    start=(int)(rowNum/pageSize-1)*pageSize;
    //                    page=(int)(rowNum/pageSize);
    //                }else{
    //                    start=(int)(rowNum/pageSize)*pageSize;
    //                    page=(int)(rowNum/pageSize+1);
    //                }
    //            }
    //            Set<String> rankSet= redisClient.zRange(rKey, start, start + pageSize -1);
    //            if (rankSet == null ) {
    //                logger.warn("rank entry is null, key is " + rKey);
    //            }
    //            List<String> rankEntry = new ArrayList<String>();
    //            for(String s :rankSet){
    //               //Player tmp = getService.getPlayerById(Integer.parseInt(s));
    //                ProxyWPlayer tmp = wPlayerService.findWPlayerById(playerId);
    //                if(tmp!=null){
    //                    String newStr = "{" + (redisClient.zRank(rKey, s) + 1) + ",\"" + tmp.name().get() + "\"," + (int) Math.abs(redisClient.zScore(rKey, s)) + "," + tmp.isVip().get() + ","
    //                            + tmp.rank().get() + "," + tmp.generalKill().get() + "," + tmp.generalDead().get() + "," + tmp.generalAssist().get() + "," + tmp.generalWin().get() + ","
    //                            + tmp.generalLose().get() + "," + tmp.exp().get() + "," + s + ",\"" + tmp.icon().get() + "\",},";
    //                    rankEntry.add(newStr);
    //                }else{
    //                    logger.warn("GetPlayerFightNumTop/PlayerNull:\t" + s);
    //                    redisClient.zRem(rKey,s);
    //                }
    //            }
    //            int selectIndex=0;
    //            if(rowNum%10==0){
    //                selectIndex=10;
    //            }else{
    //                selectIndex=(int)rowNum%10;
    //            }
    //            String result = Converter.playerTop(page, pages, rankEntry,"PlayerCharacterTopFightNum.st",rankNumInTop,CommonUtil.minsBetweenTimes(),selectIndex);
    //            return result;
    //        }catch (Exception e) {
    //            logger.warn("GetPlayerFightNumTop/Warn:\t", e);
    //            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    //        }
    //    }
    //    /**
    //     * 玩家加入真实排行榜记录
    //     * @param cid
    //     * @param player
    //     * @throws Exception
    //     */
    //    private void joinFightNumInRealTop(String cid ,ProxyWPlayer pwPlayer) throws Exception{
    //        String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
    //      //  String pId =  String.valueOf(player.getId());
    //        String pId = String.valueOf(pwPlayer.id().get());
    //        int chid = Integer.parseInt(cid);
    //        Character character = null;
    //        int value = 0;
    //        if(chid!=0){//0 表示综合战斗力
    //            character=wCharacterDataService.getCharacterByCharacterId(pwPlayer.id().get(), Integer.parseInt(cid));
    //            if(character==null)
    //                return;
    //            value = character.getFightNum();
    //        }else{
    //            //value = player.getMaxFightNum();
    //            value = pwPlayer.maxFightNum().get();
    //        }
    //        
    //        int total = (int)redisClient.zCard(key);
    //        if(total<Constants.REAL_TOP_RANK_NUM){
    //            //nosql.zAdd(key, -value, pId);
    //            redisClient.zadd(key, -value, pId);
    //        }else{
    //            int lastValue = (int)Math.abs(redisClient.zrangeWithScores(key, total-1, total).iterator().next().getScore());
    //            if(value > lastValue){
    //               // nosql.zAdd(key, -value, pId);
    //                redisClient.zadd(key, -value, pId);
    //            }
    //        }
    //    }
}