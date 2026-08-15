<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initPlayerController($scope){
	if(undefined==localStorage['formData']){
		location.href=$('#app_root').val()+'/fcw/#/players';			
	}
	var data=JSON.parse(localStorage['formData']);
	if(data.formId!='#formPlayer'){
		location.href=$('#app_root').val()+'/fcw/#/players';					
	}
	initTableRowForm($scope,'#formPlayer', $('#app_root').val()+'/fcw/#/players');
}
</script>
<div class="box box-primary">
	<div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formPlayer" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/savePlayer">
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.players.col.id.toolTips"/>"/>
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.userName.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.userName.toolTips"/>">
					<p  class="form-control-static"  id="userName" ng-model="userName" ></p>
				</div>
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.name.toolTips"/>">
					<p  class="form-control-static"  id="name" ng-model="name" ></p>
				</div>
			</div>
			<div class="form-group">
				<label for="xunleiPoint" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.xunleiPoint.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.xunleiPoint.toolTips"/>">
					<input type="number" class="form-control" id="xunleiPoint" ng-model="xunleiPoint"  name="xunleiPoint" 
					placeholder="<fmt:message key="web.gm.players.col.xunleiPoint.toolTips"/>" />
				</div>
				<label for="voucher" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.voucher.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.voucher.toolTips"/>">
					<input type="number" class="form-control" id="voucher" ng-model="voucher" name="voucher"  
					placeholder="<fmt:message key="web.gm.players.col.voucher.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="exp" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.exp.title"/></label>
				<div class="col-sm-4"  title="<fmt:message key="web.gm.players.col.exp.toolTips"/>">
					<input type="number" class="form-control" id="exp" ng-model="exp"  name="exp" 
					placeholder="<fmt:message key="web.gm.players.col.exp.toolTips"/>"/>
				</div>
				<label for="rank" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.rank.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.rank.toolTips"/>">
					<input type="number" class="form-control" id="rank" ng-model="rank" name="rank"  
					placeholder="<fmt:message key="web.gm.players.col.rank.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="isVip" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.isVip.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.isVip.toolTips"/>">
					<input class="form-control" id="isVip" ng-model="isVip"  name="isVip" 
					placeholder="<fmt:message key="web.gm.players.col.isVip.toolTips"/>" />
				</div>
				<label for="vipExp" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.vipExperience.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.vipExperience.toolTips"/>">
					<input type="number" class="form-control" id="vipExp" ng-model="vipExp" name="vipExperience"  
					placeholder="<fmt:message key="web.gm.players.col.vipExperience.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="gPoint" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.gPoint.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.gPoint.toolTips"/>">
					<input type="number" class="form-control" id="gPoint" ng-model="gPoint"  name="gPoint" 
					placeholder="<fmt:message key="web.gm.players.col.gPoint.toolTips"/>" />
				</div>
				<label for="gScore" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.generalScore.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.generalScore.toolTips"/>">
					<input type="number" class="form-control" id="gScore" ng-model="gScore"  name="gScore" 
					placeholder="<fmt:message key="web.gm.players.col.generalScore.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="unusableResource" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.unusableResource.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.unusableResource.toolTips"/>">
					<input type="number" class="form-control" id="unusableResource" ng-model="unusableResource"  name="unusableResource" 
					placeholder="<fmt:message key="web.gm.players.col.unusableResource.toolTips"/>"/>
				</div>
				<label for="usableResource" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.usableResource.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.usableResource.toolTips"/>">
					<input type="number" class="form-control" id="usableResource" ng-model="usableResource"  name="usableResource" 
					placeholder="<fmt:message key="web.gm.players.col.usableResource.toolTips"/>"/>
				</div>
			</div>
			<div class="form-group">
				<label for="matchWin" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.matchWin.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.matchWin.toolTips"/>">
					<input class="form-control" id="matchWin" ng-model="matchWin"  name="matchWin" 
					placeholder="<fmt:message key="web.gm.players.col.matchWin.toolTips"/>"/>
				</div>
				<label for="tutorial" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.tutorial.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.tutorial.toolTips"/>">
					<input class="form-control" id="tutorial" ng-model="tutorial"  name="tutorial" 
					placeholder="<fmt:message key="web.gm.players.col.tutorial.toolTips"/>"/>
				</div>
			</div>
			<div class="form-group">
				<label for="characterSize" class="col-sm-2 control-label"><fmt:message key="web.gm.players.col.characterSize.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.players.col.characterSize.toolTips"/>">
					<input type="number" class="form-control" id="characterSize" ng-model="characterSize"  name="characterSize" 
					placeholder="<fmt:message key="web.gm.players.col.characterSize.toolTips"/>"/>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submit" class="btn btn-primary"><fmt:message key="web.gm.button.submit"/></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" id="cancel" class="btn btn-default"><fmt:message key="web.gm.button.cancel"/></button>
				</div>
			</div>
		</form>
	</div>
</div>