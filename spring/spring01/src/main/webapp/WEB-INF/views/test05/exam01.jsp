<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<h2>test05.do / exam01.jsp</h2>

<form action="test05Proc.do" method="post">
	이름 : <input type="text" name="name" id="name" value=""><br>
	국어 : <input type="text" name="kor" id="kor" value=""><br>
	영어 : <input type="text" name="eng" id="eng" value=""><br>
	수학 : <input type="text" name="mat" id="mat" value=""><br>
	과학 : <input type="text" name="sci" id="sci" value=""><br>
	역사 : <input type="text" name="his" id="his" value=""><br>
	<input type="submit" value="확인">
</form>