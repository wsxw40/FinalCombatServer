package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetLuckyPackageToPlayer extends BaseClientServlet {

	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(GetLuckyPackageToPlayer.class.getName());
	private static final String[] paramNames = {"pid","level","payType","multiple"};
	protected String innerService(String... args) {
		try{
			final int playerId = StringUtil.toInt(args[0]);
			final int level	 = StringUtil.toInt(args[1]);//1:青铜	2：白银	3:黄金
			final int payType	 = StringUtil.toInt(args[2]);//1使用卡2勋章兑换
			final int multiple = StringUtil.toInt(args[3]);//连抽次数
			
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			List<SysChest> fixList = getService.getChestListNoDeleted(Constants.PACKAGE_TYPE_FIX, level);
			for(SysChest sc :fixList){
				sc.setNumber(sc.getNumber()*multiple);
			}
			String msg	 = getService.getLuckyPackageIsPay(playerId, fixList.get(0), payType, level);
			if(msg!=null){
				return Converter.playerLuckyPackage(null,null, null, msg,null,null,null);
			}
			final List<SysChest> multiRandoms = new ArrayList<SysChest>();
			for(int i=0;i<multiple;i++){
				SysChest random = getService.getRandomChestByLevelAndType(level,payType);
				multiRandoms.add(random);
				
				int systemId = random.getSysItemId();
				int number = random.getNumber();
				int useType = random.getUseType();
				
						
			}
			//如果是新手且开黄金彩盒，必得一使用次数最多的角色的精良武器
			Player player = getService.getPlayerById(playerId);
			if(player!=null && player.getIsChest()!=null){
				if(player.getIsChest()==0 && level== 3){
					List<CharacterData> allCharacterData=getService.getCharacterDataSortByUsedNums(playerId);
				
					SysChest execllentChest=getService.getExcellentWeaponForNewer(allCharacterData.get(0).getCharacterId(),
							level);
				
					if(execllentChest!=null){
						multiRandoms.remove(0);
						multiRandoms.add(0,execllentChest);
						
						player.setIsChest(1); //置为1，以后不再有效
						updateService.updatePlayerInfo(player);
					}
					
				}
			}
			
			// TODO Auto-generated method stub
			for(int i=0;i<multiple;i++){
				SysChest sysChest=multiRandoms.get(i);
						
				ServiceLocator.getLuckyPackageLog.info(String.format("%s\t%s\t%s\t%s\t%s\t%s", 
						playerId,level,payType,sysChest.getSysItemId(),sysChest.getNumber(),sysChest.getUseType()));
						
				String message = String.format(
						"%s\t%s\t%s\t%s\t%s\t%s\t%s", playerId, level, payType, sysChest.getSysItemId(),sysChest.getNumber(),sysChest.getUseType(), sysChest.getChipPrice());
				// 记到analyser server
				transferDataToDc.addLog("bjLuckPackage", message);
			}
				
	
			OnlineAward onlineAward =updateService.updatePlayerLuckyPackagePay(playerId,level,payType,multiple,fixList,multiRandoms);
			Runnable task = new Runnable() {
				@Override
				public void run() {
					for(SysChest random : multiRandoms){
					try {
						Player player = getService.getPlayerById(playerId);
						int color = random.getSysItem().getIsStrengthen() == 1 ? random.getSysItem().getGunProperty().getColor() : 0;
						boolean isBoadcast = false;
						int totalRare = random.getSysItem().getRareLevel();
						if(random.getUseType() == Constants.NUM_ONE){
							totalRare *= random.getNumber();
						}
						if((random.getLevel() == 1 && totalRare > Constants.BRONZE_HIGH_RARE_LEVEL) || (random.getLevel() == 2 && totalRare > Constants.SILVER_HIGH_RARE_LEVEL) || (random.getLevel() == 3 && totalRare > Constants.GOLD_HIGH_RARE_LEVEL)){
							isBoadcast = true;
						}
						if(isBoadcast){
							soClient.proxyBroadCast(Constants.MSG_NBA, 
									Constants.SYSTEM_SPEAKER, CommonUtil.messageFormatI18N(CommonMsg.LUCKY_PACAKGE_SYS, new Object[]{GunProperty.RED + "@!" + player.getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + random.getSysItem().getDisplayName(), random.getNumber()}));
						}
						if(random.getSysItem().getRareLevel()>Constants.BILL_BOARD_RARE_LEVEL){
							soClient.updateBillBoardList( CommonUtil.messageFormatI18N(CommonMsg.LUCKY_PACAKGE_SYS, new Object[]{GunProperty.GOLD + "@!" + player.getName(), GunProperty.GOLD + "@!" + random.getSysItem().getDisplayName(), random.getNumber()}));
						}
					} catch (Exception e) {
						log.error("send proxyBroadCast or updateBillBoardList error.playerId="+playerId+",rarelevel="+random.getSysItem().getRareLevel()+",num="+random.getNumber());
					}
					}
				}
			};
			ServiceLocator.asynTakService.submit(task);
			
			if(null!=onlineAward){
				SysItem si=getService.getSysItemByItemId(onlineAward.getItemId());
				onlineAward.setSysItem(si);
			}
			String[] retStrs = {null,"-1"};
			if(level==3){	
				retStrs = createService.multiRandomPinTu(playerId, multiple, payType);
			}else{
				StringBuilder sb=new StringBuilder();
				if(multiple>1){
					for(int i=1;i<multiple;i++){
						sb.append(",-1");
					}
					retStrs[1]+=sb.toString();
				}
				String playerPinTuFlags = ServiceLocator.nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_KEY+playerId); 
				if(playerPinTuFlags==null||playerPinTuFlags.length()!=Constants.PLAYER_PT_PRI_FLAGS.length()+1||!playerPinTuFlags.matches("[01]*[0-" + Constants.PLAYER_PT_TOTAL_CHANCE + "]")){
					playerPinTuFlags = Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE;
				}
				retStrs[0] = playerPinTuFlags.replaceAll("", ",").replaceFirst(",", "");
				
			}
			
			String randoms = getService.getLuckyPackageRandomNames(level);
			return Converter.playerLuckyPackage(randoms,fixList, multiRandoms,null,onlineAward,retStrs[0],retStrs[1]);
		}catch(Exception e){
			log.warn("Error in GetLuckyPackageList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
