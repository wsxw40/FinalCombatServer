page = #$page#
pages = #$pages#
num = #$num#
awoke = #$awoke#
mission = {
	#foreach from=$missions item=mi#
	{
		#$mi.id#, "#$mi.icon#", "#$mi.title#","#$mi.desc#", #$mi.target#, #$mi.number#, #$mi.status#, #$mi.award#, #$mi.type#, #$mi.needaward#, #$mi.isnew#,
		#$mi.money#,#$mi.exp#,0,0,#$mi.isMain#,
		#include file="MissionItemAward.st" awardItemVos=$mi.awardItemVos#,
	},
	#/foreach#
}