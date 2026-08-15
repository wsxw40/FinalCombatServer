list={
	#foreach from=$list item=r#
		{"#$r.bTeamName#","#$r.levelType#","#$r.levelName#","#$r.stringScore#","#$r.createTimeStr#","#$r.result#","#$r.bTeamId#","#$r.bTeamLeader#",},
	#/foreach#
}
