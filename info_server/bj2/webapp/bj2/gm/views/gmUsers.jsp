<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmUsersController($scope){
	initTable('#tableGmUsers',
			$('#app_root').val()+'/fcw/gm/gmUsers',
			[{data:'id'},
			 {data:'userName'},
			 {data:'name'},
			 {data:'creatorId'},
			 {data:'deleted'},
			 {data:'groupId',sortable:false},
			 {data:'groupName',sortable:false},
			 {data:function(data, type, full, meta){
				 var str='';
				 for(var i in data.privileges){
					 str+=i+' - '+data.privileges[i]+' ; ';
				 }
				 return '<span class="large-width" style="width:auto;" title="'+str+'">'+str+'</span>';
			 },sortable:false}
			],
			{leftColumns:3},
			{add:$('#app_root').val()+'/fcw/#/gmUser',
				edit:$('#app_root').val()+'/fcw/#/gmUser',
				remove:$('#app_root').val()+'/fcw/gm/removeGmUser',
				gear:$('#app_root').val()+'/fcw/gm/resetGmUserPwd',
				'delete':$('#app_root').val()+'/fcw/gm/deleteGmUser'}
	);
}
</script>
<div class="box box-primary">
    <div class="box-header with-border">
		<button type="button" id="add" class="btn btn-info" >
			<i class="fa fa-plus"></i>&nbsp;<fmt:message key="web.gm.button.add"/>
		</button>
		<button type="button" id="edit" class="btn btn-info" >
			<i class="fa fa-edit"></i>&nbsp;<fmt:message key="web.gm.button.edit"/>
		</button>
		<button type="button" id="remove" class="btn btn-info" >
			<i class="fa fa-remove"></i>&nbsp;<fmt:message key="web.gm.button.remove"/>
		</button>
		<button type="button" id="delete" class="btn btn-info" >
			<i class="fa fa-remove"></i>&nbsp;<fmt:message key="web.gm.button.delete"/>
		</button>
		<button type="button" id="resetPwd" class="btn btn-info" >
			<i class="fa fa-gear"></i>&nbsp;<fmt:message key="web.gm.button.resetPwd"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableGmUsers" data-datatable="" data-dt-options="dtOptions" rowFormId="#formGmUser"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.gmUsers.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmUsers.col.id.title" />
					</th>
					<th property="userName" title="<fmt:message key="web.gm.gmUsers.col.userName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmUsers.col.userName.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.gmUsers.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmUsers.col.name.title" />
					</th>
					<th property="creatorId" title="<fmt:message key="web.gm.gmUsers.col.creatorId.toolTips" />">
						<fmt:message key="web.gm.gmUsers.col.creatorId.title" />
					</th>
					<th property="deleted" title="<fmt:message key="web.gm.gmUsers.col.deleted.toolTips" />">
						<fmt:message key="web.gm.gmUsers.col.deleted.title" />
					</th>
					<th property="groupId" title="<fmt:message key="web.gm.gmUsers.col.groupId.toolTips" />">
						<fmt:message key="web.gm.gmUsers.col.groupId.title" />
					</th>
					<th property="groupName" title="<fmt:message key="web.gm.gmUsers.col.groupName.toolTips" />">
						<fmt:message key="web.gm.gmUsers.col.groupName.title" />
					</th>
					<th property="privileges" title="<fmt:message key="web.gm.gmUsers.col.privileges.toolTips" />">
						<fmt:message key="web.gm.gmUsers.col.privileges.title" />
					</th>
				</tr>
			</thead>
		</table>
    </div>
</div>