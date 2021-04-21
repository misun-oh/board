<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#addReplyBtn").on("click",function(){
		$("#myModal").modal();
	});
	
	$("#replyAddBtn").on("click",function(){
		addReply();
	});
	getReplyList(1,222);

});

function getReplyList(page,bno){
	
	
	
	
	getAjaxList(page, bno, 
			// 성공 콜백 함수
			function(data){
		
				// 서버로 부터 데이터 획득
				var replySrc = "";
				
				// 서버로 부터 가져온 데이터를 화면애 출력 해줍시다
				$.each(data, function(index, list){
					
					replySrc +="<li class='left clearfix' data-rno='"+list.rno+"'>";
					replySrc +="  <div><div class='header'><strong class='primary-font'>["
				    	   +list.rno+"] "+list.replyer+"</strong>"; 
				    replySrc +="    <small class='pull-right text-muted'>"
				           +list.updateTime+"</small></div>";
				    replySrc +="    <p>"+list.reply+"</p></div></li>";
			       
					
				});
				
								
				// 리플테이블 내용 삭제
				$(".chat").html("");
				// 리플테이블 내용 입력
				$(".chat").html(replySrc);
				
			});

}

/**
 * ajax 통신을 이용하여 서버로 부터 json형식의 데이터를 획득
 *
 * 콜백함수를 이용하여 후 처리
 */
function getAjaxList(pageNo, bno, callback, error){
	
	$.ajax({
		// 서버 접속 URL
		url : '/reply/list/'+pageNo+'/'+bno,
		method  : 'get',
		// 반환 데이터 타입
		dataType : 'json',
		
		// 처리 성공
		success : function(data, textStatus, jqXHR){
			console.log("success!");
			//console.log("data", data);
			//console.log("textStatus", textStatus);
			//console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(callback){
				callback(data);
			}
			
		},
		
		// 처리 실패
		error : function(jqXHR, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(error){
				error(errorThrown);
			}
			
		}
		
	});
	
}

function replyModal(){
	$("#myModal").modal("show");
}

function addReply(){

	// 파라메터를 JSON형식으로 만들어 줍니다.
	var reply = {
            reply: $("#reply").val(),
            replyer: $("#replyer").val(),
            bno: $("#bno").val()
          };
	
	
	addAjax(reply, 
			function(data){
					$("#myModal").modal("hide");
					// 등록후 리스트를 다시 조회
					getReplyList(1,222);
			});	
}

function addAjax( data ,callback ,error){
	$.ajax({
		url : '/reply/insert',
		method : 'post',
		
		data : JSON.stringify(data), 
		contentType : 'application/json; charset=UTF-8',

		// 처리 성공
		success : function(data, textStatus, jqXHR){
			console.log("success!");
			console.log("data", data);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(callback){
				console.log("callback",data);
				callback(data);
			}
			
		},
		
		// 처리 실패
		error : function(jqXHR, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(error){
				error(errorThrown);
			}
			
		}
	});
}

</script>


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
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                            
                            
                           <!-- 답글 -->
                           <div class='row'>

							  <div class="col-lg-12">
							
							    <!-- /.panel -->
							    <div class="panel panel-default">
							      
							      <div class="panel-heading">
							        <i class="fa fa-comments fa-fw"></i> Reply
							        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
							      </div>      
							      
							      
							      <!-- /.panel-heading -->
							      <div class="panel-body">        
							      
							        <ul class="chat">
										
							        </ul>
							        <!-- ./ end ul -->
							      </div>
							      <!-- /.panel .chat-panel -->
							
								<div class="panel-footer"></div>
							
							
									</div>
							  </div>
							  <!-- ./ end row -->
							  
							  
							  
							</div>
                            
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
			
			
			
        <!-- 모달 Modal -->
        
        bno<input type="text" id="bno" value=222>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                               <h4 class="modal-title" id="myModalLabel">Reply</h4>
                           </div>
                           <div class="modal-body">
                                 <ul class="list-group list-group-flush">
							    <li class="list-group-item">
									<input type="text" class="form-control ml-2" placeholder="replyer" id="replyer">
								</li>
								<li class="list-group-item">
									<textarea class="form-control" id="reply" placeholder="reply" rows="3"></textarea>
							    </li>
							</ul>
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-default" data-dismiss="modal">cancle</button>
                               <button type="button" class="btn btn-primary" id = replyAddBtn>save</button>
                           </div>
                       </div>
                       <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <!-- /.modal -->
                

        
<jsp:include page="/resources/header/bottom.jsp"/>


