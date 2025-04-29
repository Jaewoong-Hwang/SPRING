<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/file/list</h1>
	<div>${uploadPath}</div>
	<!--실무에서는 최상위 결로를 절대 제공하면 안됨!! -->
	<c:forEach items='${uploadPath.listFiles()}' var='subdir'>
		<hr>
		FOLDER : ${subdir.getPath()}  <!--폴더  -->
		<c:forEach items='${subdir.listFiles()}' var='file'>
			<br />
		- FILE : 
		<a href="javascript:void(0)" class="item"
				data-dir="${subdir.getPath()}" data-filename="${file.getName()}">
				${file.getPath()} <!-- 폴더 안에 파일 -->
			</a>

		</c:forEach>
		<hr>

	</c:forEach>

	<script>
	const projectPath = '${pageContext.request.contextPath}';
	
	const item_els = document.querySelectorAll('.item');
	item_els.forEach((item)=>{
		
		item.addEventListener('click',function(){
			
			/* 인코딩 처리 */
			const filepath=  encodeURIComponent(item.getAttribute('data-dir')+"\\"+item.getAttribute('data-filename'))
			alert(filepath);
			location.href=projectPath+"/file/download?path="+filepath;
		})
		
	})

</script>

</body>
</html>