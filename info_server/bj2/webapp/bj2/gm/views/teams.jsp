<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" >
function initTeamsController($scope){
	initTable('#tableTeams',
			$('#app_root').val()+'/fcw/gm/teams',
			[{data:'id'},
			 {data:'name'},
			 {data:'board'},
			 {data:'logo'},
			 {data:'size'},
			 {data:'kill'},
			 {data:'headShot'},
			 {data:'gameWin'},
			 {data:'gameTotal'},
			 {data:'challengeWin'},
			 {data:'challengeTotal'},
			 {data:'createdTime'},
			 {data:'deleted'},
			 {data:'score'},
			 {data:'hitScore'},
			 {data:'province'},
			 {data:'city'},
			 {data:'rank'},
			 {data:'level'},
			 {data:'exp'},
			 {data:'recoreRankingFormer'},
			 {data:'recoreRankingCurr'},
			 {data:'fightRankingFormer'},
			 {data:'fightRankingCurr'},
			 {data:'preweekResAmount'},
			 {data:'teamSpaceLevel'},
			 {data:'unusableResource'},
			 {data:'usableResource'},
			 {data:'teamSpaceActive'},
			 {data:'tResLastAddTime'},
			 {data:'predayResAmount'},
			 {data:'curWeekRobCount'}
			],
			{leftColumns:2},
			{edit:$('#app_root').val()+'/fcw/#/team',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WTeam'
			}
	);
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
		<button type="button" id="edit" class="btn btn-info" >
			<i class="fa fa-edit"></i>&nbsp;<fmt:message key="web.gm.button.edit"/>
		</button>
	</div>
	<div class="box-body">
		<table id="tableTeams" data-datatable="" data-dt-options="dtOptions" data-dt-columns="dtColumns" rowFormId="#formTeam"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.players.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.teams.col.name.toolTips" />" class="search large-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.teams.col.name.title" />
					</th>
<th property="board" title="<fmt:message key="web.gm.teams.col.board.toolTips" />">
	<fmt:message key="web.gm.teams.col.board.title" />
</th>
<th property="logo" title="<fmt:message key="web.gm.teams.col.logo.toolTips" />">
	<fmt:message key="web.gm.teams.col.logo.title" />
</th>
<th property="size" title="<fmt:message key="web.gm.teams.col.size.toolTips" />">
	<fmt:message key="web.gm.teams.col.size.title" />
</th>
<th property="kill" title="<fmt:message key="web.gm.teams.col.kill.toolTips" />">
	<fmt:message key="web.gm.teams.col.kill.title" />
</th>
<th property="headShot" title="<fmt:message key="web.gm.teams.col.headShot.toolTips" />">
	<fmt:message key="web.gm.teams.col.headShot.title" />
</th>
<th property="gameWin" title="<fmt:message key="web.gm.teams.col.gameWin.toolTips" />">
	<fmt:message key="web.gm.teams.col.gameWin.title" />
</th>
<th property="gameTotal" title="<fmt:message key="web.gm.teams.col.gameTotal.toolTips" />">
	<fmt:message key="web.gm.teams.col.gameTotal.title" />
</th>
<th property="challengeWin" title="<fmt:message key="web.gm.teams.col.challengeWin.toolTips" />">
	<fmt:message key="web.gm.teams.col.challengeWin.title" />
</th>
<th property="challengeTotal" title="<fmt:message key="web.gm.teams.col.challengeTotal.toolTips" />">
	<fmt:message key="web.gm.teams.col.challengeTotal.title" />
</th>
<th property="createdTime" title="<fmt:message key="web.gm.teams.col.createdTime.toolTips" />"class="search normal-width">
	<fmt:message key="web.gm.teams.col.createdTime.title" />
</th>
<th property="deleted" title="<fmt:message key="web.gm.teams.col.deleted.toolTips" />">
	<fmt:message key="web.gm.teams.col.deleted.title" />
</th>
<th property="score" title="<fmt:message key="web.gm.teams.col.score.toolTips" />">
	<fmt:message key="web.gm.teams.col.score.title" />
</th>
<th property="hitScore" title="<fmt:message key="web.gm.teams.col.hitScore.toolTips" />">
	<fmt:message key="web.gm.teams.col.hitScore.title" />
</th>
<th property="province" title="<fmt:message key="web.gm.teams.col.province.toolTips" />">
	<fmt:message key="web.gm.teams.col.province.title" />
</th>
<th property="city" title="<fmt:message key="web.gm.teams.col.city.toolTips" />">
	<fmt:message key="web.gm.teams.col.city.title" />
</th>
<th property="rank" title="<fmt:message key="web.gm.teams.col.rank.toolTips" />">
	<fmt:message key="web.gm.teams.col.rank.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.teams.col.level.toolTips" />">
	<fmt:message key="web.gm.teams.col.level.title" />
</th>
<th property="exp" title="<fmt:message key="web.gm.teams.col.exp.toolTips" />">
	<fmt:message key="web.gm.teams.col.exp.title" />
</th>
<th property="recoreRankingFormer" title="<fmt:message key="web.gm.teams.col.recoreRankingFormer.toolTips" />">
	<fmt:message key="web.gm.teams.col.recoreRankingFormer.title" />
</th>
<th property="recoreRankingCurr" title="<fmt:message key="web.gm.teams.col.recoreRankingCurr.toolTips" />">
	<fmt:message key="web.gm.teams.col.recoreRankingCurr.title" />
</th>
<th property="fightRankingFormer" title="<fmt:message key="web.gm.teams.col.fightRankingFormer.toolTips" />">
	<fmt:message key="web.gm.teams.col.fightRankingFormer.title" />
</th>
<th property="fightRankingCurr" title="<fmt:message key="web.gm.teams.col.fightRankingCurr.toolTips" />">
	<fmt:message key="web.gm.teams.col.fightRankingCurr.title" />
</th>
<th property="preweekResAmount" title="<fmt:message key="web.gm.teams.col.preweekResAmount.toolTips" />">
	<fmt:message key="web.gm.teams.col.preweekResAmount.title" />
</th>
<th property="teamSpaceLevel" title="<fmt:message key="web.gm.teams.col.teamSpaceLevel.toolTips" />">
	<fmt:message key="web.gm.teams.col.teamSpaceLevel.title" />
</th>
<th property="unusableResource" title="<fmt:message key="web.gm.teams.col.unusableResource.toolTips" />">
	<fmt:message key="web.gm.teams.col.unusableResource.title" />
</th>
<th property="usableResource" title="<fmt:message key="web.gm.teams.col.usableResource.toolTips" />">
	<fmt:message key="web.gm.teams.col.usableResource.title" />
</th>
<th property="teamSpaceActive" title="<fmt:message key="web.gm.teams.col.teamSpaceActive.toolTips" />">
	<fmt:message key="web.gm.teams.col.teamSpaceActive.title" />
</th>
<th property="lastTeamPlaceLevelUpTime" title="<fmt:message key="web.gm.teams.col.lastTeamPlaceLevelUpTime.toolTips" />">
	<fmt:message key="web.gm.teams.col.lastTeamPlaceLevelUpTime.title" />
</th>
<th property="predayResAmount" title="<fmt:message key="web.gm.teams.col.predayResAmount.toolTips" />">
	<fmt:message key="web.gm.teams.col.predayResAmount.title" />
</th>
<th property="curWeekRobCount" title="<fmt:message key="web.gm.teams.col.curWeekRobCount.toolTips" />">
	<fmt:message key="web.gm.teams.col.curWeekRobCount.title" />
</th>
				</tr>
			</thead>
		</table>
	</div>
</div>