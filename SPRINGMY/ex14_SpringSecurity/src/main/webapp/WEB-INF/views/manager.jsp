<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<h1>MANAGER</h1>
	
	<p>PRINCIPAL : <sec:authentication property="principal"/></p>
	<p>USERDTO:    <sec:authentication property="principal.userDto"/> </p>
	<p>principal로 꺼낸 ID : <sec:authentication property="principal.username"/></p><hr/>
	<p>사용자의 이름 : <sec:authentication property="principal.userDto.username"/></p><hr/>
	<p>사용자의 아이디 : <sec:authentication property="principal.userDto.username"/></p><hr/>
	<p>사용자의 권한 목록 : <sec:authentication property="principal.userDto.role"/></p><hr/>
	<sec:authorize access="isAuthenticated()">
		<p>비로그인 사용자입니다.</p>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<P>비로그인 사용자입니다. 로그인 해주세요.</P>
	</sec:authorize>
	<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
	
</body>
</html>