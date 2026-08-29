<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="easyui-accordion" data-options="fit:true,border:false">

	<a href="?lang=en"><spring:message code="label.firstname" /></a>&nbsp;&nbsp;&nbsp;
	<a href="?lang=zh"><spring:message code="label.firstname2" /></a>
	
	<div title='<spring:message code="report.title.luckyPackage" />' <c:if test="${ param.parent == 'listLuckyPackage'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listLuckyPackage.do';"><spring:message code="luckyPackage.title2" /></a>
	</div>
	<div title='<spring:message code="report.title.character" />' <c:if test="${ param.parent == 'listCharacterKD' || param.parent == 'listCharacterAmount' }">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listCharacterAmount.do';"><spring:message code="character.title1" /></a><p>
		<a href="javascript:void(0);" onclick="javascript:window.location='listCharacterKD.do';"><spring:message code="character.title2" /></a>
	</div>
	<div title='<spring:message code="level.title" />' <c:if test="${ param.parent == 'listLevelTypeAmount'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listLevelTypeAmount.do';"><spring:message code="character.title1" /></a>
	</div>
	<div title='<spring:message code="level.title2" />' <c:if test="${ param.parent == 'listLevelAmount'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listLevelAmount.do';"><spring:message code="level.title3" /></a><p>
		<spring:message code="level.title4" />
	</div>
 	<div title='<spring:message code="report.title.medalExchange" />'  <c:if test="${ param.parent == 'listMedalExchange'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listMedalExchange.do';"><spring:message code="medal.exchange.showCost" /></a><p>
	</div>
 	<div title='<spring:message code="report.title.itemCost" />'  <c:if test="${ param.parent == 'listItemCost'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listItemCost.do';"><spring:message code="report.title.itemCost" /></a><p>
	</div>
 	<div title='<spring:message code="report.title.reCharge" />'  <c:if test="${ param.parent == 'listReCharge'|| param.parent == 'listRechargeReport'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listReCharge.do';"><spring:message code="report.title.reCharge" /></a><p>
		<a href="javascript:void(0);" onclick="javascript:window.location='listRechargeReport.do';"><spring:message code="report.title.reChargeReport" /></a><p>
	</div>
	<div title='<spring:message code="report.title.money" />'  <c:if test="${ param.parent == 'listGPointTotal'}">data-options="selected:true"</c:if> style="padding:10px;">
		<a href="javascript:void(0);" onclick="javascript:window.location='listGPointTotal.do';"><spring:message code="report.title.gpointTotal" /></a><p>
	</div>
</div>
