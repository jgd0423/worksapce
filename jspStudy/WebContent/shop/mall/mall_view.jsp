<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2">
			<h1>Shopping Mall</h1>
		</td>
	</tr>
	<tr>
		<td width="150">상품코드 : </td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>상품사진 : </td>
		<td>
			<c:if test="${dto.product_img == '-,-,-' }">
				이미지X
			</c:if>
			<c:if test="${dto.product_img != '-,-,-' }">
				<c:set var="temp1" value="${fn:split(dto.product_img, ',')[0] }"></c:set>
				<c:set var="temp2" value="${fn:split(temp1, '|')[0] }"></c:set>
				<c:set var="temp3" value="${fn:split(temp1, '|')[1] }"></c:set>
				<img 
					src="${path }/attach/product_img/${temp3}" 
					alt="${dto.name }" 
					title="${dto.name }" 
					style="width: 100px; height: 100px;"
				/>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>상품명 : </td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>가격 : </td>
		<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.price }"/></td>
	</tr>
	<tr>
		<td style="align: center;">상품설명 : </td>
		<td id="content">${dto.description}</td>
	</tr>
	<tr>
		<td>파일명 : </td>
		<td>${fn:split(dto.product_img, '|')[0] }</td>
	</tr>
	<tr>
		<td>등록일 : </td>
		<td>${dto.regi_date }</td>
	</tr>
	<tr>
		<td colspan="2">
			<select id="amount" name="amount">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		    <option value="6">6</option>
		    <option value="7">7</option>
		    <option value="8">8</option>
		    <option value="9">9</option>
		    <option value="10">10</option>
			</select>
			<button type="button" onclick="chooseProc('cart_add', '1', '')">장바구니담기</button>
			<button type="button" onclick="">바로구매</button>
			<button type="button" onclick="chooseProc('mall_list', '1', '')">쇼핑하기</button>
			<button type="button" onclick="chooseProc('cart_list', '1', '')">장바구니</button>
		</td>
	</tr>
</table>