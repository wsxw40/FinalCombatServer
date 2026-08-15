list={
	#foreach from=$list item=item#
		{"#$item.sysItem.displayName#",#$item.expireSecondsLeft#,},
	#/foreach#
}
list1={
	#foreach from=$durableList item=item#
		{"#$item.sysItem.displayName#",#$item.durable#,},
	#/foreach#
}