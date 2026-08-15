item = {   
	sid=#$sysItem.id#,
		display="#$sysItem.displayName#",
		name="#$sysItem.name#",
		description ="#$sysItem.description#",
		#include file="Timelimit.st" unitType=$unitType unit=$unit#
		color=#$sysItem.gunProperty.color#,
		type = #$sysItem.type#,
		sendperson = #$sysItem.isHot#,
		#if $sysItem.wId==4# wid = #$sysItem.wId#, #/if#
		common={
			level = #$sysItem.level#,
			modified_desc="#$sysItem.modifiedDesc#",
			characters={#foreach from=$sysItem.characterList item=ids)# #$ids#, #/foreach#},
			subtype = #$sysItem.subType#,
			#if $sysItem.type==1# wid=#$sysItem.wId#, #/if#
			seq=#$sysItem.seq#,
			is_vip=#$sysItem.isVip#,
			is_new=#$sysItem.isNew#,
			is_hot=#$sysItem.isPopular#,
			is_web=#$sysItem.isWeb#,
			star=#$sysItem.figthNumOutput#,   		
			strength=#if $sysItem.isStrengthen == 0#-1#else# #$sysItem.strengthLevel# #/if#,
			cResistanceFire=#$sysItem.cResistanceFire#,
			cResistanceBlast=#$sysItem.cResistanceBlast#,
			cResistanceBullet=#$sysItem.cResistanceBullet#,
			cResistanceKnife=#$sysItem.cResistanceKnife#,
			cBloodAdd=#$sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$sysItem.rareLevel#,
		},
	    performance = {
    		damange = #$sysItem.damange#,					
	    	speed =#$sysItem.shootSpeed#,	
	    	ammos = #$sysItem.wAmmoOneClip#,
	    	ammo_count=#$sysItem.wAmmoCount#,				
		},
	    gunproperty={
	    	#foreach from=$sysItem.gunProperty.propertyList item=property# {
	    	#if $sysItem.gunProperty.color!=1# #$sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    resource = {
	    #if $sysItem.type==1# type=#$sysItem.wId#, #foreach from=$sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $sysItem.type==2# type=#$sysItem.cId#,#foreach from=$sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $sysItem.type==3# type=#$sysItem.cId#,#foreach from=$sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
		quantity=#$unit#,
}