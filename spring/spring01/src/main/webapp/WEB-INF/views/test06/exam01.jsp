<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<h2>test06.do / exam01.jsp</h2>

<form action="test06Proc.do" method="post">
	상품분류: <input type="text" name="section" id="section" value=""><br>
	상품이름 : <input type="text" name="name" id="name" value=""><br>
	상품가격 : <input type="text" name="price" id="price" value=""><br>
	할인률 : <input type="text" name="discount" id="discount" value=""><br>
	제조사 : <input type="text" name="maker" id="maker" value=""><br>
	<input type="submit" value="확인">
</form>