package com.pearl.fcw.lobby.service;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.pojo.WPlayerItem;

/**
 * 玩家
 */
@Service
public class WPlayerItemService extends DmServiceImpl<WPlayerItem, Integer> {
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    //  
    //    @Resource
    //    private LobbyCacheKey lobbyCacheKey;
    //    @Resource
    //    private RedisClient redisClient;
    //    @Resource
    //    private EntityManager entityManager;
    //    @Resource
    //    private ProtoConverter protoConverter;
    //    
    //    @Resource
    //    private WPlayerItemDao wPlayerItemDao;
    //
    //    @PostConstruct
    //    public void init() {
    //        this.genDao = wPlayerItemDao;
    //    }
    //    /**
    //     * 根据背包号，获取该背包下所有武器
    //     * @param playerId
    //     * @param packId
    //     * @return 包含PLAYER ITEM的LIST
    //     * @throws Exception
    //     */
    //    @SuppressWarnings("unchecked")
    //    public List<PlayerItem> getWeaponPackList(final int playerId, final int packId,final int characterId) throws Exception {
    //        String objKey=CacheUtil.oWeaponPack(playerId, packId,characterId);
    //        List<PlayerItem> packList = (List<PlayerItem>) loadValue(objKey, new CacheMissHandler() {
    //            @Override
    //            public Object loadFromDB(GetService service) throws Exception {
    //
    //                try{
    //                    //所有未删除的武器道具
    //                    List<PlayerItem> playerItemList = getPlayerItem_s1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
    //                    //所有有效的背包条
    //                    List<PlayerPack> playerPackList = playerPackDao.getWeaponPackByPackId(playerId, characterId);
    //                    List<PlayerItem> packList = new ArrayList<PlayerItem>();
    //                    for (PlayerPack pp : playerPackList) {
    //                        if (pp.getPlayerItemId() != null) {
    //                            PlayerItem item=null;
    //                            for(PlayerItem pi:playerItemList){
    //                                if(null != pi && ((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
    //                                    if(pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
    //                                        pi.calculateTimeLeft();
    //                                    }
    //                                    item=pi;
    //                                    packList.add(pi);
    //                                    break;
    //                                }
    //                            }
    //                            if (item == null) {
    //                                boolean normal=false;
    //                                for (PlayerItem pi : playerItemList) {
    //                                    SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
    //                                    if (pi.getIsDefault().equals("Y") && CommonUtil.getWeaponSeq(si.getWId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
    //                                        log.warn("find the root cause of weaponlist,seq ="+pp.getSeq()+" itemId="+pi.getId()+"playerId="+pp.getPlayerId());
    //                                        packList.add(pi);
    //                                        pp.setPlayerItemId(pi.getId());
    //                                        playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
    //                                        mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
    //                                        ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
    //                                        mcc.delete(CacheUtil.oCharacterList(playerId));
    //                                        normal=true;
    //                                        break;
    //                                    }
    //                                }
    //                                if(!normal&&pp.getSeq()==4){
    //                                    packList.add(null);
    //                                    normal=true;
    //                                }
    //                                if(!normal){
    //                                    log.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
    //                                }
    //                            }
    //                        }else{
    //                            packList.add(null);
    //                        }
    //                    }
    //                    return packList;
    //                }catch(Exception e){
    //                    log.warn("exception in getWeaponPackList, playerId=" + playerId, e);
    //                    throw e;
    //                }
    //            }
    //        });
    //        if(packList.size()!=4&&characterId<10&&characterId!=0){
    //            log.warn("WeaponPack size="+packList.size()+" playerId="+playerId+" characterId="+characterId+".start to reset the character pack");
    //            packList=new ArrayList<PlayerItem>();
    //            mcc.delete(CacheUtil.oWeaponPack(playerId, packId, characterId));
    //            mcc.delete(CacheUtil.oCharacterList(playerId));
    //            List<PlayerItem> playerItemList = getPlayerItemList1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
    //            List<PlayerPack> playerPackList = playerPackDao.getWeaponPackByPackId(playerId, characterId);
    //            for(PlayerPack pp : playerPackList){
    //                if(pp.getPlayerItemId() != null){//have item
    //                    PlayerItem item=null;
    //                    for(PlayerItem pi:playerItemList){
    //                        SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
    //                        if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()){
    //                            packList.add(pi);
    //                            break;
    //                        }
    //                    }
    //                    if(item==null){//equip item miss
    //                        log.warn("find the root cause of weaponlist size,seq ="+pp.getSeq()+" itemId="+pp.getPlayerItemId()+"playerId="+pp.getPlayerId());
    //                        for(PlayerItem pi:playerItemList){
    //                            SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
    //                            if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()&&isFitCharacter(si, characterId)){
    //                                packList.add(pi);
    //                                ServiceLocator.updateService.updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), characterId);
    //                                break;
    //                            }
    //                        }
    //                    }
    //                }else if(pp.getSeq()==4&&characterId!=4){//other character fourth item
    //                    packList.add(null);
    //                }else{//leader fourth item and other character's item
    //                    for(PlayerItem pi:playerItemList){
    //                        SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
    //                        if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()&&isFitCharacter(si, characterId)){
    //                            packList.add(pi);
    //                            ServiceLocator.updateService.updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), characterId);
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //
    //        }
    //        return packList;
    //    }
    //    /**
    //     * method to get costume pack
    //     * @param playerId
    //     * @param packId
    //     * @param side
    //     * @return
    //     * @throws Exception
    //     */
    //    @SuppressWarnings("unchecked")
    //    public List<PlayerItem> getCostumePackList(final int playerId, final int packId,final int characterId) throws Exception {
    //        String objKey=CacheUtil.oCostumePack(playerId, packId, characterId);
    //        List<PlayerItem> packList = (List<PlayerItem>) loadValue(objKey, new CacheMissHandler() {
    //            @Override
    //            public Object loadFromDB(GetService service) throws Exception {
    //                List<PlayerItem> playerItemList = getPlayerItem_s1(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
    //                List<PlayerItem> playerItemList2 = getPlayerItem_s1(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
    //                List<PlayerPack> playerPackList = playerPackDao.getCostumePackByPackId(playerId, characterId);
    //                List<PlayerItem> packList = new ArrayList<PlayerItem>();
    //                for(PlayerPack pp : playerPackList){
    //                    if(pp.getPlayerItemId() != null){
    //                        PlayerItem item=null;
    //                        for(PlayerItem pi:playerItemList){
    //                            if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
    //                                if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
    //                                    pi.calculateTimeLeft();
    //                                }
    //                                item=pi;
    //                                packList.add(pi);
    //                                break;
    //                            }
    //                        }
    //                        for(PlayerItem pi:playerItemList2){
    //                            if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
    //                                if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
    //                                    pi.calculateTimeLeft();
    //                                }
    //                                item=pi;
    //                                packList.add(pi);
    //                                break;
    //                            }
    //                        }
    //
    //                        if (item == null) {
    //                            boolean normal=false;
    //                            for (PlayerItem pi : playerItemList) {
    //                                SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
    //                                if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
    //                                    log.warn("find the root cause of costumelist,seq ="+pp.getSeq()+" itemId="+pi.getId()+"playerId="+pp.getPlayerId());
    //                                    packList.add(pi);
    //                                    pp.setPlayerItemId(pi.getId());
    //                                    playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
    //                                    mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
    //                                    ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
    //                                    mcc.delete(CacheUtil.oCharacterList(playerId));
    //                                    log.debug("run updateMappingBeanInCache playerItemId=" + pp.getPlayerItemId());
    //                                    normal=true;
    //                                    break;
    //                                }
    //                            }
    //                            if(!normal){
    //                                for (PlayerItem pi : playerItemList2) {
    //                                    SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
    //                                    if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
    //                                        packList.add(pi);
    //                                        pp.setPlayerItemId(pi.getId());
    //                                        playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
    //                                        mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
    //                                        ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
    //                                        mcc.delete(CacheUtil.oCharacterList(playerId));
    //                                        log.debug("run updateMappingBeanInCache playerItemId=" + pp.getPlayerItemId());
    //                                        normal=true;
    //                                        break;
    //                                    }
    //                                }
    //                            }
    //                            if(!normal){
    //                                log.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
    //                            }
    //                        }
    //                    }else{
    //                        packList.add(null);
    //                    }
    //                }
    //                return packList;
    //            }
    //        });
    //        if(packList.size()!=3&&characterId<10){
    //            log.warn("CostumePack size="+packList.size()+" playerId="+playerId+".start to reset the character pack");
    //            packList=new ArrayList<PlayerItem>();
    //            soClient.puchCMDtoClient(getSimplePlayerById(playerId).getName(), CommonUtil.messageFormat(CommonMsg.WARN_MSG, CommonMsg.WARN_MSG_STR));
    //            mcc.delete(CacheUtil.oCostumePack(playerId, packId, characterId));
    //            mcc.delete(CacheUtil.oCharacterList(playerId));
    //            List<PlayerItem> playerItemList = getPlayerItemList1(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
    //            List<PlayerItem> playerItemList2 = getPlayerItemList1(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
    //            List<PlayerPack> playerPackList = playerPackDao.getCostumePackByPackId(playerId, characterId);
    //            for(PlayerPack pp : playerPackList){
    //                if(pp.getPlayerItemId() != null){
    //                    PlayerItem item=null;
    //                    for(PlayerItem pi:playerItemList){
    //                        if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
    //                            if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
    //                                pi.calculateTimeLeft();
    //                            }
    //                            item=pi;
    //                            packList.add(pi);
    //                            break;
    //                        }
    //                    }
    //                    for(PlayerItem pi:playerItemList2){
    //                        if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
    //                            if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
    //                                pi.calculateTimeLeft();
    //                            }
    //                            item=pi;
    //                            packList.add(pi);
    //                            break;
    //                        }
    //                    }
    //
    //                    if (item == null) {
    //                        boolean normal=false;
    //                        for (PlayerItem pi : playerItemList) {
    //                            SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
    //                            if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
    //                                packList.add(pi);
    //                                ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
    //                                normal=true;
    //                                break;
    //                            }
    //                        }
    //                        if(!normal){
    //                            for (PlayerItem pi : playerItemList2) {
    //                                SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
    //                                if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
    //                                    packList.add(pi);
    //                                    ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
    //                                    normal=true;
    //                                    break;
    //                                }
    //                            }
    //                        }
    //                        if(!normal){
    //                            logger.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
    //                        }
    //                    }
    //                }
    //                else{
    //                    for(PlayerItem pi:playerItemList){
    //                        if(pi.getIsDefault().equals("Y")&&CommonUtil.getCotumeSeq(pi.getSysItem().getCId())==pp.getSeq()){
    //                            packList.add(pi);
    //                            ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
    //                            break;
    //                        }
    //                    }
    //                    for(PlayerItem pi:playerItemList2){
    //                        if(pi.getIsDefault().equals("Y")&&CommonUtil.getCotumeSeq(pi.getSysItem().getCId())==pp.getSeq()){
    //                            packList.add(pi);
    //                            ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //        return packList;
    //    }
    //    /**
    //     * 需要提交的任务标示
    //     * @param pwPlayer
    //     * @throws Exception
    //     */
    //    public void update(ProxyWPlayer pwPlayer) throws Exception {
    //        entityManager.update(pwPlayer);
    //    }
}
