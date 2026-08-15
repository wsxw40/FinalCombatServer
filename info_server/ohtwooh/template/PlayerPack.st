#if($list == null)#
enable = false
items = {}
#else#
enable = true
items={#foreach from=$list item=theItem#
	{
	#if ($theItem.id != null)#
		pid=#$theItem.id#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			durable = #$theItem.durable#,
			c_side = #$theItem.cSide#,
			quantity =  #$theItem.quantity#,
			minutes_left = #$theItem.timeLeft#,
			gender = "#$theItem.cGender#",
		},
		resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #/if#
	    },
	 #/if#
	 },
#/foreach#}

#/if#