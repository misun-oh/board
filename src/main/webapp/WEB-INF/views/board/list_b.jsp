<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script type="text/javascript">
if('${resMsg}' != ''){
	alert('${resMsg}');	
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
                        <div class="panel-heading">
                            <button type="button" onclick="location.href='/board/register'" class="btn btn-default">등록</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성시간</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach var="vo" items="${list}">
                                    <tr class="odd gradeX">
                                        <td>${vo.bno }</td>
                                        <td><a href="/board/get?bno=${vo.bno}">${vo.title }</a></td>
                                        <td>${vo.writer }</td>
                                        <td class="center">${vo.regdate }</td>
                                    </tr>
                                </c:forEach>
                                    
                                </tbody>
                            </table>
                            ${pageNavi }
                            
                            <c:if test="${pageNavi.prev}">${pageNavi.startPage - 1 } 이전 </c:if>
                            
                            <c:forEach begin="${pageNavi.startPage }" end="${pageNavi.endPage }" var="page">
                            	${page}
                            </c:forEach>
                            
                            <c:if test="${pageNavi.next}"> 다음> </c:if>
                            
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
