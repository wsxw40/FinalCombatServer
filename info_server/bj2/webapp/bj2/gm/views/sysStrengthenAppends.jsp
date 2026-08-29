<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysStrengthenAppendsController($scope){
	initTable('#tableSysStrengthenAppends',
			$('#app_root').val()+'/fcw/gm/sysStrengthenAppends',
			[{data:'id'},
			 {data:'level'},
			 {data:'type'},
			 {data:'property1'},
			 {data:'property2'},
			 {data:'streNum'},
			 {data:'streGr'},
			 {data:'winRate'},
			 {data:'falseKeepRate'},
			 {data:'falseFallRate'},
			 {data:'falseRuinRate'},
			 {data:'holeWinRate'},
			 {data:'switchFallRate'}
			],
			{leftColumns:1},
			{add:$('#app_root').val()+'/fcw/#/sysStrengthenAppend',
				edit:$('#app_root').val()+'/fcw/#/sysStrengthenAppend',
				'delete':$('#app_root').val()+'/fcw/gm/deleteSysStrengthenAppend',
				transactions:$('#app_root').val()+'/fcw/#/gmTransactions',
				modelName:'WSysStrengthenAppend'}
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
        <table id="tableSysStrengthenAppends" data-datatable="" data-dt-options="dtOptions" rowFormId="#formSysStrengthenAppend"
               data-dt-columns="dtColumns" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.sysStrengthenAppends.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.sysStrengthenAppends.col.id.title" />
					</th>
<th property="level" title="<fmt:message key="web.gm.sysStrengthenAppends.col.level.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.level.title" />
</th>
<th property="type" title="<fmt:message key="web.gm.sysStrengthenAppends.col.type.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.type.title" />
</th>
<th property="property1" title="<fmt:message key="web.gm.sysStrengthenAppends.col.property1.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.property1.title" />
</th>
<th property="property2" title="<fmt:message key="web.gm.sysStrengthenAppends.col.property2.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.property2.title" />
</th>
<th property="streNum" title="<fmt:message key="web.gm.sysStrengthenAppends.col.streNum.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.streNum.title" />
</th>
<th property="streGr" title="<fmt:message key="web.gm.sysStrengthenAppends.col.streGr.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.streGr.title" />
</th>
<th property="winRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.winRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.winRate.title" />
</th>
<th property="falseKeepRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseKeepRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.falseKeepRate.title" />
</th>
<th property="falseFallRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseFallRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.falseFallRate.title" />
</th>
<th property="falseRuinRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.falseRuinRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.falseRuinRate.title" />
</th>
<th property="holeWinRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.holeWinRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.holeWinRate.title" />
</th>
<th property="switchFallRate" title="<fmt:message key="web.gm.sysStrengthenAppends.col.switchFallRate.toolTips" />">
	<fmt:message key="web.gm.sysStrengthenAppends.col.switchFallRate.title" />
</th>		
				</tr>
			</thead>
		</table>
    </div>
</div>