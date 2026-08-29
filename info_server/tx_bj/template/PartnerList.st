partnerslist = 
{
#foreach from=$friendPartners item=p#
	{
		#$p.id#,
		#$p.rank#,
		"#$p.name#",
		"#$p.team#",
		#$p.online#,
		#$p.isVip#,
		"#$p.icon#",
		#$p.internetCafe#,
	},
#/foreach#
}