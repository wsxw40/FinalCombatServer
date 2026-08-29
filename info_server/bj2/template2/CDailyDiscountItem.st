theItem = {
	#foreach from = $proto.theItemList item=discount#
		{
		#include file="SysItem.st" sysItem=$discount.sysItem#
		},
	#/foreach#
}
flag = {
	#foreach from = $proto.theItemList item=discount#
		{
		#foreach from = $discount.flagList item=flag#
			#$flag#,
		#/foreach#
		},
	#/foreach#
}
discounts = {
	#foreach from = $proto.theItemList item=discount#
		{
		#foreach from = $discount.flagList item=ratio#
			#$ratio#,
		#/foreach#
		},
	#/foreach#
}
