sid=#$sysItem.sid#,
display="#$sysItem.display#",
name="#$sysItem.name#",
description ="#$sysItem.description#",
modified_desc = "#$sysItem.modifiedDesc#",
timelimit = "#$sysItem.timelimit#",
color=#$sysItem.color.number#,
type = #$sysItem.type.number#,
sendperson = #$sysItem.sendperson#,
wid = #$sysItem.wid.number#,
iid = #$sysItem.iid.number#,
quantity = #$sysItem.quantity#,
price={
	#foreach from=$sysItem.priceList item=sysItemPrice#
		{
		#include file="SysItemPrice.st" sysItemPrice=$sysItemPrice#
		},
	#/foreach#
},
characters={
	#foreach from=$sysItem.charactersList item=ids# 
		#$ids#, 
	#/foreach#
},
common={
	level = #$sysItem.common.level#,
	isOpenQuality = #$sysItem.common.isOpenQuality#,
	modified_desc="#$sysItem.common.modifiedDesc#",
	characters={
		#foreach from=$sysItem.common.charactersList item=ids# 
			#$ids#, 
		#/foreach#
	},
	type = #$sysItem.common.type.number#,
	subtype = #$sysItem.common.subtype#,
	wid=#$sysItem.common.wid.number#,
	seq=#$sysItem.common.seq#,
	is_vip=#$sysItem.common.isVip#,
	is_new=#$sysItem.common.isNew#,
	is_hot=#$sysItem.common.isHot#,
	is_web=#$sysItem.common.isWeb#,
	isFlashSale = #$sysItem.common.isFlashSale#,
	mType = #$sysItem.common.mType.number#,
	star=#$sysItem.common.star#,   		
	strength= #$sysItem.common.strength#,
	cResistanceFire=#$sysItem.common.cResistanceFire#,
	cResistanceBlast=#$sysItem.common.cResistanceBlast#,
	cResistanceBullet=#$sysItem.common.cResistanceBullet#,
	cResistanceKnife=#$sysItem.common.cResistanceKnife#,
	cBloodAdd=#$sysItem.common.cBloodAdd#,
	cResistanceFire_add=#$sysItem.common.cResistanceFireAdd#,
	cResistanceBlast_add=#$sysItem.common.cResistanceBlastAdd#,
	cResistanceBullet_add=#$sysItem.common.cResistanceBulletAdd#,
	cResistanceKnife_add=#$sysItem.common.cResistanceKnifeAdd#,
	cBloodAdd_add=#$sysItem.common.cBloodAddAdd#,
	rareLevel=#$sysItem.common.rareLevel#,
},
performance = {
	damange = #$sysItem.performance.damage#,					
	speed =#$sysItem.performance.speed#,	
	ammos = #$sysItem.performance.ammos#,	
	ammo_count=#$sysItem.performance.ammoCount#,			
	damange_add = #$sysItem.performance.damageAdd#,
	speed_add = #$sysItem.performance.speedAdd#,
},
gunproperty={
	#foreach from=$sysItem.gunpropertyList item=property# 
		{"#$property.color.number#","#$property.basePropertyStr#"}, 
	#/foreach#
},
package = {
	#foreach from=$sysItem.packageStrList item=str# 
		"#$str#", 
	#/foreach#
},
gpprices={
	#foreach from=$sysItem.gppricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
crprices={
	#foreach from=$sysItem.crpricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
voucherprices={
	#foreach from=$sysItem.voucherpricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
resource = {
	type = #$sysItem.resource.type#,
	#foreach from=$sysItem.resource.resList item=res#
		"#$res#",
	#/foreach#
},
suitId=#$sysItem.suitId#,
suitDetail={
	des4={
	},
	des6={
	},
},