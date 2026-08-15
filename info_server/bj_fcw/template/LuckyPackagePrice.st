chipNum=#$chipNum#
copperyKeyNum=#$copperyKeyNum#
silverKeyNum=#$silverKeyNum#
goldKeyNum=#$goldKeyNum#
fix={
	#foreach from=$fix item=theItem#	
	{
		level = #$theItem.level#,							
		payFC = 1,							
		payChip = #$theItem.chipPrice#,	
	},
	#/foreach#
}
luckyPackages={
	#foreach from=$luckyPackages item=theItem#	
		{
		sid=#$theItem.id#,
		display="#$theItem.displayName#",
		name="#$theItem.name#",
		modified_desc="#$theItem.modifiedDesc#",
		characters={#foreach from=$theItem.characterList item=ids)# #$ids#, #/foreach#},
		description ="#$theItem.description#",
		sendperson = #$theItem.isHot#,
		ivalue="#$theItem.iValue#",
		common={
			level = #$theItem.level#,
			type = #$theItem.type#,
			subtype = #$theItem.subType#,
			#if $theItem.type==1# wid=#$theItem.wId#, #/if#
			seq=#$theItem.seq#,
			is_vip=#$theItem.isVip#,
			is_new=#$theItem.isNew#,
			is_hot=#$theItem.isHot#,
			star=#$theItem.sysItem.figthNumOutput#,    		
			strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.sysItem.strengthLevel##/if#,	
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
	    	ammos = #$theItem.wAmmoOneClip#,
	    	ammo_count=#$theItem.wAmmoCount#,				
		},
	    color=#$theItem.gunProperty.color#,
	    gunproperty={
	    	#foreach from=$theItem.gunProperty.propertyList item=property# {
	    	#if $theItem.gunProperty.color!=1# #$theItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {#foreach from=$theItem.packages item=item# "#$item.displayName#", #/foreach#},
	    gpprices={#foreach from=$theItem.gpPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    crprices={#foreach from=$theItem.crPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
	    voucherprices={#foreach from=$theItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
	   
	    
	    resource = {
	    #if $theItem.type==1# type=#$theItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
	    #elseif $theItem.type==2# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #elseif $theItem.type==3# type=#$theItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #else#
	    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
	    #/if#
	    },
	},
	#/foreach#
}
