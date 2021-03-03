<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<table border="1">
	<c:forEach var="i" begin="0" end="${loopNumforI}" step="1" >
		<tr>
			<c:forEach var="j" begin="${i * oneLineSize}" end="${(i * oneLineSize) + oneLineSize - 1 }" step="1" >
				<c:set var="dto" value="${list[j] }"></c:set>
				<c:set var="temp1" value="${fn:split(dto.product_img, ',')[0] }"></c:set>
				<c:set var="temp2" value="${fn:split(temp1, '|')[0] }"></c:set>
				<c:set var="temp3" value="${fn:split(temp1, '|')[1] }"></c:set>
				<c:choose>
					<c:when test="${j >= productListSize }">
						<td align="center" style="width:100px; height:100px;">없음</td>
					</c:when>
					<c:otherwise>
						<td>
							<table>
								<tr>
									<td>
										<a href="#">
											<img src="${path }/attach/product_img/${temp3}" style="width:100px; height:100px;"/>
										</a>
									</td>
								</tr>
								<tr>
									<td>${dto.name }</td>
								</tr>
								<tr>
									<td>${dto.price }</td>
								</tr>
							</table>
						</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</c:forEach>
</table>