result = #$result#
items= 
{
	#if $playerItem!=null#
	{   
		playeritemid=#$playerItem.id#,
		sid=#$playerItem.itemId#,
		isBind = "#$playerItem.isBind#",
		#if $playerItem.sysItem.iId!=null# iid=#$playerItem.sysItem.iId#, #/if#
		display="#$playerItem.sysItem.displayName#",
		name="#$playerItem.sysItem.name#",
		unit_type=#$playerItem.playerItemUnitType#,
		modified_desc="#$playerItem.modifiedDesc#",
		characters={#foreach from=$playerItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$playerItem.sysItem.description#",
		pack_id = #$playerItem.pack#,
		repair_cost = #$playerItem.repairCost#,
		#if $playerItem.sysItem.wId==4# wid = #$playerItem.sysItem.wId#, #/if#
		#if $playerItem.sysItem.iId==1# buff = #$playerItem.buff#, #/if#
		#if $playerItem.sysItem.iId==5# buff = #$playerItem.buff#, #/if#	
		isDefault = #if $playerItem.isDefault=="Y"#0#else#1#/if#,
		mType = #$playerItem.sysItem.mType#,
		mValue = #$playerItem.sysItem.mValue#,
		common={
			level = #$playerItem.sysItem.level#,
			materialNeed = #$playerItem.materialNeed#,
			gpNeed = #$playerItem.gpNeed#,
			type = #$playerItem.sysItem.type#,
			subtype = #$playerItem.sysItem.subType#,
			#if $playerItem.type==1# wid=#$playerItem.sysItem.wId#, #/if#
			durable = #if $playerItem.durableInt<=0# 0,#else# #$playerItem.durableInt#, #/if#
			quantity =  #$playerItem.quantity#,
			minutes_left = #if $playerItem.timeLeft<=0# 0,#else# #$playerItem.timeLeft#, #/if#
			seq=#$playerItem.sysItem.seq#,
			is_vip=#$playerItem.sysItem.isVip#,
			is_new=1,
			star=#$playerItem.fightNum#,    		
			strength=#if $playerItem.sysItem.isStrengthen == 0#-1#else##$playerItem.level##/if#,
			holeNum=#$playerItem.holeNum#,
			cResistanceFire=#$playerItem.sysItem.cResistanceFire#,
			cResistanceBlast=#$playerItem.sysItem.cResistanceBlast#,
			cResistanceBullet=#$playerItem.sysItem.cResistanceBullet#,
			cResistanceKnife=#$playerItem.sysItem.cResistanceKnife#,
			cBloodAdd=#$playerItem.sysItem.cBloodAdd#,
			cResistanceFire_add=#$playerItem.cResistanceFire#,
			cResistanceBlast_add=#$playerItem.cResistanceBlast#,
			cResistanceBullet_add=#$playerItem.cResistanceBullet#,
			cResistanceKnife_add=#$playerItem.cResistanceKnife#,
			cBloodAdd_add=#$playerItem.cBloodAdd#,
			rareLevel=#$playerItem.sysItem.rareLevel#,
		},
	    performance = {
			damange = #$playerItem.sysItem.damange#,					
	    	speed =#$playerItem.sysItem.shootSpeed#,	
	    	damange_add = #$playerItem.damange - $playerItem.sysItem.damange#,			
			speed_add = #$playerItem.shootSpeed - $playerItem.sysItem.shootSpeed#,			
	    	ammos = #$playerItem.sysItem.wAmmoOneClip#,
	    	ammo_count=#$playerItem.sysItem.wAmmoCount#,				
		},
		nextLevelDetail = {
	    	damange = #$playerItem.sysItem.damange#,					
	    	speed =#$playerItem.sysItem.shootSpeed#,	
	    	durable = #if $playerItem.durableInt<=0# 0,#else# #$playerItem.durableInt#, #/if#
	    	damange_add = #$playerItem.nextDamange - $playerItem.sysItem.damange#,			
			speed_add = #$playerItem.nextShootSpeed - $playerItem.sysItem.shootSpeed#,	
			star = #$playerItem.nextFightNum#,
			level = #if ($playerItem.level) >= 18#18#else##$playerItem.level + 1##/if#,
			cResistanceFire=#$playerItem.nextCResistanceFire#,
			cResistanceBlast=#$playerItem.nextCResistanceBlast#,
			cResistanceBullet=#$playerItem.nextCResistanceBullet#,
			cResistanceKnife=#$playerItem.nextCResistanceKnife#,
			holesNum = #$playerItem.nextHoleNum#,	
	    	color=#$playerItem.nextColor#,
	    	cBloodAdd_add=#$playerItem.nextBloodAdd#,			
	    },
	    color=#$playerItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$playerItem.gunProperty.propertyList item=property# {
	    	#if $playerItem.gunProperty.color!=1# #$playerItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    combineDetail = {
	    	#foreach from=$playerItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    	#/foreach#
	    },
	    parts = {#foreach from=$playerItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
	    gpprices={#foreach from=$playerItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$playerItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$playerItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
	    package = {#foreach from=$playerItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
		resource = {
	    #if $playerItem.sysItem.type==1# type=#$playerItem.sysItem.wId#, #foreach from=$playerItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $playerItem.sysItem.type==2# type=#$playerItem.sysItem.cId#,#foreach from=$playerItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $playerItem.sysItem.type==3# type=#$playerItem.sysItem.cId#,#foreach from=$playerItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$playerItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	gstLevel =#if $playerItem.gst_level==null# 0, #else# #$playerItem.gst_level#,  #/if#
	gstExp=#if $playerItem.gst_level_exp==null# 0, #else# #$playerItem.gst_level_exp#, #/if#
	},
	#/if#
}
items2= 
{
#if $itemList!=null#
#foreach from=$itemList item=theItem#
	{   
		#if $theItem!=null#
		playeritemid=#$theItem.id#,
		sid=#$theItem.itemId#,
		isBind = "#$theItem.isBind#",
		#if $theItem.sysItem.iId!=null# iid=#$theItem.sysItem.iId#, #/if#
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		unit_type=#$theItem.playerItemUnitType#,
		modified_desc="#$theItem.modifiedDesc#",
		characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.sysItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
		#if $theItem.sysItem.iId==1# buff = #$theItem.buff#, #/if#
		#if $theItem.sysItem.iId==5# buff = #$theItem.buff#, #/if#	
		isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
		mType = #$theItem.sysItem.mType#,
		common={
			level = #$theItem.sysItem.level#,
			materialNeed = #$theItem.materialNeed#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
			durable = #if $theItem.durableInt<=0# 0,#else# #$theItem.durableInt#, #/if#
			quantity =  #$theItem.quantity#,
			minutes_left = #if $theItem.timeLeft<=0# 0,#else# #$theItem.timeLeft#, #/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=1,
			star=#$theItem.fightNum#,    		
			strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.level##/if#,
			holeNum=#$theItem.holeNum#,
			cResistanceFire=#$theItem.cResistanceFire#,
			cResistanceBlast=#$theItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.cResistanceKnife#,
			cBloodAdd=#$theItem.cBloodAdd#,
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
	    parts = {#foreach from=$theItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
	    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
	    package = {#foreach from=$theItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
		resource = {
	    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	   #/if#
	},
#/foreach#
#/if#
}
failAwards={
	#if $failAwards!=null#
	#foreach from=$failAwards item=theItem#
	{   
		#if $theItem!=null#
		sid=#$theItem.Id#,
		#if $theItem.iId!=null# iid=#$theItem.iId#, #/if#
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		item_num =#$theItem.unit#,
		unit_type=#$theItem.unitType#,
		characters={#foreach from=$theItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.description#",
		pack_id = #$theItem.pack#,
		repair_cost = #$theItem.repairCost#,
		#if $theItem.wId==4# wid = #$theItem.wId#, #/if#
		#if $theItem.iId==1# buff = #$theItem.buff#, #/if#
		#if $theItem.iId==5# buff = #$theItem.buff#, #/if#	
		isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
		mType = #$theItem.mType#,
		common={
			level = #$theItem.level#,
			materialNeed = #$theItem.materialNeed#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			#if $theItem.type==1# wid=#$theItem.wId#, #/if#
			durable = #if $theItem.durableInt<=0# 0,#else# #$theItem.durableInt#, #/if#
			quantity =  #$theItem.quantity#,
			minutes_left = #if $theItem.timeLeft<=0# 0,#else# #$theItem.timeLeft#, #/if#
			seq=#$theItem.seq#,
			is_vip=#$theItem.isVip#,
			is_new=1,
			star=#$theItem.fightNum#,    		
			strength=#if $theItem.isStrengthen == 0#-1#else##$theItem.level##/if#,
			holeNum=#$theItem.holeNum#,
			cResistanceFire=#$theItem.cResistanceFire#,
			cResistanceBlast=#$theItem.cResistanceBlast#,
			cResistanceBullet=#$theItem.cResistanceBullet#,
			cResistanceKnife=#$theItem.cResistanceKnife#,
			cBloodAdd=#$theItem.cBloodAdd#,
			rareLevel=#$theItem.rareLevel#,
		},
	    performance = {
    		damange = #$theItem.damange#,					
	    	speed =#$theItem.shootSpeed#,	
	    	damange_add = #$theItem.damange - $theItem.damange#,			
    		speed_add = #$theItem.shootSpeed - $theItem.shootSpeed#,			
	    	ammos = #$theItem.wAmmoOneClip#,
	    	ammo_count=#$theItem.wAmmoCount#,				
	    },
	    color=#$theItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
	    	#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    parts = {#foreach from=$theItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
	    gpprices={#foreach from=$theItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
	    package = {#foreach from=$theItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
		resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.type==2# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.type==3# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	   #/if#
	},
	#/foreach#
	#/if#

}