package com.pearl.fcw.lobby.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WBlackIpService;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemGunPropertyService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WCharacterData;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.fcw.lobby.pojo.json.JsonItemSight;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerMelting;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.ECharacterDataNumberParam;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemGunPropertyType;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EItemWId;
import com.pearl.fcw.proto.enums.EPlayerVipType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.proto.rpc.ResponseCharacterGet;
import com.pearl.fcw.proto.server.RequestPlayerOnline;
import com.pearl.fcw.proto.server.ResponsePlayerOnline;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.GameThreadPool;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 角色
 */
@Service
public class CharacterService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysCharacterService wSysCharacterService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private WSysConfigService wSysConfigService;
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysItemGunPropertyService wSysItemGunPropertyService;
	@Resource
	private WBlackIpService wBlackIpService;
	@Resource
	private ProtoConverter protoConverter;
	@Resource
	private GameThreadPool gameThreadPool;

	/**
	 * 获取玩家角色信息
	 * @param playerId
	 * @param sysCharacterId
	 * @return
	 * @throws Exception
	 */
	public String getCharacter(int playerId, int sysCharacterId) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWCharacterData> pwCharacterDataMap = wPlayerService.findProxyWCharacterDataMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);

		//FIXME 排行榜段位(结构不合理，暂时借用)
		int mateRank = ServiceLocator.getService.getQW_MateRank(playerId);
		ResponseCharacterGet proto = protoConverter.responseCharacterGet(pwPlayer, pwCharacterDataMap.get(sysCharacterId), pwPlayerInfo, pwPlayerPackMap, pwPlayerItemMap, pwPlayerBuffMap, mateRank);
		return Smarty4jConverter.proto2Lua(proto);
	}

	public ResponsePlayerOnline login(RequestPlayerOnline request, boolean isCreateAuto) throws Exception {
		//检查输入参数
		if (StringUtil.isEmpty(request.getUserName()) || StringUtil.isEmpty(request.getIp()) || StringUtil.isEmpty(request.getClientVersion())) {
			throw new IllegalArgumentException("parameter error");
		}
		//验证版本信息
		String[] clientVersions = wSysConfigService.findEntity("client.version").getValue().split(";");
		boolean versionCorrect = false;
		for (String v : clientVersions) {
			if (v.equals(request.getClientVersion()) || v.trim().equals("*")) {
				versionCorrect = true;
				break;
			}
		}
		if (!versionCorrect) {
			throw new BaseException(CommonUtil.messageFormatI18N(ExceptionMessage.CLIENT_VERSION_ERROR, wSysConfigService.findEntity("client.version").getValue()));
		}
		//FIXME 验证ip是否被禁(数据库的ip是个int数据，需要转换)
		LocalDateTime now = LocalDateTime.now();
		Date nowDate = Date.from(now.toInstant(ZoneOffset.UTC));
		if (wBlackIpService.findList(null).stream().filter(p -> p.getIp().equals(request.getIp()) && "Y".equals(p.getIsBanned()) && now.getSecond() < p.getBannedTime()).count() > 0) {
			throw new BaseException(ExceptionMessage.BANED_ERROR);
		}

		//游戏内登录
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayerByName(request.getName(), request.getUserName());
		if (null == pwPlayer) {
			if (!isCreateAuto) {

			}
			pwPlayer = wPlayerService.addProxyWPlayer(request.getName(), request.getUserName(), request.getAccountTypeValue());
			//application.properties里配置的数值如果为0或者是当前月份，记录playerTrack数据
			if (ConfigurationUtil.PLAYER_TRACK_TIMESTAMP == 0 || ConfigurationUtil.PLAYER_TRACK_TIMESTAMP == now.getMonthValue()) {
				wPlayerService.addProxyWPlayerTrack(pwPlayer.id().get());
			}
		}
		//验证是否黑名单
		if ("1".equals(pwPlayer.blackWhite().get())) {
			throw new BaseException(ExceptionMessage.BLACK_ERROR);
		}
		boolean isFirstLoginToday = !DateUtil.isSameDay(pwPlayer.lastLoginTime().get(), nowDate);//是否当天首次登录
		boolean isFirstLoginWeek = !DateUtil.isSameWeek(pwPlayer.lastLoginTime().get(), nowDate);//是否本周首次登录
		boolean isFirstLoginMonth = !DateUtil.isSameMonth(pwPlayer.lastLoginTime().get(), nowDate);//是否本月首次登录
		pwPlayer.lastLoginIp().set(request.getIp());
		pwPlayer.lastLoginTime().set(nowDate);
		if (request.getAccountTypeValue() > 0) {
			pwPlayer.isVip().set(EPlayerVipType.XUNLEI_VIP.getNumber());
		}
		ProxyWPlayerMelting pwPlayerMelting = wPlayerService.findProxyWPlayerMelting(pwPlayer.id().get());
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(pwPlayer.id().get());
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		jsonCaches.setInternetCafe(request.getInternetCafe());
		pwPlayerInfo.cachesEntity().set(jsonCaches);
		//        ProtoPlayerInfoProto.Builder builder = pwPlayerInfo.protoEntity().get().toBuilder();
		//		builder.setSecondPasswordFlag(0);//二级密码标记
		//		builder.setInternetCafe(request.getInternetCafe());//玩家在网咖登录的标记
		//        pwPlayerInfo.protoEntity().set(builder.build());
		if (isFirstLoginToday) {//当天第一次登录
			//TODO
			//          //每天第一次登陆 ，赠送月卡福利
			//            updateService.onCardsWelfare(player);
			//          //每天第一次登陆，天天礼赠送
			//            updateService.everydayGiftWelfare(player);
			//            //每天第一次登陆，天天礼赠送
			//            updateService.everydayGiftWelfare_6275(player);
			//熔炼残余值清零
			pwPlayerMelting.remaind().set(0.0);
		}

		//熔炼相关刷新
		pwPlayerMelting.startTime().set(System.currentTimeMillis());

		//第三方登录标记
		loginXunlei(pwPlayer.id().get());

		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(pwPlayer.id().get());
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(pwPlayer.id().get());
		Map<Serializable, ProxyWCharacterData> pwCharacterDataMap = wPlayerService.findProxyWCharacterDataMap(pwPlayer.id().get());
		ResponsePlayerOnline response = protoConverter.responsePlayerOnline(request.getUserId(), pwPlayer, pwPlayerInfo, pwPlayerItemMap, pwPlayerBuffMap, pwCharacterDataMap);
		return response;
	}

	/**
	 * 异步登录迅雷，并修改player代理
	 * @param playerId
	 */
	private void loginXunlei(int playerId) {
		if ("none".equals(ConfigurationUtil.XUNLEI_LOGIN_URL)) {
			return;
		}
		gameThreadPool.executeWithEntityManager(() -> {
			LocalDateTime now = LocalDateTime.now();
			try {
				ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
				//检查是否迅雷用户
				if (ConfigurationUtil.logConfig == 1 && pwPlayer.userName().get().indexOf("xldcf") == -1) {
					logger.error("xunleiID incorrent xunleiID=" + pwPlayer.userName().get());
				}
				if (pwPlayer.isXunlei().get() != 1 && pwPlayer.isXunlei().get() < 30) {
					String serverIP = ConfigurationUtil.XUNLEI_SERVER_IP;
					String urlStr = ConfigurationUtil.XUNLEI_LOGIN_URL;
					String dateStr = now.toInstant(ZoneOffset.UTC).toEpochMilli() + "";
					urlStr += "account=" + pwPlayer.userName().get() + "&";
					urlStr += "sid=" + serverIP + "&";
					urlStr += "time=" + dateStr + "&";
					urlStr += "sign=" + MD5Util.md5(pwPlayer.userName().get() + serverIP + dateStr + "dfa0988aajmx*ds1wa");
					logger.debug(urlStr);
					URL url = new URL(urlStr);
					URLConnection connection = url.openConnection();
					connection.setConnectTimeout(1000);
					connection.setReadTimeout(1000);
					InputStreamReader r = new InputStreamReader(connection.getInputStream());
					BufferedReader rd = new BufferedReader(r);
					StringBuffer sb = new StringBuffer();
					String line;
					while ((line = rd.readLine()) != null) {
						sb.append(line);
					}
					rd.close();
					String result = sb.toString();
					logger.debug(result);
					if ("result=0".equals(result)) {
						pwPlayer.isXunlei().set((byte) 1);
					} else {
						pwPlayer.isXunlei().increase((byte) 10);
					}
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		});
	}

	/**
	 * 对战局内客户端输出流写角色数据
	 * @param out
	 * @param wCharacterData
	 * @param equips 装备的道具
	 * @throws Exception
	 */
	public void writeCharacterOutputStream(final OutputStream out, WCharacterData wCharacterData, Collection<WPlayerItem> equips) throws Exception {
		int playerId = null == wCharacterData.getPlayerId() ? 0 : wCharacterData.getPlayerId();
		WSysCharacter wSysCharacter = wSysCharacterService.findEntity(wCharacterData.getCharacterId());
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);

		out.write(BinaryUtil.toBytas(playerId));
		//=====系统角色信息=====
		out.write(BinaryUtil.toByta(wSysCharacter.getId()));
		out.write(BinaryUtil.toByta(wSysCharacter.getName().equalsIgnoreCase("boss") ? wSysCharacter.getName() : ("<SC" + wSysCharacter.getId() + "^0>")));
		out.write(BinaryUtil.toByta(wSysCharacter.getResourceName()));
		//头部素材，默认使用sysCharacter.resourceName。如果是玩家，使用衣服的sysItem.name
		if (playerId == 0) {
			if (wSysCharacter.getId() >= 40 && wSysCharacter.getId() <= 45) {//FIXME (不要直接写角色ID)
				out.write(BinaryUtil.toByta(wSysCharacter.getResourceName()));
			} else if (wSysCharacter.getId() > 121) {
				out.write(BinaryUtil.toByta(wSysCharacter.getResourceName()));
			} else {
				out.write(BinaryUtil.toByta("boss"));
			}
		} else {
			WSysItem wSysItem = equips.stream().map(p -> wSysItemMap.get(p.getItemId())).filter(p -> p.getType() == EItemType.COSTUME.getNumber()).findFirst().orElse(null);
			out.write(BinaryUtil.toByta(null != wSysItem ? wSysItem.getName() : wSysCharacter.getResourceName()));
		}
		out.write(BinaryUtil.toBytas(wSysCharacter.getMaxHp(), wSysCharacter.getExHp(), wSysCharacter.getIsFired()));
		out.write(BinaryUtil.toBytas(wSysCharacter.getRunSpeed(), wSysCharacter.getWalkSpeed(), wSysCharacter.getCrouchSpeed(), wSysCharacter.getAccelSpeed(), wSysCharacter.getJumpSpeed(),
				wSysCharacter.getThrowSpeed()));
		out.write(BinaryUtil.toBytas(wSysCharacter.getControllerHeight(), wSysCharacter.getControllerRadius(), wSysCharacter.getControllerCrouchHeight(), wSysCharacter.getIsEnable().byteValue(),
				wSysCharacter.getScoreOffset().byteValue()));
		//角色战斗力fightNum
		if (playerId == 0) {//非玩家用固定值
			out.write(BinaryUtil.toByta(999999));
		} else {
			int fightNum = wCharacterData.getNumberParamMap().get(ECharacterDataNumberParam.characterFightNum.toString()).getValue().intValue();
			out.write(BinaryUtil.toByta(fightNum));
		}

		activeSuitGunProperty(equips);//触发套装属性
		//=====服装=====
		float gstFactorCostume = 1F;//全体服装的升星影响系数
		List<WPlayerItem> costumeList = equips.stream().filter(p -> {
			WSysItem wSysItem = wSysItemMap.get(p.getItemId());
			return wSysItem.getType() == EItemType.COSTUME.getNumber() || wSysItem.getType() == EItemType.PART.getNumber();
		}).collect(Collectors.toList());
		out.write(BinaryUtil.toByta(costumeList.size()));
		for (WPlayerItem wPlayerItem : costumeList) {
			WSysItem wSysItem = wSysItemMap.get(wPlayerItem.getItemId());
			//道具共同属性
			writePlayerItemOutputStream(out, wSysCharacter, wPlayerItem);
			//服装抗性，加血
			float cResistanceFire = wSysItem.getcResistanceFire()
					+ wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.cResistanceFire.toString(), new ParamObject<>(0)).getValue().floatValue();
			float cResistanceBlast = wSysItem.getcResistanceBlast()
					+ wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.cResistanceBlast.toString(), new ParamObject<>(0)).getValue().floatValue();
			float cResistanceBullet = wSysItem.getcResistanceBullet()
					+ wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.cResistanceBullet.toString(), new ParamObject<>(0)).getValue().floatValue();
			float cResistanceKnife = wSysItem.getcResistanceKnife()
					+ wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.cResistanceKnife.toString(), new ParamObject<>(0)).getValue().floatValue();
			float cBloodAdd = wSysItem.getcBloodAdd() + wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.cBloodAdd.toString(), new ParamObject<>(0)).getValue().floatValue();
			out.write(BinaryUtil.toBytas(cResistanceFire, cResistanceBlast, cResistanceBullet, cResistanceKnife, cBloodAdd));
			float gstFactorCostume1 = wPlayerItem.getNumberParamMap().getOrDefault(EItemNumberParam.gstFactorCostume.toString(), new ParamObject<>(0F)).getValue().floatValue();
			gstFactorCostume += gstFactorCostume1 > 1 ? (gstFactorCostume1 - 1) : 0;
		}
		out.write(BinaryUtil.toByta(gstFactorCostume));//血量系数
		out.write(BinaryUtil.toByta(1));//背包总数
		out.write(BinaryUtil.toByta(1));//背包ID

		//=====武器=====
		List<WPlayerItem> weaponList = equips.stream().filter(p -> {
			WSysItem wSysItem = wSysItemMap.get(p.getItemId());
			return wSysItem.getType() == EItemType.WEAPON.getNumber() ;
		}).collect(Collectors.toList());
		out.write(BinaryUtil.toByta(weaponList.size()));
		for (WPlayerItem wPlayerItem : weaponList) {
			WSysItem wSysItem = wSysItemMap.get(wPlayerItem.getItemId());
			//道具共同属性
			writePlayerItemOutputStream(out, wSysCharacter, wPlayerItem);
			//武器攻击属性
			out.write(BinaryUtil.toByta(wSysItem.getwId()));
			out.write(BinaryUtil.toByta(wSysItem.getwChangeInTime()));
			out.write(BinaryUtil.toByta(wSysItem.getwMoveSpeedOffset()));
			out.write(BinaryUtil.toBytas(wSysItem.getwCrossOffset(), wSysItem.getwCrossLengthBase(), wSysItem.getwCrossLengthFactor(), wSysItem.getwCrossDistanceBase(),
					wSysItem.getwCrossDistanceFactor()));
			out.write(BinaryUtil.toBytas(wSysItem.getwHitSpeed(), wSysItem.getwHitAcceleration(), wSysItem.getwHitDistance(), wSysItem.getwCritRate(), wSysItem.getwCritAvailable()));
			out.write(BinaryUtil.toByta(wSysItem.getwDamageModifer()));
			out.write(BinaryUtil.toByta(wSysItem.getwTimeToIdle()));
			out.write(BinaryUtil.toByta(wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.fightNum.toString(), 0).intValue()));//战斗力FightNum
			out.write(BinaryUtil.toByta(wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.damage.toString(), 0).floatValue()));//伤害Damage
			out.write(BinaryUtil.toByta(wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.shootSpeed.toString(), 0).floatValue()));//攻速shootSpeed
			switch (EItemWId.forNumber(wSysItem.getwId())) {
			case WID_PISTOL://以下为不离手射击武器
			case WID_SUBMACHINE:
			case WID_RIFFLE:
			case WID_SNIPER_GUN:
			case WID_SHOT_GUN:
			case WID_MACHINE_GUN:
			case WID_MINI_GUN:
			case WID_DOUBLE_PISTOL:
			case WID_MEDITNEEDLE_GUN:
			case WID_BOSSPVE:
			case WID_JQT_GUN:
			case WID_SIGNAL_GUN:
			case WID_CURE_GUN:
			case WID_FLAME_GUN:
			case WID_ROCKET_LUNCHER:
			case WID_BOW:
				out.write(BinaryUtil.toBytas(wSysItem.getwAccuracyDivisor(), wSysItem.getwAccuracyOffset()));
				out.write(BinaryUtil.toByta(wSysItem.getwMaxInaccuracy()));
				out.write(BinaryUtil.toByta(wSysItem.getwPenetration()));
				out.write(BinaryUtil.toByta(wSysItem.getwDamage()));
				out.write(BinaryUtil.toBytas(wSysItem.getwRangeModifier(), wSysItem.getwRangeStart(), wSysItem.getwRangeEnd()));
				out.write(BinaryUtil.toBytas(wSysItem.getwFireTime(), wSysItem.getwReloadTime()));
				out.write(BinaryUtil.toBytas(wSysItem.getwAmmoOneClip(), wSysItem.getwAmmoCount()));
				out.write(BinaryUtil.toByta(wSysItem.getwAutoFire().byteValue()));
				out.write(BinaryUtil.toBytas(wSysItem.getwNormalOffset(), wSysItem.getwNormalFactor(), wSysItem.getwOnairOffset(), wSysItem.getwOnairFactor()));
				out.write(BinaryUtil.toBytas(wSysItem.getwMoveOffset(), wSysItem.getwMoveFactor()));
				//瞄准信息
				if (wSysItem.getwSightInfoList().isEmpty()) {
					out.write(BinaryUtil.toByta(0));
				} else {
					for (JsonItemSight p : wSysItem.getwSightInfoList()) {
						out.write(BinaryUtil.toBytas(p.getFov(), p.getMouseSensitivity(), p.getMoveSpeedAdd(), p.getRecoilReduceFactor(), p.getShootSpeedReduceFactor()));
					}
				}
				switch (EItemWId.forNumber(wSysItem.getwId())) {
				case WID_PISTOL://手枪
				case WID_DOUBLE_PISTOL:
					out.write(BinaryUtil.toBytas(wSysItem.getwUpModifier(), wSysItem.getwAccuracyTime(), wSysItem.getwAccuracyTimeModifier(), wSysItem.getwMaxAccuracy(), wSysItem.getwMinAccuracy()));
					out.write(BinaryUtil.toBytas(wSysItem.getwNormalUpBase(), wSysItem.getwNormalLateralBase(), wSysItem.getwNormalUpModifier(), wSysItem.getwNormalLateralModifier(),
							wSysItem.getwNormalUpMax(), wSysItem.getwNormalLateralMax(), wSysItem.getwNormalDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwMoveUpBase(), wSysItem.getwMoveLateralBase(), wSysItem.getwMoveUpModifier(), wSysItem.getwMoveLateralModifier(),
							wSysItem.getwMoveUpMax(), wSysItem.getwMoveLateralMax(), wSysItem.getwMoveDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwOnairUpBase(), wSysItem.getwOnairLateralBase(), wSysItem.getwOnairUpModifier(), wSysItem.getwOnairLateralModifier(),
							wSysItem.getwOnairUpMax(), wSysItem.getwOnairLateralMax(), wSysItem.getwOnairDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwCrouchUpBase(), wSysItem.getwCrouchLateralBase(), wSysItem.getwCrouchUpModifier(), wSysItem.getwCrouchLateralModifier(),
							wSysItem.getwCrouchUpMax(), wSysItem.getwCrouchLateralMax(), wSysItem.getwCrouchDirChange()));
					break;
				case WID_SNIPER_GUN://狙击
					out.write(BinaryUtil.toBytas(wSysItem.getwSightNormalOffset(), wSysItem.getwSightOnairOffset(), wSysItem.getwSightMoveOffset(), wSysItem.getwReadyTime()));
					break;
				case WID_SHOT_GUN://霰弹
					out.write(BinaryUtil.toBytas(wSysItem.getwShootBulletCount(), wSysItem.getwSpread(), wSysItem.getwNormalUpBase(), wSysItem.getwNormalUpModifier(), wSysItem.getwNormalUpMax()));
					break;
				case WID_MACHINE_GUN://机枪
					out.write(BinaryUtil.toBytas(wSysItem.getwShootBulletCount(), wSysItem.getwSpread(), wSysItem.getwNormalUpBase(), wSysItem.getwNormalUpModifier(), wSysItem.getwNormalUpMax()));
					out.write(BinaryUtil.toBytas(wSysItem.getwFireMaxSpeed(), wSysItem.getwFireStartSpeed(), wSysItem.getwFireAceleration(), wSysItem.getwFireResistance(), wSysItem.getwReadyTime()));
					out.write(BinaryUtil.toByta(wSysItem.getwAmmoType()));
					out.write(BinaryUtil.toByta(wSysItem.getwMaxDistance()));
					out.write(BinaryUtil.toByta(wSysItem.getwFlySpeed()));
					out.write(BinaryUtil.toByta(wSysItem.getwUpModifier()));
					out.write(BinaryUtil.toBytas(wSysItem.getwAccuracyTime(), wSysItem.getwAccuracyTimeModifier(), wSysItem.getwMaxAccuracy(), wSysItem.getwMinAccuracy()));
					out.write(BinaryUtil.toByta(wSysItem.getwMaxaliveTime()));
					out.write(BinaryUtil.toByta(wSysItem.getwGravity()));
					out.write(BinaryUtil.toByta(wSysItem.getwHurt()));
					out.write(BinaryUtil.toByta(wSysItem.getwAmmopartKey()));
					out.write(BinaryUtil.toByta(wSysItem.getwAmmopartKey()));
					out.write(BinaryUtil.toByta(0F));//端游写了range属性，但数据库无该字段
					out.write(BinaryUtil.toBytas(wSysItem.getwDmgModifyTimerMin(), wSysItem.getwDmgModifyTimerMax(), wSysItem.getwDmgModifyMin(), wSysItem.getwDmgModifyMax(),
							wSysItem.getwCapsuleHeight(), wSysItem.getwCapsuleRadius()));
					break;
				case WID_SUBMACHINE://连射
				case WID_RIFFLE:
					out.write(BinaryUtil.toBytas(wSysItem.getwNormalUpBase(), wSysItem.getwNormalLateralBase(), wSysItem.getwNormalUpModifier(), wSysItem.getwNormalLateralModifier(),
							wSysItem.getwNormalUpMax(), wSysItem.getwNormalLateralMax(), wSysItem.getwNormalDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwMoveUpBase(), wSysItem.getwMoveLateralBase(), wSysItem.getwMoveUpModifier(), wSysItem.getwMoveLateralModifier(),
							wSysItem.getwMoveUpMax(), wSysItem.getwMoveLateralMax(), wSysItem.getwMoveDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwOnairUpBase(), wSysItem.getwOnairLateralBase(), wSysItem.getwOnairUpModifier(), wSysItem.getwOnairLateralModifier(),
							wSysItem.getwOnairUpMax(), wSysItem.getwOnairLateralMax(), wSysItem.getwOnairDirChange()));
					out.write(BinaryUtil.toBytas(wSysItem.getwCrouchUpBase(), wSysItem.getwCrouchLateralBase(), wSysItem.getwCrouchUpModifier(), wSysItem.getwCrouchLateralModifier(),
							wSysItem.getwCrouchUpMax(), wSysItem.getwCrouchLateralMax(), wSysItem.getwCrouchDirChange()));
					break;
				case WID_CURE_GUN://治疗枪
					out.write(BinaryUtil.toBytas(wSysItem.getwMaxDistance(), wSysItem.getwAddBlood()));
					break;
				case WID_MEDITNEEDLE_GUN://子弹能看见运行轨迹的武器
				case WID_ROCKET_LUNCHER:
				case WID_BOW:
					out.write(BinaryUtil.toByta(wSysItem.getwAmmoType()));
					out.write(BinaryUtil.toByta(wSysItem.getwFlySpeed()));
					out.write(BinaryUtil.toByta(wSysItem.getwSpread()));
					out.write(BinaryUtil.toBytas(wSysItem.getwNormalUpBase(), wSysItem.getwNormalUpModifier(), wSysItem.getwNormalUpMax()));
					out.write(BinaryUtil.toByta(wSysItem.getwMaxaliveTime()));
					out.write(BinaryUtil.toByta(wSysItem.getwGravity().byteValue()));
					out.write(BinaryUtil.toByta(wSysItem.getwHurt()));
					out.write(BinaryUtil.toByta(wSysItem.getwAmmopartKey()));
					out.write(BinaryUtil.toByta(wSysItem.getwHurtRange()));
					out.write(BinaryUtil.toByta(wSysItem.getwThrowOutTime()));
					out.write(BinaryUtil.toBytas(wSysItem.getwDmgModifyTimerMin(), wSysItem.getwDmgModifyTimerMax(), wSysItem.getwDmgModifyMin(), wSysItem.getwDmgModifyMax()));
					out.write(BinaryUtil.toBytas(wSysItem.getwCapsuleHeight(), wSysItem.getwCapsuleRadius()));
					out.write(BinaryUtil.toBytas(wSysItem.getwStabTime(), wSysItem.getwStabLightTime(), wSysItem.getwStabDistance(), wSysItem.getwStabLightDistance(), wSysItem.getwStabWidth(),
							wSysItem.getwBackFactor(), wSysItem.getwStabHurt(), wSysItem.getwStabLightHurt(), wSysItem.getBackBoostPlus()));
					break;
				case WID_FLAME_GUN://喷火枪
					out.write(BinaryUtil.toBytas(wSysItem.getwSpecialDistance(), wSysItem.getwSpecialRange(), wSysItem.getwSpecialLasttime(), wSysItem.getwParticlemun(), wSysItem.getwShowSpeed(),
							wSysItem.getwHurtRange()));
					break;
				default:
					break;
				}
				break;
			case WID_KNIFE://刀
				out.write(BinaryUtil.toBytas(wSysItem.getwStabTime(), wSysItem.getwStabLightTime(), wSysItem.getwStabDistance(), wSysItem.getwStabLightDistance(), wSysItem.getwStabWidth(),
						wSysItem.getwBackFactor(), wSysItem.getwStabHurt(), wSysItem.getwStabLightHurt(), wSysItem.getBackBoostPlus()));
				break;
			case WID_BIOCHEMICAL://僵尸主武器
				out.write(BinaryUtil.toBytas(wSysItem.getwStabTime(), wSysItem.getwStabLightTime(), wSysItem.getwStabDistance(), wSysItem.getwStabLightDistance(), wSysItem.getwStabWidth(),
						wSysItem.getwBackFactor(), wSysItem.getwStabHurt(), wSysItem.getwStabLightHurt(), wSysItem.getBackBoostPlus(), wSysItem.getwLastTime(), wSysItem.getwAccuracyTime()));
				break;
			case WID_BIOCHEMICAL_AVO://僵尸副武器
				out.write(BinaryUtil.toBytas(wSysItem.getwLastTime(), wSysItem.getwAccuracyTime(), wSysItem.getwStabHurt()));
				break;
			case WID_THROW://投掷武器
			case WID_GRENADE://手雷
				out.write(BinaryUtil.toBytas(wSysItem.getwExplodeTime(), wSysItem.getwReadyTime(), wSysItem.getwThrowOutTime(), wSysItem.getwHurtRange(), wSysItem.getwHurt()));
				break;
			case WID_FLASH://闪光弹
				out.write(BinaryUtil.toBytas(wSysItem.getwExplodeTime(), wSysItem.getwReadyTime(), wSysItem.getwThrowOutTime(), wSysItem.getwFlashRangeStart(), wSysItem.getwFlashRangeEnd(),
						wSysItem.getwTimeMax(), wSysItem.getwTimeFade(), wSysItem.getwFlashBackFactor()));
				break;
			case WID_SMOKE://烟雾
				out.write(BinaryUtil.toBytas(wSysItem.getwExplodeTime(), wSysItem.getwReadyTime(), wSysItem.getwThrowOutTime(), wSysItem.getwTime()));
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 循环检查玩家的当前装备道具，找到已触发的套装属性，伪装成一个新的玩家道具附加属性。<br/>
	 * 该方法伪装的玩家道具属性不写入数据库和缓存，目前仅用于战斗前获取玩家数据的输出流
	 * @param equips
	 * @throws Exception
	 */
	private void activeSuitGunProperty(Collection<WPlayerItem> equips) throws Exception {
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		for (WPlayerItem wPlayerItem : equips) {
			WSysItemGunProperty suitGunProperty = null;//触发的套装属性。下面经过判断后，如果套装属性已触发，该值不为null
			WSysItem wSysItem = wSysItemMap.get(wPlayerItem.getItemId());
			int suitId = wSysItem.getNeedTeamPlaceLevel();//套装ID
			if (suitId > 0) {//该道具可能是套装的一部分，且不一定触发套装属性
				suitGunProperty = wSysItemGunPropertyService.findList(null).stream().filter(p -> {//检查该道具属于几件套
							boolean b = false;
							List<Integer> list = p.getMultiTypeMap().getOrDefault(EItemGunPropertyType.SUIT_ID.getNumber(), new ArrayList<>());
							b = !list.isEmpty() && list.get(0) == suitId;//对应套装ID
							if (b) {
								list = p.getMultiTypeMap().getOrDefault(EItemGunPropertyType.SUIT_ITEM_SUB_TYPE.getNumber(), new ArrayList<>());
								b = !list.isEmpty() && list.get(0) == wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType();//对应父子类型
							}
							return b;
						}).findFirst().orElse(null);
				int suitGroup = suitGunProperty.getMultiTypeMap().getOrDefault(EItemGunPropertyType.SUIT_GROUP.getNumber(), new ArrayList<>()).stream().findAny().orElse(0);//找到该套装属于几件套
				int suitCount = (int) equips.stream().filter(p -> {
					return wSysItemMap.get(p.getItemId()).getNeedTeamPlaceLevel() == suitId;
				}).count();
				if (suitGroup > 0 && suitCount >= suitGroup) {//触发套装属性,伪装成一个新的玩家道具附加属性
					ItemGunProperty itemGunProperty = new ItemGunProperty();
					itemGunProperty.setOpen(0);
					itemGunProperty.setmValue(0);
					itemGunProperty.getGunPropertyList().add(suitGunProperty);
					wPlayerItem.getGunPropertyMap().put("0", itemGunProperty);
				}
			}
		}
	}

	/**
	 * 向输出流写入wPlayerItem的通用参数（武器和服装各有部分对方无用的属性，原编写者不会利用压缩工具，为了节省流量而人工按逻辑分支手写）
	 * @param out
	 * @param wSysCharacter
	 * @param wPlayerItem 当前要写入输出流的玩家道具。如果是伪造的玩家道具，必须保证两点，id=0，sysItemId有效。否则抛出异常
	 * @throws Exception
	 */
	private void writePlayerItemOutputStream(final OutputStream out, WSysCharacter wSysCharacter, WPlayerItem wPlayerItem) throws Exception {
		WSysItem wSysItem = wSysItemService.findEntity(wPlayerItem.getItemId());
		if (null == wPlayerItem.getId() || null == wSysItem) {
			throw new Exception("playerItem id can not be null, or ref sysItemId is in use.");
		}
		out.write(BinaryUtil.toByta(wPlayerItem.getItemId()));
		out.write(BinaryUtil.toByta(wPlayerItem.getId()));
		out.write(BinaryUtil.toByta("<SN" + wSysItem.getId() + "^0>"));	//displayName国际化文本
		out.write(BinaryUtil.toByta(wSysItem.getName()));
		out.write(BinaryUtil.toByta(wSysItem.getNumberParamMap().getOrDefault(EItemNumberParam.color.toString(), EItemColor.TRANSPARENT.getNumber()).intValue()));//颜色
		out.write(BinaryUtil.toByta(null == wPlayerItem ? wSysItem.getStrengthLevel().byteValue() : wPlayerItem.getLevel().byteValue()));//强化等级
		out.write(BinaryUtil.toByta(null == wPlayerItem ? (byte) 0 : wPlayerItem.getGstLevel().byteValue()));//星级
		if (wPlayerItem.getId() == 0) {//伪造道具的耐久取系统设置的最大值
			out.write(BinaryUtil.toByta(WSysConfigService.getPlayerItemDuration().getMaxDuration().byteValue()));
		} else if (wPlayerItem.getUnitType() == EUnitType.TIME.getNumber() && wPlayerItem.getExpireTime().before(new Date())) {//玩家道具限时且过期
			out.write(BinaryUtil.toByta((byte) 0));
		} else {
			out.write(BinaryUtil.toByta(wPlayerItem.getDurable().byteValue()));
		}
		//附加属性
		if (wPlayerItem.getId() == 0) {//伪造的道具直接使用系统道具的附加属性
			out.write(BinaryUtil.toByta((byte) wSysItem.getGunPropertyMap().size()));
			for (String key : wSysItem.getGunPropertyMap().keySet().stream().sorted().collect(Collectors.toList())) {
				ItemGunProperty itemGunProperty = wSysItem.getGunPropertyMap().get(key);
				if (null == itemGunProperty.getOpen()) {//该附加属性无内容
					out.write(BinaryUtil.toByta((byte) 0));
				} else {
					out.write(BinaryUtil.toByta((byte) itemGunProperty.getGunPropertyList().size()));
					for (WSysItemGunProperty p : itemGunProperty.getGunPropertyList()) {
						out.write(BinaryUtil.toBytas(p.getIndex().shortValue(), p.getValue().shortValue(), p.getValue2().shortValue(), p.getTime().shortValue()));
					}
				}
			}
		} else {//玩家道具
			out.write(BinaryUtil.toByta((byte) wPlayerItem.getGunPropertyMap().size()));
			for (String key : wPlayerItem.getGunPropertyMap().keySet().stream().sorted().collect(Collectors.toList())) {
				ItemGunProperty itemGunProperty = wPlayerItem.getGunPropertyMap().get(key);
				if (null == itemGunProperty.getOpen()) {//该附加属性无内容
					out.write(BinaryUtil.toByta((byte) 0));
				} else {
					out.write(BinaryUtil.toByta((byte) itemGunProperty.getGunPropertyList().size()));
					for (WSysItemGunProperty p : itemGunProperty.getGunPropertyList()) {
						out.write(BinaryUtil.toBytas(p.getIndex().shortValue(), p.getValue().shortValue(), p.getValue2().shortValue(), p.getTime().shortValue()));
					}
				}
			}

		}
		//贴图资源(以系统角色的资源ResourceP为背景，再添加或者替换系统道具的资源ResourceStable)
		List<String> costumeResourceList = wSysCharacter.getResourcePList();
		while (costumeResourceList.size() < wSysItem.getcId()) {
			costumeResourceList.add(null);
		}
		costumeResourceList.set(wSysItem.getcId() - 1, wSysItem.getResourceStable());
		costumeResourceList.removeIf(null);
		out.write(BinaryUtil.toByta(costumeResourceList.size()));
		for (String p : costumeResourceList) {
			out.write(BinaryUtil.toByta(p));
		}
	}
}
