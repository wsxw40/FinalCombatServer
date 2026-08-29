<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formSysCharacter" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysCharacter">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" readonly="readonly"
					placeholder="<fmt:message key="web.gm.sysCharacters.col.id.toolTips"/>" />
				</div>
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.name.toolTips"/>">
					<input class="form-control" id="name" ng-model="name"  name="name" required
					placeholder="<fmt:message key="web.gm.sysCharacters.col.name.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="runSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.runSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.runSpeed.toolTips"/>">
		<input class="form-control" id="runSpeed" ng-model="runSpeed"  name="runSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.runSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="walkSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.walkSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.walkSpeed.toolTips"/>">
		<input class="form-control" id="walkSpeed" ng-model="walkSpeed"  name="walkSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.walkSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="crouchSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.crouchSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.crouchSpeed.toolTips"/>">
		<input class="form-control" id="crouchSpeed" ng-model="crouchSpeed"  name="crouchSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.crouchSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="accelSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.accelSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.accelSpeed.toolTips"/>">
		<input class="form-control" id="accelSpeed" ng-model="accelSpeed"  name="accelSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.accelSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="jumpSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.jumpSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.jumpSpeed.toolTips"/>">
		<input class="form-control" id="jumpSpeed" ng-model="jumpSpeed"  name="jumpSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.jumpSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="throwSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.throwSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.throwSpeed.toolTips"/>">
		<input class="form-control" id="throwSpeed" ng-model="throwSpeed"  name="throwSpeed"placeholder="<fmt:message key="web.gm.sysCharacters.col.throwSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="resourceP" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.resourceP.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.resourceP.toolTips"/>">
		<input class="form-control" id="resourceP" ng-model="resourceP"  name="resourceP"placeholder="<fmt:message key="web.gm.sysCharacters.col.resourceP.toolTips"/>" />
	</div>
	<label for="isDefault" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.isDefault.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.isDefault.toolTips"/>">
		<input class="form-control" id="isDefault" ng-model="isDefault"  name="isDefault"placeholder="<fmt:message key="web.gm.sysCharacters.col.isDefault.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="cost" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.cost.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.cost.toolTips"/>">
		<input class="form-control" id="cost" ng-model="cost"  name="cost"placeholder="<fmt:message key="web.gm.sysCharacters.col.cost.toolTips"/>"  type="number"/>
	</div>
	<label for="maxHp" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.maxHp.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.maxHp.toolTips"/>">
		<input class="form-control" id="maxHp" ng-model="maxHp"  name="maxHp"placeholder="<fmt:message key="web.gm.sysCharacters.col.maxHp.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="exHp" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.exHp.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.exHp.toolTips"/>">
		<input class="form-control" id="exHp" ng-model="exHp"  name="exHp"placeholder="<fmt:message key="web.gm.sysCharacters.col.exHp.toolTips"/>"  type="number"/>
	</div>
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.sysCharacters.col.level.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isFired" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.isFired.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.isFired.toolTips"/>">
		<input class="form-control" id="isFired" ng-model="isFired"  name="isFired"placeholder="<fmt:message key="web.gm.sysCharacters.col.isFired.toolTips"/>"  type="number"/>
	</div>
	<label for="resourceName" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.resourceName.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.resourceName.toolTips"/>">
		<input class="form-control" id="resourceName" ng-model="resourceName"  name="resourceName"placeholder="<fmt:message key="web.gm.sysCharacters.col.resourceName.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="isEnable" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.isEnable.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.isEnable.toolTips"/>">
		<input class="form-control" id="isEnable" ng-model="isEnable"  name="isEnable"placeholder="<fmt:message key="web.gm.sysCharacters.col.isEnable.toolTips"/>"  type="number"/>
	</div>
	<label for="controllerHeight" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.controllerHeight.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.controllerHeight.toolTips"/>">
		<input class="form-control" id="controllerHeight" ng-model="controllerHeight"  name="controllerHeight"placeholder="<fmt:message key="web.gm.sysCharacters.col.controllerHeight.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="controllerRadius" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.controllerRadius.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.controllerRadius.toolTips"/>">
		<input class="form-control" id="controllerRadius" ng-model="controllerRadius"  name="controllerRadius"placeholder="<fmt:message key="web.gm.sysCharacters.col.controllerRadius.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="controllerCrouchHeight" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.controllerCrouchHeight.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.controllerCrouchHeight.toolTips"/>">
		<input class="form-control" id="controllerCrouchHeight" ng-model="controllerCrouchHeight"  name="controllerCrouchHeight"placeholder="<fmt:message key="web.gm.sysCharacters.col.controllerCrouchHeight.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="scoreOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysCharacters.col.scoreOffset.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysCharacters.col.scoreOffset.toolTips"/>">
		<input class="form-control" id="scoreOffset" ng-model="scoreOffset"  name="scoreOffset"placeholder="<fmt:message key="web.gm.sysCharacters.col.scoreOffset.toolTips"/>"  type="number"/>
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