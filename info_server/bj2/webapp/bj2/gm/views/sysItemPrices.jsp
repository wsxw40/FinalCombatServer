<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysItemPricesController($scope){
	initTable('#tableSysItemPrices',
			$('#app_root').val()+'/fcw/gm/sysItemPrices',
			[{data:'id'},
			 {data:'sysItemDisplayName'},
			 {data:'sysItemId'},
			 {data:'payType'},
			 {data:'unitType'},
			 {data:'cost'},
			 {data:'unit'},
			 {data:'isTarget'},
			 {data:'payGroup'},
			 {data:'multiType'},
			 {data:'level'},
			 {data:'vipLevel'},
			 {data:'weightRate'},
			 {data:'probability'},
			 {data:'createTime'},
			 {data:'updateTime'},
			],
			{leftColumns:3},
			{add:$('#app_root').val()+'/fcw/#/sysItemPrice',
				edit:$('#app_root').val()+'/fcw/#/sysItemPrice',
				remove:$('#app_root').val()+'/fcw/gm/removeSysItemPrice',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysItemPrice'}
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
        <table id="tableSysItemPrices" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysItemPrice"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysItemPrices.col.id.toolTips" />"  class="small-width">
						<fmt:message key="web.gm.sysItemPrices.col.id.title" />
					</th>
			
<th property="sysItemDisplayName" title="<fmt:message key="web.gm.sysItemPrices.col.sysItemDisplayName.toolTips" />" class="search large-width">
	<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItemPrices.col.sysItemDisplayName.title" />
</th>		
<th property="sysItemId" title="<fmt:message key="web.gm.sysItemPrices.col.sysItemId.toolTips" />"class="search normal-width">
	<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItemPrices.col.sysItemId.title" />
</th>
<th property="payType" title="<fmt:message key="web.gm.sysItemPrices.col.payType.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.payType.title" />
</th>
<th property="unitType" title="<fmt:message key="web.gm.sysItemPrices.col.unitType.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.unitType.title" />
</th>
<th property="cost" title="<fmt:message key="web.gm.sysItemPrices.col.cost.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.cost.title" />
</th>
<th property="unit" title="<fmt:message key="web.gm.sysItemPrices.col.unit.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.unit.title" />
</th>
<th property="isTarget" title="<fmt:message key="web.gm.sysItemPrices.col.isTarget.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.isTarget.title" />
</th>
<th property="payGroup" title="<fmt:message key="web.gm.sysItemPrices.col.payGroup.toolTips" />"class="search normal-width">
	<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItemPrices.col.payGroup.title" />
</th>
<th property="multiType" title="<fmt:message key="web.gm.sysItemPrices.col.multiType.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.multiType.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.sysItemPrices.col.level.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.level.title" />
</th>
<th property="vipLevel" title="<fmt:message key="web.gm.sysItemPrices.col.vipLevel.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.vipLevel.title" />
</th>
<th property="weightRate" title="<fmt:message key="web.gm.sysItemPrices.col.weightRate.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.weightRate.title" />
</th>
<th property="probability" title="<fmt:message key="web.gm.sysItemPrices.col.probability.toolTips" />">
	<fmt:message key="web.gm.sysItemPrices.col.probability.title" />
</th>
<th property="createTime" title="<fmt:message key="web.gm.sysItemPrices.col.createTime.toolTips" />"class="normal-width">
	<fmt:message key="web.gm.sysItemPrices.col.createTime.title" />
</th>
<th property="updateTime" title="<fmt:message key="web.gm.sysItemPrices.col.updateTime.toolTips" />"class="normal-width">
	<fmt:message key="web.gm.sysItemPrices.col.updateTime.title" />
</th>				
				</tr>
			</thead>
		</table>
    </div>
</div>