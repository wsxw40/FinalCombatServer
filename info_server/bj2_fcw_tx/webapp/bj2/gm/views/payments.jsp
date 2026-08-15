<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initPaymentsController($scope){
	initTable('#tablePayments',
			$('#app_root').val()+'/fcw/gm/payments',
			[{data:'id'},
			 {data:'itemId'},
			 {data:'payType'},
			 {data:'unitType'},
			 {data:'cost'},
			 {data:'unit'},
			 {data:'isShow'},
			 {data:'level'},
			 {data:'finishPayType'},
			 {data:'finishCost'}
			],
			{leftColumns:2},
			{add:$('#app_root').val()+'/fcw/#/payment',
				edit:$('#app_root').val()+'/fcw/#/payment',
				'delete':$('#app_root').val()+'/fcw/gm/deletePayment',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WPayment'}
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
        <table id="tablePayments" data-datatable="" data-dt-options="dtOptions" rowFormId="#formPayment"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.payments.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.payments.col.id.title" />
					</th>
					<th property="itemId" title="<fmt:message key="web.gm.payments.col.itemId.toolTips" />"class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.payments.col.itemId.title" />
					</th>
<th property="payType" title="<fmt:message key="web.gm.payments.col.payType.toolTips" />">
	<fmt:message key="web.gm.payments.col.payType.title" />
</th>
<th property="unitType" title="<fmt:message key="web.gm.payments.col.unitType.toolTips" />">
	<fmt:message key="web.gm.payments.col.unitType.title" />
</th>
<th property="cost" title="<fmt:message key="web.gm.payments.col.cost.toolTips" />">
	<fmt:message key="web.gm.payments.col.cost.title" />
</th>
<th property="unit" title="<fmt:message key="web.gm.payments.col.unit.toolTips" />">
	<fmt:message key="web.gm.payments.col.unit.title" />
</th>
<th property="isShow" title="<fmt:message key="web.gm.payments.col.isShow.toolTips" />">
	<fmt:message key="web.gm.payments.col.isShow.title" />
</th>
<th property="level" title="<fmt:message key="web.gm.payments.col.level.toolTips" />">
	<fmt:message key="web.gm.payments.col.level.title" />
</th>
<th property="finishPayType" title="<fmt:message key="web.gm.payments.col.finishPayType.toolTips" />">
	<fmt:message key="web.gm.payments.col.finishPayType.title" />
</th>
<th property="finishCost" title="<fmt:message key="web.gm.payments.col.finishCost.toolTips" />">
	<fmt:message key="web.gm.payments.col.finishCost.title" />
</th>					
				</tr>
			</thead>
		</table>
    </div>
</div>