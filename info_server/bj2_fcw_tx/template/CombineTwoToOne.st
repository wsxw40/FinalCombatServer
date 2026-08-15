mainItem= {
	#if $mainItem != null#
		playeritemid=#$mainItem.id#,
		sid=#$mainItem.itemId#,
		isBind = "#$mainItem.isBind#",
		#if $mainItem.sysItem.iId!=null# 
			iid=#$mainItem.sysItem.iId#, 
		#/if#
		display="#$mainItem.sysItem.displayName#",
		name="#$mainItem.sysItem.name#",
		unit_type=#$mainItem.playerItemUnitType#,
		modified_desc="#$mainItem.modifiedDesc#",
		characters={
			#foreach from=$mainItem.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$mainItem.sysItem.description#",
		pack_id = #$mainItem.pack#,
		repair_cost = #$mainItem.repairCost#,
		#if $mainItem.sysItem.wId==4# 
			wid = #$mainItem.sysItem.wId#, 
		#/if#
		#if $mainItem.sysItem.iId==1# 
			buff = #$mainItem.buff#, 
		#/if#
		#if $mainItem.sysItem.iId==5# 
			buff = #$mainItem.buff#, 
		#/if#	
		isDefault =  
			#if $mainItem.isDefault=="Y"# 
				0 ,
			#else# 
				1 ,
			#/if#
		mType = #$mainItem.sysItem.mType#,
		mValue = #$mainItem.sysItem.mValue#,
		common={
			level = #$mainItem.sysItem.level#,
			isOpenQuality=
				#if $mainItem.sysItem.wId==13# 
					0,
				#else# 
					1, 
				#/if#
			materialNeed = #$mainItem.materialNeed#,
			type = #$mainItem.sysItem.type#,
			subtype = #$mainItem.sysItem.subType#,
			#if $mainItem.type==1# 
				wid=#$mainItem.sysItem.wId#, 
			#/if#
			durable = 
				#if $mainItem.durableInt<=0# 
					0,
				#else# 
					#$mainItem.durableInt#, 
				#/if#
			quantity =  #$mainItem.quantity#,
			minutes_left = 
				#if $mainItem.timeLeft<=0# 
					0,
				#else# 
					#$mainItem.timeLeft#, 
				#/if#
			seq=#$mainItem.sysItem.seq#,
			is_vip=#$mainItem.sysItem.isVip#,
			is_new=1,
			star=#$mainItem.fightNum#,       		
			strength= 
				#if $mainItem.sysItem.isStrengthen == 0# 
					-1, 
				#else# 
					#$mainItem.level#, 
				#/if#
			holeNum=#$mainItem.holeNum#,
			cResistanceFire=#$mainItem.sysItem.cResistanceFire#,
			cResistanceBlast=#$mainItem.sysItem.cResistanceBlast#,
			cResistanceBullet=#$mainItem.sysItem.cResistanceBullet#,
			cResistanceKnife=#$mainItem.sysItem.cResistanceKnife#,
			cBloodAdd=#$mainItem.sysItem.cBloodAdd#,
			cResistanceFire_add=#$mainItem.cResistanceFire#,
			cResistanceBlast_add=#$mainItem.cResistanceBlast#,
			cResistanceBullet_add=#$mainItem.cResistanceBullet#,
			cResistanceKnife_add=#$mainItem.cResistanceKnife#,
			cBloodAdd_add=#$mainItem.cBloodAdd#,
			rareLevel=#$mainItem.sysItem.rareLevel#,
		},
		performance = {
			damange = #$mainItem.sysItem.damange#,					
			speed =#$mainItem.sysItem.shootSpeed#,	
			damange_add = #$mainItem.damange - $mainItem.sysItem.damange#,			
			speed_add = #$mainItem.shootSpeed - $mainItem.sysItem.shootSpeed#,			
			ammos = #$mainItem.sysItem.wAmmoOneClip#,
			ammo_count=#$mainItem.sysItem.wAmmoCount#,				
		},
		color=#$mainItem.gunProperty.color#,
		gunproperty={
	    		#foreach from=$mainItem.gunProperty.propertyList item=property# 
			{
		    		#if $mainItem.gunProperty.color!=1# 
					#$mainItem.gunProperty.color#,
				#else# 
					1,
				#/if#
		    		"#$property.basePropertyStr#"
			}, 
	    		#/foreach#
		},
		combineDetail = {
	    		#foreach from=$mainItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    		#/foreach#
		},
		parts = {
			#foreach from=$mainItem.parts item=part# 
			{#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, 
			#/foreach#
		},
		gpprices={
			#foreach from=$mainItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		crprices={
			#foreach from=$mainItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		voucherprices={
			#foreach from=$mainItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},	
		package = {
			#foreach from=$mainItem.packages item=item# 
				"#$item.sysItem.displayName#", 
			#/foreach#
		},
		resource = {
			#if $mainItem.sysItem.type==1# 
				type=#$mainItem.sysItem.wId#, 
				#foreach from=$mainItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $mainItem.sysItem.type==2# 
				type=#$mainItem.sysItem.cId#,
				#foreach from=$mainItem.resources item=resource#
					"#$resource#", 
				#/foreach#
			#elseif $mainItem.sysItem.type==3# 
				type=#$mainItem.sysItem.cId#,
				#foreach from=$mainItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$mainItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
		gstLevel =
			#if $mainItem.gst_level==null# 
				0, 
			#else# 
				#$mainItem.gst_level#,  
			#/if#
		gstExp=
			#if $mainItem.gst_level_exp==null# 
				0, 
			#else# 
				#$mainItem.gst_level_exp#, 
			#/if#
	#/if#
}

