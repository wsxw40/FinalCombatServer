<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><spring:message code="level.title2" /><spring:message code="character.title1" /></title>
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
		<c:param name="parent" value="listLevelAmount" />
	</c:import>
	</div>

	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><spring:message code="copyright" /></div>

	<div data-options="region:'center',title:'<spring:message code="level.title2" />',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title='<spring:message code="character.title1" />' style="padding:5px">
				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.startDate" /><font color="red">*</font>: <input id="startDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="report.msg.endDate" /><font color="red">*</font>: <input id="endDate" class="easyui-datebox" style="width:120px" required="required" maxlength="10">
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="report.btn.search" /></a>
				</div>
				<table id="dg" title="" style="width:5000px;height:600px;display:none;" data-options="rownumbers:true,singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10">
				<thead>
					<tr>
						<th field="date" width="100" rowspan="2"><spring:message code="report.msg.date" /></th>
						<th colspan="2"><spring:message code="level.list.level22" /></th>
						<th colspan="2"><spring:message code="level.list.level3" /></th>
						<th colspan="2"><spring:message code="level.list.level15" /></th>
						<th colspan="2"><spring:message code="level.list.level30" /></th>
						<th colspan="2"><spring:message code="level.list.level8" /></th>
						<th colspan="2"><spring:message code="level.list.level20" /></th>
						<th colspan="2"><spring:message code="level.list.level19" /></th>
						<th colspan="2"><spring:message code="level.list.level14" /></th>
						<th colspan="2"><spring:message code="level.list.levelT" /></th>
						<th colspan="2"><spring:message code="level.list.level36" /></th>
						<th colspan="2"><spring:message code="level.list.level29" /></th>
						<th colspan="2"><spring:message code="level.list.level28" /></th>
						<th colspan="2"><spring:message code="level.list.level32" /></th>
						<th colspan="2"><spring:message code="level.list.level12" /></th>
						<th colspan="2"><spring:message code="level.list.level5" /></th>
						<th colspan="2"><spring:message code="level.list.level38" /></th>
						<th colspan="2"><spring:message code="level.list.level25" /></th>
						<th colspan="2"><spring:message code="level.list.level26" /></th>
						<th colspan="2"><spring:message code="level.list.level35" /></th>
						<th colspan="2"><spring:message code="level.list.level24" /></th>
						<th colspan="2"><spring:message code="level.list.level7" /></th>
						<th colspan="2"><spring:message code="level.list.level9" /></th>
						<th colspan="2"><spring:message code="level.list.level10" /></th>
						<th colspan="2"><spring:message code="level.list.level27" /></th>
						<th colspan="2"><spring:message code="level.list.level16" /></th>
						<th colspan="2"><spring:message code="level.list.level37" /></th>
						<th colspan="2"><spring:message code="level.list.level34" /></th>
						<th colspan="2"><spring:message code="level.list.level31" /></th>
						<th colspan="2"><spring:message code="level.list.level2" /></th>
						<th colspan="2"><spring:message code="level.list.level6" /></th>
						<th colspan="2"><spring:message code="level.list.level11" /></th>
						<th colspan="2"><spring:message code="level.list.level33" /></th>
						<th colspan="2"><spring:message code="level.list.level23" /></th>
						<th colspan="2"><spring:message code="level.list.level1" /></th>
					</tr>
					<tr>
						<th field="level22" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level22_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level3" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level3_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level15" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level15_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level30" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level30_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level8" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level8_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level20" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level20_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level19" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level19_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level14" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level14_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="levelT" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="levelT_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level36" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level36_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level29" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level29_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level28" width="70" align="right"><spring:message code="report.msg.using.amount" /></th>
						<th field="level28_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level32" width="70" align="right"><spring:message code="report.msg.using.amount" /></th>
						<th field="level32_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level12" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level12_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level5" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level5_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level38" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level38_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level25" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level25_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level26" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level26_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level35" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level35_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level24" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level24_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level7" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level7_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level9" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level9_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level10" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level10_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level27" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level27_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level16" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level16_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level37" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level37_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level34" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level34_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level31" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level31_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level2" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level2_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level6" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level6_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level11" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level11_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level33" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level33_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level23" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level23_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
						
						<th field="level1" width="70" align="center"><spring:message code="report.msg.using.amount" /></th>
						<th field="level1_rate" width="70" align="center"><spring:message code="report.msg.using.rate" /></th>
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
	var url = "ajaxGetLevelAmount.do?"+param;
	$.getJSON(url, function(json){
		var rows = [];
		var level22,level3,level15,level30,level8,level20,level19,level14,levelT,level36,level29,level28,level32,level12,level5,level38,level25,level26,level35,level24,level7,
		level9,level10,level27,level16,level37,level34,level31,level2,level6,level11,level33,level23,level1, sum = 0;
		$.each(json,function(i, n){
			level22 = json[i].level22;
			level3 = json[i].level3;
			level15 = json[i].level15;
			level30 = json[i].level30;
			level8 = json[i].level8;
			level20 = json[i].level20;
			level19 = json[i].level19;
			level14 = json[i].level14;
			levelT = json[i].levelT;
			level36 = json[i].level36;
			level29 = json[i].level29;
			level28 = json[i].level28;
			level32 = json[i].level32;
			level12 = json[i].level12;
			level5 = json[i].level5;
			level38 = json[i].level38;
			level25 = json[i].level25;
			level26 = json[i].level26;
			level35 = json[i].level35;
			level24 = json[i].level24;
			level7 = json[i].level7;
			level9 = json[i].level9;
			level10 = json[i].level10;
			level27 = json[i].level27;
			level16 = json[i].level16;
			level37 = json[i].level37;
			level34 = json[i].level34;
			level31 = json[i].level31;
			level2 = json[i].level2;
			level6 = json[i].level6;
			level11 = json[i].level11;
			level33 = json[i].level33;
			level23 = json[i].level23;
			level1 = json[i].level1;			
			sum = level22+level3+level15+level30+level8+level20+level19+level14+levelT+level36+level29+
			level28+level32+level12+level5+level38+level25+level26+level35+level24+level7+level9+level10+level27+level16+level37+level34+level31+level2+level6+level11+level33+level23+level1;
			if (sum<1) sum = 1;
			rows.push({
				date: json[i].statisticDate,
				level22: level22, level22_rate: (level22*100 / sum).toFixed(2),
				level3: level3, level3_rate: (level3*100 / sum).toFixed(2),
				level15: level15, level15_rate: (level15*100 / sum).toFixed(2),
				level30: level30, level30_rate: (level30*100 / sum).toFixed(2),
				level8: level8, level8_rate: (level22*100 / sum).toFixed(2),
				level20: level20, level20_rate: (level20*100 / sum).toFixed(2),
				level19: level19, level19_rate: (level19*100 / sum).toFixed(2),
				level14: level14, level14_rate: (level14*100 / sum).toFixed(2),
				levelT: levelT, levelT_rate: (levelT*100 / sum).toFixed(2),
				level36: level36, level36_rate: (level36*100 / sum).toFixed(2),
				level29: level29, level29_rate: (level29*100 / sum).toFixed(2),
				level28: level28, level28_rate: (level28*100 / sum).toFixed(2),
				level32: level32, level32_rate: (level32*100 / sum).toFixed(2),
				level12: level12, level12_rate: (level12*100 / sum).toFixed(2),
				level15: level15, level15_rate: (level15*100 / sum).toFixed(2),
				level38: level38, level38_rate: (level38*100 / sum).toFixed(2),
				level25: level25, level25_rate: (level25*100 / sum).toFixed(2),
				level26: level26, level26_rate: (level26*100 / sum).toFixed(2),
				level35: level35, level35_rate: (level35*100 / sum).toFixed(2),
				level24: level24, level24_rate: (level24*100 / sum).toFixed(2),
				level7: level7, level7_rate: (level7*100 / sum).toFixed(2),
				level9: level9, level9_rate: (level9*100 / sum).toFixed(2),
				level10: level10, level10_rate: (level10*100 / sum).toFixed(2),
				level27: level27, level27_rate: (level27*100 / sum).toFixed(2),
				level16: level16, level16_rate: (level16*100 / sum).toFixed(2),
				level37: level37, level37_rate: (level37*100 / sum).toFixed(2),
				level34: level34, level34_rate: (level34*100 / sum).toFixed(2),
				level31: level31, level31_rate: (level31*100 / sum).toFixed(2),
				level2: level2, level2_rate: (level2*100 / sum).toFixed(2),
				level6: level6, level6_rate: (level6*100 / sum).toFixed(2),
				level11: level11, level11_rate: (level11*100 / sum).toFixed(2),
				level33: level33, level33_rate: (level33*100 / sum).toFixed(2),
				level23: level23, level23_rate: (level23*100 / sum).toFixed(2),
				level1: level1, level1_rate: (level1*100 / sum).toFixed(2)
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