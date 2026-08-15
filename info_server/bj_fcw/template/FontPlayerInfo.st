id = #$player.id#
newGP =#$player.gPoint#
newCR = #$playerInfo.xunleiPoint#
newTicket=#$player.voucher#
name = "#$player.name#"
level = #$player.rank#
exp = #$player.exp#
newTeamReq=#$teamRequestNum#
newEmailNum=#$emailNum#
isvip=#$player.isVip#
vipExp=#$player.vipExp#
vipLeftMins=#$vipLeftMins#
leftMinites=#$player.leftMinites#
internetCafe=#$player.internetCafe#
card=#$card#
head_image="#$player.icon#"
title ="#$player.title#"
top=#$top#
winRate=#$winRate#
maxFightNum=#$maxFightNum#
team="#$team#"
maxLevel=#$maxLevel#
currentyEXPItem=#$currentyEXPItem#
buff_list =
{
	#foreach from=$buffList item=buff#
		{"#$buff.sysItem.name#","#$buff.sysItem.displayName#","#$buff.sysItem.description#",#$buff.timeLeft#,#$buff.sysItem.iBuffId#,},
	#/foreach#
}
#if ($erroMsg!=null)# error = "#$erroMsg#" #/if#

