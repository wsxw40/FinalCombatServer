<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="report.title.reCharge.state" /></title>
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
			<c:param name="parent" value="listRechargeReport" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.reCharge.state" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			
			<div title='<spring:message code="report.title.reCharge.state" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<div>
				<table id="dg" title="" style="width:1320px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
					<thead>
						<tr>
							<th colspan="1" field="date"  align="center"><spring:message code="report.msg.date" /></th>
							<th colspan="1" field="fiveH"  align="center"><spring:message text="1-5"  /></th>
							<th colspan="1" field="tenH" align="center"><spring:message text="6-10"/></th>
							<th colspan="1" field="twentyH" align="center"><spring:message text="11-20" /></th>
							<th colspan="1" field="fiftyH" align="center"><spring:message text="21-50"/></th>
							
							<th colspan="1" field="hundredH" align="center"><spring:message text="51-100"/></th>
							<th colspan="1" field="twoHundredH" align="center"><spring:message text="101-200"/></th>
							<th colspan="1" field="fiveHundredH" align="center"><spring:message text="201-500"/></th>
							<th colspan="1" field="thousandH" align="center"><spring:message text="501-1000"/></th>
							<th colspan="1" field="twoThousandH" align="center"><spring:message text="1001-2000"/></th>
							<th colspan="1" field="fiveThousandH" align="center"><spring:message text="2001-5000"/></th>
							<th colspan="1" field="tenThousandH" align="center"><spring:message text="5001-10000"/></th>
							<th colspan="1" field="thousandsH" align="center"><spring:message text="above 10000"/></th>
							
							
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
});

function getData() {
	var param = "startDate="+$("#startDate").datebox("getValue")+"&endDate="+$("#endDate").datebox("getValue");
	var url = "ajaxGetReChargeList.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var  fiveH,tenH,twentyH,fiftyH,hundredH,twoHundredH,fiveHundredH,thousandH,twoThousandH,fiveThousandH,tenThousandH,thousandsH;
		$.each(json,function(i, n){
			rows.push({
				date : json[i].statisticDate,
				fiveH:json[i].fiveH ,
				tenH:json[i].tenH,
				twentyH:json[i].twentyH ,
				fiftyH:json[i].fiftyH ,
				hundredH:json[i].hundredH ,
				twoHundredH:json[i].twoHundredH,
				fiveHundredH:json[i].fiveHundredH ,
				thousandH:json[i].thousandH ,
				twoThousandH:json[i].twoThousandH ,
				fiveThousandH:json[i].fiveThousandH ,
				tenThousandH:json[i].tenThousandH ,
				thousandsH:json[i].thousandsH 
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