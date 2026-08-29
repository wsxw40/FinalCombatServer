t_logo="#$t_logo#"
t_teamName="#$t_teamName#" 
t_teamLeader="#$t_teamLeader#" 
t_teamLevel=#$t_teamLevel#  
t_exp=#$t_exp# 
t_totalExp=#$t_totalExp#
t_teamFightNum=#$t_teamFightNum#
t_recoreRankingCurr=#$t_recoreRankingCurr# 
t_resourceRank=#$t_resourceRank# 
t_teamSpaceLevel=#$t_teamSpaceLevel#
t_transitionResource=#$t_transitionResource#
t_addTransition=#$t_addTransition#
t_unusableResource=#$t_unusableResource#
t_unusableResourceMAX=#$t_unusableResourceMAX#
t_usableResource=#$t_usableResource#
t_usableResourceMAX=#$t_usableResourceMAX#
t_teamMemberAcount=#$t_teamMemberAcount#
t_teamSpaceActive=#$t_teamSpaceActive#
p_Id=#$p_Id#
p_TransitionResource=#$p_TransitionResource#
p_AddTransition=#$p_AddTransition#
p_UnusableResource=#$p_UnusableResource#
p_UnusableResourceMAX=#$p_UnusableResourceMAX#
p_UsableResource=#$p_UsableResource#	
p_UsableResourceMAX=#$p_UsableResourceMAX#
challenge=#$challenge#
challengeSDate="#$challengeSDate#"
challengeEDate="#$challengeEDate#"

#if $preZYZDZRankList!=null# 
preZYZDZRankList = 
{
#foreach from=$preZYZDZRankList item=topTeam#
	{
		#$topTeam.team.id#,
		"#$topTeam.team.name#",
		#$topTeam.team.teamSpaceLevel#,
		#$topTeam.ableBeChallenge#,
		#$topTeam.leftTime#,    
		#$topTeam.challengeHillStatus.stones#,
		#$topTeam.challengeHillStatus.canBeRob#,
		#$topTeam.challengeCost#,
		#$topTeam.leaderMaxReturn#,
		#$topTeam.memberMaxDisReturn#,
	},
#/foreach#
}
#/if#




SHOP_PersonalTransform={
	type=#$SHOP_PersonalTransform.type#,
	costs={
		#foreach from=$SHOP_PersonalTransform.costs item=cost#
			{
				#$cost[0]#,
				#$cost[1]#,
				#$cost[2]#,
				#$cost[3]#
			},
		#/foreach#
	}
}


SHOP_TeamTransofrm={
	type=#$SHOP_TeamTransofrm.type#,
	costs={
		#foreach from=$SHOP_TeamTransofrm.costs item=cost#
			{
				#$cost[0]#,
				#$cost[1]#,
				#$cost[2]#,
				#$cost[3]#
			},
		#/foreach#
	}
}


SHOP_TeamBuy={
	type=#$SHOP_TeamBuy.type#,
	costs={
		#foreach from=$SHOP_TeamBuy.costs item=cost#
			{
				#$cost[0]#,
				#$cost[1]#,
				#$cost[2]#,
				#$cost[3]#
			},
		#/foreach#
	}
}