items = {
#if ($awardItemVos != null)#
#foreach from=$awardItemVos item=awardItemVo#
	#if $awardItemVo.item.unitType == 1# 
		#include file="MissionItemAwardEntity.st" sysItem=$awardItemVo.item unitType=$awardItemVo.item.unitType unit=$awardItemVo.itemNum isVip=$awardItemVo.vip#
	#else# 
		#include file="MissionItemAwardEntity.st" sysItem=$awardItemVo.item unitType=$awardItemVo.item.unitType unit=$awardItemVo.item.unit isVip=$awardItemVo.vip#
	#/if#
#/foreach#
#/if#
}
