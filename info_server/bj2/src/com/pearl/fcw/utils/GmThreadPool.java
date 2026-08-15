package com.pearl.fcw.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.gm.dao.WGmLogDao;
import com.pearl.fcw.gm.dao.WGmTransactionDao;
import com.pearl.fcw.gm.dao.WGmUserDao;
import com.pearl.fcw.gm.pojo.WChannel;
import com.pearl.fcw.gm.pojo.WGmLog;
import com.pearl.fcw.gm.pojo.WGmTransaction;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WServer;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.gm.pojo.WSysActivity;
import com.pearl.fcw.gm.pojo.WSysBannedWord;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.pojo.WSysChest;
import com.pearl.fcw.gm.pojo.WSysConfig;
import com.pearl.fcw.gm.pojo.WSysGrowthMission;
import com.pearl.fcw.gm.pojo.WSysIcon;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.pojo.WSysMission;
import com.pearl.fcw.gm.pojo.WSysOnlineAward;
import com.pearl.fcw.gm.pojo.WSysRank;
import com.pearl.fcw.gm.pojo.WSysStrengthenAppend;
import com.pearl.fcw.proto.enums.EOnlineAwardType;
import com.pearl.o2o.dao.impl.nonjoin.PaymentDao;
import com.pearl.o2o.dao.impl.nonjoin.ServerDao;
import com.pearl.o2o.dao.impl.nonjoin.SysCharacterDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemLogDao;
import com.pearl.o2o.dao.impl.nonjoin.SysLevelDao;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysCharacterLog;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.DaoCacheUtil;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 针对GM操作中读写数据库和网络清理更新不同步而部署的线程池
 */
public class GmThreadPool {
    @Resource
    private SysItemDao sysItemDao;
    @Resource
    private SysItemLogDao sysItemLogDao;
    @Resource
    private SysCharacterDao sysCharacterDao;
    @Resource
    private SysLevelDao sysLevelDao;
    @Resource
    private ServerDao serverDao;
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private WGmUserDao wGmUserDao;
    @Resource
    private WGmLogDao wGmLogDao;
    @Resource
    private WGmTransactionDao wGmTransactionDao;
    //    @Resource
    //    private WLogService wLogService;
    @Resource
    private MemcachedClient memcachedClient;
    @Resource
    private SocketClientNew socketClientNew;

    private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService gmThreadPool = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

    }

    public GmThreadPool() {
        //        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        //        System.setProperty("java.util.List.useLegacyMergeSort", "true");
    }

    /**
	 * 修改系统表后清理本地和网络缓存，并且记录日志
	 * @param m
	 * @param gmUser
	 */
    public void update(BaseEntity m, GmUser gmUser) {
		gmThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //                    Date now = new Date();
                    if (m instanceof WChannel) {
                        WChannel w = (WChannel) m;
                        memcachedClient.delete(CacheUtil.oChannelList(w.getServerId()));
                        socketClientNew.refreashServerList();
                    } else if (m instanceof WServer) {
                        serverDao.getServerMap().clear();
                        memcachedClient.delete(CacheUtil.oServerList());
                    } else if (m instanceof WPayment) {
                        WPayment w = (WPayment) m;
                        memcachedClient.delete(CacheUtil.oPaymentList(w.getItemId()));
                        SysItem sysItem = sysItemDao.getSystemItemById(w.getItemId());
                        ServiceLocator.getService.joinPayment(sysItem);
                    } else if (m instanceof WSysCharacter) {
                        WSysCharacter w = (WSysCharacter) m;
                        memcachedClient.delete(CacheUtil.oSysCharacterList());
                        SysCharacter sysCharacter = sysCharacterDao.getSysCharacterById(w.getId());
                        SysCharacterLog characterLog = new SysCharacterLog();
                        characterLog.setSysCharacter(sysCharacter);
                    } else if (m instanceof WSysBannedWord) {

                    } else if (m instanceof WSysIcon) {

                    } else if (m instanceof WSysItem) {
                        WSysItem w = (WSysItem) m;
						//准备记录日志
                        //                        SysItem oldSysItem = sysItemDao.getSystemItemById(w.getId());
						//清除本地和网络缓存的SysItem相关数据
                        if (!StringUtil.isEmpty(w.getCharacterId())) {
                            for (int i : Arrays.asList(w.getCharacterId().split(",")).stream().map(Integer::valueOf).collect(Collectors.toList())) {
                                memcachedClient.delete(CacheUtil.sShop((int) w.getType(), i));
                                memcachedClient.delete(CacheUtil.oShop((int) w.getType(), i));
                            }
                        }
                        memcachedClient.delete(DaoCacheUtil.oCacheKey(SysItem.class, 1));
                        sysItemDao.getAllSysItemMap().clear();
						//记录日志
                        SysItem sysItem = sysItemDao.getSystemItemById(w.getId());
                        sysItemDao.getAllSysItemMap().put(sysItem.getId(), sysItem);
                        //                        wLogService.writeGmLog(new GmUpdateLog(gmUser, oldSysItem, sysItem, now));
                        SysItemLog sysItemLog = new SysItemLog();
                        sysItemLog.setSysItem(sysItem);
                        sysItemLog.setLogVersion(ServiceLocator.getService.getLogVersionFromSysItemLog(sysItem.getId()) + 1);
                        sysItemLogDao.createSysItemLog(sysItemLog);
                    } else if (m instanceof WSysLevel) {
                        memcachedClient.delete(CacheUtil.oLevelList());
                        socketClientNew.refreashLevelList();
					} else if (m instanceof WSysActivity) {
						memcachedClient.delete(CacheUtil.oCurrentActivityMap());
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "A");
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "B");
						socketClientNew.refreashSysConfig();
                    }
                } catch (Exception e) {
					logger.error("", e);
                }
            }
        });
    }

    /**
	 * 新增系统表后清理本地和网络缓存，并且记录日志
	 * @param m
	 * @param gmUser
	 */
    public void add(BaseEntity m, GmUser gmUser) {
        gmThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (m instanceof WChannel || m instanceof WServer) {
                        memcachedClient.delete(CacheUtil.oServerList());
                        socketClientNew.refreashServerList();
                    } else if (m instanceof WPayment) {
                        WPayment w = (WPayment) m;
                        memcachedClient.delete(CacheUtil.oPaymentList(w.getItemId()));
                        SysItem sysItem = sysItemDao.getSystemItemById(w.getItemId());
                        ServiceLocator.getService.joinPayment(sysItem);
                    } else if (m instanceof WSysCharacter) {
                        memcachedClient.delete(CacheUtil.oSysCharacterList());
                        WSysCharacter w = (WSysCharacter) m;
                        SysCharacter sysCharacter = sysCharacterDao.getSysCharacterById(w.getId());
                        SysCharacterLog characterLog = new SysCharacterLog();
                        characterLog.setSysCharacter(sysCharacter);
                        characterLog.setLogVersion(0);
                    } else if (m instanceof WSysBannedWord) {

                    } else if (m instanceof WSysIcon) {

                    } else if (m instanceof WSysItem) {
                        WSysItem w = (WSysItem) m;
						//清除本地和网络缓存的SysItem相关数据
                        memcachedClient.delete(DaoCacheUtil.oCacheKey(SysItem.class, 1));
                        sysItemDao.getAllSysItemMap().clear();
						//记录日志
                        SysItem sysItem = sysItemDao.getSystemItemById(w.getId());
                        SysItemLog sysItemLog = new SysItemLog();
                        sysItemLog.setId(null);
                        sysItemLog.setSysItem(sysItem);
                        sysItemLog.setLogVersion(0);
                        sysItemLogDao.createSysItemLog(sysItemLog);
                        socketClientNew.messageCMD(CommonUtil.messageFormat(CommonMsg.REFRESH_SHOP, null));
                    } else if (m instanceof WSysLevel) {
                        memcachedClient.delete(CacheUtil.oLevelList());
                        socketClientNew.refreashLevelList();
					} else if (m instanceof WSysActivity) {
						memcachedClient.delete(CacheUtil.oCurrentActivityMap());
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "A");
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "B");
						socketClientNew.refreashSysConfig();
                    }
                } catch (Exception e) {
					logger.error("", e);
                }
            }
        });
    }

    /**
	 * 物理删除系统表后清理本地和网络缓存，并且记录日志
	 * @param m
	 * @param gmUser
	 */
    public void delete(BaseEntity m, GmUser gmUser) {
        gmThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (m instanceof WChannel || m instanceof WServer) {
                        memcachedClient.delete(CacheUtil.oServerList());
                        socketClientNew.refreashServerList();
                    } else if (m instanceof WPayment) {
                        WPayment w = (WPayment) m;
                        memcachedClient.delete(CacheUtil.oPaymentList(w.getItemId()));
                        SysItem sysItem = sysItemDao.getSystemItemById(w.getItemId());
                        ServiceLocator.getService.joinPayment(sysItem);
					} else if (m instanceof WSysAchievement) {

					} else if (m instanceof WSysActivity) {
						memcachedClient.delete(CacheUtil.oCurrentActivityMap());
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "A");
						memcachedClient.delete(CacheUtil.oCurrentActivityMap() + "B");
						socketClientNew.refreashSysConfig();
					} else if (m instanceof WSysBannedWord) {
						memcachedClient.delete(CacheUtil.oBannedWords());
						socketClientNew.refreashKeyWords();
						KeywordFilterUtil.loadKeyWords();
					} else if (m instanceof WSysCharacter) {
                        memcachedClient.delete(CacheUtil.oSysCharacterList());
					} else if (m instanceof WSysChest) {

					} else if (m instanceof WSysConfig) {
						memcachedClient.delete(CacheUtil.oSysConfigMap());
						socketClientNew.refreashSysConfig();
					} else if (m instanceof WSysGrowthMission) {

					} else if (m instanceof WSysIcon) {

                    } else if (m instanceof WSysItem) {
                        memcachedClient.delete(DaoCacheUtil.oCacheKey(SysItem.class, 1));
                        sysItemDao.getAllSysItemMap().clear();
                        sysItemDao.setClassifySysItemMap(null);
					} else if (m instanceof WSysItemGunProperty) {

                    } else if (m instanceof WSysLevel) {
                        memcachedClient.delete(CacheUtil.oLevelList());
                        socketClientNew.refreashLevelList();
					} else if (m instanceof WSysMission) {

					} else if (m instanceof WSysOnlineAward) {
						for (EOnlineAwardType p : EOnlineAwardType.values()) {
							if (!p.equals(EOnlineAwardType.UNRECOGNIZED)) {
								memcachedClient.delete(CacheUtil.oOnlineAwardList(p.getNumber()));
							}
						}
					} else if (m instanceof WSysRank) {

					} else if (m instanceof WSysStrengthenAppend) {

					}
                } catch (Exception e) {
					logger.error("", e);
                }
            }
        });
    }

    /**
	 * GM操作日志
	 * @param oldList
	 * @param newList
	 * @param gmUser
	 * @param methodName
	 */
    public void gmLog(LinkedList<BaseEntity> oldList, LinkedList<BaseEntity> newList, GmUser gmUser, String methodName, String type) {
        gmThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Date now = new Date();
                    WGmLog wGmLog = new WGmLog();
                    wGmLog.setGmUserId(gmUser.getId());
                    wGmLog.setGmUserName(gmUser.getName());
                    wGmLog.setRecordTime(now);
					System.out.println(oldList);
					System.out.println(newList);
					String className = (!oldList.isEmpty() && null != oldList.get(0)) ? oldList.get(0).getClass().getSimpleName() : newList.get(0).getClass().getSimpleName();
					wGmLog.setMethodName(oldList.isEmpty() ? methodName : methodName.replace("..", className));
                    wGmLog.setType(type);
                    wGmLog = wGmLogDao.add(wGmLog);
					//WGmLog的数据修改索引采用<Key:DmModel子类的简单名，Value:WGmTransaction表id>结构，防止Service的方法中同时修改多个不同的DmModel子类
                    for (int i = 0; i < oldList.size(); i++) {
                        WGmTransaction wGmTransaction = null;
                        BaseEntity oldM = oldList.get(i);
                        BaseEntity newM = newList.get(i);
                        if (null == oldM && null == newM) {
                            continue;
                        }
                        Class<?> clazz = null == oldM ? newM.getClass() : oldM.getClass();
                        Map<String, List<Object>> diff = CompareUtil.compareDmModel(oldM, newM);
						//如果新旧数据的数据库字段没有差异，就不生成WGmTransaction数据，WGmLog数据中也没有针对该组数据的修改索引
                        if (!diff.isEmpty()) {
                            wGmTransaction = new WGmTransaction();
                            wGmTransaction.setGmUserId(gmUser.getId());
                            wGmTransaction.setModelName(clazz.getSimpleName());
                            wGmTransaction.setModelId(null == oldM ? newM.getId() : oldM.getId());
                            wGmTransaction.setCreateTime(now);
                            wGmTransaction.setHistory(JsonUtil.writeAsString(diff));
                            wGmTransaction.setLogId(wGmLog.getId());
                            wGmTransaction = wGmTransactionDao.add(wGmTransaction);
                        }
                    }
                } catch (Exception e) {
					logger.error("", e);
                }
            }
        });
    }

	public void shutdown() {
		gmThreadPool.shutdown();
	}
}
