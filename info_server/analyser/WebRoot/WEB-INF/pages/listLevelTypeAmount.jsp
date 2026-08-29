<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="level.title" /><spring:message code="character.title1" /></title>
	<link rel="stylesheet" type="text/css" href="css/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/locale/easyui-lang-en.js"></script>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px"><h2><spring:message code="system.title" /></h2></div>

	<div data-options="region:'west',split:true,title:'PDE Manager'" style="width:150px;padding:10px;">
		<c:import url="/WEB-INF/pages/listLeft.jsp">
			<c:param name="parent" value="listLevelTypeAmount" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="level.title" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="character.title1" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:2020px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th colspan="2"><spring:message code="level.list.team" /></th>
						<th colspan="2"><spring:message code="level.list.target" /></th>
						<th colspan="2"><spring:message code="level.list.deliver" /></th>
						<th colspan="2"><spring:message code="level.list.newtrain" /></th>
						<th colspan="2"><spring:message code="level.list.destory" /></th>
						<th colspan="2"><spring:message code="level.list.hitboss" /></th>
						<th colspan="2"><spring:message code="level.list.knife" /></th>
						<th colspan="2"><spring:message code="level.list.blast" /></th>
						<th colspan="2"><spring:message code="level.list.streetboy" /></th>
						<th colspan="2"><spring:message code="level.list.biochemical" /></th>
						<th colspan="2"><spring:message code="level.list.boss2" /></th>
						<th colspan="2"><spring:message code="level.list.biochemical2" /></th>
						<th colspan="2"><spring:message code="level.list.hitboss2" /></th>
					</tr>
					<tr>
						<th field="team" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="team_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="target" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="target_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="deliver" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="deliver_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="newtrain" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="newtrain_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="destory" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="destory_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="hitboss" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="hitboss_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="knife" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="knife_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="blast" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="blast_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="streetboy" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="streetboy_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="biochemical" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="biochemical_rate" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						
						<th field="boss2" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="boss2_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="biochemical2" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="biochemical2_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="hitboss2" width="100" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="hitboss2_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
					</tr>
				</thead>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	initSearchDate();
});

function getData() {
	var param = "startDate="+$("#startDate").datebox("getValue")+"&endDate="+$("#endDate").datebox("getValue");
	var url = "ajaxGetLevelTypeAmount.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var team, target, deliver, newtrain, destory, hitboss, knife, blast, streetboy, biochemical, boss2, biochemical2, hitboss2, sum = 0;
		$.each(json,function(i, n){
//			console.log(json[i].statisticDate+" && "+json[i].characterA);
			team = json[i].team;
			target = json[i].target;
			deliver = json[i].deliver;
			newtrain = json[i].newtrain;
			destory = json[i].destory;
			hitboss = json[i].hitboss;
			knife = json[i].knife;
			blast = json[i].blast;
			streetboy = json[i].streetboy;
			biochemical = json[i].biochemical;
			boss2 = json[i].boss2;
			biochemical2 = json[i].biochemical2;
			hitboss2 = json[i].hitboss2;
			sum = target + deliver + newtrain + destory + hitboss + knife + blast + streetboy + biochemical + boss2 + biochemical2 + hitboss2;
			if (sum<1) sum = 1;
			rows.push({
				date: json[i].statisticDate,
				team: team,
				team_rate: (team*100 / sum).toFixed(2),
				target: target,
				target_rate: (target*100 / sum).toFixed(2),
				deliver: deliver,
				deliver_rate: (deliver*100 / sum).toFixed(2),
				newtrain: newtrain,
				newtrain_rate: (newtrain*100 / sum).toFixed(2),
				destory: destory,
				destory_rate: (destory*100 / sum).toFixed(2),
				hitboss: hitboss,
				hitboss_rate: (hitboss*100 / sum).toFixed(2),
				knife: knife,
				knife_rate: (knife*100 / sum).toFixed(2),
				blast: blast,
				blast_rate: (blast*100 / sum).toFixed(2),
				streetboy: streetboy,
				streetboy_rate: (streetboy*100 / sum).toFixed(2),
				biochemical: biochemical,
				biochemical_rate: (biochemical*100 / sum).toFixed(2),
				boss2: boss2,
				boss2_rate: (boss2*100 / sum).toFixed(2),
				biochemical2: biochemical2,
				biochemical2_rate: (biochemical2*100 / sum).toFixed(2),
				hitboss2: hitboss2,
				hitboss2_rate: (hitboss2*100 / sum).toFixed(2)
			});
		});
		$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows).show();
	});
}
function doSearch(){
	getData();
}
</script>
</html>