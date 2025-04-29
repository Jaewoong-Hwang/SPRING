<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/file/upload</h1>
	<form action="${pageContext.request.contextPath }/file/upload_dto" method="post" enctype="multipart/form-data">
	<input type = "file" name ="files" multiple/>
	<button>전송</button>
	</form>
	
</body>
</html>