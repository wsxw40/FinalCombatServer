$.ajaxSetup({
	type : "POST",
	encode : "UTF-8",
	dataType : "json",
	error : function(req, status, e) {
	}
});

function callbackInitData(data) {
	var newsColumns = data.newsColumn;
	var columns = "";
	$.each(newsColumns, function(i, newsColumn){
		var column = "<option value='"+newsColumn.newsColumnId+"'>"+newsColumn.columnName+"</option>";
		columns += column;
	});
	$("#newsColumnId").html(columns);
};

function callbackSubmit(data) {
	if (!data.error) {
		window.location = "cmsContentList.html";
	} else {
		alert("error="+data.error);
	}
};

function checkForm() {
	var result = true;
	if ($.trim($("#headline").val()) == "") {
		$("#headlineError").html("标题不能为空");
		result = false;
	}
	if ($.trim($("#subhead").val()) == "") {
		$("#subheadError").html("摘要不能为空");
		result = false;
	}
	if ($.trim($("#subhead").val()).length > 150) {
		$("#subheadError").html("摘要不能超过150个字");
		result = false;
	}
	return result;
}

$(function(){
	CrossDomainRequest.sendRequest({
		url:"nologin/cms/cmsContentInitData.htm",
		requestData:"callback=callbackInitData",
		method:"post",
		callback:"callbackInitData"
	});
	
	$("#btnSubmit").click(function(){
		if (checkForm()) {
			CrossDomainRequest.sendRequest({
				url:"nologin/cms/cmsContentPublish.htm",
				requestData:"callback=callbackSubmit&headline="+$("#headline").val()+"&newsColumnId="+$("#newsColumnId").val()+"&subhead="+$("#subhead").val()+"&author="+$("#author").val()+"&status="+$("#status").val()+"&type="+$("#type").val()+"&priorityLevel="+$("#priorityLevel").val()+"&thumbnails="+encodeURIComponent($("#thumbnails").attr("src"))+"&content="+encodeURIComponent(FCKeditorAPI.GetInstance('content').GetXHTML()),
				method:"post",
				callback:"callbackSubmit"
			});
		}
	});
	
	$("#file_upload").uploadify({
    	'height'        : 27,
    	'width'         : 80,
    	'buttonText'    : '浏览本地文件',
        'swf'           : '/u-admin/resources/uploadify/uploadify.swf',
//        'uploader'      : '/u-admin/nologin/cms/cmsContentUpload.htm',
        'uploader'      : '/u-admin/servlet/UploadifySerlet',
        'cancelImg'		: '/u-admin/resources/uploadify/uploadify-cancel.png',
        'fileTypeExts'	: '*.gif; *.jpg; *.png; *.jpeg',
        'fileSizeLimit'	: 300,
		'onUploadSuccess': function(file, data) {
			$("#thumbnails").attr("src", data);
		}
    });
});