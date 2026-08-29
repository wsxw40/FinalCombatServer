id = #$playerAchievement.id#,
sysCharacterId = #$playerAchievement.sysCharacterId#,
status = #$playerAchievement.status.number#,
sysAchievementId= {
	#foreach from = $playerAchievement.sysAchievementIdList item=sysAchievementId#
		#$sysAchievementId#,
	#/foreach#
},
group = #$playerAchievement.group#,
level = #$playerAchievement.level#,
number = #$playerAchievement.number#,
backUp = "#$playerAchievement.backUp#",