mail = {
	#$message.id#,"#$message.sender#","#$message.subject#","#$message.content#","#$message.createdDateStr#",
	#if ($message.haveAttached!="N") #
		attachment = {
			id=#$message.playerItem.id#,
			time_left=#$message.playerItem.timeLeft#,
			modified_desc="#$message.playerItem.modifiedDesc#",
			unit_type=#$message.playerItem.playerItemUnitType#,
			unit=#$message.playerItem.quantity#,
			name="#$si.name#",
			display="#$si.displayName#",
			characters={
				#foreach from=$si.characterList item=ids# 
					#$ids#, 
				#/foreach#
			},
			description ="#$si.description#",
			sendperson = #$si.isHot#,
			iid=#$si.iId#,
			#if $si.wId==4# 
				wid = #$si.wId#, 
			#/if#
			common={
				level = #$si.level#,
				type = #$si.type#,
				subtype = #$si.subType#,
				#if $si.type==1# 
					wid=#$si.wId#, 
				#/if#
				seq=#$si.seq#,
				is_vip=#$si.isVip#,
				is_new=#$si.isNew#,
				is_hot=#$si.isPopular#,
				is_web=#$si.isWeb#,
				star=#$si.figthNumOutput#,   		
				strength= 
					#if $si.isStrengthen == 0# 
						-1 ,
					#else#  
						#$si.strengthLevel#  ,
					#/if#
				cResistanceFire=#$si.cResistanceFire#,
				cResistanceBlast=#$si.cResistanceBlast#,
				cResistanceBullet=#$si.cResistanceBullet#,
				cResistanceKnife=#$si.cResistanceKnife#,
				cBloodAdd=#$si.cBloodAdd#,
				cResistanceFire_add=0,
				cResistanceBlast_add=0,
				cResistanceBullet_add=0,
				cResistanceKnife_add=0,
				cBloodAdd_add=0,
				rareLevel=#$si.rareLevel#,
			},
			performance = {
				damange = #$si.damange#,
				speed = #$si.shootSpeed#,
				damange_add = #$si.damangeAdd#,
				speed_add = #$si.shootSpeedAdd#,
				ammos = #$si.wAmmoOneClip#,
				ammo_count=#$si.wAmmoCount#,
			},
			color=#$si.gunProperty.color#,
			gunproperty={
				#foreach from=$si.gunProperty.propertyList item=property# 
				{
					#if $si.gunProperty.color!=1# 
						#$si.gunProperty.color#,
					#else# 
						1,
					#/if#
					"#$property.basePropertyStr#"
				}, 
				#/foreach#
			},
			package = {
				#foreach from=$si.packages item=item# 
					"#$item.displayName#", 
				#/foreach#
			},
			gpprices={
				#foreach from=$si.gpPricesList item=pay# 
				{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},
			crprices={
				#foreach from=$si.crPricesList item=pay# 
				{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},
			voucherprices={
				#foreach from=$si.voucherPricesList item=pay# 
				{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},		
			resource = {
				#if $si.type==1# 
					type=#$si.wId#, 
					#foreach from=$si.resource item=res#  
						#if($res!="")# 
							"#$res#", 
						#/if# 
					#/foreach#
				#elseif $si.type==2# 
					type=#$si.cId#,
					#foreach from=$si.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#elseif $si.type==3# 
					type=#$si.cId#,
					#foreach from=$si.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#else#
					#foreach from=$si.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#/if#
			},
		},
	#else# 
		attachment = {} ,
	#/if#
	num = #$num#,
	items = {
		#if ($message.haveAttached=="N") #
			#foreach from=$message.itemList item=sysItem#
			{
				name = "#$sysItem.name#",
				display_name = "#$sysItem.displayName#",
				type = #$sysItem.type#,
				color = #$sysItem.gunProperty.color#,
				unit = #$sysItem.unit#,
				unit_type = #$sysItem.unitType#,
			},
			#/foreach#
		#/if#
	},
}