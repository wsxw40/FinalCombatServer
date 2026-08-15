<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysCharactersController($scope){
	initTable('#tableSysCharacters',
			$('#app_root').val()+'/fcw/gm/sysCharacters',
			[{data:'id'},
			 {data:function(data, type, full, meta){
				 return data.name.replace(/\</g,'&lt;').replace(/\>/g,'&gt');					 
			 }},
			 {data:'isDeleted'},
			 {data:'runSpeed'},
			 {data:'walkSpeed'},
			 {data:'crouchSpeed'},
			 {data:'accelSpeed'},
			 {data:'jumpSpeed'},
			 {data:'throwSpeed'},
			 {data:'resourceP'},
			 {data:'isDefault'},
			 {data:'cost'},
			 {data:'maxHp'},
			 {data:'exHp'},
			 {data:'level'},
			 {data:'isFired'},
			 {data:'resourceName'},
			 {data:'isEnable'},
			 {data:'controllerHeight'},
			 {data:'controllerRadius'},
			 {data:'controllerCrouchHeight'},
			 {data:'scoreOffset'}
			],
			{leftColumns:2},
			{add:$('#app_root').val()+'/fcw/#/sysCharacter',
				edit:$('#app_root').val()+'/fcw/#/sysCharacter',
				remove:$('#app_root').val()+'/fcw/gm/removeSysCharacter',
				'delete':$('#app_root').val()+'/fcw/gm/deleteSysCharacter',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysCharacter'
			}
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
		<button type="button" id="delete" class="btn btn-info" >
			<i class="fa fa-remove"></i>&nbsp;<fmt:message key="web.gm.button.delete"/>
		</button>
		<button type="button" id="modelTransactions" class="btn btn-info" >
			<i class="fa fa-th"></i>&nbsp;<fmt:message key="web.gm.button.logTransactions"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableSysCharacters" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysCharacter"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysCharacters.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysCharacters.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.sysCharacters.col.name.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysCharacters.col.name.title" />
					</th>
					<th property="isDeleted" title="<fmt:message key="web.gm.sysCharacters.col.isDeleted.toolTips" />" class="search normal-width">
						<fmt:message key="web.gm.sysCharacters.col.isDeleted.title" />
					</th>
<th property="runSpeed" title="<fmt:message key="web.gm.sysCharacters.col.runSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.runSpeed.title" />
</th>
<th property="walkSpeed" title="<fmt:message key="web.gm.sysCharacters.col.walkSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.walkSpeed.title" />
</th>
<th property="crouchSpeed" title="<fmt:message key="web.gm.sysCharacters.col.crouchSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.crouchSpeed.title" />
</th>
<th property="accelSpeed" title="<fmt:message key="web.gm.sysCharacters.col.accelSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.accelSpeed.title" />
</th>
<th property="jumpSpeed" title="<fmt:message key="web.gm.sysCharacters.col.jumpSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.jumpSpeed.title" />
</th>
<th property="throwSpeed" title="<fmt:message key="web.gm.sysCharacters.col.throwSpeed.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.throwSpeed.title" />
</th>
<th property="resourceP" title="<fmt:message key="web.gm.sysCharacters.col.resourceP.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.resourceP.title" />
</th>
<th property="isDefault" title="<fmt:message key="web.gm.sysCharacters.col.isDefault.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.isDefault.title" />
</th>
<th property="cost" title="<fmt:message key="web.gm.sysCharacters.col.cost.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.cost.title" />
</th>
<th property="maxHp" title="<fmt:message key="web.gm.sysCharacters.col.maxHp.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.maxHp.title" />
</th>
<th property="exHp" title="<fmt:message key="web.gm.sysCharacters.col.exHp.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.exHp.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.sysCharacters.col.level.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.level.title" />
</th>
<th property="isFired" title="<fmt:message key="web.gm.sysCharacters.col.isFired.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.isFired.title" />
</th>
<th property="resourceName" title="<fmt:message key="web.gm.sysCharacters.col.resourceName.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.resourceName.title" />
</th>
<th property="isEnable" title="<fmt:message key="web.gm.sysCharacters.col.isEnable.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.isEnable.title" />
</th>
<th property="controllerHeight" title="<fmt:message key="web.gm.sysCharacters.col.controllerHeight.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.controllerHeight.title" />
</th>
<th property="controllerRadius" title="<fmt:message key="web.gm.sysCharacters.col.controllerRadius.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.controllerRadius.title" />
</th>
<th property="controllerCrouchHeight" title="<fmt:message key="web.gm.sysCharacters.col.controllerCrouchHeight.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.controllerCrouchHeight.title" />
</th>
<th property="scoreOffset" title="<fmt:message key="web.gm.sysCharacters.col.scoreOffset.toolTips" />">
	<fmt:message key="web.gm.sysCharacters.col.scoreOffset.title" />
</th>
				</tr>
			</thead>
		</table>
    </div>
</div>