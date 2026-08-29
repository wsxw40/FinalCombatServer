pageCount = #$pageCount#
pageNo = #$pageNo#
random = {
	#foreach from=$random item=theItem#	
	{
		sid=#$theItem.sysItem.id#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		description ="#$theItem.sysItem.description#",
		#include file="Timelimit.st" unitType=$theItem.useType unit=$theItem.number#
		color=#$theItem.sysItem.gunProperty.color#,
		type = #$theItem.sysItem.type#,
		sendperson = #$theItem.sysItem.isHot#,
		#if $theItem.sysItem.wId==4# 
			wid = #$theItem.sysItem.wId#, 
		#/if#
		common={
			level = #$theItem.sysItem.level#,
			modified_desc="#$theItem.sysItem.modifiedDesc#",
			characters={
				#foreach from=$theItem.sysItem.characterList item=ids# 
					#$ids#, 
				#/foreach#
			},
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.sysItem.type==1# 
				wid=#$theItem.sysItem.wId#, 
			#/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=#$theItem.sysItem.isNew#,
			is_hot=#$theItem.sysItem.isPopular#,
			is_web=#$theItem.sysItem.isWeb#,
			star=#$theItem.sysItem.figthNumOutput#,   		
			strength= 
				#if $theItem.sysItem.isStrengthen == 0# 
					-1 ,
				#else#  
					#$theItem.sysItem.strengthLevel#  ,
				#/if#
			cResistanceFire=#$theItem.sysItem.cResistanceFire#,
			cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
			cBloodAdd=#$theItem.sysItem.cBloodAdd#,
			cResistanceFire_add=#$theItem.sysItem.cResistanceFire_add#,
			cResistanceBlast_add=#$theItem.sysItem.cResistanceBlast_add#,
			cResistanceBullet_add=#$theItem.sysItem.cResistanceBullet_add#,
			cResistanceKnife_add=#$theItem.sysItem.cResistanceKnife_add#,
			cBloodAdd_add=#$theItem.sysItem.cBloodAdd_add#,
			rareLevel=#$theItem.sysItem.rareLevel#,
		},
		performance = {
			damange = #$theItem.sysItem.showDamage#,					
			speed =#$theItem.sysItem.showShootSpeed#,	
			ammos = #$theItem.sysItem.wAmmoOneClip#,
			ammo_count=#$theItem.sysItem.wAmmoCount#,				
		},
		gunproperty={
	    		#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# 
			{
	    			#if $theItem.sysItem.gunProperty.color!=1# 
					#$theItem.sysItem.gunProperty.color#,
				#else# 
					1,
				#/if#
	    			"#$property.basePropertyStr#"
			}, 
			#/foreach#
		},
		#if $theItem.sysItem.needTeamPlaceLevel > 99# 
			suitId=#$theItem.sysItem.belongSuit.suitId#, 
			suitDetail={
				des4={
					#foreach from=$theItem.sysItem.belongSuit.allSpec4Pros item=property#
						"#$property.desc#",
					#/foreach#
				},
				des6={
					#foreach from=$theItem.sysItem.belongSuit.allSpec6Pros item=property#
						"#$property.desc#",
					#/foreach#
				},
			},
		#/if#
		package = {
			#foreach from=$theItem.sysItem.packages item=item# 
				"#$item.displayName#", 
			#/foreach#
		},
		resource = {
			#if $theItem.sysItem.type==1# 
				type=#$theItem.sysItem.wId#, 
				#foreach from=$theItem.sysItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $theItem.sysItem.type==2# 
				type=#$theItem.sysItem.cId#,
				#foreach from=$theItem.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#elseif $theItem.sysItem.type==3# 
				type=#$theItem.sysItem.cId#,
				#foreach from=$theItem.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$theItem.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
		quantity=#$theItem.number#,
	},
	#/foreach#
}