myGroup = {
	#foreach from=$myGroup item=friend#
	{
		#$friend.friendId#,
		#$friend.rank#,
		"#$friend.name#",
		"",
		"#$friend.groupName#",
		#$friend.online#,
		#$friend.isVip#,
		#$friend.card#,
		#$friend.internetCafe#,
	},
	#/foreach#
}
addGroups = {
	#foreach from=$addGroups item=group#
	{
		#foreach from=$group item=p#
		{
			#$p.friendId#,
			#$p.rank#,
			"#$p.name#",
			"",
			"#$p.groupName#",
			#$p.online#,
			#$p.isVip#,
			#$p.card#,
			#$p.internetCafe#,
		},
		#/foreach#
	},
	#/foreach#
}