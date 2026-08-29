stName = #$stName#
type = #$type#
meltingLevel = #$meltingLevel#
#if ($type == 0)#
items= 
{#foreach from=$list item=theItem#
	{   
		playeritemid=#$theItem.id#,sid=#$theItem.itemId#,		
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		quantity=#$theItem.quantity#,		
		subtype = #$theItem.sysItem.subType#,
	},#/foreach#
}		
#/if#
#if ($type == 1)#
items= 
{#foreach from=$list item=theItem#
	{   
		playeritemid=#$theItem.id#,
		sid=#$theItem.itemId#,
		subtype = #$theItem.sysItem.subType#,
	},#/foreach#
}		
#/if#