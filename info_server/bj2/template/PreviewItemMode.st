items= {	
	pid=#$theItem.id#,
	display="#$theItem.displayName#",
	name="#$theItem.name#",
	unit_type=#$theItem.playerItemUnitType#,
	modified_desc="#$theItem.modifiedDesc#",
	description ="#$theItem.description#",
	pack_id = #$theItem.pack#,
	repair_cost = #$theItem.repairCost#,
	buff = #$theItem.buff#,
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
		level = #$theItem.level#,
		type = #$theItem.type#,
		subtype = #$theItem.subType#,
		durable = #$theItem.durable#,
		quantity =  #$theItem.quantity#,
		p_suitable = {
			#foreach from=$theItem.pSuits item=pSuit# 
				"#$pSuit#", 
			#/foreach#
		},
		c_side = #$theItem.cSide#,
		minutes_left = #$theItem.timeLeft#,
		gender = "#$theItem.cGender#",
		length = #$theItem.wMaxLength# ,
		offset = #$theItem.wXOffset#,
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
		{#$part.subType#,"#$part.displayName#", #$part.id#,}, 
		#/foreach#
	},
	resource = {
		#if $theItem.type==1# 
			type=#$theItem.wId#,
			posx = #$theItem.wPoseX#, 
			posy = #$theItem.wPoseY#, 
			posz = #$theItem.wPoseZ#, 
			camera = "#$theItem.wCamera#", 
			direction = 0 , 
			#foreach from=$theItem.resource item=res#  
				#if($res!="")# 
					"#$res#", 
				#/if# 
			#/foreach#
		#/if#
	},
	resource_new ={
		#foreach from=$newList item=part# 
			#foreach from=$part.resources item=res#  
				#if($res!="")# 
					"#$theItem.name#/#$res#", 
				#/if#
			#/foreach#
		#/foreach#
		#foreach from=$deleteList item=res#  
			#if($res!="")# 
				"#$res#", 
			#/if#
		#/foreach#
	},
}