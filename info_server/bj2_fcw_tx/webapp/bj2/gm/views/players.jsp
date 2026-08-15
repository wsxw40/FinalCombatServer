<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" >
function initPlayersController($scope){
	initTable('#tablePlayers',
			$('#app_root').val()+'/fcw/gm/players',
			[{data:'id'},
			 {data:'userName'},
			 {data:'name'},
			 {data:'icon'},
			 {data:'exp'},
			 {data:'rank'},
			 {data:'isVip'},
			 {data:'vipExp'},
			 {data:'gPoint'},
			 {data:'gScore'},
			 {data:'gold'},
			 {data:'silver'},
			 {data:'bronze'},
			 {data:'lastLoginTime'},
			 {data:'lastLoginIp'},
			 {data:'lastLogoutTime'}
			],
			{leftColumns:3},
			{edit:$('#app_root').val()+'/fcw/#/player',
				gear:$('#app_root').val()+'/fcw/#/playerItems'
			}
	);
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
<%-- 		<h3 class="box-title"><fmt:message key="web.gm.detail.players" /></h3> --%>
		<button type="button" id="edit" class="btn btn-info" >
			<i class="fa fa-edit"></i>&nbsp;<fmt:message key="web.gm.button.edit"/>
		</button>
		<button type="button" id="gear" class="btn btn-info">
			<i class="fa fa-gear"></i>&nbsp;<fmt:message key="web.gm.button.item"/>
		</button>
<!-- 			<button type="button" class="btn btn-info" title="Remove"> -->
<!-- 				<i class="fa fa-remove"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-eyedropper"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-ils"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-medium"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-heart"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-star"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-th"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-check"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-search-plus"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-power-off"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-signal"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-gear"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-trash"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-file"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-download"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-flag"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-code"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-tag"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-book"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-share"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-minus"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-comment"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-key"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-unlock"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-wrench"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-table"></i> -->
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-info" title="Other"> -->
<!-- 				<i class="fa fa-database"></i> -->
<!-- 			</button> -->
	</div>
	<div class="box-body">
		<table id="tablePlayers" data-datatable="" data-dt-options="dtOptions" data-dt-columns="dtColumns" rowFormId="#formPlayer"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th property="id" title="<fmt:message key="web.gm.players.col.id.toolTips" />" class="search small-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.id.title" />
					</th>
					<th property="userName"  title="<fmt:message key="web.gm.players.col.userName.toolTips" />" class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.userName.title" />
					</th>
					<th property="name" title="<fmt:message key="web.gm.players.col.name.toolTips" />"  class="search normal-width">
						<i class="fa fa-search"></i>&nbsp;<fmt:message key="web.gm.players.col.name.title" />
					</th>
					<th property="icon" title="<fmt:message key="web.gm.players.col.icon.toolTips" />">
						<fmt:message key="web.gm.players.col.icon.title" />
					</th>
					<th property="exp" title="<fmt:message key="web.gm.players.col.exp.toolTips" />">
						<fmt:message key="web.gm.players.col.exp.title" />
					</th>
					<th property="rank" title="<fmt:message key="web.gm.players.col.rank.toolTips" />">
						<fmt:message key="web.gm.players.col.rank.title" />
					</th>
					<th property="isVip" title="<fmt:message key="web.gm.players.col.isVip.toolTips" />">
						<fmt:message key="web.gm.players.col.isVip.title" />
					</th>
					<th property="vipExperience" title="<fmt:message key="web.gm.players.col.vipExperience.toolTips" />">
						<fmt:message key="web.gm.players.col.vipExperience.title" />
					</th>
					<th property="gPoint" title="<fmt:message key="web.gm.players.col.gPoint.toolTips" />">
						<fmt:message key="web.gm.players.col.gPoint.title" />
					</th>
					<th property="generalScore" title="<fmt:message key="web.gm.players.col.generalScore.toolTips" />">
						<fmt:message key="web.gm.players.col.generalScore.title" />
					</th>
					<th property="gold" title="<fmt:message key="web.gm.players.col.gold.toolTips" />">
						<fmt:message key="web.gm.players.col.gold.title" />
					</th>
					<th property="silver" title="<fmt:message key="web.gm.players.col.silver.toolTips" />">
						<fmt:message key="web.gm.players.col.silver.title" />
					</th>
					<th property="bronze" title="<fmt:message key="web.gm.players.col.bronze.toolTips" />">
						<fmt:message key="web.gm.players.col.bronze.title" />
					</th>
					<th property="lastLoginTime" title="<fmt:message key="web.gm.players.col.lastLoginTime.toolTips" />" class="normal-width">
						<fmt:message key="web.gm.players.col.lastLoginTime.title" />
					</th>
					<th property="lastLoginIp" title="<fmt:message key="web.gm.players.col.lastLoginIp.toolTips" />"class="normal-width">
						<fmt:message key="web.gm.players.col.lastLoginIp.title" />
					</th>
					<th property="lastLogoutTime" title="<fmt:message key="web.gm.players.col.lastLogoutTime.toolTips" />"class="normal-width">
						<fmt:message key="web.gm.players.col.lastLogoutTime.title" />
					</th>
				</tr>
			</thead>
		</table>
	</div>
</div>