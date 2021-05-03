<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/resources/header/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

$(document).ready(function(){

	//파일 리스트 보여주기
	getFileList(document.editForm.attachNo.value);
});
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
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        <form method="post" action="/board/edit" name=editForm>

						<input type=text name=pageNo value=${criteria.pageNo }>
						<input type=text name=type value=${criteria.type }>
						<input type=text name=keyword value=${criteria.keyword }>
						<input type=text name=bno value=${vo.bno }>
						<input type=text name=attachNo id="attachNo" value=${vo.attachNo }>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" value="${vo.title}" name=title>
                                
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="3" name=content>${vo.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name=writer value="${vo.writer}">
                                
                            </div>
                            
                            <button type="submit" >수정</button>
						    </form> 
						    <div>
						    <jsp:include page="attach.jsp"></jsp:include>
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
     
<jsp:include page="/resources/header/bottom.jsp"/>
