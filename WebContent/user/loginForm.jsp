<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container d-flex justify-content-center mt-5">
	<div class="card align-middle" style="width:20rem; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">로그인</h2>
		</div>
		<div class="card-body">
			<form class="form-signin" method="POST" action="/users/user?cmd=login">
				<input type="text" name="username" class="form-control" placeholder="Input Username" required autofocus><BR>
				<input type="password" name="password" class="form-control" placeholder="Input Password" required><br>
				
				<button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit">LOGIN</button>
			</form>
		</div>
	</div>
</div>


</body>
</html>