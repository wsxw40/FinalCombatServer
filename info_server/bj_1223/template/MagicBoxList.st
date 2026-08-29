box_num=#$boxNum#
hummer_num=#$hummerNum#
need=#$needNum#
hummer = 
{		
		sid=#$hummer.id#,
		display="#$hummer.displayName#",
		name="#$hummer.name#",
		modified_desc="#$hummer.modifiedDesc#",
		characters={#foreach from=$hummer.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$hummer.description#",
		sendperson = #$hummer.isHot#,

		common={
			level = #$hummer.level#,
			type = #$hummer.type#,
			subtype = #$hummer.subType#,
			#if $hummer.type==1# wid=#$hummer.wId#, #/if#
			seq=#$hummer.seq#,
			is_vip=#$hummer.isVip#,
			is_new=#$hummer.isNew#,
			is_hot=#$hummer.isHot#,
			star=#$theItem.sysItem.figthNumOutput#,    		
			strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.sysItem.strengthLevel##/if#,	
			cResistanceFire=#$hummer.cResistanceFire#,
			cResistanceBlast=#$hummer.cResistanceBlast#,
			cResistanceBullet=#$hummer.cResistanceBullet#,
			cResistanceKnife=#$hummer.cResistanceKnife#,
			cBloodAdd=#$hummer.cBloodAdd#,
			rareLevel=#$hummer.rareLevel#,
		},
	    performance = {
    		damange = #$hummer.damange#,					
	    	speed =#$hummer.shootSpeed#,	
	    	ammos = #$hummer.wAmmoOneClip#,
	    	ammo_count=#$hummer.wAmmoCount#,				
		},
	    color=#$hummer.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$hummer.gunProperty.propertyList item=property# {
	    	#if $hummer.gunProperty.color!=1# #$hummer.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$hummer.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$hummer.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$hummer.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$hummer.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $hummer.type==1# type=#$hummer.wId#, #foreach from=$hummer.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $hummer.type==2# type=#$hummer.cId#,#foreach from=$hummer.resources item=resource# "#$resource#", #/foreach#
	    #elseif $hummer.type==3# type=#$hummer.cId#,#foreach from=$hummer.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$hummer.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
}

items= 
{
#foreach from=$playerItems item=theItem#
	{
		name="#$theItem.player.name#",
		rank=#$theItem.player.rank#,
		exp = #$theItem.player.exp#,
		item={
		item_num=#$theItem.quantity#,
		sid=#$theItem.sysItem.id#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		modified_desc="#$theItem.sysItem.modifiedDesc#",
		characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.sysItem.description#",
		#include file="Timelimit.st" unitType=$theItem.sysItem.defaultPayment.unitType unit=$theItem.quantity#
		sendperson = #$theItem.sysItem.isHot#,
		#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=#$theItem.sysItem.isNew#,
			is_hot=#$theItem.sysItem.isHot#,
			star=#$theItem.sysItem.figthNumOutput#,    		
			strength=#$theItem.sysItem.strengthLevel#,
			cResistanceFire=#$theItem.cResistanceFire#,
			cResistanceBlast=#$theItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.cResistanceKnife#,
			cBloodAdd=#$theItem.cBloodAdd#,
			rareLevel=#$theItem.sysItem.rareLevel#,
		},
	    performance = {
		    		damange = #$theItem.sysItem.damange#,					
			    	speed =#$theItem.sysItem.shootSpeed#,	
			    	damange_add = #$theItem.damange - $theItem.sysItem.damange#,			
		    		speed_add = #$theItem.shootSpeed - $theItem.sysItem.shootSpeed#,			
			    	ammos = #$theItem.sysItem.wAmmoOneClip#,
			    	ammo_count=#$theItem.sysItem.wAmmoCount#,				
		    },
	    color=#$theItem.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# {
	    	#if $theItem.sysItem.gunProperty.color!=1# #$theItem.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$theItem.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	  },  
	},
	#/foreach#
}
