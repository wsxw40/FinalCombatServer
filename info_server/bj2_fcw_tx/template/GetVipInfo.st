vipLevel=#$vipLevel#
vipExp=#$vipExp#
nextLevelExp=#$nextLevelExp#
currentyEXPItem=#$currentyEXPItem#
leftMins=#$leftMins#
sid=#$vipItem.id#
display="#$vipItem.displayName#"
name="#$vipItem.name#"
description ="#$vipItem.description#"
crprices={
	#foreach from=$vipItem.crPricesList item=pay# 
	{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
	#/foreach#
}