<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmLogsController($scope){
	initTable('#tableGmLogs',
			$('#app_root').val()+'/fcw/gm/gmLogs',
			[{data:'id'},
			 {data:'gmUserId'},
			 {data:'gmUserName'},
			 {data:'type'},
			 {data:'recordTime'},
			 {data:'methodName'}
			],
			{leftColumns:2},
			{transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				rollBack:$('#app_root').val()+'/fcw/gm/backLog'
				},
			[[4,'desc']]
	);
}
</script>
<div class="box box-primary">
    <div class="box-header with-border">
		<button type="button" id="logTransactions" class="btn btn-info" >
			<i class="fa fa-th"></i>&nbsp;<fmt:message key="web.gm.button.logTransactions"/>
		</button>
		<button type="button" id="rollBack" class="btn btn-info" >
			<i class="fa fa-database"></i>&nbsp;<fmt:message key="web.gm.button.rollBack"/>
		</button>
    </div>
    <div class="box-body">
        <table id="tableGmLogs" data-datatable="" data-dt-options="dtOptions" rowFormId="#formGmTransactions"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.gmLogs.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.id.title" />
					</th>
					<th property="gmUserId" title="<fmt:message key="web.gm.gmLogs.col.gmUserId.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.gmUserId.title" />
					</th>
					<th property="gmUserName" title="<fmt:message key="web.gm.gmLogs.col.gmUserName.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.gmUserName.title" />
					</th>
					<th property="type" title="<fmt:message key="web.gm.gmLogs.col.type.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.type.title" />
					</th>
					<th property="recordTime" title="<fmt:message key="web.gm.gmLogs.col.recordTime.toolTips" />" class="small-width">
						<fmt:message key="web.gm.gmLogs.col.recordTime.title" />
					</th>
					<th property="methodName" title="<fmt:message key="web.gm.gmLogs.col.methodName.toolTips" />">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.gmLogs.col.methodName.title" />						
					</th>
				</tr>
			</thead>
		</table>
    </div>
</div>