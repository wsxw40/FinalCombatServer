list={
	#foreach from=$list item=record#
	{
	 #$record.battleFieldRobDaily.type#,
	 #$record.isAttacker#,
	 "#$record.attackTeam.name#",
	 "#$record.defenceTeam.name#",
	 #$record.battleFieldRobDaily.robAmount#,
	 #$record.battleFieldRobDaily.beRobAmount#,
	 "#$record.battleTime#",
	 },	
	#/foreach#
}
