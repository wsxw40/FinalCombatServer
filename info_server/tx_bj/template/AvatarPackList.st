page = #$page#
pages = #$pages#
player_rank = #$rank#
items= 
{
#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		sid=#$theItem.itemId#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		description ="#$theItem.sysItem.description#",
		pack_id = #$theItem.pack#,
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			c_side = #$theItem.sysItem.cSide#,
			minutes_left = #$theItem.timeLeft#,
			gender = "#$theItem.sysItem.cGender#",
		},
	    
	   
		resource = {
	    #if $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	},
#/foreach#
}