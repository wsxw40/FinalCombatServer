page = #$proto.page#
pages = #$proto.pages#
player_rank = #$proto.playerRank#
items= {
	#foreach from = $proto.itemsList item=sysItem#
		{
		#include file="SysItem.st" sysItem=$sysItem#
		},
	#/foreach#
}
