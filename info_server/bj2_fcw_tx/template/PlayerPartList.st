page = #$page#
pages = #$pages#

items= {
	#if $defaultItem!=null #
	{
		pid=0,
		display="",
		name="defaultpart",
		parent_id=0,
		description ="",
		resource = {},
		common={},
		performance = {},
	},
	#/if#
	#foreach from=$list item=theItem#
		{   
			pid=#$theItem.id#,
			display="#$theItem.sysItem.displayName#",
			name="#$theItem.sysItem.name#",
			parent_id=#$theItem.parentItemId#,
			description ="#$theItem.sysItem.description#",
			resource = {
				#foreach from=$theItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			},
			common={
				level = #$theItem.sysItem.level#,
				type = #$theItem.sysItem.type#,
				subtype = #$theItem.sysItem.subType#,
				durable = #$theItem.durable#,
				quantity =  #$theItem.quantity#,
				p_suitable = {
					#foreach from=$theItem.pSuits item=pSuit# 
						"#$pSuit#", 
					#/foreach#
				},
				c_side = #$theItem.sysItem.cSide#,
				minutes_left = #$theItem.timeLeft#,
				gender = "#$theItem.sysItem.cGender#",
				length = #$theItem.sysItem.wMaxLength# ,
				offset = #$theItem.sysItem.wXOffset#,
			},
			performance = {
				damange = #$theItem.sysItem.damange#,					
				speed =#$theItem.sysItem.shootSpeed#,	
				damange_add = #$theItem.damange - $theItem.sysItem.damange#,			
				speed_add = #$theItem.shootSpeed - $theItem.sysItem.shootSpeed#,			
				stop_power = #$theItem.stopPpower#,		
				recoil = #$theItem.recoil#,				
				accuracy = #$theItem.accuracy#,			
				range = #$theItem.range#,		
				ammos = #$theItem.wAmmoOneClip#,
				ammo_count=#$theItem.wAmmoCount#,			
				weight = #$theItem.weight#,			
				explode_time = #$theItem.wExplodeTime#,	
				explode_range = #$theItem.wHurtRange#,				
			},
		},
	#/foreach#
}