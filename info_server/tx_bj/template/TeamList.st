page = #$page#
pages = #$pages#
team = 
{
	#foreach from=$teamList item=team #
	{
		#$team.id#,"#$team.name#","#$team.logo#",#$team.kill#,#$team.headShot#,
		#$team.gameWin#,#$team.gameTotal#,#$team.memberCount#,#$team.size#,
		"#$team.challengeRatio#","#$team.leaderName#","#$team.city#",#$team.rank#,
		#$team.level#,#* 战队等级 *#
		#$team.fight#,#* 战斗力 *#
		"#$team.createTimeStr#",#* 创建时间 *#
	},
	#/foreach#
}