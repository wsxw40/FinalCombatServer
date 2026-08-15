info = {0,0,0,0,0}
items = 
{
	#if ($pa != null)#
		#include file="MissionItemAwardEntity.st" sysItem=$pa.sysActivity.sysItem unit=$pa.sysActivity.unit unitType=$pa.sysActivity.unitType isVip=0#
	#/if#
}