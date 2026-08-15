num = #$num#
team = {
	#if $team != null# 
		#$team.id#,
		"#$team.name#", 
		"#$team.logo#", 
		"#$team.description#",  
		#$team.kill#, 
		#$team.headShot#, 
		#$team.gameWin#, #$team.gameTotal#,
		"#$team.gameRatio#",
		#$job#,
		"#$createTime#",
		#$currentMembers#,
		#$team.size#,
		"#$leaderName#",
		"#$team.province#",
		"#$team.city#",
		#$team.rank#,
		#$team.level#,
		#$team.exp#,
		#$team.totalExp#,
		#$team.fight#,
		#$team.recoreRankingCurr#,
		#$team.recoreOffset#,
		#$team.fightRankingCurr#,
		#$team.fightOffset#,
	#/if#
}
#if $application!= null#
	application={"#$teamName#",#$application.createTime#,#$application.id#}
#/if#