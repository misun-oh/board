<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">


$(document).ready(function(){
	
	// 모달창 보여주기
	$("#addReplyBtn").on("click",function(){
		addModal();
	});
	
	// 등록버튼
	$("#regBtn").on("click",function(){
		addAjax();
	});
	
	// 수정버튼
	$("#editBtn").on("click",function(){
		updateAjax();
	});
	
	// 삭제버튼
	$("#deleteBtn").on("click", function(){
		deleteAjax();
	});
	
	// 리플을 조회해서 화면에 출력
	getAjaxList();

});

//등록창
function addModal(){
	// 버튼 숨기기
	$("#editBtn").hide();
	$("#deleteBtn").hide();
	
	// 등록일 숨기기
	$("#replydateLi").hide();
	
	// 모달창 띄우기
	$("#myModal").modal("show");
	
	$("#reply").val("");
	$("#replyer").val("");
	
}


function getAjaxDetail(rno){
	$.ajax({
		url :'/reply/get/'+rno, 
		method : 'get',
		dataType : 'json',
		
		success : function(data, status){
			// 모달창 세팅
			$("#reply").val(data.reply);
			$("#replyer").val(data.replyer);
			$("#updatedate").val(data.updatedate);
			$("#rno").val(data.rno);
			
			// 버튼 보여주기
			$("#editBtn").show();
			$("#deleteBtn").show();
			$("#replydateLi").show();
			
			// 모달창 보여주기
			$("#myModal").modal("show");
		},
		error : function(xhr, status, error){
			console.log(error);
			alert('데이터를 조회중 오류가 발생했습니다.');
		}
			
	});
}

/**
 * ajax 통신을 이용하여 서버로 부터 json형식의 데이터를 획득
 *
 * 콜백함수를 이용하여 후 처리
 */
function getAjaxList(){
	
	$.ajax({
		// 서버 접속 URL
		url : '/reply/list/'+1+'/'+$("#bno").val(),
		method  : 'get',
		// 반환 데이터 타입
		dataType : 'json',
		
		// 처리 성공
		success : function(data, textStatus, jqXHR){
			console.log("success!");
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
			
			// 리플테이블 내용 입력
			$(".chat").html(replySrc);
			
			// li태그에 이벤트를 걸어 줍니다.
			$(".chat li[data-rno]").on("click",function(){
				
				// 상세 화면을 보여줍니다
				getAjaxDetail($(this).attr("data-rno"));
				
			});
			
		},
		
		// 처리 실패
		error : function(jqXHR, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);

			
		}
		
	});
	
}



function addAjax(){
	
	// 오브젝트를 만들어 데이터를 담아줍니다.
	var reply = {
            reply: $("#reply").val(),
            replyer: $("#replyer").val(),
            bno: $("#bno").val()
          };
	
	
	console.log(reply);
	console.log(JSON.stringify(reply));
	
	$.ajax({
		url : '/reply/insert',
		method : 'post',
		
		data : JSON.stringify(reply), 
		contentType : 'application/json; charset=UTF-8',

		// 처리 성공
		success : function(data, textStatus){
			console.log("add success!");
			console.log("data", data);
			console.log("textStatus", textStatus);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList();
			
		},
		
		// 처리 실패
		error : function(xhr, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			
		}
	});
}

function deleteAjax(){
	$.ajax({
		url : '/reply/delete/'+$("#rno").val(),
		method : 'post',
		dataType : 'json',
		success : function(data, status){
			console.log(data);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList();
		},
		error : function(xhr, status, error){
			console.log(error)
		}
		
	});
	
}

function updateAjax(){
	var reply = {
			bno : $("#bno").val(),
			rno : $("#rno").val(),
			reply : $("#reply").val(),
			replyer : $("#replyer").val(),
	};
	
	$.ajax({
		url : '/reply/update',
		method : 'post',
		
		data : JSON.stringify(reply), 
		contentType : 'application/json; charset=UTF-8',
		
		success : function(data, status){
			console.log(data);
			
			$("#myModal").modal("hide");
			// 등록후 리스트를 다시 조회
			getAjaxList();
		},
		error : function(xhr, status, error){
			console.log(error);
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
        rno<input type="text" id="rno">	
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                               <h4 class="modal-title" >Reply</h4>
                           </div>
                           <div class="modal-body">
                             <ul class="list-group list-group-flush">
							    <li class="list-group-item">
									<input type="text" class="form-control ml-2" placeholder="replyer" id="replyer">
								</li>
								<li class="list-group-item">
									<textarea class="form-control" id="reply" placeholder="reply" rows="3"></textarea>
							    </li>
							    <li class="list-group-item" id=replydateLi>
									<input type="text" class="form-control ml-2" id="updatedate" >
								</li>
							</ul>
                           </div>
                           <div class="modal-footer">
                           	   <button id='editBtn' type="button" class="btn btn-warning">Modify</button>
        					   <button id='deleteBtn' type="button" class="btn btn-danger">Remove</button>
                               <button type="button" class="btn btn-default" data-dismiss="modal">cancle</button>
                               <button type="button" class="btn btn-primary" id = regBtn>save</button>
                           </div>
                       </div>
                       <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        
        <!-- /.modal -->
                

        
<jsp:include page="/resources/header/bottom.jsp"/>


