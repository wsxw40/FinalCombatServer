page = 1
pages = 1
num=#$num# ,
mission = 
{
	#foreach from=$list item=activity#
	{
		#$activity.id#, "#$activity.sysActivity.path#", "", "#$activity.name#", #$activity.target#,#$activity.number#,#$activity.status#,#$activity.award#,2,#$activity.sysActivity.needAward#,
		items = 
		{
			#if ($activity.sysActivity.itemList != null)#
				#foreach from=$activity.sysActivity.itemList item=sysItem#
					{0 , "#$sysItem.name#","#$sysItem.displayName#","#$sysItem.description#",#$sysItem.type#},
				#/foreach#
			#/if#
		},
  	},
  	#/foreach#
}
