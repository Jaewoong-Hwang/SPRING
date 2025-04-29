<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/file/list</h1>
	<div>${uploadPath}</div>
	<c:forEach items='${uploadPath.listFiles()}' var='subdir'>
	
	
	
	</c:forEach>
	
</body>
</html>