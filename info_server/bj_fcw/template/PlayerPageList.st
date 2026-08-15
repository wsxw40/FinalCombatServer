page_count = #$pages#
list = 
{
#foreach from=$players item=player#
	{#$player.id#,#$player.rank#, "#$player.name#" , "#$player.playerTeam.name#",#$player.online#,#$player.isVip#,"#$player.icon#",},
#/foreach#
}
