friendlist = {
	#foreach from=$friends item=friend#
		{
			#$friend.playerId#,
			#$friend.friendId#,
			#$friend.type#,
		},
	#/foreach#
}
grouplist = {
	#foreach from=$groupList item=group#
		{
			#$group.playerId#,
			#$group.friendId#,
			#$group.type#,
		},
	#/foreach#
}