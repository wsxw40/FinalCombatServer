<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
function initExportController($scope){
	$.post($('#app_root').val()+'/fcw/gm/exportedTables',function(msg){
		var index=0;
		for(var i in msg){
			if(i=='error'){
				$('#dialogAlert p').html(msg[i]);
				$('#dialogAlert').dialog('open');	
				return;
			}
			if(index%2==0){
				$('#formExport').append('<div class="form-group">');			
				$('#formExport').append('<div class="col-sm-5"><a href="'+$('#app_root').val()+'/fcw/gm/export?t='+msg[i]+'"><b>'+i.replace(/^w/i,'')+'</b></a></div>');					
			}else{
				$('#formExport').append('<div class="col-sm-5"><a href="'+$('#app_root').val()+'/fcw/gm/export?t='+msg[i]+'"><b>'+i.replace(/^w/i,'')+'</b></a></div>');		
				$('#formExport').append('</div>');	
			}
			index++;
		}
	});
}
</script>
<div class="box box-primary">
	<div class="box-header with-border">
	</div>
	<div class="box-body">
		<form id="formExport" class="form-horizontal">
		</form>
	</div>
</div>