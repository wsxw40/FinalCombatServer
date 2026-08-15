page = #$proto.page#
pages = #$proto.pages#
player_rank = #$proto.playerRank#
items= {
	#foreach from=$proto.itemsList item=playerItem#
		{
		#include file="PlayerItem.st" playerItem=$playerItem#		
		},
	#/foreach#
}
maxStartExp = {
	#foreach from=$proto.gstExpList item=gstList#
		{
		#foreach from=$gstList.iList item=exp#
			#$exp#,
		#/foreach#
		},
	#/foreach#
}
everyDayExp={
	#foreach from=$proto.everydayGstExpInCombineList item=exp#
		#$exp#,
	#/foreach#
}
everyDayIsVisiable={
	#foreach from=$proto.everydayGstExpInCombineUseFlagList item=useFlag#
		#$useFlag#,
	#/foreach#
}
everyDayCPoint={
	#foreach from=$proto.everydayGpointInCombineList item=gPoint#
		#$gPoint#,
	#/foreach#
}