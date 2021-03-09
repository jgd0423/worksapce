<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp" %>
     
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2">
			<h2>게시글삭제</h2>			
		</td>
	</tr>
	<tr>
		<td style="align: center;">작성자</td>
		<td>
	     	${dto.writer }
		</td>
	</tr>
	<tr>
		<td style="align: center;">이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td style="align: center;">제목</td>
		<td>${dto.subject }</td>
	</tr>
	<tr>
		<td style="align: center;">내용</td>
		<td>${dto.content }</td>
	</tr>
	<tr>
		<td style="align: center;">공지글</td>
		<td>
			<c:if test="${dto.noticeNo > 0}">
				공지글
			</c:if>
			<c:if test="${dto.noticeNo == 0}">
				&nbsp;
			</c:if>
		</td>
	</tr>
	<tr>
		<td style="align: center;">비밀글</td>
		<td>
			<c:if test="${dto.noticeNo > 0}">
				비밀글
			</c:if>
			<c:if test="${dto.noticeNo == 0}">
				&nbsp;
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px;">
			<button type="button" id="btnSakje">삭제하기</button>
			<button type="button" id="btnList">목록으로</button>
		</td>
	</tr>
</table>

<script>
	$(document).ready(function(){
		$("#btnSakje").click(function(){
			if (confirm('삭제하시겠습니까?')) {
				suntaek_proc('sakjeProc', '1', ${dto.no });
			}
		});
    	$("#btnList").click(function(){
    		suntaek_proc('list', '1', '');
		});
	});
</script>