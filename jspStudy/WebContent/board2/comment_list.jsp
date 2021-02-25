<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>


<table align="center" width="95%">
	<tr>
		<td style="padding : 0 0 20 0;">				
			이름 : <input type="text" name="comment_writer" id="comment_writer" size="10" value="${cookName }" />
			비밀번호 : <input type="text" name="comment_passwd" id="comment_passwd" size="10" /><br>
			댓글 : <input type="text" name="comment_content" id="comment_content" size="40" />
			<button type="button" id="btnCommentSave" onclick="commentWrite()">확인</button>
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
				<a href="#comment" onclick="chooseCommentPage(1, ${no})"><<</a>
				<c:choose>
					<c:when test="${commentPageNumber - 1 <= 0 }">
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber }, ${no})"><</a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber - 1 }, ${no})"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${commentPageNumber == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#comment" onclick="chooseCommentPage(${i }, ${no})">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${commentPageNumber + 1 >= maxPagesCount }">
						<a href="#comment" onclick="chooseCommentPage(${maxPagesCount }, ${no})">></a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber + 1 }, ${no})">></a>
					</c:otherwise>
				</c:choose>
				<a href="#comment" onclick="chooseCommentPage(${maxPagesCount }, ${no})">>></a>
			</td>
		</tr>
	</table>
</c:if>


<script>

function commentWrite() {
	document.commentWriteForm.method = 'post';
	document.commentWriteForm.action = '${path}/board2_servlet/commentWrite.do';
	document.commentWriteForm.submit();
}

$(document).ready(() => {	
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

function chooseCommentPage(commentPageNumber, no) {
	let url = '';
	url += `${path}/board2_servlet/view.do?commentPageNumber=\${commentPageNumber}`;
	url += `&no=\${no}`;
	location.href = url;
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