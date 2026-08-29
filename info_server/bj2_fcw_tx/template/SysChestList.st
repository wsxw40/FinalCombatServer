num=#$num# ,
chest = {
	#foreach from=$list item=chest#
	{
		#$chest.id#, #$chest.type#, #$chest.level#, #$chest.sysItemId#, #$chest.weight#, #$chest.number#, #$chest.useType#, #$chest.isNotice#, #$chest.price#, #$chest.chipPrice#,
		item = 		{
			#if ($chest.sysItem != null)#
				{0 , "#$chest.sysItem.name#","#$chest.sysItem.displayName#","#$chest.sysItem.description#",#$chest.sysItem.type#},
			#/if#
		},
  	},
  	#/foreach#
}
