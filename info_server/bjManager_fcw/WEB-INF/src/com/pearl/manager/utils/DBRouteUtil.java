package com.pearl.manager.utils;



public class DBRouteUtil {
//    public static <T extends BaseMappingBean<T>> String getTableSuffix(Class<T> mappingBeanClass, int fid) {
//		int tables =Constants.DEFAULT_DB_SIZE;//playerId size per table
//		int  perTable=Constants.DEFAULT_DB_NUM;//table size
//		if (mappingBeanClass.getName().equals(PlayerMission.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().equals(PlayerItem.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().equals(PlayerPack.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().equals(CharacterData.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().equals(PlayerBuff.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().equals(PlayerActivity.class.getName())) {
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		} else if (mappingBeanClass.getName().contains("com.pearl.o2o.pojo.PlayerAchievement")) {
//			int result = getSuffix(tables, perTable, fid);
//			return String.valueOf(result);
//		} else if (mappingBeanClass.getName().equals(PlayerChest.class.getName())) {
//			int result = getSuffix(tables, perTable, fid);
//			return String.valueOf(result);
//		} else if (mappingBeanClass.getName().equals(PlayerGrowthMission.class.getName())){
//			return String.valueOf(getSuffix(tables, perTable, fid));
//		}
//		return "";
//	}
//    // 根据分表数，每个表的记录大小，获取分表的后缀
//    private static int getSuffix(int tables, int perTable, int id) {
//		for (int i = 1; i <= tables; i++) {
//			if (id <= perTable * i) {
//				return i;
//			}
//		}
//
//		return tables;
//	}
}
