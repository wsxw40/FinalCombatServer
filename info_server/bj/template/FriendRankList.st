list={
	#foreach from=$list item=friend#
	{#$friend.friendId#,"#$friend.name#",#$friend.rank#,#$friend.gameRank#,},
	#/foreach#
}