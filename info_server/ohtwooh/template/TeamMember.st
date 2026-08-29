team = {#$team.id#,
member = {
   #foreach from=$team.memberList item=member#
     {#$member.playerId# ,#$member.job#, #$member.rank#, "#$member.nickName#", "Y", "N",},
   #/foreach#
   }
}