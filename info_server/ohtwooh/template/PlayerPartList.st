page = #$page#
pages = #$pages#

items= 
{
#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		parent_id=#$theItem.parentItemId#,
		description ="#$theItem.description#",
		resource = {#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#},
	},
#/foreach#
}