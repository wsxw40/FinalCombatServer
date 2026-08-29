<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysChestsController($scope){
	initTable('#tableSysChests',
			$('#app_root').val()+'/fcw/gm/sysChests',
			[{data:'id'},
			 {data:'sysItemId'},
			 {data:'sysItemName',sortable:false},
			 {data:'sysItemDisplayName',sortable:false},
			 {data:'type'},
			 {data:'level'},
			 {data:'weight'},
			 {data:'number'},
			 {data:'useType'},
			 {data:'isNotice'},
			 {data:'price'},
			 {data:'chipPrice'},
			 {data:'weight1'}
			],
			{leftColumns:2},
			{add:$('#app_root').val()+'/fcw/#/sysChest',
				edit:$('#app_root').val()+'/fcw/#/sysChest',
				'delete':$('#app_root').val()+'/fcw/gm/deleteSysChest',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysChest'}
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
        <table id="tableSysChests" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysChest"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysChests.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysChests.col.id.title" />
					</th>
					<th property="sysItemId" title="<fmt:message key="web.gm.sysChests.col.sysItemId.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysChests.col.sysItemId.title" />
					</th>
					<th property="sysItemName" title="<fmt:message key="web.gm.sysItems.col.name.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.name.title" />
					</th>
					<th property="sysItemDisplayName" title="<fmt:message key="web.gm.sysItems.col.displayName.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.displayName.title" />
					</th>
<th property="type" title="<fmt:message key="web.gm.sysChests.col.type.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.type.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.sysChests.col.level.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.level.title" />
</th>
<th property="weight" title="<fmt:message key="web.gm.sysChests.col.weight.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.weight.title" />
</th>
<th property="number" title="<fmt:message key="web.gm.sysChests.col.number.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.number.title" />
</th>
<th property="useType" title="<fmt:message key="web.gm.sysChests.col.useType.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.useType.title" />
</th>
<th property="isNotice" title="<fmt:message key="web.gm.sysChests.col.isNotice.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.isNotice.title" />
</th>
<th property="price" title="<fmt:message key="web.gm.sysChests.col.price.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.price.title" />
</th>
<th property="chipPrice" title="<fmt:message key="web.gm.sysChests.col.chipPrice.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.chipPrice.title" />
</th>
<th property="weight1" title="<fmt:message key="web.gm.sysChests.col.weight1.toolTips" />">
	<fmt:message key="web.gm.sysChests.col.weight1.title" />
</th>					
				</tr>
			</thead>
		</table>
    </div>
</div>