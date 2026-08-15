result = #$meltingReslut.result#
recycle_ratio = #$recycle_ratio#
#if $meltingReslut.result == 0# 
	upgrade = #$meltingReslut.playerMelting.upgrade#
	#include file="MeltingInfoGet.st" playerMelting=$meltingReslut.playerMelting rate=$meltingReslut.rate price=$meltingReslut.price#
	#if $meltingReslut.mt == 32# 
		#include file="MeltingOutput.st" sysItem=$meltingReslut.meltingAward.item unitType=$meltingReslut.meltingAward.unitType unit=$meltingReslut.meltingAward.unit isVip=0#
	#/if#
#/if#