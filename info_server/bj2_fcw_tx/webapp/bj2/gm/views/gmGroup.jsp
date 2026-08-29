<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmGroupController($scope){
	var data=initTableRowForm($scope,'#formGmGroup', $('#app_root').val()+'/fcw/#/gmGroups');
    $.post($('#app_root').val()+'/fcw/gm/gmPrivileges',{},function(msg){
    	var html='';
		for(var i in msg){
			if(i%2==0){
				html+='<div class="form-group" style="margin-top: 8px;">';
				html+='<div class="col-sm-5"><input type="checkbox" name="privileges" value="'+msg[i].id+'">&nbsp;'+msg[i].id+"&nbsp;-&nbsp;"+msg[i].name+' : '+msg[i].description+'</div>';
			}else{
				html+='<div class="col-sm-5"><input type="checkbox" name="privileges" value="'+msg[i].id+'">&nbsp;'+msg[i].id+"&nbsp;-&nbsp;"+msg[i].name+' : '+msg[i].description+'</div>';
				html+='</div>';
			}
		}
		$('#privileges').html(html);
	    $('input').iCheck({
	        checkboxClass: 'icheckbox_square-blue'
	    });
		if(undefined!=data){
			$.post($('#app_root').val()+'/fcw/gm/gmGroupPrivileges',{groupId:data.id},function(msg){
				for(var i in msg){
					$('input[type="checkbox"][value="'+msg[i].privilegeId+'"]').iCheck('check');						
				}
			});
		}
	});
}
</script>
<div class="box box-primary">
	<div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formGmGroup" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveGmGroup">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.gmGroups.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmGroups.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.gmGroups.col.id.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.gmGroups.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmGroups.col.name.toolTips"/>">
					<input class="form-control" id="name" ng-model="name"  name="name" required
					placeholder="<fmt:message key="web.gm.gmGroups.col.name.toolTips"/>" />
				</div>
				<label for="code" class="col-sm-2 control-label"><fmt:message key="web.gm.gmGroups.col.code.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.gmGroups.col.code.toolTips"/>">
					<input class="form-control" id="code" ng-model="code"  name="code" 
					placeholder="<fmt:message key="web.gm.gmGroups.col.code.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label"><fmt:message key="web.gm.gmGroups.col.description.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.gmGroups.col.description.toolTips"/>">
					<textarea class="form-control" id="description" ng-model="description"  name="description"  rows="3"
					placeholder="<fmt:message key="web.gm.gmGroups.col.description.toolTips"/>" ></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="privileges" class="col-sm-2 control-label"><fmt:message key="web.gm.gmGroups.col.privileges.title"/></label>
				<div class="col-sm-10" id="privileges">
				</div>
			</div>

			<div class="form-group" button>
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submit" class="btn btn-primary"><fmt:message key="web.gm.button.submit"/></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" id="cancel" class="btn btn-default"><fmt:message key="web.gm.button.cancel"/></button>
				</div>
			</div>
		</form>
	</div>
</div>