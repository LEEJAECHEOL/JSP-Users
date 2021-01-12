<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container mt-5 d-flex flex-wrap">
	<c:choose>
	  	<c:when test="${sessionScope.principal!=null}">
			<c:forEach var="user" items="${users}">
				<div class="card pt-4 m-1 text-center" style="width:300px">
				  <i class="fas fa-user card-img-top" style="font-size:100px;color:#ddd;"></i>
				  <div class="card-body">
				    <h4 class="card-title">${user.username}</h4>
				    <p class="card-text">${user.email}</p>
				    <p class="card-text">${fn:substring(user.createDate,0,10)}</p>
				    <c:if test="${sessionScope.principal.role eq 'ADMIN' || user.id eq sessionScope.principal.id}">
					    <form action="/users/user?cmd=delete" method="post">
					    	<input type="hidden" name="id" value="${user.id}">
					    	<button type="submit" class="btn btn-danger">delete</button>
					    </form>
				    </c:if>
				  </div>
				</div>
			</c:forEach>
	  	</c:when>
	  	<c:otherwise>
	  		<h3 class="text-center">로그인 후 이용해 주세요.</h3>
	  	</c:otherwise>
  	</c:choose>
</div>
</body>
</html>