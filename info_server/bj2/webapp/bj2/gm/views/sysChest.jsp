<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formSysChest" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysChest">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" readonly="readonly"
					placeholder="<fmt:message key="web.gm.sysChests.col.id.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="type" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.type.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.type.toolTips"/>">
		<input class="form-control" id="type" ng-model="type"  name="type"placeholder="<fmt:message key="web.gm.sysChests.col.type.toolTips"/>"  type="number"/>
	</div>
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.sysChests.col.level.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="sysItemId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.sysItemId.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.sysItemId.toolTips"/>">
		<input class="form-control" id="sysItemId" ng-model="sysItemId"  name="sysItemId"placeholder="<fmt:message key="web.gm.sysChests.col.sysItemId.toolTips"/>"  type="number"/>
	</div>
	<label for="weight" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.weight.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.weight.toolTips"/>">
		<input class="form-control" id="weight" ng-model="weight"  name="weight"placeholder="<fmt:message key="web.gm.sysChests.col.weight.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="number" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.number.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.number.toolTips"/>">
		<input class="form-control" id="number" ng-model="number"  name="number"placeholder="<fmt:message key="web.gm.sysChests.col.number.toolTips"/>"  type="number"/>
	</div>
	<label for="useType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.useType.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.useType.toolTips"/>">
		<input class="form-control" id="useType" ng-model="useType"  name="useType"placeholder="<fmt:message key="web.gm.sysChests.col.useType.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isNotice" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.isNotice.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.isNotice.toolTips"/>">
		<input class="form-control" id="isNotice" ng-model="isNotice"  name="isNotice"placeholder="<fmt:message key="web.gm.sysChests.col.isNotice.toolTips"/>"  type="number"/>
	</div>
	<label for="price" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.price.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.price.toolTips"/>">
		<input class="form-control" id="price" ng-model="price"  name="price"placeholder="<fmt:message key="web.gm.sysChests.col.price.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="chipPrice" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.chipPrice.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.chipPrice.toolTips"/>">
		<input class="form-control" id="chipPrice" ng-model="chipPrice"  name="chipPrice"placeholder="<fmt:message key="web.gm.sysChests.col.chipPrice.toolTips"/>"  type="number"/>
	</div>
	<label for="weight1" class="col-sm-2 control-label"><fmt:message key="web.gm.sysChests.col.weight1.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysChests.col.weight1.toolTips"/>">
		<input class="form-control" id="weight1" ng-model="weight1"  name="weight1"placeholder="<fmt:message key="web.gm.sysChests.col.weight1.toolTips"/>"  type="number"/>
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