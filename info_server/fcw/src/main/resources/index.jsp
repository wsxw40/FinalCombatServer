<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html ng-app="tpsView">
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="images/favicon.png">
    <title>TPS GM</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrap-reset.css" rel="stylesheet">
    <link href="${ctx}/css/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="${ctx}/js/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/style.css" rel="stylesheet">
    <link href="${ctx}/css/style-responsive.css" rel="stylesheet"/>
    <link href="${ctx}/js/table/ui-grid.min.css" rel="stylesheet"/>
    <link href="${ctx}/js/slidebars/slidebars.css" rel="stylesheet"/>
</head>
<body class="full-width">
<section id="container" class="">
    <%@include file="/common/Header.jsp" %>
    <section id="main-content">
        <section class="wrapper" ng-view>
        </section>
    </section>
    <%@include file="/common/Footer.jsp" %>
</section>

<div id="loading" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog" aria-hidden="true">
    <div style="display: table; height: 100%; margin: 0px auto; padding-top: 20%;">
        <div class="modal-dialog" style="width: 80px;">
            <div class="modal-content" style="background: none;">
                <div class="modal-body"><img src="${ctx}/images/loading.gif" border="0" width="36px" height="36px"/></div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade top-modal-without-space " id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content-wrap">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">确认删除</h4>
                </div>
                <div class="modal-body">
                    {{conformDeleteMessage}}
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
                    <button type="button" class="btn btn-warning" data-dismiss="modal" ng-click="conformDeleteItem()">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Slidebar start -->
<div off-canvas="off-canvas-weapon bottom push" class="transition-1000 sb-slidebar">
    <h5 class="side-title">选择武器</h5>

    <div class="panel-body">
        <div class="row">
            <div class="col-md-1" style="margin-bottom: 3px;" ng-repeat="weapon in sysWeaponList">
                <button type="button" class="btn btn-default" ng-click="sysItemSlideBars.selected(weapon)">{{weapon.id}}:{{weapon.displayName}}</button>
            </div>
        </div>
    </div>
</div>
<div off-canvas="off-canvas-item bottom push" class="transition-1000 sb-slidebar">
    <h5 class="side-title">选择系统物品</h5>

    <div class="panel-body">
        <div class="row">
            <div class="col-md-1" style="margin-bottom: 3px;" ng-repeat="item in sysItemList">
                <button type="button" class="btn btn-default" ng-click="sysItemSlideBars.selected(item)">{{item.id}}:{{item.displayName}}</button>
            </div>
        </div>
    </div>
</div>
<!-- Slidebar end -->
<script src="${ctx}/js/LAB.js" type="text/javascript"></script>
<script>
    $LAB.setGlobalDefaults({
        "BasePath": "/tps/js/"
    }).script("jquery.js")
            .script("angular.js")
            .script("angular-route.js").wait()
            .script("bootstrap.min.js", "jquery-ui.min.js", "jquery.dcjqaccordion.2.7.js", "hover-dropdown.js", "jquery.nicescroll.js", "gritter/js/jquery.gritter.js", "bootstrap-inputmask.min.js", "common-scripts.js", "form-component.js")
            .script("i18n/key_zh-cn.min.js")
            .script("table/ui-grid.min.js").wait()
            .script("localize.js")
            .script("initialize.js").wait()
            .script("tps.js")
            .script("jquery.validate.min.js")
            .script("slidebars/slidebars.js");
</script>
<script type="text/javascript">
    if (document.location.pathname == "/tps/home" && document.location.hash == "") {
        document.location.hash = "${indexAction}";
    }
</script>
</body>
</html>
