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
				<c:forEach var="i" begin="1" end="10" step="1">
					<option value="${i }">${i }</option>
				</c:forEach>
			</select>
			<button type="button" onclick="chooseProc('cart_add', '1', '${dto.no}')">장바구니담기</button>
			<button type="button" onclick="chooseProc('mall_list', '1', '')">쇼핑하기</button>
			<button type="button" onclick="chooseProc('cart_list', '1', '')">장바구니</button>
		</td>
	</tr>
</table>