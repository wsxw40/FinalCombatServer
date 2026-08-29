page_count = #$pages#
characters = 
{
#foreach from=$players item=player#
	{#$player.id#, "#$player.name#", #$player.rank#, "#$player.team#"},
#/foreach#
}
