$.ajaxSetup({
	type : "POST",
	encode : "UTF-8",
	dataType : "json",
	error : function(req, status, e) {
	}
});

var rowCount = 10;

/** 分页处理 */
function callbackCrossDomainHandler(data) {
	var totalSize = data.jsonObjSize;
	$("#next").pagination(totalSize, {
		callback : renderIndexCms,
		items_per_page : rowCount,
		prev_text : "上一页",
		next_text : "下一页"
	});
}
/** 进行跨域处理请求 */
function sendCrossDomainRequest() {
	CrossDomainRequest.sendRequestWithRaw({
		id:"scriptId",
		url:"nologin/cms/cmsContentList.htm",
		requestData:"method=handlerQueryContentSize&callback=callbackCrossDomainHandler"
	});
}
function getParamVal(){
	return "headline="+$("#headline").val()+"&type="+$("#type").val()+"&status="+$("#status").val()+"&fromDate="+$("#from").val()+"&toDate="+$("#to").val()+"&author="+$("#author").val();
}

function renderIndexCms(pageIndex) {
	CrossDomainRequest.sendRequest({
		url:"nologin/cms/cmsContentList.htm",
		method:"post",
		requestData:"method=handlerQueryContent&callback=handlerRenderPage&rowCount="+rowCount+"&pageIndex="+pageIndex+"&"+getParamVal(),
		callback:"handlerRenderPage"
	});
}
/** callback for renderIndexCms()  */
function handlerRenderPage(data) {
	$("#latestnews").empty();
	var tables = "<table border='1'><tr><td>排序</td><td>内容标题</td><td>游戏栏目</td><td>内容状态</td><td>作者</td><td>编辑时间</td><td>动作</td><td>操作</td></tr>";
	var jsonObjList = data.jsonObjList;
	$.each(jsonObjList, function(i, item){
		var table = "<tr>";
		// 排序
		table += "<td>"+item.priorityLevel+"</td>";
		// 内容标题
		var headline = item.headline;
		if (headline.length>20) {
			headline = headline.substring(0, 20);
		}
		table += "<td>" + headline + "</td>";
		// 游戏栏目
		var columnid = item.newsColumnId;
		var columnName = "";
		if (columnid==1)
			columnName = "游戏攻略";
		else if (columnid==2)
			columnName = "游戏试玩";
		else if (columnid==3)
			columnName = "游戏MM";
		table += "<td>"+columnName+"</td>";
		// 内容状态
		var status = item.status == "1" ? "发布" : "屏蔽";
		table += "<td>"+status+"</td>";
		// 作者
		var author = item.author==null ? "" : item.author;
		table += "<td>"+author+"</td>";
		// 编辑时间
		var modifyDate = new Date(item.lastModifyDate);
		table += "<td>"+(modifyDate.getFullYear()+"-"+(modifyDate.getMonth()+1)+"-"+modifyDate.getDate()+" "+modifyDate.getHours()+":"+modifyDate.getMinutes())+"</td>";
		// 动作
		table += "<td><a href='javascript:void(0);' onclick='deleteNews(\""+item.strNewId+"\")'>删除</a></td>";
		// 操作
		table += "<td><a href='cmsContentEdit.html?newsId="+item.strNewId+"'>编辑</a></td>";
		table += "</tr>";
		
		tables += table;
	});
	tables += "</table>";
	$("#latestnews").append(tables);
};

function deleteNews(newsId) {
	if (confirm("确认要删除此条新闻吗？不可恢复！")) {
		CrossDomainRequest.sendRequest({
			url:"nologin/cms/cmsContentList.htm",
			requestData:"method=deleteNewsById&callback=callbackDeleteNews&newsId="+newsId,
			method:"post",
			callback:"callbackDeleteNews"
		});
	}
}
function callbackDeleteNews(data) {
	if (!data.isSuccess) {
		alert("删除失败");
	}
	window.location = "/u-admin/resources//html/cms/cmsContentList.html";
}

/**
 * 页面加载时进行处理，并且渲染html页面
 */
$(function() {
	//初始化分页
	CrossDomainRequest.sendRequest({
		url:"nologin/cms/cmsContentList.htm",
		requestData:"method=handlerQueryContentSize&callback=callbackCrossDomainHandler",
		method:"post",
		callback:"callbackCrossDomainHandler"
	});
	$("#from").datepicker({
		defaultDate: "+1w",
		showOn: "both",
		buttonImage: "/u-admin/resources/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true,
		buttonText: '请选起始择时间',
		minDate: '2012-10-01',
		changeYear: true,
		numberOfMonths: 3,
		onSelect: function( selectedDate ) {
			$("#to").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#to").datepicker({
		defaultDate: "+1w",
		showOn: "both",
		buttonImage: "/u-admin/resources/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true,
		buttonText: '请选结束择时间',
		minDate: '2012-10-01',
		changeYear: true,
		numberOfMonths: 3,
		onSelect: function( selectedDate ) {
			$("#from").datepicker( "option", "maxDate", selectedDate );
		}
	});
	$("#btnSearch").click(function(){
		CrossDomainRequest.sendRequest({
			url:"nologin/cms/cmsContentList.htm",
			method:"post",
			requestData:"method=handlerQueryContentSize&callback=callbackCrossDomainHandler&"+getParamVal(),
			callback:"callbackCrossDomainHandler"
		});
	});
	$("#btnCreate").click(function(){
		window.location = "/u-admin/resources/html/cms/cmsContentCreate.html";
	});
});