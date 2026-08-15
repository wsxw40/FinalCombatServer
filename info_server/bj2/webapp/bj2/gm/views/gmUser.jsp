<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formServer" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveGmUser">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.gmUsers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmUsers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.gmUsers.col.id.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2 control-label"><fmt:message key="web.gm.gmUsers.col.userName.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmUsers.col.userName.toolTips"/>">
					<input class="form-control" id="userName" ng-model="userName"  name="userName" required
					placeholder="<fmt:message key="web.gm.gmUsers.col.userName.toolTips"/>" />
				</div>
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.gmUsers.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmUsers.col.name.toolTips"/>">
					<input class="form-control" id="name" ng-model="name"  name="name" required
					placeholder="<fmt:message key="web.gm.gmUsers.col.name.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="groupId" class="col-sm-2 control-label"><fmt:message key="web.gm.gmUsers.col.groupId.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmUsers.col.groupId.toolTips"/>">
					<input type="number" min="1" class="form-control" id="groupId" ng-model="groupId"  name="groupId" required
					placeholder="<fmt:message key="web.gm.gmUsers.col.groupId.toolTips"/>" />
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