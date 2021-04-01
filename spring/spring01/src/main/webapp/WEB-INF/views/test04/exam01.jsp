<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<h2>test04.do / exam01.jsp</h2>

<form action="test04Proc.do" method="post">
	 이름 : <input type="text" name="name" id="name" value=""><br>
	 성별 : <input type="radio" name="gender" id="gender" value="남자"> 남자 &nbsp;
	 <input type="radio" name="gender" id="gender" value="여자"> 여자<br>
	 주민번호 : <input type="text" name="jumin" id="jumin" value=""><br>
	 나이 : <input type="text" name="age" id="age" value=""><br>
	 <input type="submit" value="확인">
</form>