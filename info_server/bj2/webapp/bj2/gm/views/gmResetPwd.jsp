<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initGmResetPwdController($scope){
	$('#formGmResetPwd').submit(function(){
		if($('#password1').val()!=$('#password').val()){
			$('#password1').get(0).setCustomValidity(i18nWords.alert.pwdDiff);
		}
		$.post($('#app_root').val()+'/fcw/gmResetPwd',{pwd:$('#password').val()},function(msg){
			alertDialog(msg);
		});
		return false;
	});
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
	</div>
	<div class="box-body">
		<form id="formGmResetPwd" class="form-horizontal">
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"><fmt:message key="web.gm.gmResetPwd.pwd"/></label>
				<div class="col-sm-8" title="<fmt:message key="web.gm.gmResetPwd.pwdRule"/>">
					<input type="password" class="form-control" id="password" name="password" required pattern="^.{6,64}$"
					placeholder="<fmt:message key="web.gm.gmResetPwd.pwdRule"/>"/>
				</div>
			</div>
			<div class="form-group">
				<label for="password1" class="col-sm-2 control-label"><fmt:message key="web.gm.gmResetPwd.repeatPwd"/></label>
				<div class="col-sm-8" title="<fmt:message key="web.gm.gmResetPwd.pwdRule"/>">
					<input type="password" class="form-control" id="password1" name="password1" required pattern="^.{6,64}$"
					placeholder="<fmt:message key="web.gm.gmResetPwd.pwdRule"/>"/>
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