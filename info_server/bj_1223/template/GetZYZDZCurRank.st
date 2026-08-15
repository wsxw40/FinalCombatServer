#if $curZYZDZRankList!=null# 
curZYZDZRankList = 
{
#foreach from=$curZYZDZRankList item=team#
	{
		#$team.id#,
		"#$team.name#",
		#$team.teamSpaceLevel#,
		#$team.predayResAmount#
	},
#/foreach#
}
#/if#
