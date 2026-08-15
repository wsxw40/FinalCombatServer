team = {
	#$team.id#,"#$team.name#",#$num#,
	member = {
   		#foreach from=$team.memberList item=member#
     		{
			#$member.playerId# ,#$member.job#, #$member.rank#, "#$member.nickName#", #$member.online#,#$member.score#,
			#$member.killNum#,#$member.deadNum#,#$member.assist#, #$member.killNum-$member.deadNum#,#$member.exp#,
			#$member.isVip#, #$member.card#,#$member.internetCafe#,
		},
   		#/foreach#
   	},
}