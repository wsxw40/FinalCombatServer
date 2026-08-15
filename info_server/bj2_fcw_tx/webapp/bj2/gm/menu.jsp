<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!-- Sidebar Menu -->
<ul class="sidebar-menu">
	<li class="header"><fmt:message key="web.gm.menu.menuName" /></li>
	
	<li class="active"><a href="#/players"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.players" /></span></a></li>
	<li class="hidden"><a href="#/player"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.player" /></span></a></li>
	<li class="hidden"><a href="#/playerItems"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.playerItems" /></span></a></li>
	<li class="hidden"><a href="#/playerItem"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.playerItem" /></span></a></li>
	
	<li class="treeview">
		<a href="#"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.sys" /></span><i class="fa fa-angle-left pull-right"></i></a>
		<ul class="treeview-menu">
			<li><a href="#/sysItems"><fmt:message key="web.gm.menu.sysItems" /></a></li>
			<li class="hidden"><a href="#/sysItem"><fmt:message key="web.gm.menu.sysItem" /></a></li>
			<li><a href="#/sysCharacters"><fmt:message key="web.gm.menu.sysCharacters" /></a></li>
			<li class="hidden"><a href="#/sysCharacter"><fmt:message key="web.gm.menu.sysCharacter" /></a></li>
			<li><a href="#/sysLevels"><fmt:message key="web.gm.menu.sysLevels" /></a></li>
			<li class="hidden"><a href="#/sysLevel"><fmt:message key="web.gm.menu.sysLevel" /></a></li>
			<li><a href="#/payments"><fmt:message key="web.gm.menu.payments" /></a></li>
			<li class="hidden"><a href="#/payment"><fmt:message key="web.gm.menu.payment" /></a></li>
	  </ul>
	</li>
	<li class="treeview">
		<a href="#"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.msg" /></span><i class="fa fa-angle-left pull-right"></i></a>
		<ul class="treeview-menu">
			<li><a href="#/messages"><fmt:message key="web.gm.menu.messages" /></a></li>
			<li class="hidden"><a href="#/message"><fmt:message key="web.gm.menu.message" /></a></li>
			<li class="hidden"><a href="#/messageGift"><fmt:message key="web.gm.menu.messageGift" /></a></li>
	  </ul>
	</li>
	<li class="treeview">
		<a href="#"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.gm" /></span><i class="fa fa-angle-left pull-right"></i></a>
		<ul class="treeview-menu">
			<li><a href="#/gmUsers"><fmt:message key="web.gm.menu.gmUsers" /></a></li>
			<li class="hidden"><a href="#/gmUser"><fmt:message key="web.gm.menu.gmUser" /></a></li>
			<li><a href="#/gmGroups"><fmt:message key="web.gm.menu.gmGroups" /></a></li>
			<li class="hidden"><a href="#/gmGroup"><fmt:message key="web.gm.menu.gmGroup" /></a></li>
			<li><a href="#/gmResetPwd"><fmt:message key="web.gm.menu.gmResetPwd" /></a></li>
			<li><a href="#/gmLogs"><fmt:message key="web.gm.menu.gmLogs" /></a></li>
			<li class="hidden"><a href="#/gmTransactions"><fmt:message key="web.gm.menu.gmTransactions" /></a></li>
	  </ul>
	</li>
	<li class="treeview">
		<a href="#"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.serverAndChannel" /></span><i class="fa fa-angle-left pull-right"></i></a>
		<ul class="treeview-menu">
			<li><a href="#/servers"><fmt:message key="web.gm.menu.servers" /></a></li>
			<li class="hidden"><a href="#/server"><fmt:message key="web.gm.menu.server" /></a></li>
			<li><a href="#/channels"><fmt:message key="web.gm.menu.channels" /></a></li>
			<li class="hidden"><a href="#/channel"><fmt:message key="web.gm.menu.channel" /></a></li>
	  </ul>
	</li>
	
	<li><a href="#/import"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.import" /></span></a></li>
	<li><a href="#/export"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.export" /></span></a></li>
<!-- 	<li><a href="#/functionClose"><i class='fa fa-link'></i><span>功能管理</span></a></li> -->
<%-- 	<li><a href="#/gmUsers"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.gmUsers"/></span></a></li> --%>
<%-- 	<li class="hidden"><a href="#/gmUser"><i class='fa fa-link'></i><span><fmt:message key="web.gm.menu.gmUser"/></span></a></li> --%>
 
</ul>