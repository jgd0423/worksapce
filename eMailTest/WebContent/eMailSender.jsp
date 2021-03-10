<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<form name="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>단체 메일 발송</h2>
			</td>
		</tr>
		<tr>
			<td style="align: center;">제목</td>
			<td><input type="text" name="subject" id="subject" /></td>
		</tr>
		<tr>
			<td style="align: center;">내용</td>
			<td>
				<textarea 
					name="content" 
					id="content" 
					style="width: 300px; height: 100px;" 
					wrap="hard"
				></textarea>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" id="btnSave" onclick="sendEmail()">메일보내기</button>
			</td>
		</tr>
	</table>
</form>

<script>
	function sendEmail() {
		document.writeForm.method = 'post';
		document.writeForm.action = '${path}/email_servlet/write.do';
		document.writeForm.submit();
	}
</script>