<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<h2>test06Proc.do / exam01Result.jsp</h2>
<h2>상품분류 : ${dto.section }</h2>
<h2>상품이름 : ${dto.name }</h2>
<h2>상품가격 : ${dto.price }</h2>
<h2>할인률 : ${dto.discount }%</h2>
<h2>할인금액 : ${dto.discountedPrice }</h2>
<h2>제조사 : ${dto.maker }</h2>