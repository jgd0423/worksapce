<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="vote_form" method="post" action="bossProc.jsp">
	<table border="1">
		<tr>
			<td>반장</td>
			<td>
				<input type="radio" name=boss_1 value="홍길동">홍길동
				<input type="radio" name=boss_1 value="김철수">김철수
				<input type="radio" name=boss_1 value="이영희">이영희
			</td>
		</tr>
		<tr>
			<td>부반장</td>
			<td>
				<input type="radio" name=boss_2 value="이승엽">이승엽
				<input type="radio" name=boss_2 value="박찬호">박찬호
				<input type="radio" name=boss_2 value="김병현">김병현
			</td>
		</tr>
		<tr>
			<td>회장</td>
			<td>
				<input type="radio" name=boss_3 value="이문세">이문세
				<input type="radio" name=boss_3 value="안성기">안성기
				<input type="radio" name=boss_3 value="이연복">이연복
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form>

</body>
</html>