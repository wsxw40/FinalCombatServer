package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.proto.enums.EItemShoppingType;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.IdComparatorClass;
import com.pearl.o2o.utils.ServiceLocator;

public class SysItemDao extends BaseMappingDao {
	private static final int DEFAULTID = 1;
	private static Map<Integer, SysItem> allSysitem = new HashMap<Integer, SysItem>();
	private static Map<String, List<SysItem>> classifySysItemMap = null;
	@SuppressWarnings("unchecked")
	public Map<Integer, SysItem> getAllSysItemMap() {
		if(allSysitem.isEmpty()){
			allSysitem=queryMappingBeanMapByRelatedId(SysItem.class, DEFAULTID);
			for(SysItem item : allSysitem.values()){
				item.init();
				try {
					ServiceLocator.getService.joinPayment(item);
					item.initFightNumFront();

					if(item.getNeedTeamPlaceLevel()>99){
						item.setBelongSuit(ConfigurationUtil.SUITMAP.get(item.getNeedTeamPlaceLevel()));
					}
				} catch (Exception e) {
					ServiceLocator.exceptionLog.warn("getAllSysItemMap joinPayment sysItemId = " + item.getId(), e);
				}
			}
		}
		return allSysitem;
	}
//	@SuppressWarnings("unchecked")
//	public Map<Integer, SysItem> getAllSysItemMapReal() {
//		return queryMappingBeanMapByRelatedId(SysItem.class, DEFAULTID);
//	}
	@SuppressWarnings("unchecked")
	public List<SysItem> getAllSystemItem() {
		Map<Integer, SysItem> allSysitems=getAllSysItemMap();
		List<SysItem> sysitemsList=new ArrayList<SysItem>(allSysitems.values());
		Collections.sort(sysitemsList, new IdComparatorClass());
		return sysitemsList;
	}

	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemListByCharacter(final int type, int characterId,int...isPopular) {
		List<SysItem> returnList=new ArrayList<SysItem>();
		List<SysItem> newList=new ArrayList<SysItem>();
		List<SysItem> hotList=new ArrayList<SysItem>();
		List<SysItem> vipList=new ArrayList<SysItem>();
		List<SysItem> webList=new ArrayList<SysItem>();
		Map<Integer, SysItem> allSysitems=getAllSysItemMap();
		for(Map.Entry<Integer, SysItem> entry: allSysitems.entrySet()) {
			SysItem si=allSysitems.get(entry.getKey());
			String[] characterIds=si.getCharacterIds();
			if(si.getIsPopular()>0&&type==Constants.DEFAULT_IS_POPULAR&&isPopular!=null&&isPopular.length>0&&isPopular[0]>0){
					returnList.add(si);
			}else if(si.getType()==type){
				for(String id:characterIds){
					if(characterId==Integer.valueOf(id)){
						if(si.getIsPopular()==1){
							hotList.add(si);
						}else if(si.getIsNew()==1){
							newList.add(si);
						}else if(si.getIsVip()>=1){
							vipList.add(si);
						}else if(si.getIsWeb()==1){
							webList.add(si);
						}else {
							returnList.add(si);
						}
						break;
					}
				}
			}
		}
//		Collections.sort(returnList, new Comparator<SysItem>(){
//
//			@Override
//			public int compare(SysItem o1, SysItem o2) {
//				if(type==1){
//					return o1.getWId().compareTo(o2.getWId());
//				}else if(type==4){
//					return o1.getIId().compareTo(o2.getIId());
//				}else{
//					return ((Integer)o1.getId()).compareTo((Integer)o2.getId());
//				}
//			}});

		Collections.sort(newList, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return (((SysItem)o1).getItemIndex()).compareTo(((SysItem)o2).getItemIndex());
			}
		});
		Collections.sort(vipList, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return (((SysItem)o1).getItemIndex()).compareTo(((SysItem)o2).getItemIndex());
			}
		});
		Collections.sort(returnList, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return (((SysItem)o1).getItemIndex()).compareTo(((SysItem)o2).getItemIndex());
			}
		});
		Collections.sort(webList, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return (((SysItem)o1).getItemIndex()).compareTo(((SysItem)o2).getItemIndex());
			}
		});
		Collections.sort(hotList, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				return (((SysItem)o1).getItemIndex()).compareTo(((SysItem)o2).getItemIndex());
			}
		});
		vipList.addAll(webList);
		vipList.addAll(newList);
		vipList.addAll(hotList);
		vipList.addAll(returnList);
		return vipList;
	}
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemList(int type, int subType) {
		List<SysItem> returnList=new ArrayList<SysItem>();
		List<SysItem> sysitems=getSystemItemList(type);
		for(SysItem si:sysitems) {

			if(si.getSubType()==subType){
				returnList.add(si);
			}
		}
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}

	/**
	 * method for flex only
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysItem> getSystemItemList(int type) {
		List<SysItem> returnList=new ArrayList<SysItem>();
		Map<Integer, SysItem> allSysitems=getAllSysItemMap();
		for(Map.Entry<Integer, SysItem> entry: allSysitems.entrySet()) {
			SysItem si=allSysitems.get(entry.getKey());
			if(si.getType()==type){
				returnList.add(si);
			}
		}
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}

	@SuppressWarnings("unchecked")
	public SysItem getSystemItemById(Integer itemId) {
		Map<Integer, SysItem> allSysitems=getAllSysItemMap();
		return allSysitems.get(itemId);
	}
	@SuppressWarnings("unchecked")
	public Integer createSysItem(SysItem sysItem) throws Exception{
		Integer id = insertObjIntoDBAndCache(sysItem, DEFAULTID);
		setAllSysitem(new HashMap<Integer, SysItem>());
		setClassifySysItemMap(null);
		return id;
	}
	@SuppressWarnings("unchecked")
	public void deleteSysItem(SysItem sysItem) throws Exception{
		deleteObjFromDBAndCache(sysItem, DEFAULTID);
	}
	public void deleteSysItemHard(SysItem sysItem) throws Exception{
		this.getSqlMapClientTemplate().delete("SysItem.deleteHard", sysItem);
	}
	public void updateSysItem(SysItem sysItem) throws Exception{
		updateMappingBeanInCache(sysItem,DEFAULTID);
	}

	/* -------------------------------- 华丽的分割线----------------------------------------- */
	public static Map<Integer, SysItem> getAllSysitem() {
		return allSysitem;
	}
	public static void setAllSysitem(Map<Integer, SysItem> allSysitem) {
		ServiceLocator.fileLog.warn("drop all sysitem in memory");
		SysItemDao.allSysitem = allSysitem;
	}
	public Map<String, List<SysItem>> getClassifySysItemMap() throws Exception {
		if(null == classifySysItemMap || classifySysItemMap.isEmpty()){
			initClassifySysItemMap();
		}
		return classifySysItemMap;
	}
	public void setClassifySysItemMap(Map<String, List<SysItem>> classifySysItemMap) {
		SysItemDao.classifySysItemMap = classifySysItemMap;
	}

//	@SuppressWarnings("unchecked")
//	public Integer createSysPackage(Integer packageId,Integer itemId){
//		HashMap param = new HashMap();
//		param.put("packageId", packageId);
//		param.put("itemId", itemId);
//		return (Integer)this.getSqlMapClientTemplate().insert("SysItem.insertPackage", param);
//	}

	                /**
	 * 初始化classifySysItemMap
	 * 依照SysItem的type和subType对SysItem进行分类
	 * @throws Exception
	 */
	private void initClassifySysItemMap() throws Exception{
		//键：角色ID_type_subType_payType
		classifySysItemMap = new HashMap<String, List<SysItem>>();
		//一. 对角色商城的物品分类
		List<SysCharacter> chracterList = ServiceLocator.getService.getDefaultSysCharacterList();
		for(SysCharacter sc : chracterList) {
			int characterId = sc.getId();
			for(int type = Constants.NUM_ONE; type <= Constants.DEFAULT_PACKAGE_TYPE; type++) {
				switch(type){
				//1. 武器类
				case Constants.DEFAULT_WEAPON_TYPE:
					List<SysItem> weaponList = getSystemItemListByCharacter(type, characterId);
//					Collections.sort(weaponList, new ComparatorUtil.SysItemIndexComparatorClass());
					for(int i = 0; i < Constants.weaponTypeArray[0].length; i++){
						for(int j = 0; j < Constants.weaponTypeArray[1].length; j++){
							int subType = Constants.weaponTypeArray[0][i];
							int payType = Constants.weaponTypeArray[1][j];
							String key = getClassifyKey(characterId, type, subType, payType);
							List<SysItem> value = new ArrayList<SysItem>();
							for(SysItem item : weaponList){
								if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
									if(subType == 0){
										addPayTypeList(item, value, payType);
									} else if(subType == 1){
										if(item.getType() == 1 && item.getSubType() == 1){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == 2){
										if(item.getType() == 1 && item.getSubType() == 2){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == 3){
										if(item.getType() == 1 && item.getSubType() == 3){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == 4){
										if(item.getType() == 1 && item.getSubType() == 4){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == Constants.SHOP_VIP_INDEX){
										if(item.getType() == 1 && item.getIsVip() >= 1){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == Constants.SHOP_WEB_INDEX){
										if(item.getType() == 1 && item.getIsWeb() == 1){
											addPayTypeList(item, value, payType);
										}
									}
								}
							}
							classifySysItemMap.put(key, value);
						}
					}
					break;
				//2. 服装类
				case Constants.DEFAULT_COSTUME_TYPE:
					List<SysItem> clothingList = getSystemItemListByCharacter(type, characterId);
//					Collections.sort(clothingList, new ComparatorUtil.SysItemIndexComparatorClass());
					for(int i = 0; i < Constants.clothTypeArray[0].length; i++){
						for(int j = 0; j < Constants.clothTypeArray[1].length; j++){
							int subType = Constants.clothTypeArray[0][i];
							int payType = Constants.clothTypeArray[1][j];
							String key = getClassifyKey(characterId, type, subType, payType);
							List<SysItem> value = new ArrayList<SysItem>();
							for(SysItem item : clothingList){
								if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
									if(subType == 0){
										addPayTypeList(item, value, payType);
									} else if(subType == Constants.SHOP_VIP_INDEX){
										if(item.getType() == 2 && item.getIsVip() >= 1){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == Constants.SHOP_WEB_INDEX){
										if(item.getType() == 2 && item.getIsWeb() == 1){
											addPayTypeList(item, value, payType);
										}
									}
								}
							}
							classifySysItemMap.put(key, value);
						}
					}
					break;
				//3. 配饰类
				case Constants.DEFAULT_PART_TYPE:
					List<SysItem> partList = getSystemItemListByCharacter(type, characterId);
//					Collections.sort(partList, new ComparatorUtil.SysItemIndexComparatorClass());
					for(int i = 0; i < Constants.partTypeArray[0].length; i++){
						for(int j = 0; j < Constants.partTypeArray[1].length; j++){
							int subType = Constants.partTypeArray[0][i];
							int payType = Constants.partTypeArray[1][j];
							String key = getClassifyKey(characterId, type, subType, payType);
							List<SysItem> value = new ArrayList<SysItem>();
							for(SysItem item : partList){
								if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
									if(subType == 0){
										addPayTypeList(item, value, payType);
									} else if(subType == 1){
										if(item.getType() == 3 && item.getSubType() == 1){
											addPayTypeList(item, value, payType);
										}
									} else if(subType == 2){
										if(item.getType() == 3 && item.getSubType() == 2){
											addPayTypeList(item, value, payType);
										}
									}else if(subType == Constants.SHOP_VIP_INDEX){
										if(item.getType() ==3 && item.getIsVip() >= 1){
											addPayTypeList(item, value, payType);
										}
									}else if(subType == Constants.SHOP_WEB_INDEX){
										if(item.getType() == 3 && item.getIsWeb() == 1){
											addPayTypeList(item, value, payType);
										}
									}
								}
							}
							classifySysItemMap.put(key, value);
						}
					}
					break;
				}
			}
		}

		//二. 对道具商城物品进行分类
		//4. 道具类
		List<SysItem> itemList = getSystemItemListByCharacter(Constants.DEFAULT_ITEM_TYPE, 0);
//		Collections.sort(itemList, new ComparatorUtil.SysItemIndexComparatorClass());
		for(int i = 0; i < Constants.itemTypeArray[0].length; i++){
			for(int j = 0; j < Constants.itemTypeArray[1].length; j++){
				int subType = Constants.itemTypeArray[0][i];
				int payType = Constants.itemTypeArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_ITEM_TYPE, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : itemList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						} else if(subType == 1){
							if(item.getSubType() == 1){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 2){
							if(item.getSubType() == 2){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 3){
							if(item.getSubType() == 3){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 4){
							if (item.getSubType() == 4 || item.getSubType() == 5) {  //20150114 功能类内整合非卖类
								addPayTypeList(item, value, payType);
							}
						}else if(subType == 5){
							if(item.getSubType() == 5){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue()){
							if(item.getSubType() == Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue()){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}

		//5. 素材
		List<SysItem> materialList = getSystemItemListByCharacter(Constants.DEFAULT_MATERIAL_TYPE, 0);
//		Collections.sort(materialList, new ComparatorUtil.SysItemIndexComparatorClass());
//		for (int payType = 0; payType <= 2; payType++) {
//			String key = getClassifyKey(0, Constants.DEFAULT_MATERIAL_TYPE, 0, payType);
//			List<SysItem> value = new ArrayList<SysItem>();
//			for (SysItem item : materialList) {
//				if (Constants.BOOLEAN_NO.equals(item.getIsDeleted())) {
//					addPayTypeList(item, value, payType);
//				}
//			}
//			classifySysItemMap.put(key, value);
//		}
		for(int i = 0; i < Constants.materialTypeArray[0].length; i++){
			for(int j = 0; j < Constants.materialTypeArray[1].length; j++){
				int subType = Constants.materialTypeArray[0][i];
				int payType = Constants.materialTypeArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_MATERIAL_TYPE, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : materialList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						} else if(subType == 1){
							if(item.getSubType() == 1){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 2){
							if(item.getSubType() == 2){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 3){
							if(item.getSubType() == 3){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 4){
							if(item.getSubType() == 4){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}

		//6. 蓝图
		List<SysItem> blueprintList = getSystemItemListByCharacter(Constants.DEFAULT_BLUEPRINT_TYPE, 0);
//		Collections.sort(blueprintList, new ComparatorUtil.SysItemIndexComparatorClass());
		for(int i=0;i<Constants.blueTypeArray[0].length;i++){
			for(int j=0;j<Constants.blueTypeArray[1].length;j++){
				int subType = Constants.blueTypeArray[0][i];
				int payType = Constants.blueTypeArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_BLUEPRINT_TYPE, subType, payType);
				List<SysItem> value=new ArrayList<SysItem>();
				for(SysItem item : blueprintList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}
		//7. 大礼包
		List<SysItem> packageList = getSystemItemListByCharacter(Constants.DEFAULT_PACKAGE_TYPE, 0);
//		Collections.sort(packageList, new ComparatorUtil.SysItemIndexComparatorClass());
		for(int i=0;i<Constants.packageTypeArray[0].length;i++){
			for(int j=0;j<Constants.packageTypeArray[1].length;j++){
				int subType=Constants.packageTypeArray[0][i];
				int payType=Constants.packageTypeArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_PACKAGE_TYPE, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : packageList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip()>= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}

		//8. 打开类
		List<SysItem> openTypeList = getSystemItemListByCharacter(Constants.DEFAULT_OPEN_TYPE, 0);
		for(int i=0;i<Constants.useTypeArray[0].length;i++){
			for(int j=0;j<Constants.useTypeArray[1].length;j++){
				int subType=Constants.useTypeArray[0][i];
				int payType=Constants.useTypeArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_OPEN_TYPE, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : openTypeList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						} else if(subType == 1){
							addPayTypeList(item, value, payType);
						} else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}


		//9. 资源争夺战类
		List<SysItem> zyzdzTypeList = getSystemItemListByCharacter(Constants.DEFAULT_ZYZDZ_TYPE, 0);
		for(int i=0;i<Constants.zyzdzArray[0].length;i++){
			for(int j=0;j<Constants.zyzdzArray[1].length;j++){
				int subType=Constants.zyzdzArray[0][i];
				int payType=Constants.zyzdzArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_ZYZDZ_TYPE, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : zyzdzTypeList){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						} else if(subType == 1){
							if(item.getSubType() == 1){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 2){
							if(item.getSubType() == 2){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 3){
							if(item.getSubType() == 3){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == 4){
							if(item.getSubType() == 4){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == 5){
							if(item.getSubType() == 5){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == 6){
							if(item.getSubType() == 6){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}

					}
				}
				classifySysItemMap.put(key, value);
			}
		}

		//10.推荐
		List<SysItem> popular = getSystemItemListByCharacter(Constants.DEFAULT_IS_POPULAR, 0, 1);
		for(int i = 0; i < Constants.popularArray[0].length; i++){
			for(int j = 0; j < Constants.popularArray[1].length; j++){
				int subType = Constants.popularArray[0][i];
				int payType = Constants.popularArray[1][j];
				String key = getClassifyKey(0, Constants.DEFAULT_IS_POPULAR, subType, payType);
				List<SysItem> value = new ArrayList<SysItem>();
				for(SysItem item : popular){
					if (item.getShoppingType() != EItemShoppingType.UNSALE.getNumber()) {
						if(subType == 0){
							addPayTypeList(item, value, payType);
						} else if(subType == 1){
							if(item.getSubType() == 1){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 2){
							if(item.getSubType() == 2){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 3){
							if(item.getSubType() == 3){
								addPayTypeList(item, value, payType);
							}
						} else if(subType == 4){
							if(item.getSubType() == 4){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_VIP_INDEX){
							if(item.getIsVip() >= 1){
								addPayTypeList(item, value, payType);
							}
						}else if(subType == Constants.SHOP_WEB_INDEX){
							if(item.getIsWeb() == 1){
								addPayTypeList(item, value, payType);
							}
						}
					}
				}
				classifySysItemMap.put(key, value);
			}
		}

	}

	private void addPayTypeList(SysItem si, List<SysItem> list, int payType){
		switch(payType){
		case 0:
			list.add(si);
			break;
		case 1://fc点
			if(si.getCrPricesList() != null && si.getCrPricesList().size() > 0){
				list.add(si);
			}
			break;
		case 2://c币
			if(si.getGpPricesList() != null && si.getGpPricesList().size() > 0){
				list.add(si);
			}
			break;
		case 5://黑晶石
			if(si.getResPricesList() != null && si.getResPricesList().size() > 0){
				list.add(si);
			}
			break;
		}
	}

	                /**
	 * 通过此方法，获取sysitem分类的key
	 * @param characterId
	 * @param type
	 * @param subType
	 * @param payType
	 * @return (角色ID_type_subType_payType)
	 */
	public static String getClassifyKey(int characterId, int type, int subType, int payType){
		StringBuilder sb = new StringBuilder();
		sb.append(characterId).append("_").append(type).append("_").append(subType).append("_").append(payType);
		return sb.toString();
	}

}
