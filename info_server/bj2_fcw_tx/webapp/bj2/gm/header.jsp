<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!-- Logo -->
<a class="logo"> <!-- mini logo for sidebar mini 50x50 pixels --> <span
	class="logo-mini"><b><fmt:message key="web.gm.head.name" /></b></span> <!-- logo for regular state and mobile devices -->
	<span class="logo-lg"><b><fmt:message key="web.gm.head.name" /></b></span>
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
	<!-- Sidebar toggle button-->
	<a href="javascript:void(0);" class="sidebar-toggle" data-toggle="offcanvas" role="button" onclick="saveBodyClass();"> 
	<span class="sr-only">Toggle navigation</span>
	</a>

	<div class="navbar-custom-menu">
		<ul class="nav navbar-nav">
			<!-- User Account: style can be found in dropdown.less -->
			<li class="dropdown user user-menu"><a
				href="javascript:void(0);" class="dropdown-toggle"
				data-toggle="dropdown"> <img
					src="${pageContext.request.contextPath}/bj2/static/img/user.png" class="user-image"
					alt="User Image" /> <span class="hidden-xs">${sessionScope.gm_session.name}</span>
			</a>
				<ul class="dropdown-menu">
					<!-- User image -->
					<li class="user-header"><img src="${pageContext.request.contextPath}/bj2/static/img/user.png"
						class="img-circle" alt="User Image" />

						<p>
							${sessionScope.gm_session.name} <small></small>
						</p></li>
					<!-- Menu Footer-->
					<li class="user-footer">
						<div class="pull-left">
							<a href="${pageContext.request.contextPath}/fcw/#/gmResetPwd" class="btn btn-default btn-flat"><fmt:message key="web.gm.head.detail"/></a>
						</div>
						<div class="pull-right">
							<form action="${pageContext.request.contextPath}/fcw/logout" method="post">
								<input type="submit" value="<fmt:message key="web.gm.head.logout"/>"
									class="btn btn-default btn-flat"></input>
							</form>
						</div>
					</li>
				</ul></li>
			<!-- Control Sidebar Toggle Button -->
			<li><a href="#" data-toggle="control-sidebar"><i
					class="fa fa-gears"></i></a></li>
		</ul>
	</div>
</nav>