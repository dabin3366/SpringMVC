<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>info.jsp</h1>
	
	${memberVO} <hr>
	${vo } <hr>
	
	아이디 : ${vo.userid } <br>
	비밀번호 : ${vo.userpw } <br>
	이름 : ${vo.username } <br>
	이메일 : ${vo.useremail } <br>
	
	<a href="/member/main">메인</a>
	
	
	
	
	
	
	
	
	
	
</body>
</html>