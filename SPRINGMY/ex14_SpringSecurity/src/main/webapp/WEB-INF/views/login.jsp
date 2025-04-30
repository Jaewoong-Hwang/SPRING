<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1>LOGIN</h1>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div>
			<label>USERNAME : </label>
			<input name="username" />
		</div>
		<div>
			<label>PASSWORD : </label>
			<input name="password" type="password" />
		</div>
		<div>
		<input type ="checkbox" name = "remember-me" id="remember-me"/>
		<label for="remember-me">로그인 상태를 유지</label> 
		
		</div>


		<button>로그인</button>
		<%-- <input type="hidden" name="_csrf" value="${_csrf.token}"/>  --%>

	</form>
	${message} ${param.error}




	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
</body>
</html>