page = #$proto.page#
pages = #$proto.pages#
chipNum=#$proto.chipNum#
reviceCoinNum=#$proto.reviceCoinNum#
sysItem ={
	#foreach from=$proto.sysItemList item=sysItem#
		{
		#include file="SysItem.st" sysItem=$sysItem#		
		},
	#/foreach#
}
sysItemAndQuantity={
	#foreach from=$proto.sysItemAndQuantityList item=item#
		{
		sysItemId = #$item.k# , 
		quantity = #$item.v# ,
		},
	#/foreach#
}