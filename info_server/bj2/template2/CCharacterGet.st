enable = #$proto.enable#
player={
	name= "#$proto.name#",
	matchRank=#$proto.matchRank#,
}
fightnum=#$proto.fightnum#
items=	{
	resourcename="#$proto.items.resourcename#",
	name="#$proto.items.name#",
	avatar={
		#foreach from=$proto.items.avatarList item=res# 
			#if ($res != null)#
				"#$res#",
			#/if#
		#/foreach#},
	putSuitId=#$proto.items.putSuitId#,
	suitCount=#$proto.items.suitCount#,
	weapons = {
		#foreach from=$proto.items.weaponsList item=playerItem#	
			{
			#include file="PlayerItem.st" playerItem=$playerItem#
			},
		#/foreach#
	},
	costume = {
		#foreach from=$proto.items.weaponsList item=playerItem#	
			{
			#include file="PlayerItem.st" playerItem=$playerItem#
			},
		#/foreach#
	},
}