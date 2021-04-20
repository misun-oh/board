<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

var xhr = new XMLHttpRequest();
var url = 'http://api.seibro.or.kr/openapi/service/StockSvc/getKDRIssuLmtDetailsN1'; /*URL*/
var queryParams = '?' + encodeURIComponent('DU3KPJJtl2AKmdX%2FWH2DnaDhijW8wJFXUTXPdEjTBlHxKSdZVfJP5mIBAcUevwxp3ZQrC8gMUPuq%2BMqo8MDEPA%3D%3D') + '='+'서비스키'; /*Service Key*/
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
queryParams += '&' + encodeURIComponent('isin') + '=' + encodeURIComponent('KR8392070007'); /**/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
        console.log("res",'Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
    }
};

xhr.send('');

function comAjax(url, method, data){
	var resultData;	
	$.ajax({
		type : method,
			url : url,
			async : false,
			// 리턴타입을 json으로 해주어야지 결과를 json형식으로 받아옴
			dataType : "json",
			
			// request 정보 
			data : JSON.stringify(data),
			// http Header
		    contentType : "application/json; charset=utf-8",
		    
			// 성공콜백 
			success : function(result, status, xhr){
				console.log("result", result); 	// 컨트롤러의 리턴값
				console.log("status", status);	// 상태
				console.log("xhr", xhr);	//
				
				resultData = result;
			},
			
			// 실패 콜백
			error : function(xhr, status, er){
				if(errorFunc){
					errorFunc(er)
				}
			},
			
			// 파이널
			
		});
	
	return resultData;
}


if('${resMsg}' != ''){
	alert('${resMsg}');	
}

// 답글달기
function addReply(){
	var data = {
				reply:$('#reply').val(),
				replyer:$('#replyer').val(),
				bno:$("#bno").val()
				}; 
	var resultData = comAjax('/reply/insert','post', data);
	console.log("res",resultData);
	
	// 리스트 조회
}

// 답글 리스트
function replyList(){
	var url = '/reply/pages/'+$('#bno').val()+"/"+1 ;
	
	var data = comAjax(url,'get');
	
	
	// 답글 리스트 제거
	$("#replyList").html("");
	
	var content = "<tbody>";
	// 답글 리스트 생성
	$.each(data.result,function(index,item){
		content += "<tr><td>"+( index+1)+"</td><td>"+item.reply+"</td><td><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></td></tr>"; 
		content += "<tr><td colspan=3>"+item.reply+"</td></tr>";
		
		
		
	});
	
	content +="</tbody>";
	// 답글 리스트 제거
	$("#replyList").html(content);
	
	
}




function move(url){
	document.moveForm.action = url;
	document.moveForm.submit();
	
}
</script>


<form name = moveForm>
	<input type=text name=pageNo value=${criteria.pageNo }>
	<input type=text name=type value=${criteria.type }>
	<input type=text name=keyword value=${criteria.keyword }>
	<input type=text name=bno value=${vo.bno } id=bno>
</form>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="form-group">
                                <label>제목</label>
                                <input readonly class="form-control" value="${vo.title}">
                                
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea readonly class="form-control" rows="3">${vo.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input readonly class="form-control" value="${vo.writer}">
                                
                            </div>
                            <div class="form-group">
                                <label>등록시간</label>
                                <input readonly class="form-control" value="${vo.regdate}">
                                
                            </div>
                            <div class="card mb-2">
								<div class="card-header bg-light">
								        <i class="fa fa-comment fa"></i> REPLY
								</div>
								<div class="card-body">
									<ul class="list-group list-group-flush">
									    <li class="list-group-item">
										<div class="form-inline mb-2">
											<label for="replyId"><i class="fa fa-user-circle-o fa-2x"></i></label>
											<input type="text" class="form-control ml-2" placeholder="Enter yourId" id="replyer">
											<button type="button" class="btn btn-dark mt-3" onClick="javascript:addReply();">등록</button>
										</div>
										</li>
										<li class="list-group-item">
										<textarea class="form-control" id="reply" rows="3"></textarea>
							
									    </li>
									</ul>
								</div>
								<table class="table table-hover" id = replyList>
									<tr>
									<td>a</td><td>writer</td>
									</tr>
									<tr>
									<td>a</td><td>writer</td>
									</tr>
									<tr>
									<td>a</td><td>writer</td>
									</tr>
									<tr>
									<td>a</td><td>writer</td>
									</tr>
								</table>
							</div>
                            
                            <button type="button" class="btn btn-default" onClick="move('/board/edit')">수정</button>
							<button class="btn btn-default" onClick="move('/board/delete')">삭제</button>
							<button class="btn btn-default" onClick="move('/board/list')">목록</button>
							
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
        
<jsp:include page="/resources/header/bottom.jsp"/>
