sysQuests = {
		#foreach from=$proto.sysQuestList item=sysQuest#	
			{
			#include file="SysQuest.st" sysQuest=$sysQuest#
			},
		#/foreach#
}
playerQuests = {
		#foreach from=$proto.playerQuestList item=playerQuest#	
			{
			#include file="PlayerQuest.st" playerQuest=$playerQuest#
			},
		#/foreach#
}
awardSysQuestId={
		#foreach from=$proto.awardSysQuestIdList item=id#	
			#$id#,
		#/foreach#
}