<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table align="center" width="95%">
	<tr>
		<td style="padding : 0 0 20 0;">
			이름 : <input type="text" name="comment_writer" id="comment_writer" size="10" value="${cookName }" />
			비밀번호 : <input type="text" name="comment_passwd" id="comment_passwd" size="10" /><br>
			댓글 : <input type="text" name="comment_content" id="comment_content" size="40" />
			<button type="button" id="btnCommentSave">확인</button>
		</td>
	</tr>
</table>

<c:if test="${list.size() > 0 }">
	<table align="center" width="95%">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td style="padding: 0 0 10 10;">
					<table align="center" style="width: 100%;">
						<tr>
							<td>
								${dto.writer } 
								&nbsp;
								(${dto.regiDate }) 
								<button 
									class="btnCommentDelete" 
									value="${dto.comment_no }" 
									type="button" 
									style="float: right;"
								>
									삭제
								</button>
							</td>
						</tr>
						<tr>
							<td id="content${dto.comment_no }">${dto.content }</td>
						</tr>
					</table>
					<hr />
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" height="50" align="center">
				<a href="#comment" onclick="chooseCommentPage(1)"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#comment" onclick="chooseCommentPage(${pageNum })"><</a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${pageNum - 1 })"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${commentPageNumber == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#comment" onclick="chooseCommentPage(${i })">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum + 1 >= maxPagesCount }">
						<a href="#comment" onclick="chooseCommentPage(${maxPagesCount })">></a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${pageNum + 1 })">></a>
					</c:otherwise>
				</c:choose>
				<a href="#comment" onclick="chooseCommentPage(${maxPagesCount })">>></a>
			</td>
		</tr>
	</table>
</c:if>


<script>

$(document).ready(() => {
	$("#btnCommentSave").click(() => {
		commentSave();
	});
	
	$(".btnCommentDelete").click((event) => {
		const { 
			target: { value } 
		} = event;
		const content = document.querySelector(`#content\${value}`);

		if (content.children.length === 0) {
			const input = document.createElement("input");
			input.setAttribute("id", `input\${value}`);
			input.setAttribute("style", "float: right");
			input.setAttribute("placeholder", "비밀번호 입력")
			const confirm = document.createElement("button");
			confirm.innerText = '확인';
			confirm.setAttribute("type", "button");
			confirm.setAttribute("id", `confirm\${value}`);
			confirm.setAttribute("class", "confirm");
			confirm.setAttribute("style", "float: right");
			confirm.setAttribute("value", value);
			content.append(confirm);
			content.append(input);
			event.target.innerText = "취소";
		} else {
			const input = document.querySelector(`#input\${value}`);
			const confirm = document.querySelector(`#confirm\${value}`);
			input.remove();
			confirm.remove();
			event.target.innerText = "삭제";
		}
	});
	
	$(document).on("click", ".confirm", (event) => {
		const { 
			target: { value } 
		} = event;
		const password = document.querySelector(`#input\${value}`).value;
		commentDelete(value, password);
	})
});

function commentSave() {
	const param = {
			"commentPageNumber": $("#span_commentPageNumber").text(),
			"no": $("#span_no").text(),
			"comment_writer": $("#comment_writer").val(),
			"comment_passwd": $("#comment_passwd").val(),
			"comment_content": $("#comment_content").val()
	};
	const url = "${path}/board_servlet/commentWrite.do";
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			$("#span_commentPageNumber").text(${commentPageNumber});
			commentList();
		}
	});
}

function chooseCommentPage(commentPageNumber) {
	$("#span_commentPageNumber").text(commentPageNumber);
	commentList();
}

function commentDelete(comment_no, password) {
	const param = {
			"comment_no": comment_no,
			"passwd": password
	};
	const url = "${path}/board_servlet/commentDelete.do";
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			commentList();
		}
	})
}

</script>