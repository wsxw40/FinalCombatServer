gp = #$cal#
items= 
{
	#foreach from=$sysitemList item=theItem#
	{
		sid=#$theItem.id#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		modified_desc="#$theItem.modifiedDesc#",
		characters={#foreach from=$theItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.description#",
		#include file="Timelimit.st" unitType=$theItem.unitType unit=$theItem.unit#
		sendperson = #$theItem.isHot#,
		#if $theItem.wId==4# wid = #$theItem.wId#, #/if#
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			#if $theItem.type==1# wid=#$theItem.wId#, #/if#
			seq=#$theItem.seq#,
			quantity = #$theItem.unit#,
			is_vip=#$theItem.isVip#,
			is_new=#$theItem.isNew#,
			is_hot=#$theItem.isHot#,
			is_web=#$theItem.isWeb#,
			star=#$theItem.figthNumOutput#,   		
			strength=#if $theItem.isStrengthen == 0#-1#else#0#/if#,
			cResistanceFire=#$theItem.cResistanceFire#,
			cResistanceBlast=#$theItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.cResistanceKnife#,
			cBloodAdd=#$theItem.cBloodAdd#,
			cResistanceFire_add=#$theItem.cResistanceFire_add#,
			cResistanceBlast_add=#$theItem.cResistanceBlast_add#,
			cResistanceBullet_add=#$theItem.cResistanceBullet_add#,
			cResistanceKnife_add=#$theItem.cResistanceKnife_add#,
			cBloodAdd_add=#$theItem.cBloodAdd_add#,
			rareLevel=#$theItem.rareLevel#,
		},
	    performance = {
    		damange = #$theItem.damange#,					
	    	speed =#$theItem.shootSpeed#,	
	    	ammos = #$theItem.wAmmoOneClip#,
	    	ammo_count=#$theItem.wAmmoCount#,				
		},
	    color=#$theItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
	    	#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$theItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$theItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.type==2# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.type==3# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	    
	},
	#/foreach#
}