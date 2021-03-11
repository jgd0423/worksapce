<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<form name="writeForm" id="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>성적입력</h2></td>
		</tr>
		<tr>
			<td style="align: center;">학년</td>
			<td>
				<select name="grade" id="grade" style="width: 80px;">
					<option value="">- 선택 -</option>
					<option value="1">1</option>
    			<option value="2">2</option>
    			<option value="3">3</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="align: center;">반</td>
			<td>
				<select name="classes" id="classes" style="width: 80px;">
					<option value="">- 선택 -</option>
					<c:forEach var="classes" items="${classesList }">
						<option value="${classes }">${classes }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td style="align: center;">학생이름</td>
			<td>
				<select name="studentId" id="studentId" style="width: 80px;">
					<option value="">- 선택 -</option>
					<c:forEach var="dto" items="${studentIdNameList }">
						<option value="${dto.id }">${dto.name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td style="align: center;">시험종류</td>
			<td>
				<select name="examId" id="examId" style="width: 80px;">
					<option value="">- 선택 -</option>
					<c:forEach var="dto" items="${examList }">
						<option value="${dto.no }">${dto.name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td style="align: center;">국어</td>
			<td><input type="text" name="korean" id="korean" /></td>
		</tr>
		<tr>
			<td style="align: center;">영어</td>
			<td><input type="text" name="english" id="english"/></td>
		</tr>
		<tr>
			<td style="align: center;">수학</td>
			<td><input type="text" name="math" id="math"/></td>
		</tr>
		<tr>
			<td style="align: center;">과학</td>
			<td><input type="text" name="science" id="science"/></td>
		</tr>
		<tr>
			<td style="align: center;">사회</td>
			<td><input type="text" name="history" id="history"/></td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" onclick="goWrite()" id="btnWrite">등록하기</button>
				<button type="button" onclick="goList()" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

const gradeSelectTag = document.querySelector('#grade');
const classesSelectTag = document.querySelector('#classes');
const studentIdSelectTag = document.querySelector('#studentId');

const grade = '${grade}';
const classes = '${classes}';

if (grade) {
	for (let i = 0; i < gradeSelectTag.length; i++) {
		if (gradeSelectTag[i].value === grade) {
			gradeSelectTag[i].selected = true;
		}
	}
}

if (classes) {
	for (let i = 0; i < classesSelectTag.length; i++) {
		if (classesSelectTag[i].value === classes) {
			classesSelectTag[i].selected = true;
		}
	}
}

gradeSelectTag.addEventListener('change', (e) => {
	if (e.target.value !== '') {
		classesSelectTag.value = '';
		studentIdSelectTag.value = '';
		document.writeForm.method = 'post';
		document.writeForm.action = '${path}/score_servlet/write.do';
		document.writeForm.submit();
	}
});

classesSelectTag.addEventListener('change', (e) => {
	if (e.target.value !== '') {
		studentIdSelectTag.value = '';
		document.writeForm.method = 'post';
		document.writeForm.action = '${path}/score_servlet/write.do';
		document.writeForm.submit();
	}
});

function goList() {
	location.href = '${path}/score_servlet/list.do';
}

function goWrite() {
	document.writeForm.method = 'post';
	document.writeForm.action = '${path}/score_servlet/writeProc.do';
	document.writeForm.submit();
}

</script>