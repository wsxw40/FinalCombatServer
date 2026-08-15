<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formChannel" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveChannel">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.servers.col.id.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="serverId" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.serverId.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.serverId.toolTips"/>">
		<input class="form-control" id="serverId" ng-model="serverId"  name="serverId"placeholder="<fmt:message key="web.gm.channels.col.serverId.toolTips"/>"  type="number"/>
	</div>
	<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.name.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.name.toolTips"/>">
		<input class="form-control" id="name" ng-model="name"  name="name"placeholder="<fmt:message key="web.gm.channels.col.name.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="maxLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.maxLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.maxLevel.toolTips"/>">
		<input class="form-control" id="maxLevel" ng-model="maxLevel"  name="maxLevel"placeholder="<fmt:message key="web.gm.channels.col.maxLevel.toolTips"/>"  type="number"/>
	</div>
	<label for="minLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.minLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.minLevel.toolTips"/>">
		<input class="form-control" id="minLevel" ng-model="minLevel"  name="minLevel"placeholder="<fmt:message key="web.gm.channels.col.minLevel.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="maxOnline" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.maxOnline.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.maxOnline.toolTips"/>">
		<input class="form-control" id="maxOnline" ng-model="maxOnline"  name="maxOnline"placeholder="<fmt:message key="web.gm.channels.col.maxOnline.toolTips"/>"  type="number"/>
	</div>
	<label for="channelId" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.channelId.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.channelId.toolTips"/>">
		<input class="form-control" id="channelId" ng-model="channelId"  name="channelId"placeholder="<fmt:message key="web.gm.channels.col.channelId.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isTcp" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.isTcp.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.isTcp.toolTips"/>">
		<input class="form-control" id="isTcp" ng-model="isTcp"  name="isTcp"placeholder="<fmt:message key="web.gm.channels.col.isTcp.toolTips"/>"  type="number"/>
	</div>
	<label for="maxTeamLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.maxTeamLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.maxTeamLevel.toolTips"/>">
		<input class="form-control" id="maxTeamLevel" ng-model="maxTeamLevel"  name="maxTeamLevel"placeholder="<fmt:message key="web.gm.channels.col.maxTeamLevel.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="minTeamLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.channels.col.minTeamLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.channels.col.minTeamLevel.toolTips"/>">
		<input class="form-control" id="minTeamLevel" ng-model="minTeamLevel"  name="minTeamLevel"placeholder="<fmt:message key="web.gm.channels.col.minTeamLevel.toolTips"/>"  type="number"/>
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