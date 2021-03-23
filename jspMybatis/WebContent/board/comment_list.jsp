<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

comment_no : <span id="span_comment_no"></span>

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
	<table id="commentTable" align="center" width="95%">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td style="padding: 0 0 10 10;">
					<table align="center" style="width: 100%;">
						<tr>
							<td>
								<span id="writer${dto.comment_no }">${dto.writer }</span> 
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
								<button style="float: right;" type="button" onclick="modifyComment('${dto.comment_no}')" >수정</button>
							</td>
						</tr>
						<tr>
							<td>
								<span id="content${dto.comment_no }">${dto.content }</span>
								<div id="delete__container${dto.comment_no }" style="display: none; float: right;">
									<input id="input${dto.comment_no }" placeholder="비밀번호 입력">
									<button 
										type="button" 
										class="confirm" 
										value="${dto.comment_no }"
									>
										확인
									</button>
								</div>
							</td>
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
					<c:when test="${commentPageNumber - 1 <= 0 }">
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber })"><</a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber - 1 })"><</a>
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
					<c:when test="${commentPageNumber + 1 >= maxPagesCount }">
						<a href="#comment" onclick="chooseCommentPage(${maxPagesCount })">></a>
					</c:when>
					<c:otherwise>
						<a href="#comment" onclick="chooseCommentPage(${commentPageNumber + 1 })">></a>
					</c:otherwise>
				</c:choose>
				<a href="#comment" onclick="chooseCommentPage(${maxPagesCount })">>></a>
			</td>
		</tr>
	</table>
</c:if>


<script>

function modifyComment(no) {
	$("#span_comment_no").text(no);
	$("#comment_writer").val($("#writer" + no).text());
	$("#comment_content").val($("#content" + no).text().trim());
	$("#btnCommentSave").text("수정");
}

$(document).ready(() => {
	$("#btnCommentSave").click(() => {
		commentSave();
	});
	
	$("#commentTable").click((event) => {
		if (event.target.classList.contains("btnCommentDelete")) {
			const { 
				target: { value } 
			} = event;
			const deleteContainer = document.querySelector("#delete__container" + value);
			const {
				style: { display }
			} = deleteContainer;
			if (display === 'none') {
				deleteContainer.style.display = 'inline-block';
				event.target.innerText = '취소';
			} else {
				deleteContainer.style.display = 'none';
				event.target.innerText = '삭제';
			}
		}
	});
	
	$(".confirm").click((event) => {
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
			"comment_no" : $("#span_comment_no").text(),
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