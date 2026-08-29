<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initSysLevelController($scope){
	$('button#btnAdd').click(function(){
		$(this).parent().children('div[tabIndex]').children().clone().appendTo($(this).parent().children('nav')).removeClass('hidden');
	});
	$('#formSysLevel').on('click','[name="btnRemove"]',function(){
		$(this).parent().parent().remove();
	});
	var data=initTableRowForm($scope,'#formSysLevel', $('#app_root').val()+'/fcw/#/sysLevels');
	showSplitText('startPoints', data);
	showSplitText('blastPoints', data);
	showSplitText('flagPoints', data);
	showSplitText('supplies', data);
	$('#formSysLevel').submit(function(){
		$(this).find('nav[id]').each(function(){
			var name=$(this).attr('id');
			var str='';
			$(this).children('div').each(function(){
				$(this).find('input').each(function(){
					var tmp=$.trim($(this).val());
					if($(this).attr('type')=='number'){
						str+=(''==tmp?0:tmp)+','						
					}else{
						str+=tmp+',';
					}
				});
				str=str.replace(/\,$/img,';');
			});
			str=str.replace(/;$/img,'');
			$('[name="'+name+'"]').val(str);			
		});
	});
}
function showSplitText(name,data){
	if(undefined!=data && undefined!=data[name] && $.trim(data[name])!=''){
		console.log(data[name]);
		var arr=data[name].split(';');
		for(var i in arr){
			var arr1=arr[i].split(',');
			var div=$('div#'+name).children().clone();
			div.appendTo($('div#'+name).parent().children('nav')).removeClass('hidden');
			for(var j in arr1){
				$(div.find('input[name]').get(j)).val(arr1[j]);
			}
		}
	}	
}
</script>
<div class="box box-primary">
    <div class="box-header with-border"></div>
    <div class="box-body">
		<form id="formSysLevel" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveSysLevel">
			<input type="hidden" name="u"/>
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.id.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.id.toolTips"/>">
					<input type="number" min="1" class="form-control" id="id" ng-model="id"  name="id" required
					placeholder="<fmt:message key="web.gm.sysLevels.col.id.toolTips"/>" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.name.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.name.toolTips"/>">
					<input class="form-control" id="name" ng-model="name"  name="name" required
					placeholder="<fmt:message key="web.gm.sysLevels.col.name.toolTips"/>" />
				</div>
				<label for="displayName" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.displayName.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.displayName.toolTips"/>">
					<input class="form-control" id="displayName" ng-model="displayName"  name="displayName"
					placeholder="<fmt:message key="web.gm.sysLevels.col.displayName.toolTips"/>" />
				</div>
			</div>
<div class="form-group">
	<label for="type" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.type.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.type.toolTips"/>">
		<input class="form-control" id="type" ng-model="type"  name="type"placeholder="<fmt:message key="web.gm.sysLevels.col.type.toolTips"/>"  type="number"/>
	</div>
	<label for="index" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.index.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.index.toolTips"/>">
		<input class="form-control" id="index" ng-model="index"  name="index"placeholder="<fmt:message key="web.gm.sysLevels.col.index.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isnew" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isnew.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isnew.toolTips"/>">
		<input class="form-control" id="isnew" ng-model="isnew"  name="isnew"placeholder="<fmt:message key="web.gm.sysLevels.col.isnew.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group hidden">
	<label for="startPoints" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.title"/></label>
	<div class="col-sm-10" title="<fmt:message key="web.gm.sysLevels.col.startPoints.toolTips"/>">
		<textarea class="form-control" id="startPoints" ng-model="startPoints"  name="startPoints"placeholder="<fmt:message key="web.gm.sysLevels.col.startPoints.toolTips"/>" rows="3"></textarea>
	</div>
</div>
<div class="form-group hidden">
	<label for="blastPoints" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.toolTips"/>">
		<input class="form-control" id="blastPoints" ng-model="blastPoints"  name="blastPoints"placeholder="<fmt:message key="web.gm.sysLevels.col.blastPoints.toolTips"/>" />
	</div>
	<label for="flagPoints" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.toolTips"/>">
		<input class="form-control" id="flagPoints" ng-model="flagPoints"  name="flagPoints"placeholder="<fmt:message key="web.gm.sysLevels.col.flagPoints.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="weaponPoints" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.toolTips"/>">
		<input class="form-control" id="weaponPoints" ng-model="weaponPoints"  name="weaponPoints"placeholder="<fmt:message key="web.gm.sysLevels.col.weaponPoints.toolTips"/>" />
	</div>
	<label for="bossItems" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.bossItems.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.bossItems.toolTips"/>">
		<input class="form-control" id="bossItems" ng-model="bossItems"  name="bossItems"placeholder="<fmt:message key="web.gm.sysLevels.col.bossItems.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="isSelf" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isSelf.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isSelf.toolTips"/>">
		<input class="form-control" id="isSelf" ng-model="isSelf"  name="isSelf"placeholder="<fmt:message key="web.gm.sysLevels.col.isSelf.toolTips"/>"  type="number"/>
	</div>
	<label for="description" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.description.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.description.toolTips"/>">
		<input class="form-control" id="description" ng-model="description"  name="description"placeholder="<fmt:message key="web.gm.sysLevels.col.description.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="zombieInfo" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.zombieInfo.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.zombieInfo.toolTips"/>">
		<input class="form-control" id="zombieInfo" ng-model="zombieInfo"  name="zombieInfo"placeholder="<fmt:message key="web.gm.sysLevels.col.zombieInfo.toolTips"/>" />
	</div>
	<label for="levelHorizon" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.levelHorizon.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.levelHorizon.toolTips"/>">
		<input class="form-control" id="levelHorizon" ng-model="levelHorizon"  name="levelHorizon"placeholder="<fmt:message key="web.gm.sysLevels.col.levelHorizon.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="vehicleLineInfo" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.vehicleLineInfo.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.vehicleLineInfo.toolTips"/>">
		<input class="form-control" id="vehicleLineInfo" ng-model="vehicleLineInfo"  name="vehicleLineInfo"placeholder="<fmt:message key="web.gm.sysLevels.col.vehicleLineInfo.toolTips"/>" />
	</div>
	<label for="vehicleAddNum" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.vehicleAddNum.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.vehicleAddNum.toolTips"/>">
		<input class="form-control" id="vehicleAddNum" ng-model="vehicleAddNum"  name="vehicleAddNum"placeholder="<fmt:message key="web.gm.sysLevels.col.vehicleAddNum.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="vehicleInfo" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.vehicleInfo.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.vehicleInfo.toolTips"/>">
		<input class="form-control" id="vehicleInfo" ng-model="vehicleInfo"  name="vehicleInfo"placeholder="<fmt:message key="web.gm.sysLevels.col.vehicleInfo.toolTips"/>" />
	</div>
	<label for="targetSpeed" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.targetSpeed.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.targetSpeed.toolTips"/>">
		<input class="form-control" id="targetSpeed" ng-model="targetSpeed"  name="targetSpeed"placeholder="<fmt:message key="web.gm.sysLevels.col.targetSpeed.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="bloodOffset" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.bloodOffset.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.bloodOffset.toolTips"/>">
		<input class="form-control" id="bloodOffset" ng-model="bloodOffset"  name="bloodOffset"placeholder="<fmt:message key="web.gm.sysLevels.col.bloodOffset.toolTips"/>"  type="number"/>
	</div>
	<label for="isRushGold" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isRushGold.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isRushGold.toolTips"/>">
		<input class="form-control" id="isRushGold" ng-model="isRushGold"  name="isRushGold"placeholder="<fmt:message key="web.gm.sysLevels.col.isRushGold.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isMoneyReward" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isMoneyReward.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isMoneyReward.toolTips"/>">
		<input class="form-control" id="isMoneyReward" ng-model="isMoneyReward"  name="isMoneyReward"placeholder="<fmt:message key="web.gm.sysLevels.col.isMoneyReward.toolTips"/>"  type="number"/>
	</div>
	<label for="rushGoldPoint" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.rushGoldPoint.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.rushGoldPoint.toolTips"/>">
		<input class="form-control" id="rushGoldPoint" ng-model="rushGoldPoint"  name="rushGoldPoint"placeholder="<fmt:message key="web.gm.sysLevels.col.rushGoldPoint.toolTips"/>" />
	</div>
</div>
<div class="form-group">
	<label for="isVip" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isVip.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isVip.toolTips"/>">
		<input class="form-control" id="isVip" ng-model="isVip"  name="isVip"placeholder="<fmt:message key="web.gm.sysLevels.col.isVip.toolTips"/>"  type="number"/>
	</div>
	<label for="expAdd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.expAdd.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.expAdd.toolTips"/>">
		<input class="form-control" id="expAdd" ng-model="expAdd"  name="expAdd"placeholder="<fmt:message key="web.gm.sysLevels.col.expAdd.toolTips"/>"  type="number" step="0.0001"/>
	</div>
</div>
<div class="form-group">
	<label for="gpAdd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.gpAdd.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.gpAdd.toolTips"/>">
		<input class="form-control" id="gpAdd" ng-model="gpAdd"  name="gpAdd"placeholder="<fmt:message key="web.gm.sysLevels.col.gpAdd.toolTips"/>"  type="number" step="0.0001"/>
	</div>
	<label for="activityStart" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.activityStart.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.activityStart.toolTips"/>">
		<input class="form-control" id="activityStart" ng-model="activityStart"  name="activityStart"placeholder="<fmt:message key="web.gm.sysLevels.col.activityStart.toolTips"/>" type="datetime"/>
	</div>
</div>
<div class="form-group">
	<label for="activityEnd" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.activityEnd.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.activityEnd.toolTips"/>">
		<input class="form-control" id="activityEnd" ng-model="activityEnd"  name="activityEnd"placeholder="<fmt:message key="web.gm.sysLevels.col.activityEnd.toolTips"/>" type="datetime"/>
	</div>
	<label for="numForTeam" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.numForTeam.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.numForTeam.toolTips"/>">
		<input class="form-control" id="numForTeam" ng-model="numForTeam"  name="numForTeam"placeholder="<fmt:message key="web.gm.sysLevels.col.numForTeam.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group">
	<label for="isGm" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.isGm.title"/></label>
	<div class="col-sm-4" title="<fmt:message key="web.gm.sysLevels.col.isGm.toolTips"/>">
		<input class="form-control" id="isGm" ng-model="isGm"  name="isGm"placeholder="<fmt:message key="web.gm.sysLevels.col.isGm.toolTips"/>"  type="number"/>
	</div>
</div>
<div class="form-group hidden">
	<label for="supplies" class="col-sm-2 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.title"/></label>
	<div class="col-sm-10" title="<fmt:message key="web.gm.sysLevels.col.supplies.toolTips"/>">
		<textarea class="form-control" id="supplies" ng-model="supplies"  name="supplies"placeholder="<fmt:message key="web.gm.sysLevels.col.supplies.toolTips"/>" rows="3"></textarea>
	</div>
</div>
			<div class="form-group">
				<label for="startPoints" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.startPoints.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.startPoints.title"/></label>
				<div class="col-sm-10">
					<hr/>
					<div class="hidden" id="startPoints" tabindex="">
						<div class="form-group">
							<div class="col-sm-1" >
								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button>
							</div>
							<div  class="col-sm-9" >
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.teamId.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number"  title="<fmt:message key="web.gm.sysLevels.col.startPoints.teamId.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.x.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.startPoints.x.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.y.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.startPoints.y.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.z.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.startPoints.z.toolTips"/>"/>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.startPoints.rotate.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control" name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.startPoints.rotate.toolTips"/>"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav id="startPoints"></nav>					
					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button>
				</div>
			</div>
			<div class="form-group">
				<label for="blastPoints" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.blastPoints.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.blastPoints.title"/></label>
				<div class="col-sm-10">
					<hr/>
					<div class="hidden" id="blastPoints" tabindex="">
						<div class="form-group">
							<div class="col-sm-1" >
								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button>
							</div>
							<div  class="col-sm-9" >
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.x.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.x.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.y.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.y.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.z.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.z.toolTips"/>"/>
									</div>
								</div>		
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.rotate.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.rotate.toolTips"/>"/>
									</div>
								</div>						
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.x1.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.x1.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.y1.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.y1.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.blastPoints.z1.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.blastPoints.z1.toolTips"/>"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav id="blastPoints"></nav>					
					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button>
				</div>
			</div>
			<div class="form-group">
				<label for="flagPoints" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.flagPoints.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.flagPoints.title"/></label>
				<div class="col-sm-10">
					<hr/>
					<div class="hidden" id="flagPoints" tabindex="">
						<div class="form-group">
							<div class="col-sm-1" >
								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button>
							</div>
							<div  class="col-sm-9" >
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.teamId.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number"  title="<fmt:message key="web.gm.sysLevels.col.flagPoints.teamId.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.x.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.x.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.y.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.y.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.z.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.z.toolTips"/>"/>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.flagPoints.rotate.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control" name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.flagPoints.rotate.toolTips"/>"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav id="flagPoints"></nav>					
					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button>
				</div>
			</div>
<!-- 			<div class="form-group"> -->
<%-- 				<label for="weaponPoints" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.weaponPoints.title"/></label> --%>
<!-- 				<div class="col-sm-10"> -->
<!-- 					<hr/> -->
<!-- 					<div class="hidden" id="weaponPoints" tabindex=""> -->
<!-- 						<div class="form-group"> -->
<!-- 							<div class="col-sm-1" > -->
<!-- 								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button> -->
<!-- 							</div> -->
<!-- 							<div  class="col-sm-9" > -->
<!-- 								<div class="form-group"> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.weaponId.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" type="number"  title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.weaponId.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.x.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.x.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.y.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.y.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.z.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.z.toolTips"/>"/> --%>
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="form-group"> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.weaponPoints.rotate.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control" name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.weaponPoints.rotate.toolTips"/>"/> --%>
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<nav id="weaponPoints"></nav>					 -->
<!-- 					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button> -->
<!-- 				</div> -->
<!-- 			</div>		 -->
<!-- 			<div class="form-group"> -->
<%-- 				<label for="bossItems" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.bossItems.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.bossItems.title"/></label> --%>
<!-- 				<div class="col-sm-10"> -->
<!-- 					<hr/> -->
<!-- 					<div class="hidden" id="bossItems" tabindex=""> -->
<!-- 						<div class="form-group"> -->
<!-- 							<div class="col-sm-1" > -->
<!-- 								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button> -->
<!-- 							</div> -->
<!-- 							<div  class="col-sm-9" > -->
<!-- 								<div class="form-group"> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.bossItems.itemId.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" type="number"  title="<fmt:message key="web.gm.sysLevels.col.bossItems.itemId.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.bossItems.paramA.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" title="<fmt:message key="web.gm.sysLevels.col.bossItems.paramA.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.bossItems.paramB.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" title="<fmt:message key="web.gm.sysLevels.col.bossItems.paramB.toolTips"/>"/> --%>
<!-- 									</div> -->
<%-- 									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.bossItems.paramC.title"/></label> --%>
<!-- 									<div class="col-sm-2" > -->
<%-- 										<input class="form-control"  name="" title="<fmt:message key="web.gm.sysLevels.col.bossItems.paramC.toolTips"/>"/> --%>
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<nav id="bossItems"></nav>					 -->
<!-- 					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button> -->
<!-- 				</div> -->
<!-- 			</div>	 -->
			<div class="form-group">
				<label for="supplies" class="col-sm-2 control-label"  title="<fmt:message key="web.gm.sysLevels.col.supplies.toolTips"/>"><fmt:message key="web.gm.sysLevels.col.supplies.title"/></label>
				<div class="col-sm-10">
					<hr/>
					<div class="hidden" id="supplies" tabindex="">
						<div class="form-group">
							<div class="col-sm-1" >
								<button name="btnRemove" type="button" class="btn btn-info" ><i class="fa fa-minus"></i></button>
							</div>
							<div  class="col-sm-9" >
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.x.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.supplies.x.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.y.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.supplies.y.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.z.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" step="0.001" title="<fmt:message key="web.gm.sysLevels.col.supplies.z.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.type.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" title="<fmt:message key="web.gm.sysLevels.col.supplies.type.toolTips"/>"/>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.name.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control" name="" title="<fmt:message key="web.gm.sysLevels.col.supplies.name.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.value.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" title="<fmt:message key="web.gm.sysLevels.col.supplies.value.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.random.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" title="<fmt:message key="web.gm.sysLevels.col.supplies.random.toolTips"/>"/>
									</div>
									<label for="" class="col-sm-1 control-label"><fmt:message key="web.gm.sysLevels.col.supplies.skillTime.title"/></label>
									<div class="col-sm-2" >
										<input class="form-control"  name="" type="number" title="<fmt:message key="web.gm.sysLevels.col.supplies.skillTime.toolTips"/>"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<nav id="supplies"></nav>					
					<button id="btnAdd" type="button" class="btn btn-info" ><i class="fa fa-plus"></i></button>
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