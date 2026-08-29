list = {
#foreach from=$list item=p#
	#if $p != null#
	{
		#$p.id#,
		#$p.rank#,
		"#$p.name#",
		"#$p.team#",
		#$p.minutesSinceLastLogin#,
		#$p.online#,
	},
	#/if#
#/foreach#
}