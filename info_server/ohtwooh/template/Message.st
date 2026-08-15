mail = {
#$message.id#,"#$message.sender#","#$message.subject#","#$message.content#","#$message.createdTimeStr#",
#if ($message.isAttached!='N') #
	attachment = {
		id=#$message.playerItemId#,
		name="#$message.playerItemName#"
	}#else# 
	attachment = nil #/if#
}