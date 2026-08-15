result = #$result#
item= 
{
	#if $theItem != null#
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
		mValue = #$theItem.sysItem.mValue#,
		common={
			level = #$theItem.sysItem.level#,
			isOpenQuality=#if $theItem.sysItem.wId==13# 0,#else# 1, #/if#
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
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
		    	#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
		    	"#$property.basePropertyStr#"}, 
	    	#/foreach#
	    },
	    combineDetail = {
	    	#foreach from=$theItem.gunProperty.strPropertyList item=property# 
	    		{index= #$property.index#, state=#$property.state#, level=#$property.level#, desc="#$property.desc#", open=#$property.open#}, 
	    	#/foreach#
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
	gstLevel =#if $theItem.gst_level==null# 0, #else# #$theItem.gst_level#,  #/if#
	gstExp=#if $theItem.gst_level_exp==null# 0, #else# #$theItem.gst_level_exp#, #/if#
	#/if#
}
