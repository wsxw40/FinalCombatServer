package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.object.playerevent.RoomInEvent;
import com.pearl.o2o.pojo.BossPojo;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.LevelModeInfo.AABBPoints;
import com.pearl.o2o.pojo.LevelModeInfo.BannerPoints;
import com.pearl.o2o.pojo.LevelModeInfo.BossItem;
import com.pearl.o2o.pojo.LevelModeInfo.BossLinePoint;
import com.pearl.o2o.pojo.LevelModeInfo.Supply;
import com.pearl.o2o.pojo.LevelModeInfo.TeamPoints;
import com.pearl.o2o.pojo.LevelModeInfo.Vector3;
import com.pearl.o2o.pojo.LevelModeInfo.VehicleLinePoint;
import com.pearl.o2o.pojo.LevelModeInfo.WeaponPoint;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamLevelModeInfo;
import com.pearl.o2o.socket.Response;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryOut;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TeamConstants;

public class LevelInfo extends BaseServerServlet {

    private static final long serialVersionUID = 8449242457860139805L;
    private static Logger logger = LoggerFactory.getLogger(LevelInfo.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        super.service(req, res);
        try {
        } catch (Exception e) {
            logger.error("error happend when load level info.", e);
            res.sendError(500);
        }
    }

    private void writeVector(LevelModeInfo.Vector3 vector, BinaryOut out) throws IOException {
        out.writeFloat(vector.getX());
        out.writeFloat(vector.getY());
        out.writeFloat(vector.getZ());
    }

    private void writeTransform(LevelModeInfo.Transform transform, BinaryOut out) throws IOException {
        writeVector(transform.getPosition(), out);
        out.writeFloat(transform.getRotate());
    }

    private void writeWeaponPoints(LevelModeInfo.WeaponPoint weaponPoint, BinaryOut out, List<LevelWeapon> weapons, int type) throws Exception {
        if (type == weaponPoint.getType()) {
            //write the index  in weapon list instead of id 
            int weaponIndex = getIndexFromWeaponList(weapons, weaponPoint.getId());
            if (weaponIndex < 0) {
                throw new Exception("weapon:" + weaponPoint.getId() + "can not be found in weaponlist");
            }
            out.writeByte(weaponIndex);
            writeTransform(weaponPoint.getTransform(), out);
        }
    }

    private void writeTeamPoints(LevelModeInfo.TeamPoints tp, BinaryOut out) throws IOException {
        out.writeByte(tp.getTeamId());
        writeTransform(tp.getPoint(), out);
    }

    private void writeBannerPoints(LevelModeInfo.BannerPoints tp, BinaryOut out) throws IOException {
        out.writeByte(tp.getTeamId());
        writeAABBPoints(tp.getPoint(), out);
    }

    private void writeVehicleLinePoints(LevelModeInfo.VehicleLinePoint tp, BinaryOut out) throws IOException {
        writeVector(tp.getVecter(), out);
        writeVector(tp.getVecter2(), out);
        out.writeInt(tp.getIsSlope());
    }

    private void writeBossLinePoints(LevelModeInfo.BossLinePoint blp, BinaryOut out) throws IOException {
        out.writeInt(blp.getLineId());
        out.writeInt(blp.getIndex());
        writeVector(blp.getVecter(), out);
        writeVector(blp.getVecter2(), out);
        out.writeInt(blp.getIsSlope());
    }

    private void writeAABBPoints(LevelModeInfo.AABBPoints tp, BinaryOut out) throws IOException {
        writeTransform(tp.getTransform(), out);
        writeVector(tp.getPoint(), out);
    }

    private void writeSupply(LevelModeInfo.Supply supply, BinaryOut out, List<LevelWeapon> weapons) throws Exception {
        writeVector(supply.getVector(), out);
        out.writeByte(supply.getType());
        out.writeString(supply.getName());
        out.writeInt(supply.getValue());
        //if type is weapon, value is the index in the weapon-list
        out.writeFloat(supply.getAppearRation());
        out.writeFloat(supply.getSkillTime());
    }

    private void writeVehicleInfo(LevelModeInfo.VehicleInfo vehicle, BinaryOut out) throws Exception {
        out.writeInt(vehicle.getAddBlood());
        writeVector(vehicle.getVector(), out);
    }

    private static int getIndexFromWeaponList(List<LevelWeapon> weapons, int id) {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    //just for team level
    private void writeConfigPoints(TeamLevelModeInfo.ConfigPoint configPoint, final int teamId, BinaryOut out) throws Exception {

        TeamItem teamItem = getService.getFullTeamItemByTeamIdAndItemId(teamId, configPoint.getItemId());

        SysItem siItem = getService.getSysItemByItemId(configPoint.getItemId()).clone();

        if (teamItem != null) {
            siItem.setWDamage(teamItem.getProperty1().get("value").intValue());
            siItem.setWFireTime(teamItem.getProperty2().get("value").floatValue());
            siItem.setWHitAcceleration(teamItem.getProperty3().get("value").floatValue());
            siItem.setWHurtRange(teamItem.getProperty4().get("value").floatValue());
            siItem.setWShowSpeed(teamItem.getProperty5().get("value").floatValue());
            if (teamItem.getDurable() <= 0) { //无耐久
                siItem.setWAmmoOneClip(0);
                siItem.setWSpecialLasthurt(0f);
                siItem.setWUpModifier(0f);
                siItem.setWAccuracyTimeModefier(0f);
            }
            if (teamItem.getBullet() <= 0) { //无子弹
                siItem.setWAmmoOneClip(0);
                siItem.setWSpecialLasthurt(0f);
                siItem.setWUpModifier(0f);
            }
        }

        if (siItem != null) {

            out.writeByte(siItem.getSubType() == 2 ? 1 : 0); //type  0-2客户端用于区别墙还是塔
            out.writeByte(siItem.getSubType() - 1); //subtype

            out.writeInt(siItem.getLevel()); //level
            writeVector(configPoint.getPoint(), out); //position
            writeTransform(configPoint.getTransform(), out); //rotation
            out.writeString(siItem.getName()); //reskey
            out.writeString(siItem.getName()); //name
            out.writeInt(Math.round(siItem.getWHitAcceleration())); //max_hp
            out.writeInt(siItem.getWDamageModifer()); //W_DAMAGE_MODIFER
            out.writeFloat(siItem.getWRangeStart()); //wRangeStart
            out.writeFloat(siItem.getWRangeEnd()); //wRangeEnd
            out.writeFloat(siItem.getWRangeModifier()); //wRangeModifer
            out.writeInt(siItem.getWDamage()); //wDamage
            out.writeFloat(siItem.getWMinAccuracy()); //check_angle 
            out.writeFloat(siItem.getWHurtRange()); //check_range 机枪塔警戒范围
            out.writeFloat(siItem.getWShowSpeed()); //angle_speed 机枪塔转动角速度
            out.writeFloat(siItem.getWFireTime()); //fire_interval 机枪塔射速
            out.writeFloat(siItem.getWMaxaliveTime()); //life_time 机枪塔存活时间
            out.writeFloat(siItem.getWMaxDistance()); //distance 机枪塔的控制范围

            out.writeInt(siItem.getWAmmoOneClip()); //max_ammo_count 机枪塔弹药数
            out.writeInt(siItem.getWAmmoOneClip()); //current_ammo_count

            out.writeFloat(siItem.getWMaxAccuracy()); //move_speed 机枪塔移动速度
            out.writeFloat(siItem.getWAccuracyTime()); //move_keep_time 机枪塔移动时间

            out.writeFloat(siItem.getWSpecialRange()); //recover_range 治疗范围
            out.writeFloat(siItem.getWSpecialLasttime()); //recover_check_interval 治疗间隔
            out.writeInt(Math.round(siItem.getWSpecialHurt())); //recover_per_count_life 治疗数值（值）
            out.writeInt(Math.round(siItem.getWSpecialLasthurt())); //recover_per_percent_ammo 补充弹药值（百分比）
            out.writeInt(Math.round(siItem.getWUpModifier())); //recover_per_minus_ammo 补给消耗的弹药量（百分比）

            out.writeShort((short) Math.round(siItem.getWAccuracyTimeModefier())); //freezeCount   瘫痪次数 
            out.writeFloat(siItem.getWLastTime()); //freezetime    瘫痪到恢复攻击的时间
        }
    }

    class BufferedOutputStream extends FilterOutputStream {

        public BufferedOutputStream(OutputStream out) {
            super(out);
        }

        private List<Byte> bytes = new ArrayList<Byte>();

        @Override
        public void write(int b) throws IOException {
            bytes.add((byte) b);
        }

        @Override
        public void flush() throws IOException {
            for (Byte b : bytes) {
                super.write(b);
            }
            super.flush();
        }

    }

    @Override
    public ArrayList<Response> execute(BinaryChannelBuffer in) throws MemcachedException, TimeoutException, Exception {
        ArrayList<Response> responses = new ArrayList<Response>(1);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            final int lid = in.readInt();
            final int roomId = in.readInt();
            final int playerId = in.readInt();
            int serverId = in.readInt();
            final int channelId = in.readInt();
            final int isTeam = in.readByte();
            int count = in.readInt();
            Set<Integer> bossCharacterIdSet = new HashSet<Integer>();
            final List<Integer> team0 = new ArrayList<Integer>();
            for (int i = 0; i < count; i++) {
                team0.add(in.readInt());
            }
            count = in.readInt();
            final List<Integer> team1 = new ArrayList<Integer>();
            for (int i = 0; i < count; i++) {
                team1.add(in.readInt());
            }
            final int roomCreate = in.readInt();
            final int gameStart = in.readInt();
            int type = in.readByte();
            final int characterId = in.readByte();

            final LevelModeInfo levelAndMode = getService.getLevelModeInfoIncludeTeamLevel(lid);

            Runnable addXunleiTask = new Runnable() {

                @Override
                public void run() {
                    try {
                        if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
                            Player host = getService.getPlayerById(playerId);

                            nosqlService.addXunleiLog("2.1" + Constants.XUNLEI_LOG_DELIMITER + roomId + Constants.XUNLEI_LOG_DELIMITER + channelId + Constants.XUNLEI_LOG_DELIMITER
                                    + host.getUserName() + Constants.XUNLEI_LOG_DELIMITER + host.getRank() + Constants.XUNLEI_LOG_DELIMITER
                                    + CommonUtil.simpleDateFormat.format(new Date(roomCreate * 1000l)) + Constants.XUNLEI_LOG_DELIMITER + levelAndMode.getDisplayNameCN()
                                    + Constants.XUNLEI_LOG_DELIMITER + levelAndMode.getType() + Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date(gameStart * 1000l))
                                    + Constants.XUNLEI_LOG_DELIMITER + (team0.size() + team1.size()) + Constants.XUNLEI_LOG_DELIMITER + characterId + Constants.XUNLEI_LOG_DELIMITER + isTeam);
                            for (Integer cid : team0) {
                                Player player = getService.getPlayerById(cid);
                                nosqlService.publishEvent(new RoomInEvent(roomId, System.currentTimeMillis() / 1000, playerId, player.getName()));
                                ServiceLocator.nosqlService.addXunleiLog("2.2" + Constants.XUNLEI_LOG_DELIMITER + roomId + Constants.XUNLEI_LOG_DELIMITER + channelId + Constants.XUNLEI_LOG_DELIMITER
                                        + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getRank() + Constants.XUNLEI_LOG_DELIMITER
                                        + CommonUtil.simpleDateFormat.format(new Date(gameStart * 1000l)));
                            }
                            for (Integer cid : team1) {
                                Player player = getService.getPlayerById(cid);
                                nosqlService.publishEvent(new RoomInEvent(roomId, System.currentTimeMillis() / 1000, playerId, player.getName()));
                                ServiceLocator.nosqlService.addXunleiLog("2.2" + Constants.XUNLEI_LOG_DELIMITER + roomId + Constants.XUNLEI_LOG_DELIMITER + channelId + Constants.XUNLEI_LOG_DELIMITER
                                        + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getRank() + Constants.XUNLEI_LOG_DELIMITER
                                        + CommonUtil.simpleDateFormat.format(new Date(gameStart * 1000l)));
                            }
                        }
                    } catch (Exception e) {
                        logger.warn(e.getMessage(), e);
                    }
                }

            };
            ServiceLocator.asynTakService.execute(addXunleiTask);

            BinaryOut bOut = new BinaryOut(out);
            if (levelAndMode == null) {
                bOut.writeInt(0);
            } else {
                bOut.writeInt(levelAndMode.getId());
                bOut.writeByte(levelAndMode.getType() == 111 ? 11 : levelAndMode.getType());
                bOut.writeString(levelAndMode.getName());
                //是否自带武器
                bOut.writeByte(levelAndMode.getIsSelf());
                bOut.writeFloat(levelAndMode.getLevelHorizon());
                bOut.writeFloat(levelAndMode.getTargetSpeed());
                //vehicle info
                if (levelAndMode.getVehicleInfo() != null) {
                    writeVehicleInfo(levelAndMode.getVehicleInfo(), bOut);
                } else {
                    bOut.writeInt(0);
                }
                //startPoints
                bOut.writeInt(levelAndMode.getStartPoints().size());
                for (TeamPoints tp : levelAndMode.getStartPoints()) {
                    writeTeamPoints(tp, bOut);
                }
                //blast points
                //资源争夺战 相关模式时，此地段表示油罐点
                if (levelAndMode.getType() == Constants.GAMETYPE.TEAMEDIT.getValue() || levelAndMode.getType() == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
                    bOut.writeInt(0);
                } else {
                    bOut.writeInt(levelAndMode.getBlastPoints().size());
                    for (AABBPoints v : levelAndMode.getBlastPoints()) {
                        writeAABBPoints(v, bOut);
                    }
                }

                if (levelAndMode.getType() == Constants.GAMETYPE.DELIVER.getValue() || levelAndMode.getType() == Constants.GAMETYPE.NEWTRAIN.getValue()) {
                    bOut.writeInt(levelAndMode.getVehicleLine1Points().size());
                    for (VehicleLinePoint v : levelAndMode.getVehicleLine1Points()) {
                        writeVehicleLinePoints(v, bOut);
                    }
                    bOut.writeInt(levelAndMode.getVehicleLine2Points().size());
                    for (VehicleLinePoint v : levelAndMode.getVehicleLine2Points()) {
                        writeVehicleLinePoints(v, bOut);
                    }
                } else if (levelAndMode.getType() == Constants.GAMETYPE.BOSS2.getValue()) {
                    bOut.writeInt(levelAndMode.getBossLinePoint().size());
                    for (BossLinePoint v : levelAndMode.getBossLinePoint()) {
                        int bossCharacterId = (int) v.getVecter2().getX();
                        if (bossCharacterId >= 100 && bossCharacterId < 120) {
                            bossCharacterIdSet.add(bossCharacterId);
                        } else {
                            logger.error("wrong parameter while loading BOSS line info from GM System.BossCharacterId=" + bossCharacterId);
                            bossCharacterIdSet.add(100);
                        }
                        writeBossLinePoints(v, bOut);
                    }
                } else {
                    bOut.writeInt(0);
                }

                //banner point
                bOut.writeInt(levelAndMode.getBannerPoint().size());
                for (BannerPoints tp : levelAndMode.getBannerPoint()) {
                    writeBannerPoints(tp, bOut);
                }
                //lazy load
                List<LevelWeapon> weapons = new ArrayList<LevelWeapon>();
                if (levelAndMode.hasRelatedWeapon()) {
                    weapons = getService.getLevelWeaponsByLevelId(lid);
                }

                //weapon point
                Set<WeaponPoint> pointSet = levelAndMode.getWeaponPoints();
                int size = 0;
                for (WeaponPoint wp : pointSet) {
                    if (type == wp.getType()) {
                        size++;
                    }
                }
                bOut.writeInt(size);
                if (size != 0) {
                    for (WeaponPoint v : levelAndMode.getWeaponPoints()) {
                        writeWeaponPoints(v, bOut, weapons, type);
                    }
                }
                //              //supply points
                //              bOut.writeInt(levelAndMode.getSupplyPoints().size());
                //              for (Vector3 v : levelAndMode.getSupplyPoints()) {
                //                  writeVector(v,bOut);
                //              }
                //weapons
                bOut.writeInt(weapons.size());
                for (LevelWeapon lw : weapons) {
                    //                  lw.writeByte(bOut.getOutputStream());
                }
                //supplies
                bOut.writeInt(levelAndMode.getSupplies().size());
                for (Supply v : levelAndMode.getSupplies()) {
                    writeSupply(v, bOut, weapons);
                }
                bOut.writeByte(levelAndMode.getBloodOffset());
                bOut.writeByte(levelAndMode.getIsRushGold());
                //RushGlodPoints
                bOut.writeInt(levelAndMode.getRushGlodPoints().size());
                for (Vector3 v : levelAndMode.getRushGlodPoints()) {
                    writeVector(v, bOut);
                }
                bOut.writeByte(levelAndMode.getIsMoneyReward());
                if (levelAndMode.getType() == Constants.GAMETYPE.HITBOSS.getValue()) {
                    BossPojo hero = new BossPojo();
                    hero.setPlayerId(0);
                    SysCharacter character = getService.getSysCharacter(1);
                    character.setId(Constants.DEFAULT_BOSS_CHARACTER_ID);
                    character.setRunSpeed(5f);
                    character.setName("BOSS");
                    character.setNameCN("BOSS");
                    character.setResourceName("robot");
                    character.setScoreOffset(10);
                    character.setIsEnable(0);
                    character.setMaxHp(20000);
                    character.setExHp(20000);
                    character.setControllerHeight(0.75f * 4);
                    character.setControllerRadius(0.5f * 4);
                    character.setControllerCrouchHeight(0.3f * 4);
                    hero.setSysCharacter(character);
                    SysItem mainSysItem = getService.getSysItemByItemId(1001);
                    SysItem subSysItem = getService.getSysItemByItemId(1000);
                    SysItem knifItem = getService.getSysItemByItemId(1002);
                    List<SysItem> weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    weaponList.add(subSysItem);
                    weaponList.add(knifItem);
                    //                  weaponList.add(null);
                    SysItem clothes = getService.getSysItemByItemId(2003);
                    List<SysItem> costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    //                  costumeList.add(null);
                    //                  costumeList.add(null);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());
                } else if (levelAndMode.getType() == Constants.GAMETYPE.BOSS2.getValue()) {

                    List<BossItem> bossItems = levelAndMode.getBossItems();
                    int biSize = 0;
                    if (bossItems != null) {
                        biSize = bossItems.size();
                    }
                    if (biSize < bossCharacterIdSet.size()) {
                        logger.warn("LevelInfo/BossItemsError:\t" + levelAndMode.getId() + "\t" + biSize + "\t" + bossCharacterIdSet.size());
                        for (int i = biSize; i < bossCharacterIdSet.size(); i++) {
                            BossItem bi = new BossItem(Constants.BOSS2_AWARDS_ID[i], 1.0f, 1.0f, 10);
                            bossItems.add(bi);
                        }
                    }
                    int count1 = 0;
                    bOut.writeByte(bossCharacterIdSet.size());
                    List<Integer> bossIds = new ArrayList<Integer>(bossCharacterIdSet);
                    Collections.sort(bossIds, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1.compareTo(o2);
                        }
                    });
                    for (Integer bossId : bossIds) {
                        BossPojo hero = new BossPojo();
                        hero.setPlayerId(0);
                        SysCharacter character = getService.getSysCharacter(bossId);
                        character.setId(bossId);
                        character.setIsEnable(0);
                        hero.setSysCharacter(character);
                        SysItem mainSysItem = getService.getSysItemByItemId(4911);
                        //                      SysItem subSysItem=getService.getSysItemByItemId(1000);
                        //                      SysItem knifItem=getService.getSysItemByItemId(1002);
                        List<SysItem> weaponList = new ArrayList<SysItem>();
                        hero.setWeaponList(weaponList);
                        weaponList.add(mainSysItem);
                        //                      weaponList.add(subSysItem);
                        //                      weaponList.add(knifItem);
                        //                      weaponList.add(null);
                        SysItem clothes = getService.getSysItemByItemId(2003);
                        List<SysItem> costumeList = new ArrayList<SysItem>();
                        costumeList.add(clothes);
                        //                      costumeList.add(null);
                        //                      costumeList.add(null);
                        hero.setCostumeList(costumeList);
                        hero.putOnCostume();
                        hero.writeByte(bOut.getOutputStream());
                        bOut.writeInt(1);
                        BossItem bi = bossItems.get(count1);
                        bOut.writeInt(bi.getItemId());
                        bOut.writeFloat(bi.getParamA());
                        bOut.writeFloat(bi.getParamB());
                        bOut.writeInt(bi.getNum());
                        count1++;
                    }
                } else if (levelAndMode.getType() == Constants.GAMETYPE.BIOCHEMICAL2.getValue() || levelAndMode.getType() == Constants.GAMETYPE.BIOCHEMICAL2_MATCH.getValue()) {

                    BossPojo hero = new BossPojo();
                    hero.setPlayerId(0);
                    SysCharacter character = getService.getSysCharacter(Constants.FIRST_BIOCHEMICAL_CHARACTER_ID);//母体僵尸
                    hero.setSysCharacter(character);
                    SysItem mainSysItem = getService.getSysItemByItemId(5196);
                    SysItem subSysItem = null;
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    List<SysItem> weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    //                  weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    SysItem clothes = getService.getSysItemByItemId(4797);
                    List<SysItem> costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.NORMAL_BIOCHEMICAL_CHARACTER_ID);//普通僵尸
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5197);
                    //                  subSysItem=getService.getSysItemByItemId(4869);
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    //                  weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    clothes = getService.getSysItemByItemId(4846);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.FINAL_BIOCHEMICAL_CHARACTER_ID);//终极僵尸
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5198);
                    subSysItem = getService.getSysItemByItemId(5207);
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    clothes = getService.getSysItemByItemId(4821);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    bOut.writeInt(3); //buff僵尸总数
                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.NEW_BIOCHEMICAL_ONK_CHARACTER_ID);//新增的僵尸欧纳卡
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5347);
                    //                  subSysItem=getService.getSysItemByItemId(4869);
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    //                  weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    clothes = getService.getSysItemByItemId(4821);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.NEW_BIOCHEMICAL_HW_CHARACTER_ID);//新增的僵尸黑雾
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5366);
                    //                  subSysItem=getService.getSysItemByItemId(4869);
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    //                  weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    clothes = getService.getSysItemByItemId(4821);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.NEW_BIOCHEMICAL_HK_CHARACTER_ID);//新增的僵尸浩克
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5390);
                    //                  subSysItem=getService.getSysItemByItemId(4869);
                    //                  SysItem knifItem=getService.getSysItemByItemId(4799);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    //                  weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    clothes = getService.getSysItemByItemId(4821);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                } else if (levelAndMode.getType() == Constants.GAMETYPE.HITBOSS2.getValue()) {
                    BossPojo hero = new BossPojo();
                    hero.setPlayerId(0);
                    SysCharacter character = getService.getSysCharacter(Constants.BOSS3CHARACTERID.DEFAULT_BOSS31_CHARACTER_ID.getValue());//boss
                    character.setId(Constants.BOSS3CHARACTERID.DEFAULT_BOSS31_CHARACTER_ID.getValue());
                    character.setControllerHeight(0.75f * 2.5f);
                    character.setControllerRadius(0.5f * 2);
                    character.setControllerCrouchHeight(0.3f * 2);
                    character.setIsEnable(0);
                    hero.setSysCharacter(character);

                    SysItem mainSysItem = getService.getSysItemByItemId(5279);
                    SysItem subSysItem = getService.getSysItemByItemId(5280);
                    //                  SysItem knifItem=getService.getSysItemByItemId(1002);
                    List<SysItem> weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    weaponList.add(subSysItem);
                    //                  weaponList.add(knifItem);
                    //                  weaponList.add(null);
                    SysItem clothes = getService.getSysItemByItemId(5285);
                    List<SysItem> costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    //                  costumeList.add(null);
                    //                  costumeList.add(null);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.BOSS3CHARACTERID.DEFAULT_BOSS32_CHARACTER_ID.getValue());//0:固定火箭塔，1:固定炮塔，2:炮塔UAV，3:机枪UAV
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5281);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    clothes = getService.getSysItemByItemId(5286);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.BOSS3CHARACTERID.DEFAULT_BOSS33_CHARACTER_ID.getValue());
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5282);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    clothes = getService.getSysItemByItemId(5287);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.BOSS3CHARACTERID.DEFAULT_BOSS34_CHARACTER_ID.getValue());
                    character.setControllerHeight(0.75f * 0.6f);
                    character.setControllerRadius(0.5f * 2.5f);
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5283);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    clothes = getService.getSysItemByItemId(5288);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                    hero = new BossPojo();
                    hero.setPlayerId(0);
                    character = getService.getSysCharacter(Constants.BOSS3CHARACTERID.DEFAULT_BOSS35_CHARACTER_ID.getValue());
                    character.setControllerHeight(0.75f * 0.6f);
                    character.setControllerRadius(0.5f * 2.5f);
                    hero.setSysCharacter(character);
                    mainSysItem = getService.getSysItemByItemId(5284);
                    weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    clothes = getService.getSysItemByItemId(5289);
                    costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());

                } else if (levelAndMode.getType() == Constants.GAMETYPE.AMAZINGWAR.getValue()) { //道具战
                    BossPojo hero = new BossPojo();
                    hero.setPlayerId(0);
                    SysCharacter character = getService.getSysCharacter(Constants.NEW_CHARACTER_FOR_AMAZINGWAR);//新增的道具战角色
                    hero.setSysCharacter(character);
                    SysItem mainSysItem = getService.getSysItemByItemId(5369);
                    SysItem subSysItem = getService.getSysItemByItemId(5370);

                    List<SysItem> weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    weaponList.add(subSysItem);

                    SysItem clothes = getService.getSysItemByItemId(5387);
                    List<SysItem> costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());
                } else if (levelAndMode.getType() == Constants.GAMETYPE.AGRAVITY.getValue()) { //勇者攀登
                    BossPojo hero = new BossPojo();
                    hero.setPlayerId(0);
                    SysCharacter character = getService.getSysCharacter(Constants.NEW_CHARACTER_FOR_AMAZINGWAR);//新增的道具战角色，杀戮甜心
                    hero.setSysCharacter(character);
                    SysItem mainSysItem = getService.getSysItemByItemId(5369);
                    SysItem subSysItem = getService.getSysItemByItemId(5370);

                    List<SysItem> weaponList = new ArrayList<SysItem>();
                    hero.setWeaponList(weaponList);
                    weaponList.add(mainSysItem);
                    weaponList.add(subSysItem);

                    SysItem clothes = getService.getSysItemByItemId(5387);
                    List<SysItem> costumeList = new ArrayList<SysItem>();
                    costumeList.add(clothes);
                    hero.setCostumeList(costumeList);
                    hero.putOnCostume();
                    hero.writeByte(bOut.getOutputStream());
                } else if (levelAndMode.getType() == Constants.GAMETYPE.AGRAVITY2.getValue()) { //圣诞跳跳乐
                } else if (levelAndMode.getType() == Constants.GAMETYPE.RISK_ISLET.getValue()) { //冒险岛
                //                  BossPojo hero=new BossPojo();
                //                  hero.setPlayerId(0);
                //                  SysCharacter character=getService.getSysCharacter(Constants.RISK_ISLET_GHOST_CHARACTER);//幽灵
                //                  hero.setSysCharacter(character);
                //                  
                //                  SysItem clothes=getService.getSysItemByItemId(5285);
                //                  List<SysItem> costumeList=new ArrayList<SysItem>();
                //                  costumeList.add(clothes);
                //                  
                ////                    costumeList.add(null);
                ////                    costumeList.add(null);
                //                  hero.setCostumeList(costumeList);
                //                  hero.putOnCostume();
                //                  hero.writeByte(bOut.getOutputStream());

                }
                //              
                //              if(levelAndMode.getType()!=Constants.GAMETYPE.NEWGAMETYPE1.getValue()){
                //                  bOut.writeInt(0);
                //              }

                bOut.writeByte((byte) 0); // 

                if (levelAndMode.getType() == Constants.GAMETYPE.TEAMZYZDZ.getValue() || levelAndMode.getType() == Constants.GAMETYPE.TEAMEDIT.getValue()) {
                    //资源战模式下，playerteam是进攻方，不能用
                    PlayerTeam playerTeam = getService.getPlayerTeamByPlayerIdSimple(playerId);
                    TeamLevelModeInfo teamLevelModeInfo = getLevelModeInfoForTeamLevel(levelAndMode.getType(), playerTeam.getTeamId(), lid);

                    //防止多人编辑
                    if (levelAndMode.getType() == Constants.GAMETYPE.TEAMEDIT.getValue()) {
                        if (playerTeam.getJob() != TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) {
                            responses.add(Response.binary(Constants.EMPTY_BYTE_ARRAY));
                            return responses;
                        }
                        if (teamLevelModeInfo != null && teamLevelModeInfo.getIsEditable().equals("Y")) {
                            responses.add(Response.binary(Constants.EMPTY_BYTE_ARRAY));
                            return responses;
                        } else {
                            teamLevelModeInfo.setIsEditable("Y");
                        }
                    }

                    if (teamLevelModeInfo != null) {
                        //发送 油罐信息
                        int blastPoints = levelAndMode.getBlastPoints().size();
                        Team team = getService.getSimpleTeamById(teamLevelModeInfo.getTeamId());

                        int headQuarterSize = 1; //默认油罐数目
                        if (team != null) {
                            headQuarterSize = Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).getMaxCreateColletter() > blastPoints ? blastPoints
                                    : Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).getMaxCreateColletter();
                        }
                        Set<TeamLevelModeInfo.ConfigPoint> headquarterSet = new HashSet<TeamLevelModeInfo.ConfigPoint>();
                        if (blastPoints > 0) {
                            Iterator<AABBPoints> iterator = levelAndMode.getBlastPoints().iterator();
                            for (int i = 0; i < headQuarterSize; i++) {
                                if (iterator.hasNext()) {
                                    AABBPoints abPoints = iterator.next();
                                    headquarterSet.add(new TeamLevelModeInfo.ConfigPoint(Constants.SYSTEM_TEAM_HEAD_QUARTERS, abPoints.getTransform(), abPoints.getPoint()));
                                }

                            }
                        }

                        Set<TeamLevelModeInfo.ConfigPoint> allConfigPoints = teamLevelModeInfo.getConfigPoints();
                        allConfigPoints.addAll(headquarterSet);
                        if (allConfigPoints != null && allConfigPoints.size() > 0) {
                            bOut.writeInt(allConfigPoints.size()); //count
                            for (TeamLevelModeInfo.ConfigPoint configPoint : allConfigPoints) {
                                writeConfigPoints(configPoint, teamLevelModeInfo.getTeamId(), bOut);
                            }
                        } else {
                            bOut.writeInt(0);
                        }
                    } else {
                        bOut.writeInt(0);
                    }

                } else {
                    bOut.writeInt(0);
                }

                //审批、机甲、资源战模式
                if (levelAndMode.getType() == Constants.GAMETYPE.BOSS2.getValue() || levelAndMode.getType() == Constants.GAMETYPE.HITBOSS2.getValue()
                        || levelAndMode.getType() == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
                    //死亡复活 buff,不用的要及时删除
                    List<SysItem> dbuffItems = getService.getSysItemByIID(Constants.DEFAULT_DIE_BUFF_IID, Constants.DEFAULT_ITEM_TYPE);
                    bOut.writeInt(dbuffItems.size()); //size
                    for (SysItem si : dbuffItems) {
                        bOut.writeFloat(si.getWFireTime()); //  stream.ReadFloat(info.duration_timer);
                        bOut.writeFloat(si.getWChangeInTime()); //  stream.ReadFloat(info.interval);
                        bOut.writeInt(si.getWAmmoCount()); //  stream.ReadInt(info.type);对应constants中enum EEffect
                        bOut.writeFloat(Float.valueOf(si.getIValue())); //  stream.ReadFloat(info.value);
                        bOut.writeString(si.getName()); //  stream.ReadString(info.res_key, res_key_length);
                        bOut.writeString(si.getDescription()); //describe
                        bOut.writeInt(Math.round(si.getWReloadTime())); //权重
                    }
                } else {
                    bOut.writeInt(0);
                }

            }
            bOut.flush();

        } catch (Exception e) {
            logger.error("error happend when load level info.", e);
            throw e;
        }

        responses.add(Response.binary(out.toByteArray()));
        //      System.out.println(out.toByteArray().length);

        return responses;
    }

    /**
     * @param teamId
     * @param lid 只有编辑模式lid才是refLevelId, 一般情况下应该是teamlevelId
     * @return
     * @throws Exception
     */
    private TeamLevelModeInfo getLevelModeInfoForTeamLevel(final int mode, final int teamId, final int lid) throws Exception {

        TeamLevelInfo teamLevelInfo = getService.getTeamLevelInfo(lid);

        if (teamLevelInfo != null) {
            return new TeamLevelModeInfo(teamLevelInfo);

        } else {
            if (mode == Constants.GAMETYPE.TEAMEDIT.getValue()) {//编辑模式 才能创建该战队地图
                teamLevelInfo = getService.getTeamLevelInfo(teamId, lid);
                if (teamLevelInfo != null) {
                    return new TeamLevelModeInfo(teamLevelInfo);
                } else {
                    teamLevelInfo = createService.createTeamLevel(teamId);

                    if (teamLevelInfo != null) {
                        return new TeamLevelModeInfo(teamLevelInfo);
                    } else {
                        teamLevelInfo = getService.getTeamLevelInfo(teamId, Constants.DEFAULT_TEAM_LEVEL_RES_ID);
                        return new TeamLevelModeInfo(teamLevelInfo);
                    }
                }
            }
        }
        return null;
    }
}
