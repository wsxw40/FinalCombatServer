package com.pearl.manager.utils;


public class BiochemicalConstants {
	// 道具 BuffId
	// 特殊生化者
	public static final int ordinaryBuffId = 51;
	// 王者生化者(道具)
	public static final int especialBuffId = 52;
	// 角色 id
	public static final int ordinaryDefaultType = 0;
	public static final int especialDefaultType = 2;
	public static final int ordinaryCId = 20;
	public static final int especialCId = 30;
	
	//僵尸的 武器背包  服装背包
//	private static final Map<Integer, BiochemicalCharacterData> biochemicalMap = new HashMap<Integer, BiochemicalCharacterData>();
//	static{
//		//普通生化体
//		biochemicalMap.put(20, new BiochemicalCharacterData(	20,20,"“瑞泽尔”生化体",	5.5f,10.5f,4.5f,		"body01,hat01","Y",175,175,100,0,1,"cell01",	0,0.75f,0.5f,0.3f,1, 	new int[]{4799},new int[]{4797}));
//		//biochemicalMap.put(21, new BiochemicalCharacterData(	21,21,"“瑞泽尔II”生化体",	4.5f+0.5f,2.5f+8,4.0f,	"body01,hat01","Y",450/2,450/2,100,0,1,"cell02",0,0.75f,0.5f,0.3f,1,	new int[]{4843,4842}, new int[]{4845}));
//		//特殊生化体(道具)
////	biochemicalMap.put(4810, new BiochemicalCharacterData(4810,20,"“毒孢”生化体",		4.5f+0.5f,2.5f+8,4f,	"body01,hat01","Y",400/2,400/2,100,0,1,"cell01",0,0.75f,0.5f,0.3f,1,	new int[]{4799},new int[]{4797}));
//		biochemicalMap.put(4810, new BiochemicalCharacterData(4810,20,"“炽岩”王者生化体",	5f,10.5f,18f,			"body01,hat01","Y",700,700,100,0,1,"cellhero01",0,1.05f,0.5f,0.42f,1,	new int[]{4822,4869},new int[]{4821}));
//		biochemicalMap.put(4811, new BiochemicalCharacterData(4811,20,"“影痕”生化体",		6f,10.5f,5.4f,			"body01,hat01","Y",105,105,100,0,1,"cellrmb01",	0,0.75f,0.5f,0.3f,1,	new int[]{4844},new int[]{4846}));
//		biochemicalMap.put(23, new BiochemicalCharacterData(	23,20,"“巨角”生化体",		5.5f,11f,4.5f,			"body01,hat01","Y",210,210,100,0,1,"cell01",	0,0.75f,0.5f,0.3f,1,	new int[]{4799},new int[]{4797}));
//		biochemicalMap.put(24, new BiochemicalCharacterData(	24,20,"“蜂巢”生化体",		4.5f,2.5f+8,3.5f,		"body01,hat01","Y",140,140,100,0,1,"cell01",	0,0.75f,0.5f,0.3f,1,	new int[]{4799},new int[]{4797}));
//		biochemicalMap.put(25, new BiochemicalCharacterData(	25,20,"“电鳗”生化体",		4.5f+0.5f,11f,4f,		"body01,hat01","Y",140,140,100,0,1,"cell01",	0,0.75f,0.5f,0.3f,1,	new int[]{4799},new int[]{4797}));
//		//王者生化体
//		biochemicalMap.put(26, new BiochemicalCharacterData(	26,30,"“炽岩”王者生化体",	5f,10.5f,18f,			"body01,hat01","Y",700,700,100,0,1,"cellhero01",0,1.05f,0.5f,0.42f,1,	new int[]{4822,4869},new int[]{4821}));
//		//王者生化体(道具)
//		biochemicalMap.put(4812, new BiochemicalCharacterData(4812,30,"“巴隆”王者生化体",	5f,11f,4.5f,			"body01,hat01","Y",525,525,100,0,1,"cellhero01",0,0.75f,0.5f,0.3f,1,	new int[]{4822,4869},new int[]{4821}));
//	} 
//	
////	public static BiochemicalCharacterData getBiochemicalCharacter(int sysItemId){
////		return biochemicalMap.get(sysItemId);
////	}
	
//	public static class BiochemicalCharacterData{
//		private int sysItemId;
//		private SysCharacter sysCharacter;
//		private int[] weapons;
//		private int[] costumes;
//		public BiochemicalCharacterData(int sysItemId ,int cId,String name, float runSpeed, float jumpSpeed, float throwSpeed, String costumeResource,
//				String isDefault, int maxHp, int exHp, int cost, int defaultLevel, int isFired, String resourceName, int isEnable, float controllerHeight,
//				float controllerRadius, float controllerCrouchHeight, int scoreOffset, int[] weapons, int[] costumes) {
//			super();
//			this.sysItemId = sysItemId;
//			this.sysCharacter = new SysCharacter( cId, name,  runSpeed,  jumpSpeed,  throwSpeed,  costumeResource,
//					 isDefault,  maxHp,  exHp,  cost,  defaultLevel,  isFired,  resourceName,  isEnable,  controllerHeight,
//					 controllerRadius,  controllerCrouchHeight,  scoreOffset);
//			this.weapons = weapons;
//			this.costumes = costumes;
//		}
//		public int getSysItemId() {
//			return sysItemId;
//		}
//		public SysCharacter getSysCharacter() {
//			return sysCharacter;
//		}
//		public int[] getWeapons() {
//			return weapons;
//		}
//		public int[] getCostumes() {
//			return costumes;
//		}
//	}
}
