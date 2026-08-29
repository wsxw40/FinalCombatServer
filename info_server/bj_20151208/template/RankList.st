rank = {
#foreach from=$rankList item=rank#
	[#$rank.id#]={"#$rank.title#",#$rank.exp#},
#/foreach#
}