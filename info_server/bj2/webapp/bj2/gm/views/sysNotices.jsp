<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysNoticesController($scope){
	initTable('#tableSysNotices',
			$('#app_root').val()+'/fcw/gm/sysNotices',
			[{data:'id'},
			 {data:'type'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.content+'">'+data.content+'</span>';
			 }},
			 {data:'startTime'},
			 {data:'endTime'},
			 {data:'noticeTime'},
			 {data:'createTime'},
			 {data:'updateTime'}
			],
			{leftColumns:3},
			{add:$('#app_root').val()+'/fcw/#/sysNotice',
				edit:$('#app_root').val()+'/fcw/#/sysNotice',
				remove:$('#app_root').val()+'/fcw/gm/removeSysNotice',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysNotice'}
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
			<i class="fa fa-minus"></i>&nbsp;<fmt:message key="web.gm.button.remove"/>
		</button>
		<button type="button" id="modelTransactions" class="btn btn-info" >
			<i class="fa fa-th"></i>&nbsp;<fmt:message key="web.gm.button.logTransactions"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableSysNotices" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysNotice"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysItemPrices.col.id.toolTips" />"  class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItemPrices.col.id.title" />
					</th>
			
<th property="type" title="<fmt:message key="web.gm.sysNotices.col.type.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.type.title" />
</th>
<th property="content" title="<fmt:message key="web.gm.sysNotices.col.content.toolTips" />" class="search large-width">
	<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysNotices.col.content.title" />
</th>
<th property="startTime" title="<fmt:message key="web.gm.sysNotices.col.startTime.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.startTime.title" />
</th>
<th property="endTime" title="<fmt:message key="web.gm.sysNotices.col.endTime.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.endTime.title" />
</th>
<th property="noticeTime" title="<fmt:message key="web.gm.sysNotices.col.noticeTime.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.noticeTime.title" />
</th>
<th property="createTime" title="<fmt:message key="web.gm.sysNotices.col.createTime.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.createTime.title" />
</th>
<th property="updateTime" title="<fmt:message key="web.gm.sysNotices.col.updateTime.toolTips" />">
	<fmt:message key="web.gm.sysNotices.col.updateTime.title" />
</th>		
				</tr>
			</thead>
		</table>
    </div>
</div>