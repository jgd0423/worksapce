<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<table border="1" align="center" width="95%">
	<tr>
		<td colspan="10" align="center">
			<h2>장바구니 목록</h2>
		</td>
	</tr>
	<tr>
		<td><input type="checkbox"></td>
		<td>상품사진</td>
		<td>상품명</td>
		<td>가격</td>
		<td>구매수량</td>
		<td>금액</td>
		<td>등록일</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="10" height="200" align="center">
				등록된 상품이 없습니다.
			</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:set var="totalPrice" value="0"></c:set>
		<c:forEach var="dto" items="${list }">
			<c:set var="totalPrice" value="${totalPrice + dto.buy_money }"/>
			<tr>
				<td><input type="checkbox"> ${tableRowNum }</td>
				<td>
					<c:if test="${dto.product_img == '-,-,-' }">
						<a href="#" onclick="chooseProc('view', '', '${dto.no }')">이미지X</a>
					</c:if>
					<c:if test="${dto.product_img != '-,-,-' }">
						<c:set var="temp1" value="${fn:split(dto.product_img, ',')[0] }"></c:set>
						<c:set var="temp2" value="${fn:split(temp1, '|')[0] }"></c:set>
						<c:set var="temp3" value="${fn:split(temp1, '|')[1] }"></c:set>
						<a href="#" onclick="chooseProc('view', '', '${dto.no}')">
							<img 
								src="${path }/attach/product_img/${temp3}" 
								alt="${dto.product_name }" 
								title="${dto.product_name }" 
								style="width: 50px; height: 50px;"
							/>
						</a>
					</c:if>
				</td>
				<td>
					<a href="#" onclick="chooseProc('view', '', '${dto.no }')">${dto.product_name }</a>
				</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.product_price }"/></td>
				<td>${dto.amount }</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.buy_money }"/></td>
				<td>${dto.regi_date }</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="10">합계금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrice }"/>원</td>
	</tr>
	<tr>
		<td colspan="10" height="50" align="center">
			<a href="#" onclick="choosePage(1)"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="choosePage(${pageNum})"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePage(${pageNum - 1 })"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${pageNum == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${i })">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="choosePage(${maxPagesCount })">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePage(${pageNum + 1 })">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="choosePage(${maxPagesCount })">>></a>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="right">
			<button type="button" onclick="">장바구니 비우기</button>&nbsp;
			<button type="button" onclick="">쇼핑하기</button>&nbsp;
			<button type="button" onclick="">주문하기</button>&nbsp;
		</td>
	</tr>
</table>
<br>

<script>



</script>