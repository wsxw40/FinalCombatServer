<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initServersController($scope){
	initTable('#tableServers',
			$('#app_root').val()+'/fcw/gm/servers',
			[{data:'id'},
			 {data:'name'},
			 {data:'maxLevel'},
			 {data:'minLevel'},
			 {data:'maxOnline'},
			 {data:'isNew'},
			 {data:'minFightnum'},
			 {data:'gameType'},
			 {data:'maxTeamLevel'},
			 {data:'minTeamLevel'},
			 {data:'serverType'},
			 {data:'index'}
			],
			{leftColumns:2},
			{add:$('#app_root').val()+'/fcw/#/server',
				edit:$('#app_root').val()+'/fcw/#/server',
				'delete':$('#app_root').val()+'/fcw/gm/deleteServer',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WServer'}
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
		<button type="button" id="modelTransactions" class="btn btn-info" >
			<i class="fa fa-th"></i>&nbsp;<fmt:message key="web.gm.button.logTransactions"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableServers" data-datatable="" data-dt-options="dtOptions" rowFormId="#formServer"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.servers.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.servers.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.servers.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.servers.col.name.title" />
					</th>
<th property="maxLevel" title="<fmt:message key="web.gm.servers.col.maxLevel.toolTips" />">
	<fmt:message key="web.gm.servers.col.maxLevel.title" />
</th>
<th property="minLevel" title="<fmt:message key="web.gm.servers.col.minLevel.toolTips" />">
	<fmt:message key="web.gm.servers.col.minLevel.title" />
</th>
<th property="maxOnline" title="<fmt:message key="web.gm.servers.col.maxOnline.toolTips" />">
	<fmt:message key="web.gm.servers.col.maxOnline.title" />
</th>
<th property="isNew" title="<fmt:message key="web.gm.servers.col.isNew.toolTips" />">
	<fmt:message key="web.gm.servers.col.isNew.title" />
</th>
<th property="minFightnum" title="<fmt:message key="web.gm.servers.col.minFightnum.toolTips" />">
	<fmt:message key="web.gm.servers.col.minFightnum.title" />
</th>
<th property="gameType" title="<fmt:message key="web.gm.servers.col.gameType.toolTips" />">
	<fmt:message key="web.gm.servers.col.gameType.title" />
</th>
<th property="maxTeamLevel" title="<fmt:message key="web.gm.servers.col.maxTeamLevel.toolTips" />">
	<fmt:message key="web.gm.servers.col.maxTeamLevel.title" />
</th>
<th property="minTeamLevel" title="<fmt:message key="web.gm.servers.col.minTeamLevel.toolTips" />">
	<fmt:message key="web.gm.servers.col.minTeamLevel.title" />
</th>
<th property="serverType" title="<fmt:message key="web.gm.servers.col.serverType.toolTips" />">
	<fmt:message key="web.gm.servers.col.serverType.title" />
</th>		
<th property="index" title="<fmt:message key="web.gm.servers.col.index.toolTips" />">
	<fmt:message key="web.gm.servers.col.index.title" />
</th>				
				</tr>
			</thead>
		</table>
    </div>
</div>