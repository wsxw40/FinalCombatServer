page = 1
pages = 1
num=#$num#  #if($flag ==1)#,#/if#
awoke = #$awoke#
mission = 
{
	#foreach from=$list item=amission#
	{
		#$amission.id#, 
		"#$amission.sysMission.name#", 
		"#$amission.sysMission.missionTitle#", 
		"#$amission.description#", 
		#$amission.target#,
		#$amission.number#,
		#$amission.status#,
		#$amission.award#,
		#$amission.sysMission.type#,
		1,
		0,
		#$amission.cmIncome.cal#,#$amission.cmIncome.exp#,#$amission.cmIncome.vipCal#,#$amission.cmIncome.vipExp#,
		vipExp=#$amission.vipUpGradeExp#,
		#include file="MissionItemAward.st" awardItemVos=$amission.awardItemVos#,
  	},
  	#/foreach#
}
