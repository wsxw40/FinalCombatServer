info = 
{
	mateRank=#$mateRank#,
	nickName = "#$player.name#",
	rank	 = #$player.rank#,
	exp	 = #$player.exp#,
	isVip =#$player.isVip#,
	vipExp=#$player.vipExp#,
	leftMinites=#$player.leftMinites#,
	maxFightNum =#$player.maxFightNum#,
	internetCafe=#$player.internetCafe#,
	title ="#$player.title#",
	headIcon ="#$player.icon#",
	winRate=#$player.winRate#,
	runAwayRate=#$player.runAwayRate#,
	top=#$top#,
	gWin=#$player.gWin#,
	gLose=#$player.gLose#,
	gold=#$player.gold#,
	silver=#$player.silver#,
	bronze=#$player.bronze#,
	total=#$player.total#,
	achievement="#$player.achievement#",
	buff_list =
	{
		#foreach from=$buffList item=buff#
			{"#$buff.sysItem.name#","#$buff.sysItem.displayName#","#$buff.sysItem.description#",#$buff.timeLeft#,"#$buff.sysItem.unitType#",},
		#/foreach#
	},
}
newSimple={
	#foreach from=$news item=event#
		#$event.nosqlPlainStr#
	#/foreach#
}