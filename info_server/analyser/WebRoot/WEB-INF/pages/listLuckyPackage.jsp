<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="luckyPackage.title1" /></title>
	<link rel="stylesheet" type="text/css" href="css/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/locale/easyui-lang-en.js"></script>
</head>
<body class="easyui-layout">

	<c:forEach var="unitPrice" items="${unitPrices}" varStatus="count">
		<input id="${unitPrice.name}" type="hidden" value="${unitPrice.cost}">
	</c:forEach>

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px"><h2><spring:message code="system.title" /></h2></div>

	<div data-options="region:'west',split:true,title:'PDE Manager'" style="width:150px;padding:10px;">
		<c:import url="/WEB-INF/pages/listLeft.jsp">
			<c:param name="parent" value="listLuckyPackage" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="luckyPackage.title1" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="luckyPackage.title2" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:1100px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th colspan="3"><spring:message code="luckyPackage.options.cardType3" /></th>
						<th colspan="3"><spring:message code="luckyPackage.options.cardType2" /></th>
						<th colspan="3"><spring:message code="luckyPackage.options.cardType1" /></th>
					</tr>
					<tr>
						<th field="usingNumberGold" width="100" align="right"><spring:message code="luckyPackage.useCard.list.usingNumber" /></th>
						<th field="userNumberGold" width="100" align="right"><spring:message code="luckyPackage.useCard.list.userNumber" /></th>
						<th field="costGold" width="100" align="right"><spring:message code="luckyPackage.useCard.list.cost" /></th>
						
						<th field="usingNumberSilver" width="100" align="right"><spring:message code="luckyPackage.useCard.list.usingNumber" /></th>
						<th field="userNumberSilver" width="100" align="right"><spring:message code="luckyPackage.useCard.list.userNumber" /></th>
						<th field="costSilver" width="100" align="right"><spring:message code="luckyPackage.useCard.list.cost" /></th>
						
						<th field="usingNumberCopper" width="100" align="right"><spring:message code="luckyPackage.useCard.list.usingNumber" /></th>
						<th field="userNumberCopper" width="100" align="right"><spring:message code="luckyPackage.useCard.list.userNumber" /></th>
						<th field="costCopper" width="100" align="right"><spring:message code="luckyPackage.useCard.list.cost" /></th>
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
	var url = "ajaxGetLuckyPackage.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		$.each(json,function(i, n){
			rows.push({
				date: json[i].statisticDate,
				usingNumberGold: json[i].usingNumberGold,
				userNumberGold: json[i].userNumberGold,
				costGold: json[i].usingNumberGold * $("#goldCard").val(),
				usingNumberSilver: json[i].usingNumberSilver,
				userNumberSilver: json[i].userNumberSilver,
				costSilver: json[i].usingNumberSilver * $("#silverCard").val(),
				usingNumberCopper: json[i].usingNumberCopper,
				userNumberCopper: json[i].userNumberCopper,
				costCopper: json[i].usingNumberCopper * $("#bronzeCard").val()
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