package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.SysItemVariation;
import com.pearl.o2o.utils.SysItemVariationMagicBox;

public class ResMagicBoxOpen extends BaseClientServlet {

	private static final long serialVersionUID = -847563471118712879L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = { "pid","sid" };
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int sid = StringUtil.toInt(args[1]);
			Player player = getService.getPlayerById(playerId);
			Team team=getService.getTeamByPlayerId(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			//检查队伍是否存在
			if(team==null){
				throw new BaseException(ExceptionMessage.TEAM_NOT_EXIST);
			}
			// 验证是否是资源魔盒
			SysItem resMagicBox=getService.getSysItemByItemId(sid);//宝盒
			if(CommonUtil.isResMagicBox(resMagicBox)){
				throw new Exception(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			//验证钱是否足够
			BuyItemRecord buyItemRecord=getService.getBuyItemRecord(playerId, sid);//购买记录
			Payment resMagicBoxpayment=resMagicBox.getAllResPricesList().get(0);//宝盒对应的payment

			int count=0;
			if(buyItemRecord!=null){
				count=buyItemRecord.getRecord();
			}
			
			//创建本次购买的物品帮助类
			SysItemVariation sysIVa=new SysItemVariationMagicBox(resMagicBox.getId(),resMagicBox.getAllResPricesList().get(0).getCost(),count,resMagicBox.getIValue(),resMagicBox.getIId());			

			int stones=player.getLatestPlayerRes(team.getTeamSpaceLevel()).get(Player.RES);
			int cost=sysIVa.getThisCalCost();
			if(stones<cost){//石头不够
				throw new BaseException(ExceptionMessage.LACK_P_USABLE_STONE);
			}
			
			if(buyItemRecord==null){//如果购买记录为空，则创建购买记录
				buyItemRecord=createService.createBuyItemRecord(playerId, sid, resMagicBoxpayment.getId(), resMagicBoxpayment.getPayType(), sysIVa.getThisCalCost());
			}			
			
			//扣石头
			int frontPay = stones;
			int leftMoney = stones - cost;
			player.setUsableResource(leftMoney);
			

			
			//开启宝箱
			List<OnlineAward> resMagicGifts = getService.getSortedOnlineAwardByType(Constants.ONLINE_AWARD_TYPES.RES_MAGIC_BOX.getValue(),new ComparatorUtil.OnlineAwardComparatorClass(),resMagicBox.getLevel());
			OnlineAward award = getService.randomOnlineAwardFromList(Constants.NUM_ONE, resMagicGifts).get(0);			
			Payment payment = new Payment();
			payment.setUnit(award.getUnit());
			payment.setUnitType(award.getUnitType());
			SysItem awardItem = getService.getSysItemByItemId(award.getItemId());
			award.setSysItem(awardItem);
			
			if(awardItem!=null){
				createService.awardToPlayer(player, awardItem, payment, null, Constants.BOOLEAN_YES);
				log.info("MagicBoxOpen/Award:\t" + playerId + "\t" +awardItem.getId() + "\t" + award.getUnitType() + "\t" + award.getUnit());
				}else{
				log.warn("MagicBoxOpen/SysItemNull:\t"  +award.getItemId());
				throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
			}
			int quantity = award.getUnit();
			if(award.getUnitType()!=Constants.DEFAULT_NUMBASE_TYPE)
				quantity=1;
			//稀有物品发公告
			int totalRare = awardItem.getRareLevel();
			if(award.getUnitType() == Constants.NUM_ONE){
				totalRare *= award.getUnit();
			}
			int color = awardItem.getIsStrengthen() == 1 ? awardItem.getStrengthLevel() : 0;
			if(totalRare >= Constants.HIGH_RARE_LEVEL){
				soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER, 
						CommonUtil.messageFormatI18N(CommonMsg.RES_MAGIC_BOX_SYS, new Object[]{GunProperty.RED + "@!" + player.getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + awardItem.getDisplayName(),award.getUnit()}));
			}
			if(totalRare >= Constants.MAGIC_BOX_HIGH_RARE_LEVEL){
				//nosql记录
				//nosqlService.addPlayerMagixBoxItemId(player, awardItem.getId(),quantity);
			}
			
			//删除缓存中的该物品
			deleteService.deletePlayerItemInMemcached(playerId, awardItem);
			//增加购买次数
			sysIVa.setCount(sysIVa.getCount()+1);
			buyItemRecord.setRecord(sysIVa.getCount());
			updateService.updateBuyItemRecord(buyItemRecord);
			//刷新各种信息
			createService.refreshPlayerTeamALLMessages(player, null, null);
			return Converter.resMagicBoxOpen(award, player, sysIVa, team.getTeamSpaceLevel());

		} catch (BaseException e) {
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("MagicBoxOpen/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}
