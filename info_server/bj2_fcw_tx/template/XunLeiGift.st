{
	cmd = "xunlei_gift",
	num = #$num#,
	items = {
		#if ($list != null)#
			#foreach from=$list item=sysItem#
				{0 , "#$sysItem.name#","#$sysItem.displayName#","#$sysItem.description#",#$sysItem.type#,#$sysItem.gunProperty.color#},
			#/foreach#
		#/if#
	},
}
