<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<h2>test07Proc.do / exam01Result.jsp</h2>
<h2>상품분류 : ${map.dto.section }</h2>
<h2>상품이름 : ${map.dto.name }</h2>
<h2>상품가격 : ${map.dto.price }</h2>
<h2>할인률 : ${map.dto.discount }%</h2>
<h2>할인금액 : ${map.dto.discountedPrice }</h2>
<h2>제조사 : ${map.dto.maker }</h2>