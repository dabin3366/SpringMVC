<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>insertForm.jsp</h1>
	
	
	<fieldset>
		<!-- <form action="./insertPro" method="post"> -->
		<form action="/member/insert" method="post">
			아이디 : <input type="text" name="userid"> <br> 
			비밀번호 : <input type="password" name="userpw"> <br> 
			이름 : <input type="text" name="username"> <br> 
			이메일 : <input type="text" name="useremail"> <br>
			
			<input type="submit" value="회원가입"> 
		</form>
	</fieldset>
	
</body>
</html>