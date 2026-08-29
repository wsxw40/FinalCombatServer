result = #$proto.result#
items= {
	#foreach from=$proto.itemsList item=playerItem#
		{
		#include file="PlayerItem.st" playerItem=$playerItem#		
		},
	#/foreach#
}
items2= {
	#foreach from=$proto.items2List item=playerItem#
		{
		#include file="PlayerItem.st" playerItem=$playerItem#		
		},
	#/foreach#
}
failAwards={
	#foreach from=$proto.failAwardsList item=sysItem#
		{
		#include file="SysItem.st" playerItem=$sysItem#		
		},
	#/foreach#
}