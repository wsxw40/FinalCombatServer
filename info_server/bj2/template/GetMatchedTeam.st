#if $outOfCount!=null#
	outOfCount="#$outOfCount#"
#/if# 	
#if $outOfStones!=null#
	outOfStones="#$outOfStones#"
#/if# 	

#if $outOfCount==null#
	#if $outOfStones==null#
		id="#$id#"
		name="#$name#"
		level=#$level#
		unusableResource=#$unusableResource#
		canRobUnuseRes=#$canRobUnuseRes#
		teamSpaceLevel=#$teamSpaceLevel#
		battleFieldId=#$battleFieldId#
		battleFieldConfig="#$battleFieldConfig#"
		formatedConfig="#$formatedConfig#"
		pDisResStone=#$pDisResStone#
	#/if# 
#/if# 	
