<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/resources/header.jsp"/>

<!DOCTYPE html>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일시</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="vo" items="${list}">
                                    <tr class="odd gradeX">
                                        <td>${vo.bno }</td>
                                        <td>${vo.title }</td>
                                        <td>${vo.writer }</td>
                                        <td class="center">${vo.regdate }</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
<h1>게시판</h1>

<table border = 1>
	<c:forEach items="${list }" var="list">
	<tr>
		<td>${list.bno }</td>
		<td>
		<a href="/board/get?bno=${list.bno}"><c:out value="${list.title }"/></a>
		</td>
		<td><c:out value="${list.writer }"/></td>
		<td><c:out value="${list.regdate }"/></td>
	</tr>
	</c:forEach>
</table>















<jsp:include page="/resources/bottom.jsp"/>