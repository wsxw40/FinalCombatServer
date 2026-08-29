#if ($erroCode!=null)# error="#$erroCode#" #else# error=nil #/if#
misticAward={
	#if $misticAward!=null#  
		sid=#$misticAward.sysItem.id#,
		display="#$misticAward.sysItem.displayName#",
		name="#$misticAward.sysItem.name#",
		modified_desc="#$misticAward.sysItem.modifiedDesc#",
		characters={#foreach from=$misticAward.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$misticAward.sysItem.description#",
		unit=#$misticAward.unit#,
		sendperson = #$misticAward.sysItem.isHot#,
		#if $misticAward.sysItem.wId==4# wid = #$misticAward.sysItem.wId#, #/if#
		common={
			level = #$misticAward.sysItem.level#,
			type = #$misticAward.sysItem.type#,
			subtype = #$misticAward.sysItem.subType#,
			#if $misticAward.sysItem.type==1# wid=#$misticAward.sysItem.wId#, #/if#
			seq=#$misticAward.sysItem.seq#,
			is_vip=#$misticAward.sysItem.isVip#,
			is_new=#$misticAward.sysItem.isNew#,
			is_hot=#$misticAward.sysItem.isHot#,
			star=#$misticAward.sysItem.figthNumOutput#, 
			strength=#if $misticAward.sysItem.isStrengthen == 0#-1#else##$misticAward.sysItem.strengthLevel##/if#,			
			cResistanceFire=#$misticAward.sysItem.cResistanceFire#,
			cResistanceBlast=#$misticAward.sysItem.cResistanceBlast#,
			cResistanceBullet=#$misticAward.sysItem.cResistanceBullet#,
			cResistanceKnife=#$misticAward.sysItem.cResistanceKnife#,
			cBloodAdd=#$misticAward.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$misticAward.sysItem.rareLevel#,
		},
	    performance = {
		    		damange = #$misticAward.sysItem.damange#,					
			    	speed =#$misticAward.sysItem.shootSpeed#,	
			    	damange_add = #$misticAward.sysItem.damange - $misticAward.sysItem.damange#,			
		    		speed_add = #$misticAward.sysItem.shootSpeed - $misticAward.sysItem.shootSpeed#,			
			    	ammos = #$misticAward.sysItem.wAmmoOneClip#,
			    	ammo_count=#$misticAward.sysItem.wAmmoCount#,				
		    },
	    color=#$misticAward.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$misticAward.sysItem.gunProperty.propertyList item=property# {
	    	#if $misticAward.sysItem.gunProperty.color!=1# #$misticAward.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$misticAward.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$misticAward.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$misticAward.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$misticAward.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $misticAward.sysItem.type==1# type=#$misticAward.sysItem.wId#, #foreach from=$misticAward.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $misticAward.sysItem.type==2# type=#$misticAward.sysItem.cId#,#foreach from=$misticAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $misticAward.sysItem.type==3# type=#$misticAward.sysItem.cId#,#foreach from=$misticAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$misticAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	    #/if#

}
