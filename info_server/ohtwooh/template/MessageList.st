list = {
#foreach from=$messageList item=message#
	{#$message.id#,"#$message.subject#","#$message.createdDateStr#","#$message.sender#","#$message.isAttached#","#$message.open#"},
#/foreach#
}