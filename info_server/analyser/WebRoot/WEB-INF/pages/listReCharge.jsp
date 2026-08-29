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
			<c:param name="parent" value="listReCharge" />
		</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="report.title.reCharge.state" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			
			<div title='<spring:message code="report.title.reCharge.state" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="reCharge.msg.totle.bydate" /><input id="byDate" type="radio" value="d" checked="checked" name="statistic">
					
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="reCharge.msg.totle.bymonth" /><input id="byMonth" type="radio" value="m" name="statistic">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<div>
				<table id="dg" title="" style="width:1320px;height:700px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
					<thead>
						<tr>
							
							<th colspan="1" field="date" width="200" align="center"><spring:message code="report.msg.date" /></th>
							<th colspan="1" field="month" width="200" align="center" ><spring:message code="report.msg.month" /></th>
							<th colspan="1" field="moneyAmount" width="200" align="center"><spring:message code="reCharge.msg.moneyAmount" /></th>
							<th colspan="1" field="peopleAmount" width="200" align="center"><spring:message code="reCharge.msg.peopleAmount" /></th>
							<th colspan="1" field="newerAmount" width="200" align="center"><spring:message code="reCharge.msg.newerAmount" /></th>
							<th colspan="1" field="arpu" width="200" align="center"><spring:message code="reCharge.msg.arpu" /></th>
							
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
		var  day,month,people,monthPeople, money,monthMoney,newer,monthNewer,arpu,flag=0;
		$.each(json,function(i, n){
			day=json[i].statisticDate;
			money = json[i].moneyAmount;
			people= json[i].peopleAmount;
			newer=json[i].newerAmount;
			arpu= (money/people).toFixed(2);
			if($("input:radio[name='statistic']:checked").val()=="d"){		
				rows.push({
					date : day,
					moneyAmount: money,
					peopleAmount: people,
					newerAmount: newer,
					arpu : arpu
				});
			}else{
				if(flag==0){
					month=day.substr(0,7);
					monthPeople=people;
					monthMoney=money;
					monthNewer=newer;
					flag=1;
				}else{
					if(month==day.substr(0,7)){
						monthPeople+=people;
						monthMoney+=money;
						monthNewer+=newer;
					}else{
						
						rows.push({
							month : month,
							moneyAmount:monthMoney,
							peopleAmount: monthPeople,
							newerAmount: monthNewer,
							arpu : (monthMoney/monthPeople).toFixed(2)
						});
						month=day.substr(0,7);
						monthPeople=people;
						monthMoney=money;
						monthNewer=newer;
					}
				}
				
			}
		});
		if($("input:radio[name='statistic']:checked").val()=="m"){
			rows.push({
				month : month,
				moneyAmount:monthMoney,
				peopleAmount: monthPeople,
				newerAmount: monthNewer,
				arpu : (monthMoney/monthPeople).toFixed(2)
			});
		}
		
//		return rows;
		$('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', rows).show();
		if($("input:radio[name='statistic']:checked").val()=="m"){
			$("td[field='date']").hide();
			$("td[field='month']").show();
		}else{
			$("td[field='date']").show();
			$("td[field='month']").hide();
		}
			
	});
}
function doSearch(){
	getData();
}
</script>
</html>