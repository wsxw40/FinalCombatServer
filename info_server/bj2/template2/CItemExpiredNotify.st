list={
	#foreach from=$proto.list item=item#
		{"#$item.displayName#",#$item.expireSecondsLeft#,},
	#/foreach#
}
list1={
	#foreach from=$proto.list1 item=item#
		{"#$item.displayName#",#$item.durable#,},
	#/foreach#
}