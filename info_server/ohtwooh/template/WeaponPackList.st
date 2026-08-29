page = #$page#
pages = #$pages#
player_rank = #$rank#
items= 
{
#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		sid=#$theItem.itemId#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		description ="#$theItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			durable = #$theItem.durable#,
			quantity =  #$theItem.quantity#,
			minutes_left = #$theItem.timeLeft#,
			length = #$theItem.wMaxLength# ,
			offset = #$theItem.wXOffset#,
		},
	    performance = {
	    		damange = #$theItem.damange#,			
		    	stop_power = #$theItem.stopPpower#,		
		    	recoil = #$theItem.recoil#,				
		    	accuracy = #$theItem.accuracy#,			
		    	speed =#$theItem.shootSpeed#,			
		    	range = 50,			
		    	ammos = #$theItem.wAmmoOneClip#,
		    	ammo_count=#$theItem.wAmmoCount#,				
		    	weight = 50,			
		    	explode_time = 50,		
		    	explode_range = 60,			
	    },
	    parts = {#foreach from=$theItem.parts item=part# {#$part.subType#,"#$part.displayName#", #$part.id#, }, #/foreach#},
		resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #/if#
	    },
	},
#/foreach#
}