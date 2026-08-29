activity = {
	#foreach from=$list item=activity#
	{
		#$amission.id#, "#$activity.name#",  #$activity.targetNum#,#$amission.type#,
  	},
  	#/foreach#
}