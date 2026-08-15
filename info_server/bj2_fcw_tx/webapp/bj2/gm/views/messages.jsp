<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initMessagesController($scope){
	initTable('#tableMessages',
			$('#app_root').val()+'/fcw/gm/messages',
			[{data:'id'},
			 {data:'senderId'},
			 {data:'senderName'},
			 {data:'receiverId'},
			 {data:'isDeleted'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.subject+'">'+data.subject+'</span>';
			 }},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.content+'">'+data.content+'</span>';
			 }},
			 {data:'createdTime'},
			 {data:'open'},
			 {data:'isAttached'},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.sysItems+'">'+data.sysItems+'</span>';
			 }},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.itemUnits+'">'+data.itemUnits+'</span>';
			 }},
			 {data:function(data, type, full, meta){
				 return '<span class="large-width" title="'+data.itemUnittypes+'">'+data.itemUnittypes+'</span>';
			 }}
			],
			{leftColumns:4},
			{add:$('#app_root').val()+'/fcw/#/message',
				remove:$('#app_root').val()+'/fcw/gm/removeMessage'}
	);
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
		<button type="button" id="add" class="btn btn-info" >
			<i class="fa fa-plus"></i>&nbsp;<fmt:message key="web.gm.button.add"/>
		</button>
		<button type="button" id="remove" class="btn btn-info" >
			<i class="fa fa-remove"></i>&nbsp;<fmt:message key="web.gm.button.remove"/>
		</button>
	</div>
	<div class="box-body">
		<table id="tableMessages" data-datatable="" data-dt-options="dtOptions" data-dt-columns="dtColumns" rowFormId="#formMessage"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.messages.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.messages.col.id.title" />
					</th>
					<th property="senderId" title="<fmt:message key="web.gm.messages.col.senderId.toolTips" />"class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.messages.col.senderId.title" />
					</th>
					<th property="senderName" title="<fmt:message key="web.gm.messages.col.senderName.toolTips" />"class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.messages.col.senderName.title" />
					</th>
					<th property="receiverId" title="<fmt:message key="web.gm.messages.col.receiverId.toolTips" />"class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.messages.col.receiverId.title" />
					</th>
					<th property="isDeleted" title="<fmt:message key="web.gm.messages.col.isDeleted.toolTips" />">
						<fmt:message key="web.gm.messages.col.isDeleted.title" />
					</th>
					<th property="subject" title="<fmt:message key="web.gm.messages.col.subject.toolTips" />">
						<fmt:message key="web.gm.messages.col.subject.title" />
					</th>
					<th property="content" title="<fmt:message key="web.gm.messages.col.content.toolTips" />">
						<fmt:message key="web.gm.messages.col.content.title" />
					</th>
					<th property="createdTime" title="<fmt:message key="web.gm.messages.col.createdTime.toolTips" />" class="search normal-width">
						<fmt:message key="web.gm.messages.col.createdTime.title" />
					</th>
					<th property="open" title="<fmt:message key="web.gm.messages.col.open.toolTips" />">
						<fmt:message key="web.gm.messages.col.open.title" />
					</th>
					<th property="isAttached" title="<fmt:message key="web.gm.messages.col.isAttached.toolTips" />">
						<fmt:message key="web.gm.messages.col.isAttached.title" />
					</th>
					<th property="sysItems" title="<fmt:message key="web.gm.messages.col.sysItems.toolTips" />">
						<fmt:message key="web.gm.messages.col.sysItems.title" />
					</th>
					<th property="itemUnits" title="<fmt:message key="web.gm.messages.col.itemUnits.toolTips" />">
						<fmt:message key="web.gm.messages.col.itemUnits.title" />
					</th>
					<th property="itemUnittypes" title="<fmt:message key="web.gm.messages.col.itemUnittypes.toolTips" />">
						<fmt:message key="web.gm.messages.col.itemUnittypes.title" />
					</th>					
				</tr>
			</thead>
		</table>
	</div>
</div>