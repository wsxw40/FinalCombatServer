myTeamIndex=#$myTeamIndex#
top = 
{#foreach from=$gameRecords item=gameRecord#
	{
	teamBname="#$gameRecord.teambName#",
	myTeamScoce=#$gameRecord.myTeamScoce#,
	teambScoce=#$gameRecord.teambScoce#,
	{#foreach from=$gameRecord.list item=entry#
		{#$entry#},
	#/foreach#},
	},
#/foreach#
}

