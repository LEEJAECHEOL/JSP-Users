<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <ul class="navbar-nav">
	    <li class="nav-item active">
	      <a class="nav-link" href="/users">UsersManage</a>
	    </li>
	   	<c:choose>
		  	<c:when test="${sessionScope.principal!=null}">
				<li class="nav-item">
				  <a class="nav-link" href="<%=request.getContextPath() %>/user?cmd=listForm">회원리스트</a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="<%=request.getContextPath() %>/user?cmd=logout">로그아웃</a>
				</li>
		  	</c:when>
		  	<c:otherwise>
			    <li class="nav-item">
			      <a class="nav-link" href="<%=request.getContextPath() %>/user?cmd=loginForm">Lgoin</a>
			    </li>
			    <li class="nav-item">
			      <a class="nav-link" href="<%=request.getContextPath() %>/user?cmd=joinForm">Register</a>
			    </li>
		  	</c:otherwise>
		</c:choose>
	  </ul>

	</nav>