prices = {
	#foreach from=$prices item=price#
		#$price#,
	#/foreach#
}
items= {
	#foreach from=$items item=sysItem#
	{
		#if $sysItem != null #
			sid=#$sysItem.id#,
			display="#$sysItem.displayName#",
			name="#$sysItem.name#",
			modified_desc="#$sysItem.modifiedDesc#",
			characters={
				#foreach from=$sysItem.characterList item=ids# 
					#$ids#, 
				#/foreach#
			},
			description ="#$sysItem.description#",
			sendperson = #$sysItem.isHot#,
			quantity = #$sysItem.quantity#,
			common={
				level = #$sysItem.level#,
				type = #$sysItem.type#,
				subtype = #$sysItem.subType#,
				#if $sysItem.type==1# 
					wid=#$sysItem.wId#, 
				#/if#
				seq=#$sysItem.seq#,
				is_vip=#$sysItem.isVip#,
				is_new=#$sysItem.isNew#,
				is_hot=#$sysItem.isHot#,
				star=#$sysItem.fightNum#,   		
				strength=0,
				cResistanceFire=#$sysItem.cResistanceFire#,
				cResistanceBlast=#$sysItem.cResistanceBlast#,
				cResistanceBullet=#$sysItem.cResistanceBullet#,
				cResistanceKnife=#$sysItem.cResistanceKnife#,
				cBloodAdd=#$sysItem.cBloodAdd#,
				rareLevel=#$sysItem.rareLevel#,
			},
			performance = {	
			},
			color=#$sysItem.gunProperty.color#,
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
			package = {
				#foreach from=$sysItem.packages item=item# 
					"#$item.displayName#", 
				#/foreach#
			},
			gpprices={
				#foreach from=$sysItem.gpPricesList item=pay# 
	    			{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},
			crprices={
				#foreach from=$sysItem.crPricesList item=pay# 
				{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},
			voucherprices={
				#foreach from=$sysItem.voucherPricesList item=pay# 
				{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
				#/foreach#
			},
			resource = {
				#if $sysItem.type==1# 
					type=#$sysItem.wId#, 
					#foreach from=$sysItem.resource item=res#  
						#if($res!="")# 
							"#$res#", 
						#/if# 
					#/foreach#
				#elseif $sysItem.type==2# 
					type=#$sysItem.cId#,
					#foreach from=$sysItem.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#elseif $sysItem.type==3# 
					type=#$sysItem.cId#,
					#foreach from=$sysItem.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#else#
					#foreach from=$sysItem.resources item=resource# 
						"#$resource#", 
					#/foreach#
				#/if#
			},
		#/if#
	},
	#/foreach#
}