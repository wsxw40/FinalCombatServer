<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initMessageController($scope){
	initTableRowForm($scope,'#formMessage', $('#app_root').val()+'/fcw/#/messages');

	var tableChosenPlayers;
	$('#dialogChosenPlayers').dialog({
			autoOpen:false,modal:true,draggable:true,width:$(window).width()*0.6,height:$(window).height()*0.8,
			open:function(){
				if(!tableChosenPlayers){
					tableChosenPlayers=initChosenPlayersController($scope);
				}else{
					tableChosenPlayers.draw();				
				}
			},
			buttons: [
			          {text:i18nWords.button.selectAll,
			        	  click:function(){tableChosenPlayers.selectAll();}			        	  
			          },
			          {text:i18nWords.button.submit,
			        	  click:function(){
			        		  var ids=$.trim($('#receiverIds').val());
			        		  if(ids.length>0){
				        		  ids=ids.split(",");			        			  
			        		  }else{
			        			  ids=[];
			        		  }
			        		  for(var i=0;i<tableChosenPlayers.getSelected().length;i++){
			        			  if(ids.indexOf(tableChosenPlayers.getSelected()[i])<0){
			        				  ids.push(tableChosenPlayers.getSelected()[i]);
			        			  }
			        		  }
			        		  $('#receiverIds').val(ids);
			        		  $(this).dialog('close');
			        	  }			        	  
			          },
			          {text:i18nWords.button.cancel,
			        	  click:function(){
			        		  $(this).dialog('close');
			        	  }			        	  
			          },
			]
	});
	$('#receiverIds').click(function(){
		$('#dialogChosenPlayers').dialog('open');
	});
	
	var tableChosenSysItems;
	$('#dialogChosenSysItems').dialog({
		autoOpen:false,modal:true,draggable:true,width:$(window).width()*0.6,height:$(window).height()*0.8,
		open:function(){
			if(!tableChosenSysItems){
				tableChosenSysItems=initChosenSysItemsController($scope);
			}else{
				tableChosenPlayers.draw();				
			}
		},
		buttons: [
		          {text:i18nWords.button.selectAll,
		        	  click:function(){tableChosenSysItems.selectAll();}			        	  
		          },
		          {text:i18nWords.button.submit,
		        	  click:function(){
		        		  var ids=$.trim($('#sysItems').val());
		        		  if(ids.length>0){
			        		  ids=ids.split(",");			        			  
		        		  }else{
		        			  ids=[];
		        		  }
		        		  for(var i=0;i<tableChosenSysItems.getSelected().length;i++){
		        			  if(ids.indexOf(tableChosenSysItems.getSelected()[i])<0){
		        				  ids.push(tableChosenSysItems.getSelected()[i]);
		        			  }
		        		  }
		        		  $('#sysItems').val(ids);
		        		  $(this).dialog('close');
		        	  }			        	  
		          },
		          {text:i18nWords.button.cancel,
		        	  click:function(){
		        		  $(this).dialog('close');
		        	  }			        	  
		          },
		]
	});
	$('#sysItems').click(function(){
		$('#dialogChosenSysItems').dialog('open');
	});
}
</script>
<div class="box box-primary">
	<div class="hidden">
		<div id="dialogChosenPlayers"><p><jsp:include page="chosenPlayers.jsp"/></p></div>
		<div id="dialogChosenSysItems"><p><jsp:include page="chosenSysItems.jsp"/></p></div>
	</div>
	<div class="box-header with-border"></div>
	<div class="box-body">
		<form id="formMessage" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/fcw/gm/saveMessage">
			<div class="form-group">
				<label for="receiverIds" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.receiverIds.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.messages.col.receiverIds.toolTips"/>">
					<textarea rows="5"  class="form-control" id="receiverIds" ng-model="receiverIds"  name="receiverIds"placeholder="<fmt:message key="web.gm.messages.col.receiverIds.toolTips"/>" required readonly></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="subject" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.subject.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.messages.col.subject.toolTips"/>">
					<input class="form-control" id="subject" ng-model="subject"  name="subject"placeholder="<fmt:message key="web.gm.messages.col.subject.toolTips"/>"/>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.content.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.messages.col.content.toolTips"/>">
					<textarea class="form-control" id="content" ng-model="content"  name="content"placeholder="<fmt:message key="web.gm.messages.col.content.toolTips"/>"  rows="3"></textarea>
				</div>
			</div>		
			<div class="form-group" >
				<label for="sysItems" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.sysItems.title"/></label>
				<div class="col-sm-10" title="<fmt:message key="web.gm.messages.col.sysItems.toolTips"/>">
					<textarea rows="5" class="form-control" id="sysItems" ng-model="sysItems"  name="sysItems"placeholder="<fmt:message key="web.gm.messages.col.sysItems.toolTips"/>" readonly></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label for="itemUnittypes" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.itemUnittypes.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.messages.col.itemUnittypes.toolTips"/>">
					<input class="form-control" id="itemUnittypes" ng-model="itemUnittypes"  name="itemUnittypes"placeholder="<fmt:message key="web.gm.messages.col.itemUnittypes.toolTips"/>" type="number"/>
				</div>
				<label for="itemUnits" class="col-sm-2 control-label"><fmt:message key="web.gm.messages.col.itemUnits.title"/></label>
				<div class="col-sm-4" title="<fmt:message key="web.gm.messages.col.itemUnits.toolTips"/>">
					<input class="form-control" id="itemUnits" ng-model="itemUnits"  name="itemUnits"placeholder="<fmt:message key="web.gm.messages.col.itemUnits.toolTips"/>" type="number"/>
				</div>
			</div>

			<div class="form-group" button>
				<div class="col-sm-offset-2 col-sm-10">
					<button id="submit" class="btn btn-primary"><fmt:message key="web.gm.button.submit"/></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" id="cancel" class="btn btn-default"><fmt:message key="web.gm.button.cancel"/></button>
				</div>
			</div>
		</form>
	</div>
</div>