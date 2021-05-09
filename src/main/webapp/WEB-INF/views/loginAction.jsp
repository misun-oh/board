<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<form method="get" action="/logout">

	<div>
		<label for="">
			${loginResult }
		</label>
	</div>
    
	<c:if test="${loginResult != null }">
    	<button type="submit">로그아웃</button>
    </c:if>
    
    
</form>
        

        
        