<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="modifyForm">
	<input type="hidden" name="no" id="no" value="${dto.no }" />
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>게시글수정</h2>
			</td>
		</tr>
		<tr>
			<td style="align: center;">작성자</td>
			<td><input type="text" name="writer" id="writer" value="${dto.writer }" /></td>
		</tr>
		<tr>
			<td style="align: center;">이메일</td>
			<td><input type="text" name="email" id="email" value="${dto.email }" /></td>
		</tr>
		<tr>
			<td style="align: center;">비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" /></td>
		</tr>
		<tr>
			<td style="align: center;">제목</td>
			<td><input type="text" name="subject" id="subject" value="${dto.subject }" /></td>
		</tr>
		<tr>
			<td style="align: center;">내용</td>
			<td>
				<textarea 
					name="content" 
					id="content" 
					style="width: 300px; height: 100px;" 
					wrap="hard"
				>${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td style="align: center;">공지글</td>
			<td>
				<input 
					type="text" 
					name="noticeGubun" 
					id="noticeGubun" 
					value="${dto.noticeNo > 0 ? 'T' : 'F' }" 
				/>
				<input 
					type="checkbox" 
					name="noticeGubunCheckBox" 
					id="noticeGubunCheckBox" 
					value="T" 
					onclick="clickChk('noticeGubun')"
					${dto.noticeNo > 0 ? 'checked' : '' }
				/>
				공지글 체크
			</td>
		</tr>
		<tr>
			<td style="align: center;">비밀글</td>
			<td>
				<input 
					type="text" 
					name="secretGubun" 
					id="secretGubun" 
					value="${dto.secretGubun}" 
				/>
				<input 
					type="checkbox" 
					name="secretGubunCheckBox" 
					id="secretGubunCheckBox" 
					value="T" 
					onclick="clickChk('secretGubun')" 
					${dto.secretGubun == 'T' ? 'checked' : '' }
				/>	
				비밀글 체크
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" id="btnModify">수정하기</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

function clickChk(gubun) {
	if ($(`input:checkbox[name=\${gubun}CheckBox]`).is(":checked")) {
		$(`#\${gubun}`).val("T");
	} else {
		$(`#\${gubun}`).val("");
	}
}

$(document).ready(() => {
	$("#subject").select();
	$("#subject").focus();
	
	$("#btnModify").click(() => {
		goModify();
	});
	
	$("#btnList").click(() => {
		goList();
	});
});

function goModify() {
	document.modifyForm.method = 'post';
	document.modifyForm.action = '${path}/board2_servlet/modifyProc.do';
	document.modifyForm.submit();
}

function goList() {
	location.href = '${path}/board2_servlet/list.do';
}

</script>