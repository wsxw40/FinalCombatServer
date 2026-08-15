team = {#$team.id#,
   ally = {
   #foreach from=$team.teamList item=ally#
   		{#$ally.id#,"#$ally.name#", "#$ally.logo#", "#$ally.kill#","#$ally.headShot#","#$ally.gameWin#", "#$ally.challengeRatio#",},
   #/foreach#
   }	
}