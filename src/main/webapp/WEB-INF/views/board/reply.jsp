<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/resources/js/reply.js"></script>
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
	getAjaxList(1);

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

function replyPageing(pageNavi){
	var pageNaviContent = "";
	if(pageNavi.prev){
		pageNaviContent +=
					'<li class="page-item" onclick="replyPage('+(startPage-1)+')">'
					+'	<a class="page-link" href="#">Previous</a>'
					+'</li>';
	}
	var startPage = pageNavi.startPage;
	var endPage = pageNavi.endPage;
	for(startPage;startPage<=endPage;startPage++){
		if(startPage==pageNavi.cri.pageNo){
			pageNaviContent +=
				'<li class="page-item active">'
			    +  '<span class="page-link">'
			    +     startPage
			    +    '<span class="sr-only">(current)</span>'
			    +  '</span>'
		    	+'</li>';	
		}else{
			pageNaviContent +=
				'<li class="page-item" onclick="replyPage('+startPage+')"><a class="page-link" href="#">'+startPage+'</a></li>';
		}
	}
	
	if(pageNavi.next){
		pageNaviContent += 
					'<li class="page-item" onclick="replyPage('+(startPage+1)+')">'
					+'	<a class="page-link" href="#">Next</a>'
					+'</li>';
	}
	
	$(".pagination").html(pageNaviContent);
}

function replyPage(rpPageNo){
	getAjaxList(rpPageNo)
	
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
							
								<div class="panel-footer">
								
									<!-- 페이지 처리 -->
			                        <nav aria-label="pageNavi">
									  <ul class="pagination">
									    									   	
									  </ul>
									</nav>
			                       	<!-- 페이지 처리 -->
								
								</div>
							</div>
						  </div>
						</div>
						<!-- ./ end row -->	
							
                           
                        
                        
                            
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


