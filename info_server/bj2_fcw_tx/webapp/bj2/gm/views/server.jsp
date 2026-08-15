<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formServer" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveServer">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.servers.col.id.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.name.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.name.toolTips"/>">
		<input class="form-control" id="name" ng-model="name"  name="name"placeholder="<fmt:message key="web.gm.servers.col.name.toolTips"/>" />
	</div>
	<label for="maxLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.maxLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.maxLevel.toolTips"/>">
		<input class="form-control" id="maxLevel" ng-model="maxLevel"  name="maxLevel"placeholder="<fmt:message key="web.gm.servers.col.maxLevel.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="minLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.minLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.minLevel.toolTips"/>">
		<input class="form-control" id="minLevel" ng-model="minLevel"  name="minLevel"placeholder="<fmt:message key="web.gm.servers.col.minLevel.toolTips"/>"  type="number"/>
	</div>
	<label for="maxOnline" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.maxOnline.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.maxOnline.toolTips"/>">
		<input class="form-control" id="maxOnline" ng-model="maxOnline"  name="maxOnline"placeholder="<fmt:message key="web.gm.servers.col.maxOnline.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isNew" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.isNew.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.isNew.toolTips"/>">
		<input class="form-control" id="isNew" ng-model="isNew"  name="isNew"placeholder="<fmt:message key="web.gm.servers.col.isNew.toolTips"/>"  type="number"/>
	</div>
	<label for="minFightnum" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.minFightnum.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.minFightnum.toolTips"/>">
		<input class="form-control" id="minFightnum" ng-model="minFightnum"  name="minFightnum"placeholder="<fmt:message key="web.gm.servers.col.minFightnum.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="gameType" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.gameType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.gameType.toolTips"/>">
		<input class="form-control" id="gameType" ng-model="gameType"  name="gameType"placeholder="<fmt:message key="web.gm.servers.col.gameType.toolTips"/>" />
	</div>
	<label for="maxTeamLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.maxTeamLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.maxTeamLevel.toolTips"/>">
		<input class="form-control" id="maxTeamLevel" ng-model="maxTeamLevel"  name="maxTeamLevel"placeholder="<fmt:message key="web.gm.servers.col.maxTeamLevel.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="minTeamLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.minTeamLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.minTeamLevel.toolTips"/>">
		<input class="form-control" id="minTeamLevel" ng-model="minTeamLevel"  name="minTeamLevel"placeholder="<fmt:message key="web.gm.servers.col.minTeamLevel.toolTips"/>"  type="number"/>
	</div>
	<label for="serverType" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.serverType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.serverType.toolTips"/>">
		<input class="form-control" id="serverType" ng-model="serverType"  name="serverType"placeholder="<fmt:message key="web.gm.servers.col.serverType.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="index" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.index.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.index.toolTips"/>">
		<input class="form-control" id="index" ng-model="index"  name="index"placeholder="<fmt:message key="web.gm.servers.col.index.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="remark" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.remark.title"/></label>
	<div class="col-sm-10" title="<fmt:message key="web.gm.servers.col.remark.toolTips"/>">
		<textarea cols="3" class="form-control" id="remark" ng-model="remark"  name="remark"placeholder="<fmt:message key="web.gm.servers.col.remark.toolTips"/>" ></textarea>
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