<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<h1 data-ng-bind="data.title"></h1>
<ol class="breadcrumb">
  <li><i class="fa fa-home"></i>&nbsp;<fmt:message key="web.gm.ribbon.home"/></li>
  <li data-ng-repeat="b in data.breadcrumb" data-ng-bind="b" class="active"></li>
</ol>
