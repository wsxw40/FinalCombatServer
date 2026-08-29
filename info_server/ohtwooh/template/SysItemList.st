page = #$page#
pages = #$pages#
newGP = #$gp#
newCR = #$cr#
player_rank = #$rank#
items= 
{
	#foreach from=$list item=theItem#
	{
		sid=#$theItem.id#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		unit_type=#$theItem.unitType#,
		currency=#$theItem.currency#,
		modified_desc="#$theItem.modifiedDesc#",
		description ="#$theItem.description#",
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			p_suitable = {#foreach from=$theItem.pSuits item=pSuit)# "#$pSuit#", #/foreach#},
			c_side = #$theItem.cSide#,
			gender = "#$theItem.cGender#",
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
	    package = {#foreach from=$theItem.packages item=item# "#$item.displayName#", #/foreach#},
	    price = {#foreach from=$theItem.prices item=price# #if $price!=null# #$price#, #/if# #/foreach#},
	    
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