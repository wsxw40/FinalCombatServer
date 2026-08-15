today = {
	win = #$win#,
	lose = #$lose#,
	kill = #$kill#,
	assist = #$assist#,
	dead = #$dead#,
	score = #$score#,
	exp = #$exp#,
	gp = #$gp#,
}
num = #$num#
mission = 
{
	#foreach from=$missions item=amission#
	{
		#$amission.id#, "#$amission.sysMission.name#", "#$amission.sysMission.title#", "#$amission.description#", #$amission.target#,#$amission.number#,#$amission.status#,#$amission.award#,#$amission.sysMission.type#,
		items = 
		{
			#if ($amission.sysMission.normalItemList != null)#
				#foreach from=$amission.sysMission.normalItemList item=normalItem#
					{0 , "#$normalItem.name#","#$normalItem.displayName#","#$normalItem.description#",#$normalItem.type#,#$normalItem.gunProperty.color#},
				#/foreach#
			#/if#
			#if ($amission.sysMission.vipItemList != null)#
				#foreach from=$amission.sysMission.vipItemList item=vipItem#
					{1 , "#$vipItem.name#","#$vipItem.displayName#","#$vipItem.description#",#$vipItem.type#,#$vipItem.gunProperty.color#},
				#/foreach#
			#/if#
		},
  	},
  	#/foreach#
}