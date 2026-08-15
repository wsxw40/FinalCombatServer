bout_num=#$boutNum#
dartle_num=#$dartleNum#   
ammo_num=#$ammoNum#
needDartleNum=#$needDartleNum#

look_item = 
{
		sid=#$shootingLook.id#,
		display="#$shootingLook.displayName#",
		name="#$shootingLook.name#",
		modified_desc="#$shootingLook.modifiedDesc#",
		characters={#foreach from=$shootingLook.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$shootingLook.description#",
		unit=#$shootingLook.unit#,
		i_value = #$shootingLook.iValue#,
		sendperson = #$shootingLook.isHot#,
		#if $shootingLook.wId==4# wid = #$shootingLook.wId#, #/if#
		common={
			level = #$shootingLook.level#,
			type = #$shootingLook.type#,
			subtype = #$shootingLook.subType#,
			#if $shootingLook.type==1# wid=#$shootingLook.wId#, #/if#
			seq=#$shootingLook.seq#,
			is_vip=#$shootingLook.isVip#,
			is_new=#$shootingLook.isNew#,
			is_hot=#$shootingLook.isHot#,
			star=#$shootingLook.figthNumOutput#, 
			strength=#if $shootingLook.isStrengthen == 0#-1#else##$shootingLook.strengthLevel##/if#,			
			cResistanceFire=#$shootingLook.cResistanceFire#,
			cResistanceBlast=#$shootingLook.cResistanceBlast#,
			cResistanceBullet=#$shootingLook.cResistanceBullet#,
			cResistanceKnife=#$shootingLook.cResistanceKnife#,
			cBloodAdd=#$shootingLook.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$shootingLook.rareLevel#,
		},
	    performance = {
		    		damange = #$shootingLook.damange#,					
			    	speed =#$shootingLook.shootSpeed#,	
			    	damange_add = #$shootingLook.damange - $shootingLook.damange#,			
		    		speed_add = #$shootingLook.shootSpeed - $shootingLook.shootSpeed#,			
			    	ammos = #$shootingLook.wAmmoOneClip#,
			    	ammo_count=#$shootingLook.wAmmoCount#,				
		    },
	    color=#$shootingLook.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$shootingLook.gunProperty.propertyList item=property# {
	    	#if $shootingLook.gunProperty.color!=1# #$shootingLook.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$shootingLook.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$shootingLook.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$shootingLook.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$shootingLook.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $shootingLook.type==1# type=#$shootingLook.wId#, #foreach from=$shootingLook.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $shootingLook.type==2# type=#$shootingLook.cId#,#foreach from=$shootingLook.resources item=resource# "#$resource#", #/foreach#
	    #elseif $shootingLook.type==3# type=#$shootingLook.cId#,#foreach from=$shootingLook.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$shootingLook.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
}
gold_gift =  
{
		sid=#$goldGift.sysItem.id#,
		display="#$goldGift.sysItem.displayName#",
		name="#$goldGift.sysItem.name#",
		modified_desc="#$goldGift.sysItem.modifiedDesc#",
		characters={#foreach from=$goldGift.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$goldGift.sysItem.description#",
		unit=#$goldGift.unit#,
		sendperson = #$goldGift.sysItem.isHot#,
		#if $goldGift.sysItem.wId==4# wid = #$goldGift.sysItem.wId#, #/if#
		common={
			level = #$goldGift.sysItem.level#,
			type = #$goldGift.sysItem.type#,
			subtype = #$goldGift.sysItem.subType#,
			#if $goldGift.sysItem.type==1# wid=#$goldGift.sysItem.wId#, #/if#
			seq=#$goldGift.sysItem.seq#,
			is_vip=#$goldGift.sysItem.isVip#,
			is_new=#$goldGift.sysItem.isNew#,
			is_hot=#$goldGift.sysItem.isHot#,
			star=#$goldGift.sysItem.figthNumOutput#, 
			strength=#if $goldGift.sysItem.isStrengthen == 0#-1#else##$goldGift.sysItem.strengthLevel##/if#,			
			cResistanceFire=#$goldGift.sysItem.cResistanceFire#,
			cResistanceBlast=#$goldGift.sysItem.cResistanceBlast#,
			cResistanceBullet=#$goldGift.sysItem.cResistanceBullet#,
			cResistanceKnife=#$goldGift.sysItem.cResistanceKnife#,
			cBloodAdd=#$goldGift.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$goldGift.sysItem.rareLevel#,
		},
	    performance = {
		    		damange = #$goldGift.sysItem.damange#,					
			    	speed =#$goldGift.sysItem.shootSpeed#,	
			    	damange_add = #$goldGift.sysItem.damange - $goldGift.sysItem.damange#,			
		    		speed_add = #$goldGift.sysItem.shootSpeed - $goldGift.sysItem.shootSpeed#,			
			    	ammos = #$goldGift.sysItem.wAmmoOneClip#,
			    	ammo_count=#$goldGift.sysItem.wAmmoCount#,				
		    },
	    color=#$goldGift.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$goldGift.sysItem.gunProperty.propertyList item=property# {
	    	#if $goldGift.sysItem.gunProperty.color!=1# #$goldGift.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$goldGift.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$goldGift.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$goldGift.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$goldGift.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $goldGift.sysItem.type==1# type=#$goldGift.sysItem.wId#, #foreach from=$goldGift.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $goldGift.sysItem.type==2# type=#$goldGift.sysItem.cId#,#foreach from=$goldGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $goldGift.sysItem.type==3# type=#$goldGift.sysItem.cId#,#foreach from=$goldGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$goldGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	    
}

silver_gift = 
{
		sid=#$silverGift.sysItem.id#,
		display="#$silverGift.sysItem.displayName#",
		name="#$silverGift.sysItem.name#",
		modified_desc="#$silverGift.sysItem.modifiedDesc#",
		characters={#foreach from=$silverGift.sysItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$silverGift.sysItem.description#",
		unit=#$silverGift.unit#,
		sendperson = #$silverGift.sysItem.isHot#,
		#if $silverGift.sysItem.wId==4# wid = #$silverGift.sysItem.wId#, #/if#
		common={
			level = #$silverGift.sysItem.level#,
			type = #$silverGift.sysItem.type#,
			subtype = #$silverGift.sysItem.subType#,
			#if $silverGift.sysItem.type==1# wid=#$silverGift.sysItem.wId#, #/if#
			seq=#$silverGift.sysItem.seq#,
			is_vip=#$silverGift.sysItem.isVip#,
			is_new=#$silverGift.sysItem.isNew#,
			is_hot=#$silverGift.sysItem.isHot#,
			star=#$silverGift.sysItem.figthNumOutput#, 
			strength=#if $silverGift.sysItem.isStrengthen == 0#-1#else##$silverGift.sysItem.strengthLevel##/if#,			
			cResistanceFire=#$silverGift.sysItem.cResistanceFire#,
			cResistanceBlast=#$silverGift.sysItem.cResistanceBlast#,
			cResistanceBullet=#$silverGift.sysItem.cResistanceBullet#,
			cResistanceKnife=#$silverGift.sysItem.cResistanceKnife#,
			cBloodAdd=#$silverGift.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$silverGift.sysItem.rareLevel#,
		},
	    performance = {
		    		damange = #$silverGift.sysItem.damange#,					
			    	speed =#$silverGift.sysItem.shootSpeed#,	
			    	damange_add = #$silverGift.sysItem.damange - $silverGift.sysItem.damange#,			
		    		speed_add = #$silverGift.sysItem.shootSpeed - $silverGift.sysItem.shootSpeed#,			
			    	ammos = #$silverGift.sysItem.wAmmoOneClip#,
			    	ammo_count=#$silverGift.sysItem.wAmmoCount#,				
		    },
	    color=#$silverGift.sysItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$silverGift.sysItem.gunProperty.propertyList item=property# {
	    	#if $silverGift.sysItem.gunProperty.color!=1# #$silverGift.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$silverGift.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$silverGift.sysItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$silverGift.sysItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$silverGift.sysItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $silverGift.sysItem.type==1# type=#$silverGift.sysItem.wId#, #foreach from=$silverGift.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $silverGift.sysItem.type==2# type=#$silverGift.sysItem.cId#,#foreach from=$silverGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $silverGift.sysItem.type==3# type=#$silverGift.sysItem.cId#,#foreach from=$silverGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$silverGift.sysItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
}

common_gift = 
{
	#foreach from=$commonGifts item=onlineAward#
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
