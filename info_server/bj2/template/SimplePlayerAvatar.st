avatar_info ={
	"#$player.name#",	
	#$player.lastGameSide#,	
	"#$player.gender#",	
	{
		#foreach from=$player.costumeT item=itemT#
			#if($itemT!="")#
				"#$itemT#", 
			#/if#
		#/foreach#
	},
	{
		#foreach from=$player.costumeP item=itemP#
			#if($itemP!="")#
				"#$itemP#", 
			#/if#
		#/foreach#
	},
	{
		type=#$player.pi.wId#, 
		#foreach from=$player.pi.resource item=itemS#
			#if($itemS!="")#
				"#$itemS#", 
			#/if#
		#/foreach#
	},	
}