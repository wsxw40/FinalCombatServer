<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="report.title.gpointTotal" /></title>
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
			<c:param name="parent" value="listGPointTotal" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.gpointTotal" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="character.title1" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="range1" name="range" type="radio" checked="checked" onclick="javascript:showByRange(1);" value="1"><spring:message code="gpointTotal.title.range1" />
					<input id="range2" name="range" type="radio" onclick="javascript:showByRange(2);" value="2"><spring:message code="gpointTotal.title.range2" />
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:1000px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th field="totalGpoint" rowspan="2"><spring:message code="gpointTotal.list.totalGpoint" /></th>
						<th field="totalPlayer" rowspan="2"><spring:message code="gpointTotal.list.totalPlayer" /></th>
						<th colspan="12"><spring:message code="gpointTotal.list.Distribution" /></th>
					</tr>
					<tr>
						<th field="amount1" width="100" align="center"><spring:message code="gpointTotal.list.amount1" /></th>
						<th field="amount2" width="100" align="center"><spring:message code="gpointTotal.list.amount2" /></th>
						<th field="amount3" width="100" align="center"><spring:message code="gpointTotal.list.amount3" /></th>
						<th field="amount4" width="100" align="center"><spring:message code="gpointTotal.list.amount4" /></th>
						<th field="amount5" width="100" align="center"><spring:message code="gpointTotal.list.amount5" /></th>
						<th field="amount6" width="100" align="center"><spring:message code="gpointTotal.list.amount6" /></th>
						<th field="amount7" width="100" align="center"><spring:message code="gpointTotal.list.amount7" /></th>
						<th field="amount8" width="100" align="center"><spring:message code="gpointTotal.list.amount8" /></th>
						<th field="amount9" width="100" align="center"><spring:message code="gpointTotal.list.amount9" /></th>
						<th field="amount10" width="100" align="center"><spring:message code="gpointTotal.list.amount10" /></th>
						<th field="amount11" width="100" align="center"><spring:message code="gpointTotal.list.amount11" /></th>
						<th field="amount12" width="100" align="center"><spring:message code="gpointTotal.list.amount12" /></th>
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
	var url = "ajaxGetGPointTotal.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		$.each(json,function(i, n){
			rows.push({
				date: json[i].statisticDate,
				totalGpoint: json[i].totalGPoint,
				totalPlayer: json[i].totalPlayer,
				amount1: json[i].amount1,
				amount2: json[i].amount2,
				amount3: json[i].amount3,
				amount4: json[i].amount4,
				amount5: json[i].amount5,
				amount6: json[i].amount6,
				amount7: json[i].amount7,
				amount8: json[i].amount8,
				amount9: json[i].amount9,
				amount10: json[i].amount10,
				amount11: json[i].amount11,
				amount12: json[i].amount12
			});
		});
		$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows);
		showByRange($("input:radio[name='range']:checked").val());
	});
}
function doSearch(){
	getData();
}
function showByRange(range){
	var tds1 = new Array($("td[field='amount1']"),$("td[field='amount2']"),$("td[field='amount3']"),$("td[field='amount4']"),$("td[field='amount5']"));
	var tds2 = new Array($("td[field='amount6']"),$("td[field='amount7']"),$("td[field='amount8']"),$("td[field='amount9']"),$("td[field='amount10']"),$("td[field='amount11']"),$("td[field='amount12']"));
	for (var i=0; i<tds1.length; i++) {
		if (range==1)
			tds1[i].show();
		else if (range==2)
			tds1[i].hide();
	}
	for (var i=0; i<tds2.length; i++) {
		if (range==1)
			tds2[i].hide();
		else if (range==2)
			tds2[i].show();
	}
}
</script>
</html>