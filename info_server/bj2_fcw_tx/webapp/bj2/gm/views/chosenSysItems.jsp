<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initChosenSysItemsController($scope){
	var specialSearch={};
	var table=initTable('#tableChosenSysItems',
			$('#app_root').val()+'/fcw/gm/sysItems',
			[
			 {data:function(data, type, full, meta){
				 return '<span><input type="checkbox"/></span>';
			 },sortable:false},
			 {data:'id'},
			 {data:'name'},
			 {data:'displayName'},
			 {data:'isDeleted'},
			 {data:'resourceStable'}
			],
			{leftColumns:2},
			{},
			{},
			specialSearch
	);
	table.selectAll=function(){
		$('#tableChosenSysItems_wrapper').find('td span input[type="checkbox"]').attr('checked',true);
	};
	table.getSelected=function(){
		var ids=[];
		$('#tableChosenSysItems_wrapper').find('.DTFC_LeftWrapper').find('td span input[type="checkbox"]:checked').each(function(){
			ids.push($(this).closest('td').next('td').html());
		});
		return ids;
	};
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
	return table;
}
</script>

    <div class="box-body">
    	<p><jsp:include page="sysItemType.jsp"/></p>
        <table id="tableChosenSysItems" data-datatable="" data-dt-options="dtOptions" 
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property></th>
					<th property="id" title="<fmt:message key="web.gm.sysItems.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.id.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.sysItems.col.name.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.name.title" />
					</th>
					<th property="displayName" title="<fmt:message key="web.gm.sysItems.col.displayName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysItems.col.displayName.title" />
					</th>
					<th property="isDeleted" title="<fmt:message key="web.gm.sysItems.col.isDeleted.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.isDeleted.title" />
					</th>
					<th property="resourceStable" title="<fmt:message key="web.gm.sysItems.col.resourceStable.toolTips" />">
						<fmt:message key="web.gm.sysItems.col.resourceStable.title" />
					</th>
				</tr>
			</thead>
		</table>
    </div>
