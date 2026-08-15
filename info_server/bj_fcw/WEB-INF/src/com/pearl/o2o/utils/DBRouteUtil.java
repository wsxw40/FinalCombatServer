package com.pearl.o2o.utils;

import com.google.common.collect.ImmutableSet;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.PlayerAchievement0;
import com.pearl.o2o.pojo.PlayerAchievement1;
import com.pearl.o2o.pojo.PlayerAchievement10;
import com.pearl.o2o.pojo.PlayerAchievement2;
import com.pearl.o2o.pojo.PlayerAchievement3;
import com.pearl.o2o.pojo.PlayerAchievement4;
import com.pearl.o2o.pojo.PlayerAchievement5;
import com.pearl.o2o.pojo.PlayerAchievement6;
import com.pearl.o2o.pojo.PlayerAchievement7;
import com.pearl.o2o.pojo.PlayerAchievement8;
import com.pearl.o2o.pojo.PlayerAchievement9;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerChest;
import com.pearl.o2o.pojo.PlayerGrowthMission;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerPack;


public class DBRouteUtil {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final ImmutableSet<Class<? extends BaseMappingBean>> DBRouteClazz = ImmutableSet.<Class<? extends BaseMappingBean>>of(
			PlayerMission.class,PlayerItem.class,PlayerPack.class,CharacterData.class,PlayerBuff.class,PlayerActivity.class,PlayerChest.class,PlayerGrowthMission.class,
			PlayerAchievement0.class,PlayerAchievement1.class,PlayerAchievement2.class,PlayerAchievement3.class,PlayerAchievement4.class,PlayerAchievement5.class,PlayerAchievement6.class,
			PlayerAchievement7.class,PlayerAchievement8.class,PlayerAchievement9.class,PlayerAchievement10.class,BuyItemRecord.class);
	
	@SuppressWarnings("rawtypes")
	private static final ImmutableSet<Class<? extends BaseMappingBean>> BigDBRouteClazz = ImmutableSet.<Class<? extends BaseMappingBean>>of(
			BuyItemRecord.class);
    public static <T extends BaseMappingBean<T>> String getTableSuffix(Class<T> mappingBeanClass, int fid) {
		int tables =Constants.DEFAULT_DB_SIZE;//table size
		int  perTable=Constants.DEFAULT_DB_NUM;//playerId size per table
		int bigPerTable=perTable*10;  //对于信息少的  分表中playerID范围扩大
		
//		int maxTables=Constants.DEFAULT_MAX_DB_TABLES;
		return DBRouteClazz.contains(mappingBeanClass)? BigDBRouteClazz.contains(mappingBeanClass)?
				String.valueOf(getSuffix(tables, bigPerTable, fid)):
				String.valueOf(getSuffix(tables, perTable, fid)):"";
	}
    
    // 根据分表数，每个表的记录大小，获取分表的后缀
    private static int getSuffix(int tables, int perTable, int id) {
  		for (int i = 1; i <= tables; i++) {
  			if (id <= perTable * i) {
  				return i;
  			}
  		}
  		return tables;
  	}

//    // 根据分表数，每个表的记录大小，获取分表的后缀
//    private static int getSuffix(int tables, int perTable,int maxTables, int id) {
//		for (int i = 1; i <= tables; i++) {
//			if ((id/perTable) %maxTables <=  i) {
//				return i;
//			}
//		}
//		return tables;
//	}

}
