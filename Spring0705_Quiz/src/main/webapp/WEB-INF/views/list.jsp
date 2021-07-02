<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC 게시판</title>
</head>
<body>


	<table border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>삭제</td>
		</tr>

		<c:forEach items="${list }" var = "dto">
			<tr>
				<td>${dto.bId }</td>
				<td>${dto.bName }</td>
				<td>${dto.bTitle }</td>
				<td>${dto.bDate }</td>
				<td><a href = "delete?bId=${dto.bId }">X</a></td>
			</tr>
		</c:forEach>
		
		<tr>
		<td colspan="5"><a href="write_view">글작성</a></td>
		</tr>
		
	</table>
</body>
</html>