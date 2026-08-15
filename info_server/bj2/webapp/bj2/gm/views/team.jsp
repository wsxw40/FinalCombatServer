<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initTeamController($scope){
}
</script>
<div class="box box-primary">
	<div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formTeam" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveTeam">
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.players.col.id.toolTips"/>"/>
				</div>
			</div>

<div class="form-group">
	<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.name.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.name.toolTips"/>">
		<input class="form-control" id="name" ng-model="name"  name="name"placeholder="<fmt:message key="web.gm.teams.col.name.toolTips"/>" />
	</div>
	<label for="declaration" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.declaration.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.declaration.toolTips"/>">
		<input class="form-control" id="declaration" ng-model="declaration"  name="declaration"placeholder="<fmt:message key="web.gm.teams.col.declaration.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="description" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.description.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.description.toolTips"/>">
		<input class="form-control" id="description" ng-model="description"  name="description"placeholder="<fmt:message key="web.gm.teams.col.description.toolTips"/>" />
	</div>
	<label for="board" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.board.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.board.toolTips"/>">
		<input class="form-control" id="board" ng-model="board"  name="board"placeholder="<fmt:message key="web.gm.teams.col.board.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="logo" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.logo.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.logo.toolTips"/>">
		<input class="form-control" id="logo" ng-model="logo"  name="logo"placeholder="<fmt:message key="web.gm.teams.col.logo.toolTips"/>" />
	</div>
	<label for="size" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.size.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.size.toolTips"/>">
		<input class="form-control" id="size" ng-model="size"  name="size"placeholder="<fmt:message key="web.gm.teams.col.size.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="kill" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.kill.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.kill.toolTips"/>">
		<input class="form-control" id="kill" ng-model="kill"  name="kill"placeholder="<fmt:message key="web.gm.teams.col.kill.toolTips"/>"  type="number"/>
	</div>
	<label for="headShot" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.headShot.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.headShot.toolTips"/>">
		<input class="form-control" id="headShot" ng-model="headShot"  name="headShot"placeholder="<fmt:message key="web.gm.teams.col.headShot.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="gameWin" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.gameWin.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.gameWin.toolTips"/>">
		<input class="form-control" id="gameWin" ng-model="gameWin"  name="gameWin"placeholder="<fmt:message key="web.gm.teams.col.gameWin.toolTips"/>"  type="number"/>
	</div>
	<label for="gameTotal" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.gameTotal.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.gameTotal.toolTips"/>">
		<input class="form-control" id="gameTotal" ng-model="gameTotal"  name="gameTotal"placeholder="<fmt:message key="web.gm.teams.col.gameTotal.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="challengeWin" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.challengeWin.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.challengeWin.toolTips"/>">
		<input class="form-control" id="challengeWin" ng-model="challengeWin"  name="challengeWin"placeholder="<fmt:message key="web.gm.teams.col.challengeWin.toolTips"/>"  type="number"/>
	</div>
	<label for="challengeTotal" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.challengeTotal.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.challengeTotal.toolTips"/>">
		<input class="form-control" id="challengeTotal" ng-model="challengeTotal"  name="challengeTotal"placeholder="<fmt:message key="web.gm.teams.col.challengeTotal.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="score" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.score.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.score.toolTips"/>">
		<input class="form-control" id="score" ng-model="score"  name="score"placeholder="<fmt:message key="web.gm.teams.col.score.toolTips"/>"  type="number"/>
	</div>
	<label for="hitScore" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.hitScore.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.hitScore.toolTips"/>">
		<input class="form-control" id="hitScore" ng-model="hitScore"  name="hitScore"placeholder="<fmt:message key="web.gm.teams.col.hitScore.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="province" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.province.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.province.toolTips"/>">
		<input class="form-control" id="province" ng-model="province"  name="province"placeholder="<fmt:message key="web.gm.teams.col.province.toolTips"/>" />
	</div>
	<label for="city" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.city.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.city.toolTips"/>">
		<input class="form-control" id="city" ng-model="city"  name="city"placeholder="<fmt:message key="web.gm.teams.col.city.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="rank" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.rank.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.rank.toolTips"/>">
		<input class="form-control" id="rank" ng-model="rank"  name="rank"placeholder="<fmt:message key="web.gm.teams.col.rank.toolTips"/>"  type="number"/>
	</div>
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.teams.col.level.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="exp" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.exp.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.exp.toolTips"/>">
		<input class="form-control" id="exp" ng-model="exp"  name="exp"placeholder="<fmt:message key="web.gm.teams.col.exp.toolTips"/>"  type="number"/>
	</div>
	<label for="recoreRankingFormer" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.recoreRankingFormer.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.recoreRankingFormer.toolTips"/>">
		<input class="form-control" id="recoreRankingFormer" ng-model="recoreRankingFormer"  name="recoreRankingFormer"placeholder="<fmt:message key="web.gm.teams.col.recoreRankingFormer.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="recoreRankingCurr" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.recoreRankingCurr.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.recoreRankingCurr.toolTips"/>">
		<input class="form-control" id="recoreRankingCurr" ng-model="recoreRankingCurr"  name="recoreRankingCurr"placeholder="<fmt:message key="web.gm.teams.col.recoreRankingCurr.toolTips"/>"  type="number"/>
	</div>
	<label for="fightRankingFormer" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.fightRankingFormer.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.fightRankingFormer.toolTips"/>">
		<input class="form-control" id="fightRankingFormer" ng-model="fightRankingFormer"  name="fightRankingFormer"placeholder="<fmt:message key="web.gm.teams.col.fightRankingFormer.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="fightRankingCurr" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.fightRankingCurr.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.fightRankingCurr.toolTips"/>">
		<input class="form-control" id="fightRankingCurr" ng-model="fightRankingCurr"  name="fightRankingCurr"placeholder="<fmt:message key="web.gm.teams.col.fightRankingCurr.toolTips"/>"  type="number"/>
	</div>
	<label for="preweekResAmount" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.preweekResAmount.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.preweekResAmount.toolTips"/>">
		<input class="form-control" id="preweekResAmount" ng-model="preweekResAmount"  name="preweekResAmount"placeholder="<fmt:message key="web.gm.teams.col.preweekResAmount.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="teamSpaceLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.teamSpaceLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.teamSpaceLevel.toolTips"/>">
		<input class="form-control" id="teamSpaceLevel" ng-model="teamSpaceLevel"  name="teamSpaceLevel"placeholder="<fmt:message key="web.gm.teams.col.teamSpaceLevel.toolTips"/>"  type="number"/>
	</div>
	<label for="unusableResource" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.unusableResource.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.unusableResource.toolTips"/>">
		<input class="form-control" id="unusableResource" ng-model="unusableResource"  name="unusableResource"placeholder="<fmt:message key="web.gm.teams.col.unusableResource.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="usableResource" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.usableResource.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.usableResource.toolTips"/>">
		<input class="form-control" id="usableResource" ng-model="usableResource"  name="usableResource"placeholder="<fmt:message key="web.gm.teams.col.usableResource.toolTips"/>"  type="number"/>
	</div>
	<label for="teamSpaceActive" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.teamSpaceActive.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.teamSpaceActive.toolTips"/>">
		<input class="form-control" id="teamSpaceActive" ng-model="teamSpaceActive"  name="teamSpaceActive"placeholder="<fmt:message key="web.gm.teams.col.teamSpaceActive.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="predayResAmount" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.predayResAmount.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.predayResAmount.toolTips"/>">
		<input class="form-control" id="predayResAmount" ng-model="predayResAmount"  name="predayResAmount"placeholder="<fmt:message key="web.gm.teams.col.predayResAmount.toolTips"/>"  type="number"/>
	</div>
	<label for="curWeekRobCount" class="col-sm-2 control-label"><fmt:message key="web.gm.teams.col.curWeekRobCount.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.teams.col.curWeekRobCount.toolTips"/>">
		<input class="form-control" id="curWeekRobCount" ng-model="curWeekRobCount"  name="curWeekRobCount"placeholder="<fmt:message key="web.gm.teams.col.curWeekRobCount.toolTips"/>"  type="number"/>
	</div>
</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submit" class="btn btn-primary"><fmt:message key="web.gm.button.submit"/></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" id="cancel" class="btn btn-default"><fmt:message key="web.gm.button.cancel"/></button>
				</div>
			</div>
		</form>
	</div>
</div>