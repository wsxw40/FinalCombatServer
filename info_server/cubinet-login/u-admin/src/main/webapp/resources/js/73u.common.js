/**
 * 进行跨越处理
 *
 * requestObj { url:"",相对于baseurl requestData:"" callback:"" };
 */
var CrossDomainRequest = new Object();

/**
 * 请求版本控制,目前版本仅仅只是标识作用。
 */
CrossDomainRequest.version = "0.01";

/**
 * 跨域请求的url
 */
CrossDomainRequest.baseRequestUrl = "http://192.168.1.100:8080/u-admin/"; //"/u-admin/";

/**
 * 是否打开跨域请求，true打开，false关闭 ,若关闭侧进行普通的ajax调用。
 */
CrossDomainRequest.isOnCrossDomainRequest = true;

/**
 * jquery模式进行处理
 * 
 * requestObj = 
 * { 
 * 		url:"", method:"post",
 * 		requestData:"method=handlerQueryContentSize&callback=callbackCrossDomainHandler"
 * }
 */
CrossDomainRequest.sendRequest = function(requestObj) {

	if (CrossDomainRequest.isOnCrossDomainRequest) {
		// 进行跨域处理
		$.ajax({
			url : CrossDomainRequest.baseRequestUrl + requestObj.url,
			processData : true,
			type : requestObj.method,
			timeout : 5000,
			data : requestObj.requestData,
			dataType : "jsonp",
			jsonp : requestObj.callback,
			error : function(msg) {}
		});
	} else {
		// 进行非跨域处理
		$.ajax({
			url : CrossDomainRequest.baseRequestUrl + requestObj.url,
			processData : true,
			type : requestObj.method,
			timeout : 5000,
			data : requestObj.requestData,
			dataType : "json",
			error : function(msg) {},
			success : requestObj.callback
		});
	}
};