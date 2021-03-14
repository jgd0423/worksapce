<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<form name="listForm">
	<table border="1" align="center" width="95%">
		<tr>
			<td colspan="20" align="center">
				<h2>성적리스트</h2>
			</td>
		</tr>
		<tr>
			<td colspan="20" align="center">
				<select name="grade" id="grade">
					<option value="">- 학년 전체 -</option>
					<option value="1">1</option>
    			<option value="2">2</option>
    			<option value="3">3</option>
				</select>
				<select name="classes" id="classes">
					<option value="">- 반 전체 -</option>
					<c:forEach var="classes" items="${classesList }">
						<option value="${classes }">${classes }</option>
					</c:forEach>
				</select>
				<select name="examId" id="examId">
					<option value="">- 시험 전체 -</option>
					<c:forEach var="dto" items="${examList }">
						<option value="${dto.no }">${dto.name }</option>
					</c:forEach>
				</select>
				<input type="text" name="studentName" id="studentName" style="width: 150px;" />
				&nbsp;
				<input type="button" value="검색" onclick="search();" />		
			</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>성적리스트 id</td>
			<td>학생이름</td>
			<td>학년</td>
			<td>반</td>
			<td>시험이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>과학</td>
			<td>사회</td>
			<td>총점</td>
			<td>평균</td>
			<td>등록일</td>
		</tr>
		<c:if test="${list.size() == 0 }">
			<tr>
				<td colspan="20" height="200" align="center">
					등록된 정보가 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list.size() > 0 }">
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${tableRowNum }</td>
					<td>${dto.no }</td>
					<td>${dto.studentName }</td>
					<td>${dto.grade }</td>
					<td>${dto.classes }</td>
					<td>${dto.examName }</td>
					<td>${dto.korean }</td>
					<td>${dto.english }</td>
					<td>${dto.math }</td>
					<td>${dto.science }</td>
					<td>${dto.history }</td>
					<td>${dto.totalScore }</td>
					<td>${dto.averageScore }</td>
					<td>${dto.regiDate }</td>
				</tr>
				<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="20" height="50" align="center">
				<a href="#" onclick="choosePage(1)"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#" onclick="choosePage(${pageNum}, '${grade }', '${classes }', '${examId }', '${studentName }')"><</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum - 1 }, '${grade }', '${classes }', '${examId }', '${studentName }')"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${pageNum == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#" onclick="choosePage(${i }, '${grade }', '${classes }', '${examId }', '${studentName }')">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum + 1 >= maxPagesCount }">
						<a href="#" onclick="choosePage(${maxPagesCount }, '${grade }', '${classes }', '${examId }', '${studentName }')">></a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum + 1 }, '${grade }', '${classes }', '${examId }', '${studentName }')">></a>
					</c:otherwise>
				</c:choose>
				<a href="#" onclick="choosePage(${maxPagesCount }, '${grade }', '${classes }', '${examId }', '${studentName }')">>></a>
			</td>
		</tr>
		<tr>
			<td colspan="20" align="right">
				<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
				<button type="button" onclick="goWrite()">성적등록</button>&nbsp;
			</td>
		</tr>
	</table>
</form>
<br>

<script>

const classesSelectTag = document.querySelector('#classes');
const gradeSelectTag = document.querySelector('#grade');
const examIdSelectTag = document.querySelector('#examId');

const grade = '${grade}';
if (grade) {
	for (let i = 0; i < gradeSelectTag.length; i++) {
		if (gradeSelectTag[i].value === grade) {
			gradeSelectTag[i].selected = true;
		}
	}
}

gradeSelectTag.addEventListener('change', (e) => {
	classesSelectTag.value = '';
	document.listForm.method = 'post';
	document.listForm.action = '${path}/score_servlet/list.do';
	document.listForm.submit();
});

const classes = '${classes}';
if (classes) {
	for (let i = 0; i < classesSelectTag.length; i++) {
		if (classesSelectTag[i].value === classes) {
			classesSelectTag[i].selected = true;
		}
	}
}

classesSelectTag.addEventListener('change', (e) => {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/score_servlet/list.do';
	document.listForm.submit();
});

const examId = '${examId}';
if (examId) {
	for (let i = 0; i < examIdSelectTag.length; i++) {
		if (examIdSelectTag[i].value === examId) {
			examIdSelectTag[i].selected = true;
		}
	}
}

examIdSelectTag.addEventListener('change', (e) => {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/score_servlet/list.do';
	document.listForm.submit();
});

function choosePage(pageNumber) {
	let url = '';
	url += `${path}/score_servlet/list.do?pageNumber=\${pageNumber}`;
	url += "&grade=" + "${grade}";
	url += "&classes=" + "${classes}";
	url += "&examId=" + "${examId}";
	url += "&studentName=" + "${studentName}";
	location.href = url;
}

function search() {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/score_servlet/list.do';
	document.listForm.submit();
}

function chooseAll() {
	location.href = '${path}/score_servlet/list.do';
}

function goView(no) {
	location.href = `${path}/score_servlet/view.do?no=\${no}`;
}

function goWrite() {
	location.href = '${path}/score_servlet/write.do';
}

</script>