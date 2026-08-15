<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initPlayerItemController($scope){
	initTableRowForm($scope,'#formPlayerItem', $('#app_root').val()+'/fcw/#/playerItems');
}
</script>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formPlayerItem" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/savePlayerItem">
			<input type="hidden" name="u"/>
			<input class="hidden" id="playerId" ng-model="playerId" name="playerId"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.playerItems.col.id.toolTips"/>" />
				</div>
				<label for="itemId" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.sysItemId.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.sysItemId.toolTips"/>">
					<input type="number" min="1" class="form-control" id="itemId" ng-model="itemId"  name="itemId" readonly
					placeholder="<fmt:message key="web.gm.playerItems.col.sysItemId.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="itemDisplayName" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.displayName.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.displayName.toolTips"/>">
					<input class="form-control" id="itemDisplayName" ng-model="itemDisplayName"  name="itemDisplayName" readonly
					placeholder="<fmt:message key="web.gm.playerItems.col.displayName.toolTips"/>" />
				</div>
				<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.level.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.level.toolTips"/>">
					<input type="number" class="form-control" id="level" ng-model="level"  name="level"
					placeholder="<fmt:message key="web.gm.playerItems.col.level.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="gunProperty1" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty1.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty1.toolTips"/>">
					<input class="form-control" id="gunProperty1" ng-model="gunProperty1"  name="gunProperty1" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty1.toolTips"/>" />
				</div>
				<label for="gunProperty2" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty2.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty2.toolTips"/>">
					<input class="form-control" id="gunProperty2" ng-model="gunProperty2"  name="gunProperty2" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty2.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="gunProperty3" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty3.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty3.toolTips"/>">
					<input class="form-control" id="gunProperty3" ng-model="gunProperty3"  name="gunProperty3" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty3.toolTips"/>" />
				</div>
				<label for="gunProperty4" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty4.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty4.toolTips"/>">
					<input class="form-control" id="gunProperty4" ng-model="gunProperty4"  name="gunProperty4" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty4.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="gunProperty5" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty5.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty5.toolTips"/>">
					<input class="form-control" id="gunProperty5" ng-model="gunProperty5"  name="gunProperty5" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty5.toolTips"/>" />
				</div>
				<label for="gunProperty6" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty6.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty6.toolTips"/>">
					<input class="form-control" id="gunProperty6" ng-model="gunProperty6"  name="gunProperty6" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty6.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="gunProperty7" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty7.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty7.toolTips"/>">
					<input class="form-control" id="gunProperty7" ng-model="gunProperty7"  name="gunProperty7" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty7.toolTips"/>" />
				</div>
				<label for="gunProperty8" class="col-sm-2 control-label"><fmt:message key="web.gm.playerItems.col.gunProperty8.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.playerItems.col.gunProperty8.toolTips"/>">
					<input class="form-control" id="gunProperty8" ng-model="gunProperty8"  name="gunProperty8" pattern=".{0,45}"
					placeholder="<fmt:message key="web.gm.playerItems.col.gunProperty8.toolTips"/>" />
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