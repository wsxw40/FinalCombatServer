page = #$page#
pages = #$pages#
num=#$num#
awoke = #$awoke#
mission = {
	#foreach from=$list item=pa#
		{
			#$pa.id#,
			"#$pa.sysActivity.path#",
			"#$pa.sysActivity.title#",
			"#$pa.name#",
			#$pa.target#,
			#$pa.number#,
			#$pa.status#,
			#$pa.award#,
			#$pa.sysActivity.action#,
			#$pa.sysActivity.needAward#,
			0,
			0,0,0,0,
			items = 
			{
				#if $pa.sysActivity.sysItem != null#
					#include file="MissionItemAwardEntity.st" sysItem=$pa.sysActivity.sysItem unit=$pa.sysActivity.unit unitType=$pa.sysActivity.unitType isVip=0#
				#/if#
			},
		},
	#/foreach#
}