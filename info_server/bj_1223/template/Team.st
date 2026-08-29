team = {
#if $team != null#
		#$team.id#,"#$team.name#", "#$team.logo#", "#$team.description#",  #$team.kill#, #$team.headShot#, 
		#$team.gameWin#, #$team.gameTotal#,
		"#$team.gameRatio#",#$job#,"#$createTime#",#$currentMembers#,#$team.size#,"#$team.leaderName#","#$team.province#","#$team.city#",#$team.rank#,
		#$team.level#,				#*战队等级*#
		#$team.exp#,				#*战队经验*#
		#$team.totalExp#,			#*升级需要经验*#
		#$team.fight#,				#*战斗力*#
		#$team.recoreRankingCurr#, 	#*战绩排行榜*#
		#$team.recoreOffset#, 		#*>0 升 0不变 <0降*#
		#$team.fightRankingCurr#,	#*战斗力排行榜*#
		#$team.fightOffset#,		#*>0 升 0不变 <0降*#
#/if#
}