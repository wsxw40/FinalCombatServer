result = #$result#
item= {
	#if $theItem != null#
		playeritemid=#$theItem.id#,
		sid=#$theItem.itemId#,
		isBind = "#$theItem.isBind#",
		#if $theItem.sysItem.iId!=null# 
			iid=#$theItem.sysItem.iId#, 
		#/if#
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		characters={
			#foreach from=$theItem.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$theItem.sysItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		#if $theItem.sysItem.wId==4# 
			wid = #$theItem.sysItem.wId#, 
		#/if#
		#if $theItem.sysItem.iId==1# 
			buff = #$theItem.buff#, 
		#/if#
		#if $theItem.sysItem.iId==5# 
			buff = #$theItem.buff#, 
		#/if#	
		isDefault =  
			#if $theItem.isDefault=="Y"# 
				0 ,
			#else# 
				1 ,
			#/if#
		mType = #$theItem.sysItem.mType#,
		mValue = #$theItem.sysItem.mValue#,
		common={
			level = #$theItem.sysItem.level#,
			isOpenQuality=
				#if $theItem.sysItem.wId==13# 
					0,
				#else# 
					1, 
				#/if#
			materialNeed = #$theItem.materialNeed#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.type==1# 
				wid=#$theItem.sysItem.wId#, 
			#/if#
			durable = 
				#if $theItem.durableInt<=0# 
					0,
				#else# 
					#$theItem.durableInt#, 
				#/if#
			quantity =  #$theItem.quantity#,
			minutes_left = 
				#if $theItem.timeLeft<=0# 
					0,
				#else# 
					#$theItem.timeLeft#, 
				#/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=1,
			star=#$theItem.fightNum#,       		
			strength= 
				#if $theItem.sysItem.isStrengthen == 0# 
					-1 ,
				#else# 
					#$theItem.level# ,
				#/if#
			holeNum=#$theItem.holeNum#,
			cResistanceFire=#$theItem.cResistanceFire#,
			cResistanceBlast=#$theItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.cResistanceKnife#,
			cBloodAdd=#$theItem.sysItem.cBloodAdd#,
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
		color=#$theItem.gunProperty.color#,
		gunproperty={
			#foreach from=$theItem.gunProperty.propertyList item=property# 
			{
				#if $theItem.gunProperty.color!=1#
					#$theItem.gunProperty.color#,
				#else# 
					1,
				#/if#
				"#$property.basePropertyStr#"
			}, 
			#/foreach#
		},
		combineDetail = {
			#foreach from=$theItem.gunProperty.strPropertyList item=property# 
			{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
			#/foreach#
		},
		parts = {
			#foreach from=$theItem.parts item=part# 
			{#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, 
			#/foreach#
		},
		gpprices={
			#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		crprices={
			#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		voucherprices={
			#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},	
		package = {
			#foreach from=$theItem.packages item=item# 
				"#$item.sysItem.displayName#", 
			#/foreach#
		},
		resource = {
			#if $theItem.sysItem.type==1#
				type=#$theItem.sysItem.wId#, 
				#foreach from=$theItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $theItem.sysItem.type==2# 
				type=#$theItem.sysItem.cId#,
				#foreach from=$theItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#elseif $theItem.sysItem.type==3# 
				type=#$theItem.sysItem.cId#,
				#foreach from=$theItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$theItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
		gstLevel =
			#if $theItem.gst_level==null# 
				0, 
			#else# 
				#$theItem.gst_level#,  
			#/if#
		gstExp=
			#if $theItem.gst_level_exp==null# 
				0, 
			#else# 
				#$theItem.gst_level_exp#, 
			#/if#
	#/if#
}
sloterItem= {
	#if $sloterItem != null#
		playeritemid=#$sloterItem.id#,
		sid=#$sloterItem.itemId#,
		isBind = "#$sloterItem.isBind#",
		#if $sloterItem.sysItem.iId!=null# 
			iid=#$sloterItem.sysItem.iId#, 
		#/if#
		display="#$sloterItem.sysItem.displayName#",
		name="#$sloterItem.sysItem.name#",
		unit_type=#$sloterItem.playerItemUnitType#,
		modified_desc="#$sloterItem.modifiedDesc#",
		characters={
			#foreach from=$sloterItem.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$sloterItem.sysItem.description#",
		pack_id = #$sloterItem.pack#,
		repair_cost = #$sloterItem.repairCost#,
		#if $sloterItem.sysItem.iId==1# 
			buff = #$sloterItem.buff#, 
		#/if#
		#if $sloterItem.sysItem.iId==5# 
			buff = #$sloterItem.buff#, 
		#/if#	
		isDefault =  
			#if $sloterItem.isDefault=="Y"# 
				0 ,
			#else# 
				1 ,
			#/if#
		mType = #$sloterItem.sysItem.mType#,
		mValue = #$sloterItem.sysItem.mValue#,
		common={
			level = #$sloterItem.sysItem.level#,
			materialNeed = #$sloterItem.materialNeed#,
			type = #$sloterItem.sysItem.type#,
			subtype = #$sloterItem.sysItem.subType#,
			#if $sloterItem.type==1# 
				wid=#$sloterItem.sysItem.wId#, 
			#/if#
			durable = 
				#if $sloterItem.durableInt<=0# 
					0,
				#else# 
					#$sloterItem.durableInt#, 
				#/if#
			quantity =  #$sloterItem.quantity#,
			minutes_left = 
				#if $sloterItem.timeLeft<=0# 
					0,
				#else# 
					#$sloterItem.timeLeft#, 
				#/if#
			seq=#$sloterItem.sysItem.seq#,
			is_vip=#$sloterItem.sysItem.isVip#,
			is_new=1,
			strength= 
				#if $sloterItem.sysItem.isStrengthen == 0# 
					-1 ,
				#else# 
					#$sloterItem.level# ,
				#/if#
			holeNum=#$sloterItem.holeNum#,
			cResistanceFire=#$sloterItem.cResistanceFire#,
			cResistanceBlast=#$sloterItem.cResistanceBlast#,
			cResistanceBullet=#$sloterItem.cResistanceBullet#,
			cResistanceKnife=#$sloterItem.cResistanceKnife#,
			cBloodAdd=#$sloterItem.sysItem.cBloodAdd#,
			rareLevel=#$sloterItem.sysItem.rareLevel#,
		},
		performance = {
			damange = #$sloterItem.sysItem.damange#,					
			speed =#$sloterItem.sysItem.shootSpeed#,	
			damange_add = #$sloterItem.damange - $sloterItem.sysItem.damange#,			
			speed_add = #$sloterItem.shootSpeed - $sloterItem.sysItem.shootSpeed#,			
			ammos = #$sloterItem.sysItem.wAmmoOneClip#,
			ammo_count=#$sloterItem.sysItem.wAmmoCount#,				
		},
		color=#$sloterItem.gunProperty.color#,
		gunproperty={
			#foreach from=$sloterItem.gunProperty.propertyList item=property# 
			{
				#if $sloterItem.gunProperty.color!=1# 
					#$sloterItem.gunProperty.color#,
				#else# 
					1,
				#/if#
				"#$property.basePropertyStr#"
			}, 
			#/foreach#
		},
		combineDetail = {
	    		#foreach from=$sloterItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    		#/foreach#
		},
		parts = {
			#foreach from=$sloterItem.parts item=part# 
			{#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, 
			#/foreach#
		},
		gpprices={
			#foreach from=$sloterItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		crprices={
			#foreach from=$sloterItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},
		voucherprices={
			#foreach from=$sloterItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
			#/foreach#
		},	
		package = {
			#foreach from=$sloterItem.packages item=item# 
				"#$item.sysItem.displayName#", 
			#/foreach#
		},
		resource = {
			#if $sloterItem.sysItem.type==1# 
				type=#$sloterItem.sysItem.wId#, 
				#foreach from=$sloterItem.resource item=res#  
					#if($res!="")# 
						"#$res#", 
					#/if# 
				#/foreach#
			#elseif $sloterItem.sysItem.type==2# 
				type=#$sloterItem.sysItem.cId#,
				#foreach from=$sloterItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#elseif $sloterItem.sysItem.type==3# 
				type=#$sloterItem.sysItem.cId#,
				#foreach from=$sloterItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#else#
				#foreach from=$sloterItem.resources item=resource# 
					"#$resource#", 
				#/foreach#
			#/if#
		},
		gstLevel =
			#if $sloterItem.gst_level==null# 
				0, 
			#else# 
				#$sloterItem.gst_level#,  
			#/if#
		gstExp=
			#if $sloterItem.gst_level_exp==null# 
				0, 
			#else# 
				#$sloterItem.gst_level_exp#, 
			#/if#
	#/if#
}