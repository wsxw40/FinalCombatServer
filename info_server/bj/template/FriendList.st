friendlist = 
{
#foreach from=$friends item=friend#
	{
		#$friend.friendId#,
		#$friend.rank#,
		"#$friend.name#",
		"",
		#$friend.online#,
		#$friend.isVip#,
		"#$friend.icon#",
		#$friend.internetCafe#,
		#$friend.card#,
		
	},
#/foreach#
}
blacklist = 
{
#foreach from=$blackList item=black#
	{
		#$black.friendId#,
		#$black.rank#,
		"#$black.name#",
		"",
		#$black.online#,
		#$black.isVip#,
		"#$black.icon#",
		#$black.internetCafe#,
		#$black.card#,
		
	},
#/foreach#
}
