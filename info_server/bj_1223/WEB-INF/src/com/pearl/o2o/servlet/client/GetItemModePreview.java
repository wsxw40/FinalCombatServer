//package com.pearl.o2o.servlet.client;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;import org.slf4j.LoggerFactory;
//
//import com.pearl.o2o.pojo.PlayerItem;
//import com.pearl.o2o.pojo.SysItem;
//import com.pearl.o2o.utils.Constants;
//import com.pearl.o2o.utils.Converter;
//import com.pearl.o2o.utils.StringUtil;
//
//public class GetItemModePreview extends BaseClientServlet {
//	private static final long serialVersionUID = 5707425177857498182L;
//	static Logger log = LoggerFactory.getLogger(CreateItemMod.class.getName());
//	private String[] paramNames={"uid","cid","pid","array","part_pid","part_sid"};
//	@Override
//	protected String innerService(String... args) {
//		try {
//			int userId = StringUtil.toInt(args[0]);
//			int playerId = StringUtil.toInt(args[1]);
//			int weaponId = StringUtil.toInt(args[2]);
//			String array = args[3];
////			int partId=StringUtil.toInt(args[4]);
////			int partSId=StringUtil.toInt(args[5]);
//			String part[] = null;
//			if (array != null && !"".equals(array.trim())) {
//				part = array.split(",");
//			}
//			//weapon
//			PlayerItem item = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_WEAPON_TYPE, weaponId);
//			List<PlayerItem> oldList=item.getParts();
//			List<PlayerItem> deleteList=new ArrayList<PlayerItem>();
//			if(oldList!=null){
//				deleteList.addAll(oldList);
//			}
//			List<SysItem> newList=new ArrayList<SysItem>();
//			
//			List<SysItem> partList = new ArrayList<SysItem>();
//			//
//			SysItem sysItem=getService.getSysItemByItemId(userId,playerId,item.getSysItem().getType(), item.getSysItem().getSubType(), item.getItemId());
//			if (part != null && part.length != 0) {
//				for (int i = 0; i < part.length; i++) {
//					int index=StringUtil.toInt(part[i]);
//					if(index!=0){
//						String str = part[i + 2];
//						PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_PART_TYPE,StringUtil.toInt(str));
//						if (playerItem != null) {
//							SysItem syspart=getService.getSysItemByItemId(userId,playerId,playerItem.getSysItem().getType(), playerItem.getSysItem().getSubType(), playerItem.getItemId());
//							if(syspart==null){
//								syspart=getService.getSysItemFromGMList(playerItem.getSysItem().getType(), playerItem.getSysItem().getSubType(), playerItem.getItemId());
//							}
//							partList.add(syspart);
//							boolean isNew=true;
//							if(oldList!=null&&oldList.size()!=0){
//								for(PlayerItem pi:oldList){
//									if(pi.getId() == playerItem.getId()){
//										isNew=false;
//										break;
//									}
//								}
//							}
//							if(isNew){
//								newList.add(syspart);
//							}
//						}
//					}else{
//						String str = part[i + 2];
//						SysItem syspart=getService.getSysItemByItemId(userId,playerId,Constants.DEFAULT_PART_TYPE,StringUtil.toInt( part[i + 1]), StringUtil.toInt(str));
//						partList.add(syspart);
//						newList.add(syspart);
//					}
//					i+=2;
//				}
//				if (partList.size() > 0) {
//					sysItem.setParts(partList);
//					sysItem.putOnPart();
//					item.setResource(sysItem.getResource());
//					item.setDamange(sysItem.getDamange());
//					item.setStopPpower(sysItem.getStopPpower());
//					item.setRecoil(sysItem.getRecoil());
//					item.setAccuracy(sysItem.getAccuracy());
//					item.setShootSpeed(sysItem.getShootSpeed());
//					item.getSysItem().setWAmmoOneClip(sysItem.getWAmmoOneClip());
//					item.getSysItem().setWAmmoCount(sysItem.getWAmmoCount());
//				}
//			} else {
//				item.setParts(null);
//				sysItem.init();
//				item.setResource(sysItem.getResource());
//				item.setDamange(sysItem.getDamange());
//				item.setStopPpower(sysItem.getStopPpower());
//				item.setRecoil(sysItem.getRecoil());
//				item.setAccuracy(sysItem.getAccuracy());
//				item.setShootSpeed(sysItem.getShootSpeed());
//				item.getSysItem().setWAmmoOneClip(sysItem.getWAmmoOneClip());
//				item.getSysItem().setWAmmoCount(sysItem.getWAmmoCount());
//			}
//			if(oldList!=null&&oldList.size()!=0&&partList.size()!=0){
//				for(int i=0;i<oldList.size();i++){
//					PlayerItem pi=(PlayerItem)oldList.get(i);
//					for(SysItem si:partList){
//						if(si.getSubType().equals(pi.getSysItem().getSubType())){
//							deleteList.remove(pi);
//							break;
//						}
//					}
//				}
//			}
//			List<String> deleteResoures=new ArrayList<String>();
//			 if(deleteList!=null&&deleteList.size()>0){
//				String[] changebles=sysItem.getResourceChangeable().split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
//				for(PlayerItem pi:deleteList){
//					if((int)pi.getSysItem().getSubType()<=changebles.length){
//						String resoure=changebles[(int)pi.getSysItem().getSubType()-1];
//						String[] deleteResoure=resoure.split(Constants.DELIMITER_RESOURCE_STABLE);
//						for(String s:deleteResoure){
//							deleteResoures.add(item.getSysItem().getName()+"/"+s);
//						}
//					}
//				}
//			}
//			return  Converter.previewItemMode(item,newList,deleteResoures);
//
//		} catch (Exception e) {
//			log.error("Exception in GetItemModePreview",e);
//			return Converter.error("系统出现异常错误，请联系GM");	
//		}
//	}
//	@Override
//	protected String[] paramNames() {
//		return paramNames;
//	}
//}
