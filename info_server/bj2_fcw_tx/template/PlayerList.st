characters = {
	#foreach from=$players item=player#
	{
		id = #$player.id#,
		name = "#$player.name#",
		level = #$player.rank#,
		exp=#$player.exp#,
		gender = "#$player.gender#",
		last_side=#$player.lastGameSide#,
		last_time=#$player.lastLoginTime#,
		guide="#$player.tutorial#",
		team0 = {
			#foreach from=$player.costumeT item=itemT#
				#if($itemT!="")#
					"#$itemT#", 
				#/if#
			#/foreach#
		},
		team1 = {
			#foreach from=$player.costumeP item=itemP#
				#if($itemP!="")#
					"#$itemP#", 
				#/if#
			#/foreach#
		},
		weapon_info =
			#if $player.pi!=null# 
			{
				itemid=#$player.pi.id#, 
				id=#$player.pi.itemId#, 
				type=#$player.pi.sysItem.wId#, 
				#foreach from=$player.pi.resource item=itemS#
					#if($itemS!="")#
						"#$itemS#", 
					#/if#
				#/foreach#
			}
			#else#
				nil
			#/if#
	},
	#/foreach#
}
cost = #$cost#
