<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

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
	<input type=text name=bno value=${vo.bno }>
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
