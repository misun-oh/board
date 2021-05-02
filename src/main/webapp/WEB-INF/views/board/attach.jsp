<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

// . : 개행 문자를 제외한 모든 단일 문자와 대응됩니다.
// \. : .을 일반 문자로 인식 할수 있도록 처리 해줍니다. 
// (x|y) : x 또는 y
// exe|sh|zip|alz : exe 또는 sh 또는 zip 또는 alz
// regex.test("문자열")
// 작성된 패턴에 대응되는 문자열이 있는지 검사하는 RegExp 메소드 입니다. true 나 false를 반환합니다.
// 파일명에 .exe, .sh, .zip, .alz 를 포함하는 경우 업로드 불가능 하도록 처리
var regex = new RegExp("\.(exe|sh|zip|alz)$");

var maxSize = 1024*1024*5; //5MB
 
function checkExtension(files){

	var msg = "";
 	$.each(files, function(index, file){
 		
 		console.log("file", file);
 		if(file.size >= maxSize){
 			msg += file.name + "의 파일의 사이즈는 "+ file.size+"입니다.\n파일 사이즈는 5MB로 제한 됩니다.\n";
 		}
 		 
 		if(regex.test(file.name)){
 			msg += "해당 종류의 파일은 업로드할 수 없습니다.\n";
 		}
 				
 	});
 	
 	if(msg!=""){
 		alert(msg);
 		return false;
 	} else {
 		return true;	
 	}
 	
 	
 }


$(document).ready(function(){
	//업로드 버튼을 클릭하면 이벤트 발생
	$("#uploadBtn").on("click", function(e){
		
		// 업로드 파일 사이즈및 종류 체크
		if(!checkExtension(document.fileUploadForm.uploadFile.files)){
			return false;
		}
		
		// form 데이터 생성
		var formData = new FormData(document.fileUploadForm);
		console.log("formData.get",formData.get("uploadFile"));
		
		var uploadFile = formData.get("uploadFile");
		
		
		// processData속성과 contentType속성의 조절이 필수
		$.ajax({
		    	url: '/ajaxFileUpload',
		    	method: 'post',
		    	dataType : 'json',
		    	
		    	processData: false,
		      	contentType: false,
		      	data: formData,
		      	
		      	success: function(result){
		      		console.log("result" + result);
		      		var fileListContent = "";
		      		$.each(result, function (index, item){
		      			var filePath = encodeURIComponent(item.uploadPath+item.uuid+"_"+item.fileName);
		      			var s_filePath = encodeURIComponent(item.uploadPath+"s_"+item.uuid+"_"+item.fileName);
		      			if(item.image){
		      				fileListContent +=
		      					"<li><a href='/download?fileName=" + filePath +"'>"
		      					+ "<img src='/display?fileName=" + s_filePath+"'>"
		      					+ item.fileName
		      					+ "</a></li>";		
		      			} else {
		      				fileListContent +=
		      					"<li><a href='/download?fileName="+ filePath +"'>"
			      				+ item.fileName
		      					+ "</a></li>";		
		      			}
		      	});
		      	
		      	// 업로드된 파일 이름을 화면에 출력
		      	$("#fileList").html(fileListContent);
		      		
		     }
		}); //$.ajax
	}); 
});

</script>
<form name="fileUploadForm">
<input type=file name=uploadFile multiple>
<button type="button" id = "uploadBtn">upload</button>
<div>
	<ul id=fileList></ul>
</div>
</form>