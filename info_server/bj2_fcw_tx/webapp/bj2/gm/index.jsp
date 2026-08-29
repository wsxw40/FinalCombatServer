<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html data-ng-app="app">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <title><fmt:message key="web.gm.head.name"/></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/jQueryUI-1.11.4/jquery-ui.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/bootstrap-3.3.5/css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/font-awesome-4.3.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/datatables-plugins-1.10.7/integration/bootstrap/3/dataTables.bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/datatables-responsive-1.0.6/css/dataTables.responsive.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/dropzone-4.0.1/dropzone.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/adminlte-2.2.0/css/AdminLTE.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/adminlte-2.2.0/css/skins/skin-blue.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/icheck-1.0.2/skins/square/blue.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/css/app.css">
</head>
<body class="sidebar-mini skin-blue">
<input id="app_root" type="hidden" value="${pageContext.request.contextPath}"/>
<div class="hidden">
	<div id="dialogAlert"><p></p></div>
	<div id="dialogRemove"><p><fmt:message key="web.gm.hidden.alert.ConfirmToRemove"/></p></div>
</div>
<div class="wrapper">
  <!-- Header -->
  <header class="main-header"><jsp:include page="header.jsp"/></header>
  <!-- Menu -->
  <aside class="main-sidebar">
    <section class="sidebar"><jsp:include page="menu.jsp"/></section>
  </aside>
  <!-- Main content -->
  <div class="content-wrapper">
    <section class="content-header"><jsp:include page="ribbon.jsp"/></section>
    <section class="content" data-ng-view=""></section>
  </div>
  <!-- Footer -->
  <footer class="main-footer"><jsp:include page="footer.jsp"/></footer>
</div>
<!-- JAVASCRIPTS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/datatables-1.10.7/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/datatables-responsive-1.0.6/js/dataTables.responsive.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/datatables-plugins-1.10.7/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/datatables-plugins-1.10.7/dataTables.fixedColumns.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/angular-1.4.2/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/angular-route-1.4.2/angular-route.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jQueryUI-1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/angular-datatables-0.5.3/angular-datatables.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/angular-datatables-0.5.3/angular-datatables.scroller.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/dropzone-4.0.1/dropzone.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/adminlte-2.2.0/js/app.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/icheck-1.0.2/icheck.min.js"></script>
<jsp:include page="words.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/dateFormat.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/ip.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/directives.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/services.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/controllers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/app.js"></script>
</body>
</html>