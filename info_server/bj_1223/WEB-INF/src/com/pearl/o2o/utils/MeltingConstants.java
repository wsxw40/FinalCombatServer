/**
 * 
 */
package com.pearl.o2o.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;


/**
 * @author lifengyang
 *
 */
public interface MeltingConstants {
	/**
	 * 缺省 熔能 “格” 数
	 */
	int DefalutMeltingEnergyNum = 3;
	/**
	 * 熔能 “格” 数
	 */
	int MeltingEnergyNum = 3;
	/**
	 * 熔能 恢复时间
	 */
	int[] RecoveryTime = {3,3,3,3};
//	int[] RecoveryTime = {20,40,60,60};
//	int[] RecoveryTime = {2,2,2,2};
	Function<Integer, Integer> GetRecoveryTime = new Function<Integer, Integer>() {
		@Override
		public Integer apply(Integer recovery) {
			if(recovery == 0){
				return 0;
			}else if(recovery > 2){
				return RecoveryTime[3];
			}
			return RecoveryTime[recovery-1];
		}
	};
	
	String  Player_Melting_Energy_Num = "PlayerMeltingEnergyNum";
	String  Player_Melting_Award = "PlayerMeltingAward";
	
	Joiner JoinerByUnderScore = Joiner.on('_');
	Joiner JoinerByColon = Joiner.on(':');
	Joiner JoinerByTab = Joiner.on('\t');
	
	Splitter splitterByUnderScore = Splitter.on('_').trimResults().omitEmptyStrings();
	Splitter splitterByColon = Splitter.on(':').trimResults().omitEmptyStrings();
	Splitter splitterByTab = Splitter.on('\t').trimResults().omitEmptyStrings();
	
	Function<String, Integer> functionStrToInt = new Function<String, Integer>() {
		@Override
		public Integer apply(String input) {
			return Integer.parseInt(input);
		}
	};
	
	ImmutableSet<Integer> MeltingPropMTypes = ImmutableSet.of(31, 32);
	
	int ProcessingUnitMType = 31;
	int ModuleMType = 32;
	
	//							银子弹	复活币	勋章		强化部件	金子弹
//	int[] MeltingResultItem = {	4888,	4800,	4479,	125,	4889};
//	int[] MeltingResultProp = {	9,		5,		4,		1,		1};
	//							复活币	勋章		强化部件
	int[] MeltingResultItem = {	4800,	4479,	125};
	int[] MeltingResultProp = {	1,		1,		1};
	double[] MeltingResultLimit= {	Math.pow(20, 1.5),		Math.pow(40, 1.5),		Math.pow(60, 1.5)};
	
	String Melting_Empty = CommonUtil.messageFormatI18N("205");
}
