<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container mt-5 d-flex flex-column justify-content-center align-items-center">
    <div class="page-header">
        <div class="col-md-6 col-md-offset-3">
        <h3>Join</h3>
        </div>
    </div>
    <div class="col-sm-10 col-md-offset-3">
        <form method="post" action="/users/user?cmd=join" onSubmit="return valid();">
            <div class="form-group d-flex flex-row-reverse">
                <button type="button" id="checkUsername" class="btn btn-outline-info">중복확인</button>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="Input Username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Input Password">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Input Eamil">
            </div>

            <div class="form-group text-center">
                <button type="submit" id="join-submit" class="btn btn-primary">
                    회원가입<i class="fa fa-check spaceLeft"></i>
                </button>
            </div>
        </form>
    </div>
    <script>
    	let isUsername = false;
		document.querySelector('#checkUsername').addEventListener('click', function(){
			let _username = document.querySelector('#username');
			let _usernameValue = _username.value;
			if(_usernameValue === ''){
				alert("Input Username!!"); return;
			}
			let _data = {username : _usernameValue};
			$.ajax({
				type : "POST",
				url : "/users/user?cmd=usernameCheck",
				data : JSON.stringify(_data),
				contentType : "application/json;charset=utf-8",
				dataType : "json"
			})
			.done(function (result){
				if(result.statusCode == 1){
					alert("사용할 수 없는 username입니다.");
					isUsername = false;
					_username.readOnly = isUsername;
				}else{
					alert("사용할 수 있는 username입니다.");
					isUsername = true;
					_username.readOnly = isUsername;
				}
			});
		});
		function valid(){
			if(isUsername === false){
				alert("유저네임 중복체크를 해주세요!");
			}
			return isUsername;
		}
    </script>

</div>
</body>
</html>