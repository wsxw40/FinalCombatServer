<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initChannelsController($scope){
	initTable('#tableChannels',
			$('#app_root').val()+'/fcw/gm/channels',
			[{data:'id'},
			 {data:'name'},
			 {data:'serverId'},
			 {data:'channelId'},
			 {data:'maxLevel'},
			 {data:'minLevel'},
			 {data:'maxOnline'},
			 {data:'isTcp'},
			 {data:'maxTeamLevel'},
			 {data:'minTeamLevel'}
			],
			{leftColumns:4},
			{add:$('#app_root').val()+'/fcw/#/channel',
				edit:$('#app_root').val()+'/fcw/#/channel',
				'delete':$('#app_root').val()+'/fcw/gm/deleteChannel',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WChannel'}
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
        <table id="tableChannels" data-datatable="" data-dt-options="dtOptions" rowFormId="#formChannel"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.channels.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.channels.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.channels.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.channels.col.name.title" />
					</th>
					<th property="serverId" title="<fmt:message key="web.gm.channels.col.serverId.toolTips" />"class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.channels.col.serverId.title" />
					</th>
					<th property="channelId" title="<fmt:message key="web.gm.channels.col.channelId.toolTips" />"class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.channels.col.channelId.title" />
					</th>
<th property="maxLevel" title="<fmt:message key="web.gm.channels.col.maxLevel.toolTips" />">
	<fmt:message key="web.gm.channels.col.maxLevel.title" />
</th>
<th property="minLevel" title="<fmt:message key="web.gm.channels.col.minLevel.toolTips" />">
	<fmt:message key="web.gm.channels.col.minLevel.title" />
</th>
<th property="maxOnline" title="<fmt:message key="web.gm.channels.col.maxOnline.toolTips" />">
	<fmt:message key="web.gm.channels.col.maxOnline.title" />
</th>
<th property="isTcp" title="<fmt:message key="web.gm.channels.col.isTcp.toolTips" />">
	<fmt:message key="web.gm.channels.col.isTcp.title" />
</th>
<th property="maxTeamLevel" title="<fmt:message key="web.gm.channels.col.maxTeamLevel.toolTips" />">
	<fmt:message key="web.gm.channels.col.maxTeamLevel.title" />
</th>
<th property="minTeamLevel" title="<fmt:message key="web.gm.channels.col.minTeamLevel.toolTips" />">
	<fmt:message key="web.gm.channels.col.minTeamLevel.title" />
</th>				
				</tr>
			</thead>
		</table>
    </div>
</div>