id = #$sysQuest.id#,
name = "#$sysQuest.name#",
title = "#$sysQuest.title#",
desc = "#$sysQuest.desc#",
icon = "#$sysQuest.icon#",
resource = "#$sysQuest.resource#",
uiType = #$sysQuest.uiType.number#,
uiAction = #$sysQuest.uiAction.number#,
uiGroup = #$sysQuest.uiGroup#,
uiIndex = #$sysQuest.uiIndex#,
number = #$sysQuest.number#,
completeCount = #$sysQuest.completeCount#,
repeatType = #$sysQuest.repeatType.number#,
repeatParam = #$sysQuest.repeatParam#,
startTime = "#$sysQuest.startTime#",
endTime = "#$sysQuest.endTime#",
color = #$sysQuest.color.number#,
sysCharacterId = #$sysQuest.sysCharacterId#,
parentId = #$sysQuest.parentId#,
difficulty = #$sysQuest.difficulty#,
item={
	#foreach from=$sysQuest.itemList item=sysItemPrice#	
		{
		#include file="SysItemPrice.st" sysItemPrice=$sysItemPrice#
		},
	#/foreach#
},
vipItem={
	#foreach from=$sysQuest.vipItemList item=sysItemPrice#	
		{
		#include file="SysItemPrice.st" sysItemPrice=$sysItemPrice#
		},
	#/foreach#
},