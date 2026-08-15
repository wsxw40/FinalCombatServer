page = #$page#
pages = #$pages#
player_rank = #$rank#
items= {
	#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		sid=#$theItem.itemId#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		description ="#$theItem.sysItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		isDefault =  
			#if $theItem.isDefault=="Y"# 
				0 ,
			#else# 
				1 ,
			#/if#
		#if $theItem.sysItem.wId==4# 
			wid = #$theItem.sysItem.wId#, 
		#/if#
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			durable = #$theItem.durable#,
			quantity =  #$theItem.quantity#,
			minutes_left = #$theItem.timeLeft#,
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
		parts = {
			#foreach from=$theItem.parts item=part# 
			{#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#, }, 
			#/foreach#
		},
		direction = {
			#foreach from=$theItem.direction item=dir# 
				#$dir#, 
			#/foreach#
		},
		resource = {
			#if $theItem.sysItem.type==1# 
				type=#$theItem.sysItem.wId#,
				posx = #$theItem.sysItem.wPoseX#, 
				posy = #$theItem.sysItem.wPoseY#, 
				posz = #$theItem.sysItem.wPoseZ#, 
				camera = "#$theItem.sysItem.wCamera#", 
				direction = 0 , 
				#foreach from=$theItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#/if#
		},
	},
	#/foreach#
}