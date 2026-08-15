res=#$res#
outOfCost=#$outOfCost#

#if $levelChange == 1 #
	intensifyLevel = #$intensifyLevel#
#/if#
#if $isUp==1 #
	itemLevel = #$newTeamItem.level#
	resources = "#$newTeamItem.sysItem.name#"
	name = "#$newTeamItem.sysItem.displayName#"
#/if#

