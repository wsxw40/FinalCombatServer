<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formSysStrengthenAppend" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysStrengthenAppend">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" readonly="readonly"
					placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.id.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.level.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.level.toolTips"/>">
		<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.level.toolTips"/>"  type="number"/>
	</div>
	<label for="type" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.type.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.type.toolTips"/>">
		<input class="form-control" id="type" ng-model="type"  name="type"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.type.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="property1" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.property1.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.property1.toolTips"/>">
		<input class="form-control" id="property1" ng-model="property1"  name="property1"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.property1.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="property2" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.property2.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.property2.toolTips"/>">
		<input class="form-control" id="property2" ng-model="property2"  name="property2"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.property2.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="streNum" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.streNum.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.streNum.toolTips"/>">
		<input class="form-control" id="streNum" ng-model="streNum"  name="streNum"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.streNum.toolTips"/>"  type="number"/>
	</div>
	<label for="streGr" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.streGr.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.streGr.toolTips"/>">
		<input class="form-control" id="streGr" ng-model="streGr"  name="streGr"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.streGr.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="winRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.winRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.winRate.toolTips"/>">
		<input class="form-control" id="winRate" ng-model="winRate"  name="winRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.winRate.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="falseKeepRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.falseKeepRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseKeepRate.toolTips"/>">
		<input class="form-control" id="falseKeepRate" ng-model="falseKeepRate"  name="falseKeepRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.falseKeepRate.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="falseFallRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.falseFallRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseFallRate.toolTips"/>">
		<input class="form-control" id="falseFallRate" ng-model="falseFallRate"  name="falseFallRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.falseFallRate.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="falseRuinRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.falseRuinRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseRuinRate.toolTips"/>">
		<input class="form-control" id="falseRuinRate" ng-model="falseRuinRate"  name="falseRuinRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.falseRuinRate.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="holeWinRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.holeWinRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.holeWinRate.toolTips"/>">
		<input class="form-control" id="holeWinRate" ng-model="holeWinRate"  name="holeWinRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.holeWinRate.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="switchFallRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysStrengthenAppends.col.switchFallRate.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysStrengthenAppends.col.switchFallRate.toolTips"/>">
		<input class="form-control" id="switchFallRate" ng-model="switchFallRate"  name="switchFallRate"placeholder="<fmt:message key="web.gm.sysStrengthenAppends.col.switchFallRate.toolTips"/>"  type="number" step="0.0001"/>
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