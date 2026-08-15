rid = #$stageClear.rid#
type = #$stageClear.type#
winner = #$stageClear.winner#
terrorist_score = #$stageClear.terroristScore#
police_score = #$stageClear.policeScore#
#if $stageClear.type==5#
	team0={
		#foreach from=$stageClear.stageClearPlayerInfo item=playerInfo#
		{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, 
			#$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			icon="#$playerInfo.headGif#",
			box={
				#foreach from=$playerInfo.stageClearBoxList item=box#
				{
					#$box.type#,
					#$box.num#,
					item={
						#if $box.sysItem !=null#
							"#$box.sysItem.name#","#$box.sysItem.displayName#",#$box.sysItem.gunProperty.color#,
						#/if#
					},
				
				},
				#/foreach#
			},
			buff_list =			{
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
		},
		#/foreach#
	}
	team1={}
#else#
	team0={
		#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#
		{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, 
			#$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			icon="#$playerInfo.headGif#",
			box={
				#foreach from=$playerInfo.stageClearBoxList item=box#
				{
					#$box.type#,
					#$box.num#,
					item={
						#if $box.sysItem !=null#
							"#$box.sysItem.name#","#$box.sysItem.displayName#",#$box.sysItem.gunProperty.color#,
						#/if#
					},
				},
				#/foreach#
			},
			buff_list ={
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
		},
		#/foreach#
	}
	team1={
		#foreach from=$stageClear.stageClearPlayerInfoTeam1 item=playerInfo#
		{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, 
			#$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			icon="#$playerInfo.headGif#",
			box={
				#foreach from=$playerInfo.stageClearBoxList item=box#
				{
					#$box.type#,
					#$box.num#,
					item={
						#if $box.sysItem !=null#
							"#$box.sysItem.name#","#$box.sysItem.displayName#",#$box.sysItem.gunProperty.color#,
						#/if#
					},
				},
				#/foreach#
			},
			buff_list = {
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
		},
		#/foreach#
	}
#/if#
mvp ={"",1,"","","",1,-1,}	