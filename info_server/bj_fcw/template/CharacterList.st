characters = {
#foreach from=$characterList item=character#
	{
	id=#$character.id#,
	gender="#$character.gender#",
	cost=#$character.cost#,
	team0={#foreach from=$character.costumeT item=resourceT# #if($resourceT!="")#"#$resourceT#",#else#"", #/if# #/foreach#},
	team1={#foreach from=$character.costumeP item=resourceP# #if($resourceP!="")#"#$resourceP#",#else#"",#/if# #/foreach#}, 
	weapon_info ={type=3,"mk18/bh1", "mk18/rv1", "mk18/gs1", "mk18/si1", "mk18/tg1", "mk18/mz1", "mk18/rs1", "mk18/bl1", "mk18/fh1", },
	},
	
#/foreach#
}