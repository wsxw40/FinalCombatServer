page = #$page#
pages = #$pages#
player_rank = #$rank#
items= 
{
#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		sid=#$theItem.itemId#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		description ="#$theItem.description#",
		pack_id = #$theItem.pack#,
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			c_side = #$theItem.cSide#,
			minutes_left = #$theItem.timeLeft#,
			gender = "#$theItem.cGender#",
		},
	    
	   
		resource = {
	    #if $theItem.type==2# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	},
#/foreach#
}