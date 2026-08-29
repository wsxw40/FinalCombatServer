characters = 
{
#foreach from=$players item=player#
	{
		id = #$player.id#,
		name = "#$player.name#",
		level = #$player.rank#,
		gender = "#$player.gender#",
		last_side=#$player.lastGameSide#,
		last_time=#$player.lastLoginTime#,
		team0 = {#foreach from=$player.costumeT item=itemT##if($itemT!="")#"#$itemT#", #/if##/foreach#},
		team1 = {#foreach from=$player.costumeP item=itemP##if($itemP!="")#"#$itemP#", #/if##/foreach#},
	},
#/foreach#
}
