<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/bj2/static/lib/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var start=10637963;
	for(var i=start;i<start+1;i++){
		$.getJSON('http://127.0.0.1:8080/bj/test?id='+i,function(data){
			console.log(data);
		});		
	}
// setTimeout('aa('+start+','+200+')',50);
});
function aa(i,counter){
	if(counter-->0){
		$.getJSON('http://192.168.2.76:8080/bj/test?id='+(i++),function(data){
			console.log(data);
		});				
	}
	setTimeout('aa('+i+','+counter+');',50);
}
</script>
</head>
<body>
test
</body>
</html>