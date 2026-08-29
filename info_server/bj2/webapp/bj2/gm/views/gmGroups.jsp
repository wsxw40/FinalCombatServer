<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmGroupsController($scope){
	initTable('#tableGmGroups',
			$('#app_root').val()+'/fcw/gm/gmGroups',
			[{data:'id'},
			 {data:'name'},
			 {data:'creatorId'},
			 {data:'code'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.description+'">'+data.description+'</span>';
			 }},
			 {data:function(data, type, full, meta){
				 var str='';
				 for(var i in data.privileges){
					 str+=i+' - '+data.privileges[i]+' ; ';
				 }
				 return '<span class="large-width" style="width:auto;" title="'+str+'">'+str+'</span>';
			 },sortable:false}
			],
			{leftColumns:2},
			{add:$('#app_root').val()+'/fcw/#/gmGroup',
				edit:$('#app_root').val()+'/fcw/#/gmGroup',
				'delete':$('#app_root').val()+'/fcw/gm/deleteGmGroup'}
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
		<button type="button" id="delete" class="btn btn-info" >
			<i class="fa fa-remove"></i>&nbsp;<fmt:message key="web.gm.button.delete"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableGmGroups" data-datatable="" data-dt-options="dtOptions" rowFormId="#formGmGroup"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.gmGroups.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmGroups.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.gmGroups.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmGroups.col.name.title" />
					</th>
					<th property="creatorId" title="<fmt:message key="web.gm.gmGroups.col.creatorId.toolTips" />">
						<fmt:message key="web.gm.gmGroups.col.creatorId.title" />
					</th>
					<th property="code" title="<fmt:message key="web.gm.gmGroups.col.code.toolTips" />">
						<fmt:message key="web.gm.gmGroups.col.code.title" />
					</th>
					<th property="description" title="<fmt:message key="web.gm.gmGroups.col.description.toolTips" />">
						<fmt:message key="web.gm.gmGroups.col.description.title" />
					</th>
					<th property="privileges" title="<fmt:message key="web.gm.gmGroups.col.privileges.toolTips" />">
						<fmt:message key="web.gm.gmGroups.col.privileges.title" />
					</th>
				</tr>
			</thead>
		</table>
    </div>
</div>