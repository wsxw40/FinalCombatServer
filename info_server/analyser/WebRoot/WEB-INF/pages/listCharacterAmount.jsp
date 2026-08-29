<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="character.title1" /></title>
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
			<c:param name="parent" value="listCharacterAmount" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.character" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="character.title1" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="character.condition.model" />: 
					<select id="type" class="easyui-combobox">
						<option value=""><spring:message code="report.msg.all" /></option>
						<option value="0"><spring:message code="character.type0.name" /></option>
						<option value="1"><spring:message code="character.type1.name" /></option>
						<option value="2"><spring:message code="character.type2.name" /></option>
						<option value="3"><spring:message code="character.type3.name" /></option>
						<option value="4"><spring:message code="character.type4.name" /></option>
						<option value="5"><spring:message code="character.type5.name" /></option>
						<option value="6"><spring:message code="character.type6.name" /></option>
						<option value="7"><spring:message code="character.type7.name" /></option>
						<option value="8"><spring:message code="character.type8.name" /></option>
						<option value="9"><spring:message code="character.type9.name" /></option>
						<option value="10"><spring:message code="character.type10.name" /></option>
						<option value="11"><spring:message code="character.type11.name" /></option>
						<option value="12"><spring:message code="character.type12.name" /></option>
					</select>
					
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="character.condition.mapName" />: 
					<select id="displayName" class="easyui-combobox">
						<option value=""><spring:message code="report.msg.all" /></option>
							<c:forEach var="level" items="${levels}" varStatus="count">
								<option value="${level}">${level}</option>
							</c:forEach>
					</select>

					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:1300px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th colspan="2"><spring:message code="character.characterA.name" /></th>
						<th colspan="2"><spring:message code="character.characterB.name" /></th>
						<th colspan="2"><spring:message code="character.characterC.name" /></th>
						<th colspan="2"><spring:message code="character.characterD.name" /></th>
						<th colspan="2"><spring:message code="character.characterE.name" /></th>
						<th colspan="2"><spring:message code="character.characterF.name" /></th>
						<th colspan="2"><spring:message code="character.characterG.name" /></th>
					</tr>
					<tr>							
						<th field="characterA" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>						
						<th field="characterA_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterB" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterB_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterC" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterC_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterD" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterD_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterE" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterE_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterF" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterF_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="characterG" width="80" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="characterG_rate" width="80" align="center"><spring:message code="report.msg.using.rate" /></th>
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
	
	var type = $("#type").combo("getValue");
	if (type!="")
		param += "&type=" + type;
	
	var displayName = $("#displayName").combo("getValue");
	if (displayName!="")
		param += "&displayName=" + displayName;

	var url = "ajaxGetCharacterAmount.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var chA, chB, chC, chD, chE, chF, chG, sum = 0;
		$.each(json,function(i, n){
//			console.log(json[i].statisticDate+" && "+json[i].characterA);
			chA = json[i].characterA;
			chB = json[i].characterB;
			chC = json[i].characterC;
			chD = json[i].characterD;
			chE = json[i].characterE;
			chF = json[i].characterF;
			chG = json[i].characterG;
			sum = chA+chB+chC+chD+chE+chF+chG;
			if (sum<1) sum = 1;
			rows.push({
				date: json[i].statisticDate,
				characterA: chA,
				characterA_rate: (chA*100 / sum).toFixed(2),
				characterB: chB,
				characterB_rate: (chB*100 / sum).toFixed(2),
				characterC: chC,
				characterC_rate: (chC*100 / sum).toFixed(2),
				characterD: chD,
				characterD_rate: (chD*100 / sum).toFixed(2),
				characterE: chE,
				characterE_rate: (chE*100 / sum).toFixed(2),
				characterF: chF,
				characterF_rate: (chF*100 / sum).toFixed(2),
				characterG: chG,
				characterG_rate: (chG*100 / sum).toFixed(2)
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