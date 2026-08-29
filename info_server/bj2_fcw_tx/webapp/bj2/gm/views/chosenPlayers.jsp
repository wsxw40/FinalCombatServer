<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" >
function initChosenPlayersController($scope){
	var table= initTable('#tableChosenPlayers',
			$('#app_root').val()+'/fcw/gm/players',
			[
			 {data:function(data, type, full, meta){
				 return '<span><input type="checkbox"/></span>';
			 },sortable:false},
			 {data:'id'},
			 {data:'userName'},
			 {data:'name'}
			],
			{leftColumns:2}
	);
	table.selectAll=function(){
		$('#tableChosenPlayers_wrapper').find('input:checkbox').attr('checked',true);
	};
	table.getSelected=function(){
		var ids=[];
		$('#tableChosenPlayers_wrapper').find('.DTFC_LeftWrapper').find('input:checkbox:checked').each(function(){
			ids.push($(this).closest('td').next('td').html());
		});
		return ids;
	};
	return table;
}
</script>	
<div class="box-body">
	<table id="tableChosenPlayers" data-datatable="" data-dt-options="dtOptions" data-dt-columns="dtColumns" 
		class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th property></th>
				<th property="id" title="<fmt:message key="web.gm.players.col.id.toolTips" />" class="search small-width">
					<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.id.title" />
				</th>
				<th property="userName"  title="<fmt:message key="web.gm.players.col.userName.toolTips" />" class="search normal-width">
					<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.userName.title" />
				</th>
				<th property="name" title="<fmt:message key="web.gm.players.col.name.toolTips" />"  class="search normal-width">
					<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.name.title" />
				</th>
			</tr>
		</thead>
	</table>
</div>