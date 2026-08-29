list = {
	#foreach from=$messageList item=message#
	{
		#$message.id#,
		"#$message.subject#",
		"#$message.createdDateStr#",
		#if ($type==0) # 
			"#$message.sender#",
		#else# 
			"#$message.receiver#",
		#/if#
		"#$message.isAttached#",
		"#$message.open#",
		"#$message.haveAttached#"
	},
	#/foreach#
}