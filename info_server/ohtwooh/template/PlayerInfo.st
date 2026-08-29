info = 
{
	nickname = "#$player.name#",
	rank	 = #$player.rank#,
	exp	 = #$player.exp#,
	teams	 = "#$player.team#",
	flowers	 = #$player.flowerTotal#,
	
	general	 = {
				#$player.gRank#, 
				#$player.gScore#, 
				#$player.gHeadShot#, 
				#$player.gVictoryRate#, 
				#$player.gHitPoint#, 
				#$player.gKill#, 
				#$player.gKnifeKill#, 
				#$player.gDead#, 
				#$player.gGrenadeKill#
			   },
	team	 = {
				#$player.tRank#, 
				#$player.tScore#, 
				#$player.tHeadShot#, 
				#$player.tVictoryRate#, 
				#$player.tHitPoint#, 
				#$player.tKill#, 
				#$player.tKnifeKill#, 
				#$player.tDead#, 
				#$player.tGrenadeKill#
			   },
	flag	 = {
				#$player.flagRank#, 
				#$player.flagScore#, 
				#$player.flagVictoryRate#, 
				#$player.flagSucceed#, 
				#$player.flagReturn#
			   },
	bio		 = {
				#$player.bioRank#, 
				#$player.bioScore#, 
				#$player.bioInfect#, 
				#$player.bioKill#
			   },
	blast	 = {
				#$player.blastRank#, 
				#$player.blastScore#, 
				#$player.blastVictoryRate#, 
				#$player.blastSucceed#, 
				#$player.blastDisable#
			   },
	fun		 = {
				#$player.funGpRank#, 
				#$player.funGpTotal#, 
				#$player.funCrRank#, 
				#$player.funCrTotal#, 
				#$player.funGrenadeKillRank#,
				#$player.funGrenadeKill#,
				#$player.funDropKillRank#,
				#$player.funDropKill#,
				#$player.funWeaponModRank#,
				#$player.funWeaponMod#,
				#$player.funDeadRank#,
				#$player.funDead#
			   },
}

buff = 
{
	#foreach from=$player.buffList item=playerItem# {"#$playerItem.displayName#",#$playerItem.timeLeft#,}, #/foreach#
}
