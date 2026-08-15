#if ($type == 1)#	
	head={
		totalPage = #$totalPage#,
		current="#$player.icon#",
		current1="#$player.achievement#",
		current2="#$player.title#",
		data_list={
			#foreach from=$iconList item=icon#
			"#$icon.icon#",
			#$icon.level#,
			#/foreach#
		},
	}
#/if#
#if($type == 2)#
	title={
		totalPage = #$totalPage#,
		current="#$player.title#",
		data_list={
			#foreach from=$achievementList item=t#
				#if ($t.achievement.title != null && $t.achievement.title != "")#
				{
					"#$t.achievement.title#","#$t.status#",
				},
				#/if#
			#/foreach#
		},
	}
#/if#
#if($type == 3)#
	achievement={
		totalPage = #$totalPage#,
		current="#$player.achievement#",
		general_list={
			#foreach from=$achievementList item=a#
				#if $a.achievement.type==1# 
				{#$a.achievement.id#,"#$a.achievement.description#",#$a.status#,"#$a.achievement.name#",}, 
				#/if#			
			#/foreach#
		},
	}
#/if#
#if($type == 4)#
	achievement={
		totalPage = #$totalPage#,
		current="#$player.achievement#",
		character_list={
			#foreach from=$achievementList item=a#
				#if $a.achievement.type==3# 
				{#$a.achievement.id#,"#$a.achievement.description#",#$a.status#,"#$a.achievement.name#",}, 
				#/if#			
			#/foreach#
		},
	}
#/if#