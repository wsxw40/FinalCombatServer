tid = #$player.teamId#
avatar_info = 
{
	gender = "#$player.gender#",
	last_side=#$player.lastGameSide#,
	last_time=#$player.lastLoginTime#,
	team0 = {#foreach from=$cosT item=itemT##if($itemT!="")#"#$itemT#", #else# "",#/if##/foreach#},
		default_team0 = {#foreach from=$cos0 item=item0##if($item0!="")#"#$item0#",#else# "", #/if##/foreach#},
	team1 = {#foreach from=$cosP item=itemP##if($itemP!="")#"#$itemP#", #else# "",#/if##/foreach#},
		default_team1 = {#foreach from=$cos1 item=item1##if($item1!="")#"#$item1#", #else# "", #/if##/foreach#},
}
weapon_info =
#if $playerItem!=null# 
	{itemid=#$playerItem.id#, id=#$playerItem.itemId#, type=#$playerItem.sysItem.wId#, 
		#foreach from=$wpStable item=itemS#
			#if($itemS!="")#
				"#$playerItem.sysItem.name#/#$itemS#", 
			#/if#
		#/foreach# 
		#foreach from=$wpChange item=itemC#
			#if($itemC!="")#
				"#$playerItem.sysItem.name#/#$itemC#",
			 #/if#
		#/foreach#
	}
#else#
	nil
#/if#