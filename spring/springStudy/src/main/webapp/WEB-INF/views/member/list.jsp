<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="7" align="center">
			<h2>회원목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="center">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'id' }">
						<option value="">- 선택 -</option>
						<option value="id" selected>id</option>
						<option value="name">이름</option>
						<option value="gender">성별</option>
						<option value="id_name_gender">id+이름+성별</option>
					</c:when>
					<c:when test="${search_option == 'name' }">
						<option value="">- 선택 -</option>
						<option value="id">id</option>
						<option value="name" selected>이름</option>
						<option value="gender">성별</option>
						<option value="id_name_gender">id+이름+성별</option>
					</c:when>
					<c:when test="${search_option == 'gender' }">
						<option value="">- 선택 -</option>
						<option value="id">id</option>
						<option value="name">이름</option>
						<option value="gender" selected>성별</option>
						<option value="id_name_gender">id+이름+성별</option>
					</c:when>
					<c:when test="${search_option == 'id_name_gender' }">
						<option value="">- 선택 -</option>
						<option value="id">id</option>
						<option value="name">이름</option>
						<option value="gender">성별</option>
						<option value="id_name_gender" selected>id+이름+성별</option>
					</c:when>
					<c:otherwise>
						<option value="">- 선택 -</option>
						<option value="id">id</option>
						<option value="name">이름</option>
						<option value="gender">성별</option>
						<option value="id_name_gender">id+이름+성별</option>
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
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>성별</td>
		<td>출생년도</td>
		<td>가입일시</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="7" height="200" align="center">등록된 회원이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum }</td>
				<td><a href="#" onclick="chooseProc('view', '1', '${dto.no }')">${dto.id }</a></td>
				<td>${dto.passwd }</td>
				<td>${dto.name }</td>
				<td>${dto.gender }</td>
				<td>${dto.bornYear }</td>
				<td>${dto.regiDate }</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" height="50" align="center">
			<a href="#" onclick="choosePage(1)"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="choosePage(${pageNum })"><</a>
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
</table>
<br>
<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
<button type="button" onclick="chooseProc('write', '1', '')">가입하기</button>

<script>

function chooseAll() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	chooseProc('list', '1', '');
}

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	chooseProc('list', '1', '');
}

function choosePage(pageNum) {
	chooseProc('list', pageNum, '');
}

</script>
