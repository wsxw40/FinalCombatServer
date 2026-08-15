package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.cache.KryoSerializer;
import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.dao.WSysAchievementDao;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.utils.GameThreadPool;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

@Service
public class TestService extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -2662375948295212903L;

    @Resource
    private EntityManager entityManager;
    @Resource
    private MemcachedClient memcachedClient;
    @Resource
    private WPlayerService wPlayerService;
    @Resource
    private ShopService shopService;
    @Resource
    private StorageService storageService;
    @Resource
    private CharacterService characterService;
    @Resource
    private WSysAchievementDao wSysAchievementDao;
    @Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private ProtoConverter protoConverter;
    @Resource
    private WTeamService wTeamService;
    @Resource
    private GameThreadPool gameThreadPool;
	@Resource
	private KryoSerializer kryoSerializer;

    @Resource
    private TestService testService;

    @Override
    public String innerService(String... args) {
        try {

            //            int i = 0;
            //            for (int type = 1; type <= 10; type++) {
            //                for (PlayerItem pi : ServiceLocator.getService.getPlayerItems(Integer.parseInt(args[0].toString()), type, 0, 0)) {
            //                    pi.setLeftSeconds(0);
            //                    ServiceLocator.updateService.updatePlayerItem(pi);
            //                }
            //            }
            return testService.rpc(args);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        //        testNatureEhcachePlayerItem(5000);
		System.out.println(System.currentTimeMillis());
        int playerId = Integer.parseInt(args[0].toString());
		for (int i = 0; i < 10; i++) {
			ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
			pwPlayer.gPoint().set(pwPlayer.gPoint().get());
			Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
			pwPlayerPackMap.forEach((k, v) -> {
				v.seq().set(v.seq().get());
			});
			Map<Serializable, ProxyWCharacterData> pwCharacterDataMap = wPlayerService.findProxyWCharacterDataMap(playerId);
			pwCharacterDataMap.forEach((k, v) -> {
				v.kill().set(v.kill().get());
			});
			Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
			ProxyWPlayerItem pwPlayerItem = pwPlayerItemMap.entrySet().stream().findAny().get().getValue();
			pwPlayerItem.durable().set(pwPlayerItem.durable().get());
		}
		System.out.println(System.currentTimeMillis());

        //        Map<Serializable, ProxyWPlayerInfo> map = entityManager.findProxyMap(ProxyWPlayerInfo::new, WPlayerInfo::new, WPlayerInfo.class, playerId);
        //        //        System.out.println(map.size());
        //        map.values().forEach(p -> {
        //            try {
        //                ProtoPlayerInfoProto proto = p.protoEntity().get();
        //                long time = proto.getLastHour2HourAwardTime();
        //                System.out.println("time : " + time);
        //                System.out.println(proto.getDiscount().getFlagList());
        //                Date now = new Date();
        //                ProtoPlayerInfoProto.Builder builder = proto.toBuilder();
        //                builder.setLastHour2HourAwardTime(now.getTime());
        //                builder.setDiscount(protoConverter.protoDiscount(wSysItemDao.findEntity(9), Arrays.<Float> asList(0f, 0.1f, 0.2f), Arrays.asList(false, true, false), true));
        //                proto = builder.build();
        //                p.protoEntity().set(proto);
        //                time = proto.getLastHour2HourAwardTime();
        //                System.out.println("time : " + time);
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //        });

        //        ProxyWPlayer proxyM = entityManager.findProxy(ProxyWPlayer::new, WPlayer.class, Integer.parseInt(args[0].toString()));
        //        if (null == proxyM) {
        //            return "";
        //        }
        //        System.out.println("gold : " + proxyM.gold().get() + " version : " + proxyM.get().getDbVersion());
        //        proxyM.gold().set(0);
        //        proxyM.teamDead().set(0);
        //        proxyM.teamKill().set(0);
        //        proxyM.teamScore().set(0);
        //        proxyM.teamHeadShot().set(0);
        //        entityManager.update(proxyM);
        //        entityManager.remove(proxyM);

        //                Integer id = Integer.parseInt(args[0].toString());
        //        Map<Serializable, ProxyWPlayerItem> map = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, id);
        //        List<ProxyWPlayerItem> list = map.values().stream().collect(Collectors.toList());
        //        for (int i = 0; i < 10; i++) {
        //            if (list.isEmpty()) {
        //                break;
        //            }
        //            int index = new Random().nextInt(list.size());
        //            ProxyWPlayerItem proxyM = list.get(index);
        //            proxyM.leftSeconds().set(1);
        //            entityManager.update(proxyM);
        //        }
        //        for (ProxyWPlayerItem v : map.values()) {
        //            v.leftSeconds().set(0);
        //            entityManager.update(v);
        //        }

        //        Map<Serializable, ProxyWPlayerAchievement> map = entityManager.findProxyMap(ProxyWPlayerAchievement::new, WPlayerAchievement::new, WPlayerAchievement.class, playerId);
        //        for (ProxyWPlayerAchievement pw : map.values()) {
        //            if (pw.id().eq(1) && pw.get().getSysCharacterId() == 1) {
        //                pw.number().increse(1);
        //                pw.sysAchievementIdsList().forEach(p -> {
        //                    System.out.println(p.get().getValue());
        //                });
        //            }
        //        }

        //        entityManager.findProxyMap(ProxyWPlayerMelting::new, WPlayerMelting::new, WPlayerMelting.class, playerId).values().stream().findAny().get().level().increse(1);

        //        ProxyWTeamLevel pwTeamLevel = wTeamService.findProxyWTeamLevelMap(7076).values().stream().findFirst().get();
        //        pwTeamLevel.configPointsList().remove(0);
        //        pwTeamLevel.configPointsList().get(0).r1().set(0F);
        //        TeamLevelConfigPoints tl = new TeamLevelConfigPoints();
        //        tl.setR1(0F);
        //        tl.setR2(0F);
        //        tl.setR3(0F);
        //        tl.setR4(0F);
        //        tl.setSysItemId(0);
        //        tl.setX(0F);
        //        tl.setY(0F);
        //        tl.setZ(0F);
        //        List<TeamLevelConfigPoints> list = new ArrayList<TeamLevelConfigPoints>();
        //        list.add(tl);
        //        list.add(tl);
        //        pwTeamLevel.configPointsList().set(list);
        //        pwTeamLevel.configPointsList().addAll(list);
        return "success1";
    }

    @Override
    public String getLockedKey(String... args) {
        return args[0].toString();
    }

    //    /**
	//     * 性能测试Memcached Player
    //     */
    //    private void testNatureMemcachedPlayer(int num) {
    //        System.out.println("#####Test Memcached Player#####");
    //        long t1 = System.currentTimeMillis();
    //        System.out.println("startTime:" + t1);
    //        int counter = 0;
    //        int dataCount = 0;
    //        for (int i = 10637962; i < 10637962 + num; i++) {
    //            String key = "$tt|WPlayer|" + i;
    //            try {
    //                Object obj = memcachedClient.get(key);
    //                if (null == obj) {
    //                    WPlayer wPlayer = wPlayerService.findEntity(i);
    //                    memcachedClient.set(key, 100000, wPlayer);
    //                }
    //            } catch (Exception e) {
    //                continue;
    //            }
    //            counter++;
    //            dataCount++;
    //        }
    //        long t2 = System.currentTimeMillis();
    //        System.out.println("endTime:" + t2);
    //        System.out.println("playerCount:" + counter);
    //        System.out.println("dataCount:" + dataCount);
    //        System.out.println("interval:" + (t2 - t1) * 0.001 + "s");
    //    }
    //
    //    /**
	//     * 性能测试Ehcache+Redis Player
    //     */
    //    private void testNatureEhcachePlayer(int num) {
    //        System.out.println("#####Test Ehcache Player#####");
    //        long t1 = System.currentTimeMillis();
    //        System.out.println("startTime:" + t1);
    //        int counter = 0;
    //        int dataCount = 0;
    //        for (int i = 10637962; i < 10637962 + num; i++) {
    //            try {
    //                entityManager.findProxy(ProxyWPlayer::new, WPlayer.class, i);
    //            } catch (Exception e) {
    //                continue;
    //            }
    //            counter++;
    //            dataCount++;
    //        }
    //        long t2 = System.currentTimeMillis();
    //        System.out.println("endTime:" + t2);
    //        System.out.println("playerCount:" + counter);
    //        System.out.println("dataCount:" + dataCount);
    //        System.out.println("interval:" + (t2 - t1) * 0.001 + "s");
    //    }
    //
    //    /**
	//     * 性能测试Memcached PlayerItem
    //     */
    //    @SuppressWarnings("unchecked")
    //    private void testNatureMemcachedPlayerItem(int num) {
    //        System.out.println("#####Test Memcached PlayerItem#####");
    //        long t1 = System.currentTimeMillis();
    //        System.out.println("startTime:" + t1);
    //        int counter = 0;
    //        int dataCount = 0;
    //        for (int i = 10637962; i < 10637962 + num; i++) {
    //            String key = "$tt|WPlayerItem|" + i;
    //            try {
    //                Object obj = memcachedClient.get(key);
    //                if (null == obj) {
    //                    Map<Integer, WPlayerItem> map = wPlayerService.findPlayerItemMap(i);
    //                    for (Integer playerItemId : map.keySet()) {
    //                        memcachedClient.set(key + "|" + playerItemId, 1000000, map.get(playerItemId));
    //                    }
    //                    memcachedClient.set(key, 1000000, map.keySet().stream().collect(Collectors.toList()));
    //                    counter++;
    //                    dataCount += map.size();
    //                } else {
    //                    List<Integer> ids = (List<Integer>) obj;
    //                    for (Integer playerItemId : ids) {
    //                        memcachedClient.get(key + "|" + playerItemId);
    //                    }
    //                    counter++;
    //                    dataCount += ids.size();
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //                continue;
    //            }
    //        }
    //        long t2 = System.currentTimeMillis();
    //        System.out.println("endTime:" + t2);
    //        System.out.println("playerCount:" + counter);
    //        System.out.println("dataCount:" + dataCount);
    //        System.out.println("interval:" + (t2 - t1) * 0.001 + "s");
    //    }
    //
    //    /**
	//     * 性能测试Ehcache+Redis PlayerItem
    //     * @throws Exception
    //     */
    //    private void testNatureEhcachePlayerItem(int num) throws Exception {
    //        System.out.println("#####Test Ehcache PlayerItem#####");
    //        long t1 = System.currentTimeMillis();
    //        System.out.println("startTime:" + t1);
    //        int counter = 0;
    //        int dataCount = 0;
    //        for (int i = 10637962; i < 10637962 + num; i++) {
    //            try {
    //                Map<Serializable, ProxyWPlayerItem> map = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, i);
    //                if (!map.isEmpty()) {
    //                    counter++;
    //                    dataCount += map.size();
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //                continue;
    //            }
    //        }
    //        long t2 = System.currentTimeMillis();
    //        System.out.println("endTime:" + t2);
    //        System.out.println("playerCount:" + counter);
    //        System.out.println("dataCount:" + dataCount);
    //        System.out.println("interval:" + (t2 - t1) * 0.001 + "s");
    //    }
}
