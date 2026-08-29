id = #$sysAchievement.id#,
name = "#$sysAchievement.name#",
description = "#$sysAchievement.description#",
title = "#$sysAchievement.title#",
type = #$sysAchievement.type.number#,
number = #$sysAchievement.number#,
color = #$sysAchievement.color.number#,
sysCharacterId = #$sysAchievement.sysCharacterId#,
action = #$sysAchievement.action.number#,
statType = #$sysAchievement.statType.number#,
parent = #$sysAchievement.parent#,
group = #$sysAchievement.group#,
backUp = "#$sysAchievement.backUp#",
gift= {
	#foreach from = $sysAchievement.giftList item=payment#
		{
		#include file="Payment.st" payment=$payment#
		},
	#/foreach#
},