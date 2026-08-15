<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <title><fmt:message key="web.login.title"/></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/bootstrap-3.3.5/css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/font-awesome-4.3.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/adminlte-2.2.0/css/AdminLTE.min.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/icheck-1.0.2/skins/square/blue.css"/>
</head>
<body class="login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="javascript: void(0);"><b><fmt:message key="web.login.head"/></b></a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg"><fmt:message key="web.login.login"/></p>

    <form action="${pageContext.request.contextPath}/fcw/login" method="post">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" id="username" name="userName" placeholder="<fmt:message key="web.login.username"/>" required autofocus/>
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="web.login.password"/>" maxlength="30" required/>
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
<!--         <input type="password" style="display: none" id="password" name="password"/> -->
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
<%--               <input type="checkbox" id="rememberme" name="rememberme">&nbsp;<fmt:message key="web.login.stayLogin"/> --%>
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat"><fmt:message key="web.login.login"/></button>
        </div>
        <!-- /.col -->
      </div>
    </form>
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/icheck-1.0.2/icheck.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jshashes-1.0.5/hashes.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/js/commons.js"></script>
<script>
  $(function () {
    $('form').on('submit', function () {
//       var username = $('#userName').val();
//       var $rawPassword = $('#rawpassword');
//       var password = commons.encodePassword(username, $rawPassword.val());
//       $rawPassword.val(new Array($rawPassword.val().length + 1).join('*'));
//       $('#password').val(password);
    });

    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue'
    });
  });
</script>
<c:if test="${error eq 1}">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/lib/jQueryUI-1.11.4/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bj2/static/css/app.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jQueryUI-1.11.4/jquery-ui.min.js"></script>
<div class="hidden">
	<div id="dialogAlert"><p><fmt:message key="web.login.usernameOrPasswordError"/></p></div>
</div>
<script>
$('#dialogAlert').dialog({
	modal:true,draggable:false,width:$(window).width()/3,buttons: [{
		text:'Ok',
		click:function(){$(this).dialog('close');}
	}]
});
</script>
</c:if>
</body>
</html>