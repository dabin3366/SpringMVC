<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>list.jsp</h1>
	
	<%-- ${requestScope } --%>
	<%-- ${memberList } --%>
	<%-- ${list } (x) --%>
	${memberVOList }
	
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>회원가입일</td>
		</tr>
		
		<c:forEach var="vo" items="${memberVOList }">
			<tr>
				<td>${vo.userid }</td>
				<td>${vo.userpw }</td>
				<td>${vo.username }</td>
				<td>${vo.useremail }</td>
				<td>${vo.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	
	
	
	
	
	
	
	
	
</body>
</html>