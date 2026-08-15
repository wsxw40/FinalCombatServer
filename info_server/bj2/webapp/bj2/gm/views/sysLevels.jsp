<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysLevelsController($scope){
	initTable('#tableSysLevels',
			$('#app_root').val()+'/fcw/gm/sysLevels',
			[{data:'id'},
			 {data:'name'},
			 {data:'displayName'},				 
			 {data:'type'},
			 {data:'index'},
			 {data:'isnew'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.startPoints+'">'+data.startPoints+'</span>';
			 }},
			 {data:'blastPoints'},
			 {data:'flagPoints'},
			 {data:'weaponPoints'},
			 {data:'bossItems'},
			 {data:'isSelf'},
			 {data:'description'},
			 {data:'zombieInfo'},
			 {data:'levelHorizon'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.vehicleLineInfo+'">'+data.vehicleLineInfo+'</span>';
			 }},
			 {data:'vehicleAddNum'},
			 {data:'vehicleInfo'},
			 {data:'targetSpeed'},
			 {data:'bloodOffset'},
			 {data:'isRushGold'},
			 {data:'isMoneyReward'},
			 {data:'rushGoldPoint'},
			 {data:'isVip'},
			 {data:'expAdd'},
			 {data:'gpAdd'},
			 {data:'activityStart'},
			 {data:'activityEnd'},
			 {data:'numForTeam'},
			 {data:'isGm'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.supplies+'">'+data.supplies+'</span>';
			 }}
			],
			{leftColumns:3},
			{add:$('#app_root').val()+'/fcw/#/sysLevel',
				edit:$('#app_root').val()+'/fcw/#/sysLevel',
				'delete':$('#app_root').val()+'/fcw/gm/deleteSysLevel',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysLevel'}
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
        <table id="tableSysLevels" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysLevel"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysLevels.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysLevels.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.sysLevels.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysLevels.col.name.title" />
					</th>
					<th property="displayName" title="<fmt:message key="web.gm.sysLevels.col.displayName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysLevels.col.displayName.title" />
					</th>
<th property="type" title="<fmt:message key="web.gm.sysLevels.col.type.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.type.title" />
</th>
<th property="index" title="<fmt:message key="web.gm.sysLevels.col.index.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.index.title" />
</th>
<th property="isnew" title="<fmt:message key="web.gm.sysLevels.col.isnew.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isnew.title" />
</th>
<th property="startPoints" title="<fmt:message key="web.gm.sysLevels.col.startPoints.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.startPoints.title" />
</th>
<th property="blastPoints" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.blastPoints.title" />
</th>
<th property="flagPoints" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.flagPoints.title" />
</th>
<th property="weaponPoints" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.weaponPoints.title" />
</th>
<th property="bossItems" title="<fmt:message key="web.gm.sysLevels.col.bossItems.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.bossItems.title" />
</th>
<th property="isSelf" title="<fmt:message key="web.gm.sysLevels.col.isSelf.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isSelf.title" />
</th>
<th property="description" title="<fmt:message key="web.gm.sysLevels.col.description.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.description.title" />
</th>
<th property="zombieInfo" title="<fmt:message key="web.gm.sysLevels.col.zombieInfo.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.zombieInfo.title" />
</th>
<th property="levelHorizon" title="<fmt:message key="web.gm.sysLevels.col.levelHorizon.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.levelHorizon.title" />
</th>
<th property="vehicleLineInfo" title="<fmt:message key="web.gm.sysLevels.col.vehicleLineInfo.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.vehicleLineInfo.title" />
</th>
<th property="vehicleAddNum" title="<fmt:message key="web.gm.sysLevels.col.vehicleAddNum.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.vehicleAddNum.title" />
</th>
<th property="vehicleInfo" title="<fmt:message key="web.gm.sysLevels.col.vehicleInfo.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.vehicleInfo.title" />
</th>
<th property="targetSpeed" title="<fmt:message key="web.gm.sysLevels.col.targetSpeed.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.targetSpeed.title" />
</th>
<th property="bloodOffset" title="<fmt:message key="web.gm.sysLevels.col.bloodOffset.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.bloodOffset.title" />
</th>
<th property="isRushGold" title="<fmt:message key="web.gm.sysLevels.col.isRushGold.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isRushGold.title" />
</th>
<th property="isMoneyReward" title="<fmt:message key="web.gm.sysLevels.col.isMoneyReward.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isMoneyReward.title" />
</th>
<th property="rushGoldPoint" title="<fmt:message key="web.gm.sysLevels.col.rushGoldPoint.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.rushGoldPoint.title" />
</th>
<th property="isVip" title="<fmt:message key="web.gm.sysLevels.col.isVip.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isVip.title" />
</th>
<th property="expAdd" title="<fmt:message key="web.gm.sysLevels.col.expAdd.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.expAdd.title" />
</th>
<th property="gpAdd" title="<fmt:message key="web.gm.sysLevels.col.gpAdd.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.gpAdd.title" />
</th>
<th property="activityStart" title="<fmt:message key="web.gm.sysLevels.col.activityStart.toolTips" />" class="normal-width">
	<fmt:message key="web.gm.sysLevels.col.activityStart.title" />
</th>
<th property="activityEnd" title="<fmt:message key="web.gm.sysLevels.col.activityEnd.toolTips" />" class="normal-width">
	<fmt:message key="web.gm.sysLevels.col.activityEnd.title" />
</th>
<th property="numForTeam" title="<fmt:message key="web.gm.sysLevels.col.numForTeam.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.numForTeam.title" />
</th>
<th property="isGm" title="<fmt:message key="web.gm.sysLevels.col.isGm.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.isGm.title" />
</th>
<th property="supplies" title="<fmt:message key="web.gm.sysLevels.col.supplies.toolTips" />">
	<fmt:message key="web.gm.sysLevels.col.supplies.title" />
</th>
				</tr>
			</thead>
		</table>
    </div>
</div>