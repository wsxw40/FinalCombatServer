<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formSysItemPrice" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysItemPrice">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.servers.col.id.toolTips"/>" />
				</div>
			</div>
			
<div class="form-group">
	<label for="sysItemId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.sysItemId.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.sysItemId.toolTips"/>">
		<input class="form-control" id="sysItemId" ng-model="sysItemId"  name="sysItemId"placeholder="<fmt:message key="web.gm.sysItemPrices.col.sysItemId.toolTips"/>"  type="number"/>
	</div>
	<label for="payType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.payType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.payType.toolTips"/>">
		<input class="form-control" id="payType" ng-model="payType"  name="payType"placeholder="<fmt:message key="web.gm.sysItemPrices.col.payType.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="unitType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.unitType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.unitType.toolTips"/>">
		<input class="form-control" id="unitType" ng-model="unitType"  name="unitType"placeholder="<fmt:message key="web.gm.sysItemPrices.col.unitType.toolTips"/>"  type="number"/>
	</div>
	<label for="cost" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.cost.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.cost.toolTips"/>">
		<input class="form-control" id="cost" ng-model="cost"  name="cost"placeholder="<fmt:message key="web.gm.sysItemPrices.col.cost.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="unit" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.unit.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.unit.toolTips"/>">
		<input class="form-control" id="unit" ng-model="unit"  name="unit"placeholder="<fmt:message key="web.gm.sysItemPrices.col.unit.toolTips"/>"  type="number"/>
	</div>
	<label for="isTarget" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.isTarget.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.isTarget.toolTips"/>">
		<input class="form-control" id="isTarget" ng-model="isTarget"  name="isTarget"placeholder="<fmt:message key="web.gm.sysItemPrices.col.isTarget.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="payGroup" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.payGroup.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.payGroup.toolTips"/>">
		<input class="form-control" id="payGroup" ng-model="payGroup"  name="payGroup"placeholder="<fmt:message key="web.gm.sysItemPrices.col.payGroup.toolTips"/>"  type="number"/>
	</div>
	<label for="multiType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.multiType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.multiType.toolTips"/>">
		<input class="form-control" id="multiType" ng-model="multiType"  name="multiType"placeholder="<fmt:message key="web.gm.sysItemPrices.col.multiType.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.sysItemPrices.col.level.toolTips"/>"  type="number"/>
	</div>
	<label for="vipLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.vipLevel.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.vipLevel.toolTips"/>">
		<input class="form-control" id="vipLevel" ng-model="vipLevel"  name="vipLevel"placeholder="<fmt:message key="web.gm.sysItemPrices.col.vipLevel.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="weightRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.weightRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.weightRate.toolTips"/>">
		<input class="form-control" id="weightRate" ng-model="weightRate"  name="weightRate"placeholder="<fmt:message key="web.gm.sysItemPrices.col.weightRate.toolTips"/>"  type="number"/>
	</div>
	<label for="probability" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItemPrices.col.probability.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysItemPrices.col.probability.toolTips"/>">
		<input class="form-control" id="probability" ng-model="probability"  name="probability"placeholder="<fmt:message key="web.gm.sysItemPrices.col.probability.toolTips"/>"  type="number" step="0.0001"/>
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