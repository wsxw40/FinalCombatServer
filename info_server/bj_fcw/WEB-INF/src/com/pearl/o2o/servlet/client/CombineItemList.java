package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统内部仓库接口
 * @author wuxiaofei
 *
 */
public class CombineItemList extends BaseClientServlet {
	private static final long serialVersionUID = 5117201445216191162L;
	static Logger log = LoggerFactory.getLogger(CombineItemList.class.getName());
	private static final String[] paramNames = { "uid", "pid", "t", "cid", "p" , "st", "ct"};

	protected String innerService(String... args) {
		try {
//			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int characterId = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);//1强化2改装3装换4熔炼5升星(精英强化系统)
			
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct.");
			} else if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
				characterId = Constants.NUM_ZERO;
			}
			int subType = Constants.NUM_ZERO;
			if(null != args[5]){
				subType = StringUtil.toInt(args[5]);
			}
			if(subType < Constants.NUM_ZERO){
				subType = Constants.NUM_ZERO;
			} 
			int combineType = StringUtil.toInt(args[6]);
			if(combineType < Constants.NUM_ONE || combineType > Constants.NUM_FIVE){
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			
			String result = "";
			int pages = Constants.NUM_ZERO;

			List<PlayerItem> itemList = null;
			//角色携带武器
			Map<Integer,Byte> pkayerPacks=new HashMap<Integer, Byte>();
			
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct in getPlayerItems.");
			} else {
				List<PlayerItem> itemArray = getService.getPlayerItemList1(playerId, type, characterId, subType);
				List<PlayerPack> playerPacks = ServiceLocator.getService.getPlayerPackList(playerId);
				Map<Integer, PlayerItem> playerItemMap = getService.getPlayerItemMapByType1(playerId, type, characterId, subType);
				
				for(PlayerPack pp : playerPacks){
					pkayerPacks.put(pp.getPlayerItemId(), null);
					PlayerItem pi = playerItemMap.get(pp.getPlayerItemId());
					if(combineType == Constants.NUM_FOUR){
						if(type>Constants.NUM_THREE&&pi != null&&pi.getPack()==Constants.NUM_ZERO){
							SysItem si = ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
							if(si.getEvaluateRmb()>0||pi.getLevel()>0||si.getMType()==31||si.getMType()==32){
								pi.setSysItem(si);
								pi.initWithoutPart(si);
								itemArray.add(pi);
							}
						}
					}else if(pi != null){
						SysItem si = ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
						
//						if(si.getWId()==Constants.WEAPON_JQT_GUN_ID&&(combineType==2||combineType==3)){
//							continue;
//						}
						pi.setSysItem(si);
						pi.initWithoutPart(si);
						
					}		
					itemArray.add(pi);
				}
				if(type == Constants.DEFAULT_MATERIAL_TYPE){
					List<PlayerItem> tempArray = new ArrayList<PlayerItem>();
					for(PlayerItem pi : itemArray){
						if(combineType == Constants.NUM_ONE){
							if(pi!=null && pi.getSysItem()!=null)
							if(pi.getSysItem().getMType() == Constants.STRENGTH_PART || pi.getSysItem().getMType() == Constants.ADD_S || pi.getSysItem().getMType() == Constants.NOT_BREAK || pi.getSysItem().getMType() == Constants.NOT_BACK_LEVEL || pi.getSysItem().getMType() == Constants.STRENGTH_ADD_S){
								tempArray.add(pi);
							}
						} else if(combineType == Constants.NUM_TWO){
							if(pi!=null && pi.getSysItem()!=null)
							if(pi.getSysItem().getMType() == Constants.WEAPON_PART || pi.getSysItem().getMType() == Constants.CLOTH_PART || pi.getSysItem().getMType() == Constants.INSERT_PART){
								tempArray.add(pi);
							}
						} else if(combineType == 4&&pi!=null && pi.getSysItem()!=null &&(pi.getSysItem().getEvaluateRmb()>0||pi.getLevel()>0||pi.getSysItem().getMType()==31||pi.getSysItem().getMType()==32)&&pi.getPack()==0){
							tempArray.add(pi);
						}
//						else if(combineType == Constants.NUM_THREE){
//							if(pi.getSysItem().getMType() == 10){
//								tempArray.add(pi);
//							}
//						}
					}
					itemArray = tempArray;
				}
				if (itemArray != null) {
					List<PlayerItem> tempList = new ArrayList<PlayerItem>();
					if(combineType == Constants.NUM_FOUR){//如果是熔炼
						for(PlayerItem pi : itemArray){
							if(pi!=null && pi.getSysItem()!=null && "N".equals(pi.getIsDefault())&&combineType == 4&&(pi.getSysItem().getEvaluateRmb()>0||pi.getLevel()>0||pi.getSysItem().getMType()==31||pi.getSysItem().getMType()==32)&&pi.getPack()==0&&pi.getBuff()==0){
								if(pi.getSysItem().getMType()==32){
									meltingItemStr(pi);
								}
								//zlm2015-5-7-限时装备-开始 --判断是否可以熔炼--
								if (!pi.getProvisional_item_flag()) {
									tempList.add(pi);
								}
								//zlm2015-5-7-限时装备-结束
								/*tempList.add(pi);*/
							}
						}
					}else if(type == Constants.DEFAULT_WEAPON_TYPE || type == Constants.DEFAULT_COSTUME_TYPE || type == Constants.DEFAULT_PART_TYPE){
						for(PlayerItem each : itemArray){
							if(each!=null && each.getSysItem()!=null && each.getSysItem().getIsStrengthen() == Constants.NUM_ONE){//是否可以强化
//								if(each.getSysItem().getWId()==Constants.WEAPON_JQT_GUN_ID&&(combineType==2||combineType==3)){
//									continue;
//								}
								//如为合成[升星]
								if(combineType == Constants.NUM_FIVE)
								{
									//过滤主武器，去掉普通武器，并取消身上携带
									if(!pkayerPacks.containsKey(each.getId())
											&&
											(
											(each.getSysItem().getType() == Constants.DEFAULT_WEAPON_TYPE && each.getSysItem().getSubType() == Constants.NUM_ONE)
											||(each.getSysItem().getType() == Constants.DEFAULT_COSTUME_TYPE && each.getSysItem().getSubType() == Constants.NUM_ONE)
											||(each.getSysItem().getType() == Constants.DEFAULT_PART_TYPE && (each.getSysItem().getSubType() == Constants.NUM_ONE || each.getSysItem().getSubType() == Constants.NUM_TWO))
											)
											&&each.getSysItem().getRareLevel() >= Constants.RARE_LEVEL[0])
									{
										tempList.add(each);
//										System.out.println(each.getSysItem().getDisplayNameCN()+":"+each.getGst_level());
									}	 
								}
								else{
									tempList.add(each);
								}
							}
						}
					} else {
						tempList = itemArray;
					}
					pages = CommonUtil.getStrengthSubListPages(tempList, type);
					if(page > pages){
						page = pages;
					}
					if (page <= Constants.NUM_ZERO) {
						page = Constants.NUM_ONE;
					}
//					if(type<=Constants.DEFAULT_PART_TYPE||type>=Constants.DEFAULT_WEAPON_TYPE){//装备自动排序
//						Collections.sort(tempList, new ComparatorUtil.PlayerStorgeComparator());
//					}else{
					Collections.sort(tempList, new ComparatorUtil.PlayerItemIndexComparatorClass());
//					}
					int fromIndex = (page - 1) * Constants.STRENGTH_PAGE_SIZE[type - 1];
					int toIndex = (page) * Constants.STRENGTH_PAGE_SIZE[type - 1];
					itemList = tempList.subList(fromIndex, toIndex > tempList.size() ? tempList.size() : toIndex);
				}
			}
			Player player = getService.getSimplePlayerById(playerId);
			
			if(pages==Constants.NUM_ZERO){
				pages=Constants.NUM_ONE;
			}
			result = Converter.combineItemList(page, pages, itemList, player.getRank(),spellParam(playerId));
			return result;
		} catch (Exception e) {
			log.warn("Exception in GetPlayerItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	private List<String> spellParam(int playerId) throws Exception{
		List<String> result = new ArrayList<String>();
		/* 武器每个星级等级需要的经验 **/
		String maxStartExp = "";
		/* 武器每把可充当的星级经验,根据品质**/
//		String rareStartExp = "";
		/* 每日一次武器品级对应的增加经验**/
		String everyDayExp = "";
		/* 每个品质对应的每日一次是否可用**/
		String everyDayIsVisiable = "";
		/* 每个品质对应的每日一次需要的C币**/
		String everyDayCPoint = "";
		String temp = "";
		//精良-卓越-稀有-大师
		for(int i = 0; i < Constants.RARE_LEVEL.length;i++){
			maxStartExp+=",{";
			temp = "";
			for(int j=0; j< Constants.MAX_GST_LEVEL;j++){
				temp += ","+String.valueOf(Constants.getGst_Level_Exp(j,Constants.RARE_LEVEL[i]));
			}
			maxStartExp			+= temp.substring(1) + "}";
//			rareStartExp 		+= "," + Constants.getGst_Level_Exp(Constants.RARE_LEVEL[i]);
			everyDayExp 		+= "," + Constants.EVERY_DAY_EXPERIENCE[i];
			String key = Constants.PLAYER_EVERY_DAY_E_FLAG_KEY + playerId + Constants.DELIMITER + Constants.getQualityLevel(Constants.RARE_LEVEL[i]);
			String toDay = DateUtil.getDf2().format(new Date());
			String toKey = nosqlService.getNosql().get(key);
			String flag = toKey==null||!toKey.equals(toDay)?"1":"0"; 
			everyDayIsVisiable 	+= "," + flag;
			everyDayCPoint += "," + Constants.EVERY_DAY_C_POINT[i];
		} 
		maxStartExp 		= "{" + maxStartExp.substring(1)		+"}";
//		rareStartExp		= "{" + rareStartExp.substring(1)		+"}";
		everyDayExp 		= "{" + everyDayExp.substring(1)		+"}";
		everyDayIsVisiable 	= "{" + everyDayIsVisiable.substring(1)	+"}";
		everyDayCPoint 	 	= "{" + everyDayCPoint.substring(1)		+"}";
		result .add(maxStartExp);
//		result .add(rareStartExp);
		result .add(everyDayExp);
		result .add(everyDayIsVisiable);
		result .add(everyDayCPoint);
		
		return result;
	}
	
	private String meltingItemStr(PlayerItem pi){
		Integer[] array = Iterables.toArray(Iterables.transform(MeltingConstants.splitterByUnderScore.split(pi.getSysItem().getResourceChangeable()), MeltingConstants.functionStrToInt), Integer.class);
		HashMultiset<Integer> moduleMultiset = HashMultiset.create(Iterables.transform(MeltingConstants.splitterByColon.split(pi.getSysItem().getResourceStable()), MeltingConstants.functionStrToInt));
		StringBuilder sb=new StringBuilder();
		if(array.length!=0&&moduleMultiset.size()!=0){
			try{
				sb.append("items={");
				for(int i:moduleMultiset){
					SysItem si=getService.getSysItemByItemId(i);
					sb.append("{");
					sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName()).append("\",");
					sb.append("},");
				}
				sb.append("},");
				sb.append("result={");
				SysItem si=getService.getSysItemByItemId(array[0]);
				sb.append("{");
				sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName())
					.append("\",").append(array[1]).append(",").append(array[2]).append(",");
				sb.append("},");
				sb.append("},");
			}catch (Exception e) {
				sb.append("items={");
				sb.append("},");
				sb.append("result={");
				sb.append("},");
			}
		}
		pi.setMeltingItemStr(sb.toString());
		return sb.toString();
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
