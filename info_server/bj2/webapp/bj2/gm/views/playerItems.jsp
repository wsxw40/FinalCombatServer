<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initPlayerItemsController($scope){
	$('span#pId').html(localStorage['pId']);
	$('span#pName').html(localStorage['pName']);
	initTable('#tablePlayerItems',
			$('#app_root').val()+'/fcw/gm/playerItems',
			[{data:'id'},
			 {data:'itemDisplayName'},
			 {data:'itemId'},
			 {data:'isDeleted'},
			 {data:'sysItem.isDeleted'},
			 {data:'isDefault'},
			 {data:'isBind'},
			 {data:'level'},
			 {data:'playerItemUnitType'},
			 {data:'quantity'},
			 {data:'validDate'},
			 {data:'expireDate'}
			],
			{leftColumns:2},
			{remove:$('#app_root').val()+'/fcw/gm/removePlayerItem',
				edit:$('#app_root').val()+'/fcw/#/playerItem'}
	);
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
		<pre>
			<fmt:message key="web.gm.players.col.id.title" /> : <span id="pId"></span>    
			<fmt:message key="web.gm.players.col.name.title" /> : <span id="pName"></span>
		</pre>
		<button type="button" id="edit" class="btn btn-info" >
			<i class="fa fa-edit"></i>&nbsp;<fmt:message key="web.gm.button.edit"/>
		</button>
		<button type="button" id="remove" class="btn btn-info" >
			<i class="fa fa-edit"></i>&nbsp;<fmt:message key="web.gm.button.remove"/>
		</button>
	</div>
	<div class="box-body">
		<table id="tablePlayerItems" data-datatable="" data-dt-options="dtOptions" data-dt-columns="dtColumns" rowFormId="#formPlayerItem"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.playerItems.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.playerItems.col.id.title" />
					</th>
					<th property="itemDisplayName" title="<fmt:message key="web.gm.playerItems.col.displayName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.playerItems.col.displayName.title" />
					</th>
					<th property="itemId" title="<fmt:message key="web.gm.playerItems.col.sysItemId.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.playerItems.col.sysItemId.title" />
					</th>
					<th property="isDeleted" title="<fmt:message key="web.gm.playerItems.col.isDeleted.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.isDeleted.title" />
					</th>
					<th property="sysItem.isDeleted" title="<fmt:message key="web.gm.playerItems.col.isSysItemDeleted.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.isSysItemDeleted.title" />
					</th>
					<th property="isDefault" title="<fmt:message key="web.gm.playerItems.col.isSysItemDeleted.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.isDefault.title" />
					</th>
					<th property="isBind" title="<fmt:message key="web.gm.playerItems.col.isBind.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.isBind.title" />
					</th>
					<th property="level" title="<fmt:message key="web.gm.playerItems.col.level.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.level.title" />
					</th>
					<th property="playerItemUnitType" title="<fmt:message key="web.gm.playerItems.col.unitType.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.unitType.title" />
					</th>
					<th property="quantity" title="<fmt:message key="web.gm.playerItems.col.quantity.toolTips" />">
						<fmt:message key="web.gm.playerItems.col.quantity.title" />
					</th>
					<th property="validDate" title="<fmt:message key="web.gm.playerItems.col.validTime.toolTips" />" class="normal-width">
						<fmt:message key="web.gm.playerItems.col.validTime.title" />
					</th>
					<th property="expireDate" title="<fmt:message key="web.gm.playerItems.col.expireTime.toolTips" />" class="normal-width">
						<fmt:message key="web.gm.playerItems.col.expireTime.title" />
					</th>
				</tr>
			</thead>
		</table>
	</div>
</div>