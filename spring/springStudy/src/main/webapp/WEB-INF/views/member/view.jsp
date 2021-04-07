<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2" align="center">
			<h2>상세보기</h2>
		</td>
	</tr>
	<tr>
		<td>번호</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>ID : ${dto.id } / 세션ID : ${sessionScope.cookId }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${dto.passwd }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${dto.gender }</td>
	</tr>
	<tr>
		<td>출생년도</td>
		<td>${dto.bornYear }</td>
	</tr>
	<tr>
		<td>가입일시</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>
			<table border="1">
				<tr>
					<td>우편번호</td>
					<td>${dto.postcode }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${dto.address }</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>${dto.detailAddress }</td>
				</tr>
				<tr>
					<td>참고항목</td>
					<td>${dto.extraAddress }</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="50" align="center">
			<button type="button" onclick="chooseProc('list', '1', '')">목록으로</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="chooseProc('modify', '1', ${dto.no })">수정하기</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="chooseProc('delete', '1', ${dto.no })">삭제하기</button>
		</td>
	</tr>
	<tr>
  	<td colspan="2" height="50px">
			<table border="1" width="100%" align="center">
				<tr>
			  	<td width="100px">이전글 : </td>
			  	<td>
			    	<c:if test="${dto.preId == null}">
			     		이전글이 없습니다.
			     	</c:if>
			     	<c:if test="${dto.preId != null}">
			     		<a href="#" onclick="chooseProc('view', '0', '${dto.preNo}');">${dto.preId}</a>
			     	</c:if>
			  	</td>
				</tr>
				<tr>
		    	<td width="100px">다음글 : </td>
		    	<td>
		     		<c:if test="${dto.nxtId == null}">
		     			다음글이 없습니다.
		     		</c:if>
		     		<c:if test="${dto.nxtId != null}">
		     			<a href="#" onclick="chooseProc('view', '0', '${dto.nxtNo}');">${dto.nxtId}</a>
		     		</c:if>
	     		</td>
				</tr>	
			</table>
    </td>
	</tr>		
</table>
