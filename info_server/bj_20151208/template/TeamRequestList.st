page = #$page#
pages = #$pages#
requests = {
	 #foreach from=$list item=member#
     	{#$member.playerId#,#$member.player.isVip#,#$member.player.rank#,#$member.player.exp#, "#$member.nickName#", "#$member.creatDay#",#$member.player.maxFightNum#,#$member.player.winRate#},
  	 #/foreach#
}