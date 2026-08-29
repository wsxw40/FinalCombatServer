weapon_info =
	#if $playerItem!=null# 
	{
		type=#$playerItem.sysItem.wId#, 
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
		{}
	#/if#