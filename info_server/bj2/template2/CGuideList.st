playerAchievement={
	#foreach from = $proto.playerAchievementList item=playerAchievement#
		{#include file="PlayerAchievement.st" playerAchievement=$playerAchievement#},
	#/foreach#
}
nextSysAchievement={
	#foreach from = $proto.nextSysAchievementList item=sysAchievement#
		{
		#include file="SysAchievement.st" sysAchievement=$sysAchievement#
		},
	#/foreach#
}