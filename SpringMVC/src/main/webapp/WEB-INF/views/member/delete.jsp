<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>delete.jsp</h1>
	
	<form action="/member/delete" method="post">
		<input type="hidden" name="userid" value="${id }">
		비밀번호 : <input type="password" name="userpw">
	
		<input type="submit" value="삭제">
	</form>
	
</body>
</html>