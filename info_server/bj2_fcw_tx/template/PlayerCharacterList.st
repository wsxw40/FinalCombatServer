data ={
	#foreach from=$list item=ch#	
	{
		cid=#$ch.sysCharacter.id#,
		name="#$ch.sysCharacter.name#",
		is_open=#$ch.isOpen#,
		resource="#$ch.headResource#",
		resourceName="#$ch.sysCharacter.resourceName#",
		description="#$ch.description#",
	},
	#/foreach#		
}
select={#$selectIds#,}
size=#$size#
openSize=#$openSize#
bioChardata={
	#foreach from=$bioCharList item=bio#	
	{
		cid=#$bio.sysCharacter.id#,
		name="#$bio.sysCharacter.name#",
		state=#$bio.stateInCharList#,
		resourceName="#$bio.sysCharacter.resourceName#",
		leftSeconds=#$bio.leftSeconds#,
	},
#/foreach#		
}
bioItemdata={
	#foreach from=$bioItemList item=theItem#	
	{
	itemID=#$theItem.id#,
	display="#$theItem.displayName#",
	name="#$theItem.name#",
	description ="#$theItem.description#",
	gpprices={
		#foreach from=$theItem.gpPricesList item=pay# 
		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
		#/foreach#},
	crprices={
		#foreach from=$theItem.crPricesList item=pay# 
		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
		#/foreach#},
	voucherprices={
		#foreach from=$theItem.voucherPricesList item=pay# 
	    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
		#/foreach#},
	},
	resource = {
		#foreach from=$theItem.resources item=resource# 
			"#$resource#", 
		#/foreach#
	},
	#/foreach#
}