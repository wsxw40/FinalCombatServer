<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysItemsController($scope){
	var specialSearch={};
	var table=initTable('#tableSysItems',
			$('#app_root').val()+'/fcw/gm/sysItems',
			[{data:'id'},
			 {data:'name'},
			 {data:'displayName'},
			 {data:'type'},
			 {data:'subType'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.description+'">'+data.description+'</span>';
			 }},
			 {data:'isDeleted'},
			 {data:'resourceStable'},
			 {data:'resourceChangeable'},
			 {data:'level'},
			 {data:'modifiedDesc'},
			 {data:'cId'},
			 {data:'iId'},
			 {data:'iValue'},
			 {data:'characterId'},
			 {data:'iBuffId'},
			 {data:'isVip'},
			 {data:'isNew'},
			 {data:'isHot'},
			 {data:'items'},
			 {data:'backBoostPlus'},
			 {data:'index'},
			 {data:'cResistanceFire'},
			 {data:'cResistanceBlast'},
			 {data:'cResistanceBullet'},
			 {data:'cResistanceKnife'},
			 {data:'cBloodAdd'},
			 {data:'isStrengthen'},
			 {data:'mType'},
			 {data:'mValue'},
			 {data:'isWeb'},
			 {data:'fightNum'},
			 {data:'rareLevel'},
			 {data:'isPopular'},
			 {data:'isExchange'},
			 {data:'isAsset'},
			 {data:'strengthLevel'},
			 {data:'evaluateRmb'},
			 {data:'needTeamPlaceLevel'},
			 {data:'timeForBuildingUp'},
			 {data:'timeForCreate'},
			 {data:'timeForBuild'}
			],
			{leftColumns:3},
			{add:$('#app_root').val()+'/fcw/#/sysItem',
				edit:$('#app_root').val()+'/fcw/#/sysItem',
				remove:$('#app_root').val()+'/fcw/gm/removeSysItem',
				'delete':$('#app_root').val()+'/fcw/gm/deleteSysItem',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysItem'
			},
			{},
			specialSearch
	);
	$('[name="itemSubType"]').hide();
	$('#itemType,#itemSubType').change(function(){
		if($(this).attr('id')=='itemType'){
			$('[name="itemSubType"]').hide();
			if($(this).val()!=0){
				$('[name="itemSubType"][type="'+$(this).val()+'"]').show();
			}				
		}
		
		specialSearch.type=$('#itemType').val();
		specialSearch.subType=$('[name="itemSubType"]:visible select').val();
		var newSearch=undefined==table.ajax.params().searchValue?'':table.ajax.params().searchValue;
		table.search(newSearch).draw();
	});	
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
    	<p><jsp:include page="sysItemType.jsp"/></p>
        <table id="tableSysItems" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysItem"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysItems.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.sysItems.col.name.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.name.title" />
					</th>
					<th property="displayName" title="<fmt:message key="web.gm.sysItems.col.displayName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.displayName.title" />
					</th>
					<th property="type" title="<fmt:message key="web.gm.sysItems.col.type.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.type.title" />
					</th>
					<th property="subType" title="<fmt:message key="web.gm.sysItems.col.subType.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.subType.title" />
					</th>
					<th property="description" title="<fmt:message key="web.gm.sysItems.col.description.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.description.title" />
					</th>
					<th property="isDeleted" title="<fmt:message key="web.gm.sysItems.col.isDeleted.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.isDeleted.title" />
					</th>
<th property="resourceStable" title="<fmt:message key="web.gm.sysItems.col.resourceStable.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.resourceStable.title" />
</th>
<th property="resourceChangeable" title="<fmt:message key="web.gm.sysItems.col.resourceChangeable.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.resourceChangeable.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.sysItems.col.level.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.level.title" />
</th>
<th property="modifiedDesc" title="<fmt:message key="web.gm.sysItems.col.modifiedDesc.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.modifiedDesc.title" />
</th>
<th property="cId" title="<fmt:message key="web.gm.sysItems.col.cId.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cId.title" />
</th>
<th property="iId" title="<fmt:message key="web.gm.sysItems.col.iId.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.iId.title" />
</th>
<th property="iValue" title="<fmt:message key="web.gm.sysItems.col.iValue.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.iValue.title" />
</th>
<th property="characterId" title="<fmt:message key="web.gm.sysItems.col.characterId.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.characterId.title" />
</th>
<th property="iBuffId" title="<fmt:message key="web.gm.sysItems.col.iBuffId.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.iBuffId.title" />
</th>
<th property="isVip" title="<fmt:message key="web.gm.sysItems.col.isVip.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isVip.title" />
</th>
<th property="isNew" title="<fmt:message key="web.gm.sysItems.col.isNew.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isNew.title" />
</th>
<th property="isHot" title="<fmt:message key="web.gm.sysItems.col.isHot.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isHot.title" />
</th>
<th property="items" title="<fmt:message key="web.gm.sysItems.col.items.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.items.title" />
</th>
<th property="backBoostPlus" title="<fmt:message key="web.gm.sysItems.col.backBoostPlus.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.backBoostPlus.title" />
</th>
<th property="index" title="<fmt:message key="web.gm.sysItems.col.index.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.index.title" />
</th>
<th property="cResistanceFire" title="<fmt:message key="web.gm.sysItems.col.cResistanceFire.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cResistanceFire.title" />
</th>
<th property="cResistanceBlast" title="<fmt:message key="web.gm.sysItems.col.cResistanceBlast.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cResistanceBlast.title" />
</th>
<th property="cResistanceBullet" title="<fmt:message key="web.gm.sysItems.col.cResistanceBullet.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cResistanceBullet.title" />
</th>
<th property="cResistanceKnife" title="<fmt:message key="web.gm.sysItems.col.cResistanceKnife.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cResistanceKnife.title" />
</th>
<th property="cBloodAdd" title="<fmt:message key="web.gm.sysItems.col.cBloodAdd.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.cBloodAdd.title" />
</th>
<th property="isStrengthen" title="<fmt:message key="web.gm.sysItems.col.isStrengthen.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isStrengthen.title" />
</th>
<th property="mType" title="<fmt:message key="web.gm.sysItems.col.mType.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.mType.title" />
</th>
<th property="mValue" title="<fmt:message key="web.gm.sysItems.col.mValue.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.mValue.title" />
</th>
<th property="isWeb" title="<fmt:message key="web.gm.sysItems.col.isWeb.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isWeb.title" />
</th>
<th property="fightNum" title="<fmt:message key="web.gm.sysItems.col.fightNum.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.fightNum.title" />
</th>
<th property="rareLevel" title="<fmt:message key="web.gm.sysItems.col.rareLevel.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.rareLevel.title" />
</th>
<th property="isPopular" title="<fmt:message key="web.gm.sysItems.col.isPopular.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isPopular.title" />
</th>
<th property="isExchange" title="<fmt:message key="web.gm.sysItems.col.isExchange.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isExchange.title" />
</th>
<th property="isAsset" title="<fmt:message key="web.gm.sysItems.col.isAsset.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.isAsset.title" />
</th>
<th property="strengthLevel" title="<fmt:message key="web.gm.sysItems.col.strengthLevel.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.strengthLevel.title" />
</th>
<th property="evaluateRmb" title="<fmt:message key="web.gm.sysItems.col.evaluateRmb.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.evaluateRmb.title" />
</th>
<th property="needTeamPlaceLevel" title="<fmt:message key="web.gm.sysItems.col.needTeamPlaceLevel.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.needTeamPlaceLevel.title" />
</th>
<th property="timeForBuildingUp" title="<fmt:message key="web.gm.sysItems.col.timeForBuildingUp.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.timeForBuildingUp.title" />
</th>
<th property="timeForCreate" title="<fmt:message key="web.gm.sysItems.col.timeForCreate.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.timeForCreate.title" />
</th>
<th property="timeForBuild" title="<fmt:message key="web.gm.sysItems.col.timeForBuild.toolTips" />">
	<fmt:message key="web.gm.sysItems.col.timeForBuild.title" />
</th>
				</tr>
			</thead>
		</table>
    </div>
</div>