<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<table border="1" align="center">
	<tr>
		<td colspan="10" align="center" width="80%">
			<h2>Shopping Mall</h2>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="center">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'name' }">
						<option value="">- 선택 -</option>
						<option value="name" selected>상품명</option>
						<option value="description">상품설명</option>
						<option value="name_description">상품명+상품설명</option>
					</c:when>
					<c:when test="${search_option == 'description' }">
						<option value="">- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description" selected>상품설명</option>
						<option value="name_description">상품명+상품설명</option>
					</c:when>
					<c:when test="${search_option == 'name_description' }">
						<option value="">- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description">상품설명</option>
						<option value="name_description" selected>상품명+상품설명</option>
					</c:when>
					<c:otherwise>
						<option value="">- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description">상품설명</option>
						<option value="name_description">상품명+상품설명</option>
					</c:otherwise>
				</c:choose>
			</select>
			
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;" />
			&nbsp;
			<input type="button" value="검색" onclick="search();" />		
		</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="10" height="200" align="center">
				등록된 상품이 없습니다.
			</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="i" begin="0" end="${loopNumforI >= 0 ? loopNumforI : 0}" step="1" >
			<tr>
				<c:forEach var="j" begin="${i * oneLineSize}" end="${(i * oneLineSize) + oneLineSize - 1 }" step="1" >
					<c:set var="dto" value="${list[j] }"></c:set>
					<c:choose>
						<c:when test="${j >= productListSize }">
							<td align="center" style="width:150px; height:200px;">없음</td>
						</c:when>
						<c:otherwise>
							<td>
								<table align="center" border="1">
									<tr>
										<c:if test="${dto.product_img == '-,-,-' }">
											<td align="center" style="width:150px; height:200px;">
												<a href="#" onclick="chooseProc('mall_view', '', '${dto.no }')">이미지X</a>
											</td>
										</c:if>
										<c:if test="${dto.product_img != '-,-,-' }">
											<c:set var="temp1" value="${fn:split(dto.product_img, ',')[0] }"></c:set>
											<c:set var="temp2" value="${fn:split(temp1, '|')[0] }"></c:set>
											<c:set var="temp3" value="${fn:split(temp1, '|')[1] }"></c:set>
											<td align="center" style="width:150px; height:200px;">
												<a href="#" onclick="chooseProc('mall_view', '', '${dto.no }')">
													<img 
														src="${path }/attach/product_img/${temp3}" 
														style="width:100px; height:100px;"
														alt="${dto.name }" 
														title="${dto.name }" 
													/>
												</a>
											</td>
										</c:if>
									</tr>
									<tr>
										<td>${dto.name }</td>
									</tr>
									<tr>
										<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.price }"/></td>
									</tr>
								</table>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</c:if>
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
			<button type="button" onclick="chooseAll()">상품목록</button>&nbsp;
			<button type="button" onclick="chooseProc('cart_list', '1', '')">장바구니</button>&nbsp;
		</td>
	</tr>
</table>

<script>

function search() {
	chooseProc('mall_search', '1', '');
}

function choosePage(pageNum) {
	chooseProc('mall_list', pageNum, '');
}

function chooseAll() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	chooseProc('mall_list', '1', '');
}

</script>