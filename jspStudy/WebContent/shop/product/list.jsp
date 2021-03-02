<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<table border="1" align="center" width="95%">
	<tr>
		<td colspan="10" align="center">
			<h2>상품목록</h2>
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
	<tr>
		<td>순번</td>
		<td>상품사진</td>
		<td>상품명</td>
		<td>가격</td>
		<td>파일</td>
		<td>장바구니수</td>
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
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum }</td>
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
								alt="${dto.name }" 
								title="${dto.name }" 
								style="width: 50px; height: 50px;"
							/>
						</a>
					</c:if>
				</td>
				<td>
					<a href="#" onclick="chooseProc('view', '', '${dto.no }')">${dto.name }</a>
				</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.price }"/></td>
				<td>${dto.product_img }</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3" value="0"/></td>
				<td>${dto.regi_date }</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
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
			<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
			<button type="button" onclick="goPage('write', '', '')">상품등록</button>&nbsp;
		</td>
	</tr>
</table>
<br>

<script>

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	chooseProc('list', '1', '');
}

function chooseAll() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	chooseProc('list', '1', '');
}

function choosePage(pageNum) {
	chooseProc('list', pageNum, '');
}

</script>