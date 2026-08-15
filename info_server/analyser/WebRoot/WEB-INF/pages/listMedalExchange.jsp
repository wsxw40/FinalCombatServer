<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="report.title.medalExchange" /></title>
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
			<c:param name="parent" value="listMedalExchange" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.medalExchange" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="medal.exchange.showCost" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:2020px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th colspan="2"><spring:message code="medal.list.goldbox" /></th>
						<th colspan="2"><spring:message code="medal.list.silverbox" /></th>
						<th colspan="2"><spring:message code="medal.list.bronzebox" /></th>
						<th colspan="2"><spring:message code="medal.list.10000coins" /></th>
						<th colspan="2"><spring:message code="medal.list.strengthUnit" /></th>
						<th colspan="2"><spring:message code="medal.list.refitUnit" /></th>
						<th colspan="2"><spring:message code="medal.list.addSuccess" /></th>
						<th field="total" width="100" rowspan="2"><spring:message code="medal.list.costtotle" /></th>
					</tr>
					<tr>
						<th field="goldbox" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="goldbox_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="silverbox" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="silverbox_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="bronzebox" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="bronzebox_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="coins10000" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="coins10000_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="strengthUnit" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="strengthUnit_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="refitUnit" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="refitUnit_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="addSuccess" width="70" align="center"><spring:message code="report.msg.using.amount.medal" /></th>
						<th field="addSuccess_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>

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
	var url = "ajaxGetMedalExchangeList.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var gold, silver, bronze, strength,refit, addsuccess, coins, sum = 0;
		$.each(json,function(i, n){
//			console.log(json[i].statisticDate+" && "+json[i].characterA);

			gold = json[i].medalForGoldBox;
			silver = json[i].medalForSilverBox;
			bronze = json[i].medalForBronzeBox;
			strength = json[i].medalForStrengthUnit;
			refit = json[i].medalForRefitUnit;
			addsuccess = json[i].medalForAddSuccess;
			coins = json[i].medalForTenThsConis;
			
			sum = gold + silver + bronze + strength + refit + addsuccess + coins ;
			if (sum<1) sum = 1;
			rows.push({
				date: json[i].statisticDate,
				goldbox: gold,
				goldbox_rate: (gold*100 / sum).toFixed(2),
				silverbox: silver,
				silverbox_rate: (silver*100 / sum).toFixed(2),
				bronzebox: bronze,
				bronzebox_rate: (bronze*100 / sum).toFixed(2),
				coins10000: coins,
				coins10000_rate: (coins*100 / sum).toFixed(2),
				strengthUnit: strength,
				strengthUnit_rate: (strength*100 / sum).toFixed(2),
				refitUnit: refit,
				refitUnit_rate: (refit*100 / sum).toFixed(2),
				addSuccess: addsuccess,
				addSuccess_rate: (addsuccess*100 / sum).toFixed(2),
				total:sum
			});
		});
//		return rows;
		$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows).show();
	});
}
function doSearch(){
	getData();
}
</script>
</html>