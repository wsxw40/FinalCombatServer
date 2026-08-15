achievement={
	pages = #$pages#,
	size = #$size#,
	complete = #$complete#,
	list={
		#foreach from=$list item=a#
		{
			#$a.achievement.id#,
			"#$a.achievement.name#",
			"#$a.achievement.description#",
			"#$a.achievement.title#",
			#$a.status#,
			#$a.number#,
			#$a.achievement.number#,
		},
		#/foreach#
	},
}
