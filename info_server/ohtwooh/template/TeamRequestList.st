requests = {
	 #foreach from=$list item=member#
     	{#$member.playerId#, "#$member.nickName#", #$member.createTime#,},
  	 #/foreach#
}