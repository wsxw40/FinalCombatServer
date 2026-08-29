myData = 
{
#foreach from=$list item=data#
	{
		characterId=#$data.characterId#,
		kill=#$data.kill#,
		dead=#$data.dead#,
		knifeKill=#$data.knifeKill#,
		controlNum=#$data.controlNum#,
		revengeNum=#$data.revengeNum#,
		assistNum=#$data.assistNum#,
		usedCount=#$data.usedCount#,
		maxHeadshot=#$data.maxHeadshot#,
		maxKill=#$data.maxKill#,
		maxHealth=#$data.maxHealth#,
		maxDamage=#$data.maxDamage#,
		maxAliveTime=#$data.maxAliveTime#,
		mvpNum=#$data.mvpNum#,
		id=#$data.id#,
		playerId=#$data.playerId#
	},
#/foreach#
}
