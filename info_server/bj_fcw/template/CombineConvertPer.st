rate=#$rate#
from_rate=#$rateFrom#
to_rate=#$rateTo#
from= 
{
	#if $fromItem != null#

		playeritemid=#$fromItem.id#,
		sid=#$fromItem.itemId#,
		isBind = "#$fromItem.isBind#",
		#if $fromItem.sysItem.iId!=null# iid=#$fromItem.sysItem.iId#, #/if#
		display="#$fromItem.sysItem.displayName#",
		name="#$fromItem.sysItem.name#",
		unit_type=#$fromItem.playerItemUnitType#,
		modified_desc="#$fromItem.modifiedDesc#",
		characters={#foreach from=$fromItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$fromItem.sysItem.description#",
		pack_id = #$fromItem.pack#,
		repair_cost = #$fromItem.repairCost#,
		#if $fromItem.sysItem.wId==4# wid = #$fromItem.sysItem.wId#, #/if#
		#if $fromItem.sysItem.iId==1# buff = #$fromItem.buff#, #/if#
		#if $fromItem.sysItem.iId==5# buff = #$fromItem.buff#, #/if#	
		isDefault = #if $fromItem.isDefault=="Y"#0#else#1#/if#,
		mType = #$fromItem.sysItem.mType#,
		mValue = #$fromItem.sysItem.mValue#,
		common={
			level = #$fromItem.sysItem.level#,
			isOpenQuality=#if $fromItem.sysItem.wId==13# 0,#else# 1, #/if#
			materialNeed = #$fromItem.materialNeed#,
			type = #$fromItem.sysItem.type#,
			subtype = #$fromItem.sysItem.subType#,
			#if $fromItem.type==1# wid=#$fromItem.sysItem.wId#, #/if#
			durable = #if $fromItem.durableInt<=0# 0,#else# #$fromItem.durableInt#, #/if#
			quantity =  #$fromItem.quantity#,
			minutes_left = #if $fromItem.timeLeft<=0# 0,#else# #$fromItem.timeLeft#, #/if#
			seq=#$fromItem.sysItem.seq#,
			is_vip=#$fromItem.sysItem.isVip#,
			is_new=1,
			star=#$fromItem.fightNum#,   		
			strength=#if $fromItem.sysItem.isStrengthen == 0#-1#else##$fromItem.level##/if#,
			holeNum=#$fromItem.holeNum#,
			cResistanceFire=#$fromItem.sysItem.cResistanceFire#,
			cResistanceBlast=#$fromItem.sysItem.cResistanceBlast#,
			cResistanceBullet=#$fromItem.sysItem.cResistanceBullet#,
			cResistanceKnife=#$fromItem.sysItem.cResistanceKnife#,
			cBloodAdd=#$fromItem.sysItem.cBloodAdd#,
			cResistanceFire_add=#$fromItem.cResistanceFire#,
			cResistanceBlast_add=#$fromItem.cResistanceBlast#,
			cResistanceBullet_add=#$fromItem.cResistanceBullet#,
			cResistanceKnife_add=#$fromItem.cResistanceKnife#,
			cBloodAdd_add=#$fromItem.cBloodAdd#,
			rareLevel=#$fromItem.sysItem.rareLevel#,
		},
	    performance = {
    		damange = #$fromItem.sysItem.damange#,					
	    	speed =#$fromItem.sysItem.shootSpeed#,	
	    	damange_add = #$fromItem.damange - $fromItem.sysItem.damange#,			
    		speed_add = #$fromItem.shootSpeed - $fromItem.sysItem.shootSpeed#,			
	    	ammos = #$fromItem.sysItem.wAmmoOneClip#,
	    	ammo_count=#$fromItem.sysItem.wAmmoCount#,				
	    },
	    color=#$fromItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$fromItem.gunProperty.propertyList item=property# {
		    	#if $fromItem.gunProperty.color!=1# #$fromItem.gunProperty.color#,#else# 1,#/if#
		    	"#$property.basePropertyStr#"}, 
	    	#/foreach#
	    },
	    combineDetail = {
	    	#foreach from=$fromItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    	#/foreach#
	    },
	    parts = {#foreach from=$fromItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
	    gpprices={#foreach from=$fromItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$fromItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$fromItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
	    package = {#foreach from=$fromItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
		resource = {
	    #if $fromItem.sysItem.type==1# type=#$fromItem.sysItem.wId#, #foreach from=$fromItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $fromItem.sysItem.type==2# type=#$fromItem.sysItem.cId#,#foreach from=$fromItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $fromItem.sysItem.type==3# type=#$fromItem.sysItem.cId#,#foreach from=$fromItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$fromItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	#/if#
}
to= 
{
	#if $toItem != null#
		playeritemid=#$toItem.id#,
		sid=#$toItem.itemId#,
		isBind = "#$toItem.isBind#",
		#if $toItem.sysItem.iId!=null# iid=#$toItem.sysItem.iId#, #/if#
		display="#$toItem.sysItem.displayName#",
		name="#$toItem.sysItem.name#",
		unit_type=#$toItem.playerItemUnitType#,
		modified_desc="#$toItem.modifiedDesc#",
		characters={#foreach from=$toItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$toItem.sysItem.description#",
		pack_id = #$toItem.pack#,
		repair_cost = #$toItem.repairCost#,
		#if $toItem.sysItem.wId==4# wid = #$toItem.sysItem.wId#, #/if#
		#if $toItem.sysItem.iId==1# buff = #$toItem.buff#, #/if#
		#if $toItem.sysItem.iId==5# buff = #$toItem.buff#, #/if#	
		isDefault = #if $toItem.isDefault=="Y"#0#else#1#/if#,
		mType = #$toItem.sysItem.mType#,
		common={
			level = #$toItem.sysItem.level#,
			isOpenQuality=#if $toItem.sysItem.wId==13# 0,#else# 1, #/if#
			materialNeed = #$toItem.materialNeed#,
			type = #$toItem.sysItem.type#,
			subtype = #$toItem.sysItem.subType#,
			#if $toItem.type==1# wid=#$toItem.sysItem.wId#, #/if#
			durable = #if $toItem.durableInt<=0# 0,#else# #$toItem.durableInt#, #/if#
			quantity =  #$toItem.quantity#,
			minutes_left = #if $toItem.timeLeft<=0# 0,#else# #$toItem.timeLeft#, #/if#
			seq=#$toItem.sysItem.seq#,
			is_vip=#$toItem.sysItem.isVip#,
			is_new=1,
			star=#$toItem.fightNum#,    		
			strength=#if $toItem.sysItem.isStrengthen == 0#-1#else##$toItem.level##/if#,
			holeNum=#$toItem.holeNum#,
			cResistanceFire=#$toItem.sysItem.cResistanceFire#,
			cResistanceBlast=#$toItem.sysItem.cResistanceBlast#,
			cResistanceBullet=#$toItem.sysItem.cResistanceBullet#,
			cResistanceKnife=#$toItem.sysItem.cResistanceKnife#,
			cBloodAdd=#$toItem.sysItem.cBloodAdd#,
			cResistanceFire_add=#$toItem.cResistanceFire#,
			cResistanceBlast_add=#$toItem.cResistanceBlast#,
			cResistanceBullet_add=#$toItem.cResistanceBullet#,
			cResistanceKnife_add=#$toItem.cResistanceKnife#,
			cBloodAdd_add=#$toItem.cBloodAdd#,
			rareLevel=#$toItem.sysItem.rareLevel#,
		},
	    performance = {
    		damange = #$toItem.sysItem.damange#,					
	    	speed =#$toItem.sysItem.shootSpeed#,	
	    	damange_add = #$toItem.damange - $toItem.sysItem.damange#,			
    		speed_add = #$toItem.shootSpeed - $toItem.sysItem.shootSpeed#,			
	    	ammos = #$toItem.sysItem.wAmmoOneClip#,
	    	ammo_count=#$toItem.sysItem.wAmmoCount#,				
	    },
	    color=#$toItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$toItem.gunProperty.propertyList item=property# {
		    	#if $toItem.gunProperty.color!=1# #$toItem.gunProperty.color#,#else# 1,#/if#
		    	"#$property.basePropertyStr#"}, 
	    	#/foreach#
	    },
	    combineDetail = {
	    	#foreach from=$toItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#"}, 
	    	#/foreach#
	    },
	    parts = {#foreach from=$toItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
	    gpprices={#foreach from=$toItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$toItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$toItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
	    package = {#foreach from=$toItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
		resource = {
	    #if $toItem.sysItem.type==1# type=#$toItem.sysItem.wId#, #foreach from=$toItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $toItem.sysItem.type==2# type=#$toItem.sysItem.cId#,#foreach from=$toItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $toItem.sysItem.type==3# type=#$toItem.sysItem.cId#,#foreach from=$toItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$toItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	#/if#
}