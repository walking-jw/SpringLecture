<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify & Delete</title>
</head>
<body>

	<table border="1">
		<form action="modify_address" method="post">
				<input type="hidden" name="seqno" value="${content.seqno }">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="50" value="${content.name }"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="telno" size="50" value="${content.telno }"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" size="50" value="${content.address }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="50" value="${content.email }"></td>
			</tr>
			<tr>
				<td>관계</td>
				<td><input type="text" name="relation" size="50" value="${content.relation }"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정">&nbsp;&nbsp;<a href="delete_address?seqno=${content.seqno }">삭제</a></td>
			</tr>
		</form>
	</table>


</body>
</html>