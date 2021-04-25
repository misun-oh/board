<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script type="text/javascript">
if('${resMsg}' != ''){
	alert('${resMsg}');	
}

function page(pageNo){
	document.listForm.action.value="/board/list";
	document.listForm.pageNo.value=pageNo;
	document.listForm.submit();
	//location.href="/board/list?pageNo="+pageNo;
}

function detail(bno){
	document.listForm.bno.value=bno;
	document.listForm.action="/board/get";
	document.listForm.submit();
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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="">
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
                                        <td><a href="#" onclick="detail('${vo.bno }')">${vo.title }
                                        <c:if test="${vo.replycnt>0}">
                                        [${vo.replycnt}]
                                        </c:if>
                                        </a></td>
                                        <td>${vo.writer }</td>
                                        <td class="center">${vo.regdate }</td>
                                    </tr>
                                </c:forEach>
                                
                                <c:if test="${list.size()== 0}">
	                                <tr class="odd gradeX">
	                                	<td colspan="4" align="center">게시글이 존재 하지 않습니다.</td>
	                                </tr>
                                </c:if> 
                                    
                                </tbody>
                                
                               
                            </table>
                            
                        <!-- 검색 -->
                        <form method=get action=/board/list name=listForm>
                        <div  class="form-inline">
                        <input type=text name=bno >
                        <input type=text name=pageNo value=${pageNavi.cri.pageNo }>
	                        <div class="form-group">
	                              <select class="form-control" name=type>
	                                  <option value=title <c:if test = "${pageNavi.cri.type == 'title' }">selected</c:if>>제목</option>
	                                  <option value=content <c:if test = "${pageNavi.cri.type == 'content' }">selected</c:if>>내용</option>
	                                  <option value=writer <c:if test = "${pageNavi.cri.type == 'writer' }">selected</c:if>>작성자</option>
	                              </select>
	                          </div>
	                        <div class="form-group">
		                       <input class="form-control" name=keyword value="${pageNavi.cri.keyword}">
		                   </div>
	                       <button type="submit" class="btn btn-default">조회</button>
   					   </div>
   					   </form>
                        
                        <!-- 검색 -->
                        
                        
                        
                        <!-- 페이지 처리 -->
                        <nav aria-label="pageNavi">
						  <ul class="pagination">
						    
						    <c:if test="${pageNavi.prev}">
						    	<li class="page-item" onclick="page(${pageNavi.startPage -1})">
							      <span class="page-link">Previous</span>
						    	</li>
						    </c:if>
						    
						    
						    <c:forEach begin="${pageNavi.startPage }" end="${pageNavi.endPage }" var="page">
                            	<c:choose>
                            		<c:when test="${ pageNavi.cri.pageNo eq page }">
	                            		<li class="page-item active">
									      <span class="page-link">
									        ${page }
									        <span class="sr-only">(current)</span>
									      </span>
									    </li>
                            		</c:when>
                            		
                            		<c:otherwise>
                            			<li class="page-item" onclick="page(${page})"><a class="page-link" href="#">${page }</a></li>
                            		</c:otherwise>
                            	</c:choose>
                            	
                            </c:forEach>
						    
						        
						    <c:if test="${pageNavi.next}">
						    	<li class="page-item" onclick="page(${pageNavi.endPage +1})">
						    		<a class="page-link" href="#">Next</a>
						    	</li>
						   	</c:if>
						   	
						  </ul>
						</nav>
                       	<!-- 페이지 처리 -->
                       	
                       
                   
                        
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
        
<jsp:include page="/resources/header/bottom.jsp"/>
