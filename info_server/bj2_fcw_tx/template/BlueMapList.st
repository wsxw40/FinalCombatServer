{
	#if $theItem!=null#
		name="#$theItem.name#",
		item=
			#if $theItem.item==null || $theItem.item.mType!=32# 
				nil, 
			#else#
			{
				#$theItem.meltingItemStr#,
				map={#$theItem.item.id#,"#$theItem.item.name#","#$theItem.item.displayName#"},
			},
			#/if#
		children=
			#if $theItem.children==null#
				nil,
			#else#
				{
					#$theItem.childrenStr#
				},
			#/if# 
	#/if#
}