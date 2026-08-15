items= 
{	
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
	    resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #/if#
	    },
}