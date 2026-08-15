{cmd = "achievement_completed",
	list = 
	{
		#foreach from=$list item=achievement#
			{
				#$achievement.achievement.id#,"#$achievement.achievement.name#","#$achievement.achievement.description#",#$achievement.achievement.type#,#$achievement.status#,#$achievement.number#,#$achievement.achievement.number#,
				"medal",
				#if $time>=300#
					0,
				#elseif ($achievement.achievement.color<=2)&&($time>=180)#
					0,
				#elseif ($achievement.achievement.color>2) &&($time>=180)#
					1,
				#elseif $achievement.achievement.color>2#
					2,
				#else#
					1,
				#/if#
			},
		#/foreach#
		
	},
}
