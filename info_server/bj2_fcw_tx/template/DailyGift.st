#if ($msg!=null)# 
	msg="#$msg#" 
#else# 
	msg=nil 
#/if#
check_gift={	
	#if ($daitemsy!= null)#
		#foreach from=$daitemsy item=theItem#
		{
			item_num=#$theItem.unit#,
			sid=#$theItem.sysItem.id#,
			name="#$theItem.sysItem.name#",
			display="#$theItem.sysItem.displayName#",
			color=#$theItem.sysItem.gunProperty.color#
		},
		#/foreach#
	#/if#
}
is_gift=#$isGift#
quietBounds={
	disResBounds=#$quietBounds.disResBounds#,
}