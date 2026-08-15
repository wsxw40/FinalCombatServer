{   
	display="#$sysItem.displayName#",
	name="#$sysItem.name#",
	#include file="Timelimit.st" unitType=$unitType unit=$unit#
	isBind="N",
	isVip=#$isVip#,
	color=#$sysItem.gunProperty.color#,
	#if $sysItem.wId==4# 
		wid = #$sysItem.wId#, 
	#/if#
	#if ($sysItem.type == 1)#
		common={
			type = #$sysItem.type#,
			subtype = #$sysItem.subType#,
			#if $unitType == 1# 
				quantity = #$unit#,
			#else# 
				quantity = 1,
			#/if#
			seq=#$sysItem.seq#,
			star=#$sysItem.figthNumOutput#,    		
			strength= 
				#if $sysItem.isStrengthen == 0# 
					-1 ,
				#else#  
					#$sysItem.strengthLevel#  ,
				#/if#
			rareLevel=#$sysItem.rareLevel#,
		},
		performance = {
			damange = #$sysItem.damange#,					
			speed =#$sysItem.shootSpeed#,	
			ammos = #$sysItem.wAmmoOneClip#,
			ammo_count=#$sysItem.wAmmoCount#,				
		},
		gunproperty={
			#foreach from=$sysItem.gunProperty.propertyList item=property# 
			{
				#if $sysItem.gunProperty.color!=1# 
					#$sysItem.gunProperty.color#,
				#else# 
					1,
				#/if#
				"#$property.basePropertyStr#"
			}, 
			#/foreach#
    		},
	#elseif ($sysItem.type == 2 || $sysItem.type == 3)#
		common={
			type = #$sysItem.type#,
			subtype = #$sysItem.subType#,
			#if $unitType == 1# 
				quantity = #$unit#,
			#else# 
				quantity = 1,
			#/if#
			seq=#$sysItem.seq#,
			star=#$sysItem.figthNumOutput#,    		
			strength= 
				#if $sysItem.isStrengthen == 0# 
					-1 ,
				#else#  
					#$sysItem.strengthLevel#  ,
				#/if#
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
	#else#
		description ="#$sysItem.description#",
		common={
			type = #$sysItem.type#,
			subtype = #$sysItem.subType#,
			#if $unitType == 1# 
				quantity = #$unit#,
			#else# 
				quantity = 1,
			#/if#
			rareLevel=#$sysItem.rareLevel#,
		},
	#/if#
},