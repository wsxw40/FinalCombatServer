#if $treeType==3 #
 tank={
	#foreach from=$map key=key item=sysItem #
			{
				display ="#$sysItem.displayName#",
				name ="#$sysItem.name#",
				description = "#$sysItem.description#",
				level = #$sysItem.level#,
				isLight=0,
				place = #$key#,
				#foreach from=$list item=item#
					#if $item.level>=$sysItem.level#
						isLight=1,
					#/if#
				#/foreach#
			},
		#/foreach#
}
#/if#
#if $treeType!=3 #
	#if $treeType==2 #
	attack=
	#/if#
	#if $treeType==1 #
	wall=
	#/if#
	#if $treeType==5 #
	machine=
	#/if#

	{
		#foreach from=$map key=key item=sysItem #
			{
				display ="#$sysItem.displayName#",
				name ="#$sysItem.name#",
				description = "#$sysItem.description#",
				level = #$sysItem.level#,
				isLight=0,
				place = #$key#,
				#foreach from=$list item=teamItem#
					#if $teamItem.itemId==$sysItem.id#
						isLight=1,
					#/if#
				#/foreach#
			},
		#/foreach#
	}
	#/if#