<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="modal-header">
		<h3 class="modal-title">${writer}님의작성글</h3>
		<button type="button" class="btn-close" data-bs-dismiss="modal"
			aria-label="Close"></button>
	</div>
	<div class="modal-body">
		<!-- 게시글 테이블 -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${postList}">
					<tr>
						<td>${post.board_no}</td>
						<td>${post.title}</td>
						<td><fmt:formatDate value="${post.updated_at}"
							pattern="yyyy-MM-dd HH:mm" timeZone="UTC" /></td>
						<td><button type="button" class="btn btn-primary"
								onclick="goPost(${post.board_no})">이동</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary"
			data-bs-dismiss="modal">닫기</button>
	</div>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Bootstrap JS Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>