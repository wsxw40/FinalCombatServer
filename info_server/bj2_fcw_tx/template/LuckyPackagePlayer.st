list={
	#$randoms#
}
fix={
	#foreach from=$fix item=theItem#	
	{
		quantity = #$theItem.number#,
		sid=#$theItem.sysItem.id#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		characters={
			#foreach from=$theItem.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$theItem.sysItem.description#",
		#if $theItem.sysItem.wId==4# 
			wid = #$theItem.sysItem.wId#, 
		#/if#
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.sysItem.type==1# 
				wid=#$theItem.sysItem.wId#, 
			#/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=#$theItem.sysItem.isNew#,
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
		gunproperty={{
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
		}},
	},
	#/foreach#
}

random={
	#foreach from=$multiRandoms item=random#	
	{
		quantity=#$random.number#,
		sid=#$random.sysItem.id#,
		display="#$random.sysItem.displayName#",
		type = #$random.sysItem.type#,
		name="#$random.sysItem.name#",
		description ="#$random.sysItem.description#",
		#include file="Timelimit.st" unitType=$random.useType unit=$random.number#
		color=#$random.sysItem.gunProperty.color#,
		sendperson = #$random.sysItem.isHot#,
		#if $random.sysItem.wId==4# 
			wid = #$random.sysItem.wId#, 
		#/if#
		common={
			level = #$random.sysItem.level#,
			modified_desc="#$random.sysItem.modifiedDesc#",
			characters={
				#foreach from=$random.sysItem.characterList item=ids# 
					#$ids#, 
				#/foreach#
			},
			subtype = #$random.sysItem.subType#,
			#if $random.sysItem.type==1# 
				wid=#$random.sysItem.wId#, 
			#/if#
			seq=#$random.sysItem.seq#,
			is_vip=#$random.sysItem.isVip#,
			is_new=#$random.sysItem.isNew#,
			is_hot=#$random.sysItem.isPopular#,
			is_web=#$random.sysItem.isWeb#,
			star=#$random.sysItem.figthNumOutput#,   		
			strength= 
				#if $random.sysItem.isStrengthen == 0# 
					-1 ,
				#else#  
					#$random.sysItem.strengthLevel#  ,
				#/if#
			cResistanceFire=#$random.sysItem.cResistanceFire#,
			cResistanceBlast=#$random.sysItem.cResistanceBlast#,
			cResistanceBullet=#$random.sysItem.cResistanceBullet#,
			cResistanceKnife=#$random.sysItem.cResistanceKnife#,
			cBloodAdd=#$random.sysItem.cBloodAdd#,
			cResistanceFire_add=#$random.sysItem.cResistanceFire_add#,
			cResistanceBlast_add=#$random.sysItem.cResistanceBlast_add#,
			cResistanceBullet_add=#$random.sysItem.cResistanceBullet_add#,
			cResistanceKnife_add=#$random.sysItem.cResistanceKnife_add#,
			cBloodAdd_add=#$random.sysItem.cBloodAdd_add#,
			star=#$random.sysItem.figthNumOutput#,   		
			rareLevel=#$random.sysItem.rareLevel#,
		},
		performance = {
			damange = #$random.sysItem.showDamage#,					
			speed =#$random.sysItem.showShootSpeed#,	
			ammos = #$random.sysItem.wAmmoOneClip#,
			ammo_count=#$random.sysItem.wAmmoCount#,				
		},
		gunproperty={
	    		#foreach from=$random.sysItem.gunProperty.propertyList item=property# 
			{
	    			#if $random.sysItem.gunProperty.color!=1# 
					#$random.sysItem.gunProperty.color#,
				#else# 
					1,
				#/if#
	    			"#$property.basePropertyStr#"
			}, 
			#/foreach#
		},
		#if $random.sysItem.needTeamPlaceLevel > 99# 
			suitId=#$random.sysItem.belongSuit.suitId#, 
			suitDetail={
				des4={
					#foreach from=$random.sysItem.belongSuit.allSpec4Pros item=property#
						"#$property.desc#",
					#/foreach#
				},
				des6={
					#foreach from=$random.sysItem.belongSuit.allSpec6Pros item=property#
					"#$property.desc#",
					#/foreach#
				},
			},
		#/if#
		package = {
			#foreach from=$random.sysItem.packages item=item# 
				"#$item.displayName#", 
			#/foreach#
		},
		resource = {
			#if $random.sysItem.type==1# 
				type=#$random.sysItem.wId#, 
				#foreach from=$random.sysItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $random.sysItem.type==2# 
				type=#$random.sysItem.cId#,
				#foreach from=$random.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#elseif $random.sysItem.type==3# 
				type=#$random.sysItem.cId#,
				#foreach from=$random.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$random.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
	},
	#/foreach#
}
onlineAward={
	#if $onlineAward!=null#  
		sid=#$onlineAward.sysItem.id#,
		display="#$onlineAward.sysItem.displayName#",
		name="#$onlineAward.sysItem.name#",
		modified_desc="#$onlineAward.sysItem.modifiedDesc#",
		characters={
			#foreach from=$onlineAward.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$onlineAward.sysItem.description#",
		unit=#$onlineAward.unit#,
		sendperson = #$onlineAward.sysItem.isHot#,
		#if $onlineAward.sysItem.wId==4# 
			wid = #$onlineAward.sysItem.wId#, 
		#/if#
		common={
			level = #$onlineAward.sysItem.level#,
			type = #$onlineAward.sysItem.type#,
			subtype = #$onlineAward.sysItem.subType#,
			#if $onlineAward.sysItem.type==1# 
				wid=#$onlineAward.sysItem.wId#, 
			#/if#
			seq=#$onlineAward.sysItem.seq#,
			is_vip=#$onlineAward.sysItem.isVip#,
			is_new=#$onlineAward.sysItem.isNew#,
			is_hot=#$onlineAward.sysItem.isHot#,
			star=#$onlineAward.sysItem.figthNumOutput#, 
			strength= 
				#if $onlineAward.sysItem.isStrengthen == 0# 
					-1 ,	
				#else# 
					#$onlineAward.sysItem.strengthLevel# ,	
				#/if#		
			cResistanceFire=#$onlineAward.sysItem.cResistanceFire#,
			cResistanceBlast=#$onlineAward.sysItem.cResistanceBlast#,
			cResistanceBullet=#$onlineAward.sysItem.cResistanceBullet#,
			cResistanceKnife=#$onlineAward.sysItem.cResistanceKnife#,
			cBloodAdd=#$onlineAward.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$onlineAward.sysItem.rareLevel#,
		},
		performance = {
			damange = #$onlineAward.sysItem.damange#,					
			speed =#$onlineAward.sysItem.shootSpeed#,	
			damange_add = #$onlineAward.sysItem.damange - $onlineAward.sysItem.damange#,			
			speed_add = #$onlineAward.sysItem.shootSpeed - $onlineAward.sysItem.shootSpeed#,			
			ammos = #$onlineAward.sysItem.wAmmoOneClip#,
			ammo_count=#$onlineAward.sysItem.wAmmoCount#,				
		},
		color=#$onlineAward.sysItem.gunProperty.color#,
		gunproperty={
	    		#foreach from=$onlineAward.sysItem.gunProperty.propertyList item=property# 
			{
	    			#if $onlineAward.sysItem.gunProperty.color!=1# 
					#$onlineAward.sysItem.gunProperty.color#,
				#else# 
					1,
				#/if#
	    			"#$property.basePropertyStr#"
			}, 
			#/foreach#
		},
		package = {
			#foreach from=$onlineAward.sysItem.packages item=item# 
				"#$item.displayName#", 
			#/foreach#
		},
		gpprices={
			#foreach from=$onlineAward.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		crprices={
			#foreach from=$onlineAward.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		voucherprices={
			#foreach from=$onlineAward.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},	
		resource = {
			#if $onlineAward.sysItem.type==1# 
				type=#$onlineAward.sysItem.wId#, 
				#foreach from=$onlineAward.sysItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $onlineAward.sysItem.type==2# 
				type=#$onlineAward.sysItem.cId#,
				#foreach from=$onlineAward.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#elseif $onlineAward.sysItem.type==3# 
				type=#$onlineAward.sysItem.cId#,
				#foreach from=$onlineAward.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$onlineAward.sysItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
	#/if#
}
#if ($erroMsg!=null)# 
	error="#$erroMsg#" 
#else# 
	error=nil 
#/if#
indexs={
	#if ($indexs!=null)# 
		#$indexs#
 	#/if#
}
flags ={
	#if ($flags!=null)# 
		#$flags#
 	#/if#
}

