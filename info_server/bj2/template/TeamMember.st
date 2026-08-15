page = #$page#
pages = #$pages#
team = {
	#$team.id#,
	member = {
   		#foreach from=$team.memberList item=member#
     		{	
			#$member.playerId# ,#$member.job#, #$member.rank#, "#$member.nickName#", 
     			#$member.online#,#$member.score#,#$member.killNum#,#$member.deadNum#,
     			#$member.assist#, 
     			#$member.killNum-$member.deadNum#,
     			#$member.exp#,
     			#$member.isVip#,
     			#$member.contribution#,#$member.times#,"#$member.creatDate#",#$member.isNew#,#$member.fight#,"#$member.player.icon#"
     		},
   		#/foreach#
   	},
}