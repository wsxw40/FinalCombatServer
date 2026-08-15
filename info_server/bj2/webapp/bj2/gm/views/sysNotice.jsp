<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formSysNotice" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysNotice">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.servers.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.servers.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.servers.col.id.toolTips"/>" />
				</div>
				<label for="type" class="col-sm-2 control-label"><fmt:message key="web.gm.sysNotices.col.type.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysNotices.col.type.toolTips"/>">
					<input class="form-control" id="type" ng-model="type"  name="type"placeholder="<fmt:message key="web.gm.sysNotices.col.type.toolTips"/>"  type="number" required/>
				</div>
			</div>			
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label"><fmt:message key="web.gm.sysNotices.col.content.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.sysNotices.col.content.toolTips"/>">
					<textarea class="form-control" id="content" ng-model="content"  name="content"placeholder="<fmt:message key="web.gm.sysNotices.col.content.toolTips"/>" rows="3" required></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="startTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysNotices.col.startTime.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysNotices.col.startTime.toolTips"/>">
					<input class="form-control" id="startTime" ng-model="startTime"  name="startTime"placeholder="<fmt:message key="web.gm.sysNotices.col.startTime.toolTips"/>"  required/>
				</div>
				<label for="endTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysNotices.col.endTime.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysNotices.col.endTime.toolTips"/>">
					<input class="form-control" id="endTime" ng-model="endTime"  name="endTime"placeholder="<fmt:message key="web.gm.sysNotices.col.endTime.toolTips"/>"  required/>
				</div>
			</div>
			<div class="form-group">
				<label for="noticeTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysNotices.col.noticeTime.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysNotices.col.noticeTime.toolTips"/>">
					<input class="form-control" id="noticeTime" ng-model="noticeTime"  name="noticeTime"placeholder="<fmt:message key="web.gm.sysNotices.col.noticeTime.toolTips"/>"  type="number" required/>
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