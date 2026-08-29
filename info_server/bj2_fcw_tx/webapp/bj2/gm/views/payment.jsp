<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formServer" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/savePayment">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.servers.col.id.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="itemId" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.itemId.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.itemId.toolTips"/>">
		<input class="form-control" id="itemId" ng-model="itemId"  name="itemId"placeholder="<fmt:message key="web.gm.payments.col.itemId.toolTips"/>"  type="number" required min="1"/>
	</div>
	<label for="payType" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.payType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.payType.toolTips"/>">
		<input class="form-control" id="payType" ng-model="payType"  name="payType"placeholder="<fmt:message key="web.gm.payments.col.payType.toolTips"/>"  type="number" required min="1"/>
	</div>
</div>
<div class="form-group">
	<label for="unitType" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.unitType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.unitType.toolTips"/>">
		<input class="form-control" id="unitType" ng-model="unitType"  name="unitType"placeholder="<fmt:message key="web.gm.payments.col.unitType.toolTips"/>"  type="number"/>
	</div>
	<label for="cost" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.cost.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.cost.toolTips"/>">
		<input class="form-control" id="cost" ng-model="cost"  name="cost"placeholder="<fmt:message key="web.gm.payments.col.cost.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="unit" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.unit.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.unit.toolTips"/>">
		<input class="form-control" id="unit" ng-model="unit"  name="unit"placeholder="<fmt:message key="web.gm.payments.col.unit.toolTips"/>"  type="number"/>
	</div>
	<label for="isShow" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.isShow.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.isShow.toolTips"/>">
		<input class="form-control" id="isShow" ng-model="isShow"  name="isShow"placeholder="<fmt:message key="web.gm.payments.col.isShow.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.payments.col.level.toolTips"/>"  type="number"/>
	</div>
	<label for="finishPayType" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.finishPayType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.finishPayType.toolTips"/>">
		<input class="form-control" id="finishPayType" ng-model="finishPayType"  name="finishPayType"placeholder="<fmt:message key="web.gm.payments.col.finishPayType.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="finishCost" class="col-sm-2 control-label"><fmt:message key="web.gm.payments.col.finishCost.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.payments.col.finishCost.toolTips"/>">
		<input class="form-control" id="finishCost" ng-model="finishCost"  name="finishCost"placeholder="<fmt:message key="web.gm.payments.col.finishCost.toolTips"/>"  type="number"/>
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