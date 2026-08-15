page = #$page#
pages = #$pages#
player_rank = #$rank#
items= 
{
#foreach from=$list item=theItem#
	{   
		pid=#$theItem.id#,
		sid=#$theItem.itemId#,
	#if $theItem.iId!=null# iid=#$theItem.iId#, #/if#
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		description ="#$theItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		buff = #$theItem.buff#,
		isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			durable = #$theItem.durable#,
			quantity =  #$theItem.quantity#,
			p_suitable = {#foreach from=$theItem.pSuits item=pSuit)# "#$pSuit#", #/foreach#},
			c_side = #$theItem.cSide#,
			minutes_left = #$theItem.timeLeft#,
			gender = "#$theItem.cGender#",
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
	    parts = {#foreach from=$theItem.parts item=part# {#$part.subType#,"#$part.displayName#", #$part.id#,}, #/foreach#},
	    package = {#foreach from=$theItem.packages item=item# "#$item.displayName#", #/foreach#},
		resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.type==2# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	},
#/foreach#
}