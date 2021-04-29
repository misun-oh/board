<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="/resources/js/reply.js"></script>
<script type="text/javascript">

if('${resMsg}' != ''){
	alert('${resMsg}');	
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
                            
                            <div class='form-group'>
                            <button type="button" class="btn btn-default" onClick="move('/board/edit')">수정</button>
							<button class="btn btn-default" onClick="move('/board/delete')">삭제</button>
							<button class="btn btn-default" onClick="move('/board/list')">목록</button>
							</div>
							
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
        
        								
        rno<input type="text" id="rno">
        replyPageNo<input type="text" id="replyPageNo" value=1>		
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
