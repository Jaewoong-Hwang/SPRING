<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/join" method="post">
		<div>
			<h1>회원가입</h1>
		</div>
		<div>
			<label>userid : </label><span style="color:red;font-size:.7rem;">${userid}</span><br>
			<input name="userid" />
		</div>
		<div>
			<label>password : </label><span style="color:red;font-size:.7rem;">${password}</span><br>
			<input name="password" />
		</div>
		<div>
			<label>rePassword : </label><span style="color:red;font-size:.7rem;">${rePassword}</span><br>
			<input name="rePassword" />
		</div>
		<div>
			<label>username : </label><span style="color:red;font-size:.7rem;">${username}</span><br>
			<input name="username" />
		</div>
		<div>
			<label>phone : </label><span style="color:red;font-size:.7rem;">${phone}</span><br>
			<input name="phone" placeholder="ex)010-0000-0000" />
		</div>
		<div>
			<label>zipcode : </label><span style="color:red;font-size:.7rem;">${zipcode}</span><br>
			<input name="zipcode" />
		</div>
		<div>
			<label>addr1 : </label><span style="color:red;font-size:.7rem;">${addr1}</span><br>
			<input name="addr1" />
		</div>
		<div>
			<label>addr2 : </label><span style="color:red;font-size:.7rem;">${addr2}</span><br>
			<input name="addr2" />
		</div>
		<div>
			<label>birthday : </label><span style="color:red;font-size:.7rem;">${birthday}</span><br>
			<input type="text" name="birthday" placeHolder="ex)2025~01~01"/>
		</div>
		<div>
			<input type="submit" value="회원가입" />
		</div>

	
	</form>
</body>
</html>