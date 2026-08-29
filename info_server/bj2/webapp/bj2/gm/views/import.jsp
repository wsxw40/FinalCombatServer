<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<div class="dropzone">
  <div class="dz-message">
    <i class="fa fa-arrow-right"></i><fmt:message key="web.gm.hidden.import.pushFile"></fmt:message><br/>
    <span class="note"><fmt:message key="web.gm.hidden.import.clickSelectFile"></fmt:message></span>
  </div>
</div>
<script type="text/javascript">

  (function () {

//    var rABS = typeof FileReader !== "undefined"
//      && typeof FileReader.prototype !== "undefined"
//      && typeof FileReader.prototype.readAsBinaryString !== "undefined";
//
//    function fixdata(data) {
//      var o = "", l = 0, w = 10240;
//      for (; l < data.byteLength / w; ++l) {
//        o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
//      }
//      o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
//      return o;
//    }
	var myDone;
    var myDropzone=$(".dropzone").dropzone({
      url: 'gm/import',
      maxFilesize: 100,
      acceptedFiles:'.xlsx',
      dictDefaultMessage: '拖入文件导入',
      dictResponseError: '文件上传错误！',
      accept:function(file,done){
    	  myDone=done;
    	  done();
      },
      init: function () {

//        this.on('sending', function (file, xhr, formData) {
//          throw new Error();
//
//          var readCompleted = false;
//
//          var reader = new FileReader();
//
//          reader.onloadend = function () {
//            readCompleted = true;
//          };
//
//          reader.onload = function (e) {
//
//            var data = e.target.result;
//
//            var wb;
//            if (rABS) {
//              wb = XLSX.read(data, {type: 'binary'});
//            } else {
//              var arr = fixdata(data);
//              wb = XLSX.read(btoa(arr), {type: 'base64'});
//            }
//
//            var csv = XLSX.utils.sheet_to_csv(wb.Sheets[wb.SheetNames[0]]);
//            alert(csv);
//            formData.append('csv', csv);
//            formData.append('a', 'a');
//
//            alert(JSON.stringify(formData));
//            var readCompleted = true;
//
//          };
//
//          if (rABS) {
//            reader.readAsBinaryString(file);
//          } else {
//            reader.readAsArrayBuffer(file);
//          }
//
//
//          alert("end");
//          alert(JSON.stringify(formData));
//
//        });

        this.on('complete', function (file,msg) {
//        	var xhr = file.xhr;
//        	throw new Error();
//        	alert("adfasdf");
//        	alert(JSON.stringify(file.xhr.responseURL));
        });
		this.on('success',function(file,msg){
			try{
				msg=JSON.parse(msg);	
				if(undefined!=msg.error && null!=msg.error){
					myDone(msg.error);			
				}			
			}catch(e){}
		});
      }
    });
  }());
</script>