<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boss_2</title>
</head>
<body>

<form name="vote_form" method="post" action="boss_2Proc.jsp">
	<table border="1">
		<tr>
			<td>1반반장</td>
			<td>
				<input type="radio" name=boss_1 value="홍길동1">홍길동1
				<input type="radio" name=boss_1 value="김철수1">김철수1
				<input type="radio" name=boss_1 value="이영희1">이영희1
				<input type="radio" name=boss_1 value="강감찬1">강감찬1
			</td>
		</tr>
		<tr>
			<td>2반반장</td>
			<td>
				<input type="radio" name=boss_2 value="홍길동2">홍길동2
				<input type="radio" name=boss_2 value="김철수2">김철수2
				<input type="radio" name=boss_2 value="이영희2">이영희2
				<input type="radio" name=boss_2 value="강감찬2">강감찬2
			</td>
		</tr>
		<tr>
			<td>3반반장</td>
			<td>
				<input type="radio" name=boss_3 value="홍길동3">홍길동3
				<input type="radio" name=boss_3 value="김철수3">김철수3
				<input type="radio" name=boss_3 value="이영희3">이영희3
				<input type="radio" name=boss_3 value="강감찬3">강감찬3
			</td>
		</tr>
		<tr>
			<td>4반반장</td>
			<td>
				<input type="radio" name=boss_4 value="홍길동4">홍길동4
				<input type="radio" name=boss_4 value="김철수4">김철수4
				<input type="radio" name=boss_4 value="이영희4">이영희4
				<input type="radio" name=boss_4 value="강감찬4">강감찬4
			</td>
		</tr>
		<tr>
			<td>5반반장</td>
			<td>
				<input type="radio" name=boss_5 value="홍길동5">홍길동5
				<input type="radio" name=boss_5 value="김철수5">김철수5
				<input type="radio" name=boss_5 value="이영희5">이영희5
				<input type="radio" name=boss_5 value="강감찬5">강감찬5
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form>

</body>
</html>