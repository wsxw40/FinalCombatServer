characters = 
{
#foreach from=$friends item=friend#
	{
		#$friend.friendId#,
		"#$friend.name#",
		#$friend.rank#,
		"#$friend.team#",
			#if $friend.channel !=null# #$friend.room#,#/if#
		#if $friend.channel !=null# "#$friend.channel#",	#/if#
		#if $friend.server !=null# "#$friend.server#",	#/if#		
	},
#/foreach#
}