characters = {
#foreach from=$characterList item=character#
	{
	id=#$character.id#,
	gender="#$character.gender#",
	cost=#$character.cost#,
	team0={#foreach from=$character.costumeT item=resourceT# #if($resourceT!="")#"#$resourceT#",#else#"", #/if# #/foreach#},
	team1={#foreach from=$character.costumeP item=resourceP# #if($resourceP!="")#"#$resourceP#",#else#"",#/if# #/foreach#}, 
	},
#/foreach#
}