page = #$proto.page#
pages = #$proto.pages#
player_rank = #$proto.playerRank#
currenttime = #$proto.currenttime#
maxDuration = #$proto.maxDuration#
items= {
	#foreach from = $proto.itemsList item=playerItem#
		{
		#include file="PlayerItem.st" playerItem=$playerItem#
		},
	#/foreach#
}