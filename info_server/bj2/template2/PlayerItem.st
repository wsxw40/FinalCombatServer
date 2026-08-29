playeritemid = #$playerItem.playeritemid#,
sid = #$playerItem.sid#,
isBind = "#$playerItem.isBind#",
iid = #$playerItem.iid#, 
wid = #$playerItem.wid#,
display = "#$playerItem.display#",
name = "#$playerItem.name#",
createtime = #$playerItem.createtime#,
unit_type = #$playerItem.unitType.number#,
modified_desc = "#$playerItem.modifiedDesc#",
characters={
	#foreach from=$playerItem.charactersList item=id# 
		#$id#, 
	#/foreach#
},
description = "#$playerItem.description#",
pack_id = #$playerItem.packId#,
repair_cost = #$playerItem.repairCost#,
buff = #$playerItem.buff#,
isDefault = #$playerItem.isDefault#,
mType = #$playerItem.mType#,
mValue = #$playerItem.mValue#,
provisional_item_day = #$playerItem.provisionalItemDay#,
provisional_item_flag = #$playerItem.provisionalItemFlag#,
baseItemFightNum = #$playerItem.baseItemFightNum#,
strengthenItemFightNum = #$playerItem.strengthenItemFightNum#,
common={
	isAsset = #$playerItem.common.isAsset#,
	isOpenQuality = #$playerItem.common.isOpenQuality#,
	level = #$playerItem.common.level#,
	materialNeed = #$playerItem.common.materialNeed#,
	type = #$playerItem.common.type.number#,
	subtype = #$playerItem.common.subtype#,
	wid=#$playerItem.common.wid.number#, 
	durable = #$playerItem.common.durable#,
	quantity =  #$playerItem.common.quantity#,
	minutes_left = #$playerItem.common.minutesLeft#,
	seq = #$playerItem.common.seq#,
	is_vip = #$playerItem.common.isVip#,
	is_new = #$playerItem.common.isNew#,
	star = #$playerItem.common.star#,   		
	strength = #$playerItem.common.strength#, 
	holeNum=#$playerItem.common.holeNum#,
	cResistanceFire = #$playerItem.common.cResistanceFire#,
	cResistanceBlast = #$playerItem.common.cResistanceBlast#,
	cResistanceBullet = #$playerItem.common.cResistanceBullet#,
	cResistanceKnife = #$playerItem.common.cResistanceKnife#,
	cBloodAdd = #$playerItem.common.cBloodAdd#,
	cResistanceFire_add = #$playerItem.common.cResistanceFireAdd#,
	cResistanceBlast_add = #$playerItem.common.cResistanceBlastAdd#,
	cResistanceBullet_add = #$playerItem.common.cResistanceBulletAdd#,
	cResistanceKnife_add = #$playerItem.common.cResistanceKnifeAdd#,
	cBloodAdd_add = #$playerItem.common.cBloodAddAdd#,
	rareLevel = #$playerItem.common.rareLevel#,
	evaluateRmb = #$playerItem.common.evaluateRmb#,
	needTeamPlaceLevel = #$playerItem.common.needTeamPlaceLevel#,
	p_suitable = {
		#foreach from=$playerItem.common.pSuitableList item=pSuit# 
			"#$pSuit#", 
		#/foreach#
	}
},
performance = {
	damange = #$playerItem.performance.damage#,					
	speed =#$playerItem.performance.speed#,	
	ammos = #$playerItem.performance.ammos#,	
	ammo_count=#$playerItem.performance.ammoCount#,			
	damange_add = #$playerItem.performance.damageAdd#,
	speed_add = #$playerItem.performance.speedAdd#,
},
nextLevelDetail = {
	damange = #$playerItem.nextLevelDetail.damage#,					
	speed = #$playerItem.nextLevelDetail.speed#,	
	durable =  #$playerItem.nextLevelDetail.durable#,
	damange_add = #$playerItem.nextLevelDetail.damageAdd#,			
	speed_add = #$playerItem.nextLevelDetail.speedAdd#,		
	star = #$playerItem.nextLevelDetail.star#,
	level =  #$playerItem.nextLevelDetail.level#,
	cResistanceFire=#$playerItem.nextLevelDetail.cResistanceFire#,
	cResistanceBlast=#$playerItem.nextLevelDetail.cResistanceBlast#,
	cResistanceBullet=#$playerItem.nextLevelDetail.cResistanceBullet#,
	cResistanceKnife=#$playerItem.nextLevelDetail.cResistanceKnife#,
	holesNum = #$playerItem.nextLevelDetail.holesNum#,	
	color=#$playerItem.nextLevelDetail.color#,
	cBloodAdd_add=#$playerItem.nextLevelDetail.cBloodAddAdd#,			
},
color=#$playerItem.color.number#,
gunproperty={
	#foreach from=$playerItem.gunpropertyList item=property# 
		{"#$property.color.number#","#$property.basePropertyStr#"}, 
	#/foreach#
},
combineDetail = {
	#foreach from=$playerItem.combineDetailList item=property# 
		{
		index = #$property.index#, 
		state = #$property.state#, 
		level = #$property.level#, 
		desc = "#$property.desc#", 
		open = #$property.open#,
		}, 
	#/foreach#
},
suitId = #$playerItem.suitId#, 
suitDetail={
	des4={
		#foreach from=$playerItem.suitDetail.des4List item=des4#
			"#$des4#",
		#/foreach#
	},
	des6={
		#foreach from=$playerItem.suitDetail.des6List item=des6#
			"#$des6#",
		#/foreach#
	},
},
parts = {
	#foreach from=$theItem.partsList item=part# 
		{
			#$part.subType#,"#$part.displayName#", #$part.id#,
		}, 
	#/foreach#
},
gpprices={
	#foreach from=$playerItem.gppricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
crprices={
	#foreach from=$playerItem.crpricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
voucherprices={
	#foreach from=$playerItem.voucherpricesList item=pay# 
		{
		id=#$pay.id#,
		unittype=#$pay.unittype.number#,
		cost=#$pay.cost#,
		unit=#$pay.unit#,
		}, 
	#/foreach#
},
package = {
	#foreach from=$playerItem.packageStrList item=str# 
		"#$str#", 
	#/foreach#
},
resource = {
	type = #$playerItem.resource.type#,
	#foreach from=$playerItem.resource.resList item=res#
		"#$res#",
	#/foreach#
},
gstLevel = #$playerItem.gstLevel#,
gstExp= #$playerItem.gstExp#,
items={
	#foreach from=$playerItem.meltingItemList item=meltingItem# 
		{#$meltingItem.id#,"#$meltingItem.name#","#$meltingItem.displayName#"},
	#/foreach#
},
result={
	#foreach from=$playerItem.meltingResultList item=meltingResult# 
		{#$meltingResult.id#,"#$meltingResult.name#","#$meltingResult.displayName#",#$meltingResult.unit#,#$meltingResult.unitType.number#},
	#/foreach#
},