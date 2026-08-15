prices = {
	#foreach from=$proto.pricesList item=price#
		#$price#,
	#/foreach#
}
items= {
	#foreach from = $proto.itemsList item=sysItem#
		{
		#include file="SysItem.st" sysItem=$sysItem#
		},
	#/foreach#
}