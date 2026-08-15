<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="report.title.itemCost" /></title>
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
			<c:param name="parent" value="listItemCost" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.itemCost" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="report.title.dateshow" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate_is" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate_is" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="item.cost.msg.itemName" /> <input id="itemName"  style="width:120px" maxlength="20"/>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="item.cost.msg.itemID" /> <input id="itemID" data-options="min:0" style="width:80px" maxenlgth="20"/>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="item.cost.msg.itemType" /> <select id="itemType" class="easyui-combobox" style="width:100px;" maxlength="10">
								<option value="weapon"><spring:message code="item.cost.msg.itemType.weapon" /></option>
								<option value="clothes"><spring:message code="item.cost.msg.itemType.clothes" /></option>
								<option value="accessory"><spring:message code="item.cost.msg.itemType.accessory" /></option>
								<option value="item"><spring:message code="item.cost.msg.itemType.item" /></option>
								<option value="material"><spring:message code="item.cost.msg.itemType.material" /></option></select>
					
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="item.cost.msg.payType" /> <select id="costType" class="easyui-combobox" style="width:100px;" maxlength="10" />
								<option value="rmb"><spring:message code="item.cost.msg.payType.rmb" /></option></select>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doVarSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
					
				</div>
				<div>
				<table id="date_dg" title="" style="width:1620px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
					<thead>
						<tr>
							<th colspan="1" field="date" width="100" align="center" rowspan="2"><spring:message code="report.msg.date" /></th>
							<th colspan="1" field="itemName" width="80" align="center" rowspan="2"><spring:message code="item.cost.msg.itemName" /></th>
							<th colspan="1" field="itemId" width="80" align="center" rowspan="2"><spring:message code="item.cost.msg.itemID" /></th>
							<th colspan="1" field="itemType" width="80" align="center" rowspan="2"><spring:message code="item.cost.msg.itemType" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.cost" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.buy" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.renew" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.present" /></th>
						</tr>
						<tr>
							<th field="numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="b_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="b_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="b_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="r_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="r_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="r_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="p_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="p_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="p_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
						</tr>
					</thead>
					</table>
				</div>
			</div>
			<div title='<spring:message code="report.title.itemshow" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<div>
				<table id="dg" title="" style="width:1620px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
					<thead>
						<tr>
							<th colspan="1" field="itemName" width="100" align="center" rowspan="2"><spring:message code="item.cost.msg.itemName" /></th>
							<th colspan="1" field="itemId" width="80" align="center" rowspan="2"><spring:message code="item.cost.msg.itemID" /></th>
							<th colspan="1" field="itemType" width="80" align="center" rowspan="2"><spring:message code="item.cost.msg.itemType" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.cost" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.buy" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.renew" /></th>
							<th colspan="3" align="center"><spring:message code="item.cost.msg.present" /></th>
						</tr>
						<tr>
							<th field="numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="b_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="b_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="b_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="r_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="r_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="r_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
							<th field="p_numAmount" width="100" align="center"><spring:message code="item.cost.msg.costNumAmount" /></th>
							<th field="p_peopleAmount" width="100" align="center"><spring:message code="item.cost.msg.costPeopleAmount" /></th>	
							<th field="p_moneyAmount" width="120" align="center"><spring:message code="item.cost.msg.costMoneyAmount" /></th>
						</tr>
					</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	initSearchDate();
	var today = new Date();
	today.setDate(today.getDate()-1);
	var yesterday = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate();
	$("#startDate_is, #endDate_is").datebox("setValue", yesterday);
});

function getData() {
	var param = "startDate="+$("#startDate").datebox("getValue")+"&endDate="+$("#endDate").datebox("getValue");
	var url = "ajaxGetItemCostList.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var num, people, money,itemName,itemId,itemType;
		$.each(json,function(i, n){

			people = json[i].peopleAmount;
			num = json[i].numberAmount;
			money = json[i].moneyAmount;
			itemName=json[i].itemName;
			itemId=json[i].itemId;
			switch (json[i].itemType){
			case 1: itemType="weapon";break;
			case 2: itemType="clothes" ;break;
			case 3: itemType="accessory";break;
			case 4: itemType="item";break;
			case 5: itemType="material";break;
			}
		
			rows.push({
				itemName: itemName,
				itemId: itemId,
				itemType: itemType,
				numAmount: num,
				peopleAmount: people,
				moneyAmount: money,
				b_numAmount: json[i].buyNumberAmount,
				b_peopleAmount: json[i].buyPeopleAmount,
				b_moneyAmount: json[i].buyMoneyAmount,
				r_numAmount: json[i].renewNumberAmount,
				r_peopleAmount: json[i].renewPeopleAmount,
				r_moneyAmount: json[i].renewMoneyAmount,
				p_numAmount: json[i].giftNumberAmount,
				p_peopleAmount: json[i].giftPeopleAmount,
				p_moneyAmount: json[i].giftMoneyAmount
			});
		});
		$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows).show();
	});
}
function doSearch(){
	getData();
}

function doVarSearch(){
	var param = "startDate="+$("#startDate_is").datebox("getValue")+"&endDate="+$("#endDate_is").datebox("getValue")+"&itemName="+$("#itemName").val()
		+"&itemId="+$("#itemID").val()+"&itemType="+$("#itemType").combo("getValue")+"&payType="+$("#costType").combo("getValue");
	var url = "ajaxGetItemCostListByVar.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var num, people, money,itemName,itemId,itemType;
		$.each(json,function(i, n){
			people = json[i].peopleAmount;
			num = json[i].numberAmount;
			money = json[i].moneyAmount;
			itemName=json[i].itemName;
			itemId=json[i].itemId;
			switch (json[i].itemType) {
				case 1: itemType="weapon";break;
				case 2: itemType="clothes";break;
				case 3: itemType="accessory";break;
				case 4: itemType="item";break;
				case 5: itemType="material";break;
				default: itemType="";break;
			}

			rows.push({
				date: json[i].statisticDate,
				itemName: itemName,
				itemId: itemId,
				itemType: itemType,
				numAmount: num,
				peopleAmount: people,
				moneyAmount: money,
				b_numAmount: json[i].buyNumberAmount,
				b_peopleAmount: json[i].buyPeopleAmount,
				b_moneyAmount: json[i].buyMoneyAmount,
				r_numAmount: json[i].renewNumberAmount,
				r_peopleAmount: json[i].renewPeopleAmount,
				r_moneyAmount: json[i].renewMoneyAmount,
				p_numAmount: json[i].giftNumberAmount,
				p_peopleAmount: json[i].giftPeopleAmount,
				p_moneyAmount: json[i].giftMoneyAmount
			});
		});
		$('#date_dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows).show();
	});
}
</script>
</html>