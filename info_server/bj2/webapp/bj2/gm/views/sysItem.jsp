<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(function(){
	$('#tab').tabs();
});
</script>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body" id="accordion">    	
		<form id="formSysItem" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysItem">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.sysItems.col.id.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.name.toolTips"/>">
					<input class="form-control" id="name" ng-model="name"  name="name" required
					placeholder="<fmt:message key="web.gm.sysItems.col.name.toolTips"/>" />
				</div>
				<label for="displayName" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.displayName.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.displayName.toolTips"/>">
					<input class="form-control" id="displayName" ng-model="displayName"  name="displayName"
					placeholder="<fmt:message key="web.gm.sysItems.col.displayName.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.description.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.sysItems.col.description.toolTips"/>">
					<textarea class="form-control" id="description" ng-model="description"  name="description" rows="3"
					placeholder="<fmt:message key="web.gm.sysItems.col.description.toolTips"/>" ></textarea>
				</div>
			</div>
			
			<div id="tab">
				<ul>
					<li><a  href="#tabs-1"><fmt:message key="web.gm.tab.sysItem.tabs-1"/></a></li>
					<li><a  href="#tabs-2"><fmt:message key="web.gm.tab.sysItem.tabs-2"/></a></li>
					<li><a  href="#tabs-3"><fmt:message key="web.gm.tab.sysItem.tabs-3"/></a></li>
					<li><a  href="#tabs-4"><fmt:message key="web.gm.tab.sysItem.tabs-4"/></a></li>
					<li><a  href="#tabs-5"><fmt:message key="web.gm.tab.sysItem.tabs-5"/></a></li>
					<li><a  href="#tabs-6"><fmt:message key="web.gm.tab.sysItem.tabs-6"/></a></li>
					<li><a  href="#tabs-7"><fmt:message key="web.gm.tab.sysItem.tabs-7"/></a></li>
					<li><a  href="#tabs-8"><fmt:message key="web.gm.tab.sysItem.tabs-8"/></a></li>
					<li><a  href="#tabs-9"><fmt:message key="web.gm.tab.sysItem.tabs-9"/></a></li>
					<li><a  href="#tabs-10"><fmt:message key="web.gm.tab.sysItem.tabs-10"/></a></li>
					<li><a  href="#tabs-11"><fmt:message key="web.gm.tab.sysItem.tabs-11"/></a></li>
					<li><a  href="#tabs-12"><fmt:message key="web.gm.tab.sysItem.tabs-12"/></a></li>
					<li><a  href="#tabs-13"><fmt:message key="web.gm.tab.sysItem.tabs-13"/></a></li>
					<li><a  href="#tabs-14"><fmt:message key="web.gm.tab.sysItem.tabs-14"/></a></li>
					<li><a  href="#tabs-15"><fmt:message key="web.gm.tab.sysItem.tabs-15"/></a></li>
				</ul>
				<div id="tabs-1">
					<div class="form-group">
						<label for="resourceStable" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.resourceStable.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.resourceStable.toolTips"/>">
							<input class="form-control" id="resourceStable" ng-model="resourceStable"  name="resourceStable"placeholder="<fmt:message key="web.gm.sysItems.col.resourceStable.toolTips"/>" />
						</div>
						<label for="resourceChangeable" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.resourceChangeable.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.resourceChangeable.toolTips"/>">
							<input class="form-control" id="resourceChangeable" ng-model="resourceChangeable"  name="resourceChangeable"placeholder="<fmt:message key="web.gm.sysItems.col.resourceChangeable.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.type.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.type.toolTips"/>">
							<input class="form-control" id="type" ng-model="type"  name="type"placeholder="<fmt:message key="web.gm.sysItems.col.type.toolTips"/>"  type="number"/>
						</div>
						<label for="subType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.subType.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.subType.toolTips"/>">
							<input class="form-control" id="subType" ng-model="subType"  name="subType"placeholder="<fmt:message key="web.gm.sysItems.col.subType.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="level" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.level.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.level.toolTips"/>">
							<input class="form-control" id="level" ng-model="level"  name="level"placeholder="<fmt:message key="web.gm.sysItems.col.level.toolTips"/>"  type="number"/>
						</div>
						<label for="modifiedDesc" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.modifiedDesc.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.modifiedDesc.toolTips"/>">
							<input class="form-control" id="modifiedDesc" ng-model="modifiedDesc"  name="modifiedDesc"placeholder="<fmt:message key="web.gm.sysItems.col.modifiedDesc.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="wId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wId.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wId.toolTips"/>">
							<input class="form-control". id="wId" ng-model="wId"  name="wId"placeholder="<fmt:message key="web.gm.sysItems.col.wId.toolTips"/>"  type="number"/>		
						</div>
						<label for="cId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cId.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cId.toolTips"/>">
							<input class="form-control" id="cId" ng-model="cId"  name="cId"placeholder="<fmt:message key="web.gm.sysItems.col.cId.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="iId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.iId.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.iId.toolTips"/>">
							<input class="form-control" id="iId" ng-model="iId"  name="iId"placeholder="<fmt:message key="web.gm.sysItems.col.iId.toolTips"/>"  type="number"/>
						</div>
						<label for="iValue" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.iValue.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.iValue.toolTips"/>">
							<input class="form-control" id="iValue" ng-model="iValue"  name="iValue"placeholder="<fmt:message key="web.gm.sysItems.col.iValue.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="mType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.mType.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.mType.toolTips"/>">
							<input class="form-control" id="mType" ng-model="mType"  name="mType"placeholder="<fmt:message key="web.gm.sysItems.col.mType.toolTips"/>"  type="number"/>
						</div>
						<label for="mValue" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.mValue.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.mValue.toolTips"/>">
							<input class="form-control" id="mValue" ng-model="mValue"  name="mValue"placeholder="<fmt:message key="web.gm.sysItems.col.mValue.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="iBuffId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.iBuffId.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.iBuffId.toolTips"/>">
							<input class="form-control" id="iBuffId" ng-model="iBuffId"  name="iBuffId"placeholder="<fmt:message key="web.gm.sysItems.col.iBuffId.toolTips"/>"  type="number"/>
						</div>
						<label for="rareLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.rareLevel.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.rareLevel.toolTips"/>">
							<input class="form-control" id="rareLevel" ng-model="rareLevel"  name="rareLevel"placeholder="<fmt:message key="web.gm.sysItems.col.rareLevel.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="needTeamPlaceLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.needTeamPlaceLevel.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.needTeamPlaceLevel.toolTips"/>">
							<input class="form-control" id="needTeamPlaceLevel" ng-model="needTeamPlaceLevel"  name="needTeamPlaceLevel"placeholder="<fmt:message key="web.gm.sysItems.col.needTeamPlaceLevel.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="items" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.items.title"/></label>
						<div class="col-sm-10" title="<fmt:message key="web.gm.sysItems.col.items.toolTips"/>">
							<textarea class="form-control" id="items" ng-model="items"  name="items" rows="3" placeholder="<fmt:message key="web.gm.sysItems.col.items.toolTips"/>" ></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="gunProperty1" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty1.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty1.toolTips"/>">
							<input class="form-control" id="gunProperty1" ng-model="gunProperty1"  name="gunProperty1"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty1.toolTips"/>" />
						</div>
						<label for="gunProperty2" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty2.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty2.toolTips"/>">
							<input class="form-control" id="gunProperty2" ng-model="gunProperty2"  name="gunProperty2"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty2.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="gunProperty3" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty3.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty3.toolTips"/>">
							<input class="form-control" id="gunProperty3" ng-model="gunProperty3"  name="gunProperty3"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty3.toolTips"/>" />
						</div>
						<label for="gunProperty4" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty4.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty4.toolTips"/>">
							<input class="form-control" id="gunProperty4" ng-model="gunProperty4"  name="gunProperty4"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty4.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">	
						<label for="gunProperty5" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty5.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty5.toolTips"/>">
							<input class="form-control" id="gunProperty5" ng-model="gunProperty5"  name="gunProperty5"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty5.toolTips"/>" />
						</div>
						<label for="gunProperty6" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty6.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty6.toolTips"/>">
							<input class="form-control" id="gunProperty6" ng-model="gunProperty6"  name="gunProperty6"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty6.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="gunProperty7" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty7.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty7.toolTips"/>">
							<input class="form-control" id="gunProperty7" ng-model="gunProperty7"  name="gunProperty7"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty7.toolTips"/>" />
						</div>
						<label for="gunProperty8" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.gunProperty8.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.gunProperty8.toolTips"/>">
							<input class="form-control" id="gunProperty8" ng-model="gunProperty8"  name="gunProperty8"placeholder="<fmt:message key="web.gm.sysItems.col.gunProperty8.toolTips"/>" />	
						</div>
					</div>
					<div class="form-group">
						<label for="isVip" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isVip.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isVip.toolTips"/>">
							<input class="form-control" id="isVip" ng-model="isVip"  name="isVip"placeholder="<fmt:message key="web.gm.sysItems.col.isVip.toolTips"/>"  type="number"/>
						</div>
						<label for="isNew" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isNew.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isNew.toolTips"/>">
							<input class="form-control" id="isNew" ng-model="isNew"  name="isNew"placeholder="<fmt:message key="web.gm.sysItems.col.isNew.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="isHot" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isHot.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isHot.toolTips"/>">
							<input class="form-control" id="isHot" ng-model="isHot"  name="isHot"placeholder="<fmt:message key="web.gm.sysItems.col.isHot.toolTips"/>"  type="number"/>
						</div>
						<label for="isWeb" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isWeb.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isWeb.toolTips"/>">
							<input class="form-control" id="isWeb" ng-model="isWeb"  name="isWeb"placeholder="<fmt:message key="web.gm.sysItems.col.isWeb.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="isPopular" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isPopular.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isPopular.toolTips"/>">
							<input class="form-control" id="isPopular" ng-model="isPopular"  name="isPopular"placeholder="<fmt:message key="web.gm.sysItems.col.isPopular.toolTips"/>"  type="number"/>
						</div>
						<label for="isExchange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isExchange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isExchange.toolTips"/>">
							<input class="form-control" id="isExchange" ng-model="isExchange"  name="isExchange"placeholder="<fmt:message key="web.gm.sysItems.col.isExchange.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">					
						<label for="isStrengthen" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isStrengthen.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isStrengthen.toolTips"/>">
							<input class="form-control" id="isStrengthen" ng-model="isStrengthen"  name="isStrengthen"placeholder="<fmt:message key="web.gm.sysItems.col.isStrengthen.toolTips"/>"  type="number"/>
						</div>
						<label for="strengthLevel" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.strengthLevel.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.strengthLevel.toolTips"/>">
							<input class="form-control" id="strengthLevel" ng-model="strengthLevel"  name="strengthLevel"placeholder="<fmt:message key="web.gm.sysItems.col.strengthLevel.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">					
						<label for="shoppingType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.shoppingType.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.shoppingType.toolTips"/>">
							<input class="form-control" id="shoppingType" ng-model="shoppingType"  name="shoppingType"placeholder="<fmt:message key="web.gm.sysItems.col.shoppingType.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">				
						<label for="shoppingStartTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.shoppingStartTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.shoppingStartTime.toolTips"/>">
							<input class="form-control" id="shoppingStartTime" ng-model="shoppingStartTime"  name="shoppingStartTime"placeholder="<fmt:message key="web.gm.sysItems.col.shoppingStartTime.toolTips"/>"  />
						</div>	
						<label for="shoppingEndTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.shoppingEndTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.shoppingEndTime.toolTips"/>">
							<input class="form-control" id="shoppingEndTime" ng-model="shoppingEndTime"  name="shoppingEndTime"placeholder="<fmt:message key="web.gm.sysItems.col.shoppingEndTime.toolTips"/>"  />
						</div>
					</div>
				</div>
				<div id="tabs-2">
					<div class="form-group">
						<label for="wCrossOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrossOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrossOffset.toolTips"/>">
							<input class="form-control" id="wCrossOffset" ng-model="wCrossOffset"  name="wCrossOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wCrossOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAccuracyDivisor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAccuracyDivisor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAccuracyDivisor.toolTips"/>">
							<input class="form-control" id="wAccuracyDivisor" ng-model="wAccuracyDivisor"  name="wAccuracyDivisor"placeholder="<fmt:message key="web.gm.sysItems.col.wAccuracyDivisor.toolTips"/>"  type="number"/>
						</div>
						<label for="wAccuracyOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAccuracyOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAccuracyOffset.toolTips"/>">
							<input class="form-control" id="wAccuracyOffset" ng-model="wAccuracyOffset"  name="wAccuracyOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wAccuracyOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMaxInaccuracy" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMaxInaccuracy.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMaxInaccuracy.toolTips"/>">
							<input class="form-control" id="wMaxInaccuracy" ng-model="wMaxInaccuracy"  name="wMaxInaccuracy"placeholder="<fmt:message key="web.gm.sysItems.col.wMaxInaccuracy.toolTips"/>"  type="number" step="0.0001"/>
						</div>	
					</div>
					<div class="form-group">
						<label for="wNormalOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalOffset.toolTips"/>">
							<input class="form-control" id="wNormalOffset" ng-model="wNormalOffset"  name="wNormalOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wNormalFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalFactor.toolTips"/>">
							<input class="form-control" id="wNormalFactor" ng-model="wNormalFactor"  name="wNormalFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wOnairOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairOffset.toolTips"/>">
							<input class="form-control" id="wOnairOffset" ng-model="wOnairOffset"  name="wOnairOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMoveOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveOffset.toolTips"/>">
							<input class="form-control" id="wMoveOffset" ng-model="wMoveOffset"  name="wMoveOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMoveFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveFactor.toolTips"/>">
							<input class="form-control" id="wMoveFactor" ng-model="wMoveFactor"  name="wMoveFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-3">
					<div class="form-group">
						<label for="wCrossLengthBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrossLengthBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrossLengthBase.toolTips"/>">
							<input class="form-control" id="wCrossLengthBase" ng-model="wCrossLengthBase"  name="wCrossLengthBase"placeholder="<fmt:message key="web.gm.sysItems.col.wCrossLengthBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCrossLengthFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrossLengthFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrossLengthFactor.toolTips"/>">
							<input class="form-control" id="wCrossLengthFactor" ng-model="wCrossLengthFactor"  name="wCrossLengthFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wCrossLengthFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCrossDistanceBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrossDistanceBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrossDistanceBase.toolTips"/>">
							<input class="form-control" id="wCrossDistanceBase" ng-model="wCrossDistanceBase"  name="wCrossDistanceBase"placeholder="<fmt:message key="web.gm.sysItems.col.wCrossDistanceBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCrossDistanceFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrossDistanceFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrossDistanceFactor.toolTips"/>">
							<input class="form-control" id="wCrossDistanceFactor" ng-model="wCrossDistanceFactor"  name="wCrossDistanceFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wCrossDistanceFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-4">
					<div class="form-group">
						<label for="wSightNormalOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSightNormalOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSightNormalOffset.toolTips"/>">
							<input class="form-control" id="wSightNormalOffset" ng-model="wSightNormalOffset"  name="wSightNormalOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wSightNormalOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wSightOnairOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSightOnairOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSightOnairOffset.toolTips"/>">
							<input class="form-control" id="wSightOnairOffset" ng-model="wSightOnairOffset"  name="wSightOnairOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wSightOnairOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wSightMoveOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSightMoveOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSightMoveOffset.toolTips"/>">
							<input class="form-control" id="wSightMoveOffset" ng-model="wSightMoveOffset"  name="wSightMoveOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wSightMoveOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wSightInfo" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSightInfo.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSightInfo.toolTips"/>">
							<input class="form-control" id="wSightInfo" ng-model="wSightInfo"  name="wSightInfo"placeholder="<fmt:message key="web.gm.sysItems.col.wSightInfo.toolTips"/>" />
						</div>	
					</div>
				</div>
				<div id="tabs-5">
					<div class="form-group">	
						<label for="wChangeInTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wChangeInTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wChangeInTime.toolTips"/>">
							<input class="form-control" id="wChangeInTime" ng-model="wChangeInTime"  name="wChangeInTime"placeholder="<fmt:message key="web.gm.sysItems.col.wChangeInTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>					
					<div class="form-group">
						<label for="wMoveSpeedOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveSpeedOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveSpeedOffset.toolTips"/>">
							<input class="form-control" id="wMoveSpeedOffset" ng-model="wMoveSpeedOffset"  name="wMoveSpeedOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveSpeedOffset.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wPenetration" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wPenetration.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wPenetration.toolTips"/>">
							<input class="form-control" id="wPenetration" ng-model="wPenetration"  name="wPenetration"placeholder="<fmt:message key="web.gm.sysItems.col.wPenetration.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wDamage" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDamage.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDamage.toolTips"/>">
							<input class="form-control" id="wDamage" ng-model="wDamage"  name="wDamage"placeholder="<fmt:message key="web.gm.sysItems.col.wDamage.toolTips"/>"  type="number"/>
						</div>
						<label for="wRangeModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wRangeModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wRangeModifier.toolTips"/>">
							<input class="form-control" id="wRangeModifier" ng-model="wRangeModifier"  name="wRangeModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wRangeModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFireTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFireTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFireTime.toolTips"/>">
							<input class="form-control" id="wFireTime" ng-model="wFireTime"  name="wFireTime"placeholder="<fmt:message key="web.gm.sysItems.col.wFireTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wReloadTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wReloadTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wReloadTime.toolTips"/>">
							<input class="form-control" id="wReloadTime" ng-model="wReloadTime"  name="wReloadTime"placeholder="<fmt:message key="web.gm.sysItems.col.wReloadTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAmmoOneClip" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAmmoOneClip.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAmmoOneClip.toolTips"/>">
							<input class="form-control" id="wAmmoOneClip" ng-model="wAmmoOneClip"  name="wAmmoOneClip"placeholder="<fmt:message key="web.gm.sysItems.col.wAmmoOneClip.toolTips"/>"  type="number"/>
						</div>
						<label for="wAmmoCount" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAmmoCount.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAmmoCount.toolTips"/>">
							<input class="form-control" id="wAmmoCount" ng-model="wAmmoCount"  name="wAmmoCount"placeholder="<fmt:message key="web.gm.sysItems.col.wAmmoCount.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAutoFire" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAutoFire.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAutoFire.toolTips"/>">
							<input class="form-control" id="wAutoFire" ng-model="wAutoFire"  name="wAutoFire"placeholder="<fmt:message key="web.gm.sysItems.col.wAutoFire.toolTips"/>"  type="number"/>
						</div>
						<label for="wTimeToIdle" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wTimeToIdle.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wTimeToIdle.toolTips"/>">
							<input class="form-control" id="wTimeToIdle" ng-model="wTimeToIdle"  name="wTimeToIdle"placeholder="<fmt:message key="web.gm.sysItems.col.wTimeToIdle.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCritRate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCritRate.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCritRate.toolTips"/>">
							<input class="form-control" id="wCritRate" ng-model="wCritRate"  name="wCritRate"placeholder="<fmt:message key="web.gm.sysItems.col.wCritRate.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCritAvailable" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCritAvailable.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCritAvailable.toolTips"/>">
							<input class="form-control" id="wCritAvailable" ng-model="wCritAvailable"  name="wCritAvailable"placeholder="<fmt:message key="web.gm.sysItems.col.wCritAvailable.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wDamageModifer" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDamageModifer.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDamageModifer.toolTips"/>">
							<input class="form-control" id="wDamageModifer" ng-model="wDamageModifer"  name="wDamageModifer"placeholder="<fmt:message key="web.gm.sysItems.col.wDamageModifer.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="characterId" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.characterId.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.characterId.toolTips"/>">
							<input class="form-control" id="characterId" ng-model="characterId"  name="characterId"placeholder="<fmt:message key="web.gm.sysItems.col.characterId.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="wRangeStart" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wRangeStart.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wRangeStart.toolTips"/>">
							<input class="form-control" id="wRangeStart" ng-model="wRangeStart"  name="wRangeStart"placeholder="<fmt:message key="web.gm.sysItems.col.wRangeStart.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wRangeEnd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wRangeEnd.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wRangeEnd.toolTips"/>">
							<input class="form-control" id="wRangeEnd" ng-model="wRangeEnd"  name="wRangeEnd"placeholder="<fmt:message key="web.gm.sysItems.col.wRangeEnd.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wShootBulletCount" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wShootBulletCount.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wShootBulletCount.toolTips"/>">
							<input class="form-control" id="wShootBulletCount" ng-model="wShootBulletCount"  name="wShootBulletCount"placeholder="<fmt:message key="web.gm.sysItems.col.wShootBulletCount.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wSpread" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpread.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpread.toolTips"/>">
							<input class="form-control" id="wSpread" ng-model="wSpread"  name="wSpread"placeholder="<fmt:message key="web.gm.sysItems.col.wSpread.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-6">
					<div class="form-group">
						<label for="wOnairFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairFactor.toolTips"/>">
							<input class="form-control" id="wOnairFactor" ng-model="wOnairFactor"  name="wOnairFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFireMaxSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFireMaxSpeed.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFireMaxSpeed.toolTips"/>">
							<input class="form-control" id="wFireMaxSpeed" ng-model="wFireMaxSpeed"  name="wFireMaxSpeed"placeholder="<fmt:message key="web.gm.sysItems.col.wFireMaxSpeed.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFireStartSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFireStartSpeed.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFireStartSpeed.toolTips"/>">
							<input class="form-control" id="wFireStartSpeed" ng-model="wFireStartSpeed"  name="wFireStartSpeed"placeholder="<fmt:message key="web.gm.sysItems.col.wFireStartSpeed.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wFireAceleration" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFireAceleration.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFireAceleration.toolTips"/>">
							<input class="form-control" id="wFireAceleration" ng-model="wFireAceleration"  name="wFireAceleration"placeholder="<fmt:message key="web.gm.sysItems.col.wFireAceleration.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFireResistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFireResistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFireResistance.toolTips"/>">
							<input class="form-control" id="wFireResistance" ng-model="wFireResistance"  name="wFireResistance"placeholder="<fmt:message key="web.gm.sysItems.col.wFireResistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wXOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wXOffset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wXOffset.toolTips"/>">
							<input class="form-control" id="wXOffset" ng-model="wXOffset"  name="wXOffset"placeholder="<fmt:message key="web.gm.sysItems.col.wXOffset.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wHitSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wHitSpeed.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wHitSpeed.toolTips"/>">
							<input class="form-control" id="wHitSpeed" ng-model="wHitSpeed"  name="wHitSpeed"placeholder="<fmt:message key="web.gm.sysItems.col.wHitSpeed.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wLastHurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wLastHurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wLastHurt.toolTips"/>">
							<input class="form-control" id="wLastHurt" ng-model="wLastHurt"  name="wLastHurt"placeholder="<fmt:message key="web.gm.sysItems.col.wLastHurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wDmgModifyTimerMin" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMin.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMin.toolTips"/>">
							<input class="form-control" id="wDmgModifyTimerMin" ng-model="wDmgModifyTimerMin"  name="wDmgModifyTimerMin"placeholder="<fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMin.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wDmgModifyTimerMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMax.toolTips"/>">
							<input class="form-control" id="wDmgModifyTimerMax" ng-model="wDmgModifyTimerMax"  name="wDmgModifyTimerMax"placeholder="<fmt:message key="web.gm.sysItems.col.wDmgModifyTimerMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wDmgModifyMin" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDmgModifyMin.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDmgModifyMin.toolTips"/>">
							<input class="form-control" id="wDmgModifyMin" ng-model="wDmgModifyMin"  name="wDmgModifyMin"placeholder="<fmt:message key="web.gm.sysItems.col.wDmgModifyMin.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wDmgModifyMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wDmgModifyMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wDmgModifyMax.toolTips"/>">
							<input class="form-control" id="wDmgModifyMax" ng-model="wDmgModifyMax"  name="wDmgModifyMax"placeholder="<fmt:message key="web.gm.sysItems.col.wDmgModifyMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="backBoostPlus" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.backBoostPlus.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.backBoostPlus.toolTips"/>">
							<input class="form-control" id="backBoostPlus" ng-model="backBoostPlus"  name="backBoostPlus"placeholder="<fmt:message key="web.gm.sysItems.col.backBoostPlus.toolTips"/>"  type="number"/>
						</div>
					</div>
				</div>
				<div id="tabs-7">
					<div class="form-group">
						<label for="wNormalUpBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalUpBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalUpBase.toolTips"/>">
							<input class="form-control" id="wNormalUpBase" ng-model="wNormalUpBase"  name="wNormalUpBase"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalUpBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wNormalLateralBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalLateralBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalLateralBase.toolTips"/>">
							<input class="form-control" id="wNormalLateralBase" ng-model="wNormalLateralBase"  name="wNormalLateralBase"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalLateralBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wNormalUpModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalUpModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalUpModifier.toolTips"/>">
							<input class="form-control" id="wNormalUpModifier" ng-model="wNormalUpModifier"  name="wNormalUpModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalUpModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wNormalLateralModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalLateralModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalLateralModifier.toolTips"/>">
							<input class="form-control" id="wNormalLateralModifier" ng-model="wNormalLateralModifier"  name="wNormalLateralModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalLateralModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wNormalUpMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalUpMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalUpMax.toolTips"/>">
							<input class="form-control" id="wNormalUpMax" ng-model="wNormalUpMax"  name="wNormalUpMax"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalUpMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wNormalLateralMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalLateralMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalLateralMax.toolTips"/>">
							<input class="form-control" id="wNormalLateralMax" ng-model="wNormalLateralMax"  name="wNormalLateralMax"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalLateralMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wNormalDirChange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wNormalDirChange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wNormalDirChange.toolTips"/>">
							<input class="form-control" id="wNormalDirChange" ng-model="wNormalDirChange"  name="wNormalDirChange"placeholder="<fmt:message key="web.gm.sysItems.col.wNormalDirChange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-8">
					<div class="form-group">
						<label for="wMoveUpBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveUpBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveUpBase.toolTips"/>">
							<input class="form-control" id="wMoveUpBase" ng-model="wMoveUpBase"  name="wMoveUpBase"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveUpBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMoveLateralBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveLateralBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveLateralBase.toolTips"/>">
							<input class="form-control" id="wMoveLateralBase" ng-model="wMoveLateralBase"  name="wMoveLateralBase"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveLateralBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMoveUpModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveUpModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveUpModifier.toolTips"/>">
							<input class="form-control" id="wMoveUpModifier" ng-model="wMoveUpModifier"  name="wMoveUpModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveUpModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMoveLateralModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveLateralModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveLateralModifier.toolTips"/>">
							<input class="form-control" id="wMoveLateralModifier" ng-model="wMoveLateralModifier"  name="wMoveLateralModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveLateralModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMoveUpMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveUpMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveUpMax.toolTips"/>">
							<input class="form-control" id="wMoveUpMax" ng-model="wMoveUpMax"  name="wMoveUpMax"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveUpMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMoveLateralMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveLateralMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveLateralMax.toolTips"/>">
							<input class="form-control" id="wMoveLateralMax" ng-model="wMoveLateralMax"  name="wMoveLateralMax"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveLateralMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMoveDirChange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMoveDirChange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMoveDirChange.toolTips"/>">
							<input class="form-control" id="wMoveDirChange" ng-model="wMoveDirChange"  name="wMoveDirChange"placeholder="<fmt:message key="web.gm.sysItems.col.wMoveDirChange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-9">
					<div class="form-group">
						<label for="wOnairUpBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairUpBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairUpBase.toolTips"/>">
							<input class="form-control" id="wOnairUpBase" ng-model="wOnairUpBase"  name="wOnairUpBase"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairUpBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wOnairLateralBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairLateralBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairLateralBase.toolTips"/>">
							<input class="form-control" id="wOnairLateralBase" ng-model="wOnairLateralBase"  name="wOnairLateralBase"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairLateralBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wOnairUpModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairUpModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairUpModifier.toolTips"/>">
							<input class="form-control" id="wOnairUpModifier" ng-model="wOnairUpModifier"  name="wOnairUpModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairUpModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wOnairLateralModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairLateralModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairLateralModifier.toolTips"/>">
							<input class="form-control" id="wOnairLateralModifier" ng-model="wOnairLateralModifier"  name="wOnairLateralModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairLateralModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wOnairUpMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairUpMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairUpMax.toolTips"/>">
							<input class="form-control" id="wOnairUpMax" ng-model="wOnairUpMax"  name="wOnairUpMax"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairUpMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wOnairLateralMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairLateralMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairLateralMax.toolTips"/>">
							<input class="form-control" id="wOnairLateralMax" ng-model="wOnairLateralMax"  name="wOnairLateralMax"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairLateralMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wOnairDirChange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wOnairDirChange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wOnairDirChange.toolTips"/>">
							<input class="form-control" id="wOnairDirChange" ng-model="wOnairDirChange"  name="wOnairDirChange"placeholder="<fmt:message key="web.gm.sysItems.col.wOnairDirChange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-10">
					<div class="form-group">
						<label for="wStabTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabTime.toolTips"/>">
							<input class="form-control" id="wStabTime" ng-model="wStabTime"  name="wStabTime"placeholder="<fmt:message key="web.gm.sysItems.col.wStabTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wStabTime1" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabTime1.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabTime1.toolTips"/>">
							<input class="form-control" id="wStabTime1" ng-model="wStabTime1"  name="wStabTime1"placeholder="<fmt:message key="web.gm.sysItems.col.wStabTime1.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wStabLightTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabLightTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabLightTime.toolTips"/>">
							<input class="form-control" id="wStabLightTime" ng-model="wStabLightTime"  name="wStabLightTime"placeholder="<fmt:message key="web.gm.sysItems.col.wStabLightTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wStabHurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabHurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabHurt.toolTips"/>">
							<input class="form-control" id="wStabHurt" ng-model="wStabHurt"  name="wStabHurt"placeholder="<fmt:message key="web.gm.sysItems.col.wStabHurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wStabLightHurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabLightHurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabLightHurt.toolTips"/>">
							<input class="form-control" id="wStabLightHurt" ng-model="wStabLightHurt"  name="wStabLightHurt"placeholder="<fmt:message key="web.gm.sysItems.col.wStabLightHurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wStabDistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabDistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabDistance.toolTips"/>">
							<input class="form-control" id="wStabDistance" ng-model="wStabDistance"  name="wStabDistance"placeholder="<fmt:message key="web.gm.sysItems.col.wStabDistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wStabLightDistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabLightDistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabLightDistance.toolTips"/>">
							<input class="form-control" id="wStabLightDistance" ng-model="wStabLightDistance"  name="wStabLightDistance"placeholder="<fmt:message key="web.gm.sysItems.col.wStabLightDistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wStabWidth" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wStabWidth.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wStabWidth.toolTips"/>">
							<input class="form-control" id="wStabWidth" ng-model="wStabWidth"  name="wStabWidth"placeholder="<fmt:message key="web.gm.sysItems.col.wStabWidth.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-11">
					<div class="form-group">
						<label for="wExplodeTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wExplodeTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wExplodeTime.toolTips"/>">
							<input class="form-control" id="wExplodeTime" ng-model="wExplodeTime"  name="wExplodeTime"placeholder="<fmt:message key="web.gm.sysItems.col.wExplodeTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wReadyTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wReadyTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wReadyTime.toolTips"/>">
							<input class="form-control" id="wReadyTime" ng-model="wReadyTime"  name="wReadyTime"placeholder="<fmt:message key="web.gm.sysItems.col.wReadyTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wThrowOutTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wThrowOutTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wThrowOutTime.toolTips"/>">
							<input class="form-control" id="wThrowOutTime" ng-model="wThrowOutTime"  name="wThrowOutTime"placeholder="<fmt:message key="web.gm.sysItems.col.wThrowOutTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wBackFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wBackFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wBackFactor.toolTips"/>">
							<input class="form-control" id="wBackFactor" ng-model="wBackFactor"  name="wBackFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wBackFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wFlashRangeStart" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFlashRangeStart.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFlashRangeStart.toolTips"/>">
							<input class="form-control" id="wFlashRangeStart" ng-model="wFlashRangeStart"  name="wFlashRangeStart"placeholder="<fmt:message key="web.gm.sysItems.col.wFlashRangeStart.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFlashRangeEnd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFlashRangeEnd.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFlashRangeEnd.toolTips"/>">
							<input class="form-control" id="wFlashRangeEnd" ng-model="wFlashRangeEnd"  name="wFlashRangeEnd"placeholder="<fmt:message key="web.gm.sysItems.col.wFlashRangeEnd.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wFlashBackFactor" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFlashBackFactor.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFlashBackFactor.toolTips"/>">
							<input class="form-control" id="wFlashBackFactor" ng-model="wFlashBackFactor"  name="wFlashBackFactor"placeholder="<fmt:message key="web.gm.sysItems.col.wFlashBackFactor.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wTimeMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wTimeMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wTimeMax.toolTips"/>">
							<input class="form-control" id="wTimeMax" ng-model="wTimeMax"  name="wTimeMax"placeholder="<fmt:message key="web.gm.sysItems.col.wTimeMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wTimeFade" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wTimeFade.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wTimeFade.toolTips"/>">
							<input class="form-control" id="wTimeFade" ng-model="wTimeFade"  name="wTimeFade"placeholder="<fmt:message key="web.gm.sysItems.col.wTimeFade.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wTime.toolTips"/>">
							<input class="form-control" id="wTime" ng-model="wTime"  name="wTime"placeholder="<fmt:message key="web.gm.sysItems.col.wTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-12">
					<div class="form-group">
						<label for="wHurtRange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wHurtRange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wHurtRange.toolTips"/>">
							<input class="form-control" id="wHurtRange" ng-model="wHurtRange"  name="wHurtRange"placeholder="<fmt:message key="web.gm.sysItems.col.wHurtRange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wHurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wHurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wHurt.toolTips"/>">
							<input class="form-control" id="wHurt" ng-model="wHurt"  name="wHurt"placeholder="<fmt:message key="web.gm.sysItems.col.wHurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAddBlood" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAddBlood.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAddBlood.toolTips"/>">
							<input class="form-control" id="wAddBlood" ng-model="wAddBlood"  name="wAddBlood"placeholder="<fmt:message key="web.gm.sysItems.col.wAddBlood.toolTips"/>"  type="number"/>
						</div>
						<label for="wAmmoType" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAmmoType.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAmmoType.toolTips"/>">
							<input class="form-control" id="wAmmoType" ng-model="wAmmoType"  name="wAmmoType"placeholder="<fmt:message key="web.gm.sysItems.col.wAmmoType.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFlySpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFlySpeed.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFlySpeed.toolTips"/>">
							<input class="form-control" id="wFlySpeed" ng-model="wFlySpeed"  name="wFlySpeed"placeholder="<fmt:message key="web.gm.sysItems.col.wFlySpeed.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMaxaliveTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMaxaliveTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMaxaliveTime.toolTips"/>">
							<input class="form-control" id="wMaxaliveTime" ng-model="wMaxaliveTime"  name="wMaxaliveTime"placeholder="<fmt:message key="web.gm.sysItems.col.wMaxaliveTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wGravity" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wGravity.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wGravity.toolTips"/>">
							<input class="form-control" id="wGravity" ng-model="wGravity"  name="wGravity"placeholder="<fmt:message key="web.gm.sysItems.col.wGravity.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wParticlemun" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wParticlemun.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wParticlemun.toolTips"/>">
							<input class="form-control" id="wParticlemun" ng-model="wParticlemun"  name="wParticlemun"placeholder="<fmt:message key="web.gm.sysItems.col.wParticlemun.toolTips"/>"  type="number"/>
						</div>
						<label for="wShowSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wShowSpeed.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wShowSpeed.toolTips"/>">
							<input class="form-control" id="wShowSpeed" ng-model="wShowSpeed"  name="wShowSpeed"placeholder="<fmt:message key="web.gm.sysItems.col.wShowSpeed.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAmmopartKey" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAmmopartKey.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAmmopartKey.toolTips"/>">
							<input class="form-control" id="wAmmopartKey" ng-model="wAmmopartKey"  name="wAmmopartKey"placeholder="<fmt:message key="web.gm.sysItems.col.wAmmopartKey.toolTips"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="wCapsuleHeight" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCapsuleHeight.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCapsuleHeight.toolTips"/>">
							<input class="form-control" id="wCapsuleHeight" ng-model="wCapsuleHeight"  name="wCapsuleHeight"placeholder="<fmt:message key="web.gm.sysItems.col.wCapsuleHeight.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCapsuleRadius" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCapsuleRadius.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCapsuleRadius.toolTips"/>">
							<input class="form-control" id="wCapsuleRadius" ng-model="wCapsuleRadius"  name="wCapsuleRadius"placeholder="<fmt:message key="web.gm.sysItems.col.wCapsuleRadius.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-13">
					<div class="form-group">
						<label for="teamLevelOccupyLength" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.teamLevelOccupyLength.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.teamLevelOccupyLength.toolTips"/>">
							<input class="form-control" id="teamLevelOccupyLength" ng-model="teamLevelOccupyLength"  name="teamLevelOccupyLength"placeholder="<fmt:message key="web.gm.sysItems.col.teamLevelOccupyLength.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="teamLevelOccupyWidth" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.teamLevelOccupyWidth.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.teamLevelOccupyWidth.toolTips"/>">
							<input class="form-control" id="teamLevelOccupyWidth" ng-model="teamLevelOccupyWidth"  name="teamLevelOccupyWidth"placeholder="<fmt:message key="web.gm.sysItems.col.teamLevelOccupyWidth.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="teamItemUpgradePrice" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.teamItemUpgradePrice.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.teamItemUpgradePrice.toolTips"/>">
							<input class="form-control" id="teamItemUpgradePrice" ng-model="teamItemUpgradePrice"  name="teamItemUpgradePrice"placeholder="<fmt:message key="web.gm.sysItems.col.teamItemUpgradePrice.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="timeForCreate" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.timeForCreate.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.timeForCreate.toolTips"/>">
							<input class="form-control" id="timeForCreate" ng-model="timeForCreate"  name="timeForCreate"placeholder="<fmt:message key="web.gm.sysItems.col.timeForCreate.toolTips"/>"  type="number"/>
						</div>
					</div>
				</div>
				<div id="tabs-14">
					<div class="form-group">
						<label for="wUpModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wUpModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wUpModifier.toolTips"/>">
							<input class="form-control" id="wUpModifier" ng-model="wUpModifier"  name="wUpModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wUpModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wAccuracyTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAccuracyTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAccuracyTime.toolTips"/>">
							<input class="form-control" id="wAccuracyTime" ng-model="wAccuracyTime"  name="wAccuracyTime"placeholder="<fmt:message key="web.gm.sysItems.col.wAccuracyTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wAccuracyTimeModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wAccuracyTimeModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wAccuracyTimeModifier.toolTips"/>">
							<input class="form-control" id="wAccuracyTimeModifier" ng-model="wAccuracyTimeModifier"  name="wAccuracyTimeModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wAccuracyTimeModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wMaxAccuracy" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMaxAccuracy.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMaxAccuracy.toolTips"/>">
							<input class="form-control" id="wMaxAccuracy" ng-model="wMaxAccuracy"  name="wMaxAccuracy"placeholder="<fmt:message key="web.gm.sysItems.col.wMaxAccuracy.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMinAccuracy" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMinAccuracy.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMinAccuracy.toolTips"/>">
							<input class="form-control" id="wMinAccuracy" ng-model="wMinAccuracy"  name="wMinAccuracy"placeholder="<fmt:message key="web.gm.sysItems.col.wMinAccuracy.toolTips"/>"  type="number" step="0.0001"/>
						</div>	
					</div>
					<div class="form-group">
						<label for="wHitAcceleration" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wHitAcceleration.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wHitAcceleration.toolTips"/>">
							<input class="form-control" id="wHitAcceleration" ng-model="wHitAcceleration"  name="wHitAcceleration"placeholder="<fmt:message key="web.gm.sysItems.col.wHitAcceleration.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wHitDistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wHitDistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wHitDistance.toolTips"/>">
							<input class="form-control" id="wHitDistance" ng-model="wHitDistance"  name="wHitDistance"placeholder="<fmt:message key="web.gm.sysItems.col.wHitDistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wMaxDistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wMaxDistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wMaxDistance.toolTips"/>">
							<input class="form-control" id="wMaxDistance" ng-model="wMaxDistance"  name="wMaxDistance"placeholder="<fmt:message key="web.gm.sysItems.col.wMaxDistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wLastTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wLastTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wLastTime.toolTips"/>">
							<input class="form-control" id="wLastTime" ng-model="wLastTime"  name="wLastTime"placeholder="<fmt:message key="web.gm.sysItems.col.wLastTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wSpecialDistance" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpecialDistance.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpecialDistance.toolTips"/>">
							<input class="form-control" id="wSpecialDistance" ng-model="wSpecialDistance"  name="wSpecialDistance"placeholder="<fmt:message key="web.gm.sysItems.col.wSpecialDistance.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wSpecialRange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpecialRange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpecialRange.toolTips"/>">
							<input class="form-control" id="wSpecialRange" ng-model="wSpecialRange"  name="wSpecialRange"placeholder="<fmt:message key="web.gm.sysItems.col.wSpecialRange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wSpecialLasttime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpecialLasttime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpecialLasttime.toolTips"/>">
							<input class="form-control" id="wSpecialLasttime" ng-model="wSpecialLasttime"  name="wSpecialLasttime"placeholder="<fmt:message key="web.gm.sysItems.col.wSpecialLasttime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wSpecialHurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpecialHurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpecialHurt.toolTips"/>">
							<input class="form-control" id="wSpecialHurt" ng-model="wSpecialHurt"  name="wSpecialHurt"placeholder="<fmt:message key="web.gm.sysItems.col.wSpecialHurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wSpecialLasthurt" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wSpecialLasthurt.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wSpecialLasthurt.toolTips"/>">
							<input class="form-control" id="wSpecialLasthurt" ng-model="wSpecialLasthurt"  name="wSpecialLasthurt"placeholder="<fmt:message key="web.gm.sysItems.col.wSpecialLasthurt.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
				</div>
				<div id="tabs-15">
					<div class="form-group">
						<label for="wZoomTime" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wZoomTime.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wZoomTime.toolTips"/>">
							<input class="form-control" id="wZoomTime" ng-model="wZoomTime"  name="wZoomTime"placeholder="<fmt:message key="web.gm.sysItems.col.wZoomTime.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wZoomValue" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wZoomValue.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wZoomValue.toolTips"/>">
							<input class="form-control" id="wZoomValue" ng-model="wZoomValue"  name="wZoomValue"placeholder="<fmt:message key="web.gm.sysItems.col.wZoomValue.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCrouchUpBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchUpBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchUpBase.toolTips"/>">
							<input class="form-control" id="wCrouchUpBase" ng-model="wCrouchUpBase"  name="wCrouchUpBase"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchUpBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCrouchLateralBase" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchLateralBase.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchLateralBase.toolTips"/>">
							<input class="form-control" id="wCrouchLateralBase" ng-model="wCrouchLateralBase"  name="wCrouchLateralBase"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchLateralBase.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCrouchUpModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchUpModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchUpModifier.toolTips"/>">
							<input class="form-control" id="wCrouchUpModifier" ng-model="wCrouchUpModifier"  name="wCrouchUpModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchUpModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCrouchLateralModifier" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchLateralModifier.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchLateralModifier.toolTips"/>">
							<input class="form-control" id="wCrouchLateralModifier" ng-model="wCrouchLateralModifier"  name="wCrouchLateralModifier"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchLateralModifier.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCrouchUpMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchUpMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchUpMax.toolTips"/>">
							<input class="form-control" id="wCrouchUpMax" ng-model="wCrouchUpMax"  name="wCrouchUpMax"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchUpMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wCrouchLateralMax" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchLateralMax.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchLateralMax.toolTips"/>">
							<input class="form-control" id="wCrouchLateralMax" ng-model="wCrouchLateralMax"  name="wCrouchLateralMax"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchLateralMax.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="wCrouchDirChange" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wCrouchDirChange.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wCrouchDirChange.toolTips"/>">
							<input class="form-control" id="wCrouchDirChange" ng-model="wCrouchDirChange"  name="wCrouchDirChange"placeholder="<fmt:message key="web.gm.sysItems.col.wCrouchDirChange.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="wFixPrice" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.wFixPrice.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.wFixPrice.toolTips"/>">
							<input class="form-control" id="wFixPrice" ng-model="wFixPrice"  name="wFixPrice"placeholder="<fmt:message key="web.gm.sysItems.col.wFixPrice.toolTips"/>"  type="number"/>
						</div>
						<label for="index" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.index.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.index.toolTips"/>">
							<input class="form-control" id="index" ng-model="index"  name="index"placeholder="<fmt:message key="web.gm.sysItems.col.index.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="cResistanceFire" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cResistanceFire.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cResistanceFire.toolTips"/>">
							<input class="form-control" id="cResistanceFire" ng-model="cResistanceFire"  name="cResistanceFire"placeholder="<fmt:message key="web.gm.sysItems.col.cResistanceFire.toolTips"/>"  type="number" step="0.0001"/>	
							</div>
					</div>
					<div class="form-group">
						<label for="cResistanceBlast" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cResistanceBlast.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cResistanceBlast.toolTips"/>">
							<input class="form-control" id="cResistanceBlast" ng-model="cResistanceBlast"  name="cResistanceBlast"placeholder="<fmt:message key="web.gm.sysItems.col.cResistanceBlast.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="cResistanceBullet" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cResistanceBullet.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cResistanceBullet.toolTips"/>">
							<input class="form-control" id="cResistanceBullet" ng-model="cResistanceBullet"  name="cResistanceBullet"placeholder="<fmt:message key="web.gm.sysItems.col.cResistanceBullet.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="cResistanceKnife" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cResistanceKnife.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cResistanceKnife.toolTips"/>">
							<input class="form-control" id="cResistanceKnife" ng-model="cResistanceKnife"  name="cResistanceKnife"placeholder="<fmt:message key="web.gm.sysItems.col.cResistanceKnife.toolTips"/>"  type="number" step="0.0001"/>
						</div>
						<label for="cBloodAdd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.cBloodAdd.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.cBloodAdd.toolTips"/>">
							<input class="form-control" id="cBloodAdd" ng-model="cBloodAdd"  name="cBloodAdd"placeholder="<fmt:message key="web.gm.sysItems.col.cBloodAdd.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="fightNum" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.fightNum.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.fightNum.toolTips"/>">
							<input class="form-control" id="fightNum" ng-model="fightNum"  name="fightNum"placeholder="<fmt:message key="web.gm.sysItems.col.fightNum.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="isAsset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.isAsset.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.isAsset.toolTips"/>">
							<input class="form-control" id="isAsset" ng-model="isAsset"  name="isAsset"placeholder="<fmt:message key="web.gm.sysItems.col.isAsset.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="evaluateRmb" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.evaluateRmb.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.evaluateRmb.toolTips"/>">
							<input class="form-control" id="evaluateRmb" ng-model="evaluateRmb"  name="evaluateRmb"placeholder="<fmt:message key="web.gm.sysItems.col.evaluateRmb.toolTips"/>"  type="number" step="0.0001"/>
						</div>
					</div>
					<div class="form-group">
						<label for="timeForBuildingUp" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.timeForBuildingUp.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.timeForBuildingUp.toolTips"/>">
							<input class="form-control" id="timeForBuildingUp" ng-model="timeForBuildingUp"  name="timeForBuildingUp"placeholder="<fmt:message key="web.gm.sysItems.col.timeForBuildingUp.toolTips"/>"  type="number"/>
						</div>
					</div>
					<div class="form-group">
						<label for="timeForBuild" class="col-sm-2 control-label"><fmt:message key="web.gm.sysItems.col.timeForBuild.title"/></label>
						<div class="col-sm-4" title="<fmt:message key="web.gm.sysItems.col.timeForBuild.toolTips"/>">
							<input class="form-control" id="timeForBuild" ng-model="timeForBuild"  name="timeForBuild"placeholder="<fmt:message key="web.gm.sysItems.col.timeForBuild.toolTips"/>"  type="number"/>
						</div>
					</div>
				</div>
			</div>

    		<div class="box-header with-border"></div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submit" class="btn btn-primary"><fmt:message key="web.gm.button.submit"/></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" id="cancel" class="btn btn-default"><fmt:message key="web.gm.button.cancel"/></button>
				</div>
			</div>
		</form>
    </div>
</div>