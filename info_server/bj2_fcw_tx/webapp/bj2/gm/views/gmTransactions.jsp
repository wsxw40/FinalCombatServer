<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmTransactionsController($scope){
	var specialSearch={};
	if(undefined != localStorage['gmTransactionsSpecialSearch']){
		try{
			specialSearch=JSON.parse(localStorage['gmTransactionsSpecialSearch']);			
		}catch(e){
			specialSearch={};
		}
	}
	initTable('#tableGmTransactions',
			$('#app_root').val()+'/fcw/gm/gmTransactions',
			[{data:'id'},
			 {data:'gmUserId'},
			 {data:'modelName'},
			 {data:'modelId'},
			 {data:'createTime'},
// 			 {data:'history'}
			 {data:function(data, type, full, meta){
				 var obj={};
				 try{
					 obj=JSON.parse(data.history);					 
				 }catch(e){}
				 var str='';
				 for(var i in obj){
					 str+=i+':['+obj[i]+'] , ';
				 }
				 return '<span class="large-width" style="width:auto;" title="'+str+'">'+str+'</span>';
			 }}
			],
			{leftColumns:4},
			{rollBack:$('#app_root').val()+'/fcw/gm/backTransaction'},
			[[4,'desc']],
			specialSearch
	);
}
</script>
<div class="box box-primary">
    <div class="box-header with-border">
		<button type="button" id="rollBack" class="btn btn-info" >
			<i class="fa fa-database"></i>&nbsp;<fmt:message key="web.gm.button.rollBack"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableGmTransactions" data-datatable="" data-dt-options="dtOptions"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.gmLogs.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.id.title" />
					</th>
					<th property="gmUserId" title="<fmt:message key="web.gm.gmTransactions.col.gmUserId.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmTransactions.col.gmUserId.title" />
					</th>
					<th property="modelName" title="<fmt:message key="web.gm.gmTransactions.col.modelName.toolTips" />"class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmTransactions.col.modelName.title" />
					</th>
					<th property="modelId" title="<fmt:message key="web.gm.gmTransactions.col.modelId.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmTransactions.col.modelId.title" />
					</th>
					<th property="createTime" title="<fmt:message key="web.gm.gmTransactions.col.createTime.toolTips" />" class="search normal-width">
						<fmt:message key="web.gm.gmTransactions.col.createTime.title" />
					</th>
					<th property="history" title="<fmt:message key="web.gm.gmTransactions.col.history.toolTips" />">
						<fmt:message key="web.gm.gmTransactions.col.history.title" />
					</th>
				</tr>
			</thead>
		</table>
    </div>
</div>