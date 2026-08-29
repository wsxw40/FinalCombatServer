$.ajaxSetup({
	type : "POST",
	encode : "UTF-8",
	dataType : "json",
	error : function(req, status, e) {
	}
});

/** callback for renderIndexCms()  */
function callbackInitData(data) {
	var newsColumns = data.newsColumn;
	var cmsContent  = data.cmsContent;
	// init data
	$("#headline").val(cmsContent.headline);
	$("#subhead").text(cmsContent.subhead);
	$("#author").val(cmsContent.author);
	$("#content").val(cmsContent.content);
	console.log("cmsContent.thumbnails="+cmsContent.thumbnails);
	$("#thumbnails").attr("src", cmsContent.thumbnails);
	
	// dropdown for 栏目 
	var columns = "";
	$.each(newsColumns, function(i, newsColumn){
		var column = "<option value='"+newsColumn.newsColumnId+"'";
		if (cmsContent.newsColumnId == newsColumn.newsColumnId)
			column += " selected";
		column += ">"+newsColumn.columnName+"</option>";
		columns += column;
	});
	$("#newsColumnId").html(columns);
	
	// dropdown for 显示优先级
	var priorityLevels = "";
	for (var i=1; i<4; i++) {
		var priorityLevel = "<option value='"+i+"'";
		if (cmsContent.priorityLevel==i)
			priorityLevel += " selected";
		priorityLevel += ">"+i+"</option>";
		priorityLevels += priorityLevel;
	}
	$("#priorityLevel").html(priorityLevels);
	
	var types = "";
	var optionName = new Array("CMS", "游戏", "媒体");
	for (var i=1; i<4; i++) {
		var type = "<option value='"+i+"'";
		if (cmsContent.type == i)
			type += "selected";
		type += ">"+optionName[i-1]+"</option>";
		types += type;
	}
	$("#type").html(types);
	
	// create dropdown for column_status
	var options = "";
	for (var i=0; i<2; i++) {
		var option = "<option value='"+i+"'";
		if (cmsContent.status==i)
			option += " selected";
		option += ">";
		if (i==0)
			option += "屏蔽";
		else
			option += "发布";
		option += "</option>";
		options += option;
	}
	$("#status").html(options);
}

function callbackSubmit(data) {
	if (!data.error) {
		window.location = "/u-admin/resources/html/cms/cmsContentList.html";
	} else {
		alert("更新失败，请重试");
	}
}

function QueryString(val) {
	var uri = window.location.search;
	var re = new RegExp("" +val+ "\=([^\&\?]*)", "ig");
	return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null);
}

function checkForm() {
	var result = true;
	if ($.trim($("#headline").val()) == "") {
		$("#headlineError").show();
		result = false;
	}
	if ($.trim($("#subhead").val()) == "") {
		$("#subheadError1").show();
		result = false;
	}
	if ($.trim($("#subhead").val()).length > 150) {
		$("#subheadError2").show();
		result = false;
	}
	return result;
}

$(function() {
	var newsId = QueryString("newsId");
	$("#newsId").val(newsId);
	CrossDomainRequest.sendRequest({
		url:"nologin/cms/cmsContentInitData.htm",
		requestData:"newsId="+newsId+"&callback=callbackInitData",
		method:"post",
		callback:"callbackInitData"
	});
	
	$("#btnSubmit").click(function(){
		if (checkForm()) {
			CrossDomainRequest.sendRequest({
				url:"nologin/cms/cmsContentEdit.htm",
				requestData:"callback=callbackSubmit&newsId="+$("#newsId").val()+"&headline="+$("#headline").val()+"&newsColumnId="+$("#newsColumnId").val()+"&subhead="+$("#subhead").val()+"&author="+$("#author").val()+"&type="+$("#type").val()+"&status="+$("#status").val()+"&priorityLevel="+$("#priorityLevel").val()+"&thumbnails="+encodeURIComponent($("#thumbnails").attr("src"))+"&content="+encodeURIComponent(FCKeditorAPI.GetInstance('content').GetXHTML()),
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