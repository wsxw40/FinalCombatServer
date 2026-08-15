enable=1
player={
	name= "#$name#",
}
fightnum=#$fightnum#
items=
	{
	resourcename="#$character.sysCharacter.resourceName#",
	name="#$character.sysCharacter.name#",
	avatar={#foreach from=$character.sysCharacter.costume item=res# 
		#if ($res != null)#"#$res#",#/if#
		#/foreach#},
	putSuitId=#$character.suitId#,
	suitCount=#$character.suitNum#,
	weapons={
		#foreach from=$character.weaponList item=theItem#
		
		#if ($theItem != null)#
			{
			playeritemid=#$theItem.id#,
			sid=#$theItem.itemId#,
			isBind = "#$theItem.isBind#",
			display="#$theItem.sysItem.displayName#",
			name="#$theItem.sysItem.name#",
			unit_type=#$theItem.playerItemUnitType#,
			modified_desc="#$theItem.modifiedDesc#",
			characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
			description ="#$theItem.sysItem.description#",
			pack_id = #$theItem.pack#,
			#if $theItem.sysItem.isStrengthen==0# 
				baseItemFightNum=#$theItem.sysItem.figthNumOutput#,
			#else#
				baseItemFightNum=#$theItem.baseItemFightNum#,
			 #/if#
			strengthenItemFightNum=#$theItem.strengthenItemFightNum#,
			repair_cost = #$theItem.repairCost#,
			isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
			provisional_item_day=#$theItem.provisional_item_day#,
			provisional_item_flag=#$theItem.provisional_item_flag#,
			#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
			common={
			
			isAsset = #$theItem.sysItem.isAsset#,
			isOpenQuality=#if $theItem.sysItem.wId==13# 0,#else# 1, #/if#
				level = #$theItem.sysItem.level#,
				type = #$theItem.sysItem.type#,
				subtype = #$theItem.sysItem.subType#,
				#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
				durable = #if $theItem.durable<=0# 0,#else# #$theItem.durable#, #/if#
				quantity =  #$theItem.quantity#,
				p_suitable = {#foreach from=$theItem.sysItem.pSuits item=pSuit)# "#$pSuit#", #/foreach#},
				minutes_left = #if $theItem.timeLeft<=0# 0,#else# #$theItem.timeLeft#, #/if#
				seq=#$theItem.sysItem.seq#,
				is_vip=#$theItem.sysItem.isVip#,
				is_new=1,
				star = #$theItem.fightNum#,    		
				strength=#$theItem.level#,
				cResistanceFire=#$theItem.sysItem.cResistanceFire#,
				cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
				cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
				cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
				cBloodAdd=#$theItem.sysItem.cBloodAdd#,
				cResistanceFire_add=#$theItem.cResistanceFire#,
				cResistanceBlast_add=#$theItem.cResistanceBlast#,
				cResistanceBullet_add=#$theItem.cResistanceBullet#,
				cResistanceKnife_add=#$theItem.cResistanceKnife#,
				cBloodAdd_add=#$theItem.cBloodAdd#,
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
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
	    		#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	   #if $theItem.sysItem.needTeamPlaceLevel > 99# 
		  suitId=#$theItem.sysItem.belongSuit.suitId#, 
		  
		  suitDetail={
				des4={#foreach from=$theItem.sysItem.belongSuit.allSpec4Pros item=property#
				"#$property.desc#",
				#/foreach#
				},
				des6={#foreach from=$theItem.sysItem.belongSuit.allSpec6Pros item=property#
				"#$property.desc#",
				#/foreach#},
		  },
		#/if#
	     combineDetail = {
	    	#foreach from=$theItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    	#/foreach#
	    },
		   	gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    	crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    	voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
		    parts = {#foreach from=$theItem.parts item=part# {#$part.subType#,"#$part.displayName#", #$part.id#,}, #/foreach#},
			resource = {
		    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
		    #/if#
		    },

		gstLevel =#if $theItem.gst_level==null# 0, #else# #$theItem.gst_level#,  #/if#
		gstExp=#if $theItem.gst_level_exp==null# 0, #else# #$theItem.gst_level_exp#, #/if#

		    },
		 #else#   
		 {},
		 #/if#
		 
	#/foreach#
	},
	costume={
		#foreach from=$character.costumeList item=theItem#
		
		#if ($theItem!= null)#
		{
			playeritemid=#$theItem.id#,
			sid=#$theItem.itemId#,
			isBind = "#$theItem.isBind#",
			display="#$theItem.sysItem.displayName#",
			name="#$theItem.sysItem.name#",
			unit_type=#$theItem.playerItemUnitType#,
			currency=#$theItem.sysItem.currency#,
			modified_desc="#$theItem.modifiedDesc#",
			characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
			description ="#$theItem.sysItem.description#",
			pack_id = #$theItem.pack#,
			repair_cost = #$theItem.repairCost#,
			baseItemFightNum=#$theItem.baseItemFightNum#,
			strengthenItemFightNum=#$theItem.strengthenItemFightNum#,
			isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
			provisional_item_day=#$theItem.provisional_item_day#,
			provisional_item_flag=#$theItem.provisional_item_flag#,
			#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
			common={
			isAsset = #$theItem.sysItem.isAsset#,
				level = #$theItem.sysItem.level#,
				type = #$theItem.sysItem.type#,
				subtype = #$theItem.sysItem.subType#,
				#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
				durable = #$theItem.durable#,
				quantity =  #$theItem.quantity#,
				minutes_left = #$theItem.timeLeft#,
				seq=#$theItem.sysItem.seq#,
				is_vip=#$theItem.sysItem.isVip#,
				is_new=1,
				star = #$theItem.fightNum#,    		
				strength=#$theItem.level#,
				cResistanceFire=#$theItem.sysItem.cResistanceFire#,
				cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
				cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
				cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
				cBloodAdd=#$theItem.sysItem.cBloodAdd#,
				cResistanceFire_add=#$theItem.cResistanceFire#,
				cResistanceBlast_add=#$theItem.cResistanceBlast#,
				cResistanceBullet_add=#$theItem.cResistanceBullet#,
				cResistanceKnife_add=#$theItem.cResistanceKnife#,
				cBloodAdd_add=#$theItem.cBloodAdd#,
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
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
	    		#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	       #if $theItem.sysItem.needTeamPlaceLevel > 99# 
		  suitId=#$theItem.sysItem.belongSuit.suitId#, 
		  suitDetail={
				des4={#foreach from=$theItem.sysItem.belongSuit.allSpec4Pros item=property#
				"#$property.desc#",
				#/foreach#
				},
				des6={#foreach from=$theItem.sysItem.belongSuit.allSpec6Pros item=property#
				"#$property.desc#",
				#/foreach#},
		  },
		#/if#
		    combineDetail = {
	    	#foreach from=$theItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    	#/foreach#
	    	},
		   	gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    	crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    	voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
		    parts = {#foreach from=$theItem.parts item=part# {#$part.subType#,"#$part.displayName#", #$part.id#,}, #/foreach#},
			resource = {
		    #if $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#, #foreach from=$theItem.resources item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
		    #/if#
		    #if $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#, #foreach from=$theItem.resources item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
		    #/if#
		    },

		gstLevel =#if $theItem.gst_level==null# 0, #else# #$theItem.gst_level#,  #/if#
		gstExp=#if $theItem.gst_level_exp==null# 0, #else# #$theItem.gst_level_exp#, #/if#

		  },
		  #else#
		  {}, 
		 #/if#

	#/foreach#
	},
	}

