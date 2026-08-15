bout_num=#$boutNum#
dartle_num=#$dartleNum#   
ammo_num=#$ammoNum#
look_num = #$lookNum#
needDartleNum=#$needDartleNum#

list = 
{
	#foreach from=$gifts item=onlineAward#
	{
		sid=#$onlineAward.sysItem.id#,
		display="#$onlineAward.sysItem.displayName#",
		name="#$onlineAward.sysItem.name#",
		modified_desc="#$onlineAward.sysItem.modifiedDesc#",
		characters={#foreach from=$onlineAward.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$onlineAward.sysItem.description#",
		unit=#$onlineAward.unit#,
		sendperson = #$onlineAward.sysItem.isHot#,
		#if $onlineAward.sysItem.wId==4# wid = #$onlineAward.sysItem.wId#, #/if#
		common={
			level = #$onlineAward.sysItem.level#,
			type = #$onlineAward.sysItem.type#,
			subtype = #$onlineAward.sysItem.subType#,
			#if $onlineAward.sysItem.type==1# wid=#$onlineAward.sysItem.wId#, #/if#
			seq=#$onlineAward.sysItem.seq#,
			is_vip=#$onlineAward.sysItem.isVip#,
			is_new=#$onlineAward.sysItem.isNew#,
			is_hot=#$onlineAward.sysItem.isHot#,
			star=#$onlineAward.sysItem.figthNumOutput#, 
			strength=#if $onlineAward.sysItem.isStrengthen == 0#-1#else##$onlineAward.sysItem.strengthLevel##/if#,			
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
	    	#foreach from=$onlineAward.sysItem.gunProperty.propertyList item=property# {
	    	#if $onlineAward.sysItem.gunProperty.color!=1# #$onlineAward.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$onlineAward.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$onlineAward.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$onlineAward.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$onlineAward.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $onlineAward.sysItem.type==1# type=#$onlineAward.sysItem.wId#, #foreach from=$onlineAward.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $onlineAward.sysItem.type==2# type=#$onlineAward.sysItem.cId#,#foreach from=$onlineAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $onlineAward.sysItem.type==3# type=#$onlineAward.sysItem.cId#,#foreach from=$onlineAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$onlineAward.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	    
	},
	#/foreach#
}

dartle_gift =  
{
		sid=#$dartleGift.sysItem.id#,
		display="#$dartleGift.sysItem.displayName#",
		name="#$dartleGift.sysItem.name#",
		modified_desc="#$dartleGift.sysItem.modifiedDesc#",
		characters={#foreach from=$dartleGift.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$dartleGift.sysItem.description#",
		unit=#$dartleGift.unit#,
		sendperson = #$dartleGift.sysItem.isHot#,
		#if $dartleGift.sysItem.wId==4# wid = #$dartleGift.sysItem.wId#, #/if#
		common={
			level = #$dartleGift.sysItem.level#,
			type = #$dartleGift.sysItem.type#,
			subtype = #$dartleGift.sysItem.subType#,
			#if $dartleGift.sysItem.type==1# wid=#$dartleGift.sysItem.wId#, #/if#
			seq=#$dartleGift.sysItem.seq#,
			is_vip=#$dartleGift.sysItem.isVip#,
			is_new=#$dartleGift.sysItem.isNew#,
			is_hot=#$dartleGift.sysItem.isHot#,
			star=#$dartleGift.sysItem.figthNumOutput#, 
			strength=#if $dartleGift.sysItem.isStrengthen == 0#-1#else##$dartleGift.sysItem.strengthLevel##/if#,			
			cResistanceFire=#$dartleGift.sysItem.cResistanceFire#,
			cResistanceBlast=#$dartleGift.sysItem.cResistanceBlast#,
			cResistanceBullet=#$dartleGift.sysItem.cResistanceBullet#,
			cResistanceKnife=#$dartleGift.sysItem.cResistanceKnife#,
			cBloodAdd=#$dartleGift.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$dartleGift.sysItem.rareLevel#,
		},
	    performance = {
		    		damange = #$dartleGift.sysItem.damange#,					
			    	speed =#$dartleGift.sysItem.shootSpeed#,	
			    	damange_add = #$dartleGift.sysItem.damange - $dartleGift.sysItem.damange#,			
		    		speed_add = #$dartleGift.sysItem.shootSpeed - $dartleGift.sysItem.shootSpeed#,			
			    	ammos = #$dartleGift.sysItem.wAmmoOneClip#,
			    	ammo_count=#$dartleGift.sysItem.wAmmoCount#,				
		    },
	    color=#$dartleGift.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$dartleGift.sysItem.gunProperty.propertyList item=property# {
	    	#if $dartleGift.sysItem.gunProperty.color!=1# #$dartleGift.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$dartleGift.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$dartleGift.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$dartleGift.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$dartleGift.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $dartleGift.sysItem.type==1# type=#$dartleGift.sysItem.wId#, #foreach from=$dartleGift.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $dartleGift.sysItem.type==2# type=#$dartleGift.sysItem.cId#,#foreach from=$dartleGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $dartleGift.sysItem.type==3# type=#$dartleGift.sysItem.cId#,#foreach from=$dartleGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$dartleGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
}

dartle_top_list = 
{
#foreach from=$dartleTopList item=entry#
	#$entry#
#/foreach#
}

dartle_awards_list =
{
	#foreach from=$awardsList item=theItem#
	{
		"#$theItem.player.name#",
		#$theItem.player.rank#,
		{
		item_num=#$theItem.quantity#,
		sid=#$theItem.sysItem.id#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		modified_desc="#$theItem.sysItem.modifiedDesc#",
		characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.sysItem.description#",
		sendperson = #$theItem.sysItem.isHot#,
		#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=#$theItem.sysItem.isNew#,
			is_hot=#$theItem.sysItem.isHot#,
			star=#$theItem.sysItem.figthNumOutput#,    		
			strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.sysItem.strengthLevel##/if#,	
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
	    color=#$theItem.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# {
	    	#if $theItem.sysItem.gunProperty.color!=1# #$theItem.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$theItem.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	  },  
	},
	#/foreach#
}
