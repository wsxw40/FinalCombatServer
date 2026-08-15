/**
 * 
 */
package com.pearl.manager.utils;

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
//	int[] RecoveryTime = {20,40,60,60};
	int[] RecoveryTime = {2,2,2,2};
	Function<Integer, Integer> GetRecoveryTime = new Function<Integer, Integer>() {
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
		public Integer apply(String input) {
			return Integer.parseInt(input);
		}
	};
	
	ImmutableSet<Integer> MeltingPropMTypes = ImmutableSet.of(31, 32);
}
