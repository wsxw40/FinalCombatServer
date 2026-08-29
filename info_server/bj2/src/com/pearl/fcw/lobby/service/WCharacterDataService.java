package com.pearl.fcw.lobby.service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.pojo.WCharacterData;

public class WCharacterDataService extends DmServiceImpl<WCharacterData, Integer> {
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    //    @Resource
    //    private   RedisClient redisClient;
    //    @Resource
    //    private   SocketClientNew soClient;
    //    @Resource
    //    private WPlayerService wPlayerService;
    //    @Resource
    //    private WPlayerItemService wPlayerItemService;
    //    /**
    //     * 获得该玩家该职业的信息
    //     * @param playerId
    //     * @param characterId
    //     * @return
    //     * @throws Exception
    //     */
    //    public Character getCharacterByCharacterId(Integer playerId,Integer characterId)throws Exception {
    //        Character character=null;
    //        List<ProxyWCharacterData> characterList=getCharacterListById(playerId);
    //        List<Integer> fightNumList = new ArrayList<Integer>();
    //        boolean isNeedUpdate = false;
    //        for(ProxyWCharacterData ch:characterList){
    //            int fightNum = ch.getFightNum();
    //            int lastFightNum = ch.initFightNum();
    //            if(fightNum!=lastFightNum){
    //                isNeedUpdate = true;
    //            }
    //            fightNumList.add(fightNum);
    //            if(ch.getSysCharacter().getId()==characterId){
    //                character=ch;
    //            }
    //        }
    //        if(character==null){
    //            throw new BaseException(ExceptionMessage.NO_HAVE_THE_CHARACTER);
    //        }
    //        Collections.sort(fightNumList, new Comparator<Integer>(){
    //            @Override
    //            public int compare(Integer i1, Integer i2) {
    //                return i2.compareTo(i1);
    //            }
    //        });
    //        double maxCFightNum = 0;
    //        if(isNeedUpdate){
    //            //update charcter in cache
    //            String objKey=CacheUtil.oCharacterList(playerId);
    //            // TODO MCC先放这 START
    //            //mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, characterList,Constants.CACHE_TIMEOUT);
    //            // TODO MCC先放这 END
    //        }
    //        for(int i=0;i<fightNumList.size();i++){
    //            maxCFightNum += fightNumList.get(i)*Math.pow(Constants.FUNDUS_NUM, i);
    //        }
    //        ProxyWPlayer pwPlayer = wPlayerService.findWPlayerById(playerId);
    //        String totalFightNumKey = NosqlKeyUtil.fightNumInRealTopByType("0");
    //        if((int)maxCFightNum!=pwPlayer.maxFightNum().get()){
    //                soClient.puchCMDtoClient( pwPlayer.name().get(), CommonUtil.messageFormat(CommonMsg.REFRESH_FIGHT_NUM,""+maxCFightNum));
    //                pwPlayer.maxFightNum().set((int)maxCFightNum);
    //                wPlayerService.update(pwPlayer);
    //            }
    //        int totalFightNumScore =  (int)Math.abs(redisClient.zScore(totalFightNumKey, String.valueOf(playerId)));
    //        if((int)maxCFightNum!=totalFightNumScore){
    //        redisClient.zadd(totalFightNumKey, -maxCFightNum, String.valueOf(playerId));//为了保证按照战斗力由大到小排列，将真实战斗力符号取反
    //        }
    //        String fightnumKey = NosqlKeyUtil.fightNumInRealTopByType(String.valueOf(characterId));
    //        int fightNumScore = (int)Math.abs(redisClient.zScore(fightnumKey, String.valueOf(playerId)));
    //        if(character.getFightNum()!=fightNumScore){
    //            redisClient.zAdd(fightnumKey, -character.getFightNum(), String.valueOf(playerId));
    //        }
    //        return character;
    //}
    //    /**
    //     * 根据玩家id获得职业列表
    //     * @param playerId
    //     * @return
    //     */
    //    public List<ProxyWCharacterData> getCharacterListById(Integer playerId) {
    //        EntityManager e;
    //        e.findProxyMap(ProxyWCharacterData::new, WCharacterData::new, WCharacterData.class, playerId);
    //        String objKey=CacheUtil.oCharacterList(playerId);
    //        List<Character> characterList=new ArrayList<Character>();
    ////      characterList=mcc.get(objKey,Constants.CACHE_TIMEOUT);
    //        if(characterList==null){
    //            characterList=new ArrayList<Character>();
    //            List<Integer> characterIds=new ArrayList<Integer>();
    //            characterIds=playerPackDao.getCharacterIdFromPlayerPack(playerId);
    //            if(characterIds.size()!=0){
    //                for(Integer id:characterIds){
    //                    List<PlayerItem> costumeList=wPlayerItemService.getCostumePackList(playerId, Constants.NUM_ONE, id);
    //                    List<PlayerItem> weaponList=wPlayerItemService.getWeaponPackList(playerId, Constants.NUM_ONE, id);
    //                    SysCharacter sysCharacter=sysCharacterDao.getSysCharacterById(id);
    //                    Character character=new Character();
    //                    character.setPlayerId(playerId);
    //                    character.setSysCharacter(sysCharacter);
    //                    character.setWeaponList(weaponList);
    //                    character.setCostumeList(costumeList);
    //                    character.putOnCostume();
    //                    character.initFightNum();
    //                    characterList.add(character);
    //                }
    //            }
    ////            if(characterList.size()!=0){
    ////             // TODO MCC先放这 START
    ////                //mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, characterList,Constants.CACHE_TIMEOUT);
    ////                // TODO MCC先放这 END
    ////            }
    //        }
    //        return characterList;
    //    }
    //
    //    
    //    /**
    //     * 获得玩家战斗力排名
    //     * @param playerId
    //     * @param cid 0:总战斗力,1：火箭兵.......
    //     * @return
    //     * @throws Exception
    //     */
    //    public int getPlayerFightNumRankInTop(int playerId , String cid) throws Exception{
    //        String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
    //        ProxyWPlayer pwPlayer = wPlayerService.findWPlayerById(playerId);
    //        int chid = Integer.parseInt(cid);
    //        int rank = (int)redisClient.zRank(key, String.valueOf(playerId)) + 1;
    //        if (rank > 0 && rank <= Constants.REAL_TOP_RANK_NUM) {//如果玩家的排名在前15000内直接返回排名，否则要估算
    //            return rank;
    //        }
    //        int value = 0;
    //        double firstValue = 0;
    //        double lastValue = 0;
    //        Iterator<Tuple> firstItrt = redisClient.zrangeWithScores(key, 0, 0).iterator();
    //        Iterator<Tuple> lastItrt = redisClient.zrangeWithScores(key, -1, -1).iterator();
    //        if(firstItrt.hasNext()){
    //            firstValue = Math.abs(firstItrt.next().getScore());
    //            if(lastItrt.hasNext()){
    //                lastValue= Math.abs(lastItrt.next().getScore());
    //            }
    //        }
    //        int total = (int)redisClient.zCard(key);
    //        if(total>Constants.REAL_TOP_RANK_NUM){
    //            lastValue = Math.abs(redisClient.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
    //        }
    //        if (chid != 0) {//0 表示综合战斗力
    //            Character character = getCharacterByCharacterId(playerId, Integer.parseInt(cid));
    //            value = character.getFightNum();
    //        }else{
    //            //value = player.getMaxFightNum();
    //            value = pwPlayer.maxFightNum().get();
    //        }
    //        if(rank>Constants.REAL_TOP_RANK_NUM){
    //            rank = computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
    //        }else if(rank==0){
    //            String pId = String.valueOf(playerId);
    //            if(total<Constants.REAL_TOP_RANK_NUM){
    //                redisClient.zAdd(key, -value, pId);
    //                rank  = (int)redisClient.zRank(key, pId) + 1;
    //            }else{
    //                if(value > lastValue){
    //                    redisClient.zAdd(key, -value, pId);
    //                    rank  = (int)redisClient.zRank(key, pId) + 1;
    //                }else{
    //                    rank = computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
    //                }
//            }
    //        }
    //        return rank;
    //    }
    //    
    //    /**
    //     * 
    //     * @param value
    //     * @param firstValue
    //     * @param lastValue
    //     * @param rankNum
    //     * @return
    //     */
    //    public int computeCommmonRank(double value,double firstValue ,double lastValue, int rankNum){
    //        if(value>=lastValue){
    //            return rankNum + 1;
    //        }
    //        int rank = rankNum + 1 + (int)(((lastValue - value)/(firstValue -lastValue))*rankNum);
    //        return rank;
    //    }
}
