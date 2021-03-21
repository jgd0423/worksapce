<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="modifyForm" id="modifyForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>수정하기</h2></td>
		</tr>
		<tr>
			<td width="150">question</td>
			<td><input type="text" name="question" id="question" value="${dto.question }" /></td>
		</tr>
		<tr>
			<td>ans1</td>
			<td><input type="text" name="ans1" id="ans1" value="${dto.ans1 }" /></td>
		</tr>
		<tr>
			<td>ans2</td>
			<td><input type="text" name="ans2" id="ans2" value="${dto.ans2 }" /></td>
		</tr>
		<tr>
			<td>ans3</td>
			<td><input type="text" name="ans3" id="ans3" value="${dto.ans3 }" /></td>
		</tr>
		<tr>
			<td>ans4</td>
			<td><input type="text" name="ans4" id="ans4" value="${dto.ans4 }" /></td>
		</tr>
		<tr>
			<td>status</td>
			<td>
				<c:if test="${dto.status == '1' }">
					<input type="radio" name="statusRadio" id="statusTrue" checked onclick="chooseStatus('1')" /> 진행중
					<input type="radio" name="statusRadio" id="statusFalse" onclick="chooseStatus('0')" /> 종료
				</c:if>
				<c:if test="${dto.status == '0' }">
					<input type="radio" name="statusRadio" id="statusTrue" onclick="chooseStatus('1')" /> 진행중
					<input type="radio" name="statusRadio" id="statusFalse" checked onclick="chooseStatus('0')" /> 종료
				</c:if>
				<input type="hidden" name="status" id="status" value="${dto.status }" />
			</td>
		</tr>
		<tr>
			<td>start_date</td>
			<td>
				<select name="startYear" id="startYear">
					<c:forEach var="i" begin="${yearMonthDayMap['nowYear'] - 1 }" end="${yearMonthDayMap['nowYear'] + 1 }" step="1">
						<c:if test="${startYear == i }">
							<option value="${i }" selected>${i }</option>
						</c:if>
						<c:if test="${startYear != i }">
							<option value="${i }">${i }</option>
						</c:if>
					</c:forEach>
				</select>
				년
				<select name="startMonth" id="startMonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${startMonth == i }">
							<c:if test="${i < 10 }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }" selected>${i }</option>
							</c:if>
						</c:if>
						<c:if test="${startMonth != i }">
							<c:if test="${i < 10 }">
								<option value="0${i }">0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>
				월
				<select name="startDay" id="startDay">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${startDay == i }">
							<c:if test="${i < 10 }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }" selected>${i }</option>
							</c:if>
						</c:if>
						<c:if test="${startDay != i }">
							<c:if test="${i < 10 }">
								<option value="0${i }">0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>
				일		
			</td>
		</tr>
		<tr>
			<td>last_date</td>
			<td>
				<select name="lastYear" id="lastYear">
					<c:forEach var="i" begin="${yearMonthDayMap['nowYear'] - 1 }" end="${yearMonthDayMap['nowYear'] + 1 }" step="1">
						<c:if test="${lastYear == i }">
							<option value="${i }" selected>${i }</option>
						</c:if>
						<c:if test="${lastYear != i }">
							<option value="${i }">${i }</option>
						</c:if>
					</c:forEach>
				</select>
				년
				<select name="lastMonth" id="lastMonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${lastMonth == i }">
							<c:if test="${i < 10 }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }" selected>${i }</option>
							</c:if>
						</c:if>
						<c:if test="${lastMonth != i }">
							<c:if test="${i < 10 }">
								<option value="0${i }">0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>
				월
				<select name="lastDay" id="lastDay">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${lastDay == i }">
							<c:if test="${i < 10 }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }" selected>${i }</option>
							</c:if>
						</c:if>
						<c:if test="${lastDay != i }">
							<c:if test="${i < 10 }">
								<option value="0${i }">0${i }</option>
							</c:if>
							<c:if test="${i >= 10 }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>
				일		
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" id="btnModify" onclick="chooseProc('modifyProc', '1', '0')">수정하기</button>
				<button type="button" id="btnList" onclick="chooseProc('list', '1', '')">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

function chooseStatus(status) {
	$("#status").val(status);
}

</script>