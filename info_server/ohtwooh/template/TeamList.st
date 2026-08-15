page = #$page#
pages = #$pages#
team = {
#foreach from=$teamList item=team#
{#$team.id#,"#$team.name#", "#$team.logo#",  "#$team.kill#", "#$team.headShot#", "#$team.gameWin#", "#$team.challengeRatio#",},
#/foreach#
}